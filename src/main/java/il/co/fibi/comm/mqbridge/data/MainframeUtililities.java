package il.co.fibi.comm.mqbridge.data;

import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import il.co.fibi.comm.mqbridge.data.decorators.BinaryDecorator;
import il.co.fibi.comm.mqbridge.data.decorators.CharsetDecorator;
import il.co.fibi.comm.mqbridge.data.decorators.HebrewDecorator;
import il.co.fibi.comm.mqbridge.data.decorators.IDecorator;
import net.sf.cb2xml.def.Cb2xmlConstants;

public class MainframeUtililities {
	public static final String DECORATORS_ATTRIBUTE = "decorators";
	public static final String NUMERIC_DECORATOR = "NUM";
	public static final String ENGLISH_DECORATOR = "ENG";
	public static final String CHARSET_DECORATOR = "ENC";

	public static List<Map.Entry<IDecorator, String>> setupDecorators(Element element, List<IDecorator> decorators) {
		String value = element.getAttribute(DECORATORS_ATTRIBUTE);
		// process decorators attribute in format AAA:PAR1,PAR2,...;BBB:PAR1,...
		List<Map.Entry<IDecorator, String>> result = Arrays.stream(value.split(";"))
				.filter(Predicate.not(String::isEmpty)).map(s -> {
					String[] param = s.split(":");
					return new AbstractMap.SimpleEntry<IDecorator, String>(decorators.stream()
							.filter(decor -> decor.getClass().getAnnotation(Component.class).value() == param[0].trim())
							.findFirst().get(), param.length > 1 ? param[1] : null);
				}).collect(Collectors.toList());
		// set default decorator if none specified
		if (result.isEmpty()) {
			if (Boolean.valueOf(element.getAttribute(Cb2xmlConstants.NUMERIC))) {
				result.add(0, new AbstractMap.SimpleEntry<IDecorator, String>(decorators.stream()
						.filter(decor -> decor.getClass().getAnnotation(Component.class).value() == NUMERIC_DECORATOR)
						.findFirst().get(), ""));
			} else {
				result.add(0, new AbstractMap.SimpleEntry<IDecorator, String>(decorators.stream()
						.filter(decor -> decor.getClass().getAnnotation(Component.class).value() == ENGLISH_DECORATOR)
						.findFirst().get(), ""));
			}
		}
		// add charset only if not binary or already has charset
		if (result.stream()
				.filter(elem -> elem.getKey() instanceof BinaryDecorator || elem.getKey() instanceof CharsetDecorator)
				.findAny().isEmpty()) {
			result.add(0,
					new AbstractMap.SimpleEntry<IDecorator, String>(
							decorators.stream()
									.filter(decor -> decor.getClass().getAnnotation(Component.class)
											.value() == CHARSET_DECORATOR)
									.findFirst().get(),
							CharsetDecorator.DEFAULT));
		}
		Optional<Entry<IDecorator, String>> charset = result.stream()
				.filter(elem -> elem.getKey() instanceof CharsetDecorator).findFirst();
		if (charset.isPresent()) {
			// charset should be first in list if exists
			setAsFirst(result, charset.get());
			// add the charset as parameter to hebrew decorator
			Optional<Entry<IDecorator, String>> hebrew = result.stream()
					.filter(elem -> elem.getKey() instanceof HebrewDecorator).findFirst();
			if (hebrew.isPresent()) {
				hebrew.get()
						.setValue(!StringUtils.isAllEmpty(hebrew.get().getValue())
								? StringUtils.join(charset.get().getValue(), ",", hebrew.get().getValue())
								: charset.get().getValue());
			}
		}
		return result;
	}

	private static <T> void setAsFirst(List<T> list, T elem) {
		if (list.indexOf(elem) > 0) {
			list.remove(elem);
			list.add(0, elem);
		}
	}

	public static String elementToXmlString(Element element) {
		try {
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			StringWriter buffer = new StringWriter();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(element), new StreamResult(buffer));
			return buffer.toString();
		} catch (IllegalArgumentException | TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
			return null;
		}
	}
}
