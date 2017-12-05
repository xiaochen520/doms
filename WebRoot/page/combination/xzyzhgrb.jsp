<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>夏转油综合站日报</title>
   
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
	var rwsq = "";
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
				url : 'xzyzhgrb_pageInit.action',
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
					url : 'xzyzhgrb_searchXzyzhg.action?txtDate='+$("#txtDate").val(),
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
									    rwsq = U1S1SS[0].rwsq;

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
						     		tableval ="<table border=0 cellpadding=0 cellspacing=0 width=2600 style='border-collapse:collapse;table-layout:fixed'>"+
							     		" <tr height=36>"+
							     		 " <td colspan=37 height=36 class=xl76 style='height:27.0pt'>夏转油综合岗 日报</td>"+
							     		" </tr>"+
							     		 "<tr class=xl70 height=26 style='mso-height-source:userset'>"+
							     		 " <td colspan=20 height=26 class=xl70 style='height:19.5pt'>单位：夏子街采油站</td>"+
						    			  "<td class=xl70>审核：</td>"+
											"<td colspan=8 class=xl75><input id='SHR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+SHR+"'/></td> "+
							     		"<td class=xl73>日期：</td>"+
										" <td colspan=7 class=xl75><input id='RQ' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+RQ+"'/></td> "+
							     		 "</tr>"+
							     		 "<tr height=24 style='mso-height-source:userset'>"+
							     		 " <td rowspan=3 class=xl67>时间</td>"+
							     		 " <td colspan=2 class=xl77 style='border-right:.5pt solid black;border-left:none'>除油器</td>"+
							     		" <td colspan=3 class=xl77 style='border-right:.5pt solid black;border-left:none'>1#分离器</td>"+
							     		" <td colspan=3 class=xl77 style='border-right:.5pt solid black;border-left:none'>2#分离器</td>"+
							     		" <td colspan=2 class=xl77 style='border-right:.5pt solid black;border-left:none'>1#外输泵</td>"+
							     		" <td colspan=2 class=xl77 style='border-right:.5pt solid black;border-left:none'>2#外输泵</td>"+
							     		" <td  class=xl77 style='border-right:.5pt solid black;border-left:none'>外输泵</td>"+
							     		" <td colspan=4 class=xl77 style='border-right:.5pt solid black;border-left:none'>1#相变炉</td>"+
							     		" <td colspan=4 class=xl77 style='border-right:.5pt solid black;border-left:none'>2#相变炉</td>"+
							     		" <td class=xl77 style='border-right:.5pt solid black;border-left:none'>事故罐</td>"+
							     		" <td class=xl67>含水仪</td>"+
							     		" <td  class=xl67>天然气</td>"+
							     		" <td colspan=4 class=xl67>油田伴生气气表</td>"+
							     		" <td colspan=5 class=xl67>干气气表</td>"+
							     		" <td colspan=2 class=xl67>气井气</td>"+
							     		" <td  class=xl67>日外输气</td>"+
							     		 "</tr>"+
							     		  "<tr  class=xl66 height=39 style='mso-height-source:userset'>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>液位</td>"+
							     		 "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>液位</td>"+
							     		 "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出压</td>"+
							     		 "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出压</td>"+							     		  
							     		 " <td class=xl68  style='border-top:none;border-left:none;width:100pt'>变频</td>"+							     		
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进温</td>"+
							     		 "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出温</td>"+
							     		 "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出压</td>"+
							     		  "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>进温</td>"+
							     		 "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>出温</td>"+		     		
							     		 "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>液位</td>"+							     		 
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>含水</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>压力</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>标况</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>温度</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>累计读数</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>调压阀前端压力</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>调压阀后端压力</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>温度</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>标况</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>累计读数</td>"+
							     		 "<td colspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>流量计读数</td>"+
							     		 "<td rowspan=2 class=xl68  style='border-top:none;border-left:none;width:100pt'>m3</td>"+
							     		 "</tr>"+
							     		"<tr class=xl68 height=39 style='mso-height-source:userset'>"+
							     		   "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>HZ</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>%</td>"+
							     		 
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>MPa</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>m3/h</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>℃</td>"+
							     		 
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>m3</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>MPa</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>MPa</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>℃</td>"+
							     		
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>m3/h</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>m3</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>瞬时m3/h</td>"+
							     		 "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>累计m3</td>"+
							     		 "</tr>";
					     		var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
							     	for ( var i = 0; i < U1S1S.length; i++) {
									tableval +="<tr height=30 style='mso-height-source:userset;height:23.1pt'>"+
								     		"  <td style='display: none'><input id='rpd_zyz_id"+i+"' type='hidden' value='"+U1S1S[i].rpd_zyz_id+"'></td>"+
							    			  "<td id='date"+i+"' height=21 class=xl69 width=100 style='border-top:none;width:47pt'>"+U1S1S[i].rq+"</td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='cyqjkyl"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].cyqjkyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='cyqckyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].cyqckyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq1jkyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq1jkyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq1ckyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq1ckyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq1yw"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq1yw+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq2jkyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq2jkyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq2ckyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq2ckyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='flq2yw"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].flq2yw+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='wsb1jkyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb1jkyl+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='wsb1ckyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb1ckyl+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='wsb2jkyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb2jkyl+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='wsb2ckyl"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsb2ckyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='wsbbppl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].wsbbppl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='xbl1jkyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].xbl1jkyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='xbl1ckyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].xbl1ckyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='xbl1jkwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].xbl1jkwd+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='xbl1ckwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].xbl1ckwd+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='xbl2jkyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].xbl2jkyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='xbl2ckyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].xbl2ckyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='xbl2jkwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].xbl2jkwd+"></td>"+
								     		 
								     		 
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='xbl2ckwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].xbl2ckwd+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='sggyw"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].sggyw+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='hsyhs"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].hsyhs+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='trqyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].trqyl+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='bsqqbyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].bsqqbyl+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='bsqqbll"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].bsqqbll+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='bsqqbwd"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].bsqqbwd+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='bsqqblllj"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].bsqqblllj+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='gqqbfqyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].gqqbfqyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='gqqbfhyl"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].gqqbfhyl+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='gqqbwd"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].gqqbwd+"></td>"+
								     		 "<td class=xl67  style='border-top:none;border-left:none'><input id='gqqbll"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].gqqbll+"></td>"+
								     		"<td class=xl67  style='border-top:none;border-left:none'><input id='gqqblllj"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].gqqblllj+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='qjqll"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].qjqll+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none'><input id='qjqlllj"+i+"'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].qjqlllj+"></td>";
								     		  	
								     		  if(i == 0){
								     			 tableval += "  <td style='display: none'><input id='ID' type='hidden' value='"+ID+"'></td>";
								     			tableval +="<td class=xl67 rowspan=30 style='border-top:none;border-left:none'><input id='rwsq' type='text' "+disStr+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+rwsq+"></td>";
								     			
								     		  }
								     		tableval += "</tr>";
							     			
								     	//alert(U1S1S[i].rq)
							     		
										}
