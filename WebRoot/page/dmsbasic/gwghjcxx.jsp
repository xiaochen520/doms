<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>管网管汇基础信息</title>
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
					url : 'gwgh_pageInit.action',
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
    					if ( data != null && typeof(data)!="undefined" && data != '' && data !=1) {
    						OnChangeData($("#oilName").val(),"");
    					}else{
    						getAllInitData();
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
    					if (data != null && typeof(data)!="undefined" && data != ''){
    						OnChangeData("",$("#stationName").val());
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
    			//编辑
    			$("#edit_STATION").ligerComboBox({
    				url:'gwgh_searchStation.action',
    				valueField: 'id',
    				valueFieldID: 'staid',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:110,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				}
    			});
			  $("#edit_INSTALLDATE").ligerDateEditor(
                {
                    format: "yyyy-MM-dd ",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
			  $("#edit_JRBZ").ligerComboBox({
				  url:'comboboxdata_searchSwitchInflg.action', 
	     			valueField: 'id',
	     			valueFieldID: 'jrbzid',
	     			textField: 'text',
	     			selectBoxHeight:150,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true
		     		});
			  $("#edit_FWQLJJD").ligerComboBox({
	     			url:'sagd_getServerNode.action',
	     			valueField: 'id',
	     			valueFieldID: 'hidserverid',
	     			textField: 'text',
	     			selectBoxHeight:150,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true

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
                submitHandler: function ()
                {
                    var NETWORK_MD_ID = $("#NETWORK_MD_ID").val();
                    var  ORG_ID = $("#ORG_ID").val();
              	   	var edit_STATION = "";
              	   	if( $("#edit_STATION").val() !=''){
              	   		edit_STATION = $("#staid").val();
                  	 }
              	    var edit_NETWORK = $("#edit_NETWORK").val();
              	    var  edit_CODE =   $("#edit_CODE").val();
              	    var edit_RTUCODE  =$("#edit_RTUCODE").val();
              	    var  edit_JRBZ =""; 
              	    if( $("#edit_JRBZ").val() !=''){
              	    	edit_JRBZ =  $("#jrbzid").val();
                  	    }
				    var  edit_FWQLJJD ="";
				    if( $("#edit_FWQLJJD").val() !=''){
				    	edit_FWQLJJD= $("#hidserverid").val();
				    	}
				    var  edit_INSTALLDATE  = $("#edit_INSTALLDATE").val();
				    var  edit_REMARK  	   = $("#edit_REMARK").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'gwgh_saveorUpdateData.action',
									async : false,
									data: {"NETWORK_MD_ID":NETWORK_MD_ID,"ORG_ID":ORG_ID,"edit_STATION":edit_STATION ,"edit_NETWORK":edit_NETWORK,"edit_CODE":edit_CODE ,
											"edit_RTUCODE":edit_RTUCODE,"edit_JRBZ":edit_JRBZ,"edit_FWQLJJD":edit_FWQLJJD,
											"edit_INSTALLDATE":edit_INSTALLDATE,"edit_REMARK":edit_REMARK},
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
									url : 'gwgh_saveorUpdateData.action',
									async : false,
									data: {"NETWORK_MD_ID":NETWORK_MD_ID,"ORG_ID":ORG_ID,"edit_STATION":edit_STATION ,"edit_NETWORK":edit_NETWORK,"edit_CODE":edit_CODE ,
											"edit_RTUCODE":edit_RTUCODE,"edit_JRBZ":edit_JRBZ,"edit_FWQLJJD":edit_FWQLJJD,
											"edit_INSTALLDATE":edit_INSTALLDATE,"edit_REMARK":edit_REMARK},
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
        							
									if (rowdata.PIPE_NETWORK != null && typeof(rowdata.PIPE_NETWORK)!="undefined"){
										PIPE_NETWORK = rowdata.PIPE_NETWORK;
									 }else{
										 PIPE_NETWORK = "";
									 }
									if (rowdata.PIPE_CODE != null && typeof(rowdata.PIPE_CODE)!="undefined"){
										PIPE_CODE = rowdata.PIPE_CODE;
									 }else{
										 PIPE_CODE = "";
									 }
									if (rowdata.STATIONNAME != null && typeof(rowdata.STATIONNAME)!="undefined"){
										STATIONNAME = rowdata.STATIONNAME;
									 }else{
										 STATIONNAME = "";
									 }
									
									if (rowdata.STATIONCODE != null && typeof(rowdata.STATIONCODE)!="undefined"){
										STATIONCODE = rowdata.STATIONCODE;
									 }else{
										 STATIONCODE = "";
									 }

									if (rowdata.RTU_CODE != null && typeof(rowdata.RTU_CODE)!="undefined"){
										RTU_CODE = rowdata.RTU_CODE;
									 }else{
										 RTU_CODE = "";
									 }
									if (rowdata.SWITCH_IN_FLAG != null && typeof(rowdata.SWITCH_IN_FLAG)!="undefined"){
										SWITCH_IN_FLAG = rowdata.SWITCH_IN_FLAG;
									 }else{
										 SWITCH_IN_FLAG = "";
									 }				
									if (rowdata.JRBZ != null && typeof(rowdata.JRBZ)!="undefined"){
										JRBZ = rowdata.JRBZ;
									 }else{
										 JRBZ = "";
									 }				
									if (rowdata.SCADA_NODE != null && typeof(rowdata.SCADA_NODE)!="undefined"){
										SCADA_NODE = rowdata.SCADA_NODE;
									 }else{
										 SCADA_NODE = "";
									 }
									if (rowdata.LJJD != null && typeof(rowdata.LJJD)!="undefined"){
										LJJD = rowdata.LJJD;
									 }else{
										 LJJD = "";
									 }
									if (rowdata.INSTALL_DATE != null && typeof(rowdata.INSTALL_DATE)!="undefined"){
										INSTALL_DATE = rowdata.INSTALL_DATE; 
									 }else{
										 INSTALL_DATE = "";
									 }
									 if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
										 REMARK = rowdata.REMARK; 
									 }else{
										 REMARK = "";
									 }
									 if (rowdata.STATIONID != null && typeof(rowdata.STATIONID)!="undefined"){
										 STATIONID = rowdata.STATIONID; 
									 }else{
										 STATIONID = "";
									 }
									 if (rowdata.ORG_ID != null && typeof(rowdata.ORG_ID)!="undefined"){
										 ORG_ID = rowdata.ORG_ID; 
									 }else{
										 ORG_ID = "";
									 }
									 if (rowdata.NETWORK_MD_ID != null && typeof(rowdata.NETWORK_MD_ID)!="undefined"){
										 NETWORK_MD_ID = rowdata.NETWORK_MD_ID; 
									 }else{
										 NETWORK_MD_ID = "";
									 }
				                	if(operate == 2){
				                		assignM(2);
				                	}
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
    					
    					//if (key == 'oilText'){
    					//	oilText = val;
    					//}
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
        		parms: [{name: 'oilName',value: $("#oilName").val()},
        		        {name: 'stationName',value: $("#stationName").val()},
						{name: 'NETWORK', value:$("#NETWORK").val()}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
				 $("#edit_STATION").ligerGetComboBoxManager().selectValue(STATIONID);
				 document.getElementById("edit_NETWORK").value =PIPE_NETWORK;
				 document.getElementById("edit_CODE").value =PIPE_CODE;
				 document.getElementById("edit_RTUCODE").value =RTU_CODE;
				 $("#edit_JRBZ").ligerGetComboBoxManager().selectValue(SWITCH_IN_FLAG);
				 //document.getElementById("edit_JRBZ").value =JRBZ;
				 $("#edit_FWQLJJD").ligerGetComboBoxManager().selectValue(SCADA_NODE);
				// document.getElementById("").value =LJJD;
				 document.getElementById("edit_INSTALLDATE").value = INSTALL_DATE;
				 document.getElementById("edit_REMARK").value = REMARK;
				 document.getElementById("NETWORK_MD_ID").value = NETWORK_MD_ID;
	               	
			 }else if(flg == 1){
	             $("#edit_STATION").ligerGetComboBoxManager().selectValue("");
				 document.getElementById("edit_NETWORK").value = "";
				 document.getElementById("edit_CODE").value = "";
				 document.getElementById("edit_RTUCODE").value = "";
				 $("#edit_JRBZ").ligerGetComboBoxManager().selectValue("");
				 $("#edit_FWQLJJD").ligerGetComboBoxManager().selectValue("");
				// document.getElementById("edit_JRBZ").value = "";
				// document.getElementById("edit_FWQLJJD").value = "";
				 document.getElementById("edit_INSTALLDATE").value = "";
				 document.getElementById("edit_REMARK").value = "";
				 document.getElementById("NETWORK_MD_ID").value = "";
	               
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
            	  if(operate != 1 && operate != 2){
            		  setpage2(0); //显示编辑界面
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
              	    		setpage2(0); //显示编辑界面
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
											url : 'gwgh_removeData.action',
											async : false,
											data: {"NETWORK_MD_ID":NETWORK_MD_ID,"ORG_ID":ORG_ID},
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
            	  setpage2(0); //显示编辑界面
            	    setItemsd(0);//0-显示编辑区，添加隐藏按钮
                break;   
                case "down":
                	setpage2(1); //隐藏编辑界面
				 	setItemsd(1);//1-隐藏编辑区添加显示按钮
                break;    
          }
      }
      function onExport() {
 		var oilName=$("#oilName").val();
  	    var stationName=$("#stationName").val();
  	    var NETWORK=$("#NETWORK").val();

		var totalNum = 0;
		var url = "gwgh_searchAllData.action?oilName="+encodeURIComponent(oilName)+"&stationName="+encodeURIComponent(stationName)+"&NETWORK="+encodeURIComponent(NETWORK)+'&totalNum=export';
		var urls = "gwgh_searchAllData.action?oilName="+encodeURIComponent(oilName)+"&stationName="+encodeURIComponent(stationName)+"&NETWORK="+encodeURIComponent(NETWORK)+'&totalNum=totalNum';
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
						<td align="right" class="l-table-edit-td" style="width:100px">所属采油站:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilName" name = "oilName" />
		                </td>
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:100px">所属注转站:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="stationName" name = "stationName"/>
		                </td>
		                <td align="left">
		                </td>

		                <td align="right" class="l-table-edit-td" style="width:80px">管汇名称:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="NETWORK" name = "NETWORK"/>
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
		               
		                <td align="left" class="l-table-edit-td" style="width:150px">管汇名称:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属注转站:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">管汇编码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">RTU编号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">接入标志:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">服务器逻辑节点名:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">安装日期:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		             </tr>
		             <tr>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="edit_NETWORK" type="text" id="edit_NETWORK"  ltype="text" validate="{required:true}" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="edit_STATION" type="text" id="edit_STATION"  ltype="text" validate="{required:true}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="edit_CODE" type="text" id="edit_CODE" ltype="text"  />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="edit_RTUCODE" type="text" id="edit_RTUCODE" ltype="text"  />
		                </td>
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="edit_JRBZ" type="text" id="edit_JRBZ" ltype="text" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="edit_FWQLJJD" type="text" id="edit_FWQLJJD" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="edit_INSTALLDATE" type="text" id="edit_INSTALLDATE" ltype="date" />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="edit_REMARK" type="text" id="edit_REMARK" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="NETWORK_MD_ID" type="hidden" id="NETWORK_MD_ID" />
		                	<input name="ORG_ID" type="hidden" id="ORG_ID" />
		                </td>
		                </tr>
		             
		            </tr>
		            <tr>
		                
		             
		                <td align="left">
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