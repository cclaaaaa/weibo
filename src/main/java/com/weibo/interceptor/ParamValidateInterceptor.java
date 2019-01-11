package com.weibo.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weibo.exception.ParamException;
import com.weibo.validate.ValidateProcess;
import com.weibo.validate.common.ValidateResult;

public class ParamValidateInterceptor implements MethodInterceptor {
	@Autowired
	private HttpServletRequest request;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		Class<?> clazz = method.getDeclaringClass();
		if (AnnotationUtils.findAnnotation(clazz, Controller.class) != null) {
			// 接口参数验证
			Object[] objectArray = invocation.getArguments();
			Object param = null;
			if (objectArray.length >= 1) {
				param = objectArray[0];
				/*
				 * // 支付回调使用objectArray[1] if (objectArray.length == 2) { param
				 * = objectArray[1]; }
				 */

				Class<?> paramType = param.getClass();
				// 判断参数是否有注解
				if (ValidateProcess.hasAnnotation(paramType)) {
					ValidateResult result = ValidateProcess.validateObject(param);
					if (!result.isSuccess()) {
						throw new ParamException(result.getErrorMsg());
					}
				}
			}
			Object result = invocation.proceed();
			return result;

		}
		return invocation.proceed();

	}

}
