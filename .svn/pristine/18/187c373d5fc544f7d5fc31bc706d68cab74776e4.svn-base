package com.echo.applet.pieChart;

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

import org.dom4j.Document;
import org.dom4j.Element;



import com.echo.applet.IChart;
import com.echo.util.DBUtils;
import com.echo.util.StringUtils;

import com.echo.applet.ChartFactory;


public class PieChartFactory extends ChartFactory
{
	private static Hashtable<String,Document> docHs=new Hashtable<String,Document>();
	
	/**
	 * 从docHs对象中返回由xmlFileName关键字指定的Document对象。
	 * @param xmlFileName
	 * @return
	 */
	public static Document getDocument(String xmlFileName)
	{
		return getDocument(docHs,xmlFileName,PieChartFactory.class);
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

		//Document doc = openFormXml(xmlFileName, PieChart.class);
//System.out.println("xmlFileName===="+xmlFileName);
		Document doc = getDocument(xmlFileName);
		// 不能打开指定的xml文件，返回空
		if (doc == null)
			return null;

		Element elmRoot = doc.getRootElement();
		if (elmRoot == null)
			return null;// 获取

		Element elmForm = elmRoot.element("form");
		Element elmData = elmRoot.element("datas");
		Element elm;
		if (elmForm == null || elmData == null)
			return null;// 如果没有form、data节点，表示格式文件不全，返回空

		PieChart chart = new PieChart();

		// 初始化图形格式数据
		PieChartForm form = ((PieChart) chart).form;
		form.setBgColor(getElementText(elmForm,"bgColor"));
		form.SetLegendPos(StringUtils.isNull(getElementText(elmForm,"legendPos"), "bottom"));
		form.minDiameter = StringUtils.parseInt(getElementText(elmForm,"minDiameter"), 30);
		form.pieChartWHScale = StringUtils.parseFloat(getElementText(elmForm,"pieChartWHScale"), 1f);
		elm = elmForm.element("line");
		if(elm !=null)
		{
			form.lineWidth = StringUtils.parseInt(getElementText(elm,"Width"), 0);
			form.setLineColor(getElementText(elm,"Color"));
		}
		elm = elmForm.element("aideText");
		if(elm !=null)
		{
			form.setAideTitleColor(getElementText(elm,"FontColor"));
			form.setAideTitleFont(getElementText(elm,"Font"), getElementText(elm,"FontSize"));
		}
		elm = elmForm.element("legendText");
		if(elm !=null)
		{
			form.setLegendColor(getElementText(elm,"FontColor"));
			form.setLegendFont(getElementText(elm,"Font"), getElementText(elm,"FontSize"));
		}
		elm = elmForm.element("title");
		if(elm !=null)
		{
			form.setTitleColor(getElementText(elm,"FontColor"));
			form.setTitleFont(getElementText(elm,"Font"), getElementText(elm,"FontSize"));
		}

		Iterator<Element> iter;
		elm = elmForm.element("fillColors");
		if (elm!=null)
		{
			iter = elm.elementIterator();
			while(iter.hasNext())
			{
				elm=iter.next();
				form.addPieFillColor(elm.getTextTrim());
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
			strSql.append("select * from ").append(elmData.element("table").getTextTrim()).append(" where ");
			strSql.append(elmData.elementTextTrim("objectField")).append("='").append(wellNo).append("'");
			strSql.append(" and ").append(elmData.elementTextTrim("timeField"));
			strSql.append("= '").append(strTime).append("'");
//System.out.println("newSq9l="+strSql);
	//		stmt = new StatementManager();
//System.out.println(strSql.toString());
//			ResultSet rs = stmt.executeQuery(strSql.toString());
			conn = DBUtils.getConnection(); 
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    rs = stmt.executeQuery(strSql.toString()); 
			if (rs == null && rs.next() )
				return null;// 没有数据结果集，将返回空

			Element elmDataFields = elmData.element("dataFields");
			if (elmDataFields == null)
				return null;// 没有配置数据字段的话，将返回空

			PieChartData chartData = new PieChartData();
			iter = elmDataFields.elementIterator();
			rs.first();
			while (iter.hasNext())
			{
				elm = iter.next();
				chartData.addData(elm.elementTextTrim("desc"), rs.getFloat(elm.elementText("field")));
				// 获取图形数据的纵坐标最大最小值

			}
			chartData.title=StringUtils.isNull(getElementText(elmData,"title"), "");
			chartData.aideTitle=StringUtils.isNull(getElementText(elmData,"aideTitle"), "");
			chartData.desc=StringUtils.isNull(getElementText(elmData,"desc"), "");
			chartData.unit=StringUtils.isNull(getElementText(elmData,"unit"), "");
			chart.addSeries(chartData);

		} catch (Exception e)// 如果没有配置table、objectField、timeField节点，或者数据库中没有这些配置对象，都可能引发一个错误。
		{
			System.out.println(strSql.toString());
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
		Dimension size = new Dimension(200, 240);
		Point zeroPoint = new Point(0, 0);
		//Document doc = openFormXml(xmlFileName, PieChart.class);
		Document doc = getDocument(xmlFileName);
		try
		{
			size.width  = StringUtils.parseInt(getElementText(doc.getRootElement().element("form"),"width"), 200);
			size.height = StringUtils.parseInt(getElementText(doc.getRootElement().element("form"),"height"), 240);
		} catch (Exception e)
		{

		}

		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();

		IChart chart = create(xmlFileName, wellNo, strTime);
		chart.layoutPage(g2d, zeroPoint, size);
		image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		g2d = image.createGraphics();
		chart.draw(g2d, zeroPoint, size);
		g2d.dispose();
		return image;

	}
}
