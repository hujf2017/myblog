package com.hd.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.myblog.pojo.Admin;
import com.myblog.pojo.Article;
import com.myblog.service.ArticleService;




public class testth {
	@Autowired
	ArticleService articleService;
	@Autowired
	Admin articleService1;
	@Test
	public void test() {
		Article article = articleService.selectById(109);
		article.setClick(article.getClick() + 1);
		articleService.updateArticle(article);
		int i = 1 / 0;
	}	
}
