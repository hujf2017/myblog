package com.myblog.controller;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myblog.pojo.Admin;
import com.myblog.service.AdminService;
 
@Controller
public class post {
	@Autowired
	AdminService adminService;
	@RequestMapping(value = {"/post"})
	public  void postTest(HttpServletRequest  request ,HttpServletResponse response) throws ClientProtocolException, IOException, ServletException {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		Map<String,String> params = new HashMap<String,String>();
		params.put("id", "001");
		params.put("password", "1234");
		String cookie = httpPostWithForm("http://localhost:8080/api/loginCheck", params);//模拟post 获取cookie
		String url1 = "http://localhost:8080/admin/main";
		request.getSession().setAttribute("admin",adminService.getById(Integer.parseInt(params.get("id"))));
		request.getSession().setAttribute("post","success");
		response.sendRedirect(url1);
//		InputStream urlStream = resumeConnection.getInputStream();  
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream));  
//		request.getRequestDispatcher(url).forward(request, response);
//		Cookie c = new Cookie("a",cookie);
//		response.addCookie(c);
//		response.sendRedirect(url);//重定向带上cookie
		//此处换上之前的cookie	
	}
 
	private static String httpPostWithForm(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
		for (Entry<String, String> param : params.entrySet()) {
			pairList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
		}
		HttpPost httpPost = new HttpPost(url);
		
		//添加代理，用于抓包
//		HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http"); 
//        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(8000).setConnectTimeout(8000).setSocketTimeout(8000).setProxy(proxy).build();
//        httpPost.setConfig(requestConfig);
//		
		httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
		String respContent = null;
		HttpClient _httpClient = HttpClients.createDefault();
		HttpResponse resp = _httpClient.execute(httpPost);
		HttpEntity he = resp.getEntity();

		respContent = EntityUtils.toString(he, "UTF-8");
		
		Header[] heads =  resp.getAllHeaders();
		String cookie=null;
		for(Header head :heads ){
			if(head.getName().equals("Set-Cookie")){
				cookie = head.getValue();
			}
		}
		//List list = new ArrayList();
		//if()
		
		return cookie;
	}
	
	
	
	

		@RequestMapping("/test1")
		@ResponseBody
		public String test1() {
			return "no land can see !";
		}
		
		
		

		@RequestMapping("/test2")
		@ResponseBody
		public String test2(HttpServletRequest request) {
			if (request.getSession().getAttribute("name") == null)  //判断是否有session,name属性
				return "no land no see !";
			else
				return "landed can see!";
		}
		
		
		
		
		@RequestMapping("/land")
		@ResponseBody
		public String land(HttpServletRequest request) {
			request.getSession().setAttribute("name", "knife");//创建了一个session属性
			return "land !";

		}
}