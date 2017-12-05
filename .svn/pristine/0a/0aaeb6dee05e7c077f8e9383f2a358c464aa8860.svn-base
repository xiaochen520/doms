<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>湿蒸汽锅炉日报维护数据</title>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>    -->
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/sagd.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
	<script type="text/javascript">
	var rows = parent.grid.getCheckedRows();
	var RPD_BOILER_COMMON_ID;
	var BOILERID;
	 var BLOCK_NAME;
	 var BLOCKSTATION_NAME;
	 var BOILER_NAME;
	 var REPORT_DATE ;
	 var GAS_INTO_PRESS;
	 var GAS1_PRESS;
	 var FIRE_MEASURE;
	 var GAS_FLOW ;
	 var PUMPIN_PRESS;
	 var PUMPOUT_TEMP;
	 var PUMPFC_FREQUENCY;
	 var WATERSUPPLY_FLOW;
	 var STEAMIN_TEMP;
	 var STEAMOUT_TEMP;
	 var STEAM_DRYNESS;
	 var PUMPOUT_PRESS;
	 var CSIN_PRESS;
	 var CSIN_TEMP;
	 var CSOUT_PRESS;
	 var CSOUT_TEMP;
	 var CONVECTIONR_PRESS_REDUCTION;
	 var RSIN_PRESS;
	 var RSIN_TEMP;
	 var RS_PRESS_REDUCTION;
	 var RS_TEMP;
	 var GAS2_PRESS;
	 var GAS3_PRESS;
	 var LUBE_PRESS;
	 var LUBE_TEMP;
	 var FAN_AIR_INTAKE_TEMP;
	 var BURNER_TEMP;
	 var HEARTH_PRESS;
	 var EJECTSMOKE_TEMP;
	 var SYSTEM_VOLTAGE;
	 var PUMP_MOTOR_CURRENT;
	 var PUMP_MOTOR_TEMP;
	 var FAN_MOTOR_CURRENT;
	 var FUEL_GAS_DENSITY;
	 var H2S_DENSITY;
	 var DAILY_CUMULATIVE_GAS;
	 var DAILY_CUMULATIVE_STEAM;
	 var STEAM_WORK_GROUP;
	 var OILDRILLING_STATION_NAME;
	 var STEAM_INJE_UNIT;
	var sewageBufferTank;
	 var RUNTIME;

	 function  changValue(){
			var  watersupply_flow =Number($('#WATERSUPPLY_FLOW').val());	
			var  runtime =Number($('#RUNTIME').val());	
			//var  daily_cumulative_steam = Number($('#daily_cumulative_steam').val());	
		    if(runtime =='' || watersupply_flow == ''){
	        	$('#DAILY_CUMULATIVE_STEAM').val('');
			}else{
				Number($('#DAILY_CUMULATIVE_STEAM').val((runtime*watersupply_flow).toFixed(2)));
			}
	    	}

	    function checkData(obj){
	    	//先把非数字的都替换掉，除了数字和.
			obj.value = obj.value.replace(/[^\d.]/g,"");
			//必须保证第一个为数字而不是.
			obj.value = obj.value.replace(/^\./g,"");
			//保证只有出现一个.而没有多个.
			obj.value = obj.value.replace(/\.{2,}/g,".");
			//保证.只出现一次，而不能出现两次以上
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	    }
	    
	$(function () {
		var proData = [{ id: 1 , text: '' }];
		$("#BLOCK_NAME").ligerComboBox({//区块
			url:'boilerBasicInfo_searchAcceptunit.action?orgid=boilerbasicinfo',
			valueField: 'id',
			valueFieldID: 'grzh2id',
			textField: 'text',
			selectBoxHeight:120,
			selectBoxWidth:140,
			hideOnLoseFocus:true,
            autocomplete:true,
            alwayShowInTop:false,
			onSelected:function (data){
				//alert(data+"data");
			}
		});
		$("#BLOCKSTATION_NAME").ligerComboBox({//接转战
			url:'srglrd_queryStation.action',
			valueField: 'id',
			valueFieldID: 'stationid',
			textField: 'text',
			selectBoxHeight:350,
			width: 160,
			hideOnLoseFocus:true,
            autocomplete:true,
            alwayShowInTop:false,
            onBeforeSelect: function (newvalue){
			liger.get("BOILER_NAME").setValue('');
		},
        onSelected:function (data){
			if (data != null && typeof(data)!="undefined" && data != ''){
				setBoilerData($("#stationid").val(),proData);
			}
		}
		});
	
		$("#BOILER_NAME").ligerComboBox({//锅炉
			//url:'srglrd_queryBoiler.action',
			url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
			valueField: 'id',
			valueFieldID: 'boilersid',
			textField: 'text',
			selectBoxHeight:350,
			selectBoxWidth:100,
			hideOnLoseFocus:true,
            autocomplete:true
		});
		$("#STEAM_WORK_GROUP").ligerComboBox({//运行组
			url:'boilerBasicInfo_searchGroupInfo.action',
			valueField: 'id',
			valueFieldID: 'yxzid',
			textField: 'text',
			selectBoxHeight:100,
			selectBoxWidth:100,
			hideOnLoseFocus:true,
            autocomplete:true
		});
		$("#OILDRILLING_STATION_NAME").ligerComboBox({//采油站
			//url:'srglrd_queryoilding.action',
			url:'station_queryAreablockInfo.action?orgid=sqdw',
			valueField: 'id',
			valueFieldID: 'oilid',
			textField: 'text',
			selectBoxHeight:240,
			selectBoxWidth:100,
			hideOnLoseFocus:true,
            autocomplete:true
		});
	    function setBoilerData(data,proData) {
			jQuery.ajax({
				type : 'post',
				//url:'boilerBasicInfo_queryBoilersNameInfo.action',
				url:'srglrd_queryBoilersNameInfo.action',
				data: {"orgid":data},
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("BOILER_NAME").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("BOILER_NAME").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}
			
		  $("#REPORT_DATE").ligerDateEditor(
                  {

                      format: "yyyy-MM-dd",
                    //  label: '格式化日期',
                      labelWidth: 100,
                      labelAlign: 'center',
                      cancelable : false
              });
		   $.metadata.setType("attr", "validate");
           var v = $("form").validate({
               //调试状态，不会提交数据的
               debug: true,
               errorPlacement: function (lable, element)
               {

                   if (element.hasClass("l-textarea"))
                   {
                       element.addClass("l-textarea-invalid");
                   }
                   else if (element.hasClass("l-text-field"))
                   {
                       element.parent().addClass("l-text-invalid");
                   }
                   $(element).removeAttr("title").ligerHideTip();
                   $(element).attr("title", lable.html()).ligerTip();
               },
               success: function (lable)
               {
               	if(lable.attr("for")!=""){
               		var element = $("#" + lable.attr("for"));
	                    if (element.hasClass("l-textarea"))
	                    {
	                        element.removeClass("l-textarea-invalid");
	                    }
	                    else if (element.hasClass("l-text-field"))
	                    {
	                        element.parent().removeClass("l-text-invalid");
	                    }
	                    $(element).removeAttr("title").ligerHideTip();
               	}
               		
                   
               },
         
            submitHandler: function ()
            {
            var RPD_BOILER_COMMON_ID =$("#RPD_BOILER_COMMON_ID").val();
           	 var BLOCK_NAME = $("#BLOCK_NAME").val();
        	 var BLOCKSTATION_NAME = $("#BLOCKSTATION_NAME").val();
        	 var BOILER_NAME = $("#BOILER_NAME").val();
        	 var BOILERID = $("#boilersid").val();	//	锅炉id
        	 
        	 var REPORT_DATE  = $("#REPORT_DATE").val();
        	 var RUNTIME = $("#RUNTIME").val();
        	 var GAS_INTO_PRESS = $("#GAS_INTO_PRESS").val();
        	 var GAS1_PRESS = $("#GAS1_PRESS").val();
        	 var FIRE_MEASURE = $("#FIRE_MEASURE").val();
        	 var GAS_FLOW = $("#GAS_FLOW").val() ;
        	 var PUMPIN_PRESS = $("#PUMPIN_PRESS").val();
        	 var PUMPOUT_TEMP = $("#PUMPOUT_TEMP").val();
        	 var PUMPFC_FREQUENCY = $("#PUMPFC_FREQUENCY").val();
        	 var WATERSUPPLY_FLOW = $("#WATERSUPPLY_FLOW").val();
        	 var STEAMIN_TEMP = $("#STEAMIN_TEMP").val();
        	 var STEAMOUT_TEMP = $("#STEAMOUT_TEMP").val();
        	 var STEAM_DRYNESS = $("#STEAM_DRYNESS").val();
        	 var PUMPOUT_PRESS = $("#PUMPOUT_PRESS").val();
        	 var CSIN_PRESS = $("#CSIN_PRESS").val();
        	 var CSIN_TEMP = $("#CSIN_TEMP").val();
        	 var CSOUT_PRESS = $("#CSOUT_PRESS").val();
        	 var CSOUT_TEMP = $("#CSOUT_TEMP").val();
        	 var CONVECTIONR_PRESS_REDUCTION = $("#CONVECTIONR_PRESS_REDUCTION").val();
        	 var RSIN_PRESS = $("#RSIN_PRESS").val();
        	 var RSIN_TEMP = $("#RSIN_TEMP").val();
        	 var RS_PRESS_REDUCTION = $("#RS_PRESS_REDUCTION").val();
        	 var RS_TEMP = $("#RS_TEMP").val();
        	 var GAS2_PRESS = $("#GAS2_PRESS").val();
        	 var GAS3_PRESS = $("#GAS3_PRESS").val();
        	 var LUBE_PRESS = $("#LUBE_PRESS").val();
        	 var LUBE_TEMP = $("#LUBE_TEMP").val();
        	 var FAN_AIR_INTAKE_TEMP = $("#FAN_AIR_INTAKE_TEMP").val();
        	 var BURNER_TEMP = $("#BURNER_TEMP").val();
        	 var HEARTH_PRESS = $("#HEARTH_PRESS").val();
        	 var EJECTSMOKE_TEMP = $("#EJECTSMOKE_TEMP").val();
        	 var SYSTEM_VOLTAGE = $("#SYSTEM_VOLTAGE").val();
        	 var PUMP_MOTOR_CURRENT = $("#PUMP_MOTOR_CURRENT").val();
        	 var PUMP_MOTOR_TEMP = $("#PUMP_MOTOR_TEMP").val();
        	 var FAN_MOTOR_CURRENT = $("#FAN_MOTOR_CURRENT").val();
        	 var FUEL_GAS_DENSITY = $("#FUEL_GAS_DENSITY").val();
        	 var H2S_DENSITY = $("#H2S_DENSITY").val();
        	 var DAILY_CUMULATIVE_GAS = $("#DAILY_CUMULATIVE_GAS").val();
        	 var DAILY_CUMULATIVE_STEAM = $("#DAILY_CUMULATIVE_STEAM").val();
        	 var STEAM_WORK_GROUP = $("#STEAM_WORK_GROUP").val();
        	 var OILDRILLING_STATION_NAME = $("#OILDRILLING_STATION_NAME").val();
        	 var STEAM_INJE_UNIT = $("#STEAM_INJE_UNIT").val();
			 var   REMARK = $("#REMARK").val();
			 var sewageBufferTank = $("#sewageBufferTank").val();
        	
            	$("form .l-text,.l-textarea").ligerHideTip();
				jQuery.ajax({
					type : 'post',
					url : 'srglrbwh_SaveOrUpadateBoilerCommondRPDWH.action',
					data: {
						"BOILERID":BOILERID,
						"BLOCK_NAME" : BLOCK_NAME,
						"BLOCKSTATION_NAME" :BLOCKSTATION_NAME,
						"BOILER_NAME" :BOILER_NAME,
						"REPORT_DATE" :REPORT_DATE,
						"RUNTIME" :RUNTIME,
						"GAS_INTO_PRESS" :GAS_INTO_PRESS,
						"GAS1_PRESS" :GAS1_PRESS,
						"FIRE_MEASURE" :FIRE_MEASURE,
						"GAS_FLOW" :GAS_FLOW,
						"PUMPIN_PRESS" :PUMPIN_PRESS,
						"PUMPOUT_TEMP" :PUMPOUT_TEMP,
						"PUMPFC_FREQUENCY":PUMPFC_FREQUENCY,
						"WATERSUPPLY_FLOW" :WATERSUPPLY_FLOW,
						"STEAMIN_TEMP" :STEAMIN_TEMP,
						"STEAMOUT_TEMP" :STEAMOUT_TEMP,
						"STEAM_DRYNESS": STEAM_DRYNESS,
						"PUMPOUT_PRESS" :PUMPOUT_PRESS,
						"CSIN_PRESS" :CSIN_PRESS,
						"CSIN_TEMP" :CSIN_TEMP,
						"CSOUT_PRESS" :CSOUT_PRESS,
						"CSOUT_TEMP" :CSOUT_TEMP,
						"CONVECTIONR_PRESS_REDUCTION" :CONVECTIONR_PRESS_REDUCTION,
						"RSIN_PRESS" :RSIN_PRESS,
						"RSIN_TEMP" :RSIN_TEMP,
						"RS_PRESS_REDUCTION" :RS_PRESS_REDUCTION,
						"RS_TEMP" :RS_TEMP,
						"GAS2_PRESS" :GAS2_PRESS,
						"GAS3_PRESS" :GAS3_PRESS,
						"LUBE_PRESS" :LUBE_PRESS,
						"LUBE_TEMP" :LUBE_TEMP,
						"FAN_AIR_INTAKE_TEMP" :FAN_AIR_INTAKE_TEMP,
						"BURNER_TEMP" :BURNER_TEMP,
						"HEARTH_PRESS" :HEARTH_PRESS,
						"EJECTSMOKE_TEMP" :EJECTSMOKE_TEMP,
						"SYSTEM_VOLTAGE" :SYSTEM_VOLTAGE,
						"PUMP_MOTOR_CURRENT" :PUMP_MOTOR_CURRENT,
						"PUMP_MOTOR_TEMP" :PUMP_MOTOR_TEMP,
						"FAN_MOTOR_CURRENT" :FAN_MOTOR_CURRENT,
						"FUEL_GAS_DENSITY" :FUEL_GAS_DENSITY,
						"H2S_DENSITY" :H2S_DENSITY,
						"DAILY_CUMULATIVE_GAS" :DAILY_CUMULATIVE_GAS,
						"DAILY_CUMULATIVE_STEAM" :DAILY_CUMULATIVE_STEAM,
						"STEAM_WORK_GROUP" :STEAM_WORK_GROUP,
						"OILDRILLING_STATION_NAME" :OILDRILLING_STATION_NAME,
						"STEAM_INJE_UNIT" :STEAM_INJE_UNIT,
						"REMARK":REMARK,
						"sewageBufferTank":sewageBufferTank
		            	},
					async : false,
					timeout : '3000',
					success : function(data) { 
		            		if (data != null && typeof(data)!="undefined"){
								var outData = eval('(' + data + ')');
								
								if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
								}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
									$.ligerDialog.error(outerror(outData.ERRCODE));
								}else{
									parent.$.ligerDialog.close();
									//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
									parent.window.onSubmit();
									parent.$.ligerDialog.success("保存成功！");
								}
		            		}
							},
					error : function(data) {
				
					}
				});
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
					<input type="text" id="BLOCK_NAME" name = "BLOCK_NAME" />	
				</td>
				<!-- <td align="right" class="l-table-edit-td" style="width:80px">接转战名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BLOCKSTATION_NAME" name = "BLOCKSTATION_NAME"  />	
				</td> -->
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BOILER_NAME" name = "BOILER_NAME" validate="{required:true}" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">日期：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="REPORT_DATE" name = "REPORT_DATE" validate="{required:true,minlength:1,maxlength:20}" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">运行时间：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RUNTIME" name = "RUNTIME"  onblur="changValue()" onkeyup="checkData(this)" ltype="text" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">天然气分离器压差：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS_INTO_PRESS" name = "GAS_INTO_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">天然气阀前压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS1_PRESS" name = "GAS1_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">火量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FIRE_MEASURE" name = "FIRE_MEASURE" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">天然气流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS_FLOW" name = "GAS_FLOW" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">给水压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPIN_PRESS" name = "PUMPIN_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">给水温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPOUT_TEMP" name = "PUMPOUT_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">柱塞泵变频频率：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPFC_FREQUENCY" name = "PUMPFC_FREQUENCY" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">给水流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="WATERSUPPLY_FLOW" name = "WATERSUPPLY_FLOW"     onblur="changValue()" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAMIN_TEMP" name = "STEAMIN_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAMOUT_TEMP" name = "STEAMOUT_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口干度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAM_DRYNESS" name = "STEAM_DRYNESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">柱塞泵出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPOUT_PRESS" name = "PUMPOUT_PRESS" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段入口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSIN_PRESS" name = "CSIN_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段入口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSIN_TEMP" name = "CSIN_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">对流段出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSOUT_PRESS" name = "CSOUT_PRESS" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">对流段出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CSOUT_TEMP" name = "CSOUT_TEMP"  onkeyup="checkData(this)" ltype="text"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段压降：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CONVECTIONR_PRESS_REDUCTION" name = "CONVECTIONR_PRESS_REDUCTION" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段入口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RSIN_PRESS" name = "RSIN_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段入口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RSIN_TEMP" name = "RSIN_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段压降：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RS_PRESS_REDUCTION" name = "RS_PRESS_REDUCTION" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段出口管温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RS_TEMP" name = "RS_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">天然气阀后压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS2_PRESS" name = "GAS2_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">膨胀管压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="GAS3_PRESS" name = "GAS3_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">润滑油压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="LUBE_PRESS" name = "LUBE_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">润滑油温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="LUBE_TEMP" name = "LUBE_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">鼓风机进风温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FAN_AIR_INTAKE_TEMP" name = "FAN_AIR_INTAKE_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">燃烧器喉温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BURNER_TEMP" name = "BURNER_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">炉膛压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="HEARTH_PRESS" name = "HEARTH_PRESS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">排烟温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="EJECTSMOKE_TEMP" name = "EJECTSMOKE_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">电网电压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="SYSTEM_VOLTAGE" name = "SYSTEM_VOLTAGE" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">柱塞泵电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMP_MOTOR_CURRENT" name = "PUMP_MOTOR_CURRENT" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">柱塞泵电机温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMP_MOTOR_TEMP" name = "PUMP_MOTOR_TEMP" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">鼓风机电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FAN_MOTOR_CURRENT" name = "FAN_MOTOR_CURRENT" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉房可燃气体浓度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="FUEL_GAS_DENSITY" name = "FUEL_GAS_DENSITY" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉房H2S气体浓度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="H2S_DENSITY" name = "H2S_DENSITY" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">污水缓冲罐液位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="sewageBufferTank" name = "sewageBufferTank" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">天然气日累积量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_GAS" name = "DAILY_CUMULATIVE_GAS" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">注汽日累积量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_STEAM" name = "DAILY_CUMULATIVE_STEAM"  disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">运行组：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAM_WORK_GROUP" name = "STEAM_WORK_GROUP" ltype="text"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">受汽单位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="OILDRILLING_STATION_NAME" name = "OILDRILLING_STATION_NAME" validate="{required:true}"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">注汽单位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STEAM_INJE_UNIT" name = "STEAM_INJE_UNIT" />	
				</td>
				
				</tr>
				<tr>
				<td align="right"  style="width:80px">备注：</td>
				<td align="left"  colspan="10">
					 <textarea rows="2" cols="600" id="REMARK" name = "REMARK" ></textarea>
				</td>
				</tr>
			</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<center><div style="width:80px;text-align:center"><input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /></div></center>
    </form>
    <div style="display:none"> </div>
</body>
</html>