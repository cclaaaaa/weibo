package com.weibo.service;

import java.util.List;

import com.weibo.po.Page;
import com.weibo.po.WeiboCustom;
import com.weibo.po.WeiboVo;

public interface WeiboService {

	// 根据用户id查询微博列表
	public Page<WeiboCustom> qeuryByUserId(Integer userId, int pageNo);

	// 发微博
	public void post(WeiboVo weiboVo);

	// 删除微博
	public void deleteByWeiboId(Integer weiboId);

	// 根据微博Id查询微博信息
	public List<WeiboCustom> queryWeiboByWeiboId(Integer weiboId);

	// 转发
	public void repost(WeiboCustom weibo);

	// 查询点赞次数
	public int queryLikeCount(Integer weiboId);

	// 查询转发次数
	public int queryRepostCount(Integer weiboId);

	// 查询评论次数
	public int queryCommentCount(Integer weiboId);

	// 根据用户id查询被转发的微博列表
	public Page<WeiboCustom> queryRepostByUserId(Page<WeiboCustom> page);

	// 遍历所有微博 实时
	public Page<WeiboCustom> queryAllWeiboNow(Integer pageNo);

	// 遍历所有微博 好友圈
	public Page<WeiboCustom> queryAllWeiboFriends(Integer userId, Integer pageNo);

	// 遍历所有微博 首页
	public Page<WeiboCustom> queryAllWeiboFollow(Integer userId, Integer pageNo);

	// 根据关键字搜索相关微博
	public Page<WeiboCustom> queryWeiboByWord(String keyWord, Integer pageNo);

}
