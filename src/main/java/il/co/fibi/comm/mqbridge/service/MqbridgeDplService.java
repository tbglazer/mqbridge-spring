package il.co.fibi.comm.mqbridge.service;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.ibm.etools.marshall.util.MarshallStringUtils;

import co.elastic.apm.opentracing.ElasticApmTags;
import il.co.fibi.comm.mqbridge.cache.ProtoParams;
import il.co.fibi.comm.mqbridge.constants.ibm.JmsConstants;
import il.co.fibi.comm.mqbridge.constants.ibm.MQConstants;
import il.co.fibi.comm.mqbridge.headers.MQCIH;
import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;

@RequestScope
@Component("DPL")
public class MqbridgeDplService extends MqbridgeService {
	private final Logger log = Logger.getLogger(MqbridgeDplService.class.getName());
	private String progname;
	private String runasid;
	private int carealen;
	private int respofst;

	public void setProgname(String progname) {
		this.progname = progname;
	}

	public void setRunasid(String runasid) {
		this.runasid = runasid;
	}

	public void setCarealen(int carealen) {
		this.carealen = carealen;
	}

	public void setRespofst(int respofst) {
		this.respofst = respofst;
	}

	@Override
	public Logger getLogger() {
		return log;
	}

	@Override
	public MqbridgeService init(ProtoParams params) {
		super.init(params);
		setProgname(params.getProgname());
		setRunasid(params.getRunasid());
		setCarealen(params.getCarealen());
		setRespofst(params.getRespofst());
		return this;
	}

