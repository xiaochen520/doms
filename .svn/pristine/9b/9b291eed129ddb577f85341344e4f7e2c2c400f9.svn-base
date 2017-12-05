<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>portal菜单维护</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script type="text/javascript">
        var menu;
        var actionNodeID;
        var treemanager;
        var actionNode;
     
         function itemclick(item, i)
        {
        	var node = actionNode;
             switch (item.icon) {
              case "add":
              		if(node && node.data && node.data.nodetype < 3){
              			var nowid = node.data.id;
              				   $.ligerDialog.open({
              	    			title: "添加PORTAL菜单 节点",
              	    			url:'addportlNode.jsp?pid='+nowid+'&nodetype='+node.data.nodetype,
              	    			width: 420,
              	    			height: 550
              	    		});
              			
              		}else{
              			$.ligerDialog.warn('请选择小于3级的节点进行添加操作');
              		}
	             	
                  break;
              case "modify":
              		var id = node.data.id;
              		var name = node.data.text;
              		var url = node.data.url
              		//alert(url)
                   if(node && node.data && node.data.nodetype >0 && node.data.nodetype <4){
              				   $.ligerDialog.open({
              	    			title: "修改PORTAL菜单 节点",
              	    			url:'updateportlNode.jsp?id='+id+'&menuname='+encodeURIComponent(name)+'&url='+encodeURIComponent(url),
              	    			width: 420,
              	    			height: 550
              	    		});
              			
              		}else{
              			$.ligerDialog.warn('请选择进行更新的PORTAL菜单树节点');
              		}
                  break;
              case "delete":
	              if(node && node.data && node.data.nodetype > 1){
	              	var nowid = node.data.id;
	              	jQuery.ajax({
						type : 'post',
						url : 'portal_removePortalNode.action',
						async : false,
						data: {"nowid":nowid,"nodetype":node.data.nodetype},
						success : function(data) { 
							if (data != null && typeof(data)!="undefined"){
								var outData = eval('(' + data + ')');
								if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
									$.ligerDialog.error(outerror(outData.ERRCODE));
								}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
									$.ligerDialog.error(outData.ERRMSG);
								}else{
									//成功
									//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
									treemanager.remove(actionNode.data);
									$.ligerDialog.success("删除成功成功！");
									
								}
									
							}
						},
						error : function(data) {
							
						}
					});
	              }else{
	              	$.ligerDialog.warn('请选择不为根节点的菜单节点进行删除~！');
	              }
              		
              	 	
                  break;
 				case "reload":
 					refreshTree();
 					break;
                case "open":
                	var name = node.data.text;
              		var url = node.data.url
              		var id = node.data.id;
              		//alert(id)
              		jQuery.ajax({
						type : 'post',
						url : 'portal_openPortalMenu.action',
						async : false,
						data: {"id":id},
						success : function(data) { 
							if (data != null && typeof(data)!="undefined"){
								var outData = eval('(' + data + ')');
								if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
									$.ligerDialog.error(outerror(outData.ERRCODE));
								}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
									$.ligerDialog.error(outData.ERRMSG);
								}else{
									//成功
									
									if(outData.CONFIRMMSG.indexOf("http://") != -1){
										//if('http://www.baidu.com'.equles(outData.CONFIRMMSG)){
										//	alert(1);
										//}else{
										//	alert(2);
										//}
										location.href=outData.CONFIRMMSG;
										//location.href='http://10.72.199.242/ProficyPortal/default.asp?user=administrator&password=fcpor&display=Users\Administrator\displays\油气处理\PIC_CYCLZ1S1_1号稠油流程简图';
									}else{
										$.ligerDialog.error("目标路径不正确，请检查系统路径是否完全");
									}
									
								}
									
							}
						},
						error : function(data) {
							
						}
					});
              		
                  break;   
              	
          }
        }
        $(function ()
        {
        	
        	//页面右键菜单权限
                jQuery.ajax({
					type : 'post',
					url : 'portal_pageInit.action',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							 menu = $.ligerMenu({ top: 100, left: 100, width: 120, items:data});
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
				
           
			 $("#tree1").ligerTree(
	            {
	            	type : 'post',
	                url : 'portal_seachPortalMenu.action?nowdata='+parseInt(Math.random()*100000),
	                nodeWidth : 100,
	                checkbox: false,
	                onBeforeExpand: onBeforeExpand,
	                onExpand: onExpand,
	                onSelect: function (node){
	                	//menujudge(node.data.id,node.data.text,node.data.nodetype,node.data.basid);
	                },
	                onContextmenu: function (node, e){ 
		                actionNode = node;
		                menu.show({ top: e.pageY, left: e.pageX });
		                return false;
		            }
	                
	            });
	            
	             treemanager = $("#tree1").ligerGetTreeManager();
	            // initTree();
         
        });
        	function initTree(){
        		 	jQuery.ajax({
					type : 'post',
					url : 'portal_seachPortalMenu.action?nowdata='+parseInt(Math.random()*100000),
					async : false,
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=''){
							//给树赋值
							treemanager.clear();
							treemanager.setData(eval('(' + jsondata + ')')); 
							
						}else{
							//alert(1);
							treemanager.clear();
						}
					},
					error : function(data) {
				
					}
				});
        	}
        	 function refreshTree(){
        	 	treemanager.clear();
             	initTree();
        	 }
        //子页面更新当前节点
         function refreshNode(JSONDATA){
         	if (JSONDATA != null && typeof(JSONDATA)!="undefined"){
         	//var datas = eval('(' + JSONDATA + ')');
         		//alert(datas);
         		treemanager.append(parentNode, JSONDATA);
         	}
         	//alert(JSON2.stringify( actionNode.data.children));
         	// if (actionNode.data && actionNode.data.children && actionNode.data.children.length == 0){
         	// alert(1);
         	// }else{
         //	 alert(2);
         	//	 treemanager.remove(actionNode.data.children);
         //	 }
         		
         	 	//treemanager.loadData(actionNode.target,'portal_seachPortalMenu.action?nodeid='+nodeid+'&nowdata='+parseInt(Math.random()*100000));
         
          	
        }
        
         function onBeforeExpand(note){
            if (note.data.children && note.data.children.length == 0)
            {
            	//alert(JSON2.stringify(note));
            	var nowid = note.data.id;
            	treemanager.loadData(note.target,'portal_seachPortalMenu.action?nodeid='+nowid+'&nowdata='+parseInt(Math.random()*100000));
              
            }
        }
        function onExpand(note)
        { 
        } 
    </script>
