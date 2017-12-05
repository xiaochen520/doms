<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>反应器</title>
   
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
				url : 'reactor_pageInit.action',
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
					url : 'reactor_searchReactor.action?txtDate='+$("#txtDate").val(),
					success : function(data) {
        		 
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							//alert(data);
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
									     				ZBR1 ="";
										     			}
												if(REPORTMESSAGE.SHR != null && typeof(REPORTMESSAGE.SHR)!="undefined"){
									     			 SHR =REPORTMESSAGE.SHR;
									     			}else{
									     				 SHR ="";
										     			}
												 if(REPORTMESSAGE.RQ != null && typeof(REPORTMESSAGE.RQ)!="undefined"){
									     			  RQ =REPORTMESSAGE.RQ;
									     			}else{
									     				RQ = "";
									     			}
												if(REPORTMESSAGE.BZ != null && typeof(REPORTMESSAGE.BZ)!="undefined"){
									     			  BZ =REPORTMESSAGE.BZ;
									     			}else{
									     				 BZ ="";
										     			}
												 //alert(RPDREPORTMESSAGEID);
									     	}else{
									     		RPDREPORTMESSAGEID ="";
									     	}
									    	//↓
						     	$("#tableHtml").html('');
						     	tableval ="<table border=1 bordercolor='#000000'align='center' cellpadding=0 cellspacing=0 width=1006 style='border-collapse:collapse;table-layout:fixed;width:756pt;margin:auto'>"+
						     		 "<col width=64 style='width:48pt'>"+
						     		 "<col width=71 style='mso-width-source:userset;mso-width-alt:2596;width:53pt'>"+
						     		 "<col width=86 style='mso-width-source:userset;mso-width-alt:3145;width:65pt'>"+
						     		 "<col width=71 style='mso-width-source:userset;mso-width-alt:2596;width:53pt'>"+
						     		 "<col width=86 style='mso-width-source:userset;mso-width-alt:3145;width:65pt'>"+
						     		 "<col width=71 style='mso-width-source:userset;mso-width-alt:2596;width:53pt'>"+
						     		 "<col width=86 style='mso-width-source:userset;mso-width-alt:3145;width:65pt'>"+
						     		 "<col width=71 style='mso-width-source:userset;mso-width-alt:2596;width:53pt'>"+
						     		 "<col width=86 style='mso-width-source:userset;mso-width-alt:3145;width:65pt'>"+
						     		 "<col width=71 style='mso-width-source:userset;mso-width-alt:2596;width:53pt'>"+
						     		 "<col width=86 style='mso-width-source:userset;mso-width-alt:3145;width:65pt'>"+
						     		 "<col width=71 style='mso-width-source:userset;mso-width-alt:2596;width:53pt'>"+
						     		 "<col width=86 style='mso-width-source:userset;mso-width-alt:3145;width:65pt'>"+
						     		 "<tr height=36 style='mso-height-source:userset;height:27.0pt'>"+
						     		  "<td colspan=13 height=36 class=xl69 width=1006 style='height:27.0pt;"+
						     		  "width:756pt' >风城二号稠油联合处理站反应器日报表</td>"+
						     		 "</tr>"+
						     		 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
						     		  "<td height=26 class=xl65 style='height:20.1pt;border-top:none'>值班人：</td>"+
						     		  "<td colspan=8 class=xl71 style='border-right:.5pt solid black;border-left:none'>"+
						     		 "<input type='text' id='ZBR1' style='background:transparent;border:0px solid;width:675px;height:30px;font-size: 10pt;' value='"+ZBR1+"'/>　</td>"+
						     		  "<td class=xl65 style='border-top:none;border-left:none'>审核人：</td>"+
						     		
						     			"  <td class=xl67 style='border-top:none;border-left:none'>"+SHR +" 　</td>"+
						     		  "<td  style='border-top:none;border-left:none'>日期：</td>"+
						     		 "  <td style='border-top:none;border-left:none' >"+$("#txtDate").val()+"<input type='hidden' id='RQ' value='"+$("#txtDate").val()+"'/></td>"+
						     		 "</tr>"+
						     		 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
						     		  "<td rowspan=2 height=62 class=xl66 style='border-bottom:.5pt solid black;"+
						     		  "height:47.1pt;border-top:none'>时间</td>"+
						     		  "<td colspan=12 class=xl71 style='border-right:.5pt solid black;border-left:none'>反应器处理量</td>"+
						     		 "</tr>"+
						     		" <tr height=36 style='mso-height-source:userset;height:27.0pt'>"+
						     		 " <td height=36 class=xl66 width=71 style='height:27.0pt;border-top:none;"+
						     		 " border-left:none;width:53pt'>1#瞬时流量（m3/h）</td>"+
						     		  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>1#累积流量（m3）</td>"+
						     		 " <td class=xl66 width=71 style='border-top:none;border-left:none;width:53pt'>2#瞬时流量（m3/h）</td>"+
						     		  "<td class=xl67 width=86 style='border-top:none;border-left:none;width:65pt'>2#累积流量（m3）</td>"+
						     		  "<td class=xl66 width=71 style='border-top:none;border-left:none;width:53pt'>3#瞬时流量（m3/h）</td>"+
						     		  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>3#累积流量（m3）</td>"+
						     		  "<td class=xl66 width=71 style='border-top:none;border-left:none;width:53pt'>4#瞬时流量（m3/h）</td>"+
						     		  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>4#累积流量（m3）</td>"+
						     		  "<td class=xl66 width=71 style='border-top:none;border-left:none;width:53pt'>5#瞬时流量（m3/h）</td>"+
						     		  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>5#累积流量（m3）</td>"+
						     		  "<td class=xl66 width=71 style='border-top:none;border-left:none;width:53pt'>6#瞬时流量（m3/h）</td>"+
						     		  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>6#累积流量（m3）</td>"+
						     		" </tr>";
						         	for(var i=0; i<U2OILS.length; i++) {
						         	
										if(U2OILS[i].REPORT_TIME == '班累积'){
												tableval +=" <tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
							         	  	  "<td bgColor=#00FF00 height=26 class=xl68 style='height:19.5pt;border-top:1'>"+U2OILS[i].REPORT_TIME+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301A+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301AZ+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301B+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301BZ+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301C+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301CZ+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301D+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301DZ+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301E+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301EZ+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301F+"</td>"+
									     	  "<td bgColor=#00FF00 class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].fit20301fz+"</td>"+
					     					 "</tr>";
										}else{
													tableval +=" <tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
							         	  	  "<td height=26 class=xl68 style='height:19.5pt;border-top:1'>"+U2OILS[i].REPORT_TIME+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301A+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301AZ+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301B+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301BZ+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301C+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301CZ+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301D+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301DZ+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301E+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT20301EZ+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_20301F+"</td>"+
									     	  "<td class=xl68 style='border-top:none;border-left:none'>"+U2OILS[i].fit20301fz+"</td>"+
					     					 "</tr>";
										}
						         		
						         	}
						        	tableval +="<tr height=53 style='mso-height-source:userset;height:39.75pt'>"+
						        	"	 <td height=53 class=xl68 style='height:39.75pt'>备注：</td>"+
									"	  <td colspan=12 height=53 class=xl70 style='background:transparent;border:0px solid;width:475px;height:30px;font-size: 10pt;'>"+
									  "<input id='BZ' type='text' style='vertical-align : center;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' value='"+BZ+"'/>"+
									  "</td>"+
										"	 </tr>"+
						     		 "<![if supportMisalignedColumns]>"+
						     		 "<tr height=0 style='display:none'>"+
						     		  "<td width=64 style='width:48pt'></td>"+
						     		  "<td width=71 style='width:53pt'></td>"+
						     		  "<td width=86 style='width:65pt'></td>"+
						     		  "<td width=71 style='width:53pt'></td>"+
						     		  "<td width=86 style='width:65pt'></td>"+
						     		  "<td width=71 style='width:53pt'></td>"+
						     		 " <td width=86 style='width:65pt'></td>"+
						     		 " <td width=71 style='width:53pt'></td>"+
						     		 " <td width=86 style='width:65pt'></td>"+
						     		  "<td width=71 style='width:53pt'></td>"+
						     		  "<td width=86 style='width:65pt'></td>"+
						     		  "<td width=71 style='width:53pt'></td>"+
						     		 " <td width=86 style='width:65pt'></td>"+
						     		" </tr>"+
						     		"<![endif]>"+
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
 					url : 'reactor_updateRPDREPORTMESSAGE.action?nowdata='+parseInt(Math.random()*100000),
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
 					url : 'reactor_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var url = "reactor_exportReactor.action?txtDate="+encodeURIComponent(txtDate) + '&reportName='+encodeURIComponent('反应器日报表');
     		//if (onSearchByDate(txtDate)) {
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    					/* if (!(window.location.href= url)) {
    						$.ligerDialog.confirm('没有可导出的数据！');
    					} */
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
				<div id="tableHtml" style="OVERFLOW:auto; width: 99%;height: 87%;text-align:center" align="center">
				

				
		
	

</div>
	

     
  </form>
    
</body>
</html>
