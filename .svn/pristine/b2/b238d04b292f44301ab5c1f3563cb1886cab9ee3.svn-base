<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>
	<title>用户基础信息</title>
     <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>   
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/js/ligerui.expand.js" type="text/javascript"></script> 
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../lib/js/LG.js" type="text/javascript"></script>
     <script src="../../noBackspace.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js"></script>
   
    <script type="text/javascript">
    	var grid = null;
    	 $(function ()
        {
			 
			 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'user_pageInit.action',
					async : false,
					success : function(jsondata) { 
					//alert(jsondata);
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=""){
						var data = eval('(' + jsondata + ')');
						grid = $("#maingrid").ligerGrid({ columns: [
								{ display: '用户ID', name: 'USERID', align: 'left', width: 200, minWidth: 60 ,hide: true}, 
								{ display: '操作员代码', name: 'OPER_CODE',align: 'left', minWidth: 120 ,frozen:true}, 
								{ display: '操作员姓名', name: 'OPER_NAME',align: 'left', minWidth: 120 }, 
								{ display: '部门', name: 'DEPARTMENT'  ,align: 'center', minWidth: 120},  
								{ display: '角色', name: 'ROLES'  ,align: 'center', minWidth: 120},
								{ display: '口令', name: 'OPER_PASS',align: 'left', minWidth: 120 ,hide: true},
								{ display: '当前状态', name: 'OPER_SDBSADBSE',align: 'center', minWidth: 40 }, 
								{ display: '注册日期 ', name: 'AUDBSHOR_DATE' ,align: 'center', format:'yyyy-MM-dd hh:mm:ss',minWidth: 170}, 
								{ display: '状态改变日期', name: 'SDBSADBSE_DATE'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 170},
								{ display: '最后登录时间', name: 'L_LOGIN_DATE'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 120},
								{ display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 120}, 
								{ display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 120},
								{ display: '备注', name: 'REMARK',align: 'center',minWidth: 150} ],
								height:'60%',
								dataAction: 'server',
								url:'user_seachUser.action',
								pageParmName :'pageNo',
								sortnameParmName:'sort',
								sortorderParmName: 'order',  
								pagesizeParmName:'rowsPerpage', 
								selectRowButtonOnly:true,
								sePaper:true,
								pageSize:30 ,
								rownumbers:true,
								frozen:true,
								checkbox :false, 
								toolbar: { items: [
													{ text: '修改', click: itemclick, img: '../../lib/ligerUI/skins/icons/modify.gif',icon: 'modify' },
													{ text: '删除', click: itemclick, img: '../../lib/ligerUI/skins/icons/delete.gif',icon: 'delete' },
													{ text: '添加', click: itemclick, img: '../../lib/ligerUI/skins/icons/add.gif',icon: 'add' }
													]
										}
								});
						
							
						}else{
							alert("数据库数据存在错误！");
						}
					},
					error : function(data) {
				
					}
				});
               
			$("#username").ligerTip();
			$("#depname").ligerTip();
			$("#username").focus();
            
        });
       
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'username',
					value: $("#username").val()
				},{
					name: 'depname',
					value: $("#depname").val()
				}
				]
        	});
         	grid.loadData(true);
        }

       
        //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
              	 // $.ligerDialog.open({
              	  //  			title: "增加用户信息",
              	  //  			url:'adduser.jsp',
              	  //  			width: 500,
              	   // 			height: 500
              	   // 		});
              	   alert('add');
                  break;
              case "view":
                  
                  if (rows.length == 0) { 
              	    		alert('请选择一条你要查看用户详细信息的数据！');
              	    
              	     }else if(rows.length == 1){
							$.ligerDialog.open({
              	    			title: "查看用户信息",
              	    			url:'viewuser.jsp',
              	    			width: 500,
              	    			height: 500
              	    		});

              	     }else{
              	     	alert('请选择一条你要查看用户详细信息的数据！');
              	     }
                  
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改用户信息的数据！');
              	    
              	     }else if(rows.length == 1){
							
							//$.ligerDialog.open({ width: 600, height: 450, url:'user_updateOneUser.action?userID='+str, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: "修改用户信息" });
              	     		 $.ligerDialog.open({
              	    			title: "修改用户信息",
              	    			url:'updateuser.jsp',
              	    			width: 500,
              	    			height: 500
              	    		});
              	     }else{
              	     	alert('请选择一条你要修改用户信息的数据！');
              	     }
                  break;
              case "delete":
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要删除的数据！')
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          $(rows).each(function (){ 
											str = this.USERID; 
										});
										
			                          jQuery.ajax({
											type : 'post',
											url : 'user_removeUser.action?userID='+str,
											
											success : function(data) { 
											if (data != null && typeof(data)!="undefined" && data == 1){
													
													onSubmit();
												}else{
													alert("不存在符合输入条件数据，请重新输入查询条件 ！");
												}
											},
											error : function(data) {
										
											}
										});
		                        	 } 
              	     		 });
							
							

              	     }else{
              	     	alert("请选择一条你要删除的数据！");
              	     }
                  break;
              	 case "pagelayout":
                  top.f_addTab(null, '用户信息布局设置', 'page/pagelayout/pagelayout.jsp');
                 break;
          }
      }

    </script>
    <style type="text/css">
       /* 搜索框 */
	.searchtitle{ padding-left:28px; position:relative;}
	.searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
	.searchtitle span{ font-size:14px; font-weight:bold;}
	.searchtitle .togglebtn{ position:absolute; top:6px; right:15px; background:url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
	.searchtitle .togglebtn-down{ background-position:0px -10px;}
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

<body style="overflow-x:hidden; padding:2px;">
	    <div id="mainsearch" style=" width:99%">
	   <div class="searchtitle">
	        <span id="locationlab">&nbsp;</span>
	        <div class="togglebtn"></div> 
	    </div>
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	        <form id="formsearch" class="l-form"> 
	        	<table border="0" cellspacing="1">
					<tr>
						<td align="right" class="l-table-edit-td" style="width:60px">部门：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="depname" name = "depname" title="*此处输入字符会自动匹配，例如：输入“us”将下列列出 含此字符的用户名  ; "/>
		                </td>
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:60px">操作员：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="username" name = "username" title="*此处输入字符会自动匹配，例如：输入“李”将下列列出 含此字符的真实姓名  ; "/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
		                </td>
					</tr>
				
				</table>
	       </form>
	    <div class="searchbox">
	  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
	  <div id="maingrid"></div> 
	  </div>
	  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		
		
	  </div>
	 
</body>
</html>
