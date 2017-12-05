package com.echo.aop;

import java.lang.reflect.Method;  
import org.springframework.aop.ThrowsAdvice;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
/**
 *  AOP 方法执行时，错误LOG输出类
 * @author yanlong
 *
 */
public class LogThrow implements ThrowsAdvice {
	private Logger logger = (Logger) LogFactory.getLog(this.getClass().getName());
	 public void afterThrowing(Method method, Object[] args, Object target,Throwable subclass) 
	 		throws Throwable {   
		 		logger.info("调用的方法"+method.getName());
					
					if(args != null && args.length > 0 ){
						StringBuffer sb = new StringBuffer();
						sb.append("目标参数列表：");
						for(Object obj : args){
							
							sb.append(args.toString() +",");
						}
						
						logger.info(sb.toString());
						
						
					}else{
						logger.info(method.getName()+"无参数。。。。");
					}
					logger.info("调用的类对象"+target);
					logger.info("所抛出的异常类型"+subclass +"异常信息："+subclass.getMessage());
					
				
		             
		   } 

}
