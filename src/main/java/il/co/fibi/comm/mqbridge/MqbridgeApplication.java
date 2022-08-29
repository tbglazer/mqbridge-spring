package il.co.fibi.comm.mqbridge;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import il.co.fibi.comm.mqbridge.utils.ApmUtils;

@SpringBootApplication
@EnableCaching
public class MqbridgeApplication {
	private static final Logger logger = Logger.getLogger(MqbridgeApplication.class.getName());
	public static void main(String[] args) {
		ApmUtils.startup();
		logger.info("Mqbridge application starting ...");
		SpringApplication.run(MqbridgeApplication.class, args);
	}
}
