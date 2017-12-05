 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="com.echo.applet.ergogram.ErgogramFactory"%>
<%@ page import="com.echo.util.StringUtils"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="org.dom4j.Document"%>
<%@ page import="org.dom4j.Element"%>
<%@ page import="com.echo.util.PageController"%>
<%@ page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.text.ParsePosition"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="java.sql.*,com.echo.util.DBUtils"%>
<%@page import="com.echo.util.StringUtil"%>
<%
	HttpSession session1 = request.getSession() ;
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	

	String wellNo = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("str")));
	//System.out.println(wellNo+"-----大奖");
	String aname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("aname")));
	String bname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("bname")));
	String cname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("cname")));
	String dname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dname")));
	
	String startTime = StringUtils.isNull((String) request.getParameter("startime"), "");
	//System.out.println(startTime+"hah");
	String endTime = StringUtils.isNull((String) request.getParameter("endtime"), "");
	 
	String wellRunState = StringUtils.isNull((String) session.getAttribute("WellOnoffSession"), ""); 
	String ergogramType =StringUtils.isNull((String) session.getAttribute("ErgogramTypeSession"), "地面功图"); 
//System.out.println("ergogramType==========="+ergogramType);
	int pageNo = StringUtils.parseInt(request.getParameter("pageNo"), 1);
	int pageSize = StringUtils.parseInt(request.getParameter("pageSize"), 24);
	int currentPageSize = 0;
	int pageCount = 0;
	int totalRows = 0;
	//小画面的宽度、高度尽量大于或等于xml配置文件的的width、height的设置值，否则图片会失真。
	int appletWidth = 320;//小画面的宽度
	int appletHeight = 240;//小画面的高度

	Document form;
	if("泵功图".equals(ergogramType))
	{
		form =ErgogramFactory.getDocument("pumpErgogramChartSet.xml"); 
	}
	else
	{
		form =ErgogramFactory.getDocument("ergogramChartSet.xml");
	}
	Element root = form.getRootElement();

	appletWidth  = StringUtils.parseInt(ErgogramFactory.getElementText(root,"width"),appletWidth);
	appletHeight = StringUtils.parseInt(ErgogramFactory.getElementText(root,"height"),appletHeight);
	System.out.println(appletHeight+"----------- ");
//	String[] strForm = new String[pageSize], strData = new String[pageSize];
	String[] arrWellNo = new String[pageSize];
	String[] arrGotTime = new String[pageSize];
	String[] arrGotJg = new String[pageSize];
	StringBuffer strSql = new StringBuffer();
	StringBuffer strBuf = new StringBuffer();
	
	if(startTime==""||endTime==""){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		Date date=new Date();
		//startTime=sdf.format(date);
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);  
		ca.add(Calendar.DATE, -7); 
		Date cate=ca.getTime();
		startTime=sdf.format(cate);
		System.out.println(startTime);
		endTime=sdf.format(date);
	}
	

		strSql.append("select c.structurename as UNIT_NAME,a.CJSJ,'' as DIAGNOSIS from  ");
		
		if(aname.equals("") && bname.equals("") && cname.equals("")
		&& dname.equals("") && wellNo.equals("")){
			strSql.append(" (select max(CJSJ) as CJSJ,ORG_ID from PC_RD_INDICATOR_DIAGRAM_T  group by ORG_ID) a  ");
		}else{
			
			strSql.append(" PC_RD_INDICATOR_DIAGRAM_T a ");
		}
		   
		//strSql.append("left join PC_RD_INDICATOR_RESULT_T b on a.WELL_CODE=b.WELL_CODE and a.CJSJ=b.CJSJ ");
		strSql.append(" left join pc_cd_org_tree_v c on a.org_id=c.org_id where 1=1 ");
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		String nowdate = sdformat.format(new Date());
		
		if("".equals(startTime)){
			startTime = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startTime = startTime + " 00:00:00";
		}
		if("".equals(endTime)){
			endTime = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endTime = endTime + " 23:59:59";
		}
	
		strSql.append(" and a.CJSJ between TO_DATE('").append(startTime).append("','YYYY-MM-DD HH24:MI:SS')");
		strSql.append(" and TO_DATE('").append(endTime).append("','YYYY-MM-DD HH24:MI:SS')");
	
	//if(aname!=null&&!aname.equals("") && bname!=null&&!bname.equals("") && cname!=null&&!cname.equals("")
	//	&& dname!=null&&!dname.equals("") && wellNo!=null&&!wellNo.equals("")){
	
	//}else{
		 if(aname!=null&&!aname.equals("")){
	    	strSql.append(" and c.aname='").append(aname).append("'");
	    }
	     if(bname!=null&&!bname.equals("")){
	    	strSql.append(" and c.bname='").append(bname).append("'");
	    }
	     if(cname!=null&&!cname.equals("")){
	    	strSql.append(" and c.cname='").append(cname).append("'");
	    }
	     if(dname!=null&&!dname.equals("")){
	    	strSql.append(" and c.dname='").append(dname).append("'");
	    }
		  if(wellNo!=null&&!wellNo.equals("")){
	    	strSql.append(" and c.structurename='").append(wellNo).append("'");
	    }
	//}		
   
	
	strSql.append(" order by a.CJSJ desc");
		//System.out.println("参数是：aname:"+aname +"--bname:"+bname+"--cname:"+cname+"--dname:"+dname+"--wellNo:"+wellNo+"--startTime:"+startTime+"--endTime:"+endTime);
		System.out.println("sql语句是："+strSql.toString());
