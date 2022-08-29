package il.co.fibi.comm.mqbridge.data.decorators;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.context.annotation.Scope;
import org.w3c.dom.Element;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Scope(value= )
@Named("BIN")
public class BinaryDecorator  implements IDecorator {

	@Override
	public String removeDecoration(Element elem, String param, String input) {
		return Hex.encodeHexString(input.getBytes(LATIN1));
	}

	@Override
	public String addDecoration(Element elem, String param, String input) {
		try {
			return new String(Hex.decodeHex(input), LATIN1);
		} catch (DecoderException e) {
			return input;
		}
	}
}
