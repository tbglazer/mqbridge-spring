package il.co.fibi.comm.mqbridge.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.w3c.dom.Element;

import il.co.fibi.comm.mqbridge.data.decorators.IDecorator;

@Component
@RequestScope
public class MainframeDecorator implements net.sf.cb2xml.convert.IDecorator {
	@Autowired
	List<IDecorator> decorators;

	@Override
	public String fromMainframe(Element arg0, String arg1) {
		String res = MainframeUtililities.setupDecorators(arg0, decorators).stream().reduce(arg1,
				(s, decor) -> s = decor.getKey().removeDecoration(arg0, decor.getValue(), s), (s1, s2) -> s1);
		return res;
	}

	@Override
	public String toMainframe(Element arg0, String arg1) {
		String res = reverse(MainframeUtililities.setupDecorators(arg0, decorators)).stream().reduce(arg1,
				(s, decor) -> s = decor.getKey().addDecoration(arg0, decor.getValue(), s), (s1, s2) -> s1);
		return res;
	}

	private <T> List<T> reverse(List<T> list) {
		List<T> reverse = new ArrayList<T>(list);
		Collections.reverse(reverse);
		return reverse;
	}
}
