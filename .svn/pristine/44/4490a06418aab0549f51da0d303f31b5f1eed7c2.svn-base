<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>井站采集点信息管理</title>
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
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        //鼠标选择数据
		var  edit_CODE;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'jzcjdxx_pageInit.action',
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
               //对象类型代码
    			$("#OBJECT_CODE_Q").ligerComboBox({
    				//url:'waterSoWell_queryCombinationInfo.action',
    	            valueField: 'id',
    				valueFieldID: 'oilOrgId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    	            hideOnLoseFocus:true,
    	            autocomplete:true,
    	            onSelected:function (data){
   					if ( data != null && typeof(data)!="undefined" && data != '' && data !=1) {
   						OnChangeData($("#oilOrgId").val());
   					}
   					//else{
   						//getAllInitData();
       					//}
   	            	
    				}
    			});
    			//1次关联设备
    			$("#INSTRUMENT_CALLED_Q").ligerComboBox({
    				//url:'waterSoWell_queryWellInfo.action',
    				valueField: 'id',
    				valueFieldID: 'qdxId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:120,
    				hideOnLoseFocus:true,
    	            autocomplete:true
    			});
    			//是否控制
    			$("#CONTROL_OR_Q").ligerComboBox({
 		      			hideOnLoseFocus:true,
 		 					autocomplete:true,
 		 		                data: [
 		 		                    { text: '是', id: '0' },
 		 		                    { text: '否', id: '1' }
 		 		                    
 		 		                ], valueFieldID: 'CONTROL_ORId',
 		 					initText :''

	     		});
    			// 是否接入
   			 $("#ACCESS_SIGNS_Q").ligerComboBox({
   				 hideOnLoseFocus:true,
	 					autocomplete:true,
	 		                data: [
	 		                    { text: '是', id: '0' },
	 		                    { text: '否', id: '1' }
	 		                    
	 		                ], valueFieldID: 'SIGNSId',
	 					initText :'0'
  		     		});
   			 document.getElementById("ACCESS_SIGNS_Q").value = "是";
    			//控制器型号
    			$("#CONTROLLER_TYPE_Q").ligerComboBox({
    				//url:'gwgh_searchStation.action',
    				valueField: 'id',
    				valueFieldID: 'KZQid',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:120,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				}
    			});
    			//点类型
    			 $("#POINT_TYPE_Q").ligerComboBox({
   				  //url:'comboboxdata_searchSwitchInflg.action', 
   	     			valueField: 'id',
   	     			valueFieldID: 'POINTid',
   	     			textField: 'text',
   	     			selectBoxHeight:150,
   	     			selectBoxWidth:100,
   	     			width: 120,
   	     			autocomplete:true,
   	     			hideOnLoseFocus:true
   		     		});
    			// 报警否
    			 $("#ALARM_OR_Q").ligerComboBox({
    				 hideOnLoseFocus:true,
	 					autocomplete:true,
	 		                data: [
	 		                    { text: '是', id: '0' },
	 		                    { text: '否', id: '1' }
	 		                    
	 		                ], valueFieldID: 'QlsId',
	 					initText :''
   		     		});
    			// 报警级别代码
    			  $("#ALARM_CODE_Q").ligerComboBox({
  	     			//url:'sagd_getServerNode.action',
  	     			valueField: 'id',
  	     			valueFieldID: 'hidserverid',
  	     			textField: 'text',
  	     			selectBoxHeight:150,
  	     			selectBoxWidth:100,
  	     			width: 120,
  	     			autocomplete:true,
  	     			hideOnLoseFocus:true

  	     		});
    	     		//历史曲线启用否
    			$("#HISTORY_CURVE_Q").ligerComboBox({
 		      			hideOnLoseFocus:true,
 		 					autocomplete:true,
 		 		                data: [
 		 		                    { text: '是', id: '0' },
 		 		                    { text: '否', id: '1' }
 		 		                    
 		 		                ], valueFieldID: 'QlsId',
 		 					initText :''

	     		});
    	     		//建点时间
			  $("#CREATE_DATE_Q").ligerDateEditor(
                {
                    format: "yyyy-MM-dd ",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
			  $("#CREATE_DATE_QA").ligerDateEditor(
                {
                    format: "yyyy-MM-dd ",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
			//点代码
 			/* $("#POINT_CODE_Q").ligerComboBox({
				  //url:'comboboxdata_searchSwitchInflg.action', 
	     			valueField: 'id',
	     			valueFieldID: 'PCid',
	     			textField: 'text',
	     			selectBoxHeight:150,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true
		     		});*/
			//编辑
			 //对象类型代码
    			$("#OBJECT_CODE").ligerComboBox({
    	            valueField: 'id',
    				valueFieldID: 'dxledmId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    	            hideOnLoseFocus:true,
    	            autocomplete:true,
    	            onSelected:function (data){
    					if ( data != null && typeof(data)!="undefined" && data != '' && data !=1) {
    						OnChangeDataEdit($("#dxledmId").val());
    					}
    					//else{
    						//getAllInitData();
        					//}
    				}
    			});
    			//1次关联设备
    			$("#YCGLSB").ligerComboBox({
    				//url:'waterSoWell_queryWellInfo.action',
    				valueField: 'id',
    				valueFieldID: 'ycglsbId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:120,
    				hideOnLoseFocus:true,
    	            autocomplete:true
    			});
    			//控制器型号
    			$("#JRKZQ").ligerComboBox({
    				//url:'gwgh_searchStation.action',
    				valueField: 'id',
    				valueFieldID: 'JRKZQId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:120,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				}
    			});
    			//点类型
    			 $("#POINT_TYPE").ligerComboBox({
   				  //url:'comboboxdata_searchSwitchInflg.action', 
   	     			valueField: 'id',
   	     			valueFieldID: 'dlxId',
   	     			textField: 'text',
   	     			selectBoxHeight:150,
   	     			selectBoxWidth:100,
   	     			width: 120,
   	     			autocomplete:true,
   	     			hideOnLoseFocus:true
   		     		});
    			//点代码
    			/* $("#POINT_CODE").ligerComboBox({
   				  //url:'comboboxdata_searchSwitchInflg.action', 
   	     			valueField: 'id',
   	     			valueFieldID: 'dId',
   	     			textField: 'text',
   	     			selectBoxHeight:150,
   	     			selectBoxWidth:100,
   	     			width: 120,
   	     			autocomplete:true,
   	     			hideOnLoseFocus:true
   		     		});*/
    			// 报警级别代码
    			  $("#ALARM_VALUE").ligerComboBox({
  	     			//url:'sagd_getServerNode.action',
  	     			valueField: 'id',
  	     			valueFieldID: 'bjjbId',
  	     			textField: 'text',
  	     			selectBoxHeight:150,
  	     			selectBoxWidth:100,
  	     			width: 120,
  	     			autocomplete:true,
  	     			hideOnLoseFocus:true

  	     		});
    	     		//厂家数据类型
    			 $("#DATA_TYPE").ligerComboBox({
  	     			//url:'sagd_getServerNode.action',
  	     			valueField: 'id',
  	     			valueFieldID: 'basId',
  	     			textField: 'text',
  	     			selectBoxHeight:150,
  	     			selectBoxWidth:100,
  	     			width: 120,
  	     			autocomplete:true,
  	     			hideOnLoseFocus:true

  	     		});
    			 $("#DATA_TYPE2").ligerComboBox({
  	     			//url:'sagd_getServerNode.action',
  	     			valueField: 'id',
  	     			valueFieldID: 'basId',
  	     			textField: 'text',
  	     			selectBoxHeight:150,
  	     			selectBoxWidth:100,
  	     			width: 120,
  	     			autocomplete:true,
  	     			hideOnLoseFocus:true

  	     		});
    			//是否控制
      			$("#CONTROL_OR").ligerComboBox({
   		      			hideOnLoseFocus:true,
   		 					autocomplete:true,
   		 		                data: [
   		 		                    { text: '是', id: '0' },
   		 		                    { text: '否', id: '1' }
   		 		                    
   		 		                ], valueFieldID: 'kzId',
   		 					initText :''

  	     		});
    	     		//历史曲线启用否
    			  $("#HISTORY_CURVE").ligerComboBox({
   		      			hideOnLoseFocus:true,
   		 					autocomplete:true,
   		 		                data: [
   		 		                    { text: '是', id: '0' },
   		 		                    { text: '否', id: '1' }
   		 		                    
   		 		                ], valueFieldID: 'lsId',
   		 					initText :''

  	     		});
  	     		
    	     		//关系数据库存储
    			  $("#ORACLE_SAVE").ligerComboBox({
   		      			hideOnLoseFocus:true,
   		 					autocomplete:true,
   		 		                data: [
   		 		                    { text: '是', id: '0' },
   		 		                    { text: '否', id: '1' }
   		 		                    
   		 		                ], valueFieldID: 'gxId',
   		 					initText :''

  	     		});
  	     			// 报警否
    			 $("#ALARM_OR").ligerComboBox({
    				 hideOnLoseFocus:true,
	 					autocomplete:true,
	 		                data: [
	 		                    { text: '是', id: '0' },
	 		                    { text: '否', id: '1' }
	 		                    
	 		                ], valueFieldID: 'QlsId',
	 					initText :''
   		     		});
    				// 接入标识
    			 $("#ACCESS_SIGNS").ligerComboBox({
    				 hideOnLoseFocus:true,
	 					autocomplete:true,
	 		                data: [
	 		                    { text: '是', id: '0' },
	 		                    { text: '否', id: '1' }
	 		                    
	 		                ], valueFieldID: 'SIGNSId',
	 					initText :''
   		     		});
    			  getAllInitData();
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
                		
                    
                },
                submitHandler: function ()
                {
                	 var OBJECT_CODE = "";
                	 if( $("#OBJECT_CODE").val() !=''){
                		 OBJECT_CODE = $("#dxledmId").val();
                    	 }
					 var POINT_NAME  = $("#POINT_NAME").val();
                	 var INTERFACE_REMARK = $("#INTERFACE_REMARK").val();
                	 var ACCESS_SIGNS = $("#ACCESS_SIGNS").val();
                	 var POINT_TYPE = $("#POINT_TYPE").val();
                	 var RANGES_UPPER =$("#RANGES_UPPER").val();
                	 var RANGES_DOWN = $("#RANGES_DOWN").val();
                	 var IFIX_UNIT = $("#IFIX_UNIT").val();
                	 var CONTROL_OR = $("#CONTROL_OR").val();
                	 var HISTORY_CURVE = $("#HISTORY_CURVE").val();
                	 var ORACLE_SAVE = $("#ORACLE_SAVE").val();
                	 var ALARM_OR = $("#ALARM_OR").val();
                	 var ALARM_VALUE = $("#ALARM_VALUE").val();
                	 var ALARM_LIMITLL = $("#ALARM_LIMITLL").val();
                	 var ALARM_LIMITL = $("#ALARM_LIMITL").val();
                	 var ALARM_LIMITH = $("#ALARM_LIMITH").val();
                	 var ALARM_LIMITHH = $("#ALARM_LIMITHH").val();
                	 var REMARK = $("#REMARK").val();
                	 var JRKZQ ="";
                	 if( $("#JRKZQ").val() !=''){
                		 JRKZQ = $("#JRKZQId").val();
                		 }
                	// var DATA_TYPE ="";
                	 //if( $("#DATA_TYPE").val() !=''){
                		// DATA_TYPE = $("#basId").val();
                		// }
                	 var DATA_TYPE = $("#DATA_TYPE").val();
                	 var PLC_ADDRESS = $("#PLC_ADDRESS").val();
                	 var POINT_DESC = $("#POINT_DESC").val();
                	 var POINT_REMARK = $("#POINT_REMARK").val();
                	 var VALUE_RATIO = $("#VALUE_RATIO").val();
                	 var MAILING_ADDRESS = $("#MAILING_ADDRESS").val();
                	 var MAILING_ADDRESSB = $("#MAILING_ADDRESSB").val();
                	 var POINT_CODE = $("#POINT_CODE").val();
                	 var YCGLSB = "";
                	 if( $("#YCGLSB").val() !=''){
                		 YCGLSB = $("#ycglsbId").val();
    					 }
                	 var DATA_TYPE2 = $("#DATA_TYPE2").val();
                	 var PLC_ADDRESS2= $("#PLC_ADDRESS2").val();
                	 var POINT_DESC2 = $("#POINT_DESC2").val();
                	 var POINT_REMARK2 = $("#POINT_REMARK2").val();
                	 var VALUE_RATIO2 = $("#VALUE_RATIO2").val();
                	 var MAILING_ADDRESS2 = $("#MAILING_ADDRESS2").val();
                	 var MAILING_ADDRESSB2 = $("#MAILING_ADDRESSB2").val();
    				 var SMALL_STATION_ID = $("#SMALL_STATION_ID").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'jzcjdxx_saveorUpdateData.action',
									async : false,
									data: {"OBJECT_CODE":OBJECT_CODE,"POINT_NAME":POINT_NAME,"INTERFACE_REMARK":INTERFACE_REMARK ,"ACCESS_SIGNS":ACCESS_SIGNS,
											"POINT_TYPE":POINT_TYPE,"RANGES_UPPER":RANGES_UPPER,"RANGES_DOWN":RANGES_DOWN,"IFIX_UNIT":IFIX_UNIT,"CONTROL_OR":CONTROL_OR,
											"HISTORY_CURVE":HISTORY_CURVE,"ORACLE_SAVE":ORACLE_SAVE,"ALARM_OR":ALARM_OR,"ALARM_VALUE":ALARM_VALUE,"ALARM_LIMITLL":ALARM_LIMITLL,
											"ALARM_LIMITL":ALARM_LIMITL,"ALARM_LIMITH":ALARM_LIMITH,"ALARM_LIMITHH":ALARM_LIMITHH,"REMARK":REMARK,"JRKZQ":JRKZQ,
											"DATA_TYPE":DATA_TYPE,"PLC_ADDRESS":PLC_ADDRESS,"POINT_DESC":POINT_DESC,"POINT_REMARK":POINT_REMARK,"VALUE_RATIO":VALUE_RATIO,
											"MAILING_ADDRESS":MAILING_ADDRESS,"MAILING_ADDRESSB":MAILING_ADDRESSB,"POINT_CODE":POINT_CODE,"YCGLSB":YCGLSB,"DATA_TYPE2":DATA_TYPE2,
											"POINT_DESC2":POINT_DESC2,"POINT_REMARK2":POINT_REMARK2,"VALUE_RATIO2":VALUE_RATIO2,"MAILING_ADDRESS2":MAILING_ADDRESS2,
											"MAILING_ADDRESSB2":MAILING_ADDRESSB2,"SMALL_STATION_ID":SMALL_STATION_ID,"PLC_ADDRESS2":PLC_ADDRESS2},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"&& data!=""){
																var outData = eval('(' + data + ')');
																//alert(data);
																if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
																	$.ligerDialog.error(outData.ERRMSG);
																}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
																	$.ligerDialog.error(outerror(outData.ERRCODE));
																}else{
																	onSubmit();
																	setpage4(1); //隐藏编辑界面
																	setItemsd(2); //去掉隐藏，显示按钮
																	$.ligerDialog.success('添加成功！');
																	operate = 0;
																		
																}
									
													}
												},
									error : function(data) {
								
									}
								});
              	   //2-修改
              	   }else if(operate == 2){
              	   		 jQuery.ajax({
									type : 'post',
									url : 'jzcjdxx_saveorUpdateData.action',
									async : false,
									data: {"OBJECT_CODE":OBJECT_CODE,"POINT_NAME":POINT_NAME,"INTERFACE_REMARK":INTERFACE_REMARK ,"ACCESS_SIGNS":ACCESS_SIGNS,
									"POINT_TYPE":POINT_TYPE,"RANGES_UPPER":RANGES_UPPER,"RANGES_DOWN":RANGES_DOWN,"IFIX_UNIT":IFIX_UNIT,"CONTROL_OR":CONTROL_OR,
									"HISTORY_CURVE":HISTORY_CURVE,"ORACLE_SAVE":ORACLE_SAVE,"ALARM_OR":ALARM_OR,"ALARM_VALUE":ALARM_VALUE,"ALARM_LIMITLL":ALARM_LIMITLL,
									"ALARM_LIMITL":ALARM_LIMITL,"ALARM_LIMITH":ALARM_LIMITH,"ALARM_LIMITHH":ALARM_LIMITHH,"REMARK":REMARK,"JRKZQ":JRKZQ,
									"DATA_TYPE":DATA_TYPE,"PLC_ADDRESS":PLC_ADDRESS,"POINT_DESC":POINT_DESC,"POINT_REMARK":POINT_REMARK,"VALUE_RATIO":VALUE_RATIO,
									"MAILING_ADDRESS":MAILING_ADDRESS,"MAILING_ADDRESSB":MAILING_ADDRESSB,"POINT_CODE":POINT_CODE,"YCGLSB":YCGLSB,"DATA_TYPE2":DATA_TYPE2,
									"POINT_DESC2":POINT_DESC2,"POINT_REMARK2":POINT_REMARK2,"VALUE_RATIO2":VALUE_RATIO2,"MAILING_ADDRESS2":MAILING_ADDRESS2,
									"MAILING_ADDRESSB2":MAILING_ADDRESSB2,"SMALL_STATION_ID":SMALL_STATION_ID,"PLC_ADDRESS2":PLC_ADDRESS2 },
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"&& data!=""){
																var outData = eval('(' + data + ')');
																//alert(data);
																if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
																	$.ligerDialog.error(outData.ERRMSG);
																}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
																	$.ligerDialog.error(outerror(outData.ERRCODE));
																}else{
																	onSubmit();
																	setpage4(1); //隐藏编辑界面
																	setItemsd(2); //去掉隐藏，显示按钮
																	$.ligerDialog.success('修改成功！');
																	operate = 0;
																		
																}
									
													}
												},
									error : function(data) {
								
									}
								});
              	   
              	   }
		           //operate = 0;
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
        							
									if (rowdata.OBJECT_TYPE_NAME != null && typeof(rowdata.OBJECT_TYPE_NAME)!="undefined"){
										OBJECT_TYPE_NAME = rowdata.OBJECT_TYPE_NAME;
									 }else{
										 OBJECT_TYPE_NAME = "";
									 }
									if (rowdata.OBJECT_CODE != null && typeof(rowdata.OBJECT_CODE)!="undefined"){
										OBJECT_CODE = rowdata.OBJECT_CODE;
									 }else{
										 OBJECT_CODE = "";
									 }
									if (rowdata.POINT_NAME != null && typeof(rowdata.POINT_NAME)!="undefined"){
										POINT_NAME = rowdata.POINT_NAME;
									 }else{
										 POINT_NAME = "";
									 }
									
									if (rowdata.INTERFACE_REMARK != null && typeof(rowdata.INTERFACE_REMARK)!="undefined"){
										INTERFACE_REMARK = rowdata.INTERFACE_REMARK;
									 }else{
										 INTERFACE_REMARK = "";
									 }

									if (rowdata.ACCESS_SIGNS != null && typeof(rowdata.ACCESS_SIGNS)!="undefined"){
										ACCESS_SIGNS = rowdata.ACCESS_SIGNS;
									 }else{
										 ACCESS_SIGNS = "";
									 }
									if (rowdata.POINT_TYPE != null && typeof(rowdata.POINT_TYPE)!="undefined"){
										POINT_TYPE = rowdata.POINT_TYPE;
									 }else{
										POINT_TYPE = "";
									 }				
									if (rowdata.RANGES_UPPER != null && typeof(rowdata.RANGES_UPPER)!="undefined"){
										RANGES_UPPER = rowdata.RANGES_UPPER;
									 }else{
										 RANGES_UPPER = "";
									 }				
									if (rowdata.RANGES_DOWN != null && typeof(rowdata.RANGES_DOWN)!="undefined"){
										RANGES_DOWN = rowdata.RANGES_DOWN;
									 }else{
										 RANGES_DOWN = "";
									 }
									if (rowdata.IFIX_UNIT != null && typeof(rowdata.IFIX_UNIT)!="undefined"){
										IFIX_UNIT = rowdata.IFIX_UNIT;
									 }else{
										 IFIX_UNIT = "";
									 }
									if (rowdata.CONTROL_OR != null && typeof(rowdata.CONTROL_OR)!="undefined"){
										CONTROL_OR = rowdata.CONTROL_OR; 
									 }else{
										 CONTROL_OR = "";
									 }
									 if (rowdata.HISTORY_CURVE != null && typeof(rowdata.HISTORY_CURVE)!="undefined"){
										 HISTORY_CURVE = rowdata.HISTORY_CURVE; 
									 }else{
										 HISTORY_CURVE = "";
									 }
									 if (rowdata.ORACLE_SAVE != null && typeof(rowdata.ORACLE_SAVE)!="undefined"){
										 ORACLE_SAVE = rowdata.ORACLE_SAVE; 
									 }else{
										 ORACLE_SAVE = "";
									 }
									 if (rowdata.ALARM_OR != null && typeof(rowdata.ALARM_OR)!="undefined"){
										 ALARM_OR = rowdata.ALARM_OR; 
									 }else{
										 ALARM_OR = "";
									 }
									 if (rowdata.ALARM_VALUE != null && typeof(rowdata.ALARM_VALUE)!="undefined"){
										 ALARM_VALUE = rowdata.ALARM_VALUE; 
									 }else{
										 ALARM_VALUE = "";
									 }

									 if (rowdata.ALARM_LIMITLL != null && typeof(rowdata.ALARM_LIMITLL)!="undefined"){
										 ALARM_LIMITLL = rowdata.ALARM_LIMITLL; 
									 }else{
										 ALARM_LIMITLL = "";
									 }
									 if (rowdata.ALARM_LIMITL != null && typeof(rowdata.ALARM_LIMITL)!="undefined"){
										 ALARM_LIMITL = rowdata.ALARM_LIMITL; 
									 }else{
										 ALARM_LIMITL = "";
									 }
									 if (rowdata.ALARM_LIMITH != null && typeof(rowdata.ALARM_LIMITH)!="undefined"){
										 ALARM_LIMITH = rowdata.ALARM_LIMITH; 
									 }else{
										 ALARM_LIMITH = "";
									 }
									 if (rowdata.ALARM_LIMITHH != null && typeof(rowdata.ALARM_LIMITHH)!="undefined"){
										 ALARM_LIMITHH = rowdata.ALARM_LIMITHH; 
									 }else{
										 ALARM_LIMITHH = "";
									 }
									 if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
										 REMARK = rowdata.REMARK; 
									 }else{
										 REMARK = "";
									 }
									 if (rowdata.TORTU_CODE != null && typeof(rowdata.TORTU_CODE)!="undefined"){
										 TORTU_CODE = rowdata.TORTU_CODE; 
									 }else{
										 TORTU_CODE = "";
									 }
									 if (rowdata.JRKZQ != null && typeof(rowdata.JRKZQ)!="undefined"){
										 JRKZQ = rowdata.JRKZQ; 
									 }else{
										 JRKZQ = "";
									 }
									 if (rowdata.INSTRUMENT_CALLED != null && typeof(rowdata.INSTRUMENT_CALLED)!="undefined"){
										 INSTRUMENT_CALLED = rowdata.INSTRUMENT_CALLED; 
									 }else{
										 INSTRUMENT_CALLED = "";
									 }
									 if (rowdata.YCGLSB != null && typeof(rowdata.YCGLSB)!="undefined"){
										 YCGLSB = rowdata.YCGLSB; 
									 }else{
										 YCGLSB = "";
									 }
									 
									 if (rowdata.POINT_CODE != null && typeof(rowdata.POINT_CODE)!="undefined"){
										 POINT_CODE = rowdata.POINT_CODE; 
									 }else{
										 POINT_CODE = "";
									 }
									 if (rowdata.DATA_TYPE != null && typeof(rowdata.DATA_TYPE)!="undefined"){
										 DATA_TYPE = rowdata.DATA_TYPE; 
									 }else{
										 DATA_TYPE = "";
									 }
									 if (rowdata.PLC_ADDRESS != null && typeof(rowdata.PLC_ADDRESS)!="undefined"){
										 PLC_ADDRESS = rowdata.PLC_ADDRESS; 
									 }else{
										 PLC_ADDRESS = "";
									 }
									 if (rowdata.POINT_DESC != null && typeof(rowdata.POINT_DESC)!="undefined"){
										 POINT_DESC = rowdata.POINT_DESC; 
									 }else{
										 POINT_DESC = "";
									 }
									 if (rowdata.POINT_REMARK != null && typeof(rowdata.POINT_REMARK)!="undefined"){
										 POINT_REMARK = rowdata.POINT_REMARK; 
									 }else{
										 POINT_REMARK = "";
									 }
									 if (rowdata.VALUE_RATIO != null && typeof(rowdata.VALUE_RATIO)!="undefined"){
										 VALUE_RATIO = rowdata.VALUE_RATIO; 
									 }else{
										 VALUE_RATIO = "";
									 }


									 if (rowdata.MAILING_ADDRESS != null && typeof(rowdata.MAILING_ADDRESS)!="undefined"){
										 MAILING_ADDRESS = rowdata.MAILING_ADDRESS; 
									 }else{
										 MAILING_ADDRESS = "";
									 }
									 if (rowdata.MAILING_ADDRESSB != null && typeof(rowdata.MAILING_ADDRESSB)!="undefined"){
										 MAILING_ADDRESSB = rowdata.MAILING_ADDRESSB; 
									 }else{
										 MAILING_ADDRESSB = "";
									 }
									 if (rowdata.DATA_TYPE2 != null && typeof(rowdata.DATA_TYPE2)!="undefined"){
										 DATA_TYPE2 = rowdata.DATA_TYPE2; 
									 }else{
										 DATA_TYPE2 = "";
									 }
									 if (rowdata.PLC_ADDRESS2 != null && typeof(rowdata.PLC_ADDRESS2)!="undefined"){
										 PLC_ADDRESS2 = rowdata.PLC_ADDRESS2; 
									 }else{
										 PLC_ADDRESS2 = "";
									 }
									 if (rowdata.POINT_DESC2 != null && typeof(rowdata.POINT_DESC2)!="undefined"){
										 POINT_DESC2 = rowdata.POINT_DESC2; 
									 }else{
										 POINT_DESC2 = "";
									 }
									 if (rowdata.POINT_REMARK2 != null && typeof(rowdata.POINT_REMARK2)!="undefined"){
										 POINT_REMARK2 = rowdata.POINT_REMARK2; 
									 }else{
										 POINT_REMARK2 = "";
									 }
									 if (rowdata.VALUE_RATIO2 != null && typeof(rowdata.VALUE_RATIO2)!="undefined"){
										 VALUE_RATIO2 = rowdata.VALUE_RATIO2; 
									 }else{
										 VALUE_RATIO2 = "";
									 }
									 if (rowdata.MAILING_ADDRESS2 != null && typeof(rowdata.MAILING_ADDRESS2)!="undefined"){
										 MAILING_ADDRESS2 = rowdata.MAILING_ADDRESS2; 
									 }else{
										 MAILING_ADDRESS2 = "";
									 }
									 if (rowdata.MAILING_ADDRESSB2 != null && typeof(rowdata.MAILING_ADDRESSB2)!="undefined"){
										 MAILING_ADDRESSB2 = rowdata.MAILING_ADDRESSB2; 
									 }else{
										 MAILING_ADDRESSB2 = "";
									 }
									 if (rowdata.SMALL_STATION_ID != null && typeof(rowdata.SMALL_STATION_ID)!="undefined"){
										 SMALL_STATION_ID = rowdata.SMALL_STATION_ID; 
									 }else{
										 SMALL_STATION_ID = "";
									 }
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }

     

      
        function getAllInitData() {
    		var typeText=[{ id: 1 , text: '' }];
    		var sysText=[{ id: 1 , text: '' }];
    		var facText=[{ id: 1 , text: '' }];

    		var conText=[{ id: 1 , text: '' }];
    		var poiText=[{ id: 1 , text: '' }];
    		var alaText=[{ id: 1 , text: '' }];
    		var basText=[{ id: 1 , text: '' }];
    		
    		
    		jQuery.ajax({
    			type : 'post',
    			url : 'jzcjdxx_cascadeInit.action?nowdata='+parseInt(Math.random()*100000),
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'typeText') {
    						typeText = val;
    					}
    					if (key == 'sysText'){
    						sysText = val;
    					}
    					if (key == 'facText'){
    						facText = val;
    					} 
    					if (key == 'conText'){
    						conText = val;
    					} 
    					if (key == 'poiText'){
    						poiText = val;
    					} 
    					if (key == 'alaText'){
    						alaText = val;
    					} 
    					if (key == 'basText'){
    						basText = val;
    					} 
    					
    				});
    				setInitData(typeText,sysText,facText,conText,poiText,alaText,basText);
    			}
    		});
    	}
    	
        function setInitData(typeText,sysText,facText,conText,poiText,alaText,basText) {
    		liger.get("OBJECT_CODE_Q").setData(typeText);
    		liger.get("INSTRUMENT_CALLED_Q").setData(sysText);
    		liger.get("CONTROLLER_TYPE_Q").setData(facText);
    		liger.get("POINT_TYPE_Q").setData(poiText);
    		//liger.get("POINT_CODE_Q").setData(conText);
    		liger.get("ALARM_CODE_Q").setData(alaText);

    		liger.get("OBJECT_CODE").setData(typeText);
    		liger.get("YCGLSB").setData(sysText);
    		liger.get("JRKZQ").setData(facText);
    		liger.get("POINT_TYPE").setData(poiText);
    		liger.get("ALARM_VALUE").setData(alaText);
    		//liger.get("POINT_CODE").setData(conText);
    		liger.get("DATA_TYPE").setData(basText);
    		liger.get("DATA_TYPE2").setData(basText);
    	}
        function OnChangeData(queID) {
    		var QUYCGLSBText=[{ id: 1 , text: '' }];
    		var QUJRKZQText=[{ id: 1 , text: '' }];
    	
    		var YCGLSBText=[{ id: 1 , text: '' }];
    		var JRKZQText=[{ id: 1 , text: '' }];
    		
    		jQuery.ajax({
    			type : 'post',
    			url : 'jzcjdxx_OnChangeData.action?nowdata='+parseInt(Math.random()*100000),
    			data :{"queID":queID},
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'QUYCGLSBText') {
    						QUYCGLSBText = val;
    					}
    					if (key == 'QUJRKZQText'){
    						QUJRKZQText = val;
    					}
    					
    					if (key == 'YCGLSBText'){
    						YCGLSBText = val;
    					} 
    					if (key == 'JRKZQText'){
    						JRKZQText = val;
    					} 
    					
    					
    				});
    				setGetData(QUYCGLSBText,QUJRKZQText,YCGLSBText,JRKZQText);
    			}
    		});
    	}
    	
        function setGetData(QUYCGLSBText,QUJRKZQText,YCGLSBText,JRKZQText) {
    		liger.get("INSTRUMENT_CALLED_Q").setData(QUYCGLSBText);
    		liger.get("CONTROLLER_TYPE_Q").setData(QUJRKZQText);

    		//liger.get("YCGLSB").setData(YCGLSBText);
    		//liger.get("JRKZQ").setData(JRKZQText);
    		
    	}
        function OnChangeDataEdit(editId) {
    		//var QUYCGLSBText=[{ id: 1 , text: '' }];
    		//var QUJRKZQText=[{ id: 1 , text: '' }];
    	
    		var YCGLSBText=[{ id: 1 , text: '' }];
    		var JRKZQText=[{ id: 1 , text: '' }];
    		
    		jQuery.ajax({
    			type : 'post',
    			url : 'jzcjdxx_OnChangeDataEdit.action?nowdata='+parseInt(Math.random()*100000),
    			data :{"editId":editId},
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'YCGLSBText'){
    						YCGLSBText = val;
    					} 
    					if (key == 'JRKZQText'){
    						JRKZQText = val;
    					} 
    					
    					
    				});
    				setGetDataEdit(YCGLSBText,JRKZQText);
    			}
    		});
    	}
    	
        function setGetDataEdit(YCGLSBText,JRKZQText) {
    		liger.get("YCGLSB").setData(YCGLSBText);
    		liger.get("JRKZQ").setData(JRKZQText);
    		
    	}
        
      
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){
			var oilName='';
			var stationName='';
			var NETWORK='';
			$("#oilName").ligerGetComboBoxManager().setValue('');
			$("#stationName").ligerGetComboBoxManager().setValue('');
			$("#NETWORK").ligerGetComboBoxManager().setValue('');
			
			if(datatype=='4'){
				oilName=name;
				$("#oilName").val(name);
			}
			
			if(datatype=='7'){
				stationName=name;
				$("#stationName").val(name);
			}
			
			if(datatype=='21'){
				NETWORK=name;
				$("#NETWORK").val(name);
			}
			
			grid.setOptions({
        		parms: [{name: 'oilName', value: oilName},
				{name: 'stationName', value: stationName},
				{name: 'NETWORK',value: NETWORK}
				]
        	});
         	grid.loadData(true);
		}
		
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [
                		{name: 'OBJECT_CODE_Q',value: $("#OBJECT_CODE_Q").val()},
        		        {name: 'INSTRUMENT_CALLED_Q',value: $("#INSTRUMENT_CALLED_Q").val()},
        		        {name: 'CONTROL_OR_Q',value: $("#CONTROL_OR_Q").val()},
        		        {name: 'ACCESS_SIGNS_Q',value: $("#ACCESS_SIGNS_Q").val()},
        		        {name: 'CONTROLLER_TYPE_Q',value: $("#CONTROLLER_TYPE_Q").val()},
        		        {name: 'POINT_TYPE_Q',value: $("#POINT_TYPE_Q").val()},
        		        {name: 'ALARM_OR_Q',value: $("#ALARM_OR_Q").val()},
						{name: 'ALARM_CODE_Q', value:$("#ALARM_CODE_Q").val()},
						{name: 'HISTORY_CURVE_Q', value:$("#HISTORY_CURVE_Q").val()},
						{name: 'CREATE_DATE_Q',value: $("#CREATE_DATE_Q").val()},
						{name: 'CREATE_DATE_QA', value:$("#CREATE_DATE_QA").val()},
						{name: 'POINT_CODE_Q', value:$("#POINT_CODE_Q").val()
					}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
				 $("#OBJECT_CODE").ligerGetComboBoxManager().selectValue(OBJECT_CODE);
				 document.getElementById("POINT_NAME").value =POINT_NAME;
				 document.getElementById("INTERFACE_REMARK").value =INTERFACE_REMARK;
				 if (ACCESS_SIGNS == '是') {
						$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue(0);
					}
					else if(ORACLE_SAVE == '否'){
						$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue(1);
					}else{
						$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue('');
				}
				 $("#POINT_TYPE").ligerGetComboBoxManager().selectValue(POINT_TYPE);
				 document.getElementById("RANGES_UPPER").value =RANGES_UPPER;
				 document.getElementById("RANGES_DOWN").value =RANGES_DOWN;
				 document.getElementById("IFIX_UNIT").value =IFIX_UNIT;

				 if (CONTROL_OR == '是') {
						$("#CONTROL_OR").ligerGetComboBoxManager().selectValue(0);
					}
					else if(CONTROL_OR == '否'){
						$("#CONTROL_OR").ligerGetComboBoxManager().selectValue(1);
					}else{
						$("#CONTROL_OR").ligerGetComboBoxManager().selectValue('');
				}

				 if (HISTORY_CURVE == '是') {
						$("#HISTORY_CURVE").ligerGetComboBoxManager().selectValue(0);
					}
					else if(HISTORY_CURVE == '否'){
						$("#HISTORY_CURVE").ligerGetComboBoxManager().selectValue(1);
					}else{
						$("#HISTORY_CURVE").ligerGetComboBoxManager().selectValue('');
				}
				 if (ORACLE_SAVE == '是') {
						$("#ORACLE_SAVE").ligerGetComboBoxManager().selectValue(0);
					}
					else if(ORACLE_SAVE == '否'){
						$("#ORACLE_SAVE").ligerGetComboBoxManager().selectValue(1);
					}else{
						$("#ORACLE_SAVE").ligerGetComboBoxManager().selectValue('');
				}
				 if (ALARM_OR == '是') {
						$("#ALARM_OR").ligerGetComboBoxManager().selectValue(0);
					}
					else if(ALARM_OR == '否'){
						$("#ALARM_OR").ligerGetComboBoxManager().selectValue(1);
					}else{
						$("#ALARM_OR").ligerGetComboBoxManager().selectValue('');
				}
				// $("#ALARM_VALUE").ligerGetComboBoxManager().selectValue(ALARM_VALUE);
				 document.getElementById("ALARM_VALUE").value =ALARM_VALUE ;
				 document.getElementById("ALARM_LIMITLL").value =ALARM_LIMITLL ;
				 document.getElementById("ALARM_LIMITL").value = ALARM_LIMITL;
				 document.getElementById("ALARM_LIMITH").value = ALARM_LIMITH;

				 document.getElementById("ALARM_LIMITHH").value =ALARM_LIMITHH ;
				 document.getElementById("REMARK").value =REMARK ;
				 //document.getElementById("JRKZQ").value =JRKZQ ;
				 $("#JRKZQ").ligerGetComboBoxManager().selectValue(TORTU_CODE);
				 document.getElementById("DATA_TYPE").value =DATA_TYPE ;
				 document.getElementById("PLC_ADDRESS").value =PLC_ADDRESS ;
				 document.getElementById("POINT_DESC").value =POINT_DESC ;
				 document.getElementById("POINT_REMARK").value =POINT_REMARK ;
				 document.getElementById("VALUE_RATIO").value =VALUE_RATIO ;
				 document.getElementById("MAILING_ADDRESS").value =MAILING_ADDRESS ;
				 document.getElementById("MAILING_ADDRESSB").value =MAILING_ADDRESSB ;
				 document.getElementById("POINT_CODE").value =POINT_CODE ;
				// document.getElementById("YCGLSB").value =YCGLSB ;
				$("#YCGLSB").ligerGetComboBoxManager().selectValue(INSTRUMENT_CALLED);
				 document.getElementById("DATA_TYPE2").value =DATA_TYPE2 ;
				 document.getElementById("POINT_DESC2").value =POINT_DESC2 ;
				 document.getElementById("POINT_REMARK2").value =POINT_REMARK2 ;
				 document.getElementById("VALUE_RATIO2").value =VALUE_RATIO2 ;
				 document.getElementById("MAILING_ADDRESS2").value =MAILING_ADDRESS2 ;
				 document.getElementById("MAILING_ADDRESSB2").value =MAILING_ADDRESSB2 ;
				 document.getElementById("SMALL_STATION_ID").value =SMALL_STATION_ID ;
			

				 
			 }else if(flg == 1){
				 $("#OBJECT_CODE").ligerGetComboBoxManager().selectValue("");
				 document.getElementById("POINT_NAME").value ="";
				 document.getElementById("INTERFACE_REMARK").value ="";
				 $("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue('');
				 $("#POINT_TYPE").ligerGetComboBoxManager().selectValue("");
				 document.getElementById("RANGES_UPPER").value ="";
				 document.getElementById("RANGES_DOWN").value ="";
				 document.getElementById("IFIX_UNIT").value ="";
				 $("#CONTROL_OR").ligerGetComboBoxManager().selectValue('');
				 $("#HISTORY_CURVE").ligerGetComboBoxManager().selectValue('');
				 $("#ORACLE_SAVE").ligerGetComboBoxManager().selectValue('');
				 $("#ALARM_OR").ligerGetComboBoxManager().selectValue('');
				 
				 document.getElementById("ALARM_VALUE").value ="" ;
				 document.getElementById("ALARM_LIMITLL").value ="" ;
				 document.getElementById("ALARM_LIMITL").value = "";
				 document.getElementById("ALARM_LIMITH").value = "";
				 document.getElementById("ALARM_LIMITHH").value ="" ;
				 document.getElementById("REMARK").value ="" ;
				 document.getElementById("JRKZQ").value ="" ;
				 document.getElementById("DATA_TYPE").value ="" ;
				 document.getElementById("PLC_ADDRESS").value ="" ;
				 document.getElementById("POINT_DESC").value ="" ;
				 document.getElementById("POINT_REMARK").value ="" ;
				 document.getElementById("VALUE_RATIO").value ="" ;
				 document.getElementById("MAILING_ADDRESS").value ="" ;
				 document.getElementById("MAILING_ADDRESSB").value ="" ;
				 document.getElementById("POINT_CODE").value ="" ;
				 document.getElementById("YCGLSB").value =""  ;
				 document.getElementById("DATA_TYPE2").value =""  ;
				 document.getElementById("POINT_DESC2").value ="" ;
				 document.getElementById("POINT_REMARK2").value ="" ;
				 document.getElementById("VALUE_RATIO2").value ="" ;
				 document.getElementById("MAILING_ADDRESS2").value ="" ;
				 document.getElementById("MAILING_ADDRESSB2").value ="" ;
				 document.getElementById("SMALL_STATION_ID").value ="" ;
			
	               
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
            	  if(operate != 1 && operate != 2){
            		  setpage4(0); //显示编辑界面
				 		setItemsd(0);//1-隐藏编辑区添加显示按钮
					}
              	   operate = 1;
              	   assignM(1);
              	   
              	   
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改信息的数据！');
              	    
              	     }else if(rows.length == 1){
							
              	    	if(operate != 1 && operate != 2){
              	    		setpage4(0); //显示编辑界面
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
											url : 'jzcjdxx_removeData.action',
											async : false,
											data: {"SMALL_STATION_ID":SMALL_STATION_ID},
											success : function(data) {
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
														if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
															alert(outData.ERRMSG);
														}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
															alert(outerror(outData.ERRCODE));
														}else{
															$.ligerDialog.success('删除成功');
															onSubmit();
														}
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
            	  setpage4(0); //显示编辑界面
            	    setItemsd(0);//0-显示编辑区，添加隐藏按钮
                break;   
                case "down":
                	setpage4(1); //隐藏编辑界面
				 	setItemsd(1);//1-隐藏编辑区添加显示按钮
                break;    
          }
      }
      function onExport() {
    	   /* var OBJECT_CODE_Q 		= $("#OBJECT_CODE_Q").val();
	        var INSTRUMENT_CALLED_Q = $("#INSTRUMENT_CALLED_Q").val();
	        var FACTURER_CODE_Q 	= $("#FACTURER_CODE_Q").val();
	        var CONTROLLER_TYPE_Q 	= $("#CONTROLLER_TYPE_Q").val();
	        var POINT_TYPE_Q 		= $("#POINT_TYPE_Q").val();
	        var ALARM_CODE_Q 		= $("#ALARM_CODE_Q").val();
	        var HISTORY_CURVE_Q 	= $("#HISTORY_CURVE_Q").val();
			var CREATE_DATE_Q 		= $("#CREATE_DATE_Q").val();
			*/
			var OBJECT_CODE_Q 		=  $("#OBJECT_CODE_Q").val();
	        var INSTRUMENT_CALLED_Q =  $("#INSTRUMENT_CALLED_Q").val();
	        var CONTROL_OR_Q 		=  $("#CONTROL_OR_Q").val();
	        var ACCESS_SIGNS_Q 		=  $("#ACCESS_SIGNS_Q").val();
	        var CONTROLLER_TYPE_Q	=  $("#CONTROLLER_TYPE_Q").val();
	        var POINT_TYPE_Q 		=  $("#POINT_TYPE_Q").val();
	        var ALARM_OR_Q 			=  $("#ALARM_OR_Q").val();
			var ALARM_CODE_Q 		= $("#ALARM_CODE_Q").val();
			var HISTORY_CURVE_Q 	= $("#HISTORY_CURVE_Q").val();
			var CREATE_DATE_Q 		=  $("#CREATE_DATE_Q").val();
			var CREATE_DATE_QA 		= $("#CREATE_DATE_QA").val();
			var POINT_CODE_Q 		= $("#POINT_CODE_Q").val();

		var totalNum = 0;
		var url = "jzcjdxx_searchDatas.action?OBJECT_CODE_Q="+encodeURIComponent(OBJECT_CODE_Q)+
		"&INSTRUMENT_CALLED_Q="+encodeURIComponent(INSTRUMENT_CALLED_Q)+
		"&CONTROL_OR_Q="+encodeURIComponent(CONTROL_OR_Q)+
		
		"&ACCESS_SIGNS_Q="+encodeURIComponent(ACCESS_SIGNS_Q)+
		"&CONTROLLER_TYPE_Q="+encodeURIComponent(CONTROLLER_TYPE_Q)+
		"&POINT_TYPE_Q="+encodeURIComponent(POINT_TYPE_Q)+
		"&ALARM_OR_Q="+encodeURIComponent(ALARM_OR_Q)+
		"&ALARM_CODE_Q="+encodeURIComponent(ALARM_CODE_Q)+
		"&HISTORY_CURVE_Q="+encodeURIComponent(HISTORY_CURVE_Q)+
		"&CREATE_DATE_Q="+encodeURIComponent(CREATE_DATE_Q)+
		"&CREATE_DATE_QA="+encodeURIComponent(CREATE_DATE_QA)+
		"&POINT_CODE_Q="+encodeURIComponent(POINT_CODE_Q)+
		'&totalNum=export';
		var urls = "jzcjdxx_searchDatas.action?OBJECT_CODE_Q="+encodeURIComponent(OBJECT_CODE_Q)+
		"&INSTRUMENT_CALLED_Q="+encodeURIComponent(INSTRUMENT_CALLED_Q)+
		"&CONTROL_OR_Q="+encodeURIComponent(CONTROL_OR_Q)+
		
		"&ACCESS_SIGNS_Q="+encodeURIComponent(ACCESS_SIGNS_Q)+
		"&CONTROLLER_TYPE_Q="+encodeURIComponent(CONTROLLER_TYPE_Q)+
		"&POINT_TYPE_Q="+encodeURIComponent(POINT_TYPE_Q)+
		"&ALARM_OR_Q="+encodeURIComponent(ALARM_OR_Q)+
		"&ALARM_CODE_Q="+encodeURIComponent(ALARM_CODE_Q)+
		"&HISTORY_CURVE_Q="+encodeURIComponent(HISTORY_CURVE_Q)+
		"&CREATE_DATE_Q="+encodeURIComponent(CREATE_DATE_Q)+
		"&CREATE_DATE_QA="+encodeURIComponent(CREATE_DATE_QA)+
		"&POINT_CODE_Q="+encodeURIComponent(POINT_CODE_Q)+
		'&totalNum=totalNum';
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
			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url;});
		}
		else if(totalNum > 10000){
			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url;});
		}
		else {
			$.ligerDialog.confirm('没有可导出的数据！');
		}
	}
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

