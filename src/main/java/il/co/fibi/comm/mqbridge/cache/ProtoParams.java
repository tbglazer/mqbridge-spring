package il.co.fibi.comm.mqbridge.cache;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import il.co.fibi.comm.mqbridge.mongo.ProtolItem;

@Component
@RequestScope
public class ProtoParams {
	@Autowired
	@Cache(CacheType.PROTO)
	ICache proto;

	@XmlEnum
	public enum PROTOCOL {
		@XmlEnumValue("3270")
		L3270, @XmlEnumValue("DPL")
		DPL, @XmlEnumValue("CONT")
		CONT
	};

	private PROTOCOL protocol; // the protocol
	private String cicsid; // the cics id
	private int timeout; // timeout in secs
	private String progname; // grogram name
	private String runasid; // cics transaction id
	private int carealen; // commarea length
	private int respofst; // response offset

	public PROTOCOL getProtocol() {
		return protocol;
	}

	public String getCicsid() {
		return cicsid;
	}

	public int getTimeout() {
		return timeout;
	}

	public String getProgname() {
		return progname;
	}

	public String getRunasid() {
		return runasid;
	}

	public int getCarealen() {
		return carealen;
	}

	public int getRespofst() {
		return respofst;
	}

	public ProtoParams build(String trxid) {
		ProtolItem item = (ProtolItem) proto.get(trxid);
		if (item != null) {
			JsonNode o = null;
			try {
				o = new ObjectMapper().readTree(item.getVal());
			} catch (Exception e) {
			}
			protocol = PROTOCOL.valueOf(o.get("protocol").asText());
			cicsid = o.get("cicsid") != null ? o.get("cicsid").asText() : "";
			timeout = o.get("timeout") != null ? o.get("timeout").asInt() : 10;
			progname = o.get("progname") != null ? o.get("progname").asText() : "";
			runasid = o.get("runasid") != null ? o.get("runasid").asText() : "";
			carealen = o.get("carealen") != null ? o.get("carealen").asInt() : -1;
			respofst = o.get("respofst") != null ? o.get("respofst").asInt() : -1;
		} else {
			protocol = PROTOCOL.L3270;
			cicsid = "";
			timeout = 10;
			progname = "";
			runasid = "";
			carealen = -1;
			respofst = -1;

		}
		return this;
	}
}
