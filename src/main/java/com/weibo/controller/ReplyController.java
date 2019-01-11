package com.weibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weibo.controller.process.ReplyProcess;
import com.weibo.po.ReplyCustom;

@Controller
public class ReplyController {

	@Autowired
	private ReplyProcess replyProcess;

	/**
	 * 删除回复
	 * 
	 * @param replyId
	 */
	@RequestMapping(value = "deleteReply")
	public void deleteReply(@RequestParam("replyId") Integer replyId) {
		replyProcess.deleteReply(replyId);
	}

	/**
	 * 添加回复
	 * 
	 * @param replyCustom
	 * @return
	 */
	@RequestMapping(value = "reply", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ReplyCustom reply(@RequestBody ReplyCustom replyCustom) {
		replyProcess.reply(replyCustom);
		return replyCustom;
	}

}
