/**
 * 文件名： addLog.java
 * 描述： TODO(日志AOP切面类)
 * 修改人： 
 * 修改时间： 2013-04-11
 * 修改内容：创建类
 * @author: yanloong
 * @date: 2013-04-11
 * @version V1.0
 */
package com.echo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * 类名：addLog
 * 
 * @author: yanloong
 * @version 1.0
 */

public class AddLog implements MethodBeforeAdvice,AfterReturningAdvice,ThrowsAdvice{

	// 定义LOG4J对象
	static Logger logger = Logger.getLogger(AddLog.class.getName());

	// 日志配置文件
	static Properties properties = null;
	// 读取日志配置文件
	static {
		properties = new Properties();

		try {

			FileInputStream in = new FileInputStream("/" + getWEBINFAddress()
					+ File.separator + "conif/log4j/Log.properties");

			properties.load(in);
		} catch (FileNotFoundException e) {
			logger.error("没有找到日志配置文件，请确认你的路径是否正确。");
		} catch (IOException e) {
			logger.error("日志配置文件读写错误");
		}
	}

	/**
	 * 描述: 记录系统安全日志的方法
	 * 
	 * @author: lio
	 * 
	 * @param joinPoint
	 * @version: 1.0
	 */
	@SuppressWarnings("unused")
	private void addLog(JoinPoint joinPoint) {

		String key = joinPoint.getTarget().getClass().getName() + "."
				+ joinPoint.getSignature().getName();
		// 得到方法描述信息
		String info = properties.getProperty(key);
		if (info != null && !info.equals("") && !info.equals(" ")) {
			logger.info(info);
		} else {
			/*
			 * System.out.println("请检查您的日志配置文件，AOP" +
			 * "中配置了此方法记录日志，但是没有在配置文件中找到该方法的描述，方法名：" + key);
			 */
		}

	}

	/**
	 * 处理路径
	 * 
	 * @return
	 */
	public static String getWEBINFAddress() {
		Class theClass = AddLog.class;
		java.net.URL u = theClass.getResource("");
		// str会得到这个函数所在类的路径
		String str = u.toString();
		
		// 截去一些前面6个无用的字符
		str = str.substring(6, str.length());
		// 将%20换成空格（如果文件夹的名称带有空格的话，会在取得的字符串上变成%20）
		str = str.replaceAll("%20", " ");
		// 查找“WEB-INF”在该字符串的位置
		int num = str.indexOf("WEB-INF");
		// 截取即可
		str = str.substring(0, num + "WEB-INF".length());
		return str;
	}

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
