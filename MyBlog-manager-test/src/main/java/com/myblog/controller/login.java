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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;


@Controller
public class login {
	@Autowired
	AdminService adminService;
//	
    @Autowired
    AdminLoginLogService adminLoginLogService;
	
    @RequestMapping(value = {"/admin/index","/admin","/admin/login"})
    public String toIndex(HttpServletRequest request) {
    	
        return "admin/login";
    }
    
    
    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest request,HttpServletResponse httpServletResponse) { 	
        int id=Integer.parseInt(request.getParameter("id"));
        String passwd = request.getParameter("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("id"),passwd);
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        HashMap<String, String> res = new HashMap<String, String>();
        if(adminService.getById(id)==null){
        	 res.put("stateCode", "0");
        }else if(!adminService.getById(id).getPassword().equals(passwd)){
            res.put("stateCode", "1");
        }else {
        	 String ip=request.getRemoteAddr();
             AdminLoginLog adminLoginLog=new AdminLoginLog();
             adminLoginLog.setAdminId(id);
             adminLoginLog.setDate(new Date());
             adminLoginLog.setIp(ip);
             adminLoginLogService.insert(adminLoginLog);  //保存上次登录信息
             Cookie cookie = new Cookie("userId",""+id);
             cookie.setMaxAge(3600*24); 
             httpServletResponse.addCookie(cookie);
             request.getSession().setAttribute("admin",adminService.getById(id));//设置session内容
             res.put("stateCode", "2");
        }
     

    return res;  
    }

   
    @RequestMapping(value = {"/admin/logout"})
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        request.getSession().removeAttribute("admin");
        return "redirect:/admin";

    }

}
