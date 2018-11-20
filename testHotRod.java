package org.jboss.as.quickstarts.datagrid.hotrod;

import java.util.logging.Logger;

import org.infinispan.client.hotrod.CacheTopologyInfo;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class testHotRod {

	private static final String serverList = "127.0.0.1:11222;127.0.0.1:11322";
	private static RemoteCacheManager rmc = null;
	private static RemoteCache<String, Object> remoteCache = null;
	private final static Logger logger = Logger.getLogger(testHotRod.class.getName());

	public static void main(String[] args) {

		startMyCache(); // Starting the cache manager and initialize connection
		putCache(); // To put entries in cache teams
		getSizeOfCache(); // to get size of cache
		getValueFromCache(); // To retrieve value from cache using key
		containsKey(); // Boolean method to check if key value is present in
						// cache or not
	}

	public static void startMyCache() {

		if (rmc == null) {
			if (rmc == null) {

				ConfigurationBuilder cb = new ConfigurationBuilder();
				cb.tcpNoDelay(true).connectionPool().addServers(serverList);
				rmc = new RemoteCacheManager(cb.build());
				CacheTopologyInfo cti = rmc.getCache().getCacheTopologyInfo();
				cti.getTopologyId();
				remoteCache = rmc.getCache("teams");
				System.out.println("Connection from client to server is established");
			}
		}
	}

	private static void getSizeOfCache() {
		System.out.println("Size of cache is" + " " + remoteCache.size());
	}

	private static void putCache() {
		for (int i = 0; i < 100000; i++) {
			remoteCache.put("key" + i, "value" + i);
		}
	}

	private static void getValueFromCache() {
		if (null != remoteCache.get("key50")) {
			String str = (String) remoteCache.get("key2");
			logger.info(str);
			System.out.println("Value for key-50 is" + " " + remoteCache.get("key50"));
		} else {
			System.out.println("Value Unavailable");
		}
	}

	private static void containsKey() {
		if (remoteCache.containsKey("key200")) {
			System.out.println("Value for Key2 is" + " " + remoteCache.get("key2"));
		} else {
			System.out.println("Value for Key2 key is not present :(");
		}
	}

}
