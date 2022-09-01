package il.co.fibi.comm.mqbridge.cache;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import il.co.fibi.comm.mqbridge.mongo.ProtolItem;

@Service
@Cache(CacheType.PROTO)
public class ProtoCache implements ICache {
	//private static Logger logger = Logger.getLogger(ProtoCache.class.getName());
	@Autowired
	private MongoRepository<ProtolItem, String> proto;

	@Cacheable("Proto")
	public String getValue(String key) {
		Optional<ProtolItem> item = proto.findById(key);
		return item.isPresent() ? item.get().getVal() : null;
	}
}