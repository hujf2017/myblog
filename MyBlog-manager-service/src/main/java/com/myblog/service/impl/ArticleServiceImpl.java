package com.myblog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myblog.mapper.ArticleMapper;
import com.myblog.pojo.Article;
import com.myblog.pojo.ArticleExample;
import com.myblog.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    public ArticleMapper  articleDao;

    public Article selectById(Integer id) {
        return articleDao.selectByPrimaryKey(id);
    }





    public boolean updateArticle(Article article) {
        return articleDao.updateByPrimaryKeySelective(article)>0;
    }

    public int deleteById(Integer id) {
        return articleDao.deleteByPrimaryKey(id);
    }



    public boolean insert(Article article) {
        return articleDao.insert(article)>0;
    }





	@Override
	public Article selectLastArticle(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public Article selectNextArticle(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public List<Article> queryAll() {
		ArticleExample example = new ArticleExample();
		return articleDao.selectByExampleWithBLOBs(example);	 
	}





	@Override
	public int countAllNum() {
		return 0;
	}





	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		
		return queryAll().size();
	}





	@Override
	public List<Article> selectByWord(String word) {
		return articleDao.selectByWord(word);
	
	}





	@Override
	public List<Article> selectBySign(int  id) {
		return articleDao.selectBySign(id);
		// TODO Auto-generated method stub
		//return articleDao.se;
	}


}