</head>
<body style="padding:10px">  
    <!--带复选框和Icon-->
    <div style="width:500px; height:450px; margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
    <ul id="tree1">
       
    </ul>
    </div> 
     <div style="width:500px; height:450px; margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
  		PORTAL菜单维护方法: </br>
  		<font color="red">
  		1、添加PORTAL菜单时,要添加目录路径的全路径如：http://10.72.199.242:8080/dmsweb
  		2、添加PORTAL菜单时,菜单顺序设置,通过设置菜单顺序,角色授权及菜单显示时系统按照其设置值排序；菜单顺序添加方法：</br>
  		&nbsp;&nbsp;根节点顺序值:20000 </br>
  		&nbsp;&nbsp;二级节点第一个顺序值为20100第二个为20200 </br>
  		&nbsp;&nbsp;三级节点父节点为20100的叶子节点第一个顺序值为20101第二个为20102 </br>
  		&nbsp;&nbsp;三级节点父节点为20200的叶子节点第一个顺序值为20201第二个为20202 </br>
  		每级节点允许用户自定义添加100个节点; </br>
  		3、添加显示菜单权限:操作员通过选择添加显示菜单权限按钮，系统自动生成显示权限;</br>
  		&nbsp;&nbsp;添加时选择添加显示菜单权限按钮，系统自动生成显示权限;</br>
  		&nbsp;&nbsp;添加时不选择添加显示菜单权限按钮，系统不会生成显示权限;</br>
  		&nbsp;&nbsp;修改时选择添加显示菜单权限按钮，存在更新显示权限,不存在生成显示权限;</br>
  		&nbsp;&nbsp;修改时不选择添加显示菜单权限按钮，存在删除显示权限,不存在不生成显示权限;</br>
  		PORTAL显示菜单权限同DMS功能列表相同,需要管理员在角色授权页面授权后,角色才能拥有显示权限。
  		</font>
    </div> 

</body>
</html>
