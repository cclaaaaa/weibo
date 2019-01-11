package com.weibo.controller.process;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.weibo.controller.base.BaseProcess;
import com.weibo.controller.param.RegisterParam;
import com.weibo.controller.param.UserPasswordParam;
import com.weibo.po.MentionCustom;
import com.weibo.po.Page;
import com.weibo.po.User;
import com.weibo.po.UserCustom;
import com.weibo.po.UserVo;
import com.weibo.po.WeiboCustom;
import com.weibo.service.MentionService;
import com.weibo.service.RelationService;
import com.weibo.service.UserService;
import com.weibo.service.WeiboService;
import com.weibo.utils.CompareValueUtils;
import com.weibo.utils.DateConvert;
import com.weibo.utils.UploadUtils;
import com.weibo.utils.VerifyCode;

@Component
public class UserAccountProcess extends BaseProcess {
	// UserService
	@Autowired
	private UserService userService;

	// WeiboService
	@Autowired
	private WeiboService weiboService;

	// RelationService
	@Autowired
	private RelationService relationService;

	@Autowired
	private MentionService mentionService;

	/**
	 * 注册操作
	 * 
	 * @param registerParam
	 * @param request
	 * @param response
	 * @return
	 */
	public String doRegister(RegisterParam registerParam, HttpServletRequest request, HttpServletResponse response) {
		// 验证username是否存在
		if (!isExistUsername(registerParam.getUsername())) {
			@SuppressWarnings("unused")
			int flag = 0;
			String vCode = registerParam.getvCode();
			// 验证码校验
			if (!(request.getSession().getAttribute("verifyCode").toString().toLowerCase())
					.equals(vCode.toLowerCase())) {
				request.setAttribute("error_vc", "验证码错误");
				request.getSession().removeAttribute("vCode");
				flag = 1;
			}
			if (flag == 0) {
				// 校验成功
				request.setAttribute("register_success", "注册成功 即刻登录");
				User user = new User();
				user.setUsername(registerParam.getUsername());
				user.setPassword(registerParam.getPassword());
				userService.addUser(user);
				return "login";
			} else {
				// 校验失败
				request.setAttribute("username", registerParam.getUsername());
				return "register";
			}

		} else {
			request.setAttribute("username", registerParam.getUsername());
			request.setAttribute("error_username", "用户名已存在");
			return "register";
		}

	}

	/**
	 * 用户登录操作
	 * 
	 * @param request
	 * @param session
	 * @param userVo
	 * @return
	 */
	public String doUserLogin(HttpServletRequest request, HttpSession session, UserVo userVo) {
		List<UserCustom> userList = userService.doUserLogin(userVo);

		// 验证账号密码
		if (userList.size() > 0) {

			// 用户基础信息
			UserCustom user = userList.get(0);

			// 用户年龄
			user.setAge(userService.calculateAge(user.getBir()));

			// 省市
			user.setP(userService.queryPC(user.getProvince()));
			user.setC(userService.queryPC(user.getCity()));

			// 微博数wi
			int weiboCount = userService.queryWeiboCount(user.getUserId());
			// 关注
			int followCount = userService.queryFollowCount(user.getUserId());
			// 粉丝
			int fansCount = userService.queryFansCount(user.getUserId());
			user.setWeiboCount(weiboCount);
			user.setFollowCount(followCount);
			user.setFansCount(fansCount);

			// 读取 数据库中保存的 [上次退出时] 与我相关数
			MentionCustom mention = mentionService.queryLastMention(user.getUserId());

			user.setMentionCustom(mention);
			user.setPassword(null);
			session.setAttribute("user", user);
			request.setAttribute("pageNo", 1);
			return "forward:/queryAllWeiboNow.action?pageNo=1";
		} else {
			return "login";
		}
	}

