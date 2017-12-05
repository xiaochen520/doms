﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>管网管汇动态数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
   <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
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
   
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>    -->
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
	var eee;
	
	var exportFlag = false;
        $(function () {
        
            var proData = [{ id: 1 , text: '' }];
			  
    			$("#oilName").ligerComboBox({
    				//url:'waterSoWell_queryCombinationInfo.action',
    				//url:'gwgh_cascadeInit.action',
    	            valueField: 'id',
    				valueFieldID: 'oilOrgId',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:100,
    	            hideOnLoseFocus:true,
    	            autocomplete:true,
    	            onSelected:function (data){
    				liger.get("stationName").setValue('');
    				liger.get("NETWORK").setValue('');
    					if ( data != null && typeof(data)!="undefined" && data != '') {
    						OnChangeData($("#oilName").val(),"");
    					}
    				}
    			});
    			$("#stationName").ligerComboBox({
    				//url:'waterSoWell_queryWellInfo.action',
    				valueField: 'id',
    				valueFieldID: 'staOrgId',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:140,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				liger.get("NETWORK").setValue('');
    					if (data != null && typeof(data)!="undefined" && data != '' && data !='1'){
    						OnChangeData("",$("#stationName").val());
    					}else{
    						getAllInitData();
        					}
    				}
    			});
    			$("#NETWORK").ligerComboBox({
    				//url:'waterSoWell_queryWaterSoWell.action',
    				valueField: 'id',
    				valueFieldID: 'NETWORKId',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				if (data != null && typeof(data)!="undefined" && data != ''){
    					
        				}
    				}
    			});
    			
				getAllInitData();
			
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
	                    showTime: true,
	                    labelAlign: 'center',
	                    cancelable : false
	            });
            $("#txtDate").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(myDate().Format("yyyy-MM-dd hh:mm"));
			$("#txtDate1").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));
			
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'jzsclrd_pageInit.action?arg=GWGH',
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
			
			
            $("#pageloading").hide();
			
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
       
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			
			var oilstationname='';
			var blockstationname='';
			var cliquepool='';
			
			if(datatype=='4'){
				oilstationname=name;
				$("#oilName").val(name);
				document.getElementById("stationName").value="";
				document.getElementById("NETWORK").value="";
			}
			
			if(datatype=='7'){
				blockstationname=name;
				$("#stationName").val(name);
				document.getElementById("oilName").value="";
				document.getElementById("NETWORK").value="";
			}
			if(datatype=='21'){
				cliquepool=name;
				$("#NETWORK").val(cliquepool);
				document.getElementById("oilName").value="";
				document.getElementById("stationName").value="";
			}
			
			grid.setOptions({
        		parms: [{
					name: 'oilstationname',
					value: $("#oilName").val()
				},{
					name: 'blockstationname',
					value: $("#stationName").val()
				},{
					name: 'cliquepool',
					value: $("#NETWORK").val()
				},{
					name: 'date',
					value: $("#txtDate").val()
				},{
					name: 'date1',
					value: $("#txtDate1").val()
				}
				]
        	});
         	grid.loadData(true);
		}
        
           function onSubmit()
        {
        
        
    
			exportFlag = false;
			
          
        	grid.setOptions({
        		parms: [
        		{
					name: 'oilstationname',
					value: $("#oilName").val()
				},{
					name: 'blockstationname',
					value: $("#stationName").val()
				},{
					name: 'cliquepool',
					value: $("#NETWORK").val()
				},{
					name: 'date',
					value: $("#txtDate").val()
				},{
					name: 'date1',
					value: $("#txtDate1").val()
				}
				]
        	});
         	grid.loadData(true);
        }
	
	function getAllInitData() {
        	//$("#oilText").ligerGetComboBoxManager().setValue('');
    		//$("#stationName").ligerGetComboBoxManager().setValue('');
    		//$("#NETWORK").ligerGetComboBoxManager().setValue('');
    		var oilText=[{ id: 1 , text: '' }];
    		var stationText=[{ id: 1 , text: '' }];
    		var pipeText=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'gwgh_cascadeInit.action?nowdata='+parseInt(Math.random()*100000),
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'oilText') {
    						oilText = val;
    					}
    					if (key == 'stationText'){
    						stationText = val;
    					}
    					if (key == 'pipeText'){
    						pipeText = val;
    					} 
    				});
    				setInitData(oilText,stationText,pipeText);
    			}
    		});
    	}
    	
        function setInitData(oilText,stationText,pipeText) {
    		liger.get("oilName").setData(oilText);
    		liger.get("stationName").setData(stationText);
    		liger.get("NETWORK").setData(pipeText);
    	}
        function OnChangeData(oilname,stationname) {
        	//$("#oilText").ligerGetComboBoxManager().setValue('');
    		//$("#stationName").ligerGetComboBoxManager().setValue('');
    		//$("#NETWORK").ligerGetComboBoxManager().setValue('');
    		//var oilText=[{ id: 1 , text: '' }];
    		var stationText=[{ id: 1 , text: '' }];
    		var pipeText=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'gwgh_searchOnChangeOil.action',
    			data :{"oilname":oilname,"stationname":stationname},
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					
    					if (key == 'stationText'){
    						stationText = val;
    					}
    					if (key == 'pipeText'){
    						pipeText = val;
    					} 
    				});
    				setOnChangeData(stationText,pipeText);
    			}
    		});
    	}
    	
        function setOnChangeData(stationText,pipeText) {
    		//liger.get("oilName").setData(oilText);
    		liger.get("stationName").setData(stationText);
    		liger.get("NETWORK").setData(pipeText);
    	}
        
    </script>
    <style type="text/css"> 
    html,body
	{
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
					<td align="right" class="l-table-edit-td" style="width:60px">采油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilName" name = "oilName" />
		                </td>
		                
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:60px">注转站：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="stationName" name = "stationName"/>
		                </td>
		                <td align="left">
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">管网管汇：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="NETWORK" name = "NETWORK"/>
		                </td>
		              
		                
		                <td align="left">
	                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
					<!-- <td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td> -->
				</tr>
			</table>
        </form>
    </div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
 
</body>
</html>