package il.co.fibi.comm.mqbridge.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	@Value("${service_name}")
	private String serviceName;
	@Value("${service_version}")
	private String serviceVersion;
	
    private ApiInfo apiInfo() {
        return new ApiInfo(serviceName,
                "CICS Mqbridge Api",
                serviceVersion,
                "Terms of service",
                ApiInfo.DEFAULT_CONTACT,
                "Licensed to FIBI",
                "",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}