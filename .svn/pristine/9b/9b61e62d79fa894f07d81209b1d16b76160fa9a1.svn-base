package com.echo.applet.ergogram;

import com.echo.applet.ChartData;
import com.echo.util.StringUtils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Point;
import java.awt.Font;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.text.DecimalFormat;

/**
 * 功图类数据
 * 
 * @author wcq
 * 
 */
public class Ergogram extends ChartData
{
	ErgogramForm form;
	Vector<ErgogramData> dataSeries;
	protected float maxY = Float.MIN_VALUE;
	protected float minY = Float.MAX_VALUE;
	protected float maxX = Float.MIN_VALUE;
	protected float minX = Float.MAX_VALUE;

	float scaleX, scaleY;
	Point origin = new Point();
	Dimension innerSize = new Dimension();
	private Dimension minInnerSize = new Dimension(100, 80);
	private int legendWidth, axesYWidth;
	int titleHeight, axesXHeight, aideTextHeight;
	int space = 3;//行间距，单词之间间距
	float[] axesX, axesY;

	public Ergogram()
	{
		form = new ErgogramForm();
		dataSeries = new Vector<ErgogramData>();
	}

	/**
	 * 根据格式参数、实际数据在Graphics对象g上进行绘制布局，仅有draw调用
	 * 
	 * @param g
	 *            被布局对象
	 * @param zeroPoint
	 *            被布局对象的允许布局0点
	 * @param size
	 *            允许的被布局对象大小
	 */
	public int layoutPage(Graphics g, Point zeroPoint, Dimension size)
	{
		int result = 0;
		DecimalFormat fmt = new DecimalFormat("########0.###");
		int len = 0;
		calculateAxesValue();//计算坐标标签
		
		for (int i = 0; i < axesY.length; i++)
		{
			len = Math.max(len, fmt.format(axesY[i]).length());
		}
		axesYWidth = len * form.axesFont.getSize()/2 + space * 3;
		legendWidth = space * 4;
		titleHeight = form.titleFont.getSize() + form.axesFont.getSize()
				+ space * 5;
		axesXHeight = form.axesFont.getSize() + space * 2;
		
		calAideTexHeight();//计算辅助文本区域高度

		len = 0;
		for (int i = 0; i < axesX.length; i++)
		{
			len = Math.max(len, fmt.format(axesX[i]).length());
		}
		int iTmp = len * form.axesFont.getSize()/2 + 2 * space;// 最大横向标签的宽度

		// 计算画面允许的最小宽度
		minInnerSize.width = iTmp * axesX.length;// 横向标签需要的宽度
		if (iTmp / 2 < legendWidth)// 考虑右边信息宽度
		{
			minInnerSize.width += legendWidth - iTmp / 2;
		}
		if (iTmp / 2 < axesYWidth)// 考虑坐标信息宽度
		{
			minInnerSize.width += axesYWidth - iTmp / 2;
		}

		if (dataSeries.size() > 1)
		{
			iTmp = StringUtils.getCNStringLen(form.title);
		} else
		{
			iTmp = StringUtils.getCNStringLen(form.title.concat(dataSeries.get(0).wellNo));
		}
		iTmp = iTmp *form.titleFont.getSize()/2 + space * 4; // 标题宽度

		minInnerSize.height = titleHeight + form.axesFont.getSize()
				* axesY.length + axesXHeight + aideTextHeight;
		minInnerSize.width = Math.max(minInnerSize.width, iTmp);
		// 计算最小画面的图形区域大小
		innerSize.width = minInnerSize.width - axesYWidth - legendWidth;
		innerSize.height = minInnerSize.height - titleHeight - axesXHeight
				- aideTextHeight;
		if (form.fixChartWHScale > 0) // 使用纵横固定比例及横向大小
		{
			if (innerSize.width * form.fixChartWHScale > innerSize.height)
			{
				innerSize.width = (int) (innerSize.height / form.fixChartWHScale);
			} else
			{
				innerSize.height = (int) (innerSize.width * form.fixChartWHScale);
			}
			if (innerSize.width < form.minChartWidth)
			{
				innerSize.width = (int) form.minChartWidth;
				innerSize.height = (int) (innerSize.width * form.fixChartWHScale);
			}
		} else
		// 指定最小纵横大小
		{
			if (innerSize.width < form.minChartWidth)
			{
				innerSize.width = (int) form.minChartWidth;
			}
			if (innerSize.height < form.minChartHeight)
			{
				innerSize.height = (int) form.minChartHeight;
			}
		}
		minInnerSize.height=innerSize.height;
		minInnerSize.width =innerSize.width;

		// 计算可使用图形区域大小
		innerSize.width = size.width - axesYWidth - legendWidth;
		innerSize.height = size.height - titleHeight - axesXHeight
				- aideTextHeight;
		if (form.fixChartWHScale > 0) // 使用纵横固定比例及横向大小
		{
			if (innerSize.width * form.fixChartWHScale > innerSize.height)
			{
				innerSize.width = (int) (innerSize.height / form.fixChartWHScale);
			} else
			{
				innerSize.height = (int) (innerSize.width * form.fixChartWHScale);
			}
		}
		
		// 必须不小于布局时获取的最小图形尺寸
		if (innerSize.height < minInnerSize.height)
		{
			innerSize.height = minInnerSize.height;
		}
		if (innerSize.width < minInnerSize.width)
		{
			innerSize.width = minInnerSize.width;
		}

		// 反算修改传入图形尺寸
		iTmp = innerSize.height + titleHeight + axesXHeight + aideTextHeight;
		if (Math.abs(size.height - iTmp) > 2)
		{
			//zeroPoint.y = zeroPoint.y - size.height - iTmp;
			size.height = iTmp;
			result = 1;
		}
		iTmp = innerSize.width + axesYWidth + legendWidth;
		if (Math.abs(size.width - iTmp) > 2)
		{
			size.width = iTmp;
			result = 1;
		}

		// 计算图形区域原点
		origin.x = zeroPoint.x + axesYWidth;
		origin.y = zeroPoint.y + size.height - axesXHeight - aideTextHeight;

		if((axesX[axesX.length - 1] - axesX[0]) == 0f)
		{
			scaleX=0f;
		}
		else
		{
		scaleX = innerSize.width / (axesX[axesX.length - 1] - axesX[0]);
		}
		if((axesY[axesY.length - 1] - axesY[0]) == 0f)
		{
			scaleY=0f;
		}
		else
		{
		scaleY = innerSize.height / (axesY[axesY.length - 1] - axesY[0]);
		}

		return result;// 如果改动了尺寸，则返回1
	}

