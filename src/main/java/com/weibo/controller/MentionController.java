package com.weibo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weibo.controller.process.MentionProcess;
import com.weibo.po.MentionCustom;

@Controller
public class MentionController {

	@Autowired
	private MentionProcess mentionProcess;

	/**
	 * ajax长轮询 获得新通知
	 * 
	 * @param session
	 * @return
	 * @throws InterruptedException
	 */
	@ResponseBody
	@RequestMapping(value = "getNotice")
	public MentionCustom getNotice(HttpSession session) throws InterruptedException {
		return mentionProcess.getNotice(session);
	}

	/**
	 * 跳转到<转发过我的>
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 * @throws Exceptiond
	 */
	@RequestMapping(value = "toRepostPage")
	public String toRepostPage(HttpSession session, Model model, @RequestParam("pageNo") int pageNo) {
		return mentionProcess.toRepostPage(session, model, pageNo);
	}

	/**
	 * 跳转到<收到的评论>
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "toCommentPage")
	public String toCommentPage(HttpSession session, Model model, @RequestParam("pageNo") int pageNo) {
		return mentionProcess.toCommentPage(session, model, pageNo);
	}

	/**
	 * 跳转到<回复我的>
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "toReplyPage")
	public String toReplyPage(HttpSession session, Model model, @RequestParam("pageNo") int pageNo) {
		return mentionProcess.toReplyPage(session, model, pageNo);
	}

	/**
	 * 跳转到<赞过我的>
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "toLikePage")
	public String toLikePage(HttpSession session, Model model, @RequestParam("pageNo") int pageNo) {
		return mentionProcess.toLikePage(session, model, pageNo);
	}

	/**
	 * 跳转到<我的赞>
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "toMyLikes")
	public String toMyLikes(HttpSession session, Model model, @RequestParam("pageNo") int pageNo) {
		return mentionProcess.toMyLikes(session, model, pageNo);
	}

	/**
	 * 跳转到<我的收藏>
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "toMyCollection")
	public String toMyCollection(HttpSession session, Model model, @RequestParam("pageNo") int pageNo) {
		return mentionProcess.toMyCollection(session, model, pageNo);
	}
}
