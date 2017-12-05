<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta content='width=330, height=400, initial-scale=1' name='viewport' />
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="../../lib/jquery/jquery-1.7.1.min.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>   
    <script src="../../lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>   
	 <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
	 <script src="../../lib/myday.js" type="text/javascript"></script>
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>
     <script src="../../noBackspace.js" type="text/javascript"></script>   
      <script src="../../lib/json2.js" type="text/javascript"></script> 
     
    <link rel="stylesheet" type="text/css" href="../../lib/jqChart/css/jquery.jqChart.css" />
    <link rel="stylesheet" type="text/css" href="../../lib/jqChart/css/jquery.jqRangeSlider.css" />
    <link rel="stylesheet" type="text/css" href="../../lib/jqChart/themes/smoothness/jquery-ui-1.8.21.css" />
    <script src="../../lib/jqChart/js/jquery.mousewheel.js" type="text/javascript"></script>
    <script src="../../lib/jqChart/js/jquery.jqChart.min.js" type="text/javascript"></script>
    <script src="../../lib/jqChart/js/jquery.jqRangeSlider.min.js" type="text/javascript"></script>
    <!--[if IE]><script lang="javascript" type="text/javascript" src="../../lib/jqChart/js/excanvas.js"></script><![endif]-->
    
    <script lang="javascript" type="text/javascript">
    
    	var sagdid = parent.sagdid;
    	var jhmc = parent.jhmc;
    	var linename=[];
    	
    	var txpar=[] ;  //当前选择的图形变量
    	var txunit=[];	//当前选择图形变量单位
    	var tudatas;
    
 		
    	var pidData = 
            [
            { id: 1, text: '抽油机'},
            { id: 2, text: '井口参数' }, 
            { id: 3, text: '井下参数'},
            { id: 4, text: '蒸汽计量'}
            
            ];
       var chinaData =
       [
       { id: 'MOTOR_PRESS_A', text: '电机A相电压',unit:'V', pid: 1 },
       { id: 'MOTOR_PRESS_B', text: '电机B相电压', unit:'V',pid: 1 },
       { id: 'MOTOR_PRESS_C', text: '电机C相电压', unit:'V',pid: 1 },
       { id: 'MOTOR_TEMP_A', text: '电机A相电流', unit:'A',pid: 1 },
       { id: 'MOTOR_TEMP_B', text: '电机B相电流', unit:'A',pid: 1 },
       { id: 'MOTOR_TEMP_C', text: '电机C相电流', unit:'A',pid: 1 },
       { id: 'ACTIVE_ENERGY', text: '有功电量', unit:'kWh',pid: 1 },
       { id: 'IDLE_ENERGY', text: '无功电量', unit:'kVar',pid: 1 },
       { id: 'POWER_FACTOR', text: '功率因素', unit:'%',pid: 1 },
        { id: 'CONVERSION_PRESS', text: '变频电压', unit:'V',pid: 1 },
       { id: 'CONVERSION_ELECTRICITY', text: '变频电流', unit:'A',pid: 1 },
       { id: 'NOW_FREQUENCY', text: '当前频率', unit:'HZ',pid: 1 },
       { id: 'STROKE', text: '冲程', unit:'m',pid: 1 },
       { id: 'JIG_FREQUENCY', text: '冲次', unit:'次/分',pid: 1 },
        { id: 'NOW_LOAD', text: '当前载荷', unit:'-',pid: 1 },
       { id: 'P_PRESSURE_PRESS', text: 'P井主管压力', unit:'MPa',pid: 2 },
       { id: 'P_PRESSURE_TEMP', text: 'P井主管温度', unit:'℃',pid: 2 },
       { id: 'P_LOOPED_PRESS', text: 'P井副管压力', unit:'MPa',pid: 2 },
       { id: 'P_LOOPED_TEMP', text: 'P井副管温度', unit:'℃',pid: 2 },
       { id: 'P_DRIVEPIPE_PRESS', text: 'P井套管压力', unit:'MPa',pid: 2 },
       { id: 'I_PRESSURE_PRESS', text: 'I井主管压力', unit:'MPa',pid: 2 },
       { id: 'I_PRESSURE_TEMP', text: 'I井主管温度', unit:'℃',pid: 2 },
       { id: 'I_LOOPED_PRESS', text: 'I井副管压力', unit:'MPa',pid: 2 },
       { id: 'I_LOOPED_TEMP', text: 'I井副管温度', unit:'℃',pid: 2 },
       { id: 'I_DRIVEPIPE_PRESS', text: 'I井套管压力', unit:'MPa',pid: 2 },
       { id: 'P_PRESS1', text: 'P井压力1', unit:'MPa',pid: 3 },
       { id: 'P_PRESS2', text: 'P井压力2', unit:'MPa',pid: 3 },
       { id: 'P_TEMP1', text: 'P井温度1', unit:'℃',pid: 3 },
       { id: 'P_TEMP2', text: 'P井温度2', unit:'℃',pid: 3 },
       { id: 'P_TEMP3', text: 'P井温度3', unit:'℃',pid: 3 },
       { id: 'P_TEMP4', text: 'P井温度4', unit:'℃',pid: 3 },
       { id: 'P_TEMP5', text: 'P井温度5', unit:'℃',pid: 3 },
       { id: 'P_TEMP6', text: 'P井温度6', unit:'℃',pid: 3 },
       { id: 'P_TEMP7', text: 'P井温度7', unit:'℃',pid: 3 },
       { id: 'P_TEMP8', text: 'P井温度8', unit:'℃',pid: 3 },
       { id: 'P_TEMP9', text: 'P井温度9', unit:'℃',pid: 3 },
       { id: 'P_TEMP10', text: 'P井温度10', unit:'℃',pid: 3 },
       { id: 'P_TEMP11', text: 'P井温度11', unit:'℃',pid: 3 },
       { id: 'P_TEMP12', text: 'P井温度12', unit:'℃',pid: 3 },
     
       { id: 'NO1STEAM_PRESS', text: '蒸汽压力1#', unit:'mPa',pid: 4 },
       { id: 'NO2STEAM_PRESS', text: '蒸汽压力2#', unit:'mPa',pid: 4 },
       { id: 'NO1STEAM_TEMP', text: '蒸汽温度1#', unit:'℃',pid: 4 },
       { id: 'NO2STEAM_TEMP', text: '蒸汽温度2#', unit:'℃',pid: 4 },
       { id: 'NO1STEAM_DIFP', text: '蒸汽差压1#', unit:'mPa',pid: 4 },
       { id: 'NO2STEAM_DIFP', text: '蒸汽差压2#', unit:'mPa',pid: 4 },
       { id: 'NO1INSTANT_DRYNESS', text: '瞬时干度1#', unit:'%',pid: 4 },
       { id: 'NO2INSTANT_DRYNESS', text: '瞬时干度2#', unit:'%',pid: 4 },
       { id: 'NO1INSTANT_FLOW', text: '瞬时流量1#', unit:'T/H',pid: 4 },
       { id: 'NO2INSTANT_FLOW', text: '瞬时流量2#', unit:'T/H',pid: 4 },
       { id: 'NO1CONTROL_APERTURE', text: '调节阀开度1#', unit:'%',pid: 4 },
       { id: 'NO2CONTROL_APERTURE', text: '调节阀开度2#', unit:'%',pid: 4 },
       { id: 'CLIQUE_CONTROL_APERTURE1', text: '阀控制开度1#', unit:'%',pid: 4 },
       { id: 'CLIQUE_CONTROL_APERTURE2', text: '阀控制开度2#', unit:'%',pid: 4 },
       { id: 'SUM_FLOW1', text: '总累积流量1#', unit:'T',pid: 4 },
       { id: 'SUM_FLOW2', text: '总累积流量2#', unit:'T',pid: 4 }
       ];  
       
        var background = {
                type: 'linearGradient',
                x0: 0,
                y0: 0,
                x1: 0,
                y1: 1,
                colorStops: [{ offset: 0, color: '#d2e6c9' },
                             { offset: 1, color: 'white' }]
            };
        $(document).ready(function () {
        	jhmc = jhmc.substring(0,jhmc.length-1);
        
        	$("#linetype").ligerComboBox({
        		selectBoxHeight:100,
        		selectBoxWidth:100,
                data: pidData, 
                isMultiSelect: false,
                autocomplete:true,
        		hideOnLoseFocus:true,
				valueField: 'id',
        		valueFieldID: 'linetypeid',
				textField: 'text',
                onSelected: function (newvalue)
                {
                    var newData = new Array();
                    for (i = 0; i < chinaData.length; i++)
                    {
                        if (chinaData[i].pid == newvalue)
                        {
                            newData.push(chinaData[i]);
                        }
                    }
                    liger.get("prvar").setData(newData);
                }
            });
            $("#prvar").ligerComboBox({ 
            	selectBoxHeight:150,
            	selectBoxWidth:130,
            	data: null, 
            	autocomplete:true,
        		hideOnLoseFocus:true,
				valueField: 'id',
        		valueFieldID: 'prvarid',
				textField: 'text',
            	isMultiSelect: true, 
            	isShowCheckBox: false,
            	onSelected: function (newvalue)
                {
            		txpar = [];
            		txunit = [];
                    for (i = 0; i < chinaData.length; i++)
                    {
                        if (newvalue.indexOf(chinaData[i].id)>=0)
                        {
                        	txpar.push(chinaData[i].text);  //当前选择的图形变量
    						txunit.push(chinaData[i].unit);	//当前选择图形变量单位
                        }
                    }
                }
            	 });
        
        	 $("#txtDate").ligerDateEditor(
                {
                    showTime: true,
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false,
                    format :"yyyy-MM-dd"
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
            $("#txtDate1").ligerDateEditor(
                {
                    showTime: true,
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false,
                    format :"yyyy-MM-dd"
            }).setValue(myDate().Format("yyyy-MM-dd"));
         	
        });

        
        function onSubmit()
        {
        	if($("#prvar").val() != ''){
        		  //获取菜单树
                jQuery.ajax({
					type : 'post',
					url : 'sagddrpd_getline2.action',
					async : false,
					data: {"sagdid":sagdid ,"prvarid":$("#prvarid").val(),"startDate":$("#txtDate").val(),"endDate":$("#txtDate1").val()},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
								tudatas = eval('(' + jsondata + ')');
								if(tudatas==null){
									alert("无数据");
									return;
								}
								var covertData = [];
								for(var l=0;l<10;l++){
									covertData[l]=[]
								} 
								for(var i=0;i<tudatas.length;i++) {
									
									var str = tudatas[i][0].replace(/-/g,"/");
									var date = new Date(str );
									if(txpar.length>1){
										for(var j=1;j<tudatas[i].length;j++){
											covertData[j].push([str, tudatas[i][j]]);
										}
									}else{
										for(var j=1;j<tudatas[i].length;j++){
											covertData[j].push([date, tudatas[i][j]]);
										}
									}
								}
					            if(typeof(txpar)!="undefined"){
					            	linename = [];
						            for(var k in txpar){
						            	linename[k] = txpar[k]+'('+txunit[k]+')';
						            }
					            }
								 $('#jqChart').jqChart({
					                title: jhmc+'--数据曲线图',
					                legend: { title: '图形变量' },
					                border: { strokeStyle: '#6ba851' },
					                background: background,
					                animation: { duration: 2 },
					                tooltips: { type: 'shared' },
					                shadows: {
					                    enabled: true
					                },
					                crosshairs: {
					                    enabled: true,
					                    hLine: false,
					                    vLine: { strokeStyle: '#cc0a0c' }
					                },
					                axes: [
					                    {
					                        type: 'dateTime',
					                        location: 'bottom',
					                        zoomEnabled: true
					                    }
					                ],
					                series: [
					                    {
					                        title: linename[0],
					                        type: 'line',
					                        data: covertData[1],
					                        markers: null
					                    }
					                ]
					            });
								

								 var series = $('#jqChart').jqChart('option', 'series');
								 for(var m=1;m<linename.length;m++){
									 
									 var newSeries = {  
												title:linename[m],  
												type: 'line',  
												data: covertData[m+1],  
												markers:null 
											};  
											series.push(newSeries);
											$('#jqChart').jqChart('update');
								 }
					           
								$('#jqChart').bind('tooltipFormat', function (e, data) {
									/* var oDate1 = new Date(data.x); */
									if ($.isArray(data) == false && txpar.length>1) {
					                    var tooltip = '<b>当前时间：' + data.x + '</b><br />' +
					                          '<span style="color:' + data.series.fillStyle + '">' + data.series.title + ': </span>' +
					                          '<b>' + data.y + '</b><br />';

					                    return tooltip;
					                }else if($.isArray(data) == false && txpar.length==1){
					                	var tooltip = '<b>当前时间：' + data.x.Format("yyyy-MM-dd") + '</b><br />' +
				                          '<span style="color:' + data.series.fillStyle + '">' + data.series.title + ': </span>' +
				                          '<b>' + data.y + '</b><br />';

				                    return tooltip;
					                }
									var allStyle = '';
									var vdata='';
									for(var i=0;i<txpar.length;i++){
										if(typeof(data[i])!="undefined"){
											allStyle += '<span style="color:' + data[i].series.fillStyle + '">' + data[i].series.title + ': </span>' +
					                      	'<b>' + data[i].y + '</b><br />' 
										}
									}
					                var tooltip = '<b>当前日期：' + data[0].x + '</b><br />' + allStyle;
					                

					                return tooltip;
								});
						}else if(jsondata == null){
							alert("无数据");
						}else{
							alert("数据库数据存在错误！");
						}
					},
					error : function(data) {
				
					}
				});
        	}else{
        		$.ligerDialog.error("请选择图形变量");
        	}
         	
        }
    </script>

</head>
<body>
    
    <div id="mainsearch" style=" width:99%">
	 
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
						<td align="right" class="l-table-edit-td" style="width:80px">曲线类型：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="linetype" name = "linetype"/>	
		                </td>
		                <td align="left">
		                </td>
		              
		                <td align="right" class="l-table-edit-td" style="width:60px">图形变量：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="prvar" name = "prvar" />
		                </td>
		                <td align="left">
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td valign="middle">--</td>
						<td><input type="text" id="txtDate1" ltype="date"/></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>

					</tr>
				
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  	<div align="center" style="text-align: center;">
		        <div id="jqChart" style="width: 950px; height: 300px;">
		        </div>
		    </div>
	  
	  
		  
		    </form>
		    
		</div>
</body>
</html>

