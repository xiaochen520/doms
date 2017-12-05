package com.echo.applet.ergogram;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import com.echo.util.StringUtils;

class  RefLine
{
	public String field;
	public Color color;
	public int width;
	public String desc;
	
	public RefLine(String field, String color,String width, String desc)
	{
		this.field = field;
		this.color =StringUtils.parseColor(color);
		if (this.color==null)
		{
			this.color = Color.BLACK;
		}
		this.width = StringUtils.parseInt(width, 1);
		this.desc = desc;
	}
}

class AideText
{
	public String prefix;
	public String field;
	public String suffix;
	
	public AideText(String prefix,String field, String suffix)
	{
		this.prefix = prefix;
		this.field = field;
		this.suffix = suffix;
	}
}

public class ErgogramForm
{
	String objectField;
	String timeField;
	boolean chartClosed;
	Color bgColor;
	Color axesLineColor;
	int axesLineWidth;
	Color gridLineColor;
	int gridLineWidth;
	Color axesFontColor;
	Font axesFont;
	float fixChartWHScale;
	float minChartWidth;
	float minChartHeight;
	String axesXUnit;
	int axesXTagNum;
	boolean axesXGridLine;
	String axesYUnit;
	int axesYTagNum;
	boolean axesYGridLine;
	int axesYBlankScale;
	float axesYMaxLimit = Float.MAX_VALUE,axesYMinLimit= Float.MIN_VALUE;
	Vector<RefLine> axesYRefLines;
	Vector<AideText> aideTexts;
	String title;
	Color titleColor;
	Font titleFont;
	Vector<Color> chartLineColors;
	int chartLineWidth;
	Color aideTextColor;
	Font aideTextFont;
	Vector<String> markingPointNoFields;
	
	public ErgogramForm()
	{
		chartClosed = true;
		bgColor = Color.WHITE;
		axesLineColor = Color.BLACK;
		axesLineWidth =1;
		gridLineColor = Color.LIGHT_GRAY;
		gridLineWidth = 1;
		axesFontColor = Color.BLACK;
		setAxesFont("宋体", "10");
		fixChartWHScale=0.625f;
		minChartWidth=100f;
		minChartHeight=62.5f;
		axesXTagNum = 6;
		axesXGridLine = false;
		axesYTagNum = 6;
		axesYGridLine = false;
		axesYBlankScale = 10;
		axesYRefLines = new Vector<RefLine>();
		aideTexts = new Vector<AideText>();
		titleColor = Color.BLACK;
		setTitleFont("宋体","16");
		chartLineColors = new Vector<Color>() ;
		markingPointNoFields = new Vector<String>();
		chartLineWidth = 1;
		aideTextColor = Color.BLACK;
		setAideTextFont("宋体","10");
		
	}
	
	public int addYRefLine(String field, String color,String width, String desc)
	{
		RefLine refLine = new RefLine(field,color,width,desc);
		if (axesYRefLines==null)
		{
			axesYRefLines = new Vector<RefLine>();
		}
		axesYRefLines.add(refLine);
		return axesYRefLines.size();
	}
	
	public int addAideText(String prefix, String field, String suffix)
	{
		AideText aideText= new AideText(prefix,field,suffix);
		if (aideTexts == null)
		{
			aideTexts = new Vector<AideText>();
		}
		aideTexts.add(aideText);
		return aideTexts.size();
	}
	
	public int setChartLineColors(String chartLinecColorStr)
	{
		if (chartLinecColorStr==null || chartLinecColorStr.trim().length()==0) return -1;
		
		String[] strArr=StringUtils.split(chartLinecColorStr, ",");
		if (strArr == null )
		{
			return -1;
		}
		if (chartLineColors!=null)
		{
			chartLineColors.clear();
		}
		else
		{
			chartLineColors = new Vector<Color>();
		}
		for (int i = 0; i < strArr.length;i++ )
		{
			addChartLineColor(strArr[i]);
		}
		return chartLineColors.size();
		
	}

	public int addChartLineColor(String charLineColor)
	{
		if (chartLineColors==null)
		{
			chartLineColors = new Vector<Color>();
		}
		Color cl = StringUtils.parseColor(charLineColor);
		if (cl==null)
		{
			cl = Color.BLUE;
		}
		chartLineColors.add(cl);
		return chartLineColors.size();
		
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

	public void setAxesLineColor(String axesLineColor)
	{
		Color cl = StringUtils.parseColor(axesLineColor);
		if (cl==null)
		{
			this.axesLineColor = Color.BLACK;
		}
		else
		{
			this.axesLineColor = cl;
		}
	}

	public void setGridLineColor(String gridLineColor)
	{
		Color cl = StringUtils.parseColor(gridLineColor);
		if (cl==null)
		{
			this.gridLineColor = Color.LIGHT_GRAY;
		}
		else
		{
			this.gridLineColor = cl;
		}
	}

	public void setAxesFontColor(String axesFontColor)
	{
		Color cl = StringUtils.parseColor(axesFontColor);
		if (cl==null)
		{
			this.axesFontColor = Color.BLACK;
		}
		else
		{
			this.axesFontColor = cl;
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

	public void setAideTextColor(String aideTextColor)
	{
		Color cl = StringUtils.parseColor(aideTextColor);
		if (cl==null)
		{
			this.aideTextColor = Color.BLACK;
		}
		else
		{
			this.aideTextColor = cl;
		}
	}


	public void setAxesFont(String fontName, String fontSize)
	{
		this.axesFont = StringUtils.parseFont(fontName,fontSize);
	}

	public void setTitleFont(String fontName, String fontSize)
	{
		this.titleFont = StringUtils.parseFont(fontName,fontSize);
	}

	public void setTitleFont(String fontName,int fontStyle, String fontSize)
	{
		this.titleFont = StringUtils.parseFont(fontName,fontStyle,fontSize);
	}

	public void setAideTextFont(String fontName, String fontSize)
	{
		this.aideTextFont = StringUtils.parseFont(fontName,fontSize);
	}

	public void setChartClosed(String chartClosed)
	{
		if (chartClosed != null && chartClosed.equalsIgnoreCase("true"))
		{
			this.chartClosed = true;
		}
		else
		{
			this.chartClosed = false;
		}
	}
	
	public void setAxesXGridLine(String showGrid)
	{
		if (showGrid != null && showGrid.equalsIgnoreCase("true"))
		{
			this.axesXGridLine = true;
		}
		else
		{
			this.axesXGridLine = false;
		}
		
	}
	
	public void setAxesYGridLine(String showGrid)
	{
		if (showGrid != null && showGrid.equalsIgnoreCase("true"))
		{
			this.axesYGridLine = true;
		}
		else
		{
			this.axesYGridLine = false;
		}
		
	}
	public int setMarkingPointNos(String markingPointFields)
	{
		String[] strArr=StringUtils.split(markingPointFields, ",");
		if (strArr == null )
		{
			return -1;
		}
		if (markingPointNoFields!=null)
		{
			markingPointNoFields.clear();
		}
		else
		{
			markingPointNoFields = new Vector<String>();
		}
		for (int i = 0; i < strArr.length;i++ )
		{
			markingPointNoFields.add(strArr[i]);
		}
		return markingPointNoFields.size();
		
	}
}
