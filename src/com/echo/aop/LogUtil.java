package com.echo.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class LogUtil {
	// 定义LOG4J对象
	static Logger logger = Logger.getLogger(LogUtil.class.getName());
	
	public void logInfo(JoinPoint jp) {
		// TODO Auto-generated method stub
		String methodName = jp.getSignature().getName();
		
		logger.info("当前位置："+jp.getTarget().getClass().getName()+"---调用方法："+methodName);
		
		Object[] obj = jp.getArgs();
		if(obj != null && obj.length >0){
				logger.info("目标参数列表： ");
			for(int i = 0 ; i < obj.length;i++){
				logger.info("参数 params["+i+"]："+obj[i].toString());
				
			}
		}
		

	}
	
	
}