	/**
	 * 绘制图像时，如果根据格式设定对图像的大小进行了调整，返回1；如果没有数据、格式数据，则返回-1;否则返回0
	 */
	public int draw(Graphics g, Point zeroPoint, Dimension size)
	{
		if (dataSeries == null || dataSeries.size() == 0)
			return -1;
		int result = 0;

		//result = layoutPage(g, zeroPoint, size);

		Graphics2D g2 = (Graphics2D) g;

		Font oldFont = g2.getFont();
		Stroke oldStroke = g2.getStroke();
		Color oldBgColor = g2.getBackground();
		Color oldColor = g2.getColor();

		BasicStroke stroke;
		int x1, x2, y1, y2;
		DecimalFormat fmt = new DecimalFormat("########0.###");

		// 以指定背景重填充画面区域部分
		g2.setBackground(form.bgColor);
		g2.setColor(form.bgColor);
		g2.fillRect(zeroPoint.x, zeroPoint.y, size.width, size.height);

		// 绘制网格-----
		g2.setColor(form.gridLineColor);
		stroke = new BasicStroke(form.gridLineWidth, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 1f, new float[] { 4f }, 1f);
		g2.setStroke(stroke);
		// 绘制垂直网格
		if (form.axesXGridLine)
		{
			y1 = origin.y;
			y2 = y1 - innerSize.height;
			for (int i = 1; i < axesX.length-1; i++)
			{
				if(scaleX ==0f)
				{
					x1=x2=(int)(origin.x +innerSize.width /(axesX.length -1) * i);
				}
				else
				{
					x1 = x2 = (int) (origin.x + (axesX[i] - axesX[0]) * scaleX);
				}
				g2.drawLine(x1, y1, x2, y2);
			}
		}
		// 绘制水平网格
		if (form.axesYGridLine)
		{
			x1 = origin.x;
			x2 = x1 + innerSize.width;
			for (int i = 1; i < axesY.length-1; i++)
			{
				if(scaleY ==0f)
				{
					y1=y2=(int)(origin.y - innerSize.height /(axesY.length -1) * i);
				}
				else
				{
					y1 = y2 = (int) (origin.y - (axesY[i] - axesY[0]) * scaleY);
				}
				g2.drawLine(x1, y1, x2, y2);
			}
		}

		// 绘制坐标轴
		g2.setColor(form.axesLineColor);
		stroke = new BasicStroke(form.axesLineWidth, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER);
		g2.setStroke(stroke);
		g2.drawRect(origin.x, origin.y - innerSize.height, innerSize.width, innerSize.height);
		// 绘制横坐标轴刻度
		y1 = origin.y;
		y2 = y1 - 5;
		for (int i = 1; i < axesX.length; i++)
		{
			if(scaleX ==0f)
			{
				x1=x2=(int)(origin.x +innerSize.width /(axesX.length -1) * i);
			}
			else
			{
				x1 = x2 = (int) (origin.x + (axesX[i] - axesX[0]) * scaleX);
			}
			g2.drawLine(x1, y1, x2, y2);
		}
		// 绘制纵坐标轴刻度
		x1 = origin.x;
		x2 = x1 + 5;
		for (int i = 1; i < axesY.length; i++)
		{
			if(scaleY ==0f)
			{
				y1=y2=(int)(origin.y - innerSize.height /(axesY.length -1) * i);
			}
			else
			{
				y1 = y2 = (int) (origin.y - (axesY[i] - axesY[0]) * scaleY);
			}
			g2.drawLine(x1, y1, x2, y2);
		}
		
		//绘制参考曲线
		if(dataSeries.size()>0)//多图比较时，只绘制第一幅图的参考线
		{
			for (int i = 0;i<form.axesYRefLines.size();i++ )
			{
				g2.setColor(form.axesYRefLines.get(i).color);
				stroke = new BasicStroke(form.axesYRefLines.get(0).width, BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_MITER);
				g2.setStroke(stroke);
				x1=origin.x;
				x2=origin.x+innerSize.width;
				y1 = y2 = (int) (origin.y - (StringUtils.parseFloat(
								dataSeries.get(0).fields.get(form.axesYRefLines.get(i).field), axesY[0]
								) - axesY[0]) * scaleY);
				g2.drawLine(x1, y1, x2, y2);
				
				
			}
			
		}

		// 绘制坐标标签
		g2.setColor(form.axesFontColor);
		g2.setFont(form.axesFont);
		Font tFont = form.axesFont.deriveFont(Font.BOLD);
		// 绘制水平坐标标签
		y1 = origin.y + axesXHeight - space;
		String str;
		for (int i = 0; i < axesX.length; i++)
		{
			str = fmt.format(axesX[i]);
			x2 = str.length() * form.axesFont.getSize() / 2/2; // 获取标签的一半宽度
			if(scaleX ==0f)
			{
				x1=(int)(origin.x +innerSize.width /(axesX.length -1) * i) - x2;
			}
			else
			{
				x1 = (int) (origin.x + (axesX[i] - axesX[0]) * scaleX - x2);// 标签以标签刻度为对齐中心
			}
			g2.drawString(str, x1, y1);
		}
		// 绘制水平坐标单位
		g2.setFont(tFont);
		y1 = y1 + form.axesFont.getSize() + space;
		x2 = form.axesXUnit.length() * form.axesFont.getSize() / 2 /* /2*/; // 获取标签的一半宽度
		x1 = (int) (origin.x + innerSize.width - x2);
		g2.drawString(form.axesXUnit, x1, y1);
		// 绘制纵向坐标标签
		g2.setFont(form.axesFont);
		x2 = origin.x - space;
		y2 = form.axesFont.getSize();
		for (int i = 0; i < axesY.length; i++)
		{
			str = fmt.format(axesY[i]);
			x1 = str.length() * y2/2; // 获取标签的宽度
			x1 = x2 - x1; // 标签靠纵坐标轴对齐
			if(scaleY ==0f)
			{
				y1=(int)(origin.y - innerSize.height /(axesY.length -1) * i) + y2 / 2;
			}
			else
			{
				y1 = (int) (origin.y - (axesY[i] - axesY[0]) * scaleY + y2 / 2);// 标签以坐标轴刻度水平居中对齐
			}
			g2.drawString(str, x1, y1);
		}
		// 绘制纵向坐标单位
		g2.setFont(tFont);
		y1 = origin.y - innerSize.height - space;
		x2 = form.axesYUnit.length() * y2 / 2; // 获取标签的一半宽度
		x1 = (int) (origin.x /*- x2*/);
		g2.drawString(form.axesYUnit, x1, y1);

		// 绘制标题区域
		drawTitle(g2,zeroPoint,size);

		// 绘制辅助文本
		drawFooter(g2,zeroPoint,size);

		// 绘制曲线
		stroke = new BasicStroke(form.chartLineWidth, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER);
		g2.setStroke(stroke);
		g2.setColor(form.chartLineColors.lastElement());
		for (int i = dataSeries.size() - 1; i >= 0; i--)// 最前面数据系列的图形，显示在最上层
		{
			if (i < form.chartLineColors.size())// 如果格式中设定的颜色总类少于数据系列，后面的系列使用颜色集合的最后一个颜色
			{
				g2.setColor(form.chartLineColors.elementAt(i));
			}
			ErgogramData data = dataSeries.elementAt(i);
			if (data == null || data.dataX == null || data.dataX.length <= 1)
			{
				continue;
			}
			x1 = scaleX == 0f ? origin.x + innerSize.width / 2 : origin.x + (int) ((data.dataX[0] - axesX[0]) * scaleX);
			y1 = scaleY == 0f ? origin.y - innerSize.height/ 2 : origin.y - (int) ((data.dataY[0] - axesY[0]) * scaleY);
			
			for (int j = 1; j < data.dataX.length; j++)
			{
				x2 = scaleX == 0f ? origin.x + innerSize.width / 2 : origin.x + (int) ((data.dataX[j] - axesX[0]) * scaleX);
				y2 = scaleY == 0f ? origin.y - innerSize.height/ 2 : origin.y - (int) ((data.dataY[j] - axesY[0]) * scaleY);
				g2.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
			if (form.chartClosed)
			{
				x2 = scaleX == 0f ? origin.x + innerSize.width / 2 : origin.x + (int) ((data.dataX[0] - axesX[0]) * scaleX);
				y2 = scaleY == 0f ? origin.y - innerSize.height/ 2 : origin.y - (int) ((data.dataY[0] - axesY[0]) * scaleY);
				g2.drawLine(x1, y1, x2, y2);
			}
			g2.setFont(form.axesFont);
			int iRGB = g2.getColor().getRGB();
			iRGB ^=  form.bgColor.getRGB() ;
			iRGB &= 0x770000;
			g2.setColor(new Color(iRGB));
			for(int j= 0;j<form.markingPointNoFields.size();j++)
			{
				int no=StringUtils.parseInt(data.fields.get(form.markingPointNoFields.get(j)),-1);
				if(no>-1 && no < data.dataX.length)
				{
					x1 = scaleX == 0f ? origin.x + innerSize.width / 2 : origin.x + (int) ((data.dataX[no] - axesX[0]) * scaleX);
					y1 = scaleY == 0f ? origin.y - innerSize.height/ 2 : origin.y - (int) ((data.dataY[no] - axesY[0]) * scaleY);
					x1 -= (form.axesFont.getSize()/4);
					y1 += (form.axesFont.getSize()/2);
					g2.drawString("*", x1, y1);
				}
			}
		}

		// 恢复g对象的绘画部分参数
		g2.setStroke(oldStroke);
		g2.setBackground(oldBgColor);
		g2.setColor(oldColor);
		g2.setFont(oldFont);

		return result;
	}

	public int addSeries(ErgogramData data)
	{
		if (data == null)
		{
			return -1;
		}

		if (dataSeries == null)
		{
			dataSeries = new Vector<ErgogramData>();
		}
		dataSeries.add(data);

		// 添加数据系列时，计算新的最大、最小值
		if (data.maxX > maxX)
		{
			maxX = data.maxX;
		}
		if (data.minX < minX)
		{
			minX = data.minX;
		}

		if (data.maxY > maxY)
		{
			maxY = data.maxY;
		}
		if (data.minY < minY)
		{
			minY = data.minY;
		}

		return dataSeries.size();

	}

	public void clearData()
	{
		if (dataSeries == null)
		{
			dataSeries = new Vector<ErgogramData>();
		}
		dataSeries.clear();
		maxY = Float.MIN_VALUE;
		minY = Float.MAX_VALUE;
		maxX = Float.MIN_VALUE;
		minX = Float.MAX_VALUE;
	}

	public static void main(String[] args)
	{
		String str = "aa";
		str.concat("bb");
		System.out.println(str.substring(0,0).concat("aa"));
		 //testcalculateReferrenceValue();
	}

	public static void testcalculateReferrenceValue()
	{
		float[] f = ChartData.calculateReferrenceValue(77f, 32f,150f,0f,0.1f, 6);
		DecimalFormat fmt = new DecimalFormat("########0.####");
		for (int i = 0; i < f.length; i++)
			System.out.println(fmt.format(f[i]));
		for (int i = 0; i < f.length; i++)
			System.out.println(f[i]);
	}

	protected void drawTitle(Graphics2D g2, Point zeroPoint, Dimension size)
	{
		String str;
		int x1,x2,y1;
		g2.setColor(form.titleColor);
		g2.setFont(form.titleFont);
		if (dataSeries.size() == 1)
		{
			str = dataSeries.get(0).wellNo + form.title;// 单幅图形时，在标题中显示单幅图形数据对象
		} else
		{
			str = form.title;
		}
		x2 = zeroPoint.x + size.width / 2;
		x1 = x2 - StringUtils.getCNStringLen(str) * form.titleFont.getSize() / 2/2;
		y1 = zeroPoint.y + space * 2 + form.titleFont.getSize();
		g2.drawString(str, x1, y1);
		if (dataSeries.size() == 1)// 单幅图形时，在标题区显示单幅图形数据采集时间
		{
			g2.setFont(form.axesFont);
			x1 = x2 - dataSeries.get(0).time.length() * form.axesFont.getSize()
					/ 2/2;
			y1 = y1 + form.axesFont.getSize() + space * 2;
			g2.drawString(dataSeries.get(0).time, x1, y1);
			
		}
	}
	
	protected void drawFooter(Graphics2D g2, Point zeroPoint, Dimension size)
	{
		int x1,x2,y1,y2;
		String str;
		BasicStroke stroke;
		g2.setFont(form.aideTextFont);
		g2.setColor(form.aideTextColor);
		if (dataSeries.size() == 1)// 单条数据时，显示辅助信息文本
		{
			AideText aideText;
			Hashtable<String, String> hstbl = dataSeries.get(0).fields;
			y1 = origin.y + axesXHeight + form.aideTextFont.getSize();
			x1 = origin.x;
			for (int i = 0; i < form.aideTexts.size(); i++)
			{
				aideText = form.aideTexts.elementAt(i);
				if (aideText.field.equals("--"))
				{
					y1 += form.aideTextFont.getSize() + space;
					x1 = origin.x;
					continue;
				}
				str = aideText.prefix + hstbl.get(aideText.field)
						+ aideText.suffix;
				g2.drawString(str, x1, y1);
				x1 += StringUtils.getCNStringLen(str) * form.aideTextFont.getSize()/2 + space * 10;
			}
		} else
		// 多条记录时，显示辅助图例信息
		{
			// 绘制图例，并计算wellNo的最大长度
			int axesLineLen = 10;
			int len = 0;
			stroke = new BasicStroke(5, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER);
			g2.setStroke(stroke);
			for (int i = 0; i < dataSeries.size(); i++)
			{
				if (i < form.chartLineColors.size())// 如果格式中设定的颜色总类少于数据系列，后面的系列使用颜色集合的最后一个颜色
				{
					g2.setColor(form.chartLineColors.elementAt(i));
				}
				y1 = origin.y + axesXHeight
						+ (form.aideTextFont.getSize() + space) * i
						+ form.aideTextFont.getSize() / 2;
				g2.drawLine(origin.x, y1, origin.x + axesLineLen, y1);
				len = Math.max(len, dataSeries.elementAt(i).wellNo.length());
			}
			x1 = origin.x + axesLineLen + 10 * space;
			x2 = x1 + form.aideTextFont.getSize() * len/2 + 10 * space;
			g2.setColor(form.aideTextColor);
			g2.setFont(form.aideTextFont);
			for (int i = 0; i < dataSeries.size(); i++)
			{
				y1 = origin.y + axesXHeight
						+ (form.aideTextFont.getSize() + space) * i
						+ form.aideTextFont.getSize();
				ErgogramData data = dataSeries.elementAt(i);
				g2.drawString(data.wellNo, x1, y1);
				g2.drawString(data.time, x2, y1);
			}
		}
	}
	
	protected void calAideTexHeight()
	{
		
		if (dataSeries.size() > 1)
		{
			aideTextHeight = (form.aideTextFont.getSize() + space)
					* dataSeries.size();
		} else
		{
			int num = 0;
			Vector<AideText> vct = form.aideTexts;
			if (vct != null)
			{
				num++;
				for (int i = 0; i < vct.size(); i++)
				{
					if (vct.get(i).field.equals("--"))
					{
						num++;
					}
				}
			}
			aideTextHeight = (form.aideTextFont.getSize() + space) * num;
		}
	}
	
	//计算坐标标签值（xy轴是否固定）
	protected void calculateAxesValue()
	{
	//y最大值=y*3
		float fmaxY=maxY,fminY=minY;
		if (dataSeries.size()==1)
		{
			for(int i= 0;i<form.axesYRefLines.size();i++)
			{
				fmaxY = Math.max(fmaxY,StringUtils.parseFloat(dataSeries.get(0).fields.get(form.axesYRefLines.elementAt(i)),maxY));
				fminY = Math.min(fminY,StringUtils.parseFloat(dataSeries.get(0).fields.get(form.axesYRefLines.elementAt(i)),minY));
			}
		}
		axesY = ChartData.calculateReferrenceValue(fmaxY, fminY,
				form.axesYMaxLimit, form.axesYMinLimit, form.axesYBlankScale,
				form.axesYTagNum);
		
		
//  横向最大坐标固定为7.5

		maxX=8.0f;
		axesX = ChartData
		.calculateReferrenceValue(maxX, minX, form.axesXTagNum);
	}
}
