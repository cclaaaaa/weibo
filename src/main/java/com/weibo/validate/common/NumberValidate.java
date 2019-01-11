package com.weibo.validate.common;

import org.apache.commons.lang.StringUtils;

import com.weibo.utils.RegexUtils;


public class NumberValidate extends AbstractValidate{

	@Override
	protected boolean execute() {
		if(StringUtils.isBlank(value)){
			return true;
		}
		String pattern = "^\\d+$";
        return RegexUtils.regexMatch(pattern, value);
	}

}
