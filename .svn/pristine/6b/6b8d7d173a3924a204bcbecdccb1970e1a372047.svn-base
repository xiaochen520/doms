
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="com.echo.applet.ergogram.DetailErgogramFactory"%>
<%@ page import="com.echo.applet.pieChart.PieChartFactory"%>
<%@ page import="com.echo.util.StringUtils"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="org.dom4j.Document"%>
<%@ page import="org.dom4j.Element"%>

<%@ page import="java.sql.*,com.echo.util.DBUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

%>
<%
	System.out.println("++++++++++++++++++");
	String wellNo=StringUtils.isNull((String)request.getParameter("wellNo"),"");
	String gotTime=StringUtils.isNull((String)request.getParameter("time"),"");
	//String f=(String)session.getAttribute("from");
	StringBuffer strSql = new StringBuffer();
	StringBuffer strBuf = new StringBuffer();
	StringBuffer strSql1 = new StringBuffer();
System.out.println("-----------------------------"+wellNo);
	if (wellNo.equals("")||wellNo.equals("null") || gotTime.equals("")||gotTime.equals("null"))
	{
		out.write("没有指定功图条件!");       
		out.flush();
		return;
	}
	if (wellNo.equals("*") )
	{
	wellNo=request.getParameter("wellNo");
	gotTime=request.getParameter("gotTime");
}

System.out.println("DetailErgogram.jsp="+wellNo+"||gotTime="+gotTime);
	
//System.out.println("info1.getP02()=="+info1.getP02());
//	DetailErgogramAideTool eat = new DetailErgogramAideTool();
//	eat.setFormXml("detailErgogramChartSet.xml", eat.getClass());
//	strBuf.append(wellNo).append(",").append(strTime);
//	String strDetailForm = eat.createDataPara(strBuf.toString());
//	String strDetailData = eat.createFormPara();

//	Document form = eat.getFormXml();
	Document form ;
	Element root ;
	int ergogramWidth = 350,ergogramHeight=200;//详细工图图形宽、高
	int pumpWidth = 320,pumpHeight=200;//泵效饼图图形宽、高

	form =PieChartFactory.getDocument("pieChartSet.xml");
	root = form.getRootElement().element("form");
	pumpWidth  = StringUtils.parseInt(DetailErgogramFactory.getElementText(root,"width"),pumpWidth);
	pumpHeight = StringUtils.parseInt(DetailErgogramFactory.getElementText(root,"height"),pumpHeight);

	form =DetailErgogramFactory.getDocument("detailErgogramChartSet.xml");
	root = form.getRootElement();
	ergogramWidth  = StringUtils.parseInt(DetailErgogramFactory.getElementText(root,"width"),ergogramWidth);
	ergogramHeight = StringUtils.parseInt(DetailErgogramFactory.getElementText(root,"height"),ergogramHeight);

/*
	Element elm= root.element("width");
	if (elm != null)
	{
		appletWidth = StringUtils.parseInt(elm.getTextTrim(),320);
	}
	elm = root.element("height");
	if (elm != null)
	{
		appletHeight = StringUtils.parseInt(elm.getTextTrim(),320);
	}
*/	  
//动液面
String dym = "";
Connection conn1 = null;                 //声明Connection对象
	Statement stmt1=null;
	ResultSet rs1 = null;                    //声明ResultSet对象
		strSql1.append("select a.p17 as dym,b.* from dbat2073 a,dbat1001 b where a.p01=b.p01 and a.p01='").append(wellNo).append("'");
		//strSql1.append("select p19 from vwht where p01='").append(wellNo).append("'");
		strSql1.append(" and a.p02='").append(gotTime).append(
				"'");
		String p04="",p08="",p05="";
		Double p16=0.0,p17=0.0,p22=0.0,p23=0.0,p24=0.0,p25=0.0,p26=0.0,p27=0.0,p28=0.0,p29=0.0,p30=0.0,p31=0.0,p32=0.0,p33=0.0,p34=0.0,p46=0.0;
		
		
