package il.co.fibi.comm.mqbridge.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("proto")
public class ProtolItem {
	@Id
	private String id;
	private String val;

	public String getVal() {
		return val;
	}

	public ProtolItem(String id, String val) {
		super();
		this.id = id;
		this.val = val;
	}
}
