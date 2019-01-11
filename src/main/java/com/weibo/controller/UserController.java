package com.weibo.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.weibo.controller.model.UserIsExistModel;
import com.weibo.controller.param.RegisterParam;
import com.weibo.controller.param.UserPasswordParam;
import com.weibo.controller.process.UserAccountProcess;
import com.weibo.po.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserAccountProcess userAccountProcess;

	// 登录页面
	@RequestMapping(value = "login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "goregister")
	public String goregister() {
		return "register";
	}

	// 查看用户名是否存在
	@RequestMapping("isExistUsername")
	@ResponseBody
	public UserIsExistModel isExistUsername(String username) {
		UserIsExistModel model = new UserIsExistModel();
		boolean flag = userAccountProcess.isExistUsername(username);
		if (flag) {
			model.setMessage("yes");
		} else {
			model.setMessage("no");
		}
		return model;
	}

	// 用户退出
	@RequestMapping(value = "exit")
	public String exit(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}

	// 用户注册
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(RegisterParam registerParam, HttpServletRequest request, HttpServletResponse response) {
		return userAccountProcess.doRegister(registerParam, request, response);

	}

	// 注册页面（生成验证码）
	@RequestMapping(value = "getImageCheckCode")
	public void getImageCheckCode(HttpServletRequest request, HttpServletResponse response) {
		userAccountProcess.getImageCheckCode(request, response);
	}

	// 用户登录
	@RequestMapping(value = "user_Login", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request, HttpSession session, UserVo userVo) {
		return userAccountProcess.doUserLogin(request, session, userVo);
	}

	// 跳至修改用户信息视图
	@RequestMapping(value = "updateInfo")
	public String updateInfo(HttpSession session, Model model) {

		return userAccountProcess.updateInfo(session, model);
	}

	// 提交修改用户信息
	@RequestMapping(value = "submitInfo", method = RequestMethod.POST)
	public String submitInfo(HttpSession session, Model model, MultipartFile user_face, UserVo userVo)
			throws IllegalStateException, IOException, ParseException {
		return userAccountProcess.submitInfo(session, model, user_face, userVo);

	}

	// 跳至修改密码页面
	@RequestMapping(value = "skipToResetPassword")
	public String skipToResetPassword(HttpSession session) {

		return userAccountProcess.skipToResetPassword(session);
	}

	// 修改密码
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	public String updatePassword(HttpSession session, HttpServletRequest request, UserPasswordParam param) {
		return userAccountProcess.updatePassword(session, request, param);
	}

	// 去往我的主页
	@RequestMapping(value = "queryMinePage")
	public String queryMinePage(HttpSession session, Model model, @RequestParam("pageNo") int pageNo) {
		return userAccountProcess.queryMinePage(session, model, pageNo);
	}

	// 访问用户主页
	@RequestMapping(value = "queryUserPage")
	public String queryUserPage(HttpServletRequest request, HttpSession session, @RequestParam("userId") int userId,
			@RequestParam("pageNo") int pageNo, Model model) {
		return userAccountProcess.queryUserPage(request, session, userId, pageNo, model);

	}

	// 模糊查询用户列表
	@RequestMapping("queryUserBySimpleName")
	public String queryUserBySimpleName(Model model, HttpSession session, @RequestParam("key") String key)
			throws Exception {
		return userAccountProcess.queryUserBySimpleName(model, session, key);
	}

}
