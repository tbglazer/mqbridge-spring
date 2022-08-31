package il.co.fibi.comm.mqbridge.services;

public class ServiceResponse {
	private String data;    // response data
	private String key;     // message key
	private long status;      // status    
	
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
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
}
