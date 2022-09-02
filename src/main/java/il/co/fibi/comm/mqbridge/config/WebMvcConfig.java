package il.co.fibi.comm.mqbridge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	@Qualifier("UserInterceptor")
	private HandlerInterceptor userInterceptor;
	@Autowired
	@Qualifier("LogInterceptor")
	private HandlerInterceptor logInterceptor;
	
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(userInterceptor);
	    registry.addInterceptor(logInterceptor);
	  }
}
