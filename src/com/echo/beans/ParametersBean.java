package com.echo.beans;

import java.io.Serializable;

public class ParametersBean implements Serializable{
	
	/**
	 * 接受前台参数
	 */
	private static final long serialVersionUID = -2856263655224392103L;
	private String username;//用户ID
	private String password;//用户密码
	private String password2;//用户重复密码
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
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
