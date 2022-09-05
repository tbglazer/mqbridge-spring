package il.co.fibi.comm.mqbridge.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import il.co.fibi.comm.mqbridge.cache.ProtoParams;
import il.co.fibi.comm.mqbridge.constants.ibm.MQConstants;
import il.co.fibi.comm.mqbridge.headers.MQCIH;

public abstract class AbstractMqbridgeService {

	static protected final byte[] AID = { 0x7d, ' ', ' ', ' ' };
	static protected final String RCV_VECTOR = "0402";
	static protected final String INBOUND = "I ";
	static protected final String VERSION = "0000";
	static protected final String YES = "Y ";
	static protected final String NO = "N ";
	static protected final String CSQCBDCI = "CSQCBDCI";
	static protected final String CHARSET = "424";
	static protected final String LATIN_1 = "8859_1";
	static protected final String FMH = "\306\324\310"; // FMH
	static protected final int PROGNAME_LEN = 8;
	static protected final int ENCODING = 785;

	@Autowired
	protected JmsTemplate jmsTemplate;

	protected String requestQueue;
	protected String replyQueue;
	protected int timeout;
	protected int priority;

	public AbstractMqbridgeService init(ProtoParams params) {
		setTimeout(params.getTimeout() * 1000);
		return this;
	}

	public String getId() {
		return null;
	}

	public abstract ServiceResponse send(ServiceRequest request);

	public abstract ServiceResponse receive(ServiceRequest request);

	public abstract Logger getLogger();

	public void setRequestQueue(String requestQueue) {
		this.requestQueue = requestQueue;
	}

	public void setReplyQueue(String replyQueue) {
		this.replyQueue = replyQueue;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Analyze the CICS header for errors
	 * 
	 * @param cicsHeader
	 * @throws DSECCException
	 */
	protected void checkCicsHeaderForErrors(MQCIH cicsHeader) throws MqbridgeException {
		int returncode = cicsHeader.getMqcih__returncode();
		switch (returncode) {
		case MQConstants.MQCRC_OK: {
			return;
		}
		case MQConstants.MQCRC_BRIDGE_ERROR: {
			int reason = cicsHeader.getMqcih__reason();
			MqbridgeException e = new MqbridgeException("Bridge error, reason:" + reason);
			getLogger().error("Mqbridge operation failed: {}", e.getMessage());
			throw e;
		}
		case MQConstants.MQCRC_MQ_API_ERROR: {
			String function = cicsHeader.getMqcih__function();
			int compcode = cicsHeader.getMqcih__compcode();
			int reason = cicsHeader.getMqcih__reason();
			MqbridgeException e = new MqbridgeException(
					"Api error, function:" + function + ",compcode:" + compcode + ",reason:" + reason);
			getLogger().error("Mqbridge operation failed: {}", e.getMessage());
			throw e;
		}
		case MQConstants.MQCRC_BRIDGE_TIMEOUT: {
			String function = cicsHeader.getMqcih__function();
			int compcode = cicsHeader.getMqcih__compcode();
			int reason = cicsHeader.getMqcih__reason();
			MqbridgeException e = new MqbridgeException(
					"Bridge timeout, function:" + function + ",compcode:" + compcode + ",reason:" + reason);
			getLogger().error("Mqbridge operation failed: {}",  e.getMessage());
			throw e;
		}
		case MQConstants.MQCRC_CICS_EXEC_ERROR: {
			String function = cicsHeader.getMqcih__function();
			int compcode = cicsHeader.getMqcih__compcode();
			int reason = cicsHeader.getMqcih__reason();
			MqbridgeException e = new MqbridgeException(
					"Cics exec error, function:" + function + ",compcode:" + compcode + ",reason:" + reason);
			getLogger().error("Mqbridge operation failed: {}", e.getMessage());
			throw e;
		}
		case MQConstants.MQCRC_SECURITY_ERROR: {
			String function = cicsHeader.getMqcih__function();
			int compcode = cicsHeader.getMqcih__compcode();
			int reason = cicsHeader.getMqcih__reason();
			MqbridgeException e = new MqbridgeException(
					"Security error, function:" + function + ",compcode:" + compcode + ",reason:" + reason);
			getLogger().error("Mqbridge operation failed: {}", e.getMessage());
			throw e;
		}
		case MQConstants.MQCRC_PROGRAM_NOT_AVAILABLE: {
			String function = cicsHeader.getMqcih__function();
			int compcode = cicsHeader.getMqcih__compcode();
			int reason = cicsHeader.getMqcih__reason();
			MqbridgeException e = new MqbridgeException(
					"Program not available, function:" + function + ",compcode:" + compcode + ",reason:" + reason);
			getLogger().error("Mqbridge operation failed: {}",  e.getMessage());
			throw e;
		}
		case MQConstants.MQCRC_TRANSID_NOT_AVAILABLE: {
			String function = cicsHeader.getMqcih__function();
			int compcode = cicsHeader.getMqcih__compcode();
			int reason = cicsHeader.getMqcih__reason();
			MqbridgeException e = new MqbridgeException(
					"Transid not available, function:" + function + ",compcode:" + compcode + ",reason:" + reason);
			getLogger().error("Mqbridge operation failed: {}", e.getMessage());
			throw e;
		}
		case MQConstants.MQCRC_BRIDGE_ABEND: {
			String abendcode = cicsHeader.getMqcih__abendcode();
			MqbridgeException e = new MqbridgeException("Bridge abend, abendcode:" + abendcode);
			getLogger().error("Mqbridge operation failed: {}", e.getMessage());
			throw e;
		}
		case MQConstants.MQCRC_APPLICATION_ABEND: {
			String abendcode = cicsHeader.getMqcih__abendcode();
			MqbridgeException e = new MqbridgeException("Application abend, abendcode:" + abendcode);
			getLogger().error("Mqbridge operation failed: {}", e.getMessage());
			throw e;
		}
		default: {
			MqbridgeException e = new MqbridgeException("Unknown return code, returncode:" + returncode);
			getLogger().error("Mqbridge operation failed:", e.getMessage());
			throw e;
		}
		}
	}
}
