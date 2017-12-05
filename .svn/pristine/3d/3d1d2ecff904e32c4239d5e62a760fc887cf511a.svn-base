package com.echo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.echo.util.xml.XmlReader;

public class ReadFile {
	
	public String read(String filename)
	{
		StringBuffer result = new StringBuffer();
		try
		{
			File xmlInFile = new File(StringUtils.unescapeString(this.getClass().getResource("/com/echo/ergogram/ergogramListSet.xml").toString().substring(6)));
			BufferedReader bufReader = new BufferedReader(new FileReader(xmlInFile));
			String line = bufReader.readLine();
			while(line!=null)
			{
				result.append(line).append("\n");
				line=bufReader.readLine();
			}
			bufReader.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.print("flag1");
		return result.toString();
	}

}
