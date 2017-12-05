package com.echo.util;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.beans.PropertyDescriptor;

public class PropertyUtil {
	
	public static PropertyDescriptor getPropertyDescriptor(
			Class<? extends Object> clazz, String propertyName) {
		StringBuffer sb = new StringBuffer();// 构建一个可变字符串用来构建方法名称
		Method setMethod = null;
		Method getMethod = null;
		PropertyDescriptor pd = null;

		try {
			Field f = clazz.getDeclaredField(propertyName);// 根据字段名来获取字段
			if (f != null) {
				// 构建方法的后缀
				String methodEnd = propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1);
				sb.append("set" + methodEnd);// 构建set方法
				// 构建set 方法
				setMethod = clazz.getDeclaredMethod(sb.toString(),
						new Class[] { f.getType() });
				sb.delete(0, sb.length());// 清空整个可变字符串
				sb.append("get" + methodEnd);// 构建get方法
				// 构建get 方法
				getMethod = clazz.getDeclaredMethod(sb.toString(),
						new Class[] {});
				// 构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
				pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pd;
	}

	public static void setProperty(Object obj, String propertyName, Object value) {
		Class<? extends Object> clazz = obj.getClass();// 获取对象的类型
		PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);// 获取clazz类型中的propertyName的属性描述器
		Method setMethod = pd.getWriteMethod();// 从属性描述器中获取 set 方法
		try {
			setMethod.invoke(obj, new Object[] { value });// 调用 set
															// 方法将传入的value值保存属性中去
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 特殊处理复制数据带有颜色字体型号 去除颜色符号
	 * @param obj
	 * @param propertyName
	 * @param value
	 */
	public static String removeColor(String value) {
		
		String newStr = "";
		
		if(value != null && !"".equals(value)){
			if(value.indexOf("span") != -1){
				value = value.replaceAll("<span style='color: red;'>", "");
				value = value.replaceAll("</span>", "");
			}
			newStr = value;
		}
		return newStr;
	}

	public static Object getProperty(Object obj, String propertyName) {
		Class<? extends Object> clazz = obj.getClass();// 获取对象的类型
		PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);// 获取clazz类型中的propertyName的属性描述器
		Method getMethod = pd.getReadMethod();// 从属性描述器中获取 get 方法
		Object value = null;
		try {
			value = getMethod.invoke(clazz, new Object[] {});// 调用方法获取方法的返回值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;// 返回值
	}
}
