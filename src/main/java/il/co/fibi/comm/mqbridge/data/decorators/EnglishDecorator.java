package il.co.fibi.comm.mqbridge.data.decorators;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.w3c.dom.Element;

import net.sf.cb2xml.def.Cb2xmlConstants;
import net.sf.cb2xml.def.Cb2xmlConstants.Justified;

@RequestScope
@Component("ENG")
public class EnglishDecorator implements IDecorator {
	private int length;
	@SuppressWarnings("unused")
	private Justified justified;
	private boolean blankWhenZero;

	@Override
	public String removeDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		input = StringUtils.substringBefore(input, String.valueOf(CharUtils.NUL));
		if (blankWhenZero && StringUtils.containsOnly(input, '0'))
			return "";
		return StringUtils.stripEnd(input, null);
	}

	@Override
	public String addDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		input = StringUtils.rightPad(input, length, ' ');
		return input;
	}

	@Override
	public void getAttributes(Element elem, String param) {
		length = DecoratorUtils.getAsUnsignedInt(elem.getAttribute(Cb2xmlConstants.STORAGE_LENGTH), 0);
		justified = Cb2xmlConstants.toJustified(elem.getAttribute(Cb2xmlConstants.JUSTIFIED));
		blankWhenZero = DecoratorUtils.getAsBoolean(elem.getAttribute(Cb2xmlConstants.BLANK_WHEN_ZERO), false);
	}
}
