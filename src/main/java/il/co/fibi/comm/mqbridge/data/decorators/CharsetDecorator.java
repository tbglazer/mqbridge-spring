package il.co.fibi.comm.mqbridge.data.decorators;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.w3c.dom.Element;

import com.ibm.icu.charset.CharsetICU;

@Component
@RequestScope
public class CharsetDecorator implements IDecorator {
	public static final String DEFAULT = "CP424";
	public static final String OLDCODE = "CP037";
	public static final String NEWCODE = "CP424";

	@Override
	public String getId() {
		return "ENC";
	}
	
	@Override
	public String removeDecoration(Element elem, String param, String input) {
		return new String(input.getBytes(LATIN1), CharsetICU.forName(param));
	}

	@Override
	public String addDecoration(Element elem, String param, String input) {
		return new String(input.getBytes(CharsetICU.forName(param)), LATIN1);
	}
}
