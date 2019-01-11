package com.weibo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
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

import com.weibo.controller.model.UploadImgModel;
import com.weibo.controller.process.WeiboProcess;
import com.weibo.po.WeiboVo;

@Controller
public class WeiboController {

	@Autowired
	private WeiboProcess weiboProcess;

	/**
	 * 个人微博
	 * @param session
	 * @param request
	 * @param response
	 * @param weiboId
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/singleWeibo")
	public void singleWeibo(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("weiboId") Integer weiboId) throws ServletException, IOException {
		weiboProcess.singleWeibo(session, request, response, weiboId);
	}

	/**
	 * 删除微博
	 * @param weiboId
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "deleteWeibo", method = RequestMethod.GET)
	public void deleteWeibo(@RequestParam("weiboId") Integer weiboId, HttpServletResponse response,
			HttpServletRequest request) {
		weiboProcess.deleteWeibo(weiboId, response, request);
	}

	/**
	 * 转发微博
	 * @param session
	 * @param repostId
	 * @param repostContent
	 * @return
	 */
	@RequestMapping(value = "repost")
	public String repost(HttpSession session, @RequestParam("repostId") int repostId,
			@RequestParam("repostContent") String repostContent) {
		return weiboProcess.repost(session, repostId, repostContent);
	}

	/**
	 * 发微博
	 * @param request
	 * @param session
	 * @param model
	 * @param weiboVo
	 * @return
	 */
	@RequestMapping(value = "post")
	public String post(HttpServletRequest request, HttpSession session, Model model, WeiboVo weiboVo) {
		return weiboProcess.post(request, session, model, weiboVo);
	}

	/**
	 * 图片上传
	 * @param file
	 * @param model
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public UploadImgModel upload(MultipartFile file, Model model, HttpSession session) throws IllegalStateException, IOException {
		return weiboProcess.upload(file, model, session);
	}

	/**
	 * 实时 遍历所有微博
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "queryAllWeiboNow")
	public String queryAllWeiboNow(HttpSession session, Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo) {
		return weiboProcess.queryAllWeiboNow(session, model, pageNo);
	}

	/**
	 * 好友圈
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "queryAllWeiboFriends")
	public String queryAllWeiboFriends(HttpSession session, Model model, @RequestParam("pageNo") int pageNo) {
		return weiboProcess.queryAllWeiboFriends(session, model, pageNo);
	}

	/**
	 * 首页 遍历所有关注的人 和自己
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "queryAllWeiboFollow")
	public String queryAllWeiboFollow(HttpSession session, Model model, @RequestParam("pageNo") Integer pageNo) {
		return weiboProcess.queryAllWeiboFollow(session, model, pageNo);
	}

	/**
	 * 模糊查询 根据关键字查询微博
	 * @param model
	 * @param session
	 * @param key
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "queryWeiboByWord")
	public String queryWeiboByWord(Model model, HttpSession session, @RequestParam("key") String key,
			@RequestParam(value = "pageNo", required = false) int pageNo) {
		return weiboProcess.queryWeiboByWord(model, session, key, pageNo);
	}
}
