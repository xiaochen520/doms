package com.echo.filter;

import java.util.Map;

import com.echo.action.LoginAction;
import com.echo.dto.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		//获取session
		Map session=actionInvocation.getInvocationContext().getSession();
		//获取拦截器对象
		Object action = (Action) actionInvocation.getAction();
		//如果获取的拦截器为 LoginAction或者UserLoginAction,则不进行拦截
		if(action instanceof LoginAction){
			return actionInvocation.invoke();
			}
		//得到session中的用户
		User user=(User) session.get("userInfo");
		//如果用户为空则跳到LOGIN页面，否则退出拦截
		if(user==null){
			return Action.LOGIN;
			}else{
				return actionInvocation.invoke();
			}
	}

}
