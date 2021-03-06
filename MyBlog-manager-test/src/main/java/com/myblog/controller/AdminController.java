package com.myblog.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myblog.pojo.Admin;
import com.myblog.pojo.AdminLoginLog;
import com.myblog.service.AdminLoginLogService;
import com.myblog.service.ArticleService;
import com.myblog.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	public AdminLoginLogService adminLoginLogService;
	@Autowired
	public ArticleService articleService;
	@Autowired
	public CommentService commentService;
	
//	@RequiresRoles("admin")
//	@RequestMapping(value ="/testRole",method = RequestMethod.GET)
//	@ResponseBody
//	public String testRole(){
//		return "testRole success";
//	}
//	
	@RequiresRoles("admin1")
	@RequestMapping(value ="/testRole1",method = RequestMethod.GET)
	@ResponseBody
	public String testRole1(){
		return "testRole1 success";
	}
	

	@RequestMapping(value ="/create",method = RequestMethod.GET)
	public String UserCreate(){
		return "admin/login2";
	}

	@RequestMapping("/main")
	public ModelAndView toMain(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView("admin/main");
		String clientIp = request.getRemoteAddr(); // 获取客户端IP，如：127.0.0.1
		String hostIp = request.getLocalAddr();
		int hostPort = request.getLocalPort();
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");// 设置日期格式
		String dates = df.format(date);
		Admin admin = (Admin) request.getSession().getAttribute("admin");//
		AdminLoginLog lastLoginLog = null;
		boolean test = false;
		// List<AdminLoginLog> adminLoginLog
		// =adminLoginLogService.selectRencent(a);
		try {
			if (adminLoginLogService.selectRencent(admin.getId()) != null
					|| adminLoginLogService.selectRencent(admin.getId()).size() > 0) {
				List<AdminLoginLog> adminLoginLogs = adminLoginLogService.selectRencent(admin.getId());
				lastLoginLog = adminLoginLogs.get(adminLoginLogs.size() - 1);
			}

		} catch (Exception e) {
			test = true;
		} finally {
			if (test == false) {
				int articleCount = articleService.selectCount();
				int commentCount = commentService.countAllNum();
				int loginNum = adminLoginLogService.selectCountByAdminId(admin.getId());
				modelAndView.addObject("clientIp", clientIp);
				modelAndView.addObject("hostIp", hostIp);
				modelAndView.addObject("hostPort", hostPort);
				modelAndView.addObject("date", dates);
				if (lastLoginLog != null) {
					modelAndView.addObject("loginLog", lastLoginLog);
				}
				modelAndView.addObject("articleCount", articleCount);// 文章数
				modelAndView.addObject("commentCount", commentCount);// 评论数
				modelAndView.addObject("loginNum", loginNum); // 登录次数
				
					return modelAndView;
				
			} else {
				response.sendRedirect("/admin");
				return null;
			}

		}

	}

}
