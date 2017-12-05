<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>稀油注输联合站分线计量</title>
   
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
    <link href="../../lib/css/fxjl.css" rel="stylesheet" type="text/css" /> 
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
        $(function ()
        {
        	$("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd ",
                    labelWidth: 100,
                    lableHeight:140,
                    labelAlign: 'center',
                   // showTime : true,
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
           	//获取报表及功能按钮ｊｓ
        	jQuery.ajax({
				type : 'post',
				url : 'linem_pageInit.action',
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
        });
        
         function onSubmit()
        {
        	//if (onSearchByDate($("#txtDate").val())){
        	 jQuery.ajax({
					type : 'post',
					url : 'linemmb_searchLineMeasu.action?txtDate='+$("#txtDate").val(),
					success : function(data) { 
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							//alert(data)
							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
									if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
									    var U2OILS = outData.JSONDATA.U2OILS;
									   
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									    if (U2OILS != null && typeof(U2OILS)!="undefined"){
									     	if(REPORTMESSAGE != null && typeof(REPORTMESSAGE)!="undefined"){
									     		if(REPORTMESSAGE.RPDREPORTMESSAGEID != null && typeof(REPORTMESSAGE.RPDREPORTMESSAGEID)!="undefined"){
									     				RPDREPORTMESSAGEID =REPORTMESSAGE.RPDREPORTMESSAGEID;	
									     			}else{
									     				RPDREPORTMESSAGEID ="";
									     			}
									     		 if(REPORTMESSAGE.ZBR1 != null && typeof(REPORTMESSAGE.ZBR1)!="undefined"){
									     			 ZBR1 =REPORTMESSAGE.ZBR1;	
									     			}else{
									     				ZBR1 = "";
									     			}
												if(REPORTMESSAGE.SHR != null && typeof(REPORTMESSAGE.SHR)!="undefined"){
									     			 SHR =REPORTMESSAGE.SHR;
									     			}else{
									     				SHR = "";
									     			}
												 if(REPORTMESSAGE.RQ != null && typeof(REPORTMESSAGE.RQ)!="undefined"){
									     			  RQ =REPORTMESSAGE.RQ;
									     			}else{
									     				RQ = "";
									     			}
												if(REPORTMESSAGE.BZ != null && typeof(REPORTMESSAGE.BZ)!="undefined"){
									     			  BZ =REPORTMESSAGE.BZ;
									     			}else{
									     				BZ = "";
									     			}
												 //alert(RPDREPORTMESSAGEID);
									     	}else{
									     		RPDREPORTMESSAGEID ="";
									     	}
									    	//↓
						     	$("#tableHtml").html('');
						     	tableval ="<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:"+
					     		 "collapse;table-layout:fixed;width:800pt'>"+

					     		 "<tr height=28 style='mso-height-source:userset;height:21.0pt'>"+
					     		 " <td colspan=13 height=28 class=xl71 style='height:21.0pt'"+
					     		  ">密闭处理站 分线计量 日报表</td>"+
					     		" </tr>"+
					     
					     		 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
					     		 " <td bgColor=#CCCCFF  rowspan=2 height=58 class=xl69 style='border-bottom:.5pt solid black;"+
					     		 " height:44.1pt;border-top:none'>时间</td>"+
					     		 " <td bgColor=#CCCCFF colspan=2 class=xl65 style='border-left:none'>提升泵出口</td>"+
					     		 " <td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>高温密闭出口</td>"+
					     		 " <td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>重18SAGD出口</td>"+

					     		" </tr>"+
					     		" <tr height=34 style='mso-height-source:userset;height:26.1pt'>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（t/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（t)</td>"+
					     		"<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br>%</td>"+
					     		 "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>油量<br>(t)</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		"<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br> %</td>"+
					     		 "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>油量<br>(t)</td>"+
					     		 
					     		 "</tr>";
					     	 	 for(var i=0; i<U2OILS.length; i++) {
						     	 	 if(U2OILS[i].REPORT_TIME == '班累积'){
						     	 		 							     	 		 	
						     	 	 		tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
								     		  "<td height=24 class=xl65111 style='height:18.0pt;border-top:none'>"+U2OILS[i].REPORT_TIME+"</td>"+								     		 
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_TSB+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIQ_TSB+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_201+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIQ_201+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].TT_201+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].AT_201+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].OIL_201+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_4203+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIQ_4203+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].TT_4203+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].AT_4203+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].OIL_4203+"</td>"+
											  
								     		" </tr>";
						     	 	 }else{
						     	 	 		tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
						     	 	 		"<td height=24 class=xl65 style='height:18.0pt;border-top:none'>"+U2OILS[i].REPORT_TIME+"</td>"+
								     		 
						     	 	 	 "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_TSB+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIQ_TSB+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_201+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIQ_201+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].TT_201+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].AT_201+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].OIL_201+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_4203+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIQ_4203+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].TT_4203+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].AT_4203+"</td>"+
										  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].OIL_4203+"</td>"+
											  
								     		" </tr>";
						     	 	 }
					     		
					     	 	 }
					     		

					     		"</table>";
					     								     	
									//↑
						     		$("#tableHtml").html(tableval);	
						    
									    }else{
											$.ligerDialog.error("当前日期无数据，请选择其他日期！");
										}
										
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
	        	//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
	        	//}
        }
        
         function onSave()
        {
         	//var	SHR = $("#SHR").val();
 			var	ZBR1 = $("#ZBR1").val();
 			var	BZ = $("#BZ").val();
 			var	RQ = $("#RQ").val();
         	//var RPDREPORTMESSAGEID = $("#RPDREPORTMESSAGEID").val();
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行修改");
         	}else{
         		if (modifyDataFlag($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'linemxy_updateRPDREPORTMESSAGE.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"ZBR1":ZBR1,"BZ":BZ,"RQ":RQ},
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
         		} else {
    				$.ligerDialog.error("只能对当日和明日报表数据进行修改");
    			}
         	}
 			
 			//alert(RPDREPORTMESSAGEID);
 			
         }

         function onAudit()
         {
         	//var RPDREPORTMESSAGEID = $("#RPDREPORTMESSAGEID").val();
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行再次审核");
         	}else{
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'linemxy_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID},
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
 			
 			//alert(RPDREPORTMESSAGEID);
 			
         }
         function onExport() {
     		var txtDate=$("#txtDate").val();
     		var url = "linemmb_exportLineMeasure.action?txtDate="+encodeURIComponent(txtDate) + '&reportName='+encodeURIComponent('分线计量日报表');
     		//if (onSearchByDate(txtDate)) {
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    				}
    			});
    		//} else {
    			//$.ligerDialog.error("选择日期无效，请选择其他日期！");
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
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
</head>
<body style="padding:10px"> 
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
						<!--  
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						-->
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		


<div id="tableHtml" align=center x:publishsource="Excel" style="height:450px;width:98%;height: 89%; overflow:auto;text-align: center;">

	</div>
    
  </form>
    
</body>
</html>
