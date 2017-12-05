<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>添加PORTAL菜单节点</title>
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
    <script src="../../columnnamedisplay.js" type="text/javascript"></script>

    <script type="text/javascript">
    	var columnname = '${param.columnname}';
	    var namedisplay = '${param.namedisplay}';
	    var textValue = '${param.textValue}';
					                	
        $(function () {
        
        	var comont1 = "*请修改"+getColumName(columnname)+"数值,当填写值大于<span style='color: red;'>" +namedisplay+"</span>时返回表格颜色为红色";
        	$("#comont").html(comont1);
        	$("#sign").html(getColumName(columnname)+":");
        	$("#pram").val(textValue);
			$("#pram").focus(); 
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
                		var param = "";
                		
                		if ($("#pram").val() != null && $("#pram").val() != "" && typeof($("#pram").val())!="undefined"){
                			param = $("#pram").val();
                			//alert(param +"--------------"+namedisplay+ +"--------------"+param > namedisplay);
                			if(parseFloat(param) > parseFloat(namedisplay)){
                			
                				param = "<span style='color: red;'>"+param+"</span>";
                			}
                			
                		}
						parent.pwaterparam = param;
                    	parent.$.ligerDialog.close();
                    	parent.$(".l-dialog,.l-window-mask").remove();                	
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {


            });
        });  
        
    
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

<body style="overflow-x:hidden; padding:2px;" >
	 <div id="mainsearch" style=" width:99%">
	 
	 	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	 		
	 		<div id="comont">
	 			
	 		</div>
	 	
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
		               <td align="left" class="l-table-edit-td" style="width:100px" id='sign'></td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<input type="text" id="pram" name = "pram" ltype="number"   />
		                </td>
		             
		                <td align="right" class="l-table-edit-td" ></td>
		                <td align="right" class="l-table-edit-td" style="width:100px">
		                	<input type="submit" value="确认" id="Button1" class="l-button l-button-submit" />
		                </td>
					</tr>
				
				</table>
		   
		    </form>
		    
		    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		    
		</div>
    
</body>
</html>