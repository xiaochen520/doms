<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>稀油注输联合站-原油处理综合日报</title>
   
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
     <link href="../../lib/css/xywscl.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	
 	<link href="../../lib/css/xyyycl.css" rel="stylesheet" type="text/css" />  
 	
    <script type="text/javascript">
    var tableval;
	var  RPD_U_THIN_OIL_GENERAL_ID ="";
	var BBRQ ="";
	var SHR ="";
	var mod;
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
				url : 'xyyycl_pageInit.action',
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
        function changValue(){
	           var WEHXFWLJ0 = $('#WEHXFWLJ0').val(); 
				var XZJXFWLJ0 = $('#XZJXFWLJ0').val(); 
				var FIT11103_Q0 = $('#FIT11103_Q0').val(); 
				var W33BSXFWLJ0 = $('#W33BSXFWLJ0').val(); 
				var FIT1101_Q0 = $('#FIT1101_Q0').val(); 
				var FIT1301_Q0 = $('#FIT1301_Q0').val(); 
				
				var WEHXFWLJ12 = $('#WEHXFWLJ12').val(); 
				var XZJXFWLJ12 = $('#XZJXFWLJ12').val(); 
				var FIT11103_Q12 = $('#FIT11103_Q12').val(); 
				var W33BSXFWLJ12 = $('#W33BSXFWLJ12').val(); 
				var FIT1101_Q12 = $('#FIT1101_Q12').val(); 
				var FIT1301_Q12 = $('#FIT1301_Q12').val(); 
				
				
	            if(WEHXFWLJ0!="" && WEHXFWLJ12!=""){
	            	document.getElementById('WEHXFWLJ').value = (WEHXFWLJ12);
	            }else{
	           		document.getElementById('WEHXFWLJ').value = ("");
	            }
	            if(XZJXFWLJ0!="" && XZJXFWLJ12!=""){
	            	document.getElementById('XZJXFWLJ').value = (XZJXFWLJ12);
	            }else{
	           		document.getElementById('XZJXFWLJ').value = ("");
	            }
	            if(FIT11103_Q0!="" && FIT11103_Q12!=""){
	            	document.getElementById('FIT11103_Q').value = (FIT11103_Q12-FIT11103_Q0);
	            }else{
	           		document.getElementById('FIT11103_Q').value = ("");
	            }
	            if(W33BSXFWLJ0!="" && W33BSXFWLJ12!=""){
	            	document.getElementById('W33BSXFWLJ').value = (W33BSXFWLJ12-W33BSXFWLJ0);
	            }else{
	           		document.getElementById('W33BSXFWLJ').value = ("");
	            }
	            
	            if(FIT1101_Q0!="" && FIT1101_Q12!=""){
	            	document.getElementById('FIT1101_Q').value = (FIT1101_Q12 - FIT1101_Q0);
	            }else{
	           		document.getElementById('FIT1101_Q').value = ("");
	            }
	            if(FIT1301_Q0!="" && FIT1301_Q12!=""){
	            	document.getElementById('FIT1301_Q').value = (FIT1301_Q12-  FIT1301_Q0);
	            }else{
	           		document.getElementById('FIT1301_Q').value = ("");
	            }
	            
            }
            
           function changValue1(){
	           	var SUMWEHYL = 0;
	           	var SUMXZJYL = 0;
	        	var WEHYL0 = $('#WEHYL0').val(); 
				var XZJYL0 = $('#XZJYL0').val(); 
	           	var WEHYL12 = $('#WEHYL12').val(); 
				var XZJYL12 = $('#XZJYL12').val(); 
	           	for(var i=0;i<13;i++){
	           		if ( typeof($("#WEHYL"+i).val())!="undefined"&& $("#WEHYL"+i).val()!=""){
	           		
	           			//SUMWEHYL += parseFloat($("#WEHYL"+i).val());
	           		}
	           		
	           		if ( typeof($("#XZJYL"+i).val())!="undefined"&& $("#XZJYL"+i).val()!=""){
	           		
	           				//SUMXZJYL += parseFloat($("#XZJYL"+i).val());
	           		}
	           	}
	           	//document.getElementById('WEHYL').value = (SUMWEHYL);
	           //	document.getElementById('XZJYL').value = (SUMXZJYL);
	           
	            
	           	document.getElementById('WEHYL').value = (WEHYL12);
	           	document.getElementById('XZJYL').value = (XZJYL12);
	           	
           
           } 
            
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

        function checkDataInt(obj){
        	if(obj.value.length==1)
            {
                obj.value=obj.value.replace(/[^1-9]/g,'')
            }else{
                obj.value=obj.value.replace(/\D/g,'')
            }
        }

        function checkData2p(obj){
        	if(obj.value!="")
            {
                obj.value=Number(obj.value).toFixed(2);
            }
        }

        function checkData1p(obj){
        	if(obj.value!="")
            {
                obj.value=Number(obj.value).toFixed(1);
            }
        }
        
         function onSubmit()
        {
        	 

        	 jQuery.ajax({
					type : 'post',
					url : 'xyyycl_searchXyyycl.action?txtDate='+$("#txtDate").val(),
					success : function(data) {
        			
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
								var disStr;
									if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
										var firstArr  = outData.JSONDATA.firstArr;
										var secondArr  = outData.JSONDATA.secondArr;
										var thirdArr  = outData.JSONDATA.thirdArr;
										var fourArr  = outData.JSONDATA.fourArr;
										var fineArr = outData.JSONDATA.fineArr;
										RPD_U_THIN_OIL_GENERAL_ID = fineArr[0].RPD_U_THIN_OIL_GENERAL_ID;
										BBRQ = fineArr[0].BBRQ1;
										SHR = fineArr[0].SHR;
										if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
									    		$("#tableHtml").html("");
									    	
tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1841 style='border-collapse: "+
" collapse;table-layout:fixed;width:1381pt'> "+
" <col width=72 span=16 style='width:54pt'> "+
" <col width=81 style='mso-width-source:userset;mso-width-alt:2592;width:61pt'> "+
" <col width=72 span=3 style='width:54pt'> "+
" <col width=87 style='mso-width-source:userset;mso-width-alt:2784;width:65pt'> "+
" <col width=72 style='width:54pt'> "+
" <col width=89 style='mso-width-source:userset;mso-width-alt:2848;width:67pt'> "+
" <col width=72 span=2 style='width:54pt'> "+
" <tr class=xl65 height=43 style='mso-height-source:userset;height:32.25pt'> "+
"  <td colspan=25 height=43 class=xl82 width=1841 style='height:32.25pt; "+
"  width:1381pt'>原 油 处 理 综 合 日 报<ruby><font class='font7'><rt class=font7></rt></font></ruby></td> "+
" </tr> "+
" <tr class=xl65 height=20 style='mso-height-source:userset;height:15.0pt'> "+
"  <td height=20 class=xl71 style='height:15.0pt;border-top:none'>单位：<span "+
"  style='display:none'></span><ruby><font class='font7'><rt class=font7></rt></font></ruby></td> "+
//"  <td class=xl71 style='border-top:none;border-left:none'>　</td> "+
"  <td colspan=3 class=xl83 style='border-left:none'>　稀油注输联合站</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'>值班人：<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=11 class=xl83 style='border-left:none'><input id='ZBR' type='text' style='background:transparent;border:0px solid;width:3600px;line-height:25px;height:30px;font-size: 12pt;text-align:left;' value='"+fineArr[0].ZBR+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'>审核：<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=3 class=xl83 style='border-left:none'>"+SHR+"　</td> "+
"  <td colspan=3 class=xl83 style='border-left:none'> "+fineArr[0].BBRQ+"　</td> "+
"  <td colspan=2 class=xl83 style='border-left:none'>　</td> "+
" </tr> "+
" <tr class=xl65 height=20 style='mso-height-source:userset;height:15.0pt'> "+
"  <td colspan=12 height=20 class=xl84 style='height:15.0pt'>一、各罐液位<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=9 class=xl84 style='border-left:none'>二、分离器<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=4 class=xl84 style='border-left:none'>三、各泵参数<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
" </tr> "+
" <tr class=xl65 height=27 style='mso-height-source:userset;height:20.25pt'> "+
"  <td rowspan=2 height=58 class=xl85 width=72 style='height:43.5pt;border-top:none;width:54pt'> "+
"  	<img   src='../../img/tablexiexian.jpg'> "+
"  </td> "+
"  <td colspan=2 class=xl66 width=144 style='border-left:none;width:108pt'>沉降罐油厚（m）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=2 class=xl66 width=144 style='border-left:none;width:108pt'>缓冲罐液位（m）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=6 class=xl66 width=432 style='border-left:none;width:324pt'>净化罐液位（m）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl66 width=72 style='border-top:none;width:54pt'>清水罐液位(m)<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=2 class=xl67 style='border-left:none'>分离器液位(cm)<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl66 width=72 style='border-top:none;width:54pt'>除油器液位(cm)<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl66 width=72 style='border-top:none;width:54pt'>来油管汇压力（MPa）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl66 width=81 style='border-top:none;width:61pt'>分离器压力（MPa）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=4 class=xl67 style='border-left:none'>来油温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=2 class=xl87 style='border-left:none'>提升泵<ruby><font class='font7'><rt "+
"  class=font7></rt></font></ruby></td> "+
"  <td colspan=2 class=xl87 style='border-left:none'>回掺泵<ruby><font class='font7'><rt "+
"  class=font7></rt></font></ruby></td> "+
" </tr> "+
" <tr class=xl65 height=31 style='mso-height-source:userset;height:23.25pt'> "+
"  <td height=31 class=xl66 width=72 style='height:23.25pt;border-top:none; "+
"  border-left:none;width:54pt'>1#罐<ruby><font class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>2#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>1#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>2#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>1#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>2#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>3#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>4#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>5#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>6#罐<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>1#<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>2#<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>乌33<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl67 style='border-top:none;border-left:none'>乌5-1<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>乌5-2<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl66 width=87 style='border-top:none;border-left:none;width:65pt'>夏子街<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'>含水（%）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'>频率（HZ）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'>含水（%）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'>频率（HZ）<ruby><font "+

"  class='font7'><rt class=font7></rt></font></ruby></td> "+
" </tr> ";
for(var i=0;i<firstArr.length;i++){
tableval +=	" <tr class=xl65 height=21 style='mso-height-source:userset;height:15.75pt'> "+
"  <td style='display: none'><input id='RPD_U_THIN_OIL_AUTO_ID"+i+"' type='hidden' value='"+firstArr[i].RPD_U_THIN_OIL_AUTO_ID+"'></td>"+
"  <td height=21 class=xl69 style='height:15.75pt;border-top:none'>"+firstArr[i].BBSJ+"</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1201"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1201+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1202"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1202+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1203"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1203+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1204"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1204+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1205"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1205+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1206"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1206+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1207"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1207+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1208"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1208+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1209"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1209+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1210"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1210+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1601"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1601+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1101"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1101+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1102"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1102+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:75pt'><input id='LIT1103"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT1103+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'><input id='PT1101"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PT1101+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'><input id='FLQYL"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].FLQYL+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'><input id='TE1101"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TE1101+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'><input id='W51LYWD"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].W51LYWD+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'><input id='W52LYWD"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].W52LYWD+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'><input id='TE1103"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TE1103+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='MT1402"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].MT1402+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='SEI1201"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].SEI1201+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='MT1401"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].MT1401+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='SEO1201"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].SEO1201+"'/>　</td> "+
" </tr> ";
}

tableval +=	" <tr class=xl65 height=20 style='mso-height-source:userset;height:15.0pt'> "+
"  <td height=20 class=xl71 style='height:15.0pt;border-top:none'>　</td> "+
"  <td colspan=11 class=xl84 style='border-left:none'>四、相变炉参数<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=13 class=xl88 width=977 style='border-left:none;width:733pt'>计量数据<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
" </tr> "+
" <tr class=xl65 height=28 style='mso-height-source:userset;height:21.0pt'> "+
"  <td rowspan=3 height=70 class=xl89 width=72 style='height:52.5pt;border-top: none;width:54pt'><img  src='../../img/tablexiexian.jpg'> </td> "+
"  <td colspan=5 height=28 width=360 style='height:21.0pt;width:270pt' "+
"  align=left valign=top><![if !vml]><span style='mso-ignore:vglayout; "+
"  position:absolute;z-index:1;margin-left:0px;margin-top:22px;width:4px; "+
"  height:10px'><img width=4 height=10 src=image002.png v:shapes='Line_x0020_3'></span><![endif]><span "+
"  style='mso-ignore:vglayout2'> "+
"  <table cellpadding=0 cellspacing=0> "+
"   <tr> "+
"  <td colspan=5 height=28 class=xl67 width=360 style='height:21.0pt; "+
"  border-left:none;width:270pt'>相变炉（总线）<ruby><font class='font7'><rt "+
"  class=font7></rt></font></ruby></td> "+
"   </tr> "+
"  </table> "+
"  </span></td> "+
"  <td colspan=3 class=xl70 width=216 style='border-left:none;width:162pt'>1#相变炉<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=3 class=xl70 width=216 style='border-left:none;width:162pt'>2#相变炉<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=4 class=xl70 width=288 style='border-left:none;width:216pt'>乌尔禾<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=4 class=xl67 style='border-left:none'>夏子街<ruby><font class='font7'><rt "+
"  class=font7></rt></font></ruby></td> "+
"  <td colspan=2 class=xl67 style='border-left:none'>提升泵<ruby><font class='font7'><rt "+
"  class=font7></rt></font></ruby></td> "+
"  <td colspan=3 class=xl67 style='border-left:none'>乌33补水<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
" </tr> "+
" <tr class=xl65 height=22 style='mso-height-source:userset;height:16.5pt'> "+
"  <td rowspan=2 height=42 class=xl70 width=72 style='height:31.5pt;border-top: "+
"  none;width:54pt'>燃气压力（MPa）<ruby><font class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>进油温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl79 width=72 style='border-top:none;width:54pt'>出油温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>进口压力（MPa）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>出口压力（MPa）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>进油温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>出油温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>炉水温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>进油温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>出油温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>炉水温度℃<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>瞬时流量（m&sup3;/h）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>累积流量（m&sup3;）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>含水率（%）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>油量（t）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=81 style='border-top:none;width:61pt'>瞬时流量（m&sup3;/h）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>累积流量（m&sup3;）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>含水率（%）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>油量（t）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=87 style='border-top:none;width:65pt'>瞬时流量（m&sup3;/h）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>累积流量（m&sup3;）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=89 style='border-top:none;width:67pt'>瞬时流量（m&sup3;/h）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=2 rowspan=2 class=xl70 width=144 style='width:108pt'>累积流量（m&sup3;）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
" </tr> "+
" <tr class=xl65 height=20 style='mso-height-source:userset;height:15.0pt'> "+
" </tr> ";
for(var i=0;i<secondArr.length;i++){
	tableval +=" <tr class=xl65 height=21 style='mso-height-source:userset;height:15.75pt'> "+
	"  <td id='BBSJ"+i+"' height=21 class=xl69 style='height:15.75pt;border-top:none'>"+firstArr[i].BBSJ+"</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='PT1303"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].PT1303+"'/>　</td> "+
	"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:54pt'><input id='TE1301"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TE1301+"'/>　</td> "+
	"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:54pt'><input id='TE1302"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TE1302+"'/>　</td> "+
	"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PT1301"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].PT1301+"'/>　</td> "+
	"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PT1302"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].PT1302+"'/>　</td> "+
	"  <td class=xl69 style='border-top:none;border-left:none'><input id='TES1304"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TES1304+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='TES1301"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TES1301+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='TES1302"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TES1302+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='TES1404"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TES1404+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='TES1401"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TES1401+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='TES1402"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TES1402+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='WEH_FT1"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].WEH_FT1+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='WEHXFWLJ"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].WEHXFWLJ+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='WEH_HS1"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].WEH_HS1+"'/>　</td> "+
	"  <td class=xl71 style='border-top:none;border-left:none'><input id='WEHYL"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue1()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].WEHYL+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='WEH_FT2"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].WEH_FT2+"'/>　</td> "+
	"  <td class=xl71 style='border-top:none;border-left:none'><input id='XZJXFWLJ"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].XZJXFWLJ+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='WEH_HS2"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].WEH_HS2+"'/>　</td> "+
	"  <td class=xl71 style='border-top:none;border-left:none'><input id='XZJYL"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue1()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].XZJYL+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT11103"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT11103+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT11103_Q"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT11103_Q+"'/>　</td> "+
	"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT1601"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT1601+"'/>　</td> "+
	"  <td colspan=2 class=xl83 style='border-left:none'><input id='W33BSXFWLJ"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].W33BSXFWLJ+"'/>　</td> "+
	" </tr> ";
}


