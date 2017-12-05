<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="../../lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script> 
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	 <script src="../../noBackspace.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
		         
		            //设置焦点
		            $("#portalname").focus().select();
		            
		            
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
			                  	var portalname = $("#portalname").val();
								var portalurl = $("#portalurl").val();
				               
				                jQuery.ajax({
									type : 'post',
									url : 'right_addRight.action',
									data: {"portalname":portalname,"portalurl":portalurl},
									async : false,
									timeout : '3000',
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata == 1){
											parent.$.ligerDialog.close();
											//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
											parent.window.onSubmit();
											parent.$.ligerDialog.success("保存成功！");
											
											
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
	        <span>添加Portal菜单节点</span><img src="../../lib/icons/32X32/user.gif" />
	    </div>
   		 <br />
		<div id="errorLabelContainer" class="l-text-invalid">
		</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" id="table1">
            <tr>
                <td align="right" class="l-table-edit-td">PORTAL节点名称:</td>
                <td align="left" class="l-table-edit-td" style="width:120px"><input name="portalname" type="text" id="portalname" /></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">PORTAL节点地址:</td>
                <td align="left" class="l-table-edit-td" style="width:180px"><input name="portalurl" type="text" id="portalurl"  /></td>
                <td align="left"></td>
            </tr>
        
        </table>
		<br />
		
        
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>
    
</body>
</html>