package com.weibo.controller.process;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.weibo.po.UserCustom;
import com.weibo.service.LikesService;

@Component
public class LikesProcess {
	@Autowired
	private LikesService likesService;

	/**
	 * 点赞
	 * 
	 * @param weiboId
	 * @param response
	 * @param session
	 */
	public void like(int weiboId, HttpServletResponse response, HttpSession session) {
		UserCustom user = (UserCustom) session.getAttribute("user");
		likesService.like(weiboId, user.getUserId());
	}

	/**
	 * 取消赞
	 * 
	 * @param weiboId
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	public void unlike(int weiboId, HttpServletResponse response, HttpSession session) {
		UserCustom user = (UserCustom) session.getAttribute("user");
		likesService.unlike(weiboId, user.getUserId());
	}
}
