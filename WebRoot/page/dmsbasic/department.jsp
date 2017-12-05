﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>部门基础信息</title>
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

    <script type="text/javascript">
        var eee;
         var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        //鼠标选择数据
		var depname;
		var depheader;
		var depaddress;
		var depphone;
		var bz;
		var hiddepid;
		var org_id;
        $(function ()
        {
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'dep_pageInit.action',
					success : function(jsondata) { 
					//alert(jsondata);
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=""){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(eval('(' + data.GRIDS + ')'));					
							toolbar = grid.topbar.ligerGetToolBarManager();
						}else{
							alert("数据库数据存在错误！");
						}
					},
					error : function(data) {
				
					}
				});
		
			$("#depname").ligerTip();
			
            $.validator.addMethod(
                    "notnull",
                    function (value, element, regexp)
                    {
                        if (!value) return true;
                        return !$(element).hasClass("l-text-field-null");
                    },
                    "不能为空"
            );

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
                 
              	   	 var adddepname = $("#department_name").val();
              	   	 var addheader = $("#department_header").val();
              	   	 var addadss = $("#department_address").val();
              	   	 var addphone = $("#department_phone").val();
              	   	 var addremarke = $("#remark").val();
              	   	 var updatedepid =  $("#depid").val();
              	   	 // var orgid = $("#org_id").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'dep_addDep.action',
									async : false,
									data: {"department_name":adddepname ,"department_header":addheader,"department_address":addadss ,"department_phone":addphone,"remark":addremarke},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											
											onSubmit();
											  setpage(1); //隐藏编辑界面
  												setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('部门信息添加成功！');
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
									url : 'dep_updateDep.action',
									async : false,
									data: {"department_name":adddepname ,"department_header":addheader,"department_address":addadss ,"department_phone":addphone,"remark":addremarke,"depid":updatedepid},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											 setpage(1); //隐藏编辑界面
 											 setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('部门修改成功！');
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
            $("form").ligerForm();
            $(".l-button-test").click(function ()
            {
                alert(v.element($("#department_name")));
            });
        });  
        
        function fromAu(rowdata){
        	//用户选择修改数据
				                	 depname = rowdata.DEPARTMENT_NAME;
				                	  if (rowdata.DEPARTMENT_HEADER != null && typeof(rowdata.DEPARTMENT_HEADER)!="undefined"){
				                	   	depheader = rowdata.DEPARTMENT_HEADER;
				                	  }else{
				                	  	 depheader = "";
				                	  }
									if (rowdata.DEPARTMENT_ADDRESS != null && typeof(rowdata.DEPARTMENT_ADDRESS)!="undefined"){
										 depaddress = rowdata.DEPARTMENT_ADDRESS;
									 }else{
									  	depaddress = "";
									 }
									 if (rowdata.DEPARTMENT_PHONE != null && typeof(rowdata.DEPARTMENT_PHONE)!="undefined"){
									 		depphone = rowdata.DEPARTMENT_PHONE;
									 	}else{
									 		depphone = rowdata.DEPARTMENT_PHONE;	
									 	}
									 
									 if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
									 	 bz = rowdata.REMARK;
									 }else{
									 	 bz = "";
									 }    
									 hiddepid = rowdata.DEPARTMENTID;
									 org_id = rowdata.ORG_ID;
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }
        
        //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			alert(id);
			alert(name);
			alert(datatype);
		}
		
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'depname',
					value: $("#depname").val()
				}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
			 		document.getElementById("department_name").value = depname;
	               	document.getElementById("department_header").value = depheader;
	               	document.getElementById("department_address").value = depaddress;
	               	if(depphone==null){
	               		document.getElementById("department_phone").value= "";
	               	}else{
	               		document.getElementById("department_phone").value= depphone;
	               	}
	               	document.getElementById("remark").value= bz;
	               	document.getElementById("depid").value= hiddepid;
	               	document.getElementById("org_id").value= org_id;
			 }else if(flg == 1){
			 		document.getElementById("department_name").value = "";
	               	document.getElementById("department_header").value = "";
	               	document.getElementById("department_address").value = "";
	               	document.getElementById("department_phone").value= "";
	               	document.getElementById("remark").value= "";
	               	document.getElementById("depid").value= "";
	               	document.getElementById("org_id").value= "";
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
              	    		alert('请选择一条你要修改用户信息的数据！');
              	    
              	     }else if(rows.length == 1){
							
						 	 if(operate != 1 && operate != 2){
			              	setpage(0); //显示编辑界面
			              	setItemsd(0);//0-显示编辑区，添加隐藏按钮
			              }
				           operate = 2;
		              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改用户信息的数据！');
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
											url : 'dep_removeDep.action',
											async : false,
											data: {"depid":hiddepid,"org_id":org_id},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(jsondata));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else{
														onSubmit();
														assignM(1);
													}
													
												}else{
													$.ligerDialog.error(outerror(data));
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
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;">

	<div id="mainsearch" style=" width:99%">
	 
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
						<td align="right" class="l-table-edit-td" style="width:60px">部门：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="depname" name = "depname" title="系统支持部门模糊查询"/>
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
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">部门名称:</td>
		               
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">部门领导:</td>
		               
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">部门地址:</td>
		               
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">部门电话:</td>
		               
		                
		                 <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="depid" type="hidden" id="depid" />
		                	<input name="org_id" type="hidden" id="org_id" />
		                </td>
		               
		            </tr>
		            <tr>
		                
		                 <td align="left" class="l-table-edit-td"  >
		                 	<input name="department_name" type="text" id="department_name" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                 </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="department_header" type="text" id="department_header" ltype="text"  />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="department_address" type="text" id="department_address" ltype="text"  />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="department_phone" type="text" id="department_phone" ltype="text"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"  />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text"   />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<!--  <input type="submit" value="提交" id="Button1" class="l-button" style="width:100px" /> -->
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