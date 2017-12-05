package com.echo.applet;

import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Dimension;

public class ChartPanel extends JPanel
{
	IChart chart;
	ChartApplet chartApplet;
	public ChartPanel (ChartApplet applet)
	{
		this.chartApplet = applet;
	}
	public void setChart(IChart chart)
	{
		this.chart = chart;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (chart!=null)
		{
			Point zeroPoint = new Point();
			zeroPoint.x = 0;
			zeroPoint.y = 0;
			Dimension size = new Dimension();
			size = getSize();
			
			if (chart.layoutPage(g, zeroPoint, size) == 1)
			{
				chartApplet.resize(size);
			}
			chart.draw(g, zeroPoint, size);
		}
	}
}
