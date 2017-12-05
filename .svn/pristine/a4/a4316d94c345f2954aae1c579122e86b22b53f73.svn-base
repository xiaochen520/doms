<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>软化器运行日报</title>
   
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
   
 	  <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
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
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/U2_check.js" type="text/javascript"></script>
 	 <link href="../../page/dayreport/css/t2lsclrb.css" rel="stylesheet" type="text/css" /> 
    <link href="../../page/dayreport/css/rhqyxrb.css" rel="stylesheet" type="text/css" /> 
    <script type="text/javascript">
    var tableval;
	var  SHR ="";
	var  RQ ="";
	var ZBR1 = "";
	var ZBR2 = "";
	var  BZ1 ="";
	var  BZ2 ="";
	var EXPORTNAME ="";
	var mod ;

	var  WATERMESSAGEID ="";
	var RPD_MOLLIFIER_ID;
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
				url : 't2lyxrb_pageInit.action',
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
        	 var txtDate1 = $("#txtDate").val();
        	 var clz = $("#clz").val();
        	 var rhq = $("#rhq").val();
        	 jQuery.ajax({
					type : 'post',
					url : 't2lyxrb_searchDatas.action?nowdata='+parseInt(Math.random()*100000),
					data: {"txtDate":txtDate1,"clz":clz,"rhq":rhq},
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
									    var dataArr = outData.JSONDATA.dataArr;
									    var WATERMESSAGE = outData.JSONDATA.WATERMESSAGE;
									     	if(WATERMESSAGE != null && typeof(WATERMESSAGE)!="undefined"){
									     	
									     		if(WATERMESSAGE.WATERMESSAGEID != null && typeof(WATERMESSAGE.WATERMESSAGEID)!="undefined"){
									     			WATERMESSAGEID =WATERMESSAGE.WATERMESSAGEID;
									     			}else{
									     				WATERMESSAGEID ="";
									     			}
												if(WATERMESSAGE.SHR != null && typeof(WATERMESSAGE.SHR)!="undefined"){
									     			 SHR =WATERMESSAGE.SHR;
									     			}else{
									     				 SHR ="";
										     			}
												 if(WATERMESSAGE.RQ != null && typeof(WATERMESSAGE.RQ)!="undefined"){
									     			  RQ =WATERMESSAGE.RQ;
									     			}else{
									     				RQ = "";
									     			}
									     			
									     			 if(WATERMESSAGE.TBR1 != null && typeof(WATERMESSAGE.TBR1)!="undefined"){
									     				TBR1 =WATERMESSAGE.TBR1;
									     			}else{
									     				TBR1 = "";
									     			}
									     			
									     			 if(WATERMESSAGE.TBR2 != null && typeof(WATERMESSAGE.TBR2)!="undefined"){
									     				TBR2 =WATERMESSAGE.TBR2;
									     			}else{
									     				TBR2 = "";
									     			}
									     			
									     			 if(WATERMESSAGE.BZ1 != null && typeof(WATERMESSAGE.BZ1)!="undefined"){
									     			  BZ1 =WATERMESSAGE.BZ1;
									     			}else{
									     				BZ1 = "";
									     			}
									     			
									     			 if(WATERMESSAGE.BZ2 != null && typeof(WATERMESSAGE.BZ2)!="undefined"){
									     			  BZ2 =WATERMESSAGE.BZ2;
									     			}else{
									     				BZ2 = "";
									     			}
									     			
										     		$("#tableHtml").html('');
tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1145 class=xl7531547 style='border-collapse:collapse;table-layout:fixed;width:864pt'>"+
"	 <col class=xl7531547 width=88 style='mso-width-source:userset;mso-width-alt:2816;width:66pt'>"+
"	 <col class=xl8331547 width=70 style='mso-width-source:userset;mso-width-alt: 2240;width:53pt'>"+
"	 <col class=xl7831547 width=70 span=2 style='mso-width-source:userset; mso-width-alt:2240;width:53pt'>"+
"	 <col class=xl7831547 width=54 style='mso-width-source:userset;mso-width-alt:1728;width:41pt'>"+
"	 <col class=xl7831547 width=53 style='mso-width-source:userset;mso-width-alt:1696;width:40pt'>"+
"	 <col class=xl7831547 width=44 style='mso-width-source:userset;mso-width-alt:1408;width:33pt'>"+
"	 <col class=xl7831547 width=47 style='mso-width-source:userset;mso-width-alt:1504;width:35pt'>"+
"	 <col class=xl7831547 width=48 style='mso-width-source:userset;mso-width-alt:1536;width:36pt'>"+
"	 <col class=xl7831547 width=53 style='mso-width-source:userset;mso-width-alt:1696;width:40pt'>"+
"	 <col class=xl7831547 width=48 span=2 style='mso-width-source:userset;mso-width-alt:1536;width:36pt'>"+
"	 <col class=xl7831547 width=53 span=2 style='mso-width-source:userset;mso-width-alt:1696;width:40pt'>"+
"	 <col class=xl7831547 width=42 span=4 style='mso-width-source:userset;mso-width-alt:1344;width:32pt'>"+
"	 <col class=xl7831547 width=86 style='mso-width-source:userset;mso-width-alt:2752;width:65pt'>"+
"	 <col class=xl7831547 width=92 style='mso-width-source:userset;mso-width-alt:2944;width:69pt'>"+
"	 <tr class=xl7631547 height=36 style='mso-height-source:userset;height:27.6pt'>"+
"	 <td colspan=20 height=36 class=xl9531547 width=1145 style='height:27.6pt;width:864pt'>风城供汽联合站特二联水处理日报<font class='font531547'><span"+
"	 style='mso-spacerun:yes'>&nbsp;</span></font></td>"+
"	 </tr>"+
"	 <tr>"+
"	  <td colspan=8 class=xl10231547></td>"+
"	  <td colspan=2 class=xl10231547 height=30 width=101 style='border-left:none;width:76pt'>审核人</td>"+
"	  <td colspan=4 class=xl10231547 width=202 style='border-left:none;width:152pt'>"+WATERMESSAGE.SHR+"</td>"+
"	  <td colspan=2 class=xl9831547 width=346 style='border-right:.5pt solid black; border-left:none;width:262pt'>日期</td>"+
"	<td colspan=4 class=xl9831547 width=346 style='border-right:.5pt solid black; border-left:none;width:262pt'>"+WATERMESSAGE.RQ+"</td>"+

"</tr>"+
"	 <tr class=xl7731547 height=24 style='mso-height-source:userset;height:18.0pt'>"+
"  <td rowspan=3 height=99 class=xl11331319 width=99 style='border-bottom:.5pt solid black;height:60.0pt;border-top:none;width:68pt;border-right:.5pt solid black;height:60.0pt;border-left:.5pt solid black;height:60.0pt'>"+
"<img src='../../img/xt2l.jpg'></img>"+
"  </td>"+
"	  <td colspan=13 class=xl7931547 width=711 style='border-left:none;width:536pt'>设备关键数据</td>"+
"	  <td colspan=4 class=xl9431547 style='border-left:none'>水质（清水、污水）</td>"+
"	  <td colspan=2 rowspan=3 class=xl9431547>其它</td>"+
"	 </tr>"+
"	 <tr class=xl7731547 height=20 style='mso-height-source:userset;height:15.0pt'>"+
"	  <td colspan=3 height=20 class=xl7931547 width=210 style='height:15.0pt; border-left:none;width:159pt'>水量</td>"+
"	  <td colspan=6 class=xl9331547 width=299 style='border-left:none;width:225pt'>液位</td>"+
"	  <td colspan=4 class=xl9431547 style='border-left:none'>压力</td>"+
"	  <td rowspan=2 class=xl7931547 width=42 style='border-top:none;width:32pt'>硬度</td>"+
"	  <td rowspan=2 class=xl7931547 width=42 style='border-top:none;width:32pt'>含氧</td>"+
"	  <td rowspan=2 class=xl7931547 width=42 style='border-top:none;width:32pt'>氯根</td>"+
"	  <td rowspan=2 class=xl7931547 width=42 style='border-top:none;width:32pt'>碱度</td>"+
"	 </tr>"+
"	 <tr class=xl7731547 height=54 style='height:40.5pt'>"+
"	  <td height=54 class=xl8231547 width=70 style='height:40.5pt;border-top:none; border-left:none;width:53pt'>软化器总出水量</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>除氧器总出水量</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>增压泵总出水量</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>净化水生水罐</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>净化水软水罐</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>清水生水</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>清水软水</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>高盐池</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none;width:53pt'>低盐池</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>一期增压泵</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none; width:53pt'>二期增压泵</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none;width:53pt'>一期压缩空气</td>"+
"	  <td class=xl7931547 width=70 style='border-top:none;border-left:none;width:53pt'>二期压缩空气</td>"+
"	 </tr>";


var rowdisabled = "";
 for ( var i = 0; i < dataArr.length; i++) {
	 rowdisabled = "";
	 if(dataArr[i].RPD_RHSCL_T2_ID != null && dataArr[i].RPD_RHSCL_T2_ID !="" && typeof(dataArr[i].RPD_RHSCL_T2_ID)!="undefined"){
		 rowdisabled = "";
	 }else{
		 rowdisabled = "disabled='disabled'";
	 }
		tableval +="<tr class=xl7731547 height=23 style='mso-height-source:userset;height:17.25pt'>"+
"	  <td height=23 class=xl8131547 width=88 style='height:17.25pt;border-top:none;"+
	"<td height=23 class=xl8131547 width=88 style='height:17.25pt;border-top:none;width:66pt'><input id='BBSJ"+i+"' onkeyup='checkData(this)' type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+dataArr[i].BBSJ+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='RHQZCSL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:73px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].RHQZCSL+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='CYQZCSL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:73px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].CYQZCSL+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='ZYBZCSL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:73px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].ZYBZCSL+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='LT_30101A"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].LT_30101A+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='LT_30101B"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].LT_30101B+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='LT_30102A"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].LT_30102A+"'/></td>"+
"	  <td class=xl8031547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='LT_30102B"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].LT_30102B+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='LT_30301"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].LT_30301+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='LT_30302"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].LT_30302+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='ZYBYL1"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].ZYBYL1+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='ZYBYL2"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].ZYBYL2+"'/></td>"+
"	  <td class=xl8931547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='YSKQYL1"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].YSKQYL1+"'/></td>"+
"	  <td class=xl8831547 width:66 style='border-top:none;border-left:none;width:66pt'><input id='YSKQYL2"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].YSKQYL2+"'/></td>"+
"	  <td class=xl11131547  width=66 style='border-left:none;width:66pt;background:transparent'><input id='HARDNESS"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].HARDNESS+"'/></td>"+
"	  <td class=xl11131547  width=66 style='border-left:none;width:66pt;background:transparent'><input id='OXYGEN_BEARING"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].OXYGEN_BEARING+"'/></td>"+
"	  <td class=xl11131547  width=66 style='border-left:none;width:66pt;background:transparent'><input id='CHLORIDE"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].CHLORIDE+"'/></td>"+
"	  <td class=xl11131547  width=66 style='border-left:none;width:66pt;background:transparent'><input id='BASICITY"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:45px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+dataArr[i].BASICITY+"'/></td>"+
"  <td style='display: none'><input id='RPD_RHSCL_T2_ID"+i+"' type='hidden' value='"+dataArr[i].RPD_RHSCL_T2_ID+"'></td>";