//System.out.println("dym===="+dym);
		if(dym.equals("-9999.00")||dym.equals("null")||dym==null){
			dym="动液面未计算";
		}


	Connection conn = null;                 //声明Connection对象
	Statement stmt=null;
	ResultSet rs = null;                    //声明ResultSet对象
		strSql.append("select * from PC_RD_INDICATOR_RESULT_T").append(" where ");
		strSql.append(root.element("objectField").getTextTrim()).append(
				"='").append(wellNo).append("'");
		strSql.append(" and ").append(
				root.element("timeField").getTextTrim());
		strSql.append("= '").append(gotTime).append(
				"'");
		conn = DBUtils.getConnection(); 
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    rs = stmt.executeQuery(strSql.toString()); 

	StringBuffer aideTitle = new StringBuffer();
	Hashtable<String, String> htMeasure = new Hashtable<String, String>();
	String strArrMeasure[][] = new String[16][4];
	int k = 0;
	
	
	strArrMeasure[k][0] = "冲程";
	strArrMeasure[k][1] = "STROKE";
	strArrMeasure[k][2] = "m";
	k++;
	strArrMeasure[k][0] = "冲次";
	strArrMeasure[k][1] = "JIG_FREQUENCY";
	strArrMeasure[k][2] = "次";
	k++;
	strArrMeasure[k][0] = "地面最大载荷";
	strArrMeasure[k][1] = "HIGHER_LOAD";
	strArrMeasure[k][2] = "kn";
	k++;
	strArrMeasure[k][0] = "地面最小载荷";
	strArrMeasure[k][1] = "LOWER_LOAD";
	strArrMeasure[k][2] = "kn";
	k++;
	strArrMeasure[k][0] = "上载荷线FMU";
	strArrMeasure[k][1] = "FMU";
	strArrMeasure[k][2] = "kn";
	k++;
	strArrMeasure[k][0] = "下载荷线FMD";
	strArrMeasure[k][1] = "FMD";
	strArrMeasure[k][2] = "kn";
	k++;
	strArrMeasure[k][0] = "有效冲程";
	strArrMeasure[k][1] = "EFFECTIVE_STROKE";
	strArrMeasure[k][2] = "米";
	k++;
	strArrMeasure[k][0] = "活塞冲程";
	strArrMeasure[k][1] = "PISTON_STROKE";
	strArrMeasure[k][2] = "米";
	k++;
	strArrMeasure[k][0] = "泵效";
	strArrMeasure[k][1] = "EFFICIENCY_SYSTEM";
	strArrMeasure[k][2] = "%";
	k++;
	strArrMeasure[k][0] = "漏失损失";
	strArrMeasure[k][1] = "LOSS_STROKE_DODWALL";
	strArrMeasure[k][2] = "%";
	k++;
	strArrMeasure[k][0] = "冲程损失";
	strArrMeasure[k][1] = "LOSS_DESCE_DODWALL";
	strArrMeasure[k][2] = "%";
	k++;
	strArrMeasure[k][0] = "气体损失";
	strArrMeasure[k][1] = "CRUDE_OIL_SHRINKAGE";
	strArrMeasure[k][2] = "%";
	k++;
	strArrMeasure[k][0] = "容积损失";
	strArrMeasure[k][1] = "ROOFBOLT_STROKE_LOSS";
	strArrMeasure[k][2] = "%";
	k++;
	strArrMeasure[k][0] = "理论排量";
	strArrMeasure[k][1] = "THEORETICAL_DIS_CAP";
	strArrMeasure[k][2] = "t/d";
	k++;
	strArrMeasure[k][0] = "实际产液量";
	strArrMeasure[k][1] = "PRODUCED_FLUID";
	strArrMeasure[k][2] = "t/d";
	k++;
	strArrMeasure[k][0] = "诊断结果";
	strArrMeasure[k][1] = "DIAGNOSIS";
	strArrMeasure[k][2] = " ";
	k++;
	
	try
	{
	if (rs.first())
	{
		aideTitle.append(rs.getString(root.element("objectField").getTextTrim())).append(
				"&nbsp;&nbsp;&nbsp;&nbsp;");
		aideTitle.append(rs.getString(root.element("timeField").getTextTrim()));
		for (int i = 0; i < k; i++)
		{
			strArrMeasure[i][3] = StringUtils.isNull(rs.getString(strArrMeasure[i][1]), "--");
		}
	}
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}finally{
		if(rs != null){
			rs.close();
		}
		if(stmt != null){
			stmt.close();
		}
		if(rs != null){
			rs.close();
		}
	}
	
	
	

//	session.removeValue("AppWellNameSession");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'DetailErgogram.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath%>web/echo/common/styleEx.css" rel="stylesheet" type="text/css" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 18px
}

.STYLE4 {
	font-size: 20px
}
-->
</style>
<script type="text/javascript">
function ff(){
			window.history.back(1);
			 // window.open("<%=basePath%>module/behaviourStatistics/ListBehaviourStatistics.do","TableFrm");
		}
