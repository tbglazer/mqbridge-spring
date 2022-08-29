package il.co.fibi.comm.mqbridge.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import co.elastic.apm.attach.ElasticApmAttacher;
import co.elastic.apm.opentracing.ElasticApmTracer;
import io.opentracing.util.GlobalTracer;

public class ApmUtils {

	public static void startup() {
		Map<String, String> apmConfig = new HashMap<>();
		Config config = ConfigProvider.getConfig();
		config.getPropertyNames().forEach(name -> {
			if (name.startsWith("apm.")) {
				apmConfig.put(StringUtils.substringAfter(name, "apm."), config.getValue(name, String.class));
			}
		} );
		ElasticApmAttacher.attach(apmConfig);
		GlobalTracer.registerIfAbsent(new ElasticApmTracer());
	}
	
}
