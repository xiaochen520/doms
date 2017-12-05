        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        var dataflg = 1;
		var grid;
		var filter;
		var line = 0;
		var path;
		var exportFlag = false;

		var line = 0;//0-显示动态数据  1-显示曲线

        
          
        $(function () {
        	 //获取报表及功能按钮ｊｓ
                 jQuery.ajax({
					type : 'post',
					url : 'sagdgh_pageInit.action',
					async : false,
					success : function(jsondata) { 		
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(data);					
							toolbar = grid.topbar.ligerGetToolBarManager();
							searchSagdnow();		
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {				
					}
				});

			 
				

  				  var proData = [{ id: 1 , text: '' }];
        		  //查询所属SAGD采油站
        		 $("#oilationname").ligerComboBox({
                	url:'rulewell_queryOilSationInfo.action?arg=sagdcaiyou',
        			valueField: 'id',
        			valueFieldID: 'oilationnameid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			autocomplete:true,
        			hideOnLoseFocus:true,
        			onSelected:function (data){
        			liger.get("ghmc").setValue('');	        		
                		if ($("#oilationnameid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
        					setGHData($("#oilationnameid").val(),proData);       					
        				}
         				else {
        					setGHData();
        				} 
                	}
        		});
        	
        	
        		//查询采油站下管汇
        		$("#ghmc").ligerComboBox({
        			url:'sagd_queryGhmcInfo.action',
                    valueField: 'id',
        			valueFieldID: 'ghmcid',
        			textField: 'text',
        			selectBoxHeight:400,
        			selectBoxWidth:140,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                    onSelected:function (data){
        				if (data != null && typeof(data)!="undefined" && data != ''){
        					setWellInitData($("#ghmcid").val(),proData);        					
        					}
        				}       			
        			}        		
        		);

		
			//关联只显示SGAD井下的管汇
			function setGHData(oilationnameid,proData) {
  			//alert(oilationnameid);
			jQuery.ajax({
				type : 'post',
				url:'sagd_queryGhmcInfo.action',
				data: {"oilationnameid":oilationnameid},	
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("ghmc").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("ghmc").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}
			   $("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd hh:mm",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd hh:mm"));
            
            $("#txtDate1").ligerDateEditor(
                {
                    format: "yyyy-MM-dd hh:mm",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
            }).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));
        });  

     function onSubmit()
        {
        if(line == 1) {
 		$("#maingrid").toggle("normal");
 		$("#mainline").toggle("normal");
 		line = 0;
 	}
        	loadData();	
        }
  
   
     function loadData() {
		exportFlag = false;

        	grid.setOptions({
        		parms: [
        		{
					name: 'oilationname',
					value: $("#oilationname").val()
				},{
					name: 'ghmc',
					value: $("#ghmc").val()
				},{
					name: 'startDate',
					value: $("#txtDate").val()
				},{
					name: 'endDate',
					value: $("#txtDate1").val()
				}
				]
        	});
  
        	 	
         	grid.loadData(true);
         	
		}
        

        
	function setInitData(oilationnameText,ghmcText) {
		liger.get("oilationname").setData(oilationnameText);
		liger.get("ghmc").setData(ghmcText);
		
	}

     
      function onExport() {
		
			var oilationname=$("#oilationname").val();
			var ghmc=$("#ghmc").val();
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			if (exportFlag) {
				oilstationname=oilstation;
				areablock=areaname;
				blockstationname=stationname;
				gh=ghname;
				wellnum=wellname;
			}
			var totalNum = 0;
			var url = 'sagdgh_searchSagdgh.action?oilationname='+encodeURIComponent(oilationname)+'&ghmc='+encodeURIComponent(ghmc)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('export');
			var urls = 'sagdgh_searchSagdgh.action?oilationname='+encodeURIComponent(oilationname)+'&ghmc='+encodeURIComponent(ghmc)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('totalNum');
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
Highcharts.setOptions({
    global: {
        timezoneOffset: -8 * 60
    }
});

function onLine() {
	if(typeof($("#ghmc").val()) == "undefined" || $("#ghmc").val() == "" ){
		$.ligerDialog.question("请选择需要查看曲线的管汇名称");
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
		url : 'sagdgh_searchAlarmLine.action',
		data : {
			"oilationname":$("#oilationname").val(),
			"ghmc":$("#ghmc").val(),
			"startDate":$("#txtDate").val(),
			"endDate":$("#txtDate1").val()
			
		},
		dataType: "json",
		success : function(result) {			

			 var data1 = [];
			 var data2 = [];
			 var data3 = [];
			 var data4 = [];
			 var data5 = [];
			 var data6 = [];	
			if (result) {
				 for(var i = 0; i < result.time.length; i++){
					 var time = result.time[i];					
					 data1.push([time,result.zhup[i]]);
					 data2.push([time,result.zhut[i]]);
					 data3.push([time,result.fup[i]]);
					 data4.push([time,result.fut[i]]);
					 data5.push([time,result.jlp[i]]);
					 data6.push([time,result.jlt[i]]);
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
		            text: "主线动态曲线"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " ℃<br>" +
		                    this.points[1].series.name +": "+ this.points[1].y + " Mpa";
		            }
		        },
		        series: [{
		            name: "主线压力",
		            data: data1
		        }, {
		            name: "主线温度",
		            data: data2
		        }]
		    });
			
			$("#main1").highcharts({
		        chart: {
		            type: 'line',
		            marginTop: 30,
		            marginBottom: 40,
		            marginRight: 60
		        },
		        title: {
		            text: "副线动态曲线"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " ℃<br>" +
		                    this.points[1].series.name +": "+ this.points[1].y + " Mpa";
		            }
		        },
		        series: [{
		            name: "副线压力",
		            data: data3
		        }, {
		            name: "副线温度",
		            data: data4
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
		            text: "计量线动态曲线"
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
		        series: [{
		            name: "计量线压力",
		            data: data5
		        }, {
		            name: "计量线温度",
		            data: data6
		        }]
		    });		
		},
		error : function(data) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
};
