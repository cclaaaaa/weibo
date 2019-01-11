package com.weibo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.mapper.ReplyMapperCustom;
import com.weibo.po.Page;
import com.weibo.po.ReplyCustom;
import com.weibo.service.ReplyService;
import com.weibo.utils.DateConvert;

@Transactional
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapperCustom replyMapperCustom;
	
	

	// 添加回复
	@Override
	public void addReply(ReplyCustom replyCustom) {
		replyMapperCustom.addReply(replyCustom);
	}

	// 遍历评论id为commentId的回复
	@Override
	public List<ReplyCustom> queryReply(int commentId) {
		return replyMapperCustom.queryReply(commentId);
	}

	// 删除回复
	@Override
	public void deleteReplyById(int replyId) {
		replyMapperCustom.deleteReplyById(replyId);
	}

	// 根据userID查询回复列表
	@Override
	public Page<ReplyCustom> queryReplyByUserId(Integer userId, int pageNo) {
		Page<ReplyCustom> page = new Page<ReplyCustom>();
		page.setPageSize(10);
		page.setPageNo(pageNo);
		page.getParams().put("userId", userId);
		List<ReplyCustom> replyList = replyMapperCustom.queryReplyByUserId(page);
		for (ReplyCustom reply : replyList) {
			reply.setRtime(DateConvert.convert2s(reply.getTime()));
		}
		page.setResults(replyList);
		return page;
	}

}
