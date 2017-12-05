<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ page import="java.sql.*,com.echo.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");
String aname = StringUtil.toStr((String)request.getParameter("str")); 
String startTime = StringUtil.toStr((String) request.getParameter("startime"));
String endTime = StringUtil.toStr((String) request.getParameter("endtime"));
//System.out.println(aname+"hah"+startTime+"11111"+endTime);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <title>气井动态信息</title>
 
     <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
     <script src="../../lib/jquery/jquery-1.7.1.min.js" type="text/javascript"></script> 
    <script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>   
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/js/ligerui.expand.js" type="text/javascript"></script> 
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    
    <script src="../../lib/js/LG.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 <script src="../../lib/myday.js" type="text/javascript"></script>
 <script src="../../noBackspace.js" type="text/javascript"></script>
 <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script type="text/javascript">
       var wellData;
       var wellDatas = [{ id: 1 , text: '' }];
		var proData = [{ id: 1 , text: '' }];
		var ghname;  	
        $(function ()
        {
        	var a = $("#a").val();
        	var b= $("#b").val();
        	var c = $("#c").val();
        	//$("#txtDate").ligerDateEditor({ showTime: true, labelWidth: 320, labelAlign: 'left' ,format :"yyyy-MM-dd hh:00"}).setValue(myDate().Format("yyyy-MM-dd hh:00"));
			//$("#txtDate1").ligerDateEditor({ showTime: true, labelWidth: 320, labelAlign: 'left' ,format :"yyyy-MM-dd hh:00"}).setValue(myDate().Format("yyyy-MM-dd hh:00"));
            $("#txtDate").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                 //   label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
             $("#txtDate1").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                 //   label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
            var today=new Date(); // 获取今天时间
			today.setDate(today.getDate() - 7); // 系统会自动转换
			//alert(today);
            $("#txtDate").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(today.Format("yyyy-MM-dd"));
			$("#txtDate1").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(myDate().Format("yyyy-MM-dd"));
			var proData = [{ id: 1 , text: '' }];
          
          
          jQuery.ajax({
					type : 'post',
					url : 'sagd_searchAutoCompleteData.action?nowdata='+parseInt(Math.random()*100000),
					data: {"flg":"0"},
					timeout : '30000',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=""){
						var data = eval('(' + jsondata + ')');
						//alert(data[0].WELLDATAS);
					    $("#gas_well").AutoComplete({
		                'data': data[0].WELLDATAS,
		                'itemHeight': 20,
		                'width': 175
		            }).AutoComplete('show');
							
						}else{
							alert("数据库数据存在错误！");
						}
					},
					error : function(data) {
				
					}
				});
				$("#oilstationname1").ligerComboBox({
				//url:'rulewell_queryOilSationInfo.action',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:130,
				autocomplete:true,
				hideOnLoseFocus:true,
				 onSelected:function (data){
					 ghname='';
					if ($("#oilstationname1").val() != 0 && $("#oilstationname1").val() != 1 && data != null && typeof(data)!="oilstationname1" && data != '') {
						liger.get("blockstationname").setValue('');
						liger.get("wellnum").setValue('');
						setTeamData($("#oilationid").val(),proData);
						setWellData($("#oilationid").val(),'',proData);
						//liger.get("team_station").setData(wellDatas);
					}
					else {
						getWellInitData();
					}
				}
			});
			
				$("#blockstationname").ligerComboBox({
        			//url:'sagd_queryStationInfo.action',
                    valueField: 'id',
        			valueFieldID: 'stationsid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:130,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                    onSelected:function (data){
                    	ghname='';
        				if (data != null && typeof(data)!="undefined" && data != ''){
						liger.get("wellnum").setValue('');
						setWellData('',$("#stationsid").val(),proData);
						}
        			}
        		});
        		
        		
      
	
        		
        		$("#wellnum").ligerComboBox({
        		//	url:'sagd_queryWellNameInfo.action',
        			valueField: 'id',
        			valueFieldID: 'wellnumsid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:130,
        			hideOnLoseFocus:true,
        			data:wellData,
                    autocomplete:true,
                    onSelected:function (data){
                    	ghname='';
        			}
        		});
        		
        		getWellInitData();
        		if(a != null && b != null && c != null){
        			onSubmit();
        			$("#txtDate").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(b);
        			$("#txtDate1").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(c);
        			//$("#wellnum").ligerComboBox({labelWidth: 160, labelAlign: 'left'}).setValue(a);
        		}
        			
		
        });
        function setTeamData(stationid,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'searchQueryAll_searchTeam.action?arg=GTDT',
    			data: {"CYZ":stationid},
    			
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("blockstationname").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				
    			}
    		});
    	}
    	
         function setWellData(cyz,team,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'searchQueryAll_searchCYZTEAMJH.action?arg=GTDT ',
    			data: {"CYZ":cyz,"TEAM":team},
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("wellnum").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("wellnum").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				
    			}
    		});
    	}
        function getWellInitData() {
        	//$("#blockstationname").ligerGetComboBoxManager().setValue('');
    		//$("#cliquepool").ligerGetComboBoxManager().setValue('');
    		var oilStationtext=[{ id: 1 , text: '' }];
    		var teamStationtext=[{ id: 1 , text: '' }];
    		var wellstationtext=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'searchQueryAll_searchALLCYZ.action?arg=GTDT',
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'oilStationtext') {
    						oilStationtext = val;
    					}
    					if (key == 'teamStationtext'){
    						teamStationtext = val;
    					}
    					if (key == 'wellstationtext'){
    						wellstationtext = val;
    						wellDatas = val;
    					}
    				});	
    				setInitData(oilStationtext,teamStationtext,wellstationtext);
    			}
    		});
    	}
    	
        function setInitData(combinationStationtext,processingStationtext,observationWelltext) {
    		liger.get("oilstationname1").setData(combinationStationtext);
    		liger.get("blockstationname").setData(processingStationtext);
    		liger.get("wellnum").setData(observationWelltext);
    	}
	
    	
    	
      		
        function onJKSubmit(id,name,datatype,basid){ 
        	//alert(datatype);
			var aname='';
			var bname='';
			var cname='';
			var dname='';
			
			if(datatype==9){
				aname=name;
				$("#oilstationname1").val('');
				$("#blockstationname").val('');
				$("#wellnum").val(name);
			}
			if(datatype==8){
				bname=name;
				$("#oilstationname1").val('');
				$("#blockstationname").val('');
				$("#wellnum").val('');
				
			}
			if(datatype==7){
				cname=name;
				$("#blockstationname").val(name);
				
				$("#oilstationname1").val('');
				$("#wellnum").val('');
			}
			if(datatype==4){
				dname=name;
				$("#oilstationname1").val(name);
				$("#blockstationname").val('');
				$("#wellnum").val('');
			}
			
			
			var startime=$("#txtDate").val();
        	var endtime=$("#txtDate1").val();
        	window.open('<%=basePath%>page/dynamicdata/viewRzBl.jsp?str='+aname+'&bname='+bname+'&cname='+cname+'&dname='+dname+'&startime='+startime+'&endtime='+endtime,"TableFrm");	
			
			
         	
		}
           function onSubmit()
        {
        	aname=$("#wellnum").val();
        	cname=$("#blockstationname").val();
        	dname=$("#oilstationname1").val();
        	var startime=$("#txtDate").val();
        	var endtime=$("#txtDate1").val();
        	window.open('<%=basePath%>page/dynamicdata/viewRzBl.jsp?str='+aname+'&cname='+cname+'&dname='+dname+'&startime='+startime+'&endtime='+endtime,"TableFrm");	
            
        }
		
          
		    
		
		
		
		
    </script>
    <style type="text/css">
             html,body
			{
					font-size:10px;
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
           
            /* 搜索框 */
		.searchtitle{ padding-left:28px; position:relative;}
		.searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
		.searchtitle span{ font-size:14px; font-weight:bold;}
		.searchtitle .togglebtn{ position:absolute; top:6px; right:15px; background:url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
		.searchtitle .togglebtn-down{ background-position:0px -10px;}
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
    </style>
    <style type="text/css">
   html,body
   {
       height:98%;
       margin:0;
   }
</style>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<input id="a" type="hidden"  value="<%=aname%>"></input>
	<input id="b" type="hidden" value="<%=startTime%>"></input>
	<input id="c" type="hidden" value="<%=endTime%>"></input>
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form" target="TableFrm"> 
        	<table border="0" cellspacing="1">
				<tr>
						<td align="right" class="l-table-edit-td" style="width:50px">采油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname1" name = "oilstationname1" />
		                </td>
		                <td align="left">
		                </td>
		               
		                <td align="left">
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:50px">注转站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="blockstationname" name = "blockstationname"/>
		                </td>
		                <td align="left"></td>
		               
		               
<!-- 		               	<td align="right" class="l-table-edit-td" style="width:50px">管汇：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="manifold" name = "manifold"/>
		                </td>
		                <td align="left"></td> -->
		               
		               
		                <td align="right" class="l-table-edit-td" style="width:50px">井名：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="wellnum" name = "wellnum"/>
		                </td>
		           
	                <td align="right" class="l-table-edit-td" style="width:60px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date" /></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
				<td align="left" class="l-table-edit-td" style="width:30px">
	                </td>
				<td><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
				
				</tr>
				<tr>
				<td></td>
				</tr>
			</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
        </form>
        
        <!-- 
         <table width="100%" border="1" cellpadding="0" cellspacing="0"
				bordercolor="#FFFFFF">
				<tr>
					<td height="400px" colspan="0" align="left" valign="middle"
						bordercolor="#FFFFFF" bgcolor="#F1F3F5">
						
					</td>
				</tr>
			</table>
         -->
       
    </div>
  <div style="height: 86%">
        <iframe id="TableFrm" name="TableFrm" height="100%"  width="100%" marginheight=0
							marginwidth=0 frameborder=0 scrolling=yes>
						</iframe>
        </div>
  <div id="maingrid"></div> 
 
</body>
</html>
