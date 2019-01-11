package com.weibo.controller.process;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weibo.po.CollectCustom;
import com.weibo.po.UserCustom;
import com.weibo.service.CollectService;

@Component
public class CollectProcess {
	@Autowired
	private CollectService collectService;

	/**
	 * 
	 * @param weiboId
	 * @param response
	 * @param session
	 */
	public void collect(int weiboId, HttpServletResponse response, HttpSession session) {
		UserCustom user = (UserCustom) session.getAttribute("user");
		CollectCustom collection = new CollectCustom();
		collection.setUserId(user.getUserId());
		collection.setWeiboId(weiboId);
		// 收藏时间
		Date time = new java.sql.Date(new java.util.Date().getTime());
		collection.setCollectTime(time);
		collectService.collect(collection);
	}

	/**
	 * 
	 * @param weiboId
	 * @param response
	 * @param session
	 */
	public void uncollect(int weiboId, HttpServletResponse response, HttpSession session) {
		UserCustom user = (UserCustom) session.getAttribute("user");
		CollectCustom collection = new CollectCustom();
		collection.setUserId(user.getUserId());
		collection.setWeiboId(weiboId);
		collectService.uncollect(collection);
	}

}
