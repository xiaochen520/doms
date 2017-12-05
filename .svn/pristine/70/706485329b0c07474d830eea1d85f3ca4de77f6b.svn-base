<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.io.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
   <%response.reset();
		 response.setContentType("application/vnd.ms-excel");
		 InputStream ips = new FileInputStream("D:\\workspaces\\echoms\\dmsweb\\WebRoot\\exceltemplet\\combination\\反应器日报表.xls");  //<---你的excel文件
		 OutputStream ops = response.getOutputStream();
		
		 int data = -1;
		 while((data = ips.read()) != -1) {
		
		 ops.write(data);
		 }
		
		 ops.flush();%>
  </body>
</html>
