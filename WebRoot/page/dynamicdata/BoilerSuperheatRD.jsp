<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>过热锅炉动态数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
   <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
             <script src="../../lib/highcharts/highcharts.js"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
   
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>    -->
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	
 	
 	 		<script type="text/javascript" src="BoilerSuperheatRD.js"></script>
 	
 	
    <script type="text/javascript">
   
		
		
		
		       
    </script>
    <style type="text/css"> 
    html,body
	{
	       margin: 0px;
			padding: 0px;
			background: #FAFCFC;
			position: absolute;
			width: 100%;
			height: 100%;
			hoverflow:scroll;
			overflow-y:hidden;
			overflow-x:hidden;
	   }
    </style>
</head>
<body style="overflow-x:hidden; padding:2px;">
 
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form"> 
        	<table border="0" cellspacing="1">
				<tr>
					<td align="right" class="l-table-edit-td" style="width:50px;display: none;">采油站：</td>
		                <td align="left" class="l-table-edit-td"  style="display: none;">
		                	<input type="text" id="oilationname" name = "oilationname" />
		                </td>
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:50px;">受汽区块：</td>
		                <td align="left" class="l-table-edit-td" style="width:50px;">
		                	<input type="text" id="areablock" name = "areablock"/>
		                </td>
		                <td align="left">
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">供热站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="blockstationname" name = "blockstationname"/>
		                </td>
		                <td align="left"></td>
		               
		                <td align="right" class="l-table-edit-td" style="width:50px">锅炉名称：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="boilersName" name = "boilersName"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px;display: none;">运行组：</td>
	               		 <td align="left" class="l-table-edit-td" style="width:80px;display: none;">
	                	<input type="text" id="group" name="group"/>
	               		 </td>
		                
		                <td align="right" class="l-table-edit-td" style="width:80px">受汽单位：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="acceptunit" name="acceptunit"/>
	                </td>
	                <td align="left">
		                </td>
	                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
					<td style="padding-left: 20px;"><a href="javascript:onLine()" class="l-button" style="width:80px">动态曲线</a></td>
					
					<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td>
				</tr>
			</table>
        </form>
    </div>
 <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<div id="maingrid"></div>
		<div id="mainline" style="display: none;">
		<div id="main0" style="height:120px;width:1100px"></div>
		<div id="main1" style="height:120px;width:1100px"></div>
		<div id="main2" style="height:120px;width:1100px"></div>
		<div id="main3" style="height:120px;width:1100px"></div>
	</div>	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
</body>
</html>