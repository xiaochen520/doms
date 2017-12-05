package com.echo.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ClearFieldErrorInterceptor extends AbstractInterceptor {   
	   
	/**
	 * 清空 FieldError与actionerror拦截器
	 */
	private static final long serialVersionUID = -3068884286971339936L;

	@Override   
	public String intercept(ActionInvocation invocation) throws Exception {   
	ActionSupport actionSupport = (ActionSupport)invocation.getAction();   
	actionSupport.clearErrorsAndMessages();   
	String resultCode = invocation.invoke();   
	return resultCode;   
	}

}