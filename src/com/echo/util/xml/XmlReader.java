package com.echo.util.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.echo.util.StringUtils;

public class XmlReader {
	/**
	 * �������XML�ļ�����ȡ���нڵ��ֵ�������Ե�ֵ��������HashMap��
	 * 
	 * @param filename
	 *            String ������XML�ļ������·�����߾��·����
	 */
	Document d;
	public void iterateWholeXML(String filename) {
		try
		{
			Class c=this.getClass();
			String filename1 = StringUtils.unescapeString(c.getResource("").toString().substring(6),"UTF-8");
			if(filename.charAt(1)!=':')
			{
				filename = StringUtils.unescapeString(this.getClass().getResource(filename).toString().substring(6),"UTF-8");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
				
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File(filename));
			Element root = document.getRootElement();
			searchNode(root);
			
			// ���ڼ�¼ѧ���ŵı�

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchNode(Element node)
	{
		Iterator itAttr = node.attributeIterator();
		Attribute attr=null;
		while(itAttr.hasNext())
		{
			attr = (Attribute)itAttr.next();
		}
		// ������node�����к��ӽڵ�
		for (Iterator iter = node.elementIterator(); iter.hasNext();) 
		{
			// ��ȡperson�ڵ��age���Ե�ֵ
			searchNode((Element) iter.next());
		}
	}
}
