<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String name = new String(request.getParameter("menuname").getBytes("ISO-8859-1"),"UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>修改PORTAL菜单节点</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
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
    
    
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script> 
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/js/ligerui.expand.js" type="text/javascript"></script> 
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    
    <script type="text/javascript">
					                	
    var id = '${param.id}';
   	var name = '<%= name%>';
    var url  = '${param.url}';
     var checkRight = false;
    var rightid = "";
        //var SIGNData = { Rows: [{ "PARAMID": null, "PORTALMENUID": null, "PARAMNAME": "用户名", "PARAMVALUE":null },{ "PARAMID": null, "PORTALMENUID": null, "PARAMNAME": "密码", "PARAMVALUE":null }], Total: 2 };
      	//alert(SIGNData)
        $(function () {
        
        	  $('input:checkbox').ligerCheckBox();
            
             $("#chk1").change(function () {
             checkRight = this.checked;
              
              });
              $("#menuname").val(name);
              $("#menuaddr").val(url);
             
             if(id != null && id != "" && typeof(id)!="undefined"){
        	 		 jQuery.ajax({
						type : 'post',
						url : 'portal_seachUpdateData.action?id='+id,
						async : true,
						success : function(data) { 
							if (data != null && typeof(data)!="undefined"){
								var outData = eval('(' + data + ')');
								if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
									$.ligerDialog.error(outerror(outData.ERRCODE));
								}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
									$.ligerDialog.error(outData.ERRMSG);
								}else{
									//成功
									$("#menuorder").val(outData.MENUORDER);
									if(outData.RIGHTID != null && outData.RIGHTID != "" && typeof(outData.RIGHTID)!="undefined"){
										rightid = outData.RIGHTID;
										checkRight = true;
										//$("[name='chbox']").attr("checked",'true');//全选
										$('input:checkbox').ligerCheckBox().setValue(true);
									}
								}
									
							}
						},
						error : function(data) {
							
						}
					});
        	 	}
        	 	
        	 csgrid = $("#portalgrid").ligerGrid({
	                columns: [
	                { display: '参数ID', name: 'PARAMID', width: 50,hide: true},
	                { display: '菜单ID', name: 'PORTALMENUID', width: 50, hide: true},
	                { display: '参数名称', name: 'PARAMNAME',editor: { type: 'text' }, width: 120},
	                { display: '参数值', name: 'PARAMVALUE',editor: { type: 'text' }, width: 200},
	                { display: '备注', name: 'REMARK',editor: { type: 'text' }, width: 200,hide: true}
	                ],
	                enabledEdit: true,
	                checkbox :false,
	                dataAction: "server",
					url:"portal_seachPor.action?id="+id+"",
	                height:300,
	                usePager : false, 
	                 rownumbers:true,
	                toolbar: { items: [
	                { text: '增加', click: itemclick, icon: 'add',img: '../../lib/ligerUI/skins/icons/add.gif' },
	                { line: true },
	                { text: '删除', click: itemclick, icon: 'delete',img: '../../lib/ligerUI/skins/icons/delete.gif' }
	                ]
	                }
	            });  
        	
             $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                //调试状态，不会提交数据的
                debug: true,
                errorPlacement: function (lable, element)
                {

                    if (element.hasClass("l-textarea"))
                    {
                        element.addClass("l-textarea-invalid");
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().addClass("l-text-invalid");
                    }
                    $(element).removeAttr("title").ligerHideTip();
                    $(element).attr("title", lable.html()).ligerTip();
                },
                success: function (lable)
                {
                	if(lable.attr("for")!=""){
                		var element = $("#" + lable.attr("for"));
	                    if (element.hasClass("l-textarea"))
	                    {
	                        element.removeClass("l-textarea-invalid");
	                    }
	                    else if (element.hasClass("l-text-field"))
	                    {
	                        element.parent().removeClass("l-text-invalid");
	                    }
	                    $(element).removeAttr("title").ligerHideTip();
                	}
                		
                    
                },
                submitHandler: function ()
                {
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
                alert(v.element($("#role_name")));
            });
        });  
   
    function itemclick(item)
        {
             switch (item.icon) {
              case "add":
              		csgrid.addRow({ "PARAMID": null, "PORTALMENUID": null, "PARAMNAME": null, "PARAMVALUE":null});
                  break;
              case "delete":
              		csgrid.deleteSelectedRow();
              	break;
             }
        }
         function onSubmit()
        {
        	 var rows = csgrid.getData();
        	 var updateRowDate = JSON2.stringify(rows);
        		//var PARAMNAME = updateRowDate.PARAMNAME ;
        		//alert(updateRowDate)
        	if($("#menuname").val() == ""){
        		$.ligerDialog.warn('菜单名称不能为空');
        	}else if($("#menuaddr").val() == ""){
        		$.ligerDialog.warn('菜单地址不能为空');
        	}else{
        		jQuery.ajax({
						type : 'post',
						url : 'portal_updatePortalNode.action',
						async : false,
						data: {"id" :id, "menuname":$("#menuname").val(),"menuaddr":$("#menuaddr").val(),
								"MENU_ORDER":$("#menuorder").val(),
								"RIGHTS":checkRight,
								"RIGHTID":rightid,
								"updateRowDate":updateRowDate},
						success : function(data) { 
							if (data != null && typeof(data)!="undefined"){
								var outData = eval('(' + data + ')');
								if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
									$.ligerDialog.error(outerror(outData.ERRCODE));
								}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
									$.ligerDialog.error(outData.ERRMSG);
								}else{
									//成功
									parent.$.ligerDialog.close();
									//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
									//parent.window.refreshNode(outData.JSONDATA);
									parent.window.refreshTree();
									parent.$.ligerDialog.success("修改成功！");
								}
									
							}
						},
						error : function(data) {
					
						}
					});
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
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;" >
	 <div id="mainsearch" style=" width:99%">
	 
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
						
		               <td align="right" class="l-table-edit-td" style="width:60px">菜单名称：</td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<input type="text" id="menuname" name = "menuname" ltype="text" validate="{required:true}"  />
		                </td>
		             
		                <td align="right" class="l-table-edit-td" style="width:60px">菜单地址：</td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<input type="text" id="menuaddr" name = "menuaddr" ltype="text" validate="{required:true}" />
		                </td>
					</tr>
					<tr>
						
		               <td align="right" class="l-table-edit-td" style="width:60px">菜单顺序：</td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<input type="text" id="menuorder" name = "menuorder" ltype="text" validate="{required:true}"  />
		                </td>
		             
		                <td align="right" class="l-table-edit-td" style="width:60px">
							<input type="checkbox" name="chbox" id="chk1" />
						</td>
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	添加显示菜单权限
		                </td>
					</tr>
				</table>
		  	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  		PORTAL菜单-参数设置 <font color="red"> *设置连接所需参数名称及值 </font>
		  	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			 <div id="portalgrid" style="width: 370px;"></div> 
		  	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  	<table>
		  		<tr>
		  			<td align="right" class="l-table-edit-td" ></td>
	                <td align="right" class="l-table-edit-td" style="width:100px">
	                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">确定</a>
	                </td>
	                
	                <td align="right" class="l-table-edit-td" ></td>
		  		</tr>
		  	</table>
	  
		   
		    </form>
		    
		</div>
    
</body>
</html>