if(i==0){
		tableval +="<td   colspan=2 class=xl8931547 width=178 style='border-left:none;width:134pt'>盐车登记：</td>"+
		"</tr>"; 
		}
		
	if(i==1){
		tableval +="<td  class=xl8931547 width=86 style='border-top:none;border-left:none;width:65pt'>车号</td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none;width:69pt'>重量</td>"+
		"</tr>"; 
		}
	if(i==2){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='CAR_NO1' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.CAR_NO1+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='CAR_WEIGHT1' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.CAR_WEIGHT1+"'/></td>"+
		"</tr>"; 
}
	if(i==3){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='CAR_NO2' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.CAR_NO2+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='CAR_WEIGHT2' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.CAR_WEIGHT2+"'/></td>"+
		"</tr>"; 
}
	if(i==4){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='CAR_NO3' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.CAR_NO3+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='CAR_WEIGHT3' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.CAR_WEIGHT3+"'/></td>"+
		"</tr>"; 
}
	if(i==5){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='CAR_NO4' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.CAR_NO4+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='CAR_WEIGHT4' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.CAR_WEIGHT4+"'/></td>"+
		"</tr>"; 
}
	if(i==6){
		tableval +="<td colspan=2 class=xl8931547 width=178 style='border-left:none;width:134pt'>亚硫酸钠加药：</td>"+
		"</tr>"; 
}
	if(i==7){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none;width:65pt'>加药：</td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none;width:69pt'>重量：</td>"+
		"</tr>"; 
}
	if(i==8){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='SODIUMS_DOSING1' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.SODIUMS_DOSING1+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='DOS_WEIGHT1' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.DOS_WEIGHT1+"'/></td>"+
		"</tr>"; 
}
	if(i==9){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='SODIUMS_DOSING2' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.SODIUMS_DOSING2+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='DOS_WEIGHT2' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.DOS_WEIGHT2+"'/></td>"+
		"</tr>"; 
}
	if(i==10){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='SODIUMS_DOSING3' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.SODIUMS_DOSING3+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='DOS_WEIGHT3' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.DOS_WEIGHT3+"'/></td>"+
		"</tr>"; 
}
	if(i==11){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='SODIUMS_DOSING4' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.SODIUMS_DOSING4+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='DOS_WEIGHT4' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.DOS_WEIGHT4+"'/></td>"+
		"</tr>"; 
	}
	if(i==12){
		tableval +="<td class=xl8931547 width=86 style='border-top:none;border-left:none; width:65pt'>"+
		"<input id='SODIUMS_DOSING5' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.SODIUMS_DOSING5+"'/></td>"+
		"<td class=xl8931547 width=92 style='border-top:none;border-left:none; width:69pt'>"+
		"<input id='DOS_WEIGHT5' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.DOS_WEIGHT5+"'/></td>"+
		"</tr>"; 
		}
	}	
	tableval +="<tr class=xl7731547 height=30 style='mso-height-source:userset;height:22.5pt'>"+
