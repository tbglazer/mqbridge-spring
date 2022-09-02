package il.co.fibi.comm.mqbridge.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	@Value("${service_name}")
	private String serviceName;
	@Value("${service_version}")
	private String serviceVersion;

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title(serviceName).description("Mqbridge CICS Api").version(serviceName));
	}
}