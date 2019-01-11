package com.weibo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.mapper.CommentMapperCustom;
import com.weibo.po.Comment;
import com.weibo.po.CommentCustom;
import com.weibo.po.Page;
import com.weibo.service.CommentService;
import com.weibo.utils.DateConvert;

@Transactional
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapperCustom commentMapperCustom;

	private DateConvert dateConvert;
	// 评论
	@Override
	public void addComment(CommentCustom commentCustom) {
		commentMapperCustom.addComment(commentCustom);
	}

	// 根据weiboId查询评论
	@Override
	public List<CommentCustom> queryComment(int weiboId)  {
		return commentMapperCustom.queryComment(weiboId);
	}

	// 查询回复数
	@Override
	public int qeuryCountReply(Integer commentId)  {
		return commentMapperCustom.qeuryCountReply(commentId);
	}

	// 根据id删除微博
	@Override
	public void deleteCommentById(int commentId)  {
		commentMapperCustom.deleteCommentById(commentId);
	}

	// 根据userID查询评论列表
	@SuppressWarnings("static-access")
	@Override
	public Page<CommentCustom> queryCommentByUserId(Integer userId, int pageNo) {
		Page<CommentCustom> page = new Page<CommentCustom>();
		page.setPageSize(10);
		page.setPageNo(pageNo);
		page.getParams().put("userId", userId);
		List<CommentCustom> commentList = commentMapperCustom.queryCommentByUserId(page);
		for (CommentCustom comment : commentList) {
			comment.setTime(dateConvert.convert2s(comment.getCommentTime()));
		}
		page.setResults(commentList);
		return page;
	}

}
