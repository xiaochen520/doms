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
 	<script src="noBackspace.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var jklistbox;
    	var fzvalue;
    	var box;
        $(function ()
        { 
        	//fzvalue = parent.window.JKsearch_fz;
          // jklistbox = 
         //  $("#listbox1").ligerListBox({
         //       isShowCheckBox: false,
         //        isMultiSelect: false,
        //       url:'qjGourpInfo_getQJByJZId.action?qjzid='+$("#fzid").val(),
         //       width:160,
      //          height:290,
         //       valueFieldID: 'hidjkid',
         //       onSelect:function (data){
               	//parent.window.getnowtab(data);
					//alert(data);
			//	}
           // }); 
            
             $("#selecttree").ligerComboBox({
                width: 180,
                selectBoxWidth: 180,
                selectBoxHeight: 280, 
                resize :true,
                valueField: 'text',
                treeLeafOnly:false,
                tree: { 
                	url: 'system_getStructureData.action', 
	                nodeWidth : 100,
	                checkbox: false,
	                textFieldName:"text",
                    idFieldName:"id",
                    parentIDFieldName:"pid",
	                onBeforeExpand: onBeforeExpand,
	                onExpand: onExpand },
	                // manager = $("#tree1").ligerGetTreeManager();
                onSelected:function (data){
                }
            });
            // g.tree.ligerTree
           //manager = $("#selecttree").ligerGetTreeManager();
          manager = $("#selecttree").ligerGetComboBoxManager.tree.treeManager();
        });
       function onBeforeExpand(note)
        {
        	alert(note.data.id);
            if (note.data.children && note.data.children.length == 0)
            {
            	alert(JSON2.stringify(manager));
            	var nowid = note.data.id;
            	manager.loadData(note.target,'system_getOrgChridData.action?id='+nowid+'&nowdata='+parseInt(Math.random()*100000));
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
	<input type="hidden" id="fzid"/>
     <div class="searchbox">
        <form id="formsearch" class="l-form"> 
        	<table border="0" cellspacing="1">
				<tr>
	                <td align="left" class="l-table-edit-td" style="width:200px">
	                	<input type="text" id="selecttree"/>
	                </td>
				</tr>
				
				<tr>
	                <td align="left" class="l-table-edit-td" style="width:200px">
	                	<input type="text" id="filtration"/>
	                </td>
				</tr>
				
			</table>
        </form>
    </div> 
 <div id="listbox1"></div> 
</body>
</html>
