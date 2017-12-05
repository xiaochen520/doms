/**
 * 
 */
package com.echo.applet.ergogram;

import com.echo.applet.ChartAideTool;

import org.dom4j.Element;

import java.util.Iterator;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.sql.ResultSet;

import com.echo.util.StatementManager;
import com.echo.util.StringUtils;

/**
 * @author wcq
 * 
 */
public class ErgogramAideTool extends ChartAideTool
{

	/**
	 * 
	 * @param strWhere
	 * @return
	 */
	public String createDataPara(String strWhere)
	{
		if (form == null)
			return "";

		StringBuffer result = new StringBuffer();
		StringBuffer strBuf = new StringBuffer();
		StringBuffer strSql = new StringBuffer();
		StringBuffer strFields = new StringBuffer();
		StringBuffer strValue = new StringBuffer();
		StatementManager stmtMgr = new StatementManager();
		ResultSet rs = null;
		Element root = form.getRootElement();
		Element eTmp = null;
		Iterator iter = null;
		Vector<String> vctField = new Vector<String>();
		String strTmp;

		strTmp = root.element("objectField").getTextTrim();
		vctField.add(strTmp);
		strFields.append(strTmp);
		strTmp = root.element("timeField").getTextTrim();
		vctField.add(strTmp);
		strFields.append(",").append(strTmp);
		strTmp = root.element("dataField").getTextTrim();
		vctField.add(strTmp);
		strFields.append(",").append(strTmp);
		
		/*增加工图中附加的点标识数据*/
		iter = root.element("markingPointNoFields").elementIterator();
		while (iter.hasNext())
		{
			eTmp = (Element) (iter.next());
			if(eTmp != null)
			{
				strTmp = eTmp.getTextTrim();
				if(strTmp !=null && strTmp.length() > 0 )
				{
					vctField.add(strTmp);
					strFields.append(",").append(strTmp);
				}
			}
		}
		
		//参考线位置
		iter = root.element("axesY").element("referenceLines")
				.elementIterator();
		while (iter.hasNext())
		{
			eTmp = ((Element) (iter.next())).element("field");
			if(eTmp != null)
			{
				strTmp = eTmp.getTextTrim();
				if(strTmp !=null && strTmp.length() > 0 )
				{
					vctField.add(strTmp);
					strFields.append(",").append(strTmp);
				}
			}
		}
		iter = root.element("aideTexts").elementIterator();
		while (iter.hasNext())
		{
			eTmp = ((Element) (iter.next())).element("field");
			if(eTmp != null)
			{
				strTmp = eTmp.getTextTrim();
				if(strTmp !=null && strTmp.length() > 0 && !strTmp.equals("--"))
				{
					vctField.add(strTmp);
					strFields.append(",").append(strTmp);
				}
			}
		}
		
		//生成SQL语句
		String[] strArr = StringUtils.split(strWhere, ";");
		String[] strArr2 = null;
		for (int i = 0; i < strArr.length; i++)
		{
			strArr2 = StringUtils.split(strArr[i], ",");
			if(strArr2!=null && strArr2.length >1)
			{
				strValue.append("('").append(strArr2[0]).append("',to_date('");
				strValue.append(strArr2[1]).append("','yyyy-mm-dd hh24:mi:ss')),");
			}
		}
		strSql.append("select ").append(strFields).toString();
		strSql.append(" from ").append(root.element("table").getTextTrim());
		if (strValue.length() > 1)
		{
			strValue.setLength(strValue.length() - 1);
			strSql.append(" where (").append(
					root.element("objectField").getTextTrim()).append(",");
			strSql.append(root.element("timeField").getTextTrim()).append(
					") in ( ");
			strSql.append(strValue.toString()).append(")");
		}
//System.out.println(strSql.toString());
		rs = stmtMgr.executeQuery(strSql.toString());
		try
		{
			if (rs!=null && rs.first())
			{
				result.append("<param name=datas value='");
				do
				{
					strBuf.append(rs.getString(vctField.get(0))).append(",");
					strBuf.append(rs.getString(vctField.get(1))).append(",");
					strBuf.append(StringUtils.isNull(rs.getString(vctField.get(2)),""));
					for (int i = 3; i < vctField.size(); i++)
					{
						strBuf.append(";").append(vctField.get(i));
						strBuf.append(",")
								.append(StringUtils.isNull(rs.getString(vctField.get(i)),"--"));
					}
					strBuf.append("|");
				} while (rs.next());
				if (strBuf.length() > 1)
				{
					strBuf.setLength(strBuf.length() - 1);
				}
				result.append(strBuf).append("'>");
				
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result.setLength(0);//如果出现异常，返回空串
		}
		stmtMgr.close();

		return result.toString();
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
		StringBuffer strBuf2 = new StringBuffer();
		Hashtable<String, String> htForm = new Hashtable<String, String>();
		Element root = form.getRootElement();
		Element eTmp = null,eTmp2;
		Iterator iter = null,iter2;
		String strTmp;

		iter = root.elementIterator();
		while (iter.hasNext())
		{
			eTmp = (Element) iter.next();
			strTmp = eTmp.getTextTrim();
			if (strTmp == null || strTmp.length() == 0)//第一级节点中有子节点的需进行二级节点进行处理
			{
				strTmp = eTmp.getName();
				if(strTmp.equalsIgnoreCase("axesX") || strTmp.equalsIgnoreCase("axesY"))//第二级节点中只有"axesX","axesY"中的子节点直接作为格式参数传递。
				{
					iter2 = eTmp.elementIterator();
					while(iter2.hasNext())
					{
						eTmp2 = (Element)iter2.next();
						strTmp= eTmp2.getTextTrim();
						if (strTmp != null && strTmp.length() > 0)////第二级节点中没有子节点的直接作为格式参数
						{
							htForm.put(eTmp.getName()+eTmp2.getName(), strTmp);
						}
					}
				}
			} else//第一级节点中没有子节点的直接作为格式参数
			{
				htForm.put(eTmp.getName(), strTmp);
			}
		}
		htForm.remove("table");
		htForm.remove("objectField");
		htForm.remove("timeField");
		htForm.remove("dataField");
		Enumeration enm = htForm.keys();
		while (enm.hasMoreElements())
		{
			strTmp = (String) enm.nextElement();
			result.append("<param name=").append(strTmp);
			result.append(" value='").append(htForm.get(strTmp)).append("'>\n");
		}
		
		eTmp = root.element("chartLineColors");
		iter = eTmp.elementIterator();
		result.append("<param name=chartLinecColorStr value='");
		strBuf.setLength(0);
		while (iter.hasNext())
		{
			eTmp = (Element) (iter.next());
			strBuf.append(eTmp.getTextTrim()).append(",");
		}
		if (strBuf.length() > 0)
		{
			strBuf.setLength(strBuf.length() - 1);
		}
		result.append(strBuf.toString()).append("'>\n");
		
		eTmp = root.element("axesY").element("referenceLines");
		iter = eTmp.elementIterator();
		result.append("<param name=axesYreferenceLines value='");
		strBuf.setLength(0);
		while (iter.hasNext())
		{
			iter2 = ((Element)iter.next()).elementIterator();
			strBuf2.setLength(0);
			while(iter2.hasNext())
			{
				eTmp = (Element) (iter2.next());
				strBuf2.append(eTmp.getTextTrim()).append(",");
			}
			if(strBuf2.length()>0)
			{
				strBuf2.setLength(strBuf2.length()-1);
			}
			strBuf.append(strBuf2).append(";");
		}
		if (strBuf.length() > 0)
		{
			strBuf.setLength(strBuf.length() - 1);
		}
		result.append(strBuf.toString()).append("'>\n");
		
		eTmp = root.element("aideTexts");
		iter = eTmp.elementIterator();
		result.append("<param name=aideTexts value='");
		strBuf.setLength(0);
		while (iter.hasNext())
		{
			iter2 = ((Element)iter.next()).elementIterator();
			strBuf2.setLength(0);
			while(iter2.hasNext())
			{
				eTmp = (Element) (iter2.next());
				strBuf2.append(eTmp.getTextTrim()).append(",");
			}
			if(strBuf2.length()>0)
			{
				strBuf2.setLength(strBuf2.length()-1);
			}
			strBuf.append(strBuf2).append(";");
		}
		if (strBuf.length() > 0)
		{
			strBuf.setLength(strBuf.length() - 1);
		}
		result.append(strBuf.toString()).append("'>\n");
		
		//标志点参数
		eTmp = root.element("markingPointNoFields");
		iter = eTmp.elementIterator();
		result.append("<param name=markingPointNoFields value='");
		strBuf.setLength(0);
		while (iter.hasNext())
		{
			eTmp = (Element) (iter.next());
			strBuf.append(eTmp.getTextTrim()).append(",");
		}
		if (strBuf.length() > 0)
		{
			strBuf.setLength(strBuf.length() - 1);
		}
		result.append(strBuf.toString()).append("'>\n");
		
		

		return result.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ErgogramAideTool et=new ErgogramAideTool();
		et.setFormXml("ergogramChartSet.xml", et.getClass());
		System.out.println(et.createFormPara());

	}

}
