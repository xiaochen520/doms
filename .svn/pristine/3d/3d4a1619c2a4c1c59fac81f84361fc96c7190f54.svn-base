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
     
    <link rel="stylesheet" type="text/css" href="../../lib/jqChart/css/jquery.jqChart.css" />
    <link rel="stylesheet" type="text/css" href="../../lib/jqChart/css/jquery.jqRangeSlider.css" />
    <link rel="stylesheet" type="text/css" href="../../lib/jqChart/themes/smoothness/jquery-ui-1.8.21.css" />
    <script src="../../lib/jqChart/js/jquery.mousewheel.js" type="text/javascript"></script>
    <script src="../../lib/jqChart/js/jquery.jqChart.min.js" type="text/javascript"></script>
    <script src="../../lib/jqChart/js/jquery.jqRangeSlider.min.js" type="text/javascript"></script>
    <!--[if IE]><script lang="javascript" type="text/javascript" src="../../lib/jqChart/js/excanvas.js"></script><![endif]-->
    
    <script lang="javascript" type="text/javascript">
    
    	var glid = parent.glid;
    	//var rbid = parent.rbid;
    	var glmc = parent.glmc;
    	var linename = '';
    	//alert(glid);
    	var txpar ;  //当前选择的图形变量
    	var txunit;	//当前选择图形变量单位
    	var tudatas;
    	
       var chinaData =
       [
        { id: 'STEAMOUT_TEMP', text: '蒸汽出口温度',unit:'℃'},
		{ id: 'STEAMIN_TEMP', text: '蒸汽出口压力',unit:'MPa'},
		{ id: 'EJECTSMOKE_TEMP', text: '排烟温度',unit:'℃'},
		{ id: 'BURNER_TEMP', text: '燃烧器温度',unit:'℃'},
		{ id: 'CSIN_PRESS', text: '对流段进口压力',unit:'MPa'},
		{ id: 'CSOUT_PRESS', text: '对流段出口压力',unit:'MPa'},
		{ id: 'CSIN_TEMP', text: '对流段进口温度',unit:'℃'},
		{ id: 'CSOUT_TEMP', text: '对流段出口温度',unit:'℃'},
		{ id: 'SL_LEVEL', text: '分离器液位',unit:'M'},
		{ id: 'SUPERHEAT_TEMP', text: '过热段管温',unit:'℃'},
		{ id: 'SUPERHEAT_PIEZORESISTANCE', text: '过热段压阻',unit:'MPa'},
		{ id: 'SUPERHEATIN_TEMP', text: '过热入口温度',unit:'℃'},
		{ id: 'SUPERHEATOUT_TEMP', text: '过热出口温度',unit:'℃'},
		{ id: 'SUPERHEATIN_PRESS', text: '过热入口压力',unit:'MPa'},
		{ id: 'SUPERHEATOUT_PRESS', text: '过热出口压力',unit:'MPa'},
		{ id: 'SUPERHEATIN_FLOW', text: '过热入口流量',unit:'T'},
		{ id: 'HEARTH_PRESS', text: '炉膛压力',unit:'MPa'},
		{ id: 'GAS1_PRESS', text: '燃气一级压力',unit:'MPa'},
		{ id: 'GAS2_PRESS', text: '燃气二级压力',unit:'MPa'},
		{ id: 'GAS3_PRESS', text: '燃气三级压力',unit:'MPa'},
		{ id: 'RS_TEMP', text: '辐射段管温',unit:'℃'},
		{ id: 'RS_DRYNESS', text: '辐射段干度',unit:'%'},
		{ id: 'RS_PIEZORESISTANCE', text: '辐射段压阻',unit:'MPa'},
		{ id: 'RSIN_PRESS', text: '辐射段进口压力',unit:'MPa'},
		{ id: 'RS_PRESS', text: '辐射段出口压力',unit:'MPa'},
		{ id: 'RSIN_TEMP', text: '辐射段进口温度',unit:'℃'},
		{ id: 'RSOUT_TEMP', text: '辐射段出口温度',unit:'℃'},
		{ id: 'PUMPA_FLOW', text: '泵A相流量',unit:'T'},
		{ id: 'PUMPB_FLOW', text: '泵B相流量',unit:'T'},
		{ id: 'PUMPC_FLOW', text: '泵C相流量',unit:'T'},
		{ id: 'PUMPA_PRESS', text: '泵AC电压',unit:'V'},
		{ id: 'PUMPB_PRESS', text: '泵AB电压',unit:'V'},
		{ id: 'PUMPC_PRESS', text: '泵BC电压',unit:'V'},
		{ id: 'FANA_ELECTRICITY', text: '风机A相电流',unit:'A'},
		{ id: 'FANB_ELECTRICITY', text: '风机B相电流',unit:'A'},
		{ id: 'FANC_ELECTRICITY', text: '风机C相电流',unit:'A'},
		{ id: 'PUMPFC_FREQUENCY', text: '泵变频频率',unit:'HZ'},
		{ id: 'PUMPIN_PRESS', text: '泵进口压力',unit:'MPa'},
		{ id: 'PUMPOUT_PRESS', text: '泵出口压力',unit:'MPa'},
		{ id: 'PUMPIN_TEMP', text: '泵进口温度',unit:'℃'},
		{ id: 'PUMPOUT_TEMP', text: '泵出口温度',unit:'℃'},
		{ id: 'WATERSUPPLY_FLOW', text: '给水流量',unit:'T'},
		{ id: 'WATERSUPPLY_TOTAL', text: '给水量累计',unit:'T'},
		{ id: 'GAS_FLOW', text: '燃气流量',unit:'T'},
		{ id: 'GAS_TOTAL', text: '燃气量累计',unit:'T'},
		{ id: 'STEAMINJECTION_TOTAL', text: '注汽量累计',unit:'T'},
		{ id: 'DAILY_CUMULATIVE_WATER', text: '给水日累计量',unit:'T'},
		{ id: 'DAILY_CUMULATIVE_GAS', text: '燃气日累计',unit:'T'},
		{ id: 'DAILY_CUMULATIVE_STEAM', text: '注汽日累计',unit:'T'}
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
        
        	$("#prvar").ligerComboBox({
        		selectBoxHeight:300,
        		selectBoxWidth:130,
                data: chinaData, 
                isMultiSelect: false,
                autocomplete:true,
        		hideOnLoseFocus:false,
				valueField: 'id',
        		valueFieldID: 'prvarid',
				textField: 'text',
                onSelected: function (newvalue)
                {
                    for (i = 0; i < chinaData.length; i++)
                    {
                        if (chinaData[i].id == newvalue)
                        {
                        	txpar = chinaData[i].text;  //当前选择的图形变量
    						txunit = chinaData[i].unit;	//当前选择图形变量单位
    						//alert(txunit);
                        }
                    }
                }
            });
            
        
        	 $("#txtDate").ligerDateEditor(
                {
                    showTime: true,
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
            $("#txtDate1").ligerDateEditor(
                {
                    showTime: true,
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));


            $('#jqChart').bind('tooltipFormat', function (e, data) {

                if ($.isArray(data) == false) {

                    //var date = data.chart.stringFormat(data.x, "ddd, mmm dS, yyyy");
                    var tooltip = '<b>当前时间：' + data.x.Format("yyyy-MM-dd hh:mm:ss") + '</b><br />' +
                          '<span style="color:' + data.series.fillStyle + '">' + data.series.title + ': </span>' +
                          '<b>' + data.y + '</b><br />';

                    return tooltip;
                }


                var tooltip = '<b>' + data.x + '</b><br />' +
                      '<span style="color:' + data[0].series.fillStyle + '">' + data[0].series.title + ': </span>' +
                      '<b>' + data[0].y + '</b><br />' +
                      '<span style="color:' + data[1].series.fillStyle + '">' + data[1].series.title + ': </span>' +
                      '<b>' + data[1].y + '</b><br />';

                return tooltip;
            });
        });
        
        function onSubmit()
        {
        	if($("#prvar").val() != ''){
        		  //获取菜单树
                jQuery.ajax({
					type : 'post',
					url : 'blilercdrpd_getGGline.action?nowdata='+parseInt(Math.random()*100000),
					async : false,
					data: {"glid":glid ,"prvarid":$("#prvarid").val(),"startDate":$("#txtDate").val(),"endDate":$("#txtDate1").val()},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
								tudatas = eval('(' + jsondata + ')');
							   var covertData = [];
								for(var i in tudatas) {
									var str = tudatas[i][0].replace(/-/g,"/");
									var date = new Date(str );
									covertData.push([date, tudatas[i][1]]);
								}
					            if(typeof(txpar)!="undefined"){
					            	linename = txpar+'('+txunit+')';
					            }
								 $('#jqChart').jqChart({
					                title: glmc+'锅炉--动态曲线图',
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
					                        title: linename,
					                        type: 'line',
					                        data: covertData,
					                        markers: null
					                    }
					                ]
					            });
					           
							
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
						<!-- <td align="right" class="l-table-edit-td" style="width:80px">曲线类型：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="linetype" name = "linetype"/>	
		                </td>
		                <td align="left">
		                </td>
		               -->
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

