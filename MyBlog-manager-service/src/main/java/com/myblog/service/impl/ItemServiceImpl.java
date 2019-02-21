package com.myblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.mapper.BgUserMapper;
import com.myblog.pojo.BgUser;
import com.myblog.pojo.BgUserExample;
import com.myblog.pojo.BgUserExample.Criteria;
import com.myblog.service.ItemService;



@Service
public class ItemServiceImpl implements ItemService{

	//注入操作数据库的对象
		@Autowired 
		private BgUserMapper bgusermapper;
		
		public BgUser selectbyId(Integer id) {
			//bgusermapper.selectByPrimaryKey(id);
			System.out.println(bgusermapper);
			BgUserExample example = new BgUserExample();
			Criteria criteria  = example.createCriteria();
			criteria.andIdEqualTo(id);
			List<BgUser>  list =bgusermapper.selectByExample(example);
			if(list !=null &&list.size()>0){
				BgUser bduser	=  list.get(0);
				return bduser;
			}
			return null;
		}
		
		public int insert(BgUser record) {
			//BgUserExample example = new BgUserExample();
			int i = bgusermapper.insert(record);
			return i;
		}

}
