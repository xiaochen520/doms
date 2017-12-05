package com.echo.applet.pieChart;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import com.echo.util.StringUtils;

public class PieChartForm
{
	public static final int LEFT=3;
	public static final int RIGHT=4;
	public static final int TOP=1;
	public static final int BOTTOM=2;
	
	Color bgColor;	//北京颜色
	int minDiameter;//最小圆直径
	Color titleColor;//正标题字颜色
	Font titleFont;//正标题字体
	Color aideTitleColor;//付标题字颜色
	Font aideTitleFont;//付标题字体
	Color legendColor;//图例文本颜色
	Font legendFont;//图例文本字体
	int lineWidth;//饼图线条宽度
	Color lineColor;//饼图线条颜色
	int legendPos=BOTTOM;//图例位于饼图的位置
	float pieChartWHScale=1f;//饼图长短半径比例
	Vector<Color> pieFillColors = new Vector<Color>();//饼图填充颜色
	
	
	public PieChartForm()
	{
		bgColor = Color.WHITE;
		minDiameter = 10;
		lineWidth = 1;
		titleColor = aideTitleColor = legendColor = Color.BLACK;
		setTitleFont("宋体","16");
		setAideTitleFont("宋体","16");
		setLegendFont("宋体","12");
		setAideTitleFont("宋体","12");
		
	}
	
	
	
	public int setPieChartColors(String pieFillColorStrs)
	{
		if(pieFillColorStrs==null || pieFillColorStrs.trim().length() ==0) return -1;
		
		String[] strArr=StringUtils.split(pieFillColorStrs, ",");
		if (strArr == null )
		{
			return -1;
		}
		if (pieFillColors!=null)
		{
			pieFillColors.clear();
		}
		else
		{
			pieFillColors = new Vector<Color>();
		}
		for (int i = 0; i < strArr.length;i++ )
		{
			addPieFillColor(strArr[i]);
		}
		return pieFillColors.size();
		
	}

	public int addPieFillColor(String pieFillColorStr)
	{
		if (pieFillColors==null)
		{
			pieFillColors = new Vector<Color>();
		}
		Color cl = StringUtils.parseColor(pieFillColorStr);
		if (cl==null)
		{
			cl = Color.BLACK;
		}
		pieFillColors.add(cl);
		return pieFillColors.size();
		
	}
	
	public void setBgColor(String bgColor)
	{
		Color cl = StringUtils.parseColor(bgColor);
		if (cl==null)
		{
			this.bgColor = Color.WHITE;
		}
		else
		{
			this.bgColor = cl;
		}
	}

	public void setLegendColor(String axesFontColor)
	{
		Color cl = StringUtils.parseColor(axesFontColor);
		if (cl==null)
		{
			this.legendColor = Color.BLACK;
		}
		else
		{
			this.legendColor = cl;
		}
	}
	public void setTitleColor(String titleColor)
	{
		Color cl = StringUtils.parseColor(titleColor);
		if (cl==null)
		{
			this.titleColor = Color.WHITE;
		}
		else
		{
			this.titleColor = cl;
		}
	}

	public void setAideTitleColor(String aideTextColor)
	{
		Color cl = StringUtils.parseColor(aideTextColor);
		if (cl==null)
		{
			this.aideTitleColor = Color.BLACK;
		}
		else
		{
			this.aideTitleColor = cl;
		}
	}

	public void setLineColor(String lineColor)
	{
		Color cl = StringUtils.parseColor(lineColor);
		if (cl==null)
		{
			this.lineColor = Color.BLACK;
		}
		else
		{
			this.lineColor = cl;
		}
	}

	public void setAideTitleFont(String fontName, String fontSize)
	{
		this.aideTitleFont = StringUtils.parseFont(fontName,fontSize);
	}

	public void setTitleFont(String fontName, String fontSize)
	{
		this.titleFont = StringUtils.parseFont(fontName,fontSize);
	}

	public void setLegendFont(String fontName, String fontSize)
	{
		this.legendFont = StringUtils.parseFont(fontName,fontSize);
	}

	public void SetLegendPos(String pos)
	{
		if(pos==null)return;
		pos = pos.trim();
		if(pos.equalsIgnoreCase("top")||pos.equalsIgnoreCase("1"))
		{
			legendPos = TOP;
		}
		else if(pos.equalsIgnoreCase("bottom")||pos.equalsIgnoreCase("2"))
		{
			legendPos = BOTTOM;
		}
		else if(pos.equalsIgnoreCase("left")||pos.equalsIgnoreCase("3"))
		{
			legendPos = LEFT;
		}
		else if(pos.equalsIgnoreCase("right")||pos.equalsIgnoreCase("4"))
		{
			legendPos = RIGHT;
		}
	}
}
