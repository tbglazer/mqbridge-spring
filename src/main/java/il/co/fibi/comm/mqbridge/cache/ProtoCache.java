package il.co.fibi.comm.mqbridge.cache;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import il.co.fibi.comm.mqbridge.mongo.ProtoRepository;
import il.co.fibi.comm.mqbridge.mongo.ProtolItem;

@Service
@Cache(CacheType.PROTO)
public class ProtoCache implements ICache {
	@Autowired
	private ProtoRepository proto;

	@Cacheable("Proto")
	public String getValue(String key) {
		Optional<ProtolItem> item = proto.findById(key);
		return item.isPresent() ? item.get().getVal() : null;
	}
}