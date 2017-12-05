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
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/js/validator.js" type="text/javascript"></script>
	<script type="text/javascript" src="subcooldefaultparam.js"></script>
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
		<tr><td align="left" class="l-table-edit-td" colspan="5">Subcool计算公式：</td></tr>
		<tr><td align="left" class="l-table-edit-td" style="width:520px;font-size:16px;" colspan="5">Sub-cool=Power（操作压力，21/100） * 210 -30 -水平段最高温度</td></tr>
		<tr><td align="left" class="l-table-edit-td" colspan="5">操作压力计算公式（默认）：</td></tr>
		<tr>
			<td align="right" class="l-table-edit-td">
				<input type="radio" value="1" name="cal" />
			</td>
			<td align="left" class="l-table-edit-td" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;算法一：  I井套压</td>
		</tr>
		<tr>
			<td align="right" class="l-table-edit-td">
				<input type="radio" value="2" name="cal" />
			</td>
			<td align="left" class="l-table-edit-td" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;算法二： (I井注汽压力+I井套压)/2</td>
		</tr>
		<tr>
			<td align="right" class="l-table-edit-td">
				<input type="radio" value="3" name="cal" />
			</td>
			<td align="left" class="l-table-edit-td" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;算法三： I井注汽压力 - 1</td>
		</tr>
		<form method="post" id="form">
		<tr>
			<td align="left" class="l-table-edit-td" style="width:120px">正常Subcool值范围：</td>
			<td align="left" class="l-table-edit-td" style="width:60px">
				<input type="text" id="min_subcool" value="5" name="min_subcool" validate="{required:true, digits:true, max:15}" />
			</td>
			<td align="center" class="l-table-edit-td" style="width:20px">至</td>
			<td align="left" class="l-table-edit-td" style="width:60px">
				<input type="text" id="max_subcool" value="15" name="max_subcool" validate="{required:true, digits:true, compare:'#min_subcool'}" />
			</td>
			<td align="left" class="l-table-edit-td" style="width:120px">℃</td>
		</tr>
		</form>
		<tr>
			<td align="left" class="l-table-edit-td" style="width:120px">流量计方法：</td>
			<td align="left" class="l-table-edit-td" colspan="5">
				<input type="radio" value="1" name="flow" />&nbsp;&nbsp;流量计1&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" value="2" name="flow" />&nbsp;&nbsp;流量计2&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" value="3" name="flow" />&nbsp;&nbsp;取和
			</td>
		</tr>
		<tr>
			<td align="right" class="l-table-edit-td" colspan="3">
				<a href="javascript:onSubmit()" class="l-button" style="width:100px">保存</a>
			</td>
			<td align="right" class="l-table-edit-td" colspan="2">
				<a href="javascript:onExport()" class="l-button" style="width:100px">取消</a>
			</td>
		</tr>
   </table>
   
  </body>
</html>