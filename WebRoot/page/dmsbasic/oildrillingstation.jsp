<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>采油站信息管理</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
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
     <link href="../../lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

    
    <script type="text/javascript">
        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        
        //鼠标选择数据
		var oildrilling_stationid ;	//采油井ID
		var oildrilling_stationname;	//采油井名称
		var status_value;		//建设生产状态
		//var commissioning_date	//投产日期
		//var scrapped_date	 	//报废日期
		var org_id;				//组织结构ID
		var remark;			 	//备注
		
					                	
        
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'oildrilling_pageInit.action',
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
                    
              	   	var oildrilling_stationid  = $("#oildrilling_stationid").val();
					var oildrilling_stationname  = $("#oildrilling_stationname").val();
					var status_value  = $("#status_value").val();
					//var commissioning_date  = $("#commissioning_date").val();
					//var scrapped_date  = $("#scrapped_date").val();
					var org_id  = $("#org_id").val();
					var remark  = $("#remark").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'oildrilling_addOildrilling.action',
									async : false,
									data: {"oildrilling_stationid":oildrilling_stationid ,"oildrilling_stationname":oildrilling_stationname,
											"status_value":status_value,"remark":remark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('采油站添加成功！');
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
									url : 'oildrilling_updateOildrilling.action',
									async : false,
									data: {"oildrilling_stationid":oildrilling_stationid ,"oildrilling_stationname":oildrilling_stationname,
											"status_value":status_value,"remark":remark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('采油站修改成功！');
											operate = 0;
										}else{
											$.ligerDialog.error(outerror(jsondata));
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   }
		           
                }
            });
           // $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
           // $("#scrapped_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
             $("#commissioning_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
             $("#scrapped_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                 //   label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
             	
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
        							
        							oildrilling_stationid = rowdata.OILDRILLING_STATIONID;
        							org_id = rowdata.ORG_ID;
									oildrilling_stationname = rowdata.OILDRILLING_STATION_NAME;
							
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
			var oilstationname='全部';
			var areablock='';
			var blockstationname='';
			var ghname='';
			var wellnum='';
			
			
			if(datatype=='4'){
				oilstationname=name;
			}
			else{
				alert("该节点错误，请查看出信息对应节点");
				return;
			}

			grid.setOptions({
        		parms: [{
					name: 'oilstationname',
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
					name: 'oilstationname',
					value: $("#oilstationname").val()
				}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
	               	document.getElementById("remark").value= remark;
			 		document.getElementById("oildrilling_stationid").value = oildrilling_stationid;
	               	document.getElementById("oildrilling_stationname").value = oildrilling_stationname;
	               	//document.getElementById("status_value").value= status_value;
	               	/*if (status_value != null && typeof(status_value)!="undefined" && status_value != '') {
						$("#status_value").ligerGetComboBoxManager().selectValue(status_value);
					}
					else {
						document.getElementById("status_value").value= status_value;
					}*/
	              // 	document.getElementById("commissioning_date").value= commissioning_date;
	               //	document.getElementById("scrapped_date").value= scrapped_date;
			 }else if(flg == 1){
	               	document.getElementById("remark").value= "";
			 		document.getElementById("oildrilling_stationid").value = "";
	               	document.getElementById("oildrilling_stationname").value = "";
	               //	$("#status_value").ligerGetComboBoxManager().selectValue("");
	               //	document.getElementById("status_value").value= "";
	               //	document.getElementById("commissioning_date").value="";
	               	//document.getElementById("scrapped_date").value= "";
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
											url : 'oildrilling_removeOildrilling.action',
											async : false,
											data: {"oildrilling_stationId":oildrilling_stationid,"org_id":org_id},
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
														$.ligerDialog.success('采油站基础删除成功！');
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
						<td align="right" class="l-table-edit-td" style="width:60px">采油站：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="oilstationname" name = "oilstationname" />
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td" >
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
		                <td align="left" class="l-table-edit-td" style="width:150px">采油站名称:</td>
		               
		               <%--<td align="left" class="l-table-edit-td" style="width:150px">建设生产状态:</td> --%>
		               <%--
		                <td align="left" class="l-table-edit-td" style="width:150px">投产日期:</td>
		               
		                
		                 <td align="left" class="l-table-edit-td" style="width:150px">报废日期:</td>
		               --%>
		                
		            	<td align="left" class="l-table-edit-td" style="width:150px" >备注:</td>
		               
		            </tr>
		            <tr>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="oildrilling_stationname" type="text" id="oildrilling_stationname"  ltype="text" validate="{required:true,minlength:2,maxlength:20}" />
		                </td>
		               
		                
		               <%--  	<td align="left" class="l-table-edit-td" >
		                	<input name="status_value" type="text" id="status_value"  ltype="text" validate="{required:true,minlength:2,maxlength:20}" />
		                </td>
		               --%>
		              <%--
		                <td align="left" class="l-table-edit-td" >
		                	<input name="commissioning_date" type="text" id="commissioning_date" ltype="date"  validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="scrapped_date" type="text" id="scrapped_date" ltype="text" />
		                </td>
		                --%>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text"  />
		                </td>
		               
		          
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="oildrilling_stationid" type="hidden" id="oildrilling_stationid" />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="org_id" type="hidden" id="org_id" />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<!--<input type="submit" value="提交" id="Button1" class="l-button" style="width:100px" />   -->
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