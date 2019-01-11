package com.weibo.validate.common;

import org.apache.commons.lang.StringUtils;

public class RequireValidate extends AbstractValidate{

	@Override
	protected boolean execute() {
		if(StringUtils.isNotBlank(value)){
			return true;
		}
		return false;
	}

}