function autoMaxWindow(){
	top.window.moveTo(0,0);
	if (document.all) {
		top.window.resizeTo(screen.availWidth,screen.availHeight);
	}
	else if (document.layers||document.getElementById) {
		if (top.window.outerHeight<screen.availHeight||top.window.outerWidth<screen.availWidth){
			top.window.outerHeight = screen.availHeight;
			top.window.outerWidth = screen.availWidth;
		}
	}
}
</script>
	</head>

	<body > <!--  onload="autoMaxWindow();"-->
		<div width=100% height=100% align=center valign=middle>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >

				<tr>
					<td>
						<div align="center" class="STYLE1">
							<table width="100%"  cellspacing="0" cellpadding="0">
								<tr height="24">
									<td background="左圆角.jpg" width="13">
										&nbsp;
									</td>
									<td background="middle_bg.JPG">
										<div align="center">
											<span style="font-family: '宋体';font-weight: bold;font-size: 9pt; ">
											功图诊断详细信息</span>
										</div>
									</td>
									<td background="右边角.jpg" width="13">
										&nbsp;
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center" style="font-family: '宋体';font-size: 9pt; "><%=aideTitle%></td>
				</tr>

				<tr>
					<table width="100%" border="1" cellspacing="0" cellpadding="0" height="385pt" >
					
					<tr class="STYLE4" bgcolor=#A97ED4>
						<td width = 25% height="30pt">单井产量分析结果</td>
						<td width = 30%>地面功图及泵功图</td>
						<td width = 20%>泵效分析饼图</td>
						<td width = 25%>基础信息</td>
					</tr>
					<tr>
					<td valign = top>
					<table width="100%" height="100%"  cellspacing="0" cellpadding="0">
					<%
					for (int j =0;j<strArrMeasure.length;j++)
					{
					%>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center><%=strArrMeasure[j][0] %>(<%=strArrMeasure[j][2] %>)</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><%=strArrMeasure[j][3] %></td>
						</tr>
					<%
					}
					%>
					<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center><%="动液面" %>(<%="米" %>)</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><%=dym %></td>
						</tr>
					</table>
					</td>
					<td align = center valign =middle>
						<iframe src="page/productInfo/DetailErgogramChart.jsp?wellNo=<%=java.net.URLEncoder.encode(wellNo,"UTF-8")%>&gotTime=<%=gotTime%>"
							width="<%=ergogramWidth%>" height="<%=ergogramHeight%>"
							frameborder=0 scrolling=no border="0" MARGINWIDTH=0 marginheight=0>
						</iframe>
					</td>
					<td align = center  valign =middle>
						<iframe src="page/productInfo/PumpEffectsChart.jsp?wellNo=<%=java.net.URLEncoder.encode(wellNo,"UTF-8")%>&gotTime=<%=gotTime%>"
							width="<%=pumpWidth%>" height="<%=pumpHeight%>"
							frameborder=0 scrolling=no border="0" MARGINWIDTH=0 marginheight=0>
						</iframe>
					</td>
					<td>
						<table width="100%" height="100%"  cellspacing="0" cellpadding="0">
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>区块</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><%= "采油工区"%></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>生产方式</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><%= p04%></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>当前状态</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><%= p05%></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>电机型号</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center>&nbsp;<%= p08%></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>泵径</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p16==-9999){out.print("--");}else{out.print(p16);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>泵深</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p17==-9999){out.print("--");}else{out.print(p17);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>脱气原油密度</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p23==-9999){out.print("--");}else{out.print(p23);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>杆级数</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p24==-9999){out.print("--");}else{out.print(p24);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>一级杆长</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p25==-9999){out.print("--");}else{out.print(p25);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>一级杆径</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p26==-9999){out.print("--");}else{out.print(p26);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>二级杆长</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p27==-9999){out.print("--");}else{out.print(p27);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>二级杆径</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p28==-9999){out.print("--");}else{out.print(p28);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>三级杆长</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p29==-9999){out.print("--");}else{out.print(p29);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>三级杆径</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p30==-9999){out.print("--");}else{out.print(p30);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>四级杆长</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p31==-9999){out.print("--");}else{out.print(p31);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>四级杆径</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p32==-9999){out.print("--");}else{out.print(p32);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>五级杆长</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p33==-9999){out.print("--");}else{out.print(p33);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>五级杆径</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p34==-9999){out.print("--");}else{out.print(p34);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>生产水油比</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p22==-9999){out.print("--");}else{out.print(p22);} %></td>
						</tr>
						<tr >
						<td bgcolor="#f1f5fc" style="border-top:1 solid gray; border-right:1 solid gray" align =center>生产油气比</td>
						<td style="border-top:1 solid gray; font-family: '宋体';font-size: 9pt; " align =center><% if(p46==-9999){out.print("--");}else{out.print(p46);} %></td>
						</tr>
					
					</table>
					</td>
					</tr>
					</table>
				</tr>
			</table>
			
		</div>
		<br><br>
		
		

		<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
					<TD align=center>
						<img src="img/btn_back.gif" width="51" height="22" onclick="ff();" style="cursor: hand">
					</TD>
			</TABLE>

	 
	</body>
</html>
