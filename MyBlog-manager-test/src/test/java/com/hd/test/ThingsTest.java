package com.hd.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myblog.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"}) 
public class ThingsTest {
	@Autowired
	ArticleService articleService;
	
	@Test
	public void test()  {
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", "001");
		params.put("password", "1234");

		articleService.selectById(106);
		
	}
}
