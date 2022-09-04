package il.co.fibi.comm.mqbridge.rest;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import il.co.fibi.comm.mqbridge.cache.Cache;
import il.co.fibi.comm.mqbridge.cache.CacheType;
import il.co.fibi.comm.mqbridge.cache.ICache;
import il.co.fibi.comm.mqbridge.data.MainframeConvertor;
import il.co.fibi.comm.mqbridge.services.ServiceRequest;

@Component
@RequestScope
public class MqbridgeRequest {
	@Autowired MainframeConvertor convertor;
	@Autowired @Cache(CacheType.CBXML) ICache cbxml;

	private String trxid; // transaction id
	private JSONObject body; // request body
	private String key; // message key

	public String getTrxid() {
		return trxid;
	}

	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}

	public JSONObject getBody() {
		return body;
	}

	public void setBody(JSONObject body) {
		this.body = body;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public MqbridgeRequest setup(String trxid, JSONObject body, String key) {
		setTrxid(trxid);
		setBody(body);
		setKey(key);
		return this;
	}
	
	public ServiceRequest toServiceRequest() {
		String data = (body != null ? convertor.xml2data(xmlFromJsonBody(trxid, body), cbxml.getValue(trxid)) : null);
		return new ServiceRequest(trxid, data, key);
	}

	private String xmlFromJsonBody(String trxid, JSONObject body) {
		StringBuffer sb = new StringBuffer();
		return sb.append("<copybook><")
		  .append(trxid)
		  .append(">")
		  .append(XML.toString(body))
		  .append("</")
		  .append(trxid)
		  .append("></copybook>")
		  .toString();
	}
	
}
