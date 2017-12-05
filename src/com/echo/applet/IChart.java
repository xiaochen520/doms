package com.echo.applet;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.io.File;

public interface IChart {
	public int draw(Graphics g, Point zeroPoint, Dimension  size); 
	public int save(File file, String fileType); 
	public int print(); 
	public int layoutPage(Graphics g, Point zeroPoint, Dimension size);
}
