<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>SAGD管汇动态数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="../../lib/highcharts/highcharts.js"></script>
<!--     <script src="../../lib/highcharts/exporting.js"></script>    -->
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
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>    
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
   <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
 	
 	
 		<script type="text/javascript" src="sagdghdt.js"></script>
    <script type="text/javascript">

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
           
          
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;"><div align="right"> 
	 </div><div align="right"> 
	    </div><div class="searchbox"><div align="right"> 
	        </div><form id="formsearch" class="l-form"><div align="right">  
	        	</div><table border="0" cellspacing="1">
					<tr>

						<td class="l-table-edit-td" style="width:80px" align="right">所属采油站：</td>
						<td class="l-table-edit-td" style="width:80px" align="right">
							<input type="text" id="oilationname" name = "oilationname"/>	
						</td>
		                  <td class="l-table-edit-td" style="width:80px" align="right">管汇：</td>
		                <td class="l-table-edit-td" style="width:80px" align="right">
		                	<input type="text" id="ghmc" name = "ghmc"/>
		                </td>
		                
			<td align="right" class="l-table-edit-td" style="width:80px">开始日期：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="txtDate" ltype="date" /></td>
			<td align="right" class="l-table-edit-td" style="width:80px">结束日期：</td>
			<td align="left" class="l-table-edit-td" style="width:80px"><input type="text" id="txtDate1" ltype="date" /></td>

						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
					
		 <td style="padding-left: 20px;"><a href="javascript:onLine()" class="l-button" style="width:80px">动态曲线</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:80px">导出报表</a></td>
						
					</tr>
				
				</table>
				 </form>
		    
			</div>
			
	 <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		<div id="maingrid"></div>
		<div id="mainline" style="display: none;">
		<div id="main0" style="height:160px;width:1300px"></div>
		<div id="main1" style="height:160px;width:1300px"></div>
		<div id="main2" style="height:160px;width:1300px"></div>
	</div>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

</body>
</html>