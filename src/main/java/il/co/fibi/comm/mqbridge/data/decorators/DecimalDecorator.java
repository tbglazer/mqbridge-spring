package il.co.fibi.comm.mqbridge.data.decorators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.w3c.dom.Element;

import net.sf.cb2xml.def.Cb2xmlConstants;
import net.sf.cb2xml.def.Cb2xmlConstants.SignClause;

@Component
@RequestScope
public class DecimalDecorator implements IDecorator {
	private int length, scale;
	private SignClause sign;

	private enum DecimalParam implements DecoratorUtils.IGetName {
		SHOW_ZERO("SHZ"), SHOW_POSITIVE_SIGN("SPS"), ADD_POSITIVE_SIGN("APS");

		private final String name;

		private DecimalParam(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public static Optional<DecimalParam> toDecimalParam(String name) {
			return Arrays.asList(DecimalParam.values()).stream().filter(p -> p.getName().equals(name)).findFirst();
		}

	};

	List<DecimalParam> params;

	@Override
	public String getId() {
		return "DEC";
	}
	
	@Override
	public String removeDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		if (sign == SignClause.NO_SIGN_CLAUSE || sign == SignClause.SIGN_LEADING_SEPARATE) {
			boolean signed = StringUtils.startsWithAny(input, "+", "-");
			boolean negative = input.startsWith("-");
			input = StringUtils.substring(input, signed ? 1 : 0);
			if (!StringUtils.containsOnly(input, '0') || params.contains(DecimalParam.SHOW_ZERO)) {
				input = StringUtils.overlay(input, ".", input.length() - scale, input.length() - scale);
				input = StringUtils.stripStart(input, "0");
				if (input.startsWith(".")) {
					input = StringUtils.overlay(input, "0", 0, 0);
				}
				if (negative) {
					input = StringUtils.overlay(input, "-", 0, 0);
				}
				input = StringUtils.stripEnd(input, ".");
			} else {
				input = "";
			}
		}
		return input;
	}

	@Override
	public String addDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		return StringUtils.leftPad(input, length, '0');
	}

	@Override
	public void getAttributes(Element elem, String param) {
		length = DecoratorUtils.getAsUnsignedInt(elem.getAttribute(Cb2xmlConstants.STORAGE_LENGTH), 0);
		scale = DecoratorUtils.getAsUnsignedInt(elem.getAttribute(Cb2xmlConstants.SCALE), 0);
		sign = Cb2xmlConstants.toSignClause(elem.getAttribute(Cb2xmlConstants.SIGN_CLAUSE));
		params = new ArrayList<>(3);
		if (param != null) {
			Arrays.asList(StringUtils.split(param, PARAM_SEPARATOR)).stream().forEach(p -> {
				Optional<DecimalParam> dp = DecimalParam.toDecimalParam(p.toUpperCase());
				if (dp.isPresent())
					params.add(dp.get());
			});
		}
	}
}