package com.myblog.service;


import java.util.List;


import com.myblog.pojo.Article;

public interface ArticleService {

    public Article selectById (Integer id);

    Article selectLastArticle (Integer id);

    Article selectNextArticle (Integer id);

    List<Article> queryAll();

    int countAllNum();

    boolean updateArticle(Article article);

    int deleteById(Integer id);

    int selectCount();

    List<Article> selectByWord(String word);
    
    List<Article> selectBySign(Integer  id);

    boolean insert(Article article);
}
