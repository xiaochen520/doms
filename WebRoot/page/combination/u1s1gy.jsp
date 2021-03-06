<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>水一区罐液位及流量报表</title>
   
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
	var  SHR ="";
	var  RQ ="";
	var  BZ ="";
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
				url : 'u1s1gy_pageInit.action',
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
					url : 'u1s1gy_searchU1s1gy.action?txtDate='+$("#txtDate").val(),
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
									    //alert(U1S1S)
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									    if (U1S1S != null && typeof(U1S1S)!="undefined"){
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
						     		tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1300 style='border-collapse:collapse;table-layout:fixed'>"+
							   //  		 "<col class=xl65 width=100 style='mso-width-source:userset;mso-width-alt:2624;width:62pt'>"+
							    // 		 "<col class=xl65 width=100 style='mso-width-source:userset;mso-width-alt:2048;width:48pt'>"+
							    // 		 "<col class=xl65 width=100 style='mso-width-source:userset;mso-width-alt:1984;width:47pt'>"+
							    // 		 "<col class=xl65 width=100 style='mso-width-source:userset;mso-width-alt:2144;width:50pt'>"+
							    // 		 "<col class=xl65 width=100 style='mso-width-source:userset;mso-width-alt:2368; width:56pt'>"+
							    // 		 "<col class=xl65 width=100 span=2 style='mso-width-source:userset;mso-width-alt:2080;width:49pt'>"+
							    // 		 "<col class=xl65 width=100 style='mso-width-source:userset;mso-width-alt:1920; width:45pt'>"+
							    // 		 "<col class=xl65 width=58 span=7 style='mso-width-source:userset;mso-width-alt:1856;width:44pt'>"+
							     		" <tr height=36>"+
							     		 " <td colspan=13 height=36 class=xl76 style='height:27.0pt'>一号稠油联合处理站水一区罐液位及流量报表</td>"+
							     		" </tr>"+
							     		 "<tr class=xl70 height=26 style='mso-height-source:userset'>"+
							     		 " <td height=26 class=xl74 style='height:19.5pt'>值班人：</td>"+
										  "<td colspan=5 class=xl70><input id='ZBR1' type='text' style='background:transparent;border:0px solid;font-size: 12pt;' value='"+ZBR1+"'/> </td> "+
						    			  "<td class=xl70>审核：</td>"+
											"<td class=xl75><input id='SHR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;' disabled='disabled' value='"+SHR+"'/></td> "+
							     		"<td class=xl73>日期：</td>"+
										" <td colspan=4 class=xl75><input id='RQ' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;' disabled='disabled' value='"+RQ+"'/></td> "+
							     		 "</tr>"+
							     		 "<tr height=24 style='mso-height-source:userset'>"+
							     		 " <td rowspan=2 height=63 class=xl67>时间</td>"+
							     		 " <td colspan=7 class=xl77 style='border-right:.5pt solid black;border-left:none'>累计流量（m<font class='font6'><sup>3</sup></font><fontclass='font0'>）</font></td>"+
							     		 " <td colspan=5 class=xl77 style='border-right:.5pt solid black;border-left:none'>水罐液位（m）</td>"+
							     		 "</tr>"+
							     		 "<tr class=xl66 height=39 style='mso-height-source:userset'>"+
							     		  "<td class=xl68 width=150pt style='border-top:none; border-left:none'>来水量</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>回收水量</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>1#反应器</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>2#反应器</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>3#反应器</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>4#反应器</td>"+
							     		  "<td class=xl68  style='border-top:none;border-left:none;width:100pt'>外输水量</td>"+
//							     		  "<td class=xl68 width=58 style='border-top:none;border-left:none;width:44pt'>人工湿地</td>"+
//							     		  "<td class=xl68 width=58 style='border-top:none;border-left:none;width:44pt'>外排水量</td>"+
							     		  "<td class=xl68 width=58 style='border-top:none;border-left:none;width:44pt'>1<fontclass='font7'><sup>#</sup></font><font class='font8'>调储罐</font></td>"+
							     		  "<td class=xl68 width=58 style='border-top:none;border-left:none;width:44pt'>2<fontclass='font7'><sup>#</sup></font><font class='font8'>调储罐</font></td>"+
							     		  "<td class=xl68 width=58 style='border-top:none;border-left:none;width:44pt'>缓冲罐</td>"+
							     		  "<td class=xl68 width=58 style='border-top:none;border-left:none;width:44pt'>1#污水池</td>"+
							     		  "<td class=xl68 width=58 style='border-top:none;border-left:none;width:44pt'>2#污水池</td>"+
							     		 "</tr>";

							     		 var disabl;
							     		var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
							     	for ( var i = 0; i < U1S1S.length; i++) {
							    		if(i==13){
											disabl = "disabled='disabled'";
											tableval +="<tr height=30 style='mso-height-source:userset;height:23.1pt'>"+
								     		"  <td style='display: none'><input id='rpd_u_thin_water_auto_id"+i+"' type='hidden' value='"+U1S1S[i].RPD_U1_WATER_AUTO_ID+"'></td>"+
							    			  "<td id='date"+i+"' height=21 class=xl69 width=100 style='border-top:none;width:47pt'>"+U1S1S[i].rq+"</td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1001"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1001+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='hssllj"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].hssllj+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1005"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1005+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1004"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1004+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1003"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1003+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1002"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1002+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1016"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1016+"></td>"+
								     		  //"<td class=xl67 width=58 style='border-top:none;border-left:none;width:44pt'><input id='rgsdlj"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].rgsdlj+"></td>"+
								     		 // "<td class=xl67 width=58 style='border-top:none;border-left:none;width:44pt'><input id='fit801"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].fit801+"></td>"+
								     		  "<td class=xl67 colspan=5 width=58 style='border-top:none;border-left:none;width:44pt'><input id='lt1001"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'></td>"+
									     	"</tr>";
										}else{
											disabl = "";
											tableval +="<tr height=30 style='mso-height-source:userset;height:23.1pt'>"+
								     		"  <td style='display: none'><input id='rpd_u_thin_water_auto_id"+i+"' type='hidden' value='"+U1S1S[i].RPD_U1_WATER_AUTO_ID+"'></td>"+
							    			  "<td id='date"+i+"' height=21 class=xl69 width=100 style='border-top:none;width:47pt'>"+U1S1S[i].rq+"</td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none width:100pt'><input id='ft1001"+i+"'  type='text' "+disStr+"   "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1001+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='hssllj"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].hssllj+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1005"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1005+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1004"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1004+"></td>"+
								     		  "<td class=xl67 style='border-top:none;border-left:none;width:100pt'><input id='ft1003"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1003+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1002"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1002+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:100pt'><input id='ft1016"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:100px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].ft1016+"></td>"+
								     		 // "<td class=xl67 width=58 style='border-top:none;border-left:none;width:44pt'><input id='rgsdlj"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].rgsdlj+"></td>"+
								     		 // "<td class=xl67 width=58 style='border-top:none;border-left:none;width:44pt'><input id='fit801"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].fit801+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:44pt'><input id='lt1001"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].lt1001+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:44pt'><input id='lt1002"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].lt1002+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:44pt'><input id='lt1007"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].lt1007+"></td>"+
								     		  "<td class=xl67 style='border-top:none;border-left:none;width:44pt'><input id='lt1012"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].lt1012+"></td>"+
								     		  "<td class=xl67  style='border-top:none;border-left:none;width:44pt'><input id='lt1011"+i+"'  type='text' "+disStr+"    "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+U1S1S[i].lt1011+"></td>"+
												//"<td class=xl74 width=69 style='border-top:none;border-left:none;width:52pt'><input type='hidden' id='RPD_U1_WATER_AUTO_ID"+i+"' value='"+U1S1S[i].RPD_U1_WATER_AUTO_ID+"'/>　</td>"+	    			

										     	"</tr>";
										}
								     	//alert(U1S1S[i].rq)
							     		
										}
							     		tableval +="<tr height=80 style='mso-height-source:userset;height:60.0pt'>"+
							     		"<td height=80 class=xl69 width=82 style='height:60.0pt;border-top:none"+
							     		"'>备注：</td>"+
							     		"<td colspan=12 class=xl67 style='border-left:none;'><input id='BZ'  type='text'  style='background:transparent;border:0px solid;width:863px;line-height:25px;height:25px;font-size: 10pt;text-align:left;' value="+BZ+"></td>"+
							     		"</tr>"+
							     		"</table>";
						     								     	
						     								     	
									//↑
						     		$("#tableHtml").html(tableval);	
									    
									   }else{
											$.ligerDialog.error("当前日期无数据，请选择其他日期11！");
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
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<13;i++){
             		firstArr.push($("#RPD_U1_WATER_AUTO_ID"+i).val());
             		firstArr.push($("#date"+i).text());
             		firstArr.push($("#ft1001"+i).val());
             		firstArr.push($("#hssllj"+i).val());
             		firstArr.push($("#ft1005"+i).val());
             		firstArr.push($("#ft1004"+i).val());
             		firstArr.push($("#ft1003"+i).val());
             		firstArr.push($("#ft1002"+i).val());
             		firstArr.push($("#ft1016"+i).val());
             		//firstArr.push($("#rgsdlj"+i).val());
             		//firstArr.push($("#fit801"+i).val());
             		firstArr.push($("#lt1001"+i).val());
             		firstArr.push($("#lt1002"+i).val());
             		firstArr.push($("#lt1007"+i).val());
             		firstArr.push($("#lt1012"+i).val());
             		firstArr.push($("#lt1011"+i).val());
             		
             		
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'u1s1gy_updateU1S1gy.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"ZBR1":ZBR1,"BZ":BZ,"RQ":RQ,"firstStr":firstStr},
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
 					url : 'u1s1gy_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var url = "u1s1gy_exportU1S1.action?txtDate="+encodeURIComponent(txtDate);
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
