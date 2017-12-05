package com.echo.util.xml;

import java.io.*;

import org.dom4j.*;
import org.dom4j.io.SAXReader;


public class xml2String2xml {
	public static void main(String[] args)
	{
		try
		{
			File xmlInFile = new File("D://myProject/aaa/WebRoot/WEB-INF/classes/com/echo/util/xml/ergogramListSet.xml");
			BufferedReader bufReader = new BufferedReader(new FileReader(xmlInFile));
			File mapping=File.createTempFile("mapping", "xml");
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(mapping));
			String line = bufReader.readLine();
			while(line!=null)
			{
				bufWriter.write(line);
				bufWriter.newLine();
				line=bufReader.readLine();
			}
			bufWriter.close();
			bufReader.close();
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(mapping);
			XmlReader xmlReader = new XmlReader();
			xmlReader.searchNode(document.getRootElement());
			Element root = document.getRootElement();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
