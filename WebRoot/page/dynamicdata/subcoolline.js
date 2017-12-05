var grid;
var filter;
var line = 0;
var path;
$(function () {
	

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
//			var timestamp2;
//			for(var i=0;i<(result.time).length;i++){			
//				var stringTime = result.time[i];
//				timestamp2 = Date.parse(new Date(stringTime));
//				timestamp2 = timestamp2 / 1000;
//
//			}
			
			console.info(result);
			
//			Highcharts.each(result.time, function(t) {
//			    t = new Date(t).getTime();
//			});
			
			
			
			$('#main0')option = {
			    title: {
			        text: '折线图'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['压力','subcool']
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: [resutl.time]
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'压力',
			            type:'line',
			            stack: '总量',
			            data:[result.press]
			        },
			        {
			            name:'subcool',
			            type:'line',
			            stack: '总量',
			            data:[resuet.subcool]
			        }
			    ]
			};

		},
		error : function(data) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
	

}