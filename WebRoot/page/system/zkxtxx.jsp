<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>站库系统信息管理</title>
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
      
		//区块名称
		var qkmc;
		//单元名
		var unit_name;
		//系统代码
		var system_code;
		//下位软件型号
		var down_software_model;
		//上位软件型号
		var upper_software_model;
		
		//描述标识
		var remark;
		
		//服务器id
		var ljjd_id;
		//下位软件厂家id
		var down_software_facturer;
		//上位软件厂家id
		var upper_software_facturer;
		//集成商厂家id
		var integrator_code;
		//对象类型id
		var object_code;
		//大型站库类型id
		var large_station_id;
		
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'zkxtxx_pageInit.action',
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
			$("#objecttype").ligerComboBox({
				//url:'searchQueryAll_searchALLCYZ.action',
	            valueField: 'id',
				valueFieldID: 'hidobjecttype',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
	            hideOnLoseFocus:true,
	            autocomplete:true
			});
			$("#ljjds").ligerComboBox({
				//url:'searchQueryAll_searchTeam.action',
				valueField: 'id',
				valueFieldID: 'hidljjds',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				hideOnLoseFocus:true,
	            autocomplete:true
			});
			
			
			$("#OBJECT_NAME").ligerComboBox({
				//url:'searchQueryAll_searchCYZTEAMJH.action',
				valueField: 'id',
				valueFieldID: 'hidOBJECT_NAME',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
				
				});
			
			$("#DOWN_SOFTWARE_NAME").ligerComboBox({
				//url:'searchQueryAll_searchCYZTEAMJH.action',
				valueField: 'id',
				valueFieldID: 'hidDOWN_SOFTWARE_NAME',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
				
				});
		
			
			$("#UPPER_SOFTWARE_NAME").ligerComboBox({
				//url:'searchQueryAll_searchCYZTEAMJH.action',
				valueField: 'id',
				valueFieldID: 'hidUPPER_SOFTWARE_NAME',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
				
				});
			$("#INTEGRATOR_NAME").ligerComboBox({
				//url:'rulewell_queryStationInfo.action?oilid=alls',
	            valueField: 'id',
				valueFieldID: 'hidINTEGRATOR_NAME',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true
			});
		
	
			  $("#LJJD_S").ligerComboBox({
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
			  $("#COMUNIT").ligerComboBox({
		   			url:'zkxtxx_getComData.action',
		   			valueField: 'id',
		   			valueFieldID: 'COMUNITid',
		   			textField: 'text',
		   			selectBoxHeight:180,
		   			selectBoxWidth:140,
		   			width: 120,
		   			autocomplete:true,
		   			hideOnLoseFocus:true
		
		   		});
			//接入标识			  
			  $("#ACCESS_SIGNS").ligerComboBox({
 				 hideOnLoseFocus:true,
	 					autocomplete:true,
	 		                data: [
	 		                    { text: '接入', id: '0' },
	 		                    { text: '未接入', id: '1' }
	 		                    
	 		                ], valueFieldID: 'SIGNSId',
	 					initText :''
		     		});
            getWellInitData();
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
              	   
              	   	var OBJECT_NAME  = $("#hidOBJECT_NAME").val();
					var QKMC  = $("#QKMC").val();
					var UNIT_NAME  = $("#UNIT_NAME").val();
					var SYSTEM_CODE  = $("#SYSTEM_CODE").val();
					
					var DOWN_SOFTWARE_MODEL  = $("#DOWN_SOFTWARE_MODEL").val();
					var DOWN_SOFTWARE_NAME ="";
					if( $("#hidDOWN_SOFTWARE_NAME").val() != ''){
						DOWN_SOFTWARE_NAME  = $("#hidDOWN_SOFTWARE_NAME").val();
					}
					
					var UPPER_SOFTWARE_MODEL  = $("#UPPER_SOFTWARE_MODEL").val();
					var UPPER_SOFTWARE_NAME ="";
					if( $("#hidUPPER_SOFTWARE_NAME").val() != ''){
						UPPER_SOFTWARE_NAME  = $("#hidUPPER_SOFTWARE_NAME").val();
					}
					var INTEGRATOR_NAME ="";
					if( $("#hidINTEGRATOR_NAME").val() != ''){
						INTEGRATOR_NAME  = $("#hidINTEGRATOR_NAME").val();
					}
				
					var LJJD_S  = $("#LJJD_S").val();
					if(LJJD_S != ''){
						LJJD_S =  $("#hidserverid").val() ;
					}
				
					var remark  = $("#remark").val();
					var ACCESS_SIGNS  = $("#ACCESS_SIGNS").val();
					var COMUNIT="";
					if( $("#COMUNIT").val() !=''){
						COMUNIT = $("#COMUNITid").val();
						}
              	   //判断修改还是添加操作 1-添加、2-修改
              	  
              	   if(operate == 1){ 
              		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'zkxtxx_addOrupdateData.action',
									async : false,
									data: {
										   "OBJECT_NAME":OBJECT_NAME,
										   "QKMC":QKMC ,
											"UNIT_NAME":UNIT_NAME,
											"SYSTEM_CODE":SYSTEM_CODE ,
											"DOWN_SOFTWARE_MODEL":DOWN_SOFTWARE_MODEL,
											"DOWN_SOFTWARE_NAME":DOWN_SOFTWARE_NAME,
											"UPPER_SOFTWARE_MODEL":UPPER_SOFTWARE_MODEL,
											"UPPER_SOFTWARE_NAME":UPPER_SOFTWARE_NAME,
											"INTEGRATOR_NAME":INTEGRATOR_NAME,
											"LJJD_S":LJJD_S,
											"remark":remark,
											"ACCESS_SIGNS":ACCESS_SIGNS,
											"COMUNIT":COMUNIT},
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
															setpage2(1); //隐藏编辑界面
															setItemsd(2); //去掉隐藏，显示按钮
															$.ligerDialog.success('阀池添加成功！');
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
									url : 'zkxtxx_addOrupdateData.action',
									async : false,
									data: {"large_station_id":large_station_id,
										    "OBJECT_NAME":OBJECT_NAME,
										   "QKMC":QKMC ,
											"UNIT_NAME":UNIT_NAME,
											"SYSTEM_CODE":SYSTEM_CODE ,
											"DOWN_SOFTWARE_MODEL":DOWN_SOFTWARE_MODEL,
											"DOWN_SOFTWARE_NAME":DOWN_SOFTWARE_NAME,
											"UPPER_SOFTWARE_MODEL":UPPER_SOFTWARE_MODEL,
											"UPPER_SOFTWARE_NAME":UPPER_SOFTWARE_NAME,
											"INTEGRATOR_NAME":INTEGRATOR_NAME,
											"LJJD_S":LJJD_S,
											"remark":remark,
											"ACCESS_SIGNS":ACCESS_SIGNS,
											"COMUNIT":COMUNIT},
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
														setpage2(1); //隐藏编辑界面
														setItemsd(2); //去掉隐藏，显示按钮
														$.ligerDialog.success('阀池修改成功！');
														operate = 0;
															
													}
						
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   }
              	
                }
            });
            
           
         	 $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  

			
        function getWellInitData() {
        	//$("#blockstationname").ligerGetComboBoxManager().setValue('');
    		//$("#cliquepool").ligerGetComboBoxManager().setValue('');
    		var INITDATA1=[{ id: 1 , text: '' }];
    		var INITDATA2=[{ id: 1 , text: '' }];
    		var INITDATA3=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'searchQueryAll_searchZKCJD.action?arg=ZKXTXX',
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'INITDATA1') {
    						INITDATA1 = val;
    					}
    					if (key == 'INITDATA2'){
    						INITDATA2 = val;
    					}
    					if (key == 'INITDATA3'){
    						INITDATA3 = val;
    						//wellDatas = val;
    					}
    				});
    				setInitData(INITDATA1,INITDATA2,INITDATA3);
    			}
    		});
    	}
    	
        function setInitData(INITDATA1,INITDATA2,INITDATA3) {
    		liger.get("objecttype").setData(INITDATA1);
    		liger.get("OBJECT_NAME").setData(INITDATA1);
    		liger.get("DOWN_SOFTWARE_NAME").setData(INITDATA2);
    		liger.get("UPPER_SOFTWARE_NAME").setData(INITDATA2);
    		liger.get("INTEGRATOR_NAME").setData(INITDATA2);
    		liger.get("ljjds").setData(INITDATA3);
    		liger.get("LJJD_S").setData(INITDATA3);
    	}
	
       	 function fromAu(rowdata){
        	//用户选择修改数据
    				//区块名称
					qkmc = rowdata.QKMC;
					//单元名
					unit_name = rowdata.UNIT_NAME;
					//系统代码
					system_code = rowdata.SYSTEM_CODE;
					//对象类型id
					object_code = rowdata.OBJECT_CODE;
					//大型站库类型id
					large_station_id = rowdata.LARGE_STATION_ID;
					
					//下位软件型号
					if (rowdata.DOWN_SOFTWARE_MODEL != null && typeof(rowdata.DOWN_SOFTWARE_MODEL)!="undefined"){
						down_software_model = rowdata.DOWN_SOFTWARE_MODEL;
					 }else{
						 down_software_model = "";
					 }
					
					
					
					//上位软件型号
					if (rowdata.UPPER_SOFTWARE_MODEL != null && typeof(rowdata.UPPER_SOFTWARE_MODEL)!="undefined"){
						upper_software_model = rowdata.UPPER_SOFTWARE_MODEL;
					 }else{
						 upper_software_model = "";
					 }
					//描述标识
					if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
						remark = rowdata.REMARK;
					 }else{
						 remark = "";
					 }
					
					//服务器id
					
					if (rowdata.LJJD_ID != null && typeof(rowdata.LJJD_ID)!="undefined"){
						ljjd_id = rowdata.LJJD_ID;
					 }else{
						 ljjd_id = "";
					 }
					//下位软件厂家id
					
					if (rowdata.DOWN_SOFTWARE_FACTURER != null && typeof(rowdata.DOWN_SOFTWARE_FACTURER)!="undefined"){
						down_software_facturer = rowdata.DOWN_SOFTWARE_FACTURER;
					 }else{
						 down_software_facturer = "";
					 }
					//上位软件厂家id
					
					if (rowdata.UPPER_SOFTWARE_FACTURER != null && typeof(rowdata.UPPER_SOFTWARE_FACTURER)!="undefined"){
						upper_software_facturer = rowdata.UPPER_SOFTWARE_FACTURER;
					 }else{
						 upper_software_facturer = "";
					 }
					//集成商厂家id
					
					if (rowdata.INTEGRATOR_CODE != null && typeof(rowdata.INTEGRATOR_CODE)!="undefined"){
						integrator_code = rowdata.INTEGRATOR_CODE;
					 }else{
						 integrator_code = "";
					 }
					 //联合站
					if (rowdata.COMUNIT != null && typeof(rowdata.COMUNIT)!="undefined"){
						COMUNIT = rowdata.COMUNIT;
					 }else{
						 COMUNIT = "";
					 }
					if (rowdata.COMBINATION_STATIONID != null && typeof(rowdata.COMBINATION_STATIONID)!="undefined"){
						COMBINATION_STATIONID = rowdata.COMBINATION_STATIONID;
					 }else{
						 COMBINATION_STATIONID = "";
					 }
					// 接入标志
					if (rowdata.ACCESS_SIGNS != null && typeof(rowdata.ACCESS_SIGNS)!="undefined"){
						ACCESS_SIGNS = rowdata.ACCESS_SIGNS;
					 }else{
						 ACCESS_SIGNS = "";
					 }
						
                	if(operate == 2){
                		assignM(2);
                	}
       		 }
        
        //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){
		}
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'objecttype',
					value: $("#objecttype").val()
				},{
					name: 'qkmc',
					value: $("#qkmc1").val()
				},{
					name: 'unitname',
					value: $("#unitname").val()
				},{
					name: 'systemcode',
					value: $("#systemcode").val()
				},{
					name: 'ljjds',
					value: $("#ljjds").val()
				}
				]
        	});
         	grid.loadData(true);
        }
        
        
       function onExport() {
   		var objecttype=$("#objecttype").val();
		var qkmc=$("#qkmc1").val()+'';
		var unitname=$("#unitname").val();
		var systemcode=$("#systemcode").val();
		var ljjds=$("#ljjds").val();
	
  		var totalNum = 0;
  		var url = "zkxtxx_searchDatas.action?objecttype="+encodeURIComponent(objecttype)+"&qkmc="+encodeURIComponent(qkmc)+"&unitname="+encodeURIComponent(unitname)+"&systemcode="+encodeURIComponent(systemcode)+"&ljjds="+encodeURIComponent(ljjds)+"&totalNum=export";
  		var urls = "zkxtxx_searchDatas.action?objecttype="+encodeURIComponent(objecttype)+"&qkmc="+encodeURIComponent(qkmc)+"&unitname="+encodeURIComponent(unitname)+"&systemcode="+encodeURIComponent(systemcode)+"&ljjds="+encodeURIComponent(ljjds)+"&totalNum=totalNum";
  		$.ajax({
  			type : 'post',
  			url : urls,
  			async : false,
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
      function assignM(flg){
      		
			 if(flg == 2){
			 		$("#OBJECT_NAME").ligerGetComboBoxManager().selectValue(object_code);
			 		document.getElementById("QKMC").value = qkmc;
	               	document.getElementById("UNIT_NAME").value = unit_name;
	               	//var ctypeid = $("#CLIQUE_TYPE").ligerGetComboBoxManager().findValueByText(clique_type);
	               	document.getElementById("SYSTEM_CODE").value= system_code;
	               	
	               	document.getElementById("DOWN_SOFTWARE_MODEL").value= down_software_model;
	               	$("#DOWN_SOFTWARE_NAME").ligerGetComboBoxManager().selectValue(down_software_facturer);
	               	document.getElementById("UPPER_SOFTWARE_MODEL").value= upper_software_model;
	               	$("#UPPER_SOFTWARE_NAME").ligerGetComboBoxManager().selectValue(upper_software_facturer);
	               	$("#INTEGRATOR_NAME").ligerGetComboBoxManager().selectValue(integrator_code);
	               	$("#LJJD_S").ligerGetComboBoxManager().selectValue(ljjd_id);
	               	document.getElementById("remark").value= remark;
	               	
	               	$("#COMUNIT").ligerGetComboBoxManager().selectValue(COMBINATION_STATIONID);
	               	if(ACCESS_SIGNS =='接入'){
	             		$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue(0);
	               	}else  	if(ACCESS_SIGNS =='未接入'){
	               		$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue(1);
	               	}else{
	               		$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue('');
	               		}
	              	
			 }else if(flg == 1){
			 		$("#OBJECT_NAME").ligerGetComboBoxManager().selectValue("");
			 		document.getElementById("QKMC").value = "";
	               	document.getElementById("UNIT_NAME").value = "";
	               	//var ctypeid = $("#CLIQUE_TYPE").ligerGetComboBoxManager().findValueByText(clique_type);
	               	document.getElementById("SYSTEM_CODE").value= "";
	               	
	               	document.getElementById("DOWN_SOFTWARE_MODEL").value= "";
	               	$("#DOWN_SOFTWARE_NAME").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("UPPER_SOFTWARE_MODEL").value= "";
	               	$("#UPPER_SOFTWARE_NAME").ligerGetComboBoxManager().selectValue("");
	               	$("#INTEGRATOR_NAME").ligerGetComboBoxManager().selectValue("");
	               	$("#LJJD_S").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("remark").value= "";
	             	$("#COMUNIT").ligerGetComboBoxManager().selectValue(COMBINATION_STATIONID);
	               	$("#ACCESS_SIGNS").ligerGetComboBoxManager().selectValue("");
	               	
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
              	    if(operate != 1 && operate != 2){
						setpage2(0); //显示编辑界面
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
								setpage2(0); //显示编辑界面
								setItemsd(0);//0-显示编辑区，添加隐藏按钮
							  }
		              	   operate = 2;
		              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改信息的数据！');
              	     }
                  break;
              case "delete":
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要删除的数据！')
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'zkxtxx_removeData.action',
											async : false,
											data: {"clique_id":clique_id,"org_id":org_id},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else{
														$.ligerDialog.success('阀池基础删除成功！');
														onSubmit();
														//assignM(1);
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
				setpage2(0); //显示编辑界面
				setItemsd(0);//0-显示编辑区，添加隐藏按钮
				break;   
				case "down":
						setpage2(1); //隐藏编辑界面
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
						<td align="right" class="l-table-edit-td" style="width:60px">对象类型：</td>
		                <td align="left" class="l-table-edit-td" >
	                		<input type="text" id="objecttype" name = "objecttype" />
		                </td>
		                
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:50px">区块：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="qkmc1" name = "qkmc1"/>
		                </td>
		                <td align="left">
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">单元名：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="unitname" name = "unitname"/>
		                </td>
		              
		                <td align="right" class="l-table-edit-td" style="width:60px">系统代码：</td>
						<td align="right" class="l-table-edit-td" style="width:80px">
							<input type="text" id="systemcode" name="systemcode" />
						</td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">服务器节点：</td>
						<td align="right" class="l-table-edit-td" style="width:80px">
							<input type="text" id="ljjds" name="ljjds" />
						</td>
		                
		                <td align="right" class="l-table-edit-td" style="width:10px"></td>
		                <td align="left" class="l-table-edit-td"  >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:10px"></td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a>
		                </td>
					</tr>
				
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="width:99%;height: 100px; display:none;">
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">对象类型:</td>
		              	<td align="left" class="l-table-edit-td" style="width:150px">区块名称:</td>
		              	<td align="left" class="l-table-edit-td" style="width:150px">单元名:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">系统代码:</td>
		             	 <td align="left" class="l-table-edit-td" style="width:150px">下位软件型号:</td>
		           	
		           		 <td align="left" class="l-table-edit-td" >下位软件厂家:</td>
			                <td align="left" class="l-table-edit-td" >上位软件型号:</td>
			               	<td align="left" class="l-table-edit-td" >上位软件厂家:</td>
			                
						
		                </tr>
		            	<tr>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="OBJECT_NAME" type="text" id="OBJECT_NAME"  ltype="text" validate="{required:true}" />
		                </td>
		                 
		                  <td align="left" class="l-table-edit-td" style="">
		                	<input name="QKMC" type="text" id="QKMC" ltype="text"  validate="{required:true}" />
		               	 </td>
		               	  <td align="left" class="l-table-edit-td" >
		                	<input name="UNIT_NAME" type="text" id="UNIT_NAME" ltype="text" validate="{required:true}" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="SYSTEM_CODE" type="text" id="SYSTEM_CODE" ltype="text"  validate="{required:true}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="DOWN_SOFTWARE_MODEL" type="text" id="DOWN_SOFTWARE_MODEL" ltype="text"/>
		                </td>
		          
		             	 <td align="left" class="l-table-edit-td" >
		                	<input name="DOWN_SOFTWARE_NAME" type="text" id="DOWN_SOFTWARE_NAME" ltype="text" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="UPPER_SOFTWARE_MODEL" type="text" id="UPPER_SOFTWARE_MODEL" ltype="text"/>
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="UPPER_SOFTWARE_NAME" type="text" id="UPPER_SOFTWARE_NAME" ltype="date" />
		                </td>
		               
		           	 </tr>
		          		
		          	<tr>
		          		<td align="left" class="l-table-edit-td" >集成商厂家:</td>
		          		<td align="left" class="l-table-edit-td" >服务器节点名:</td>
		          		<td align="left" class="l-table-edit-td" >描述标识:</td>
		          		<td align="left" class="l-table-edit-td" >所属单位:</td>
		          		<td align="left" class="l-table-edit-td" >接入标识:</td>
		          	</tr>
		          	
		          	<tr>
		          		<td align="left" class="l-table-edit-td" >
			          		<input name="INTEGRATOR_NAME" type="text" id="INTEGRATOR_NAME" ltype="text"  />
		                	
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="LJJD_S" type="text" id="LJJD_S" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text"  />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="COMUNIT" type="text" id="COMUNIT" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ACCESS_SIGNS" type="text" id="ACCESS_SIGNS" ltype="text"  />
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