package com.weibo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.mapper.WeiboMapperCustom;
import com.weibo.po.Page;
import com.weibo.po.WeiboCustom;
import com.weibo.po.WeiboVo;
import com.weibo.service.WeiboService;

@Transactional
@Service("weiboService")
public class WeiboServiceImpl implements WeiboService {

	@Autowired
	private WeiboMapperCustom weiboMapperCustom;

	// 根据用户id查询微博列表
	@Override
	public Page<WeiboCustom> qeuryByUserId(Integer userId, int pageNo) {
		Page<WeiboCustom> page = new Page<WeiboCustom>();
		page.setPageNo(pageNo);
		page.setPageSize(8);
		page.getParams().put("userId", userId);
		List<WeiboCustom> weiboList = weiboMapperCustom.qeuryByUserId(page);
		page.setResults(weiboList);

		return page;
	}

	// 发送微博
	@Override
	public void post(WeiboVo weiboVo) {
		weiboMapperCustom.post(weiboVo);
	}

	// 删除微博
	@Override
	public void deleteByWeiboId(Integer weiboId) {
		weiboMapperCustom.deleteByWeiboId(weiboId);
	}

	// 根据微博id查询微博
	@Override
	public List<WeiboCustom> queryWeiboByWeiboId(Integer weiboId) {
		return weiboMapperCustom.queryWeiboByWeiboId(weiboId);
	}

	// 转发微博
	@Override
	public void repost(WeiboCustom weibo) {
		weiboMapperCustom.repost(weibo);
	}

	// 查询赞次数
	@Override
	public int queryLikeCount(Integer weiboId) {
		return weiboMapperCustom.queryLikeCount(weiboId);
	}

	// 查询转发次数
	@Override
	public int queryRepostCount(Integer weiboId) {
		return weiboMapperCustom.queryRepostCount(weiboId);
	}

	// 查询评论次数
	@Override
	public int queryCommentCount(Integer weiboId) {

		return weiboMapperCustom.queryCommentCount(weiboId) + weiboMapperCustom.queryReplyCount(weiboId);
	}

	// 根据用户id查询被转发的微博
	@Override
	public Page<WeiboCustom> queryRepostByUserId(Page<WeiboCustom> page) {

		List<WeiboCustom> weiboList = weiboMapperCustom.queryRepostByUserId(page);
		page.setResults(weiboList);
		return page;
	}

	// 首页遍历微博——实时
	@Override
	public Page<WeiboCustom> queryAllWeiboNow(Integer pageNo) {
		Page<WeiboCustom> page = new Page<WeiboCustom>();
		page.setPageNo(pageNo);
		page.setPageSize(8);
		List<WeiboCustom> weiboList = weiboMapperCustom.queryAllWeiboNow(page);
		page.setResults(weiboList);

		return page;
	}

	// 首页遍历微博——好友圈
	@Override
	public Page<WeiboCustom> queryAllWeiboFriends(Integer userId, Integer pageNo) {
		Page<WeiboCustom> page = new Page<WeiboCustom>();
		page.setPageNo(pageNo);
		page.setPageSize(8);
		page.getParams().put("userId1", userId);
		page.getParams().put("userId2", userId);
		List<WeiboCustom> weiboList = weiboMapperCustom.queryAllWeiboFriends(page);
		page.setResults(weiboList);

		return page;
	}

	// 首页遍历微博——首页
	@Override
	public Page<WeiboCustom> queryAllWeiboFollow(Integer userId, Integer pageNo) {
		Page<WeiboCustom> page = new Page<WeiboCustom>();
		page.setPageNo(pageNo);
		page.setPageSize(8);
		page.getParams().put("userId", userId);
		List<WeiboCustom> weiboList = weiboMapperCustom.queryAllWeiboFollow(page);
		page.setResults(weiboList);

		return page;
	}

	// 根据关键字搜索微博
	@Override
	public Page<WeiboCustom> queryWeiboByWord(String keyWord, Integer pageNo) {
		Page<WeiboCustom> page = new Page<WeiboCustom>();
		page.setPageNo(pageNo);
		page.setPageSize(8);
		page.getParams().put("keyWord", keyWord);
		List<WeiboCustom> weiboList = weiboMapperCustom.queryWeiboByWord(page);
		page.setResults(weiboList);

		return page;
	}

}
