package com.echo.applet;

import java.awt.*;
import java.awt.print.*;
import java.io.*;
import java.net.URL;

import javax.swing.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.echo.util.StringUtils;
import com.echo.util.ReadFile;
/**
 * ChartApplet.java
 * 
 * Copyright (c) 2003,
 * All rights reserved.
 * 
 * @author WangChunQiao
 * @version 1.0
 * Date:2003-9-09
 */


/**
 * 
 */
public class ChartAppletTemp extends JApplet {

	private PageFormat pageFormat;
	private String pageTitle,pageHeader;
	private int pageStartNo;
	private int paperSize;
	private String [][] data ;
	private String [] colTopic ;
	private int [] colSpan ;
	private String [] colName ;
	private int[] colWidth ;
	private int [] colType;
	private int keyColNo = -1;
	private JPanel chartPanel = new JPanel();
	private int rows,cols;
	
	private boolean valid = true;
	private String [] colFullName;
	
	private Document doc;

	/**
	 * Applet constructor
	 */
	public void init()
	{
		String xml = getParameter("xml");
		if (xml == null)
		{
			xml=new ReadFile().read("aa");
		}
		System.out.print(xml);
		SAXReader saxReader = new SAXReader();
		try {
			File mapping=File.createTempFile("mapping", "xml");
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(mapping));
			bufWriter.write(xml);
			bufWriter.newLine();
			bufWriter.close();
			doc = saxReader.read(mapping);
					// 用于记录学生编号的变量

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDoc(Document doc)
	{
		this.doc = doc;
		
	}
	
	public void start()
	{
		setVisible(false);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		if(doc != null)
		{
			g.drawString(doc.getRootElement().element("baseTable").getTextTrim(), 10, 10);
			
		}
		else
		{
			g.drawString("test", 10, 10);
		}

		
	}


}
