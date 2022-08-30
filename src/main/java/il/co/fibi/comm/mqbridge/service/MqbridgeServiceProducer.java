package il.co.fibi.comm.mqbridge.service;

import java.util.List;

import javax.xml.bind.annotation.XmlEnumValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import il.co.fibi.comm.mqbridge.cache.ProtoParams;
import il.co.fibi.comm.mqbridge.cache.ProtoParams.PROTOCOL;
import il.co.fibi.comm.mqbridge.jms.JmsConfiguration;

public class MqbridgeServiceProducer {
	@Autowired
	JmsConfiguration config;
	@Autowired
	ProtoParams proto;
	@Autowired
	List<AbstractMqbridgeService> services;

	@Bean
	@RequestScope
	public @ProducedService AbstractMqbridgeService select() {
		try {
			String name = PROTOCOL.class.getField(proto.getProtocol().name()).getAnnotation(XmlEnumValue.class).value();
			List<AbstractMqbridgeService> srvs = services.stream().filter(service -> service.getClass().getAnnotation(Component.class).value() == name).toList();
			if (!srvs.isEmpty()) {
				AbstractMqbridgeService srv = srvs.get(0).init(proto);
				srv.setRequestQueue(config.getQueue(name, proto.getCicsid()).sender);
				srv.setReplyQueue(config.getQueue(name, proto.getCicsid()).receiver);
				return srv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
