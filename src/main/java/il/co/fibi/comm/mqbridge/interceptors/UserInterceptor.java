package il.co.fibi.comm.mqbridge.interceptors;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import il.co.fibi.comm.mqbridge.MqbridgeApplication;

@Component("UserInterceptor")
public class UserInterceptor implements HandlerInterceptor {
	private static final Logger logger = Logger.getLogger(MqbridgeApplication.class.getName());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String user = request.getHeader("remote_user");
		if (user == null) {
			logger.info("Access from url " + request.getRequestURI() + " not allowed");
			response.sendError(HttpStatus.FORBIDDEN.value());
			return false;
		}
		String[] s = user.split("@");
		MDC.put("username", s[0]);
		if (s.length > 1) MDC.put("domain", s[1]);
		return true;
	}
}
