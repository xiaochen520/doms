<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>2#软化水站综合日报</title>  
   
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
	var ZBR1="";
	var  SHR ="";
	var  RQ ="";
	var ID = "";
	var rwsq = "";
	var mod ;
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
				url : 'u2rhs_pageInit.action',				
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
        	 jQuery.ajax({
					type : 'post',
					url : 'u2rhs_searchZHRB.action?txtDate='+$("#txtDate").val(),
					success : function(data) { 
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
												
									     	}else{
									     		RPDREPORTMESSAGEID ="";
									     	}
									    	//↓
						    	$("#tableHtml").html('');
						    	
						    	var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
										
						     	tableval ="<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:"+
					     		 "collapse;table-layout:fixed;width:100%'>"+
					     		 "<tr height=28 style='mso-height-source:userset;height:21.0pt'>"+
					     		 " <td colspan=9 height=28 class=xl71 style='height:21.0pt;font-weight:bold;font-size:30px';"+
					     		  ">二号供汽联合站2#软化水处理综合报表</td>"+
					     		" </tr>"+	
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
 										" <td class=xl65>值班人：</td>"+
										" <td colspan=2 class=xl65 style='width:300px'><input id='ZBR1' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+ZBR1+"'/></td> "+		
							     		" <td  class=xl65>审核：</td>"+
										" <td colspan=2 class=xl65 style='width:300px'><input id='SHR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+SHR+"'/></td> "+							     		
							     		" <td  class=xl65>日期：</td>"+
										" <td colspan=2 class=xl65><input id='RQ' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+RQ+"'/></td> "+							     		
							     		" </tr>"+			
							     		 
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
							     		" <td bgColor=#FF0000 colspan=5 class=xl65 style='border-bottom:.5pt solid black;border-top:none;font-size 14px;font-weight:bolder'>软化器运行相关数据</td>"+
							     		" <td bgColor=#FF0000 colspan=4 class=xl65 style='border-left:none;font-size 14px;font-weight:bolder'>2#软水站相关能耗</td>"+								     		 
							     		" </tr> "+		
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>项目</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>一期72方清水</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>一期72方污水</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>二期216污水</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>合计</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>电</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>昨日读数</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>今日读数</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>电量</td>"+
							     		"</tr>"+	
				    		 
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>软化器台数</td>"+
							     		
							     		 "<td style='display: none'><input id='pc_prd_report_rhs2_id' type='hidden' value='"+U2OILS[0].pc_prd_report_rhs2_id+"'></td>"+		
							     		 "<td style='display: none'><input id='report_date' type='hidden' value='"+U2OILS[0].report_date+"'></td>"+					
							     		"<td class=xl65  style='border-bottom:.5pt solid black;border-top:none'><input id='rhqts_72qs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].rhqts_72qs+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='rhqts_72ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].rhqts_72ws+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='rhqts_216ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].rhqts_216ws+"></td>"+
										 "<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].rhqhj+"</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1#</td>"+
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].xgnh_yd1+"</td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_d1'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_d1+"></td>"+
										"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].xgnh_dl1+"</td>"+
							     		"</tr>"+
							     		
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>运行组数</td>"+
							     		" <td class=xl65  style='border-bottom:.5pt solid black;border-top:none'><input id='yxzs_72qs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].yxzs_72qs+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='yxzs_72ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].yxzs_72ws+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='yxzs_216ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].yxzs_216ws+"></td>"+
										 "<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].yxzshj+"</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>2#</td>"+
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].xgnh_yd2+"</td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_d2'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_d2+"></td>"+
										"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].xgnh_dl2+"</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>合计瞬时量</td>"+
							     		" <td class=xl65  style='border-bottom:.5pt solid black;border-top:none'><input id='ss_72qs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ss_72qs+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='ss_72ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ss_72ws+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='ss_216ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ss_216ws+"></td>"+
										"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].sshj+"</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>3#</td>"+
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].xgnh_yd3+"</td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_d3'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_d3+"></td>"+
										"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].xgnh_dl3+"</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>日总量</td>"+
							     		" <td class=xl65  style='border-bottom:.5pt solid black;border-top:none'><input id='rzl_72qs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].rzl_72qs+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='rzl_72ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].rzl_72ws+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='rzl_216ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].rzl_216ws+"></td>"+
										"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].rzlhj+"</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>4#</td>"+
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].xgnh_yd4+"</td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_d4'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_d4+"></td>"+
										"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].xgnh_dl4+"</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>日再生组数</td>"+
							     		" <td class=xl65  style='border-bottom:.5pt solid black;border-top:none'><input id='zszs_72qs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].zszs_72qs+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='zszs_72ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].zszs_72ws+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='zszs_216ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].zszs_216ws+"></td>"+
										"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].zszshj+"</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>盐</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>车号</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>重量</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>接收人</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>再生用水量</td>"+
							     		" <td class=xl65  style='border-bottom:.5pt solid black;border-top:none'><input id='zsysl_72qs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].zsysl_72qs+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='zsysl_72ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].zsysl_72ws+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='zsysl_216ws'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].zsysl_216ws+"></td>"+
										"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].zsyslhj+"</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ch1'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ch1+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y1'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y1+"></td>"+
										" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y1_jsr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y1_jsr+"></td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=5 class=xl65 style='border-left:none;font-weight:bold'></td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>2</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ch2'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ch2+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y2'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y2+"></td>"+
										" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y2_jsr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y2_jsr+"></td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td bgColor=#FF0000 colspan=5 class=xl65 style='border-left:none;font-weight:bold;'>清水含氧检测数据</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>3</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ch3'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ch3+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y3'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y3+"></td>"+
										" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y3_jsr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y3_jsr+"></td>"+
							     		"</tr>"+
							     								     								     									     								     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 class=xl65 style='border-left:none;font-weight:bold'>取样地点</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>取样频次</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>检测结果</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>检测人</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>4</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ch4'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ch4+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y4'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y4+"></td>"+
										" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y4_jsr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y4_jsr+"></td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 class=xl65 style='border-left:none;font-weight:bold'>2#软化水除氧器出口</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1次/天</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='dwcy_hy_2r'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].dwcy_hy_2r+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='dwcy_hy_2r_jcr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].dwcy_hy_2r_jcr+"></td>"+										
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>5</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ch5'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ch5+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y5'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y5+"></td>"+
										" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y5_jsr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y5_jsr+"></td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 class=xl65 style='border-left:none;font-weight:bold'>2#软化水加药泵出口</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1次/天</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='jyb_hy_2r'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].jyb_hy_2r+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='jyb_hy_2r_jcr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].jyb_hy_2r_jcr+"></td>"+										
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>6</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ch6'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ch6+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y6'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y6+"></td>"+
										" <td class=xl65  style='border-top:none;border-left:none'><input id='xgnh_y6_jsr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xgnh_y6_jsr+"></td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 class=xl65 style='border-left:none;font-weight:bold'>35-29#站锅炉泵进口</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1次/周</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='gl35_29_hy'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].gl35_29_hy+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='gl35_29_hy_jcr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].gl35_29_hy_jcr+"></td>"+										
							     		" <td colspan=4 class=xl65 style='border-left:none;font-weight:bold'> </td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 class=xl65 style='border-left:none;font-weight:bold'>重45SAGD站锅炉泵进口</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1次/周</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='z45sagd_hy'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].z45sagd_hy+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='z45sagd_hy_jcr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].z45sagd_hy_jcr+"></td>"+										
							     		" <td bgColor=#FF0000 colspan=4 class=xl65 style='border-left:none;font-size 14px;font-weight:bolder'>2#软水站相关水量</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 class=xl65 style='border-left:none;font-weight:bold'>重1-1#站锅炉泵进口</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1次/周</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='z1sagd_hy'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].z1sagd_hy+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='z1sagd_hy_jcr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].z1sagd_hy_jcr+"></td>"+										
							     		" <td rowspan=6 class=xl65 style='border-left:none;font-weight:bold'>反渗透</td>"+
							     		" <td colspan=3 class=xl65 style='border-left:none;font-weight:bold'>用水</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 class=xl65 style='border-left:none;font-weight:bold'>集中水处理除氧器出口</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1次/天</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='gwcy_jzs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].gwcy_jzs+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='gwcy_jzs_jcr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].gwcy_jzs_jcr+"></td>"+										
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>昨日</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>今日</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>水量</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 class=xl65 style='border-left:none;font-weight:bold'>集中水处理加药泵出口</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>1次/天</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='jyb_jzs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].jyb_jzs+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='jyb_jzs_jcr'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].jyb_jzs_jcr+"></td>"+										
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].fst_yys+"</td>"+	
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='fst_ys'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].fst_ys+"></td>"+										
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].fst_sl+"</td>"+						     		
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=5 class=xl65 style='border-left:none;font-weight:bold'></td>"+
							     		" <td colspan=3 class=xl65 style='border-left:none;font-weight:bold'>产水</td>"+
							     		"</tr>"+	
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=2 rowspan=2 class=xl65 style='border-left:none;font-weight:bold'>亚硫酸钠加药量</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>白班</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>夜班kg</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>库存</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>昨日</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>今日</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>水量</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='sds_bb'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].sds_bb+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='sds_yb'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].sds_yb+"></td>"+										
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='sds_kc'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].sds_kc+"></td>"+
								     	"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].fst_ycs+"</td>"+
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='fst_cs'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].fst_cs+"></td>"+										
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].fst_csl+"</td>"+							     	
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td colspan=5 class=xl65 style='border-left:none;font-weight:bold'></td>"+
							     		" <td rowspan=6 class=xl65 style='border-left:none;font-weight:bold'>回注回灌</td>"+
							     		" <td colspan=3 class=xl65 style='border-left:none;font-weight:bold'>回注泵</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	     		 				     		 
							     		" <td rowspan=7 class=xl65 style='border-left:none;font-weight:bold'>水质</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>项目</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>硬度</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>氯根</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>碱度</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>昨日</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>今日</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>水量</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>清水</td>"+     		 				     		 
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='qs_yd'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].qs_yd+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='qs_lg'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].qs_lg+"></td>"+										
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='qs_jd'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].qs_jd+"></td>"+
								     	"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].hzbsl_y+"</td>"+	
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='hzbsl'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].hzbsl+"></td>"+										
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].hzbsl_sl+"</td>"+							     	
							     		"</tr>"+
							   
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>污水</td>"+     		 				     		 
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ws_yd'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ws_yd+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='ws_lg'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ws_lg+"></td>"+										
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ws_jd'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ws_jd+"></td>"+								     	
							     		" <td colspan=3 class=xl65 style='border-left:none;font-weight:bold'>回灌泵</td>"+  
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>管汇间</td>"+     		 				     		 
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ghj_yd'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ghj_yd+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='ghj_lg'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ghj_lg+"></td>"+										
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ghj_jd'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ghj_jd+"></td>"+								     	
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>昨日</td>"+  
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>今日</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>水量</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>项目</td>"+   
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>昨日 18:00</td>"+  
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>昨日 0:00</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>今日 10:00</td>"+  		 				     		 
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].hgbsl_y+"</td>"+	
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='hgbsl'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].hgbsl+"></td>"+										
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].hgbsl_sl+"</td>"+							     								     		
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>含油</td>"+     		 				     		 
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='hy1'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].hy1+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='hy2'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].hy2+"></td>"+										
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='hy3'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].hy3+"></td>"+								     	
							     		" <td rowspan=2 class=xl65 style='border-left:none;font-weight:bold'>宇澄排污</td>"+  
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>昨日</td>"+  
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>今日</td>"+
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>水量</td>"+
							     		"</tr>"+
							     		
							     		" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+ 	
							     		" <td  class=xl65 style='border-left:none;font-weight:bold'>悬浮</td>"+     		 				     		 
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='xf1'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xf1+"></td>"+
								     	" <td class=xl65  style='border-top:none;border-left:none'><input id='xf2'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xf2+"></td>"+										
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='xf3'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].xf3+"></td>"+
								     	"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].ycpwsl_y+"</td>"+		
							     		" <td class=xl65  style='border-top:none;border-left:none'><input id='ycpwsl'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[0].ycpwsl+"></td>"+										
							     		"<td class=xl65111 style='border-top:none;border-left:none;background:#FFFFFF'>"+U2OILS[0].ycpwsl_sl+"</td>"+							     	
							     		"</tr>"	;
									}
					     		"</table>";					     								     	
									//↑
						     		$("#tableHtml").html(tableval);	
						    
									    }else{
											$.ligerDialog.error("当前日期无数据，请选择其他日期！");
										}
										
								}
							}
						
					},
					error : function(data) {
				
					}
				});
        }


		 /**
          * 把数组转换成特定符号分割的字符串
         */
        function arrayToString(arr,separator)
          {
          	if(!separator) separator = ",";
             	return arr.join(separator); 
          }

          function onSave()
        	{          
        	var	RQ = $("#RQ").val();   		
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";		
 																									
             		firstArr.push($("#pc_prd_report_rhs2_id").val());
             		firstArr.push($("#report_date").val());
             		firstArr.push($("#rhqts_72qs").val());
             		firstArr.push($("#rhqts_72ws").val());
             		firstArr.push($("#rhqts_216ws").val());
             		firstArr.push($("#yxzs_72qs").val());
             		firstArr.push($("#yxzs_72ws").val());
             		firstArr.push($("#yxzs_216ws").val());
             		firstArr.push($("#ss_72qs").val());
             		firstArr.push($("#ss_72ws").val());
             		firstArr.push($("#ss_216ws").val());           		
             		firstArr.push($("#rzl_72qs").val());
             		firstArr.push($("#rzl_72ws").val());
             		firstArr.push($("#rzl_216ws").val());
             		firstArr.push($("#zszs_72qs").val());
             		firstArr.push($("#zszs_72ws").val());
             		firstArr.push($("#zszs_216ws").val());
             		firstArr.push($("#zsysl_72qs").val());
             		firstArr.push($("#zsysl_72ws").val());
             		firstArr.push($("#zsysl_216ws").val());
             		firstArr.push($("#dwcy_hy_2r").val());
             		firstArr.push($("#jyb_hy_2r").val());             		
             		firstArr.push($("#gl35_29_hy").val());
             		firstArr.push($("#z45sagd_hy").val());
             		firstArr.push($("#gwcy_jzs").val());
             		firstArr.push($("#jyb_jzs").val());
             		firstArr.push($("#z1sagd_hy").val());
             		firstArr.push($("#dwcy_hy_2r_jcr").val());
             		firstArr.push($("#jyb_hy_2r_jcr").val());
             		firstArr.push($("#gl35_29_hy_jcr").val());
             		firstArr.push($("#z45sagd_hy_jcr").val());
             		firstArr.push($("#z1sagd_hy_jcr").val());
             		firstArr.push($("#gwcy_jzs_jcr").val());            		
             		firstArr.push($("#jyb_jzs_jcr").val());
             		firstArr.push($("#sds_bb").val());
             		firstArr.push($("#sds_yb").val());
             		firstArr.push($("#sds_kc").val());
             		firstArr.push($("#qs_yd").val());
             		firstArr.push($("#qs_lg").val());
             		firstArr.push($("#qs_jd").val());
             		firstArr.push($("#ws_yd").val());
             		firstArr.push($("#ws_lg").val());
             		firstArr.push($("#ws_jd").val());
             		firstArr.push($("#ghj_yd").val());           		
             		firstArr.push($("#ghj_lg").val());
             		firstArr.push($("#ghj_jd").val());
             		firstArr.push($("#hy1").val());
             		firstArr.push($("#hy2").val());
             		firstArr.push($("#hy3").val());
             		firstArr.push($("#xf1").val());
             		firstArr.push($("#xf2").val());
             		firstArr.push($("#xf3").val());
             		firstArr.push($("#xgnh_d1").val());
             		firstArr.push($("#xgnh_d2").val());
             		firstArr.push($("#xgnh_d3").val());            		
             		firstArr.push($("#xgnh_d4").val());
             		firstArr.push($("#xgnh_y1").val());
             		firstArr.push($("#xgnh_y2").val());
             		firstArr.push($("#xgnh_y3").val());
             		firstArr.push($("#xgnh_y4").val());
             		firstArr.push($("#xgnh_y5").val());
             		firstArr.push($("#xgnh_y6").val());
             		firstArr.push($("#ch1").val());
             		firstArr.push($("#ch2").val());
             		firstArr.push($("#ch3").val());
             		firstArr.push($("#ch4").val());             		
             		firstArr.push($("#ch5").val());
             		firstArr.push($("#ch6").val());
             		firstArr.push($("#xgnh_y1_jsr").val());
             		firstArr.push($("#xgnh_y2_jsr").val());
             		firstArr.push($("#xgnh_y3_jsr").val());
             		firstArr.push($("#xgnh_y4_jsr").val());
             		firstArr.push($("#xgnh_y5_jsr").val());
             		firstArr.push($("#xgnh_y6_jsr").val());
             		firstArr.push($("#fst_ys").val());
             		firstArr.push($("#fst_cs").val());
             		firstArr.push($("#hzbsl").val());             		
             		firstArr.push($("#hgbsl").val());
             		firstArr.push($("#ycpwsl").val());
              
             
              		firstStr += arrayToString(firstArr,",");
              		//alert(firstStr);
              		
              		firstStr += ";";
              		firstArr = [];
              
                var myday = new Date().Format("yyyy-MM-dd");
             	if (modifyDataFlag(myday)) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'u2rhs_update.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"RQ":RQ,"firstStr":firstStr},
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
          		}else {
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
         	//	if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'u2rhs_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
////         		} else {
//    				$.ligerDialog.error("审核时间无效,不能进行审核！");
//    			}
         	}
         }
         
         
         
     function onExport() {
     		var txtDate=$("#txtDate").val();
     		var url = "u2rhs_exportData.action?txtDate="+encodeURIComponent(txtDate);
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
            .datege{mso-style-parent:style0;
				 mso-number-format:"Short Date";
				 text-align:center;
				 vertical-align:middle;
				 border:.5pt solid windowtext;
				 white-space:nowrap;}
		.searchtitle{ padding-left:28px; position:relative;}
		.searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
		.searchtitle span{ font-size:14px; font-weight:bold;}
		.searchtitle .togglebtn{ position:absolute; top:6px; right:15px; background:url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
		.searchtitle .togglebtn-down{ background-position:0px -10px;}
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:1px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
    
    
    </style>
</head>
<body style="padding:10px"> 
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table>
					<tr>	
		                <td align="right" style="width: 60px">日期：</td>
				
				<td><input type="text" id="txtDate" ltype="date"/></td>
				
						
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						
					
<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>
						</tr>
				
				</table>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		


<div id="tableHtml" align=center x:publishsource="Excel" style="height:450px;width:98%;height: 89%; overflow:auto;text-align: center;">

	</div>
    
  </form>
    
</body>
</html>
