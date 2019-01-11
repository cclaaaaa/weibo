package com.weibo.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weibo.po.User;
import com.weibo.po.UserCustom;
import com.weibo.po.UserVo;

public interface UserService {

	// 用户登录
	public List<UserCustom> doUserLogin(UserVo userVo);

	// 根据用户id查询用户所有信息
	public List<UserCustom> queryInfoByUserId(int id);

	// 修改用户信息
	@Transactional
	public void updateUserInfo(UserVo userVo);

	// 根据用户id查询用户的微博数
	public int queryWeiboCount(Integer userId);

	// 根据用户id查询用户的关注数
	public int queryFollowCount(Integer userId);

	// 根据用户id查询用户的粉丝数
	public int queryFansCount(Integer userId);

	// 根据username查找用户
	public List<UserCustom> queryByUsername(String username);

	// 用户注册
	public void addUser(User user);

	// 查询省市
	public String queryPC(String province);

	// 根据userId查询关注列表
	public List<UserCustom> queryFollowList(int userId);

	// 根据userId查询粉丝列表
	public List<UserCustom> queryFansList(Integer userId);

	// 计算用户年龄
	public int calculateAge(Date bir);

	// 修改密码
	public void updatePassword(User u);

	// 查询用户
	public List<UserCustom> queryUserByWord(String keyWord);

}
