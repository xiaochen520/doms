<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>SAGD井日报数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
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
    
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
	<script type="text/javascript">
	
    	var glid = parent.glid;
    	var rbid = parent.rbid;
    	var glmc = parent.glmc;
	$(function () {
        
			// 获取日报相信信息 初始化数据
			 jQuery.ajax({
					type : 'post',
					url : 'srglrb_searchPTglview.action',
					data: {"glid":glid ,"rbid":rbid},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
							if(jsondata < 0){
								$.ligerDialog.error(outerror(jsondata));
								
							}else{
							//alert(jsondata);
								var data = eval('(' + jsondata + ')');
								//$("#BOILER_NAME").val(data.BOILER_NAME);
								 $("#BLOCK_NAME").val(data.BLOCK_NAME);
				   				 $("#BLOCKSTATION_NAME").val(data.BLOCKSTATION_NAME);
				       			 $("#BOILER_NAME").val(data.BOILER_NAME);
				       			 $("#REPORT_DATE").val(data.REPORT_DATE);
				       			 $("#GAS_INTO_PRESS").val(data.GAS_INTO_PRESS);
				       			 $("#GAS1_PRESS").val(data.GAS1_PRESS);
				       			 $("#FIRE_MEASURE").val(data.FIRE_MEASURE);
				       			 $("#GAS_FLOW").val(data.GAS_FLOW);
				       			 $("#PUMPIN_PRESS").val(data.PUMPIN_PRESS);

				       			$("#RUNTIME").val(data.RUNTIME);
				       			
				       			 $("#MPIN_PRESS").val(data.MPIN_PRESS);
				       			 $("#PUMPOUT_TEMP").val(data.PUMPOUT_TEMP);
				       			 $("#PUMPFC_FREQUENCY").val(data.PUMPFC_FREQUENCY);
				       			 $("#WATERSUPPLY_FLOW").val(data.WATERSUPPLY_FLOW);
				       			 $("#STEAMIN_TEMP").val(data.STEAMIN_TEMP);
				       			 $("#STEAMOUT_TEMP").val(data.STEAMOUT_TEMP);
				       			 $("#STEAM_DRYNESS").val(data.STEAM_DRYNESS);
				       			 $("#PUMPOUT_PRESS").val(data.PUMPOUT_PRESS);
				       			 $("#CSIN_PRESS").val(data.CSIN_PRESS);
				       			 $("#CSIN_TEMP").val(data.CSIN_TEMP);
				       			 $("#CSOUT_PRESS").val(data.CSOUT_PRESS);
				       			 $("#CSOUT_TEMP").val(data.CSOUT_TEMP);
				       			 $("#CONVECTIONR_PRESS_REDUCTION").val(data.CONVECTIONR_PRESS_REDUCTION);
				       			 $("#RSIN_PRESS").val(data.RSIN_PRESS);
				       			 $("#RSIN_TEMP").val(data.RSIN_TEMP);
				       			 $("#RS_PRESS_REDUCTION").val(data.RS_PRESS_REDUCTION);
				       			 $("#RS_TEMP").val(data.RS_TEMP);
				       			 $("#GAS2_PRESS").val(data.GAS2_PRESS);
				       			 $("#GAS3_PRESS").val(data.GAS3_PRESS);
				       			 $("#LUBE_PRESS").val(data.LUBE_PRESS);
				       			 $("#LUBE_TEMP").val(data.LUBE_TEMP);
				       			 $("#FAN_AIR_INTAKE_TEMP").val(data.FAN_AIR_INTAKE_TEMP);
				       			 $("#BURNER_TEMP").val(data.BURNER_TEMP);
				       			 $("#HEARTH_PRESS").val(data.HEARTH_PRESS);
				       			 $("#EJECTSMOKE_TEMP").val(data.EJECTSMOKE_TEMP);
				       			 $("#SYSTEM_VOLTAGE").val(data.SYSTEM_VOLTAGE);
				       			 $("#PUMP_MOTOR_CURRENT").val(data.PUMP_MOTOR_CURRENT);
				       			 $("#PUMP_MOTOR_TEMP").val(data.PUMP_MOTOR_TEMP);
				       			 $("#FAN_MOTOR_CURRENT").val(data.FAN_MOTOR_CURRENT);
				       			 $("#FUEL_GAS_DENSITY").val(data.FUEL_GAS_DENSITY);
				       			 $("#H2S_DENSITY").val(data.H2S_DENSITY);
				       			 $("#DAILY_CUMULATIVE_GAS").val(data.DAILY_CUMULATIVE_GAS);
				       			 $("#DAILY_CUMULATIVE_STEAM").val(data.DAILY_CUMULATIVE_STEAM);
				       			 $("#STEAM_WORK_GROUP").val(data.STEAM_WORK_GROUP);
				       			 $("#OILDRILLING_STATION_NAME").val(data.OILDRILLING_STATION_NAME);
				       			 $("#STEAM_INJE_UNIT").val(data.STEAM_INJE_UNIT);
							}
						}
					},
					error : function(data) {
				
					}
				});
		$("form").ligerForm();
	});
	</script>
