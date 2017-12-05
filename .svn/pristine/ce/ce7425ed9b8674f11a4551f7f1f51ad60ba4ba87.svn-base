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
	var rows = parent.grid.getCheckedRows();
	var sagddid='';
	var sagdid='';
	var rowid='';
	$(function () {
		$("#jhmc").ligerComboBox({
			url:'sagd_queryWellNameInfo.action',
			valueField: 'id',
			valueFieldID: 'jhid',
			textField: 'text',
			selectBoxHeight:150,
			width: 180,
			hideOnLoseFocus:true
		});
		/* $("#reportDate").ligerDateEditor({ showTime: true, labelWidth: 150, labelAlign: 'left' }); */
		$("#reportDate").ligerDateEditor({format: "yyyy-MM-dd",labelWidth: 100,labelAlign: 'center',cancelable : false
		});
		
		if(rows.length == 1){
			//var rowData = JSON2.stringify(rows);
			//var rowData = JSON2.stringify(parent.grid.getData());
			var rowt=parent.grid.getRow(1);
			$(rows).each(function (){
				sagddid = this.SAGDDID;
				sagdid=this.SAGDID;
				rowid=this.__index;
				rowid=chk(rowid);//序号 偶数为i井
				rowt=parent.grid.getRow(rowid);
				$("#jhmc").ligerGetComboBoxManager().selectValue(this.SAGDID);
				$("#reportDate").val($.trim(this.REPORT_DATE));
				$("#sl").val($.trim(this.SL));
				$("#bj").val($.trim(this.BJ));
				$("#stroke").val($.trim(this.STROKE));
				$("#jigFrequency").val($.trim(this.JIG_FREQUENCY));
				$("#press1").val($.trim(this.PRESS1));
				$("#press2").val($.trim(this.PRESS2));
				$("#bz").val($.trim(this.REMARK));
				if (checkNum(rowid) == 1) {//序号 偶数为i井
					$(rowt).each(function (){
						$("#YZI").val($.trim(this.YZ));
						$("#pressurePRESSI").val($.trim(this.PRESSURE_PRESS));
						$("#pressureTempI").val($.trim(this.PRESSURE_TEMP));
						$("#zghyI").val($.trim(this.ZGHY));
						$("#zgyyI").val($.trim(this.ZGYY));
						$("#loopedPressI").val($.trim(this.LOOPED_PRESS));
						$("#loopedTempI").val($.trim(this.LOOPED_TEMP));
						$("#fghyI").val($.trim(this.FGHY));
						$("#drivepipePressI").val($.trim(this.DRIVEPIPE_PRESS));
						$("#ylI").val($.trim(this.YL));
						$("#steamPressI").val($.trim(this.STEAM_PRESS));
						$("#steamTempI").val($.trim(this.STEAM_TEMP));
						$("#instantDrynessI").val($.trim(this.INSTANT_DRYNESS));
						$("#instantFlow").val($.trim(this.INSTANT_FLOW));
						$("#rlI").val($.trim(this.RL));
						$("#dailyCumulativeSteamI").val($.trim(this.DAILY_CUMULATIVE_STEAM));
						$("#temp1I").val($.trim(this.TEMP1));
						$("#temp2I").val($.trim(this.TEMP2));
						$("#temp3I").val($.trim(this.TEMP3));
						$("#temp4I").val($.trim(this.TEMP4));
						$("#temp5I").val($.trim(this.TEMP5));
						$("#temp6I").val($.trim(this.TEMP6));
					});
					$("#YZP").val($.trim(this.YZ));
					$("#pressurePRESSP").val($.trim(this.PRESSURE_PRESS));
					$("#pressureTempP").val($.trim(this.PRESSURE_TEMP));
					$("#zghyP").val($.trim(this.ZGHY));
					$("#zgyyP").val($.trim(this.ZGYY));
					$("#loopedPressP").val($.trim(this.LOOPED_PRESS));
					$("#loopedTempP").val($.trim(this.LOOPED_TEMP));
					$("#fghyP").val($.trim(this.FGHY));
					$("#drivepipePressP").val($.trim(this.DRIVEPIPE_PRESS));
					$("#ylP").val($.trim(this.YL));
					$("#steamPressP").val($.trim(this.STEAM_PRESS));
					$("#steamTempP").val($.trim(this.STEAM_TEMP));
					$("#instantDrynessP").val($.trim(this.INSTANT_DRYNESS));
					$("#instantFlowP").val($.trim(this.INSTANT_FLOW));
					$("#rlP").val($.trim(this.RL));
					$("#dailyCumulativeSteamP").val($.trim(this.DAILY_CUMULATIVE_STEAM));
					$("#temp1P").val($.trim(this.TEMP1));
					$("#temp2P").val($.trim(this.TEMP2));
					$("#temp3P").val($.trim(this.TEMP3));
					$("#temp4P").val($.trim(this.TEMP4));
					$("#temp5P").val($.trim(this.TEMP5));
					$("#temp6P").val($.trim(this.TEMP6));
				}
				else {
					$(rowt).each(function (){
						$("#YZP").val($.trim(this.YZ));
						$("#pressurePRESSP").val($.trim(this.PRESSURE_PRESS));
						$("#pressureTempP").val($.trim(this.PRESSURE_TEMP));
						$("#zghyP").val($.trim(this.ZGHY));
						$("#zgyyP").val($.trim(this.ZGYY));
						$("#loopedPressP").val($.trim(this.LOOPED_PRESS));
						$("#loopedTempP").val($.trim(this.LOOPED_TEMP));
						$("#fghyP").val($.trim(this.FGHY));
						$("#drivepipePressP").val($.trim(this.DRIVEPIPE_PRESS));
						$("#ylP").val($.trim(this.YL));
						$("#steamPressP").val($.trim(this.STEAM_PRESS));
						$("#steamTempP").val($.trim(this.STEAM_TEMP));
						$("#instantDrynessP").val($.trim(this.INSTANT_DRYNESS));
						$("#instantFlowP").val($.trim(this.INSTANT_FLOW));
						$("#rlP").val($.trim(this.RL));
						$("#dailyCumulativeSteamP").val($.trim(this.DAILY_CUMULATIVE_STEAM));
						$("#temp1P").val($.trim(this.TEMP1));
						$("#temp2P").val($.trim(this.TEMP2));
						$("#temp3P").val($.trim(this.TEMP3));
						$("#temp4P").val($.trim(this.TEMP4));
						$("#temp5P").val($.trim(this.TEMP5));
						$("#temp6P").val($.trim(this.TEMP6));
					});
					$("#YZI").val($.trim(this.YZ));
					$("#pressurePRESSI").val($.trim(this.PRESSURE_PRESS));
					$("#pressureTempI").val($.trim(this.PRESSURE_TEMP));
					$("#zghyI").val($.trim(this.ZGHY));
					$("#zgyyI").val($.trim(this.ZGYY));
					$("#loopedPressI").val($.trim(this.LOOPED_PRESS));
					$("#loopedTempI").val($.trim(this.LOOPED_TEMP));
					$("#fghyI").val($.trim(this.FGHY));
					$("#drivepipePressI").val($.trim(this.DRIVEPIPE_PRESS));
					$("#ylI").val($.trim(this.YL));
					$("#steamPressI").val($.trim(this.STEAM_PRESS));
					$("#steamTempI").val($.trim(this.STEAM_TEMP));
					$("#instantDrynessI").val($.trim(this.INSTANT_DRYNESS));
					$("#instantFlow").val($.trim(this.INSTANT_FLOW));
					$("#rlI").val($.trim(this.RL));
					$("#dailyCumulativeSteamI").val($.trim(this.DAILY_CUMULATIVE_STEAM));
					$("#temp1I").val($.trim(this.TEMP1));
					$("#temp2I").val($.trim(this.TEMP2));
					$("#temp3I").val($.trim(this.TEMP3));
					$("#temp4I").val($.trim(this.TEMP4));
					$("#temp5I").val($.trim(this.TEMP5));
					$("#temp6I").val($.trim(this.TEMP6));
				}
			});
		}
		//parent.grid.
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
				jQuery.ajax({
					type : 'post',
					url : 'sagddrpd_addOrUpdateSagddRPD.action',
					data: {"sagddid":sagddid,"jhmc":$("#jhmc").ligerGetComboBoxManager().getValue(),
						"reportDate":$("#reportDate").val(),
						"sl":$("#sl").val(),
						"bj":$("#bj").val(),
						"stroke":$("#stroke").val(),
						"jigFrequency":$("#jigFrequency").val(),
						"YZP":$("#YZP").val(),
						"pressurePRESSP":$("#pressurePRESSP").val(),
						"pressureTempP":$("#pressureTempP").val(),
						"zghyP":$("#zghyP").val(),
						"zgyyP":$("#zgyyP").val(),
						"loopedPressP":$("#loopedPressP").val(),
						"loopedTempP":$("#loopedTempP").val(),
						"fghyP":$("#fghyP").val(),
						"drivepipePressP":$("#drivepipePressP").val(),
						"ylP":$("#ylP").val(),
						"steamPressP":$("#steamPressP").val(),
						"steamTempP":$("#steamTempP").val(),
						"instantDrynessP":$("#instantDrynessP").val(),
						"instantFlowP":$("#instantFlowP").val(),
						"rlP":$("#rlP").val(),
						"dailyCumulativeSteamP":$("#dailyCumulativeSteamP").val(),
						"temp1P":$("#temp1P").val(),
						"temp2P":$("#temp2P").val(),
						"temp3P":$("#temp3P").val(),
						"temp4P":$("#temp4P").val(),
						"temp5P":$("#temp5P").val(),
						"temp6P":$("#temp6P").val(),
						"YZI":$("#YZI").val(),
						"pressurePRESSI":$("#pressurePRESSI").val(),
						"pressureTempI":$("#pressureTempI").val(),
						"zghyI":$("#zghyI").val(),
						"zgyyI":$("#zgyyI").val(),
						"loopedPressI":$("#loopedPressI").val(),
						"loopedTempI":$("#loopedTempI").val(),
						"fghyI":$("#fghyI").val(),
						"drivepipePressI":$("#drivepipePressI").val(),
						"ylI":$("#ylI").val(),
						"steamPressI":$("#steamPressI").val(),
						"steamTempI":$("#steamTempI").val(),
						"instantDrynessI":$("#instantDrynessI").val(),
						"instantFlow":$("#instantFlow").val(),
						"rlI":$("#rlI").val(),
						"dailyCumulativeSteamI":$("#dailyCumulativeSteamI").val(),
						"temp1I":$("#temp1I").val(),
						"temp2":$("#temp2").val(),
						"temp3I":$("#temp3I").val(),
						"temp4I":$("#temp4I").val(),
						"temp5I":$("#temp5I").val(),
						"temp6I":$("#temp6I").val(),
						"press1":$("#press1").val(),
						"press2":$("#press2").val(),
						"bz":$("#bz").val()},
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
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
			}
		});
		$("form").ligerForm();
	});
	function chk(num){
		return num?num%2?num-1:num+1:"1";
	}
	function checkNum(num){
		return num?num%2?'1':'2':"0";
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
	<form name="form" method="post" id="form">
		<div id="errorLabelContainer" class="l-text-invalid">
		</div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
		</table>
		<div class="searchtitle">
		<span>SAGD井日报数据&nbsp;<div id="errormsg"></div></span><img src="../../lib/icons/32X32/user.gif" />
		</div>
		<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">井号名称：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="jhmc" name = "jhmc"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">采集时间：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="reportDate" name = "reportDate"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">时率：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="sl" name = "sl"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">泵径：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="bj" name = "bj"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">冲程：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="stroke" name = "stroke"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">冲次：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="jigFrequency" name = "jigFrequency"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井主管油嘴(mm)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="YZP" name = "YZP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井主管压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pressurePRESSP" name = "pressurePRESSP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井主管温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pressureTempP" name = "pressureTempP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井主管回压(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="zghyP" name = "zghyP"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">P井副管油嘴(mm)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="zgyyP" name = "zgyyP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井副管压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="loopedPressP" name = "loopedPressP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井副管温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="loopedTempP" name = "loopedTempP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井副管回压(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="fghyP" name = "fghyP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井套压(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="drivepipePressP" name = "drivepipePressP"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">P井计量液量(m3/d)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="ylP" name = "ylP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井蒸汽计量压力(MPa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="steamPressP" name = "steamPressP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井蒸汽计量温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="steamTempP" name = "steamTempP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井蒸汽计量干度(%)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="instantDrynessP" name = "instantDrynessP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井蒸汽计量瞬时流量(m3/h)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="instantFlowP" name = "instantFlowP"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">P井蒸汽计量热量(KJ)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rlP" name = "rlP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井蒸汽计量累计流量(m3)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="dailyCumulativeSteamP" name = "dailyCumulativeSteamP"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井井温度1(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp1P" name = "temp1P"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井井温度2(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp2P" name = "temp2P"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井井温度3(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp3P" name = "temp3P"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">P井井温度4(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp4P" name = "temp4P"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井井温度5(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp5P" name = "temp5P"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">P井井温度6(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp6P" name = "temp6P"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井主管油嘴(mm)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="YZI" name = "YZI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井主管压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pressurePRESSI" name = "pressurePRESSI"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">I井主管温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="pressureTempI" name = "pressureTempI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井主管回压(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="zghyI" name = "zghyI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井副管油嘴(mm)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="zgyyI" name = "zgyyI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井副管压力(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="loopedPressI" name = "loopedPressI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井副管温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="loopedTempI" name = "loopedTempI"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">I井副管回压(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="fghyI" name = "fghyI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井套压(Mpa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="drivepipePressI" name = "drivepipePressI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井计量液量(m3/d)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="ylI" name = "ylI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井蒸汽计量压力(MPa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="steamPressI" name = "steamPressI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井蒸汽计量温度(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="steamTempI" name = "steamTempI"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">I井蒸汽计量干度(%)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="instantDrynessI" name = "instantDrynessI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井蒸汽计量瞬时流量(m3/h)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="instantFlow" name = "instantFlow"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井蒸汽计量热量(KJ)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="rlI" name = "rlI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井蒸汽计量累计流量(m3)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="dailyCumulativeSteamI" name = "dailyCumulativeSteamI"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井井温度1(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp1I" name = "temp1I"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">I井井温度2(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp2" name = "temp2"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井井温度3(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp3I" name = "temp3I"/>	
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:80px">I井井温度4(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp4I" name = "temp4I"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井井温度5(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp5I" name = "temp5I"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">I井井温度6(℃)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="temp6I" name = "temp6I"/>	
				</td>
				</tr>
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">井压力1(MPa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="press1" name = "press1"/>	
				</td>
				<td align="right" class="l-table-edit-td" style="width:80px">井压力2(MPa)：</td>
				<td align="left" class="l-table-edit-td" style="width:80px">
					<input type="text" id="press2" name = "press2"/>	
				</td>
				</tr>
				<tr>
                <td align="right" class="l-table-edit-td">备注 :</td>
                <td align="left" class="l-table-edit-td" style="width:490px" colspan="7"><textarea name="bz" id="bz" ></textarea></td>
                <td align="left"></td>
				</tr>
				<!-- <tr>
					<td style=" text-align:center;"><input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /></td>
				</tr> -->
			</table>
			<br/>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<center><div style="width:80px;text-align:center"><input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /></div></center>
    </form>
    <div style="display:none"> </div>
</body>
</html>