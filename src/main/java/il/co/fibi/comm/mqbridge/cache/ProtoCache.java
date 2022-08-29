package il.co.fibi.comm.mqbridge.cache;

import static com.mongodb.client.model.Filters.eq;

import java.util.logging.Logger;

import javax.naming.InitialContext;

import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ibm.websphere.cache.DistributedMap;
import com.mongodb.client.MongoCollection;

import il.co.fibi.comm.mqbridge.mongo.Collection;
import il.co.fibi.comm.mqbridge.mongo.CollectionType;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Cache(CacheType.PROTO)
public class ProtoCache implements ICache {
	private static Logger logger = Logger.getLogger(ProtoCache.class.getName());
	@Inject 
	@Collection(CollectionType.PROTO)
	MongoCollection<Document> coll;
	@Inject
	@ConfigProperty(name = "cache.proto.jndiName")
	String jndiName;
	@Inject
	@ConfigProperty(name = "cache.proto.timeToLive")
	Integer timeToLive;
	private DistributedMap proto;

	@PostConstruct
	public void initialize() {
		try {
			proto = (DistributedMap) new InitialContext().lookup(jndiName);
			proto.setTimeToLive(timeToLive);
		} catch (Exception e) {
			logger.severe("Proto cache initialization failed :" + e.getMessage());
		}
	}

	public Object get(Object key) {
		if (!proto.containsKey(key)) {
			Document value = coll.find(eq("_id", key)).first();
			proto.put(key, value != null ? value.get("val") : null);
		}
		return proto.get(key);
	}
}