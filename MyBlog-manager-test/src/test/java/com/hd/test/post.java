package com.hd.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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
import org.junit.Test;
 
public class post {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", "001");
		params.put("password", "1234");
		String result = httpPostWithForm("http://localhost:8080/api/loginCheck", params);
		System.out.println(result);
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
		return respContent;
	}
	
	
	//@Test
	public void testpost(){

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet getMethod = new HttpGet("http://localhost:8080/test1");  
		HttpResponse response;
		try {
			response = httpclient.execute(getMethod);
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//@Test
	public void testpost2(){

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet getMethod = new HttpGet("http://localhost:8080/test2");
		HttpResponse response;
		try {
			response = httpclient.execute(getMethod);
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public  void testpost3(){
		 CloseableHttpClient httpclient=HttpClients.createDefault();
			HttpGet getMethod = new HttpGet("http://localhost:8080/land");   
			HttpResponse response;
			try {
				response = httpclient.execute(getMethod);
				System.out.println(EntityUtils.toString(response.getEntity()));
				
				Header[] headers= response.getAllHeaders();
				String cookie=null;
				for(Header header:headers){//将这个session属性存下来
					if(header.getName().equals("Set-Cookie"))
						cookie=header.getValue();
				}
				
				if(cookie!=null){
					
					HttpGet getMethodWithCookie = new HttpGet("http://localhost:8080/test2");//模拟get获取httpget
					getMethodWithCookie.addHeader("Cookie",cookie);//此处换上之前的cookie	
					response=httpclient.execute(getMethodWithCookie);
					System.out.println(EntityUtils.toString(response.getEntity()));
				}
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
	}
	
	
	
}