tableval +=" <tr class=xl65 height=26 style='mso-height-source:userset;height:19.5pt'> "+
"  <td colspan=12 height=26 class=xl69 style='height:19.5pt'>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'>日累计<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl75 style='border-top:none;border-left:none'><input id='WEHXFWLJ'  type='text' disabled = 'disabled'  style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].WEHXFWLJSUM+"'/>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'>　</td> "+
"  <td class=xl76 align=right style='border-top:none;border-left:none'><input id='WEHYL'  type='text' disabled = 'disabled'  style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].WEHYLSUM+"'/></td> "+
"  <td class=xl71 style='border-top:none;border-left:none'>日累计<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl75 style='border-top:none;border-left:none'><input id='XZJXFWLJ'  type='text' disabled = 'disabled'  style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].XZJXFWLJSUM+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'>　</td> "+
"  <td class=xl76 align=right style='border-top:none;border-left:none'><input id='XZJYL'  type='text' disabled = 'disabled'  style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].XZJYLSUM+"'/></td> "+
"  <td class=xl71 style='border-top:none;border-left:none'>日累计<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl75 style='border-top:none;border-left:none'><input id='FIT11103_Q'  type='text' disabled = 'disabled'  style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].FIT11103_QSUM+"'/>　</td> "+
"  <td class=xl71 style='border-top:none;border-left:none'>日累计<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=2 class=xl90 style='border-left:none'><input id='W33BSXFWLJ'  type='text' disabled = 'disabled'  style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].W33BSXFWLJSUM+"'/>　</td> "+
" </tr> "+
" <tr class=xl65 height=20 style='mso-height-source:userset;height:15.0pt'> "+
"  <td colspan=16 height=20 class=xl91 style='height:15.0pt'>天然气<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=9 class=xl91 style='border-left:none'>手工录入数据<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
" </tr> "+
" <tr class=xl65 height=20 style='mso-height-source:userset;height:15.0pt'> "+
"  <td rowspan=2 height=40 class=xl92 width=72 style='height:30.0pt;border-top: none;width:54pt'><img src='../../img/tablexiexian.jpg'></td> "+
"  <td rowspan=2 class=xl83 style='border-top:none'>　</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>8:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>10:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>12:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>14:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>16:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>18:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>20:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>22:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>0:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>2:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>4:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>6:00</td> "+
"  <td rowspan=2 class=xl69 style='border-top:none'>8:00</td> "+
"  <td rowspan=2 class=xl67 style='border-top:none'>合计<ruby><font class='font7'><rt "+
"  class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl70 width=81 style='border-top:none;width:61pt'>一段加药量<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl83 style='border-top:none'><input id='YDJYL' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].YDJYL+"'/></td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>2#站液位<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl83 style='border-top:none'><input id='EZYW' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].EZYW+"'/> </td> "+
"  <td rowspan=2 class=xl70 width=87 style='border-top:none;width:65pt'>夏子街液位<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl83 style='border-top:none'><input id='XZJYW' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].XZJYW+"'/></td> "+
"  <td rowspan=2 class=xl70 width=89 style='border-top:none;width:67pt'>交油量<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=2 rowspan=2 class=xl83><input id='JYL' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].JYL+"'/> </td> "+
" </tr> "+
" <tr class=xl65 height=20 style='mso-height-source:userset;height:15.0pt'> "+
" </tr> "+
" <tr class=xl65 height=21 style='mso-height-source:userset;height:15.75pt'> "+
"  <td rowspan=2 height=42 class=xl94 style='height:31.5pt;border-top:none'>湿气流量：<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'>瞬时流量<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT11010'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[0].FIT1101+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT11011'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].FIT1101+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT11012'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[2].FIT1101+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT11013'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[3].FIT1101+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT11014'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[4].FIT1101+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT11015'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[5].FIT1101+"'/>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT11016'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[6].FIT1101+"'/>　</td> "+
"  <td class=xl77 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT11017'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[7].FIT1101+"'/>　</td> "+
"  <td class=xl77 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT11018'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[8].FIT1101+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT11019'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[9].FIT1101+"'/>　</td> "+
"  <td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT110110'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[10].FIT1101+"'/>　</td> "+
"  <td class=xl69 style='border-top:none;border-left:none'><input id='FIT110111'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[11].FIT1101+"'/>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT110112'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[12].FIT1101+"'/>　</td> "+
"  <td rowspan=2 class=xl75 style='border-top:none'><input id='FIT1101_Q'  type='text' disabled = 'disabled'  style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].FIT1101_QSUM+"'/>　</td> "+
"  <td rowspan=2 class=xl70 width=81 style='border-top:none;width:61pt'>二段加药量<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl67 style='border-top:none'><input id='EDJYL' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].EDJYL+"'/>　</td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>2#站产气<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl67 style='border-top:none'><input id='EZCQ' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].EZCQ+"'/>　</td> "+
"  <td rowspan=2 class=xl70 width=87 style='border-top:none;width:65pt'>夏子街伴生气<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl67 style='border-top:none'><input id='XZJBSQ' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].XZJBSQ+"'/>　</td> "+
"  <td rowspan=2 class=xl70 width=89 style='border-top:none;width:67pt'>乌尔禾交气量<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=2 rowspan=2 class=xl83><input id='WEHJQL' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].WEHJQL+"'/>　</td> "+
" </tr> "+
" <tr class=xl65 height=21 style='mso-height-source:userset;height:15.75pt'> "+
"  <td height=21 class=xl68 style='height:15.75pt;border-top:none;border-left: "+
"  none'>累积流量<ruby><font class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1101_Q0'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[0].FIT1101_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1101_Q1'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].FIT1101_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1101_Q2'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[2].FIT1101_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1101_Q3'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[3].FIT1101_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1101_Q4'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[4].FIT1101_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1101_Q5'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[5].FIT1101_Q+"'/>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT1101_Q6'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[6].FIT1101_Q+"'/>　</td> "+
"  <td class=xl77 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1101_Q7'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[7].FIT1101_Q+"'/>　</td> "+
"  <td class=xl77 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1101_Q8'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[8].FIT1101_Q+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1101_Q9'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[9].FIT1101_Q+"'/>　</td> "+
"  <td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1101_Q10'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[10].FIT1101_Q+"'/>　</td> "+
"  <td class=xl69 style='border-top:none;border-left:none'><input id='FIT1101_Q11'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[11].FIT1101_Q+"'/>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT1101_Q12'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[12].FIT1101_Q+"'/>　</td> "+
" </tr> "+
" <tr class=xl65 height=21 style='mso-height-source:userset;height:15.75pt'> "+
"  <td rowspan=2 height=42 class=xl94 style='height:31.5pt;border-top:none'>干气流量：<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'>瞬时流量<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT13010'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[0].FIT1301+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT13011'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].FIT1301+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT13012'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[2].FIT1301+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT13013'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[3].FIT1301+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT13014'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[4].FIT1301+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT13015'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[5].FIT1301+"'/>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT13016'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[6].FIT1301+"'/>　</td> "+
"  <td class=xl77 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT13017'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[7].FIT1301+"'/>　</td> "+
"  <td class=xl77 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT13018'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[8].FIT1301+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT13019'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[9].FIT1301+"'/>　</td> "+
"  <td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT130110'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[10].FIT1301+"'/>　</td> "+
"  <td class=xl79 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT130111'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[11].FIT1301+"'/>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT130112'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[12].FIT1301+"'/>　</td> "+
"  <td rowspan=2 class=xl75 style='border-top:none'><input id='FIT1301_Q'  type='text' disabled = 'disabled'  style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].FIT1301_QSUM+"'/>　</td> "+
"  <td rowspan=2 class=xl67 style='border-top:none'>自用气量<ruby><font class='font7'><rt "+
"  class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl83 style='border-top:none'><input id='ZYQL' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].ZYQL+"'/>　</td> "+
"  <td rowspan=2 class=xl70 width=72 style='border-top:none;width:54pt'>卸油车数（夏/乌）<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl83 style='border-top:none'><input id='XYCS' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].XYCS+"'/>　</td> "+
"  <td rowspan=2 class=xl70 width=87 style='border-top:none;width:65pt'>夏子街高气<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td rowspan=2 class=xl83 style='border-top:none'><input id='XZJGQ' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].XZJGQ+"'/>　</td> "+
"  <td rowspan=2 class=xl70 width=89 style='border-top:none;width:67pt'>夏子街交气量<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=2 rowspan=2 class=xl83><input id='XZJJQL' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:65px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+fineArr[0].XZJJQL+"'/>　</td> "+
" </tr> "+
" <tr class=xl65 height=21 style='mso-height-source:userset;height:15.75pt'> "+
"  <td height=21 class=xl68 style='height:15.75pt;border-top:none;border-left: "+
"  none'>累积流量<ruby><font class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1301_Q0'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[0].FIT1301_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1301_Q1'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].FIT1301_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1301_Q2'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[2].FIT1301_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1301_Q3'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[3].FIT1301_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1301_Q4'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[4].FIT1301_Q+"'/>　</td> "+
"  <td class=xl68 style='border-top:none;border-left:none'><input id='FIT1301_Q5'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[5].FIT1301_Q+"'/>　</td> "+
"  <td class=xl67 style='border-top:none;border-left:none'><input id='FIT1301_Q6'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[6].FIT1301_Q+"'/>　</td> "+
"  <td class=xl77 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1301_Q7'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[7].FIT1301_Q+"'/>　</td> "+
"  <td class=xl77 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1301_Q8'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[8].FIT1301_Q+"'/>　</td> "+
"  <td class=xl70 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1301_Q9'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[9].FIT1301_Q+"'/>　</td> "+
"  <td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1301_Q10'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[10].FIT1301_Q+"'/>　</td> "+
"  <td class=xl79 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FIT1301_Q11'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[11].FIT1301_Q+"'/>　</td> "+
"  <td class=xl73 style='border-top:none;border-left:none'><input id='FIT1301_Q12'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[12].FIT1301_Q+"'/>　</td> "+
" </tr> "+
" <tr class=xl65 height=32 style='mso-height-source:userset;height:24.0pt'> "+
"  <td height=32 class=xl81 style='height:24.0pt;border-top:none'>备注：<ruby><font "+
"  class='font7'><rt class=font7></rt></font></ruby></td> "+
"  <td colspan=24 class=xl83 style='border-left:none'><input id='BZ' type='text' style='background:transparent;border:0px solid;width:3600px;line-height:25px;height:30px;font-size: 12pt;text-align:left;' value='"+fineArr[0].BZ+"'/>　</td> "+
" </tr> "+
" <tr class=xl65 height=16 style='height:12.0pt'> "+
"  <td height=16 class=xl65 style='height:12.0pt'></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl80></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
"  <td class=xl65></td> "+
" </tr> "+
" <![if supportMisalignedColumns]> "+
" <tr height=0 style='display:none'> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=81 style='width:61pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=87 style='width:65pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=89 style='width:67pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  <td width=72 style='width:54pt'></td> "+
"  </tr> "+
" <![endif]> "+
"</table> ";

													
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
			
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行修改");
         	}else{
         		var RPD_U_THIN_OIL_GENERAL_ID1 = RPD_U_THIN_OIL_GENERAL_ID;
				var BBRQ1 = BBRQ;
				var YDJYL1 = $("#YDJYL").val();
				var EDJYL1 = $("#EDJYL").val();
				var ZYQL1 = $("#ZYQL").val();
				var EZYW1 = $("#EZYW").val();
				var EZCQ1 = $("#EZCQ").val();
				var XYCS1 = $("#XYCS").val();
				var XZJYW1 = $("#XZJYW").val();
				var XZJBSQ1 = $("#XZJBSQ").val();
				var XZJGQ1 = $("#XZJGQ").val();
				var JYL1 = $("#JYL").val();
				var WEHJQL1 = $("#WEHJQL").val();
				var XZJJQL1 = $("#XZJJQL").val();
				var ZBR1 = $("#ZBR").val();
				
				var BZ1 = $("#BZ").val();
         		var firstArr = [];
             	var firstStr = "";
            	for(var i=0;i<13;i++){
            		firstArr.push($("#RPD_U_THIN_OIL_AUTO_ID"+i).val());
            		firstArr.push($("#BBSJ"+i).text());
					firstArr.push($("#LIT1201"+i).val());
					firstArr.push($("#LIT1202"+i).val());
					firstArr.push($("#LIT1203"+i).val());
					firstArr.push($("#LIT1204"+i).val());
					firstArr.push($("#LIT1205"+i).val());
					firstArr.push($("#LIT1206"+i).val());
					firstArr.push($("#LIT1207"+i).val());
					firstArr.push($("#LIT1208"+i).val());
					firstArr.push($("#LIT1209"+i).val());
					firstArr.push($("#LIT1210"+i).val());
					firstArr.push($("#LIT1601"+i).val());
					firstArr.push($("#LIT1101"+i).val());
					firstArr.push($("#LIT1102"+i).val());
					firstArr.push($("#LIT1103"+i).val());
					firstArr.push($("#PT1101"+i).val());
					firstArr.push($("#FLQYL"+i).val());
					firstArr.push($("#TE1101"+i).val());
					firstArr.push($("#W51LYWD"+i).val());
					firstArr.push($("#W52LYWD"+i).val());
					firstArr.push($("#TE1103"+i).val());
					firstArr.push($("#MT1402"+i).val());
					firstArr.push($("#SEI1201"+i).val());
					firstArr.push($("#MT1401"+i).val());
					firstArr.push($("#SEO1201"+i).val());
					
					firstArr.push($("#PT1303"+i).val());
					firstArr.push($("#TE1301"+i).val());
					firstArr.push($("#TE1302"+i).val());
					firstArr.push($("#PT1301"+i).val());
					firstArr.push($("#PT1302"+i).val());
					firstArr.push($("#TES1304"+i).val());
					firstArr.push($("#TES1301"+i).val());
					firstArr.push($("#TES1302"+i).val());
					firstArr.push($("#TES1404"+i).val());
					firstArr.push($("#TES1401"+i).val());
					firstArr.push($("#TES1402"+i).val());
					firstArr.push($("#WEH_FT1"+i).val());
					firstArr.push($("#WEHXFWLJ"+i).val());
					firstArr.push($("#WEH_HS1"+i).val());
					firstArr.push($("#WEHYL"+i).val());
					firstArr.push($("#WEH_FT2"+i).val());
					firstArr.push($("#XZJXFWLJ"+i).val());
					firstArr.push($("#WEH_HS2"+i).val());
					firstArr.push($("#XZJYL"+i).val());
					firstArr.push($("#FIT11103"+i).val());
					firstArr.push($("#FIT11103_Q"+i).val());
					firstArr.push($("#FIT1601"+i).val());
					firstArr.push($("#W33BSXFWLJ"+i).val());
					
					firstArr.push($("#FIT1101"+i).val());
					firstArr.push($("#FIT1101_Q"+i).val());
					firstArr.push($("#FIT1301"+i).val());
					firstArr.push($("#FIT1301_Q"+i).val());
            		
             		firstStr += arrayToString(firstArr,",");
             		firstStr += ";";
             		firstArr = [];
                	}
            	if (modifyDataFlag($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'xyyycl_updateOil.action?nowdata='+parseInt(Math.random()*100000),
 					data: {
 							"RPD_U_THIN_OIL_GENERAL_ID":RPD_U_THIN_OIL_GENERAL_ID,
							"BBRQ":BBRQ,
							"YDJYL":YDJYL1,
							"EDJYL":EDJYL1,
							"ZYQL":ZYQL1,
							"EZYW":EZYW1,
							"EZCQ":EZCQ1,
							"XYCS":XYCS1,
							"XZJYW":XZJYW1,
							"XZJBSQ":XZJBSQ1,
							"XZJGQ":XZJGQ1,
							"JYL":JYL1,
							"WEHJQL":WEHJQL1,
							"XZJJQL":XZJJQL1,
							"ZBR":ZBR1,
							"BZ":BZ1,
 							"firstStr":firstStr},
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
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行再次审核");
         	}else{
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'xyyycl_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"RPD_U_THIN_OIL_GENERAL_ID":RPD_U_THIN_OIL_GENERAL_ID},
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
         	
     		var url = "xyyycl_exportData.action?reportDate="+encodeURIComponent(BBRQ);
     		if (onSearchByDate(BBRQ)) {
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    				}
    			});
    		} else {
    			$.ligerDialog.error("选择日期无效，请选择其他日期！");
    		}
     	}
        
         function isChange(obj){
	        if(obj.value!="" && obj.name!="1"){
	            obj.disabled = "disabled";
	        }else{
	            obj.name="1";
	        }
	        return true;
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
<body link=blue vlink=fuchsia>
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
						<td align="right" class="l-table-edit-td" style="width:100px" >
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
			
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
<div id="tableHtml" style="OVERFLOW:auto; width: 99%;height: 90%;" align="center">

	</div>
  </form>
</body>
</html>
