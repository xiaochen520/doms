<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- <html xmlns="http://www.w3.org/1999/xhtml"> -->
<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">
<head><title>水处理加药</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    
    <meta name=ProgId content=Excel.Sheet />
	<meta name=Generator content="Microsoft Excel 14" />
	<link rel=File-List href="file8632.files/filelist.xml" />
	<link href="../../lib/css/scljy.css" rel="stylesheet" type="text/css" /> 
	
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
     <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
     
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerPopupEdit.js"></script>
  
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    
    <script src="../../lib/json2.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
 	<script src="../../lib/checkDate.js" type="text/javascript"></script>
 	<script src="../../lib/U2_check.js" type="text/javascript"></script>
 	<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
 	<script language="javascript" type="text/javascript" src="../../lib/WdatePicker.js"></script>
    <script type="text/javascript">
    var  RPDREPORTMESSAGEID ="";
	var  SHR ="";
    var tableHtml;
        $(function () {
        	$("#reportDate").ligerDateEditor(
                    {
                        format: "yyyy-MM-dd",
                        labelWidth: 100,
                        labelAlign: 'center',
                        cancelable : false
                }).setValue(myDate().Format("yyyy-MM-dd"));

			//获取报表及功能按钮ｊｓ
           	
            jQuery.ajax({
				type : 'post',
				url : 'scljy_pageInit.action',
				success : function(data) { 
				
					if (data != null && typeof(data)!="undefined"&& data!=""){
						var outData = eval('(' + data + ')');
						if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
							$.ligerDialog.error(outData.ERRMSG);
						}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
							$.ligerDialog.error(outerror(outData.ERRCODE));
						}else{
							var bottons = outData.JSONDATA;
							if (bottons != null && typeof(bottons)!="undefined"&& bottons!=""){
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
        	//if (onSearchByDate($("#reportDate").val())){
          	 jQuery.ajax({
    			type : 'post',
    			url : 'scljy_searchSCLJYRPD.action?reportDate='+$("#reportDate").val(),
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
    						/*if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
							    var tableStr = outData.JSONDATA.tableStr;
							    var cssStr = outData.JSONDATA.cssStr;

							    $("#tableHtml").html(tableStr);
							   // document.write('<style type="text\/css">' + cssStr + '<\/style>'); 
							    document.styleSheets[0].value= cssStr;
    						}*/
        						
    							if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
    							    var SCLJY = outData.JSONDATA.SCLJY;
    							    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
    							    if (SCLJY != null && typeof(SCLJY)!="undefined"){
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
    					    		$("#tableHtml").html('');
    					    		tableHtml = "<table border=0 cellpadding=0 cellspacing=0 width=988 style='border-collapse:"+
    									 "collapse;table-layout:fixed;width:748pt;overflow:auto;margin-left:50px'>"+
    									 "<col class=xl7124284 width=62 style='mso-width-source:userset;mso-width-alt:"+
    									 "2267;width:47pt'>"+
    									 "<col width=66 span=13 style='mso-width-source:userset;mso-width-alt:2413;"+
    									 "width:50pt'>"+
    									 "<col width=68 style='mso-width-source:userset;mso-width-alt:2486;width:51pt'>"+
    									 "<tr height=33 style='mso-height-source:userset;height:24.75pt'>"+
    									 " <td colspan=15 height=33 class=xl7724284 width=988 style='BORDER-RIGHT: black 0.5pt solid;height:24.75pt;"+
    									 " width:748pt'>二号稠油联合处理站水处理加药系统日报表</td>"+
    									 "</tr>"+
    									 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
    									 " <td height=26 class=xl7924284 style='height:20.1pt;border-top:none'>值班人：</td>"+
    									 " <td colspan=8 class=xl8024284 style='border-right:.5pt solid black'>"+
    									 "	<input id='zbr' type='text' style='background:transparent;border:0px solid;width:530px;height:20px;font-size: 10pt;' value='"+ZBR1+"'/>"+
    									 "</td>"+
    									 " <td class=xl7324284 style='border-top:none'>审核人：</td>"+
    									 " <td colspan=2 class=xl8024284 style='border-right:.5pt solid black'>"+ SHR +
    									// " <input id='shr' type='text' style='background:transparent;border:0px solid;width:115px;height:30px;font-size: 10pt;' disabled='disabled' value='"+SHR+"'/>"+
    									  "　</td>"+
    									  "<td  class=xl7224284 style='border-top:none;border-left:none'>日期：</td>"+
    									  "<td colspan=2 class=xl6724284>　"+ RQ +
    									// " <input id='rq' type='text'  style='background:transparent;border:0px solid;width:120px;height:30px;font-size: 10pt;' disabled='disabled' value='"+RQ+"'/>"+
    									 " <input type='hidden' id='rq' value='"+$("#reportDate").val()+"'/></td>"+
    									 "</tr>"+
    									 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
    									 " <td rowspan=3 height=72 class=xl7624284 style='height:54.0pt;border-top:none'>时间</td>"+
    									 " <td colspan=14 class=xl6724284 style='border-left:none'>加药量（L）</td>"+
    									 "</tr>"+
    									 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
    									 " <td colspan=2 height=24 class=xl6724284 style='height:18.0pt;border-left:"+
    									 " none'>SDJ-E1(调出)</td>"+
    									 " <td colspan=6 class=xl6724284 style='border-left:none'>SDJ-E2反应器</td>"+
    									 " <td colspan=6 class=xl6724284 style='border-left:none'>SDJ-E3反应器</td>"+
    									 "</tr>"+
    									 "<tr class=xl6524284 height=24 style='mso-height-source:userset;height:18.0pt'>"+
    									 " <td height=24 class=xl6624284 style='height:18.0pt;border-top:none;"+
    									 " border-left:none'>　</td>"+
    									 " <td class=xl6624284 style='border-top:none;border-left:none'>　</td>"+
    									 " <td class=xl6824284 width=66 style='border-top:none;border-left:none;"+
    									 " width:50pt'>1#</td>"+
    									 " <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									 " width:50pt'>2#</td>"+
    									 " <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>3#</td>"+
    									"  <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>4#</td>"+
    									"  <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>5#</td>"+
    									"  <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>6#</td>"+
    									"  <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>1#</td>"+
    									"  <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>2#</td>"+
    									"  <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>3#</td>"+
    								    "  <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>4#</td>"+
    									"  <td class=xl6924284 width=66 style='border-top:none;border-left:none;"+
    									"  width:50pt'>5#</td>"+
    									"  <td class=xl6924284 width=68 style='border-top:none;border-left:none;"+
    									"  width:51pt'>6#</td>"+
    									" </tr>";
    					
    									 for(var i=0; i<SCLJY.length; i++) {
        									 if(SCLJY[i].REPORT_TIME=='班累积'){
        										 tableHtml += "<tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
	   											  "<td height=26 class=xl6724285 style='height:19.5pt;border-top:none'>"+SCLJY[i].REPORT_TIME+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20501Z+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].OTHER+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502AZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none'>"+SCLJY[i].FIT20502BZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502CZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502DZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502EZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502FZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503AZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503BZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503CZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503DZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503EZ+"</td>"+
	   											  "<td class=xl6724285 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503FZ+"</td>"+
	   											 "</tr>";
        									 }else{
        										 tableHtml += "<tr height=26 style='mso-height-source:userset;height:19.5pt'>"+
	   											  "<td height=26 class=xl6724284 style='height:19.5pt;border-top:none'>"+SCLJY[i].REPORT_TIME+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20501Z+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].OTHER+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502AZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none'>"+SCLJY[i].FIT20502BZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502CZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502DZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502EZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20502FZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503AZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503BZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503CZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503DZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503EZ+"</td>"+
	   											  "<td class=xl6724284 style='border-top:none;border-left:none'>"+SCLJY[i].FIT20503FZ+"</td>"+
	   											 "</tr>";
        									 }
    										 
    									 }
    								 tableHtml += "<tr height=53 style='mso-height-source:userset;height:39.75pt'>"+
    									  "<td height=53 class=xl8124284 style='height:39.75pt;border-top:none'>备注：</td>"+
    									  "<td colspan=14 class=xl8424284 style='vertical-align : center;border-right:.5pt solid black'>"+
    									  "	<input id='bz' type='text' style='vertical-align : center;background:transparent;border:0px solid;width:940px;height:44px;font-size: 10pt;' value='"+BZ+"'/>"+ 
    									  "</td>"+
    									 "</tr>"+
    									 "<![if supportMisalignedColumns]>"+
    									 "<tr height=0 style='display:none'>"+
    									 " <td width=62 style='width:47pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=66 style='width:50pt'></td>"+
    									 " <td width=68 style='width:51pt'></td>"+
    									 "</tr>"+
    									 "<![endif]>"+
    									"</table>";
    									 $("#tableHtml").html(tableHtml);		
    							    }else{
    									$.ligerDialog.error("当前日期无数据，请选择其他日期！");
    								}
    								
    							}else{
    								$.ligerDialog.error("当前日期无数据，请选择其他日期！");
    							}
    					
    						}
    					}
    				/* document.getElementById('zbr').value = ZBR1;
    				 document.getElementById('shr').value = SHR;
    				 document.getElementById('rq').value = RQ;
    				 document.getElementById('bz').value = BZ;*/
    			},
    			error : function(data) {
    		
    			}
    		});
        	//}
        	//else {
        	//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
        	//}
     	}
        
        //为公用页面提供接口方法 
	function onJKSubmit(id,name,datatype,basid){
	}
		

	function onSave()
    {
		var	ZBR1 = $("#zbr").val();
		var	BZ = $("#bz").val();
		var	RQ = $("#rq").val();
		
		if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
    		$.ligerDialog.error("数据已审核过，不能进行修改");
    	}else{
    		if (modifyDataFlag($("#reportDate").val())) {
    			jQuery.ajax({
    				type : 'post',
    				url : 'scljy_updateRPDREPORTMESSAGE.action?nowdata='+parseInt(Math.random()*100000),
    				data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"SHR":SHR,"ZBR1":ZBR1,"BZ":BZ,"RQ":RQ},
    				success : function(data) { 
    					if (data != null && typeof(data)!="undefined"){
    						var outData = eval('(' + data + ')');
    						if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
    							$.ligerDialog.error(outData.ERRMSG);
    						}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
    							$.ligerDialog.error(outerror(outData.ERRCODE));
    						}else{
    							onSubmit();
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
	function onAduit()
	{
		if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
    		$.ligerDialog.error("数据已审核过，不能进行再次审核");
    	}else{
    		if (chekAduitByDate($("#reportDate").val())) {
    			jQuery.ajax({
    				type : 'post',
    				url : 'scljy_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
	}
	function onExport() {
		var reportDate=$("#reportDate").val();
		var url = "scljy_exportU2_WATER.action?reportDate="+encodeURIComponent(reportDate) + '&reportName='+encodeURIComponent('水处理加药系统日报表');
		//if (onSearchByDate(reportDate)) {
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

<body style="overflow-x:hidden; padding:2px;">
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
	        		<tr>
		                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="reportDate" ltype="date"/></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" id="dayreport" style="width:80px;" >导出报表</a></td>
						
						<td style="padding-left: 20px;"><a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onAduit()" class="l-button"  style="width:100px;display:none" id="onAuditid">审核</a></td>
						
					</tr>
				</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<div id="tableHtml" x:publishsource="Excel" style="OVERFLOW:auto; width: 99%;height: 87%;">

			</div> 
			</form>
</body>
</html>