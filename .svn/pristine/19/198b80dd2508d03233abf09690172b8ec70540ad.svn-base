/**
 * 
 */
package com.echo.applet;


import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.echo.util.StringUtils;

/**
 * 为所有创建Applet所需的参数文本，并实现了IChartAideTool中的部分功能。
 * @author wcq
 * @see com.echo.applet.IChartAideTool
 */
public abstract class ChartAideTool implements IChartAideTool
{
	protected Document form;
	
	/** 设置格式文件
	 * @param formName 所用格式的xml文件名。其路径相对于本类，或者以"/"打头的相对于classes的文件名，可以包含路径 
	 * @see com.echo.applet.IChartAideTool#setFormXml(java.lang.String)
	 */
	public void setFormXml(String formName)
	{
		setFormXml(formName,this.getClass());
	}
	
	public Document getFormXml()
	{
		return form;
	}

	/**
	 * 设置格式文件
	 * @param formName 所用格式的xml文件名。其路径相对于类c，或者以"/"打头的相对于classes的文件名，可以包含路径
	 * @param c 在类C中查找相对路径的formName文件
	 */
	public void setFormXml(String formName, Class c)
	{
		String filename = StringUtils.unescapeString(c.getResource(formName).toString().substring(6),"UTF-8");
		SAXReader saxReader = new SAXReader();
		try {
			System.out.println("filename="+filename);
			form = saxReader.read(new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * 从格式文件中获取某个节点的文本值
	 * @param strArr 相对于根节点的节点序列名数组
	 * @return 指定节点的文本值
	 */
	public String getFormText(String[] strArr)
	{
		if (form ==null) return null;
		Element e = form.getRootElement();
		
		for(int i= 0; i<strArr.length;i++)
		{
			e = e.element(strArr[i]);
			if(e==null) return null;
		}
	
		return e.getTextTrim();
	}

}
