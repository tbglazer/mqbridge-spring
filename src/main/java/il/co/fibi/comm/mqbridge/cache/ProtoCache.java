package il.co.fibi.comm.mqbridge.cache;

import org.springframework.beans.factory.annotation.Autowired;

import il.co.fibi.comm.mqbridge.mongo.ProtoRepository;

@Cache(CacheType.PROTO)
public class ProtoCache implements ICache {
	//private static Logger logger = Logger.getLogger(ProtoCache.class.getName());
	@Autowired
	private ProtoRepository proto;

	public Object get(String key) {
		return proto.findById(key);
	}
}