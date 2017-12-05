<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title>SUBCOOL动态历史数据</title>
	<meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<script src="../../lib/jquery/jquery-1.7.1.min.js" type="text/javascript"></script> 
    <script src="../../lib/highcharts/highcharts.js"></script>
<!-- 	<script src="../../lib/highcharts/exporting.js"></script> -->
	
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>
    <script src="../../lib/myday.js" type="text/javascript"></script> 
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
	<script type="text/javascript" src="subcoolalarminfo.js"></script>
	<style type="text/css">
		body{ font-size:12px;}
		.l-table-edit {}
		.l-table-edit-td{ padding:4px;}
		.l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
		.l-verify-tip{ left:230px; top:120px;}
    </style>
  </head>
  <body>
	<table border="0" cellspacing="1" style="margin-left: 10px;margin-top: 10px;">
		<tr>
			<td align="right" class="l-table-edit-td" style="width:80px">区块：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="areablock" name="areablock" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px">注转站：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="blockstationname" name="blockstationname" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px">井号：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="wellnum" name="wellnum" /></td>
			<td class="l-table-edit-td" style="width:120px;padding-left: 20px">
				<input type="checkbox" id="filter" class="l-checkbox" />
				<div style="margin-left:30px;margin-top:-16px;float: left;">非报警信息过滤</div>
			</td>
		</tr>
		<tr>	
			<td align="right" class="l-table-edit-td" style="width:80px">开始日期：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="start" ltype="date" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px">结束日期：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="end" ltype="date" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px"></td>
			<td align="left" class="l-table-edit-td" style="width:100px">
				<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询数据</a>
			</td>
			<td align="left" class="l-table-edit-td" style="width:120px">
				<a id="lineBtn" href="javascript:onLine()" class="l-button" style="width:100px">动态曲线</a>
			</td>
			<td align="left" class="l-table-edit-td" style="width:120px">
				<a href="javascript:onExport()" class="l-button" style="width:100px">数据导出</a>
			</td>
		</tr>
	</table>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div id="maingrid"></div>
	<div id="mainline" style="display: none;">
		<div id="main0" style="height:160px;width:1300px"></div>
<!-- 		<div id="main1" style="height:160px;width:1300px"></div> -->
		<div id="main2" style="height:160px;width:1300px"></div>
		<div id="main3" style="height:160px;width:1300px"></div>
	</div>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  </body>
  
</html>