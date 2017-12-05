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
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta content='width=330, height=400, initial-scale=1' name='viewport' />
    <link href="lib/ligerUI/js/core1.20/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>  
    <script src="lib/ligerUI/js/core1.20/base.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core1.20/inject.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core1.20/ligerCheckBox.js" type="text/javascript"></script>  
    <script src="lib/ligerUI/js/core1.20/ligerButton.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core1.20/ligerListBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="lib/json2.js" type="text/javascript"></script> 
 	<script src="noBackspace.js" type="text/javascript"></script>
 	
 	<style type="text/css">
 	.l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
 	</style>
 	
    <script type="text/javascript">
         var  listboxDatas ;
         var jklistbox;
        $(function ()
        { 
        	
         jklistbox = $("#listbox1").ligerListBox({
	                isMultiSelect: false,   //是否多选
        			isShowCheckBox: false,  //是否选择复选框
	                data: '' ,
	                valueField: 'id',       //值成员
       				textField: 'text',
	                valueFieldID: 'hidnodeid',
	                height:40000,
	                onSelect:function (data,name,nodetype){
                	//var selecteds = jklistbox.getSelectedItems();
                	//alert(alert(JSON2.stringify(selecteds.data)));
                	alert(data);
                		//获取节点类型
                		 jQuery.ajax({
							type : 'post',
							url : 'system_getNodeType.action?nowdata='+parseInt(Math.random()*100000),
							data: {"nodeid":data},
							async : false,
							success : function(jsondata) { 
							if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
									var datas = eval('(' + jsondata + ')');
									alert(datas.NODETYPE);
									
								}
							},
							error : function(data) {
						
							}
						});
	                }
	            }); 
	            
        	$("#filtertext").keyup(function(){
					//alert($("#filtertext").val());
				//过滤数据以及下面子节点
				var noded = $("#filtertext").val();
					if(noded != null && typeof(noded)!="undefined" && noded != '' ){
						 jQuery.ajax({
							type : 'post',
							url : 'system_getFilterOrgData.action?nowdata='+parseInt(Math.random()*100000),
							data: {"node":noded},
							async : false,
							success : function(jsondata) { 
							if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
									jklistbox.setData(eval('(' + jsondata + ')')); 
									
								}else{
									jklistbox.setData('');
								}
							},
							error : function(data) {
						
							}
						});
					}
               
			
			}).keyup();
           
        });
     
    </script>
</head>
<body style="padding:10px"> 
	<form name="formsearch" method="post"  id="form1">
			<table border="0" cellspacing="1">
						<tr>
			               <td align="right" class="l-table-edit-td" style="width:50px">过滤：</td>
			                <td align="left" class="l-table-edit-td" >
			                	<input type="text" id="filtertext" name = "filtertext"/>
			                </td>
			            </tr>
			         
			            <tr>
			                <td align="right" class="l-table-edit-td" colspan="2">
			                	<div id="listbox1"></div> 
			                </td>
			            </tr>
			</table>
	</form>
		
     

</body>
</html>
