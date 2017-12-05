package com.echo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class StringUtil {
	public static String toStr(String str){
		String encode = "GB2312";
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	        	  return str;
	           }      
	       } catch (Exception exception) {
	       }      
	       encode = "ISO-8859-1";
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	        	  return new String(str.getBytes(encode), "UTF-8");      
	           }      
	       } catch (Exception exception1) {      
	       }
//	       encode = "GBK";
//	        try {   
//	            if (str.equals(new String(str.getBytes(encode), encode))) {   
//	            	return new String(str.getBytes(encode), "UTF-8");
//	            }   
//	        } catch (Exception exception3) {
//	        } 
		return str;
	}
	/**
	 * 判断字符串是否为null 如何为null 将 字符串转为空字符串
	 * @param str
	 * @return
	 */
	public static String isNullUtil(String str){
		if (str == null) {
			return "";
		}
		return str.trim();
	}
	/**
	 * 判断字符串是否为null 如何为null 将 字符串转为空字符串
	 * @param str
	 * @return
	 */
	public static String isNullStr(String str){
		if (null == str || str.equals("null")) {
			return "";
		}
		return str.trim();
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getNowDate(){
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date currTime = new java.util.Date();
		String curTime = formatter.format(currTime);
		return curTime;
	}
	
	
	public static boolean jcFlag(String str){
		if (str.endsWith("井场")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 将值为‘全部’的字符串转为空字符串 用于检索
	 * @param str
	 * @return
	 */
	public static String isAlltil(String str){
		if ("全部".equals(str)) return "";
		return str.trim();
	}
	
	public static  Date parseDate(String strFormat, String dateValue) {
		if (dateValue == null)
			return null;
		if (strFormat == null)
//			strFormat = "yyyy-MM-dd HH:mm:ss";
		strFormat = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = null;
		Date newDate = null;
//		Calendar c = Calendar.getInstance();//
//		int hour = c.get(Calendar.HOUR_OF_DAY); 
//		int minute = c.get(Calendar.MINUTE); 
//		int second = c.get(Calendar.SECOND);
//		dateValue += " " + hour + ":" + minute + ":" + second;
		try {
			dateFormat = new SimpleDateFormat(strFormat);
			newDate = dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			pe.printStackTrace();
			newDate = null;
		}
		return newDate;
	}
}
