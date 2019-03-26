package com.hd.test;

import java.util.HashMap;

import redis.clients.jedis.Jedis;

public class RedisTest {
	 public static void main(String[] args) {

		  //连接本地的 Redis 服务
	        @SuppressWarnings("resource")
			Jedis jedis = new Jedis("localhost");
	        System.out.println("连接成功");
	        //设置 redis 字符串数据
	        jedis.set("runoobkey", "www.runoob.com");
	        
	        // 获取存储的数据并输出
	        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
	        
	        jedis.flushDB();//清除所有数据
	        System.out.println(jedis.exists("runoobkey"));
	        
	        jedis.set("runoobkey", "www.runoob.com");  //类似于map
	        jedis.set("runoobkey", "www.runoob.com1");
	        System.out.println(jedis.get("runoobkey"));
	        jedis.setnx("runoobkey1", "www.runoob.com2");
	        System.out.println("redis 存储的字符串为: "+ jedis.mget("runoobkey","runoobkey1"));  //获取多个key对应的vaule
//	        HashMap map = new HashMap();
//	        map.put("name", "hujf");
//	        map.put("name", "hujf2");
//	        System.out.println(map); 
	    }
}
