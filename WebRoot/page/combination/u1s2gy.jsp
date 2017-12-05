<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>一号稠油联合处理站水二区罐液位及流量报表</title>
   
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
   
 	 <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/inject.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
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
    
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
       <link href="../../lib/css/fyq.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	
    <script type="text/javascript">
    var tableval;
	var  ZBR1 ="";
	var  SHR ="";
	var  RQ ="";
	var  BZ ="";
	var  RPDREPORTMESSAGEID ="";
	var h_id = "";
	var authority='';
        $(function ()
        {
        	$("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
           	//获取报表及功能按钮ｊｓ
            jQuery.ajax({
				type : 'post',
				url : 'U1s2gy_pageInit.action',
				success : function(data) { 
				
					if (data != null && typeof(data)!="undefined"&& data!=""){
						var outData = eval('(' + data + ')');
						//alert(JSON2.stringify(outData));
						if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
							$.ligerDialog.error(outData.ERRMSG);
						}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
							$.ligerDialog.error(outerror(outData.ERRCODE));
						}else{
							var bottons = outData.JSONDATA;
							//alert(JSON2.stringify(bottons.modify ));
							//alert(bottons.audit != null && typeof(bottons.audit)!="undefined"&& bottons.audit =="1");
							if (bottons != null && typeof(bottons)!="undefined"&& bottons!=""){
									//alert( bottons.modify != null && typeof(bottons.modify)!="undefined"&& bottons.modify =="1");
									if (bottons.modify != null && typeof(bottons.modify)!="undefined"&& bottons.modify =="1"){
											jQuery("#onSaveid").css('display','block');
											authority='修改';
										}
										
								 	if (bottons.audit != null && typeof(bottons.audit)!="undefined"&& bottons.audit =="1"){
											jQuery("#onAuditid").css('display','block');
									} 
							}
						}
					}
				},
				error : function(data) {
				}
			});
          /*   var obj = $("input[type=text]").bind("blur",
                    function () {
                        CkeckData(this);
                    });
                    var obj1 = $("input[type=text][class=readOnlyNumberInput]").bind("blur",
                    function () {
                        CkeckData(this);
                    });
                }); */
                
                $("#form1").validate({
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
                        var message = lable[0].innerText;
                        if (message!='') {
                    		alert(lable[0].innerText);
                        	$(element).attr("value", '');
						}
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
        });
        function CkeckData(obj)
        {
         var inputObj = obj;
         var temp = inputObj.value;
         if(temp!="")
        {
           var digit = /^-?\d+(\.\d+)?$/;
           if (!digit.test(temp)) 
           {
              alert("只能输入负数或数字");
              inputObj.focus();
           }
         }
        }
         function onSubmit()
        {
        	//if (onSearchByDate($("#txtDate").val())){
				jQuery.ajax({
					type : 'post',
					url : 'U1s2gy_exportReactorTohtml.action?reportDate='+$("#txtDate").val()+'&authority='+encodeURIComponent(authority),
					success : function(data) {
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
									if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
										//$("#tableHtml").html("");
										$("#tableHtml").html(outData.JSONDATA.tableStr);
									}else{
										$.ligerDialog.error("当前日期无数据，请选择其他日期！");
									}
							
								}
							}
						
					},
					error : function(data) {
				
					}
				});
        	//}else {
         		//$.ligerDialog.error("选择日期无效，请选择其他日期！");
         	//}
        }
        
         function onSave()
        {
 			var zbr = $("#zbr").val();
 			var reportDate = $("#txtDate").val();
 			var remark = $("#remark").val();
 			var h_id=$("#h_id").val();
 			var fieldArray = ["s2lsllj", "s2hssllj", "s2qf1lj", "s2qf2lj", "s2wssllj", "s2rxlj", "lit_601b", "lit_601a", "lit_603", "lit_602a", "lit_602b", "lt11015", "lt11016", "time"];
			var dataArray = [];
			for (var j = 1; j < 15; j++) {
				var tempVal=[];
				for (var i = 0; i < fieldArray.length; i++) {
					var tv = $('#r'+j+fieldArray[i]).attr("value");
					tempVal[i]=tv;
					if (tv == '' || typeof(tv) == "undefined" || tv == null) {
						tempVal[i]=$('#r'+j+fieldArray[i]).html();
					}
				}
				dataArray.push(tempVal);
			}
         if (modifyDataFlag($("#txtDate").val())) {
         	var da = JSON2.stringify(dataArray);
         		jQuery.ajax({
 					type : 'post',
 					url : 'U1s2gy_updateU1s2gy.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"dataArray":da,"zbr":zbr,"remark":remark,"h_id":h_id,"reportDate":reportDate},
 					success : function(data) { 
 						if (data != null && typeof(data)!="undefined"){
 							var outData = eval('(' + data + ')');
 							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
 								$.ligerDialog.error(outData.ERRMSG);
 							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
 								$.ligerDialog.error(outerror(outData.ERRCODE));
 							}else{
 								$.ligerDialog.success('修改成功！');
 							}
 						}
 					},
 					error : function(data) {
 				
 					}
 			});
      }
      else {
		$.ligerDialog.error("只能对当日和明日报表数据进行修改");
      }
        }
         function onAudit()
         {
        	 var h_id=$("#h_id").val();
        	 SHR=$(".c4").html();
         	//var RPDREPORTMESSAGEID = $("#RPDREPORTMESSAGEID").val();
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行再次审核");
         	}else{
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'U1s2gy_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"h_id":h_id},
 					success : function(data) { 
 						if (data != null && typeof(data)!="undefined"){
 							var outData = eval('(' + data + ')');
 							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
 								$.ligerDialog.error(outData.ERRMSG);
 							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
 								$.ligerDialog.error(outerror(outData.ERRCODE));
 							}else{
 								onSubmit();
 								$.ligerDialog.success('审核成功！');
 							}
 						}
 					},
 					error : function(data) {
 				
 					}
 				});
         		} else {
    				$.ligerDialog.error("审核时间无效,不能进行审核！");
    			}
         	}
         }
         function onExport() {
     		var txtDate=$("#txtDate").val();
     		var url = "U1s2gy_exportReactor.action?reportDate="+encodeURIComponent(txtDate);
     		//if (onSearchByDate(txtDate)) {
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    				}
    			});
    		//} else {
    		//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
    		//}
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
          .t1{border-collapse:collapse;border-spacing:0;}
