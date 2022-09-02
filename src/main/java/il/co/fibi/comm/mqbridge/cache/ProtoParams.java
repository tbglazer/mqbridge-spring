package il.co.fibi.comm.mqbridge.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RequestScope
public class ProtoParams {
	@Autowired
	@Cache(CacheType.PROTO)
	ICache proto;

	private ProtocolType protocol; // the protocol
	private String cicsid; // the cics id
	private int timeout; // timeout in secs
	private String progname; // grogram name
	private String runasid; // cics transaction id
	private int carealen; // commarea length
	private int respofst; // response offset

	public ProtocolType getProtocol() {
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
		String val = proto.getValue(trxid);
		if (val != null) {
			JsonNode o = null;
			try {
				o = new ObjectMapper().readTree(val);
			} catch (Exception e) {
			}
			protocol = ProtocolType.valueOf(o.get("protocol").asText());
			cicsid = o.get("cicsid") != null ? o.get("cicsid").asText() : "";
			timeout = o.get("timeout") != null ? o.get("timeout").asInt() : 10;
			progname = o.get("progname") != null ? o.get("progname").asText() : "";
			runasid = o.get("runasid") != null ? o.get("runasid").asText() : "";
			carealen = o.get("carealen") != null ? o.get("carealen").asInt() : -1;
			respofst = o.get("respofst") != null ? o.get("respofst").asInt() : -1;
		} else {
			protocol = ProtocolType.L3270;
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
