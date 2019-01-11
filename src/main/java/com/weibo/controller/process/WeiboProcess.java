package com.weibo.controller.process;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.weibo.controller.model.UploadImgModel;
import com.weibo.po.CommentCustom;
import com.weibo.po.MentionCustom;
import com.weibo.po.Page;
import com.weibo.po.ReplyCustom;
import com.weibo.po.User;
import com.weibo.po.UserCustom;
import com.weibo.po.WeiboCustom;
import com.weibo.po.WeiboVo;
import com.weibo.service.CollectService;
import com.weibo.service.CommentService;
import com.weibo.service.LikesService;
import com.weibo.service.MentionService;
import com.weibo.service.ReplyService;
import com.weibo.service.UserService;
import com.weibo.service.WeiboService;
import com.weibo.utils.DateConvert;
import com.weibo.utils.UploadUtils;

import net.sf.json.JSONObject;

@Component
public class WeiboProcess {
	// Weibo
	@Autowired
	private WeiboService weiboService;

	// User
	@Autowired
	private UserService userService;

	// Comment
	@Autowired
	private CommentService commentService;

	// Reply
	@Autowired
	private ReplyService replyService;

	// Mention
	@Autowired
	private MentionService mentionService;

	// Likes
	@Autowired
	private LikesService likesService;

	// Collect
	@Autowired
	private CollectService collectService;

	/**
	 * 独立微博页面
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param weiboId
	 * @throws IOException
	 * @throws ServletException
	 */
	public void singleWeibo(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Integer weiboId) throws ServletException, IOException {
		//
		User user = (User) session.getAttribute("user");
		// 微博数
		int weiboCount = userService.queryWeiboCount(user.getUserId());
		// 关注
		int followCount = userService.queryFollowCount(user.getUserId());
		// 粉丝
		int fansCount = userService.queryFansCount(user.getUserId());

		// 微博主体
		List<WeiboCustom> weiboList = weiboService.queryWeiboByWeiboId(weiboId);
		WeiboCustom weibo = weiboList.get(0);
		weibo.setDate(DateConvert.convert2s(weibo.getPostTime()));

		// 非原创 即属于转发微博
		if (weibo.getOriginal() == 0) {
			WeiboCustom repostWeibo = weiboService.queryWeiboByWeiboId(weibo.getRepostId()).get(0);
			repostWeibo.setDate(DateConvert.convert2s(repostWeibo.getPostTime()));
			weibo.setRepost(repostWeibo);
		}
		// 评论主体
		List<CommentCustom> commentList = commentService.queryComment(weiboId);
		for (CommentCustom commentCustom : commentList) {
			// 遍历回复
			List<ReplyCustom> replyList = (replyService.queryReply(commentCustom.getCommentId()));
			for (ReplyCustom replyCustom : replyList) {
				replyCustom.setRtime(DateConvert.convert2s(replyCustom.getTime()));
			}
			commentCustom.setTime(DateConvert.convert2s(commentCustom.getCommentTime()));
			commentCustom.setReplyList(replyList);
		}

		request.setAttribute("user", user);
		request.setAttribute("weibo", weibo);
		request.setAttribute("weiboCount", weiboCount);
		request.setAttribute("followCount", followCount);
		request.setAttribute("fansCount", fansCount);
		request.setAttribute("commentList", commentList);

		request.getRequestDispatcher("/WEB-INF/jsp/weibo/single.jsp").forward(request, response);

	}

	/**
	 * 删除微博
	 * 
	 * @param weiboId
	 * @param response
	 * @param request
	 */
	public void deleteWeibo(@RequestParam("weiboId") Integer weiboId, HttpServletResponse response,
			HttpServletRequest request) {
		weiboService.deleteByWeiboId(weiboId);
	}

	/**
	 * 转发微博
	 * 
	 * @param session
	 * @param repostId
	 * @param repostContent
	 * @return
	 */
	public String repost(HttpSession session, int repostId, String repostContent) {

		// 微博扩展类
		WeiboCustom weibo = new WeiboCustom();

		// userId
		User user = (User) session.getAttribute("user");
		weibo.setUserId(user.getUserId());

		// 发送时间
		Date postTime = new java.sql.Date(new java.util.Date().getTime());
		weibo.setPostTime(postTime);

		// 内容
		weibo.setContent(repostContent);

		// 转发Id
		weibo.setRepostId(repostId);

		weiboService.repost(weibo);
		return "redirect:queryAllWeiboNow.action?pageNo=1";
	}

