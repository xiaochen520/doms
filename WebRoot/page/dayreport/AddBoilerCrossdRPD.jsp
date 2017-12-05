<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>过热锅炉日报维护数据</title>
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
	
	var boilerid; 	//	锅炉id
	var report_date; 	//	日期
	var oildrilling_station_name; 	//	采油站名称
	var blockstation_name; 	//	接转站名称
	var boiler_name; 	//	锅炉名称
	var runtime;
	var RUN_STATUS;		//运行状态
	var steamout_temp; 	//	蒸汽出口温度
	var steamin_temp; 	//	蒸汽出口压力
	var ejectsmoke_temp; 	//	排烟温度
	var burner_temp; 	//	燃烧器温度
	var csin_press; 	//	对流段进口压力
	var csout_press; 	//	对流段出口压力
	var csin_temp; 	//	对流段进口温度
	var csout_temp; 	//	对流段出口温度
	var sl_level; 	//	分离器液位
	var superheat_temp; 	//	过热段管温
	var superheat_piezoresistance; 	//	过热段压阻
	var superheatin_temp; 	//	过热入口温度
	var superheatout_temp; 	//	过热出口温度
	var superheatin_press; 	//	过热入口压力
	var superheatout_press; 	//	过热出口压力
	var superheatin_flow; 	//	过热入口流量
	var hearth_press; 	//	炉膛压力
	var gas1_press; 	//	燃气一级压力
	var gas2_press; 	//	燃气二级压力
	var gas3_press; 	//	燃气三级压力
	var rs_temp; 	//	辐射段管温
	var rs_dryness; 	//	辐射段干度
	var rs_piezoresistance; 	//	辐射段压阻
	var rsin_press; 	//	辐射段进口压力
	var rs_press; 	//	辐射段出口压力
	var rsin_temp; 	//	辐射段进口温度
	var rsout_temp; 	//	辐射段出口温度
	var pumpa_flow; 	//	泵a相流量
	var pumpb_flow; 	//	泵b相流量
	var pumpc_flow; 	//	泵c相流量
	var pumpa_press; 	//	泵ac电压
	var pumpb_press; 	//	泵ab电压
	var pumpc_press; 	//	泵bc电压
	var fana_electricity; 	//	风机a相电流
	var fanb_electricity; 	//	风机b相电流
	var fanc_electricity; 	//	风机c相电流
	var pumpfc_frequency; 	//	泵变频频率
	var pumpin_press; 	//	泵进口压力
	var pumpout_press; 	//	泵出口压力
	var pumpin_temp; 	//	泵进口温度
	var pumpout_temp; 	//	泵出口温度
	var watersupply_flow; 	//	给水流量
	var watersupply_total; 	//	给水量累计
	var gas_flow; 	//	燃气流量
	var gas_total; 	//	燃气量累计
	var steaminjection_total; 	//	注汽量累计
	var gas_into_press;	//站区来气压力
	var fire_measure;	//火量
	var superheat_degree;	//过热度
	var mixer_press_reduction;	//掺混器压降
	var convectionr_press_reduction;	//对流段压降
	var separator_press_export;	//分离器出口压力
	var mixer_press_water;	//掺混水压力
	var lube_temp;	//润滑油温度
	var system_voltage;	//电网电压
	var pump_motor_temp;	//柱塞泵电机温度
	var pump_motor_current;	//柱塞泵电流
	var rs_press_reduction;	//辐射段压降
	var superheat_press_reduction;	//过热段压降
	var fan_air_export_press; // 助燃风压力
	var fan_motor_current;	//鼓风机电流
	var block_name;	//区块
	var steam_work_group;	//运行组
	var steam_inje_unit;	//注汽单位
	var fan_air_intake_temp; // 鼓风机进风温度
	var h2s_density;
	var fuel_gas_density;
	var daily_cumulative_gas;
	var daily_cumulative_steam;
	var  REMARK ;
	var  sewageBufferTank;

