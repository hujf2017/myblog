package com.myblog.service;

import com.myblog.pojo.BgUser;

public interface ItemService {
	public BgUser selectbyId(Integer id);
	public int insert(BgUser record);
}
