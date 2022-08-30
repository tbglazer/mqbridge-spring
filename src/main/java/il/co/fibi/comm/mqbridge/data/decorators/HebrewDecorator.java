package il.co.fibi.comm.mqbridge.data.decorators;

import java.util.Arrays;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.w3c.dom.Element;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.Bidi;
import com.ibm.icu.text.BidiTransform;

import net.sf.cb2xml.def.Cb2xmlConstants;
import net.sf.cb2xml.def.Cb2xmlConstants.Justified;

@Component
@RequestScope
public class HebrewDecorator implements IDecorator {
	private int length;
	private Justified justified;
	private String charset;

	@Override
	public String getId() {
		return "HEB";
	}
	
	@Override
	public String removeDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		input = StringUtils.substringBefore(input, String.valueOf(CharUtils.NUL));
		if (justified == Justified.RIGHT) {
			input = StringUtils.reverseDelimited(input, CharUtils.LF);
		}
		return StringUtils.join(Arrays.asList(StringUtils.split(input, CharUtils.LF)).stream()
				.map(line -> convertToHebrew(line, true, charset)).toArray(String[]::new));
	}

	@Override
	public String addDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		if (justified == Justified.RIGHT) {
			input = StringUtils.reverseDelimited(input, CharUtils.LF);
		}
		return StringUtils
				.leftPad(
						StringUtils.join(Arrays.asList(StringUtils.split(input, CharUtils.LF)).stream()
								.map(line -> convertFromHebrew(line, true, charset)).toArray(String[]::new)),
						length, ' ');
	}

	@Override
	public void getAttributes(Element elem, String param) {
		length = DecoratorUtils.getAsUnsignedInt(elem.getAttribute(Cb2xmlConstants.STORAGE_LENGTH), 0);
		justified = Cb2xmlConstants.toJustified(elem.getAttribute(Cb2xmlConstants.JUSTIFIED));
		charset = StringUtils.split(param, ',')[0];
	}

	private static String convertToHebrew(String input, boolean reverse, String charset) {
		if (charset == CharsetDecorator.OLDCODE) {
			input = StringUtils.replaceChars(input, "&abcdefghijklmnopqrstuvwxyz~", "אבגדהוזטחיכךלמםנןסעפפצץקרשת ");
		}
		if (reverse && Bidi.requiresBidi(input.toCharArray(), 0, input.length())) {
			input = new com.ibm.icu.text.BidiTransform().transform(input, Bidi.LTR, BidiTransform.Order.VISUAL,
					Bidi.RTL, BidiTransform.Order.LOGICAL, BidiTransform.Mirroring.ON,
					ArabicShaping.DIGITS_NOOP | ArabicShaping.LETTERS_NOOP);
		}
		return input;
	}

	private static String convertFromHebrew(String input, boolean reverse, String charset) {
		if (reverse && Bidi.requiresBidi(input.toCharArray(), 0, input.length())) {
			input = new com.ibm.icu.text.BidiTransform().transform(input, Bidi.RTL, BidiTransform.Order.LOGICAL,
					Bidi.LTR, BidiTransform.Order.VISUAL, BidiTransform.Mirroring.ON,
					ArabicShaping.DIGITS_NOOP | ArabicShaping.LETTERS_NOOP);
			if (charset == CharsetDecorator.OLDCODE) {
				input = StringUtils.replaceChars(input, "אבגדהוזטחיכךלמםנןסעפפצץקרשת", "&abcdefghijklmnopqrstuvwxyz");
			}
		}
		return input;
	}
}
