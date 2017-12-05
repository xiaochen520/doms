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
    	var tableLength = 7;  //表格显示列长
    	$(function () {
			var str = '${param.roleID}';
				
		             //获取菜单树
                jQuery.ajax({
					type : 'post',
					url : 'role_accredit.action?roleID='+str,
					async : false,
					timeout : '3000',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" ){
							var datas = eval('(' + jsondata + ')');
							$("#roleid").val(datas.ROLEID);	
				            $("#rolename").val(datas.ROLE_NAME);	
				             var htmlXIANSHI ="<tr style='position:relative;top:expression_r(this.offsetParent.scrollTop-2);'><th width='20%' align='center' >系统菜单</th><th  align='center' colspan='"+tableLength+"'>权限选项</th> </tr>";
							 //页面显示权限
							 var RIGHTSIN = datas.RIGHTSIN;
				            
				             //页面显示权限
				            if (RIGHTSIN != null && typeof(RIGHTSIN)!="undefined" && RIGHTSIN.length != 0){
				            	
				            	for(var i=0; i<RIGHTSIN.length; i++) { 
				            		htmlXIANSHI = htmlXIANSHI+"<tr><td >"+RIGHTSIN[i].MENUNAME+"</td>";
				            		var rightss = RIGHTSIN[i].MENURIGHTS;
				            		if(rightss != null && typeof(rightss)!="undefined" && rightss.length != 0){
				            			// 判断权限个数，是否小于5
				            			rightsum = rightss.length;
				            			for(var k = 0; k < rightss.length; k++){
				            				if(rightss[k].CHECKED == 0){
				            					htmlXIANSHI = htmlXIANSHI+"<td ><input type='checkbox' name='rightXIANSHIchbox' hight='80px'  value='"+rightss[k].RIGHTID+"'/>"+rightss[k].RIGHTNAME+"</td>";	
				            				}else{
				            					htmlXIANSHI = htmlXIANSHI+"<td ><input type='checkbox' name='rightXIANSHIchbox' hight='80px' checked='checked' value='"+rightss[k].RIGHTID+"'/>"+rightss[k].RIGHTNAME+"</td>";	
				            				}
				            			}
				            			
			            				//计算权限缺少表格数
			            				if(rightsum < tableLength){
			            					var shao = tableLength-rightsum;
			            					for( var x=0; x<shao;x++){
			            							htmlXIANSHI = htmlXIANSHI+"<td ></td>";
			            					}
			            				}
				            		}else{
				            		
				            			for( var y=0; y<tableLength;y++){
			            							htmlXIANSHI = htmlXIANSHI+"<td ></td>";
			            					}
				            		}
				            		
				            		
				            		htmlXIANSHI = htmlXIANSHI+"</tr>";
								 	
 
								} 
								$("#xianshitable").html(htmlXIANSHI);
								
				            }
				            
						}else{
							alert("数据库数据存在错误！");
						}
					},
					error : function(data) {
				
					}
				});
				
		            
		            $.metadata.setType("attr", "validate");
			            var v = $("form").validate({
			                errorPlacement: function (lable, element)
			                {
			                    if (element.hasClass("l-textarea"))
			                    {
			                        element.ligerTip({ content: lable.html(), target: element[0] }); 
			                    }
			                    else if (element.hasClass("l-text-field"))
			                    {
			                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
			                    }
			                    else
			                    {
			                        lable.appendTo(element.parents("td:first").next("td"));
			                    }
			                },
			                success: function (lable)
			                {
			                    lable.ligerHideTip();
			                    lable.remove();
			                },
			                submitHandler: function ()
			                {
			                    $("form .l-text,.l-textarea").ligerHideTip();
			                    //获取分配角色权限
				                  //获取分配页面显示权限
				                  var chkrole = "";
								 var chk_XIANSHI =[];
								 $('input[name="rightXIANSHIchbox"]:checked').each(function(){    
								   chk_XIANSHI.push($(this).val());    
								  }); 
								  //添加获取分配页面显示权限
								 if(chk_XIANSHI.length!=0){
									for(var i=0;i<chk_XIANSHI.length;i++){
										chkrole +=chk_XIANSHI[i]+","
									}
							    }
			                      //更新角色
				                jQuery.ajax({
									type : 'post',
									url : 'role_accreditDone.action?nowdata='+parseInt(Math.random()*100000),
									data: {"roleid":str,"chkrole":chkrole},
									async : false,
									timeout : '3000',
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata == 1){
											parent.$.ligerDialog.close();
											//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
											parent.window.onSubmit();
											parent.$.ligerDialog.success("修改成功！");
											
											
										}else{
											alert("数据库数据存在错误！");
										}
									},
									error : function(data) {
								
									}
								});
								
			                }
			            });
			            
			            $("form").ligerForm();
		            	$(".stripe tr").mouseover(function(){   
					   	//如果鼠标移到class为stripe的表格的tr上时，执行函数   
				 		$(this).addClass("over");}).mouseout(function(){   
						//给这行添加class值为over，并且当鼠标一出该行时执行函数   
						$(this).removeClass("over");}) //移除该行的class   
						 $(".stripe tr:even").addClass("alt");   
						//给class为stripe的表格的偶数行添加class值为alt
						//www.divcss5.com 整理特效
            });
       
    </script>
    <style>
	body{ margin:0 auto; text-align:center}
	table{margin:0 auto; width:880px}
	table{ border:1px solid #000}
	table tr th{ height:28px; line-height:28px; background:#999 ;text-align: center;}
	table.stripe tr td{ height:28px; line-height:28px; text-align:center;background:#FFF;vertical-align:middle;}/* 默认背景被白色 */
	table.stripe tr.alt td { background:#F2F2F2;}/* 默认隔行背景颜色 */
	table.stripe tr.over td {background:#EEECEB;}/* 鼠标经过时候背景颜色 */
	
	
	#n{margin:10px auto; width:920px; border:1px solid #CCC;font-size:14px; line-height:30px;}
	#n a{ padding:0 4px; color:#333}
	</style>
</head>
<body  style="padding:10px">
	 <form name="form" method="post"  id="form">
	 <input name="roleid" type="hidden" id="roleid"  />
	 <input name="rolename" type="hidden" id="rolename"  />
	 	<table  border="1" class="stripe" cellpadding="0" cellspacing="1" id="xianshitable">
			 
		</table>
		<table  border="0" class="stripe" cellpadding="0" cellspacing="1">
		 <tr>
                <td align="right" class="l-table-edit-td" style="width: 480px;"></td>
                <td align="right" class="l-table-edit-td" >
               	 <input type="submit" value="提交" id="Button1" class="l-button l-button-submit" />
                </td>
         </tr>
         </table>
	</form>
</body>
</html>
