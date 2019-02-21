package com.myblog.service;


import java.util.List;

import com.myblog.pojo.Comment;

public interface CommentService {
    List<Comment> allComments(int article_id, int offset , int limit);
    int insertComment(Comment comment);
    int countAllNum();
    boolean delById(Long id);
}
