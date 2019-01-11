package com.weibo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.exception.ParamException;


public class ExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView view = new ModelAndView();
		if (ex instanceof ParamException) {
			view.setViewName("500");
		}else{
			view.setViewName("500");
		}
		return view;
	}

}
