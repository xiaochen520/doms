<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>SAGD井动态数据</title>
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
    <script type="text/javascript"><!--
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
        	/*Highcharts.setOptions({
                lang: {
        			printChart:"打印图表",
        			downloadJPEG: "下载JPEG 图片" , 
        			downloadPDF: "下载PDF文档"  ,
        			downloadPNG: "下载PNG 图片"  ,
        			downloadSVG: "下载SVG 矢量图" , 
        			exportButtonTitle: "导出图片" 
                }
            });*/
            
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'sagdrd_pageInit.action',
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
				
				 $("#datastype").ligerComboBox({
             			hideOnLoseFocus:true,
						autocomplete:true,
			                data: [
			                    { text: '全部', id: '1' },
			                    { text: '抽油机参数', id: '2' },
			                    { text: '井口数据', id: '3' },
			                    { text: '井下数据', id: '4' },
			                    { text: '蒸汽数据', id: '5' }
			                    
			                ], valueFieldID: 'datastypeid',
			                 onSelected:function (data){
		        				setGridHid(data);
		        				dataflg = data;
		        			}
			                
			                //initText:'全部'
							//initValue:'1'
			            }).selectValue('1'); 
			            
			        
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
        			},
        			onSelected:function (data){
        			}
        		});
        		
        		
      		 //关联井号 和管汇	 
       		 function setWellInitData(data,proData,wellid) {
      		jQuery.ajax({
      			type : 'post',
      			url:'sagd_queryWellNameInfo.action',
      			data: {"ghmcid":data},
      			timeout : '3000',
      			success : function(jsondata) {
      				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
      					liger.get("wellnum").setData(eval('(' + jsondata + ')'));
      				}
      				else{
      					liger.get("wellnum").setData(proData);
      				}
      			},
      			error : function(jsondata) {
      				alert("请求数据失败，请重新查询");
      			}
      		});
      		if (wellid != '') {
				$("#wellnum").ligerGetComboBoxManager().selectValue(wellid);
			}
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
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
       


         function onSubmit()
        {
        	exportFlag = false;
        	datastypeid = '';
        	if($("#datastype").val() != ''){
        		datastypeid = $("#datastypeid").val();
        	}
        	
        	grid.setOptions({
        		parms: [
        		{
					name: 'datastype',
					value: datastypeid
				},{
					name: 'oilationname',
					value: $("#oilationname").val()
				},{
					name: 'ghmc',
					value: $("#ghmc").val()
				},{
					name: 'wellnum',
					value: $("#wellnum").val()
				},{
					name: 'startDate',
					value: $("#txtDate").val()
				},{
					name: 'endDate',
					value: $("#txtDate1").val()
				}
				]
        	});
        	//setGridHid();
        	
       	 	if(line == 1) {
		 		$('#maingrid').toggle("normal");
		 		$('#mainline').toggle("normal");
		 		
		 		line = 0;
		 	}
        	 	
         	grid.loadData(true);
         	
        }

	function getWellInitData() {
		$("#ghmc").ligerGetComboBoxManager().setValue('');
		$("#wellnum").ligerGetComboBoxManager().setValue('');
		var oilText=[{ id: 1 , text: '' }];
		var ghmcText=[{ id: 1 , text: '' }];
		var wellText=[{ id: 1 , text: '' }];
		jQuery.ajax({
			type : 'post',
			url : 'sagd_cascadeInit.action',
			success : function(jsondata) {
			var dataObj = $.parseJSON(jsondata);
				$.each(dataObj, function(key,val){
					if (key == 'oilText'){
						oilText = val;
					}
					if (key == 'ghmcText'){
						ghmcText = val;
					}
					if (key == 'welltext'){
						wellText = val;
					}
				});
				setInitData(oilText,ghmcText,wellText);
			}
		});
	}
  	
	function setInitData(oilText,stationText,wellText) {
		liger.get("oilationname").setData(oilText);
		liger.get("ghmc").setData(ghmcText);
		liger.get("wellnum").setData(wellText);
	}
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
              	   
              	 if(operate != 1 && operate != 2){
 	              	setpage1(0); //显示编辑界面
 	              	setItemsd(0);//0-显示编辑区，添加隐藏按钮
 	              }
              	   operate = 1;
              	   assignM(1);
              	   
              	   
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改信息的数据！');
              	    
              	     }else if(rows.length == 1){
							
              	    	if(operate != 1 && operate != 2){
							setpage(0); //显示编辑界面
					 		setItemsd(0);//1-隐藏编辑区添加显示按钮
						}
		              	   operate = 2;
		              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改信息的数据！');
              	     }
                  break;
              case "delete":
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要删除的数据！');
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'sagd_removeSagd.action',
											data: {"sagdId":sagdid,"org_id":org_id},
											async : false,
											success : function(data) { 
											if (data != null && typeof(data)!="undefined" && data == "1"){
													
													onSubmit();
													assignM(1);
												}else{
													$.ligerDialog.error(outerror(jsondata));
												}
											},
											error : function(data) {
										
											}
										});
		                        	 } 
              	     		 });
							
							

              	     }else{
              	     	alert("请选择一条你要删除的数据！");
              	     }
                  break;
              case "save":
					$("#form1").submit();
					break;
              case "up":
           		setpage1(0); //显示编辑界面
            	    setItemsd(0);//0-显示编辑区，添加隐藏按钮
                break;   
                case "down":
				 	setpage1(1); //隐藏编辑界面
				 	setItemsd(1);//1-隐藏编辑区添加显示按钮
                break;    
          }
      }

      function onExport() {
			var datastypeid = $("#datastypeid").val();
			var oilationname=$("#oilationname").val();
			var ghmc=$("#ghmc").val();
			var wellnum=$("#wellnum").val();
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var gh='';
			if (exportFlag) {
				oilationname=oilationname;
				ghmc=ghmc;
				wellnum=wellname;
			}
			var totalNum = 0;
			var url = 'sagdrd_searchSagdRD.action?datastype='+encodeURIComponent(datastypeid)+'&oilationname='+encodeURIComponent(oilationname)+'&ghmc='+encodeURIComponent(ghmc)+'&wellnum='+encodeURIComponent(wellnum)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('export');
			var urls = 'sagdrd_searchSagdRD.action?datastype='+encodeURIComponent(datastypeid)+'&oilationname='+encodeURIComponent(oilationname)+'&ghmc='+encodeURIComponent(ghmc)+'&wellnum='+encodeURIComponent(wellnum)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('totalNum');
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
   	}

	function DrawEChart() {
		$('#container').html('');
		var datastypeid = $("#datastypeid").val();
		var areablock=$("#areablock1").val();
		var blockstationname=$("#blockstationname").val();
		var wellnum=$("#wellnum").val();
		var txtDate = $("#txtDate").val();
		var txtDate1 = $("#txtDate1").val();

		jQuery.ajax({
			type : 'post',
			url : 'sagdrd_searchSagdRDLine.action',
			data : {
				"areablock" : areablock,
				"blockstationname" : blockstationname,
				"wellnum" : wellnum,
				"startDate" : txtDate,
				"endDate" : txtDate1
			},
			dataType: "json",
			success : function(result) {
			
			     var data = [];
			     var minData=[];
			     var maxData=[];
			     
			 
			if (result) {
				 for(var i = 0; i < result.time.length; i++){
					 data.push([result.time[i],result.press[i]]);
					 minData.push([result.time[i],result.min[1]]);
					 maxData.push([result.time[i],result.max[1]]);
				 }
				 console.log(data);
				 console.log(minData);
				 console.log(maxData);
			 }
			    
			    
				$('#container').highcharts({
					chart: {
			            type: 'line',
			            marginTop: 80,
			            width: null
					},
			        title: {
			            text:  result.wellName[0]+'  SAGD井动态数据',
			            x: -20
			        },
			        credits: {
			        	enabled: false
			        },
			        xAxis: {
			        	type: 'datetime',
			            labels: { 
							formatter: function () {
								return Highcharts.dateFormat('%Y-%m-%d<br>%H:%M:%S',this.value);
							}
						}
			        },
			        yAxis: {
			            title: {
			                text: 'I井主管压力(MPa)'
			            } 
			        },
			        tooltip: {
			        	formatter: function() {
                    		return '<b>'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</b><br>'+
	                    		this.series.name +': '+ this.y;
						}
			        },
			        legend: {
			        	align: 'left',
			        	verticalAlign: 'top',
			        	x: 60,
						y: 30
					},
					exporting: {
						filename: result.wellName+'SAGD井动态数据',//导出的文件名
						url:'export.action'
					},
			        series: [{
			            name: 'I井主管压力(MPa)',
			            data: data,
			            color: '#0B3FEA'
			        },{
			            name: '注汽压力预警值',
			            data: minData,
			            lineWidth: 1,
			            marker: {
               				 enabled: false
            			},
			            color: '#EB8773'
			        },{
			            name: '注汽压力上限值',
			            data: maxData,
			            lineWidth: 1,
			            marker: {
               				 enabled: false
            			},
			            color: '#FF0000'
			        }]
			    });
			},
			error : function(data) {
				$.ligerDialog.question("请求数据失败，请重新查询");
			}
		});}
		
		
		/**
	      下面的代码是copy的
		*/
	/*	

function DrawEChart() {
	jQuery.ajax({
		type : 'post',
		url : 'sagdrd_searchSagdRDLine.action',
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
			if (result) {
				 for(var i = 0; i < result.time.length; i++){
					 var time = result.time[i];					
					 data1.push([time,result.temp[i]]);
					 data2.push([time,result.press[i]]);

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
		            type: 'line',
		            marginTop: 30,
		            marginBottom: 40,
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
		},
		error : function(data) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
};
		*/
		
		
    --></script>
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
					<td align="right" class="l-table-edit-td" style="width:100px">数据类型：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="datastype" name = "datastype"/>	
		                </td>
		                <td align="left">
		                </td>
						<td align="right" class="l-table-edit-td" style="width:80px;display:none">区块：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px;display:none">
		                	<input type="text" id="areablock" name = "areablock"/>	
		                </td>
						<td align="right" class="l-table-edit-td" style="width:80px">所属采油站：</td>
						<td align="left" class="l-table-edit-td" style="width:80px">
							<input type="text" id="oilationname" name = "oilationname"/>	
						</td>
		                <td align="left">
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:80px">管汇：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="ghmc" name = "ghmc"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">井号：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="wellnum" name = "wellnum" />
		                </td>
		                <td align="left">
		                </td>
		                
		                <td align="left">
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:60px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td valign="middle">--</td>
						<td><input type="text" id="txtDate1" ltype="date"/></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
		 				<td style="padding-left: 20px;"><a href="javascript:onLine()" class="l-button" style="width:80px">动态曲线</a></td>  
						<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:80px">导出报表</a></td>
						
					</tr>
				
				</table>
				 </form>
		    
			</div>
			
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  <div id="maingrid"></div> 
	  	  <div id="mainline" style="display: none;">
			<div id="container" style="height:600px;width:1300px"></div>
		  </div>
	  
		  
		   
    
</body>
</html>