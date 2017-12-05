<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
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
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
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
    	var rightsum =0;
    	var tableLength = 6;  //表格显示列长
    	var str = '${param.roleID}';
    	 var grid = null;
    	 
    	  $(function ()
	        {
	            grid = $("#maingrid").ligerGrid({
	                columns: [
	                   
	                { display: '系统菜单', name: 'MENUID', id:'id1',width: 150, type: 'int', align: 'left' },
					{ display: '显示菜单', name: 'DISPALY', width: 70, align: 'left' },
					{ display: '添加', name: 'ADD', width: 70, align: 'left' },
					{ display: '修改', name: 'MODIFY', width: 70, align: 'left' },
					{ display: '空值修改', name: 'NULLMODIFY', width: 70, align: 'left' },
					{ display: '删除', name: 'DELETE', width: 70, align: 'left' },
					{ display: '审核', name: 'AUDIT', width: 70, align: 'left' },
					{ display: '授权', name: 'ACCREDIT', width: 70, align: 'left' },
					{ display: '注汽分配', name: 'ALLOCATION', width: 70, align: 'left' },
	                { display: '生成报表', name: 'REPORT', width: 70, align: 'left' }
	                ],
	              	 usePager: false,
	                 toolbar: { items: [{id:'saveid', text: '保存', click: itemclick, img: '../../lib/ligerUI/skins/icons/save.gif',icon: 'save' }]},
       				 enabledEdit: true, 
       				 clickToEdit: true, 
       				 fixedCellHeight: false,
       				 inWindow: true, 
       				 rownumbers: true,
       				 checkbox :false,
        			width: '98%', height: '100%',heightDiff:-14, rowHeight: 24,
	                //data: TreeDeptData,
	                dataAction: 'server',
	                url:'role_accredit.action?roleID='+str,
	                alternatingRow: false, 
	                tree: { columnId: 'id1' }
	            }
	            );
	        });
       
       
       //工具条事件
      function itemclick(item) {
      	switch (item.icon) {
      		 case "save":
					$("#form1").submit();
					break;
      	}
      
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
        
    </style>
</head>
<body  style="padding:10px">
  <form name="formsearch" method="post"  id="form1">
	<div id="mainsearch" style=" width:99%">
		 <div id="maingrid"></div> 
	</div>
	</form>
</body>
</html>
