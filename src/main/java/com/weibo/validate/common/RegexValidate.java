package com.weibo.validate.common;

import org.apache.commons.lang.StringUtils;

import com.weibo.utils.RegexUtils;

public class RegexValidate extends AbstractValidate {
	// 正则
	private String pattern;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	protected boolean execute() {
		if (StringUtils.isBlank(value)) {
			return true;
		}
		if (StringUtils.isBlank(pattern)) {
			throw new RuntimeException("pattern is empty");
		}
		return RegexUtils.regexMatch(pattern, value);
	}

}
