<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>站库采集点管理</title>
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
					url : 'zkcjd_pageInit.action',
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
    			//系统代码
    			$("#SYSTEM_CODE_Q").ligerComboBox({
    				//url:'waterSoWell_queryWellInfo.action',
    				valueField: 'id',
    				valueFieldID: 'qdxId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:200,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				if (data != null && typeof(data)!="undefined" && data != ''){
    					liger.get("DEVICE_CODE_Q").setValue('');
    					OnChangeData($("#qdxId").val());
    					OnChangeLCData($("#qdxId").val());
					}
    				}
    			});
    			//点类型
    			$("#POINT_TYPE_Q").ligerComboBox({
    				//url:'waterSoWell_queryWaterSoWell.action',
    				valueField: 'id',
    				valueFieldID: 'NETWORKId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				}
    			});
    			//流程代码
    			$("#FLOW_CODE_Q").ligerComboBox({
    				//url:'gwgh_searchStation.action',
    				valueField: 'id',
    				valueFieldID: 'staid',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:120,
    				isShowCheckBox: true,
    				isMultiSelect: true,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				}
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
    	     		//设备代码
    			$("#DEVICE_CODE_Q").ligerComboBox({
    				valueField: 'id',
  	     			valueFieldID: 'hidserverid',
  	     			textField: 'text',
  	     			selectBoxHeight:150,
  	     			selectBoxWidth:100,
  	     			width: 120,
  	     			autocomplete:true,
  	     			hideOnLoseFocus:true
	     		});
	     		//是否控制
    			 $("#CONTROL_OR_Q").ligerComboBox({
    				 hideOnLoseFocus:true,
	 					autocomplete:true,
	 		                data: [
	 		                    { text: '是', id: '0' },
	 		                    { text: '否', id: '1' }
	 		                    
	 		                ], valueFieldID: 'QlsId',
	 					initText :''
   		     		});
    			//是否接入
    			 $("#ACCESS_SIGNS_Q").ligerComboBox({
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
			//编辑
			 //系统代码
    			$("#SYSTEM_CODE").ligerComboBox({
    				//url:'waterSoWell_queryCombinationInfo.action',
    				//url:'gwgh_cascadeInit.action',
    	            valueField: 'id',
    				valueFieldID: 'sysId',
    				textField: 'text',
    				selectBoxHeight:250,
    				selectBoxWidth:200,
    	            hideOnLoseFocus:true,
    	            autocomplete:true,
    	            onSelected:function (data){
    					if ( data != null && typeof(data)!="undefined" && data != '' && data !=1) {
    						liger.get("DEVICE_CODE").setValue('');
    						OnChangeEdit($("#sysId").val(),"");
    						OnChangeEditLC($("#sysId").val(),"");
        					}
    				}
    			});
    			//点类型
    			$("#POINT_TYPE").ligerComboBox({
    				//url:'waterSoWell_queryWellInfo.action',
    				valueField: 'id',
    				valueFieldID: 'dlxId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    					if (data != null && typeof(data)!="undefined" && data != ''){
    						//liger.get("CONTROLLER_TYPE").setValue('');
    						//OnChangeEdit($("#dxxtdmId").val());
    					}
    				}
    			});
    			//流程代码
    			$("#FLOW_CODE").ligerComboBox({
    				//url:'waterSoWell_queryWaterSoWell.action',
    				valueField: 'id',
    				valueFieldID: 'flowId',
    				textField: 'text',
    				selectBoxHeight:200,
    				isShowCheckBox: true,
    				isMultiSelect: true,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				}
    			});
    		
    			//报警级别
    			 $("#ALARM_CODE").ligerComboBox({
   				  //url:'comboboxdata_searchSwitchInflg.action', 
   	     			valueField: 'id',
   	     			valueFieldID: 'jbId',
   	     			textField: 'text',
   	     			selectBoxHeight:150,
   	     			selectBoxWidth:100,
   	     			width: 120,
   	     			autocomplete:true,
   	     			hideOnLoseFocus:true
   		     		});
    			// 设备代码
    			  $("#DEVICE_CODE").ligerComboBox({
  	     			//url:'sagd_getServerNode.action',
  	     			valueField: 'id',
  	     			valueFieldID: 'deviceId',
  	     			textField: 'text',
  	     			selectBoxHeight:150,
  	     			selectBoxWidth:100,
  	     			width: 120,
  	     			autocomplete:true,
  	     			hideOnLoseFocus:true

  	     		});
    				//报警否
      			$("#ALARM_OR").ligerComboBox({
      				hideOnLoseFocus:true,
	 					autocomplete:true,
	 		                data: [
	 		                    { text: '是', id: '0' },
	 		                    { text: '否', id: '1' }
	 		                    
	 		                ], valueFieldID: 'lsId',
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
    				//是否接入
     			 $("#ACCESS_SIGNS").ligerComboBox({
     				 hideOnLoseFocus:true,
 	 					autocomplete:true,
 	 		                data: [
 	 		                    { text: '是', id: '0' },
 	 		                    { text: '否', id: '1' }
 	 		                    
 	 		                ], valueFieldID: 'jrId',
 	 					initText :''
    		     		});
		     		//控制否
     			 $("#CONTROL_OR").ligerComboBox({
    				 hideOnLoseFocus:true,
	 					autocomplete:true,
	 		                data: [
	 		                    { text: '是', id: '0' },
	 		                    { text: '否', id: '1' }
	 		                    
	 		                ], valueFieldID: 'kzId',
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
                	 //var SYSTEM_CODE = $("#SYSTEM_CODE").val();
                	 //alert(SYSTEM_CODE);
                	 if( $("#SYSTEM_CODE").val() !=''){
                		 SYSTEM_CODE = $("#sysId").val();
                    	}
                	 var TAG_NAME = $("#TAG_NAME").val();
                	 var REMARK = $("#REMARK").val();
    				 var POINT_TYPE ="";
    				 if( $("#POINT_TYPE").val() !=''){
    					 POINT_TYPE = $("#dlxId").val();
        				 }
    				 var UNIT = $("#UNIT").val();
    				 var FLOW_CODE =""
        				 if($("#FLOW_CODE").val() !=''){
        					 FLOW_CODE = $("#flowId").val();
            				}
    				 var RANGES_DOWN = $("#RANGES_DOWN").val();
    				 var RANGES_UPPER = $("#RANGES_UPPER").val();
    				 var ALARM_LIMITLL = $("#ALARM_LIMITLL").val();
    				 var ALARM_LIMITL = $("#ALARM_LIMITL").val();
    				 var ALARM_LIMITH = $("#ALARM_LIMITH").val();
    				 var ALARM_LIMITHH = $("#ALARM_LIMITHH").val();
    				 var ALARM_OR = $("#ALARM_OR").val();
    				 var ALARM_CODE ="";
    				 if( $("#ALARM_CODE").val() !=''){
    					 ALARM_CODE = $("#jbId").val();
        				 }
    				 var DEVICE_CODE ="";
    				  if( $("#DEVICE_CODE").val() !=''){
    					  DEVICE_CODE = $("#deviceId").val();
        				  }
    				 var HISTORY_CURVE = $("#HISTORY_CURVE").val();
    				 var ORACLE_SAVE = $("#ORACLE_SAVE").val();
    				 var STATION_POINT_ID  = $("#STATION_POINT_ID").val();
    				 var BEIZHU  = $("#BEIZHU").val();
    				 var CONTROL_OR  = $("#CONTROL_OR").val();
    				 var ACCESS_SIGNS  = $("#ACCESS_SIGNS").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'zkcjd_saveorUpdateData.action',
									async : false,
									data: {"SYSTEM_CODE":SYSTEM_CODE, "TAG_NAME":TAG_NAME, "REMARK":REMARK, "POINT_TYPE":POINT_TYPE,
						              	   	"UNIT":UNIT, "FLOW_CODE":FLOW_CODE, "RANGES_DOWN":RANGES_DOWN, "RANGES_UPPER":RANGES_UPPER,
						              	   	"ALARM_LIMITLL":ALARM_LIMITLL, "ALARM_LIMITL":ALARM_LIMITL, "ALARM_LIMITH":ALARM_LIMITH, "ALARM_LIMITHH":ALARM_LIMITHH,
						              	    "ALARM_OR":ALARM_OR, "ALARM_CODE":ALARM_CODE, "DEVICE_CODE":DEVICE_CODE, "HISTORY_CURVE":HISTORY_CURVE, "ORACLE_SAVE":ORACLE_SAVE, "STATION_POINT_ID":STATION_POINT_ID,
						              	    "BEIZHU":BEIZHU,"CONTROL_OR":CONTROL_OR, "ACCESS_SIGNS":ACCESS_SIGNS},
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
																	setpage1(1); //隐藏编辑界面
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
									url : 'zkcjd_saveorUpdateData.action',
									async : false,
									data: {"SYSTEM_CODE":SYSTEM_CODE, "TAG_NAME":TAG_NAME, "REMARK":REMARK, "POINT_TYPE":POINT_TYPE,
			              	   	"UNIT":UNIT, "FLOW_CODE":FLOW_CODE, "RANGES_DOWN":RANGES_DOWN, "RANGES_UPPER":RANGES_UPPER,
			              	   	"ALARM_LIMITLL":ALARM_LIMITLL, "ALARM_LIMITL":ALARM_LIMITL, "ALARM_LIMITH":ALARM_LIMITH, "ALARM_LIMITHH":ALARM_LIMITHH,
			              	    "ALARM_OR":ALARM_OR, "ALARM_CODE":ALARM_CODE, "DEVICE_CODE":DEVICE_CODE, "HISTORY_CURVE":HISTORY_CURVE, 
			              	    "ORACLE_SAVE":ORACLE_SAVE, "STATION_POINT_ID":STATION_POINT_ID,
			              	  "BEIZHU":BEIZHU,"CONTROL_OR":CONTROL_OR, "ACCESS_SIGNS":ACCESS_SIGNS},
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
																	setpage1(1); //隐藏编辑界面
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
        							
									if (rowdata.SYSTEM_CODE != null && typeof(rowdata.SYSTEM_CODE)!="undefined"){
										SYSTEM_CODE = rowdata.SYSTEM_CODE;
									 }else{
										 SYSTEM_CODE = "";
									 }
									if (rowdata.TAG_NAME != null && typeof(rowdata.TAG_NAME)!="undefined"){
										TAG_NAME = rowdata.TAG_NAME;
									 }else{
										 TAG_NAME = "";
									 }
									if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
										REMARK = rowdata.REMARK;
									 }else{
										 REMARK = "";
									 }
									
									if (rowdata.POINT_ORDER != null && typeof(rowdata.POINT_ORDER)!="undefined"){
										POINT_ORDER = rowdata.POINT_ORDER;
									 }else{
										 POINT_ORDER = "";
									 }

									if (rowdata.POINT_TYPE != null && typeof(rowdata.POINT_TYPE)!="undefined"){
										POINT_TYPE = rowdata.POINT_TYPE;
									 }else{
										 POINT_TYPE = "";
									 }
									if (rowdata.UNIT != null && typeof(rowdata.UNIT)!="undefined"){
										UNIT = rowdata.UNIT;
									 }else{
										 UNIT = "";
									 }				
									if (rowdata.CREATE_DATE != null && typeof(rowdata.CREATE_DATE)!="undefined"){
										CREATE_DATE = rowdata.CREATE_DATE;
									 }else{
										 CREATE_DATE = "";
									 }				
									if (rowdata.FLOW_CODE != null && typeof(rowdata.FLOW_CODE)!="undefined"){
										FLOW_CODE = rowdata.FLOW_CODE;
									 }else{
										 FLOW_CODE = "";
									 }
									if (rowdata.FLOW_NAME != null && typeof(rowdata.FLOW_NAME)!="undefined"){
										FLOW_NAME = rowdata.FLOW_NAME;
									 }else{
										 FLOW_NAME = "";
									 }
									if (rowdata.RANGES_DOWN != null && typeof(rowdata.RANGES_DOWN)!="undefined"){
										RANGES_DOWN = rowdata.RANGES_DOWN;
									 }else{
										 RANGES_DOWN = "";
									 }
									if (rowdata.RANGES_UPPER != null && typeof(rowdata.RANGES_UPPER)!="undefined"){
										RANGES_UPPER = rowdata.RANGES_UPPER; 
									 }else{
										 RANGES_UPPER = "";
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

									 if (rowdata.ALARM_OR != null && typeof(rowdata.ALARM_OR)!="undefined"){
										 ALARM_OR = rowdata.ALARM_OR; 
									 }else{
										 ALARM_OR = "";
									 }
									 if (rowdata.ALARM_CODE != null && typeof(rowdata.ALARM_CODE)!="undefined"){
										 ALARM_CODE = rowdata.ALARM_CODE; 
									 }else{
										 ALARM_CODE = "";
									 }
									 if (rowdata.ALARM_VALUE != null && typeof(rowdata.ALARM_VALUE)!="undefined"){
										 ALARM_VALUE = rowdata.ALARM_VALUE; 
									 }else{
										 ALARM_VALUE = "";
									 }
									 if (rowdata.DEVICE_CODE != null && typeof(rowdata.DEVICE_CODE)!="undefined"){
										 DEVICE_CODE = rowdata.DEVICE_CODE; 
									 }else{
										 DEVICE_CODE = "";
									 }
									 if (rowdata.DEVICE_NAME != null && typeof(rowdata.DEVICE_NAME)!="undefined"){
										 DEVICE_NAME = rowdata.DEVICE_NAME; 
									 }else{
										 DEVICE_NAME = "";
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
									 if (rowdata.RLAST_OPER != null && typeof(rowdata.RLAST_OPER)!="undefined"){
										 RLAST_OPER = rowdata.RLAST_OPER; 
									 }else{
										 RLAST_OPER = "";
									 }
									 if (rowdata.RLAST_ODATE != null && typeof(rowdata.RLAST_ODATE)!="undefined"){
										 RLAST_ODATE = rowdata.RLAST_ODATE; 
									 }else{
										 RLAST_ODATE = "";
									 }
									 if (rowdata.STATION_POINT_ID != null && typeof(rowdata.STATION_POINT_ID)!="undefined"){
										 STATION_POINT_ID = rowdata.STATION_POINT_ID; 
									 }else{
										 STATION_POINT_ID = "";
									 }
								//是否接入
									 if (rowdata.ACCESS_SIGNS != null && typeof(rowdata.ACCESS_SIGNS)!="undefined"){
										 ACCESS_SIGNS = rowdata.ACCESS_SIGNS; 
									 }else{
										 ACCESS_SIGNS = "";
									 }
									 if (rowdata.CONTROL_OR != null && typeof(rowdata.CONTROL_OR)!="undefined"){
										 CONTROL_OR = rowdata.CONTROL_OR; 
									 }else{
										 CONTROL_OR = "";
									 }
									 if (rowdata.BEIZHU != null && typeof(rowdata.BEIZHU)!="undefined"){
										 BEIZHU = rowdata.BEIZHU; 
									 }else{
										 BEIZHU = "";
									 }
								
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }

     

      
        function getAllInitData() {
        
    		var sysText=[{ id: 1 , text: '' }];
    		var poiText=[{ id: 1 , text: '' }];
    		var lcText=[{ id: 1 , text: '' }];
    		var alaText=[{ id: 1 , text: '' }];
    		var codText=[{ id: 1 , text: '' }];
    		
    		jQuery.ajax({
    			type : 'post',
    			url : 'zkcjd_cascadeInit.action?nowdata='+parseInt(Math.random()*100000),
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					
    					if (key == 'sysText'){
    						sysText = val;
    					}
    					if (key == 'poiText'){
    						poiText = val;
    					} 
    					if (key == 'lcText'){
    						lcText = val;
    					} 
    					if (key == 'alaText'){
    						alaText = val;
    					} 
    					if (key == 'codText'){
    						codText = val;
    					} 
    				});
    				setInitData(sysText,poiText,lcText,alaText,codText);
    			}
    		});
    	}
    	
        function setInitData(sysText,poiText,lcText,alaText,codText) {
    		liger.get("SYSTEM_CODE_Q").setData(sysText);
    		liger.get("POINT_TYPE_Q").setData(poiText);
    		liger.get("FLOW_CODE_Q").setData(lcText);
    		liger.get("ALARM_CODE_Q").setData(alaText);
    		liger.get("DEVICE_CODE_Q").setData(codText);

    		liger.get("SYSTEM_CODE").setData(sysText);
    		liger.get("POINT_TYPE").setData(poiText);
    		liger.get("FLOW_CODE").setData(lcText);
    		liger.get("ALARM_CODE").setData(alaText);
    		liger.get("DEVICE_CODE").setData(codText);
    	}
        
        function OnChangeData(code) {
       		jQuery.ajax({
       			type : 'post',
       			url:'zkcjd_searchOnChange.action',
       			data: {"code":code},
       			
       			success : function(jsondata) {
       				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
       					liger.get("DEVICE_CODE_Q").setData(eval('(' + jsondata + ')'));
       				}
       				else{
       					liger.get("DEVICE_CODE_Q").setData('');
       				}
       			},
       			error : function(jsondata) {
       				
       			}
       		});
       	}
        function OnChangeLCData(code) {
       		jQuery.ajax({
       			type : 'post',
       			url:'zkcjd_searchOnChangeLC.action',
       			data: {"code":code},
       			
       			success : function(jsondata) {
       				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
       					liger.get("FLOW_CODE_Q").setData(eval('(' + jsondata + ')'));
       				}
       				else{
       					liger.get("FLOW_CODE_Q").setData('');
       				}
       			},
       			error : function(jsondata) {
       				
       			}
       		});
       	}
    function OnChangeEdit(code) {
   		jQuery.ajax({
   			type : 'post',
   			url:'zkcjd_searchOnChange.action',
   			data: {"code":code},
   			
   			success : function(jsondata) {
   				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
   					liger.get("DEVICE_CODE").setData(eval('(' + jsondata + ')'));
   				}
   				else{
   					liger.get("DEVICE_CODE").setData('');
   				}
   			},
   			error : function(jsondata) {
   				
   			}
   		});
   	}
    function OnChangeEditLC(code) {
   		jQuery.ajax({
   			type : 'post',
   			url:'zkcjd_searchOnChangeLCeDIT.action',
   			data: {"code":code},
   			
   			success : function(jsondata) {
   				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
   					liger.get("FLOW_CODE").setData(eval('(' + jsondata + ')'));
   				}
   				else{
   					liger.get("FLOW_CODE").setData('');
   				}
   			},
   			error : function(jsondata) {
   				
   			}
   		});
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
                		{name: 'SYSTEM_CODE_Q',value: $("#SYSTEM_CODE_Q").val()},
        		        {name: 'POINT_TYPE_Q',value: $("#POINT_TYPE_Q").val()},
        		        {name: 'CREATE_DATE_Q',value: $("#CREATE_DATE_Q").val()},
        		        {name: 'FLOW_CODE_Q',value: $("#staid").val()},
        		        {name: 'ALARM_OR_Q',value: $("#ALARM_OR_Q").val()},
        		        {name: 'ALARM_CODE_Q',value: $("#ALARM_CODE_Q").val()},
        		        {name: 'DEVICE_CODE_Q',value: $("#DEVICE_CODE_Q").val()}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
				 $("#SYSTEM_CODE").ligerGetComboBoxManager().selectValue(SYSTEM_CODE);
				 document.getElementById("TAG_NAME").value =TAG_NAME;
				 document.getElementById("REMARK").value =REMARK ;
				 $("#POINT_TYPE").ligerGetComboBoxManager().selectValue(POINT_TYPE);
				 document.getElementById("UNIT").value =UNIT;
				 $("#FLOW_CODE").ligerGetComboBoxManager().selectValue(FLOW_CODE);
				 //document.getElementById("FLOW_CODE").value = FLOW_CODE;
				 document.getElementById("RANGES_DOWN").value = RANGES_DOWN;
				 document.getElementById("RANGES_UPPER").value = RANGES_UPPER;
				 document.getElementById("ALARM_LIMITLL").value =ALARM_LIMITLL;
				 document.getElementById("ALARM_LIMITL").value =ALARM_LIMITL;
				 document.getElementById("ALARM_LIMITH").value =ALARM_LIMITH ;
				 document.getElementById("ALARM_LIMITHH").value = ALARM_LIMITHH;
				 if (ALARM_OR == '是') {
						$("#ALARM_OR").ligerGetComboBoxManager().selectValue(0);
					}
					else if(ALARM_OR == '否'){
						$("#ALARM_OR").ligerGetComboBoxManager().selectValue(1);
					}else{
						$("#ALARM_OR").ligerGetComboBoxManager().selectValue('');
						}
				
				 $("#ALARM_CODE").ligerGetComboBoxManager().selectValue(ALARM_CODE);
				 $("#DEVICE_CODE").ligerGetComboBoxManager().selectValue(DEVICE_CODE);
				
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

				 if (ACCESS_SIGNS == '是') {
						$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue(0);
					}
					else if(ACCESS_SIGNS == '否'){
						$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue(1);
					}else{
						$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue('');
						}
				 if (CONTROL_OR == '是') {
						$("#CONTROL_OR").ligerGetComboBoxManager().selectValue(0);
					}
					else if(CONTROL_OR == '否'){
						$("#CONTROL_OR").ligerGetComboBoxManager().selectValue(1);
					}else{
						$("#CONTROL_OR").ligerGetComboBoxManager().selectValue('');
						}
				 document.getElementById("BEIZHU").value =BEIZHU ;
				 document.getElementById("STATION_POINT_ID").value =STATION_POINT_ID ;
			 }else if(flg == 1){
				 $("#SYSTEM_CODE").ligerGetComboBoxManager().selectValue("");
				 document.getElementById("TAG_NAME").value ="";
				 document.getElementById("REMARK").value ="" ;
				 $("#POINT_TYPE").ligerGetComboBoxManager().selectValue("");
				 document.getElementById("UNIT").value ="";
				 $("#FLOW_CODE").ligerGetComboBoxManager().selectValue("");
				  //document.getElementById("FLOW_CODE").value = "";
				 document.getElementById("RANGES_DOWN").value = "";
				 document.getElementById("RANGES_UPPER").value = "";
				 document.getElementById("ALARM_LIMITLL").value ="";
				 document.getElementById("ALARM_LIMITL").value ="";
				 document.getElementById("ALARM_LIMITH").value ="" ;
				 document.getElementById("ALARM_LIMITHH").value = "";
				 $("#ALARM_OR").ligerGetComboBoxManager().selectValue('');
				 $("#ALARM_CODE").ligerGetComboBoxManager().selectValue("");
				 $("#DEVICE_CODE").ligerGetComboBoxManager().selectValue("");
				 $("#HISTORY_CURVE").ligerGetComboBoxManager().selectValue('');
				 $("#ORACLE_SAVE").ligerGetComboBoxManager().selectValue('');
				 document.getElementById("STATION_POINT_ID").value ="" ;
	               
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
            	  if(operate != 1 && operate != 2){
            		  setpage1(0); //显示编辑界面
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
              	    		setpage1(0); //显示编辑界面
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
											url : 'zkcjd_removeData.action',
											async : false,
											data: {"STATION_POINT_ID":STATION_POINT_ID},
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
              case "involvesTable":

		          	   // var SYSTEM_CODE_Q 		= $("#SYSTEM_CODE_Q").val();
		          	    var SYSTEM_CODE_Q 		= $("#qdxId").val();
		      	        var POINT_TYPE_Q 		= $("#POINT_TYPE_Q").val();
		      	        var CREATE_DATE_Q 	= $("#CREATE_DATE_Q").val();
		      	        var FLOW_CODE_Q 	= $("#staid").val();
		      	        var ALARM_OR_Q 		= $("#ALARM_OR_Q").val();
		      	        var ALARM_CODE_Q 		= $("#ALARM_CODE_Q").val();
		      	        var DEVICE_CODE_Q 	= $("#DEVICE_CODE_Q").val();
		
			      		var totalNum = 0;
			      		var url = "zkcjd_searchDatas.action?SYSTEM_CODE_Q="+encodeURIComponent(SYSTEM_CODE_Q)+"&POINT_TYPE_Q="+encodeURIComponent(POINT_TYPE_Q)+"&CREATE_DATE_Q="+encodeURIComponent(CREATE_DATE_Q)
			      											  +"&FLOW_CODE_Q="+encodeURIComponent(FLOW_CODE_Q)+"&ALARM_OR_Q="+encodeURIComponent(ALARM_OR_Q)+"&ALARM_CODE_Q="+encodeURIComponent(ALARM_CODE_Q)
			      												   +"&DEVICE_CODE_Q="+encodeURIComponent(DEVICE_CODE_Q)
			      												+'&ptable=Eptable';
			      		var urls = "zkcjd_searchDatas.action?SYSTEM_CODE_Q="+encodeURIComponent(SYSTEM_CODE_Q)+"&POINT_TYPE_Q="+encodeURIComponent(POINT_TYPE_Q)+"&CREATE_DATE_Q="+encodeURIComponent(CREATE_DATE_Q)
			      												  +"&FLOW_CODE_Q="+encodeURIComponent(FLOW_CODE_Q)+"&ALARM_OR_Q="+encodeURIComponent(ALARM_OR_Q)+"&ALARM_CODE_Q="+encodeURIComponent(ALARM_CODE_Q)
			      												   +"&DEVICE_CODE_Q="+encodeURIComponent(DEVICE_CODE_Q)
			      												+'&ptable=ptable';
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
			      			$.ligerDialog.confirm('确定生成点表吗?<br/>您要生成点表的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url;});
			      		}
			      		else if(totalNum > 10000){
			      			$.ligerDialog.confirm('确定生成点表吗?<br/>您要生成点表的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url;});
			      		}
			      		else {
			      			$.ligerDialog.confirm('没有可生成点表的数据！');
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
    	    var SYSTEM_CODE_Q 		= $("#SYSTEM_CODE_Q").val();
	        var POINT_TYPE_Q 		= $("#POINT_TYPE_Q").val();
	        var CREATE_DATE_Q 	= $("#CREATE_DATE_Q").val();
	        var FLOW_CODE_Q 	= $("#staid").val();
	        var ALARM_OR_Q 		= $("#ALARM_OR_Q").val();
	        var ALARM_CODE_Q 		= $("#ALARM_CODE_Q").val();
	        var DEVICE_CODE_Q 	= $("#DEVICE_CODE_Q").val();

		var totalNum = 0;
		var url = "zkcjd_searchDatas.action?SYSTEM_CODE_Q="+encodeURIComponent(SYSTEM_CODE_Q)+"&POINT_TYPE_Q="+encodeURIComponent(POINT_TYPE_Q)+"&CREATE_DATE_Q="+encodeURIComponent(CREATE_DATE_Q)
											  +"&FLOW_CODE_Q="+encodeURIComponent(FLOW_CODE_Q)+"&ALARM_OR_Q="+encodeURIComponent(ALARM_OR_Q)+"&ALARM_CODE_Q="+encodeURIComponent(ALARM_CODE_Q)
												   +"&DEVICE_CODE_Q="+encodeURIComponent(DEVICE_CODE_Q)
												+'&totalNum=export';
		var urls = "zkcjd_searchDatas.action?SYSTEM_CODE_Q="+encodeURIComponent(SYSTEM_CODE_Q)+"&POINT_TYPE_Q="+encodeURIComponent(POINT_TYPE_Q)+"&CREATE_DATE_Q="+encodeURIComponent(CREATE_DATE_Q)
												  +"&FLOW_CODE_Q="+encodeURIComponent(FLOW_CODE_Q)+"&ALARM_OR_Q="+encodeURIComponent(ALARM_OR_Q)+"&ALARM_CODE_Q="+encodeURIComponent(ALARM_CODE_Q)
												   +"&DEVICE_CODE_Q="+encodeURIComponent(DEVICE_CODE_Q)
												+'&totalNum=totalNum';
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
						<td align="right" class="l-table-edit-td" style="width:100px">系统名称:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="SYSTEM_CODE_Q" name ="SYSTEM_CODE_Q" />
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:100px">点类型:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="POINT_TYPE_Q" name = "POINT_TYPE_Q"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:100px">创建时间:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="CREATE_DATE_Q" name = "CREATE_DATE_Q"/>
		                </td>
		                
		           		<td align="right" class="l-table-edit-td" style="width:80px">流程代码:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="FLOW_CODE_Q" name = "FLOW_CODE_Q"/>
		                </td>
		                
		                <td align="right" class="l-table-edit-td" style="width:60px">报警否:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="ALARM_OR_Q" name = "ALARM_OR_Q"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:100px">报警级别:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="ALARM_CODE_Q" name = "ALARM_CODE_Q"/>
		                </td>
		                  </tr>
		                <tr>
		                  <td align="right" class="l-table-edit-td" style="width:100px">设备代码:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="DEVICE_CODE_Q" name = "DEVICE_CODE_Q"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:100px">是否控制:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="CONTROL_OR_Q" name = "CONTROL_OR_Q"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:100px">是否接入:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="ACCESS_SIGNS_Q" name = "ACCESS_SIGNS_Q"/>
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
		               
		                <td align="left" class="l-table-edit-td" style="width:150px">系统名称 :</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">标签名:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">描述:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">是否接入:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">点类型:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">单位:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">流程代码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">量程下限:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">量程上限:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">报警限值低低:</td>
		             </tr>
		             <tr>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="SYSTEM_CODE" type="text" id="SYSTEM_CODE"  ltype="text" validate="{required:true}" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="TAG_NAME" type="text" id="TAG_NAME"  ltype="text" validate="{required:true}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="REMARK" type="text" id="REMARK" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ACCESS_SIGNS" type="text" id="ACCESS_SIGNS" ltype="text"  />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="POINT_TYPE" type="text" id="POINT_TYPE" ltype="text" validate="{required:true}" />
		                </td>
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="UNIT" type="text" id="UNIT" ltype="text" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="FLOW_CODE" type="text" id="FLOW_CODE" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="RANGES_DOWN" type="text" id="RANGES_DOWN" ltype="text" />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="RANGES_UPPER" type="text" id="RANGES_UPPER" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_LIMITLL" type="text" id="ALARM_LIMITLL" ltype="text" />
		                </td>
		                </tr>
		            <tr>
		            
		            	<td align="left" class="l-table-edit-td" style="width:150px">报警限值低:</td>
		            	<td align="left" class="l-table-edit-td" style="width:150px">报警限值高:</td>
		            	<td align="left" class="l-table-edit-td" style="width:150px">报警限值高高:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">报警否:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">报警级别:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">设备代码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">是否启用历史曲线:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">ORACLE库是否存储:</td> 
		                <td align="left" class="l-table-edit-td" style="width:150px">控制否:</td> 
		                <td align="left" class="l-table-edit-td" style="width:150px">备注:</td> 
		            </tr>
		            <tr>
		            <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_LIMITL" type="text" id="ALARM_LIMITL" ltype="text" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_LIMITH" type="text" id="ALARM_LIMITH" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_LIMITHH" type="text" id="ALARM_LIMITHH" ltype="text" />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_OR" type="text" id="ALARM_OR" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="ALARM_CODE" type="text" id="ALARM_CODE" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="DEVICE_CODE" type="text" id="DEVICE_CODE" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="HISTORY_CURVE" type="text" id="HISTORY_CURVE" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="ORACLE_SAVE" type="text" id="ORACLE_SAVE" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="CONTROL_OR" type="text" id="CONTROL_OR" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="BEIZHU" type="text" id="BEIZHU" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="STATION_POINT_ID" type="hidden" id="STATION_POINT_ID"/>
		                </td>
		            </tr>
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>