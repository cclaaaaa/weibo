package com.weibo.mapper;

import java.util.List;

import com.weibo.po.MentionCustom;
import com.weibo.po.User;
import com.weibo.po.UserCustom;
import com.weibo.po.UserVo;

public interface UserMapperCustom {

	// 用户登录
	public List<UserCustom> loginVerify(UserVo userVo);

	// 根据用户id查询用户信息
	public List<UserCustom> queryInfoByUserId(int id);

	// 修改用户id为userId的用户信息
	public void updateByUserId(UserVo userVo);

	// 查询用户发博数
	public int queryWeiboCount(int id);

	// 查询用户关注数
	public int queryFollowCount(Integer userId);

	// 查询用户粉丝数
	public int queryFansCount(Integer userId);

	// 根据用户username查询用户
	public List<UserCustom> queryByUsername(String username);

	// 添加用户
	public void insertUser(User user);

	// 查询省市
	public String queryPC(String province);

	// 查询用户被转发量
	public int queryRepostCount(Integer userId);

	// 查询用户被评论数量
	public int queryCommentCount(Integer userId);

	// 查询用户被回复量
	public int queryReplyCount(Integer userId);

	// 根据userId查询关注列表
	public List<UserCustom> queryFollowList(int userId);

	// 根据userId查询粉丝列表
	public List<UserCustom> queryFansList(Integer userId);

	// 修改密码
	public void updatePassword(User u);

	// 模糊查询用户
	public List<UserCustom> queryUserByWord(String keyWord);

}