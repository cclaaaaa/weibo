package com.weibo.service;

import org.springframework.transaction.annotation.Transactional;

import com.weibo.po.CollectCustom;
import com.weibo.po.Page;
@Transactional
public interface CollectService {

	// 收藏
	void collect(CollectCustom collection);

	// 取消收藏
	void uncollect(CollectCustom collection);

	// 该用户是否收藏 0——否 1——是
	int isCollect(Integer userId, Integer weiboId);

	// 查询我的收藏列表
	Page<CollectCustom> queryMyCollection(Integer userId, int pageNo);

}
