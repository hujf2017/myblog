package com.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myblog.pojo.BgUser;
import com.myblog.service.ItemService;

@Controller
public class UserController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/user/{id}")
	@ResponseBody
	public BgUser selectbyId(@PathVariable Integer id){
		BgUser bguser = itemService.selectbyId(id);
		return bguser;	
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public int insert(BgUser record){
		//record.setId(2);
		record.setUsername("胡剑峰");
		record.setPassword("123456");
		int bguser = itemService.insert(record);
		return bguser;
		
	}
	


	
	
}
