<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>EXCLE导入</title>

        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
     <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
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
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
  
         <script type="text/javascript">
         	   //var pageid = '${param.pageid}';
        	   function initPage(){
        	     //alert(pageid);
         	    	//$("#title").val(pageid);
        	   }
         	   function submitExcel(){
		       var excelFile = $("#upload").val();
		       if(excelFile=='') {
		       		alert("请选择需上传的文件!");
		       		return false;
		       	}
		       if(excelFile.indexOf('.xls')==-1){
		       		alert("文件格式不正确，请选择正确的Excel文件(后缀名.xls)！");
		       		return false;
		       }
		       $("#fileUpload").submit();
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
    </style>
         
    </head>

	<body>
		<br><br><!-- 完文件传设置该表单enctype属性multipart/form-data -->
		<form id="fileUpload" action="upload.action" method="post" enctype="multipart/form-data">
			<div style="color: #0000CC;font-size:12px;"> 
			报表导入使用说明：报表导入失败是会有格式校验信息提示或错误EXCEL下载</br>
			导入成功后会跳转到成功提示页面  </br>
			每天凌晨2:00 ~ 1：00 允许用户进行报表导入操作，1:00 ~ 2:00 系统将自动清理前一天上传文件
			</div>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<table cellpadding="1">
			 	<tr>
			 		<td align="right" class="l-table-edit-td"  colspan="3">
			 			<div style="color: red" id="errermsg" > <s:fielderror></s:fielderror></div>
			 			<input type="hidden" id="title" name="title" value="<%=request.getParameter("pageid") %>" />
			 		</td>
			 	</tr>
			 	<tr>
			 		<td align="right" class="l-table-edit-td" style="width:80px">选择文件：</td>
			 		<td align="right" class="l-table-edit-td" style="width:80px"> <input type="file" id="upload" name="upload" /></td>
			 		
			 		<td align="right" class="l-table-edit-td" style="width:80px"><a href="javascript:submitExcel()" class="l-button"
						style="width:100px">导入</a></td>
			 	</tr>
			</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			
		</form>
	</body>
</html>