function  changValue(){
		var  watersupply_flow =Number($('#watersupply_flow').val());	
		var  runtime =Number($('#runtime').val());	
		//var  daily_cumulative_steam = Number($('#daily_cumulative_steam').val());	
	    if(runtime =='' || watersupply_flow == ''){
        	$('#daily_cumulative_steam').val('');
		}else{
			Number($('#daily_cumulative_steam').val((runtime*watersupply_flow).toFixed(2)));
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
		$("#BLOCKSTATION_NAME").ligerComboBox({
			url:'boilerBasicInfo_queryStationInfo.action?areaid=all',
			valueField: 'id',
			valueFieldID: 'BLOCKSTATION_NAMEid',
			textField: 'text',
			selectBoxHeight:120,
			selectBoxWidth:140,
			hideOnLoseFocus:true,
            autocomplete:true,
            alwayShowInTop:false
		});
		$("#steam_work_group").ligerComboBox({
			url:'boilerBasicInfo_searchGroupInfo.action',
			valueField: 'id',
			valueFieldID: 'steam_work_groupid',
			textField: 'text',
			selectBoxHeight:120,
			width: 120,
			hideOnLoseFocus:true,
            autocomplete:true,
            alwayShowInTop:false
		});

		 $("#block_name").ligerComboBox({
				url:'boilerBasicInfo_searchAcceptunit.action?orgid=boilerbasicinfo',
				valueField: 'id',
				valueFieldID: 'areablockid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true
			});
		$("#boiler_name").ligerComboBox({
			url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
			valueField: 'id',
			valueFieldID: 'boilersid',
			textField: 'text',
			selectBoxHeight:350,
			selectBoxWidth:100,
			hideOnLoseFocus:true,
            autocomplete:true
		});
		$("#group").ligerComboBox({
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
		/*$("#steam_inje_unit").ligerComboBox({
			url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
			valueField: 'id',
			valueFieldID: 'steam_id',
			textField: 'text',
			selectBoxHeight:350,
			selectBoxWidth:100,
			hideOnLoseFocus:true,
            autocomplete:true,
			onSelected:function (data){
			}
		});*/
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
		
		/* $("#reportDate").ligerDateEditor({ showTime: true, labelWidth: 150, labelAlign: 'left' }); */
		$("#report_date").ligerDateEditor({format: "yyyy-MM-dd",labelWidth: 100,labelAlign: 'center',cancelable : false
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
            	var steam_work_group ="";
            	if($("#steam_work_groupid").val() !=''){
            		steam_work_group = $("#steam_work_groupid").val();
					} 
            	//var boiler_crossddid; 	//锅炉过热日报id
            	var boilerid = $("#boilersid").val();	//	锅炉id
            	var report_date = $("#report_date").val(); 	//	日期
            	var runtime = $("#runtime").val();
            	var oildrilling_station_name=$("#oildrilling_station_name").val(); 	//	受汽站
            	var blockstation_name = $("#blockstation_name").val(); 	//	接转站名称
            	var boiler_name = $("#boiler_name").val(); 	//	锅炉名称
            	//var run_status = $("#run_status").val();	//运行状态
            	
            	var steamout_temp = $("#steamout_temp").val(); 	//	蒸汽出口温度
            	var steamin_temp = $("#steamin_temp").val(); 	//	蒸汽出口压力
            	var ejectsmoke_temp = $("#ejectsmoke_temp").val(); 	//	排烟温度
            	var burner_temp = $("#burner_temp").val(); 	//	燃烧器温度
            	var csin_press = $("#csin_press").val(); 	//	对流段进口压力
            	var csout_press = $("#csout_press").val(); 	//	对流段出口压力
            	var csin_temp = $("#csin_temp").val(); 	//	对流段进口温度
            	var csout_temp = $("#csout_temp").val(); 	//	对流段出口温度
            	var sl_level = $("#sl_level").val(); 	//	分离器液位
            	var superheat_temp = $("#superheat_temp").val(); 	//	过热段管温
            	var superheat_piezoresistance = $("#superheat_piezoresistance").val(); 	//	过热段压阻
            	var superheatin_temp = $("#superheatin_temp").val(); 	//	过热入口温度
            	var superheatout_temp = $("#superheatout_temp").val(); 	//	过热出口温度
            	var superheatin_press = $("#superheatin_press").val(); 	//	过热入口压力
            	var superheatout_press = $("#superheatout_press").val(); 	//	过热出口压力
            	var superheatin_flow = $("#superheatin_flow").val(); 	//	过热入口流量
            	var hearth_press = $("#hearth_press").val(); 	//	炉膛压力
            	var gas1_press = $("#gas1_press").val(); 	//	燃气一级压力
            	var gas2_press = $("#gas2_press").val(); 	//	燃气二级压力
            	var gas3_press = $("#gas3_press").val(); 	//	燃气三级压力
            	var rs_temp = $("#rs_temp").val(); 	//	辐射段管温
            	var rs_dryness = $("#rs_dryness").val(); 	//	辐射段干度
            	var rs_piezoresistance = $("#rs_piezoresistance").val(); 	//	辐射段压阻
            	var rsin_press = $("#rsin_press").val(); 	//	辐射段进口压力
            	var rs_press = $("#rs_press").val(); 	//	辐射段出口压力
            	var rsin_temp = $("#rsin_temp").val(); 	//	辐射段进口温度
            	var rsout_temp = $("#rsout_temp").val(); 	//	辐射段出口温度
            	var pumpa_flow = $("#pumpa_flow").val(); 	//	泵a相流量
            	var pumpb_flow = $("#pumpb_flow").val(); 	//	泵b相流量
            	var pumpc_flow = $("#pumpc_flow").val(); 	//	泵c相流量
            	var pumpa_press = $("#pumpa_press").val(); 	//	泵ac电压
            	var pumpb_press = $("#pumpb_press").val(); 	//	泵ab电压
            	var pumpc_press = $("#pumpc_press").val(); 	//	泵bc电压
            	var fana_electricity = $("#fana_electricity").val(); 	//	风机a相电流
            	var fanb_electricity = $("#fanb_electricity").val(); 	//	风机b相电流
            	var fanc_electricity = $("#fanc_electricity").val(); 	//	风机c相电流
            	var pumpfc_frequency = $("#pumpfc_frequency").val(); 	//	泵变频频率
            	var pumpin_press = $("#pumpin_press").val(); 	//	泵进口压力
            	var pumpout_press = $("#pumpout_press").val(); 	//	泵出口压力
            	var pumpin_temp = $("#pumpin_temp").val(); 	//	泵进口温度
            	var pumpout_temp = $("#pumpout_temp").val(); 	//	泵出口温度
            	var watersupply_flow = $("#watersupply_flow").val(); 	//	给水流量
            	var watersupply_total = $("#watersupply_total").val(); 	//	给水量累计
            	var gas_flow = $("#gas_flow").val(); 	//	燃气流量
            	var gas_total = $("#gas_total").val(); 	//	燃气量累计
            	var steaminjection_total = $("#steaminjection_total").val(); 	//	注汽量累计

 				var gas_into_press= $("#gas_into_press").val();
            	var fire_measure= $("#fire_measure").val();
            	var superheat_degree= $("#superheat_degree").val();
            	var mixer_press_reduction= $("#mixer_press_reduction").val();
            	var convectionr_press_reduction= $("#convectionr_press_reduction").val();
            	var separator_press_export= $("#separator_press_export").val();
            	var mixer_press_water= $("#mixer_press_water").val();
            	var lube_temp= $("#lube_temp").val();
            	var fan_air_export_press= $("#fan_air_export_press").val();
            	var system_voltage= $("#system_voltage").val();
            	var pump_motor_temp= $("#pump_motor_temp").val();
            	var pump_motor_current= $("#pump_motor_current").val();
            	var rs_press_reduction= $("#rs_press_reduction").val();
            	var superheat_press_reduction= $("#superheat_press_reduction").val();
            	var fan_motor_current= $("#fan_motor_current").val();
            	var block_name= $("#block_name").val();
            	var steam_work_group = $("#steam_work_group").val();
            	var steam_inje_unit= $("#steam_inje_unit").val();
            	var fan_air_intake_temp= $("#fan_air_intake_temp").val();
            	var fuel_gas_density=$("#fuel_gas_density").val();
            	var h2s_density=$("#h2s_density").val();
            	var daily_cumulative_gas =$("#daily_cumulative_gas").val();
            	var daily_cumulative_steam =$("#daily_cumulative_steam").val();
            	var  REMARK = $("#REMARK").val();
            	var  sewageBufferTank = $("#sewageBufferTank").val();
            	
            	$("form .l-text,.l-textarea").ligerHideTip();
				jQuery.ajax({
					type : 'post',
					url : 'blilercdrpd_SaveOrUpadateBoilerCrossdRPD.action',
					data: {
					"blockstation_name":blockstation_name,
					 "boiler_name" :boiler_name ,
					 "report_date" :report_date,
					 "runtime":runtime,
					// "boilerCrossddid" :
					"oildrilling_station_name":oildrilling_station_name,
					 "boilerid" :boilerid,
					 "steamout_temp" :steamout_temp,
					 "steamin_temp" :steamin_temp ,
					 "ejectsmoke_temp" :ejectsmoke_temp,
					 "burner_temp" :burner_temp ,
					 "csin_press" :csin_press  ,
					 "csout_press" :csout_press  ,
					 "csin_temp" :csin_temp  ,
					 "csout_temp" :csout_temp ,
					 "sl_level" :sl_level ,
					 "superheat_temp" :superheat_temp ,
					 "superheat_piezoresistance" :superheat_piezoresistance ,
					 "superheatin_temp" :superheatin_temp ,
					 "superheatout_temp" :superheatout_temp ,
					 "superheatin_press" :superheatin_press ,
					 "superheatout_press" :superheatout_press ,
					 "superheatin_flow" :superheatin_flow  ,
					 "hearth_press" :hearth_press ,
					 "gas1_press" :gas1_press ,
					 "gas2_press" :gas2_press ,
					 "gas3_press" :gas3_press ,
					 "rs_temp" :rs_temp  ,
					 "rs_dryness" :rs_dryness ,
					 "rs_piezoresistance" :rs_piezoresistance ,
					 "rsin_press" :rsin_press ,
					 "rs_press" :rs_press,
					 "rsin_temp" :rsin_temp ,
					 "rsout_temp" :rsout_temp ,
					 "pumpa_flow" :pumpa_flow,
					 "pumpb_flow" :pumpb_flow ,
					 "pumpc_flow" :pumpc_flow ,
					 "pumpa_press" :pumpa_press ,
					 "pumpb_press" :pumpb_press,
					 "pumpb_press" :pumpc_press ,
					 "fana_electricity" :fana_electricity ,
					 "fanb_electricity" :fanb_electricity ,
					 "fanc_electricity" :fanc_electricity ,
					 "pumpfc_frequency" :pumpfc_frequency ,
					 "pumpin_press" :pumpin_press ,
					 "pumpout_press" :pumpout_press,
					 "pumpin_temp" :pumpin_temp ,
					 "pumpout_temp" :pumpout_temp ,
					 "watersupply_flow" :watersupply_flow ,
					 "watersupply_total" :watersupply_total  ,
					 "gas_flow" :gas_flow ,
					 "gas_total" :gas_total ,
					 "steaminjection_total" :steaminjection_total,
					 "steam_work_group":steam_work_group,
					 "steam_inje_unit":steam_inje_unit ,
					 "fan_air_export_press":fan_air_export_press,

					 "gas_into_press":gas_into_press,
					 "fire_measure":fire_measure,
					 "superheat_degree":superheat_degree,
					 "mixer_press_reduction":mixer_press_reduction,
					 "convectionr_press_reduction":convectionr_press_reduction,
					 "separator_press_export":separator_press_export,
					 "mixer_press_water":mixer_press_water,
					 "lube_temp":lube_temp,
					 "system_voltage":system_voltage,
					 "pump_motor_temp":pump_motor_temp,
					 "pump_motor_current":pump_motor_current,
					 "rs_press_reduction":rs_press_reduction,
					 "superheat_press_reduction":superheat_press_reduction,
					 "fan_motor_current":fan_motor_current,
					 "block_name":block_name,
					 "fan_air_intake_temp":fan_air_intake_temp,
					 "fuel_gas_density":fuel_gas_density,
					 "daily_cumulative_gas":daily_cumulative_gas,
					 "daily_cumulative_steam":daily_cumulative_steam,
					 "h2s_density":h2s_density,
					 "REMARK":REMARK,
					 "sewageBufferTank":sewageBufferTank

					 					 
		            	},
						async : false,
					timeout : '3000',
					success : function(jsondata) { 
					$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
					if (jsondata == 1){
							parent.$.ligerDialog.close();
							//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
							parent.window.onSubmit();
							parent.$.ligerDialog.success("保存成功！");
						}else{
							alert("数据库数据存在错误！");
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

<body style="overflow:scroll;overflow-x:hidden;overflow-y:scroll;width:100%;">
	<form name="form" method="post" id="form">
		<div id="errorLabelContainer" class="l-text-invalid">
		</div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
		</table>
		<div class="searchtitle">
		<span>过热锅炉日报维护数据&nbsp;<div id="errormsg"></div></span><img src="../../lib/icons/32X32/user.gif" />
		</div>
		<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">区块：</td>
				<td align="left" class="l-table-edit-td" style="width:80px" >
					<input type="text" id="block_name" name = "block_name" validate="{required:true }"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="boiler_name" name = "boiler_name"  validate="{required:true }"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">采集时间：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="report_date" name = "report_date" validate="{required:true,minlength:1,maxlength:20}" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">运行时间：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="runtime" name = "runtime"    onblur="changValue()" onkeyup="checkData(this)" ltype="text"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">站区来气压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="gas_into_press" name = "gas_into_press" onkeyup="checkData(this)" ltype="text" />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">阀前压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="gas1_press" name = "gas1_press" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">火量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="fire_measure" name = "fire_measure" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">天然气流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="gas_flow" name = "gas_flow" onkeyup="checkData(this)" ltype="text" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">给水压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pumpin_press" name = "pumpin_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">给水温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pumpout_temp" name = "pumpout_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">给水泵变频频率：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pumpfc_frequency" name = "pumpfc_frequency" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">给水流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="watersupply_flow" name = "watersupply_flow"    onblur="changValue()" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="steamin_temp" name = "steamin_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="steamout_temp" name = "steamout_temp" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="superheat_degree" name = "superheat_degree" onkeyup="checkData(this)" ltype="text" />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">过热段出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="superheatout_temp" name = "superheatout_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">分离器液位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="sl_level" name = "sl_level" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段压降：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rs_press_reduction" name = "rs_press_reduction" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热段压降：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="superheat_press_reduction" name = "superheat_press_reduction" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">掺混器压降：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="mixer_press_reduction" name = "mixer_press_reduction" onkeyup="checkData(this)" ltype="text"  />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">给水泵出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pumpout_press" name = "pumpout_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">对流段入口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="csin_press" name = "csin_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段入口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="csin_temp" name = "csin_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="csout_press" name = "csout_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="csout_temp" name = "csout_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">对流段压降：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="convectionr_press_reduction" name = "convectionr_press_reduction" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段入口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rsin_press" name = "rsin_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段入口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rsin_temp" name = "rsin_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rs_press" name = "rs_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段出口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rsout_temp" name = "rsout_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段出口管温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rs_temp" name = "rs_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">辐射段出口干度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rs_dryness" name = "rs_dryness" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">分离器出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="separator_press_export" name = "separator_press_export" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热段入口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="superheatin_press" name = "superheatin_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热段入口温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="superheatin_temp" name = "superheatin_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">过热段出口压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="superheatout_press" name = "superheatout_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">过热段管温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="superheat_temp" name = "superheat_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">过热蒸汽流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="superheatin_flow" name = "superheatin_flow" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">掺混水压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="mixer_press_water" name = "mixer_press_water" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">阀后压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="gas2_press" name = "gas2_press" onkeyup="checkData(this)" ltype="text" />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">膨胀管压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="gas3_press" name = "gas3_press" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">润滑油温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="lube_temp" name = "lube_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">助燃风压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="fan_air_export_press" name = "fan_air_export_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">鼓风机进风温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="fan_air_intake_temp" name = "fan_air_intake_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">燃烧器喉温：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="burner_temp" name = "burner_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">炉膛压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="hearth_press" name = "hearth_press" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">排烟温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="ejectsmoke_temp" name = "ejectsmoke_temp" onkeyup="checkData(this)" ltype="text"  />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">电网电压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="system_voltage" name = "system_voltage" onkeyup="checkData(this)" ltype="text" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">给水泵电机电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pump_motor_current" name = "pump_motor_current" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">给水泵电机温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pump_motor_temp" name = "pump_motor_temp" onkeyup="checkData(this)" ltype="text" />	
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">鼓风机电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="fan_motor_current" name = "fan_motor_current" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉房可燃气体浓度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="fuel_gas_density" name = "fuel_gas_density" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">锅炉房H2S气体浓度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="h2s_density" name = "h2s_density" onkeyup="checkData(this)" ltype="text" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">污水缓冲罐液位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="sewageBufferTank" name = "sewageBufferTank" onkeyup="checkData(this)" ltype="text" />	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">天然气日累积量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="daily_cumulative_gas" name = "daily_cumulative_gas" onkeyup="checkData(this)" ltype="text" />	
				</td>
					</tr>
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">注汽日累积量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="daily_cumulative_steam" name = "daily_cumulative_steam"   disabled="disabled" ltype="text"  />	
				</td>
		
				<td align="right" class="l-table-edit-td" style="width:80px">运行组：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="steam_work_group" name = "steam_work_group" validate="{required:true }" />	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">受汽单位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="oildrilling_station_name" name = "oildrilling_station_name" validate="{required:true }" />	
				</td>
					<td align="right" class="l-table-edit-td" style="width:80px">注汽单位：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="steam_inje_unit" name = "steam_inje_unit" />	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">备注：</td>
				  <td align="left"  colspan="10">
					 <textarea rows="2" cols="600" id="REMARK" name = "REMARK" ></textarea>
				</td>
			</tr>
			</table>
			<br/>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<center><div style="width:80px;text-align:center"><input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /></div></center>
    </form>
    <div style="display:none"> </div>
</body>
</html>