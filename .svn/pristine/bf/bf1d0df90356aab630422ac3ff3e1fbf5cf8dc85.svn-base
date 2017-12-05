<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
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
		       
		            //设置焦点
		            $("#oldpassword").focus();
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
			                    var oldpassword = $("#oldpassword").val();
			                    var newpassword = $("#newpassword").val();
			                    var newpassword2 = $("#newpassword2").val();
			                    
			                    if(newpassword == newpassword2){
			                    	 jQuery.ajax({
										type : 'post',
										url : 'user_updatepassword.action?nowdata='+parseInt(Math.random()*100000),
										data: {"oldpassword":oldpassword ,"newpassword":newpassword},
										
										success : function(jsondata) { 
										$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
										if (jsondata != null && typeof(jsondata)!="undefined" && jsondata == 1){
												parent.$.ligerDialog.close();
												//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
												parent.$.ligerDialog.success("修改成功！");
											}else if(jsondata == -1){
												$("#errermgs").html("<font color='red'>修改失败</font>");
											}else if(jsondata == -2){
												$("#errermgs").html("<font color='red'>原始密码错误，请重新输入</font>");
											}
										},
										error : function(data) {
									
										}
									});
			                    }else{
			                    	
			                    	$("#errermgs").html("<font color='red'>新密码与确认密码输入不一致，请重新输入</font>");
			                    }
								
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

	 <div class="searchtitle">
        <span>修改用户密码<div id="errermgs"></div></span><img src="../../lib/icons/32X32/user.gif" />
    </div>

    <form name="form" method="post"  id="form">
		<div id="errorLabelContainer" class="l-text-invalid">
		</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
            
            <tr>
                <td align="right" class="l-table-edit-td">原始密码:</td>
                <td align="left" class="l-table-edit-td" style="width:160px"><input name="oldpassword" type="password" id="oldpassword" ltype="text"  validate="{required:true,minlength:6,maxlength:32}" /></td>
                <td align="left"></td>
            </tr>
           <tr>
                <td align="right" class="l-table-edit-td">新密码:</td>
                <td align="left" class="l-table-edit-td" style="width:160px"><input name="newpassword" type="password" id="newpassword" ltype="text"  validate="{required:true,minlength:6,maxlength:32}" /></td>
                <td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">确认密码:</td>
                <td align="left" class="l-table-edit-td" style="width:160px"><input name="newpassword2" type="password" id="newpassword2" ltype="text"  validate="{required:true,minlength:6,maxlength:32}" /></td>
                <td align="left"></td>
            </tr>
           
        </table>
		 <br />
		 <table>
		 	 <tr>
                <td align="right" class="l-table-edit-td"></td>
                <td align="left" class="l-table-edit-td" style="width:140px"></td>
                <td align="left"><input type="submit" value="保存" id="Button1" class="l-button l-button-submit" /> </td>
            </tr>
		 </table>
	
    </form>
    <div style="display:none">
    
</body>
</html>