<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>阀池基础信息</title>
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
        
        var  clique_pool_name;
		var  code;
		var  clique_type;
		
		var  rtu_code;
		var  install_date;
		var  remark;
		//var  prod_id;
		var  jrbz_id;
		var  ljjd_id;
		var  station_id;
		var  org_id;
		var  clique_id;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'fcjcxx_pageInit.action',
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
				//url:'searchQueryAll_searchALLCYZ.action',
	            valueField: 'id',
				valueFieldID: 'oil_stationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onSelected:function (data){
					if ($("#oil_stationid").val() != 0 && $("#oil_stationid").val() != 1 && data != null && typeof(data)!="oil_stationid" && data != '') {
						//liger.get("blockstationname").setValue('');
						//liger.get("cliquepool").setValue('');
						setTeamData(data,proData);
						setWellData(data,'',proData);
						//liger.get("team_station").setData(wellDatas);
					}
					else {
						getWellInitData();
					}
				}
			});
			$("#blockstationname").ligerComboBox({
				//url:'searchQueryAll_searchTeam.action',
				valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						liger.get("cliquepool").setValue('');
						setWellData('',$("#stationid").val(),proData);
					}
				}
			});
			$("#cliquepool").ligerComboBox({
				//url:'searchQueryAll_searchCYZTEAMJH.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:150,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
				
				});
		
			getWellInitData();
			
			$("#cliquetype").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'clique_type_id',
				textField: 'text',
				data: [
	                    { text: '油', id: '01' },
	                    { text: '气', id: '02' },
	                    { text: '水', id: '03' }
	                ],
				selectBoxHeight:100,
				width: 120,
				autocomplete:true,
				hideOnLoseFocus:true
			});
			
			
			
			$("#CLIQUE_TYPE").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'cliquetype_id',
				textField: 'text',
				data: [
	                    { text: '油', id: '01' },
	                    { text: '气', id: '02' },
	                    { text: '水', id: '03' }
	                ],
				selectBoxHeight:100,
				width: 120,
				autocomplete:true,
				hideOnLoseFocus:true
			});
	
			$("#STATION_NAME").ligerComboBox({
				url:'rulewell_queryStationInfo.action?oilid=alls',
	            valueField: 'id',
				valueFieldID: 'zhzid',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true
			});
		  $("#JRBZ").ligerComboBox({
			  url:'comboboxdata_searchSwitchInflg.action', 
	   			valueField: 'id',
	   			valueFieldID: 'hiid',
	   			textField: 'text',
	   			selectBoxHeight:150,
	   			selectBoxWidth:100,
	   			width: 120,
	   			autocomplete:true,
	   			hideOnLoseFocus:true
	            });
	
			  $("#LJJD_S").ligerComboBox({
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
			 $("#INSTALL_DATE").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
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
              	   
              	   	var CLIQUE_POOL_NAME  = $("#CLIQUE_POOL_NAME").val();
					var CODE  = $("#CODE").val();
					var CLIQUE_TYPE ="";
					
					if( $("#CLIQUE_TYPE").val() != ''){
						CLIQUE_TYPE  = $("#cliquetype_id").val();
					}
					
					var STATION_NAME = "";
					if( $("#STATION_NAME").val() != ''){
						STATION_NAME = $("#zhzid").val();
					}
					var RTU_CODE  = $("#RTU_CODE").val();
				
					var LJJD_S  = $("#LJJD_S").val();
					if(LJJD_S != ''){
						LJJD_S =  $("#hidserverid").val() ;
					}
					var JRBZ  ="";
					if($("#JRBZ").val() !=''){
						var JRBZ = $("#hiid").val();
						}
					
					var INSTALL_DATE  = $("#INSTALL_DATE").val();
					
					var remark  = $("#remark").val();
					var dyear = $("#dyear").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	  
              	   if(operate == 1){ 
              		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'fcjcxx_addOrupdateData.action',
									async : false,
									data: {
										   "CLIQUE_POOL_NAME":CLIQUE_POOL_NAME,
										   "CODE":CODE ,
											"CLIQUE_TYPE":CLIQUE_TYPE,
											"STATION_NAME":STATION_NAME ,
											"RTU_CODE":RTU_CODE,
											"LJJD_S":LJJD_S,
											"JRBZ":JRBZ,
											"INSTALL_DATE":INSTALL_DATE,
											"remark":remark},
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
									url : 'fcjcxx_addOrupdateData.action',
									async : false,
									data: {"org_id":org_id,
										   "clique_id":clique_id ,
										   "CLIQUE_POOL_NAME":CLIQUE_POOL_NAME,
										   "CODE":CODE ,
											"CLIQUE_TYPE":CLIQUE_TYPE,
											"STATION_NAME":STATION_NAME ,
											"RTU_CODE":RTU_CODE,
											"LJJD_S":LJJD_S,
											"JRBZ":JRBZ,
											"INSTALL_DATE":INSTALL_DATE,
											"remark":remark},
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

			function setTeamData(stationid,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'searchQueryAll_searchTeam.action?arg=CLIQUE',
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
    			url:'searchQueryAll_searchCYZTEAMJH.action?arg=CLIQUE ',
    			data: {"CYZ":cyz,"TEAM":team},
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("cliquepool").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("cliquepool").setData(proData);
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
    			url : 'searchQueryAll_searchALLCYZ.action?arg=CLIQUE',
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
    						//wellDatas = val;
    					}
    				});
    				setInitData(oilStationtext,teamStationtext,wellstationtext);
    			}
    		});
    	}
    	
        function setInitData(oilStationtext,teamStationtext,wellstationtext) {
    		liger.get("oilstationname").setData(oilStationtext);
    		liger.get("blockstationname").setData(teamStationtext);
    		liger.get("cliquepool").setData(wellstationtext);
    	}
	
       	 function fromAu(rowdata){
        	//用户选择修改数据
    				clique_id = rowdata.CLIQUE_ID;
    				org_id = rowdata.ORG_ID;
    				clique_pool_name = rowdata.CLIQUE_POOL_NAME;
    				
    				if (rowdata.CODE != null && typeof(rowdata.CODE)!="undefined"){
					 	code = rowdata.CODE; 
					 }else{
					 	code = "";
					 }
					
					if (rowdata.CLIQUE_TYPE != null && typeof(rowdata.CLIQUE_TYPE)!="undefined"){
					 	clique_type = rowdata.CLIQUE_TYPE;
					 }else{
					 	clique_type = "";
					 }
					
					if (rowdata.RTU_CODE != null && typeof(rowdata.RTU_CODE)!="undefined"){
					 	rtu_code = rowdata.RTU_CODE;
					 }else{
					 	rtu_code = "";
					 }
					
					if (rowdata.INSTALL_DATE != null && typeof(rowdata.INSTALL_DATE)!="undefined"){
					 	install_date = rowdata.INSTALL_DATE;
					 }else{
					 	install_date = "";
					 }
					
					if (rowdata.STATION_ID != null && typeof(rowdata.STATION_ID)!="undefined"){
					 	station_id = rowdata.STATION_ID;
					 }else{
					 	station_id = "";
					 }
					
					 //prod_id  = rowdata.PROD_ID;
					 jrbz_id = rowdata.JRBZ_ID;
					 ljjd_id = rowdata.LJJD_ID;
					
					if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
					 	remark = rowdata.REMARK; 
					 }else{
					 	remark = "";
					 }
						
                	if(operate == 2){
                		assignM(2);
                	}
       		 }
        
        //为公用页面提供接口方法 
		 //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			
			var oilstationname='';
			var blockstationname='';
			var cliquepool='';
			
			if(datatype=='4'){
				oilstationname=name;
				$("#oilstationname").val(name);
				document.getElementById("blockstationname").value="";
				document.getElementById("cliquepool").value="";
			}
			
			if(datatype=='7'){
				blockstationname=name;
				$("#blockstationname").val(name);
				document.getElementById("oilstationname").value="";
				document.getElementById("cliquepool").value="";
			}
			if(datatype=='22'){
				cliquepool=name;
				$("#cliquepool").val(cliquepool);
				document.getElementById("oilstationname").value="";
				document.getElementById("blockstationname").value="";
			}
			
			grid.setOptions({
        		parms: [{
					name: 'oilstationname',
					value: $("#oilstationname").val()
				},{
					name: 'blockstationname',
					value: $("#blockstationname").val()
				},{
					name: 'cliquepool',
					value: $("#cliquepool").val()
				},{
					name: 'cliquetype',
					value: $("#cliquetype").val()
				}
				]
        	});
         	grid.loadData(true);
		}
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'oilstationname',
					value: $("#oilstationname").val()
				},{
					name: 'blockstationname',
					value: $("#blockstationname").val()
				},{
					name: 'cliquepool',
					value: $("#cliquepool").val()
				},{
					name: 'cliquetype',
					value: $("#cliquetype").val()
				}
				]
        	});
         	grid.loadData(true);
        }
        
        
       function onExport() {
   		var oilstationname=$("#oilstationname").val();
		var blockstationname=$("#blockstationname").val()+'';
		var cliquepool=$("#cliquepool").val();
		var cliquetype=$("#cliquetype").val();
	
  		var totalNum = 0;
  		var url = "fcjcxx_searchDatas.action?oilstationname="+encodeURIComponent(oilstationname)+"&blockstationname="+encodeURIComponent(blockstationname)+"&cliquepool="+encodeURIComponent(cliquepool)+"&cliquetype="+encodeURIComponent(cliquetype)+"&totalNum=export";
  		var urls = "fcjcxx_searchDatas.action?oilstationname="+encodeURIComponent(oilstationname)+"&blockstationname="+encodeURIComponent(blockstationname)+"&cliquepool="+encodeURIComponent(cliquepool)+"&cliquetype="+encodeURIComponent(cliquetype)+"&totalNum=totalNum";
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
			 		document.getElementById("CLIQUE_POOL_NAME").value = clique_pool_name;
	               	document.getElementById("CODE").value = code;
	               	var ctypeid = $("#CLIQUE_TYPE").ligerGetComboBoxManager().findValueByText(clique_type);
	               	$("#CLIQUE_TYPE").ligerGetComboBoxManager().selectValue(ctypeid);
	               	$("#STATION_NAME").ligerGetComboBoxManager().selectValue(station_id);
	               	document.getElementById("RTU_CODE").value= rtu_code;
	               	$("#LJJD_S").ligerGetComboBoxManager().selectValue(ljjd_id);
	               	$("#JRBZ").ligerGetComboBoxManager().selectValue(jrbz_id);
	              	document.getElementById("INSTALL_DATE").value= install_date;
	               	document.getElementById("remark").value= remark;
	              	
			 }else if(flg == 1){
			 		document.getElementById("CLIQUE_POOL_NAME").value = "";
	               	document.getElementById("CODE").value = "";
	               	$("#CLIQUE_TYPE").ligerGetComboBoxManager().selectValue("");
	               	$("#STATION_NAME").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("RTU_CODE").value= "";
	               	$("#LJJD_S").ligerGetComboBoxManager().selectValue("");
	               	$("#JRBZ").ligerGetComboBoxManager().selectValue("");
	              	document.getElementById("INSTALL_DATE").value= "";
	               	document.getElementById("remark").value= "";
	               	org_id = "";
					clique_id = "";
	               	
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
											url : 'fcjcxx_removeData.action',
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
						<td align="right" class="l-table-edit-td" style="width:60px">采油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname" name = "oilstationname" />
		                </td>
		                
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:60px">注转站：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="blockstationname" name = "blockstationname"/>
		                </td>
		                <td align="left">
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">阀池：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="cliquepool" name = "cliquepool"/>
		                </td>
		              
		                <td align="right" class="l-table-edit-td" style="width:80px">阀池类型：</td>
						<td align="right" class="l-table-edit-td" style="width:80px">
							<input type="text" id="cliquetype" name="cliquetype" />
						</td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td"  >
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
	  
		   <div id="edituser" style="width:99%;height: 100px; display:none;">
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">阀池名称:</td>
		              	<td align="left" class="l-table-edit-td" style="width:150px">阀池编号:</td>
		              	<td align="left" class="l-table-edit-td" style="width:150px">阀池类型:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属注转站:</td>
		             	 <td align="left" class="l-table-edit-td" style="width:150px">RTU编号:</td>
		           	
		           		 <td align="left" class="l-table-edit-td" >服务器逻辑节点:</td>
			                <td align="left" class="l-table-edit-td" >接入标志:</td>
			               	<td align="left" class="l-table-edit-td" >安装日期:</td>
			                <td align="left" class="l-table-edit-td" >备注:</td>
						
		                </tr>
		            	<tr>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="CLIQUE_POOL_NAME" type="text" id="CLIQUE_POOL_NAME"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                 
		                  <td align="left" class="l-table-edit-td" style="">
		                	<input name="CODE" type="text" id="CODE" ltype="text"  />
		               	 </td>
		               	  <td align="left" class="l-table-edit-td" >
		                	<input name="CLIQUE_TYPE" type="text" id="CLIQUE_TYPE" ltype="text" validate="{required:true}" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="STATION_NAME" type="text" id="STATION_NAME" ltype="text"  validate="{required:true}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="RTU_CODE" type="text" id="RTU_CODE" ltype="text"/>
		                </td>
		              
		          
		             	 <td align="left" class="l-table-edit-td" >
		                	<input name="LJJD_S" type="text" id="LJJD_S" ltype="text" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="JRBZ" type="text" id="JRBZ" ltype="text"/>
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="INSTALL_DATE" type="text" id="INSTALL_DATE" ltype="date" />
		                </td>
		               <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text"  />
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