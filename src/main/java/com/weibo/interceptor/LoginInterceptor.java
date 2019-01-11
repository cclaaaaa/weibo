package com.weibo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.model.constant.UrlRightConstant;
import com.weibo.po.UserCustom;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求的url
		String url = request.getRequestURI();

		//忽略登录
		for (String ignoreLoginUrl : UrlRightConstant.loginNotNecessaryUrlList) {
			if (url.indexOf(ignoreLoginUrl) != -1) {
				return true;
			}
		}

		HttpSession session = request.getSession();
		UserCustom user = (UserCustom) session.getAttribute("user");

		if (user != null) {
			return true;
		}

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
