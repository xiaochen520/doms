<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>SAGD18-3#站动态数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="../../lib/highcharts/highcharts.js"></script>
    <script src="../../lib/highcharts/exporting.js"></script>   
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
        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        var dataflg = 1;
        var ghname='';
        var oilstation;
        var areaname='';
		var stationname='';
		var wellname='';
		var exportFlag = false;

		var line = 0;//0-显示动态数据  1-显示曲线
		Highcharts.setOptions({ global: { useUTC: false } }); 
        $(function () {

            
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'sagd183dt_pageInit.action',
					async : false,
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
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
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
        

     
     	function onExport() {
		   var txtDate = $("#txtDate").val();
       	   var txtDate1 = $("#txtDate1").val();		

		var totalNum = 0;


			var url = 'sagd183dt_searchSagd183.action?startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('export');
		   var urls = 'sagd183dt_searchSagd183.action?startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('totalNum');
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
				 $.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.open(url); });
			}else if(totalNum > 10000){
				$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.open(url);});
			}else{
				$.ligerDialog.confirm('没有可导出的数据！');
			};
		} ;
		




function onLine() {
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
		url : 'sagd183dt_searchAlarmLine.action',
		data : {
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
			 var data7 = [];
			 var data8 = [];
			if (result) {
				 for(var i = 0; i < result.time.length; i++){
					 var time = result.time[i];					
					 data1.push([time,result.yl1[i]]);
					 data2.push([time,result.gd1[i]]);
					 data3.push([time,result.yl2[i]]);
					 data4.push([time,result.gd2[i]]);
					 data5.push([time,result.yl3[i]]);
					 data6.push([time,result.gd3[i]]);
					 data7.push([time,result.jkzx[i]]);
					 data8.push([time,result.ckzx[i]]);
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
		            text: "1#分离器曲线"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " Mpa" +
		                    this.points[1].series.name +": "+ this.points[1].y + " mm";
		            }
		        },
		        series: [{
		            name: "气相出口压力",
		            data: data1
		        }, {
		            name: "电液位高度",
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
		            text: "2#分离器曲线"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " Mpa" +
		                    this.points[1].series.name +": "+ this.points[1].y + " mm";
		            }
		        },
		        series: [{
		            name: "汽相出口压力",
		            data: data3
		        }, {
		            name: "电液位高度",
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
		            text: "3#分离器曲线"
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
		                    this.points[0].series.name +": "+ this.points[0].y + " Mpa" +
		                    this.points[1].series.name +": "+ this.points[1].y + " mm";
		            }
		        },
		        series: [{
		            name: "汽相出口压力",
		            data: data5
		        }, {
		            name: "电液位高度",
		            data: data6
		        }]
		    });		
		    
/*		    
		    $("#main3").highcharts({
		        chart: {
		            type: 'line',
		            marginTop: 30,
		            marginBottom: 40,
		            marginRight: 60
		        },
		        title: {
		            text: "泵进口出口曲线"
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
		            name: "泵进口主线压力",
		            data: data7
		        }, {
		            name: "泵出口主线压力",
		            data: data8
		        }]
		    });		
	*/	    
		},
		
		error : function(data) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});

};


		/*var chart = $('#container').highcharts();
		for(var i=0; i < chart.series.length; i++) {
			if(i != 0) chart.series[i].hide();
		}*/

    </script>
    <style type="text/css">
             html,body
			{
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
           
          
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;">
 
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form"> 
        	<table border="0" cellspacing="1">
				<tr>
				
	                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
					 <td style="padding-left: 20px;"><a href="javascript:onLine()" class="l-button" style="width:80px">动态曲线</a></td>
					
					 <td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td>
				</tr>
			</table>
        </form>
    </div>
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div id="maingrid"></div>
	<div id="mainline" style="display: none;">
		<div id="main0" style="height:160px;width:1300px"></div>
		<div id="main1" style="height:160px;width:1300px"></div>
		<div id="main2" style="height:160px;width:1300px"></div>
	<!-- 	<div id="main3" style="height:160px;width:1300px"></div> -->
	</div>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div> 
 
</body>
</html>