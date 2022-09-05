package il.co.fibi.comm.mqbridge.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.opentracing.util.GlobalTracer;
import lombok.extern.slf4j.Slf4j;

@Component("AuthInterceptor")
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String user = request.getHeader("remote_user");
		if (user != null) {
			String[] s = user.split("@");
			MDC.put("user.id", s[0]);
			GlobalTracer.get().activeSpan().setTag("user.id", s[0]);
			if (s.length > 1)
				MDC.put("user.domain", s[1]);
			log.debug("User {} requested url {} from {}", user, request.getRequestURI(), request.getRemoteAddr());
			return true;
		}
		log.warn("Access to url {} from {} forbidded", request.getRequestURI(), request.getRemoteAddr());
		response.sendError(HttpStatus.FORBIDDEN.value());
		return false;
	}
}
