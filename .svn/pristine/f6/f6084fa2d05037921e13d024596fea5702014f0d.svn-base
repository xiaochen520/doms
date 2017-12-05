<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>高干度锅炉日报数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
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
	var optit = '${param.optit}';
	
	function  changValue(){
		var  watersupply_flow =Number($('#watersupply_flow').val());	
		var  runtime =Number($('#runtime').val());	
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
        	if (optit!='') {
        		$("#optit").text("修改高干度锅炉日报数据");
        		var manager = parent.$("#maingrid").ligerGetGridManager();
            	var rows = manager.getCheckedRows();
            	$("#block_name").val(rows[0].BLOCK_NAME);
				$("#blockstation_name").val(rows[0].BLOCKSTATION_NAME);
				$("#boiler_name").val(rows[0].BOILER_NAME);
				$("#reportDate").val(rows[0].REPORT_DATE);
				$("#gas_into_press").val(rows[0].GAS_INTO_PRESS);
				$("#gas1_press").val(rows[0].GAS1_PRESS);
				$("#fire_measure").val(rows[0].FIRE_MEASURE);
				$("#gas_flow").val(rows[0].GAS_FLOW);
				$("#pumpin_press").val(rows[0].PUMPIN_PRESS);
				$("#pumpout_temp").val(rows[0].PUMPOUT_TEMP);
				$("#pumpfc_frequency").val(rows[0].PUMPFC_FREQUENCY);
				$("#watersupply_flow").val(rows[0].WATERSUPPLY_FLOW);
				$("#steamin_temp").val(rows[0].STEAMIN_TEMP);
				$("#steamout_temp").val(rows[0].STEAMOUT_TEMP);
				$("#steam_dryness").val(rows[0].STEAM_DRYNESS);
				$("#rs_press_reduction").val(rows[0].RS_PRESS_REDUCTION);
				$("#superheat_press_reduction").val(rows[0].SUPERHEAT_PRESS_REDUCTION);
				$("#rerheat_press_reduction").val(rows[0].RERHEAT_PRESS_REDUCTION);
				$("#mixer_press_reduction").val(rows[0].MIXER_PRESS_REDUCTION);
				$("#pumpout_press").val(rows[0].PUMPOUT_PRESS);
				$("#csin_press").val(rows[0].CSIN_PRESS);
				$("#csin_temp").val(rows[0].CSIN_TEMP);
				$("#csout_press").val(rows[0].CSOUT_PRESS);
				$("#csout_temp").val(rows[0].CSOUT_TEMP);
				$("#convectionr_press_reduction").val(rows[0].CONVECTIONR_PRESS_REDUCTION);
				$("#rsin_press").val(rows[0].RSIN_PRESS);
				$("#rsin_temp").val(rows[0].RSIN_TEMP);
				$("#rs_press").val(rows[0].RS_PRESS);
				$("#rsout_temp").val(rows[0].RSOUT_TEMP);
				$("#rs_temp").val(rows[0].RS_TEMP);
				$("#reheat_press_entrance").val(rows[0].REHEAT_PRESS_ENTRANCE);
				$("#reheat_temp_entrance").val(rows[0].REHEAT_TEMP_ENTRANCE);
				$("#reheat_temp_export").val(rows[0].REHEAT_TEMP_EXPORT);
				$("#gas2_press").val(rows[0].GAS2_PRESS);
				$("#gas3_press").val(rows[0].GAS3_PRESS);
				$("#lube_temp").val(rows[0].LUBE_TEMP);
				$("#burner_temp").val(rows[0].BURNER_TEMP);
				$("#hearth_press").val(rows[0].HEARTH_PRESS);
				$("#ejectsmoke_temp").val(rows[0].EJECTSMOKE_TEMP);
				$("#system_voltage").val(rows[0].SYSTEM_VOLTAGE);
				$("#pump_motor_current").val(rows[0].PUMP_MOTOR_CURRENT);
				$("#pump_motor_temp").val(rows[0].PUMP_MOTOR_TEMP);
				$("#fan_motor_current").val(rows[0].FAN_MOTOR_CURRENT);
				$("#steam_work_group").val(rows[0].STEAM_WORK_GROUP);
				$("#oildrilling_station_name").val(rows[0].OILDRILLING_STATION_NAME);
				$("#steam_inje_unit").val(rows[0].STEAM_INJE_UNIT);
				$("#fan_air_intake_temp").val(rows[0].FAN_AIR_INTAKE_TEMP);
				$("#fuel_gas_density").val(rows[0].FUEL_GAS_DENSITY);
				$("#h2s_density").val(rows[0].H2S_DENSITY);
				$("#system_press_reduction").val(rows[0].SYSTEM_PRESS_REDUCTION);
				$("#runtime").val(rows[0].RUNTIME);
				$("#rpdBoilerHighDryId").val(rows[0].RPD_BOILER_HIGH_DRY_ID);
				$("#REMARK").val(rows[0].REMARK);
				$("#DAILY_CUMULATIVE_STEAM").val(rows[0].DAILY_CUMULATIVE_STEAM);
				$("#DAILY_CUMULATIVE_GAS").val(rows[0].DAILY_CUMULATIVE_GAS);
				$("#SEWAGE_BUFFER_TANK").val(rows[0].SEWAGE_BUFFER_TANK);
            	//alert(JSON2.stringify(rows));
			}
        	
	 $("#oildrilling_station_name").ligerComboBox({
			url:'station_queryAreablockInfo.action?orgid=sqdw',
			valueField: 'id',
			valueFieldID: 'hidacceptunitid',
			textField: 'text',
			selectBoxHeight:300,
			selectBoxWidth:100,
			autocomplete:true,
			hideOnLoseFocus:true
		});
	 $("#block_name").ligerComboBox({
			url:'boilerBasicInfo_searchAcceptunit.action?orgid=boilerbasicinfo',
			valueField: 'id',
			valueFieldID: 'hidareablockid',
			textField: 'text',
			selectBoxHeight:300,
			selectBoxWidth:100,
			autocomplete:true,
			hideOnLoseFocus:true
		});
	 var proData = [{ id: 1 , text: '' }];
	$("#blockstation_name").ligerComboBox({
		//url:'boilerBasicInfo_queryStationInfo.action?selecteValue=6',
		url:'boilerBasicInfo_queryStationInfo.action',
		valueField: 'id',
		valueFieldID: 'stationid',
		textField: 'text',
		selectBoxHeight:300,
		selectBoxWidth:140,
		hideOnLoseFocus:true,
		autocomplete:true,
		onBeforeSelect: function (newvalue){
			liger.get("boiler_name").setValue('');
		},
		onSelected:function (data){
			if (data != null && typeof(data)!="undefined" && data != ''){
				setBoilerData($("#stationid").val(),proData);
			}
		}
	});
	$("#boiler_name").ligerComboBox({
		//url:'boilerBasicInfo_queryBoilersNameInfo.action?pageid=6',
		url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
		valueField: 'id',
		valueFieldID: 'boilersid',
		textField: 'text',
		selectBoxHeight:350,
		selectBoxWidth:100,
		hideOnLoseFocus:true,
		autocomplete:true
	});
	$("#steam_work_group").ligerComboBox({
		url:'boilerBasicInfo_searchGroupInfo.action',
		valueField: 'id',
		valueFieldID: 'groupid',
		textField: 'text',
		selectBoxHeight:350,
		selectBoxWidth:100,
		hideOnLoseFocus:true,
		autocomplete:true,
		onSelected:function (data){
		}
	});
					$("#reportDate").ligerDateEditor({format: "yyyy-MM-dd",labelWidth: 100,labelAlign: 'center',cancelable : false
					});
		            $.metadata.setType("attr", "validate");
			            var v = $("form").validate({
			                errorPlacement: function (lable, element)
			                {
			                    if (element.hasClass("l-textarea"))
			                    {
			                        element.ligerTip({ content: lable.html(), target: element[0] }); 
			                    }
			                    else if (element.hasClass("l-text-field"))
			                    {
			                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
			                    }
			                    else
			                    {
			                        lable.appendTo(element.parents("td:first").next("td"));
			                    }
			                },
			                success: function (lable)
			                {
			                    lable.ligerHideTip();
			                    lable.remove();
			                   
			                },
			                submitHandler: function ()
			                {
			                    $("form .l-text,.l-textarea").ligerHideTip();
			                    var rpdBoilerHighDryId = $("#rpdBoilerHighDryId").val();
					            var reportDate=$("#reportDate").val();
					            var blockstation_name=$("#blockstation_name").val();
					            var block_name =$("#block_name").val();
					            var boiler_name =$("#boiler_name").val();
					            var gas_into_press =$("#gas_into_press").val();
					            var gas1_press =$("#gas1_press").val();
					            var fire_measure =$("#fire_measure").val();
					            var gas_flow =$("#gas_flow").val();
					            var pumpin_press =$("#pumpin_press").val();
					            var pumpout_temp =$("#pumpout_temp").val();
					            var pumpfc_frequency =$("#pumpfc_frequency").val();
					            var watersupply_flow =$("#watersupply_flow").val();
					            var steamin_temp =$("#steamin_temp").val();
					            var steamout_temp =$("#steamout_temp").val();
					            var steam_dryness =$("#steam_dryness").val();
					            var rs_press_reduction =$("#rs_press_reduction").val();
					            var superheat_press_reduction =$("#superheat_press_reduction").val();
					            var rerheat_press_reduction =$("#rerheat_press_reduction").val();
					            var mixer_press_reduction =$("#mixer_press_reduction").val();
					            var pumpout_press =$("#pumpout_press").val();
					            var csin_press =$("#csin_press").val();
					            var csin_temp =$("#csin_temp").val();
					            var csout_press =$("#csout_press").val();
					            var csout_temp =$("#csout_temp").val();
					            var convectionr_press_reduction=$("#convectionr_press_reduction").val();
					            var rsin_press =$("#rsin_press").val();
					            var rsin_temp =$("#rsin_temp").val();
					            var rs_press =$("#rs_press").val();
					            var rsout_temp =$("#rsout_temp").val();
					            var rs_temp =$("#rs_temp").val();
					            var reheat_press_entrance =$("#reheat_press_entrance").val();
					            var reheat_temp_entrance =$("#reheat_temp_entrance").val();
					            var reheat_temp_export =$("#reheat_temp_export").val();
					            var gas2_press =$("#gas2_press").val();
					            var gas3_press =$("#gas3_press").val();
					            var lube_temp =$("#lube_temp").val();
					            var burner_temp =$("#burner_temp").val();
					            var hearth_press =$("#hearth_press").val();
					            var ejectsmoke_temp =$("#ejectsmoke_temp").val();
					            var system_voltage =$("#system_voltage").val();
					            var pump_motor_current =$("#pump_motor_current").val();
					            var pump_motor_temp =$("#pump_motor_temp").val();
					            var fan_motor_current =$("#fan_motor_current").val();
					            var steam_work_group =$("#steam_work_group").val();
					            var oildrilling_station_name =$("#oildrilling_station_name").val();
					            var steam_inje_unit =$("#steam_inje_unit").val();
					            var fan_air_intake_temp =$("#fan_air_intake_temp").val();
					            var fuel_gas_density =$("#fuel_gas_density").val();
					            var h2s_density =$("#h2s_density").val();
					            var system_press_reduction =$("#system_press_reduction").val();
					            var runtime =$("#runtime").val();
					            var REMARK =$("#REMARK").val();

					            var DAILY_CUMULATIVE_STEAM =$("#DAILY_CUMULATIVE_STEAM").val();
					            var DAILY_CUMULATIVE_GAS =$("#DAILY_CUMULATIVE_GAS").val();
					            var SEWAGE_BUFFER_TANK =$("#SEWAGE_BUFFER_TANK").val();
					jQuery.ajax({
											type : 'post',
											url : 'ggdglrd_saveOrUpdate.action',
											data: {
												"rpdBoilerHighDryId":rpdBoilerHighDryId,
												"reportDate":reportDate,
												"blockstation_name":blockstation_name,
												"block_name":block_name,
								            	"boiler_name":boiler_name,
								            	"gas_into_press":gas_into_press,
								            	"gas1_press":gas1_press,
								            	"fire_measure":fire_measure,
								            	"gas_flow":gas_flow,
								            	"pumpin_press":pumpin_press,
								            	"pumpout_temp":pumpout_temp,
								            	"pumpfc_frequency":pumpfc_frequency,
								            	"watersupply_flow":watersupply_flow,
								            	"steamin_temp":steamin_temp,
								            	"steamout_temp":steamout_temp,
								            	"steam_dryness":steam_dryness,
								            	"rs_press_reduction":rs_press_reduction,
								            	"superheat_press_reduction":superheat_press_reduction,
								            	"rerheat_press_reduction":rerheat_press_reduction,
								            	"mixer_press_reduction":mixer_press_reduction,
								            	"pumpout_press":pumpout_press,
								            	"csin_press":csin_press,
								            	"csin_temp":csin_temp,
								            	"csout_press":csout_press,
								            	"csout_temp":csout_temp,
								            	"convectionr_press_reduction":convectionr_press_reduction,
								            	"rsin_press":rsin_press,
								            	"rsin_temp":rsin_temp,
								            	"rs_press":rs_press,
								            	"rsout_temp":rsout_temp,
								            	"rs_temp":rs_temp,
								            	"reheat_press_entrance":reheat_press_entrance,
								            	"reheat_temp_entrance":reheat_temp_entrance,
								            	"reheat_temp_export":reheat_temp_export,
								            	"gas2_press":gas2_press,
								            	"gas3_press":gas3_press,
								            	"lube_temp":lube_temp,
								            	"burner_temp":burner_temp,
								            	"hearth_press":hearth_press,
								            	"ejectsmoke_temp":ejectsmoke_temp,
								            	"system_voltage":system_voltage,
								            	"pump_motor_current":pump_motor_current,
								            	"pump_motor_temp":pump_motor_temp,
								            	"fan_motor_current":fan_motor_current,
								            	"steam_work_group":steam_work_group,
								            	"oildrilling_station_name":oildrilling_station_name,
								            	"steam_inje_unit":steam_inje_unit,
								            	"fan_air_intake_temp":fan_air_intake_temp,
								            	"fuel_gas_density":fuel_gas_density,
								            	"h2s_density":h2s_density,
								            	"system_press_reduction":system_press_reduction,
								            	"runtime":runtime,
								            	"REMARK":REMARK,

								    			"dailyCumulativeSteam":DAILY_CUMULATIVE_STEAM,
								    			"dailyCumulativeGas":DAILY_CUMULATIVE_GAS,
								    			"sewageBufferTank":SEWAGE_BUFFER_TANK},
											async : false,
											success : function(jsondata) { 
											$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
											if (jsondata == 1){
													$("#errormsg").html("");
													parent.$.ligerDialog.close();
													//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
													parent.window.onSubmit();
												//	parent.window.getstree();
													parent.$.ligerDialog.success("保存成功！");
											}	
													
											/* 	}else if(jsondata == -2){
													$("#errormsg").html("<font size='2' color='red'>*提示 ：所属单位：'"+str+"' &nbsp;不存在，请填写正确所属单位");
												
												}else{
													$("#errormsg").html("<font size='2' color='red'>*提示 ：数据库数据存在错误！'</font>'");
												} */
											},
											error : function(data) {
										
											}
										});
					                }
					            });
					            $("form").ligerForm();
					           /*  $("#block_name").focus();   
					            $("#block_name").ligerTip(); */
            });
        
        
        function setBoilerData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'boilerBasicInfo_queryBoilersNameInfo.action',
				data: {"orgid":data},
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("boiler_name").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("boiler_name").setData(proData);
					}
				},
				error : function(jsondata) {
				}
			});
		}
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

