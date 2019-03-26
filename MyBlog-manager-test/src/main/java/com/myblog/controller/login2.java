package com.myblog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.myblog.pojo.AdminLoginLog;
import com.myblog.service.AdminLoginLogService;
import com.myblog.service.AdminService;
import com.myblog.service.ArticleService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;


@Controller
public class login2 {
	@Autowired
	AdminService adminService;
//	
	@Autowired
	ArticleService articleService;
	
    @RequestMapping(value = {"/admin/index","/admin1","/admin/login"})
    public String toIndex(HttpServletRequest request) {
    	
        return "admin/login";
    }

}