</head>

<body style="overflow-x:hidden; padding:2px;">
	 <div id="mainsearch" style=" width:99%">
	 
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
						<td align="left" class="l-table-edit-td" style="width:80px">对象类型:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="OBJECT_CODE_Q" name ="OBJECT_CODE_Q" />
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:100px">1次关联设备:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="INSTRUMENT_CALLED_Q" name = "INSTRUMENT_CALLED_Q"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">是否控制:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="CONTROL_OR_Q" name = "CONTROL_OR_Q"/>
		                </td>
		                
		                <td align="right" class="l-table-edit-td" style="width:80px">是否接入:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="ACCESS_SIGNS_Q" name = "ACCESS_SIGNS_Q"/>
		                </td>
		                
		           		<td align="right" class="l-table-edit-td" style="width:80px">接入控制器:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="CONTROLLER_TYPE_Q" name = "CONTROLLER_TYPE_Q"/>
		                </td>
		                
		                <td align="right" class="l-table-edit-td" style="width:60px">点类型:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="POINT_TYPE_Q" name = "POINT_TYPE_Q"/>
		                </td>
		               
		                  <td align="left" class="l-table-edit-td" style="width:60px">报警否:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="ALARM_OR_Q" name = "ALARM_OR_Q"/>
		                </td>
		                 </tr>
		                <tr>
		                  <td align="left" class="l-table-edit-td" style="width:80px">报警级别:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="ALARM_CODE_Q" name = "ALARM_CODE_Q"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:120px">是否启用曲线:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="HISTORY_CURVE_Q" name = "HISTORY_CURVE_Q"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:80px">建点时间:</td>
		                <td >
		                	<input type="text" id="CREATE_DATE_Q" name = "CREATE_DATE_Q" ltype="date" />
		                </td>
		                <td valign="middle">--</td>
		                <td  align="left">
		                	<input type="text" id="CREATE_DATE_QA" name = "CREATE_DATE_QA" ltype="date" />
		                </td>
		                
		                    <td align="right" class="l-table-edit-td" style="width:80px">点代码:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="POINT_CODE_Q" name = "POINT_CODE_Q"/>
		                </td>
					
		                <td align="right" class="l-table-edit-td" style="width:50px"></td>
		                <td align="left" class="l-table-edit-td" >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
		                </td>
		                
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a>
		                </td>
					</tr>
				
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="width:100%;height: 100px; display:none;">
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		               
		                <td align="left" class="l-table-edit-td" style="width:150px">对象类型:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">点描述:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">界面统一描述:</td>
		                   <td align="left" class="l-table-edit-td" style="width:150px">是否接入:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">点类型:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">量程上限:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">量程下限:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">单位:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">是否控制:</td>
		             </tr>
		             <tr>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="OBJECT_CODE" type="text" id="OBJECT_CODE"  ltype="text" validate="{required:true}" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="POINT_NAME" type="text" id="POINT_NAME"  ltype="text"/>
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="INTERFACE_REMARK" type="text" id="INTERFACE_REMARK" ltype="text"  />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="ACCESS_SIGNS" type="text" id="ACCESS_SIGNS" ltype="text" />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="POINT_TYPE" type="text" id="POINT_TYPE" ltype="text"  />
		                </td>
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="RANGES_UPPER" type="text" id="RANGES_UPPER" ltype="text" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="RANGES_DOWN" type="text" id="RANGES_DOWN" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="IFIX_UNIT" type="text" id="IFIX_UNIT" ltype="text" />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="CONTROL_OR" type="text" id="CONTROL_OR" ltype="text" />
		                </td>
		                </tr>
		            <tr>
 						<td align="left" class="l-table-edit-td" style="width:150px">是否曲线:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">是否存储:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">是否报警:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">报警级别代码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">报警低低限:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">报警低限:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">报警高限:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">报警高高限:</td>		
		                <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>             
		            </tr>
		            <tr>
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="HISTORY_CURVE" type="text" id="HISTORY_CURVE"  ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="ORACLE_SAVE" type="text" id="ORACLE_SAVE"  ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_OR" type="text" id="ALARM_OR" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_VALUE" type="text" id="ALARM_VALUE" ltype="text"  />
		                </td>
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_LIMITLL" type="text" id="ALARM_LIMITLL" ltype="text" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_LIMITL" type="text" id="ALARM_LIMITL" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_LIMITH" type="text" id="ALARM_LIMITH" ltype="text"  />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_LIMITHH" type="text" id="ALARM_LIMITHH" ltype="text"  />
		                </td>
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="REMARK" type="text" id="REMARK" ltype="text" />
		                </td>
		            </tr>
		            <tr>
		            	
		            	<td align="left" class="l-table-edit-td" style="width:150px">接入控制器:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">厂家数据类型:</td> 
		                <td align="left" class="l-table-edit-td" style="width:150px">厂家数据地址:</td> 
		                 <td align="left" class="l-table-edit-td" style="width:150px">厂家点描述:</td> 
		                 <td align="left" class="l-table-edit-td" style="width:150px">厂家值描述:</td>
		            	<td align="left" class="l-table-edit-td" style="width:150px">值缩放比:</td>
		            	<td align="left" class="l-table-edit-td" style="width:150px">Modbus地址:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">Modbus地址B:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">点代码:</td>
		            </tr>
		            <tr>
		          
		           
		            <td align="left" class="l-table-edit-td" >
		                	<input name="JRKZQ" type="text" id="JRKZQ" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="DATA_TYPE" type="text" id="DATA_TYPE" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="PLC_ADDRESS" type="text" id="PLC_ADDRESS" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="POINT_DESC" type="text" id="POINT_DESC" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="POINT_REMARK" type="text" id="POINT_REMARK" ltype="text" />
		                </td>
		            <td align="left" class="l-table-edit-td" >
		                	<input name="VALUE_RATIO" type="text" id="VALUE_RATIO" ltype="text" />
		                </td>
		            <td align="left" class="l-table-edit-td" >
		                	<input name="MAILING_ADDRESS" type="text" id="MAILING_ADDRESS" ltype="text" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="MAILING_ADDRESSB" type="text" id="MAILING_ADDRESSB" ltype="text"  />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="POINT_CODE" type="text" id="POINT_CODE" ltype="text" />
		                </td>
		                </tr>
		                <tr>
		            	<td align="left" class="l-table-edit-td" style="width:150px">1次关联设备:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">厂2数据类型:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">厂2数据地址:</td> 
		                <td align="left" class="l-table-edit-td" style="width:150px">厂2点描述:</td> 
		                 <td align="left" class="l-table-edit-td" style="width:150px">厂2值描述:</td> 
		                  <td align="left" class="l-table-edit-td" style="width:150px">值缩放比2:</td> 
		                <td align="left" class="l-table-edit-td" style="width:150px">Modbus地址2:</td> 
		                 <td align="left" class="l-table-edit-td" style="width:150px">Modbus地址B2:</td> 
		            </tr>
		            <tr>
		              <td align="left" class="l-table-edit-td" >
		                	<input name="YCGLSB" type="text" id="YCGLSB" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="DATA_TYPE2" type="text" id="DATA_TYPE2" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="PLC_ADDRESS2" type="text" id="PLC_ADDRESS2" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="POINT_DESC2" type="text" id="POINT_DESC2" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="POINT_REMARK2" type="text" id="POINT_REMARK2" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="VALUE_RATIO2" type="text" id="VALUE_RATIO2" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="MAILING_ADDRESS2" type="text" id="MAILING_ADDRESS2" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="MAILING_ADDRESSB2" type="text" id="MAILING_ADDRESSB2" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="SMALL_STATION_ID" type="hidden" id="SMALL_STATION_ID"/>
		                </td>
		                </tr>
		              
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>