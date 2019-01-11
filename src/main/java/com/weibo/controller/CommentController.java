package com.weibo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weibo.controller.process.CommentProcess;
import com.weibo.po.Comment;
import com.weibo.po.CommentCustom;
import com.weibo.service.CommentService;
import com.weibo.utils.DateConvert;

@Controller
public class CommentController {


	@Autowired
	private CommentProcess commentProcess;

	// 添加评论
	@RequestMapping(value = "comment", method = RequestMethod.POST, consumes = "application/json")
	public void comment(@RequestBody CommentCustom commentCustom) {
		commentProcess.comment(commentCustom);
	}

	// 遍历评论
	@RequestMapping(value = "queryComment", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentCustom> queryComment(HttpServletRequest request, @RequestParam("weiboId") int weiboId)
			{
		return commentProcess.queryComment(request, weiboId);
		
	}

	// 删除评论
	@RequestMapping(value = "deleteComment")
	public void deleteComment(@RequestParam("commentId") int commentId) {
		commentProcess.deleteComment(commentId);
	}
}
