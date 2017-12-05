<%@ page language="java" contentType="image/png" pageEncoding="UTF-8"%><%@
 page import="com.echo.util.StringUtils"%><%@
 page import="com.echo.applet.ergogram.*"%><%@
 page import="javax.imageio.ImageIO" %><%@
 page import="java.awt.image.BufferedImage"%>
 
 <%

	// 清空缓冲区

	String wellNo = StringUtils.isNull(request.getParameter("wellNo"), "");
	String strTime = StringUtils.isNull(request.getParameter("gotTime"), "");
	BufferedImage image = DetailErgogramFactory.createImage("detailErgogramChartSet.xml",wellNo,strTime);

	if(image==null)
	{
		response.setContentType("text/html");
		out.write("图形生成失败！");
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
	}
	
%>


