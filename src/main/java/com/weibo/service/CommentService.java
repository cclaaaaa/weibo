package com.weibo.service;

import java.util.List;

import com.weibo.po.Comment;
import com.weibo.po.CommentCustom;
import com.weibo.po.Page;

public interface CommentService {

	// 评论
	void addComment(CommentCustom commentCustom);

	// 查询微博下的评论
	List<CommentCustom> queryComment(int weiboId);

	// 查询评论下回复数
	int qeuryCountReply(Integer commentId) ;

	// 根据id删除评论
	void deleteCommentById(int commentId) ;

	// 根据userID查询评论列表
	Page<CommentCustom> queryCommentByUserId(Integer userId, int pageNo);

}