	/**
	 * skip to updateInfo
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	public String updateInfo(HttpSession session, Model model) {
		UserCustom user = (UserCustom) session.getAttribute("user");
		// 读取 数据库中保存的 [上次退出时] 与我相关数
		MentionCustom mention = mentionService.queryLastMention(user.getUserId());

		user.setMentionCustom(mention);
		// bir格式转化 返回String
		user.setBir_String(DateConvert.convert2d(user.getBir()));

		model.addAttribute("user", user);
		session.setAttribute("user", user);
		return "/user/userinfo";

	}

	/**
	 * 提交对用户信息的修改
	 * 
	 * @param session
	 * @param model
	 * @param user_face
	 * @param userVo
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws ParseException
	 */
	public String submitInfo(HttpSession session, Model model, MultipartFile user_face, UserVo userVo)
			throws IllegalStateException, IOException, ParseException {
		// 得到自身用户id
		UserCustom user = (UserCustom) session.getAttribute("user");
		int userId = user.getUserId();
		String face = user.getFace();

		userVo.getUserCustom().setUserId(userId);
		userVo.getUserCustom().setFace(face);

		// 原始名称
		String originalFilename = user_face.getOriginalFilename();
		// 上传图片
		// 新图片名
		String newFileName = UploadUtils.uploadPhoto(user_face, originalFilename);
		if (newFileName == null) {
			throw new RuntimeException("上传图片加载失败");
		}
		userVo.getUserCustom().setFace(newFileName);

		userVo.getUserCustom().setBir(DateConvert.convert2Date(userVo.getUserCustom().getBir_String()));

		// 参数绑定model
		userService.updateUserInfo(userVo);
		user = userVo.getUserCustom();
		model.addAttribute("user", user);
		// 读取 数据库中保存的 [上次退出时] 与我相关数
		MentionCustom mention = mentionService.queryLastMention(user.getUserId());
		user.setMentionCustom(mention);
		// 用户年龄
		user.setAge(userService.calculateAge(user.getBir()));
		// 微博数wi
		int weiboCount = userService.queryWeiboCount(user.getUserId());
		// 关注
		int followCount = userService.queryFollowCount(user.getUserId());
		// 粉丝
		int fansCount = userService.queryFansCount(user.getUserId());
		user.setWeiboCount(weiboCount);
		user.setFollowCount(followCount);
		user.setFansCount(fansCount);
		// 所在地
		String province = userService.queryPC(user.getProvince());
		String city = userService.queryPC(user.getCity());
		user.setP(province);
		user.setC(city);
		session.setAttribute("user", user);
		return "/user/userinfo";
	}

	/**
	 * 前往自己的主页
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	public String queryMinePage(HttpSession session, Model model, int pageNo) {
		UserCustom user = (UserCustom) session.getAttribute("user");

		Page<WeiboCustom> page = weiboService.qeuryByUserId(user.getUserId(), pageNo);
		// sql.Date 转换
		for (WeiboCustom weiboCustom : page.getResults()) {
			// 将date格式化 精确到s
			weiboCustom.setDate(DateConvert.convert2s(weiboCustom.getPostTime()));
			// 非原创 即属于转发微博
			if (weiboCustom.getOriginal() == 0) {
				WeiboCustom repostWeibo = weiboService.queryWeiboByWeiboId(weiboCustom.getRepostId()).get(0);
				repostWeibo.setDate(DateConvert.convert2s(repostWeibo.getPostTime()));
				weiboCustom.setRepost(repostWeibo);
			}
		}
		model.addAttribute("weiboList", page.getResults());
		page.setResults(null);
		model.addAttribute("page", page);
		// 读取 数据库中保存的 [上次退出时] 与我相关数
		MentionCustom mention = mentionService.queryLastMention(user.getUserId());
		user.setMentionCustom(mention);
		session.setAttribute("user", user);
		model.addAttribute("user", user);

		return "/user/mine";
	}

	/**
	 * 访问用户主页
	 * 
	 * @param request
	 * @param session
	 * @param userId
	 * @param pageNo
	 * @param model
	 * @return
	 */
	public String queryUserPage(HttpServletRequest request, HttpSession session, int userId, int pageNo, Model model) {
		UserCustom user = (UserCustom) session.getAttribute("user");

		if (user.getUserId() == userId) {
			return "forward:queryMinePage.action";
		}

		List<UserCustom> othersList = userService.queryInfoByUserId(userId);
		UserCustom others = othersList.get(0);
		// 被访问用户
		// queryInfoByUserId 仅仅查出信息 没有userId ????
		others.setUserId(userId);
		model.addAttribute("others", others);

		int relation = relationService.queryRelation(user.getUserId(), others.getUserId());
		model.addAttribute("relation", relation);

		// 遍历微博
		Page<WeiboCustom> page = weiboService.qeuryByUserId(userId, pageNo);
		for (WeiboCustom weiboCustom : page.getResults()) {
			weiboCustom.setDate(DateConvert.convert2s(weiboCustom.getPostTime()));
			// 非原创 即属于转发微博
			if (weiboCustom.getOriginal() == 0) {
				WeiboCustom repostWeibo = weiboService.queryWeiboByWeiboId(weiboCustom.getRepostId()).get(0);
				repostWeibo.setDate(DateConvert.convert2s(repostWeibo.getPostTime()));
				weiboCustom.setRepost(repostWeibo);
			}
		}
		model.addAttribute("weiboList", page.getResults());
		// 微博数
		int weiboCount = userService.queryWeiboCount(userId);
		// 关注
		int followCount = userService.queryFollowCount(userId);
		// 粉丝
		int fansCount = userService.queryFansCount(userId);
		others.setWeiboCount(weiboCount);
		others.setFollowCount(followCount);
		others.setFansCount(fansCount);
		// 所在地
		others.setP(userService.queryPC(user.getProvince()));
		others.setC(userService.queryPC(user.getCity()));
		// 年龄
		others.setAge(userService.calculateAge(user.getBir()));

		model.addAttribute("others", others);
		page.setResults(null);
		model.addAttribute("page", page);
		// 读取 数据库中保存的 [上次退出时] 与我相关数
		MentionCustom mention = mentionService.queryLastMention(user.getUserId());
		user.setMentionCustom(mention);
		session.setAttribute("user", user);
		return "/user/others";
	}

