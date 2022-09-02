package il.co.fibi.comm.mqbridge.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("UserInterceptor")
public class UserInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String user = request.getHeader("remote_user");
		if (user == null) {
			response.sendError(HttpStatus.FORBIDDEN.value());
			return false;
		}
		return true;
	}
}
