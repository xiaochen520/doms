<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>风城作业区各采油站综合日报表</title>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="pragma" content="no-cache"></meta>
<meta http-equiv="cache-control" content="no-cache"></meta>
<meta http-equiv="expires" content="0"></meta>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>

<link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="../../lib/jquery/jquery-1.5.2.min.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerRadio.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script src="../../lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="../../lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script src="../../lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>

<script src="../../lib/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->
<link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />
<script src="../../lib/js/common.js" type="text/javascript"></script>

<script src="../../lib/json2.js" type="text/javascript"></script>
<script src="../../noBackspace.js" type="text/javascript"></script>
<script src="../../lib/myday.js" type="text/javascript"></script>
<!-- <script src="../../columnnamedisplay.js" type="text/javascript"></script> -->

    <script src="../../lib/highcharts/highcharts.js"></script>
<!--     <script src="../../lib/highcharts/exporting.js"></script>    -->


<script type="text/javascript">
      var grid = null;
		var line = 0;
     $(function(){
            var yearid = $('#SYear');    //年所在的控件
            var monthid = $('#SMonth');    //月所在的控件
            //var dayid = $('#SDay')    //天所在的控件
            var myDate = new Date();
            var year = myDate.getFullYear();
            
            var month=myDate.getMonth()+1;
            
            for(var i=(year-20);i<=(year+20);i++){
                yearid.append('<option value="'+i+'">'+i+'</option>');
            }
                for(var i=1;i<=12;i++){
                    monthid.append('<option value="'+i+'">'+i+'</option>');
                }
            
           $("#SYear").val(year);
           $("#SMonth").val(month);
           
            //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'fxjlrb_pageInit.action',
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
	
     function onSubmit(){
         if(line == 1) {
 		$("#maingrid").toggle("normal");
 		$("#mainline").toggle("normal");
 		line = 0;
 		}
        	loadData();	
      }
        
        function loadData(){
        	grid.setOptions({
        		parms: [
        		{
					name: 'SYear',
					value: $("#SYear").val()
				},{
					name: 'SMonth',
					value: $("#SMonth").val()
				}
				]
        	});
         	grid.loadData(true);
        }
        
	function onExport() {
		var SYear = $("#SYear").val();
		var SMonth = $("#SMonth").val();
		var url = "fxjlrb_exportFXJLRB.action?SYear="+encodeURIComponent(SYear) +"&SMonth="+encodeURIComponent(SMonth)+ "&reportName="+encodeURIComponent('二号稠油联合处理站-水质监测报表');
			$.ligerDialog.confirm('确定导出吗?',function (yes) {
				if (yes) {
					window.location.href= url;
					/* if (!(window.location.href= url)) {
						$.ligerDialog.confirm('没有可导出的数据！');
					} */
				}
			});
		
	}
	
	Highcharts.setOptions({
    global: {
        timezoneOffset: -8 * 60
    }
});
	
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
		type : "post",
		url : "fxjlrb_searchAlarmLine.action",
		data : {
			"year":$("#SYear").val(),
			"month":$("#SMonth").val()
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
			 var data11 = [];
			 var data12 = [];
			 
			if (result) {
				 for(var i = 0; i < result.day.length; i++){
					 var day = result.day[i];					
					 data1.push([day,result.fc1zye[i]]);
					 data2.push([day,result.fc1zyou[i]]);
					 data3.push([day,result.fc2zye[i]]);
					 data4.push([day,result.fc2zyou[i]]);
					 data5.push([day,result.fc3zye[i]]);
					 data6.push([day,result.fc3zyou[i]]);
					 data7.push([day,result.sagdye[i]]);
					 data8.push([day,result.sagdyou[i]]);
					 data9.push([day,result.cyye[i]]);
					 data10.push([day,result.cyyou[i]]);
					 data11.push([day,result.xyye[i]]);
					 data12.push([day,result.xyyou[i]]);
				 }
			//	 console.log(data1);
			 }
			
			 	$("#main0").highcharts({
		        chart: {
		            type: "line",
		            marginBottom: 5,
		            marginRight: 60
		        },
		        title: {
		            text: " "
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
							return Highcharts.dateFormat("%Y-%m-%d", this.value);
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
	                    return "<b>"+ Highcharts.dateFormat("%Y-%m-%d", this.x) +"</b><br>"+
		                    this.points[0].series.name +": "+ this.points[0].y + " m3<br>" +
		                    this.points[1].series.name +": "+ this.points[1].y + " t";
		            }
		        },
		        series: [{
		            name: "风城一站总液量",
		            data: data1
		        }, {
		            name: "风城一站总油量",
		            data: data2
		        },{
		            name: "风城二站总液量",
		            data: data3
		        }, {
		            name: "风城二站总油量",
		            data: data4
		        },{
		            name: "风城三站总液量",
		            data: data5
		        }, {
		            name: "风城三站总油量",
		            data: data6
		        },{
		            name: "SAGD采油站总液量",
		            data: data7
		        }, {
		            name: "SAGD采油站总油量",
		            data: data8
		        }]
		    });
			 
		
	$("#main1").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "产量总曲线"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " m3<br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " t";
								}
							},
							series : [ {
								name : "稠油总液量",
								data : data9
							}, {
								name : "稠油总油量",
								data : data10
							}, {
								name : "稀油总液量",
								data : data11
							}, {
								name : "稀油总油量",
								data : data12
							} ]
						});

	/* 			$("#main2").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "2#重32北线"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						}); */
