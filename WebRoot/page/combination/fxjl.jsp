<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>分线计量</title>
   
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
					url : 'linem_searchLineMeasu.action?txtDate='+$("#txtDate").val(),
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
									    var U2OILSS = outData.JSONDATA.U2OILSS;
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									    if (U2OILS != null && typeof(U2OILS)!="undefined"){
									    	if (U2OILSS != null && typeof(U2OILSS)!="undefined"){
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
						     	tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1493 style='border-collapse:"+
					     		 "collapse;table-layout:fixed;width:1119pt'>"+
					     		" <col width=54 style='mso-width-source:userset;mso-width-alt:1974;width:41pt'>"+
					     		" <col width=56 style='mso-width-source:userset;mso-width-alt:2048;width:42pt'>"+
					     		 "<col width=75 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
					     		 "<col width=56 span=3 style='mso-width-source:userset;mso-width-alt:2048;"+
					     		 "width:42pt'>"+
					     		" <col width=75 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
					     		" <col width=56 span=4 style='mso-width-source:userset;mso-width-alt:2048;"+
					     		" width:42pt'>"+
					     		" <col width=75 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
					     		 "<col width=56 span=4 style='mso-width-source:userset;mso-width-alt:2048;"+
					     		 "width:42pt'>"+
					     		 "<col width=75 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
					     		" <col width=56 span=4 style='mso-width-source:userset;mso-width-alt:2048;"+
					     		 "width:42pt'>"+
					     		 "<col width=75 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
					     		" <col width=56 span=3 style='mso-width-source:userset;mso-width-alt:2048;"+
					     		" width:42pt'>"+
					     		 "<tr height=28 style='mso-height-source:userset;height:21.0pt'>"+
					     		 " <td colspan=25 height=28 class=xl71 width=1493 style='height:21.0pt;"+
					     		  "width:1119pt'>风城二号稠油联合处理站 分线计量管汇间 日报表</td>"+
					     		" </tr>"+
					     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
					     		  "<td colspan=1 height=24 class=xl72 style='height:18.0pt'>值班人：</td>"+
					     		 "  <td colspan=13 class=xl71 style='border-right:1px ; black;border-left:1'>"+
								 " <input type='text' id='ZBR1' style='background:transparent;;border:0px solid ;width:775px;height:30px;font-size: 10pt;' value='"+ZBR1+"'/></td>"+
					     		  "<td colspan=1 height=24 class=xl72 style='height:18.0pt'>审核人：</td>"+
					     		 "  <td colspan=3 class=xl65 style='border-top:none;border-left:none'>"+SHR +" 　</td>"+
					     		  "<td class=xl65832 colspan=2 style='border-top:none;border-left:none'>日期：</td>"+
					     		  "<td colspan=5 class=xl65831 style='border-left:none'>"+$("#txtDate").val()+"<input type='hidden' id='RQ' value='"+$("#txtDate").val()+"'/></td>"+
							     	 "</tr>"+
					     		 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
					     		 " <td bgColor=#CCCCFF  rowspan=2 height=58 class=xl69 style='border-bottom:.5pt solid black;"+
					     		 " height:44.1pt;border-top:none'>时间</td>"+
					     		 " <td bgColor=#CCCCFF colspan=4 class=xl65 style='border-left:none'>柴油-1线</td>"+
					     		 " <td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>1#线</td>"+
					     		 " <td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>2#线</td>"+
					     		  "<td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>3号线</td>"+
					     		  "<td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>4号线</td>"+
					     		" </tr>"+
					     		" <tr height=34 style='mso-height-source:userset;height:26.1pt'>"+
					     		 " <td bgColor=#CCCCFF height=34 class=xl67 width=56 style='height:26.1pt;border-top:none;"+
					     		  "border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'><span"+
					     		  "style='mso-spacerun:yes'>&nbsp;</span>压力<br>（MPa）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>压力<br>（MPa）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br>%</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>压力<br>（MPa）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br> %</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>压力<br>（MPa）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br> %</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>压力<br>（MPa）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br>%</td>"+
					     		 "</tr>";
					     	 	 for(var i=0; i<U2OILS.length; i++) {
						     	 	 if(U2OILS[i].REPORT_TIME == '班累积'){
						     	 	 		tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
								     		  "<td height=24 class=xl65111 style='height:18.0pt;border-top:none'>"+U2OILS[i].REPORT_TIME+"</td>"+
								     		  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_10109+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10109Z+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10109+"</td>"+
											   "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10109+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10101A+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10101B+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10101+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10101+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].MR_10101+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10102A+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10102B+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10102+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10102+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].MR_10102+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10103A+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10103B+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10103+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10103+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].MR_10103+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10104A+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10104B+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10104+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10104+"</td>"+
											  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].MR_10104+"</td>"+
								     		" </tr>";
						     	 	 }else{
						     	 	 		tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
								     		  "<td height=24 class=xl65 style='height:18.0pt;border-top:none'>"+U2OILS[i].REPORT_TIME+"</td>"+
								     		  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT_10109+"</td>"+
											  //"<td class=x165 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10109Z+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10109Z+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10109+"</td>"+
											   "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10109+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10101A+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10101B+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10101+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10101+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].MR_10101+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10102A+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10102B+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10102+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10102+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].MR_10102+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10103A+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10103B+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10103+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10103+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].MR_10103+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10104A+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].FIT10104B+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].PT_10104+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].TT_10104+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].MR_10104+"</td>"+
								     		" </tr>";
						     	 	 }
					     		
					     	 	 }
					     		tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
					     		  "<td bgColor=#CCCCFF rowspan=2 height=58 class=xl69 style='border-bottom:.5pt solid black;"+
					     		 " height:44.1pt;border-top:none'>时间</td>"+
					     		  "<td bgColor=#CCCCFF colspan=4 class=xl65 style='border-left:none'>柴油-2线</td>"+
					     		  "<td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>5#线</td>"+
					     		  "<td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>6#线</td>"+
					     		  "<td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>7#线</td>"+
					     		  "<td bgColor=#CCCCFF colspan=5 class=xl65 style='border-left:none'>8#线</td>"+
					     		 "</tr>"+
					     		 "<tr height=34 style='mso-height-source:userset;height:26.1pt'>"+
					     		 " <td bgColor=#CCCCFF height=34 class=xl67 width=56 style='height:26.1pt;border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'><span"+
					     		 " style='mso-spacerun:yes'>&nbsp;</span>压力<br>（MPa）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br> （m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>压力<br> （MPa）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br>%</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>压力<br>（MPa）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br> %</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br>（m3/h)</td>"+
					     		  "<td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br>（m3)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>压力<br>（MPa）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br>%</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>瞬时<br> （m3/h)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=75 style='border-top:none;border-left:none;width:56pt'>累计流量<br> （m3)</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>压力<br> （MPa）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>温度<br>(℃）</td>"+
					     		 " <td bgColor=#CCCCFF class=xl67 width=56 style='border-top:none;border-left:none;width:42pt'>含水率<br>%</td>"+
					     		" </tr>";
					     		
					     		 for(var i=0; i<U2OILSS.length; i++) {
					     		 
						     		 if(U2OILSS[i].REPORT_TIME == '班累积'){
						     		 	tableval += " <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
							     		"  <td height=24 class=xl65111 style='height:18.0pt;border-top:none'>"+U2OILSS[i].REPORT_TIME+"</td>"+
							     		  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT_10110+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10110Z+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10110+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].TT_10110+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10105A+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10105B+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10105+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].TT_10105+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].MR_10105+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10106A+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10106B+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10106+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].TT_10106+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].MR_10106+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10107A+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10107B+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10107+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].TT_10107+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].MR_10107+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10108A+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10108B+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10108+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:1'>"+U2OILSS[i].TT_10108+"</td>"+
										  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILSS[i].MR_10108+"</td>"+
										 "</tr>";	
						     		 }else{
						     		 		tableval += " <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
								     		"  <td height=24 class=xl65 style='height:18.0pt;border-top:none'>"+U2OILSS[i].REPORT_TIME+"</td>"+
								     		  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT_10110+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10110Z+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10110+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].TT_10110+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10105A+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10105B+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10105+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].TT_10105+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].MR_10105+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10106A+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10106B+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10106+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].TT_10106+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].MR_10106+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10107A+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10107B+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10107+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].TT_10107+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].MR_10107+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10108A+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].FIT10108B+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].PT_10108+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:1'>"+U2OILSS[i].TT_10108+"</td>"+
											  "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILSS[i].MR_10108+"</td>"+
											 "</tr>";
						     		 }
					     			
					     		
					     		 }
					     		tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
					     		"	 <td height=53  class=xl65 style='height:39.75pt'>备注：</td>"+
								"	  <td colspan=24 height=53 class=xl70  style='height:39.75pt'>"+
								  "<input id='BZ' type='text' style='vertical-align : left;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' value='"+BZ+"'/>"+
								  "</td>"+
									"</tr>"+
					     		" <![if supportMisalignedColumns]>"+
					     		" <tr height=0 style='display:none'>"+
					     		"  <td width=54 style='width:41pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=75 style='width:56pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=75 style='width:56pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		 " <td width=75 style='width:56pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		 " <td width=56 style='width:42pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		"  <td width=75 style='width:56pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		 " <td width=56 style='width:42pt'></td>"+
					     		"  <td width=56 style='width:42pt'></td>"+
					     		 " <td width=56 style='width:42pt'></td>"+
					     		 " <td width=75 style='width:56pt'></td>"+
					     		 " <td width=56 style='width:42pt'></td>"+
					     		 " <td width=56 style='width:42pt'></td>"+
					     		 " <td width=56 style='width:42pt'></td>"+
					     		" </tr>"+
					     		" <![endif]>"+
					     		"</table>";
					     								     	
									//↑
						     		$("#tableHtml").html(tableval);	
						    
									    	}
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
 					url : 'linem_updateRPDREPORTMESSAGE.action?nowdata='+parseInt(Math.random()*100000),
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
 					url : 'linem_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var url = "linem_exportLineMeasure.action?txtDate="+encodeURIComponent(txtDate) + '&reportName='+encodeURIComponent('分线计量管汇间日报表');
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
		


<div id="tableHtml" align=center x:publishsource="Excel" style="height:450px;width:98%;height: 89%; overflow:auto">

	</div>
    
  </form>
    
</body>
</html>
