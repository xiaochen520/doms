<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
   <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>  
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/core/inject.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>  
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var gltreeid = "MENU014";
    	var gltreemanager;
    	
    	
        $(function ()
        { 
        
        	$("#gltree").ligerTree(
	            {
	                data :'',
	                nodeWidth : 90,
	                checkbox: false,
	                onBeforeExpand: onBeforeExpandgl,
	                onExpand: onExpand,
	                onSelect: function (node){
	                	//menujudge(node.data.id,node.data.text,node.data.nodetype,node.data.basid);
	                	parent.boilerid = node.data.id;
						parent.boilername = node.data.text;
						parent.boileridtype = node.data.nodetype;
	                }
	            });
				
	            gltreemanager = $("#gltree").ligerGetTreeManager();
	            initglTree();
	            
        });
        
          function initglTree(){
           
	           	jQuery.ajax({
					type : 'post',
					url : 'system_getMenuTreeData.action?nowdata='+parseInt(Math.random()*100000),
					data: {"menuid":gltreeid},
					async : false,
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=''){
							//给树赋值
							gltreemanager.clear();
							gltreemanager.setData(eval('(' + jsondata + ')')); 
							
						}else{
							//alert(1);
							gltreemanager.clear();
						}
					},
					error : function(data) {
				
					}
				});
           	
           } 
           
           
               function onBeforeExpandgl(note)
        {
            if (note.data.children && note.data.children.length == 0)
            {
            	var nowid = note.data.id;
            	gltreemanager.loadData(note.target,'system_getOrgChridData.action?menuid='+gltreeid+'&id='+nowid);
              
            }
        }
        function onExpand(note)
        { 
        } 
      
    </script>
     <style type="text/css"> 
    .l-table-edit {}
    .l-table-edit-td{ padding:2px;}
    html,body
	   {
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
<body style="padding:1px"> 
	
 	<ul id="gltree" style="margin-top:3px;"></ul>
</body>
</html>
