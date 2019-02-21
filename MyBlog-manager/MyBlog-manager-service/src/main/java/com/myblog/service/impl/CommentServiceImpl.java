package com.myblog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.mapper.CommentMapper;
import com.myblog.pojo.Comment;
import com.myblog.pojo.CommentExample;
import com.myblog.pojo.CommentExample.Criteria;
import com.myblog.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    public CommentMapper  commentDao;
//    public List<Comment> allComments(int article_id, int offset, int limit) {
//        return commentDao.queryAll(article_id,offset,limit);
//    }
//
//    public int insertComment(Comment comment) {
//        return commentDao.insert(comment);
//    }
//
//    public int countAllNum() {
//        return commentDao.countAllNum();
//    }
//
//    public boolean delById(Long id) {
//        return commentDao.deleteByPrimaryKey(id)>0;
//    }
//
//	@Override
//	public int insertComment(Comment comment) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public List<Comment> allComments(int article_id, int offset, int limit) {
		 CommentExample example = new CommentExample();
		 Criteria critetia = example.createCriteria();
		 critetia.andArticleIdEqualTo(article_id);
		 return commentDao.selectByExampleWithBLOBs(example);
	}

	@Override
	public int insertComment(Comment comment) {
		return commentDao.insert(comment);
	}

	@Override
	public int countAllNum() {
		 CommentExample example = new CommentExample();
		 return commentDao.selectByExampleWithBLOBs(example).size();
	}

	@Override
	public boolean delById(Long id) {
		int a =commentDao.deleteByPrimaryKey(id);
		if(a>0){
		return true;
				}else{
					return false;
				}
	}
}