//	stmt = new StatementManager();
//	rs = stmt.executeQuery(strSql.toString());
	Connection conn = null;                 //声明Connection对象
	Statement stmt=null;
//	PreparedStatement pstmt = null;         //声明PreparedStatement对象
	ResultSet rs = null;                    //声明ResultSet对象
	try
	{
		conn = DBUtils.getConnection(); 
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//		pstmt = conn.prepareStatement(strSql.toString()); 
	    rs = stmt.executeQuery(strSql.toString()); 
	   
		if (rs != null && rs.next())
		{
			rs.absolute((pageNo - 1) * pageSize + 1);
			int i = 0;
			do
			{
				strBuf.setLength(0);
				strBuf.append(rs.getString("UNIT_NAME")).append(",").append(
						rs.getString("CJSJ"));
				arrWellNo[i]=rs.getString("UNIT_NAME");
				System.out.println("井数据-"+arrWellNo[i]);
				arrGotTime[i]=rs.getTimestamp("CJSJ").toString().substring(0,19);
				System.out.println(arrGotTime[i]+"-----");
				arrGotJg[i]=rs.getString("DIAGNOSIS");
				if(arrGotJg[i]==null){
					arrGotJg[i]="条件不足，无法判断！";
				}
				i++;

				if (i >= pageSize)
					break;

			} while (rs.next());
			currentPageSize = i;
			rs.last();
			totalRows = rs.getRow();
			pageCount = (totalRows - 1) / pageSize + 1;
		}
	} catch (Exception e)
	{
		e.printStackTrace();
	}finally{
		if(rs != null){
			rs.close();
		}
		if(stmt != null){
			stmt.close();
		}
		if(conn != null){
			conn.close();
		}
	
	
	}
	if(arrWellNo.length==0)
{
	out.print("无记录");
	return ;
}	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'ErgogramBrowse.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath%>css/styleEx.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.btn {font-family:"宋体"; font-size:9pt;height:15pt;BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 0px; BORDER-BOTTOM: #7b9ebd 1px solid
	}
		
		</style>
<script type="text/javascript" src="js_lbk/jquery.js"></script>
	</head>

	<body>
		<form method="post" name = form1 id=form1>
		
			<input type=hidden id=wn name=wn value="<%=wellNo%>">
			<input type=hidden id=st name=st value="<%=startTime%>">
			<input type=hidden id=tt name=tt value="<%=endTime%>">
			<input type=hidden id="ckv" name="ckv" value="">
			<% 
			String ckv = (String)request.getParameter("ckv");
			
