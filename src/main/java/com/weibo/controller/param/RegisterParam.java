package com.weibo.controller.param;

import com.weibo.validate.annotation.LengthAnno;
import com.weibo.validate.annotation.NumberAnno;
import com.weibo.validate.annotation.RegexAnno;
import com.weibo.validate.annotation.RequireAnno;

public class RegisterParam {
	@RequireAnno
	@LengthAnno(minLength = 4, maxLength = 15)
	private String username;
	@LengthAnno(maxLength = 18)
	private String password;
	@LengthAnno(maxLength = 18)
	private String rePassword;
	@NumberAnno
	private String vCode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}
}
