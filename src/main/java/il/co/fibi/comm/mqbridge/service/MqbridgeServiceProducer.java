package il.co.fibi.comm.mqbridge.service;

import il.co.fibi.comm.mqbridge.cache.ProtoParams;
import il.co.fibi.comm.mqbridge.cache.ProtoParams.PROTOCOL;
import il.co.fibi.comm.mqbridge.jms.JmsConfiguration;

@Singleton()
public class MqbridgeServiceProducer {
	@Inject	ProtoParams proto;	
	@Inject JmsConfiguration config;
	@Inject Instance<MqbridgeService> services;
	
	@Produces @RequestScoped 
	public @ProducedService MqbridgeService select() {
		try {
			String name = PROTOCOL.class.getField(proto.getProtocol().name()).getAnnotation(XmlEnumValue.class).value();
			Instance<MqbridgeService> service = services.select(NamedLiteral.of(name));
			if (service.isResolvable()) {
				MqbridgeService srv = service.get().init(proto);
				srv.setQueueConnFactory(config.getQueue(name, proto.getCicsid()).factory);
				srv.setRequestQueue(config.getQueue(name, proto.getCicsid()).sender);
				srv.setReplyQueue(config.getQueue(name, proto.getCicsid()).receiver);
				return service.get();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
