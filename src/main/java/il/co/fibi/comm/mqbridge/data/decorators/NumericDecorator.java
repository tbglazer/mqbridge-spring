package il.co.fibi.comm.mqbridge.data.decorators;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import net.sf.cb2xml.def.Cb2xmlConstants;
import net.sf.cb2xml.def.Cb2xmlConstants.Justified;

@RequestScoped
@Named("NUM")
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
