<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>二号稠油联合处理站脱水参数控制表</title>
   
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
       <link href="../../lib/css/u1s1gy.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	
    <script type="text/javascript">
    var tableval;
	var  ZBR1 ="";
	var  ZBR2 ="";
	var  SHR ="";
	var  RQ ="";
	var  BZ ="";
	var JYID1 = "";
	var JYID2 = "";
	var WSID1 = "";
	var WSID2 = "";
	var ZHID = "";
	var mod ;
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
				url : 'u2tscs_pageInit.action',
				success : function(data) { 
				
					if (data != null && typeof(data)!="undefined" && data!=""){
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
									if (bottons.nullmodify != null && typeof(bottons.nullmodify)!="undefined"&& bottons.nullmodify =="1" && bottons.modify !="1"){
										
										jQuery("#onSaveid").css('display','block');
										mod = 1;
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
        function checkData(obj){
        	//先把非数字的都替换掉，除了数字和.
    		obj.value = obj.value.replace(/[^\d.]/g,"");
    		//必须保证第一个为数字而不是.
    		obj.value = obj.value.replace(/^\./g,"");
    		//保证只有出现一个.而没有多个.
    		obj.value = obj.value.replace(/\.{2,}/g,".");
    		//保证.只出现一次，而不能出现两次以上
    		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        }

        function isChange(obj){
            if(obj.value!="" && obj.name!="1"){
                obj.disabled = "disabled";
            }else{
                obj.name="1";
            }
            return true;
        }
        
         function onSubmit()
        {
        	//if (onSearchByDate($("#txtDate").val())){
        	 jQuery.ajax({
					type : 'post',
					url : 'u2tscs_searchU2tscs.action?txtDate='+$("#txtDate").val(),
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
									    var U1S1S = outData.JSONDATA.U1S1S;
									    var U1S1SS = outData.JSONDATA.U1S1SS;
									    JYID1 = U1S1SS[0].jyid1;
									    JYID2 = U1S1SS[0].jyid2;
									    WSID1 = U1S1SS[0].wsid1;
									    WSID2 = U1S1SS[0].wsid2;
									    ZHID = U1S1SS[0].zhid;
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									    if (U1S1S != null && typeof(U1S1S)!="undefined"){
									    	if (U1S1SS != null && typeof(U1S1SS)!="undefined"){
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
									     		 if(REPORTMESSAGE.ZBR2 != null && typeof(REPORTMESSAGE.ZBR2)!="undefined"){
									     			 ZBR2 =REPORTMESSAGE.ZBR2;	
									     			}else{
									     				ZBR2 ="";
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
						     		tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1500 style='border-collapse:collapse;table-layout:fixed'>"+
							     		" <tr height=36>"+
							     		 " <td colspan=19 height=36 class=xl76 style='height:27.0pt'>二号稠油联合处理站脱水参数控制表</td>"+
							     		" </tr>"+
							     		 "<tr class=xl70 height=26 style='mso-height-source:userset'>"+
							     		 " <td colspan=2 height=26 class=xl74 style='height:19.5pt'>白班值班人：</td>"+
										  "<td colspan=3 class=xl70><input id='ZBR1' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center'  value='"+ZBR1+"'/> </td> "+
										  " <td colspan=2 height=26 class=xl74 style='height:19.5pt'>夜班值班人：</td>"+
										  "<td colspan=3 class=xl70><input id='ZBR2' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center'  value='"+ZBR2+"'/> </td> "+
						    			  "<td class=xl70>审核：</td>"+
											"<td colspan=5 class=xl75><input id='SHR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+SHR+"'/></td> "+
							     		"<td class=xl73>日期：</td>"+
										" <td colspan=2 class=xl75><input id='RQ' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+RQ+"'/></td> "+
							     		 "</tr>"+
							     		 "<tr height=24 style='mso-height-source:userset'>"+
							     		 " <td rowspan=2 class=xl67>时间</td>"+
							     		 " <td colspan=8 class=xl77 style='border-right:.5pt solid black;border-left:none'>罐号</td>"+
							     		" <td rowspan=2 class=xl67>1#罐液位(m)</td>"+
							     		" <td rowspan=2 class=xl67>2#罐液位(m)</td>"+
							     		 " <td colspan=6 class=xl77 style='border-right:.5pt solid black;border-left:none'>提升泵</td>"+
							     		" <td rowspan=2 class=xl67>二段掺热温度</td>"+
							     		" <td class=xl67>外输</td>"+
							     		 "</tr>"+
							     		 "<tr class=xl66 height=39 style='mso-height-source:userset'>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>3#</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>4#</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>5#</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>6#</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>7#</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>8#</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>9#</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>10#</td>"+
							     		  "<td colspan=2 class=xl68 style='border-right:.5pt solid black;border-left:none;width:160pt'>1#频率(Hz)</td>"+
							     		  "<td colspan=2 class=xl68 style='border-right:.5pt solid black;border-left:none;width:160pt'>2#频率(Hz)</td>"+
							     		  "<td colspan=2 class=xl68 style='border-right:.5pt solid black;border-left:none;width:160pt'>3#频率(Hz)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>泵频Hz</td>"+
							     		 "</tr>";

							     		var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
							     	for ( var i = 0; i < U1S1S.length; i++) {
											tableval +="<tr height=30 style='mso-height-source:userset;height:23.1pt'>"+
								     		"  <td style='display: none'><input id='RPD_U2_DEHYDRATION_ID"+i+"' type='hidden' value='"+U1S1S[i].RPD_U2_DEHYDRATION_ID+"'></td>"+
							    			  "<td id='date"+i+"' height=21 class=xl69 width=100 style='border-top:none;width:47pt'>"+U1S1S[i].rq+"</td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10307"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10307+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10308"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10308+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10309"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10309+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10310"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10310+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10311"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10311+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10312"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10312+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10313"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10313+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10314"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10314+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10305"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10305+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='LIT_10306"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT_10306+"></td>"+
								     		 "<td class=xl67 colspan=2 style='border-top:none;border-left:none'><input id='TSB1PL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TSB1PL+"></td>"+
								     		 "<td class=xl67 colspan=2 style='border-top:none;border-left:none'><input id='TSB2PL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TSB2PL+"></td>"+
								     		 "<td class=xl67 colspan=2 style='border-top:none;border-left:none'><input id='TSB3PL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TSB3PL+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='TT_10311A"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TT_10311A+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='WSBPL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].WSBPL+"></td>"+
								     		  
										     	"</tr>";
								     	//alert(U1S1S[i].rq)
							     		
										}
							     		tableval +="<tr  height=40 style='mso-height-source:userset;height:23.1pt'>"+
							     		"<td rowspan=3 class=xl68 >进油</td>" +
							     		"<td class=xl68  style='border-top:none;border-left:none'>罐号</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none'>初液位/m</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none'>停液位/m</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none'>进油量/t</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none'>总进油/t</td>"+
							     		"<td rowspan=3 class=xl68 style='border-top:none;border-left:none'>缓冲罐底水回收</td>" +
							     		"<td rowspan=2 class=xl68  style='border-top:none;border-left:none'>时间/h</td>"+
							     		"<td rowspan=2 class=xl68  style='border-top:none;border-left:none'>泵频/Hz</td>"+
							     		"<td rowspan=2 class=xl68  style='border-top:none;border-left:none'>回收量/m<font class='font6'><sup>3</sup></font><fontclass='font0'></td>"+
							     		 "<td colspan=2 rowspan=2 class=xl68 style='border-right:.5pt solid black;border-left:none'>翻油量/t</td>"+
							     		"<td rowspan=3 class=xl68 style='border-top:none;border-left:none'>外输</td>" +
							     		"<td class=xl68  style='border-top:none;border-left:none'>罐号</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none'>初液位/m</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none'>停液位/m</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none'>外输量/t</td>"+
							     		"<td colspan=2 class=xl68  style='border-top:none;border-left:none'>外输总量/t</td>"+
							     		"</tr>"+
							     		"<tr  height=30 style='mso-height-source:userset;height:23.1pt'>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='GH'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].GH+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='CYW'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].CYW+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='TYW'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].TYW+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='JYL'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].JYL+"></td>"+
							     		"<td class=xl67 rowspan=2 style='border-top:none;border-left:none'><input id='ZJY'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].ZJY+"></td>"+
							     		
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='WGH'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WGH+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='WCYW'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WCYW+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='WTYW'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WTYW+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='WSL'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WSL+"></td>"+
							     		"<td class=xl67 rowspan=2 colspan=2 style='border-top:none;border-left:none'><input id='WSZL'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WSZL+"></td>"+
							     		"</tr>"+
							     		"<tr  height=30 style='mso-height-source:userset;height:23.1pt'>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='GH2'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].GH2+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='CYW2'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].CYW2+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='TYW2'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].TYW2+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='JYL2'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].JYL2+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='HCGDSHSSJ'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].HCGDSHSSJ+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='HCGDSHSBP'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].HCGDSHSBP+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='HSL'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].HSL+"></td>"+
							     		"<td class=xl67 colspan=2 style='border-top:none;border-left:none'><input id='FYL'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].FYL+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='WGH2'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WGH2+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='WCYW2'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WCYW2+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='WTYW2'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WTYW2+"></td>"+
							     		"<td class=xl67  style='border-top:none;border-left:none'><input id='WSL2'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1SS[0].WSL2+"></td>"+
							     		"</tr>"+
							     		"<tr  height=30 style='mso-height-source:userset;height:23.1pt'>"+
							     		"<td class=xl68  >备注</td>"+
							     		"<td colspan=18 class=xl67 style='border-left:none;'><input id='BZ'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:863px;line-height:25px;height:25px;font-size: 10pt;text-align:left;' value="+BZ+"></td>"+
							     		"</tr>"+
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
						}
					},
					error : function(data) {
				
					}
				});
        	//}else {
         	//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
         	//}
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


  			var	ZBR1 = $("#ZBR1").val();
  			var	BZ = $("#BZ").val();
  			var	RQ = $("#RQ").val();
  			var	ZBR2 = $("#ZBR2").val();
  			var	GH = $("#GH").val();
  			var	CYW = $("#CYW").val();
  			var	TYW = $("#TYW").val();
  			var	WGH = $("#WGH").val();
  			var	WCYW = $("#WCYW").val();
  			var	WTYW = $("#WTYW").val();
  			var	GH2 = $("#GH2").val();
  			var	CYW2 = $("#CYW2").val();
  			var	TYW2 = $("#TYW2").val();
  			var	HCGDSHSSJ = $("#HCGDSHSSJ").val();
  			var	HCGDSHSBP = $("#HCGDSHSBP").val();
  			var	WGH2 = $("#WGH2").val();
  			var	WCYW2 = $("#WCYW2").val();
  			var	WTYW2 = $("#WTYW2").val();
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<25;i++){
             		firstArr.push($("#RPD_U2_DEHYDRATION_ID"+i).val());
             		firstArr.push($("#date"+i).text());
             		firstArr.push($("#LIT_10307"+i).val());
             		firstArr.push($("#LIT_10308"+i).val());
             		firstArr.push($("#LIT_10309"+i).val());
             		firstArr.push($("#LIT_10310"+i).val());
             		firstArr.push($("#LIT_10311"+i).val());
             		firstArr.push($("#LIT_10312"+i).val());
             		firstArr.push($("#LIT_10313"+i).val());
             		firstArr.push($("#LIT_10314"+i).val());
             		firstArr.push($("#LIT_10305"+i).val());
             		firstArr.push($("#LIT_10306"+i).val());
             		firstArr.push($("#TSB1PL"+i).val());
             		firstArr.push($("#TSB2PL"+i).val());
             		firstArr.push($("#TSB3PL"+i).val());
             		firstArr.push($("#TT_10311A"+i).val());
             		firstArr.push($("#WSBPL"+i).val());

              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'u2tscs_updateU1S1gy.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"ZBR1":ZBR1,"ZBR1":ZBR2,"BZ":BZ,"RQ":RQ,"GH":GH,"CYW":CYW,
  						"TYW":TYW,"WGH":WGH,"WCYW":WCYW,"WTYW":WTYW,"GH2":GH2,
  						"CYW2":CYW2,"TYW2":TYW2,"HCGDSHSSJ":HCGDSHSSJ,"HCGDSHSBP":HCGDSHSBP,
  						"WGH2":WGH2,"WCYW2":WCYW2,"WTYW2":WTYW2,"firstStr":firstStr,"zhid":ZHID,"jyid1":JYID1,"jyid2":JYID2,"wsid1":WSID1,"wsid2":WSID2},
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
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'u2tscs_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var txtDate=$("#txtDate").val();
     		var url = "u2tscs_exportU1S1.action?txtDate="+encodeURIComponent(txtDate);
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
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:1px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
    
    
    </style>
</head>
<body link=blue vlink=fuchsia style="padding:10px">
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table>
					<tr>	
		                 <td align="right" class="l-table-edit-td" style="width:60px">日期：</td>
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
					<div id="tableHtml" style="OVERFLOW:auto; margin:2px ;width: 99%;height: 87%;text-align: center;" align="center">
		</div>

</form>
</body>
</html>
