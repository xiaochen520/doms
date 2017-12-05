	var eee;
   	var oilationnameORG;
      	   var dataflg = 1;
		var grid;
		var filter;
		var line = 0;
		var exportFlag = false;
		
        $(function () {
        	//获取报表及功能按钮ｊｓ
			jQuery.ajax({
				type : 'post',
				url : 'boilersuperheatrd_pageInit.action',
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
			// $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
             $("#txtDate").ligerDateEditor(
                {

                    format: "yyyy-MM-dd hh:mm",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    showTime: true,
                    labelAlign: 'center',
                    cancelable : false
            });
             $("#txtDate1").ligerDateEditor(
                {

                    format: "yyyy-MM-dd hh:mm",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
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
				oilationnameORG = name;
				//$("#oilationname").val(name);
			}
			else if(datatype=='7'){
				blockstationname=name;
				$("#blockstationname").val(name);
			}
			//else if(datatype=='14'){
		//	ghname=id;
			//}
			else if(datatype=='10'){
				wellnum=name;
				$("#boilersName").val(name);
				
			}else{
				alert("没有找到该节点");
				return ;			
			}
			
			
			grid.setOptions({
			parms: [{ name: 'acceptunit', value: $("#acceptunit").val()},
				{ name: 'oilationname', value: oilationnameORG},
				{ name: 'areablock', value: $("#areablock").val()},
				{ name: 'blockstationname',value: blockstationname},
				{ name: 'boilersName',value:wellnum},
				{name: 'startDate',value: $("#txtDate").val()},
				{name: 'endDate',value: $("#txtDate1").val()}]
			});
         	grid.loadData(true);
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
    	
	function getBoilerInitData() {
		var oilText=[{ id: 1 , text: '' }];
		var areaText=[{ id: 1 , text: '' }];
		var stationText=[{ id: 1 , text: '' }];
		var boilerText=[{ id: 1 , text: '' }];
		jQuery.ajax({
			type : 'post',
			url : 'boilerBasicInfo_cascadeInit.action',
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
	liger.get("oilationname").setData(oilText);
	//liger.get("areablock").setData(areaText);
	liger.get("blockstationname").setData(stationText);
	liger.get("boilersName").setData(boilerText);
}
           function loadData()
        {
       		exportFlag = false;
        	grid.setOptions({
        		parms: [{
					name: 'oilationname',
					value: $("#oilationname").val()
				},{
					name: 'areablock',
					value: $("#areablock").val()
				},{
					name: 'blockstationname',
					value: $("#blockstationname").val()
				},{
					name: 'boilersName',
					value: $("#boilersName").val()
				},{
					name: 'group',
					value: $("#group").val()
				},{
					name: 'acceptunit',
					value: $("#acceptunit").val()
				},{
					name: 'startDate',
					value: $("#txtDate").val()
				}
				,{
					name: 'endDate',
					value: $("#txtDate1").val()
				}
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
        
        
		
		 function onExport() {
			 var oilstationname1=$("#oilationname").val();
				var areablock1=$("#areablock").val();
				var blockstationname1=$("#blockstationname").val();
				var rulewellname1=$("#boilersName").val();
				var group = $("#group").val();
				var acceptunit = $("#acceptunit").val();
				
				var txtDate = $("#txtDate").val();
				var txtDate1 = $("#txtDate1").val();

				var totalNum = 0;
				var url = 'boilersuperheatrd_searchBoilerCrossRD.action?oilationname='+encodeURIComponent(oilstationname1)+'&areablock='+encodeURIComponent(areablock1)+'&blockstationname='+encodeURIComponent(blockstationname1)+'&boilersName='+encodeURIComponent(rulewellname1)+'&group='+encodeURIComponent(group)+'&acceptunit='+encodeURIComponent(acceptunit)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+'export';
				var urls = 'boilersuperheatrd_searchBoilerCrossRD.action?oilationname='+encodeURIComponent(oilstationname1)+'&areablock='+encodeURIComponent(areablock1)+'&blockstationname='+encodeURIComponent(blockstationname1)+'&boilersName='+encodeURIComponent(rulewellname1)+'&group='+encodeURIComponent(group)+'&acceptunit='+encodeURIComponent(acceptunit)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+'totalNum';
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
		url : 'boilersuperheatrd_searchAlarmLine.action',
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
			 var data4 = [];
			if (result) {
				 for(var i = 0; i < result.time.length; i++){
					 var time = result.time[i];					
					 data1.push([time,result.temp[i]]);
					 data2.push([time,result.press[i]]);
					 data3.push([time,result.grdyj[i]]);
					 data4.push([time,result.chq[i]]);

				 }

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
			
			
			$("#main3").highcharts({
		        chart: {
		            type: 'line',
		            marginTop: 30,
		            marginBottom: 40,
		            marginRight: 60
		        },
		        title: {
		            text: "掺混器压降曲线 "
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
		            data: data4
		        }]
		    });
		},
		error : function(data) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
};
		      
		
		
		
		