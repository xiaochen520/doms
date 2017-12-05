/**
 * 
 */
package com.echo.applet.ergogram;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.Element;


import com.echo.util.StatementManager;
import com.echo.util.StringUtils;

/**
 * @author wcq
 *
 */
public class DetailErgogramAideTool extends ErgogramAideTool
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
		int fixed=0;//需要单独处理的字段数量

		strTmp = root.element("objectField").getTextTrim();
		vctField.add(strTmp);
		strFields.append(strTmp);
		strTmp = root.element("timeField").getTextTrim();
		vctField.add(strTmp);
		strFields.append(",").append(strTmp);
		strTmp = root.element("dataField").getTextTrim();
		vctField.add(strTmp);
		strFields.append(",").append(strTmp);
		
		/*增加详细工图中附加的图形数据*/
		iter = root.element("dataFieldEx").elementIterator();
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
		
		//记录需要单独处理的字段值数量
		fixed = vctField.size();
		
		/*增加工图中附加的点标识数据*/
		iter = root.element("markingPointNoFields").elementIterator();
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
		String[] strArr = StringUtils.split(strWhere, ";");
		String[] strArr2 = null;
		for (int i = 0; i < strArr.length; i++)
		{
			strArr2 = StringUtils.split(strArr[i], ",");
			strValue.append("('").append(strArr2[0]).append("',to_date('");
			strValue.append(strArr2[1]).append("','yyyy-mm-dd hh24:mi:ss')),");
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
System.out.println(strSql.toString());
		rs = stmtMgr.executeQuery(strSql.toString());
		try
		{
			if (rs!=null && rs.first())
			{
				result.append("<param name=datas value='");
				do
				{
					for(int i = 0;i<fixed;i++ )
					{
						strBuf.append(StringUtils.isNull(rs.getString(vctField.get(i)),"--")).append(",");
					}
					for (int i = fixed; i < vctField.size(); i++)
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


}