/* 
				$("#main3").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "5#13-11线"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main4").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "7#重32南线"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main5").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "1线18-2（A）"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main6").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "2线18-2（B）"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main7").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "18-1线（特一联）"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main8").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "3线18-3（1B）"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main9").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "4线18-4（1B）"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main10").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "SAGD采油二站"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main11").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "采出液密闭处理（30万处理）"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main12").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "重1换热线(管汇间）"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});

				$("#main13").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "夏子街来液线"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						});



$("#main14").highcharts(
						{
							chart : {
								type : 'line',
								marginTop : 30,
								marginBottom : 40,
								marginRight : 60
							},
							title : {
								text : "乌尔禾来液线"
							},
							credits : {
								enabled : false
							},
							legend : {
								align : 'left',
								verticalAlign : 'top',
								y : -6,
								x : 40
							},

							xAxis : {
								type : "datetime",
								labels : {
									formatter : function() {
										return Highcharts.dateFormat(
												"%Y-%m-%d", this.value);
									}
								}
							},
							yAxis : {
								title : {
									text : ""
								},
								min : 0
							},
							tooltip : {
								crosshairs : true,
								shared : true,
								formatter : function() {
									return "<b>"
											+ Highcharts.dateFormat("%Y-%m-%d",
													this.x) + "</b><br>"
											+ this.points[0].series.name + ": "
											+ this.points[0].y + " <br>"
											+ this.points[1].series.name + ": "
											+ this.points[1].y + " m3<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " t<br>"
											+ this.points[2].series.name + ": "
											+ this.points[2].y + " %";
								}
							},
							series : [ {
								name : "累计流量",
								data : data9
							}, {
								name : "油量(m3)",
								data : data10
							}, {
								name : "油量(t)",
								data : data11
							}, {
								name : "含水",
								data : data12
							} ]
						}); */
			},
			error : function(data) {
				$.ligerDialog.question("请求数据失败，请重新查询");
			}
		});
	};
</script>
<style type="text/css">
html,body {
	font-size: 12px;
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

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 100px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

.l-table-select {
	padding: 4px;
}

<!--
table {
	mso-displayed-decimal-separator: "\.";
	mso-displayed-thousand-separator: "\,";
}

@page {
	mso-header-data: &A;
	mso-footer-data: "Page &P";
	margin: .75in .7in .75in .7in;
	mso-header-margin: .3in;
	mso-footer-margin: .3in;
	mso-page-orientation: landscape;
}

ruby {
	ruby-align: left;
}

rt {
	color: windowtext;
	font-size: 9.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-char-type: none;
	display: none;
}
-->
</style>

</head>
<body style="padding: 10px">
	<form name="formsearch" method="post" id="form1">
		<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

		<table>
			<tr>
				<td align="right" style="width: 40px">日期：</td>
				<td><select name="SYear" class="l-table-select" align="left"
					style="width: 20px" id="SYear">
				</select></td>
				<td align="left" style="width: 20px">年</td>
				<td><select name="SMonth" class="l-table-select" align="left"
					style="width: 20px" id="SMonth">
				</select></td>
				<td align="left" style="width: 20px">月</td>
				<td align="right" class="l-table-edit-td" style="width: 20px"></td>
				<td align="right" class="l-table-edit-td" style="width: 100px"><a
					href="javascript:onSubmit()" class="l-button" style="width: 100px">查询</a></td>
			<td align="left" class="l-table-edit-td" style="width:120px">
				<a id="lineBtn" href="javascript:onLine()" class="l-button" style="width:100px">动态曲线</a>
			</td>
				<td align="right" class="l-table-edit-td" style="width: 100px">
					<a href="javascript:onExport()" class="l-button"
					style="width: 100px">导出报表</a>
				</td>
				<!--
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						
						 <td id="jieshi"></td> -->
			</tr>

		</table>
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div id="maingrid"></div>
	<div id="mainline" style="display: none;">
			<div id="main0" style="height:160px;width:1300px"></div>
			<div id="main1" style="height:160px;width:1300px"></div>
<!-- 			<div id="main2" style="height:160px;width:1300px"></div>

			<div id="main3" style="height:160px;width:1300px"></div>
			<div id="main4" style="height:160px;width:1300px"></div>
			<div id="main5" style="height:160px;width:1300px"></div>
			<div id="main6" style="height:160px;width:1300px"></div>
			<div id="main7" style="height:160px;width:1300px"></div>
			<div id="main8" style="height:160px;width:1300px"></div>
			<div id="main10" style="height:160px;width:1300px"></div>
			<div id="main11" style="height:160px;width:1300px"></div>
			<div id="main12" style="height:160px;width:1300px"></div>
			<div id="main13" style="height:160px;width:1300px"></div> -->

		</div>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

		<table>
			<tr>
				<td align="right" style="width: 40px">备注：</td>
				<td align="left" style="width: 700px">风城采油三站总油量=3线油量+4线油量-18-1线(B)（特一联）</td>
			</tr>

		</table>


	</form>

</body>
</html>
