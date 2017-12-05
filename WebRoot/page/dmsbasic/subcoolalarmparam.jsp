<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title>subCool默认参数设置</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>     
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	
	<script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>  
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../lib/js/validator.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
	<script type="text/javascript" src="subcoolalarmparam.js"></script>
	<style type="text/css">
		body{ font-size:12px;}
		.l-table-edit {}
		.l-table-edit-td{ padding:4px;}
		.l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
		.l-verify-tip{ left:230px; top:120px;}
    </style>

  </head>
  
  <body>
    <table border="0" cellspacing="1">
		<tr>
			<td align="right" class="l-table-edit-td" style="width:80px">区块：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="areablock" name="areablock" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px;display:none">采油站：</td>
			<td align="left" class="l-table-edit-td" style="width:80px;display:none"><input type="text" id="oilstationname" name="oilstationname" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px">注转站：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="blockstationname" name="blockstationname" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px">井号：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="wellnum" name="wellnum" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
		</tr>
	</table>
			
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div id="maingrid"></div>
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	
	
		<div id="mainsearch2" style=" width:99%">
			<div id="edituser" style="width:99%;height: 100px; display:none;">
				<div id="errorLabelContainer"></div>
				<table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
					<tr>
						<td align="left" class="l-table-edit-td" style="width:110px;display:none"><div id="calid"></div></td>
						<td align="right" class="l-table-edit-td" style="width:50px">区块：</td>
						<td align="left" class="l-table-edit-td" style="width:110px"><div id="area_block"></div></td>
						<td align="right" class="l-table-edit-td" style="width:50px;display:none">采油站：</td>
						<td align="left" class="l-table-edit-td" style="width:110px;display:none"><div id="oilstation_name"></div></td>
						<td align="right" class="l-table-edit-td" style="width:50px">注转站：</td>
						<td align="left" class="l-table-edit-td" style="width:110px"><div id="blockstation_name"></div></td>
						<td align="right" class="l-table-edit-td" style="width:50px">井号：</td>
						<td align="left" class="l-table-edit-td" style="width:110px"><div id="well_num"></div></td>
						<td align="right" class="l-table-edit-td" style="width:60px">生产阶段：</td>
						<td align="left" class="l-table-edit-td" style="width:100px"><div id="prod_stages"></div></td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td" style="width:200px;font-size:14px;" colspan="10">SubCool参数设置：</td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td" style="width:120px">操作压力取值：</td>
						<td align="left" class="l-table-edit-td" colspan="2">
							<input type="radio" value="1" name="cal" checked="checked" />算法一：  I井套压
						</td>
						<td align="left" class="l-table-edit-td" colspan="3">
							<input type="radio" value="2" name="cal" />算法二： (I井注汽压力+I井套压)/2
						</td>
						<td align="left" class="l-table-edit-td" colspan="4">
							<input type="radio" value="3" name="cal" />算法三： I井注汽压力 - <input type="text" id="subtract" name="subtract" style="width:30px" value="1" disabled="disabled" />
						</td>
					</tr>
					<form method="post" id="form">
					<tr>
						<td align="right" class="l-table-edit-td" style="width:120px"></td>
						<td align="right" class="l-table-edit-td">最小值：</td>
						<td align="left" class="l-table-edit-td" colspan="3">
							<input type="text" id="min_subcool" name="min_subcool" validate="{required:true, digits:true, max:15}"/>
						</td>
						<td align="right" class="l-table-edit-td">最大值：</td>
						<td align="left" class="l-table-edit-td" colspan="4">
							<input type="text" id="max_subcool" name="max_subcool" validate="{required:true, digits:true, compare:'#min_subcool'}"/>
						</td>
					</tr>
					</form>
					<tr>
						<td align="right" class="l-table-edit-td" style="width:120px">流量计方法：</td>
						<td align="left" class="l-table-edit-td" colspan="9">
							<input type="radio" value="1" name="flow" />&nbsp;&nbsp;流量计1&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" value="2" name="flow" />&nbsp;&nbsp;流量计2&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" value="3" name="flow" checked="checked" />&nbsp;&nbsp;取和
						</td>
					</tr>
				</table>
			</div>
		</div>
	
  </body>
</html>