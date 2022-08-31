package il.co.fibi.comm.mqbridge.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class MqbridgeContainerService extends MqbridgeDplService {

	@Override
	public String getId() {
		return "CONT";
	}
	
	@Override
	protected byte[] getPadBytes(String param) {
		return new byte[0];
	}

	@Override
	protected String extractData(String data) {
		return data.substring(PROGNAME_LEN + FMH.length());
	}
}
