package il.co.fibi.comm.mqbridge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;

import com.ibm.icu.util.TimeZone;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableMongoRepositories
@EnableJms
@EnableConfigurationProperties
@EnableCaching
@Slf4j
public class MqbridgeApplication implements ApplicationListener<ApplicationReadyEvent>{
	@Value("${service_name}")
	String name;
	@Value("${service_version}")
	String version;
	@Value("${environment}")
	String environment;
	@Value("${server.port}")
	String port;

	public static void main(String[] args) {
		SpringApplication.run(MqbridgeApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.info("{} v{} ({}) listening at http://localhost:{} in timezone: {}", name, version, environment, port, TimeZone.getDefault().getDisplayName());
	}
}
