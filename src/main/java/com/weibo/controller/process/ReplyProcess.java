package com.weibo.controller.process;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weibo.po.ReplyCustom;
import com.weibo.service.ReplyService;

@Component
public class ReplyProcess {
	@Autowired
	private ReplyService replyService;

	/**
	 * 删除回复
	 * @param replyId
	 */
	public void deleteReply(Integer replyId)  {
		replyService.deleteReplyById(replyId);
	}

	/**
	 * 添加回复
	 * @param replyCustom
	 * @return
	 */
	public ReplyCustom reply( ReplyCustom replyCustom)  {
		replyService.addReply(replyCustom);
		return replyCustom;
	}

}
