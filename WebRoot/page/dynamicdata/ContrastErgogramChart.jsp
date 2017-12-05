<%@ page language="java" contentType="image/png" pageEncoding="UTF-8"%><%@
 page import="com.echo.util.StringUtils"%><%@
 page import="com.echo.applet.ergogram.*"%><%@
 page import="javax.imageio.ImageIO" %><%@
 page import="java.awt.image.BufferedImage"%><%

	// 清空缓冲区
//dskldjksdjlsdil 
	String wellNo = StringUtils.isNull(request.getParameter("wellNo"), "");
	String strTime = StringUtils.isNull(request.getParameter("gotTime"), "");
	String ergogramType = StringUtils.isNull(request.getParameter("ergogramType"), "地面功图");
	String xmlName;
	String strWhere = request.getParameter("strWhere");
	if(strWhere==null || strWhere.trim().length()==0)
	{
		response.setContentType("text/html");
		out.write("没有输入正确的功图条件！");
		return;
	}
	strWhere=strWhere.substring(0,strWhere.length()-1);
//	System.out.println("ContrastErgogramChart.jsp:"+ strWhere);
	if(ergogramType.equals("泵功图"))//缺省为地面工图、非泵功图做
	{
		xmlName = "ContrastPumpSet.xml";
	}
	else
	{
		xmlName = "ContrastGroundSet.xml";
	}
	BufferedImage image = ErgogramFactory.createImage(xmlName,strWhere);
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


