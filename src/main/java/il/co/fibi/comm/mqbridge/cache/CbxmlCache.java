package il.co.fibi.comm.mqbridge.cache;

import org.springframework.beans.factory.annotation.Autowired;

import il.co.fibi.comm.mqbridge.mongo.CbxmlRepository;

@Cache(CacheType.CBXML)
public class CbxmlCache implements ICache {
	//private static Logger logger = Logger.getLogger(CbxmlCache.class.getName());
	@Autowired
	private CbxmlRepository cbxml;

	public Object get(String key) {
		return cbxml.findById(key);
	}
}