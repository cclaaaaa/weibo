package com.weibo.utils;

public class BaseUtils {
	/**
	 * 判断是不是基础的Class对象
	 * 
	 * @return
	 */
	public static boolean isBaseObject(Object object) {

		return isBaseObject(object.getClass());

	}

	/**
	 * 判断是不是基础的Class对象
	 * 
	 * @return
	 */
	public static boolean isBaseObject(Class<?> cla) {
		if (cla.equals(String.class) || cla.equals(Integer.class) || cla.equals(Long.class) || cla.equals(Float.class)
				|| cla.equals(Double.class) || cla.equals(Byte.class) || cla.equals(Short.class)) {
			return true;
		}
		return false;
	}
}