	/**
	 * 发微博
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param weiboVo
	 * @return
	 */
	public String post(HttpServletRequest request, HttpSession session, Model model, WeiboVo weiboVo) {
		Map<Integer, String> picMap = new HashMap<Integer, String>();
		for (int i = 1; i < 10; i++) {
			String pic = request.getParameter("pic_" + i);
			picMap.put(i, pic);
		}
		weiboVo.getWeiboCustom().setPic1(picMap.get(1));
		weiboVo.getWeiboCustom().setPic2(picMap.get(2));
		weiboVo.getWeiboCustom().setPic3(picMap.get(3));
		weiboVo.getWeiboCustom().setPic4(picMap.get(4));
		weiboVo.getWeiboCustom().setPic5(picMap.get(5));
		weiboVo.getWeiboCustom().setPic6(picMap.get(6));
		weiboVo.getWeiboCustom().setPic7(picMap.get(7));
		weiboVo.getWeiboCustom().setPic8(picMap.get(8));
		weiboVo.getWeiboCustom().setPic9(picMap.get(9));

		// 用户id
		User user = (User) session.getAttribute("user");
		weiboVo.getWeiboCustom().setUserId(user.getUserId());

		// 发送时间
		Date postTime = new java.sql.Date(new java.util.Date().getTime());
		weiboVo.getWeiboCustom().setPostTime(postTime);

		weiboService.post(weiboVo);
		return "redirect:queryAllWeiboNow.action";
	}

	/**
	 * 上传图片
	 * 
	 * @param file
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws Exception
	 */
	public UploadImgModel upload(MultipartFile file, Model model, HttpSession session)
			throws IllegalStateException, IOException {
		UploadImgModel imgModel = new UploadImgModel();
		// 原始名称
		String originalFilename = file.getOriginalFilename();
		// 上传图片
		String newFileName = UploadUtils.uploadPhoto(file, originalFilename);
		if (newFileName != null) {
			imgModel.setPicName(newFileName);
			return imgModel;
		}
		imgModel.setMessage("failed");
		return imgModel;
	}

