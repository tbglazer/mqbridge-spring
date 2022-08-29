package il.co.fibi.comm.mqbridge.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component("CONT")
public class MqbridgeContainerService extends MqbridgeDplService {

	@Override
	protected byte[] getPadBytes(String param) {
		return new byte[0];
	}

	@Override
	protected String extractData(String data) {
		return data.substring(PROGNAME_LEN + FMH.length());
	}
}
