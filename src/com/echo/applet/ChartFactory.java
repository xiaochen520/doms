package com.echo.applet;

import java.io.File;
import java.util.Hashtable;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.echo.applet.ergogram.Ergogram;
import com.echo.util.StringUtils;

public abstract class ChartFactory
{
	/**
	 * 从docHs对象中获取由xmlFileName指定的Document对象。
	 * 如果不能找到，则使用c同目录下的xmlFileName文件生成；如果生成成功的添加到docHs对象中，并返回。否则为空。
	 * @param docHs
	 * @param xmlFileName
	 * @param c
	 * @return
	 */
	public static Document getDocument(Hashtable<String,Document> docHs,String xmlFileName,Class c)
	{
		Document result = docHs.get(xmlFileName);
		if (result == null)
		{
			result = openFormXml(xmlFileName, c);
			if (result!=null)
			{
				docHs.put(xmlFileName, result);
			}
		}
		return result;
	}

	/**
	 * 打开格式文件
	 * @param formName 所用格式的xml文件名。其路径相对于类c，或者以"/"打头的相对于classes的文件名，可以包含路径
	 * @param c 在类C中查找相对路径的formName文件
	 */
	public static Document openFormXml(String formName,Class c)
	{
		Document doc =null;
		String filename = StringUtils.unescapeString(c.getResource(formName).toString().substring(6),"UTF-8");
		SAXReader saxReader = new SAXReader();
		try {
			System.out.println("filename="+filename);
			doc = saxReader.read(new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return doc;
	}

	public static String getElementText(Element elm ,String node)
	{
		if (elm == null || node == null) return null;
		String result=null;
		Element eTmp = elm.element(node);
		if(eTmp!=null)
		{
			result = eTmp.getTextTrim();
		}
		return result;
	}
}