	/**
	 * 遍历所以微博实时
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	public String queryAllWeiboNow(HttpSession session, Model model, Integer pageNo) {
		// 页码
		if (pageNo == 0) {
			pageNo = 1;
		}

		// 当前用户信息
		UserCustom user = (UserCustom) session.getAttribute("user");

		//
		// 遍历出微博列表
		Page<WeiboCustom> page = weiboService.queryAllWeiboNow(pageNo);
		for (WeiboCustom weiboCustom : page.getResults()) {
			// 将date格式化 精确到s
			weiboCustom.setDate(DateConvert.convert2s(weiboCustom.getPostTime()));

			// 用户是否赞过
			weiboCustom.setLikes(likesService.isLike(user.getUserId(), weiboCustom.getWeiboId()));

			// 用户是否收藏
			weiboCustom.setCollect(collectService.isCollect(user.getUserId(), weiboCustom.getWeiboId()));

			// 查询微博转发 评论 点赞次数
			weiboCustom.setRepostCount(weiboService.queryRepostCount(weiboCustom.getWeiboId()));
			weiboCustom.setCommentCount(weiboService.queryCommentCount(weiboCustom.getWeiboId()));
			weiboCustom.setLikeCount(weiboService.queryLikeCount(weiboCustom.getWeiboId()));
			// 非原创 即属于转发微博
			if (weiboCustom.getOriginal() == 0) {
				WeiboCustom repostWeibo = weiboService.queryWeiboByWeiboId(weiboCustom.getRepostId()).get(0);
				repostWeibo.setDate(DateConvert.convert2s(repostWeibo.getPostTime()));
				weiboCustom.setRepost(repostWeibo);
			}
		}
		// 与我相关数据库存储值
		MentionCustom mention = mentionService.queryLastMention(user.getUserId());
		user.setMentionCustom(mention);

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

		model.addAttribute("weiboList", page.getResults());
		page.setResults(null);
		model.addAttribute("page", page);
		model.addAttribute("user", user);

		session.setAttribute("user", user);
		return "/weibo/home";

	}

	/**
	 * 好友圈
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public String queryAllWeiboFriends(HttpSession session, Model model, Integer pageNo) {

		// 当前用户信息
		UserCustom user = (UserCustom) session.getAttribute("user");

		// 页码
		if (pageNo == 0) {
			pageNo = 1;
		}
		//
		// 遍历出微博列表
		Page<WeiboCustom> page = weiboService.queryAllWeiboFriends(user.getUserId(), pageNo);
		for (WeiboCustom weiboCustom : page.getResults()) {
			// 将date格式化 精确到s
			weiboCustom.setDate(DateConvert.convert2s(weiboCustom.getPostTime()));

			// 用户是否赞过
			weiboCustom.setLikes(likesService.isLike(user.getUserId(), weiboCustom.getWeiboId()));

			// 用户是否收藏
			weiboCustom.setCollect(collectService.isCollect(user.getUserId(), weiboCustom.getWeiboId()));

			// 查询微博转发 评论 点赞次数
			weiboCustom.setRepostCount(weiboService.queryRepostCount(weiboCustom.getWeiboId()));
			weiboCustom.setCommentCount(weiboService.queryCommentCount(weiboCustom.getWeiboId()));
			weiboCustom.setLikeCount(weiboService.queryLikeCount(weiboCustom.getWeiboId()));
			// 非原创 即属于转发微博
			if (weiboCustom.getOriginal() == 0) {
				WeiboCustom repostWeibo = weiboService.queryWeiboByWeiboId(weiboCustom.getRepostId()).get(0);
				repostWeibo.setDate(DateConvert.convert2s(repostWeibo.getPostTime()));
				weiboCustom.setRepost(repostWeibo);
			}
		}
		// 与我相关数据库存储值
		MentionCustom mention = mentionService.queryLastMention(user.getUserId());
		user.setMentionCustom(mention);

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

		model.addAttribute("weiboList", page.getResults());
		page.setResults(null);
		model.addAttribute("page", page);
		model.addAttribute("user", user);

		session.setAttribute("user", user);
		return "/weibo/home_friends";

	}

	/**
	 * 首页 遍历所有关注的和自己的微博
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	public String queryAllWeiboFollow(HttpSession session, Model model, @RequestParam("pageNo") Integer pageNo) {

		// 当前用户信息
		UserCustom user = (UserCustom) session.getAttribute("user");

		// 页码
		if (pageNo == 0) {
			pageNo = 1;
		}
		//
		// 遍历出微博列表 首页
		Page<WeiboCustom> page = weiboService.queryAllWeiboFollow(user.getUserId(), pageNo);
		for (WeiboCustom weiboCustom : page.getResults()) {
			// 将date格式化 精确到s
			weiboCustom.setDate(DateConvert.convert2s(weiboCustom.getPostTime()));

			// 用户是否赞过
			weiboCustom.setLikes(likesService.isLike(user.getUserId(), weiboCustom.getWeiboId()));

			// 用户是否收藏
			weiboCustom.setCollect(collectService.isCollect(user.getUserId(), weiboCustom.getWeiboId()));

			// 查询微博转发 评论 点赞次数
			weiboCustom.setRepostCount(weiboService.queryRepostCount(weiboCustom.getWeiboId()));
			weiboCustom.setCommentCount(weiboService.queryCommentCount(weiboCustom.getWeiboId()));
			weiboCustom.setLikeCount(weiboService.queryLikeCount(weiboCustom.getWeiboId()));
			// 非原创 即属于转发微博
			if (weiboCustom.getOriginal() == 0) {
				WeiboCustom repostWeibo = weiboService.queryWeiboByWeiboId(weiboCustom.getRepostId()).get(0);
				repostWeibo.setDate(DateConvert.convert2s(repostWeibo.getPostTime()));
				weiboCustom.setRepost(repostWeibo);
			}
		}
		// 与我相关数据库存储值
		MentionCustom mention = mentionService.queryLastMention(user.getUserId());
		user.setMentionCustom(mention);

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

		model.addAttribute("weiboList", page.getResults());
		page.setResults(null);
		model.addAttribute("page", page);
		model.addAttribute("user", user);

		session.setAttribute("user", user);
		return "/weibo/home_follow";

	}

	/**
	 * 模糊查询微博 根据关键字
	 * 
	 * @param model
	 * @param session
	 * @param key
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "queryWeiboByWord")
	public String queryWeiboByWord(Model model, HttpSession session, String key, Integer pageNo) {
		if (pageNo == 0) {
			pageNo = 1;
		}
		// 当前用户信息
		UserCustom user = (UserCustom) session.getAttribute("user");
		// 遍历出微博列表 首页
		Page<WeiboCustom> page = weiboService.queryWeiboByWord(key, pageNo);
		for (WeiboCustom weiboCustom : page.getResults()) {
			// 将date格式化 精确到s
			weiboCustom.setDate(DateConvert.convert2s(weiboCustom.getPostTime()));

			// 用户是否赞过
			weiboCustom.setLikes(likesService.isLike(user.getUserId(), weiboCustom.getWeiboId()));

			// 用户是否收藏
			weiboCustom.setCollect(collectService.isCollect(user.getUserId(), weiboCustom.getWeiboId()));

			// 查询微博转发 评论 点赞次数
			weiboCustom.setRepostCount(weiboService.queryRepostCount(weiboCustom.getWeiboId()));
			// weiboCustom.setCommentCount(weiboService.queryCommentCount(weiboCustom.getWeiboId()));
			weiboCustom.setLikeCount(weiboService.queryLikeCount(weiboCustom.getWeiboId()));
			// 非原创 即属于转发微博
			if (weiboCustom.getOriginal() == 0) {
				WeiboCustom repostWeibo = weiboService.queryWeiboByWeiboId(weiboCustom.getRepostId()).get(0);
				repostWeibo.setDate(DateConvert.convert2s(repostWeibo.getPostTime()));
				weiboCustom.setRepost(repostWeibo);
			}
		}
		// 与我相关数据库存储值
		MentionCustom mention = mentionService.queryLastMention(user.getUserId());
		user.setMentionCustom(mention);

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

		model.addAttribute("weiboList", page.getResults());
		page.setResults(null);
		model.addAttribute("page", page);
		model.addAttribute("user", user);

		session.setAttribute("user", user);

		return "/search/search_weibo";
	}
}
