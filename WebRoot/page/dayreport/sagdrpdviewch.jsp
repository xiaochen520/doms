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
		var sagdid = parent.sagdid;
    	var sagddid = parent.sagddid;
    	var jhmc = parent.jhmc;
	$(function () {
        
			// 获取日报相信信息 初始化数据
			 jQuery.ajax({
					type : 'post',
					url : 'sagddrpd_SagdRPDviewpageInit.action',
					data: {"sagdid":sagdid ,"sagddid":sagddid},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
							if(jsondata < 0){
								$.ligerDialog.error(outerror(jsondata));
							}else{
								var data = eval('(' + jsondata + ')');
								$("#JHMC").val(data.JHMC);
								$("#REPORTDATE").val(data.REPORT_DATE);
								$("#WELL_CODE").val(data.WELL_CODE);
								$("#OILSTATIONNAME").val(data.OILSTATIONNAME);
								$("#QKMC").val(data.QKMC);
								$("#BLOCKSTATIONNAME").val(data.BLOCKSTATIONNAME);
								$("#BRANCHING_NAME").val(data.BRANCHING_NAME);
								//$("#sl").val("");  //时率
								//$("#bj").val(""); //泵径
								
								//$("#YZP").val("");  //P井主管油嘴(mm)
								$("#P_PRESSURE_PRESS").val(data.P_PRESSURE_PRESS);   //P主管压力（第1位字母P代表P井参数，第1位字母I代表P井参数）
								$("#P_PRESSURE_TEMP").val(data.P_PRESSURE_TEMP);   //P主管温度
								$("#P_LOOPED_PRESS").val(data.P_LOOPED_PRESS);   //P副管压力
								$("#P_LOOPED_TEMP").val(data.P_LOOPED_TEMP);   //P副管温度
								$("#P_DRIVEPIPE_PRESS").val(data.P_DRIVEPIPE_PRESS);   //P套管压力
								$("#I_PRESSURE_PRESS").val(data.I_PRESSURE_PRESS);   //I主管压力
								$("#I_PRESSURE_TEMP").val(data.I_PRESSURE_TEMP);   //I主管温度
								$("#I_LOOPED_PRESS").val(data.I_LOOPED_PRESS);   //I副管压力
								$("#I_LOOPED_TEMP").val(data.I_LOOPED_TEMP);   //I副管温度
								$("#I_DRIVEPIPE_PRESS").val(data.I_DRIVEPIPE_PRESS);   //I套管压力
								$("#NO1STEAM_TEMP").val(data.NO1STEAM_TEMP);   //1#蒸汽温度（第1位字母代表蒸汽相关参数，第2位数字1、2分别代表1、2号蒸汽流量计）
								$("#NO1STEAM_PRESS").val(data.NO1STEAM_PRESS);   //1#蒸汽压力
								$("#NO1STEAM_DIFP").val(data.NO1STEAM_DIFP);   //1#蒸汽差压
								$("#NO1INSTANT_FLOW").val(data.NO1INSTANT_FLOW);   //1#瞬时流量
								$("#LOWSUM_FLOW1").val(data.LOWSUM_FLOW1);   //1#低精度总累计流量
								$("#NO1INSTANT_DRYNESS").val(data.NO1INSTANT_DRYNESS);   //1#蒸汽瞬时干度
								$("#NO1CONTROL_STATUS").val(data.NO1CONTROL_STATUS);   //1#阀门手/自动状态(0-手动，1-自动)
								$("#NO1CONTROL_APERTURE").val(data.NO1CONTROL_APERTURE);   //1#流量调节阀开度
								$("#NO1FLOW_SET").val(data.NO1FLOW_SET);   //1#流量设定值
								$("#NO2STEAM_TEMP").val(data.NO2STEAM_TEMP);   //2#蒸汽温度
								$("#NO2STEAM_PRESS").val(data.NO2STEAM_PRESS);   //2#蒸汽压力
								$("#NO2STEAM_DIFP").val(data.NO2STEAM_DIFP);   //2#蒸汽差压
								$("#NO2INSTANT_FLOW").val(data.NO2INSTANT_FLOW);   //2#瞬时流量
								$("#LOWSUM_FLOW2").val(data.LOWSUM_FLOW2);   //2#低精度总累计流量
								$("#NO2INSTANT_DRYNESS").val(data.NO2INSTANT_DRYNESS);   //2#蒸汽瞬时干度
								$("#NO2CONTROL_STATUS").val(data.NO2CONTROL_STATUS);   //2#阀门手/自动状态
								$("#NO2CONTROL_APERTURE").val(data.NO2CONTROL_APERTURE);   //2#流量调节阀开度
								$("#NO2FLOW_SET").val(data.NO2FLOW_SET);   //2#流量设定值
								$("#P_PRESS1").val(data.P_PRESS1);   //井下压力1
								$("#P_PRESS2").val(data.P_PRESS2);   //井下压力2
								$("#P_TEMP1").val(data.P_TEMP1);   //P井下温度1（第1位字母P代表P井参数，第2位字母T代表温度）
								$("#P_TEMP2").val(data.P_TEMP2);   //P井下温度2
								$("#P_TEMP3").val(data.P_TEMP3);   //P井下温度3
								$("#P_TEMP4").val(data.P_TEMP4);   //P井下温度4
								$("#P_TEMP5").val(data.P_TEMP5);   //P井下温度5
								$("#P_TEMP6").val(data.P_TEMP6);   //P井下温度6
								$("#P_TEMP7").val(data.P_TEMP7);   //P井下温度7
								$("#P_TEMP8").val(data.P_TEMP8);   //P井下温度8
								$("#P_TEMP9").val(data.P_TEMP9);   //P井下温度9
								$("#P_TEMP10").val(data.P_TEMP10);   //P井下温度10
								$("#P_TEMP11").val(data.P_TEMP11);   //P井下温度11
								$("#P_TEMP12").val(data.P_TEMP12);   //P井下温度12
								$("#NOW_LOAD").val(data.NOW_LOAD);   //载荷
								$("#MOTOR_PRESS_A").val(data.MOTOR_PRESS_A);   //电机工作电压A相
								$("#MOTOR_TEMP_A").val(data.MOTOR_TEMP_A);   //电机工作电流A相
								$("#MOTOR_PRESS_B").val(data.MOTOR_PRESS_B);   //电机工作电压B相2
								$("#MOTOR_TEMP_B").val(data.MOTOR_TEMP_B);   //电机工作电流B相
								$("#MOTOR_PRESS_C").val(data.MOTOR_PRESS_C);   //电机工作电压C相
								$("#MOTOR_TEMP_C").val(data.MOTOR_TEMP_C);   //电机工作电流C相
								$("#CLIQUE_CONTROL_APERTURE2").val(data.CLIQUE_CONTROL_APERTURE2);   //蒸汽2#调节阀阀门控制开度
								$("#ACTIVE_ENERGY").val(data.ACTIVE_ENERGY);   //电机有功用电量（Functional capacity）
								$("#IDLE_ENERGY").val(data.IDLE_ENERGY);   //电机无功用电量
								$("#POWER_FACTOR").val(data.POWER_FACTOR);   //电机功率因数
								$("#JIG_FREQUENCY").val(data.JIG_FREQUENCY);   //冲次
								$("#STROKE").val(data.STROKE);   //冲程
								$("#PUMPING_RUNNING_STATE").val(data.PUMPING_RUNNING_STATE);   //抽油机运行状态（1=指示（有报警提示下的运行），2=空闲（倒计时为零，自动启抽），4=停机，8=死停。）
								$("#MOTORSTATUS").val(data.MOTORSTATUS);   //手/自动状态0=自动，1=手动
								$("#CONVERSION_PRESS").val(data.CONVERSION_PRESS);   //变频电压
								$("#NOW_FREQUENCY").val(data.NOW_FREQUENCY);   //变频运行频率
								$("#CONVERSION_ELECTRICITY").val(data.CONVERSION_ELECTRICITY);   //变频电流
								$("#MOTOR_MODE").val(data.MOTOR_MODE);   //电机运行模式0-工频，1—变频
								$("#CLIQUE_CONTROL_APERTURE1").val(data.CLIQUE_CONTROL_APERTURE1);   //蒸汽1#调节阀阀门控制开度
								$("#RUNTIME").val(data.RUNTIME);   //运行时间
								$("#DAILY_OUTPUT").val(data.DAILY_OUTPUT);   //日产量
								$("#DAILY_POWER_CONSUMPTIVE").val(data.DAILY_POWER_CONSUMPTIVE);   //日耗电
								$("#DAILY_CUMULATIVE_STEAM1").val(data.DAILY_CUMULATIVE_STEAM1);   //1#蒸汽日累积流量
								$("#DAILY_CUMULATIVE_STEAM2").val(data.DAILY_CUMULATIVE_STEAM2);   //2#蒸汽日累积流量
								$("#REMARK").val(data.REMARK);   //备注
								
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
				<td align="right" class="l-table-edit-td" style="width:80px">井号名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="JHMC" name = "JHMC" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">采集时间：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="REPORTDATE" name = "REPORTDATE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">井号编码：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="WELL_CODE" name = "WELL_CODE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">采油站：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="OILSTATIONNAME" name = "OILSTATIONNAME" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">井区块：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="QKMC" name = "QKMC" disabled="disabled"/>	
				</td>
				</tr>
				
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">注转站：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BLOCKSTATIONNAME" name = "BLOCKSTATIONNAME" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">分线名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="BRANCHING_NAME" name = "BRANCHING_NAME" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P主管压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_PRESSURE_PRESS" name = "P_PRESSURE_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P主管温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_PRESSURE_TEMP" name = "P_PRESSURE_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P副管压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_LOOPED_PRESS" name = "P_LOOPED_PRESS" disabled="disabled"/>	
				</td>
				</tr>
				
				
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">P副管温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_LOOPED_TEMP" name = "P_LOOPED_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P套管压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_DRIVEPIPE_PRESS" name = "P_DRIVEPIPE_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I主管压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="I_PRESSURE_PRESS" name = "I_PRESSURE_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I主管温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="I_PRESSURE_TEMP" name = "I_PRESSURE_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I副管压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="I_LOOPED_PRESS" name = "I_LOOPED_PRESS" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">I副管温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="I_LOOPED_TEMP" name = "I_LOOPED_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I套管压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="I_DRIVEPIPE_PRESS" name = "I_DRIVEPIPE_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">1#蒸汽温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO1STEAM_TEMP" name = "NO1STEAM_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">1#蒸汽压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO1STEAM_PRESS" name = "NO1STEAM_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">1#蒸汽差压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO1STEAM_DIFP" name = "NO1STEAM_DIFP" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">1#瞬时流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px" >
					<input type="text" id="NO1INSTANT_FLOW" name = "NO1INSTANT_FLOW" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">1#低精度总累计流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="LOWSUM_FLOW1" name = "LOWSUM_FLOW1" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">1#蒸汽瞬时干度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO1INSTANT_DRYNESS" name = "NO1INSTANT_DRYNESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">1#阀门手/自动状态：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO1CONTROL_STATUS" name = "NO1CONTROL_STATUS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">1#流量调节阀开度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO1CONTROL_APERTURE" name = "NO1CONTROL_APERTURE" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">1#流量设定值：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO1FLOW_SET" name = "NO1FLOW_SET" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#蒸汽温度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO2STEAM_TEMP" name = "NO2STEAM_TEMP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#蒸汽压力：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO2STEAM_PRESS" name = "NO2STEAM_PRESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#蒸汽差压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO2STEAM_DIFP" name = "NO2STEAM_DIFP" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#瞬时流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO2INSTANT_FLOW" name = "NO2INSTANT_FLOW" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">2#低精度总累计流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="LOWSUM_FLOW2" name = "LOWSUM_FLOW2" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#蒸汽瞬时干度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO2INSTANT_DRYNESS" name = "NO2INSTANT_DRYNESS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#阀门手/自动状态：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO2CONTROL_STATUS" name = "NO2CONTROL_STATUS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#流量调节阀开度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO2CONTROL_APERTURE" name = "NO2CONTROL_APERTURE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#流量设定值：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="NO2FLOW_SET" name = "NO2FLOW_SET" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">井下压力1：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_PRESS1" name = "P_PRESS1" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">井下压力2：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_PRESS2" name = "P_PRESS2" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度1：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP1" name = "P_TEMP1" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度2：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP2" name = "P_TEMP2" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度3：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP3" name = "P_TEMP3" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度4：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP4" name = "P_TEMP4" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度5：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP5" name = "P_TEMP5" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度6：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP6" name = "P_TEMP6" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度7：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP7" name = "P_TEMP7" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度8：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP8" name = "P_TEMP8" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度9：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP9" name = "P_TEMP9" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度10：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP10" name = "P_TEMP10" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度11：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP11" name = "P_TEMP11" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井下温度12：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="P_TEMP12" name = "P_TEMP12" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">电机工作电压A相：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="MOTOR_PRESS_A" name = "MOTOR_PRESS_A" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">电机工作电流A相：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="MOTOR_TEMP_A" name = "MOTOR_TEMP_A" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">电机工作电压B相：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="MOTOR_PRESS_B" name = "MOTOR_PRESS_B" disabled="disabled"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">电机工作电流B相：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="MOTOR_TEMP_B" name = "MOTOR_TEMP_B" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">电机工作电压C相：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="MOTOR_PRESS_C" name = "MOTOR_PRESS_C" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">电机工作电流C相：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="MOTOR_TEMP_C" name = "MOTOR_TEMP_C" disabled="disabled"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽2#调节阀门控制开度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CLIQUE_CONTROL_APERTURE2" name = "CLIQUE_CONTROL_APERTURE2" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">电机有功用电量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="ACTIVE_ENERGY" name = "ACTIVE_ENERGY" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">电机无功用电量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="IDLE_ENERGY" name = "IDLE_ENERGY" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">电机功率因数：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="POWER_FACTOR" name = "POWER_FACTOR" disabled="disabled"/>	
				</td>
				 <td align="right" class="l-table-edit-td">冲次 :</td>
                <td align="left" class="l-table-edit-td" style="width:80px" >
                	<input type="text" id="JIG_FREQUENCY" name = "JIG_FREQUENCY" disabled="disabled"/>	
                <td align="left"></td>
				</tr>
				<tr>
             
               <tr>
				<td align="right" class="l-table-edit-td" style="width:80px">冲程：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="STROKE" name = "STROKE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">抽油机运行状态：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="PUMPING_RUNNING_STATE" name = "PUMPING_RUNNING_STATE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">手/自动状态：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="MOTORSTATUS" name = "MOTORSTATUS" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">变频电压：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CONVERSION_PRESS" name = "CONVERSION_PRESS" disabled="disabled"/>	
				</td>
				 <td align="right" class="l-table-edit-td">变频运行频率:</td>
                <td align="left" class="l-table-edit-td" style="width:80px" >
                	<input type="text" id="NOW_FREQUENCY" name = "NOW_FREQUENCY" disabled="disabled"/>	
                <td align="left"></td>
				</tr>
				<tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">/变频电流：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CONVERSION_ELECTRICITY" name = "CONVERSION_ELECTRICITY" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">电机运行模式：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="MOTOR_MODE" name = "MOTOR_MODE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">蒸汽1#调节阀门控制开度：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="CLIQUE_CONTROL_APERTURE1" name = "CLIQUE_CONTROL_APERTURE1" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">运行时间：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="RUNTIME" name = "RUNTIME" disabled="disabled"/>	
				</td>
				 <td align="right" class="l-table-edit-td">日产量 :</td>
                <td align="left" class="l-table-edit-td" style="width:80px" >
                	<input type="text" id="DAILY_OUTPUT" name = "DAILY_OUTPUT" disabled="disabled"/>	
                <td align="left"></td>
				</tr>
				<tr>
               <tr>
				<td align="right" class="l-table-edit-td" style="width:80px">日耗电：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_POWER_CONSUMPTIVE" name = "DAILY_POWER_CONSUMPTIVE" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">1#蒸汽日累积流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_STEAM1" name = "DAILY_CUMULATIVE_STEAM1" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">2#蒸汽日累积流量：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="DAILY_CUMULATIVE_STEAM2" name = "DAILY_CUMULATIVE_STEAM2" disabled="disabled"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">备注：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="REMARK" name = "REMARK" disabled="disabled"/>	
				</td>
				 
				</tr>
               
				
			</table>
			<br/>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		
    </form>
</body>
</html>