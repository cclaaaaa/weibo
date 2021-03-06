package com.weibo.mapper;

import java.util.List;
import java.util.Map;

import com.weibo.po.Page;
import com.weibo.po.WeiboCustom;
import com.weibo.po.WeiboVo;

public interface WeiboMapperCustom {

	// 遍历所有微博
	// public List<WeiboCustom> queryAllWeibo() throws Exception;

	// 分页遍历——实时
	public List<WeiboCustom> queryAllWeiboNow(Page<WeiboCustom> page);

	// 分页遍历——首页（我关注）
	public List<WeiboCustom> queryAllWeiboFollow(Page<WeiboCustom> page) ;

	// 分页遍历——好友圈（相互关注）
	public List<WeiboCustom> queryAllWeiboFriends(Page<WeiboCustom> page) ;

	
	// 根据用户id查询微博
	public List<WeiboCustom> qeuryByUserId(Page<WeiboCustom> page) ;

	// 发微博
	public void post(WeiboVo weiboVo) ;

	// 根据微博id删除微博
	public void deleteByWeiboId(Integer weiboId) ;

	// 根据微博id查询微博
	public List<WeiboCustom> queryWeiboByWeiboId(Integer weiboId);

	// 转发微博
	public void repost(WeiboCustom weibo) ;

	// 查询赞次数
	public int queryLikeCount(Integer weiboId);

	// 查询转发次数
	public int queryRepostCount(Integer weiboId);

	// 查询评论次数
	public int queryCommentCount(Integer weiboId);

	// 根据用户id查询被转发的微博
	public List<WeiboCustom> queryRepostByUserId(Page<WeiboCustom> page);

	// 根据关键字搜索微博
	public List<WeiboCustom> queryWeiboByWord(Page<WeiboCustom> page);

	// 查询回复次数
	public int queryReplyCount(Integer weiboId);

}