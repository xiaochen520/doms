/**
 * 
 */
package com.echo.applet.pieChart;

import com.echo.applet.ChartApplet;
import com.echo.applet.ergogram.Ergogram;
import com.echo.applet.ergogram.ErgogramData;
import com.echo.applet.ergogram.ErgogramForm;
import com.echo.util.StringUtils;

/**
 * @author wcq
 *
 */
public class PieChartApplet extends ChartApplet
{

	public void init()
	{
		super.init();
		createChart();
		//setPara();
		main();
		panel.setChart(chart);
		//setVisible(true);
		//repaint();
		
	}
	
	/**
	 * 创建chart
	 */
	protected void createChart()
	{
		PieChart chart = new PieChart();
		this.chart = chart;
		
	}

	private void setPara()
	{
		//初始化图形格式数据
		PieChartForm form = ((PieChart)chart).form;
		form.SetLegendPos(getParameter("legendPos"));
		form.lineWidth   = StringUtils.parseInt(getParameter("lineWidth"), 0);
		form.setLineColor(getParameter("lineColor"));
		form.minDiameter = StringUtils.parseInt(getParameter("minDiameter"), 30);
		form.setBgColor(getParameter("bgColor"));
		form.setAideTitleColor(getParameter("aideTextFontColor"));
		form.setAideTitleFont(getParameter("aideTitleFont"), getParameter("aideTitleFontSize"));
		form.setLegendColor(getParameter("legendFontColor"));
		form.setLegendFont(getParameter("legendFont"), getParameter("legendFontSize"));
		form.setPieChartColors(getParameter("pieFillColorStrs"));
		form.setTitleColor(getParameter("titleFontColor"));
		form.setTitleFont(getParameter("titleFont"), getParameter("titleFontSize"));
		
		//初始化图形数据
		createData();
	}
	
	protected int createData()
	{
		int result=0;
		//初始化图形数据
		String[] strArr1;
		PieChartData chartData ;
		strArr1 = StringUtils.split(getParameter("datas"), "=");
		if (strArr1!=null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
				chartData = PieChartData.create(strArr1[i]);
				if (chartData!=null)
				{
					result=((PieChart)chart).addSeries(chartData);
				}
			}
		}
		return result;
	}
	/**
	 * @param args
	 */
	public  void main()
	{
		// TODO Auto-generated method stub
		//初始化图形格式数据
		PieChartForm form = ((PieChart)chart).form;
		form.SetLegendPos("bottom");
		form.lineWidth   = StringUtils.parseInt("1", 0);
		form.setLineColor("#000000");
		form.minDiameter = StringUtils.parseInt("30", 30);
		form.setBgColor("#FFFFFF");
		form.setAideTitleColor("#000000");
		form.setAideTitleFont("宋体", "12");
		form.setLegendColor("#000000");
		form.setLegendFont("宋体", "12");
		form.setPieChartColors("#FF0000,#00FF00,#0000FF,#FF00FF,#00FFFF,#FFFF00");
		form.setTitleColor("#000000");
		form.setTitleFont("宋体", "18");
		PieChartData chartData = PieChartData.create("泵效分析结果图,  , ,%,|理论产量,24.74;实际产量1,7.62;实际产量2,7.7;实际产量3,7.7");
		if (chartData!=null)
		{
			((PieChart)chart).addSeries(chartData);
		}
	
	}

}
