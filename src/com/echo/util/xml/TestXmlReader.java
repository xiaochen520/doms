package com.echo.util.xml;


public class TestXmlReader 
{
	public static void main(String[]args)
	{
	XmlReader reader = new XmlReader();
//	HashMap hm = new HashMap();
	reader.iterateWholeXML("ergogramListSet.xml");
	}
}