<style type="text/css">
		html,body{
			font-size:12px;
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
		body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
        
            /* logo*/
		.searchtitle{ padding-left:28px; position:relative;}
		.searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
		.searchtitle span{ font-size:14px; font-weight:bold;}
		.searchtitle .togglebtn{ position:absolute; top:6px; right:15px; background:url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
		.searchtitle .togglebtn-down{ background-position:0px -10px;}
    </style>
</head>

<body style="overflow:scroll;overflow-x:hidden;overflow-y:scroll;width:100%;border-color: red;">
	<form name="form" method="post" id="form">
		<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr>
			<td align="right" class="l-table-edit-td" style="width:80px">区块：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BLOCK_NAME" name = "BLOCK_NAME" disabled="disabled"/>	
				</td>
				<!--  <td align="right" class="l-table-edit-td" style="width:80px">接转战名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BLOCKSTATION_NAME" name = "BLOCKSTATION_NAME" disabled="disabled"/>	
				</td>-->
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BOILER_NAME" name = "BOILER_NAME" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">日期：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="REPORT_DATE" name = "REPORT_DATE" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">运行时间：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RUNTIME" name = "RUNTIME" disabled="disabled"/>	
				</td>
				</tr>
				
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">站区来气压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS_INTO_PRESS" name = "GAS_INTO_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">天然气阀前压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS1_PRESS" name = "GAS1_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">火量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FIRE_MEASURE" name = "FIRE_MEASURE" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">天然气流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS_FLOW" name = "GAS_FLOW" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">给水压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPIN_PRESS" name = "PUMPIN_PRESS" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">给水温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPOUT_TEMP" name = "PUMPOUT_TEMP" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">柱塞泵变频频率：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPFC_FREQUENCY" name = "PUMPFC_FREQUENCY" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">给水流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="WATERSUPPLY_FLOW" name = "WATERSUPPLY_FLOW" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAMIN_TEMP" name = "STEAMIN_TEMP" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAMOUT_TEMP" name = "STEAMOUT_TEMP" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口干度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAM_DRYNESS" name = "STEAM_DRYNESS" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">柱塞泵出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPOUT_PRESS" name = "PUMPOUT_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段入口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSIN_PRESS" name = "CSIN_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段入口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSIN_TEMP" name = "CSIN_TEMP" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">对流段出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSOUT_PRESS" name = "CSOUT_PRESS" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSOUT_TEMP" name = "CSOUT_TEMP" disabled="disabled" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">对流段压降：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CONVECTIONR_PRESS_REDUCTION" name = "CONVECTIONR_PRESS_REDUCTION" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段入口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RSIN_PRESS" name = "RSIN_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段入口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RSIN_TEMP" name = "RSIN_TEMP" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段压降：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RS_PRESS_REDUCTION" name = "RS_PRESS_REDUCTION" disabled="disabled"/>	
				</td>
					</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段出口管温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RS_TEMP" name = "RS_TEMP" disabled="disabled"/>	
				</td>
			
				<td align="right" class="l-table-edit-td" style="width:80px">天然气阀后压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS2_PRESS" name = "GAS2_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">膨胀管压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS3_PRESS" name = "GAS3_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">润滑油压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="LUBE_PRESS" name = "LUBE_PRESS" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">润滑油温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="LUBE_TEMP" name = "LUBE_TEMP" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">鼓风机进风温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FAN_AIR_INTAKE_TEMP" name = "FAN_AIR_INTAKE_TEMP" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">燃烧器喉温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BURNER_TEMP" name = "BURNER_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">炉膛压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="HEARTH_PRESS" name = "HEARTH_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">排烟温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="EJECTSMOKE_TEMP" name = "EJECTSMOKE_TEMP" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">电网电压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SYSTEM_VOLTAGE" name = "SYSTEM_VOLTAGE" disabled="disabled"/>	
				</td>
					</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">柱塞泵电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMP_MOTOR_CURRENT" name = "PUMP_MOTOR_CURRENT" disabled="disabled"/>	
				</td>
			
				<td align="right" class="l-table-edit-td" style="width:80px">柱塞泵电机温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMP_MOTOR_TEMP" name = "PUMP_MOTOR_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">鼓风机电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FAN_MOTOR_CURRENT" name = "FAN_MOTOR_CURRENT" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉房可燃气体浓度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FUEL_GAS_DENSITY" name = "FUEL_GAS_DENSITY" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉房H2S气体浓度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="H2S_DENSITY" name = "H2S_DENSITY" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">天然气日累积量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_GAS" name = "DAILY_CUMULATIVE_GAS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">注汽日累积量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_STEAM" name = "DAILY_CUMULATIVE_STEAM" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">运行组：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAM_WORK_GROUP" name = "STEAM_WORK_GROUP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">受汽单位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="OILDRILLING_STATION_NAME" name = "OILDRILLING_STATION_NAME" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">注汽单位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAM_INJE_UNIT" name = "STEAM_INJE_UNIT" disabled="disabled"/>	
				</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="hidden" id="rbid" name = "rbid" disabled="disabled"/>	
				</td>
				</tr>
			</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		
    </form>
</body>
</html>