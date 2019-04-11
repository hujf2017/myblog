package com.myblog.service;

import com.myblog.pojo.Admin;

public interface AdminService {
	Admin getById(Integer id);
	
	String getpasswordByUsername(String  name);
	
	int saveAdmin(Admin  admin);

}
