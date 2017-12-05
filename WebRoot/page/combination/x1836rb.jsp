<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>夏18-36注水日报</title>
   
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
	var B1ZSL = "";
	var B2ZSL = "";
	var B3ZSL = "";
	var RZSL = "";
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
				url : 'xiarb_pageInit.action',
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
					url : 'xiarb_searchXIA.action?txtDate='+$("#txtDate").val(),
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
									    B1ZSL = U1S1SS[0].B1ZSL;
									    B2ZSL = U1S1SS[0].B2ZSL;
									    B3ZSL = U1S1SS[0].B3ZSL;
									    RZSL = U1S1SS[0].RZSL;
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
						     		tableval ="<table border=0 cellpadding=0 cellspacing=0 width=2200 style='border-collapse:collapse;table-layout:fixed'>"+
							     		" <tr height=36>"+
							     		 " <td colspan=33 height=36 class=xl76 style='height:27.0pt'>夏18-36注水日报</td>"+
							     		" </tr>"+
							     		 "<tr class=xl70 height=26 style='mso-height-source:userset'>"+
							     		 " <td colspan=16 height=26 class=xl70 style='height:19.5pt'>单位：夏子街采油站</td>"+
						    			  "<td class=xl70>审核：</td>"+
											"<td colspan=10 class=xl75><input id='SHR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+SHR+"'/></td> "+
							     		"<td class=xl73>日期：</td>"+
										" <td colspan=5 class=xl75><input id='RQ' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+RQ+"'/></td> "+
							     		 "</tr>"+
							     		 "<tr height=24 style='mso-height-source:userset'>"+
							     		 " <td rowspan=2 class=xl67>时间</td>"+
							     		 " <td colspan=5 class=xl77 style='border-right:.5pt solid black;border-left:none'>1#注水泵</td>"+
							     		" <td colspan=5 class=xl77 style='border-right:.5pt solid black;border-left:none'>2#注水泵</td>"+
							     		" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>1#2#变频反馈</td>"+
							     		" <td colspan=5 class=xl77 style='border-right:.5pt solid black;border-left:none'>3#注水泵</td>"+
							     		" <td colspan=5 class=xl77 style='border-right:.5pt solid black;border-left:none'>4#注水泵</td>"+
							     		" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>3#4#变频反馈</td>"+
							     		" <td colspan=3 class=xl77 style='border-right:.5pt solid black;border-left:none'>提升泵</td>"+
							     		" <td colspan=2 class=xl77 style='border-right:.5pt solid black;border-left:none'>提升泵</td>"+
							     		" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>分水器</td>"+
							     		" <td rowspan=2 class=xl67>注水流量计读数</td>"+
							     		" <td rowspan=2 class=xl67>回流流量计读数</td>"+
							     		" <td rowspan=2 class=xl67>班注水量（m³）</td>"+
							     		" <td rowspan=2 class=xl67>日注水量（m³）</td>"+
							     		 "</tr>"+
							     		 "<tr class=xl66 height=39 style='mso-height-source:userset'>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口压力MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口压力MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>油温℃</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>电压V</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>电流A</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口压力MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口压力MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>油温℃</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>电压V</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>电流A</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>Hz</td>"+
							     		"<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口压力MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口压力MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>油温℃</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>电压V</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>电流A</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>进口压力MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>出口压力MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>油温℃</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>电压V</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>电流A</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>Hz</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>流量计读数</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>提升量m³/h</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>变频反馈Hz</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>1#罐液位m</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>2#罐液位m</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>压变Mpa</td>"+
							     		 "</tr>";

							     		var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
							     	for ( var i = 0; i < U1S1S.length; i++) {
											tableval +="<tr height=30 style='mso-height-source:userset;height:23.1pt'>"+
								     		"  <td style='display: none'><input id='RPD_X18_36ZS_ID"+i+"' type='hidden' value='"+U1S1S[i].RPD_X18_36ZS_ID+"'></td>"+
							    			  "<td id='date"+i+"' height=21 class=xl69 width=100 style='border-top:none;width:47pt'>"+U1S1S[i].rq+"</td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_301"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_301+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_306"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_306+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='TRSA_301"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TRSA_301+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='ZSB1DY"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ZSB1DY+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='ZSB1DL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ZSB1DL+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_302"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_302+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_307"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_307+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='TRSA_302"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TRSA_302+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='ZSB2DY"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ZSB2DY+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='ZSB2DL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ZSB2DL+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='SR_301"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].SR_301+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_303"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_303+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_308"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_308+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='TRSA_303"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TRSA_303+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='ZSB3DY"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ZSB3DY+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='ZSB3DL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ZSB3DL+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_304"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_304+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_309"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_309+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='TRSA_304"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TRSA_304+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='ZSB4DY"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ZSB4DY+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='ZSB4DL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ZSB4DL+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='BP34PL"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].BP34PL+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='TSBLLLJ"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].TSBLLLJ+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='FRQ_501"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].FRQ_501+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='SR_501"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].SR_501+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LRSA_501"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LRSA_501+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='LRSA_502"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].LRSA_502+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='PRSA_305"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].PRSA_305+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='FRQ_301"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].FRQ_301+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='FRQ_302"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].FRQ_302+"></td>";
										     	
								     		  if(i == 0){
								     			 tableval +="<td class=xl67 rowspan=9 style='border-top:none;border-left:none'><input id='B1ZSL' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+B1ZSL+"></td>"+
								     			"<td class=xl67 rowspan=25 style='border-top:none;border-left:none'><input id='RZSL' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+RZSL+"></td>"+
								     			"  <td style='display: none'><input id='ID' type='hidden' value='"+ID+"'></td>";
								     		  }
								     		 if(i == 9){
								     			 tableval +="<td class=xl67 rowspan=8 style='border-top:none;border-left:none'><input id='B2ZSL' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+B2ZSL+"></td>";
								     		  }
								     		 if(i == 17){
								     			 tableval +="<td class=xl67 rowspan=8 style='border-top:none;border-left:none'><input id='B3ZSL' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+B3ZSL+"></td>";
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
  			var	B1ZSL = $("#B1ZSL").val();
  			var	B2ZSL = $("#B2ZSL").val();
  			var	B3ZSL = $("#B3ZSL").val();
  			var	RZSL = $("#RZSL").val();
  			var	ID = $("#ID").val();
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<25;i++){
             																															
             		firstArr.push($("#RPD_X18_36ZS_ID"+i).val());
             		firstArr.push($("#date"+i).text());
             		firstArr.push($("#PRSA_301"+i).val());
             		firstArr.push($("#PRSA_306"+i).val());
             		firstArr.push($("#TRSA_301"+i).val());
             		firstArr.push($("#ZSB1DY"+i).val());
             		firstArr.push($("#ZSB1DL"+i).val());
             		firstArr.push($("#PRSA_302"+i).val());
             		firstArr.push($("#PRSA_307"+i).val());
             		firstArr.push($("#TRSA_302"+i).val());
             		firstArr.push($("#ZSB2DY"+i).val());
             		firstArr.push($("#ZSB2DL"+i).val());
             		firstArr.push($("#SR_301"+i).val());
             		firstArr.push($("#PRSA_303"+i).val());
             		firstArr.push($("#PRSA_308"+i).val());
             		firstArr.push($("#TRSA_303"+i).val());
             		firstArr.push($("#ZSB3DY"+i).val());
             		firstArr.push($("#ZSB3DL"+i).val());
             		firstArr.push($("#PRSA_304"+i).val());
             		firstArr.push($("#PRSA_309"+i).val());
             		firstArr.push($("#TRSA_304"+i).val());
             		firstArr.push($("#ZSB4DY"+i).val());
             		firstArr.push($("#ZSB4DL"+i).val());
             		firstArr.push($("#BP34PL"+i).val());
             		firstArr.push($("#TSBLLLJ"+i).val());
             		firstArr.push($("#FRQ_501"+i).val());
             		firstArr.push($("#SR_501"+i).val());
             		firstArr.push($("#LRSA_501"+i).val());
             		firstArr.push($("#LRSA_502"+i).val());
             		firstArr.push($("#PRSA_305"+i).val());
             		firstArr.push($("#FRQ_301"+i).val());
             		firstArr.push($("#FRQ_302"+i).val());
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'xiarb_update.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"RQ":RQ,"firstStr":firstStr,"ID":ID,"B1ZSL":B1ZSL,"B2ZSL":B2ZSL,"B3ZSL":B3ZSL,"RZSL":RZSL},
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
         	
         		var ed = $("#txtDate").val();
         		alert(ed);
         	
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'xiarb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var url = "xiarb_exportx1836RB.action?txtDate="+encodeURIComponent(txtDate);
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