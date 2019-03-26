package com.myblog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.mapper.AdminMapper;
import com.myblog.pojo.Admin;
import com.myblog.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
    @Autowired
    public AdminMapper adminDao;
    public Admin getById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }
	@Override
	public String getpasswordByUsername(String name) {
		// TODO Auto-generated method stub
		 return adminDao.selectByUsername(name).getPassword();
	}
}