//							     		 tableval += "<tr height=30 style='mso-height-source:userset;height:23.1pt'>"+
//							     				 		"<td colspan=12 class=xl68 style='border-top:none;border-left:none;width:100pt'>夜班值班人：</td>"+						 			
//							     						 "<td colspan=12 class=xl68 style='border-top:none;border-left:none;width:100pt'>白班值班人：</td>"+				
//							     						 "<td colspan=12 class=xl68 style='border-top:none;border-left:none;width:100pt'>夜班值班人：</td>";
//							     	
//							     				tableval += "</tr>";
							     			
							     		
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
  			var	rwsq = $("#rwsq").val();
  			var	ID = $("#ID").val();
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<1;i++){           																															
             		firstArr.push($("#rpd_zyz_id"+i).val());
             		firstArr.push($("#date"+i).text());
             		firstArr.push($("#cyqjkyl"+i).val());
             		firstArr.push($("#cyqckyl"+i).val());
             		firstArr.push($("#flq1jkyl"+i).val());
             		firstArr.push($("#flq1ckyl"+i).val());
             		firstArr.push($("#flq1yw"+i).val());
             		firstArr.push($("#flq2jkyl"+i).val());
             		firstArr.push($("#flq2ckyl"+i).val());
             		firstArr.push($("#flq2yw"+i).val());
             		firstArr.push($("#wsb1jkyl"+i).val());
             		firstArr.push($("#wsb1ckyl"+i).val());
             		firstArr.push($("#wsb2jkyl"+i).val());
             		firstArr.push($("#wsb2ckyl"+i).val());
             		firstArr.push($("#wsbbppl"+i).val());
             		firstArr.push($("#xbl1jkyl"+i).val());
             		firstArr.push($("#xbl1ckyl"+i).val());
             		firstArr.push($("#xbl1jkwd"+i).val());            		
             		firstArr.push($("#xbl1ckwd"+i).val());
             		firstArr.push($("#xbl2jkyl"+i).val());
             		firstArr.push($("#xbl2ckyl"+i).val());
             		firstArr.push($("#xbl2jkwd"+i).val());    		
             		firstArr.push($("#xbl2ckwd"+i).val());
             		firstArr.push($("#sggyw"+i).val());
             		firstArr.push($("#hsyhs"+i).val());
             		firstArr.push($("#trqyl"+i).val());
             		firstArr.push($("#bsqqbyl"+i).val());
             		firstArr.push($("#bsqqbll"+i).val());
             		firstArr.push($("#bsqqbwd"+i).val());
             		firstArr.push($("#bsqqblllj"+i).val());
             		firstArr.push($("#gqqbfqyl"+i).val());
             		firstArr.push($("#gqqbfhyl"+i).val());
             		firstArr.push($("#gqqbwd"+i).val());
             		firstArr.push($("#gqqbll"+i).val());
             		firstArr.push($("#gqqblllj"+i).val());
             		firstArr.push($("#qjqll"+i).val());
             		firstArr.push($("#qjqlllj"+i).val());            		             	
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'xzyzhgrb_update.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"RQ":RQ,"firstStr":firstStr,"ID":ID,"rwsq":rwsq},
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
 					url : 'xzyzhgrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var url = "xzyzhgrb_exportxzyzhgrb.action?txtDate="+encodeURIComponent(txtDate);
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