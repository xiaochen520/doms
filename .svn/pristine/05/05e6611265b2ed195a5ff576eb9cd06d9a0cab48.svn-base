<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>控制器通信信息管理</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
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
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        
        //鼠标选择数据
		var controllerid ; 		//通讯信息id
		var instrumentationid;	//仪表设备id
		var facility_name;		//设备名称
		var communication_type;	//驱动类型
		var IP_ADDRESS;			//ip地址
		var station_no;			//通讯站号
		var communication_port;	//通讯端口
		var equipment_status;	//设备状态
		var commissioning_date	//投产日期
		var org_id				//组织结构id
		var remark				//备注

		var ybName; //所属仪表
		var YBNAMEID;
		var oilationid1;
        var stationid1;
        var wellid1;
        var boilerid1;
        var structuretype;
		var oilstationname1;
        var blockstationname1;
        var wellnum1;
        var boiler1;  
        var equipstatusid;           	
		var clearSelecteValue=0;
		//var  object_type_name;
	   // var  instrumentation_type;
        
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'control_pageInit.action',
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
			$("#oilstationname").ligerComboBox({
				//url:'rulewell_queryOilSationInfo.action?arg=im',
				//url:'instru_queryOilSationInfo.action',
				url:'control_queryOilSationInfo.action',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:150,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect:function (data){
					if (liger.get("blockstationname").getValue('') != '') {
						liger.get("blockstationname").setValue('');
					}
					if (liger.get("boiler").getValue('') != '') {
						liger.get("boiler").setValue('');
					}
					if (liger.get("wellnum").getValue != '') {
						liger.get("wellnum").setValue('');
					}
				},
				onSelected:function (data){
					if ($("#oilationid").val() != 1) {
						//setStationData(proData,$("#oilationid").val());
						YbData($("#oilationid").val(),proData);
					}
					else {
						//getWellInitData();
					}
				}
			});
			$("#blockstationname").ligerComboBox({
				//url:'rulewell_queryStationInfo.action?oilid=im',
				//url:'instru_queryInjectionTopryInfo1.action?ghid=ghid',
				url:'control_queryInjectionTopryInfo.action',
				valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
				width: 100,
				hideOnLoseFocus:true,
				autocomplete:true,
				onBeforeSelect:function (data){
				if (liger.get("oilstationname").getValue('') != '') {
					liger.get("oilstationname").setValue('');
				}
					if (liger.get("boiler").getValue('') != '') {
						liger.get("boiler").setValue('');
					}
					if (liger.get("wellnum").getValue != '') {
						liger.get("wellnum").setValue('');
					}
				},
	            onSelected:function (data){
	            	/* liger.get("wellnum").setValue('');liger.get("boiler").setValue(''); */
					if (data != null && typeof(data)!="undefined" && data != ''){
						//setWellData($("#stationid").val(),proData);
						YbData($("#stationid").val(),proData);
					}
				}
			});
			$("#wellnum").ligerComboBox({
				//url:'rulewell_queryWellInfo.action?orgid=im',
				//url:'instru_queryWellInfo1.action',
				url:'control_queryWellInfo.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
				autocomplete:true,
				onBeforeSelect:function (data){
				if (liger.get("oilstationname").getValue('') != '') {
					liger.get("oilstationname").setValue('');
				}
					if (liger.get("boiler").getValue('') != '') {
						liger.get("boiler").setValue('');
					}
					if (liger.get("blockstationname").getValue != '') {
						liger.get("blockstationname").setValue('');
					}
				},
				onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						YbData($("#wellid").val(),proData);
					}
				}
			});
			$("#boiler").ligerComboBox({
				//url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
				//url:'instru_queryBoilersNameInfo1.action',
				url:'control_queryBoilersNameInfo.action',
				valueField: 'id',
				valueFieldID: 'boilerid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
				autocomplete:true,
				onBeforeSelect:function (data){
				if (liger.get("oilstationname").getValue('') != '') {
					liger.get("oilstationname").setValue('');
				}
					if (liger.get("blockstationname").getValue('') != '') {
						liger.get("blockstationname").setValue('');
					}
					
					if (liger.get("wellnum").getValue != '') {
						liger.get("wellnum").setValue('');
					}
				},
				onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						YbData($("#boilerid").val(),proData);
					}
				}
			});
			$("#instrutype").ligerComboBox({
				url:'control_searchYBNameQuery.action',
				valueField: 'id',
				valueFieldID: 'typeid',
				textField: 'text',
				//data: [{ text: '流量计', id: '1' },{ text: '液位计', id: '2' },
				//		{ text: '压力变送器', id: '3' },{ text: '温度变送器', id: '4' }],
				selectBoxHeight:300,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
				autocomplete:true
			});
			
			//	以下为添加时
			$("#oilstationname1").ligerComboBox({
				//url:'rulewell_queryOilSationInfo.action?arg=im',
				url:'instru_queryOilSationInfo1.action',
				valueField: 'id',
				valueFieldID: 'oilationid1',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:260,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					clearSelecteValue=1;
					liger.get("blockstationname1").setValue('');
					liger.get("wellnum1").setValue('');
					liger.get("boiler1").setValue('');
					if ($("#oilationid1").val() != '') {
						setStationData( $("#oilationid1").val(),proData);
					}
					else {
						//getWellInitData();
					}
				}
			});
			$("#blockstationname1").ligerComboBox({
				//url:'rulewell_queryStationInfo.action?oilid=im',
				url:'instru_queryInjectionTopryInfo1.action?ghid=ghid',
	            valueField: 'id',
				valueFieldID: 'stationid1',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            width: 100,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						setWellData($("#stationid1").val(),proData);
					}else {
						//getWellInitData();
					}
				}
			});
		
			$("#wellnum1").ligerComboBox({
				//url:'rulewell_queryWellInfo.action?orgid=im',
				url:'instru_queryWellInfo1.action',
				valueField: 'id',
				valueFieldID: 'wellid1',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
	            onBeforeSelect:function (data){
				if( $("#wellnum1").val() !=''){
				liger.get("boiler1").setValue('');
				}
			},
	            onSelected:function (data){
				if (data != null && typeof(data)!="undefined" && data != ''){
					YbData1($("#wellid1").val(),proData);
				}else {
					//getWellInitData();
				}
			}
			});
			$("#boiler1").ligerComboBox({
				//url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
				url:'instru_queryBoilersNameInfo1.action',
				valueField: 'id',
				valueFieldID: 'boilerid1',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
				autocomplete:true,
				onBeforeSelect:function (data){
				if( $("#boiler1").val() !=''){
				liger.get("wellnum1").setValue('');
				}
			},
				  onSelected:function (data){
				if (data != null && typeof(data)!="undefined" && data != ''){
					YbData1($("#boilerid1").val(),proData);
				}else {
					//getWellInitData();
				}
			}
			});
		
			 //设备名称
			 $("#ybName").ligerComboBox({
				url:'instru_searchYBName.action',
				//url:'control_searchYBNameQuery.action',
				valueField: 'id',
				valueFieldID: 'YBNAMEID',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:130,
				hideOnLoseFocus:true,
				autocomplete:true
				
			});
			 $("#equipment_status").ligerComboBox({  //设备状态 555
					url:'control_searchEquipstatus.action',
					valueField: 'id',
					valueFieldID: 'equipstatusid',
					textField: 'text',
					selectBoxHeight:200,
					selectBoxWidth:100,
					hideOnLoseFocus:true,
					autocomplete:true
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
                		
                    
                },
                submitHandler: function (){
					jQuery("#edituser").css('height',0);
              	   	jQuery("#edituser").css('display','none');
              	   	grid.set('height',document.body.clientHeight*0.9);
              	  	if($("#oilstationname1").val() !=''){
  					oilstationname1 = $("#oilationid1").val();
  						}
  				
	  				if($("#blockstationname1").val() !=''){
	  					blockstationname1 = $("#stationid1").val();  
	  					}
	  				
	  				if($("#wellnum1").val() != ''){
	  					wellnum1 = $("#wellid1").val();
	  					}
	  				
	  				if($("#boiler1").val()!=''){
  						boiler1 = $("#boilerid1").val();
  					}
	  				//var instrumentationid  = $("#ybID").val();
	  				if($("#ybName").val()!=''){
	  					var instrumentationid = $("#YBNAMEID").val();
	  					
  					}
  					//alert(instrumentationid);
	  				var ybName  = $("#ybName").val();
              	   	var controllerid  = $("#controllerid").val();
					
					
					var facility_name = $("#facility_name").val();
					
					
					var communication_type  = $("#communication_type").val();
					var station_no  = $("#station_no").val();
					var communication_port  = $("#communication_port").val();
					if($("#equipment_status").val() != ''){
						equipment_status = $("#equipstatusid").val();
	  					}
					var IP_ADDRESS  = $("#IP_ADDRESS").val();
					var org_id  = $("#org_id").val();
					var remark  = $("#remark").val();
					structuretype  = $("#structuretype").val();  
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'control_SaveOrUpdateControl.action',
									async : false,
									data: {"facility_name":facility_name ,"ybName":ybName,"communication_type":communication_type,"oilstationname1":oilstationname1 ,
									"blockstationname1":blockstationname1,"wellnum1":wellnum1 ,"boiler1":boiler1,"IP_ADDRESS":IP_ADDRESS,
											"station_no":station_no,"communication_port":communication_port,"equipment_status":equipment_status,
											"controllerid":controllerid,	"remark":remark,"instrumentationid":instrumentationid},
											success : function(data) { 
													if (data != null && typeof(data)!="undefined"){
														var outData = eval('(' + data + ')');
														if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
															$.ligerDialog.error(outerror(outData.ERRCODE));
														}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
															$.ligerDialog.error(outData.ERRMSG);
														}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
															$.ligerDialog.success(outData.CONFIRMMSG);
														}else{
															onSubmit();
															setpage2(1); //隐藏编辑界面
															setItemsd(2); //去掉隐藏，显示按钮
															$.ligerDialog.success('控制器信息添加操作成功！');
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
									url : 'control_SaveOrUpdateControl.action',
									async : false,
									data: {"facility_name":facility_name ,"ybName":ybName,"communication_type":communication_type,"oilstationname1":oilstationname1 ,
										"blockstationname1":blockstationname1,"wellnum1":wellnum1 ,"boiler1":boiler1,"IP_ADDRESS":IP_ADDRESS,
												"station_no":station_no,"communication_port":communication_port,"equipment_status":equipment_status,
											"controllerid":controllerid,"remark":remark,"instrumentationid":instrumentationid},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
														$.ligerDialog.success(outData.CONFIRMMSG);
													}else{
														onSubmit();
														setpage2(1); //隐藏编辑界面
														setItemsd(2); //去掉隐藏，显示按钮
														$.ligerDialog.success('控制器信息修改操作成功！');
														operate = 0;
													}
											}
										},
												error : function(data) {
								
									}
								});
              	   
              	   }
		           operate = 0;
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
        							
        							controllerid = rowdata.CONTROLLERID;
        							oilationid1 = rowdata.OILSTATIONNAME;
        					         stationid1 = rowdata.OILSTATIONNAME;
        					         wellid1 = rowdata.OILSTATIONNAME;
        					         boilerid1 = rowdata.OILSTATIONNAME;
        					         org_id = rowdata.ORG_ID;
        					         structuretype = rowdata.STRUCTURETYPE;
        					     
        					         equipment_status  =rowdata.STATUS;
        					         
									if (rowdata.INSTRUMENTATIONID != null && typeof(rowdata.INSTRUMENTATIONID)!="undefined"){
									 	instrumentationid = rowdata.INSTRUMENTATIONID;
									 }else{
									 	instrumentationid = "";
									 }
									if (rowdata.YBNAME != null && typeof(rowdata.YBNAME)!="undefined"){
										YBNAME = rowdata.YBNAME;
									 }else{
										 YBNAME = "";
									 }
									if (rowdata.FACILITY_NAME != null && typeof(rowdata.FACILITY_NAME)!="undefined"){
									 	facility_name = rowdata.FACILITY_NAME;
									 }else{
									 	facility_name = "";
									 }
									 if (rowdata.COMMUNICATION_TYPE != null && typeof(rowdata.COMMUNICATION_TYPE)!="undefined"){
									 	communication_type = rowdata.COMMUNICATION_TYPE;
									 }else{
									 	communication_type = "";
									 }
									
									 if (rowdata.STATION_NO != null && typeof(rowdata.STATION_NO)!="undefined"){
									 	station_no = rowdata.STATION_NO;
									 }else{
									 	station_no = "";
									 }
									 if (rowdata.COMMUNICATION_PORT != null && typeof(rowdata.COMMUNICATION_PORT)!="undefined"){
									 	communication_port = rowdata.COMMUNICATION_PORT;
									 }else{
									 	communication_port = "";
									 }
							
									 if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
									 	remark = rowdata.REMARK; 
									 }else{
									 	remark = "";
									 }
									 if (rowdata.IP_ADDRESS != null && typeof(rowdata.IP_ADDRESS)!="undefined"){
										 IP_ADDRESS = rowdata.IP_ADDRESS; 
									 }else{
										 IP_ADDRESS = "";
									 }
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }
      
    	function YbData(data,proData){
			jQuery.ajax({
				type : 'post',
    			url:'control_searchYBNameQueryJL.action',
    			data: {"boilerid":data},
    			
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("instrutype").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("instrutype").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				
    			}
				});
        	}
    	function YbData1(data,proData){
			jQuery.ajax({
				type : 'post',
    			url:'control_searchYBNameQueryJL.action',
    			data: {"boilerid":data},
    			
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("ybName").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("ybName").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				
    			}
				});
        	}
    	 function setStationData(data,proData) {
     		var boilerText=[{ id: 1 , text: '' }];
     		var stationText=[{ id: 1 , text: '' }];
     		var wellText=[{ id: 1 , text: '' }];
     		var ybtext=[{ id: 1 , text: '' }];
     		
     		jQuery.ajax({
     			type : 'post',
     			//url:'rulewell_queryStationInfo.action',
     			url:'instru_queryAdd.action?nowdata='+parseInt(Math.random()*100000),
     			data: {"oilid":data},
     			
     			success : function(jsondata) {
     				var dataObj = $.parseJSON(jsondata);
     				$.each(dataObj, function(key,val){
     					if (key == 'boilertext'){
     						boilerText = val;
     					}
     					if (key == 'stationtext'){
     						stationText = val;
     					}
     					if (key == 'welltext'){
     						wellText = val;
     					}
     					if (key == 'ybtext'){
     						ybtext = val;
     					}
     				});
     				setInitData1(boilerText,stationText,wellText,ybtext);
     				//if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
     				//	liger.get("blockstationname1").setData(eval('(' + jsondata + ')'));
     					//liger.get("wellnum1").setData(eval('(' + jsondata + ')'));
     					//liger.get("boiler1").setData(eval('(' + jsondata + ')'));
     				//}
     				//else{
     				//	liger.get("blockstationname1").setData(proData);
     				//}
     			},
     			error : function(jsondata) {
     				
     			}
     		});
     	}
         function setInitData1(boilerText,stationText,wellText,ybtext) {
     		liger.get("boiler1").setData(boilerText);												
     		liger.get("blockstationname1").setData(stationText);
     		liger.get("wellnum1").setData(wellText);
     		liger.get("ybName").setData(ybtext);
     	}
     	
         function setWellData(data,proData) {
         	var boilerText=[{ id: 1 , text: '' }];
     		var wellText=[{ id: 1 , text: '' }];
     		var ybtext=[{ id: 1 , text: '' }];
     		jQuery.ajax({
     			type : 'post',
     			url:'instru_queryAdd.action?nowdata='+parseInt(Math.random()*100000),
     			data: {"ghid":data},
     			
     			success : function(jsondata) {
     				var dataObj = $.parseJSON(jsondata);
     				$.each(dataObj, function(key,val){
     					if (key == 'boilertext'){
     						boilerText = val;
     					}
     				
     					if (key == 'welltext'){
     						wellText = val;
     					}
     					if (key == 'ybtext'){
     						ybtext = val;
     					}
     				});
     				setInitData2(boilerText,wellText,ybtext);
     			},
     			error : function(jsondata) {
     				
     			}
     		});
     	}
         function setInitData2(boilerText,wellText,ybtext) {
     		liger.get("boiler1").setData(boilerText);												
     		liger.get("wellnum1").setData(wellText);
     		liger.get("ybName").setData(ybtext);
     	}
           function getWellInitData() {
       		var oilText=[{ id: 1 , text: '' }];
       		var boilerText=[{ id: 1 , text: '' }];
       		var stationText=[{ id: 1 , text: '' }];
       		var wellText=[{ id: 1 , text: '' }];
       		var ybtext=[{ id: 1 , text: '' }];
       		
       		jQuery.ajax({
       			type : 'post',
       			url : 'instru_cascadeInit.action',
       			success : function(jsondata) {
       			var dataObj = $.parseJSON(jsondata);
       				$.each(dataObj, function(key,val){
       					if (key == 'oiltext') {
       						oilText = val;
       					}
       					if (key == 'boilertext'){
       						boilerText = val;
       					}
       					if (key == 'stationtext'){
       						stationText = val;
       					}
       					if (key == 'welltext'){
       						wellText = val;
       					}
       					if (key == 'ybtext'){
       						ybtext = val;
       					}
       				});
       				setInitData(oilText,boilerText,stationText,wellText,ybtext);
       			}
       		});
       	}
       	
           function setInitData(oilText,boilerText,stationText,wellText,ybtext) {
       		liger.get("oilstationname1").setData(oilText);
       		liger.get("boiler1").setData(boilerText);
       		liger.get("blockstationname1").setData(stationText);
       		liger.get("wellnum1").setData(wellText);
       		liger.get("ybName").setData(ybtext);
       	}
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype){
			var oilstationname='';
			var blockstationname='';
			var boiler='';
			var wellnum='';
			//$("#areablock1").ligerGetComboBoxManager().setValue('');
			getWellInitData();
			if(datatype=='7'){
				oilstationname=name;
			}
			if(datatype=='9'){
				blockstationname=name;
				$("#blockstationname").val(name);
			}
			if(datatype=='8'){
				boiler=name;
				$("#boiler").val(name);
			}
			if(datatype=='10'){
				wellnum=name;
				//$("#wellnum").ligerGetComboBoxManager().selectValue(basid);
				$("#wellnum").val(name);
			}
			grid.setOptions({
				parms: [{name: 'oilstationname',value: oilstationname},
						{name: 'blockstationname',value: blockstationname},
						{name: 'wellnum',value: wellnum},
						{name: 'boiler',value: boiler}
						]});
			grid.loadData(true);
		}
		
		function onSubmit(){
			grid.setOptions({
				parms: [
						//{ name: 'oilstationname',value: $("#oilationid").val()},
						//{name: 'blockstationname',value: $("#stationid").val()},
						//{name: 'wellnum',value: $("#wellid").val()},
						//{name: 'boiler',value: $("#boilerid").val()},
						//{name: 'instrutype',value: $("#instrutype").val()}
						{ name: 'oilstationname',value: $("#oilstationname").val()},
						{name: 'blockstationname',value: $("#blockstationname").val()},
						{name: 'wellnum',value: $("#wellnum").val()},
						{name: 'boiler',value: $("#boiler").val()},
						{name: 'instrutype',value: $("#instrutype").val()}
						]});
			grid.loadData(true);
		}
      function assignM(flg){
      		
			 if(flg == 2){
					 //$("#oilstationname1").ligerGetComboBoxManager().selectValue(org_id);
					 //$("#blockstationname1").ligerGetComboBoxManager().selectValue(org_id);
					 //$("#wellnum1").ligerGetComboBoxManager().selectValue(org_id);
					 //$("#boiler1").ligerGetComboBoxManager().selectValue(org_id);
			 if(structuretype =='7'){
					 $("#oilstationname1").ligerGetComboBoxManager().selectValue(org_id);
					 $("#blockstationname1").ligerGetComboBoxManager().selectValue("");
					 $("#wellnum1").ligerGetComboBoxManager().selectValue("");
					 $("#boiler1").ligerGetComboBoxManager().selectValue("");
						//alert(org_id)
					
				}else if(structuretype =='8'){
					 $("#oilstationname1").ligerGetComboBoxManager().selectValue("");
					 $("#blockstationname1").ligerGetComboBoxManager().selectValue(org_id);
					 $("#wellnum1").ligerGetComboBoxManager().selectValue("");
					 $("#boiler1").ligerGetComboBoxManager().selectValue("");
					
				}else if(structuretype =='9'){
					 $("#oilstationname1").ligerGetComboBoxManager().selectValue("");
					 $("#blockstationname1").ligerGetComboBoxManager().selectValue("");
						$("#wellnum1").ligerGetComboBoxManager().selectValue(org_id);
						
						 $("#boiler1").ligerGetComboBoxManager().selectValue("");
						
						//alert(org_id)
				}else if(structuretype =='10'){
					 $("#oilstationname1").ligerGetComboBoxManager().selectValue("");
					 $("#blockstationname1").ligerGetComboBoxManager().selectValue("");
					 $("#wellnum1").ligerGetComboBoxManager().selectValue("");
					 $("#boiler1").ligerGetComboBoxManager().selectValue(org_id);
					 }
				
					//$("#ybName").ligerGetComboBoxManager().selectValue(instrumentationid);
					//alert(ybName)
			 		document.getElementById("controllerid").value = controllerid;
	              	document.getElementById("ybID").value = instrumentationid;
	              // 	document.getElementById("facility_name").value = facility_name;
	            	document.getElementById("ybName").value = YBNAME;
	               	document.getElementById("communication_type").value= communication_type;
	               	document.getElementById("station_no").value= station_no;
	               	document.getElementById("communication_port").value= communication_port;
	              	document.getElementById("equipment_status").value= equipment_status;
	               	document.getElementById("remark").value= remark;
	              	document.getElementById("IP_ADDRESS").value= IP_ADDRESS;
	               //	document.getElementById("instrumentation_type").value= instrumentation_type;
			 }else if(flg == 1){
				 $("#oilstationname1").ligerGetComboBoxManager().setValue("");
				 	$("#blockstationname1").ligerGetComboBoxManager().selectValue("");
				 	$("#wellnum1").ligerGetComboBoxManager().selectValue("");
				 	$("#boiler1").ligerGetComboBoxManager().selectValue("");
				 	
			 		document.getElementById("controllerid").value = "";
	               //	document.getElementById("instrumentationid").value = "";
	               	//document.getElementById("facility_name").value = "";
	            	document.getElementById("ybName").value = "";
	               	document.getElementById("communication_type").value= "";
	                document.getElementById("ybID").value = "";
	               	document.getElementById("station_no").value= "";
	               	document.getElementById("communication_port").value= "";
	               	document.getElementById("equipment_status").value= "";
	               	document.getElementById("remark").value= "";
	            	document.getElementById("IP_ADDRESS").value= "";
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
            	  if(operate != 1 && operate != 2){
						setpage1(0); //显示编辑界面
						setItemsd(0);//0-显示编辑区，添加隐藏按钮
						//liger.get("oilstationname1").setEnabled(true);
						//liger.get("blockstationname1").setEnabled(true);
						//liger.get("wellnum1").setEnabled(true);
						//liger.get("boiler1").setEnabled(true);	
					  }
            	  
            	   operate = 1;
            	   assignM(1);
              	   
              	   
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改信息的数据！');
              	    
              	     }else if(rows.length == 1){
              	    	//liger.get("oilstationname1").setDisabled(true);
    					//liger.get("blockstationname1").setDisabled(true);
    					//liger.get("wellnum1").setDisabled(true);
    					//liger.get("boiler1").setDisabled(true);	
              	    	if(operate != 1 && operate != 2){
							setpage1(0); //显示编辑界面
							setItemsd(0);//0-显示编辑区，添加隐藏按钮
						  }
	              	   operate = 2;
	              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改信息的数据！');
              	     }
                  break;
              case "delete":
				if(rows.length == 1){
					$.ligerDialog.confirm('确定删除吗?', function (yes) {
						if(yes){
							jQuery.ajax({
							type : 'post',
							url : 'control_removeControl.action',
							data: {"controllerid":controllerid,"org_id":org_id},
							async : false,
							success : function(data) {
								if (data != null && typeof(data)!="undefined" && data == "1"){
									onSubmit();
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
						<td align="right" class="l-table-edit-td" style="width:50px">所属站：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="oilstationname" name = "oilstationname" />
		                </td>
		                <td align="left">
		                </td>
		               <!-- <td align="right" class="l-table-edit-td" style="width:50px">区块：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="areablock1" name = "areablock1"/>
		                </td>
		                <td align="left">
		                </td> -->
		                  <td align="right" class="l-table-edit-td" style="width:50px">所属管汇：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="blockstationname" name = "blockstationname"/>
		                </td>
		                <td align="left"></td>
		                
		                <td align="right" class="l-table-edit-td" style="width:50px">所属井口：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="wellnum" name = "wellnum"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:50px">所属锅炉：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="boiler" name = "boiler"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:70px">仪表类型：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="instrutype" name = "instrutype"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:50px"></td>
		                <td align="left" class="l-table-edit-td"  >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
		                </td>
					</tr>
				
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="" >
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		           		<td align="left" class="l-table-edit-td" style="width:150px">所属站:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属管汇:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属井口:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属锅炉:</td>
		              
		                <td align="left" class="l-table-edit-td" style="width:150px">所属仪表:</td>
		                  <%-- <td align="left" class="l-table-edit-td" style="width:150px">设备名称:</td>--%>
		                <td align="left" class="l-table-edit-td" style="width:150px">驱动类型:</td>
		                 </tr>
		            <tr>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="oilstationname1" type="text" id="oilstationname1"  ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="blockstationname1" type="text" id="blockstationname1"  ltype="text" />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="wellnum1" type="text" id="wellnum1"  ltype="text"  />
		                </td>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="boiler1" type="text" id="boiler1"  ltype="text"  />
		                </td>
		            	 <%--  <td align="left" class="l-table-edit-td" >
		                	<input name="facility_name" type="text" id="ybName" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>--%>
		                
		                
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="ybName" type="text" id="ybName"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		              
		                <td align="left" class="l-table-edit-td" >
		                	<input name="communication_type" type="text" id="communication_type" ltype="text"  />
		                </td>
		               
		                
		            </tr>
		            <tr>
		            	<td align="left" class="l-table-edit-td" style="width:150px">IP地址:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">通讯站号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">通讯端口:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">设备状态:</td>
		                <td align="left" class="l-table-edit-td" >备注:</td>
		              
		               
		               </tr>
		            <tr>
		               <td align="left" class="l-table-edit-td" >
		                	<input name="IP_ADDRESS" type="text" id="IP_ADDRESS" ltype="text"  />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="station_no" type="text" id="station_no" ltype="text"  />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="communication_port" type="text" id="communication_port" ltype="text" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="equipment_status" type="text" id="equipment_status" ltype="text"  />
		                </td>
		          
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text" />
		                </td>
		               
		            </tr>
		            
		            <tr>
	
		                
		                <td align="left" class="l-table-edit-td" >
		                	
		                	<!-- <input name="audbshor_date" type="text" id="audbshor_date" ltype="text" /> -->
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="controllerid" type="hidden" id="controllerid" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ybID" type="hidden" id="ybID" />
		                </td>
		            </tr>
		            <tr>
		            	<td>
		            		<br/>
		            	</td>
		            </tr>
		            
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>