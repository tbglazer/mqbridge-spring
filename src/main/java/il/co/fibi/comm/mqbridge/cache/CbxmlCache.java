package il.co.fibi.comm.mqbridge.cache;

import static com.mongodb.client.model.Filters.eq;

import java.util.logging.Logger;

import javax.naming.InitialContext;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.mongodb.client.MongoCollection;

import il.co.fibi.comm.mqbridge.mongo.Collection;
import il.co.fibi.comm.mqbridge.mongo.CollectionType;


@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Cache(CacheType.CBXML)
public class CbxmlCache implements ICache  {
	private static Logger logger = Logger.getLogger(CbxmlCache.class.getName());
	@Autowired 
	@Collection(CollectionType.CBXML)
	MongoCollection<Document> coll;
	@Autowired 
	@ConfigProperty(name = "cache.cbxml.jndiName")
	String jndiName;
	@Inject
	@ConfigProperty(name = "cache.cbxml.timeToLive")
	Integer timeToLive;
	private DistributedMap cbxml;

	@PostConstruct
	public void initialize() {
		try {
			cbxml = (DistributedMap) new InitialContext().lookup(jndiName);
			cbxml.setTimeToLive(timeToLive);
		} catch (Exception e) {
			logger.severe("Cbxml cache initialization failed :" + e.getMessage());
		}
	}

	public Object get(Object key) {
		if (!cbxml.containsKey(key)) {
			Document value = coll.find(eq("_id", key)).first();
			cbxml.put(key, value.get("val"));
		}
		return cbxml.get(key);
	}
}