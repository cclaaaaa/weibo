package com.weibo.service;

import java.util.List;

import com.weibo.po.Relation;

public interface RelationService {

	// 查询相互关注
	public List<Relation> queryMutual(Relation relation) ;

	// 查询是否单方关注
	public List<Relation> queryUnilateral(Relation relation) ;

	// 关注
	public void follow(Relation relation, int flag);

	// 取关
	public void unfollow(Relation relation, int flag) ;

	// 查询关系
	public int queryRelation(Integer userId, Integer followId);

}
