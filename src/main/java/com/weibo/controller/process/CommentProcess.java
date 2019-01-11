package com.weibo.controller.process;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weibo.po.CommentCustom;
import com.weibo.service.CommentService;
import com.weibo.utils.DateConvert;

@Component
public class CommentProcess {
	@Autowired
	private CommentService commentService;
	
	/**
	 * 添加評論
	 * @param commentCustom
	 */
	public void comment(CommentCustom commentCustom) {
		commentService.addComment(commentCustom);
	}
	/**
	 * 加载相关评论
	 * @param request
	 * @param weiboId
	 * @return
	 */
	public List<CommentCustom> queryComment(HttpServletRequest request,int weiboId){
		List<CommentCustom> commentList = commentService.queryComment(weiboId);
		for (CommentCustom commentCustom : commentList) {
			commentCustom.setCountReply(commentService.qeuryCountReply(commentCustom.getCommentId()));
			commentCustom.setTime(DateConvert.convert2json(commentCustom.getCommentTime().getTime()));
		}
		return commentList;
	}
	/**
	 * 删除评论
	 * @param commentId
	 * @throws Exception
	 */
	public void deleteComment(int commentId) {
		commentService.deleteCommentById(commentId);
	}
}