	@Override
	public ServiceResponse send(ServiceRequest request) {
		final Span span = GlobalTracer.get().buildSpan("send").withTag(ElasticApmTags.TYPE, "comm")
				.withTag(ElasticApmTags.SUBTYPE, "dpl").start();
		final ServiceResponse response = new ServiceResponse();
		response.setStatus(-2);
		try (QueueConnection queueConn = queueConnFactory.createQueueConnection();
				QueueSession queueSess = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				QueueSender queueSender = queueSess.createSender(requestQueue);
			) {
			queueSender.setTimeToLive(timeout);
			queueSender.setPriority(priority);
			queueConn.start();

			BytesMessage bytesMsg = queueSess.createBytesMessage();
			bytesMsg.setJMSMessageID(new String(MQConstants.MQMI_NONE));
			bytesMsg.setJMSCorrelationID(new String(MQConstants.MQCI_NEW_SESSION, LATIN_1));
			bytesMsg.setJMSReplyTo(replyQueue);
			bytesMsg.setStringProperty(JmsConstants.JMS_IBM_FORMAT, MQConstants.MQFMT_CICS);
			bytesMsg.setStringProperty(JmsConstants.JMS_IBM_CHARACTER_SET, CHARSET);
			bytesMsg.setIntProperty(JmsConstants.JMS_IBM_ENCODING, ENCODING);
			bytesMsg.setIntProperty(JmsConstants.JMS_IBM_MSGTYPE, MQConstants.MQMT_REQUEST);
			bytesMsg.setIntProperty(JmsConstants.JMS_IBM_REPORT_EXPIRATION, MQConstants.MQRO_EXPIRATION_WITH_DATA);

			MQCIH cicsHeader = new MQCIH();
			cicsHeader.setMqcih__strucid(MQConstants.MQCIH_STRUC_ID);
			cicsHeader.setMqcih__struclength(MQConstants.MQCIH_LENGTH_2);
			cicsHeader.setMqcih__uowcontrol(MQConstants.MQCUOWC_ONLY);
			cicsHeader.setMqcih__version(MQConstants.MQCIH_VERSION_2);
			cicsHeader.setMqcih__facility(new String(MQConstants.MQCFAC_NONE, LATIN_1));
			cicsHeader.setMqcih__facilitykeeptime(0);
			cicsHeader.setMqcih__format(MQConstants.MQFMT_NONE);
			cicsHeader.setMqcih__linktype(MQConstants.MQCLT_PROGRAM);
			cicsHeader.setMqcih__transactionid(runasid);
			cicsHeader.setMqcih__flags(MQConstants.MQCIH_PASS_EXPIRATION);

			bytesMsg.writeBytes(cicsHeader.getBytes());
			byte bProgName[] = new byte[PROGNAME_LEN];
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(progname, bProgName, 0, CHARSET, PROGNAME_LEN,
					MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
			bytesMsg.writeBytes(bProgName);
			bytesMsg.writeBytes(request.getData().getBytes(LATIN_1));
			byte[] bPadBytes = getPadBytes(request.getData());
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer("", bPadBytes, 0, CHARSET, bPadBytes.length,
					MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
			bytesMsg.writeBytes(bPadBytes);
			queueSender.send(bytesMsg);
			response.setKey(bytesMsg.getJMSMessageID());
			response.setStatus(0);
			span.setTag(ElasticApmTags.RESULT, "success");
		} catch (UnsupportedEncodingException e) {
			span.setTag(ElasticApmTags.RESULT, "failure");
			log.severe("send failed:" + e.getMessage());
		} catch (JMSException e) {
			span.setTag(ElasticApmTags.RESULT, "failure");
			Exception le = e.getLinkedException();
			log.severe("send failed:" + e.getMessage() + ((le != null) ? (", reason:" + le.getMessage()) : ""));
		} finally {
			span.finish();
		}
		return response;
	}

	@Override
	public ServiceResponse receive(ServiceRequest request) {
		final Span span = GlobalTracer.get().buildSpan("receive").withTag(ElasticApmTags.TYPE, "comm")
				.withTag(ElasticApmTags.SUBTYPE, "dpl").start();
		final ServiceResponse response = new ServiceResponse();
		response.setStatus(-2);
		try (QueueConnection queueConn = queueConnFactory.createQueueConnection();
				QueueSession queueSess = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				QueueReceiver queueReceiver = queueSess.createReceiver(replyQueue,
						JmsConstants.JMS_CORRELATIONID + "='" + request.getKey().split(";")[0] + "'");) {
			queueConn.start();
			do {
				Message msg = queueReceiver.receive(timeout);
				if (msg instanceof BytesMessage) {
					BytesMessage bytesMsg = (BytesMessage) msg;
					if (((bytesMsg.getIntProperty(JmsConstants.JMS_IBM_MSGTYPE)) == MQConstants.MQMT_REPLY)
							&& (bytesMsg.getStringProperty(JmsConstants.JMS_IBM_FORMAT))
									.equals(MQConstants.MQFMT_CICS)) {
						byte[] bmsg = new byte[(int) bytesMsg.getBodyLength()];
						bytesMsg.readBytes(bmsg, bmsg.length);
						String msgData = new String(bmsg, LATIN_1);
						MQCIH cicsHeader = new MQCIH();
						cicsHeader.setBytes(msgData.substring(0, cicsHeader.getSize()).getBytes(LATIN_1));
						checkCicsHeaderForErrors(cicsHeader);
						msgData = msgData.substring(cicsHeader.getSize());
						response.setData(extractData(msgData));
						response.setStatus(0);
						span.setTag(ElasticApmTags.RESULT, "success");
						break;
					} else {
						span.setTag(ElasticApmTags.RESULT, "failure");
						log.severe("receive failed, unsupported message type received:" + msg.getClass().getName());
					}
				} else if (msg == null) {
					span.setTag(ElasticApmTags.RESULT, "failure");
					response.setStatus(-1);
					break;
				}
			} while (response.getStatus() == -2);
		} catch (UnsupportedEncodingException | MqbridgeException e) {
			span.setTag(ElasticApmTags.RESULT, "failure");
			log.severe("receive failed:" + e.getMessage());
		} catch (JMSException e) {
			span.setTag(ElasticApmTags.RESULT, "failure");
			Exception le = e.getLinkedException();
			log.severe("receive failed:" + e.getMessage() + ((le != null) ? (", reason:" + le.getMessage()) : ""));
		} finally {
			span.finish();
		}
		return response;
	}

	protected byte[] getPadBytes(String param) {
		return new byte[carealen - param.length()];
	}

	protected String extractData(String data) {
		return data.substring(respofst - 1 + PROGNAME_LEN + FMH.length());
	}
}
