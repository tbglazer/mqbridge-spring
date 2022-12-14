package il.co.fibi.comm.mqbridge.services;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicReference;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import co.elastic.apm.opentracing.ElasticApmTags;
import il.co.fibi.comm.mqbridge.constants.ibm.JmsConstants;
import il.co.fibi.comm.mqbridge.constants.ibm.MQConstants;
import il.co.fibi.comm.mqbridge.headers.BRMQRECEIVE;
import il.co.fibi.comm.mqbridge.headers.BRMQSEND;
import il.co.fibi.comm.mqbridge.headers.BRMQVECTORHEADER;
import il.co.fibi.comm.mqbridge.headers.MQCIH;
import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import lombok.extern.slf4j.Slf4j;

@Component
@RequestScope
@Slf4j
public class Mqbridge3270Service extends AbstractMqbridgeService {

	@Override
	public String getId() {
		return "3270";
	}

	@Override
	public Logger getLogger() {
		return log;
	}

	@Override
	public ServiceResponse send(final ServiceRequest request) {
		final Span span = GlobalTracer.get().buildSpan("send").withTag(ElasticApmTags.TYPE, "comm")
				.withTag(ElasticApmTags.SUBTYPE, "3270").start();
		final ServiceResponse response = new ServiceResponse();
		response.setStatus(-2);
		try {
			final String data = request.getData();
			final AtomicReference<BytesMessage> msg = new AtomicReference<>();
			jmsTemplate.send(requestQueue, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					try {
						BytesMessage bytesMsg = session.createBytesMessage();
						bytesMsg.setJMSMessageID(new String(MQConstants.MQMI_NONE));
						bytesMsg.setJMSCorrelationID(new String(MQConstants.MQCI_NEW_SESSION, LATIN_1));
						bytesMsg.setJMSReplyTo(session.createQueue(replyQueue));
						bytesMsg.setStringProperty(JmsConstants.JMS_IBM_FORMAT, MQConstants.MQFMT_CICS);
						bytesMsg.setStringProperty(JmsConstants.JMS_IBM_CHARACTER_SET, CHARSET);
						bytesMsg.setIntProperty(JmsConstants.JMS_IBM_ENCODING, ENCODING);
						bytesMsg.setIntProperty(JmsConstants.JMS_IBM_MSGTYPE, MQConstants.MQMT_REQUEST);
						bytesMsg.setIntProperty(JmsConstants.JMS_IBM_REPORT_EXPIRATION,
								MQConstants.MQRO_EXPIRATION_WITH_DATA);

						final MQCIH cicsHeader = new MQCIH();
						cicsHeader.setMqcih__strucid(MQConstants.MQCIH_STRUC_ID);
						cicsHeader.setMqcih__struclength(MQConstants.MQCIH_LENGTH_2);
						cicsHeader.setMqcih__transactionid(request.getId());
						cicsHeader.setMqcih__uowcontrol(MQConstants.MQCUOWC_ONLY);
						cicsHeader.setMqcih__version(MQConstants.MQCIH_VERSION_2);
						cicsHeader.setMqcih__facility(new String(MQConstants.MQCFAC_NONE, LATIN_1));
						cicsHeader.setMqcih__facilitykeeptime(0);
						cicsHeader.setMqcih__format(CSQCBDCI);
						cicsHeader.setMqcih__linktype(MQConstants.MQCLT_TRANSACTION);
						cicsHeader.setMqcih__startcode(MQConstants.MQCSC_START);
						cicsHeader.setMqcih__flags(MQConstants.MQIIH_PASS_EXPIRATION);

						final BRMQRECEIVE brReceiveHeader = new BRMQRECEIVE();
						brReceiveHeader.setBrmq__re__aid(new String(AID, LATIN_1));
						brReceiveHeader.setBrmq__re__buffer__indicator(NO);
						brReceiveHeader.setBrmq__re__data__len(request.getData().length());
						brReceiveHeader.setBrmq__re__transmit__send__areas(YES);

						final BRMQVECTORHEADER brVectorHeader = new BRMQVECTORHEADER();
						brVectorHeader.setBrmq__vector__length(brReceiveHeader.getSize() + data.length());
						brVectorHeader.setBrmq__vector__descriptor(RCV_VECTOR);
						brVectorHeader.setBrmq__vector__type(INBOUND);
						brVectorHeader.setBrmq__vector__version(VERSION);

						final byte[] brVecHdr = brVectorHeader.getBytes();
						final byte[] brRecHdr = brReceiveHeader.getBytes();
						System.arraycopy(brVecHdr, 0, brRecHdr, 0, brVecHdr.length);

						bytesMsg.writeBytes(cicsHeader.getBytes());
						bytesMsg.writeBytes(brRecHdr);
						bytesMsg.writeBytes(data.getBytes(LATIN_1));
						msg.set(bytesMsg);
						return bytesMsg;
					} catch (Exception e) {
						throw new JMSException(e.getMessage());
					}
				}
			});
			response.setKey(msg.get().getJMSMessageID() + ";0");
			response.setStatus(0);
			span.setTag(ElasticApmTags.RESULT, "success");
		} catch (final JMSException e) {
			span.setTag(ElasticApmTags.RESULT, "failure");
			final Exception le = e.getLinkedException();
			log.error("send failed: {}{}", e.getMessage(), ((le != null) ? (", reason:" + le.getMessage()) : ""));
		} finally {
			span.finish();
		}
		return response;
	}

	@Override
	public ServiceResponse receive(final ServiceRequest request) {
		final Span span = GlobalTracer.get().buildSpan("receive").withTag(ElasticApmTags.TYPE, "comm")
				.withTag(ElasticApmTags.SUBTYPE, "3270").start();
		final ServiceResponse response = new ServiceResponse();
		response.setStatus(-2);
		try {
			do {
				jmsTemplate.setReceiveTimeout(timeout);
				final Message msg = jmsTemplate.receiveSelected(replyQueue,
						JmsConstants.JMS_CORRELATIONID + "='" + request.getKey().split(";")[0] + "'");
				if (msg instanceof BytesMessage) {
					final BytesMessage bytesMsg = (BytesMessage) msg;
					if (((bytesMsg.getIntProperty(JmsConstants.JMS_IBM_MSGTYPE)) == MQConstants.MQMT_REPLY)
							&& (bytesMsg.getStringProperty(JmsConstants.JMS_IBM_FORMAT))
									.equals(MQConstants.MQFMT_CICS)) {
						final byte[] bmsg = new byte[(int) bytesMsg.getBodyLength()];
						bytesMsg.readBytes(bmsg, bmsg.length);
						String msgData = new String(bmsg, LATIN_1);
						final MQCIH cicsHeader = new MQCIH();
						cicsHeader.setBytes(msgData.substring(0, cicsHeader.getSize()).getBytes(LATIN_1));
						checkCicsHeaderForErrors(cicsHeader);
						msgData = msgData
								.substring(cicsHeader.getSize() + Integer.valueOf(request.getKey().split(";")[1]));
						final BRMQVECTORHEADER brHeader = new BRMQVECTORHEADER();
						brHeader.setBytes(msgData.substring(0, brHeader.getSize()).getBytes(LATIN_1));
						final BRMQSEND brSend = new BRMQSEND();
						brSend.setBytes(msgData.substring(0, brSend.getSize()).getBytes(LATIN_1));
						final int dataLen = Math.min(brSend.getBrmq__se__data__len(),
								msgData.length() - brSend.getSize()) - FMH.length();
						response.setData(msgData.substring(brSend.getSize() + FMH.length()).substring(0, dataLen));
						response.setStatus(0);
						if (msgData.substring(brHeader.getBrmq__vector__length()).length() > 0) {
							response.setKey(request.getKey().split(";")[0] + ";" + (Integer
									.valueOf(request.getKey().split(";")[1] + brHeader.getBrmq__vector__length())));
							span.setTag(ElasticApmTags.RESULT, "success");
							break;
						}
					} else {
						span.setTag(ElasticApmTags.RESULT, "failure");
						log.error("receive failed, unsupported message type received: {}", msg.getClass().getName());
					}
				} else if (msg == null) {
					span.setTag(ElasticApmTags.RESULT, "failure");
					response.setStatus(-1);
					break;
				}
				msg.acknowledge();
			} while (response.getStatus() == -2);
		} catch (UnsupportedEncodingException | MqbridgeException e) {
			span.setTag(ElasticApmTags.RESULT, "failure");
			log.error("receive failed: {}", e.getMessage());
		} catch (final JMSException e) {
			span.setTag(ElasticApmTags.RESULT, "failure");
			final Exception le = e.getLinkedException();
			log.error("receive failed: {}{}", e.getMessage(), ((le != null) ? (", reason:" + le.getMessage()) : ""));
		} finally {
			span.finish();
		}
		return response;
	}
}
