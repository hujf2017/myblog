package com.hd.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.myblog.pojo.Article;
import com.myblog.service.ArticleService;
@Transactional
public class things {
    @Autowired
    ArticleService articleService;
    @Test
	public void test(){
		Article article=articleService.selectById(109);
		article.setClick(article.getClick()+1);
		articleService.updateArticle(article);
		int i=1/0; 
	}
}
