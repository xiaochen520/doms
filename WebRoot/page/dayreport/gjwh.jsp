<%@ page language="java" import="java.util.*,com.echo.dto.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)session.getAttribute("userInfo");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>关井管理</title>
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
        
        var wellname; 
		var downtime_type; 
		var start_datetime; 
		var end_datetime; 
		var key_down_tag; 
		var remark; 

		var check_oper; 

		var report_date; 
		var org_id; 
		var code; 
		var closing_well_id;
		var wellDatas = [{ id: 1 , text: '' }];
		var proData = [{ id: 1 , text: '' }];
		
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'gjwh_pageInit.action',
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
			
			$("#oil_station").ligerComboBox({
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
						liger.get("team_station").setValue('');
						liger.get("well_station").setValue('');
						setTeamData($("#oil_stationid").val(),proData);
						setWellData($("#oil_stationid").val(),'',proData);
						//liger.get("team_station").setData(wellDatas);
					}
					else {
						getWellInitData();
					}
				}
			});
			$("#team_station").ligerComboBox({
				//url:'searchQueryAll_searchTeam.action',
				valueField: 'id',
				valueFieldID: 'teamid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						liger.get("well_station").setValue('');
						setWellData('',$("#teamid").val(),proData);
					}
				}
			});
			$("#well_station").ligerComboBox({
				//url:'searchQueryAll_searchCYZTEAMJH.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
				
				});
				
				 getWellInitData();
				 
				 $("#oil_station").val("<%=user.getCyz()%>");
           		$("#team_station").val("<%=user.getTeam()%>");
				//查询开始结束时间
			    $("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
			
             $("#txtDate1").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
             $("#well_name").ligerComboBox({
				            	url:'searchQueryAll_searchUserWell.action',
				            	isShowCheckBox: false,
								valueField: 'id',
								valueFieldID: 'hidwellid',
								textField: 'text',
								selectBoxWidth: 160,
								selectBoxHeight:350,
								hideOnLoseFocus:true,
	           					autocomplete:true,
								initText :''
				            });
				            
				            
			 $("#downtime_type").ligerComboBox({
					url:'gjwh_searchDOWNTIME_TYPE.action',
					valueField: 'id',
					valueFieldID: 'downtime_code',
					textField: 'text',
					selectBoxHeight:350,
					selectBoxWidth:300,
					hideOnLoseFocus:true,
		            autocomplete:true
				});
				
            
             $("#key_down_tag").ligerComboBox({
             			hideOnLoseFocus:true,
						autocomplete:true,
			                data: [
			                    { text: '否', id: '0' },
			                    { text: '是', id: '1' }
			                ], valueFieldID: 'tagid',
						initText :''
			            }); 
			   $("#start_datetime").ligerDateEditor(
                {
                    format: "yyyy-MM-dd hh:mm",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
           	 });
            
          	  $("#end_datetime").ligerDateEditor(
                {
                    format: "yyyy-MM-dd hh:mm",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
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
              	   	var param_well_name  = $("#well_name").val();
					var param_downtime_type  = $("#downtime_code").val();
					var param_key_down_tag = $("#tagid").val();
					var param_start_datetime  = $("#start_datetime").val();
					var param_end_datetime  = $("#end_datetime").val();
					var param_remark  = $("#remark").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'gjwh_addorUpdateDatas.action',
									async : false,
									data: {"param_well_name":param_well_name ,"param_downtime_type":param_downtime_type,"param_key_down_tag":param_key_down_tag ,
											"param_start_datetime":param_start_datetime,"param_end_datetime":param_end_datetime,"param_remark":param_remark},
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
														$.ligerDialog.success('关井信息添加操作成功！');
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
									url : 'gjwh_addorUpdateDatas.action',
									async : false,
									data: {"param_well_name":param_well_name ,"param_downtime_type":param_downtime_type,"param_key_down_tag":param_key_down_tag ,
											"param_start_datetime":param_start_datetime,"param_end_datetime":param_end_datetime,"param_remark":param_remark,"closing_well_id":closing_well_id},
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
														$.ligerDialog.success('关井信息修改操作成功！');
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
				
					wellname = rowdata.WELL_ID ;
					 
					if (rowdata.DOWNTIME_TYPE != null && typeof(rowdata.DOWNTIME_TYPE)!="undefined"){
					 	downtime_type = rowdata.DOWNTIME_TYPE ; 
					 }else{
					 	downtime_type = "";
					 }
					if (rowdata.START_DATETIME != null && typeof(rowdata.START_DATETIME)!="undefined"){
					 	start_datetime = rowdata.START_DATETIME ; 
					 }else{
					 	start_datetime = "";
					 }
					 
					 if (rowdata.END_DATETIME != null && typeof(rowdata.END_DATETIME)!="undefined"){
					 	end_datetime = rowdata.END_DATETIME ; 
					 }else{
					 	end_datetime = "";
					 }
					 
					 if (rowdata.KEY_DOWN_TAG != null && typeof(rowdata.KEY_DOWN_TAG)!="undefined"){
					 	key_down_tag = rowdata.KEY_DOWN_TAG ; 
					 }else{
					 	key_down_tag = "";
					 }
					
					if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
					 	remark = rowdata.REMARK ; 
					 }else{
					 	remark = "";
					 }
					org_id = rowdata.ORG_ID ; 
					closing_well_id = rowdata.CLOSING_WELL_ID ;			
                	if(operate == 2){
                		assignM(2);
                	}
									
        }

       	function setTeamData(stationid,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'searchQueryAll_searchTeam.action',
    			data: {"CYZ":stationid},
    			
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("team_station").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("team_station").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				
    			}
    		});
    	}

        function setWellData(cyz,team,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'searchQueryAll_searchCYZTEAMJH.action',
    			data: {"CYZ":cyz,"TEAM":team},
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("well_station").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("well_station").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				
    			}
    		});
    	}
        function getWellInitData() {
        	$("#team_station").ligerGetComboBoxManager().setValue('');
    		$("#well_station").ligerGetComboBoxManager().setValue('');
    		var oilStationtext=[{ id: 1 , text: '' }];
    		var teamStationtext=[{ id: 1 , text: '' }];
    		var wellstationtext=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'searchQueryAll_searchALLCYZ.action',
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
    		liger.get("oil_station").setData(combinationStationtext);
    		liger.get("team_station").setData(processingStationtext);
    		liger.get("well_station").setData(observationWelltext);
    	}
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){
		}
		
         function onSubmit()
        {
        
        	//alert(texwater);
        	grid.setOptions({
        		parms: [{name: 'CYZ',value: $("#oil_station").val()},
        		        {name: 'TEAM',value: $("#team_station").val()},
						{name: 'WELLNAME',value: $("#well_station").val()},
						{name: 'txtDate',value:$("#txtDate").val()},
						{name: 'txtDate1', value:$("#txtDate1").val()}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
				 	$("#well_name").ligerGetComboBoxManager().selectValue(org_id);
				 	//alert(org_id);
				 	//document.getElementById("well_name").value = well_name;
				 	$("#downtime_type").ligerGetComboBoxManager().selectValue(downtime_type);
				 	if("是" == key_down_tag){
				 		$("#key_down_tag").ligerGetComboBoxManager().selectValue('1');
				 	}else if("否" == key_down_tag){
				 		$("#key_down_tag").ligerGetComboBoxManager().selectValue('0');
				 	}else{
				 		$("#key_down_tag").ligerGetComboBoxManager().selectValue("");
				 	}
				   
			 		document.getElementById("start_datetime").value = start_datetime;
	               	document.getElementById("end_datetime").value = end_datetime;
	               	document.getElementById("remark").value= remark;
	             
	               	
			 }else if(flg == 1){
					$("#well_name").ligerGetComboBoxManager().selectValue("");
					//document.getElementById("well_name").value = "";
			 		 $("#downtime_type").ligerGetComboBoxManager().selectValue("");
				   $("#key_down_tag").ligerGetComboBoxManager().selectValue("");
			 		document.getElementById("start_datetime").value = "";
	               	document.getElementById("end_datetime").value = "";
	               	document.getElementById("remark").value= "";
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
											url : 'gjwh_removeData.action',
											async : false,
											data: {"closing_well_id":closing_well_id,"wellname":wellname},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else{
														
														onSubmit();
														assignM(1);
														$.ligerDialog.success('关井信息删除成功！');
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
    
 		var CYZ=$("#oil_station").val();
  	    var TEAM=$("#team_station").val();
  	    var WELLNAME=$("#well_station").val();
  	    var txtDate=$("#txtDate").val();
  	    var txtDate1 = $("#txtDate1").val();	
		var totalNum = 0;
		var url = "gjwh_searchDatas.action?CYZ="+encodeURIComponent(CYZ)+"&TEAM="+encodeURIComponent(TEAM)+"&WELLNAME="+encodeURIComponent(WELLNAME)+"&txtDate="+encodeURIComponent(txtDate)+'&txtDate1='+encodeURIComponent(txtDate1)+'&totalNum=export';
		var urls = "gjwh_searchDatas.action?CYZ="+encodeURIComponent(CYZ)+"&TEAM="+encodeURIComponent(TEAM)+"&WELLNAME="+encodeURIComponent(WELLNAME)+"&txtDate="+encodeURIComponent(txtDate)+'&txtDate1='+encodeURIComponent(txtDate1)+'&totalNum=totalNum';
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
         
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;">
	 <div id="mainsearch" style=" width:99%">
	 
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
						<td align="right" class="l-table-edit-td" style="width:80px">采油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oil_station" name = "oil_station" />
		                </td>
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:80px">班组：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="team_station" name = "team_station"/>
		                </td>
		                <td align="left">
		                </td>

		                <td align="right" class="l-table-edit-td" style="width:80px">井号：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="well_station" name = "well_station"/>
		                </td>
		                <td align="left">
		                </td>
		                
		                <td align="right" class="l-table-edit-td" style="width:80px">关井时间：</td>
						<td ><input type="text" id="txtDate" ltype="date"/></td>
						<td align="center" class="l-table-edit-td" style="width:40px">--</td>
						<td ><input type="text" id="txtDate1" ltype="date"/></td>
		                
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
		               
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">关井原因:</td>
		               
		                <td align="left" class="l-table-edit-td" style="width:150px">关井时间:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">开井时间:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">关井重点标志:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		                 </tr>
		                <tr>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="well_name" type="text" id="well_name"  ltype="text" validate="{required:true,minlength:1}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="downtime_type" type="text" id="downtime_type"  ltype="text" validate="{required:true,minlength:1}" />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="start_datetime" type="text" id="start_datetime" ltype="text"  validate="{minlength:1,maxlength:20}" />
		                </td>
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="end_datetime" type="text" id="end_datetime" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="key_down_tag" type="text" id="key_down_tag" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text" />
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