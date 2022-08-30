package il.co.fibi.comm.mqbridge.rest;

import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;

import com.ibm.icu.charset.CharsetICU;

import il.co.fibi.comm.mqbridge.cache.Cache;
import il.co.fibi.comm.mqbridge.cache.CacheType;
import il.co.fibi.comm.mqbridge.cache.ICache;
import il.co.fibi.comm.mqbridge.data.MainframeConvertor;
import il.co.fibi.comm.mqbridge.service.ServiceResponse;

@RequestScope
public class MqbridgeResponse {
	@Autowired
	@Cache(CacheType.CBXML)
	ICache cbxml;
	@Autowired
	MainframeConvertor convertor;
	private String response; // response
	private long status; // status

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public MqbridgeResponse fromServiceResponse(String trxid, ServiceResponse resp) {
		this.status = resp.getStatus();
		if (status == 0) {
			JSONObject res;
			if (resp.getData() != null) {
				String resid = new String(resp.getData().substring(0, 2).getBytes(StandardCharsets.ISO_8859_1),
						CharsetICU.forName("Cp037"));
				String data = convertor.data2xml(resp.getData(), (String) cbxml.get(trxid.concat("_").concat(resid)));
				res = XML.toJSONObject(data, true).getJSONObject("copybook")
						.getJSONObject(trxid.concat("_").concat(resid));
			} else {
				res = new JSONObject();
			}
			res.putOnce("key", resp.getKey());
			this.response = res.toString();
		}
		return this;
	}
}
