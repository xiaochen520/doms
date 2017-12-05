package com.echo.applet.pieChart;

import com.echo.applet.ChartData;
import com.echo.util.StringUtils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.geom.Arc2D;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
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
public class PieChart extends ChartData
{
	PieChartForm form;
	Vector<PieChartData> dataSeries;

	Dimension innerSize = new Dimension();
	private Dimension minInnerSize = new Dimension();
	int titleHeight, titleWidth, legendTextWidth, legendTextHeight;
	int space = 3;// 行间距，单词之间间距

	private int descLen = 0, dataLen = 0;// 最长的数据描述宽度，最长的树枝宽度

	public PieChart()
	{
		form = new PieChartForm();
		dataSeries = new Vector<PieChartData>();
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
		if (dataSeries.size() < 1)
			return -1;
		int result = 0;
		DecimalFormat fmt = new DecimalFormat("########0.###");
		int num = 0;
		float fmaxY = 0, fminY = 0;
		String str1, str2;
		titleHeight = form.titleFont.getSize() + space * 4;
		if(dataSeries.get(0).aideTitle!=null && dataSeries.get(0).aideTitle.trim().length()>0)
		{
			titleHeight += form.aideTitleFont.getSize()+ space;
		}

		titleWidth = form.titleFont.getSize() / 2 * StringUtils.getCNStringLen(dataSeries.get(0).title);
		titleWidth = Math.max(titleWidth, form.titleFont.getSize() / 2 * StringUtils.getCNStringLen(dataSeries.get(0).aideTitle));

		Hashtable<String, Float> htTmp = dataSeries.get(0).data;
		Enumeration<String> enm = htTmp.keys();
		num = 0;
		while (enm.hasMoreElements())
		{
			str1 = enm.nextElement();
			str2 = fmt.format(htTmp.get(str1).floatValue());
			descLen = Math.max(descLen, StringUtils.getCNStringLen(str1));
			dataLen = Math.max(dataLen, str2.length());
			num++;
		}
		legendTextWidth = form.legendFont.getSize() * (descLen + dataLen + 10 + StringUtils.getCNStringLen(dataSeries.get(0).unit)) / 2;
		legendTextHeight = (form.legendFont.getSize() + space) * num + space;

		if (form.legendPos == PieChartForm.TOP || form.legendPos == PieChartForm.BOTTOM)
		{
			minInnerSize.width = Math.max(Math.max(legendTextWidth, titleWidth), (int) (form.minDiameter * (form.pieChartWHScale + 0.2f)));// 0.2f表示饼图区域外的空白
			minInnerSize.height = legendTextHeight + titleHeight + (int) (form.minDiameter * 1.2f);
		} else
		{
			minInnerSize.width = Math.max(legendTextWidth + (int) (form.minDiameter * (form.pieChartWHScale + 0.2f)), titleWidth);
			minInnerSize.height = Math.max(legendTextHeight, (int) (form.minDiameter * 1.2f)) + titleHeight;// 0.2f表示饼图区域外的空白

		}

		// 反算修改传入图形尺寸
		if (size.height < minInnerSize.height - 2)
		{
			size.height = minInnerSize.height;
			result = 1;
		}
		if (size.width < minInnerSize.width - 2)
		{
			size.width = minInnerSize.width;
			result = 1;
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

		// result = layoutPage(g, zeroPoint, size);

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
		//g2.setBackground(Color.white);
		//g2.setColor(Color.white);
		g2.fillRect(zeroPoint.x, zeroPoint.y, size.width, size.height);

		// 目前只处理一个饼图
		{
			// 绘制标题区域
			drawTitle(g2, zeroPoint, size, dataSeries.get(0));

			// 绘制图例文本
			drawLegend(g2, zeroPoint, size, dataSeries.get(0));

			// 绘制饼图
			drawPieChart(g2, zeroPoint, size, dataSeries.get(0));
		}

		// 恢复g对象的绘画部分参数
		g2.setStroke(oldStroke);
		g2.setBackground(oldBgColor);
		g2.setColor(oldColor);
		g2.setFont(oldFont);

		return result;
	}

	public int addSeries(PieChartData data)
	{
		if (data == null)
		{
			return -1;
		}

		if (dataSeries == null)
		{
			dataSeries = new Vector<PieChartData>();
		}
		dataSeries.add(data);

		return dataSeries.size();

	}

	public void clearData()
	{
		if (dataSeries == null)
		{
			dataSeries = new Vector<PieChartData>();
		}
		dataSeries.clear();
	}

	public static void main(String[] args)
	{
		String str = "aa";
		str.concat("bb");
		System.out.println(str.substring(0, 0).concat("aa"));
		// testcalculateReferrenceValue();
	}

	protected void drawTitle(Graphics2D g2, Point zeroPoint, Dimension size, PieChartData chartData)
	{
		int x = 0, y = 0;
		int fontSize;
		// 绘制标题
		fontSize = form.titleFont.getSize();
		x = zeroPoint.x + size.width / 2 - StringUtils.getCNStringLen(chartData.title) * fontSize / 4;
		y = zeroPoint.y + space * 2 + fontSize;
		g2.setColor(form.titleColor);
		g2.setFont(form.titleFont);
		g2.drawString(chartData.title, x, y);
		// 绘制付标题
		fontSize = form.aideTitleFont.getSize();
		x = zeroPoint.x + size.width / 2 - StringUtils.getCNStringLen(chartData.aideTitle) * fontSize / 4;
		y += space * 2 + fontSize;
		g2.setColor(form.aideTitleColor);
		g2.setFont(form.aideTitleFont);
		g2.drawString(dataSeries.get(0).aideTitle, x, y);

	}

	protected void drawLegend(Graphics2D g2, Point zeroPoint, Dimension size, PieChartData chartData)
	{
		int x = 0, y = 0;
		int fontSize;
		String str;
		BasicStroke stroke;

		// 计算图例左上角坐标
		switch (form.legendPos)
		{
		case PieChartForm.TOP:
			x = zeroPoint.x + size.width / 2 - legendTextWidth / 2;
			y = zeroPoint.y + titleHeight;
			break;
		case PieChartForm.BOTTOM:
			x = zeroPoint.x + size.width / 2 - legendTextWidth / 2;
			y = zeroPoint.y + size.height - legendTextHeight - space;
			break;
		case PieChartForm.LEFT:
			x = zeroPoint.x + space * 2;
			y = zeroPoint.y + titleHeight + (size.height - titleHeight) / 2 - legendTextHeight / 2;
			break;
		case PieChartForm.RIGHT:
			x = zeroPoint.x + size.width - legendTextWidth - space * 2;
			y = zeroPoint.y + titleHeight + (size.height - titleHeight) / 2 - legendTextHeight / 2;
			break;

		}

		// 绘制图例外边框
		stroke = new BasicStroke(0, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
		g2.setStroke(stroke);
		g2.setColor(Color.DARK_GRAY);
		//g2.drawRect(x, y, legendTextWidth, legendTextHeight);

		// 绘制图例
		stroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
		g2.setStroke(stroke);
		fontSize = form.legendFont.getSize();
		Enumeration<String> enm = chartData.data.keys();
		int i = 0;
		x += space;
		y += space;
		if(form.pieFillColors.size()==0)
		{
			form.addPieFillColor("#FF0000");
		}
		g2.setFont(form.legendFont);

		while (enm.hasMoreElements())
		{
			str = enm.nextElement();
			if (i < form.pieFillColors.size())// 如果格式中设定的颜色总类少于数据系列，后面的系列使用颜色集合的最后一个颜色
			{
				g2.setColor(form.pieFillColors.elementAt(i));
			} else
			{
				if (form.pieFillColors.size() < 2)
				{
					g2.setColor(form.pieFillColors.elementAt(0));

				} else
				{
					g2.setColor(form.pieFillColors.elementAt(form.pieFillColors.size() - 1 - i % 2));
				}
			}
			g2.fillRect(x, y, fontSize, fontSize);
			g2.setColor(form.legendColor);
			g2.drawRect(x, y, fontSize, fontSize);
			g2.drawString(str, x + fontSize * 2, y + fontSize);
			g2.drawString(chartData.data.get(str).toString(), x + fontSize * (4 + descLen / 2), y + fontSize);
			g2.drawString(StringUtils.isNull(chartData.unit, ""), x + fontSize * (5 + descLen / 2 + dataLen / 2), y + fontSize);
			y += space + fontSize;
			i++;
		}
	}

	protected void drawPieChart(Graphics2D g2, Point zeroPoint, Dimension size, PieChartData chartData)
	{
		int x = 0, y = 0;
		int fontSize;
		int dia = 0;
		String str;
		BasicStroke stroke;
		float radianRatio = 0;
		float radianStart = 0;
		float value = 0;

		// 计算饼图圆心位置
		switch (form.legendPos)
		{
		case PieChartForm.TOP:
			dia = (int) (Math.min((size.height - titleHeight - legendTextHeight) / 1.2f, size.width / (form.pieChartWHScale + 0.2f)));
			x = zeroPoint.x + size.width / 2;
			y = zeroPoint.y + (titleHeight + legendTextHeight + size.height) / 2;
			break;
		case PieChartForm.BOTTOM:
			dia = (int) (Math.min((size.height - titleHeight - legendTextHeight) / 1.2f, size.width / (form.pieChartWHScale + 0.2f)));
			x = zeroPoint.x + size.width / 2;
			y = zeroPoint.y + (titleHeight - legendTextHeight + size.height) / 2;
			break;
		case PieChartForm.LEFT:
			dia = (int) (Math.min((size.height - titleHeight) / 1.2f, (size.width - legendTextWidth) / (form.pieChartWHScale + 0.2f)));
			x = zeroPoint.x + (size.width + legendTextWidth) / 2;
			y = zeroPoint.y + (titleHeight + size.height) / 2;
			break;
		case PieChartForm.RIGHT:
			dia = (int) (Math.min((size.height - titleHeight) / 1.2f, (size.width - legendTextWidth) / (form.pieChartWHScale + 0.2f)));
			x = zeroPoint.x + (size.width - legendTextWidth) / 2;
			y = zeroPoint.y + (titleHeight + size.height) / 2;
			break;
		}
		radianRatio = 360f / chartData.sum;
		stroke = new BasicStroke(form.lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
		g2.setStroke(stroke);
		Enumeration<String> enm = chartData.data.keys();
		Arc2D arc = new Arc2D.Float(Arc2D.PIE);
		arc.setFrame(x - dia * form.pieChartWHScale / 2, y - dia * .5f, dia * form.pieChartWHScale, dia);
		int i = 0;
		while (enm.hasMoreElements())
		{
			str = enm.nextElement();
			value = chartData.data.get(str).floatValue() * radianRatio;//
			if (i < form.pieFillColors.size())// 如果格式中设定的颜色总类少于数据系列，后面的系列使用颜色集合的最后一个颜色
			{
				g2.setColor(form.pieFillColors.elementAt(i));
			} else
			{
				if (form.pieFillColors.size() < 2)
				{
					g2.setColor(form.pieFillColors.elementAt(0));

				} else
				{
					g2.setColor(form.pieFillColors.elementAt(form.pieFillColors.size() - 1 - i % 2));
				}
			}
			arc.setAngleStart(radianStart);
			arc.setAngleExtent(value);
			g2.fill(arc);
			g2.setColor(form.lineColor);
			g2.draw(arc);

			i++;
			radianStart += value;

		}

	}
}
