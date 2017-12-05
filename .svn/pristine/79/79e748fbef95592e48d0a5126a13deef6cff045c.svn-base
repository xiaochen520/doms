package com.echo.applet;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.Container;
import java.awt.Graphics;
//import java.awt.Panel;
import javax.swing.JApplet;

import com.echo.util.ExtentionFileFilter;

public abstract class ChartApplet extends JApplet
{
	protected IChart chart;
	protected ChartPanel panel ;
	public void init()
	{
		//setSize(320, 200);
		setVisible(false);

		Container contentPane = getContentPane();

		panel = new ChartPanel(this);
		contentPane.add(panel);// 图形容器

	}
	
	public void start()
	{
		super.start();
		setVisible(true);
	}

	public void update(Graphics g)
	{
		paint(g);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		
/*		Point zeroPoint = new Point();
		zeroPoint.x = 0;
		zeroPoint.y = 0;
		Dimension size = new Dimension();
		size = getSize();
//		setVisible(false);
		Graphics gg = panel.getGraphics().create();


		if (chart.layoutPage(panel.getGraphics(), zeroPoint, size) == 1)
		{
			//setSize(size);
			resize(size);
			repaint();
		}
		chart.draw(panel.getGraphics(), zeroPoint, size);
//		setVisible(true);
				//System.out.println("paintafter");
*/		
	}

	public void setChart(IChart chart)
	{
		this.chart = chart;
	}

	public void print()
	{
	}

	public void save()
	{
		JFrame frame = new JFrame("保存图形");
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("保存图形");
		ExtentionFileFilter filter = new ExtentionFileFilter();
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.setDescription("JPG格式图片");
		chooser.setFileFilter(filter);
		if (chooser.showDialog(frame, "保存") == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			// String sfilename=file.getName();
			// System.out.print(file.getPath());
			chart.save(file, "jpg");
		}

	}

}
