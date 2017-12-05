<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>2#转油站日报表</title>
   
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
	var  SHR ="";
	var  RQ ="";
	var ID = "";
	var rxc = "";
	var rcye = "";
	var rcyou = "";
	var ceq = "";
	var ryq = "";
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
				url : 'u2zyzrb_pageInit.action',
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
					url : 'u2zyzrb_searchU2.action?txtDate='+$("#txtDate").val(),
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
									    ID = U1S1SS[0].ID;
									    rxc = U1S1SS[0].rxc;
									    rcye = U1S1SS[0].rcye;
									    rcyou = U1S1SS[0].rcyou;
									    ceq = U1S1SS[0].ceq;
									    ryq = U1S1SS[0].ryq;
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									    if (U1S1S != null && typeof(U1S1S)!="undefined"){
									    	if (U1S1SS != null && typeof(U1S1SS)!="undefined"){
									     	if(REPORTMESSAGE != null && typeof(REPORTMESSAGE)!="undefined"){
									     	
									     		if(REPORTMESSAGE.RPDREPORTMESSAGEID != null && typeof(REPORTMESSAGE.RPDREPORTMESSAGEID)!="undefined"){
									     				RPDREPORTMESSAGEID =REPORTMESSAGE.RPDREPORTMESSAGEID;
									     			}else{
									     				RPDREPORTMESSAGEID ="";
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
												
									     	}else{
									     		RPDREPORTMESSAGEID ="";
									     	}
									    	//↓
									$("#tableHtml").html('');
						     		tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1800 style='border-collapse:collapse;table-layout:fixed'>"+

							     		" <tr height=36>"+
							     		 " <td colspan=24 height=36 class=xl76 style='height:27.0pt'>2#转油站日报表</td>"+
							     		" </tr>"+
							     		 "<tr class=xl70 height=26 style='mso-height-source:userset'>"+
							     		 " <td colspan=11 height=26 class=xl70 style='height:19.5pt'>单位：稀油注输联合站 </td>"+
							     		 "<td class=xl70>审核：</td>"+
											"<td colspan=7 class=xl75><input id='SHR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+SHR+"'/></td> "+							     		
							     		"<td class=xl73>日期：</td>"+
										" <td colspan=4 class=xl75><input id='RQ' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+RQ+"'/></td> "+
							     		 "</tr>"+
							     		 "<tr height=24 style='mso-height-source:userset'>"+
							     		 " <td rowspan=3 class=xl67>时间</td>"+
							     		 " <td colspan=6 class=xl77 style='border-right:.5pt solid black;border-left:none'>1#加热炉</td>"+
							     		" <td colspan=6 class=xl77 style='border-right:.5pt solid black;border-left:none'>2#加热炉</td>"+
							     		" <td colspan=3 class=xl77 style='border-right:.5pt solid black;border-left:none'>除油器</td>"+
							     		" <td colspan=4 class=xl77 style='border-right:.5pt solid black;border-left:none'>1#分离器</td>"+
							     		" <td colspan=4 class=xl77 style='border-right:.5pt solid black;border-left:none'>2#分离器</td>"+
							     		"</tr>"+
							     		"<tr class=xl66 height=39 style='mso-height-source:userset'>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口温度</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口温度</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进气压力</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>水温</td>"+
							     		 
							 							     		 
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口温度</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口温度</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进气压力</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>水温</td>"+
							     		 
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>气压</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>分压</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>液位</td>"+
							     		
							     		
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>集油压力</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>气压</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>分压</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>液位</td>"+
							     		
							     		
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>集油压力</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>气压</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>分压</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>液位</td>"+
							     		"</tr>"+
							     		
							     		"<tr class=xl66 height=39 style='mso-height-source:userset'>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  
							     		  
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(m)</td>"+
							     		
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(m)</td>"+
							     		
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(m)</td>"+
							     		 "</tr>";					     								     						     								     	
						     								     	
										var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
							     	for ( var i = 0; i < U1S1S.length; i++) {
											tableval +="<tr height=30 style='mso-height-source:userset;height:23.1pt'>"+
								     		"  <td style='display: none'><input id='RPD_ZYZ2_ID"+i+"' type='hidden' value='"+U1S1S[i].RPD_ZYZ2_ID+"'></td>"+
							    			  "<td id='date"+i+"' height=21 class=xl69 width=100 style='border-top:none;width:47pt'>"+U1S1S[i].rq+"</td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PT104_1"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PT104_1+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PT105_1"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PT105_1+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='jrl1jkwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].jrl1jkwd+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='jrl1ckwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].jrl1ckwd+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='jrl1jqyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].jrl1jqyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='jrl1sw"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].jrl1sw+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PT106_1"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PT106_1+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PT107_1"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PT107_1+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='jrl2jkwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].jrl2jkwd+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='jrl2ckwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].jrl2ckwd+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='jrl2jqyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].jrl2jqyl+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='jrl2sw"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].jrl2sw+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='cyqqy"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].cyqqy+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='cyqfy"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].cyqfy+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='cyqyw"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].cyqyw+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq1jyyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq1jyyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq1qy"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq1qy+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq1fy"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq1fy+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq1yw"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq1yw+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='flq2jyyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq2jyyl+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='flq2qy"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq2qy+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='flq2fy"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq2fy+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='flq2yw"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq2yw+"></td>"+
											"</tr>";
						
							     	
						     	}							     	
									
							     	tableval +=	 "<tr height=36 style='mso-height-source:userset'>"+
							     		 " <td rowspan=3 class=xl67>时间</td>"+
							     		 " <td colspan=5 class=xl77 style='border-right:.5pt solid black;border-left:none'>1#外输泵</td>"+
							     		" <td colspan=5 class=xl77 style='border-right:.5pt solid black;border-left:none'>2#外输泵</td>"+
							     		" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>1#储罐</td>"+
										" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>2#储罐</td>"+
										" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>水套炉</td>"+
										" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>除油器</td>"+
										" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>日卸油</td>"+
										" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>日产液</td>"+
										" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>日输油</td>"+
										" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>日产气</td>"+
										" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>日用气</td>"+
							     		"</tr>"+
							     		"<tr class=xl66 height=39 style='mso-height-source:userset'>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口温度</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口温度</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>频率</td>"+
							     		 
							 			"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口温度</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口温度</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>频率</td>"+

							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>液位</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>液位</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>气表读数</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>气表读数</td>"+
							     		
							     		
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>车</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>T</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>T</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>m3</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>m3</td>"+
							     		"</tr>"+ 					
							     		
							     		"<tr class=xl66 height=39 style='mso-height-source:userset'>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(Hz)</td>"+
	
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(MPa)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(℃)</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(Hz)</td>"+
							     		  
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(m3)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>(m3)</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'></td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'></td>"+

							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'></td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'></td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'></td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'></td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'></td>"+
							     		 "</tr>";
							     		 
							     		 var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
							     	for ( var i = 0; i < U1S1S.length; i++) {
											tableval +="<tr height=30 style='mso-height-source:userset;height:23.1pt'>"+
								     		
							    			  "<td id='date"+i+"' height=21 class=xl69 width=100 style='border-top:none;width:47pt'>"+U1S1S[i].rq+"</td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='wsb1kyl"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb1kyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PT108_1"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PT108_1+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='TT104_1"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TT104_1+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='wsb1ckwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb1ckwd+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='wsb1pl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb1pl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='wsb2kyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb2kyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PT109_1"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PT109_1+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='TT105_1"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TT105_1+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='wsb2ckwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb2ckwd+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='wsb2pl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb2pl+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='LIT105_1"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT105_1+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='LIT106_1"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LIT106_1+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='stllllj"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].stllllj+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='cyqlllj"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].cyqlllj+"></td>";

								     		 if(i == 0){
								     		 tableval += "  <td style='display: none'><input id='ID' type='hidden' value='"+ID+"'></td>";
								     			 tableval +="<td rowspan=13 class=xl67 rowspan=8 style='border-top:none;border-left:none'><input id='rxc' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+rxc+"></td>";
								     			  tableval +="<td rowspan=13 class=xl67 rowspan=8 style='border-top:none;border-left:none'><input id='rcye' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+rcye+"></td>";
								     			   tableval +="<td rowspan=13 class=xl67 rowspan=8 style='border-top:none;border-left:none'><input id='rcyou' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+rcyou+"></td>";
								     			    tableval +="<td rowspan=13 class=xl67 rowspan=8 style='border-top:none;border-left:none'><input id='ceq' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+ceq+"></td>";
								     			     tableval +="<td rowspan=13 class=xl67 rowspan=8 style='border-top:none;border-left:none'><input id='ryq' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+ryq+"></td>";
								     			     
								     		  }
								  			 tableval +="</tr>";
								     	//alert(U1S1S[i].rq)
							     		
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
  			var	RQ = $("#RQ").val();
  			var	rxc = $("#rxc").val();
  			var	rcye = $("#rcye").val();
  			var	rcyou = $("#rcyou").val();
  			var	ceq = $("#ceq").val();
  			var	ryq = $("#ryq").val();
  			var	ID = $("#ID").val();
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<13;i++){
             																															
             		firstArr.push($("#RPD_ZYZ2_ID"+i).val());
             		
             		firstArr.push($("#date"+i).text());
             		//alert($("#date"+i).text());
             		firstArr.push($("#PT104_1"+i).val());
             		firstArr.push($("#PT105_1"+i).val());
             		firstArr.push($("#jrl1jkwd"+i).val());
             		firstArr.push($("#jrl1ckwd"+i).val());
             		firstArr.push($("#jrl1jqyl"+i).val());
             		firstArr.push($("#jrl1sw"+i).val());
             		firstArr.push($("#PT106_1"+i).val());
             		firstArr.push($("#PT107_1"+i).val());
             		firstArr.push($("#jrl2jkwd"+i).val());
             		firstArr.push($("#jrl2ckwd"+i).val());
             		firstArr.push($("#jrl2jqyl"+i).val());
             		firstArr.push($("#jrl2sw"+i).val());
             		firstArr.push($("#cyqqy"+i).val());
             		firstArr.push($("#cyqfy"+i).val());
             		firstArr.push($("#cyqyw"+i).val());
             		firstArr.push($("#flq1jyyl"+i).val());
             		firstArr.push($("#flq1qy"+i).val());
             		firstArr.push($("#flq1fy"+i).val());
             		firstArr.push($("#flq1yw"+i).val());
             		firstArr.push($("#flq2jyyl"+i).val());
             		firstArr.push($("#flq2qy"+i).val());
             		firstArr.push($("#flq2fy"+i).val());
             		firstArr.push($("#flq2yw"+i).val());
             		firstArr.push($("#wsb1kyl"+i).val());
             		firstArr.push($("#PT108_1"+i).val());
             		firstArr.push($("#TT104_1"+i).val());
             		firstArr.push($("#wsb1ckwd"+i).val());
             		firstArr.push($("#wsb1pl"+i).val());
             		firstArr.push($("#wsb2kyl"+i).val());
             		firstArr.push($("#PT109_1"+i).val());             		
             		firstArr.push($("#TT105_1"+i).val());
             		firstArr.push($("#wsb2ckwd"+i).val());
             		firstArr.push($("#wsb2pl"+i).val());
             		firstArr.push($("#LIT105_1"+i).val());
             		firstArr.push($("#LIT106_1"+i).val());
             		firstArr.push($("#stllllj"+i).val());
             		firstArr.push($("#cyqlllj"+i).val());

              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
             	
          		 jQuery.ajax({
  					type : 'post',
  					url : 'u2zyzrb_update.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"RQ":RQ,"firstStr":firstStr,"ID":ID,"rxc":rxc,"rcye":rcye,"rcyou":rcyou,"ceq":ceq,"ryq":ryq},
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
 					url : 'u2zyzrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var url = "u2zyzrb_exportU2ZYZRB.action?txtDate="+encodeURIComponent(txtDate);
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