package com.weibo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RegexUtils {
	/**
	 * 验证正则
	 * 
	 * @param pattern
	 * @param value
	 * @return
	 */
	public static boolean regexMatch(String pattern, String value) {
		if (StringUtils.isBlank(pattern) || StringUtils.isBlank(value)) {
			return false;
		}
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(value);
		return matcher.matches();
	}

}
