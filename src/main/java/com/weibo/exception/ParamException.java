package com.weibo.exception;

public class ParamException extends Exception {
	private String errMsg;

	public ParamException(String errMsg) {
		super();
		this.errMsg = errMsg;
	}
}
