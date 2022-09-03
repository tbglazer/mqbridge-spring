package il.co.fibi.comm.mqbridge.filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.opentracing.util.GlobalTracer;

@Component
@Order(1)
public class LabelsFilter implements Filter {
	private Pattern logHeaderPattern = Pattern.compile("snifit-header|fibi-\\w+-labels");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			final Enumeration<String> headerNames = ((HttpServletRequest) request).getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				if (logHeaderPattern.matcher(headerName.toLowerCase()).matches()) {
					String header = ((HttpServletRequest) request).getHeader(headerName);
					Stream.of(header.split(";")).forEach(elem -> {
						String key = elem.split("=")[0], value = elem.split("=")[1];
						MDC.put("labels.".concat(key), value);
						GlobalTracer.get().activeSpan().setTag(key, value);						
					});
				}
			}
			try {
				chain.doFilter(request, response);
			} finally {
				MDC.clear();
			}
		} else {
			chain.doFilter(request, response);
		}
	}
}