<body style="overflow:scroll;overflow-x:hidden;overflow-y:scroll;width:100%;">
    <form name="form" method="post"  id="form">
		<div id="errorLabelContainer" class="l-text-invalid">
		</div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
		</table>
		 <div class="searchtitle">
        <span id="optit">添加高干度锅炉日报数据&nbsp;</span><img src="../../lib/icons/32X32/user.gif" />
   		 </div>
   		 <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
         <tr>
				<td align="right" class="l-table-edit-td" style="width:100px;">受汽区块：</td>
	                <td align="right" class="l-table-edit-td" style="width:100p;">
	                	<input type="text" id="block_name" name="block_name" validate="{required:true }"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">接转站名称：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="blockstation_name" name="blockstation_name" />
	                </td>
	               <td align="right" class="l-table-edit-td" style="width:100px">锅炉名称：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="boiler_name" name="boiler_name" validate="{required:true }"/>
	                </td>
	               </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">运行组：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="steam_work_group" name="steam_work_group" validate="{required:true }"/>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" style="width:100px">注汽单位：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="steam_inje_unit" name="steam_inje_unit" validate="{required:true }"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">受汽单位：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="oildrilling_station_name" name="oildrilling_station_name" validate="{required:true }"/>
	                </td>
	                </tr>
	                <tr>
	                 <td align="right" class="l-table-edit-td" style="width:100px">采集时间：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="reportDate" name="reportDate" validate="{required:true,minlength:1,maxlength:20}"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">运行时间：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="runtime" name="runtime" onblur="changValue()" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" style="width:100px">天然气分离器压差：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="gas_into_press" name="gas_into_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	               </tr>
	                <tr>
	                
					<td align="right" class="l-table-edit-td" style="width:100px">阀前压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="gas1_press" name="gas1_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">火量：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="fire_measure" name="fire_measure" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	               
	                <td align="right" class="l-table-edit-td" style="width:100px">天然气流量：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="gas_flow" name="gas_flow" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">给水压力  ：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="pumpin_press" name="pumpin_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">给水温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="pumpout_temp" name="pumpout_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	               
	                <td align="right" class="l-table-edit-td" style="width:100px">柱塞泵变频频率：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="pumpfc_frequency" name="pumpfc_frequency" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">给水流量：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="watersupply_flow" name="watersupply_flow" onblur="changValue()" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">蒸汽出口压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="steamin_temp" name="steamin_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" style="width:100px">蒸汽出口温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="steamout_temp" name="steamout_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">蒸汽出口干度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="steam_dryness" name="steam_dryness" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	               
	                <td align="right" class="l-table-edit-td" style="width:100px">柱塞泵出口压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="pumpout_press" name="pumpout_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	               
	                <td align="right" class="l-table-edit-td" style="width:100px">对流段入口压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="csin_press" name="csin_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">对流段入口温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="csin_temp" name="csin_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">对流段出口压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="csout_press" name="csout_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                
					<td align="right" class="l-table-edit-td" style="width:100px">对流段出口温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="csout_temp" name="csout_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">对流段压降：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="convectionr_press_reduction" name="convectionr_press_reduction" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">辐射段入口压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="rsin_press" name="rsin_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" style="width:100px">辐射段入口温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="rsin_temp" name="rsin_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">辐射段出口压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="rs_press" name="rs_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">辐射段出口温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="rsout_temp" name="rsout_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" style="width:100px">辐射段出口管温：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="rs_temp" name="rs_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">辐射段压降：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="rs_press_reduction" name="rs_press_reduction" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" style="width:100px">再热段入口压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="reheat_press_entrance" name="reheat_press_entrance" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">再热段入口温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="reheat_temp_entrance" name="reheat_temp_entrance" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">再热段出口管温：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="reheat_temp_export" name="reheat_temp_export " onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 <td align="right" class="l-table-edit-td" style="width:100px">再热段压降：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="rerheat_press_reduction" name="rerheat_press_reduction " onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">天然气阀后压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="gas2_press" name="gas2_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">膨胀管压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="gas3_press" name="gas3_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	               
	                <td align="right" class="l-table-edit-td" style="width:100px">润滑油温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="lube_temp" name="lube_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">鼓风机进风温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="fan_air_intake_temp" name="fan_air_intake_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">燃烧器喉温：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="burner_temp" name="burner_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">炉膛压力：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="hearth_press" name="hearth_press" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" style="width:100px">排烟温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="ejectsmoke_temp" name="ejectsmoke_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">电网电压：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="system_voltage" name="system_voltage" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">柱塞泵电流：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="pump_motor_current" name="pump_motor_current" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	               
	                <td align="right" class="l-table-edit-td" style="width:100px">柱塞泵电机温度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="pump_motor_temp" name="pump_motor_temp" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                 </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">鼓风机电流：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="fan_motor_current" name="fan_motor_current" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" style="width:100px">锅炉房可燃气体浓度  ：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="fuel_gas_density" name="fuel_gas_density" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">h2s气体浓度：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="h2s_density" name="h2s_density" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">污水缓冲罐液位：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="SEWAGE_BUFFER_TANK" name="SEWAGE_BUFFER_TANK" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	              
	                  <td align="right" class="l-table-edit-td" style="width:100px">天然气日累积量 ：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="DAILY_CUMULATIVE_GAS" name="DAILY_CUMULATIVE_GAS" onkeyup="checkData(this)" ltype="text"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">注汽日累积量：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="DAILY_CUMULATIVE_STEAM" name="DAILY_CUMULATIVE_STEAM" disabled="disabled"/>
	                </td>
	                </tr>
	                <tr>
	                <td align="right" class="l-table-edit-td" style="width:100px">备注：</td>
	                <td align="left"  colspan="6">
					 <textarea rows="2" cols="400"  id="REMARK" name = "REMARK" ></textarea>
					</td>
	                <td align="left" style="width:10px"></td>
                <!-- <td align="right" class="l-table-edit-td">所属单位:</td>
                <td align="left" class="l-table-edit-td" style="width:60px">
                <input name="selecttree" type="text" id="selecttree" ltype="text" validate="{required:true}"  title="*此处输入字符会自动匹配，例如：输入“1-02”将下列列出 含此字符的井场  ; " /></td>
            --> </tr>
            
             <tr>
             <td align="left"><input name="rpdBoilerHighDryId" id="rpdBoilerHighDryId" type="hidden" /></td>
              <!-- <td align="left"><input name="boilerid" id="boilerid" type="hidden" /></td> -->
               <!-- <td align="right" class="l-table-edit-td" style="width:100px">分离器液位：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="SL_LEVEL" name="SL_LEVEL"/>
	                </td>
	            <td align="right" class="l-table-edit-td" style="width:100px">过热段出口管温：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="SUPERHEAT_TEMP" name="SUPERHEAT_TEMP"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">过热段压阻：</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="SUPERHEAT_PIEZORESISTANCE" name="SUPERHEAT_PIEZORESISTANCE"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">过热段入口温度：过热的字段都没有</td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<input type="text" id="SUPERHEATIN_TEMP" name="SUPERHEATIN_TEMP"/>
	                </td>
	                 <td align="right" class="l-table-edit-td" style="width:100px">过热段出口温度：天然气阀前压力 </td>
	                <td align="right" class="l-table-edit-td" style="width:100px">辐射段出口干度，辐射段压阻
	                	<input type="text" id="SUPERHEATOUT_TEMP" name="SUPERHEATOUT_TEMP"/>
	                </td> -->
             </tr>
        </table>
        <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<center><div style="width:80px;text-align:center"><input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /></div></center>
    </form>
    <div style="display:none"> </div>
</body>
</html>