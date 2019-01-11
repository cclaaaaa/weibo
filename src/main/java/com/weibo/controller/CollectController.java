package com.weibo.controller;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weibo.controller.process.CollectProcess;

@Controller
public class CollectController {

	@Autowired
	private CollectProcess collectProcess;

	// 收藏
	@RequestMapping(value = "collect")
	public void collect(@RequestParam("weiboId") int weiboId, HttpServletResponse response, HttpSession session) {
		collectProcess.collect(weiboId, response, session);
	}

	// 取消收藏
	@RequestMapping(value = "uncollect")
	public void uncollect(@RequestParam("weiboId") int weiboId, HttpServletResponse response, HttpSession session) {
		collectProcess.uncollect(weiboId, response, session);
	}

}
