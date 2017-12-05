<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <title>井场信息</title>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
  
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
    <script type="text/javascript">
         var grid;
        $(function ()
        {	
        //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'jc_pageInit.action',
					async : false,
					
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=""){
					
						
						grid = $("#maingrid").ligerGrid(eval('(' + jsondata + ')'));
							
						}else{
							alert("数据库数据存在错误！");
						}
					},
					error : function(data) {
				
					}
				});
           
                
            $("#selecttree").ligerComboBox({
				url:'pcd_queryPcdlb.action',
				valueField: 'id',
				valueFieldID: 'pcdid',
				textField: 'text',
				selectBoxHeight:120,
				width: 120 ,
				hideOnLoseFocus:true,
				autocomplete:true,
				onSuccess:function (data){
				getJcQjInitData();
			},
			onSelected:function (data){
				liger.get("well_site").setValue(null);
				if ($("#pcdid").val() != 1) {
					jQuery.ajax({
					type : 'post',
					url : 'singleWell_searchWellSiteData.action',
					data: {"pcdid":data},
					success : function(jsondata) {
						if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
							liger.get("well_site").setData(eval('(' + jsondata + ')'));
						}
						else{
							liger.get("well_site").setData('');
						}
					},
					error : function(jsondata) {
						alert("请求数据失败，请重新查询");
					}
				});
			}
			else {
				getJcQjInitData();
			}
		}
	});

			$("#well_site").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'jcid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
				autocomplete:true
				
			});
        });
        
        //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			alert(id);
			alert(name);
			alert(datatype);
		}
		
           function onSubmit()
        {
        		
         	var pcd=$("#selecttree").val();
        	var jc=$("#well_site").val();
        	//var jc=$("#jcid").val();
        	
        	grid.setOptions({
        		parms: [{
					name: 'pcd',
					value: pcd
				},{
					name: 'jc',
					value: jc
				}]
        	});
         	grid.loadData(true);
            
        }

    
		       
		        //工具条事件
	      function itemclick(item) {
	      var rows = grid.getCheckedRows();
	          switch (item.icon) {
	              case "add":
	                 
	                 $.ligerDialog.open({
              	    			title: "增加井场信息",
              	    			url:'addJcInfo.jsp',
              	    			width: 450,
              	    			height: 450
              	    		});
	                  break;
	              case "view":
	                  if (rows.length == 0) { 
              	    		alert('请选择一条你要修改用户信息的数据！');
              	    
              	     }else if(rows.length == 1){
							$(rows).each(function (){ 
								str = this.ROLEID; 
							});
							//$.ligerDialog.open({ width: 600, height: 450, url:'user_updateOneUser.action?userID='+str, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: "修改用户信息" });
              	    		//url:'user_updateOneRole.action?roleID='+str,
              	    		$.ligerDialog.open({
              	    			title: "查看井场基本信息",
              	    			url:'viewJc.jsp?structureid='+str,
              	    			width: 450,
              	    			height: 450
              	    		});
              	    		
              	     }else{
              	     	alert('请选择一条你要修改用户信息的数据！');
              	     }
                  break;
	              case "modify":
	             
	                  if (rows.length == 0) { 
              	    		alert('请选择一条你要修改用户信息的数据！');
              	    
              	     }else if(rows.length == 1){
							$(rows).each(function (){ 
								str = this.ROLEID; 
							});
							//$.ligerDialog.open({ width: 600, height: 450, url:'user_updateOneUser.action?userID='+str, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: "修改用户信息" });
              	    		//url:'user_updateOneRole.action?roleID='+str,
              	    		//alert(str);
              	    		$.ligerDialog.open({
              	    			title: "修改井场信息",
              	    			url:'updateJc.jsp?structureid='+str,
              	    			width: 450,
              	    			height: 450
              	    		});
              	    		
              	     }else{
              	     	alert('请选择一条你要修改用户信息的数据！');
              	     }
                  break;
	              case "delete":
	              	  var selected = grid.getSelected();
	                  if (!selected) { LG.tip('请选择行!'); return ;}
	                  jQuery.ligerDialog.confirm('确定删除吗?', function (confirm) {
	                      if (confirm){
	                      	 jQuery.ajax({
							type: "post",
							url: "jc_removeJcInfoById.action?str="+selected.structureid,
							data: { 'selectIds':1 , 'status' : -1},
							timeout: '3000',
						/* 数据库删除成功后，删除表格中选中的行 */
						success: function(date,status){
						//alert(status);   
						if(status=="success"){
							 jQuery.ligerDialog.success("删除成功！");
							onSubmit();
						
						}else{
						  jQuery.ligerDialog.error("操作失败");
						}}
					});
	                      
	                      }
	                         
	                  });
	                  break;
	              case "pagelayout":
	                 top.f_addTab(null, '注水井信息页面布局设置', 'page/pagelayout/pagelayout.jsp');
	                 break;
	          }
	      }
	      function setJcQjInitData(jcData,qjData) {
		  		liger.get("well_site").setData(jcData);
		  	}
		  	function getJcQjInitData() {
		  		var qjData=[{ id: 1 , text: '' }];
		  		var jcData=[{ id: 1 , text: '' }];
		  		jQuery.ajax({
		  			type : 'post',
		  			url : 'singleWell_cascadeInit.action',
		  			success : function(jsondata) {
		  			var dataObj = $.parseJSON(jsondata);
		  				$.each(dataObj, function(key,val){
		  					if (key == 'jctext') {
		  						jcData = val;
		  					}
		  					else {
		  						qjData = val;
		  					}
		  				});
		  				setJcQjInitData(jcData,qjData);
		  			}
		  		});
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
 <div id="mainsearch" style=" width:98%">
    <div class="searchtitle">
        <span id="locationlab">&nbsp;<font color="red">添加和删除基础信息请刷新当前页面（右键当前选项卡页面刷新）！</font></span><img src="../../lib/icons/32X32/home.gif" />
        <div class="togglebtn"></div> 
    </div>
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form"> 
        	<table border="0" cellspacing="1">
				<tr>
				<td align="right" class="l-table-edit-td" style="width:80px">排采队：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="selecttree" name="selecttree"/>
	                </td>
	                <td align="left">
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">井场：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="well_site" name="well_site"/>
	                </td>
	                <td align="left">
	                </td>
	                 
	              <td align="left" class="l-table-edit-td" style="width:30px">
	                </td>
					
			
			<td align="left" class="l-table-edit-td" style="width:100px">
	                <a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
	            </td>	
	            <td>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"> *该查询功能支持模糊查询</font></td>
			
				</tr>
				
			</table>
        </form>
    </div>
  </div>
   
 <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
 
</body>
</html>
