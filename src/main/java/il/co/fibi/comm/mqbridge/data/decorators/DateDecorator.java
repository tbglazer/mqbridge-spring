package il.co.fibi.comm.mqbridge.data.decorators;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;

import com.ibm.icu.text.SimpleDateFormat;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import net.sf.cb2xml.def.Cb2xmlConstants;

@RequestScoped
@Named("DAT")
public class DateDecorator implements IDecorator {
	private int length;

	private enum DateParam implements DecoratorUtils.IGetName {
		SOURCE_FORMAT("SF", "ddMMyyyy"), TARGET_FORMAT("TF", "ddMMyyyy");

		private final String name;
		private String format;

		private DateParam(String name, String format) {
			this.name = name;
			this.format = format;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public static Optional<DateParam> toDateParam(String name) {
			Optional<DateParam> dp = Arrays.asList(DateParam.values()).stream()
					.filter(p -> name != null && name.length() > 2 && p.getName().equals(name.substring(0, 2)))
					.findFirst();
			if (dp.isPresent())
				dp.get().setFormat(name.substring(2));
			return dp;
		}
	};

	private List<DateParam> params;

	@Override
	public String removeDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		String result = input.substring(0, input.indexOf(0) >= 0 ? input.indexOf(0) : input.length());
		String source = params.contains(DateParam.SOURCE_FORMAT)
				? params.get(params.indexOf(DateParam.SOURCE_FORMAT)).getFormat()
				: DateParam.SOURCE_FORMAT.format;
		String target = params.contains(DateParam.TARGET_FORMAT)
				? params.get(params.indexOf(DateParam.TARGET_FORMAT)).getFormat()
				: DateParam.TARGET_FORMAT.format;
		if (result.length() > 0 && !source.equals(target)) {
			SimpleDateFormat sf = new SimpleDateFormat(source);
			SimpleDateFormat tf = new SimpleDateFormat(target);
			try {
				result = tf.format(sf.parse(result));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String addDecoration(Element elem, String param, String input) {
		getAttributes(elem, param);
		String result = input.replaceAll("/", "").trim();
		String source = params.contains(DateParam.SOURCE_FORMAT)
				? params.get(params.indexOf(DateParam.SOURCE_FORMAT)).getFormat()
				: DateParam.SOURCE_FORMAT.format;;
		String target = params.contains(DateParam.TARGET_FORMAT)
				? params.get(params.indexOf(DateParam.TARGET_FORMAT)).getFormat()
				: DateParam.TARGET_FORMAT.format;;
		if (result.length() > 0 && !source.equals(target)) {
			SimpleDateFormat sf = new SimpleDateFormat(source);
			SimpleDateFormat tf = new SimpleDateFormat(target);
			try {
				result = tf.format(sf.parse(result));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return StringUtils.rightPad(result, length, '0');
	}

	@Override
	public void getAttributes(Element elem, String param) {
		length = DecoratorUtils.getAsUnsignedInt(elem.getAttribute(Cb2xmlConstants.STORAGE_LENGTH), 0);
		params = new ArrayList<>(3);
		if (param != null) {
			Arrays.asList(StringUtils.split(param, PARAM_SEPARATOR)).stream().forEach(p -> {
				Optional<DateParam> dp = DateParam.toDateParam(p.toUpperCase());
				if (dp.isPresent())
					params.add(dp.get());
			});
		}
	}
}
