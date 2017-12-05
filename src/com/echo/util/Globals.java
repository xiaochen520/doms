
/**
 * Globals.java
 * 
 * Copyright (c) 2002,安控科技有限公司
 * All rights reserved.
 * 
 * @author wangchunqiao
 * @version 1.0
 * Date:2003-09-01
 */
package com.echo.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 从classpath的根路径中提取配置文件(Globals.properties)
 * 此配置文件主要保存了预定义参数
 */
public class Globals
{
	private static Properties initProps = null;
	private static Class thisClass = com.echo.util.Globals.class;
	
	/*
	 * 设置initProps
	 * @throws Exception
	 */
	private static void  init() throws Exception
	{
		if (initProps == null)
		{
			InputStream in = null;
			initProps = new Properties();
			try
			{
				in = thisClass.getResourceAsStream("/Globals.properties");
				initProps.load(in);
			}
			catch(Exception e)
			{
				initProps = null;
				throw e;
			}
			finally
			{
				try{in.close();} catch(Exception e){}
			}
		}
	}
    
	
	/**
	 * 取得配置值，如果配置值为空或不存在，则返回一个空串。
	 * @param key 配置键名
	 * @return 配置值
	 */
	public static String getProperty(String key)
	{
		if (key == null || key.equals(""))
		{
			return null;
		}
		String value = null;
		if (initProps == null)
		{
			try
			{
				init();
			}
			catch (Exception e)
			{
				System.err.println("提取配置文件失败！");
				e.printStackTrace();
			}
		}
		try
		{
			value = initProps.getProperty(key);
		}
		catch (Exception ne)
		{
		}
		
		if (value == null)
		{
			value = "";
		} 
		return value;
	}
	
	public static void main(String[] args){
	}
}