	/**
	 * 查找相关nickname的用户
	 * 
	 * @param model
	 * @param session
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String queryUserBySimpleName(Model model, HttpSession session, String key) throws Exception {
		UserCustom me = (UserCustom) session.getAttribute("user");
		// 查询结果列表
		List<UserCustom> userList = userService.queryUserByWord(key);
		for (UserCustom userCustom : userList) {
			// 年龄
			userCustom.setAge(userService.calculateAge(userCustom.getBir()));
			// 省
			userCustom.setP(userService.queryPC(userCustom.getProvince()));
			// 市
			userCustom.setC(userService.queryPC(userCustom.getCity()));

			// 与我关系
			int state = relationService.queryRelation(me.getUserId(), userCustom.getUserId());
			userCustom.getRelation().setState(state);
		}
		model.addAttribute("fansList", userList);
		return "/search/search_people";
	}

	/**
	 * 跳转重置密码
	 */
	public String skipToResetPassword(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "/user/password";
		} else {
			return "login";
		}
	}

	/**
	 * 修改密碼
	 * 
	 * @param session
	 * @param request
	 * @param param
	 * @return
	 */
	public String updatePassword(HttpSession session, HttpServletRequest request, UserPasswordParam param) {
		// 原密码
		User user = (User) session.getAttribute("user");
		String oldPassword = userService.queryByUsername(user.getUsername()).get(0).getPassword();

		// 判断原密码是否一致
		boolean old_isEq = CompareValueUtils.isEquals(oldPassword, param.getOldPassword());
		if (!old_isEq) {
			request.setAttribute("error_old", "原密码不正确！");
		}
		// 新密码是否一致
		boolean pw_isEq = CompareValueUtils.isEquals(param.getNewPassword(), param.getRePassword());
		if (!pw_isEq) {
			request.setAttribute("error_new", "两次输入的新密码不一致");
		}
		if (!old_isEq || !pw_isEq) {
			return "password";
		} else {
			User u = new User();
			u.setUserId(user.getUserId());
			u.setPassword(param.getNewPassword());
			userService.updatePassword(u);
			request.setAttribute("updatepassword_success", "密码修改成功 请重新登录！");
			return "login";
		}
	}

	/**
	 * 输出验证码
	 * 
	 * @param request
	 * @param response
	 */
	public void getImageCheckCode(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		VerifyCode vc = new VerifyCode();// 创建验证码类
		BufferedImage image = vc.getImage();// 创建验证码图片
		System.out.println("生成验证码：" + vc.getText());
		request.getSession().setAttribute("verifyCode", vc.getText());
		try {
			// 输出图片到页面
			VerifyCode.output(image, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 用户名是否存在
	public boolean isExistUsername(String username) {

		List<UserCustom> userList = userService.queryByUsername(username);
		if (userList.size() > 0) {
			return true;
		}
		return false;

	}
}
