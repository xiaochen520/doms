<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>1#联合站-生产简报</title>
   
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
     <link href="../../lib/css/u1zhrb.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	
 	<link href="./1#稠油联合处理站日生产运行简报2014.06.30模板.files/stylesheet.css" rel="stylesheet" type="text/css" />  
    <script type="text/javascript">
    var tableval;
	var  RPD_CRUDE_TRANSITION_ID ="";
	var BBRQ ;
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
				url : 'u1ZHRB_pageInit.action',
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
        function changeRed(obj){  //改变值后 判断是否变红
        	if(obj.id=='s1hytcgjk'){
            	if(obj.value >=5000){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }

        	if(obj.id=='s1xfwtcgjk'){
            	if(obj.value >=500){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s1hytcgck'){
            	if(obj.value >=150){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s1xfwtcgck'){
            	if(obj.value >=150){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s1hyfygck'){
            	if(obj.value >=15){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s1xfwfygck'){
            	if(obj.value >=15){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s1hyyjck'){
            	if(obj.value >=5){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s1xfwyjck'){
            	if(obj.value >=5){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	
        	if(obj.id=='s1hyejck'){
            	if(obj.value >=2){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s1xfwejck'){
            	if(obj.value >=2){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='cjg11hs'){
            	if(obj.value >=10){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='cjg12hs'){
            	if(obj.value >=10){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='tsbhs'){
            	if(obj.value >=3){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='gcs11hy'){
            	if(obj.value >=10000){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2hytcgjk'){
            	if(obj.value >=5000){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2xfwtcgjk'){
            	if(obj.value >=500){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2hytcgck'){
            	if(obj.value >=150){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2xfwtcgck'){
            	if(obj.value >=150){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2hyqfjck'){
            	if(obj.value >=15){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2xfwqfjck'){
            	if(obj.value >=15){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2hyyjck'){
            	if(obj.value >=5){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }

        	if(obj.id=='s2xfwyjck'){
            	if(obj.value >=5){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2hyejck'){
            	if(obj.value >=2){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='s2xfwejck'){
            	if(obj.value >=2){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='jcjggcshy'){
            	if(obj.value >=300){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='jcjggcsxfw'){
            	if(obj.value >=300){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='wfchshy'){
            	if(obj.value >=500){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	if(obj.id=='wfchsxfw'){
            	if(obj.value >=300){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
            if(obj.id=='gcs12hy'){
            	if(obj.value >=1000){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        }
         function onSubmit()
        {
        	 

        	 jQuery.ajax({
					type : 'post',
					url : 'u1ZHRB_searchU1ZHRB.action?txtDate='+$("#txtDate").val(),
					success : function(data) {
        			
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							//alert(data)
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
						 				var fiveArr  = outData.JSONDATA.fiveArr;
										//RPD_U_THIN_OIL_GENERAL_ID = fineArr[0].RPD_U_THIN_OIL_GENERAL_ID;
						 				if(secondArr[0].rq != null && typeof(secondArr[0].rq)!="undefined"){
						 					var  BBRQ = secondArr[0].rq;
						     			}else{
						     				BBRQ = "";
						     			}
						 				if(fourArr[0].RPD_CRUDE_TRANSITION_ID!= null && typeof(fourArr[0].RPD_CRUDE_TRANSITION_ID)!="undefined"){
						 					var  RPD_CRUDE_TRANSITION_ID = fourArr[0].RPD_CRUDE_TRANSITION_ID;
						     			}else{
						     				RPD_CRUDE_TRANSITION_ID = "";
						     			}
						 				var xyt = thirdArr[0].xyt;
						 				//alert(xyt)
										SHR = secondArr[0].shr;
										//alert(SHR)
										if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
									    		$("#tableHtml").html("");
									    	
tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1553 style='border-collapse:"+
	 "collapse;table-layout:fixed;width:1168pt'>"+
	 "<col width=72 span=5 style='mso-width-source:userset;mso-width-alt:2304;width:54pt'>"+
	 "<col width=101 style='mso-width-source:userset;mso-width-alt:3232;width:76pt'>"+
	 "<col width=72 span=2 style='mso-width-source:userset;mso-width-alt:2304;width:54pt'>"+
	 "<col width=75 style='mso-width-source:userset;mso-width-alt:2400;width:56pt'>"+
	 "<col width=86 style='mso-width-source:userset;mso-width-alt:2752;width:65pt'>"+
	 "<col width=94 style='mso-width-source:userset;mso-width-alt:3008;width:71pt'>"+
	 "<col width=96 span=2 style='mso-width-source:userset;mso-width-alt:3072;width:72pt'>"+
	 "<col width=86 style='mso-width-source:userset;mso-width-alt:2752;width:65pt'>"+
	 "<col width=77 style='mso-width-source:userset;mso-width-alt:2464;width:58pt'>"+
	 "<col width=90 span=2 style='mso-width-source:userset;mso-width-alt:2880;width:68pt'>"+
	 "<col width=86 style='mso-width-source:userset;mso-width-alt:2752;width:65pt'>"+
	 "<col width=72 style='width:54pt'>"+
	 "<tr height=46 style='mso-height-source:userset;height:34.5pt'>"+
	 "<td colspan=18 height=46 class=xl130 width=1481 style='height:34.5pt;width:1114pt'>一号稠油联合处理站日生产简报</td>"+
	 "<td width=72 style='width:54pt'></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 "<td colspan=2 height=24 class=xl131 width=144 style='height:18.0pt;width:108pt'>值班人：</td>"+
	 "<td colspan=8 class=xl132 width=622 style='width:467pt'><input id='zbr' type='text' style='background:transparent;border:0px solid;width:200px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].zbr+"'/></td>"+
	  "<td class=xl83 width=94 style='width:71pt'>审核：</td>"+
	  "<td colspan=3 class=xl132 width=278 style='width:209pt'>"+secondArr[0].shr+"</td>"+
	  "<td class=xl83 width=77 style='width:58pt'>日期：</td>"+
	  "<td colspan=3  id ='rq' class=xl132 width=266 style='border-right:1.0pt solid black;width:201pt'>"+BBRQ+"</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=30 style='height:22.5pt'>"+
	  "<td colspan=18 height=30 class=xl134 width=1481 style='border-right:1.0pt solid black;height:22.5pt;width:1114pt'>一<span style='mso-spacerun:yes'>&nbsp;</span>原油处理</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=19 style='height:14.25pt'>"+
	 " <td rowspan=2 height=99 class=xl152 width=72 style='height:74.25pt; width:54pt'>　</td>"+
	  "<td rowspan=2 class=xl90 width=72 style='width:54pt'>产液量<br>（m）</font></td>"+
	  "<td rowspan=2 class=xl90 width=72 style='width:54pt'>产油量<br>（t）</td>"+
	  "<td rowspan=2 class=xl90 width=72 style='width:54pt'>外输油量<br>（t）</td>"+
	  "<td rowspan=2 class=xl90 width=72 style='width:54pt'>库存油量<br>（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	  "<td rowspan=2 colspan=2 class=xl90 width=101 style='width:76pt'>掺柴量<br>（t）</td>"+
	  //"<td rowspan=2 class=xl90 width=72 style='width:54pt'>旋流量（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	  "<td colspan=4 class=xl90 width=327 style='border-left:none;width:246pt'>正相药剂</td>"+
	  "<td colspan=4 class=xl90 width=355 style='border-left:none;width:267pt'>反相药剂</td>"+
	  "<td colspan=2 class=xl90 width=180 style='border-left:none;width:136pt'>重1循环预热液</td>"+
	  "<td rowspan=5 class=xl91 width=86 style='width:65pt'>　</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=80 style='mso-height-source:userset;height:60.0pt'>"+
	  "<td colspan=2 height=80 class=xl66 width=147 style='height:60.0pt;border-left:none;width:110pt'>正相加药量（kg）</td>"+
	  "<td colspan=2 class=xl66 width=180 style='border-left:none;width:136pt'>正相浓度（ppm）</td>"+
	  "<td colspan=2 class=xl66 width=166 style='border-top:none;border-left:none;width:72pt;text-align:center'>反向加药量（kg）</td>"+
	  "<td colspan=2 class=xl66 width=180 style='border-top:none;border-left:none;width:72pt;text-align:center'>反向浓度（ppm）</td>"+
	  //"<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>除油罐出口反相药量（kg）</td>"+
	  //"<td class=xl66 width=77 style='border-top:none;border-left:none;width:58pt'>除油罐出口反相药浓度（ppm）</td>"+
	  "<td class=xl66 width=90 style='border-top:none;border-left:none;width:68pt'>预处理剂（kg）</td>"+
	  "<td class=xl66 width=90 style='border-top:none;border-left:none;width:68pt'>预处理剂浓度（ppm）</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl85 width=72 style='height:18.0pt;border-top:none;width:54pt'>昨日</td>"+
	  "<td class=xl80 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].cyel+"</td>"+
	  "<td class=xl71 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].cyoul+"</td>"+
	  "<td class=xl71 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].wsyl+"</td>"+
	  "<td class=xl80 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].kcyl+"</td>"+
	  "<td  class=xl71 colspan=2 width=101 style='border-top:none;border-left:none;width:76pt'>"+firstArr[0].ccl+"</td>"+
	 // "<td class=xl71 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].xll+"</td>"+
	  "<td colspan=2 class=xl78 width=147 style='border-left:none;width:110pt'>"+firstArr[0].zxjyl+"</td>"+
	  "<td colspan=2 class=xl72 width=180 style='border-left:none;width:136pt'>"+firstArr[0].zxnd+"</td>"+
	  "<td colspan=2 class=xl71 width=96 style='border-top:none;border-left:none;width:72pt'>"+firstArr[0].cjgckfxyl+"</td>"+
	  "<td colspan=2 class=xl80 width=96 style='border-top:none;border-left:none;width:72pt'>"+firstArr[0].cjgckfxynd+"</td>"+
	 // "<td class=xl71 width=86 style='border-top:none;border-left:none;width:65pt'>"+firstArr[0].cygckfxyl+"</td>"+
	  //"<td class=xl80 width=77 style='border-top:none;border-left:none;width:58pt'>"+firstArr[0].z1xhyryyclj+"</td>"+
	  "<td class=xl71 width=90 style='border-top:none;border-left:none;width:68pt'>"+firstArr[0].z1xhyryyclj+"</td>"+
	  "<td class=xl80 width=90 style='border-top:none;border-left:none;width:68pt'>"+firstArr[0].z1xhyryycljnd+"</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>今日</td>"+
	  "<td class=xl102 width=72 style='border-top:none;border-left:none;width:54pt'><input id='cyel' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].cyel+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='cyoul' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].cyoul+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='wsyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].wsyl+"'/></td>"+
	  "<td class=xl102 width=72 style='border-top:none;border-left:none;width:54pt'><input id='kcyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].kcyl+"'/></td>"+
	  "<td class=xl75 colspan=2 width=101 style='border-top:none;border-left:none;width:76pt'><input id='ccl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].ccl+"'/></td>"+
	 // "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='xll' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].xll+"'/></td>"+
	  "<td colspan=2 class=xl101 width=147 style='border-left:none;width:110pt'><input id='zxjyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].zxjyl+"'/></td>"+
	  "<td colspan=2 class=xl137 width=180 style='border-left:none;width:136pt'><input id='zxnd' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].zxnd+"'/></td>"+
	  "<td colspan=2 class=xl75 width=96 style='border-top:none;border-left:none;width:72pt'><input id='cjgckfxyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].cjgckfxyl+"'/></td>"+
	  "<td colspan=2 class=xl80 width=96 style='border-top:none;border-left:none;width:72pt'><input id='cjgckfxynd' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].cjgckfxynd+"'/></td>"+
	 // "<td class=xl75 width=86 style='border-top:none;border-left:none;width:65pt'><input id='cygckfxyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].cygckfxyl+"'/></td>"+
	  //"<td class=xl80 width=77 style='border-top:none;border-left:none;width:58pt'><input id='cygckfxynd' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].cygckfxynd+"'/></td>"+
	  "<td class=xl75 width=90 style='border-top:none;border-left:none;width:68pt'><input id='z1xhyryyclj' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].z1xhyryyclj+"'/></td>"+
	  "<td class=xl80 width=90 style='border-top:none;border-left:none;width:68pt'><input id='z1xhyryycljnd' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].z1xhyryycljnd+"'/></td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>增减</td>"+
	"<td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].cyel < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].cyel+"'/>　</td>"+

		"<td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'><input  type='text' readonly='true'  style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].cyoul < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].cyoul+"'/>　</td>"+
	  
		"<td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].wsyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].wsyl+"'/>　</td>"+
	  
		"<td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].kcyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].kcyl+"'/>　</td>"+
	  
	  "<td class=xl76 colspan=2 width=101 style='border-top:none;border-left:none;width:76pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].ccl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].ccl+"'/>　</td>"+
	  
	  /*"<td class=xl76 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].xll < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].xll+"'/>　</td>"+
*/
	  "<td colspan=2 class=xl120 width=147 style='border-right:.5pt solid black;border-left:none;width:110pt'><input readonly='true' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].zxjyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].zxjyl+"'/>　</td>"+

	  "<td colspan=2 class=xl120 width=180 style='border-right:.5pt solid black;border-left:none;width:136pt'><input readonly='true' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].zxnd < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].zxnd+"'/>　</td>"+
		
	  "<td colspan=2 class=xl120 width=96 style='border-top:none;border-left:none;width:72pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].cjgckfxyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].cjgckfxyl+"'/>　</td>"+
		
	  "<td colspan=2 class=xl120 width=96 style='border-top:none;width:72pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].cjgckfxynd < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].cjgckfxynd+"'/>　</td>"+
		
	  /*"<td class=xl120 width=86 style='border-top:none;width:65pt'><input  type='text' readonly='true'  style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].cygckfxyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].cygckfxyl+"'/>　</td>"+
	  
	  "<td class=xl120 width=77 style='border-top:none;width:58pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].z1xhyryyclj < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].z1xhyryyclj+"'/>　</td>"+
	  
*/
	  "<td class=xl120 width=90 style='border-top:none;width:68pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].z1xhyryyclj < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].z1xhyryyclj+"'/>　</td>"+
	  
		  
	  "<td class=xl120 width=90 style='border-top:none;width:68pt'><input  type='text'readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].z1xhyryycljnd < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].z1xhyryycljnd+"'/>　</td>"+
	  
	  "<td></td>"+
	  
	 "</tr>"+
	 "<tr height=38 style='height:28.5pt'>"+
	  "<td height=38 class=xl86 width=72 style='height:28.5pt;border-top:none;"+
	 " width:54pt'>分线计量</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>重32南线</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>重32北线</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>重18-1线</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>13-11线</td>"+
	  "<td class=xl66 width=101 style='border-top:none;border-left:none;width:76pt'>重1SAGD循环预热液</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>卸油台</td>"+
	  "<td colspan=11 class=xl139 width=948 style='border-right:.5pt solid black; border-left:none;width:714pt'>交油情况</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='height:18.0pt'>"+
	  "<td height=24 class=xl85 width=72 style='height:30.75pt;border-top:none;width:54pt'>昨日</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].z32nx+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].z32bx+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].z181x+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].z1311x+"</td>"+
	  "<td class=xl78 width=101 style='border-top:none;border-left:none;width:76pt'>"+firstArr[0].z1xhyry+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].xyt+"</td>"+
	  "<td colspan=2 class=xl66 width=147 style='border-left:none;width:110pt'>罐号</td>"+
	  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>高液位（m）</td>"+
	  "<td class=xl66 width=94 style='border-top:none;border-left:none;width:71pt'>低液位（m）</td>"+
	  "<td class=xl66 width=96 style='border-top:none;border-left:none;width:72pt'>视密(mg/cm<font"+
	  "class='font5'><sup>3</sup></font><font class='font6'>)</font></td><td class=xl66 width=96 style='border-top:none;border-left:none;width:72pt'>标密(mg/cm<font"+
	  "class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>罐温℃</td>"+
	  "<td class=xl66 width=77 style='border-top:none;border-left:none;width:58pt'>含水%</td>"+
	  "<td class=xl66 width=90 style='border-top:none;border-left:none;width:68pt'>毛油量（t）</td>"+
	  "<td class=xl66 width=90 style='border-top:none;border-left:none;width:68pt'>纯油量（t）</td>"+
	  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>水量（t）</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>今日</td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='z32nx' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].z32nx+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='z32bx' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].z32bx+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='z181x' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].z181x+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='z1311x' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].z1311x+"'/></td>"+
	  "<td class=xl75 width=101 style='border-top:none;border-left:none;width:76pt'><input id='z1xhyry' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].z1xhyry+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='xyt' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].xyt+"'/></td>"+
	  "<td colspan=2 class=xl75 width=147 style='border-left:none;width:110pt'><input id='gh' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].gh+"'/></td>"+
	  "<td class=xl124 style='border-top:none;border-left:none'><input id='gyw' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].gyw+"'/></td>"+
	  "<td class=xl124 style='border-top:none;border-left:none'><input id='dyw' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].dyw+"'/></td>"+
	  "<td class=xl106 width=96 style='border-top:none;border-left:none;width:72pt'><input id='sm' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].sm+"'/></td>"+
	  "<td class=xl106 width=96 style='border-top:none;border-left:none;width:72pt'><input id='bm' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].bm+"'/></td>"+
	  "<td class=xl121 style='border-top:none;border-left:none'><input id='gw' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].gw+"'/></td>"+
	  "<td class=xl122 width=77 style='border-top:none;border-left:none;width:58pt'><input id='hs' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].hs+"'/></td>"+
	  "<td class=xl124 style='border-top:none;border-left:none'><input id='myl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].myl+"'/></td>"+
	  "<td class=xl124 style='border-top:none;border-left:none'><input id='cyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].cyl+"'/></td>"+
	  "<td class=xl123 width=86 style='border-top:none;border-left:none;width:65pt'><input id='sl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].sl+"'/></td>"+
	  "<td class=xl123 width=86 style='border:none;width:65pt'><input id='RPD_CRUDE_TRANSITION_ID'  type='hidden' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fourArr[0].RPD_CRUDE_TRANSITION_ID+"'/></td>"+
	 " <td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 " <td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>增减</td>"+
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].z32nx < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].z32nx+"'/>　</td>"+
	  

	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
	  if(thirdArr[0].z32bx < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].z32bx+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
	  if(thirdArr[0].z181x < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].z181x+"'/>　</td>"+
	  
	 " <td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
	 if(thirdArr[0].z1311x < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].z1311x+"'/>　</td>"+
	  
	 " <td class=xl92 width=101 style='border-top:none;border-left:none;width:76pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
	 if(thirdArr[0].z1xhyry < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].z1xhyry+"'/>　</td>"+
	  
	 " <td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
	 if(thirdArr[0].xyt < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].xyt+"'>　</td>";
	  
		tableval += "<td colspan=2 class=xl95 width=147 style='border-left:none;width:110pt'><input id='gh0'type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].gh0+"'/></td>"+
		  "<td class=xl124 style='border-top:none;border-left:none'><input id='gyw0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].gyw0+"'/></td>"+
		  "<td class=xl124 style='border-top:none;border-left:none'><input id='dyw0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].dyw0+"'/></td>"+
		  "<td class=xl106 width=96 style='border-top:none;border-left:none;width:72pt'><input id='sm0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].sm0+"'/></td>"+
		  "<td class=xl106 width=96 style='border-top:none;border-left:none;width:72pt'><input id='bm0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].bm0+"'/></td>"+
		  "<td class=xl121 style='border-top:none;border-left:none'><input id='gw0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].gw0+"'/></td>"+
		  "<td class=xl122 width=77 style='border-top:none;border-left:none;width:58pt'><input id='hs0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].hs0+"'/></td>"+
		  "<td class=xl124 style='border-top:none;border-left:none'><input id='myl0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].myl0+"'/></td>"+
		  "<td class=xl124 style='border-top:none;border-left:none'><input id='cyl0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].cyl0+"'/></td>"+
		  "<td class=xl123 width=86 style='border-top:none;border-left:none;width:65pt'><input id='sl0' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].sl0+"'/></td>"+
		  "<td class=xl123 width=86 style='border:none;width:65pt'><input id='RPD_CRUDE_TRANSITION_ID0'  type='hidden' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[0].RPD_CRUDE_TRANSITION_ID0+"'/></td>"+
	 " <td></td>"+
	 "</tr>"+

	 "<tr height=38 style='height:28.5pt'>"+
	  "<td height=38 class=xl86 width=72 style='height:28.5pt;border-top:none;width:54pt'>油区指标</td>"+
	  "<td class=xl66 width=72 style='border-left:none;width:54pt'>11#沉降罐含水（%）</td>"+
	  "<td class=xl66 width=72 style='border-left:none;width:54pt'>12#沉降罐含水（%）</td>"+
	  "<td class=xl66 width=72 style='border-left:none;width:54pt'>提升泵含水（%）</td>"+
	  "<td class=xl66 width=72 style='border-left:none;width:54pt'>12#高出水含油（mg/L）</td>"+
	  "<td colspan=13 rowspan=3 class=xl66 width=948 style='border-right:.5pt solid black; border-left:none;border-bottom:none;width:714pt'></td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 " <td height=24 class=xl88 width=72 style='height:18.0pt;border-top:none; width:54pt'>指标</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤10</td>"+
	 " <td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤10</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤3</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤1000</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<td class=xl75 width=96 style='border-top:none;width:72pt'>实测</td>"+
	  "<td class=xl106 width=96 style='border-top:none;border-left:none;width:72pt'><input id='cjg11hs' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].cjg11hs >=10){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].cjg11hs+"'/>　</td>"+
	  "<td class=xl106 width=96 style='border-top:none;border-left:none;width:72pt'><input id='cjg12hs' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].cjg12hs >=10){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].cjg12hs+"'/>　</td>"+
	  "<td class=xl106 width=96 style='border-top:none;border-left:none;width:72pt'><input id='tsbhs' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].tsbhs >=3){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].tsbhs+"'/>　</td>"+	
	  "<td class=xl106 width=96 style='border-top:none;border-left:none;width:72pt'><input id='gcs12hy' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].gcs12hy >=1000){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].gcs12hy+"'/>　</td>"+
	  "<td></td>"+
	 "</tr>"+
	 
	 "<tr height=31 style='mso-height-source:userset;height:23.25pt'>"+
	 " <td colspan=18 height=31 class=xl134 width=1481 style='border-right:1.0pt solid black;"+
	 " height:23.25pt;width:1114pt'>二<span style='mso-spacerun:yes'>&nbsp;</span>SAGD密闭处理站</td>"+
	  "<td></td>"+
	" </tr>"+
	 "<tr height=41 style='mso-height-source:userset;height:30.75pt'>"+
	 " <td height=41 class=xl93 width=72 style='height:30.75pt;width:54pt'></td>"+
	 " <td class=xl90 width=72 style='border-left:none;width:54pt'>电脱水混油量(m3)</td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>掺柴量<br>（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>产水量<br>（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	 " <td class=xl90 width=72 style='border-left:none;width:54pt'>SAGD液量<br>（m<font class='font5'><sup>3</sup></font><font class='font6'>）</td>"+
	  "<td class=xl90 width=101 style='border-left:none;width:76pt'>产油量（t）</td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>换热站混油量（m<font class='font5'><sup>3</sup></font><font class='font6'>）</td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>预处理剂（kg）</td>"+
	  "<td class=xl90 width=75 style='border-left:none;width:56pt'>正相破乳剂（kg）</td>"+
	  "<td class=xl90 width=75 style='border-left:none;width:56pt'>出水含油(mg/l)</td>"+
	  "<td class=xl90 width=86 style='border-left:none;width:65pt'>出油含水（%）</td>"+
	  "<td class=xl90 width=94 style='border-left:none;width:71pt'>污水含油量（t）</td>"+
	  "<td rowspan=4 colspan=2 class=xl159>　三相出水含油现由三达公司做样	</td>"+
	  "<td rowspan=4 colspan=4 class=xl159 style='color:red;'>　SAGD产量以电脱出口计量为准，换热站计量仅做参考，混油密度按0.90计算，仰角和热化学出水流量计拆检，产水量无法计量，污水含油量暂不计入SAGD油量。</td>"+
	  "<td></td>"+
	" </tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl85 width=72 style='height:18.0pt;border-top:none;width:54pt'>昨日</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].syzdtshyl+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].syzccl+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].syzcsl+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].syzyl+"</td>"+
	  "<td class=xl72 width=101 style='border-top:none;border-left:none;width:76pt'>"+firstArr[0].syzcyl+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].syzhszhyl+"</td>"+
	  "<td class=xl85 width=72 style='border-top:none;width:54pt'>"+firstArr[0].syzyclj+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].syzzxprj+"</td>"+
	  "<td class=xl78 width=75 style='border-top:none;border-left:none;width:56pt'>"+firstArr[0].syzcshy+"</td>"+
	  "<td class=xl78 width=86 style='border-top:none;border-left:none;width:65pt'>"+firstArr[0].syzcyhs+"</td>"+
	  "<td class=xl118 width=94 style='border-top:none;border-left:none;width:71pt'>"+firstArr[0].syzwshyl+"</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl103 width=72 style='height:18.0pt;border-top:none;width:54pt'>今日</td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='syzdtshyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzdtshyl+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='syzccl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzccl+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='syzcsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzcsl+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='syzyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzyl+"'/></td>"+
	  "<td class=xl102 width=101 style='border-top:none;border-left:none;width:76pt'><input id='syzcyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzcyl+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='syzhszhyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzhszhyl+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='syzyclj' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzyclj+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='syzzxprj' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzzxprj+"'/></td>"+
	  "<td class=xl75 width=75 style='border-top:none;border-left:none;width:56pt'><input id='syzcshy' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzcshy+"'/></td>"+
	  "<td class=xl104 width=86 style='border-top:none;border-left:none;width:65pt'><input id='syzcyhs' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzcyhs+"'/></td>"+
	  "<td class=xl119 width=94 style='border-top:none;border-left:none;width:71pt'><input id='syzwshyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].syzwshyl+"'/></td>"+
	 /* "<td class=xl75 width=86 style='border-top:none;border-left:none;width:65pt'><input id='qfyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].qfyl+"'/></td>"+
	  "<td class=xl75 width=77 style='border-top:none;border-left:none;width:58pt'><input id='qfsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].qfsl+"'/></td>"+
	  "<td class=xl75 width=90 style='border-top:none;border-left:none;width:68pt'><input id='qflyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].qflyl+"'/></td>"+
	  "<td class=xl75 width=90 style='border-top:none;border-left:none;width:68pt'><input id='qfjsj' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].qfjsj+"'/></td>"+
	  "<td class=xl105 width=86 style='border-top:none;border-left:none;width:65pt'><input id='qfznj' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].qfznj+"'/></td>"+
*/
		  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>增减</td>"+
	 " <td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzdtshyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzdtshyl+"'/>　</td>"+
		
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzccl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzccl+"'/>　</td>"+
		
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzcsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzcsl+"'/>　</td>"+
		
	 " <td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzyl+"'/>　</td>"+
		
	 " <td class=xl92 width=101 style='border-top:none;border-left:none;width:76pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzcyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzcyl+"'/>　</td>"+

	 " <td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzhszhyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzhszhyl+"'/>　</td>"+
		
	 " <td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzyclj < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzyclj+"'/>　</td>"+
		
	 " <td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
			if(thirdArr[0].syzzxprj < 0){
				tableval += "color:red;";
			}
			tableval += "text-align:center;' value='"+thirdArr[0].syzzxprj+"'/>　</td>"+
			
	 " <td class=xl92 width=75 style='border-top:none;border-left:none;width:56pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzcshy < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzcshy+"'/>　</td>"+
		
	 " <td class=xl92 width=86 style='border-top:none;border-left:none;width:65pt'><input  type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzcyhs < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzcyhs+"'/>　</td>"+
		
	 " <td class=xl92 width=94 style='border-top:none;border-left:none;width:71pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].syzwshyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].syzwshyl+"'/>　</td>"+
		
	/* "<td class=xl92 width=86 style='border-top:none;border-left:none;width:65pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].qfyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].qfyl+"'/>　</td>"+
		
	 "<td class=xl92 width=77 style='border-top:none;border-left:none;width:58pt'><input  type='text' readonly='true'style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].qfsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].qfsl+"'/>　</td>"+
		
	 "<td class=xl92 width=90 style='border-top:none;border-left:none;width:68pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].qflyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].qflyl+"'/>　</td>"+
		
	  "<td class=xl92 width=90 style='border-top:none;border-left:none;width:68pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].qfjsj < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].qfjsj+"'/>　</td>"+
		
	  "<td class=xl92 width=86 style='border-top:none;border-left:none;width:65pt'><input  type='text'  readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].qfznj < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].qfznj+"'/>　</td>"+
		*/
	  "<td></td>"+
	 "</tr>"+
	 /*"<tr height=38 style='mso-height-source:userset;height:28.5pt'> <td colspan=18 height=38 class=xl142 width=1481 style='border-right:1.0pt solid black;"+
	  "height:28.5pt;width:1114pt'>三<span style='mso-spacerun:yes'>&nbsp; </span>水区</td><td></td>"+
	 "</tr>"+
	 "<tr height=62 style='mso-height-source:userset;height:46.5pt'>"+
	  "<td height=62 class=xl96 width=72 style='height:46.5pt;width:54pt'>水一区</td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>来水（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>反应器(m<font class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>过滤出水(m<font class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>回收量（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	  "<td class=xl90 width=101 style='border-left:none;width:76pt'>外排(m<font class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	  "<td rowspan=4 class=xl156 style='border-bottom:1.0pt solid black;border-top: none'>　</td>"+
	  "<td class=xl97 width=72 style='border-left:none;width:54pt'>水二区</td>"+
	  "<td class=xl90 width=75 style='border-left:none;width:56pt'>来水量（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	  "<td class=xl90 width=86 style='border-left:none;width:65pt'>气浮机处理量(m<font class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	  "<td class=xl90 width=94 style='border-left:none;width:71pt'>过滤出水<br> (m<font class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	  "<td class=xl90 width=96 style='border-left:none;width:72pt'>污水回收量<br>（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	  "<td class=xl90 width=96 style='border-left:none;width:72pt'>净水剂加药量（kg）</td>"+
	  "<td class=xl90 width=86 style='border-left:none;width:65pt'>净水剂浓度（ppm）</td>"+
	  "<td class=xl90 width=77 style='border-left:none;width:58pt'>絮凝剂加药量（kg）</td>"+
	  "<td class=xl90 width=90 style='border-left:none;width:68pt'>絮凝剂浓度（ppm）</td>"+
	  "<td colspan=2 rowspan=4 class=xl70 style='border-right:1.0pt solid black'></td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl85 width=72 style='height:18.0pt;border-top:none; width:54pt'>昨日</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s1lsl+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s1fyq+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s1glcs+"</td>"+
	  "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s1hsl+"</td>"+
	  "<td class=xl78 width=101 style='border-top:none;border-left:none;width:76pt'>"+firstArr[0].s1wp+"</td>"+
	  "<td class=xl71 width=72 style='border-top:none;border-left:none;width:54pt'>昨日</td>"+
	  "<td class=xl71 width=75 style='border-top:none;border-left:none;width:56pt'>"+firstArr[0].s2lsl+"</td>"+
	 " <td class=xl71 width=86 style='border-top:none;border-left:none;width:65pt'>"+firstArr[0].s2qfjcll+"</td>"+
	  "<td class=xl71 width=94 style='border-top:none;border-left:none;width:71pt'>"+firstArr[0].s2glcs+"</td>"+
	  "<td class=xl71 width=96 style='border-top:none;border-left:none;width:72pt'>"+firstArr[0].s2wshsl+"</td>"+
	  "<td class=xl71 width=96 style='border-top:none;border-left:none;width:72pt'>"+firstArr[0].s2jsjjyl+"</td>"+
	  "<td class=xl79 width=86 style='border-top:none;border-left:none;width:65pt'>"+firstArr[0].s2jsjnd+"</td>"+
	  "<td class=xl80 width=77 style='border-top:none;border-left:none;width:58pt'>"+firstArr[0].s2xnjjyl+"</td>"+
	  "<td class=xl80 width=90 style='border-top:none;border-left:none;width:68pt'>"+firstArr[0].s2xnjnd+"</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>今日</td>"+
	 " <td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1lsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1lsl+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1fyq' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1fyq+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1glcs' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1glcs+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1hsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1hsl+"'/></td>"+
	  "<td class=xl101 width=101 style='border-top:none;border-left:none;width:76pt'><input id='s1wp' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1wp+"'/></td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'>今日</td>"+
	 " <td class=xl75 width=75 style='border-top:none;border-left:none;width:56pt'><input id='s2lsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2lsl+"'/></td>"+
	  "<td class=xl75 width=86 style='border-top:none;border-left:none;width:65pt'><input id='s2qfjcll' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2qfjcll+"'/></td>"+
	  "<td class=xl75 width=94 style='border-top:none;border-left:none;width:71pt'><input id='s2glcs' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2glcs+"'/></td>"+
	  "<td class=xl75 width=96 style='border-top:none;border-left:none;width:72pt'><input id='s2wshsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2wshsl+"'/></td>"+
	 " <td class=xl102 width=96 style='border-top:none;border-left:none;width:72pt'><input id='s2jsjjyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2jsjjyl+"'/></td>"+
	  "<td class=xl102 width=86 style='border-top:none;border-left:none;width:65pt'><input id='s2jsjnd' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2jsjnd+"'/></td>"+
	  "<td class=xl102 width=77 style='border-top:none;border-left:none;width:58pt'><input id='s2xnjjyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2xnjjyl+"'/></td>"+
	  "<td class=xl102 width=90 style='border-top:none;border-left:none;width:68pt'><input id='s2xnjnd' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2xnjnd+"'/></td>"+
	  "<td></td>"+
	 "</tr>"+
	" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 " <td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none; width:54pt'>增减</td>"+
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text'readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1lsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1lsl+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1fyq < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1fyq+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text'readonly='true'  style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1glcs < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1glcs+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1hsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1hsl+"'/>　</td>"+
	  

	  "<td class=xl92 width=101 style='border-top:none;border-left:none;width:76pt'><input  type='text' readonly='true'  style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1wp < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1wp+"'/>　</td>"+
	  
	  "<td class=xl100 width=72 style='border-top:none;border-left:none;width:54pt'>增减</td>"+
	  "<td class=xl92 width=75 style='border-top:none;border-left:none;width:56pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2lsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2lsl+"'/>　</td>"+
	  
	  "<td class=xl92 width=86 style='border-top:none;border-left:none;width:65pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2qfjcll < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2qfjcll+"'/>　</td>"+
	  
	  "<td class=xl92 width=94 style='border-top:none;border-left:none;width:71pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2glcs < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2glcs+"'/>　</td>"+
	  
	  "<td class=xl92 width=96 style='border-top:none;border-left:none;width:72pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2wshsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2wshsl+"'/>　</td>"+
	  
	  "<td class=xl92 width=96 style='border-top:none;border-left:none;width:72pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2jsjjyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2jsjjyl+"'/>　</td>"+
	  
	  "<td class=xl92 width=86 style='border-top:none;border-left:none;width:65pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2jsjnd < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2jsjnd+"'/>　</td>"+
	  
	  "<td class=xl92 width=77 style='border-top:none;border-left:none;width:58pt'><input  type='text'readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2xnjjyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2xnjjyl+"'/>　</td>"+
	  
	  "<td class=xl92 width=90 style='border-top:none;border-left:none;width:68pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2xnjnd < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2xnjnd+"'/>　</td>"+
	  
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=36 style='mso-height-source:userset;height:27.0pt'>"+*/
	 " <td colspan=18 height=36 class=xl142 width=1481 style='border-right:1.0pt solid black;height:27.0pt;width:1114pt'>三 污水处理</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 "<td height=62 class=xl96 width=72 style='height:46.5pt;width:54pt'>水一区</td>"+
	 "<td class=xl90 width=72 style='border-left:none;width:54pt'>来水（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	 "<td class=xl90 width=72 style='border-left:none;width:54pt'>反应器(m<font class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	 "<td class=xl90 width=72 style='border-left:none;width:54pt'>过滤出水(m<font class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	 "<td class=xl90 width=72 style='border-left:none;width:54pt'>回收量（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	 "<td class=xl90 width=101 style='border-left:none;width:76pt'>外排(m<font class='font5'><sup>3</sup></font><font class='font6'>)</font></td>"+
	 " <td rowspan=2 height=81 class=xl154 width=72 style='height:60.75pt;width:54pt'>水一区水质</td>"+
	 " <td colspan=2 class=xl90 width=144 style='border-left:none;width:108pt'>调储罐进口</td>"+
	  "<td colspan=2 class=xl90 width=144 style='border-left:none;width:108pt'>调储罐出口</td>"+
	 " <td colspan=2 class=xl90 width=173 style='border-left:none;width:130pt'>反应罐出口</td>"+
	 " <td colspan=2 class=xl90 width=147 style='border-left:none;width:110pt'>一级出口</td>"+
	 " <td colspan=2 class=xl90 width=180 style='border-left:none;width:136pt'>二级出口</td>"+
	 " <td rowspan=4 class=xl90 width=445 style='border-left:none;width:335pt'>　</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=57 style='height:42.75pt'>"+
	 "<td height=24 class=xl85 width=72 style='height:18.0pt;border-top:none; width:54pt'>昨日</td>"+
	 "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s1lsl+"</td>"+
	 "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s1fyq+"</td>"+
	 "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s1glcs+"</td>"+
	 "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s1hsl+"</td>"+
	 "<td class=xl78 width=101 style='border-top:none;border-left:none;width:76pt'>"+firstArr[0].s1wp+"</td>"+
	 " <td height=57 class=xl66 width=72 style='height:42.75pt;border-top:none; border-left:none;width:54pt'>含油(mg/l)</td>"+
	 " <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>悬浮物(mg/l)</td>"+
	 " <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>含油(mg/l)</td>"+
	 " <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>悬浮物(mg/l)</td>"+
	  "<td class=xl66 width=101 style='border-top:none;border-left:none;width:76pt'>含油(mg/l)</td>"+
	 " <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>悬浮物(mg/l)</td>"+
	 " <td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>含油(mg/l)</td>"+
	 " <td class=xl66 width=75 style='border-top:none;border-left:none;width:56pt'>悬浮物(mg/l)</td>"+
	 " <td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>含油(mg/l)</td>"+
	 " <td class=xl66 width=94 style='border-top:none;border-left:none;width:71pt'>悬浮物(mg/l)</td>"+
	 /* "<td class=xl69 width=96 style='border-top:none;border-left:none;width:72pt'>油区指标</td>"+
	 " <td class=xl66 width=96 style='border-top:none;border-left:none;width:72pt'>11#沉降罐含水（%）</td>"+
	 " <td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>12#沉降罐含水（%）</td>"+
	 " <td class=xl66 width=77 style='border-top:none;border-left:none;width:58pt'>提升泵含水（%）</td>"+
	 " <td class=xl66 width=90 style='border-top:none;border-left:none;width:68pt'>11#高出水含油（mg/L）</td>"+
	 " <td class=xl66 width=90 style='border-top:none;border-left:none;width:68pt'>SAGD出水含油<br>(mg/l)</td>"+
	 " <td class=xl84 width=86 style='border-top:none;border-left:none;width:65pt'>SAGD出油含水（%）</td>"+*/
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>今日</td>"+
	 " <td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1lsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1lsl+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1fyq' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1fyq+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1glcs' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1glcs+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1hsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1hsl+"'/></td>"+
	  "<td class=xl101 width=101 style='border-top:none;border-left:none;width:76pt'><input id='s1wp' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s1wp+"'/></td>"+
	 " <td height=24 class=xl88 width=72 style='height:18.0pt;border-top:none; width:54pt'>指标</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤5000</td>"+
	 " <td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤500</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤150</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤150</td>"+
	  "<td class=xl68 width=101 style='border-top:none;border-left:none;width:76pt'>≤15</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤15</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤5</td>"+
	  "<td class=xl68 width=75 style='border-top:none;border-left:none;width:56pt'>≤5</td>"+
	  "<td class=xl68 width=86 style='border-top:none;border-left:none;width:65pt'>≤2</td>"+
	  "<td class=xl68 width=94 style='border-top:none;border-left:none;width:71pt'>≤2</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 " <td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none; width:54pt'>增减</td>"+
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text'readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1lsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1lsl+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1fyq < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1fyq+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text'readonly='true'  style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1glcs < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1glcs+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1hsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1hsl+"'/>　</td>"+
	  

	  "<td class=xl92 width=101 style='border-top:none;border-left:none;width:76pt'><input  type='text' readonly='true'  style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s1wp < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s1wp+"'/>　</td>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>实测</td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1hytcgjk' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1hytcgjk >=5000){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1hytcgjk+"'/>　</td>"+
	  


	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1xfwtcgjk' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1xfwtcgjk >=500){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1xfwtcgjk+"'/>　</td>"+
	  
	 /* "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1hytcgck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1hytcgck >=150){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1hytcgck+"'/>　</td>"+
	  
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1xfwtcgck'  onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1xfwtcgck >=150){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1xfwtcgck+"'/>　</td>"+
	  */
	  "<td class=xl68 width=96 style='border-top:none;border-left:none;width:72pt'>--</td>"+
	  "<td class=xl68 width=86 style='border-top:none;border-left:none;width:65pt'>--</td>"+
	  "<td class=xl104 width=101 style='border-top:none;border-left:none;width:76pt'><input id='s1hyfygck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1hyfygck >=15){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1hyfygck+"'/>　</td>"+
	  
	  "<td class=xl104 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1xfwfygck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1xfwfygck >=15){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1xfwfygck+"'/>　</td>"+
	  
	 /* "<td class=xl104 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s1hyyjck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1hyyjck >=5){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1hyyjck+"'/>　</td>"+
	  
	  "<td class=xl104 width=75 style='border-top:none;border-left:none;width:56pt'><input id='s1xfwyjck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1xfwyjck >=5){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1xfwyjck+"'/>　</td>"+
	  */
	  "<td class=xl68 width=96 style='border-top:none;border-left:none;width:72pt'>--</td>"+
	  "<td class=xl68 width=86 style='border-top:none;border-left:none;width:65pt'>--</td>"+
	  "<td class=xl104 width=86 style='border-top:none;border-left:none;width:65pt'><input id='s1hyejck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1hyejck >=2){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1hyejck+"'/>　</td>"+
	  
	  "<td class=xl104 width=94 style='border-top:none;border-left:none;width:71pt'><input id='s1xfwejck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s1xfwejck >=2){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s1xfwejck+"'/>　</td>"+
	  "<td></td>"+
	 "</tr>"+
	" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	" <td height=62 class=xl155 width=72 style='height:46.5pt;border-top:none;width:54pt'>水二区</td>"+
	 "<td class=xl66 width=72 style='border-left:none;width:54pt'>来水量(m<font class='font5'><sup>3</sup></font><font class='font6'>)</td>"+
	"<td class=xl66 width=72 style='border-left:none;width:54pt'>气浮机处理量(m<font class='font5'><sup>3</sup></font><font class='font6'>)</td>"+
	  "<td class=xl66 width=72 style='border-left:none;width:54pt'>过滤出水(m<font class='font5'><sup>3</sup></font><font class='font6'>)</td>"+	 
	  "<td class=xl66 width=101 style='border-left:none;width:76pt'>污水回收量(m<font class='font5'><sup>3</sup></font><font class='font6'>)</td>"+
	  "<td class=xl66 width=72 style='border-left:none;width:54pt'></td>"+
	 " <td rowspan=2 height=62 class=xl155 width=72 style='height:46.5pt;border-top:none;width:54pt'>水二区水质</td>"+
	  "<td colspan=2 class=xl66 width=144 style='border-left:none;width:108pt'>调储罐进口</td>"+
	  "<td colspan=2 class=xl66 width=144 style='border-left:none;width:108pt'>调储罐出口</td>"+
	  "<td colspan=2 class=xl66 width=173 style='border-left:none;width:130pt'>气浮机出口</td>"+
	  "<td colspan=2 class=xl66 width=147 style='border-left:none;width:110pt'>一级出口</td>"+
	  "<td colspan=2 class=xl66 width=180 style='border-left:none;width:136pt'>二级出口</td>"+
	  "<td rowspan=4 class=xl66>　</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=38 style='height:28.5pt'>"+
	 "<td height=24 class=xl85 width=72 style='height:18.0pt;border-top:none; width:54pt'>昨日</td>"+
	 "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s2lsl+"</td>"+
	 "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s2qfjcll+"</td>"+
	 "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s2glcs+"</td>"+
	 "<td class=xl78 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].s2wshsl+"</td>"+
	 "<td class=xl78 width=101 style='border-top:none;border-left:none;width:76pt'></td>"+
	  "<td height=38 class=xl66 width=72 style='height:28.5pt;border-top:none;border-left:none;width:54pt'>含油(mg/l)</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>悬浮物(mg/l)</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>含油(mg/l)</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>悬浮物(mg/l)</td>"+
	  "<td class=xl66 width=101 style='border-top:none;border-left:none;width:76pt'>含油(mg/l)</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>悬浮物(mg/l)</td>"+
	  "<td class=xl66 width=72 style='border-top:none;border-left:none;width:54pt'>含油(mg/l)</td>"+
	  "<td class=xl66 width=75 style='border-top:none;border-left:none;width:56pt'>悬浮物(mg/l)</td>"+
	  "<td class=xl66 width=86 style='border-top:none;border-left:none;width:65pt'>含油(mg/l)</td>"+
	  "<td class=xl66 width=94 style='border-top:none;border-left:none;width:71pt'>悬浮物(mg/l)</td>"+
	 " <td></td>"+
	" </tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>今日</td>"+
	 " <td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2lsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2lsl+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2qfjcll' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2qfjcll+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2glcs' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2glcs+"'/></td>"+
	  "<td class=xl101 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2wshsl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].s2wshsl+"'/></td>"+
	  "<td class=xl101 width=101 style='border-top:none;border-left:none;width:76pt'></td>"+
	  "<td height=24 class=xl88 width=72 style='height:18.0pt;border-top:none; width:54pt'>指标</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤5000</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤500</td>"+
	 " <td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤150</td>"+
	 " <td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤150</td>"+
	  "<td class=xl68 width=101 style='border-top:none;border-left:none;width:76pt'>≤15</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤15</td>"+
	  "<td class=xl68 width=72 style='border-top:none;border-left:none;width:54pt'>≤5</td>"+
	  "<td class=xl68 width=75 style='border-top:none;border-left:none;width:56pt'>≤5</td>"+
	  "<td class=xl68 width=86 style='border-top:none;border-left:none;width:65pt'>≤2</td>"+
	  "<td class=xl68 width=94 style='border-top:none;border-left:none;width:71pt'>≤2</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	 " <td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none; width:54pt'>增减</td>"+
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text'readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2lsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2lsl+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2qfjcll < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2qfjcll+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text'readonly='true'  style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2glcs < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2glcs+"'/>　</td>"+
	  
	  "<td class=xl92 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].s2wshsl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].s2wshsl+"'/>　</td>"+

	  "<td class=xl92 width=101 style='border-top:none;border-left:none;width:76pt'></td>"+
	  
	  "<td height=24 class=xl109 width=72 style='height:18.0pt;border-top:none;width:54pt'>实测</td>"+
	  "<td class=xl95 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2hytcgjk' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2hytcgjk >=5000){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2hytcgjk+"'/>　</td>"+

	  "<td class=xl95 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2xfwtcgjk' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2xfwtcgjk >=500){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2xfwtcgjk+"'/>　</td>"+

	  /*"<td class=xl95 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2hytcgck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2hytcgck >=150){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2hytcgck+"'/>　</td>"+

	  "<td class=xl95 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2xfwtcgck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2xfwtcgck >=150){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2xfwtcgck+"'/>　</td>"+
*/
 "<td class=xl68 width=77 style='border-top:none;border-left:none;width:58pt'>--</td>"+
"<td class=xl68 width=90 style='border-top:none;border-left:none;width:68pt'>--</td>"+
	  "<td class=xl95 width=101 style='border-top:none;border-left:none;width:76pt'><input id='s2hyqfjck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2hyqfjck >=15){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2hyqfjck+"'/>　</td>"+

	  "<td class=xl95 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2xfwqfjck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2xfwqfjck >=15){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2xfwqfjck+"'/>　</td>"+

	 /* "<td class=xl95 width=72 style='border-top:none;border-left:none;width:54pt'><input id='s2hyyjck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2hyyjck >=5){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2hyyjck+"'/>　</td>"+

	  "<td class=xl110 width=75 style='border-top:none;border-left:none;width:56pt'><input id='s2xfwyjck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2xfwyjck >=5){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2xfwyjck+"'/>　</td>"+
*/
 "<td class=xl68 width=77 style='border-top:none;border-left:none;width:58pt'>--</td>"+
"<td class=xl68 width=90 style='border-top:none;border-left:none;width:68pt'>--</td>"+
	  "<td class=xl110 width=86 style='border-top:none;border-left:none;width:65pt'><input id='s2hyejck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2hyejck >=2){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2hyejck+"'/>　</td>"+

	  "<td class=xl110 width=94 style='border-top:none;border-left:none;width:71pt'><input id='s2xfwejck' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].s2xfwejck >=2){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].s2xfwejck+"'/>　</td>"+

	  /*"<td class=xl95 width=96 style='border-top:none;border-left:none;width:72pt'><input id='jcjggcshy' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].jcjggcshy >=300){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].jcjggcshy+"'/>　</td>"+

	  "<td class=xl95 width=86 style='border-top:none;border-left:none;width:65pt'><input id='jcjggcsxfw' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";

		if(secondArr[0].jcjggcsxfw >=300){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].jcjggcsxfw+"'/>　</td>"+

	  "<td class=xl95 width=77 style='border-top:none;border-left:none;width:58pt'><input id='wfchshy' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].wfchshy >=500){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].wfchshy+"'/>　</td>"+

	  "<td class=xl95 width=90 style='border-top:none;border-left:none;width:68pt'><input id='wfchsxfw' onblur='changeRed(this)' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;";
		if(secondArr[0].wfchsxfw >=300){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+secondArr[0].wfchsxfw+"'/>　</td>"+
*/
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=31 style='mso-height-source:userset;height:23.25pt'>"+
	  "<td colspan=18 height=31 class=xl142 width=1481 style='border-right:1.0pt solid black;height:23.25pt;width:1114pt'>四 博达池子</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=38 style='height:28.5pt'>"+
	  "<td height=38 class=xl99 width=72 style='height:28.5pt;width:54pt'>　</td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>博达池子空高(m)</td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>1#池空高<br>（m）</td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>2#池空高<br>（m）</td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>万方池空高（m）</td>"+
	  "<td colspan=2 class=xl90 width=173 style='border-left:none;width:130pt'>博达池子回收水量（m<font class='font5'><sup>3</sup></font><font class='font6'>）</font></td>"+
	  "<td class=xl90 width=72 style='border-left:none;width:54pt'>一号特卸油泥量</td>"+
	  "<td class=xl90 width=75 style='border-left:none;width:56pt'>二号特卸油泥量</td>"+
	  "<td class=xl90 width=86 style='border-left:none;width:65pt'>采油站等废液量</td>"+
	  "<td class=xl90 width=94 style='border-left:none;width:71pt'>净水剂（kg）</td>"+
	  "<td class=xl90 width=96 style='border-left:none;width:72pt'>助凝剂（kg）</td>"+
	  "<td class=xl90 width=96 style='border-left:none;width:72pt'>博达池收油/车</td>"+
	  "<td colspan=5 rowspan=4 class=xl165 style='border-right:1.0pt solid black;border-bottom:.5pt solid black'>　</td>"+
	  "<td></td>"+
	" </tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>昨日</td>"+
	 "<td class=xl111 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].bdczyw+"</td>"+
	  "<td class=xl112 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].c1yw+"</td>"+
	  "<td class=xl112 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].c2yw+"</td>"+
	  "<td class=xl112 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].wfcyw+"</td>"+
	  "<td colspan=2 class=xl145 width=173 style='border-left:none;width:130pt'>"+firstArr[0].dbczhssl+"</td>"+
	  "<td class=xl75 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[0].t1xynl+"</td>"+
	  "<td class=xl75 width=75 style='border-top:none;border-left:none;width:56pt'>"+firstArr[0].t2xynl+"</td>"+
	  "<td class=xl75 width=86 style='border-top:none;border-left:none;width:65pt'>"+firstArr[0].cyzfyl+"</td>"+
	  "<td class=xl75 width=94 style='border-top:none;border-left:none;width:71pt'>"+firstArr[0].jsj+"</td>"+
	  "<td class=xl75 width=96 style='border-top:none;border-left:none;width:72pt'>"+firstArr[0].znj+"</td>"+
	  "<td class=xl75 width=96 style='border-top:none;border-left:none;width:72pt'>"+firstArr[0].bdcsy+"</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>今日</td>"+
	  "<td class=xl81 width=72 style='border-top:none;border-left:none;width:54pt'><input id='bdczyw' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].bdczyw+"'/></td>"+
	  "<td class=xl73 width=72 style='border-top:none;border-left:none;width:54pt'><input id='c1yw' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].c1yw+"'/></td>"+
	  "<td class=xl73 width=72 style='border-top:none;border-left:none;width:54pt'><input id='c2yw' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].c2yw+"'/></td>"+
	  "<td class=xl73 width=72 style='border-top:none;border-left:none;width:54pt'><input id='wfcyw' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].wfcyw+"'/></td>"+
	  "<td colspan=2 class=xl79 width=173 style='border-left:none;width:130pt'><input id='dbczhssl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].dbczhssl+"'/></td>"+
	  "<td class=xl71 width=72 style='border-top:none;border-left:none;width:54pt'><input id='t1xynl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].t1xynl+"'/></td>"+
	  "<td class=xl71 width=75 style='border-top:none;border-left:none;width:56pt'><input id='t2xynl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].t2xynl+"'/></td>"+
	  "<td class=xl71 width=86 style='border-top:none;border-left:none;width:65pt'><input id='cyzfyl' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].cyzfyl+"'/></td>"+
	  "<td class=xl71 width=94 style='border-top:none;border-left:none;width:71pt'><input id='jsj' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].jsj+"'/></td>"+
	  "<td class=xl71 width=96 style='border-top:none;border-left:none;width:72pt'><input id='znj' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].znj+"'/></td>"+
	  "<td class=xl71 width=96 style='border-top:none;border-left:none;width:72pt'><input id='bdcsy' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[0].bdcsy+"'/></td>"+
	  "<td class=xl80 width=90 style='border:none;width:68pt'><input id='id' type='hidden'  value='"+secondArr[0].RPD_U1_GENERAL_ID+"'/></td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
	  "<td height=24 class=xl100 width=72 style='height:18.0pt;border-top:none;width:54pt'>增减</td>"+
	  "<td class=xl82 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].bdczyw < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].bdczyw+"'/>　</td>"+

		"<td class=xl82 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].c1yw < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].c1yw+"'/>　</td>"+
	  
	  "<td class=xl82 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].c2yw < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].c2yw+"'/>　</td>"+
	  
	  "<td class=xl82 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].wfcyw < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].wfcyw+"'/>　</td>"+
	  
	  "<td colspan=2 class=xl120 width=173 style='border-right:.5pt solid black; border-left:none;width:130pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].dbczhssl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].dbczhssl+"'/>　</td>"+
	  
	  "<td class=xl76 width=72 style='border-top:none;border-left:none;width:54pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].t1xynl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].t1xynl+"'/>　</td>"+
	  
	  "<td class=xl76 width=75 style='border-top:none;border-left:none;width:56pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].t2xynl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].t2xynl+"'/>　</td>"+
	  
	  "<td class=xl76 width=86 style='border-top:none;border-left:none;width:65pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].cyzfyl < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].cyzfyl+"'/>　</td>"+
	  
	  "<td class=xl76 width=94 style='border-top:none;border-left:none;width:71pt'><input  type='text' readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].jsj < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].jsj+"'/>　</td>"+
	  
	  "<td class=xl76 width=96 style='border-top:none;border-left:none;width:72pt'><input  type='text'readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].znj < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].znj+"'/>　</td>"+

		"<td class=xl76 width=96 style='border-top:none;border-left:none;width:72pt'><input  type='text'readonly='true' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
		if(thirdArr[0].bdcsy < 0){
			tableval += "color:red;";
		}
		tableval += "text-align:center;' value='"+thirdArr[0].bdcsy+"'/>　</td>"+
	  
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=30 style='height:22.5pt'>"+
	  "<td colspan=18 height=30 class=xl146 width=1481 style='border-right:1.0pt solid black;height:22.5pt;width:1114pt'>"+
	  "<span style='mso-spacerun:yes'>&nbsp;</span>备注</td>"+
	  "<td></td>"+
	 "</tr>"+
	 "<tr height=93 style='mso-height-source:userset;height:69.75pt'>"+
	 " <td colspan=18 height=93 class=xl149 style='border-right:1.0pt solid black; height:69.75pt'><input id ='bz'  type='text' style='background:transparent;border:0px solid;width:600px;line-height:30px;height:30px;font-size: 12pt;'value='"+secondArr[0].bz+"'/>　</td>"+
	 " <td></td>"+
	 "</tr>"+
	"</table>";


													
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
         		var RPD_CRUDE_TRANSITION_ID = $("#RPD_CRUDE_TRANSITION_ID").val();
         		var RQ = $("#rq").text();
         		var bz = $("#bz").val();
             	var zbr = $("#zbr").val();
				var cyel = $("#cyel").val();	
				var cyoul = $("#cyoul").val();	
				var wsyl = $("#wsyl").val();	
				var kcyl = $("#kcyl").val();	
				var ccl = $("#ccl").val();	
				var xll	 = $("#xll").val();
				var zxjyl = $("#zxjyl").val();		
				var zxnd = $("#zxnd").val();	
				var cjgckfxyl = $("#cjgckfxyl").val();	
				var cjgckfxynd = $("#cjgckfxynd").val();
				var cygckfxyl = $("#cygckfxyl").val();	
				var cygckfxynd = $("#cygckfxynd").val();	
				var z1xhyryyclj	 = $("#z1xhyryyclj").val();
				var z1xhyryycljnd  = $("#z1xhyryycljnd").val();

				var z32nx = $("#z32nx").val();	
				var z32bx = $("#z32bx").val();		
				var z181x = $("#z181x").val();		
				var z1311x = $("#z1311x").val();		
				var z1xhyry	 = $("#z1xhyry").val();	
				var xyt = $("#xyt").val();	
				
				var syzdtshyl = $("#syzdtshyl").val();	
				var syzccl = $("#syzccl").val();		
				var syzcsl = $("#syzcsl").val();		
				var syzyl = $("#syzyl").val();		
				var syzcyl	 = $("#syzcyl").val();	
				var syzyclj	 = $("#syzyclj").val();	
				var syzzxprj = $("#syzzxprj").val();		
				var syzcshy	 = $("#syzcshy").val();	
				var syzcyhs	 = $("#syzcyhs").val();	
				var syzwshyl = $("#syzwshyl").val();	
				var qfyl = $("#qfyl").val();	
				var qfsl = $("#qfsl").val();		
				var qflyl  = $("#qflyl").val();		
				var qfjsj = $("#qfjsj").val();		
				var qfznj = $("#qfznj").val();	

				var s1lsl =$("#s1lsl").val();	
				var s1fyq =$("#s1fyq").val();	
				var s1glcs =$("#s1glcs").val();	
				var s1hsl =$("#s1hsl").val();	
				var s1wp =$("#s1wp").val();

				var s2lsl =$("#s2lsl").val();	
				var s2qfjcll =$("#s2qfjcll").val();	
				var s2glcs =$("#s2glcs").val();	
				var s2wshsl =$("#s2wshsl").val();	
				var s2jsjjyl =$("#s2jsjjyl").val();	
				var s2jsjnd	 =$("#s2jsjnd").val();
				var s2xnjjyl =$("#s2xnjjyl").val();	
				var s2xnjnd =$("#s2xnjnd").val();
				
				var s1hytcgjk =$("#s1hytcgjk").val();	
				var s1xfwtcgjk =$("#s1xfwtcgjk").val();	
				var s1hytcgck =$("#s1hytcgck").val();	
				var s1xfwtcgck =$("#s1xfwtcgck").val();	
				var s1hyfygck =$("#s1hyfygck").val();	
				var s1xfwfygck =$("#s1xfwfygck").val();	
				var s1hyyjck =$("#s1hyyjck").val();	
				var s1xfwyjck =$("#s1xfwyjck").val();	
				var s1hyejck =$("#s1hyejck").val();	
				var s1xfwejck =$("#s1xfwejck").val();
				
				var cjg11hs	 =$("#cjg11hs").val();
				var cjg12hs	 =$("#cjg12hs").val();
				var tsbhs =$("#tsbhs").val();	
				var gcs11hy	 =$("#gcs11hy").val();
				var syzcshy	 =$("#syzcshy").val();
				var syzcyhs =$("#syzcyhs").val();
				
				var s2hytcgjk =$("#s2hytcgjk").val();	
				var s2xfwtcgjk =$("#s2xfwtcgjk").val();	
				var s2hytcgck =$("#s2hytcgck").val();	
				var s2xfwtcgck =$("#s2xfwtcgck").val();	
				var s2hyqfjck =$("#s2hyqfjck").val();	
				var s2xfwqfjck =$("#s2xfwqfjck").val();	
				var s2hyyjck =$("#s2hyyjck").val();	
				var s2xfwyjck =$("#s2xfwyjck").val();	
				var s2hyejck =$("#s2hyejck").val();	
				var s2xfwejck =$("#s2xfwejck").val();
				
				var jcjggcshy =$("#jcjggcshy").val();	
				var jcjggcsxfw =$("#jcjggcsxfw").val();	
				var wfchshy	 =$("#wfchshy").val();
				var wfchsxfw =$("#wfchsxfw").val();
				
				var bdczyw	 =$("#bdczyw").val();
				var c1yw	 =$("#c1yw").val();
				var c2yw	 =$("#c2yw").val();
				var wfcyw	 =$("#wfcyw").val();
				var dbczhssl =$("#dbczhssl").val();
				var t1xynl	 =$("#t1xynl").val();
				var t2xynl	 =$("#t2xynl").val();
				var cyzfyl	 =$("#cyzfyl").val();
				var jsj	 =$("#jsj").val();
				var znj =$("#znj").val();

				//原油
				var gh	=$("#gh").val();	
				var gyw	=$("#gyw").val();
				var dyw	=$("#dyw").val();
				var sm	=$("#sm").val();
				var bm	=$("#bm").val();
				var gw	=$("#gw").val();
				var hs	=$("#hs").val();
				var myl	=$("#myl").val();
				var cyl	=$("#cyl").val();
				var sl	=$("#sl").val();
				var  id = $("#id").val();

				//原油2行
				var gh0	=$("#gh0").val();	
				var gyw0	=$("#gyw0").val();
				var dyw0	=$("#dyw0").val();
				var sm0	=$("#sm0").val();
				var bm0	=$("#bm0").val();
				var gw0	=$("#gw0").val();
				var hs0	=$("#hs0").val();
				var myl0	=$("#myl0").val();
				var cyl0	=$("#cyl0").val();
				var sl0	=$("#sl0").val();
				var  RPD_CRUDE_TRANSITION_ID0 = $("#RPD_CRUDE_TRANSITION_ID0").val();

				//新加
				var gcs12hy	=$("#gcs12hy").val();
				var syzhszhyl =$("#syzhszhyl").val();
				var bdcsy =$("#bdcsy").val();
				
				
            	if (modifyDataFlag($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'u1ZHRB_updateU1ZHRB.action?nowdata='+parseInt(Math.random()*100000),
 					data: {
  					"id" : id,
  					"RPD_CRUDE_TRANSITION_ID":RPD_CRUDE_TRANSITION_ID,
  					"RQ" : RQ,
  					"bz" :bz,
  							"zbr" :zbr,
		         			"cyel" : cyel,
		    				"cyoul" : cyoul,
		    				"wsyl" : wsyl,
		    				"kcyl" : kcyl,
		    				"ccl" : ccl,
		    				"xll" : xll,
		    				"zxjyl" : zxjyl,	
		    				"zxnd" : zxnd,
		    				"cjgckfxyl" : cjgckfxyl,
		    				"cjgckfxynd" : cjgckfxynd,
		    				"cygckfxyl" : cygckfxyl,
		    				"cygckfxynd" : cygckfxynd,
		    				"z1xhyryyclj" : z1xhyryyclj,
		    				"z1xhyryycljnd" : z1xhyryycljnd,
		
		    				"z32nx" : z32nx,
		    				"z32bx" : z32bx,	
		    				"z181x" : z181x,	
		    				"z1311x" : z1311x,	
		    				"z1xhyry" : z1xhyry,
		    				"xyt" : xyt,
		    				
		    				"syzdtshyl" : syzdtshyl,
		    				"syzccl" : syzccl,	
		    				"syzcsl" : syzcsl,	
		    				"syzyl" : syzyl,	
		    				"syzcyl" : syzcyl,
		    				"syzyclj" : syzyclj,
		    				"syzzxprj" : syzzxprj,	
		    				"syzcshy" : syzcshy,
		    				"syzcyhs" : syzcyhs,
		    				"syzwshyl" : syzwshyl,
		    				"qfyl" : qfyl,
		    				"qfsl" : qfsl,	
		    				"qflyl" : qflyl,	
		    				"qfjsj" : qfjsj,	
		    				"qfznj" : qfznj,
		
		    				"s1lsl" : s1lsl,
		    				"s1fyq" : s1fyq,
		    				"s1glcs" : s1glcs,
		    				"s1hsl" : s1hsl,
		    				"s1wp" : s1wp,
		
		    				"s2lsl" : s2lsl,
		    				"s2qfjcll" : s2qfjcll,
		    				"s2glcs" : s2glcs,
		    				"s2wshsl" : s2wshsl,
		    				"s2jsjjyl" : s2jsjjyl,
		    				"s2jsjnd" : s2jsjnd,
		    				"s2xnjjyl" : s2xnjjyl,
		    				"s2xnjnd" : s2xnjnd,
		    				
		    				"s1hytcgjk" : s1hytcgjk,
		    				"s1xfwtcgjk" : s1xfwtcgjk,
		    				"s1hytcgck" : s1hytcgck,
		    				"s1xfwtcgck" : s1xfwtcgck,
		    				"s1hyfygck" : s1hyfygck,
		    				"s1xfwfygck" : s1xfwfygck,
		    				"s1hyyjck" : s1hyyjck,
		    				"s1xfwyjck" : s1xfwyjck,
		    				"s1hyejck" : s1hyejck,
		    				"s1xfwejck" : s1xfwejck,
		    				
		    				"cjg11hs" : cjg11hs,
		    				"cjg12hs" : cjg12hs,
		    				"tsbhs" : tsbhs,
		    				"gcs11hy" : gcs11hy,
		    				"syzcshy" : syzcshy,
		    				"syzcyhs" : syzcyhs,
		    				
		    				"s2hytcgjk" : s2hytcgjk,
		    				"s2xfwtcgjk" : s2xfwtcgjk,
		    				"s2hytcgck" : s2hytcgck,
		    				"s2xfwtcgck" : s2xfwtcgck,
		    				"s2hyqfjck" : s2hyqfjck,
		    				"s2xfwqfjck" : s2xfwqfjck,
		    				"s2hyyjck" : s2hyyjck,
		    				"s2xfwyjck" : s2xfwyjck,
		    				"s2hyejck" : s2hyejck,
		    				"s2xfwejck" : s2xfwejck,
		    				
		    				"jcjggcshy" : jcjggcshy,
		    				"jcjggcsxfw" : jcjggcsxfw,
		    				"wfchshy" : wfchshy,
		    				"wfchsxfw" : wfchsxfw,
		    				
		    				"bdczyw" : bdczyw,
		    				"c1yw" : c1yw,
		    				"c2yw" : c2yw,
		    				"wfcyw" : wfcyw,
		    				"dbczhssl" : dbczhssl,
		    				"t1xynl" : t1xynl,
		    				"t2xynl" : t2xynl,
		    				"cyzfyl" : cyzfyl,
		    				"jsj" : jsj,
		    				"znj" : znj,
		
		    				"gh" : gh,
		    				"gyw" : gyw,
		    				"dyw" : dyw,
		    				"sm" : sm,
		    				"bm" : bm,
		    				"gw" : gw,
		    				"hs" : hs,
		    				"myl" : myl,
		    				"cyl" : cyl,
		    				"sl" : sl,
		    				"RPD_CRUDE_TRANSITION_ID0":RPD_CRUDE_TRANSITION_ID0,
		    				"gh0" : gh0,
		    				"gyw0" : gyw0,
		    				"dyw0" : dyw0,
		    				"sm0" : sm0,
		    				"bm0" : bm0,
		    				"gw0" : gw0,
		    				"hs0" : hs0,
		    				"myl0" : myl0,
		    				"cyl0" : cyl0,
		    				"sl0" : sl0,
		    				"gcs12hy" : gcs12hy,
		    				"syzhszhyl" : syzhszhyl,
		    				"bdcsy"	: bdcsy		
						},
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
         		var RQ = $("#rq").text();
             	var  id = $("#id").val();
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'u1ZHRB_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"id":id,"RQ":RQ},
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
        		var BBRQ = $("#rq").text();
        		//alert(BBRQ)
     		var url = "u1ZHRB_exportData.action?reportDate="+encodeURIComponent(BBRQ);
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
