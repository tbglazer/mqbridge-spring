package il.co.fibi.comm.mqbridge.services;

import java.util.List;

import javax.xml.bind.annotation.XmlEnumValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import il.co.fibi.comm.mqbridge.cache.ProtoParams;
import il.co.fibi.comm.mqbridge.cache.ProtocolType;
import il.co.fibi.comm.mqbridge.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MqbridgeServiceFactory {
	@Autowired
	JmsConfig config;
	@Autowired
	List<AbstractMqbridgeService> services;

	public AbstractMqbridgeService select(ProtoParams params) {
		try {
			String name = ProtocolType.class.getField(params.getProtocol().name()).getAnnotation(XmlEnumValue.class).value();
			List<AbstractMqbridgeService> srvs = services.stream().filter(service -> name.equalsIgnoreCase(service.getId())).toList();
			if (!srvs.isEmpty()) {
				AbstractMqbridgeService srv = srvs.get(0).init(params);
				srv.setRequestQueue(config.getQueue(name, params.getCicsid()).sender);
				srv.setReplyQueue(config.getQueue(name, params.getCicsid()).receiver);
				return srv;
			}
		} catch (Exception e) {
			log.error("select failed", e);
		}
		return null;
	}
}
