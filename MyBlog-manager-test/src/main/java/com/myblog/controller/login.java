package com.myblog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.internal.runners.statements.ExpectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myblog.pojo.Admin;
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
    
    @RequestMapping(value = "/api/userCreate", method = RequestMethod.POST) //传用户名和密码数据
    public String saveUser(Admin admin) {
    	int id = admin.getId();
    	Admin a =adminService.getById(id);
    	if(a!=null){
    		System.out.println("用户名已存在");
    		//System.exit(0);
    	}
    	//String password = admin.getPassword();
    	//admin.setUsername(String.valueOf(id));
    	int b =adminService.saveAdmin(admin);
    	if(b==1){
    		System.out.println("创建成功");
    		return "admin/login3";
    	}else{
    		System.out.println("创建失败");
    		return "404";
    	}
    }
    
    
    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest request,HttpServletResponse httpServletResponse) { 
    	System.out.println(request.getParameter("id"));
        int id=Integer.parseInt(request.getParameter("id"));//1
        String passwd = request.getParameter("password");
        String isrememberMe = request.getParameter("rememberme");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("id"),passwd);//吧输入的id转为username 001
        boolean a =false;
        switch(isrememberMe){
        	case "true": a=true;
        }
        token.setRememberMe(a);
        subject.login(token);//realm
        System.out.println(subject.isAuthenticated());
        
//        if(subject.hasRole("admin")){
//        	 System.out.println(1);
//        }else{
//        	System.out.print(2);
//        }
//       // 把所有控制交给shiro
       HashMap<String, String> res = new HashMap<String, String>();
//        if(adminService.getById(id)==null){
//        	 res.put("stateCode", "0");
//        }else if(!adminService.getById(id).getPassword().equals(passwd)){
//            res.put("stateCode", "1");
//        }else {
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
       // }
     

    return res;  
    }

   
    @RequestMapping(value = {"/admin/logout"})
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        request.getSession().removeAttribute("admin");
        return "redirect:/admin";
    }

}
