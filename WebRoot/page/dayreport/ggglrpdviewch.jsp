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
					url : 'blilercdrpd_searchGGglview.action',
					data: {"glid":glid ,"rbid":rbid},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
							if(jsondata < 0){
								$.ligerDialog.error(outerror(jsondata));
								
							}else{
							//alert(jsondata);
								var data = eval('(' + jsondata + ')');
								$("#BOILER_NAME").val(data.BOILER_NAME);
								$("#CJSJS").val(data.CJSJS);
								$("#GLBH").val(data.GLBH);
								$("#GRZH").val(data.GRZH);
								$("#OILDRILLING_STATION_NAME").val(data.OILDRILLING_STATION_NAME);
								$("#BLOCKSTATION_NAME").val(data.BLOCKSTATION_NAME);
								$("#YXZ").val(data.YXZ);
								$("#AREABLOCK").val(data.AREABLOCK);
								
								$("#STEAMOUT_TEMP").val(data.STEAMOUT_TEMP);   //蒸汽出口温度
								$("#STEAMIN_TEMP").val(data.STEAMIN_TEMP);   //蒸汽出口压力
								$("#EJECTSMOKE_TEMP").val(data.EJECTSMOKE_TEMP);   //排烟温度
								$("#BURNER_TEMP").val(data.BURNER_TEMP);   //燃烧器温度
								$("#CSIN_PRESS").val(data.CSIN_PRESS);   //对流段进口压力
								$("#CSOUT_PRESS").val(data.CSOUT_PRESS);   //对流段出口压力
								$("#CSIN_TEMP").val(data.CSIN_TEMP);   //对流段进口温度
								$("#CSOUT_TEMP").val(data.CSOUT_TEMP);   //对流段出口温度
								$("#SL_LEVEL").val(data.SL_LEVEL);   //分离器液位
								$("#SUPERHEAT_TEMP").val(data.SUPERHEAT_TEMP);   //过热段管温
								$("#SUPERHEAT_PIEZORESISTANCE").val(data.SUPERHEAT_PIEZORESISTANCE);   //过热段压阻
								$("#SUPERHEATIN_TEMP").val(data.SUPERHEATIN_TEMP);   //过热入口温度
								$("#SUPERHEATOUT_TEMP").val(data.SUPERHEATOUT_TEMP);   //过热出口温度
								$("#SUPERHEATIN_PRESS").val(data.SUPERHEATIN_PRESS);   //过热入口压力
								$("#SUPERHEATOUT_PRESS").val(data.SUPERHEATOUT_PRESS);   //过热出口压力
								$("#SUPERHEATIN_FLOW").val(data.SUPERHEATIN_FLOW);   //过热入口流量
								$("#HEARTH_PRESS").val(data.HEARTH_PRESS);   //炉膛压力
								$("#GAS1_PRESS").val(data.GAS1_PRESS);   //燃气一级压力
								$("#GAS2_PRESS").val(data.GAS2_PRESS);   //燃气二级压力
								$("#GAS3_PRESS").val(data.GAS3_PRESS);   //燃气三级压力
								$("#RS_TEMP").val(data.RS_TEMP);   //辐射段管温
								$("#RS_DRYNESS").val(data.RS_DRYNESS);   //辐射段干度
								$("#RS_PIEZORESISTANCE").val(data.RS_PIEZORESISTANCE);   //辐射段压阻
								$("#RSIN_PRESS").val(data.RSIN_PRESS);   //辐射段进口压力
								$("#RS_PRESS").val(data.RS_PRESS);   //辐射段出口压力
								$("#RSIN_TEMP").val(data.RSIN_TEMP);   //辐射段进口温度
								$("#RSOUT_TEMP").val(data.RSOUT_TEMP);   //辐射段出口温度
								$("#PUMPA_FLOW").val(data.PUMPA_FLOW);   //泵A相流量
								$("#PUMPB_FLOW").val(data.PUMPB_FLOW);   //泵B相流量
								$("#PUMPC_FLOW").val(data.PUMPC_FLOW);   //泵C相流量
								$("#PUMPA_PRESS").val(data.PUMPA_PRESS);   //泵AC电压
								$("#PUMPB_PRESS").val(data.PUMPB_PRESS);   //泵AB电压
								$("#PUMPC_PRESS").val(data.PUMPC_PRESS);   //泵BC电压
								$("#FANA_ELECTRICITY").val(data.FANA_ELECTRICITY);   //风机A相电流
								$("#FANB_ELECTRICITY").val(data.FANB_ELECTRICITY);   //风机B相电流
								$("#FANC_ELECTRICITY").val(data.FANC_ELECTRICITY);   //风机C相电流
								$("#PUMPFC_FREQUENCY").val(data.PUMPFC_FREQUENCY);   //泵变频频率
								$("#PUMPIN_PRESS").val(data.PUMPIN_PRESS);   //泵进口压力
								$("#PUMPOUT_PRESS").val(data.PUMPOUT_PRESS);   //泵出口压力
								$("#PUMPIN_TEMP").val(data.PUMPIN_TEMP);   //泵进口温度
								$("#PUMPOUT_TEMP").val(data.PUMPOUT_TEMP);   //泵出口温度
								$("#WATERSUPPLY_FLOW").val(data.WATERSUPPLY_FLOW);   //给水流量
								$("#WATERSUPPLY_TOTAL").val(data.WATERSUPPLY_TOTAL);   //给水量累计
								$("#GAS_FLOW").val(data.GAS_FLOW);   //燃气流量
								$("#GAS_TOTAL").val(data.GAS_TOTAL);   //燃气量累计
								$("#STEAMINJECTION_TOTAL").val(data.STEAMINJECTION_TOTAL);   //注汽量累计
								$("#DAILY_CUMULATIVE_WATER").val(data.DAILY_CUMULATIVE_WATER);   //给水日累计量
								$("#DAILY_CUMULATIVE_GAS").val(data.DAILY_CUMULATIVE_GAS);   //燃气日累计
								$("#DAILY_CUMULATIVE_STEAM").val(data.DAILY_CUMULATIVE_STEAM);   //注汽日累计
								
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
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BOILER_NAME" name = "BOILER_NAME" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">日期：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CJSJS" name = "CJSJS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉编码：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GLBH" name = "GLBH" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">注转站：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GRZH" name = "GRZH" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">采油站：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="OILDRILLING_STATION_NAME" name = "OILDRILLING_STATION_NAME" disabled="disabled"/>	
				</td>
				
				
				
				</tr>
				
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">联合站：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BLOCKSTATION_NAME" name = "BLOCKSTATION_NAME" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">运行组：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="YXZ" name = "YXZ" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">所属区块：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="AREABLOCK" name = "AREABLOCK" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAMOUT_TEMP" name = "STEAMOUT_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAMIN_TEMP" name = "STEAMIN_TEMP" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">排烟温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="EJECTSMOKE_TEMP" name = "EJECTSMOKE_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">燃烧器温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BURNER_TEMP" name = "BURNER_TEMP" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">对流段进口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSIN_PRESS" name = "CSIN_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSOUT_PRESS" name = "CSOUT_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段进口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSIN_TEMP" name = "CSIN_TEMP" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSOUT_TEMP" name = "CSOUT_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">分离器液位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SL_LEVEL" name = "SL_LEVEL" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">过热段管温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SUPERHEAT_TEMP" name = "SUPERHEAT_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热段压阻：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SUPERHEAT_PIEZORESISTANCE" name = "SUPERHEAT_PIEZORESISTANCE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热入口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SUPERHEATIN_TEMP" name = "SUPERHEATIN_TEMP" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">过热出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SUPERHEATOUT_TEMP" name = "SUPERHEATOUT_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热入口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SUPERHEATIN_PRESS" name = "SUPERHEATIN_PRESS" disabled="disabled" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">过热出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SUPERHEATOUT_PRESS" name = "SUPERHEATOUT_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热入口流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SUPERHEATIN_FLOW" name = "SUPERHEATIN_FLOW" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">炉膛压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="HEARTH_PRESS" name = "HEARTH_PRESS" disabled="disabled"/>	
				</td>
					</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">燃气一级压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS1_PRESS" name = "GAS1_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">燃气二级压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS2_PRESS" name = "GAS2_PRESS" disabled="disabled"/>	
				</td>
			
				<td align="right" class="l-table-edit-td" style="width:80px">燃气三级压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS3_PRESS" name = "GAS3_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段管温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RS_TEMP" name = "RS_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段干度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RS_DRYNESS" name = "RS_DRYNESS" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段压阻：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RS_PIEZORESISTANCE" name = "RS_PIEZORESISTANCE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段进口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RSIN_PRESS" name = "RSIN_PRESS" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RS_PRESS" name = "RS_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段进口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RSIN_TEMP" name = "RSIN_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RSOUT_TEMP" name = "RSOUT_TEMP" disabled="disabled"/>	
				</td>
					</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">泵A相流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPA_FLOW" name = "PUMPA_FLOW" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">泵B相流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPB_FLOW" name = "PUMPB_FLOW" disabled="disabled"/>	
				</td>
			
				<td align="right" class="l-table-edit-td" style="width:80px">泵C相流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPC_FLOW" name = "PUMPC_FLOW" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">泵AC电压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPA_PRESS" name = "PUMPA_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">泵AB电压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPB_PRESS" name = "PUMPB_PRESS" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">泵BC电压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPC_PRESS" name = "PUMPC_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">风机A相电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FANA_ELECTRICITY" name = "FANA_ELECTRICITY" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">风机B相电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FANB_ELECTRICITY" name = "FANB_ELECTRICITY" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">风机C相电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FANC_ELECTRICITY" name = "FANC_ELECTRICITY" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">泵变频频率：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPFC_FREQUENCY" name = "PUMPFC_FREQUENCY" disabled="disabled"/>	
				</td>
				</tr>
				
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">泵进口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPIN_PRESS" name = "PUMPIN_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">泵出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPOUT_PRESS" name = "PUMPOUT_PRESS" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">泵进口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPIN_TEMP" name = "PUMPIN_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">泵出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPOUT_TEMP" name = "PUMPOUT_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">给水流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="WATERSUPPLY_FLOW" name = "WATERSUPPLY_FLOW" disabled="disabled"/>	
				</td>
				</tr>
				
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">给水量累计：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="WATERSUPPLY_TOTAL" name = "WATERSUPPLY_TOTAL" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">燃气流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS_FLOW" name = "GAS_FLOW" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">燃气量累计：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS_TOTAL" name = "GAS_TOTAL" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">注汽量累计：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAMINJECTION_TOTAL" name = "STEAMINJECTION_TOTAL" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">给水日累计量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_WATER" name = "DAILY_CUMULATIVE_WATER" disabled="disabled"/>	
				</td>
				</tr>
				
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">燃气日累计：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_GAS" name = "DAILY_CUMULATIVE_GAS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">注汽日累计：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_STEAM" name = "DAILY_CUMULATIVE_STEAM" disabled="disabled"/>	
				</td>
				
				</tr>
			</table>
			<br/>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		
    </form>
</body>
</html>