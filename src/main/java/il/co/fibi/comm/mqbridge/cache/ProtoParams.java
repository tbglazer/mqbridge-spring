package il.co.fibi.comm.mqbridge.cache;

import java.io.StringReader;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@RequestScoped
public class ProtoParams {
	@Inject
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
		String s = (String) proto.get(trxid);
		if (s != null) {
			JsonReader reader = Json.createReader(new StringReader(s));
			JsonObject o = reader.readObject();
			protocol = PROTOCOL.valueOf(o.getString("protocol"));
			cicsid = o.getString("cicsid", "");
			timeout = o.getInt("timeout", 10);
			progname = o.getString("progname", "");
			runasid = o.getString("runasid", "");
			carealen = o.getInt("carealen", -1);
			respofst = o.getInt("respofst", -1);
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
