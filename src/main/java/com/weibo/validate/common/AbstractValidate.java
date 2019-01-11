package com.weibo.validate.common;

public abstract class AbstractValidate {
	// 传入的值
	protected String value;
	// 错误信息
	protected String errorMsg;

	// 验证业务逻辑
	protected abstract boolean execute();

	// 返回验证结果
	public ValidateResult validate() {
		ValidateResult result = new ValidateResult();
		if (execute()) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
			result.setErrorMsg(errorMsg);
		}
		return result;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
