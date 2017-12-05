package com.echo.applet.ergogram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import com.echo.applet.ChartData;
import com.echo.applet.ergogram.Ergogram;
import com.echo.util.StringUtils;

public class DetailErgogram extends Ergogram
{
	public int draw(Graphics g, Point zeroPoint, Dimension size)
	{
		int result = super.draw(g, zeroPoint, size);
		drawDataEx((Graphics2D)g);
		return result;
	}
	

	private void drawDataEx(Graphics2D g2)
	{
		if (dataSeries==null || dataSeries.size()==0 )//|| ((DetailErgogramData)(dataSeries.get(0))).detailDataX==null)
		{
			return;
		}
		      //djlsdksldjkslk
		Font oldFont = g2.getFont();
		Stroke oldStroke = g2.getStroke();
		Color oldBgColor = g2.getBackground();
		Color oldColor = g2.getColor();
		
		BasicStroke stroke;
		int x1, x2, y1, y2;
		
		
		// 详细工图中，地面工图作为主，其他的曲线作为辅助曲线。绘制辅助曲线。
		// 从理论上讲，详细工图只能绘制一口井的某一个工图以及相关的泵工图。在这里不作多地面工图数据的判断。
		stroke = new BasicStroke(form.chartLineWidth, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER);
		g2.setStroke(stroke);
		
		ErgogramData erg = dataSeries.get(0);
		for(int i = 0;i < ((DetailErgogramData)erg).detailDataX.size(); i++)
		{
			float[] dataX = ((DetailErgogramData)erg).detailDataX.get(i);
			float[] dataY = ((DetailErgogramData)erg).detailDataY.get(i);
			if (i>form.chartLineColors.size()-2)//颜色组中的第一个颜色为地面工图主图颜色
			{
				g2.setColor(form.chartLineColors.lastElement());
			}
			else
			{
				g2.setColor(form.chartLineColors.get(i+1));
			}
			
			x1 = scaleX == 0f ? origin.x + innerSize.width / 2 : origin.x + (int) ((dataX[0] - axesX[0]) * scaleX);
			y1 = scaleY == 0f ? origin.y - innerSize.height/ 2 : origin.y - (int) ((dataY[0] - axesY[0]) * scaleY);
			
			for (int j = 1; j < dataX.length; j++)
			{
				x2 = scaleX == 0f ? origin.x + innerSize.width / 2 : origin.x + (int) ((dataX[j] - axesX[0]) * scaleX);
				y2 = scaleY == 0f ? origin.y - innerSize.height/ 2 : origin.y - (int) ((dataY[j] - axesY[0]) * scaleY);
				g2.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
			if (form.chartClosed)
			{
				x2 = scaleX == 0f ? origin.x + innerSize.width / 2 : origin.x + (int) ((dataX[0] - axesX[0]) * scaleX);
				y2 = scaleY == 0f ? origin.y - innerSize.height/ 2 : origin.y - (int) ((dataY[0] - axesY[0]) * scaleY);
				g2.drawLine(x1, y1, x2, y2);
			}
			g2.setFont(form.axesFont);
			int iRGB = g2.getColor().getRGB();
			iRGB ^=  form.bgColor.getRGB() ;
			iRGB &= 0x770000;
			g2.setColor(new Color(iRGB));
			for(int j= 0;j<form.markingPointNoFields.size();j++)
			{
				int no=StringUtils.parseInt(erg.fields.get(form.markingPointNoFields.get(j)),-1);
				if(no>-1 && no < erg.dataX.length)
				{
					x1 = scaleX == 0f ? origin.x + innerSize.width / 2 : origin.x + (int) ((dataX[no] - axesX[0]) * scaleX);
					y1 = scaleY == 0f ? origin.y - innerSize.height/ 2 : origin.y - (int) ((dataY[no] - axesY[0]) * scaleY);
					x1 -= (form.axesFont.getSize()/4);
					y1 += (form.axesFont.getSize()/2);
					g2.drawString("#", x1, y1);
				}
			}
		}
	
		// 恢复g对象的绘画部分参数
		g2.setStroke(oldStroke);
		g2.setBackground(oldBgColor);
		g2.setColor(oldColor);
		g2.setFont(oldFont);

	}

/*与父类方法相同。
	//计算坐标标签值,覆盖父类
	protected void calculateAxesValue()
	{
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
		axesX = ChartData
		.calculateReferrenceValue(maxX, minX, form.axesXTagNum);
	}
*/
}
