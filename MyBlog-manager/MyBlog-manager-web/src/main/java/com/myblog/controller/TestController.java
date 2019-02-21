package com.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login")
public class TestController {
	@RequestMapping(value="/in")
	public Object test(){
		return "login";
	}
}
