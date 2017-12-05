<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>角色信息管理</title>
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
    
    
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script> 
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/js/ligerui.expand.js" type="text/javascript"></script> 
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    



    <script type="text/javascript">
        var eee;
        var grid = null;
        var toolbar;
        var operate = 0;
        //鼠标选择数据
		var rolename;
		var roledescribed;
		var bz;
		var hidroleid;
					                	
        
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'role_pageInit.action',
					success : function(jsondata) { 
						if (jsondata != null && typeof(jsondata)!="undefined"){
							grid = $("#maingrid").ligerGrid(eval('(' + jsondata + ')'));
							toolbar = grid.topbar.ligerGetToolBarManager();	
							
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
			
			//toolbar = grid.toolbar.ligerGetToolBarManager();
			//toolbar.setDisabled("refers");
			$("#selectrole").focus(); 
            onSubmit();
        	
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
              	   	 var updateid =  $("#roleid").val();
              	   	 var addname = $("#role_name").val();
              	   	 var updatdes = $("#role_described").val();
              	   	 var addremarke = $("#remark").val();
              	   	 //alert(updateid);
              	   //判断修改还是添加操作 1-添加、2-修改
              	   // alert(updateid);
              	   if(operate == 1){
              	   	   jQuery.ajax({
									type : 'post',
									url : 'role_addRole.action',
									async : false,
									data: {"role_name":addname ,"role_described":updatdes,"remark":addremarke},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
  											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('角色添加成功！');
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
									url : 'role_updateRole.action',
									async : false,
									data: {"role_name":addname ,"role_described":updatdes,"remark":addremarke,"roleid":updateid},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											 setpage(1); //隐藏编辑界面
 											 setItemsd(2); //去掉隐藏，显示按钮		
											$.ligerDialog.success('角色修改成功！');
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
            $(".l-button-test").click(function () {
                alert(v.element($("#role_name")));
            });
        });  
        
        function fromAu(rowdata){
        	//用户选择修改数据
				                	 rolename = rowdata.ROLE_NAME;
									 if (rowdata.ROLE_DESCRIBED != null && typeof(rowdata.ROLE_DESCRIBED)!="undefined"){
									 	 roledescribed = rowdata.ROLE_DESCRIBED;
									 }else{
									 	 roledescribed = "";
									 } 
									 
									 
									 if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
									 	 bz = rowdata.REMARK;
									 }else{
									 	 bz = "";
									 }    
									 hidroleid = rowdata.ROLEID;
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }
         function onSubmit()
        {
        	
         	jQuery.ajax({
					type : 'post',
					url : 'role_seachRole.action?nowdata='+parseInt(Math.random()*100000),
					data: {"selectrole":$("#selectrole").val()},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
							grid.set({ data: { Rows: eval('(' + jsondata + ')')} }); 
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
        }
      function assignM(flg){
      		
			 if(flg == 2){
			 	
	               	document.getElementById("role_name").value= rolename;
	               	document.getElementById("role_described").value= roledescribed;
	               	document.getElementById("remark").value= bz;
	               	document.getElementById("roleid").value= hidroleid;
			 }else if(flg == 1){
			 		document.getElementById("role_name").value= "";
	               	document.getElementById("role_described").value= "";
	               	document.getElementById("remark").value= "";
	               	document.getElementById("roleid").value= "";
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
						 		setItemsd(0);//1-隐藏编辑区添加显示按钮
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
											url : 'role_removeRole.action?roleid='+hidroleid,
											async : false,
											success : function(data) { 
											if (data != null && typeof(data)!="undefined" && data == "1"){
													
													onSubmit();
													assignM(1);
												}else if(data == -10){
													$.ligerDialog.error('角色删除失败 - 该角色在被其他用户使用！');
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
              	 case "accredit":
                  	if (rows.length == 0) { 
              	    		alert('请选择一条你要修改角色信息的数据！');
              	    
              	     }else if(rows.length == 1){
							
              	    		$.ligerDialog.open({
              	    			title: "角色授权",
              	    			url:'accredit.jsp?roleID='+hidroleid,
              	    			width: 950,
              	    			height: 500
              	    		});
              	    		
              	     }else{
              	     	alert('请选择一条你要修改用户信息的数据！');
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
						
		               <td align="right" class="l-table-edit-td" style="width:60px">角色名称：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="selectrole" name = "selectrole" />
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
		                </td>
		                <td align="left">
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                <div style="color: red" id="errermsg" > <s:fielderror></s:fielderror></div>
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
		                <td align="left" class="l-table-edit-td" style="width:80px">角色名称:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">角色描述:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">备注:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                <input name="roleid" type="hidden" id="roleid" />
		                </td>
		                <td align="left"></td>
		                
		            </tr>
		            <tr>
		            	 <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="role_name" type="text" id="role_name"  ltype="text" validate="{required:true,minlength:2,maxlength:20}" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="role_described" type="text" id="role_described" ltype="text"  />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="remark" type="text" id="remark" ltype="text" />
		                </td>
		                <td align="left"></td>
		               
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
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