System.out.println("ckv====================="+ckv);
			request.setAttribute("ckv",ckv);
			 %>
			<table width=100% align=center>
			<tr align="center">
			<td>
			<input type="button" value="选择所有的功图" onclick="checkAll('chk')" style="display:none"/> 
         	<input type="button" value="清空选中的功图" onclick="clearAll('chk')" style="display:none"/>
			</td>
			</tr>	
				<tr>
				
					<td width=100% align=center id=dataTable name=dataTable>
					</td>
				</tr>
				<tr>
					<td><%@ include file="../common/pageErgogram.inc"%></td>
				</tr>
			</table>
			<%
			System.out.println(currentPageSize);
				for (int i = 0; i < currentPageSize; i++)
				{
				System.out.println("jing=="+arrWellNo[i]);
			%>
			<div name="applet<%=i%>" id="applet<%=i%>"  style="z-index:0;" width="<%=appletWidth%>" height="<%=appletHeight%>"
			   align = center valign = middle>
			<table cellSpacing =0 style="border:1 solid gray"><tr><td style="border-bottom:1 solid gray">
			<iframe src="page/dynamicdata/ErgogramChart.jsp?wellNo=<%=java.net.URLEncoder.encode(arrWellNo[i],"UTF-8")%>&gotTime=<%=arrGotTime[i]%>&ergogramType=<%=java.net.URLEncoder.encode(ergogramType,"UTF-8")%>"
				width="<%=appletWidth%>" height="<%=appletHeight%>"
				frameborder=0 scrolling=no border="0" MARGINWIDTH=0 marginheight=0>
			</iframe>
			</td></tr>
		
			<!-- <tr><td align=center>	
			<input type=checkbox name=chk id=chk value='<%=java.net.URLEncoder.encode(arrWellNo[i],"UTF-8")%>,<%=arrGotTime[i]%>' onclick="checkboxxz()">&nbsp;&nbsp;
			<button onclick="contrast();"  class="btn"> 对 比 </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button onclick="detail('<%=arrWellNo[i]%>','<%=arrGotTime[i]%>');"  class="btn"> 详 细 </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button onclick="bjgt('<%=arrWellNo[i]%>','<%=arrGotTime[i]%>');"  class="btn"> 标准功图</button>
			<div id="chenggong"></div>
			</td></tr> -->
			</table>
			</div>
			<%
				}
			%>

<script type="text/javascript">
function bjgt(wellNo,time){
	$.ajaxSetup ({ 
		cache: false //关闭AJAX相应的缓存 
		});

	//alert(wellNo+"=="+time);
	$("#chenggong").load("web/echo/Ergogram/bzgt_update.jsp?wellNo="+encodeURI(wellNo)+"&time="+encodeURI(time));
	//alert("web/echo/Ergogram/bzgt_update.jsp?wellNo="+encodeURI(wellNo)+"&time='"+time+"'");
	
}
function checkboxxz(){
	var num=0;
	var strWhere='';
	var str = "<%=request.getAttribute("ckv") %>";
	//alert(str);
  	for (var i=0;i<form1.elements.length;i++)
    {
	    var e = form1.elements[i];
	    if (e.type == 'checkbox' && e.checked)
	    {
	    	num++;
	    	strWhere +=e.value+';';
	    }
    }
   			// alert(strWhere);
    if(str!="null"){
    		strWhere = strWhere + str ;
	    	//alert(strWhere);
	}
	    	//window.returnValue=strWhere;
	    	 document.getElementById("ckv").value = strWhere;

}

