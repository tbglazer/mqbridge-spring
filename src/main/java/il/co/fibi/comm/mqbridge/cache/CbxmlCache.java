package il.co.fibi.comm.mqbridge.cache;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import il.co.fibi.comm.mqbridge.mongo.CbxmlItem;

@Service
@Cache(CacheType.CBXML)
public class CbxmlCache implements ICache {
	//private static Logger logger = Logger.getLogger(CbxmlCache.class.getName());
	@Autowired
	private MongoRepository<CbxmlItem, String> cbxml;

	@Cacheable("Cbxml")
	public String getValue(String key) {
		Optional<CbxmlItem> item = cbxml.findById(key);
		return item.isPresent() ? item.get().getVal() : null;
	}
}