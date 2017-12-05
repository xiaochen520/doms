<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>联合站信息管理</title>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>   --> 
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
		var combination_stationid ; 	//联合站ID
		var combination_station_name;	//联合站名称
		var combination_station_type;	//联合站类型
		var combination_station_code;	//联合站编码
		//var commissioning_date			//投产日期
		//var scrapped_date				//报废日期
		var status_value				//建设生产状态
		var org_id						//组织结构ID
		var remark						//备注
					                	
        
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'combination_pageInit.action',
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
			$("#status_value").ligerComboBox({
			url:'station_searchStatusValue.action',
			valueField: 'id',
			valueFieldID: 'hidstatusid',
			textField: 'text',
			selectBoxHeight:200,
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
              	   	var combination_stationid  = $("#combination_stationid").val();
					var combination_station_name  = $("#combination_station_name").val();
					var combination_station_type = $("#combination_station_type").val();
					var combination_station_code  = $("#combination_station_code").val();
					//var commissioning_date  = $("#commissioning_date").val();
					//var scrapped_date  = $("#scrapped_date").val();
					var status_value  = $("#status_value").val();
					var org_id  = $("#org_id").val();
					var remark  = $("#remark").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              		
              	   	   jQuery.ajax({
									type : 'post',
									url : 'combination_addCombination.action',
									async : false,
									data: {"combination_stationid":combination_stationid ,"combination_station_name":combination_station_name,
											"combination_station_type":combination_station_type ,
											"combination_station_code":combination_station_code,
											"status_value":status_value,"remark":remark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											 setpage(1); //隐藏编辑界面
											  setItemsd(2); //去掉隐藏，显示按钮
																						
											$.ligerDialog.success('联合站添加成功！');
											  operate = 0;
										}else{
											$.ligerDialog.error(outerror(jsondata));
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   //2-修改
              	   }else if(operate == 2){
              		
              	   		 jQuery.ajax({
									type : 'post',
									url : 'combination_updateCombination.action',
									async : false,
									data: {"combination_stationid":combination_stationid ,"combination_station_name":combination_station_name,
											"combination_station_type":combination_station_type ,"org_id":org_id,
											"combination_station_code":combination_station_code, 
											"status_value":status_value,"remark":remark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											 setpage(1); //隐藏编辑界面
											  setItemsd(2); //去掉隐藏，显示按钮
																						
											$.ligerDialog.success('联合站修改成功！');
											  operate = 0;
										}else{
											$.ligerDialog.error(outerror(jsondata));
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   }
		           //operate = 0;
                }
            });
           // $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
           // $("#scrapped_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
            
             $("#combination_station_type").ligerComboBox({
 				valueField: 'id',
 				valueFieldID: 'typeid',
 				textField: 'text',
 				data: [
 	                    { text: '稀油联合站', id: '1' },
 	                    { text: '稠油联合站', id: '2' },
 	                    { text: '供汽联合站', id: '3' }
 	                   // { text: '燃煤', id: '4' }
 	                ],
 				selectBoxHeight:100,
 				width: 120,
 				autocomplete:true,
 				hideOnLoseFocus:true
 			});
            $("form").ligerForm();
            $(".l-button-test").click(function () {});
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
        						
        							combination_stationid = rowdata.COMBINATION_STATIONID;
									combination_station_name = rowdata.COMBINATION_STATION_NAME;
									org_id = rowdata.ORG_ID;
									if (rowdata.COMBINATION_STATION_TYPE != null && typeof(rowdata.COMBINATION_STATION_TYPE)!="undefined"){
									 	combination_station_type = rowdata.COMBINATION_STATION_TYPE;
									 }else{
									 	combination_station_type = "";
									 }
									if (rowdata.COMBINATION_STATION_CODE != null && typeof(rowdata.COMBINATION_STATION_CODE)!="undefined"){
									 	combination_station_code = rowdata.COMBINATION_STATION_CODE;
									 }else{
									 	combination_station_code = "";
									 }
									if (rowdata.STATUS_VALUE != null && typeof(rowdata.STATUS_VALUE)!="undefined"){
									 	status_value = rowdata.STATUS_VALUE;
									 }else{
									 	status_value = "";
									 }
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
		function onJKSubmit(id,name,datatype,basid){ 

				//alert(id);
			//alert(name);
			//alert(datatype);
			var oilstationname='';
			var areablock='';
			var blockstationname='';
			var ghname='';
			var wellnum='';
			
			
			if(datatype=='5'){
				oilstationname=name;
				//$("#combinationnname").ligerGetComboBoxManager().selectValue(basid);
			}else{
				alert("没有找到该节点");
				return ;			
			}
			
			
			grid.setOptions({
        		parms: [{
					name: 'combinationnname',
					value: oilstationname
				}
				]
        	});
         	grid.loadData(true);
        }
		
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'combinationnname',
					value: $("#combinationnname").val()
				}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
			 		document.getElementById("combination_stationid").value = combination_stationid;
	               	document.getElementById("combination_station_name").value = combination_station_name;
	               	$("#combination_station_type").ligerGetComboBoxManager().selectValue(combination_station_type);
	        		if (combination_station_type == '稀油联合站'){
	        			$("#combination_station_type").ligerGetComboBoxManager().selectValue(1);
					}else if (combination_station_type == '稠油联合站') {
						$("#combination_station_type").ligerGetComboBoxManager().selectValue(2);
					}else {
						document.getElementById("combination_station_type").value= combination_station_type;
						}
	               	document.getElementById("combination_station_code").value= combination_station_code;
	               //	document.getElementById("commissioning_date").value= commissioning_date;
	               	//document.getElementById("scrapped_date").value= scrapped_date;
	               	//document.getElementById("status_value").value= status_value;
	               	if (status_value != null && typeof(status_value)!="undefined" && status_value != '') {
						$("#status_value").ligerGetComboBoxManager().selectValue(status_value);
					}
					else {
						document.getElementById("status_value").value= status_value;
					}
	               	document.getElementById("org_id").value= org_id;
	               	document.getElementById("remark").value= remark;
			 }else if(flg == 1){
			 		document.getElementById("combination_stationid").value = "";
	               	document.getElementById("combination_station_name").value = "";
	               	//document.getElementById("combination_station_type").value = "";
	               	$("#combination_station_type").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("combination_station_code").value= "";
	               	//document.getElementById("commissioning_date").value="";
	               	//document.getElementById("scrapped_date").value= "";
	               	$("#status_value").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("status_value").value= "";
	               	document.getElementById("org_id").value= "";
	               	document.getElementById("remark").value= "";
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
            	  if(operate != 1 && operate != 2){
  	              	setpage(0); //显示编辑界面
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
         	              	setpage(0); //显示编辑界面
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
											url : 'combination_removeCombination.action',
											async : false,
											data: {"combinationId":combination_stationid,"org_id":org_id},
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
          setpage(0); //显示编辑界面
          setItemsd(0);//0-显示编辑区，添加隐藏按钮
          break;   
          case "down":
          		setpage(1); //隐藏编辑界面
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
						<td align="right" class="l-table-edit-td" style="width:80px">联合站名称：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="combinationnname" name = "combinationnname" />
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td"  >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
		                </td>
					</tr>
				
				</table>
				<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" >
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">联合站名称:</td>
		               
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">联合站类型:</td>
		               
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">联合站编码:</td>
		               
		                <td align="left" class="l-table-edit-td" style="width:150px">建设生产状态:</td>
		               
		     			  <td align="left" class="l-table-edit-td"  style="width:150px">备注:</td>
		            </tr>
		            <tr>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="combination_station_name" type="text" id="combination_station_name"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="combination_station_type" type="text" id="combination_station_type" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		               
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="combination_station_code" type="text" id="combination_station_code" ltype="text"  />
		                </td>
		           
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="status_value" type="text" id="status_value"  ltype="text" />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text"  />
		                </td>
		            
		            
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	
		                	<!-- <input name="audbshor_date" type="text" id="audbshor_date" ltype="text" /> -->
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="combination_stationid" type="hidden" id="combination_stationid" />
		                
		                	<input name="org_id" type="hidden" id="org_id" />
		               
		                	<!-- <input type="submit" value="提交" id="Button1" class="l-button" style="width:100px" />  -->
		                </td>
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