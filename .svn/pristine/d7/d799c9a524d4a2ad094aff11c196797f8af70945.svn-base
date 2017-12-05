<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>
 <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta content='width=330, height=400, initial-scale=1' name='viewport' />
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="../../lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script> 
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
    
     <link href="../../lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" />
	 <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>   
	 <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" /> 
	 <script src="../../lib/js/common.js" type="text/javascript"></script> 
	 <script src="../../lib/js/ligerui.expand.js" type="text/javascript"></script> 
	 <script src="../../noBackspace.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
				var str = '${param.roleID}';
				
		             //获取菜单树
                jQuery.ajax({
					type : 'post',
					url : 'role_accredit.action?roleID='+str,
					async : false,
					timeout : '3000',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" ){
							var datas = eval('(' + jsondata + ')');
							 //权限基础信息
							 var rolejson = datas[0].ROLEJSON;
							 //页面显示权限
							 var jsonArrXIANSHI = datas[0].JSONARRXIANSHI;
							 //页面布局权限
							 var jsonArrBUJU = datas[0].JSONARRBUJU;
							 //添加权限
							 var jsonArrTIANJIA = datas[0].JSONARRTIANJIA;
							 //删除权限
							 var jsonArrSHANCHU = datas[0].JSONARRSHANCHU;
							 //修改权限
							 var jsonArrGENGXIN = datas[0].JSONARRGENGXIN;
							 //查询权限
							// var jsonArrCHAXUN = datas[0].JSONARRCHAXUN;
							 //其他权限
							 var jsonArrQITA = datas[0].JSONARRQITA;
				            
				            if(rolejson != null && typeof(rolejson)!="undefined" && rolejson != "" ){
				           		 $("#roleid").val(rolejson[0].ROLEID);	
				            	$("#rolename").val(rolejson[0].ROLE_NAME);	
				            	$("#roledescribed").val(rolejson[0].ROLE_DESCRIBED);	
				            	
				            }
				            
				             //页面显示权限
				            if (jsonArrXIANSHI != null && typeof(jsonArrXIANSHI)!="undefined" && jsonArrXIANSHI.length != 0){
				            	var htmlXIANSHI ="";
				            	for(var i=0; i<jsonArrXIANSHI.length; i++) {  
								 	if(jsonArrXIANSHI[i].RIGHTCHICKED == 0){
								 		htmlXIANSHI = htmlXIANSHI+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightXIANSHIchbox' hight='80px'  value='"+jsonArrXIANSHI[i].RIGHTID+"'/>"+jsonArrXIANSHI[i].RIGHTDESCRIBE+"</td></tr>"
								 	}else{
								 		htmlXIANSHI = htmlXIANSHI+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightXIANSHIchbox' hight='80px' checked='checked' value='"+jsonArrXIANSHI[i].RIGHTID+"'/>"+jsonArrXIANSHI[i].RIGHTDESCRIBE+"</td></tr>"
								 	}
 
								} 
								$("#xianshitable").html(htmlXIANSHI);
								
				            }
				            
				            //页面布局权限
				            if (jsonArrBUJU != null && typeof(jsonArrBUJU)!="undefined" && jsonArrBUJU.length != 0){
				            	var htmlBUJU ="";
				            	for(var i=0; i<jsonArrBUJU.length; i++) {  
								 	if(jsonArrBUJU[i].RIGHTCHICKED == 0){
								 		htmlBUJU = htmlBUJU+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightBUJUchbox' hight='80px'  value='"+jsonArrBUJU[i].RIGHTID+"'/>"+jsonArrBUJU[i].RIGHTDESCRIBE+"</td></tr>"
								 	}else{
								 		htmlBUJU = htmlBUJU+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightBUJUchbox' hight='80px' checked='checked' value='"+jsonArrBUJU[i].RIGHTID+"'/>"+jsonArrBUJU[i].RIGHTDESCRIBE+"</td></tr>"
								 	}
 
								} 
								$("#bujutable").html(htmlBUJU);
								
				            }
				            
				            //添加权限
				            if (jsonArrTIANJIA != null && typeof(jsonArrTIANJIA)!="undefined" && jsonArrTIANJIA.length != 0){
				            	var htmlTIANJIA ="";
				            	for(var i=0; i<jsonArrTIANJIA.length; i++) {  
								 	if(jsonArrTIANJIA[i].RIGHTCHICKED == 0){
								 		htmlTIANJIA = htmlTIANJIA+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightTIANJIAchbox' hight='80px'  value='"+jsonArrTIANJIA[i].RIGHTID+"'/>"+jsonArrTIANJIA[i].RIGHTDESCRIBE+"</td></tr>"
								 	}else{
								 		htmlTIANJIA = htmlTIANJIA+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightTIANJIAchbox' hight='80px' checked='checked' value='"+jsonArrTIANJIA[i].RIGHTID+"'/>"+jsonArrTIANJIA[i].RIGHTDESCRIBE+"</td></tr>"
								 	}
 
								} 
								$("#addtable").html(htmlTIANJIA);
								
				            }
				            
				            //删除权限
				            if (jsonArrSHANCHU != null && typeof(jsonArrSHANCHU)!="undefined" && jsonArrSHANCHU.length != 0){
				            	var htmlSHANCHU ="";
				            	for(var i=0; i<jsonArrSHANCHU.length; i++) {  
								 	if(jsonArrSHANCHU[i].RIGHTCHICKED == 0){
								 		htmlSHANCHU = htmlSHANCHU+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightSHANCHUchbox' hight='80px'  value='"+jsonArrSHANCHU[i].RIGHTID+"'/>"+jsonArrSHANCHU[i].RIGHTDESCRIBE+"</td></tr>"
								 	}else{
								 		htmlSHANCHU = htmlSHANCHU+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightSHANCHUchbox' hight='80px' checked='checked' value='"+jsonArrSHANCHU[i].RIGHTID+"'/>"+jsonArrSHANCHU[i].RIGHTDESCRIBE+"</td></tr>"
								 	}
 
								} 
								$("#deletetable").html(htmlSHANCHU);
								
				            }
				            
				            //修改权限
				            if (jsonArrGENGXIN != null && typeof(jsonArrGENGXIN)!="undefined" && jsonArrGENGXIN.length != 0){
				            	var htmlGENGXIN ="";
				            	for(var i=0; i<jsonArrGENGXIN.length; i++) {  
								 	if(jsonArrGENGXIN[i].RIGHTCHICKED == 0){
								 		htmlGENGXIN = htmlGENGXIN+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightGENGXINchbox' hight='80px'  value='"+jsonArrGENGXIN[i].RIGHTID+"'/>"+jsonArrGENGXIN[i].RIGHTDESCRIBE+"</td></tr>"
								 	}else{
								 		htmlGENGXIN = htmlGENGXIN+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightGENGXINchbox' hight='80px' checked='checked' value='"+jsonArrGENGXIN[i].RIGHTID+"'/>"+jsonArrGENGXIN[i].RIGHTDESCRIBE+"</td></tr>"
								 	}
 
								} 
								$("#updatetable").html(htmlGENGXIN);
								
				            }
				           
				             //其他权限
				            if (jsonArrQITA != null && typeof(jsonArrQITA)!="undefined" && jsonArrQITA.length != 0){
				            	var htmlQITA ="";
				            	for(var i=0; i<jsonArrQITA.length; i++) {  
								 	if(jsonArrQITA[i].RIGHTCHICKED == 0){
								 		htmlQITA = htmlQITA+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightQITAchbox' hight='80px'  value='"+jsonArrQITA[i].RIGHTID+"'/>"+jsonArrQITA[i].RIGHTDESCRIBE+"</td></tr>"
								 	}else{
								 		htmlQITA = htmlQITA+"<tr><td class='l-table-edit-td' style='width:200px'><input type='checkbox' name='rightQITAchbox' hight='80px' checked='checked' value='"+jsonArrQITA[i].RIGHTID+"'/>"+jsonArrQITA[i].RIGHTDESCRIBE+"</td></tr>"
								 	}
 
								} 
								$("#othertable").html(htmlQITA);
								
				            }
				            
				            
						}else{
							alert("数据库数据存在错误！");
						}
					},
					error : function(data) {
				
					}
				});
				
		            
		            $.metadata.setType("attr", "validate");
			            var v = $("form").validate({
			                errorPlacement: function (lable, element)
			                {
			                    if (element.hasClass("l-textarea"))
			                    {
			                        element.ligerTip({ content: lable.html(), target: element[0] }); 
			                    }
			                    else if (element.hasClass("l-text-field"))
			                    {
			                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
			                    }
			                    else
			                    {
			                        lable.appendTo(element.parents("td:first").next("td"));
			                    }
			                },
			                success: function (lable)
			                {
			                    lable.ligerHideTip();
			                    lable.remove();
			                },
			                submitHandler: function ()
			                {
			                    $("form .l-text,.l-textarea").ligerHideTip();
			                    //获取分配角色权限
			                    var chk_value =[];    
							  $('input[name="rightchbox"]:checked').each(function(){    
							   chk_value.push($(this).val());    
							  }); 
							  
							 //获取分配页面显示权限
							 var chk_XIANSHI =[];
							 //获取分配页面布局权限
							 var chk_BUJU =[];
							 //获取分配添加权限
							 var chk_TIANJIA =[];
							 //获取分配删除权限
							 var chk_SHANCHU =[];
							 //获取分配修改权限
							 var chk_GENGXIN =[];
							 //获取分配查询权限
							// var chk_CHAXUN =[];
							 //获取分配其他权限
							 var chk_QITA =[];
							 
							 $('input[name="rightXIANSHIchbox"]:checked').each(function(){    
							   chk_XIANSHI.push($(this).val());    
							  }); 
							  $('input[name="rightBUJUchbox"]:checked').each(function(){    
							   chk_BUJU.push($(this).val());    
							  }); 
							  $('input[name="rightTIANJIAchbox"]:checked').each(function(){    
							   chk_TIANJIA.push($(this).val());    
							  }); 
							  $('input[name="rightSHANCHUchbox"]:checked').each(function(){    
							   chk_SHANCHU.push($(this).val());    
							  }); 
							  $('input[name="rightGENGXINchbox"]:checked').each(function(){    
							   chk_GENGXIN.push($(this).val());    
							  }); 
							 // $('input[name="rightCHAXUNchbox"]:checked').each(function(){    
							  // chk_CHAXUN.push($(this).val());    
							 // }); 
							  $('input[name="rightQITAchbox"]:checked').each(function(){    
							   chk_QITA.push($(this).val());    
							  }); 
							 //所以选择权限
							 var chkrole = "";
							//添加获取分配页面显示权限
							 if(chk_XIANSHI.length!=0){
									for(var i=0;i<chk_XIANSHI.length;i++){
										chkrole +=chk_XIANSHI[i]+","
									}
							    }
							 //添加获取分配页面布局权限
							 if(chk_BUJU.length!=0){
									for(var i=0;i<chk_BUJU.length;i++){
										chkrole +=chk_BUJU[i]+","
									}
							    }
							 //添加获取分配添加权限
							 if(chk_TIANJIA.length!=0){
									for(var i=0;i<chk_TIANJIA.length;i++){
										chkrole +=chk_TIANJIA[i]+","
									}
							    }
							 //添加获取分配删除权限
							 if(chk_SHANCHU.length!=0){
									for(var i=0;i<chk_SHANCHU.length;i++){
										chkrole +=chk_SHANCHU[i]+","
									}
							    }
							 //添加获取分配修改权限
							 if(chk_GENGXIN.length!=0){
									for(var i=0;i<chk_GENGXIN.length;i++){
										chkrole +=chk_GENGXIN[i]+","
									}
							    }
							 //添加获取分配查询权限
							 //if(chk_CHAXUN.length!=0){
							//		for(var i=0;i<chk_CHAXUN.length;i++){
							//			chkrole +=chk_CHAXUN[i]+","
							//		}
							 //   }
							 //添加获取分配其他权限
								if(chk_QITA.length!=0){
									for(var i=0;i<chk_QITA.length;i++){
										chkrole +=chk_QITA[i]+","
									}
							    }
								var roleid = $("#roleid").val();
								
			                      //更新角色
				                jQuery.ajax({
									type : 'post',
									url : 'role_accreditDone.action?nowdata='+parseInt(Math.random()*100000),
									data: {"roleid":roleid,"rights":chkrole},
									async : false,
									timeout : '3000',
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata == 1){
											parent.$.ligerDialog.close();
											//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
											parent.window.onSubmit();
											parent.$.ligerDialog.success("修改成功！");
											
											
										}else{
											alert("数据库数据存在错误！");
										}
									},
									error : function(data) {
								
									}
								});
								
			                }
			            });
			            
			            $("form").ligerForm();
            });
            
           
			 //全选与反选
		//function checkall(cName) 
		//{ 
		//	alert(cName);
		//} 
			  
						 
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
        
            /* logo*/
		.searchtitle{ padding-left:28px; position:relative;}
		.searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
		.searchtitle span{ font-size:14px; font-weight:bold;}
		.searchtitle .togglebtn{ position:absolute; top:6px; right:15px; background:url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
		.searchtitle .togglebtn-down{ background-position:0px -10px;}
    </style>
    

</head>

<body style="padding:10px">
	 
    <form name="form" method="post"  id="form">
	    <div class="searchtitle">
	        <span >角色授权</span><img src="../../lib/icons/32X32/user.gif" />
	         <input name="roleid" type="hidden" id="roleid"  />
	         <input name="rolename" type="hidden" id="rolename"  />
	         <input name="roledescribed" type="hidden" id="roledescribed"  />
	    </div>
   		 <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
   		 <dir>
   		 <table cellpadding="0" cellspacing="0" class="l-table-edit" id="tableright">
   		 	<tr>
	   		 	<td class="l-table-edit-td" style="width:260px" valign="top">
	   		 	<fieldset id="xianshi">
	   		 	<!-- <input type="checkbox" id="allxianshi" onclick="javascript:checkall('rightXIANSHIchbox')"/> -->
	                <legend>显示功能菜单权限分配:</legend>
	                	<table cellpadding="0" cellspacing="0" class="l-table-edit" id="xianshitable" border="1">
	                		<tr>
				                <td align="right" class="l-table-edit-td"></td>
				            </tr>
	                	</table>
		                
	       			 </fieldset>
	   		 	</td>
	   		 	<td class="l-table-edit-td" style="width:260px" valign="top">
	   		 	<table cellpadding="0" cellspacing="0" class="l-table-edit" id="jihetable" >
	   		 		<tr>
	   		 		<td>
	   		 			<fieldset id="updateright">
				   		 	<!-- <input type="checkbox" id="allbuju"  /> -->
			                <legend>更新权限分配:</legend>
				                <table cellpadding="0" cellspacing="0" class="l-table-edit" id="updatetable" border="1">
				                		<tr>
							                <td align="right" class="l-table-edit-td"></td>
							            </tr>
				                	</table>
				                
			       			 </fieldset>
	   		 		</td>
	   		 	</tr>
	   		 	<tr>
	   		 		<td>
	   		 			<fieldset id="buju">
				   		 	<!-- <input type="checkbox" id="allbuju"  /> -->
			                <legend>页面布局权限分配:</legend>
				                <table cellpadding="0" cellspacing="0" class="l-table-edit" id="bujutable" border="1">
				                		<tr>
							                <td align="right" class="l-table-edit-td"></td>
							            </tr>
				                	</table>
				                
			       			 </fieldset>
	   		 		</td>
	   		 	</tr>
	   		 	<tr>
	   		 		<td>
	   		 			<fieldset id="otherright">
		   		 	<!-- <input type="checkbox" id="allother" /> -->
		                <legend>其他权限分配:</legend>
		                	<table cellpadding="0" cellspacing="0" class="l-table-edit" id="othertable" border="1">
		                		<tr>
					                <td align="right" class="l-table-edit-td"></td>
					            </tr>
		                	</table>
			                
		       			 </fieldset>
	   		 		</td>
	   		 	</tr>
	   		 	</table>
       			 
	   		 	</td>
   		 	</tr>
   		 	<tr>
	   		 	<td class="l-table-edit-td" style="width:260px" valign="top">
	   		 	<fieldset id="addright">
	   		 	<!-- <input type="checkbox" id="alladd" /> -->
	                <legend>添加权限分配:</legend>
	                	<table cellpadding="0" cellspacing="0" class="l-table-edit" id="addtable" border="1">
	                		<tr>
				                <td align="right" class="l-table-edit-td"></td>
				            </tr>
	                	</table>
		                
	       			 </fieldset>
	   		 	</td>
	   		 	<td class="l-table-edit-td" style="width:260px" valign="top">
	   		 	<fieldset id="deleteright">
	   		 	<!-- <input type="checkbox" id="alldelete"  /> -->
                <legend>删除权限分配:</legend>
	                <table cellpadding="0" cellspacing="0" class="l-table-edit" id="deletetable" border="1">
	                		<tr>
				                <td align="right" class="l-table-edit-td"></td>
				            </tr>
	                	</table>
	                
       			 </fieldset>
	   		 	</td>
   		 	</tr>
   		 	
   		 </table>
       	 </dir>
		<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" id="deletetable" border="0">
            <tr>
                <td align="right" class="l-table-edit-td" style="width: 380px;"></td>
                <td align="right" class="l-table-edit-td" ><input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> </td>
            </tr>
         </table>
		
    </form>
    
</body>
</html>