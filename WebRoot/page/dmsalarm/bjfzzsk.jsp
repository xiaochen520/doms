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
     <script src="../../lib/js/validator.js" type="text/javascript"></script>
    
     <link href="../../lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
    var eee;
	var grid = null;
	var toolbar;
	var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0

	//鼠标选择数据
	var username; 
	var phone; 
	var deptid; 
	var alarm_key; 
	var dep_manag;
	var data_unit;
	var alarmuserid; 
	var remark;
	var hiduserid;
	
        
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'bjfzzsk_pageInit.action',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(eval('(' + data.GRIDS + ')'));
							//alert(grid);	
							toolbar = grid.topbar.ligerGetToolBarManager();	

						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
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
                
              	    var addusername = $("#username").val();
              	   	 var addphone = $("#phone").val();
              	   	 var addalarmkey = $("#alarm_key").val();
              	   	 var deptid = $("#hiddepid").val();
              	   	 var depname = $("#department").val();
              	   	 var adddepmanag = $("#dep_manag").val();
              	   	 var adddataunit = $("#data_unit").val();
              	   	 var updatealarmuserid = $("#alarmuserid").val();
              	   	 var addremarke = $("#remark").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   
              	   if(operate == 1){
              	   	   jQuery.ajax({
									type : 'post',
									url : 'dxyhgl_adduser.action',
									async : false,
									data: {"username":addusername ,"phone":addphone,"alarm_key":addalarmkey ,"hiddepid":deptid,"depname":depname,
											"dep_manag":adddepmanag,"data_unit":adddataunit,"alarmuserid":updatealarmuserid,"remark":addremarke},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('短信推送用户添加成功！');
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
              	   alert('dsafsdfsadf');
              	   		 jQuery.ajax({
									type : 'post',
									url : 'dxyhgl_updateUser.action',
									async : false,
									data: {"username":addusername ,"phone":addphone,"alarm_key":addalarmkey,"hiddepid":deptid,"depname":depname,
											"dep_manag":adddepmanag,"data_unit":adddataunit,"alarmuserid":updatealarmuserid,"remark":addremarke},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('短信推送用户修改成功！');
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
                alert(v.element($("#username")));
                alert(v.element($("#phone")));
               alert(v.element($("#alarm_key")));
            });
        });  
					
					function fromAu(rowdata) {
						//用户选择修改数据
						username = rowdata.username;
						phone = rowdata.phone;
						alarm_key = rowdata.alarm_key;
 						dep_manag = rowdata.dep_manag;
						data_unit = rowdata.data_unit; 
						dept = rowdata.dept;

						if (rowdata.REMARK != null
								&& typeof (rowdata.REMARK) != "undefined") {
							bz = rowdata.remark;
						} else {
							bz = "";
						}
						
					hiduserid = rowdata.alarmuserid;
						if (operate == 2) {
							assignM(2);
						}
					}

         function onSubmit()
        {
        	grid.setOptions({
        		parms: [
        		{
					name: 'depname',
					value: $("#dept").val()
				},
        		{
					name: 'username',
					value: $("#alramname").val()
				}
				]
        	});
         	grid.loadData(true);
        }
	
	 function assignM(flg){      		
			 if(flg == 2){
			 	    $("#department").ligerGetComboBoxManager().selectValue(depid);  
			 		document.getElementById("username").value = username;
	               	document.getElementById("phone").value = phone;
	               	document.getElementById("alarm_key").value = alarm_key;
 	               	document.getElementById("dep_manag").value = dep_manag;
	               	document.getElementById("data_unit").value = data_unit; 
	               	document.getElementById("remark").value= bz;
	               	document.getElementById("alarmuserid").value= hiduserid;	               
			 }else if(flg == 1){
			 	    $("#department").ligerGetComboBoxManager().selectValue('');  
			 		document.getElementById("username").value="";
	               	document.getElementById("phone").value="";
	               	document.getElementById("alarm_key").value="";
 	               	document.getElementById("dep_manag").value = "";
	               	document.getElementById("data_unit").value = ""; 
	               	document.getElementById("remark").value="";
	               	document.getElementById("alarmuserid").value="";
			 }			 		
      }
					
					
					//工具条事件
					function itemclick(item) {

						var rows = grid.getCheckedRows();
						
						switch (item.icon) {
						case "add":
							if (operate != 1 && operate != 2) {
								setpage(0); //显示编辑界面
								setItemsd(0);//0-显示编辑区，添加隐藏按钮
							}
							operate = 1;
							assignM(1);

							break;
						case "modify":
							if (rows.length == 0) {
								alert('请选择一条你要修改用户信息的数据！');

							} else if (rows.length == 1) {
								if (operate != 1 && operate != 2) {
									setpage(0); //显示编辑界面
									setItemsd(0);//1-隐藏编辑区添加显示按钮
								}
								operate = 2;
								assignM(2);

							} else {
								alert('请选择一条你要修改用户信息的数据！');
							}
							break;
						case "delete":
							if (rows.length == 0) {
								alert('请选择一条你要删除的数据！')
							} else if (rows.length == 1) {
								$.ligerDialog
										.confirm('确定删除吗?',
												function(yes) {
													if (yes) {													
														jQuery
																.ajax({
																	type : 'post',
																	url : 'dxyhgl_removeUser.action?userID='
																			+ hiduserid,
																	async : false,
																	success : function(
																			data) {
																		if (data != null
																				&& typeof (data) != "undefined"
																				&& data == "1") {
															
																			onSubmit();
																			assignM(1);
																		} else {
																			$.ligerDialog
																					.error(outerror(jsondata));
																		}
																	},
																	error : function(
																			data) {
																	}
																});
													}
												});

							} else {
								alert("请选择一条你要删除的数据！");
							}
							break;
						case "unlock":
							if (rows.length == 0) {
								alert('请选择一条你要解锁的数据！')
							} else if (rows.length == 1) {
								$.ligerDialog
										.confirm(
												'您确定要为 &nbsp;' + opername
														+ '&nbsp;进行权限解锁吗?',
												function(yes) {
													if (yes) {
														jQuery
																.ajax({
																	type : 'post',
																	url : 'dxyhgl_Unlock.action?userID='
																			+ hiduserid,
																	async : false,
																	success : function(
																			data) {
																		if (data != null
																				&& typeof (data) != "undefined") {
																			var outData = eval('('
																					+ data
																					+ ')');
																			if (outData.ERRCODE != null
																					&& typeof (outData.ERRCODE) != "undefined") {
																				$.ligerDialog
																						.error(outerror(outData.ERRCODE));
																			} else if (outData.ERRMSG != null
																					&& typeof (outData.ERRMSG) != "undefined") {
																				$.ligerDialog
																						.error(outData.ERRMSG);
																			} else if (outData.CONFIRMMSG != null
																					&& typeof (outData.CONFIRMMSG) != "undefined") {
																				$.ligerDialog
																						.success(outData.CONFIRMMSG);
																			} else {
																				$.ligerDialog
																						.success("用户 ："
																								+ opername
																								+ " &nbsp;权限解锁成功,请该用户重新登录");
																			}
																		}
																	},
																	error : function(
																			data) {

																	}
																});
													}
												});

							} else {
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
						
		                <td align="left">
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
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">推送人姓名:</td>
		                <td align="left"></td>
		                
		                 <td align="left" class="l-table-edit-td" style="width:80px">电话号码:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">所属部门:</td>
		                <td align="left"></td>

		                <td align="left" class="l-table-edit-td" style="width:80px">报警关键字:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">短信发出中心:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">数据报警单位:</td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px" >备注:</td>
		                <td align="left"></td>
		            </tr>
		            <tr>
 		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="username" type="text" id="username" ltype="text"
		                	validate="{required:true,minlength:1,maxlength:10}"  />
		                </td>
		                <td align="left"></td>
		                 
		                
<!-- 		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="username" type="text" id="username" ltype="text" 
		                	validate="{required:true,minlength:1,maxlength:10}" />
		                </td>
		                <td align="left"></td> -->
		                
		                
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="phone" type="text" id="phone" ltype="text" 
		                	validate="{required:true,cellphone:true}" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="department" type="text" id="department" ltype="text"  
		                	validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                <td align="left"></td>
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="alarm_key" type="text" id="alarm_key" ltype="text"  
		                	validate="{required:true,minlength:1}" />
		                </td>

		                <td align="left"></td>
		                
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="dep_manag" type="text" id="dep_manag" ltype="text"  
		                	validate="{required:true,minlength:1}" />
		                </td>

		                <td align="left"></td>
		                
		                
		               <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="data_unit" type="text" id="data_unit" ltype="text"  
		                	validate="{required:true,minlength:1}" />
		                </td>

		                <td align="left"></td>
		                
		                
		                
		                
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input name="remark" type="text" id="remark" ltype="text"  />
		                </td>
		                <td align="left">
		                <input name="alarmuserid" type="hidden" id="alarmuserid" />
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