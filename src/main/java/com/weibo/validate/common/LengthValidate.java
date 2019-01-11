package com.weibo.validate.common;

import org.apache.commons.lang.StringUtils;

public class LengthValidate extends AbstractValidate {
	// 最小长度
	private int minLength;
	// 最大长度
	private int maxLength;

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	protected boolean execute() {
		int length = 0;
		if (StringUtils.isBlank(value)) {
			return true;
		}
		length = value.trim().length();
		if (length >= minLength && length <= maxLength) {
			return true;
		}
		return false;
	}

}
