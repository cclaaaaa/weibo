package com.weibo.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cglib.beans.BeanMap;

import com.weibo.utils.BaseUtils;
import com.weibo.validate.annotation.LengthAnno;
import com.weibo.validate.annotation.NumberAnno;
import com.weibo.validate.annotation.RegexAnno;
import com.weibo.validate.annotation.RequireAnno;
import com.weibo.validate.annotation.ValidateFactory;
import com.weibo.validate.common.AbstractValidate;
import com.weibo.validate.common.ValidateResult;

public class ValidateProcess {

	public static boolean hasAnnotation(Class<?> clazz) {
		for (Class<?> cla = clazz; cla != null; cla = cla.getSuperclass()) {
			// 如果cla是object那么结束
			if (cla == Object.class) {
				break;
			}
			// 得到参数
			Field[] fields = cla.getDeclaredFields();
			for (Field field : fields) {
				// 获取注解
				Annotation[] annotations = field.getAnnotations();
				for (Annotation annotation : annotations) {
					// 获取注解类型
					Class<? extends Annotation> type = annotation.annotationType();
					if (type.equals(RequireAnno.class) || type.equals(NumberAnno.class) || type.equals(RegexAnno.class)
							|| type.equals(LengthAnno.class)) {
						return true;
					}
				}
			}

		}
		return false;
	}

	/**
	 * 验证对象
	 * 
	 * @param entity
	 * @return
	 */
	public static ValidateResult validateObject(Object param) {
		ValidateResult result = new ValidateResult();
		Map<String, String> errorMap = validate(param);
		if(errorMap.size() == 0){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
			//字符串缓冲
			StringBuffer buffer = new StringBuffer();
			//遍历错误信息
			for(String key : errorMap.keySet()){
				String value = errorMap.get(key);
				if(buffer.length() != 0){
					buffer.append(",");
				}
				String defaultError = ValidateFactory.getDefaultError(key);
				if(defaultError != null){
					buffer.append(key).append(":").append(defaultError);
				}else{
					buffer.append(value);
				}
			}
			//设置错误信息
			result.setErrorMsg(buffer.toString());
		}
		return result;
	}

	/**
	 * 通过对象验证数据
	 * 
	 * @param param
	 * @return
	 */
	private static Map<String, String> validate(Object param) {
		Map<String, String> errorMap = new HashMap<String, String>();
		if (param == null) {
			return errorMap;
		}
		// 获得对象,及继承对象
		recursionFieldValidate(param, errorMap);
		return errorMap;
	}

	/**
	 * 
	 * @param param
	 * @param errorMap
	 */
	private static void recursionFieldValidate(Object param, Map<String, String> errorMap) {
		BeanMap fieldMap = BeanMap.create(param);
		// 循环父类
		for (Class<?> cla = param.getClass(); cla != null; cla = cla.getSuperclass()) {
			if (cla == Object.class) {
				break;
			}

			Field[] fields = cla.getDeclaredFields();
			for (Field field : fields) {
				// 参数名
				String fieldName = field.getName();
				// 得到参数名对应的参数值
				Object object = fieldMap.get(fieldName);
				// 如果是list容器，递归
				if (object != null && field.getType().equals(List.class)) {
					for (Object itemObject : (List<?>) object) {
						recursionFieldValidate(itemObject, errorMap);
					}
				}
				// 如果是类对象,递归
				else if (object != null && !BaseUtils.isBaseObject(object)) {
					recursionFieldValidate(object, errorMap);
				}
				// 属性为基本对象
				else {
					validateField(field, fieldMap, errorMap);
				}
			}
		}
	}

	/**
	 * 验证字段数据
	 * 
	 * @param field
	 * @param fieldMap
	 * @param errorMap
	 */
	private static void validateField(Field field, BeanMap fieldMap, Map<String, String> errorMap) {
		String value = null;
		String fieldName = field.getName();
		Object object = fieldMap.get(fieldName);
		if (object == null) {
			value = "";
		} else if (object != null && BaseUtils.isBaseObject(object)) {
			value = object.toString();
		} else {
			return;
		}

		// 获取参数的注解类型
		Annotation[] annotations = field.getAnnotations();
		for (Annotation annotation : annotations) {
			Class<?> type = annotation.annotationType();
			AbstractValidate validate = ValidateFactory.getValidate(field, type, value);
			if (validate == null) {
				continue;
			}
			ValidateResult result = validate.validate();
			if (!result.isSuccess()) {
				// 一个属性多个错误合并成一个错误
				if (errorMap.containsKey(fieldName)) {
					errorMap.put(fieldName, errorMap.get(fieldName) + "," + result.getErrorMsg());
				} else {
					errorMap.put(fieldName, result.getErrorMsg());
				}
			}
		}
	}

}
