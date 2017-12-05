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
    <script src="lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
      <script src="lib/json2.js" type="text/javascript"></script> 
    <script type="text/javascript">
        var manager = null;

        $(function ()
        {
        
        
        
            $("#tree1").ligerTree(
            {
                url: 'system_getStructureData.action', 
                nodeWidth : 100,
                checkbox: false,
                onBeforeExpand: onBeforeExpand,
                onExpand: onExpand
            });
            manager = $("#tree1").ligerGetTreeManager();
        });
     
        function onBeforeExpand(note)
        {
            if (note.data.children && note.data.children.length == 0)
            {
            	//alert(JSON2.stringify(note.data));
            	var nowid = note.data.id;
            	manager.loadData(note.target,'system_getOrgChridData.action?id='+nowid+'&nowdata='+parseInt(Math.random()*100000));
              
            }
        }
        function onExpand(note)
        { 
        }
     
    </script>
</head>
<body style="padding:10px"> 
    <div  >
    <ul id="tree1" > 
    </ul> 
    </div>
        <div style="display:none">
     
    </div>
</body>
</html>
