package com.myblog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myblog.pojo.BgUser;
import com.myblog.service.ItemService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping(value="/controller",produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object  logincontroller(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String userid =request.getParameter("userid");//输入的
		String password =request.getParameter("password");
		//Integer i =;
		BgUser bguser = itemService.selectbyId(Integer.parseInt(userid));
		//用户校验方法
		boolean flag =check(bguser,Integer.parseInt(userid),password);
		if(flag==true){
			response.sendRedirect("http://localhost:8080/login/index");
			//request.getRequestDispatcher("http://localhost:8080/login/index").forward(request,response);
			return null;
		}
		return "用户名或密码错误";
	}

	@RequestMapping(value="/index")
	public String tiaozhuan() {
		
		return "index";
	}


	private boolean check(BgUser bguser,int userid,String password) {
		int realui = bguser.getId();
		String realpw =bguser.getPassword();
		if((realpw==password||realpw.equals(password))&&(realui==userid)){
			return true;
		}
		return false;	
	}
}
