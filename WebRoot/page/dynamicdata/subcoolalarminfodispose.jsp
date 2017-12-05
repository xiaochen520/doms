<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title>SUBCOOL动态数据</title>
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
	<script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>
    <script src="../../lib/myday.js" type="text/javascript"></script> 
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
	<script type="text/javascript" src="subcoolalarminfodispose.js"></script>
	<style type="text/css">
		body{ font-size:12px;}
		.l-table-edit {}
		.l-table-edit-td{ padding:4px;}
		.l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
		.l-verify-tip{ left:230px; top:120px;}
		.l-table-edit-td div{ color:#6699FF;}
    </style>

  </head>
  
  <body>
  	<input type="text" id="sagdid" value="<%= request.getParameter("sagdid") %>" style="display: none" />
    	<table border="0" cellspacing="1">
    		<tr>
    			<td class="l-table-edit-td" colspan="10" style="font-size: 16;font-weight: bold">基础信息：</td>
    		</tr>
	    	<tr>
				<td align="left" class="l-table-edit-td" style="width:110px;display:none"><div id="sagdid"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">区块：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="areablock"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">采油站：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="oilstationname"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">注转站：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="blockstationname"></div></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">井号：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="wellnum"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">生产阶段：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="prodstages"></div></td>
			</tr>
			<tr>
    			<td class="l-table-edit-td" colspan="10" style="font-size: 16;font-weight: bold">监测信息：</td>
    		</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度1：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp1"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度2：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp2"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度3：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp3"></div></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度4：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp4"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度5：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp5"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度6：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp6"></div></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度7：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp7"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度8：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp8"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度9：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp9"></div></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度10：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp10"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度11：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp11"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">P井井下温度12：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_temp12"></div></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">I套管压力：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="i_drivepipe_press"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">I主管压力：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="i_pressure_press"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px"></td>
				<td align="left" class="l-table-edit-td" style="width:110px"></td>
			</tr>
			<tr>
    			<td class="l-table-edit-td" colspan="10" style="font-size: 16;font-weight: bold">注采平衡参数：</td>
    		</tr>
    		<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">注汽量：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="daily_cumulative_steam"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">产液量：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="p_daily_output"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px"></td>
				<td align="left" class="l-table-edit-td" style="width:110px"></td>
			</tr>
			<tr>
    			<td class="l-table-edit-td" colspan="10" style="font-size: 16;font-weight: bold">当前控制参数：</td>
    		</tr>
    		<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">注汽量：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="curflow"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">冲次：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="curjig"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">油嘴：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="curnip"></div></td>
			</tr>
			<tr>
    			<td class="l-table-edit-td" colspan="10" style="font-size: 16;font-weight: bold">建议控制参数：</td>
    		</tr>
    		<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">注汽量：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="sugflow"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">冲次：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="sugjig"></div></td>
				<td align="right" class="l-table-edit-td" style="width:100px">油嘴：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><div id="sugnip"></div></td>
			</tr>
			<tr>
    			<td class="l-table-edit-td" style="font-size: 16;font-weight: bold">人工调整参数：</td>
    			<td align="left" class="l-table-edit-td">
					<input type="radio" value="1" name="mode" />采纳建议参数
				</td>
				<td align="left" class="l-table-edit-td" colspan="4">
					<input type="radio" value="2" name="mode" />人工调整参数
				</td>
    		</tr>
    		<tr>
				<td align="right" class="l-table-edit-td" style="width:100px">注汽量：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><input type="text" id="modifyflow" name="modifyflow" style="width:80px" /></td>
				<td align="right" class="l-table-edit-td" style="width:100px">冲次：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><input type="text" id="modifyjig" name="modifyjig" style="width:80px" /></td>
				<td align="right" class="l-table-edit-td" style="width:100px">油嘴：</td>
				<td align="left" class="l-table-edit-td" style="width:110px"><input type="text" id="modifynip" name="modifynip" style="width:80px" /></td>
			</tr>
		</table>
  </body>
</html>