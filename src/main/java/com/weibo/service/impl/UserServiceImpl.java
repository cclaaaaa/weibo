package com.weibo.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.mapper.MentionMapperCustom;
import com.weibo.mapper.UserMapper;
import com.weibo.mapper.UserMapperCustom;
import com.weibo.po.User;
import com.weibo.po.UserCustom;
import com.weibo.po.UserVo;
import com.weibo.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapperCustom userMapperCustom;

	@Autowired
	private MentionMapperCustom mentionMapperCustom;

	// 用户登录
	@Override
	public List<UserCustom> doUserLogin(UserVo userVo) {
		return userMapperCustom.loginVerify(userVo);
	}

	// 根据用户id查询用户信息
	@Override
	public List<UserCustom> queryInfoByUserId(int id) {
		return userMapperCustom.queryInfoByUserId(id);
	}

	// 修改个人信息
	@Override
	public void updateUserInfo(UserVo userVo) {
		userMapperCustom.updateByUserId(userVo);
	}

	// 根据用户id查询用户的微博数
	@Override
	public int queryWeiboCount(Integer userId) {
		return userMapperCustom.queryWeiboCount(userId);
	}

	// 根据用户id查询用户的关注数
	@Override
	public int queryFollowCount(Integer userId) {
		return userMapperCustom.queryFollowCount(userId);
	}

	// 根据用户id查询用户的粉丝数
	@Override
	public int queryFansCount(Integer userId) {
		return userMapperCustom.queryFansCount(userId);
	}

	// 根据username查询用户
	@Override
	public List<UserCustom> queryByUsername(String username) {
		return userMapperCustom.queryByUsername(username);
	}

	// 用户注册
	@Transactional
	@Override
	public void addUser(User user) {
		userMapperCustom.insertUser(user);
		// 创建mention 记录与我相关
		List<UserCustom> userList = userMapperCustom.queryByUsername(user.getUsername());
		UserCustom u = userList.get(0);
		mentionMapperCustom.insertMention(u.getUserId());
	}

	// 查询省市
	@Override
	public String queryPC(String province) {
		return userMapperCustom.queryPC(province);
	}

	// 计算用户年龄
	@Override
	public int calculateAge(Date bir) {
		Calendar cal_bir = Calendar.getInstance();
		Calendar cal_now = Calendar.getInstance();

		cal_bir.setTime(bir);
		cal_now.setTime(new Date());

		int age = cal_now.get(Calendar.YEAR) - cal_bir.get(Calendar.YEAR);
		return age;
	}

	// 根据userId查询关注列表
	@Override
	public List<UserCustom> queryFollowList(int userId) {
		return userMapperCustom.queryFollowList(userId);
	}

	// 根据userId查询粉丝列表
	@Override
	public List<UserCustom> queryFansList(Integer userId) {
		return userMapperCustom.queryFansList(userId);
	}

	// 修改密码
	@Override
	public void updatePassword(User u) {
		userMapperCustom.updatePassword(u);
	}

	// 模糊查询用户
	@Override
	public List<UserCustom> queryUserByWord(String keyWord) {
		return userMapperCustom.queryUserByWord(keyWord);
	}

}
