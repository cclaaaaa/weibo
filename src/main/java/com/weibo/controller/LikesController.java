package com.weibo.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weibo.controller.process.LikesProcess;
import com.weibo.po.UserCustom;
import com.weibo.service.LikesService;

@Controller
public class LikesController {

	@Autowired
	private LikesProcess likesProcess;

	// 点赞
	@RequestMapping(value = "like")
	public void like(@RequestParam("weiboId") int weiboId, HttpServletResponse response, HttpSession session)
			throws Exception {
		likesProcess.like(weiboId, response, session);
	}

	// 取消赞
	@RequestMapping(value = "unlike")
	public void unlike(@RequestParam("weiboId") int weiboId, HttpServletResponse response, HttpSession session) {
		likesProcess.unlike(weiboId, response, session);
	}
}
