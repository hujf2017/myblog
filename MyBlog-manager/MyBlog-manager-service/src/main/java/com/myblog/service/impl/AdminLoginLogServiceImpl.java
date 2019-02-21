package com.myblog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.mapper.AdminLoginLogMapper;
import com.myblog.pojo.AdminLoginLog;
import com.myblog.pojo.AdminLoginLogExample;
import com.myblog.pojo.AdminLoginLogExample.Criteria;
import com.myblog.service.AdminLoginLogService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminLoginLogServiceImpl implements AdminLoginLogService{

    @Autowired
    public AdminLoginLogMapper adminLoginLogDao;
    

    public int insert(AdminLoginLog adminLoginLog) {
        return adminLoginLogDao.insert(adminLoginLog);
    }


	@Override
	public List<AdminLoginLog> selectRencent(int adminId) {
		
		// TODO Auto-generated method stub
		AdminLoginLogExample example = new AdminLoginLogExample();//获取一个对应表对象
		Criteria criteria = example.createCriteria();//用Criteria实现取值
		criteria.andAdminIdEqualTo(adminId);//注入条件
		
		List<AdminLoginLog> list =adminLoginLogDao.selectByExample(example);  //此处亦可以采用AOP实现
		return (List<AdminLoginLog>) list;
	}


	@Override
	public int selectCountByAdminId(int adminId) {
		AdminLoginLogExample example = new AdminLoginLogExample();//获取一个对应表对象
		Criteria criteria = example.createCriteria();//用Criteria实现取值
		criteria.andAdminIdEqualTo(adminId);//注入条件
		
		List<AdminLoginLog> list =adminLoginLogDao.selectByExample(example);  //此处亦可以采用AOP实现	  
		return list.size();
	}



}
