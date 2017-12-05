<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>过热锅炉日报数据</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    
                 <script src="../../lib/highcharts/highcharts.js"></script>
    
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
<script type="text/javascript">
   	var eee;
   	      	   var dataflg = 1;
		var grid;
		var filter;
		var line = 0;
   	
   	
   
        $(function () {
        	//获取报表及功能按钮ｊｓ
			jQuery.ajax({
				type : 'post',
				url : 'blilercdrpd_pageInit.action',
				success : function(jsondata) { 
				if (jsondata != null && typeof(jsondata)!="undefined"){
					var data = eval('(' + jsondata + ')');
						grid = $("#maingrid").ligerGrid(data);	
						toolbar = grid.topbar.ligerGetToolBarManager();				
						
					}else{
						$.ligerDialog.error(outerror(jsondata));
					}
				},
				error : function(data) {
				
				}
			});
			
			var proData = [{ id: 1 , text: '' }];
			$("#areablock").ligerComboBox({
				url:'boilerBasicInfo_searchAcceptunit.action?orgid=boilerbasicinfo',
				valueField: 'id',
				valueFieldID: 'hidareablockid',
				textField: 'text',
				selectBoxHeight:300,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true
			});
			$("#acceptunit").ligerComboBox({
				url:'station_queryAreablockInfo.action?orgid=sqdw',
				valueField: 'id',
				valueFieldID: 'hidacceptunitid',
				textField: 'text',
				selectBoxHeight:300,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true
				
			}); 
			$("#blockstationname").ligerComboBox({
				url:'boilerBasicInfo_queryStationInfo.action',
	            valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:300,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onBeforeSelect: function (newvalue){
					liger.get("boilersName").setValue('');
				},
	            onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						setBoilerData($("#stationid").val(),proData);
					}
				}
			});
			$("#boilersName").ligerComboBox({
				url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
				valueField: 'id',
				valueFieldID: 'boilersid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
			});
			$("#group").ligerComboBox({
				url:'boilerBasicInfo_searchGroupInfo.action',
				valueField: 'id',
				valueFieldID: 'boilersid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
			});
            
		$("#txtDate").ligerDateEditor({format: "yyyy-MM-dd", labelWidth: 100, labelAlign: 'center', cancelable : false }).setValue(myDate().Format("yyyy-MM-dd"));
		$("#txtDate1").ligerDateEditor({ format: "yyyy-MM-dd", labelWidth: 100, labelAlign: 'center', cancelable : false }).setValue(myDate().Format("yyyy-MM-dd"));
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
                		
                    
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
        function setStationData(areaid,proData,oilid,clearSelecteValue) {
			jQuery.ajax({
				type : 'post',
				url:'boilerBasicInfo_queryStationInfo.action',
				data: {"areaid":areaid,"oilid":oilid,"selecteValue":clearSelecteValue},
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						if (clearSelecteValue == '4') {
							liger.get("grzh2").setData(eval('(' + jsondata + ')'));
						} else {
							liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
						}
					}
					else{
						if (clearSelecteValue == '4') {
							liger.get("grzh2").setData(proData);
						} else {
							liger.get("blockstationname").setData(proData);
						}
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}

	    function setBoilerData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'boilerBasicInfo_queryBoilersNameInfo.action',
				data: {"orgid":data},
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("boilersName").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("boilersName").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}
		
	

//为公用页面提供接口方法 
	function onJKSubmit(id,name,datatype,basid){ 
		var oilationname = '';
		var acceptunit='';
		var areablock='';
		var blockstationname='';
		var ghname='';
		var wellnum='';
		
		//$("#areablock").ligerGetComboBoxManager().setValue('');
		getBoilerInitData();
		$("#oilationname").ligerGetComboBoxManager().setValue('');
		$("#boilersName").ligerGetComboBoxManager().setValue('');
		$("#blockstationname").ligerGetComboBoxManager().setValue('');
		$("#acceptunit").ligerGetComboBoxManager().setValue('');
		$("#areablock").ligerGetComboBoxManager().setValue('');
		if(datatype=='4'){
			oilationname = name;
			//$("#acceptunit").val(name);
		}
		else if(datatype=='7'){
			blockstationname=name;
			$("#blockstationname").val(name);
		}
		else if(datatype=='14'){
			ghname=id;
		}
		else if(datatype=='10'){
			wellnum=name;
			$("#boilersName").val(name);
			
		}else{
			alert("没有找到该节点");
			return ;			
		}
		
		
		grid.setOptions({
		parms: [{ name: 'acceptunit', value:$("#acceptunit").val()},
			{ name: 'oilationname', value: oilationname},
			{ name: 'areablock', value: $("#areablock").val()},
			{ name: 'blockstationname',value: blockstationname},
			{ name: 'boilersName',value:wellnum},
			{
				name: 'startDate',
				value: $("#txtDate").val()
			},{
				name: 'endDate',
				value: $("#txtDate1").val()
			}]
		});
     	grid.loadData(true);
    }
           function loadData()
        {
        	grid.setOptions({
        		parms: [{name: 'areablock',value:  $("#areablock").val()},
    					{name: 'blockstationname',value:  $("#blockstationname").val()},
    					{name: 'boilersName',value:  $("#boilersName").val()},
    					{name: 'acceptunit',value:  $("#acceptunit").val()},
    					{name: 'group',value:  $("#group").val()},
    					{name: 'startDate',value: $("#txtDate").val()},
    					{name: 'endDate',value: $("#txtDate1").val()}
    				]
        	});
         	grid.loadData(true);
        }


        function onSubmit() {

 	if(line == 1) {
 		$('#maingrid').toggle("normal");
 		$('#mainline').toggle("normal");
 		line = 0;
 	} 	
	loadData();
}



           function getBoilerInitData() {
       		var oilText=[{ id: 1 , text: '' }];
       		//var areaText=[{ id: 1 , text: '' }];
       		var stationText=[{ id: 1 , text: '' }];
       		var boilerText=[{ id: 1 , text: '' }];
       		jQuery.ajax({
       			type : 'post',
       			url : 'boilerBasicInfo_cascadeInit.action',
       			data: {"pageid":'基础信息'},
       			success : function(jsondata) {
					var dataObj = $.parseJSON(jsondata);
					$.each(dataObj, function(key,val){
						if (key == 'oiltext') {
						oilText = val;
						}
						if (key == 'areatext'){
							areaText = val;
						}
						if (key == 'stationtext'){
							stationText = val;
						}
						if (key == 'boilertext'){
							boilerText = val;
						}
					});
					setInitData(oilText,areaText,stationText,boilerText);
				}
			});
		}
			
	function setInitData(oilText,areaText,stationText,boilerText) {
		liger.get("acceptunit").setData(oilText);
		liger.get("areablock").setData(areaText);
		liger.get("blockstationname").setData(stationText);
		liger.get("boilersName").setData(boilerText);
	}
       
		function onExport() {
			var areablock=$("#areablock").val();
			var blockstationname=$("#blockstationname").val();
			var boilersName=$("#boilersName").val();
			var acceptunit=$("#acceptunit").val();			
			var group=$("#group").val();
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var gh='';
	
			var totalNum = 0;
			var url = 'blilercdrpd_searchBoilerCrossRPD.action?areablock='+encodeURIComponent(areablock)+'&blockstationname='+encodeURIComponent(blockstationname)+'&boilersName='+encodeURIComponent(boilersName)+'&acceptunit='+encodeURIComponent(acceptunit)+'&group='+encodeURIComponent(group)+'&gh='+encodeURIComponent(gh)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('export');
			var urls = 'blilercdrpd_searchBoilerCrossRPD.action?areablock='+encodeURIComponent(areablock)+'&blockstationname='+encodeURIComponent(blockstationname)+'&boilersName='+encodeURIComponent(boilersName)+'&acceptunit='+encodeURIComponent(acceptunit)+'&group='+encodeURIComponent(group)+'&gh='+encodeURIComponent(gh)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('totalNum');
			$.ajax({
				type : 'post',
				url : urls,
				async : false,
				timeout : '30000',
				success : function (data){
					totalNum = data;
				}
			});
			if (totalNum < 10000 && totalNum > 0) {
				 $.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url });
			}
			else if(totalNum > 10000){
				$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url });
			}
			else {
				$.ligerDialog.confirm('没有可导出的数据！');
			}
		};
		
		
		
		
		
				
		
			Highcharts.setOptions({
   		global: {
       	   timezoneOffset: -8 * 60
   			 }
		});
		      




		 
 function onLine() {
 	if(typeof($("#boilersName").val()) == "undefined" || $("#boilersName").val() == "" ){
		$.ligerDialog.question("请选择需要查看曲线的锅炉名称");
		return;
	} 
	
	if (line == 0) {
		$("#maingrid").toggle("normal");
		$("#mainline").toggle("normal");
		line = 1;
	}
	DrawEChart();
}



