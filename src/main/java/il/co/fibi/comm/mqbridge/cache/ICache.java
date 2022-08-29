package il.co.fibi.comm.mqbridge.cache;

public interface ICache {

	public void initialize();
	public Object get(Object key);
	
}
