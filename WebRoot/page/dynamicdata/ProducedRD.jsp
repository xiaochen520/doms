<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>采出液动态数据</title>
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
   
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>    -->
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
   	var eee;
   	var ghName='';
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'producedrd_pageInit.action',
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
			$("#oilstationname").ligerComboBox({
				url:'rulewell_queryOilSationInfo.action?arg=manifold',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					clearSelecteValue=1;
					liger.get("blockstationname").setValue('');
					liger.get("manifold").setValue('');
					
					if ($("#oilationid").val() != 1) {
						//setAreablockData($("#oilationid").val(),proData);
						setStationData('',proData,$("#oilationid").val(),clearSelecteValue);
					}else {
						getInitData();
					}
				}
			});
			$("#areablock").ligerComboBox({
				url:'rulewell_queryAreablockInfo.action?orgid=manifold',
				valueField: 'id',
				valueFieldID: 'areablockid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true
			});
			$("#blockstationname").ligerComboBox({
				url:'rulewell_queryStationInfo.action?oilid=manifold',
	            valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						liger.get("manifold").setValue('');
						setGhData($("#stationid").val(),proData);
					}
				}
			});
			
			$("#manifold").ligerComboBox({
				url:'manifoldBasicInfo_queryManifoldNameInfo.action',
				valueField: 'id',
				valueFieldID: 'ghid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				if (data != null && typeof(data)!="undefined" && data != ''){
					onChangeManiWellq( "","",$("#manifold").val())
				}
				}
			});
			$("#wellname").ligerComboBox({
				url:'producedrd_searchWellName.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
			});
             $("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd hh:mm",
                    labelWidth: 100,
                    showTime: true,
                    labelAlign: 'center',
                    cancelable : false
            });
             $("#txtDate1").ligerDateEditor(
                {
                    format: "yyyy-MM-dd hh:mm",
                    labelWidth: 100,
                    showTime: true,
                    labelAlign: 'center',
                    cancelable : false
            });
            $("#pageloading").hide();
			$("#txtDate").ligerDateEditor({ showTime: true, showTime: true,labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(myDate().Format("yyyy-MM-dd hh:mm"));
			$("#txtDate1").ligerDateEditor({ showTime: true, showTime: true,labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));
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
    			url:'manifoldBasicInfo_queryStationInfo.action',
    			data: {"oilid":oilid},
    			timeout : '3000',
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("blockstationname").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				alert("请求数据失败，请重新查询");
    			}
    		});
    	}
        function onChangeManiWellq(groupName,StationName, ManiName){
	    	jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchOnChangeManiWell.action?arg=manifold',
				data: {"groupName":groupName,"StationName":StationName,"ManiName":ManiName},
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("wellname").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("wellname").setData("");
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		    }
        function setGhData(data,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'manifoldBasicInfo_queryManifoldNameInfo.action',
				data: {"orgid":data},
    			timeout : '3000',
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("manifold").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("manifold").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				alert("请求数据失败，请重新查询");
    			}
    		});
    	}
    	
        function getInitData() {
    		var oilText=[{ id: 1 , text: '' }];
    		var areaText=[{ id: 1 , text: '' }];
    		var stationText=[{ id: 1 , text: '' }];
    		var manifoldText=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'manifoldBasicInfo_cascadeInit.action',
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
    					if (key == 'ghmctext'){
    						manifoldText = val;
    					}
    				});
    				setInitData(oilText,areaText,stationText,manifoldText);
    			}
    		});
    	}
        function setInitData(oilText,areaText,stationText,manifoldText) {
    		liger.get("oilstationname").setData(oilText);
    		liger.get("areablock").setData(areaText);
    		liger.get("blockstationname").setData(stationText);
    		liger.get("manifold").setData(manifoldText);
    	}
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var oilstationname='';
			var blockstationname='';
			var wellname='';
			ghName = '';
			getInitData();
			$("#areablock").ligerGetComboBoxManager().setValue('');
			$("#blockstationname").ligerGetComboBoxManager().setValue('');
			$("#manifold").ligerGetComboBoxManager().setValue('');
			$("#oilstationname").ligerGetComboBoxManager().setValue(''); 
			$("#wellname").ligerGetComboBoxManager().setValue(''); 
			if(datatype=='4'){
				oilstationname=name;
				$("#oilstationname").val(name);
			}
			if(datatype=='7'){
				blockstationname=name;
				$("#blockstationname").val(name);
			}
			if(datatype=='8'){
				ghName=name;
				$("#manifold").val(name);
			}
			if(datatype=='9'){
				wellname=name;
				$("#wellname").val(name);
			}
			grid.setOptions({
				parms: [{ name: 'oilstationname', value: oilstationname },
					{name: 'blockstationname', value: blockstationname },
					{name: 'manifold', value: ghName },
					{name: 'wellname', value: wellname },
					{name: 'date', value: txtDate},
					{name: 'date1', value: txtDate1},
				]
        	});
         	grid.loadData(true);
		}
           function onSubmit()
        {

			var oilstationname=$("#oilstationname").val();
			var areablock=$("#areablock").val();
			var blockstationname=$("#blockstationname").val();
			var manifold=$("#manifold").val();			
			var wellname=$("#wellname").val();			
        	grid.setOptions({
        		parms: [{name: 'oilstationname',value: oilstationname},
				{name: 'areablock',value: areablock},
				{name: 'blockstationname',value: blockstationname},
				{name: 'manifold',value: manifold},
				{name: 'wellname',value: wellname},
				{name: 'date',value: $("#txtDate").val()},
				{name: 'date1',value: $("#txtDate1").val()}
				]
        	});
         	grid.loadData(true);
        }
		
		function onExport() {
			var oilstationname=$("#oilstationname").val();
			var areablock=$("#areablock").val();
			var blockstationname=$("#blockstationname").val();
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var gh = $("#manifold").val();
			var wellname=$("#wellname").val();		
			var totalNum = 0;
			var url = 'producedrd_searchProducedRD.action?oilstationname='+encodeURIComponent(oilstationname)+'&areablock='+encodeURIComponent(areablock)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('export')+'&blockstationname='+encodeURIComponent(blockstationname)+'&manifold='+encodeURIComponent(gh)+'&wellname='+encodeURIComponent(wellname);
			var urls = 'producedrd_searchProducedRD.action?oilstationname='+encodeURIComponent(oilstationname)+'&areablock='+encodeURIComponent(areablock)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('totalNum')+'&blockstationname='+encodeURIComponent(blockstationname)+'&manifold='+encodeURIComponent(gh)+'&wellname='+encodeURIComponent(wellname);
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
		
		
		 function onLine() {
	if(typeof($("#rulewellname1").val()) == "undefined" || $("#rulewellname1").val() == "" ){
		$.ligerDialog.question("请选择需要查看曲线的井号");
		return;
	}
	
	if (line == 0) {
		$('#maingrid').toggle("normal");
		$('#mainline').toggle("normal");
		line = 1;
	}
		DrawEChart();
	
	};
	
 	function DrawEChart() {
	jQuery.ajax({
		type : 'post',
		url : 'subcool_searchAlarmLine.action',
		data : {
			"areablock":$("#areablock").val(),
			"blockstationname":$("#blockstationname").val(),
			"wellnum":$("#rulewellname1").val(),
			"start":$("#txtDate").val(),
			"end":$("#txtDate1").val()
		},
		dataType: "json",
		success : function(result) {
			$("#main0").highcharts({
		        chart: {
		            type: "line",
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: "电机电压曲线"
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: "left",
		        	verticalAlign: "top",
		        	x: 40
		        },
				exporting: {
					filename: "电机电压曲线",//导出的文件名
					url:"export.action"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " ℃<br>" +
 		                    this.points[1].series.name +": "+ this.points[1].y + " Mpa";
		            }
		        },
		        /* series: [{
		            name: "操作压力",
		            data: result.press
		        }, {
		            name: "SubCool",
		            data: result.subcool
		        }] */
		        
		         series: [{
		            name: "电机A相电压",
		            data: result.temp1
		        }, {
		            name: "电机B相电压",
		            data: result.temp2
		        }, {
		            name: "电机C相电压",
		            data: result.temp3
		        }]
		    });


			$("#main1").highcharts({
		        chart: {
		            type: "line",
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: "电机电流曲线"
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: "left",
		        	verticalAlign: "top",
		        	x: 40
		        },
				exporting: {
					filename: "电机电流曲线",//导出的文件名
					url:"export.action"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " ℃<br>" +
 		                    this.points[1].series.name +": "+ this.points[1].y + " Mpa";
		            }
		        },
		        /* series: [{
		            name: "操作压力",
		            data: result.press
		        }, {
		            name: "SubCool",
		            data: result.subcool
		        }] */
		        
		         series: [{
		            name: "电机A相电流",
		            data: result.temp1
		        }, {
		            name: "电机B相电流",
		            data: result.temp2
		        }, {
		            name: "电机C相电流",
		            data: result.temp3
		        }]
		    });


			$("#main2").highcharts({
		        chart: {
		            type: "line",
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: "电机电量曲线"
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: "left",
		        	verticalAlign: "top",
		        	x: 40
		        },
				exporting: {
					filename: "电机电量曲线",//导出的文件名
					url:"export.action"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " ℃<br>" +
 		                    this.points[1].series.name +": "+ this.points[1].y + " Mpa";
		            }
		        },
		        /* series: [{
		            name: "操作压力",
		            data: result.press
		        }, {
		            name: "SubCool",
		            data: result.subcool
		        }] */
		        
		         series: [{
		            name: "电机有功电量",
		            data: result.temp1
		        }, {
		            name: "电机无功电量",
		            data: result.temp2
		        }]
		    });



			$("#main1").highcharts({
		        chart: {
		            type: "line",
		            marginTop: 30,
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: ""
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: "left",
		        	verticalAlign: "top",
		        	y: -6,
		        	x: 40
		        },
				exporting: {
					filename: "井下温度动态曲线",//导出的文件名
 					url:"export.action"
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
		            formatter: function() {
	                    return "<b>"+ Highcharts.dateFormat("%Y-%m-%d %H:%M:%S", this.x) +"</b><br>"+
		                    this.series.name +": "+ this.y + " ";
		            }
		        },
		        series: [{
		            name: "井下温度1",
		            data: result.temp1
		        }, {
		            name: "井下温度2",
		            data: result.temp2
		        }, {
		            name: "井下温度3",
		            data: result.temp3
		        }, {
		            name: "井下温度4",
		            data: result.temp4
		        }, {
		            name: "井下温度5",
		            data: result.temp5
		        }, {
		            name: "井下温度6",
		            data: result.temp6
		        }, {
		            name: "井下温度7",
		            data: result.temp7
		        }, {
		            name: "井下温度8",
		            data: result.temp8
		        }, {
		            name: "井下温度9",
		            data: result.temp9
		        }, {
		            name: "井下温度10",
		            data: result.temp10
		        }, {
		            name: "井下温度11",
		            data: result.temp11
		        }, {
		            name: "井下温度12",
		            data: result.temp12
		        }]
		    });
		    $("#main2").highcharts({
		        chart: {
		            type: "line",
		            marginTop: 30,
		            marginBottom: 40,
		            marginRight: 60
		        },
		        title: {
		            text: ""
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: "left",
		        	verticalAlign: "top",
		        	y: -6,
		        	x: 40
		        },
				exporting: {
					filename: "冲次--油嘴动态曲线",//导出的文件名
					url:"export.action"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " r/min<br>" +
		                    this.points[1].series.name +": "+ this.points[1].y + " mm";
		            }
		        },
		        series: [{
		            name: "冲次",
		            data: result.jig
		        }, {
 		            name: "油嘴",
		            data: result.nip
		        }]
		    });
			
			$("#main3").highcharts({
		        chart: {
		            type: "line",
		            marginTop: 30,
		            marginRight: 60
		        },
		        title: {
		            text: ""
		        },
		        credits: {
		        	enabled: false
		        },
		        legend: {
		        	align: "left",
 		        	verticalAlign: "top",
		        	y: -6,
		        	x: 40
		        },
				exporting: {
					filename: "注气量--产液量动态曲线",//导出的文件名
					url:"export.action"
				},
		        xAxis: {
		            type: "datetime",
		            labels: { 
						formatter: function () {
							return Highcharts.dateFormat("%Y-%m-%d", this.value);
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
	                    return "<b>"+ Highcharts.dateFormat("%Y-%m-%d", this.x) +"</b><br>"+
 		                    this.points[0].series.name +": "+ this.points[0].y + " t/d<br>"+
 		                    this.points[1].series.name +": "+ this.points[1].y + " t/d";
		            }
		        },
		        series: [{
		            name: "注气量",
		            data: result.steam
		        }, {
		            name: "产液量",
		            data: result.output
		        }]
		    });
			
		},
		error : function(data) {
			$.ligerDialog.question("画曲线失败,请求数据失败，请重新查询");
		}
	});
	};
	</script>
    <style type="text/css"> 
    html,body
	{
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
    </style>
</head>
<body style="overflow-x:hidden; padding:2px;">
 
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form"> 
        	<table border="0" cellspacing="1">
				<tr>
					<td align="right" class="l-table-edit-td" style="width:60px">采油站：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="oilstationname" name="oilstationname"/>
	                </td>
					<td align="right" class="l-table-edit-td" style="width:60px">区块：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="areablock" name="areablock"/>
	                </td>
					<td align="right" class="l-table-edit-td" style="width:60px">注转站：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="blockstationname" name="blockstationname"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">管汇：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="manifold" name="manifold"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">井号：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="wellname" name="wellname"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
				<!-- 	<td style="padding-left: 20px;"><a id="lineBtn" href="javascript:onLine()" class="l-button" style="width:100px">动态曲线</a></td> -->
					<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td>
				</tr>
			</table>
        </form>
    </div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div id="maingrid"></div>

 
</body>
</html>