</script>

											
		</form>
		<form name=form2 id=form2 action="page/dynamicdata/DetailErgogram.jsp" method="post" target="_self">
			<input type="hidden" name="wellNo">
			<input type="hidden" name="gotTime">
 		</form>
		<form name=form3 id=form3 action="page/dynamicdata/ContrastErgogramChart.jsp" method="post" target="_blank">
			<input type=hidden id=ergogramType name=ergogramType value="<%=ergogramType%>">
			<input type=hidden id=strWhere name=strWhere >
		</form>
	</body>
<script language="JavaScript" type="text/javascript">
function alignApplet()
{
	var num = <%=currentPageSize%>;
	var str="";
	var cols = Math.floor((document.body.offsetWidth -24 )/ <%=appletWidth+8%>);
	var rows = Math.floor((num -1)/ cols + 1);
//	alert(document.body.offsetWidth -40);
/*	alert(cols);
	if (cols <1)
	{
		cols = 1;
	}
	var k = 0;
	var rowSet = document.all.dataTable.rows;
	rows.length=rows;
	for(var i = 0; i<rows, k<num; i++)
	{
		var colSet = rowSet(i).cells;
		colSet.length = cols;
		
		for(var j = 0 ;j < cols;j++)
		{
			colSet(j).insertBefore(document.all.item("applet"+k));
			k++;
			if (k>=num) break;
		}
		
	}
*/	

	str="<table width=100% CELLSPACING=8>";
	for(var i = 0;i<num;i++)
	{
		if(i % cols == 0)
		{
			str = str+"<tr>";
		}
		str = str +"<td id = 'td" + i +"' name ='td" + i +"'></td>";
		if(i % cols == cols -1 || i==num-1)
		{
			str = str+"</tr>";
		}
	}
	str = str + "</table>";
	//alert(str);
	document.all.dataTable.innerHTML = str;
	//alert(document.all.dataTable.innerHTML);

/// *
	for(var i = 0;i<num;i++)
	{
		document.all.item("td"+i).insertBefore(document.all.item("applet"+i));
	}
//*/	
}
alignApplet();
//<!--
function detail(wellNo,time)
{
alert("wellNo"+wellNo+"time"+time);
	form2.wellNo.value=wellNo;
	form2.gotTime.value=time;
	//form2.submit();
	window.open('DetailErgogram.jsp?wellNo='+wellNo+'&time='+time,"TableFrm");	
}
//detail('<%=arrWellNo[0]%>','<%=arrGotTime[0]%>');
function contrast()
{
	var num=0;
	var strWhere='';
	var str = "<%=request.getAttribute("ckv") %>";
	//alert(str);
  	for (var i=0;i<form1.elements.length;i++)
    {
	    var e = form1.elements[i];
	    if (e.type == 'checkbox' && e.checked)
	    {
	    	num++;
	    	strWhere +=e.value+';';
	    }
    }
    if(str!="null"){
    	strWhere = strWhere + str;
    }
	    	//alert(strWhere);
	if(num==0)
	{
		alert("请先复选需要对比的功图下面的复选框。");
		return;
	}
	
	var strPara = "?strWhere=" + strWhere + "&ergogramType=" + "<%=java.net.URLEncoder.encode(ergogramType,"UTF-8")%>";
	var strURL = "<%=basePath%>page/dynamicdata/ContrastErgogramChart.jsp" + strPara;
	//alert(strURL);
	window.open(strURL,"_blank","height=550, width=750, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no"); 
	//form3.strWhere.value = strWhere;
	//form3.submit();
}
//-->

 function checkAll(name)
  {    
 
      var el = document.getElementsByTagName('input');   
      var len = el.length;     
      for(var i=0; i<len; i++)    
      {         
         if((el[i].type=="checkbox") && (el[i].name==name))      
            {          
               el[i].checked = true;         
             }   
               
        } 
    }
     
    function clearAll(name)
    {   
        var el = document.getElementsByTagName('input');    
        var len = el.length;    
        for(var i=0; i<len; i++)   
        {  
            if((el[i].type=="checkbox") && (el[i].name==name))      
             {  
                   el[i].checked = false;       
             }   
        } 
            
     }

</script>
</html>
