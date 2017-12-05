<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>ip使用表</title>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
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
        var pipesink; //管汇
        var blockstationname ;
        //鼠标选择数据
		var IP;
		var category;
		var area;
		var unit;
		var instrumentation_name;
		var IP_NO;
		var device;
		var interface2th;
		var segment_code2;
		var is_used;
		var start_date;
		var stop_date;
		var remark;
		var VPN_IP;
		var INSTRU_1TN;
		var rlastOdate;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'ipuse_pageInit.action',
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

			   $("#category").ligerComboBox({
       			url:"ipuse_searchCategory.action",
       			valueField: 'id',
       			valueFieldID: 'selCategoryid',
       			textField: 'text',
       			selectBoxHeight:200,
       			selectBoxWidth:99,
       			autocomplete:true,
       			hideOnLoseFocus:true

       		});
               $("#area").ligerComboBox({
					url:"ipuse_searchArea.action",
					valueFiled:'id',
					valueFieldID: 'selAreaid',
	    			textField: 'text',
	    			selectBoxHeight:200,
	    			selectBoxWidth:99,
	    			autocomplete:true,
	    			hideOnLoseFocus:true,
	    			onSelected : function(data) {
					if (data != null && typeof (data) != "undefined"
							&& data != '') {
						setSelUnitData(liger.get("category").getValue()  , $("#selAreaid").val());
					}
				}		
           	});

            $("#unit").ligerComboBox({
			//url:"ipuse_searchUnit.action",
			valueFiled:'id',
			valueFieldID: 'selUnitid',
   			textField: 'text',
   			selectBoxHeight:200,
   			selectBoxWidth:99,
   			autocomplete:true,
   			hideOnLoseFocus:true	
         	});
	           	
             $("#INSTRU_1TN").ligerComboBox({
          	    url:"ipuse_searchInstru_1tn1.action",
				valueFiled:'id',
				valueFieldID: 'SBDLid',
	   			textField: 'text',
	   			selectBoxHeight:200,
	   			selectBoxWidth:99,
	   			autocomplete:true,
	   			hideOnLoseFocus:true,
	   			onSelected : function(data) {
					if (data != null && typeof (data) != "undefined" && data != '') {
						setSelShebeiNameData(liger.get("category").getValue(),$("#SBDLid").val());
					}
				}		
           	});
               $("#shebei_name").ligerComboBox({
					url:"ipuse_searchShebei.action",
					valueFiled:'id',
					valueFieldID: 'shebeiid',
	    			textField: 'text',
	    			selectBoxHeight:200,
	    			selectBoxWidth:99,
	    			autocomplete:true,
	    			hideOnLoseFocus:true	
           	});
            		$("#is_used").ligerComboBox({
            			//valueField: 'id',
            			//valueFieldID: 'is_usedid',
            			//textField: 'text',
            			selectBoxHeight:150,
            			selectBoxWidth:100,
            			width: 120,
            			autocomplete:true,
            			hideOnLoseFocus:true,
            			data:[  { text: '没有分配', id: '0' },
	               	            { text: '正式在用', id: '1' },
	               	            { text: '正式分配', id: '2' },
	               	            { text: '临时分配', id: '3' },
	               	            { text: 'IP已经停用', id: '8' }
              	            ]
            		});
            		
            		/*$("#rlastOdate").ligerDateEditor({
             	        format: "yyyy-MM-dd",
      	       	        labelWidth: 100,
      	       	        labelAlign: 'center',
      	       	        cancelable : false
                 	         });*/
            		
            		$("#txtDate").ligerDateEditor({
            			format: "yyyy-MM-dd hh:mm",
            			showTime: true,
      	       	        labelWidth: 100,
      	       	        labelAlign: 'center',
      	       	        cancelable : false
                 	         });
            		$("#txtDate1").ligerDateEditor({
            			format: "yyyy-MM-dd hh:mm",
            			showTime: true,
      	       	        labelWidth: 100,
      	       	        labelAlign: 'center',
      	       	        cancelable : false
                 	         });
				//编辑区 所属对象类型、区块名称、设备名称、驱动程序采用下拉式输入并且允许用户手动输入
     			   $("#category1").ligerComboBox({
     	       			url:"ipuse_searchCategory.action",
     	       			valueField: 'id',
     	       			valueFieldID: 'catid',
     	       			textField: 'text',
     	       			selectBoxHeight:200,
     	       			selectBoxWidth:99,
     	       			//autocomplete:true,
     	       			hideOnLoseFocus:true
     	       			/*onBeforeSelect : function(newvalue) {
    						liger.get("boilersName").setValue('');
    					},*/
    					
     	       		});
     			  //区块名称
     	             $("#area1").ligerComboBox({
     						url:"ipuse_searchArea.action",
     						valueFiled:'id',
     						valueFieldID: 'arid',
     		    			textField: 'text',
     		    			selectBoxHeight:200,
     		    			selectBoxWidth:99,
     		    			//autocomplete:true,
     		    			hideOnLoseFocus:true,
     		    			onBeforeSelect : function(newvalue) {
        						//alert(liger.get("category1").getValue()) ;
        					},
     		    			onSelected : function(data) {
        						if (data != null && typeof (data) != "undefined"
        								&& data != '') {
        							setUnitData(liger.get("category1").getValue()  , $("#arid").val());
        						}
        					}	
     	           	});
     	              $("#unit1").ligerComboBox({
   						//url:"ipuse_searchUnit.action",
   						valueFiled:'id',
   						valueFieldID: 'unitid',
   		    			textField: 'text',
   		    			selectBoxHeight:200,
   		    			selectBoxWidth:99,
   		    			autocomplete:true,
   		    			hideOnLoseFocus:true	
   	           	});
     	   	           	//设备大类名:
     	               $("#instru_1tn1").ligerComboBox({
     						url:"ipuse_searchInstru_1tn1.action",
     						valueFiled:'id',
     						valueFieldID: 'instruid',
     		    			textField: 'text',
     		    			selectBoxHeight:200,
     		    			selectBoxWidth:99,
     		    			autocomplete:true,
     		    			hideOnLoseFocus:true,
     		    			onSelected : function(data) {
        						if (data != null && typeof (data) != "undefined" && data != '') {
        							setShebeiNameData(liger.get("category1").getValue(),$("#instruid").val());
        						}
        					}	
     	           	});
     	               $("#instrumentation_name1").ligerComboBox({
     						url:"ipuse_searchShebei.action",
     						valueFiled:'id',
     						valueFieldID: 'shid',
     		    			textField: 'text',
     		    			selectBoxHeight:200,
     		    			selectBoxWidth:99,
     		    			autocomplete:true,
     		    			hideOnLoseFocus:true	
     	           	});
            		  //使用网口序列号
            		$("#IP_NO1").ligerComboBox({
            			 hideOnLoseFocus:true,
            			 alwayShowInTop:true,
 	 					 autocomplete:true,
 	 		                data: [
 	 		                    { text: '1', id: '1' },
 	 		                    { text: '2', id: '2' },
 	 		                	{ text: '3', id: '3' },
 	 		                    { text: '4', id: '4' }
 	 		                    
 	 		                ], valueFieldID: 'NOId',
 	 		              initText :'1'
                		})
     	            $("#device1").ligerComboBox({
   						url:"ipuse_searchDevice.action",
   						valueFiled:'id',
   						valueFieldID: 'device1id',
   		    			textField: 'text',
   		    			selectBoxHeight:200,
   		    			selectBoxWidth:99,
   		    			autocomplete:true,
   		    			hideOnLoseFocus:true	
   	           	});
            		$("#is_used1").ligerComboBox({
            			//valueField: 'id',
            			//valueFieldID: 'is_usedid',
            			//textField: 'text',
            			selectBoxHeight:150,
            			selectBoxWidth:100,
            			width: 120,
            			autocomplete:true,
            			hideOnLoseFocus:true,
            			data:[  { text: '没有分配', id: '0' },
                   	            { text: '正式在用', id: '1' },
                   	            { text: '正式分配', id: '2' },
                   	            { text: '临时分配', id: '3' },
                   	            { text: 'IP已经停用', id: '8' }
              	            ],
              	      	onSelected:function (data){
    					if (data === '8') {
    						$("#stop_date1").ligerGetDateEditorManager().setValue('');
    						$("#stop_date1").ligerGetDateEditorManager().setEnabled();
    					}else {
    						$("#stop_date1").ligerGetDateEditorManager().setValue('');
    						$("#stop_date1").ligerGetDateEditorManager().setDisabled(); 
    					}
    				}
            		});
            		
       	           $("#start_date1").ligerDateEditor({
       	           	format: "yyyy-MM-dd",
       	         //label: '格式化日期',
	       	        labelWidth: 100,
	       	        labelAlign: 'center',
	       	        cancelable : false
           	         });
	       	      	$("#stop_date1").ligerDateEditor({
	       	     	format: "yyyy-MM-dd",
       	         //label: '格式化日期',
	       	        labelWidth: 100,
	       	        labelAlign: 'center',
	       	        cancelable : false
           	       });
       	           	//使用状态
            		
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
              	     IP  = $("#ip1").val();
              	   	segment_code2  = $("#segment_code21").val();
              	  is_used  = $("#is_used1").val();
		          			 category  = $("#category1").val();
		 	          		var categoryID = "";
		 	          		if($("#category1").val() !=''){
		 	          			categoryID=	$("#catid").val();
		 		          		}
		 	          		 area  = $("#area1").val();
		 	          		 unit  = $("#unit1").val();
		 	          		var  unitOrgId="";
		 	          		 if($("#unit1").val() !=''){
		 	          			unitOrgId = $("#unitid").val();
		 		          		 }
		 	          		 instrumentation_name  = $("#instrumentation_name1").val();
		 	          		var INSTRU_CLC ="";
		 	          		 if($("#instrumentation_name1").val() !=''){
		 	          			INSTRU_CLC = $("#shid").val();
		 		          		 }
		 	          		 IP_NO  = $("#IP_NO1").val();
		 	          		 device  = $("#device1").val();
		 	          		 interface2th  = $("#interface2th1").val();
		 	          		
		 	          		
		 	          		 start_date  = $("#start_date1").val();
		 	          		 stop_date  = $("#stop_date1").val();
		 	          		 remark  = $("#remark1").val();
		 	          		 VPN_IP  = $("#VPN_IP1").val();
			          		
		 	          		if(is_used == '没有分配'){
								$.ligerDialog.confirm('使用状态为没有分配确定要保存么,保存会清空相应的数据信息。', function (yes){
									 if(yes){
									  //判断修改还是添加操作 1-添加、2-修改
					              	   if(operate == 1){
					              	  		
					              	   	   jQuery.ajax({
														type : 'post',
														url : 'ipuse_addIpUsed.action',
														async : false,
														data: {"IP":IP,"category":category ,"area":area,"unit":unit ,"instrumentation_name":instrumentation_name,
																"IP_NO":IP_NO,"device":device,"interface2th":interface2th,"segment_code2":segment_code2,"is_used":is_used,"start_date":start_date,
																"start_date":start_date,"stop_date":stop_date,"remark":remark,"VPN_IP":VPN_IP,"unitOrgId":unitOrgId,
																"categoryID":categoryID,"INSTRU_CLC":INSTRU_CLC},
														success : function(data) { 
														//$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
														if (data != null && typeof(data)!="undefined"){
															var outData = eval('(' + data + ')');
															if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
																$.ligerDialog.error(outData.ERRMSG);
															}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
																$.ligerDialog.error(outerror(outData.ERRCODE));
															}else{
																onSubmit();
																setpage1(1); //隐藏编辑界面
																setItemsd(2); //去掉隐藏，显示按钮
																$.ligerDialog.success('Ip使用表添加成功！');
																operate = 0;
															}
															}
															//}
														},
														error : function(data) {
													
														}
													});
					              	   
					              	   //2-修改
					              	   }else if(operate == 2){
					              		 jQuery.ajax({
												type : 'post',
												url : 'ipuse_updateIpUsed.action',
												async : false,
												data: {"IP":IP,"category":category ,"area":area,"unit":unit ,"instrumentation_name":instrumentation_name,
													"IP_NO":IP_NO,"device":device,"interface2th":interface2th,"segment_code2":segment_code2,"is_used":is_used,"start_date":start_date,
													"start_date":start_date,"stop_date":stop_date,"remark":remark,"VPN_IP":VPN_IP,"unitOrgId":unitOrgId,
													"categoryID":categoryID,"INSTRU_CLC":INSTRU_CLC},
													success : function(data) { 
												$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else{
														onSubmit();
														setpage1(1); //隐藏编辑界面
														setItemsd(2); //去掉隐藏，显示按钮
														$.ligerDialog.success('Ip使用表修改成功！');
														operate = 0;
														
													}
													}
												},
												error : function(data) {
											
												}
											});
					              	   
					              	   }

				                     }
		 	          		})
		          // operate = 0;
                }else{
                	 if(operate == 1){
	              	  		
	              	   	   jQuery.ajax({
										type : 'post',
										url : 'ipuse_addIpUsed.action',
										async : false,
										data: {"IP":IP,"category":category ,"area":area,"unit":unit ,"instrumentation_name":instrumentation_name,
												"IP_NO":IP_NO,"device":device,"interface2th":interface2th,"segment_code2":segment_code2,"is_used":is_used,"start_date":start_date,
												"start_date":start_date,"stop_date":stop_date,"remark":remark,"VPN_IP":VPN_IP,"unitOrgId":unitOrgId,
												"categoryID":categoryID,"INSTRU_CLC":INSTRU_CLC},
										success : function(data) { 
										//$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
										if (data != null && typeof(data)!="undefined"){
											var outData = eval('(' + data + ')');
											if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
												$.ligerDialog.error(outData.ERRMSG);
											}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
												$.ligerDialog.error(outerror(outData.ERRCODE));
											}else{
												onSubmit();
												setpage1(1); //隐藏编辑界面
												setItemsd(2); //去掉隐藏，显示按钮
												$.ligerDialog.success('Ip使用表添加成功！');
												operate = 0;
											}
											}
											//}
										},
										error : function(data) {
									
										}
									});
	              	   
	              	   //2-修改
	              	   }else if(operate == 2){
	              		 jQuery.ajax({
								type : 'post',
								url : 'ipuse_updateIpUsed.action',
								async : false,
								data: {"IP":IP,"category":category ,"area":area,"unit":unit ,"instrumentation_name":instrumentation_name,
									"IP_NO":IP_NO,"device":device,"interface2th":interface2th,"segment_code2":segment_code2,"is_used":is_used,"start_date":start_date,
									"start_date":start_date,"stop_date":stop_date,"remark":remark,"VPN_IP":VPN_IP,"unitOrgId":unitOrgId,
									"categoryID":categoryID,"INSTRU_CLC":INSTRU_CLC},
									success : function(data) { 
								$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
								if (data != null && typeof(data)!="undefined"){
									var outData = eval('(' + data + ')');
									if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
										$.ligerDialog.error(outData.ERRMSG);
									}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
										$.ligerDialog.error(outerror(outData.ERRCODE));
									}else{
										onSubmit();
										setpage1(1); //隐藏编辑界面
										setItemsd(2); //去掉隐藏，显示按钮
										$.ligerDialog.success('Ip使用表修改成功！');
										operate = 0;
										
									}
									}
								},
								error : function(data) {
							
								}
							});
	              	   
	              	   }

                    }
        }
      });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
        					
									if (rowdata.ip != null && typeof(rowdata.ip)!="undefined"){
										IP = rowdata.ip;
									 }else{
										 IP = "";
									 }
									 if (rowdata.category != null && typeof(rowdata.category)!="undefined"){
										 category = rowdata.category;
									 }else{
										 category = "";
									 }
									if (rowdata.area != null && typeof(rowdata.area)!="undefined"){
										area = rowdata.area;
									 }else{
										 area = "";
									 }
									if (rowdata.unit != null && typeof(rowdata.unit)!="undefined"){
										unit = rowdata.unit;
										
									 }else{
										 unit = "";
									 }
									if (rowdata.instrumentation_name != null && typeof(rowdata.instrumentation_name)!="undefined"){
										instrumentation_name = rowdata.instrumentation_name;
									 }else{
										 instrumentation_name = "";
									 }
									
									if (rowdata.ip_no != null && typeof(rowdata.ip_no)!="undefined"){
										IP_NO = rowdata.ip_no;
									 }else{
										 IP_NO = "";
									 }
									
									if (rowdata.device != null && typeof(rowdata.device)!="undefined"){
										device = rowdata.device;
									 }else{
										 device = "";
									 }
									if (rowdata.interface2th != null && typeof(rowdata.interface2th)!="undefined"){
										interface2th = rowdata.interface2th;
									 }else{
										 interface2th = "";
									 }
									if (rowdata.segment_code2 != null && typeof(rowdata.segment_code2)!="undefined"){
										segment_code2 = rowdata.segment_code2;
									 }else{
										 segment_code2 = "";
									 }
									if (rowdata.is_used != null && typeof(rowdata.is_used)!="undefined"){
										is_used = rowdata.is_used;
									 }else{
										 is_used = "";
									 }
									if (rowdata.start_date != null && typeof(rowdata.start_date)!="undefined"){
										start_date = rowdata.start_date;
									 }else{
										 start_date = "";
									 }
									 if (rowdata.stop_date != null && typeof(rowdata.stop_date)!="undefined"){
										 stop_date = rowdata.stop_date;
										 }else{
											 stop_date = "";
										 }
									if (rowdata.remark != null && typeof(rowdata.remark)!="undefined"){
										remark = rowdata.remark;
									 }else{
										 remark = "";
									 }
									if (rowdata.vpn_ip != null && typeof(rowdata.vpn_ip)!="undefined"){
										VPN_IP = rowdata.vpn_ip;
									 }else{
										 VPN_IP= "";
									 }
									if (rowdata.instru_1tc != null && typeof(rowdata.instru_1tc)!="undefined"){
										instru_1tc = rowdata.instru_1tc;
									 }else{
										 instru_1tc= "";
									 }
									if (rowdata.object_code != null && typeof(rowdata.object_code)!="undefined"){
										object_code = rowdata.object_code;
									 }else{
										 object_code= "";
									 }
									if (rowdata.qkid != null && typeof(rowdata.qkid)!="undefined"){
										qkid = rowdata.qkid;
									 }else{
										 qkid= "";
									 }
									if (rowdata.org_id != null && typeof(rowdata.org_id)!="undefined"){
										org_id = rowdata.org_id;
									 }else{
										 org_id= "";
									 }
									
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }
    	//为公用页面提供接口方法 
		
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'category',
					value: $("#category").val()
				},{
					name: 'area',
					value: $("#area").val()
				},{
					name: 'unit',
					value: $("#unit").val()
				},{
					name :'INSTRU_1TN',
					value:$("#INSTRU_1TN").val()
				},{
					name: 'shebei_name',
					value: $("#shebei_name").val()
				},{name :'ip',
					value:$("#ip").val()
				},{
					name:'code2',
					value:$("#code2").val()
				},{
					name:'is_used',
					value:$("#is_used").val()
				},{
					name :'rlastOdate',
					value :$("#rlastOdate").val()
				},{
					name :'txtDate',
					value :$("#txtDate").val()
				},{
					name :'txtDate1',
					value :$("#txtDate1").val()
					}
				]
        	});
         	grid.loadData(true);
        }
         var exportFlag=false;
    	 function onExport() {
    		 //oilationname  blockstationname  boilersName  jrbz1
    		     var Eoilationname=$("#category").val();
     	         var Eblockstationname=$("#area").val();
     	         var EboilersName=$("#unit").val();
     	         var Eareablock = $("#shebei_name").val();
     	         var Ejrbz1=$("#ip").val();	
     	         var EdyearC = $("#code2").val();
     	         var Edisused = $("#is_used").val();
     	        
     	         var Einstru = $("#INSTRU_1TN").val();
     	         var ErlastOdate = $("#rlastOdate").val();

    			if (exportFlag) {
    				Eoilationname=category;
    				Eblockstationname = area
    				EboilersName= unit;
    				Eareablock = shebei_name
    				Ejrbz1=ip;
    				EdyearC=code2;
    				Edisused = is_used;
    				Einstru =INSTRU_1TN;
    				ErlastOdate =rlastOdate;
    			}
    			var totalNum = 0;
    			
    			var url = 'ipuse_searchDatas.action?category='+encodeURIComponent(Eoilationname)+'&area='+encodeURIComponent(Eblockstationname)+'&unit='+encodeURIComponent(EboilersName)+'&shebei_name='+encodeURIComponent(Eareablock)+'&ip='+encodeURIComponent(Ejrbz1)+'&code2='+encodeURIComponent(EdyearC)+'&totalNum=export'+'&is_used='+encodeURIComponent(Edisused)+'&INSTRU_1TN='+encodeURIComponent(Einstru)+'&rlastOdate='+encodeURIComponent(ErlastOdate);
    			var urls = 'ipuse_searchDatas.action?category='+encodeURIComponent(Eoilationname)+'&area='+encodeURIComponent(Eblockstationname)+'&unit='+encodeURIComponent(EboilersName)+'&shebei_name='+encodeURIComponent(Eareablock)+'&ip='+encodeURIComponent(Ejrbz1)+'&code2='+encodeURIComponent(EdyearC)+'&totalNum=totalNum'+'&is_used='+encodeURIComponent(Edisused)+'&INSTRU_1TN='+encodeURIComponent(Einstru)+'&rlastOdate='+encodeURIComponent(ErlastOdate);
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
      function assignM(flg){
			 if(flg == 2){
					//$("#wellAreaSelf").ligerGetComboBoxManager().selectValue(jqkid);
					document.getElementById("ip1").value = IP;
			 		//document.getElementById("category1").value = category;
			 		$("#category1").ligerGetComboBoxManager().selectValue(object_code);
			 		//document.getElementById("area1").value = area;
			 		$("#area1").ligerGetComboBoxManager().selectValue(qkid);
			 		
			 		document.getElementById("unit1").value = unit;
			 		
			 		//$("#unit1").ligerGetComboBoxManager().selectValue(org_id);
			 		document.getElementById("instrumentation_name1").value = instrumentation_name;
			 		if($("#IP_NO1").val() !=""){
			 			document.getElementById("IP_NO1").value = IP_NO;
			 		}else{
			 			document.getElementById("IP_NO1").value = "1";
				 		 }
			 		document.getElementById("device1").value = device;
			 		document.getElementById("interface2th1").value = interface2th;
			 		document.getElementById("segment_code21").value = segment_code2;
			 		document.getElementById("is_used1").value = is_used;
			 		document.getElementById("start_date1").value = start_date;
			 		document.getElementById("stop_date1").value = stop_date;
			 		document.getElementById("VPN_IP1").value = VPN_IP;
			 		document.getElementById("remark1").value = remark;
			 		$("#instru_1tn1").ligerGetComboBoxManager().selectValue(instru_1tc);
			 		
	            	
	             	
			 }else if(flg == 1){
				// $("#wellAreaSelf").ligerGetComboBoxManager().selectValue("");
			 		document.getElementById("ip1").value = "";
			 		document.getElementById("category1").value = "";
			 		//document.getElementById("area1").value = "";
			 		$("#area1").ligerGetComboBoxManager().selectValue("");
			 		document.getElementById("unit1").value = "";
			 		document.getElementById("instrumentation_name1").value = "";
			 		document.getElementById("IP_NO1").value = "";
			 		document.getElementById("device1").value = "";
			 		document.getElementById("interface2th1").value = "";
			 		document.getElementById("segment_code21").value = "";
			 		document.getElementById("is_used1").value = "";
			 		document.getElementById("start_date1").value = "";
			 		document.getElementById("stop_date1").value = "";
			 		document.getElementById("VPN_IP1").value = "";
			 		document.getElementById("remark1").value = "";
			 		$("#instru_1tn1").ligerGetComboBoxManager().selectValue("");
	               	
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
         	              	setItemsd(0);//0-显示编辑区，添加隐藏按钮
         	              }
		              	   operate = 2;
		              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改信息的数据！');
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
      //查询
      function setSelUnitData(catid, arid) {
    	// var  catida = document.getElementById("category1").value();
    	 // alert(catida)
    		jQuery.ajax( {
    			type : 'post',
    			url : 'ipuse_searchOnChangeUnit.action',
    			data : {
    				"catid" : catid,
    				"arid"	:arid
    			},
    			success : function(jsondata) {
    				if (jsondata != null && typeof (jsondata) != "undefined"
    						&& jsondata != 0) {
    					liger.get("unit")
    							.setData(eval('(' + jsondata + ')'));
    				} else {
    					liger.get("unit").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    			}
    		});
    	}
      function setSelShebeiNameData(catid,shid) {
    	// var  catida = document.getElementById("category1").value();
    	 // alert(catida)
    		jQuery.ajax( {
    			type : 'post',
    			url : 'ipuse_searchOnChangeSB.action',
    			data : {
    				"catid" : catid,
    				"shid" : shid
    			},
    			success : function(jsondata) {
    				if (jsondata != null && typeof (jsondata) != "undefined" && jsondata != 0) {
    					liger.get("shebei_name").setData(eval('(' + jsondata + ')'));
    				} else {
    					liger.get("shebei_name").setData('');
    				}
    			},
    			error : function(jsondata) {
    			}
    		});
    	}
  	
      //编辑
      function setUnitData(catid, arid) {
    	// var  catida = document.getElementById("category1").value();
    	 // alert(catida)
    		jQuery.ajax( {
    			type : 'post',
    			url : 'ipuse_searchOnChangeUnit.action',
    			data : {
    				"catid" : catid,
    				"arid"	:arid
    			},
    			success : function(jsondata) {
    				if (jsondata != null && typeof (jsondata) != "undefined"
    						&& jsondata != 0) {
    					liger.get("unit1")
    							.setData(eval('(' + jsondata + ')'));
    				} else {
    					liger.get("unit1").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    			}
    		});
    	}
      function setShebeiNameData(catid,shid) {
    	// var  catida = document.getElementById("category1").value();
    	 // alert(catida)
    		jQuery.ajax( {
    			type : 'post',
    			url : 'ipuse_searchOnChangeSB.action',
    			data : {
    				"catid" : catid,
    				"shid" : shid
    			},
    			success : function(jsondata) {
    				if (jsondata != null && typeof (jsondata) != "undefined"
    						&& jsondata != 0) {
    					liger.get("instrumentation_name1").setData(eval('(' + jsondata + ')'));
    				} else {
    					liger.get("instrumentation_name1").setData('');
    				}
    			},
    			error : function(jsondata) {
    			}
    		});
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
	    	<input type="hidden" id="gh_id1"  name="gh_id1"/>
	    	<input type="hidden" id="datatype" name="datatype"/>
	          <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
		               <td align="right" class="l-table-edit-td" style="width:120px">所属对象类型：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="category" name = "category"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">区块名称：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="area" name = "area"/>
		                </td>
		                <td align="left">
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:80px">所属对象：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="unit" name = "unit"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:100px">设备大类名：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="INSTRU_1TN" name = "INSTRU_1TN"/>
		                </td>
		                <td align="left">
		                  </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">设备名称：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="shebei_name" name = "shebei_name"/>
		                </td>
		                <td align="left">
		                  </td>
		              
		              
		                <td align="right" class="l-table-edit-td" style="width:40px">IP：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="ip" name = "ip"/>
		                </td>
		                <td align="left">
		                </td>
		                </tr>
		                <tr>
		                <td align="right" class="l-table-edit-td" style="width:100px">二分段代码：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="code2" name = "code2"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">使用状态：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="is_used" name = "is_used"/>
		                </td>
		                <td align="left">
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:100px">产能设计年：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="rlastOdate" name = "rlastOdate"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:100px">上次操作日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td valign="middle">--</td>
						<td><input type="text" id="txtDate1" ltype="date"/></td>
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td"  >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                 <td align="left" class="l-table-edit-td"  >
		                	<a href="javascript:onExport()" class="l-button" style="width:80px">导出报表</a>
		                </td>
					</tr>
				
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="" style="width:100%;height: 100px; display:none;">
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">IP:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属对象类型:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">区块名称:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属对象:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">设备大类名:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">设备名称:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">使用网口序号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">驱动程序:</td>
		                
		               
		                
		              
		                
		                <tr/>
		                <tr>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="ip1" type="text" id="ip1"  ltype="text" readonly="readonly" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="category1" type="text" id="category1" ltype="text" validate="{required:true}" />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="area1" type="text" id="area1"  ltype="text" validate="{required:true}"  />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="unit1" type="text" id="unit1"  ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="instru_1tn1" type="text" id="instru_1tn1" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="instrumentation_name1" type="text" id="instrumentation_name1" ltype="text" />
		                </td>
		            	 
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="IP_NO1" type="text" id="IP_NO1" ltype="text"  />
		                </td>
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="device1" type="text" id="device1" ltype="text" />
		                </td>
		               
		              
		                
		              
		                </tr>
		                <tr>
		                  <td align="left" class="l-table-edit-td" style="width:150px">二级接口:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属二分段代码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">使用状态:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">开始使用日期:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">停用日期:</td>
		                  	<td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">VPN使用IP地址:</td>
		            </tr>
		            <tr>
		             
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="interface2th1" type="text" id="interface2th1" ltype="text"  />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="segment_code21" type="text" id="segment_code21" ltype="text"  readonly="readonly" />
		                </td>
		               
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="is_used1" type="text" id="is_used1" ltype="text"  />
		                </td>
		            
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="start_date1" type="text" id="start_date1" ltype="date" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="stop_date1" type="text" id="stop_date1" ltype="date"  />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark1" type="text" id="remark1" ltype="text"  />
		                	
		                </td>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="VPN_IP1" type="text" id="VPN_IP1"ltype="text"  />
		                </td>
		               </tr>
		            
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>