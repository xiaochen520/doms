var grid;
var filter;
var line = 0;
var path;
$(function () {
	
	Highcharts.setOptions({
	    global: {
	        timezoneOffset: -8 * 60
	    }
	});

	$("#areablock").ligerComboBox();
	$("#blockstationname").ligerComboBox();
	$("#wellnum").ligerComboBox();
	filter = $("#filter").ligerCheckBox();
	
	$("#start").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 120,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
	
	$("#end").ligerDateEditor(
            {
                format: "yyyy-MM-dd",
                labelWidth: 120,
                labelAlign: 'center',
                cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
	
	var proData = [{ id: 1 , text: '' }];
	jQuery.ajax({
		type : 'post',
		url : 'subcool_alarmInfoInit.action',
		success : function(jsondata) {
		if (jsondata != null && typeof(jsondata)!="undefined"){
				var data = eval('(' + jsondata + ')');
				grid = $("#maingrid").ligerGrid(data);
			}else{
				$.ligerDialog.error(outerror(jsondata));
			}
		},
		error : function(data) {
			
		}
	});
	
	$("#areablock").ligerComboBox({
		url:'sagd_queryAreablockInfo.action?arg=sagd',
		valueField: 'id',
		valueFieldID: 'areablockid',
		textField: 'text',
		selectBoxHeight:350,
		selectBoxWidth:100,
		autocomplete:true,
		hideOnLoseFocus:true,
		onBeforeSelect:function (data){
			liger.get("blockstationname").setValue('');
			liger.get("wellnum").setValue('');	
		},
		onSelected:function (data){
			$('#area_block').text(liger.get("areablock").getText());
			if (data != null && typeof(data)!="undefined" && data != '') {
				getStationInitData(data, proData);
			}
    	}
	});
	
	$("#blockstationname").ligerComboBox({
		url:'sagd_queryStationInfo.action',
        valueField: 'id',
		valueFieldID: 'stationid',
		textField: 'text',
		selectBoxHeight:400,
		selectBoxWidth:140,
        hideOnLoseFocus:true,
        autocomplete:true,
        onBeforeSelect:function (data){
        	liger.get("wellnum").setValue('');
		},
        onSelected:function (data){
			$('#blockstation_name').text(liger.get("blockstationname").getText());
			if (data != null && typeof(data)!="undefined" && data != ''){
				setWellInitData(data, proData);
			}
		}
	});
	
	$("#wellnum").ligerComboBox({
		url:'sagd_queryWellNameInfo.action',
		valueField: 'id',
		valueFieldID: 'wellnumsid',
		textField: 'text',
		selectBoxHeight:350,
		selectBoxWidth:100,
		hideOnLoseFocus:true,
        autocomplete:true,
        onBeforeSelect:function (data){
        	if(liger.get("areablock").getValue() != ''){
        		
        	}
		},
		onSelected:function (data){
			$('#well_num').text(liger.get("wellnum").getText());
		}
	});
});

function onSubmit() {
	var start = $("#start").val();
	var end = $("#end").val();
	if(typeof(start) == "undefined" || start == "" ){
		$.ligerDialog.question("开始时间不能为空");
		return;
	}
	if(typeof(end) == "undefined" || end == "" ){
		$.ligerDialog.question("结束时间不能为空");
		return;
	}
	if(new Date(start.replace(/\-/g, "\/")) > new Date(end.replace(/\-/g, "\/"))) {
		$.ligerDialog.question("结束时间必须大于开始时间！");
		return;
	}	
 	if(line == 1) {
 		$('#maingrid').toggle("normal");
 		$('#mainline').toggle("normal");
 		line = 0;
 	} 	
	loadData();
}

function loadData() {
	grid.setOptions({
		parms: [
	        { name: 'areablock', value: $("#areablock").val() },
	        { name: 'blockstationname', value: $("#blockstationname").val() },
	        { name: 'wellnum', value: $("#wellnum").val() },
	        { name: 'filter', value: filter.getValue() },
	        { name: 'start', value: $("#start").val() },
	        { name: 'end', value: $("#end").val() }
		]
	});
 	grid.loadData(true);
}

function setWellInitData(data,proData) {
	jQuery.ajax({
		type : 'post',
		url:'sagd_queryWellNameInfo.action',
		data: {"orgid":data},
		
		success : function(jsondata) {
			if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
				liger.get("wellnum").setData(eval('(' + jsondata + ')'));
			}
			else{
				liger.get("wellnum").setData(proData);
			}
		},
		error : function(jsondata) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
}

function getStationInitData(data,proData) {
	var url;
	if(data == '1') 
		url = 'sagd_queryStationInfo.action';
	else
		url = 'sagd_queryStationInfo.action?areaid='+data;
	jQuery.ajax({
		type : 'post',
		url : url,
		success : function(jsondata) {
			if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
				liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
				liger.get("wellnum").setData(proData);
			}
			else{
				liger.get("blockstationname").setData(proData);
				liger.get("wellnum").setData(proData);
			}
		},
		error : function(jsondata) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
}

function onExport() {
	var totalNum = 0;
	var areablock = $("#areablock").val();
	var blockstationname = $("#blockstationname").val();
	var wellnum = $("#wellnum").val();
	var start = $("#start").val();
	var end = $("#end").val();
	var url = 'subcool_exportAlarmInfo.action?type=export&areablock='+encodeURIComponent(areablock)+'&blockstationname='+encodeURIComponent(blockstationname)+'&wellnum='+encodeURIComponent(wellnum)+'&start='+encodeURIComponent(start)+'&end='+encodeURIComponent(end);
	var urls = 'subcool_exportAlarmInfo.action?type=total&areablock='+encodeURIComponent(areablock)+'&blockstationname='+encodeURIComponent(blockstationname)+'&wellnum='+encodeURIComponent(wellnum)+'&start='+encodeURIComponent(start)+'&end='+encodeURIComponent(end);
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
}

function onLine() {
	if(typeof($("#wellnum").val()) == "undefined" || $("#wellnum").val() == "" ){
		$.ligerDialog.question("请选择需要查看曲线的井号");
		return;
	}
	
	if (line == 0) {
		$('#maingrid').toggle("normal");
		$('#mainline').toggle("normal");
		line = 1;
	}
	DrawEChart();
	/*if($('#lineBtn').text() == "动态曲线") {
		$('#lineBtn').text("动态数据");
		DrawEChart();
	} else {
		$('#lineBtn').text("动态曲线");
		loadData();
	}*/
}

function DrawEChart() {
	jQuery.ajax({
		type : 'post',
		url : 'subcool_searchAlarmLine.action',
		data : {
			"areablock":$("#areablock").val(),
			"blockstationname":$("#blockstationname").val(),
			"wellnum":$("#wellnum").val(),
			"start":$("#start").val(),
			"end":$("#end").val()
		},
		dataType: "json",
		success : function(result) {			
			
			 var data1 = [];
			 var data2 = [];
			 var data3 = [];
			 var data4 = [];
			 var data5 = [];
			 var data6 = [];

			 var data7 = [];
			 var data8 = [];
			 var data9 = [];
			 var data10 = [];
			
			if (result) {
				 for(var i = 0; i < result.time.length; i++){
					 var time = result.time[i];	
					 data1.push([time, result.press[i]]);
					 data2.push([time, result.subcool[i]]);
					 data3.push([time, result.jig[i]]);
					 data4.push([time, result.nip[i]]);
					
				 }
				 for(var i = 0; i < result.day.length; i++){
					 var day = result.day[i];
					 data5.push([day, result.steam[i]]);
					 data6.push([day, result.output[i]]);
				 }	
				// console.log(data1);
			 }
			
			
			
			$('#main0').highcharts({
		        chart: {
		            type: 'line',
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: 'SubCool动态曲线'
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
		            type: 'datetime',
		            labels: { 
						formatter: function () {
							return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.value);
						}
					}
		        },
		        yAxis: {
		        	title: {
		                text: ''
		            },
		            min: 0
		        },
		        tooltip: {
		        	crosshairs: true,
		        	shared: true,
		            formatter: function() {
	                    return '<b>'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</b><br>'+
		                    this.points[0].series.name +': '+ this.points[0].y + ' ℃<br>' +
		                    this.points[1].series.name +': '+ this.points[1].y + ' Mpa';
		            }
		        },
		        series: [{
		            name: '操作压力',
		            data: data1
		        }, {
		            name: 'SubCool',
		            data: data2
		        }]
		    });

/*			$('#main1').highcharts({
		        chart: {
		            type: 'line',
		            marginTop: 30,
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: ''
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
				exporting: {
					filename: '井下温度动态曲线',//导出的文件名
					url:'export.action'
				},
		        xAxis: {
		            type: 'datetime',
		            labels: { 
						formatter: function () {
							return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.value);
						}
					}
		        },
		        yAxis: {
		        	title: {
		                text: ''
		            },
		            min: 0
		        },
		        tooltip: {
		            formatter: function() {
	                    return '<b>'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</b><br>'+
		                    this.series.name +': '+ this.y + ' '
		            }
		        },
		        series: [{
		            name: '井下温度1',
		            data: result.temp1
		        }, {
		            name: '井下温度2',
		            data: result.temp2
		        }, {
		            name: '井下温度3',
		            data: result.temp3
		        }, {
		            name: '井下温度4',
		            data: result.temp4
		        }, {
		            name: '井下温度5',
		            data: result.temp5
		        }, {
		            name: '井下温度6',
		            data: result.temp6
		        }, {
		            name: '井下温度7',
		            data: result.temp7
		        }, {
		            name: '井下温度8',
		            data: result.temp8
		        }, {
		            name: '井下温度9',
		            data: result.temp9
		        }, {
		            name: '井下温度10',
		            data: result.temp10
		        }, {
		            name: '井下温度11',
		            data: result.temp11
		        }, {
		            name: '井下温度12',
		            data: result.temp12
		        }]
		    });*/
			
			$('#main2').highcharts({
		        chart: {
		            type: 'line',
		            marginTop: 30,
		            marginBottom: 40,
		            marginRight: 60
		        },
		        title: {
		            text: ''
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
//				exporting: {
//					filename: '冲次--油嘴动态曲线',//导出的文件名
//					url:'export.action'
//				},
		        xAxis: {
		            type: 'datetime',
		            labels: { 
						formatter: function () {
							return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.value);
						}
					}
		        },
		        yAxis: {
		        	title: {
		                text: ''
		            },
		            min: 0
		        },
		        tooltip: {
		        	crosshairs: true,
		        	shared: true,
		            formatter: function() {
	                    return '<b>'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</b><br>'+
		                    this.points[0].series.name +': '+ this.points[0].y + ' r/min<br>' +
		                    this.points[1].series.name +': '+ this.points[1].y + ' mm';
		            }
		        },
		        series: [{
		            name: '冲次',
		            data: data3
		        }, {
		            name: '油嘴',
		            data: data4
		        }]
		    });
			
			$('#main3').highcharts({
		        chart: {
		            type: 'line',
		            marginTop: 30,
		            marginRight: 60
		        },
		        title: {
		            text: ''
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
//				exporting: {
//					filename: '注气量--产液量动态曲线',//导出的文件名
//					url:'export.action'
//				},
		        xAxis: {
		            type: 'datetime',
		            labels: { 
						formatter: function () {
							return Highcharts.dateFormat('%Y-%m-%d', this.value);
						}
					}
		        },
		        yAxis: {
		        	title: {
		                text: ''
		            },
		            min: 0
		        },
		        tooltip: {
		        	crosshairs: true,
		        	shared: true,
		            formatter: function() {
	                    return '<b>'+ Highcharts.dateFormat('%Y-%m-%d', this.x) +'</b><br>'+
		                    this.points[0].series.name +': '+ this.points[0].y + ' t/d<br>'+
		                    this.points[1].series.name +': '+ this.points[1].y + ' t/d';
		            }
		        },
		        series: [{
		            name: '注气量',
		            data: data5
		        }, {
		            name: '产液量',
		            data: data6
		        }]
		    });
			
		},
		error : function(data) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
}