package com.echo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;  
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
/**
 *  AOP 方法执行之前LOG输出类
 * @author yanlong
 *
 */
public class LogBefore implements MethodBeforeAdvice{

	private Logger logger = (Logger) LogFactory.getLog(this.getClass().getName());
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		logger.info("调用的方法"+arg0.getName()+"开始。。。。");
		if(arg1 != null && arg1.length > 0 ){
			StringBuffer sb = new StringBuffer();
			sb.append("目标参数列表：");
			for(Object obj : arg1){
				sb.append(obj +",");
			}
			
			logger.info(sb.toString());
			
			
		}else{
			logger.info(arg0.getName()+"无参数。。。。");
		}
		
		logger.info("调用的类对象："+arg2.toString());
		
		
	}

}
