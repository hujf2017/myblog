package com.hd.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author xupc
 *	redis缓存管理
 */
public class RedisCacheManager implements CacheManager{
	@Autowired
	private RedisCache redisCache;
	 
	
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		// TODO Auto-generated method stub
		return redisCache;//;
	}
	
}
