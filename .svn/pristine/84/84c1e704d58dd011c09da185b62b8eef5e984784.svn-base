<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>风城油田作业区供汽联合站综合日报</title>
   
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
    <link href="../../page/dayreport/css/gqlhz.css" rel="stylesheet" type="text/css" /> 
    <script type="text/javascript">
    var tableval;
	var  SHR ="";
	var  RQ ="";
	var  BZ1 ="";
	var TBR1="";
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
				url : 'gqlhz_pageInit.action',
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
							if (bottons != null && typeof(bottons)!="undefined"&& bottons!=""){
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
        	 jQuery.ajax({
					type : 'post',
					url : 'gqlhz_searchDatas.action?nowdata='+parseInt(Math.random()*100000),
					data: {"txtDate":txtDate1},
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
									    var firstArr = outData.JSONDATA.firstArr;  //昨日
									    var secondArr = outData.JSONDATA.secondArr; // 今日
									    var thirdArr = outData.JSONDATA.thirdArr;   // 对比
									    var fourArr = outData.JSONDATA.fourArr;    
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
									     			
									     			 if(WATERMESSAGE.BZ1 != null && typeof(WATERMESSAGE.BZ1)!="undefined"){
									     			  BZ1 =WATERMESSAGE.BZ1;
									     			}else{
									     				BZ1 = "";
									     			}
									     			 if(WATERMESSAGE.TBR1 != null && typeof(WATERMESSAGE.TBR1)!="undefined"){
									     				TBR1 =WATERMESSAGE.TBR1;
										     			}else{
										     				TBR1 = "";
										     			}
									     			 if(secondArr[0].RPD_GQLHZ_ID != null && typeof(secondArr[0].RPD_GQLHZ_ID)!="undefined"){
									     				RPD_GQLHZ_ID =secondArr[0].RPD_GQLHZ_ID;
											     			}else{
											     				RPD_GQLHZ_ID = "";
											     			}
									     			var rowdisabled = "";
									     				 if(RPD_GQLHZ_ID != null && RPD_GQLHZ_ID !="" && typeof(RPD_GQLHZ_ID)!="undefined"){
									     					 rowdisabled = "";
									     				 }else{
									     				 	 rowdisabled = "disabled='disabled'";
									     				 }		
										     			//if(RPD_GQLHZ_ID !=null  &&  RPD_GQLHZ_ID !="")
										     		$("#tableHtml").html('');
tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1497 class=xl6532104"+
	 "style='border-collapse:collapse;table-layout:fixed;width:1123pt'>"+
	 "<col class=xl6532104 width=52 style='mso-width-source:userset;mso-width-alt:1901;width:39pt'>"+
	 "<col class=xl6532104 width=194 style='mso-width-source:userset;mso-width-alt:7094;width:146pt'>"+
	 "<col class=xl6532104 width=75 span=3 style='mso-width-source:userset; mso-width-alt:2742;width:56pt'>"+
	 "<col class=xl6532104 width=30 style='mso-width-source:userset;mso-width-alt: 1718;width:35pt'>"+
	 "<col class=xl6532104 width=134 style='mso-width-source:userset;mso-width-alt:4900;width:101pt'>"+
	 "<col class=xl6532104 width=54 style='mso-width-source:userset;mso-width-alt:1974;width:41pt'>"+
	 "<col class=xl6532104 width=75 span=3 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
	 "<col class=xl6532104 width=54 style='mso-width-source:userset;mso-width-alt:1974;width:41pt'>"+
	 "<col class=xl6632104 width=185 style='mso-width-source:userset;mso-width-alt:6765;width:139pt'>"+
	 "<col class=xl6532104 width=75 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
	 "<col class=xl6532104 width=46 style='mso-width-source:userset;mso-width-alt:1682;width:35pt'>"+
	 "<col class=xl6532104 width=131 style='mso-width-source:userset;mso-width-alt:4790;width:98pt'>"+
	 "<col class=xl6532104 width=75 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
	 "<tr height=32 style='mso-height-source:userset;height:24.0pt'>"+
	 "<td colspan=17 rowspan=2 height=71 class=xl10532104 width=1497  style='border-right:.5pt solid black;border-bottom:.5pt solid black;"+
	 "height:53.25pt;width:1123pt'>风城油田作业区供汽联合站综合日报</td>"+
	 "</tr>"+
	 "<tr height=39 style='mso-height-source:userset;height:29.25pt'>"+
	 "</tr>"+
	 "<tr height=40 style='mso-height-source:userset;height:30.0pt'>"+
	  "<td colspan=2 height=40 class=xl10432104 style='height:30.0pt'>受控号</td>"+
	  "<td class=xl10332104 style='border-top:none;border-left:none'>　</td>"+
	  "<td class=xl10332104 style='border-top:none;border-left:none'>　</td>"+
	  "<td class=xl10332104 style='border-top:none;border-left:none'>　</td>"+
	  "<td class=xl10332104 style='border-top:none;border-left:none'>　</td>"+
	  "<td class=xl10332104 style='border-top:none;border-left:none'>　</td>"+
	  "<td class=xl10332104 style='border-top:none;border-left:none'>　</td>"+
	  "<td class=xl10332104 style='border-top:none;border-left:none'>　</td>"+
	  "<td colspan=2 class=xl11132104 style='border-left:none'>审核人</td>"+
	  "<td colspan=2 class=xl10332104 style='border-left:none'>"+WATERMESSAGE.SHR+"</td>"+
	  "<td class=xl11132104 style='border-top:none;border-left:none'>日期</td>"+
	  "<td colspan=3 class=xl6732104 style='border-left:none'>"+WATERMESSAGE.RQ+"</td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td colspan=2 height=26 class=xl9932104 style='height:20.1pt'>项目</td>"+
	  "<td class=xl10032104 style='border-left:none'>昨日</td>"+
	  "<td class=xl10032104 style='border-left:none'>今日</td>"+
	  "<td class=xl10132104 style='border-left:none'>对比</td>"+
	  "<td colspan=3 class=xl9932104 style='border-left:none'>项目</td>"+
	  "<td class=xl10032104 style='border-left:none'>昨日</td>"+
	  "<td class=xl10032104 style='border-left:none'>今日</td>"+
	  "<td class=xl10132104 style='border-left:none'>对比</td>"+
	  "<td colspan=2 class=xl10232104>项目</td>"+
	  "<td class=xl10032104 style='border-left:none'>数据</td>"+
	  "<td class=xl10032104 style='border-left:none'>　</td>"+
	  "<td class=xl10032104 style='border-left:none'>项目</td>"+
	  "<td class=xl10132104 style='border-left:none'>数据</td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td rowspan=7 height=182 class=xl8032104 width=52 style='border-bottom:1.0pt solid black; height:140.7pt;width:39pt'>"+
	    "锅炉<br>"+
	    "运行<br>"+
	   " 数据</td>"+
		"<td class=xl6932104 style='border-left:none'>点炉数</td>"+
	  "<td class=xl7032104 style='border-left:none'>"+firstArr[0].DLS+"</td>"+
	  "<td class=xl7032104 style='border-left:none'><input id='DLS' onkeyup='checkData(this)' type='text' "+rowdisabled+" style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].DLS+"'/></td>"+
	  "<td class=xl7132104 style='border-left:none'>"+thirdArr[0].DLS+"</td>"+
	  "<td rowspan=23 class=xl9432104 width=47 style='border-bottom:1.0pt solid black;width:35pt'>水质<br>情况</td>"+
	  "<td rowspan=4 class=xl9732104 style='border-bottom:1.0pt solid black'>1#特净化水</td>"+
	  "<td class=xl7032104 style='border-left:none;width:80px' >含油 mg/l</td>"+
	  "<td class=xl7032104 style='border-left:none'>"+firstArr[0].HYA+"</td>"+
	  "<td class=xl7032104 style='border-left:none'><input id='HYA' onkeyup='checkData(this)' type='text' "+rowdisabled+" style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].HYA+"'/></td>"+
	  "<td class=xl7132104 style='border-left:none'>"+thirdArr[0].HYA+"</td>"+
	  "<td rowspan=10 class=xl8032104 width=54 style='border-bottom:1.0pt solid black;width:41pt'>液位<br> 情况</td>"+
	  "<td class=xl6932104 style='border-left:none'>集中生水罐液位 /m</td>"+
	  "<td class=xl7132104 style='border-left:none'><input id='JZSSGYW' onkeyup='checkData(this)' "+rowdisabled+"  type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZSSGYW+"'/></td>"+
	  "<td rowspan=8 class=xl8532104 width=46 style='border-bottom:1.0pt solid black; width:35pt'>无盐水<br>情况</td>"+
	  "<td class=xl7032104 style='border-left:none;width:230px'>反渗透供水瞬时 /(m<sup>/h)</td>"+
	  "<td class=xl7132104 style='border-left:none'><input id='FSTGSSS' onkeyup='checkData(this)' "+rowdisabled+"  type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].FSTGSSS+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>湿蒸器注汽量 /m<sup>3</td>"+
	 "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].SZQZQL+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='SZQZQL' onkeyup='checkData(this)' "+rowdisabled+"  type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].SZQZQL+"'/></td>"+
	 "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].SZQZQL+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none;width:80px'>悬浮  mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].XFA+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='XFA' onkeyup='checkData(this)' "+rowdisabled+"  type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].XFA+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].XFA+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>集中转水罐液位/m</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='JZZSGYW' onkeyup='checkData(this)' "+rowdisabled+"  type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZZSGYW+"'/></td>"+
	  "<td class=xl7232104 style='border-bottom:.5pt solid windowtext;'>供水累积量/m<sup>3</td>"+
	  "<td class=xl7332104 style='border-top:none'><input id='GSLJL' onkeyup='checkData(this)' "+rowdisabled+"  type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].GSLJL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 " <td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none;width:230px'>过热及高干度注汽量 /m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].GRGGZQL+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='GRGGZQL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].GRGGZQL+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].GRGGZQL+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none ; width:80px'>硬度 mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].YDA+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='YDA' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].YDA+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].YDA+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>集中软水罐液位/m</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='JZRSGYW' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZRSGYW+"'/></td>"+
	  "<td class=xl7232104>浓盐水排水量/(m<sup>3/h)</td>"+
	  "<td class=xl7332104 style='border-top:none'><input id='NYSPSL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].NYSPSL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>停炉台次</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].TLTC+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='TLTC' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].TLTC+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].TLTC+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none ; width:80px'>氯根 mg/l</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].LGA+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'><input id='LGA' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].LGA+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].LGA+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>1#特清水生水罐液位/m</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='TQSSSGYW1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].TQSSSGYW1+"'/></td>"+
	  "<td class=xl6732104 style='border-left:none'>软水罐液位/m</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='RHGYW' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].RHGYW+"'/></td>"+
	"</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 " <td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>锅炉总运行时间 /h</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].GLZYXSJ+"</td>"+
	 " <td class=xl6732104 style='border-top:none;border-left:none'><input id='GLZYXSJ' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].GLZYXSJ+"'/></td>"+
	 " <td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].GLZYXSJ+"</td>"+
	 " <td rowspan=4 class=xl8732104 style='border-bottom:1.0pt solid black;border-top:none'>2#特净化水</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none; width:80px'>含油 mg/l</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+firstArr[0].HYB+"</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'><input id='HYB' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].HYB+"'/></td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'>"+thirdArr[0].HYB+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none;width:230px'>1#特清水软水罐液位/m</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='TQRSSGYW1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].TQRSSGYW1+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>阻垢剂加药/kg</td>"+
	 " <td class=xl7332104 style='border-top:none;border-left:none'><input id='ZGJJY' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].ZGJJY+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 " <td height=26 class=xl6832104 style='height:20.1pt;border-top:none; border-left:none'>计划停炉时间 /h</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].JHTLSJ+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='JHTLSJ' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].JHTLSJ+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].JHTLSJ+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none; width:80px'>悬浮 mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].XFB+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='XFB' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].XFB+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].XFB+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>1#特净化水生水罐液位/m</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='TJHSSSGYW1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].TJHSSSGYW1+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>空压机压力/Mpa</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='KYJYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].KYJYL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 " <td height=26 class=xl7832104 style='height:20.1pt;border-top:none;border-left:none'>非计划停炉时间 /h</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].FJHTLSJ+"</td>"+
	 " <td class=xl7432104 style='border-top:none;border-left:none'><input id='FJHTLSJ' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].FJHTLSJ+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].FJHTLSJ+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none; width:80px'>硬度 mg/l</td>"+
	 "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].YDB+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='YDB' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].YDB+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].YDB+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>2#特清水生水罐液位/m</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='QSSSGYW2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].QSSSGYW2+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>增压泵压力/Mpa</td>"+
	 " <td class=xl7332104 style='border-top:none;border-left:none'><input id='ZYBYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].ZYBYL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 " <td rowspan=4 height=104 class=xl8332104 width=52 style='border-bottom:1.0pt solid black;height:80.4pt;border-top:none;width:39pt'>蒸汽<br>质量</td>"+
	  "<td class=xl7932104 style='border-top:none;border-left:none'>干度抽检合格率</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+firstArr[0].GGDCJHGL+"</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+secondArr[0].GGDCJHGL+"</td>"+
	 " <td class=xl7732104 style='border-top:none;border-left:none'>"+thirdArr[0].GGDCJHGL+"</td>"+
	 " <td class=xl7432104 style='border-top:none;border-left:none; width:80px'>氯根 mg/l</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].LGB+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'><input id='LGB' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].LGB+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].LGB+"</td>"+
	 " <td class=xl6832104 style='border-top:none;border-left:none'>2#特清水软水罐液位/m</td>"+
	 " <td class=xl7332104 style='border-top:none;border-left:none'><input id='QSRSGYW2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].QSRSGYW2+"'/></td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>丙酮肟加药量/kg</td>"+
	 " <td class=xl7532104 style='border-top:none;border-left:none'><input id='BTJYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].BTJYL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 " <td height=26 class=xl6832104 style='height:20.1pt;border-top:none; border-left:none'>过热度抽检合格率</td>"+
	 " <td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].GRDCJHGL+"</td>"+
	 " <td class=xl6732104 style='border-top:none;border-left:none'>"+secondArr[0].GRDCJHGL+"</td>"+
	 " <td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].GRDCJHGL+"</td>"+
	 " <td rowspan=2 class=xl8732104 style='border-top:none'>集中生水</td>"+
	 " <td class=xl7632104 style='border-top:none;border-left:none; width:80px'>硬度 mg/l</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+firstArr[0].YDC+"</td>"+
	 " <td class=xl7632104 style='border-top:none;border-left:none'><input id='YDC' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].YDC+"'/></td>"+
	 " <td class=xl7732104 style='border-top:none;border-left:none'>"+thirdArr[0].YDC+"</td>"+
	 " <td class=xl6832104 style='border-top:none;border-left:none'>2#特净化水生水罐液位/m</td>"+
	 " <td class=xl7332104 style='border-top:none;border-left:none'><input id='JHSSGYW2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JHSSGYW2+"'/></td>"+
	 " <td rowspan=6 class=xl8332104 width=46 style='border-bottom:1.0pt solid black; border-top:none;width:35pt'>药剂<br> 情况</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>集中加药/kg</td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'><input id='JZJY' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZJY+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>水量抽检合格率</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].SLCJHGL+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+secondArr[0].SLCJHGL+"</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].SLCJHGL+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none; width:80px'>氯根 mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].LGC+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='LGC' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].LGC+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].LGC+"</td>"+
	  "<td class=xl7832104 style='border-top:none;border-left:none'>2#特净化水软水罐液位/m</td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'><input id='JHRSGYW2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JHRSGYW2+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>集中用盐/t</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='JZYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZYL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 " <td height=26 class=xl7832104 style='height:20.1pt;border-top:none;border-left:none'>单耗抽检合格率</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].DHCJHGL+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+secondArr[0].DHCJHGL+"</td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].DHCJHGL+"</td>"+
	  "<td colspan=2 class=xl8932104>集中除氧器出口含氧 mg/l </td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].JZCYQCKHY+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'><input id='JZCYQCKHY' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].JZCYQCKHY+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].JZCYQCKHY+"</td>"+
	  "<td rowspan=7 class=xl8332104 width=54 style='border-bottom:1.0pt solid black;border-top:none;width:41pt'>重要<br>机泵<br>情况</td>"+
	  "<td class=xl7932104 style='border-top:none;border-left:none'>集中转水泵压力/m</td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'><input id='JZZSBYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZZSBYL+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>1#特加药/kg</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='JY1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JY1+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 "<td rowspan=6 height=156 class=xl8332104 width=52 style='border-bottom:1.0pt solid black;height:120.6pt;border-top:none;width:39pt'>能耗<br>统计</td>"+
	  "<td class=xl7932104 style='border-top:none;border-left:none'>湿蒸器锅炉日耗天然气 /m<sup>3</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+firstArr[0].SZQGLRHTRQ+"</td>"+
	 "<td class=xl7632104 style='border-top:none;border-left:none'><input id='SZQGLRHTRQ' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].SZQGLRHTRQ+"'/></td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'>"+thirdArr[0].SZQGLRHTRQ+"</td>"+
	  "<td rowspan=2 class=xl8732104 style='border-top:none'>1#特生水</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none; width:80px'>硬度 mg/l</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+firstArr[0].YDD+"</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'><input id='YDD' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].YDD+"'/></td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'>"+thirdArr[0].YDD+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>集中增压泵压力/MPa</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='JZZYBYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZZYBYL+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>1#特用盐/t</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='YY1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].YY1+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>过热锅炉日耗天然气/m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].GRGLRHTRQ+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='GRGLRHTRQ' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].GRGLRHTRQ+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].GRGLRHTRQ+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none; width:80px'>氯根 mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].LGD+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='LGD' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].LGD+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].LGD+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>1#特一期增压泵压力/MPa</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='YQZYBYL1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].YQZYBYL1+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>2#特加药/kg</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='JY2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JY2+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>湿蒸器锅炉日耗电/m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].SZQGLRHD+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='SZQGLRHD' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].SZQGLRHD+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].SZQGLRHD+"</td>"+
	  "<td colspan=2 class=xl8932104>1#除氧器出口含氧 mg/l</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].CYQCKHY1+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'><input id='CYQCKHY1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].CYQCKHY1+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].CYQCKHY1+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>1#特二期增压泵压力/MPa</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='EQZYBYL1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].EQZYBYL1+"'/></td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>2#特用盐/t</td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'><input id='YY2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].YY2+"'/></td>"+
	" </tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>过热锅炉日耗电/m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].GRGLRHD+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='GRGLRHD' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].GRGLRHD+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].GRGLRHD+"</td>"+
	  "<td rowspan=2 class=xl8732104 style='border-top:none'>2#特生水</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none; width:80px'>硬度 mg/l</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+firstArr[0].YDE+"</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'><input id='YDE' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].YDE+"'/></td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'>"+thirdArr[0].YDE+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>1#特三期增压泵压力/MPa</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='SQZYBYL1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].SQZYBYL1+"'/></td>"+
	  "<td rowspan=9 class=xl8432104 width=46 style='border-bottom:1.0pt solid black;"+
	  "border-top:none;width:35pt'>燃煤<br>锅炉<br>情况</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>注汽量/m<sup>3</td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'><input id='ZQL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].ZQL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	 " <td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>水处理站日耗电/m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].SCLZRHD+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='SCLZRHD' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].SCLZRHD+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].SCLZRHD+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none; width:80px'>氯根 mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].LGE+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='LGE' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].LGE+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].LGE+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>2#特一期增压泵压力/MPa</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='YQZYBYL2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].YQZYBYL2+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>用水量/m<sup>3</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='YSL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].YSL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl7832104 style='height:20.1pt;border-top:none;border-left:none'>耗煤量/m<sup>3</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].HML+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'><input id='HML' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].HML+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].HML+"</td>"+
	  "<td colspan=2 class=xl8932104>2#特除氧器出口含氧 mg/l</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].CYQCKHY2+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'><input id='CYQCKHY2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].CYQCKHY2+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].CYQCKHY2+"</td>"+
	  "<td class=xl7832104 style='border-top:none;border-left:none'>2#特二期增压泵压力/MPa</td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'><input id='EQZYBYL2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].EQZYBYL2+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>高含盐产水量/m<sup>3</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='GHYCSL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].GHYCSL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td rowspan=6 height=156 class=xl8332104 width=52 style='border-bottom:1.0pt solid black;"+
	  "height:120.6pt;border-top:none;width:39pt'>用水<br> 情况</td>"+
	  "<td class=xl7932104 style='border-top:none;border-left:none'>水源井/m<sup>3</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+firstArr[0].SYJ+"</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'><input id='SYJ' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].SYJ+"'/></td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'>"+thirdArr[0].SYJ+"</td>"+
	  "<td rowspan=5 class=xl8732104 style='border-bottom:1.0pt solid black;border-top:none'>锅炉软水</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none; width:80px'>硬度 mg/l</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'>"+firstArr[0].YDF+"</td>"+
	  "<td class=xl7632104 style='border-top:none;border-left:none'><input id='YDF' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].YDF+"'/></td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'>"+thirdArr[0].YDF+"</td>"+
	  "<td rowspan=6 class=xl8332104 width=54 style='border-bottom:1.0pt solid black; border-top:none;width:41pt'>水处理<br>情况</td>"+
	  "<td class=xl7932104 style='border-top:none;border-left:none'>集中再生设备数量/组</td>"+
	  "<td class=xl7732104 style='border-top:none;border-left:none'><input id='JZZSSBSL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZZSSBSL+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>高盐水排放量/m<sup>3</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='GYSPFL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].GYSPFL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>六净化/m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].LJS+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='LJS' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].LJS+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].LJS+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none; width:80px'>氯根 mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].LGF+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='LGF' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].LGF+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].LGF+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>集中空压机压力/Mpa</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='JZKYJYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].JZKYJYL+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>存煤量/t</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='CML' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].CML+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>1#特净化污水/m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].TJHWS1+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='TJHWS1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].TJHWS1+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].TJHWS1+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none; width:80px'>碱度 mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].XDF+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='XDF' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].XDF+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].XDF+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>1#特再生设备数量/组</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='ZSSBSL1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].ZSSBSL1+"'/></td>"+
	 "<td class=xl6732104 style='border-top:none;border-left:none'>用电量/KW.h</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='YDL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].YDL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>2#特净化污水/m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].TJHWS2+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='TJHWS2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].TJHWS2+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].TJHWS2+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none; width:80px'>含氧 mg/l</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].HYF+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='HYF' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].HYF+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].HYF+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>1#特空压机压力/Mpa</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='TKYJYL1' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].TKYJYL1+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>消泡剂用量/kg</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='XPJYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].XPJYL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl6832104 style='height:20.1pt;border-top:none;border-left:none'>软化器总计供水量/m<sup>3</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>"+firstArr[0].RHQZGSL+"</td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'><input id='RHQZGSL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].RHQZGSL+"'/></td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'>"+thirdArr[0].RHQZGSL+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>PH值</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].PHZ+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'><input id='PHZ' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].PHZ+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].PHZ+"</td>"+
	  "<td class=xl6832104 style='border-top:none;border-left:none'>2#特再生设备数量/组</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='ZSSBSL2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].ZSSBSL2+"'/></td>"+
	  "<td class=xl6732104 style='border-top:none;border-left:none'>磷酸二氢钠用量/kg</td>"+
	  "<td class=xl7332104 style='border-top:none;border-left:none'><input id='LSEQNYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].LSEQNYL+"'/></td>"+
	 "</tr>"+
	 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
	  "<td height=26 class=xl7832104 style='height:20.1pt;border-top:none;border-left:none'>除氧器总计供水量/m<sup>3</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>"+firstArr[0].CYQZGSL+"</td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'><input id='CYQZGSL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].CYQZGSL+"'/></td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'>"+thirdArr[0].CYQZGSL+"</td>"+
	  "<td colspan=2 class=xl9232104 style='width:280px'>无盐水处理出口电导率μs/cm</td>"+
	  "<td class=xl9332104 style='border-top:none;border-left:none'>"+firstArr[0].WYSCLCK+"</td>"+
	  "<td class=xl9332104 style='border-top:none;border-left:none'><input id='WYSCLCK' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].WYSCLCK+"'/></td>"+
	  "<td class=xl9832104 style='border-top:none;border-left:none'>"+thirdArr[0].WYSCLCK+"</td>"+
	 " <td class=xl7832104 style='border-top:none;border-left:none'>2#特空压机压力/Mpa</td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'><input id='TKYJYL2' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].TKYJYL2+"'/></td>"+
	  "<td class=xl7432104 style='border-top:none;border-left:none'>石灰石用量/t</td>"+
	  "<td class=xl7532104 style='border-top:none;border-left:none'><input id='SHSYL' onkeyup='checkData(this)' "+rowdisabled+"   type='text'  style='background:transparent;border:0px solid;width:60px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].SHSYL+"'/></td>"+
	  "<td ><input id='RPD_GQLHZ_ID'  type='hidden' value='"+secondArr[0].RPD_GQLHZ_ID+"'/></td>"+
	"</tr>"+

	 "<tr height=37 style='mso-height-source:userset;height:27.75pt'>"+
	  "<td colspan=17 height=37 class=xl11232104 style='border-right:1.0pt solid black;"+
	  "height:27.75pt'>重要事件记录：</td>"+
	 "</tr>"+
	 "<tr height=62 style='mso-height-source:userset;height:46.5pt'>"+
	  "<td colspan=17 height=62 class=xl115321040123 style='border-right:1.0pt solid black;"+
	  "height:46.5pt'><input id='BZ1' type='text' style='background:transparent;border:0px solid;width:800px;line-height:30px;height:30px;font-size: 12pt;text-align:left' value='"+WATERMESSAGE.BZ1+"'/></td>"+
	 "</tr>"+
	 "<tr height=15 style='mso-height-source:userset;height:12.75pt'>"+
	  "<td colspan=17 height=15 class=xl11232104 style='border-right:1.0pt solid black;"+
	  "height:27.75pt'>填报人：</td>"+
	 "</tr>"+
	 "<tr height=14 style='mso-height-source:userset;height:14.5pt'>"+
	  "<td colspan=17 height=15 class=xl11532104 style='border-right:1.0pt solid black;"+
	  "height:26.5pt'><input id='TBR1' type='text' style='background:transparent;border:0px solid;width:800px;line-height:30px;height:30px;font-size: 12pt;text-align:left;' value='"+WATERMESSAGE.TBR1+"'/></td>"+
	 "</tr>"+
	"</table> ";
				     											     		
						     								     	
						     								     	
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
  			
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
              	
          		var	BZ1 = $("#BZ1").val();
      			var	TBR1 = $("#TBR1").val();
      			var txtDate = $("#txtDate").val();
          		var  RPD_GQLHZ_ID = $("#RPD_GQLHZ_ID").val();
				// --锅炉运行数据
				var  DLS  = $("#DLS").val();
				var  SZQZQL = $("#SZQZQL").val();
				var  GRGGZQL = $("#GRGGZQL").val();
				var  TLTC = $("#TLTC").val();
				var  GLZYXSJ = $("#GLZYXSJ").val();
				var  JHTLSJ = $("#JHTLSJ").val();
				var  FJHTLSJ = $("#FJHTLSJ").val();
				//蒸汽质量
				//var  GGDCJHGL = $("#GGDCJHGL").val();
				//var  GRDCJHGL = $("#GRDCJHGL").val();
				//var  SLCJHGL = $("#SLCJHGL").val();
				//var  DHCJHGL = $("#DHCJHGL").val();
				//能耗统计
				var  SZQGLRHTRQ = $("#SZQGLRHTRQ").val();
				var  GRGLRHTRQ = $("#GRGLRHTRQ").val();
				var  SZQGLRHD = $("#SZQGLRHD").val();
				var  GRGLRHD = $("#GRGLRHD").val();
				var  SCLZRHD = $("#SCLZRHD").val();
				var  HML = $("#HML").val();
				//--用水情况
				var  SYJ = $("#SYJ").val();
				var  LJS = $("#LJS").val();
				var  TJHWS1 = $("#TJHWS1").val();
				var  TJHWS2 = $("#TJHWS2").val();
				var  RHQZGSL = $("#RHQZGSL").val();
				var  CYQZGSL = $("#CYQZGSL").val();
				//1#特净化水
				var  HYA = $("#HYA").val();
				var  XFA = $("#XFA").val();
				var  YDA = $("#YDA").val();
				var  LGA = $("#YDA").val();
				
				var  HYB = $("#HYB").val();
				var  XFB = $("#XFB").val();
				var  YDB = $("#YDB").val();
				var  LGB = $("#LGB").val();
				var  YDC = $("#YDC").val();
				var  LGC = $("#LGC").val();
				var  JZCYQCKHY = $("#JZCYQCKHY").val();
				var  YDD = $("#YDD").val();
				var  LGD = $("#LGD").val();
				
				//1#除氧器出口含氧
				var  CYQCKHY1 = $("#CYQCKHY1").val();
				//2#特生水
				var  YDE = $("#YDE").val();
				var  LGE = $("#LGE").val();
				var  CYQCKHY2 = $("#CYQCKHY2").val();
				//--锅炉软水
				var  YDF = $("#YDF").val();
				var  LGF = $("#LGF").val();
				var  XDF = $("#XDF").val();
				var  HYF = $("#HYF").val();
				var  PHZ = $("#PHZ").val();
				var  WYSCLCK = $("#WYSCLCK").val();
				//液位情况
				var  JZSSGYW = $("#JZSSGYW").val();
				var  JZZSGYW = $("#JZZSGYW").val();
				var  JZRSGYW = $("#JZRSGYW").val();
				
				var  TQSSSGYW1 = $("#TQSSSGYW1").val();
				var  TQRSSGYW1 = $("#TQRSSGYW1").val();
				var  TJHSSSGYW1 = $("#TJHSSSGYW1").val();
				var  QSSSGYW2 = $("#QSSSGYW2").val();
				var  QSRSGYW2 = $("#QSRSGYW2").val();
				var  JHSSGYW2 = $("#JHSSGYW2").val();
				var  JHRSGYW2 = $("#JHRSGYW2").val();
				//重要机泵情况
				var  JZZSBYL = $("#JZZSBYL").val();
				var  JZZYBYL = $("#JZZYBYL").val();
				var  YQZYBYL1 = $("#YQZYBYL1").val();
				var  EQZYBYL1 = $("#EQZYBYL1").val();
				var  SQZYBYL1 = $("#SQZYBYL1").val();
				var  YQZYBYL2 = $("#YQZYBYL2").val();
				var  EQZYBYL2 = $("#EQZYBYL2").val();
				//水处理情况
				var  JZZSSBSL = $("#JZZSSBSL").val();
				var  JZKYJYL = $("#JZKYJYL").val();
				var  ZSSBSL1 = $("#ZSSBSL1").val();
				var  TKYJYL1 = $("#TKYJYL1").val();
				var  ZSSBSL2 = $("#ZSSBSL2").val();
				var  TKYJYL2 = $("#TKYJYL2").val();
				// 无盐水情况
				var  FSTGSSS = $("#FSTGSSS").val();
				var  GSLJL = $("#GSLJL").val();
				var  NYSPSL = $("#NYSPSL").val();
				var  RHGYW = $("#RHGYW").val();
				var  ZGJJY = $("#ZGJJY").val();
				var  KYJYL = $("#KYJYL").val();
				var  ZYBYL = $("#ZYBYL").val();
				var  BTJYL = $("#BTJYL").val();
				//药剂情况
				var  JZJY = $("#JZJY").val();
				var  JZYL = $("#JZYL").val();
				var  JY1 = $("#JY1").val();
				var  YY1 = $("#YY1").val();
				var  JY2 = $("#JY2").val();
				var  YY2 = $("#YY2").val();
				//--燃煤锅炉情况
				var  ZQL = $("#ZQL").val();
				var  YSL = $("#YSL").val();
				var  GHYCSL = $("#GHYCSL").val();
				var  GYSPFL = $("#GYSPFL").val();
				var  CML = $("#CML").val();
				var  YDL = $("#YDL").val();
				var  XPJYL = $("#XPJYL").val();
				var  LSEQNYL = $("#LSEQNYL").val();
				var  SHSYL = $("#SHSYL").val();
          		
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'gqlhz_updateDatas.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPD_GQLHZ_ID":RPD_GQLHZ_ID,"DLS":DLS,"SZQZQL":SZQZQL,"GRGGZQL":GRGGZQL,"TLTC":TLTC,
          			"GLZYXSJ":GLZYXSJ,"JHTLSJ":JHTLSJ,"FJHTLSJ":FJHTLSJ,
          			"SZQGLRHTRQ":SZQGLRHTRQ,"GRGLRHTRQ":GRGLRHTRQ,"SZQGLRHD":SZQGLRHD,
          			"GRGLRHD":GRGLRHD,"SCLZRHD":SCLZRHD,"HML":HML,"SYJ":SYJ,"LJS":LJS,"TJHWS1":TJHWS1,
          			"TJHWS2":TJHWS2,"RHQZGSL":RHQZGSL,"CYQZGSL":CYQZGSL,"HYA":HYA,"XFA":XFA,"YDA":YDA,
          			"LGA":LGA,"HYB":HYB,"XFB":XFB,"YDB":YDB,"LGB":LGB,"YDC":YDC,"LGC":LGC,"JZCYQCKHY":JZCYQCKHY,"YDD":YDD,
          			"LGD":LGD,"CYQCKHY1":CYQCKHY1,"YDE":YDE,"LGE":LGE,"CYQCKHY2":CYQCKHY2,"YDF":YDF,"LGF":LGF,"XDF":XDF,
          			"HYF":HYF,"PHZ":PHZ,"WYSCLCK":WYSCLCK,"JZSSGYW":JZSSGYW,"JZZSGYW":JZZSGYW,"JZRSGYW":JZRSGYW,
          			"TQSSSGYW1":TQSSSGYW1,"TQRSSGYW1":TQRSSGYW1,"TJHSSSGYW1":TJHSSSGYW1,"QSSSGYW2":QSSSGYW2,"QSRSGYW2":QSRSGYW2,
          			"JHSSGYW2":JHSSGYW2,"JHRSGYW2":JHRSGYW2,"JZZSBYL":JZZSBYL,"JZZYBYL":JZZYBYL,"YQZYBYL1":YQZYBYL1,
          			"EQZYBYL1":EQZYBYL1,"SQZYBYL1":SQZYBYL1,"YQZYBYL2":YQZYBYL2,"EQZYBYL2":EQZYBYL2,"JZZSSBSL":JZZSSBSL,
          			"JZKYJYL":JZKYJYL,"ZSSBSL1":ZSSBSL1,"TKYJYL1":TKYJYL1,"ZSSBSL2":ZSSBSL2,"TKYJYL2":TKYJYL2,"FSTGSSS":FSTGSSS,
          			"GSLJL":GSLJL,"NYSPSL":NYSPSL,"RHGYW":RHGYW,"ZGJJY":ZGJJY,"KYJYL":KYJYL,"ZYBYL":ZYBYL,"BTJYL":BTJYL,
          			"JZJY":JZJY,"JZYL":JZYL,"JY1":JY1,"YY1":YY1,"JY2":JY2,"YY2":YY2,"ZQL":ZQL,"YSL":YSL,
          			"GHYCSL":GHYCSL,"GYSPFL":GYSPFL,"CML":CML,"YDL":YDL,"XPJYL":XPJYL,"LSEQNYL":LSEQNYL,"SHSYL":SHSYL,
          			"BZ1":BZ1,"txtDate":txtDate,"WATERMESSAGEID":WATERMESSAGEID,"TBR1":TBR1,"BZ1":BZ1},
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

         function onAudit()
         {
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行再次审核");
         	}else{
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'gqlhz_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"WATERMESSAGEID":WATERMESSAGEID},
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

         function dayreport(){
 			jQuery.ajax({
 				type : 'post',
 				url : 'gqlhz_dayreport.action?nowdata='+parseInt(Math.random()*100000),
 				async : false,
 				success : function(data) {
 					//ownerData=JSON2.stringify(data);
 					if (data != null && typeof(data)!="undefined"){
 							var outData = eval('(' + data + ')');
 							if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
 								$.ligerDialog.error(outerror(outData.ERRCODE));
 							}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
 								$.ligerDialog.error(outData.ERRMSG);
 							}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
 								$.ligerDialog.success(outData.CONFIRMMSG);
 							}else{
 								$.ligerDialog.success("操作成功");
 							}
 					}
 				},
 				error : function(data) {
 			
 				}
 			});
 		}
  		
         function onExport() {
     		var txtDate=$("#txtDate").val();
     		
     		var url = "gqlhz_exportExcel.action?txtDate="+encodeURIComponent(txtDate)+"&WATERMESSAGEID="+encodeURIComponent(WATERMESSAGEID);
     		
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
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:dayreport()" class="l-button" id="dayreport" style="width:100px;" title="8点后才可以生成当天的日报" >生成日报</a>

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
