package com.myblog.service;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myblog.pojo.Article;

@Transactional
public interface ArticleService {

    Article selectById (Integer id);

    Article selectLastArticle (Integer id);

    Article selectNextArticle (Integer id);

    List<Article> queryAll();

    int countAllNum();

    boolean updateArticle(Article article);

    int deleteById(Integer id);

    int selectCount();

    List<Article> selectByWord(String word);
    
    List<Article> selectBySign(int  id);

    boolean insert(Article article);
}