"	  <td colspan=8 height=30 class=xl10731547 width=496 style='height:22.5pt;width:374pt;background:transparent'>记事：白班<br><br></td>"+
"	  <td colspan=12 class=xl10731547 width=649 style='border-left:none;width:490pt;background:transparent'>记事：夜班<br><br></td>"+
"	 </tr>"+
"	 <tr class=xl7731547 height=126 style='mso-height-source:userset;height:94.5pt'>"+
"	  <td colspan=8 height=126 class=xl10931547 width=496 style='height:94.5pt;width:374pt;background:transparent'><input id='BZ1' type='text' style='background:transparent;border:0px solid;width:400px;line-height:30px;height:30px;font-size: 12pt;text-align:left;' value='"+WATERMESSAGE.BZ1+"'/></td>"+

"	  <td colspan=12 class=xl10931547 width=649 style='border-left:none;width:490pt;background:transparent'><input id='BZ2' type='text' style='background:transparent;border:0px solid;width:400px;line-height:30px;height:30px;font-size: 12pt;text-align:left;' value='"+WATERMESSAGE.BZ2+"'/></td>"+
"	 </tr>"+
"	 <tr class=xl7731547 height=60 style='mso-height-source:userset;height:45.0pt'>"+
"	  <td colspan=8 height=60 class=xl11431547 width=496 style='border-right:.5pt solid black;background:transparent; height:45.0pt;width:374pt'><span style='mso-spacerun:yes'>&nbsp;</span>填表人：<input id='TBR1' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.TBR1+"'/></td>"+
"	  <td colspan=12 class=xl11431547  width=649 style='border-right:.5pt solid black;background:transparent; border-left:none;width:490pt'><span style='mso-spacerun:yes'>&nbsp;</span>填表人：<input id='TBR2' type='text' style='background:transparent;border:0px solid;width:123px;line-height:120px;height:80px;font-size: 12pt;text-align:center;' value='"+WATERMESSAGE.TBR2+"'/></td>"+
"	 </tr>"+
"	</table>";
				     											     		
						     								     	
						     								     	
									//↑
						     		$("#tableHtml").html(tableval);	
												 //alert(WATERMESSAGEID);
									     	}else{
									     		WATERMESSAGEID ="";
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
        	//车号1
        	 	var  CAR_NO1 = $("#CAR_NO1").val();
				var  CAR_NO2 = $("#CAR_NO2").val();
				var  CAR_NO3 = $("#CAR_NO3").val();
				var  CAR_NO4 = $("#CAR_NO4").val();
				
				var  CAR_WEIGHT1 = $("#CAR_WEIGHT1").val();
				var  CAR_WEIGHT2 = $("#CAR_WEIGHT2").val();
				var  CAR_WEIGHT3 = $("#CAR_WEIGHT3").val();
				var  CAR_WEIGHT4 = $("#CAR_WEIGHT4").val();
			//	亚硫酸钠加药1
				var  SODIUMS_DOSING1 = $("#SODIUMS_DOSING1").val();
				var  SODIUMS_DOSING2 = $("#SODIUMS_DOSING2").val();
				var  SODIUMS_DOSING3 = $("#SODIUMS_DOSING3").val();
				var  SODIUMS_DOSING4 = $("#SODIUMS_DOSING4").val();
				var  SODIUMS_DOSING5 = $("#SODIUMS_DOSING5").val();
			//加药重量1
				var  DOS_WEIGHT1 = $("#DOS_WEIGHT1").val();
				var  DOS_WEIGHT2 = $("#DOS_WEIGHT2").val();
				var  DOS_WEIGHT3 = $("#DOS_WEIGHT3").val();
				var  DOS_WEIGHT4 = $("#DOS_WEIGHT4").val();
				var  DOS_WEIGHT5 = $("#DOS_WEIGHT5").val();
				
			 //白班 夜班记事
  			var	BZ1 = $("#BZ1").val();
  			var	BZ2 = $("#BZ2").val();
  			 //白班 夜班 填报人
  			var	TBR1 = $("#TBR1").val();
  			var	TBR2 = $("#TBR2").val();
  			var txtDate = $("#txtDate").val();
  			
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<13;i++){
             		firstArr.push($("#RPD_RHSCL_T2_ID"+i).val());
             		firstArr.push($("#BBSJ"+i).val());
             		firstArr.push($("#RHQZCSL"+i).val());
             		firstArr.push($("#CYQZCSL"+i).val());
             		firstArr.push($("#ZYBZCSL"+i).val());
             		firstArr.push($("#LT_30101A"+i).val());

             		firstArr.push($("#LT_30101B"+i).val());
             		firstArr.push($("#LT_30102A"+i).val());
             		firstArr.push($("#LT_30102B"+i).val());
             		firstArr.push($("#LT_30301"+i).val());
             		
             		firstArr.push($("#LT_30302"+i).val());
             		firstArr.push($("#ZYBYL1"+i).val());
             		firstArr.push($("#ZYBYL2"+i).val());
             		firstArr.push($("#YSKQYL1"+i).val());
             		firstArr.push($("#YSKQYL2"+i).val());
             		
             		firstArr.push($("#HARDNESS"+i).val());
             		firstArr.push($("#OXYGEN_BEARING"+i).val());
             		firstArr.push($("#CHLORIDE"+i).val());
             		firstArr.push($("#BASICITY"+i).val());
             		
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 't2lyxrb_updateDatas.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"WATERMESSAGEID":WATERMESSAGEID,"CAR_NO1":CAR_NO1,"CAR_NO2":CAR_NO2,"CAR_NO3":CAR_NO3,"CAR_NO4":CAR_NO4,"CAR_WEIGHT1":CAR_WEIGHT1,"CAR_WEIGHT2":CAR_WEIGHT2,"CAR_WEIGHT3":CAR_WEIGHT3,
   							"CAR_WEIGHT4":CAR_WEIGHT4,"SODIUMS_DOSING1":SODIUMS_DOSING1,"SODIUMS_DOSING2":SODIUMS_DOSING2,"SODIUMS_DOSING3":SODIUMS_DOSING3,"SODIUMS_DOSING4":SODIUMS_DOSING4,"SODIUMS_DOSING5":SODIUMS_DOSING5,"DOS_WEIGHT1":DOS_WEIGHT1,
   							"DOS_WEIGHT2":DOS_WEIGHT2,"DOS_WEIGHT3":DOS_WEIGHT3,"DOS_WEIGHT4":DOS_WEIGHT4,"DOS_WEIGHT5":DOS_WEIGHT5,
   							"BZ1":BZ1,"BZ2":BZ2,"TBR1":TBR1,"TBR2":TBR2,"txtDate":txtDate,"firstStr":firstStr},
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
         	var clz = $("#clz").val();
         	var rhq = $("#rhq").val();
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行再次审核");
         	}else{
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'rhqyxrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"WATERMESSAGEID":WATERMESSAGEID,"clz":clz,"rhq":rhq},
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
     		
     		var url = "t2lyxrb_exportExcel.action?txtDate="+encodeURIComponent(txtDate)+"&WATERMESSAGEID="+encodeURIComponent(WATERMESSAGEID);
     		
     		//data: {"txtDate":txtDate,"clz":clz,"rhq":rhq},
     		//if (onSearchByDate(txtDate)) {
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    				
    				}
    			});
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
<body link=blue vlink=fuchsia style="padding:10px">
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table >
					<tr>	
						<td align="right"  class="l-table-edit-td" style="width:60px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>
						
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
			
				<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<div style="text-align: center;">
					<div id="tableHtml" style="OVERFLOW:auto; height: 87%;" align="center"></div>
				</div>
					
				

</form>
</body>
</html>
