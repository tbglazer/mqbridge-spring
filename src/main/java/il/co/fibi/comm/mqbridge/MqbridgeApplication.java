package il.co.fibi.comm.mqbridge;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties
@EnableCaching
public class MqbridgeApplication {
	private static final Logger logger = Logger.getLogger(MqbridgeApplication.class.getName());
	public static void main(String[] args) {
		logger.info("Mqbridge application starting ...");
		SpringApplication.run(MqbridgeApplication.class, args);
	}
}
