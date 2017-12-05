<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>原油处理加药</title>
   
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
 	<link href="./原油处理加药系统日报表.files/stylesheet.css" rel="stylesheet" type="text/css" />  
    <script type="text/javascript">
    	var tableval;
    	var  RPDREPORTMESSAGEID ="";
    	var  SHR ="";
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
					url : 'yycljy_pageInit.action',
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
        	// if (onSearchByDate($("#txtDate").val())){
        	 jQuery.ajax({
					type : 'post',
					url : 'yycljy_searchU2OIL.action?txtDate='+$("#txtDate").val(),
					success : function(data) { 
					var  ZBR1 ="";
					var  RQ ="";
					var  BZ ="";
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
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
									    	$("#tablearea").html("");
									    	
									    	tableval = "<table border=0  bordercolor='#000000' cellpadding=0 cellspacing=0  style='border-collapse:collapse;table-layout:fixed;width:756pt;margin-left:50px;'>"+
												" <col width=64 style='width:48pt'>"+
												 "<col width=79 span=4 style='mso-width-source:userset;mso-width-alt:2889;"+
												" width:59pt'>"+
												" <col width=79 span=4 style='mso-width-source:userset;mso-width-alt:2889;"+
												" width:59pt'>"+
												" <col width=79 span=4 style='mso-width-source:userset;mso-width-alt:2889;"+
												" width:59pt'>"+
												" <tr height=37 style='mso-height-source:userset;height:27.75pt'>"+
												 " <td colspan=13 height=37 class=xl69 width=1012 style='height:27.75pt;"+
												"  width:756pt'>风城二号稠油联合处理站 原油处理加药系统 日报表</td>"+
												" </tr>"+
												" <tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
											  	"  <td height=26 class=xl65 style='height:19.5pt;border-top:1'>值班人：</td>"+
												"  <td colspan=8 class=xl72 style='border-right:.5pt solid black;border-left:"+
												 " 1'><input type='text' id='ZBR1' style='background:transparent;border:0px solid;width:630px;height:30px;font-size: 10pt;' value='"+ZBR1+"'/></td>"+
												"  <td class=xl68 style='border-top:1;border-left:1'>审核人：</td>"+
												"  <td class=xl67 style='border-top:none;border-left:none'>"+SHR +" 　</td>"+
												 " <td class=xl68 style='border-top:none'>日期：</td>"+
												"  <td class=xl65 style='border-top:none;border-left:none' >"+$("#txtDate").val()+"<input type='hidden' id='RQ' value='"+$("#txtDate").val()+"'/></td>"+
												 "</tr>"+
												 "<tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
												 " <td height=26 class=xl66 style='height:19.5pt;border-top:none'>时间</td>"+
												  "<td colspan=2 class=xl66 style='border-left:none'>正相流量（L/h）</td>"+
												 " <td colspan=2 class=xl66 style='border-left:none'>反相流量（L/h）</td>"+
												 " <td colspan=2 class=xl66 style='border-left:none'>正相药罐液位（cm）</td>"+
												 " <td colspan=2 class=xl66 style='border-left:none'>反相药罐液位（cm）</td>"+
												 " <td colspan=2 class=xl66 style='border-left:none'>正相加药量(L)</td>"+
												 " <td colspan=2 class=xl66 style='border-left:none'>反相加药量（L）</td>"+
												 "</tr>"+
												 "<tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
												 " <td height=26 class=xl66 style='height:19.5pt;border-top:none'>　</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>一段</td>"+
												"  <td class=xl66 style='border-top:none;border-left:none'>二段</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>一段</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>二段</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>1#</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>2#</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>1#</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>2#</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>一段</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>二段</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>一段</td>"+
												 " <td class=xl66 style='border-top:none;border-left:none'>二段</td>"+
												" </tr>";
												for(var i=0; i<U2OILS.length; i++) {
												//alert(U2OILS[i].FIT_10501);
													if(U2OILS[i].REPORT_TIME == "班累积"){
														tableval += "	 <tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
																"	  <td height=26 class=xl66 bgColor=#7CFC00 style='height:19.5pt;border-top:none'>"+U2OILS[i].REPORT_TIME+"</td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT_10501+"</font></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT_10502+"</font></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT_10503+"</font></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT_10504+"</font></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><input type='hidden' id='RPD_U2_OIL_ID' value='"+U2OILS[i].RPD_U2_OIL_ID+"'/></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT10501Z+"</font></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT10502Z+"</font></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT10503Z+"</font></td>"+
																"	  <td class=xl66 bgColor=#7CFC00 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT10504Z+"</font></td>"+
																"	 </tr>	";
													}else{
												
												
													tableval += "	 <tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
																"	  <td height=26 class=xl66 style='height:19.5pt;border-top:none'>"+U2OILS[i].REPORT_TIME+"</td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT_10501+"</font></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT_10502+"</font></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT_10503+"</font></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT_10504+"</font></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><input type='hidden' id='RPD_U2_OIL_ID' value='"+U2OILS[i].RPD_U2_OIL_ID+"'/></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT10501Z+"</font></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT10502Z+"</font></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT10503Z+"</font></td>"+
																"	  <td class=xl66 style='border-top:none;border-left:none'><font class='font5'>"+U2OILS[i].FIT10504Z+"</font></td>"+
																"	 </tr>	";
													}
												} 
												
												tableval += "<tr height=53 style='mso-height-source:userset;height:39.75pt'>"+
											  	"	 <td height=38  class=xl68 style='height:39.75pt'>备注：</td>"+
												"	  <td colspan=12 height=38 class=xl73 style='BORDER-RIGHT: black 0.5pt solid;height:39.75pt'>"+
												  "<input id='BZ' type='text' style='vertical-align : center;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' value='"+BZ+"'/>"+
												  "</td>"+
													"	 </tr>"+
															"	 <tr height=0 >"+
															"	  <td width=64 ></td>"+
															"	 <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	  <td width=79 ></td>"+
															"	 </tr>"+
															"	</table>";
												
												$("#tablearea").html(tableval);
									    
									    
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
	        		//$.ligerDialog.error("选择日期无效，请选择其他日期！");
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
					url : 'yycljy_updateRPDREPORTMESSAGE.action?nowdata='+parseInt(Math.random()*100000),
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
					url : 'yycljy_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
		var url = "yycljy_exportU2_OIL.action?txtDate="+encodeURIComponent(txtDate) + '&reportName='+encodeURIComponent('二号稠油联合站原油系统日报');
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
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        <!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{mso-header-data:&A;
	mso-footer-data:"Page &P";
	margin:.75in .7in .75in .7in;
	mso-header-margin:.3in;
	mso-footer-margin:.3in;
	mso-page-orientation:landscape;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;
	display:none;}
-->
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

<div id="tablearea"  style="OVERFLOW:auto; width: 99%;height: 85%;">

			</div> 

     
  </form>
    
</body>
</html>
