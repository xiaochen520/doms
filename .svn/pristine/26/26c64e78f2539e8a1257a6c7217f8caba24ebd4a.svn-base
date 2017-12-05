package com.echo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
/**
 * AOP 方法执行之后LOG输出类
 * @author yanlong
 *
 */
public class LogAfterReturning implements AfterReturningAdvice {

	private Logger logger = (Logger) LogFactory.getLog(this.getClass().getName());
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		
		logger.info("调用方法返回的对象"+arg0);
		
		if(arg2 != null && arg2.length > 0 ){
			StringBuffer sb = new StringBuffer();
			sb.append("目标参数列表：");
			for(Object obj : arg2){
				sb.append(obj +",");
			}
			
			logger.info(sb.toString());
			
			
		}else{
			logger.info(arg1.getName()+"无参数。。。。");
		}
		logger.info("调用的类对象"+arg3);
		logger.info("调用的方法"+arg1.getName()+"结束。。。。");
		
	}

}