.r1{height:43.5pt;}
.r2{height:20.25pt;text-align:left}
.r3{height:26.25pt;}
.r4{height:26.1pt;}
.r5{height:24.75pt;}
.r6{height:60.0pt;}
.r7{height:14.25pt;}
.c1{white-space:pre-wrap;text-align:center;font-weight:bold;font-size:16pt;}
.c2{white-space:pre-wrap;text-align:right;font-size:12pt;}
.c3{white-space:pre-wrap;text-align:left;border-bottom:1px solid #000;font-weight:bold;font-size:12pt;}
.c4{white-space:pre-wrap;text-align:left;border-bottom:1px solid #000;font-size:12pt;}
.c5{white-space:pre-wrap;text-align:center;border-top:1px solid #000;border-right:1px solid #000;border-bottom:1px solid #000;border-left:1px solid #000;font-weight:bold;font-size:12pt;}
.c6{white-space:pre-wrap;border-top:1px solid #000;border-right:1px solid #000;border-bottom:1px solid #000;border-left:1px solid #000;font-weight:bold;font-size:12pt;}
.c7{white-space:pre-wrap;text-align:center;border-top:1px solid #000;border-right:1px solid #000;border-bottom:1px solid #000;border-left:1px solid #000;font-size:14pt;}
.c8{white-space:pre-wrap;text-align:center;border-right:1px solid #000;border-bottom:1px solid #000;border-left:1px solid #000;font-size:14pt;}
.c9{white-space:pre-wrap;text-align:center;border-top:1px solid #000;border-right:1px solid #000;border-bottom:1px solid #000;border-left:1px solid #000;font-size:12pt;}
/* //.c10{white-space:pre-wrap;text-align:left;border-top:1px solid #000;border-bottom:1px solid #000;border-left:1px solid #000;font-size:12pt;}
 */
 .c10{white-space:pre-wrap;text-align:left;border:1px solid #000;font-size:12pt;}
.c11{white-space:pre-wrap;text-align:center;font-size:12pt;}
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
<body link=blue vlink=fuchsia>
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table>
					<tr>	
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>
						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						 <td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						 
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<div id="tableHtml" style="OVERFLOW:auto; width: 99%;height: 87%;" align="center"></div>
  </form>
    
</body>
</html>
