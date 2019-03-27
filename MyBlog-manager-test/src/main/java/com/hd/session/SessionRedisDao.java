package com.hd.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import com.hd.util.JedisUtil;

public class SessionRedisDao extends AbstractSessionDAO{
	
	@Autowired
	private JedisUtil jedisUtil; //= new JedisUtil();
	
	private final String shiro_session_prefix="imooc-session:";
	
	private byte[] getkey(String key){
		return (shiro_session_prefix + key).getBytes();
	}
	
	private void saveSession(Session session){
		if(session!=null &&session.getId()!=null){
			byte[] key = getkey(session.getId().toString());//把现在的session在创建一遍 覆盖
			byte[] value = SerializationUtils.serialize(session);
			jedisUtil.set(key,value);//覆盖
			jedisUtil.expire(key,600);//超时
		}
		
	}
	
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		saveSession(session);
		return sessionId;
	}
	


	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId==null){
			return null;
		}
		byte[] key=getkey(sessionId.toString());
		byte[] value = jedisUtil.get(key);
		return (Session) SerializationUtils.deserialize(value);
	}

	
	@Override
	public void update(Session session) throws UnknownSessionException {
		saveSession(session);
	}

	@Override
	public void delete(Session session) {
		if(session==null||session.getId()==null){
			return ;
		}
		byte[] key=getkey(session.getId().toString());
		jedisUtil.del(key);
		
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<byte[]> keys = jedisUtil.keys(shiro_session_prefix);
		Set<Session> sessions = new HashSet<>();
		if(keys.isEmpty()){
			return sessions;
		}
		for(byte[] key :keys){
			Session session =(Session) SerializationUtils.deserialize(jedisUtil.get(key));
			sessions.add(session);
		}
		return sessions;
	}


}