function DrawEChart() {
	jQuery.ajax({
		type : 'post',
		url : 'blilercdrpd_searchAlarmLine.action',
		data : {
			"oilationname":$("#oilationname").val(),
			"areablock":$("#areablock").val(),
			"blockstationname":$("#blockstationname").val(),
			"boilersName":$("#boilersName").val(),
			
			"startDate":$("#txtDate").val(),
			"endDate":$("#txtDate1").val()
			
		},
		dataType: "json",
		success : function(result) {			

			 var data1 = [];
			 var data2 = [];
			 var data3 = [];
			if (result) {
				 for(var i = 0; i < result.time.length; i++){
					 var time = result.time[i];					
					 data1.push([time,result.temp[i]]);
					 data2.push([time,result.press[i]]);
					 data3.push([time,result.grdyj[i]]);

				 }
				 console.log(data1);
			 }
			
			$("#main0").highcharts({
		        chart: {
		            type: "line",
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: "锅炉蒸汽出口温度曲线 "
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: 'left',
		        	verticalAlign: 'top',
		        	x: 40
		        },
		        xAxis: {
		            type: "datetime",
		            labels: { 
						formatter: function () {
							return Highcharts.dateFormat("%Y-%m-%d %H:%M:%S", this.value);
						}
					}
		        },
		        yAxis: {
		        	title: {
		                text: " "
		            },
		            min: 0
		        },
		        tooltip: {
		        	crosshairs: true,
		        	shared: true,
		            formatter: function() {
	                    return "<b>"+ Highcharts.dateFormat("%Y-%m-%d %H:%M:%S", this.x) +"</b><br>"+
		                    this.points[0].series.name +": "+ this.points[0].y + " ℃<br>" ;
		            }
		        },
		        series: [{
		            name: "温度",
		            data: data1
		        }]
		    });
			
			$("#main1").highcharts({
		        chart: {
		            type: "line",
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: "锅炉蒸汽出口压力曲线 "
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: 'left',
		        	verticalAlign: 'top',
		        	y: -6,
		        	x: 40
		        },

		        xAxis: {
		            type: "datetime",
		            labels: { 
						formatter: function () {
							return Highcharts.dateFormat("%Y-%m-%d %H:%M:%S", this.value);
						}
					}
		        },
		        yAxis: {
		        	title: {
		                text: ""
		            },
		            min: 0
		        },
		        tooltip: {
		        	crosshairs: true,
		        	shared: true,
		            formatter: function() {
	                    return "<b>"+ Highcharts.dateFormat("%Y-%m-%d %H:%M:%S", this.x) +"</b><br>"+
		                    this.points[0].series.name +": "+ this.points[0].y + " Mpa";
		            }
		        },
		        series: [{
		            name: "压力",
		            data: data2
		        }]
		    });
		    
		    
		    
		    $("#main2").highcharts({
		        chart: {
		            type: 'line',
		            marginTop: 30,
		            marginBottom: 40,
		            marginRight: 60
		        },
		        title: {
		            text: "锅炉过热段压降曲线 "
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: 'left',
		        	verticalAlign: 'top',
		        	y: -6,
		        	x: 40
		        },

		        xAxis: {
		            type: "datetime",
		            labels: { 
						formatter: function () {
							return Highcharts.dateFormat("%Y-%m-%d %H:%M:%S", this.value);
						}
					}
		        },
		        yAxis: {
		        	title: {
		                text: ""
		            },
		            min: 0
		        },
		        tooltip: {
		        	crosshairs: true,
		        	shared: true,
		            formatter: function() {
	                    return "<b>"+ Highcharts.dateFormat("%Y-%m-%d %H:%M:%S", this.x) +"</b><br>"+
		                    this.points[0].series.name +": "+ this.points[0].y + " Mpa";
		            }
		        },
		        series: [{
		            name: "压力",
		            data: data3
		        }]
		    });
		    
		},
		error : function(data) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
};
		      
		
		
		
		
		
		
		
		       
    </script>
<style type="text/css">
html,body {
	margin: 0px;
	padding: 0px;
	background: #FAFCFC;
	position: absolute;
	width: 100%;
	height: 100%;
	hoverflow: scroll;
	overflow-y: hidden;
	overflow-x: hidden;
}
</style>
</head>
<body style="overflow-x:hidden; padding:2px;">

	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div class="searchbox">
		<form id="formsearch" class="l-form">
			<table border="0" cellspacing="1">
				<tr>
					<td align="right" class="l-table-edit-td" style="width:60px;display:none">采油站：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px;display:none">
	                	<input type="text" id="oilationname" name="oilationname"/>
	                </td>
					<td align="right" class="l-table-edit-td" style="width:70px">受汽区块：</td>
					<td align="left" class="l-table-edit-td"><input type="text"
						id="areablock" name="areablock" /></td>
					<td align="right" class="l-table-edit-td" style="width:60px">供热站：</td>
					<td align="left" class="l-table-edit-td"><input type="text"
						id="blockstationname" name="blockstationname" /></td>

					<td align="right" class="l-table-edit-td" style="width:50px">锅炉：</td>
					<td align="left" class="l-table-edit-td"><input type="text"
						id="boilersName" name="boilersName" /></td>
					<td align="right" class="l-table-edit-td" style="width:70px">受汽单位：</td>
					<td align="left" class="l-table-edit-td"><input type="text"
						id="acceptunit" name="acceptunit" /></td>
					<td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date" /></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date" /></td>
					<td style="padding-left: 20px;"><a
						href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
					<td style="padding-left: 20px;"><a href="javascript:onLine()" class="l-button" style="width:80px">动态曲线</a></td>
					
					<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td>
				</tr>
			</table>
        </form>
    </div>
 <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<div id="maingrid"></div>
		<div id="mainline" style="display: none;">
		<div id="main0" style="height:160px;width:1100px"></div>
		<div id="main1" style="height:160px;width:1100px"></div>
		<div id="main2" style="height:160px;width:1100px"></div>
	</div>	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
</body>
</html>