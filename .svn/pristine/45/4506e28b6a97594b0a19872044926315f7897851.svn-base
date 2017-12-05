package com.echo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;

//代理类实现了接口InvocationHandler 


public class LogProxy implements InvocationHandler{
	
	private Logger logger = (Logger) LogFactory.getLog(this.getClass().getName());
	
	private Object delegate;   
	//绑定代理对象   
	 public Object bind(Object delegate){   
			this.delegate = delegate;   
			return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),   
			delegate.getClass().  
			getInterfaces(), this);   
		}  

	
	
	//针对接口编程 
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;   
		try {
			  //在方法调用前后进行日志输出  
			logger.info(args[0] + " 开始审核数据....");   
			result = method.invoke(delegate, args); //调用绑定对象的
			logger.info(args[0]  + " 审核数据结束....");  

		}catch (Exception e){
			
			 logger.info( e.toString());   
		}
		
		
		return result;  
	}  
	
}
