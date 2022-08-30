package il.co.fibi.comm.mqbridge.data.decorators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.w3c.dom.Element;

import net.sf.cb2xml.def.Cb2xmlConstants;
import net.sf.cb2xml.def.Cb2xmlConstants.Justified;

@RequestScope
@Component("NUM")
public class NumericDecorator implements IDecorator {
	private int length;
	@SuppressWarnings("unused")
	private Justified justified;

	@Override
	public String removeDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		input = StringUtils.stripStart(input, "0");
		return input;
	}

	@Override
	public String addDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		input = StringUtils.leftPad(StringUtils.trimToEmpty(input), length, '0');
		return input;
	}

	@Override
	public void getAttributes(Element elem, String param) {
		length = DecoratorUtils.getAsUnsignedInt(elem.getAttribute(Cb2xmlConstants.STORAGE_LENGTH), 0);
		justified = Cb2xmlConstants.toJustified(elem.getAttribute(Cb2xmlConstants.JUSTIFIED));
	}
}
