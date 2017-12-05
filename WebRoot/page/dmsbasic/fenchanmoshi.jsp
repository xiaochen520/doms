<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    var p_flowmeterlist;
    var i_flowmeterlist;
   
        $(function ()
        {
        	 
			 $("#P_FLOWMETER").ligerComboBox({
			 			selectBoxHeight:80,
             			hideOnLoseFocus:true,
						autocomplete:true,
			                data: [
			                    { text: '1号流量计', id: '1' },
			                    { text: '2号流量计', id: '2' },
			                    { text: '双流量计', id: '3' }
			                    
			                ], valueFieldID: 'test3',
						initText :''
			            }); 
			           p_flowmeterlist = $("#P_FLOWMETER").ligerGetComboBoxManager();
			             $("#I_FLOWMETER").ligerComboBox({
			             selectBoxHeight:80,
             			hideOnLoseFocus:true,
						autocomplete:true,
			                data: [
			                    { text: '1号流量计', id: '1' },
			                    { text: '2号流量计', id: '2' },
			                    { text: '双流量计', id: '3' }
			                ], valueFieldID: 'test3',
						initText :''
			            }); 
			 i_flowmeterlist = $("#I_FLOWMETER").ligerGetComboBoxManager();
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
		           
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function ()
            {
                alert(v.element($("#department_name")));
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
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;">

	<div id="mainsearch" style=" width:99%">
	 
	    
	         <form name="formsearch" method="post"  id="form1">
	          <table cellpadding="0" cellspacing="0" class="l-table-edit" style="width:240px">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:120px">注汽分配系数:</td>
		            </tr>   
		             <tr>
			             <td align="left" class="l-table-edit-td" >
			                	<input name="SPLIT_COEFFICIENT"  id="SPLIT_COEFFICIENT"   />
			                </td>
		                </tr>
		              <tr>  
		                <td align="left" class="l-table-edit-td" style="width:120px">P井注汽流量计:</td>
		               <td align="left" class="l-table-edit-td" style="width:120px">I井注汽流量计:</td>
		              </tr> 
		              <tr>
			              <td align="left" class="l-table-edit-td" >
		                	<input name="P_FLOWMETER" type="text" id="P_FLOWMETER"  />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
			                	<input name="I_FLOWMETER" type="text" id="I_FLOWMETER"   />
			                </td>
		                </tr>
		                
		            
            	</table>
	        
		 <!-- <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		   <div id="maingrid"></div>
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>  -->
	  
	
		    </form>
		    
		</div>

    
</body>
</html>