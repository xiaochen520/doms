<%@ page language="java" contentType="image/png" pageEncoding="UTF-8"%><%@
 page import="com.echo.util.StringUtils"%><%@
 page import="com.echo.applet.ergogram.*"%><%@
 page import="javax.imageio.ImageIO" %><%@
 page import="java.awt.image.BufferedImage"%><%

	// 清空缓冲区

	String wellNo = StringUtils.isNull(request.getParameter("wellNo"), "");
	String strTime = StringUtils.isNull(request.getParameter("gotTime"), "");
	System.out.println(strTime);
	String ergogramType = StringUtils.isNull(request.getParameter("ergogramType"), "地面功图");
	String xmlName;
	System.out.println(wellNo);
	System.out.println("ErgogramChart.jsp:" + wellNo);
	if(ergogramType.equals("泵功图"))//缺省为地面工图、非泵功图做
	{
		xmlName = "pumpErgogramChartSet.xml";
	}
	else
	{
		xmlName = "ergogramChartSet.xml";
	}
	
	BufferedImage image = ErgogramFactory.createImage(xmlName,wellNo,strTime);
	System.out.println("image====="+image);
	if(image==null)
	{
		response.setContentType("text/html");
		out.write(wellNo+"于"+strTime+"图形生成失败！");
		return;
	}
	
	// 利用ImageIO类的write方法对图像进行编码
	try
	{
	
	response.reset();
	ServletOutputStream sos = response.getOutputStream();
	ImageIO.write(image, "PNG",sos);
	sos.close();
	return ;
	}
	catch(Exception e)
	{
		response.setContentType("text/html");
		out.write("图形生成失败！");
		//return;
	}
	
%>


