package il.co.fibi.comm.mqbridge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	@Qualifier("AuthInterceptor")
	private HandlerInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/error",
								 "/v3/api-docs/**", 
								 "/swagger-resources/**", 
								 "/swagger-ui/**", 
								 "/webjars/**")
			.pathMatcher(new AntPathMatcher());
	}
}
