package com.echo.applet.ergogram;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Dimension;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.Hashtable;

import oracle.jdbc.OracleResultSet;

import org.apache.poi.util.SystemOutLogger;
import org.dom4j.Document;
import org.dom4j.Element;

import com.echo.applet.IChart;
import com.echo.util.DBUtils;
import com.echo.util.StringUtils;
import com.echo.util.DateTimeUtils;

import com.echo.applet.ChartFactory;
import java.sql.*;

public class ErgogramFactory extends ChartFactory
{
	private static Hashtable<String,Document> docHs=new Hashtable<String,Document>();
	
	/**
	 * 从docHs对象中返回由xmlFileName关键字指定的Document对象。
	 * @param xmlFileName
	 * @return
	 */
	public static Document getDocument(String xmlFileName)
	{
		return getDocument(docHs,xmlFileName,ErgogramFactory.class);
	}
	
	/**
	 * 
	 * @param xmlFileName
	 * @param wellNo
	 * @param strTime
	 * @return
	 * @throws SQLException 
	 */
	public static IChart create(String xmlFileName, String strWhere) throws SQLException
	{
		// 不输入格式文件名，则返回空对象
		if (xmlFileName == null || xmlFileName.trim().length() == 0)
			return null;

		//Document doc = openFormXml(xmlFileName, Ergogram.class);
		Document doc = getDocument(xmlFileName);
		// 不能打开指定的xml文件，返回空
		if (doc == null)
			return null;

		Element elmRoot = doc.getRootElement();
		Iterator<Element> iter = null;
		if (elmRoot == null)
			return null;// 获取

		Element elm;
		Vector<String> vctField = new Vector<String>();
		String strTmp = null;

		Ergogram chart = new Ergogram();

		// 初始化图形格式数据
		ErgogramForm form = ((Ergogram) chart).form;
		form.objectField = StringUtils.isNull(getElementText(elmRoot, "objectField"), "");
		form.timeField = StringUtils.isNull(getElementText(elmRoot, "timeField"), "");
		form.title = StringUtils.isNull(getElementText(elmRoot, "title"), "");
		form.setTitleColor(getElementText(elmRoot, "titleColor"));
		form.setTitleFont(getElementText(elmRoot, "titleFont"), 
				StringUtils.parseInt(getElementText(elmRoot, "titleFontStyle"),0), 
				getElementText(elmRoot, "titleFontSize"));
		form.setChartClosed(getElementText(elmRoot, "chartClosed"));
		form.chartLineWidth = StringUtils.parseInt(getElementText(elmRoot, "chartLineWidth"), 1);
		form.setBgColor(getElementText(elmRoot, "bgColor"));
		form.setAxesLineColor(getElementText(elmRoot, "axeslineColor"));
		form.axesLineWidth = StringUtils.parseInt(getElementText(elmRoot, "axesLineWidth"), 1);
		form.setGridLineColor(getElementText(elmRoot, "gridLineColor"));
		form.gridLineWidth = StringUtils.parseInt(getElementText(elmRoot, "gridLineWidth"), 0);
		form.setAideTextColor(getElementText(elmRoot, "aideTextFontColor"));
		form.setAideTextFont(getElementText(elmRoot, "aideTextFont"), getElementText(elmRoot, "aideTextFontSize"));
		form.setAxesFontColor(getElementText(elmRoot, "axesFontColor"));
		form.setAxesFont(getElementText(elmRoot, "axesFont"), getElementText(elmRoot, "axesFontSize"));
		form.fixChartWHScale = StringUtils.parseFloat(getElementText(elmRoot, "fixChartWHScale"), 0.625f);
		form.minChartWidth = StringUtils.parseFloat(getElementText(elmRoot, "minChartWidth"), 100f);
		form.minChartHeight = StringUtils.parseFloat(getElementText(elmRoot, "minChartHeight"), 62.5f);

		// 处理横坐标axesX节点参数
		elm = elmRoot.element("axesX");
		if (elm != null)
		{
			form.axesXUnit = getElementText(elm, "unit");
			form.axesXTagNum = StringUtils.parseInt(getElementText(elm, "tagNum"), 6);
			form.setAxesXGridLine(getElementText(elm, "showGrid"));
		}

		// 处理纵坐标axesY节点参数
		elm = elmRoot.element("axesY");
		if (elm != null)
		{
			form.axesYUnit = getElementText(elm, "unit");
			form.axesYTagNum = StringUtils.parseInt(getElementText(elm, "tagNum"), 6);
			form.setAxesYGridLine(getElementText(elm, "showGrid"));
			form.axesYBlankScale = StringUtils.parseInt(getElementText(elm, "blankScale"), 10);
			form.axesYMaxLimit = StringUtils.parseFloat(getElementText(elm, "maxLimit"), Float.MAX_VALUE);
			form.axesYMinLimit = StringUtils.parseFloat(getElementText(elm, "minLimit"), Float.MIN_VALUE);

			// 处理纵坐标axesY节点中参考线referenceLines子节点参数
			elm = elm.element("referenceLines");
			if (elm != null)
			{
				iter = elm.elementIterator();
				while (iter.hasNext())
				{
					elm = iter.next();
					strTmp = getElementText(elm, "field");
					form.addYRefLine(strTmp, getElementText(elm, "color"), getElementText(elm, "width"), getElementText(elm, "desc"));
					if (strTmp != null && strTmp.trim().length() >= 0)
					{
						vctField.add(strTmp);
					}
				}
			}
		}

		// 处理线条颜色参数
		elm = elmRoot.element("chartLineColors");
		// form.chartLineColors.clear();//在这里可以确信以前没有添加颜色对象
		if (elm != null)
		{
			iter = elm.elementIterator();
			while (iter.hasNext())
			{
				elm = iter.next();
				form.addChartLineColor(elm.getTextTrim());
			}
		}
		if (form.chartLineColors.size() == 0)
		{
			form.chartLineColors.add(Color.blue);
		}

		// 处理附加字段参数
		elm = elmRoot.element("aideTexts");
		if (elm != null)
		{
			iter = elm.elementIterator();
			while (iter.hasNext())
			{
				elm = iter.next();
				strTmp = getElementText(elm, "field");
				form.addAideText(getElementText(elm, "prefix"), strTmp, getElementText(elm, "suffix"));
				if (strTmp != null && strTmp.length() > 0 && !strTmp.equals("--"))
				{
					vctField.add(strTmp);
				}
			}
		}
		
		//标志点参数
		elm = elmRoot.element("markingPointNoFields");
		iter = elm.elementIterator();
		while (iter.hasNext())
		{
			elm = (Element) (iter.next());
			strTmp = elm.getTextTrim();
			if (strTmp != null && strTmp.length() > 0 )
			{
				form.markingPointNoFields.add(strTmp);
				vctField.add(strTmp);
			}
		}

		// 初始化图形数据
//		StringBuffer strSql = new StringBuffer();
		String c="";
		String c1="";
//		StatementManager stmt=null;
		StringBuffer strBuf=new StringBuffer();
		StringBuffer strBuf1=new StringBuffer();
		String[] strArr = StringUtils.split(strWhere, ";"),strArr2;
//		Connection conn = null;
		if(strArr ==null)return null;
		for(int i = 0;i<strArr.length;i++)
		{
			strArr2=StringUtils.split(strArr[i],",");
			if(strArr2!=null && strArr2.length >1)
			{
		//		a=a+strArr2[0];
		//		b=b+strArr2[1];
				strBuf.append("'").append(strArr2[0]).append("',");
				strBuf1.append("'").append(strArr2[1]).append("',");
				
				c="select * from pc_cd_indicator_diagram_v where ("+elmRoot.elementTextTrim("objectField")+") = ('"+strArr2[0]+"') and ("+elmRoot.elementTextTrim("timeField")+") = (TO_DATE('"+strArr2[1]+"','YYYY-MM-DD HH24:MI:SS')) UNION ";
				c1 =c1 + c;
			}
		}
		int lnum = c1.indexOf("UNION");
		if(lnum>0)
			c1 = c1.substring(0, lnum);
		
		if(strBuf.length()>0)
		{
			strBuf.setLength(strBuf.length()-1);
			strBuf1.setLength(strBuf1.length()-1);
		}
		else
		{
			return null;//如果没有条件则返回空
		}
		Connection conn = null;                 //声明Connection对象
		Statement stmt=null;
		ResultSet rs = null;                    //声明ResultSet对象
		try
		{
//			conn = DBUtils.getConnection();
	//		c="select * from vw2070 where ("+elmRoot.elementTextTrim("objectField")+") in ("+strBuf+") and ("+elmRoot.elementTextTrim("timeField")+") in ("+strBuf1+")";
	//		strSql.append("select * from ").append(elmRoot.element("table").getTextTrim()).append(" where (");
	//		strSql.append(elmRoot.elementTextTrim("objectField")).append(",");
	//		strSql.append(elmRoot.elementTextTrim("timeField")).append(") in(");
	//		strSql.append(strBuf).append(")");
	//		strSql.append("select * from vw2070 where (p01) in('G53-25') and (p02) in('2010-07-18 16:20:00.343')");
			c = c1 ;
System.out.println("c=============="+c);
			conn = DBUtils.getConnection(); 
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    rs = stmt.executeQuery(c.toString()); 
			

//			stmt = new StatementManager();
//			ResultSet rs = stmt.executeQuery(strSql.toString());
			if (rs == null)
				return null;// 没有数据结果集，将返回空

			if (rs.first())
			{
				while (!rs.isAfterLast())
				{
					ErgogramData chartData = new ErgogramData();
					chartData.wellNo = rs.getString(getElementText(elmRoot,"objectField"));
					chartData.time = DateTimeUtils.dateFormat(rs.getDate(getElementText(elmRoot,"timeField")),"yyyy-MM-dd ");
		
					
					chartData.time = chartData.time.concat(DateTimeUtils.dateFormat(rs.getTime(getElementText(elmRoot,"timeField")),"HH:mm:ss"));
				//	Clob clob=(Clob)rs.getClob(getElementText(elmRoot,"dataField"));
					chartData.setData(rs.getString(getElementText(elmRoot,"dataField")));
//System.out.println("vctField.size()=========="+vctField.size());
					for (int i = 0; i < vctField.size(); i++)
					{
						strTmp = vctField.get(i);
						chartData.addField(strTmp, rs.getString(strTmp));
					}
					chart.addSeries(chartData);
					rs.next();
				}
			}
			else
			{
				return null;
			}

		} catch (Exception e)// 如果没有配置table、objectField、timeField节点，或者数据库中没有这些配置对象，都可能引发一个错误。
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (rs!=null){
				rs.close();
			}
			if (stmt!=null){
				stmt.close();
			}
			if (conn!=null){
				conn.close();
			}
		}
//System.out.println("***************************");
		return chart;
	}
	public static BufferedImage createImage(String xmlFileName, String wellNo, String strTime) throws SQLException
	{
//System.out.println("||||xmlFileName:"+xmlFileName+"|||wellNo:"+wellNo+"|||strTime:"+strTime);
		if(xmlFileName==null || wellNo == null || strTime==null) return null;
		return createImage(xmlFileName,wellNo.concat(",").concat(strTime));
	}

	public static BufferedImage createImage(String xmlFileName, String strWhere) throws SQLException
	{
//System.out.println("||xmlFileName:"+xmlFileName+"||strWhere:"+strWhere);
		if (xmlFileName==null || xmlFileName.trim().length()==0 || strWhere==null || strWhere.trim().length()==0)
			return null;
		
		Dimension size = new Dimension(200, 240);
		Point zeroPoint = new Point(0, 0);
		//Document doc = openFormXml(xmlFileName, Ergogram.class);
		Document doc = getDocument(xmlFileName);
		try
		{
			size.width  = StringUtils.parseInt(getElementText(doc.getRootElement(),"width"), 200);
			size.height = StringUtils.parseInt(getElementText(doc.getRootElement(),"height"), 240);
		} catch (Exception e)
		{

		}

		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
//System.out.println("g2d============"+g2d);
		IChart chart = create(xmlFileName, strWhere);
		if (chart == null) return null;
		chart.layoutPage(g2d, zeroPoint, size);
		image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		g2d = image.createGraphics();
//System.out.println("g2d=====++++++++++++++++++======="+g2d);
		chart.draw(g2d, zeroPoint, size);
		g2d.dispose();
		return image;

	}
}
