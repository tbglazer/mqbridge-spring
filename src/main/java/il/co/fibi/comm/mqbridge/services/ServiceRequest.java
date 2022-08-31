package il.co.fibi.comm.mqbridge.services;

public class ServiceRequest {
	private String id;      // transaction id 
	private String data;    // request data
	private String key;     // message key
	
	public ServiceRequest(String id, String data, String key) {
		this.id = id;
		this.data = data;
		this.key = key;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
