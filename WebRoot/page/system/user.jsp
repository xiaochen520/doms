<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>操作员信息管理</title>
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
		var opercode;
		var depid;
		var roleid;
		var opername;
		var operpass;
		var dep;
		var role;
		var opersdbsadbse;      
		var audbshorkdate;
		var bz;
		var hiduserid;
        
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'user_pageInit.action',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(eval('(' + data.GRIDS + ')'));		
							toolbar = grid.topbar.ligerGetToolBarManager();	
							//toolbar.setDisabled("saveid");
							
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
				
			 $("#depname").ligerComboBox({
				            	url:'user_getUserDep.action',
								valueField: 'id',
								valueFieldID: 'depnameid',
								textField: 'text',
								selectBoxWidth: 170,
								selectBoxHeight : 200,
								hideOnLoseFocus:true,
								initText :''
				            });
				            
              $("#roles").ligerComboBox({
				            	url:'user_getUserRoles.action',
				            	//isShowCheckBox: true,
				            	//isMultiSelect: true,
								valueField: 'id',
								valueFieldID: 'hidroleid',
								textField: 'text',
								selectBoxWidth: 180,
								hideOnLoseFocus:true,
								initText :''
				            });
				          
              $("#department").ligerComboBox({
				            	url:'user_getUserDep.action',
								valueField: 'id',
								valueFieldID: 'hiddepid',
								textField: 'text',
								selectBoxWidth: 180,
								hideOnLoseFocus:true,
								initText :''
				            });
				
				
	   			//toolbar.setDisabled("refers");
			//$("#username").val("");
			//$("#username").focus();
			
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
              	    var addopercode = $("#oper_code").val();
              	   	 var addopername = $("#oper_name").val();
              	   	 var addoperpass = $("#oper_pass").val();
              	   	 var depid = $("#hiddepid").val();
              	   	 var depname = $("#department").val();
              	   	 var updateuserid =  $("#userid").val();
              	   	 var addrole = $("#hidroleid").val();
              	   	 var updaterole = $("#roles").val();
              	   	 var addopersdbsadbse = $("#oper_sdbsadbse").val();
              	   	 var addremarke = $("#remark").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   
              	   if(operate == 1){
              	   	   jQuery.ajax({
									type : 'post',
									url : 'user_adduser.action',
									async : false,
									data: {"oper_code":addopercode ,"oper_name":addopername,"oper_pass":addoperpass ,"depid":depid,"depname":depname,
											"roles":addrole ,"oper_sdbsadbse":addopersdbsadbse,"remark":addremarke},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('操作员添加成功！');
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
									url : 'user_updateUser.action',
									async : false,
									data: {"oper_code":addopercode ,"oper_name":addopername,"oper_pass":addoperpass ,"depid":depid,"depname":depname,
											"roles":updaterole ,"oper_sdbsadbse":addopersdbsadbse,"remark":addremarke,"userid":updateuserid},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('操作员修改成功！');
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
                alert(v.element($("#oper_code")));
                alert(v.element($("#oper_name")));
                alert(v.element($("#oper_pass")));
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
				                	 opercode = rowdata.OPER_CODE;
									 opername = rowdata.OPER_NAME;
									 operpass = rowdata.OPER_PASS;
									 depid = rowdata.DEPID;
									 roleid = rowdata.ROLEID;
									 dep = rowdata.DEPARTMENT;
									 if (rowdata.ROLES != null && typeof(rowdata.ROLES)!="undefined"){
									 	 role = rowdata.ROLES;
									 	 role = role.replace("|",";");
									 }else{
									 	 role = "";
									 } 
									 
									 if (rowdata.OPER_SDBSADBSE != null && typeof(rowdata.OPER_SDBSADBSE)!="undefined"){
									 	opersdbsadbse = rowdata.OPER_SDBSADBSE; 
									 }else{
									 	opersdbsadbse ="";
									 }
								
									 
									 if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
									 	 bz = rowdata.REMARK;
									 }else{
									 	 bz = "";
									 }    
									 hiduserid = rowdata.USERID;
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }
        
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [
        		{
					name: 'depname',
					value: $("#depname").val()
				},
        		{
					name: 'username',
					value: $("#username").val()
				}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
			 		document.getElementById("oper_code").value = opercode;
	               	document.getElementById("oper_name").value = opername;
	               	document.getElementById("oper_pass").value = operpass;
	               $("#department").ligerGetComboBoxManager().selectValue(depid);  
	               //alert(roleid);
	               	$("#roles").ligerGetComboBoxManager().selectValue(roleid);  
	               	document.getElementById("oper_sdbsadbse").value= opersdbsadbse;
	               	//document.getElementById("audbshor_date").value= audbshorkdate;
	               	document.getElementById("remark").value= bz;
	               	document.getElementById("userid").value= hiduserid;
	              // 	var leng = toolbar.options.items.length;
	               //var items1 = toolbar.options.items[item];
	               //alert(JSON2.stringify(toolbar.options.items);
	               //toolbar.removeItem(items1[leng-1]); 
	               
			 }else if(flg == 1){
			 		document.getElementById("oper_code").value="";
	               	document.getElementById("oper_name").value="";
	               	document.getElementById("oper_pass").value="";
	                $("#department").ligerGetComboBoxManager().selectValue('');  
	               	$("#roles").ligerGetComboBoxManager().selectValue('');  
	               	document.getElementById("oper_sdbsadbse").value="";
	               	//document.getElementById("audbshor_date").value="";
	               	document.getElementById("remark").value="";
	               	document.getElementById("userid").value="";
	               //	toolbar.setDisabled("refers");
	               //alert(toolbar.options.items.length);
	               //for(var i=0 ; i<toolbar.options.items.length ;i++){
	               	//alert(JSON2.stringify(toolbar.options.items[i]));
	               //}
	              
	               
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
											url : 'user_removeUser.action?userID='+hiduserid,
											async : false,
											success : function(data) { 
											if (data != null && typeof(data)!="undefined" && data == "1"){
													
													onSubmit();
													assignM(1);
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
                 case "unlock":
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要解锁的数据！')
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('您确定要为 &nbsp;'+opername+'&nbsp;进行权限解锁吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'user_Unlock.action?userID='+hiduserid,
											async : false,
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
															$.ligerDialog.success("用户 ："+opername +" &nbsp;权限解锁成功,请该用户重新登录");
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
						 <td align="right" class="l-table-edit-td" style="width:60px">部门：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="depname" name = "depname"/>
		                </td>
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:100px">操作员姓名：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="username" name = "username" />
		                </td>
		               
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
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
		                <td align="left" class="l-table-edit-td" style="width:80px">操作员:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">操作员姓名:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">密码:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">部门:</td>
		                <td align="left"></td>
		                
		                 <td align="left" class="l-table-edit-td" style="width:80px">角色:</td>
		                <td align="left"></td>
		                <td align="left" class="l-table-edit-td" style="width:80px">当前状态:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px" >备注:</td>
		                <td align="left"></td>
		            </tr>
		            <tr>
		            	 <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="oper_code" type="text" id="oper_code"  ltype="text" 
		                	validate="{required:true,minlength:3,maxlength:20}" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="oper_name" type="text" id="oper_name" ltype="text" 
		                	validate="{required:true,minlength:1,maxlength:10}" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="oper_pass" type="password" id="oper_pass" ltype="text" 
		                	validate="{required:true,minlength:3,maxlength:32}" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="department" type="text" id="department" ltype="text"  
		                	validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="roles" type="text" id="roles" ltype="text"  
		                	validate="{required:true,minlength:1}" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="oper_sdbsadbse" type="text" id="oper_sdbsadbse" ltype="text" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="remark" type="text" id="remark" ltype="text"  />
		                </td>
		                <td align="left">
		                <input name="userid" type="hidden" id="userid" />
		                </td>
		            </tr>
		            
		            
		            <!--   <tr>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px"></td>
		                <td align="left"></td>
		                <td align="left" class="l-table-edit-td" style="width:80px"></td>
		                <td align="left"></td>
		                
		                 <td align="left" class="l-table-edit-td" style="width:80px"></td>
		                <td align="left"></td>
		            </tr>
		            <tr>
		            	 
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	
		                	<input name="audbshor_date" type="text" id="audbshor_date" ltype="text" />
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	 <input type="hidden" value="提交" id="hidButton" class="l-button" style="width:100px" />
		                </td>
		                <td align="left">
		                </td>
		            </tr>
		            -->
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