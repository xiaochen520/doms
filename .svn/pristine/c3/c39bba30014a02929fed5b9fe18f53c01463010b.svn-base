/**
 * 
 */
package com.echo.applet.ergogram;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Dimension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.Element;



import com.echo.applet.IChart;
import com.echo.util.DBUtils;
import com.echo.util.DateTimeUtils;
import com.echo.util.StringUtils;

import com.echo.applet.ChartFactory;

/**
 * @author wcq
 *
 */
public class DetailErgogramFactory extends ErgogramFactory
{
	private static Hashtable<String,Document> docHs=new Hashtable<String,Document>();
	
	/**
	 * 从docHs对象中返回由xmlFileName关键字指定的Document对象。
	 * @param xmlFileName
	 * @return
	 */
	public static Document getDocument(String xmlFileName)
	{
		return getDocument(docHs,xmlFileName,DetailErgogramFactory.class);
	}
	
	/**
	 * 
	 * @param xmlFileName
	 * @param wellNo
	 * @param strTime
	 * @return
	 * @throws SQLException 
	 */
	public static IChart create(String xmlFileName, String wellNo, String strTime) throws SQLException
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

		DetailErgogram chart = new DetailErgogram();
		// 初始化图形格式数据
		ErgogramForm form =  chart.form;
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
		StringBuffer strSql = new StringBuffer();
//		StatementManager stmt=null;
		Connection conn = null;                 //声明Connection对象
		Statement stmt=null;
		ResultSet rs = null;  
		try
		{
			strSql.append("select * from ").append(elmRoot.element("table").getTextTrim()).append(" where ");
			strSql.append(elmRoot.elementTextTrim("objectField")).append("='").append(wellNo).append("'");
			strSql.append(" and ").append(elmRoot.elementTextTrim("timeField"));
			strSql.append("= '").append(strTime).append("'");
System.out.println("最终的sql语句---------------------"+strSql.toString());
//			stmt = new StatementManager();
//			ResultSet rs = stmt.executeQuery(strSql.toString());
			conn = DBUtils.getConnection(); 
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    rs = stmt.executeQuery(strSql.toString()); 

			if (rs == null)
				return null;// 没有数据结果集，将返回空

			DetailErgogramData chartData = new DetailErgogramData();
			if (rs.first())
			{
				chartData.wellNo = rs.getString(getElementText(elmRoot,"objectField"));
				chartData.time = DateTimeUtils.dateFormat(rs.getDate(getElementText(elmRoot,"timeField")),"yyyy-MM-dd ");
				chartData.time = chartData.time.concat(DateTimeUtils.dateFormat(rs.getTime(getElementText(elmRoot,"timeField")),"HH:mm:ss"));
				chartData.setData(rs.getString(getElementText(elmRoot,"dataField")));
				//详细工图中杆、泵工图字段参数设置
				iter = elmRoot.element("dataFieldEx").elementIterator();
				while (iter.hasNext())
				{
					elm = (Element) (iter.next());
					if(elm != null)
					{
						strTmp = elm.getTextTrim();
						if(strTmp !=null && strTmp.length() > 0 )
						{
							//vctField.add(strTmp);
							chartData.addDataEx(rs.getString(strTmp));
						}
					}
				}
				
				for (int i = 0; i < vctField.size(); i++)
				{
					strTmp = vctField.get(i);
					chartData.addField(strTmp, rs.getString(strTmp));
				}
				chart.addSeries(chartData);
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
			if (rs!=null)
				rs.close();
			if (stmt!=null)
				stmt.close();
			if (conn!=null)
				conn.close();		
		}

		return chart;
	}

	public static BufferedImage createImage(String xmlFileName, String wellNo, String strTime) throws SQLException
	{
		Dimension size = new Dimension(320, 240);
		Point zeroPoint = new Point(0, 0);
		//Document doc = openFormXml(xmlFileName, DetailErgogram.class);
		Document doc = getDocument(xmlFileName);
		try
		{
			size.width  = StringUtils.parseInt(getElementText(doc.getRootElement(),"width"), 320);
			size.height = StringUtils.parseInt(getElementText(doc.getRootElement(),"height"), 240);
		} catch (Exception e)
		{

		}

		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();

		IChart chart = create(xmlFileName, wellNo, strTime);
		if (chart == null) return null;
		chart.layoutPage(g2d, zeroPoint, size);
		image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		g2d = image.createGraphics();
		chart.draw(g2d, zeroPoint, size);
		g2d.dispose();
		return image;

	}
}
