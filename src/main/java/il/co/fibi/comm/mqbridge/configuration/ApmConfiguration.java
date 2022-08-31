package il.co.fibi.comm.mqbridge.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import co.elastic.apm.attach.ElasticApmAttacher;

@Configuration
@ConditionalOnProperty(value = "apm.enabled", havingValue = "true")
public class ApmConfiguration {
	@Autowired
	private Environment env;

	@PostConstruct
	public void init() {
		Map<String,String> apmProps = new HashMap<>();
		apmProps.put("cloud_provider", env.getProperty("apm.cloud_provider"));
		apmProps.put("server_urls", env.getProperty("apm.server_urls"));
		apmProps.put("service_name", env.getProperty("apm.service_name"));
		apmProps.put("service_version", env.getProperty("apm.service_version"));
		apmProps.put("environment", env.getProperty("apm.environment"));
		apmProps.put("log_level", env.getProperty("apm.log_level"));
		apmProps.put("application_packages", env.getProperty("apm.application_packages"));
		ElasticApmAttacher.attach(apmProps);
	}
}
