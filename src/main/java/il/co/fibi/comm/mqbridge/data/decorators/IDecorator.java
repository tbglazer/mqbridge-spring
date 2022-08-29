package il.co.fibi.comm.mqbridge.data.decorators;

import java.nio.charset.Charset;

import org.w3c.dom.Element;

import com.ibm.icu.charset.CharsetICU;
public interface IDecorator {
	public static final Charset LATIN1 = CharsetICU.forName("8859_1");
	public static final char PARAM_SEPARATOR = ',';
	
	public String removeDecoration(Element elem, String param, String input);
	public String addDecoration(Element elem, String param, String input);
	default void getAttributes(Element elem, String param) {};
}
