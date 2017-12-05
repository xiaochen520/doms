/**
 * 
 */
package com.echo.applet.pieChart;

import com.echo.applet.ChartAideTool;

import org.dom4j.Element;
import java.sql.ResultSet;

import java.util.Iterator;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.sql.ResultSet;

import com.echo.util.StringUtils;

/**
 * @author wcq
 * 
 */
public class PieChartAideTool extends ChartAideTool
{

	public String createDataPata(ResultSet rs)
	{
		if (form == null || rs==null) return "";// 如果没有设定格式文件或者记录集为空，则返回空串
		try
		{
			rs.first();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
		StringBuffer result = new StringBuffer();
		StringBuffer strBuf = new StringBuffer();
		StringBuffer strBuf2 = new StringBuffer();
		Element root = form.getRootElement();
		Element eData,eTmp = null,eTmp2;
		Iterator iter = null;
		String strTmp;

		eData = root.element("datas");
		if (eData == null) return "";
		
		iter = eData.elementIterator();
		strBuf.setLength(0);
		while (iter.hasNext())
		{
			eTmp = (Element) iter.next();
			strTmp = eTmp.getTextTrim();
			
			if (strTmp != null && strTmp.length() != 0)// 第一级节点中有子节点的需进行二级节点进行处理
			{
				strBuf.append(strTmp);
			}
			else
			{
				strBuf.append(" ");
			}

			strBuf.append(",");
		}
		if(strBuf.length()>0)
		{
			strBuf.setLength(strBuf.length()-1);
		}

		result.append("<param name=datas value='").append(strBuf);
		
		
		strBuf.setLength(0);
		eTmp = eData.element("dataFields");
		if (eTmp!=null)
		{
			iter = eTmp.elementIterator();
			strBuf2.setLength(0);
			
			while (iter.hasNext())
			{
				eTmp2 = (Element) iter.next();
				strBuf2.setLength(0);
				
				try
				{
					strTmp = eTmp2.element("desc").getTextTrim();
					if(strTmp != null && strTmp.trim().length()>0)
					{
						strBuf2.append(strTmp).append(",");
					}
					else
					{
						break;
					}
					
					strTmp = eTmp2.element("field").getTextTrim();
					if(strTmp != null && strTmp.trim().length()>0)
					{
						strBuf2.append(rs.getString(strTmp));
					}
					else
					{
						break;
					}
					strBuf.append(strBuf2.toString()).append(";");
				}
				catch(Exception e)
				{
					e.printStackTrace();
					//strBuf2.setLength(0);
				}
				
			}
		}
		if(strBuf.length()>0)
		{
			strBuf.setLength(strBuf.length()-1);
			result.append("|");//title 等与后面字段使用|分隔
			result.append(strBuf);

		}

		result.append("'>");
		return result.toString();
	}
	
	/**
	 * 此方法在本子类中不返回空串。
	 * 
	 * @param strWhere
	 * @return
	 */
	public String createDataPara(String strWhere)
	{
		return "";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.echo.applet.IChartAideTool#createFormPara()
	 */
	public String createFormPara()
	{
		if (form == null)
			return "";

		StringBuffer result = new StringBuffer();
		StringBuffer strBuf = new StringBuffer();
		Hashtable<String, String> htForm = new Hashtable<String, String>();
		Element root = form.getRootElement();
		Element eTmp = null,eTmp2;
		Iterator iter = null,iter2;
		String strTmp;

		Element elmForm= root.element("form");
		if (elmForm == null) return "";
	
		
		iter = elmForm.elementIterator();
		while (iter.hasNext())
		{
			eTmp = (Element) iter.next();
			strTmp = eTmp.getTextTrim();
			if (strTmp == null || strTmp.length() == 0)// 第一级节点中有子节点的需进行二级节点进行处理
			{
				strTmp = eTmp.getName();
				if(!strTmp.equalsIgnoreCase("fillColors") )// 第二级节点中只有"fillColors"的子节点不直接作为格式参数传递。
				{
					iter2 = eTmp.elementIterator();
					while(iter2.hasNext())
					{
						eTmp2 = (Element)iter2.next();
						strTmp= eTmp2.getTextTrim();
						if (strTmp != null && strTmp.length() > 0)// //第二级节点中没有子节点的直接作为格式参数
						{
							htForm.put(eTmp.getName()+eTmp2.getName(), strTmp);
						}
					}
				}
				else
				{
					iter2 = eTmp.elementIterator();
					strBuf.setLength(0);
					while(iter2.hasNext())
					{
						eTmp2 = (Element)iter2.next();
						strTmp= eTmp2.getTextTrim();
						if (strTmp != null && strTmp.length() > 0)// //第二级节点中没有子节点的直接作为格式参数
						{
							strBuf.append(strTmp).append(",");
						}
					}
					if(strBuf.length()>0)
					{
						strBuf.setLength(strBuf.length()-1);
						htForm.put("pieFillColorStrs", strBuf.toString());
					}
				}
			} else// 第一级节点中没有子节点的直接作为格式参数
			{
				htForm.put(eTmp.getName(), strTmp);
			}
		}
		Enumeration<String> enm = htForm.keys();
		while (enm.hasMoreElements())
		{
			strTmp = enm.nextElement();
			result.append("<param name=").append(strTmp);
			result.append(" value='").append(htForm.get(strTmp)).append("'>\n");
		}

		return result.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		PieChartAideTool et = new PieChartAideTool();
		et.setFormXml("ergogramChartSet.xml", et.getClass());
		System.out.println(et.createFormPara());

	}

}
