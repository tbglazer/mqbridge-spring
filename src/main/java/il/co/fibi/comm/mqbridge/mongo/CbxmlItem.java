package il.co.fibi.comm.mqbridge.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cbxml")
public class CbxmlItem {
	@Id
	private String id;
	private String val;

	public String getVal() {
		return val;
	}

	public CbxmlItem(String id, String val) {
		super();
		this.id = id;
		this.val = val;
	}
}
