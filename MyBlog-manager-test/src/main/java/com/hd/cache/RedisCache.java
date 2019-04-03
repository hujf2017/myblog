package com.hd.cache;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import com.hd.util.JedisUtil;

@Service
public class RedisCache<K,V> implements Cache<K,V>{
	
	@Autowired
	private JedisUtil jedisUtil;
	
	private final String  CACHE_PREFIX="imooc-cache";
	
	private byte[] getKey(K k){
		if(k instanceof String){
			return (CACHE_PREFIX+k).getBytes();
		}
		return SerializationUtils.serialize(k);
	}

	@Override
	public V get(K key) throws CacheException {
		System.out.print("从redis获取权限数据");
		// TODO Auto-generated method stub
		byte[] value=jedisUtil.get(getKey(key));
		if(value!=null){//如果缓存里面有东西
			return (V) SerializationUtils.deserialize(value);
		}
		return null;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		byte[] k=getKey(key);
		byte[] v =  SerializationUtils.serialize(value);
		jedisUtil.set(k, v);
		jedisUtil.expire(k, 600);
		return value;//为什么返回一个value
	}

	@Override
	public V remove(K key) throws CacheException {
		byte[] k=getKey(key);//获取k
		byte[] v =  jedisUtil.get(k);
		jedisUtil.del(k);
		if(v!=null){
			return  (V) SerializationUtils.deserialize(v);
		}
		return null;
	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
