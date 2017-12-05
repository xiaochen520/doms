<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>IP规划大表</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        
        //鼠标选择数据
		var segment_id ; 		//sagd井id
		var segment_code1 ; 		//scada服务器逻辑节点名
		var segment_code2;			//井号名称
		var segment_name;			//井号编码
		var ip_segment;			//多通阀编码
		var ip_start;
		var ip_end;
		var is_used;
		var explanation;
		var remark;
		var rlast_oper;
		var rlast_odate;
		
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'Ipghdb_pageInit.action',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(data);					
							toolbar = grid.topbar.ligerGetToolBarManager();	
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
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
                    var segment_id = $("#segment_id").val();
					var segment_code1  = $("#segment_code1").val();
					var segment_code2 = $("#segment_code2").val();
					var segment_name = $("#segment_name").val();
					var ip_segment  = $("#ip_segment").val();
					var ip_start  = $("#ip_start").val();
					var ip_end  = $("#ip_end").val();
					var is_used  = $("#is_used").val();
					var explanation = $("#explanation").val();
					var remark = $("#remark").val();

					if(segment_code1=="" ||segment_code2=="" ||ip_segment=="" ||ip_start=="" ||ip_end==""){
						alert("字段不能为空");
						return;
					}
					if(Number(ip_start)>Number(ip_end)){
						alert("起始IP不能大于结束IP");
					}else if(Number(ip_start)>255 || Number(ip_end)>255)
						alert("起始或结束IP数据不合法");
					
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	   	   jQuery.ajax({
									type : 'post',
									url : 'ipghdb_addIpghdb.action',
									async : false,
									data: {"segment_id":segment_id,"segment_code1":segment_code1 ,"segment_code2":segment_code2 ,"segment_name":segment_name,"ip_segment":ip_segment,"ip_start":ip_start,
              	   							"ip_end":ip_end,"is_used":is_used,"explanation":explanation,"remark":remark},
									success : function(data) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (data != null && typeof(data)!="undefined"){
										var outData = eval('(' + data + ')');
										if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
											$.ligerDialog.error(outData.ERRMSG);
										}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
											$.ligerDialog.error(outerror(outData.ERRCODE));
										}else{
											onSubmit();
											setpage1(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('Ip规划大表添加成功！');
											operate = 0;
										}
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   //2-修改
              	   }else if(operate == 2){
              	   		 jQuery.ajax({
									type : 'post',
									url : 'ipghdb_updateIpghdb.action',
									async : false,
									data: {"segment_id":segment_id,"segment_code1":segment_code1 ,"segment_code2":segment_code2 ,"segment_name":segment_name,"ip_segment":ip_segment,"ip_start":ip_start,
	   										"ip_end":ip_end,"is_used":is_used,"explanation":explanation,"remark":remark},
									success : function(data) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (data != null && typeof(data)!="undefined"){
										var outData = eval('(' + data + ')');
										if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
											$.ligerDialog.error(outData.ERRMSG);
										}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
											$.ligerDialog.error(outerror(outData.ERRCODE));
										}else{
											onSubmit();
											setpage1(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('Ip规划大表修改成功！');
											operate = 0;
											
										}
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   }
		          
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
				segment_id = rowdata.segment_id;
				segment_code1 = rowdata.segment_code1;
				segment_code2 = rowdata.segment_code2;
				if(rowdata.segment_name != null && typeof(rowdata.segment_name)!="undefined"){
					segment_name = rowdata.segment_name;
				}else{
					segment_name = "";
				}
				ip_segment = rowdata.ip_segment;

				if(rowdata.ip_start != null && typeof(rowdata.ip_start)!="undefined"){
					ip_start = rowdata.ip_start;
				}else{
					ip_start = "";
				}
				if(rowdata.ip_end != null && typeof(rowdata.ip_end)!="undefined"){
					ip_end = rowdata.ip_end;
				}else{
					ip_end = "";
				}
				if(rowdata.is_used != null && typeof(rowdata.is_used)!="undefined"){
					is_used = rowdata.is_used;
				}else{
					is_used = "";
				}
				if(rowdata.explanation != null && typeof(rowdata.explanation)!="undefined"){
					explanation = rowdata.explanation;
				}else{
					explanation = "";
				}
				if(rowdata.remark != null && typeof(rowdata.remark)!="undefined"){
					remark = rowdata.remark;
				}else{
					remark = "";
				}
				
               	if(operate == 2){
               		assignM(2);
               	}
        }
        
        //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
		}
		

         function onSubmit()
        {
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
				 	document.getElementById("segment_id").value = segment_id;
	               	document.getElementById("segment_code1").value = segment_code1;
	               	document.getElementById("segment_code2").value = segment_code2;
	               	document.getElementById("segment_name").value= segment_name;
	               	document.getElementById("ip_segment").value= ip_segment;
	               	document.getElementById("ip_start").value= ip_start;
	               	document.getElementById("ip_end").value= ip_end;
	               	document.getElementById("is_used").value= is_used;
	               	document.getElementById("explanation").value= explanation;
	               	document.getElementById("remark").value= remark;
			 }else if(flg == 1){
				 document.getElementById("segment_id").value = "";
	               	document.getElementById("segment_code1").value = "";
	               	document.getElementById("segment_code2").value = "";
	               	document.getElementById("segment_name").value= "";
	               	document.getElementById("ip_segment").value= "";
	               	document.getElementById("ip_start").value= "";
	               	document.getElementById("ip_end").value= "";
	               	document.getElementById("is_used").value= "";
	               	document.getElementById("explanation").value= "";
	               	document.getElementById("remark").value= "";
			 }
			 		
      }

      
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
              	   
              	 if(operate != 1 && operate != 2){
 	              	setpage1(0); //显示编辑界面
 	              	setItemsd(0);//0-显示编辑区，添加隐藏按钮
 	              }
              	   operate = 1;
              	   assignM(1);
              	   
              	   
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改信息的数据！');
              	    
              	     }else if(rows.length == 1){
							
              	    	if(operate != 1 && operate != 2){
              	    		setpage1(0); //显示编辑界面
					 		setItemsd(0);//1-隐藏编辑区添加显示按钮
						}
		              	   operate = 2;
		              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改信息的数据！');
              	     }
                  break;
              case "delete":
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要删除的数据！');
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'sagd_removeSagd.action',
											data: {"sagdId":sagdid,"org_id":org_id},
											async : false,
											success : function(data) { 
											if (data != null && typeof(data)!="undefined"){
												var outData = eval('(' + data + ')');
												if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
													$.ligerDialog.error(outerror(jsondata));
												}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
													$.ligerDialog.error(outData.ERRMSG);
												}else{
													
													var datatype = $("#datatype").val();
													var gh_id1 = $("#gh_id1").val();
													if(datatype == 8){
														onJKSubmit(gh_id1,'',datatype,'');
													}else{
														onSubmit();
													}
													assignM(1);
													$.ligerDialog.success('SAGD井基础删除成功！');
												}
													
												}
											},
											error : function(data) {
													$.ligerDialog.error(outerror(data));
											}
										});
		                        	 } 
              	     		 });
							
							

              	     }else{
              	     	alert("请选择一条你要删除的数据！");
              	     }
                  break;
              case "save":
					$("#form1").submit();
					break;
              case "up":
           		setpage1(0); //显示编辑界面
            	    setItemsd(0);//0-显示编辑区，添加隐藏按钮
                break;   
                case "down":
				 	setpage1(1); //隐藏编辑界面
				 	setItemsd(1);//1-隐藏编辑区添加显示按钮
                break;    
          }
      }
      function onExport() {
  		var totalNum = 0;
  		var url = "ipghdb_searchIpghdb.action?totalNum=export";
  		var urls = "ipghdb_searchIpghdb.action?totalNum=totalNum";
  		$.ajax({
  			type : 'post',
  			url : urls,
  			async : false,
  			timeout : '30000',
  			success : function (data){
  				totalNum = data;
  			}
  		});
  		if (totalNum < 10000 && totalNum > 0) {
  			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url;});
  		}
  		else if(totalNum > 10000){
  			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url;});
  		}
  		else {
  			$.ligerDialog.confirm('没有可导出的数据！');
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

<body style="overflow-x:hidden; padding:2px;">
	<div id="mainsearch" style=" width:99%">

		<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

		<form name="formsearch" method="post" id="form1">
			<table border="0" cellspacing="1">
				<tr>
					<td align="right" class="l-table-edit-td" style="width:80px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
					<td align="right" class="l-table-edit-td" style="width:80px">
						<a href="javascript:onExport()" class="l-button"
						style="width:100px">导出报表</a>
					</td>
				</tr>

			</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

			<div id="maingrid"></div>

			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<div id="mainsearch2" style=" width:99%">
				<div id="edituser" style="width:99%; display:none;">
					<div id="errorLabelContainer"></div>
					<table cellpadding="0" cellspacing="0" class="l-table-edit"
						border="0">
						<tr>
							<td align="left" class="l-table-edit-td">一分段代码:</td>
			               <td align="left" class="l-table-edit-td" >二分段代码:</td>
			               <td align="left" class="l-table-edit-td" >分段名称:</td>
			               <td align="left" class="l-table-edit-td" >IP段:</td>
			               <td align="left" class="l-table-edit-td" >起始IP:</td>
						</tr>
						<tr>
						
							<td align="left" class="l-table-edit-td" >
		                	<input name="segment_code1" type="text" id="segment_code1"  ltype="text" validate="{required:true,minlength:1,maxlength:10}" />
			                </td>
			                
			                <td align="left" class="l-table-edit-td" >
			                	<input name="segment_code2" type="text" id="segment_code2" ltype="text" validate="{required:true,minlength:1,maxlength:10}" />
			                </td>
			                <td align="left" class="l-table-edit-td" >
			                	<input name="segment_name" type="text" id="segment_name" ltype="text"  />
			                </td>
			                <td align="left" class="l-table-edit-td" >
			                	<input name="ip_segment" type="text" id="ip_segment" ltype="text" validate="{required:true}" />
			                </td>
			                <td align="left" class="l-table-edit-td" >
			                	<input name="ip_start" type="text" id="ip_start" ltype="Number" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" validate="{required:true,minlength:1,maxlength:3}" />
			                </td>
		                
							</tr>
							<tr>
							
								<td align="left" class="l-table-edit-td" style="width:150px">结束IP:</td> 
				               <td align="left" class="l-table-edit-td" style="width:150px" >使用状态:</td>
				         		<td align="left" class="l-table-edit-td" style="width:150px">说明:</td>
				               <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
							</tr>
							<tr>
							
								<td align="left" class="l-table-edit-td" >
				                	<input name="ip_end" type="text" id="ip_end" ltype="Number" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" validate="{required:true,minlength:1,maxlength:3}" />
				                </td>
				                <td align="left" class="l-table-edit-td" >
				                	<input name="is_used" type="text" id="is_used" ltype="text" validate="{required:true,minlength:1,maxlength:1}" value="0" />
				                </td>
				                 <td align="left" class="l-table-edit-td" >
				                	<input name="explanation" type="text" id="explanation" ltype="text"  />
				                </td>
				                <td align="left" class="l-table-edit-td" >
				                	<input name="remark" type="text" id="remark" ltype="text"  />
				                </td>
				                
				                <td align="left" class="l-table-edit-td" >
				                	<input name="segment_id" type="hidden" id="segment_id" />
				                </td>
							</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>