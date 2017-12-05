<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>综合日报</title>
   
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
    
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
        <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<link href="../../lib/css/mbzhrb.css" rel="stylesheet" type="text/css" />  
    <script type="text/javascript">
    	var tableval;
    	var  SHR ="";
    	var RD_U1_SAGD_GENERAL_ID = "";
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
					url : 'mbzhrb_pageInit.action',
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

        function changValue(){
            var YJCSLJ0 = $('#YJCSLJ0').val(); 
            var YJCSLJ1 = $('#YJCSLJ1').val(); 
            var YJCSLJ2 = $('#YJCSLJ2').val();
            var YJCSLJ3 = $('#YJCSLJ3').val(); 
            var YJCSLJ4 = $('#YJCSLJ4').val(); 
            var YJCSLJ5 = $('#YJCSLJ5').val(); 
            var YJCSLJ6 = $('#YJCSLJ6').val(); 
            
            var RHXCSLJ0 = $('#RHXCSLJ0').val(); 
            var RHXCSLJ1 = $('#RHXCSLJ1').val(); 
            var RHXCSLJ2 = $('#RHXCSLJ2').val();
            var RHXCSLJ3 = $('#RHXCSLJ3').val(); 
            var RHXCSLJ4 = $('#RHXCSLJ4').val(); 
            var RHXCSLJ5 = $('#RHXCSLJ5').val();  
            var RHXCSLJ6 = $('#RHXCSLJ6').val();
            
            var RHX1JYLJ0 = $('#RHX1JYLJ0').val(); 
            var RHX1JYLJ6 = $('#RHX1JYLJ6').val(); 

            var RHX2JYLJ0 = $('#RHX2JYLJ0').val(); 
            var RHX2JYLJ6 = $('#RHX2JYLJ6').val(); 
            
            var RHX3JYLJ0 = $('#RHX3JYLJ0').val(); 
            var RHX3JYLJ6 = $('#RHX3JYLJ6').val(); 
          
            var DTCYLJ0 = $('#DTCYLJ0').val(); 
            var DTCYLJ6 = $('#DTCYLJ6').val(); 


            var XXLLJLJ0 = $('#XXLLJLJ0').val();
            var XXLLJLJ6 = $('#XXLLJLJ6').val(); 
            
            var CCLLJ0 = $('#CCLLJ0').val();
            var CCLLJ6 = $('#CCLLJ6').val(); 

			if(YJCSLJ0!="" && YJCSLJ6!="")
            	document.getElementById('YJCSLJ7').value = YJCSLJ6-YJCSLJ0;
            if(RHXCSLJ0!="" && RHXCSLJ6!="")
            	document.getElementById('RHXCSLJ7').value = RHXCSLJ6-RHXCSLJ0;    
        	if(RHX1JYLJ0!="" && RHX1JYLJ6!="")
                document.getElementById('RHX1JYLJ7').value = RHX1JYLJ6-RHX1JYLJ0;
            if(RHX2JYLJ0!="" && RHX2JYLJ6!="")
                document.getElementById('RHX2JYLJ7').value = RHX2JYLJ6-RHX2JYLJ0;
            if(RHX3JYLJ0!="" && RHX3JYLJ6!="")
                document.getElementById('RHX3JYLJ7').value = RHX3JYLJ6-RHX3JYLJ0;
           
            if(DTCYLJ0!="" && DTCYLJ6!="")
            	document.getElementById('DTCYLJ7').value = DTCYLJ6-DTCYLJ0;
            if(XXLLJLJ0!="" && XXLLJLJ6!="")
            	document.getElementById('XXLLJLJ7').value = XXLLJLJ6-XXLLJLJ0;    
        	if(CCLLJ0!="" && CCLLJ6!="")
                document.getElementById('CCLLJ7').value = CCLLJ6-CCLLJ0;

        	var ZCSL0 = 0;

            var ZCSL1 = Number(YJCSLJ1)-Number(YJCSLJ0)+Number(RHXCSLJ1)-Number(RHXCSLJ0);
            var ZCSL2 = Number(YJCSLJ2)-Number(YJCSLJ1)+Number(RHXCSLJ2)-Number(RHXCSLJ1);
            var ZCSL3 = Number(YJCSLJ3)-Number(YJCSLJ2)+Number(RHXCSLJ3)-Number(RHXCSLJ2);
            var ZCSL4 = Number(YJCSLJ4)-Number(YJCSLJ3)+Number(RHXCSLJ4)-Number(RHXCSLJ3);
            var ZCSL5 = Number(YJCSLJ5)-Number(YJCSLJ4)+Number(RHXCSLJ5)-Number(RHXCSLJ4);
            var ZCSL6 = Number(YJCSLJ6)-Number(YJCSLJ5)+Number(RHXCSLJ6)-Number(RHXCSLJ5);

            document.getElementById('ZCSL1').value = ZCSL1;
            document.getElementById('ZCSL2').value = ZCSL2;
            document.getElementById('ZCSL3').value = ZCSL3;
            document.getElementById('ZCSL4').value = ZCSL4;
            document.getElementById('ZCSL5').value = ZCSL5;
            document.getElementById('ZCSL6').value = ZCSL6;

            
            document.getElementById('BHL1').value = ZCSL1-ZCSL0;
            document.getElementById('BHL2').value = ZCSL2-ZCSL1;
            document.getElementById('BHL3').value = ZCSL3-ZCSL2;
            document.getElementById('BHL4').value = ZCSL4-ZCSL3;
            document.getElementById('BHL5').value = ZCSL5-ZCSL4;
            document.getElementById('BHL6').value = ZCSL6-ZCSL5;
        }

        function changValue2(){
            var zzyl16 = $('#zzyl16').text();
            var zzyl20 = $('#zzyl20').text();
            var zzyl8 = $('#zzyl8').text();
            var jzyl16 = $('#ZYL16').val();
            var jzyl20 = $('#ZYL20').val();
            var jzyl8 = $('#ZYL8').val();

            var zsxcs16 = $('#zsxcs16').text();
            var zsxcs20 = $('#zsxcs20').text();
            var zsxcs8 = $('#zsxcs8').text();
            var jsxcs16 = $('#SXCS16').val();
            var jsxcs20 = $('#SXCS20').val();
            var jsxcs8 = $('#SXCS8').val();

            var zrhxcs16 = $('#zrhxcs16').text();
            var zrhxcs20 = $('#zrhxcs20').text();
            var zrhxcs8 = $('#zrhxcs8').text();
            var jrhxcs16 = $('#RHXCS16').val();
            var jrhxcs20 = $('#RHXCS20').val();
            var jrhxcs8 = $('#RHXCS8').val();

            var zdtcyl16 = $('#zdtcyl16').text();
            var zdtcyl20 = $('#zdtcyl20').text();
            var zdtcyl8 = $('#zdtcyl8').text();
            var jdtcyl16 = $('#DTCYL16').val();
            var jdtcyl20 = $('#DTCYL20').val();
            var jdtcyl8 = $('#DTCYL8').val();
			if(jzyl16!="" && zzyl16!="")
            	$('#tdzyl16').html(jzyl16-zzyl16);
			if(jzyl20!="" && zzyl20!="")
            	$('#tdzyl20').html(jzyl20-zzyl20);
			if(jzyl8!="" && zzyl8!="")
            	$('#tdzyl8').html(jzyl8-zzyl8);

			if(jsxcs16!="" && zsxcs16!="")
            	$('#tdsxcs16').html(jsxcs16-zsxcs16);
			if(jsxcs20!="" && zsxcs20!="")
            	$('#tdsxcs20').html(jsxcs20-zsxcs20);
			if(jsxcs8!="" && zsxcs8!="")
            	$('#tdsxcs8').html(jsxcs8-zsxcs8);

			if(jrhxcs16!="" && zrhxcs16!="")
            	$('#tdrhxcs16').html(jrhxcs16-zrhxcs16);
			if(jrhxcs20!="" && zrhxcs20!="")
            	$('#tdrhxcs20').html(jrhxcs20-zrhxcs20);
			if(jrhxcs8!="" && zrhxcs8!="")
            	$('#tdrhxcs8').html(jrhxcs8-zrhxcs8);

			if(jdtcyl16!="" && zdtcyl16!="")
            	$('#tddtcyl16').html(jdtcyl16-zdtcyl16);
			if(jdtcyl20!="" && zdtcyl20!="")
            	$('#tddtcyl20').html(jdtcyl20-zdtcyl20);
			if(jdtcyl8!="" && zdtcyl8!="")
            	$('#tddtcyl8').html(jdtcyl8-zdtcyl8);


			var zrccyl = $('#zrccyl').text();
			var zyclj = $('#zyclj').text();
			var zzxprj = $('#zzxprj').text();

			var jrccyl = $('#RCCYL').val();
			var jyclj = $('#YCLJ').val();
			var jzxprj = $('#ZXPRJ').val();

			if(jdtcyl8!="" && jrccyl!="")
				$('#tdrccyl').html((jrccyl/jdtcyl8*100).toFixed(2));
			if(jzyl8!="" && jyclj!="")
				$('#tdyclj').html((jyclj/jzyl8*100).toFixed(2));
			if(jzyl8!="" && jzxprj!="")
				$('#tdzxprj').html((jzxprj/jzyl8*100).toFixed(2));
        }
		var YCLJJYLSUM = 0;
		var ZXPRJJYLSUM = 0;
		var YCLG5JYLSUM = 0;
		var YCLG6JYLSUM = 0;
        function changeValue3(){
            for(var i=0;i<13;i++){
            	YCLJJYLSUM += Number($("#YCLJJYL"+i).val());
            	ZXPRJJYLSUM += Number($("#ZXPRJJYL"+i).val());
            	YCLG5JYLSUM += Number($("#YCLG5JYL"+i).val());
            	YCLG6JYLSUM += Number($("#YCLG6JYL"+i).val());
            }
            document.getElementById('YCLJJYL13').value = YCLJJYLSUM;
            document.getElementById('ZXPRJJYL13').value = ZXPRJJYLSUM;
            document.getElementById('YCLG5JYL13').value = YCLG5JYLSUM;
            document.getElementById('YCLG6JYL13').value = YCLG6JYLSUM;
            YCLJJYLSUM = 0;
    		ZXPRJJYLSUM = 0;
    		YCLG5JYLSUM = 0;
    		YCLG6JYLSUM = 0;
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
        	//if (onSearchByDate($("#txtDate").val())){
        	 jQuery.ajax({
					type : 'post',
					url : 'mbzhrb_searchZHRB.action?txtDate='+$("#txtDate").val(),
					success : function(data) { 
					var  TBR ="";
					var  RQ ="";
					var  BZ ="";
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
									if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
										var firstArr  = outData.JSONDATA.firstArr;
										//alert(fristArr[1].RPD_U2_GENERAL_ID );
										//if (fristArr[1].RPD_U2_GENERAL_ID != null && typeof(fristArr[1].RPD_U2_GENERAL_ID)!="undefined" && fristArr[1].RPD_U2_GENERAL_ID != ""){
										//	TRPD_U2_GENERAL_ID = fristArr[1].RPD_U2_GENERAL_ID;
										//}else{
										//	TRPD_U2_GENERAL_ID = ""	;
										//}
										var secondArr  = outData.JSONDATA.secondArr;
										var thirdArr  = outData.JSONDATA.thirdArr;
										//var fourdArr  = outData.JSONDATA.fourdArr;
										var nongduArr  = outData.JSONDATA.nongduArr;
										SHR = nongduArr[1].SHR;
										RD_U1_SAGD_GENERAL_ID = nongduArr[1].RD_U1_SAGD_GENERAL_ID;
										var llArr  = outData.JSONDATA.llArr;

									    	$("#tablearea").html("");
									    	
									    	tableval = 
									    		"  <input id='RD_U1_SAGD_LIQUID_ID3' type='hidden' value='"+llArr[3].RD_U1_SAGD_LIQUID_ID+"'>"+
										    	"  <input id='RD_U1_SAGD_LIQUID_ID4' type='hidden' value='"+llArr[4].RD_U1_SAGD_LIQUID_ID+"'>"+
										    	"  <input id='RD_U1_SAGD_LIQUID_ID5' type='hidden' value='"+llArr[5].RD_U1_SAGD_LIQUID_ID+"'>"+
										    	"  <input id='RD_U1_SAGD_GENERAL_ID' type='hidden' value='"+nongduArr[1].RD_U1_SAGD_GENERAL_ID+"'>"+
										    	"<table border=0 cellpadding=0 cellspacing=0 width=3672 style='border-collapse:collapse;table-layout:fixed;width:2754pt'>"+
									    		" <col width=72 span=51 style='width:54pt'>"+
									    		" <tr height=27 style='height:20.25pt'>"+
									    		"  	<td colspan=51 height=27 class=xl2298439 width=3672 style='height:20.25pt;width:2754pt'>30万吨SAGD密闭实验站报表</td>"+
									    		" </tr>"+
									    		" <tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
									    		"  <td colspan=2 height=29 class=xl2308439 style='height:21.95pt'>值班人：</td>"+
									    		"  <td colspan=37 class=xl2318439 style='border-left:none'><input id='ZBR' type='text' style='background:transparent;border:0px solid;width:3600px;line-height:25px;height:30px;font-size: 12pt;text-align:left;' value='"+nongduArr[1].ZBR+"'/></td>"+
									    		"  <td class=xl2308439 style='border-top:none;border-left:none'>审核：</td>"+
									    		"  <td colspan=5 class=xl2318439 style='border-left:none'>"+SHR+"</td>"+
									    		"  <td colspan=2 class=xl2308439 style='border-left:none'>日期：</td>"+
									    		"  <td id='RQ' colspan=4 class=xl2318439 style='border-left:none'>"+nongduArr[1].REPORT_TIME+"</td>"+
									    		" </tr>"+
									    		" <tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
									    		"  <td colspan=21 height=29 class=xl2238439 style='border-right:1.0pt solid black;height:21.95pt'>重要生产参数</td>"+
									    		"  <td colspan=11 class=xl2238439 style='border-right:1.0pt solid black;border-left:none'>汇报信息</td>"+
									    		"  <td colspan=19 class=xl2268439 style='border:1.0pt solid black'>重点工作</td>"+
									    		" </tr>"+
									    		" <tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
									    		"  <td rowspan=2 height=58 class=xl1598439 width=72 style='height:43.9pt;border-top:none;width:54pt'>时间</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>仰角出水</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>热化学出水流量</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>1#热化学进液流量</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>2#热化学进液流量</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>3#热化学进液流量</td>"+
									    		" <td rowspan=2 class=xl718439 width=72 style='border-top:none;width:54pt'>总出<br>水量</td>"+
									    		"  <td rowspan=2 class=xl718439 width=72 style='border-top:none;width:54pt'>变化量</td>"+
									    		"  <td colspan=3 class=xl708439 width=216 style='border-left:none;width:162pt'>电脱出油</td>"+
									    		"  <td colspan=3 class=xl708439 width=216 style='border-left:none;width:162pt'>楔形流量计</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black;border-left:none;width:108pt'>掺柴量</td>"+
									    		"  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>流量</td>"+
									    		"  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'>昨日</td>"+
									    		"  <td class=xl718439 width=72 style='border-top:none;border-left:none;width:54pt'>今日</td>"+
									    		"  <td class=xl718439 width=72 style='border-top:none;border-left:none;width:54pt'>变化量</td>"+
									    		"  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'>　</td>"+
									    		"  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'>昨日加入量</td>"+
									    		"  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'>今日加入量</td>"+
									    		"  <td colspan=2 class=xl2158439 width=144 style='border-right:1.0pt solid black;border-left:none;width:108pt'>浓度</td>"+
									    		"  <td colspan=19 rowspan=13 class=xl2178439 style='border-right:1.0pt solid black;border-bottom:1.0pt solid black'><textarea id='ZYGZ' rows='25' cols='175' style='background:transparent;border:0px solid;line-height:25px;font-size: 12pt;text-align:left;'>"+nongduArr[1].ZYGZ+"</textarea></td>"+
									    		" </tr>"+
									    		" <tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
									    		"  <td colspan=2 height=29 class=xl708439 width=144 style='height:21.95pt;border-left:none;width:108pt'>累计读数m&sup3;</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>累计读数m&sup3;</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>累计读数m&sup3;</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>累计读数m&sup3;</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>累计读数m&sup3;</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>累计读数m&sup3;</td>"+
									    		"  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'>瞬时m&sup3;/h</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-left:none;width:108pt'>累计读数m&sup3;</td>"+
									    		"  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'>瞬时m&sup3;/h</td>"+
									    		"  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black;border-left:none;width:108pt'>累计读数m&sup3;</td>"+
									    		"  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>16:00总液量</td>"+
									    		"  <td id='zzyl16' class=xl1228439 style='border-top:none;border-left:none'>"+llArr[0].ZYL+"</td>"+
									    		"  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZYL16'  type='text' onblur='changValue2()' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[3].ZYL+"'/></td>"+
									    		"  <td id='tdzyl16' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].ZYL16+"</td>"+
									    		"  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'>日掺柴油量</td>"+
									    		"  <td id='zrccyl' class=xl1338439 width=72 style='border-top:none;border-left:none;width:54pt'>"+nongduArr[0].RCCYL+"</td>"+
									    		"  <td class=xl1338439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='RCCYL'  type='text' onblur='changValue2()' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+nongduArr[1].RCCYL+"'/></td>"+
									    		"  <td id='tdrccyl' colspan=2 class=xl2078439 width=144 style='border-right:1.0pt solid black;border-left:none;width:108pt'>"+nongduArr[2].RCCYL_ND+"</td>"+
									    		" </tr>";

									    		 for(var i=0;i<thirdArr.length+3;i++){
										    		 if(i<thirdArr.length){
														 if(i<thirdArr.length-1){
															 tableval += 
													    		 "<tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
													    		 "  <td style='display: none'><input id='RD_U1_SAGD_PIVOTAL_ID"+i+"' type='hidden' value='"+thirdArr[i].RD_U1_SAGD_PIVOTAL_ID+"'></td>"+
																 "	  <td id='REPORT_TIME"+i+"' height=29 class=xl728439 style='height:21.95pt;border-top:none'>"+thirdArr[i].REPORT_TIME+"</td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='YJCSLJ"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].YJCSLJ+"'/></td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='RHXCSLJ"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].RHXCSLJ+"'/></td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='RHX1JYLJ"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].RHX1JYLJ+"'/></td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='RHX2JYLJ"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].RHX2JYLJ+"'/></td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='RHX3JYLJ"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].RHX3JYLJ+"'/></td>"+
																 "	  <td class=xl718439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZCSL"+i+"' onkeyup='checkDataInt(this)' disabled='disabled'  type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].ZCSL+"'/></td>"+
																 "	  <td class=xl718439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='BHL"+i+"' onkeyup='checkDataInt(this)' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].BHL+"'/></td>"+
																 "	  <td colspan=2 class=xl2108439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='DTCYLJ"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].DTCYLJ+"'/></td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DTCYLL"+i+"' onkeyup='checkDataInt(this)' onblur='changValue()' type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].DTCYLL+"'/></td>"+
																 "	  <td colspan=2 class=xl2108439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='XXLLJLJ"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].XXLLJLJ+"'/></td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XXLLJLL"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)' type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].XXLLJLL+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black;border-left:none;width:108pt'><input id='CCLLJ"+i+"' onblur='changValue()' onkeyup='checkDataInt(this)'  type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].CCLLJ+"'/></td>";

														 }else{
															 tableval += 
													    		 "<tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
																 "	  <td id='REPORT_TIME"+i+"' height=29 class=xl728439 style='height:21.95pt;border-top:none'>"+thirdArr[i].REPORT_TIME+"</td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='YJCSLJ"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].YJCSLJ+"'/></td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='RHXCSLJ"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].RHXCSLJ+"'/></td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='RHX1JYLJ"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].RHX1JYLJ+"'/></td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='RHX2JYLJ"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].RHX2JYLJ+"'/></td>"+
																 "	  <td colspan=2 class=xl2028439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='RHX3JYLJ"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].RHX3JYLJ+"'/></td>"+
																 "	  <td class=xl718439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZCSL"+i+"' disabled='disabled'  type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].ZCSL+"'/></td>"+
																 "	  <td class=xl718439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='BHL"+i+"'  disabled='disabled' type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].BHL+"'/></td>"+
																 "	  <td colspan=2 class=xl2108439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='DTCYLJ"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].DTCYLJ+"'/></td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DTCYLL"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].DTCYLL+"'/></td>"+
																 "	  <td colspan=2 class=xl2108439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><input id='XXLLJLJ"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].XXLLJLJ+"'/></td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XXLLJLL"+i+"' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].XXLLJLL+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black;border-left:none;width:108pt'><input id='CCLLJ"+i+"' disabled='disabled'  type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[i].CCLLJ+"'/></td>";

														 }
											    		 if(i==0){
															tableval +=
																 "	  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>16:00三相出水</td>"+
																 "	  <td id='zsxcs16' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[0].SXCS+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='SXCS16'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[3].SXCS+"'/></td>"+
																 "	  <td id='tdsxcs16' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].SXCS16+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>预处理剂</td>"+
																 "	  <td id='zyclj' class=xl1338439 width=72 style='border-top:none;border-left:none;width:54pt'>"+nongduArr[0].YCLJ+"</td>"+
																 "	  <td class=xl1338439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLJ'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+nongduArr[1].YCLJ+"'/></td>"+
																 "	  <td id='tdyclj' colspan=2 class=xl2078439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'>"+nongduArr[2].YCLJ_ND+"</td>"+
																 "	 </tr>"; 
														 }else if(i==1){
															 tableval += 
																 "	  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>16:00热化学出水</td>"+
																 "	  <td id='zrhxcs16' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[0].RHXCS+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='RHXCS16'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[3].RHXCS+"'/></td>"+
																 "	  <td id='tdrhxcs16' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].RHXCS16+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>正相破乳剂</td>"+
																 "	  <td id='zzxprj' class=xl1338439 width=72 style='border-top:none;border-left:none;width:54pt'>"+nongduArr[0].ZXPRJ+"</td>"+
																 "	  <td class=xl1338439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZXPRJ'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+nongduArr[1].ZXPRJ+"'/></td>"+
																 "	  <td id='tdzxprj' colspan=2 class=xl2078439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'>"+nongduArr[2].ZXPRJ_ND+"</td>"+
																 "	 </tr>";
														 }else if(i==2){
															 tableval += 
																 "	  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>16:00电脱出油量</td>"+
																 "	  <td id='zdtcyl16' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[0].DTCYL+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DTCYL16'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[3].DTCYL+"'/></td>"+
																 "	  <td id='tddtcyl16' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].DTCYL16+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'>三相出水含油</td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'>电脱出水含油 </td>"+
																 "	 </tr>";
														 }else if(i==3){
															 tableval += 
																 "	  <td colspan=3 class=xl2058439 width=216 style='border-left:none;width:162pt'>20:00总液量</td>"+
																 "	  <td id='zzyl20' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[1].ZYL+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZYL20'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[4].ZYL+"'/></td>"+
																 "	  <td id='tdzyl20' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].ZYL20+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>8:00</td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'><input id='SXCSHY0'  type='text' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[0].SXCSHY+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'><input id='DTCSHY0' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[0].DTCSHY+"'/></td>"+
																 "	 </tr>";
														 }else if(i==4){
															 tableval += 
																 "	  <td colspan=3 class=xl2058439 width=216 style='border-left:none;width:162pt'>20:00三相出水</td>"+
																 "	  <td id='zsxcs20' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[1].SXCS+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='SXCS20'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[4].SXCS+"'/></td>"+
																 "	  <td id='tdsxcs20' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].SXCS20+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>12:00</td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'><input id='SXCSHY1'  type='text' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].SXCSHY+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'><input id='DTCSHY1'  type='text' onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].DTCSHY+"'/></td>"+
																 "	 </tr>";
														 }else if(i==5){
															 tableval += 
																 "	  <td colspan=3 class=xl2058439 width=216 style='border-left:none;width:162pt'>20:00热化学出水</td>"+
																 "	  <td id='zrhxcs20' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[1].RHXCS+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='RHXCS20'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[4].RHXCS+"'/></td>"+
																 "	  <td id='tdrhxcs20' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].RHXCS20+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>16:00</td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'><input id='SXCSHY2'  type='text' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[2].SXCSHY+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'><input id='DTCSHY2' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[2].DTCSHY+"'/></td>"+
																 "	 </tr>";
														 }else if(i==6){
															 tableval += 
																 "	  <td colspan=3 class=xl2058439 width=216 style='border-left:none;width:162pt'>20:00电脱出油量</td>"+
																 "	  <td id='zdtcyl20' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[1].DTCYL+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DTCYL20'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[4].DTCYL+"'/></td>"+
																 "	  <td id='tddtcyl20' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].DTCYL20+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>20:00</td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'><input id='SXCSHY3'  type='text' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[3].SXCSHY+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'><input id='DTCSHY3' onkeyup='checkData(this)' onblur='checkData2p(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[3].DTCSHY+"'/></td>"+
																 "	 </tr>";
														 }else if(i==7){
															 tableval += 
																 "	  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>8:00总液量</td>"+
																 "	  <td id='zzyl8' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[2].ZYL+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZYL8' onblur='changValue2()'  type='text' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[5].ZYL+"'/></td>"+
																 "	  <td id='tdzyl8' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].ZYL8+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>0:00</td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'><input id='SXCSHY4'  type='text' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[4].SXCSHY+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'><input id='DTCSHY4' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[4].DTCSHY+"'/></td>"+
																 "	 </tr>";
														 }
														 else{
															 tableval += 
																 "	  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'></td>"+
																 "	  <td class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>SXCS</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>1 </td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>#VALUE!</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>预处理剂</td>"+
																 "	  <td class=xl1338439 width=72 style='border-top:none;border-left:none;width:54pt'>YCLJ</td>"+
																 "	  <td class=xl1338439 width=72 style='border-top:none;border-left:none;width:54pt'>1 </td>"+
																 "	  <td colspan=2 class=xl2078439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'>100.00 </td>"+
																 "	 </tr>";
														 }
										    		 }else{
										    			 tableval += 
													    		"  <tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
													    		"  <td height=29 class=xl1178439 style='height:21.95pt'>　</td>"+
													    		"  <td colspan=2 class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td colspan=2 class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td colspan=2 class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td colspan=2 class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td colspan=2 class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td class=xl1268439 style='border-left:none'>　</td>"+
													    		"  <td class=xl1268439 style='border-left:none'>　</td>"+
													    		"  <td colspan=2 class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td colspan=2 class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td class=xl1278439 style='border-left:none'>　</td>"+
													    		"  <td colspan=2 class=xl1278439 style='border-right:1.0pt solid black;border-left:none'>　</td>";
										    			 if(i==8){
															 tableval += 
																 "	  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>8:00三相出水</td>"+
																 "	  <td id='zsxcs8' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[2].SXCS+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='SXCS8'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[5].SXCS+"'/></td>"+
																 "	  <td id='tdsxcs8' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].SXCS8+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>4:00</td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'><input id='SXCSHY5'  type='text' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[5].SXCSHY+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'><input id='DTCSHY5' onkeyup='checkData(this)' onblur='checkData2p(this)'  type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[5].DTCSHY+"'/></td>"+
																 "	 </tr>";
														 }else if(i==9){
															 tableval += 
																 "	  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>8:00热化学出水</td>"+
																 "	  <td id='zrhxcs8' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[2].RHXCS+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='RHXCS8'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[5].RHXCS+"'/></td>"+
																 "	  <td id='tdrhxcs8' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].RHXCS8+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'>8:00</td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'><input id='SXCSHY6'  type='text' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[6].SXCSHY+"'/></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'><input id='DTCSHY6' onkeyup='checkData(this)' onblur='checkData2p(this)' type='text' style='background:transparent;border:0px solid;width:150px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[6].DTCSHY+"'/></td>"+
																 "	 </tr>";
														 }else if(i==10){
															 tableval += 
																 "	  <td colspan=3 class=xl1598439 width=216 style='border-left:none;width:162pt'>8:00电脱出油量</td>"+
																 "	  <td id='zdtcyl8' class=xl1318439 width=72 style='border-top:none;border-left:none; width:54pt'>"+llArr[2].DTCYL+"</td>"+
																 "	  <td class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DTCYL8'  type='text' onblur='changValue2()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+llArr[5].DTCYL+"'/></td>"+
																 "	  <td id='tddtcyl8' class=xl1228439 width=72 style='border-top:none;border-left:none;width:54pt'>"+llArr[6].DTCYL8+"</td>"+
																 "	  <td class=xl708439 width=72 style='border-top:none;border-left:none; width:54pt'></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-top:none;border-left:none;width:108pt'></td>"+
																 "	  <td colspan=2 class=xl708439 width=144 style='border-right:1.0pt solid black; border-left:none;width:108pt'></td>"+
																 "	 </tr>";
														 }
										    		 }
										    	 }

										    	 tableval += 
											    	 "<tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
														"  <td rowspan=3 height=65 class=xl1998439 width=72 style='border-bottom:.5pt solid black;height:48.95pt;border-top:none;width:54pt'>时间</td>"+
														"  <td colspan=3 class=xl1768439 width=216 style='border-left:none;width:162pt'>预处理剂</td>"+
														"  <td colspan=3 class=xl1768439 width=216 style='border-left:none;width:162pt'>正相破乳剂</td>"+
														"  <td colspan=4 class=xl1768439 width=288 style='border-left:none;width:216pt'>预处理液位</td>"+
														"  <td colspan=2 class=xl1928439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><span style='mso-spacerun:yes'>&nbsp;</span>JYB1BH #加药泵</td>"+
														"  <td colspan=2 class=xl1928439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><span style='mso-spacerun:yes'>&nbsp;</span>JYB2BH #加药泵</td>"+
														"  <td colspan=2 class=xl1948439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><span style='mso-spacerun:yes'>&nbsp;</span><font class='font98439'><span style='mso-spacerun:yes'>&nbsp;</span>JYB3BH</font><span style='mso-spacerun:yes'>&nbsp; </span>#加药泵</td>"+
														"  <td colspan=2 class=xl1948439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'><span style='mso-spacerun:yes'>&nbsp;</span><font class='font98439'><span style='mso-spacerun:yes'>&nbsp;</span>JYB4BH</font> #加药泵</td>"+
														"  <td colspan=2 class=xl1928439 width=144 style='border-right:1.0pt solid black;border-left:none;width:108pt'><span style='mso-spacerun:yes'>&nbsp;</span>CCBBH #掺柴泵</td>"+
														"  <td rowspan=3 class=xl1858439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>时间</td>"+
														"  <td colspan=2 class=xl1458439 width=144 style='border-left:none;width:108pt'>1#蒸汽分离器</td>"+
														"  <td colspan=2 class=xl1458439 width=144 style='border-left:none;width:108pt'>2#蒸汽分离器</td>"+
														"  <td colspan=2 class=xl1458439 width=144 style='border-left:none;width:108pt'>3#蒸汽分离器</td>"+
														"  <td colspan=3 class=xl1708439 width=216 style='border-right:.5pt solid black;border-left:none;width:162pt'>1#仰角分离器</td>"+
														"  <td colspan=3 class=xl1708439 width=216 style='border-right:.5pt solid black;border-left:none;width:162pt'>2#仰角分离器</td>"+
														"  <td colspan=3 class=xl1708439 width=216 style='border-right:.5pt solid black;border-left:none;width:162pt'>3#仰角分离器</td>"+
														"  <td rowspan=3 class=xl1738439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>时间</td>"+
														"  <td colspan=3 class=xl1568439 width=216 style='border-left:none;width:162pt'>电脱水器</td>"+
														"  <td colspan=3 class=xl1768439 width=216 style='border-left:none;width:162pt'>补气装置</td>"+
														"  <td colspan=3 class=xl1688439 style='border-left:none'>泄压装置</td>"+
														"  <td colspan=2 class=xl1688439 style='border-left:none'>1#空压机</td>"+
														"  <td colspan=2 class=xl1688439 style='border-right:1.0pt solid black;border-left:none'>2#空压机</td>"+
														" </tr>"+
														" <tr height=18 style='height:13.5pt'>"+
														"  <td rowspan=2 height=36 class=xl1668439 width=72 style='border-bottom:.5pt solid black;height:27.0pt;border-top:none;width:54pt'>1#罐<br>mm</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>2#罐<br> mm</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>加药量<br>kg</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>3#罐<br> mm</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>4#罐<br>mm</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>加药量<br>kg</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>5#罐<br> mm</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>加药量<br>kg</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>6#罐<br> mm</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>加药量<br>kg</td>"+
														"  <td rowspan=2 class=xl1908439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出口压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>开度</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出口压力<br> Mpa</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>开度</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出口压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>开度</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出口压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>开度</td>"+
														"  <td rowspan=2 class=xl1668439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出口压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1888439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>频率</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>进液压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>液位<br>mm</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>进液压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>液位<br>mm</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>进液压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>液位<br>mm</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出油温度<br>℃</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>油水界面m</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>容器压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出油温度<br> ℃</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>油水界面m</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>容器压力<br>Mpa</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出油温度<br>℃</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>油水界面m</td>"+
														"  <td rowspan=2 class=xl1778439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>容器压力<br> Mpa</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>缓冲腔液位m</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>容器压力<br> Mpa</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>出油温度<br> ℃</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>预脱压力(mpa)</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>热化学压力(mpa)</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>电化学压力(mpa)</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>预脱压力(mpa)</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>热化学压力(mpa)</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>电化学压力(mpa)</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>压力</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>温度</td>"+
														"  <td rowspan=2 class=xl1538439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>压力</td>"+
														"  <td rowspan=2 class=xl1778439 width=72 style='border-bottom:.5pt solid black;border-top:none;width:54pt'>温度</td>"+
														" </tr>"+
														" <tr height=18 style='height:13.5pt'>"+
														" </tr>";
														var disStr="";
													if(mod==1){
														disStr = "onclick='isChange(this)'";
													}
													for(var i=0;i<firstArr.length;i++){
														if(i<firstArr.length-1){
															tableval +=
																"<tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
																"  <td style='display: none'><input id='RD_U1_SAGD_AUTO_ID"+i+"' type='hidden' value='"+firstArr[i].RD_U1_SAGD_AUTO_ID+"'></td>"+
																"	  <td id='REPORT_TIME_2"+i+"' height=29 class=xl768439 width=72 style='height:21.95pt;border-top:none;width:54pt'>"+firstArr[i].REPORT_TIME+"</td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLJG1YW"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLJG1YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLJG2YW"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLJG2YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLJJYL"+i+"'  type='text' "+disStr+" onblur='changeValue3()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLJJYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZXPRJG3YW"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].ZXPRJG3YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZXPRJG4YW"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].ZXPRJG4YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZXPRJJYL"+i+"'  type='text' "+disStr+" onblur='changeValue3()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].ZXPRJJYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLG5YW"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLG5YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLG5JYL"+i+"'  type='text' "+disStr+" onblur='changeValue3()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLG5JYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLG6YW"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLG6YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLG6JYL"+i+"'  type='text' "+disStr+" onblur='changeValue3()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLG6JYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB1CKYL"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB1CKYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB1KD"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB1KD+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB2CKYL"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB2CKYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB2KD"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB2KD+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB3CKYL"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB3CKYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB3KD"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB3KD+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB4CKYL"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB4CKYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB4KD"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB4KD+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CCBCKYL"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].CCBCKYL+"'/></td>"+
																"	  <td class=xl788439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CCBPL"+i+"'  type='text' "+disStr+" onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].CCBPL+"'/></td>"+
																"	  <td class=xl798439 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[i].REPORT_TIME+"</td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PR_3902"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PR_3902+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_2101A"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LRC_2101A+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PT_2112"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PT_2112+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_2101B"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LRC_2101B+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PRC_3FLQ"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3FLQ+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3FLQ"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LRC_3FLQ+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='TR_3101A"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TR_3101A+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='LIT_3101A"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT_3101A+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='PR_3101A"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PR_3101A+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='TR_3101B"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TR_3101B+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='LIT_3101B"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT_3101B+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='PR_3101B"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PR_3101B+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='TR_3101C"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TR_3101C+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='LIT_3101C"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT_3101C+"'/></td>"+
																"	  <td class=xl818439 style='border-top:none;border-left:none'><input id='PR_3101C"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PR_3101C+"'/></td>"+
																"	  <td class=xl828439 style='border-top:none;border-left:none'>"+firstArr[i].REPORT_TIME+"</td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none; width:54pt'><input id='LRC_3202D"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LRC_3202D+"'/></td>"+
																"	  <td class=xl848439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PRA_3201D"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRA_3201D+"'/></td>"+
																"	  <td class=xl858439 style='border-top:none;border-left:none'><input id='TR_3201D"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TR_3201D+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3703"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3703+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3702"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3702+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3706"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3706+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3704"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3704+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3705"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3705+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3707"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3707+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none; width:54pt'><input id='YSJ_P1"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YSJ_P1+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none; width:54pt'><input id='YSJ_T1"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YSJ_T1+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YSJ_P2"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YSJ_P2+"'/></td>"+
																"	  <td class=xl878439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YSJ_T2"+i+"'  type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData1p(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YSJ_T2+"'/></td>"+
																"	 </tr>";
														}else{
															tableval +=
																"<tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
																"	  <td id='REPORT_TIME_2"+i+"' height=29 class=xl768439 width=72 style='height:21.95pt;border-top:none;width:54pt'>"+firstArr[i].REPORT_TIME+"</td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLJG1YW"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLJG1YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLJG2YW"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLJG2YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLJJYL"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLJJYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZXPRJG3YW"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].ZXPRJG3YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZXPRJG4YW"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].ZXPRJG4YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZXPRJJYL"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].ZXPRJJYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLG5YW"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLG5YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLG5JYL"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLG5JYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLG6YW"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLG6YW+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YCLG6JYL"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YCLG6JYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB1CKYL"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB1CKYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB1KD"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB1KD+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB2CKYL"+i+"'  type='text' disabled='disabled'   style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB2CKYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB2KD"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB2KD+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB3CKYL"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB3CKYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB3KD"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB3KD+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB4CKYL"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB4CKYL+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='JYB4KD"+i+"'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].JYB4KD+"'/></td>"+
																"	  <td class=xl778439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CCBCKYL"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].CCBCKYL+"'/></td>"+
																"	  <td class=xl788439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CCBPL"+i+"'  type='text'  disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].CCBPL+"'/></td>"+
																"	  <td class=xl798439 width=72 style='border-top:none;border-left:none;width:54pt'> </td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PR_3902"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PR_3902+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_2101A"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LRC_2101A+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PT_2112"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PT_2112+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_2101B"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LRC_2101B+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PRC_3FLQ"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3FLQ+"'/></td>"+
																"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3FLQ"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LRC_3FLQ+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='TR_3101A"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TR_3101A+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='LIT_3101A"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT_3101A+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='PR_3101A"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PR_3101A+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='TR_3101B"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TR_3101B+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='LIT_3101B"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT_3101B+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='PR_3101B"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PR_3101B+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='TR_3101C"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TR_3101C+"'/></td>"+
																"	  <td class=xl808439 style='border-top:none;border-left:none'><input id='LIT_3101C"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LIT_3101C+"'/></td>"+
																"	  <td class=xl818439 style='border-top:none;border-left:none'><input id='PR_3101C"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PR_3101C+"'/></td>"+
																"	  <td class=xl828439 style='border-top:none;border-left:none'> </td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none; width:54pt'><input id='LRC_3202D"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LRC_3202D+"'/></td>"+
																"	  <td class=xl848439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PRA_3201D"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRA_3201D+"'/></td>"+
																"	  <td class=xl858439 style='border-top:none;border-left:none'><input id='TR_3201D"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].TR_3201D+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3703"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3703+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3702"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3702+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3706"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3706+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3704"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3704+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3705"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3705+"'/></td>"+
																"	  <td class=xl868439 style='border-top:none;border-left:none'><input id='PRC_3707"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].PRC_3707+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none; width:54pt'><input id='YSJ_P1"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YSJ_P1+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none; width:54pt'><input id='YSJ_T1"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YSJ_T1+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YSJ_P2"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YSJ_P2+"'/></td>"+
																"	  <td class=xl878439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YSJ_T2"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YSJ_T2+"'/></td>"+
																"	 </tr>";
														}
													}

													tableval += 
														"<tr height=18 style='height:13.5pt'>"+
														"	  <td rowspan=3 height=68 class=xl1558439 style='height:51.0pt'>时间</td>"+
														"	  <td colspan=5 rowspan=2 class=xl1568439 width=360 style='width:270pt'>1#热化学</td>"+
														"	  <td colspan=5 rowspan=2 class=xl1568439 width=360 style='width:270pt'>2#热化学</td>"+
														"	  <td colspan=5 rowspan=2 class=xl1568439 width=360 style='border-right:1.0pt solid black;width:270pt'>3#热化学</td>"+
														"	  <td rowspan=3 class=xl1588439 width=72 style='width:54pt'>时间</td>"+
														"	  <td colspan=5 class=xl1608439 style='border-right:.5pt solid black;border-left:none'>1 #换热器</td>"+
														"	  <td colspan=5 class=xl1418439 style='border-left:none'>2 #换热器</td>"+
														"	  <td colspan=5 class=xl1418439 style='border-left:none'>3 #换热器</td>"+
														"	  <td colspan=5 class=xl1428439 width=360 style='border-right:.5pt solid black;border-left:none;width:270pt'>4 #换热器</td>"+
														"	  <td colspan=2 rowspan=2 class=xl1458439 width=144 style='width:108pt'>1-1换热器</td>"+
														"	  <td colspan=2 rowspan=2 class=xl1458439 width=144 style='width:108pt'>1-2换热器</td>"+
														"	  <td colspan=2 rowspan=2 class=xl1458439 width=144 style='width:108pt'>2 -1<span style='mso-spacerun:yes'>&nbsp; </span>换热器</td>"+
														"	  <td colspan=2 rowspan=2 class=xl1458439 width=144 style='width:108pt'>2 - 2换热器</td>"+
														"	  <td colspan=2 rowspan=2 class=xl1498439 width=144 style='border-right:.5pt solid black;border-bottom:.5pt solid black;width:108pt'>干燥器</td>"+
														"	  <td rowspan=2 class=xl1358439 style='border-bottom:.5pt solid black;border-top:none'>　</td>"+
														"	  <td rowspan=2 class=xl1358439 style='border-bottom:.5pt solid black;border-top:none'>　</td>"+
														"	  <td rowspan=2 class=xl1378439 style='border-bottom:.5pt solid black;border-top:none'>　</td>"+
														"	  <td rowspan=2 class=xl1398439 style='border-bottom:.5pt solid black;border-top:none'>　</td>"+
														"	 </tr>"+
														"	 <tr height=18 style='height:13.5pt'>"+
														"	  <td colspan=2 height=18 class=xl1488439 style='height:13.5pt;border-left:none'>蒸汽</td>"+
														"	  <td colspan=3 class=xl1638439 style='border-right:.5pt solid black;border-left:none'>冷源</td>"+
														"	  <td colspan=2 class=xl1488439 style='border-left:none'>蒸汽</td>"+
														"	  <td colspan=3 class=xl1018439 width=216 style='border-left:none;width:162pt'>冷源</td>"+
														"	  <td colspan=2 class=xl1018439 width=144 style='border-left:none;width:108pt'>蒸汽</td>"+
														"	  <td colspan=3 class=xl1018439 width=216 style='border-left:none;width:162pt'>冷源</td>"+
														"	  <td colspan=2 class=xl1468439 width=144 style='border-right:.5pt solid black;border-left:none;width:108pt'>蒸汽</td>"+
														"	  <td colspan=3 class=xl1018439 width=216 style='border-left:none;width:162pt'>冷源</td>"+
														"	 </tr>"+
														"	 <tr height=32 style='height:24.0pt'>"+
														"	  <td height=32 class=xl838439 width=72 style='height:24.0pt;border-top:none;border-left:none;width:54pt'>前腔液位<br>m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>缓冲腔液位m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>后腔液位<br>m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>容器压力<br>Mpa</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>出油温度<br>℃</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>前腔液位<br>m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>缓冲腔液位m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>后腔液位<br> m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>容器压力<br>Mpa</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>出油温度<br>℃</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>前腔液位<br>m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none; width:54pt'>缓冲腔液位m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>后腔液位<br>m</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'>容器压力<br> Mpa</td>"+
														"	  <td class=xl878439 width=72 style='border-top:none;border-left:none;width:54pt'>出油温度<br> ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>换前温度<br>℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>换后温度<br> ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>换前温度<br>℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none; width:54pt'>换后温度<br> ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>流量<br> m3</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none; width:54pt'>换前温度<br>℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>换后温度<br> ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>换前温度<br>℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>换后温度<br> ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>流量<br>m3</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;width:54pt'>换前温度<br>℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>换后温度<br>  ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>换前温度<br>"+
														"	    ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>流量<br>"+
														"	    m3</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>换后温度<br>"+
														"	    ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>换前温度℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>换后温度<br>"+
														"	    ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>换前温度<br>"+
														"	    ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>换后温度<br>"+
														"	    ℃</td>"+
														"	  <td class=xl1018439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>流量<br>"+
														"	    m3</td>"+
														"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>出水温度</td>"+
														"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>流量</td>"+
														"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>出水温度</td>"+
														"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>流量</td>"+
														"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>出油温度</td>"+
														"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>流量</td>"+
														"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>出油温度</td>"+
														"	  <td class=xl708439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>流量</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>压力</td>"+
														"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;"+
														"	  width:54pt'>温度</td>"+
														"	  <td class=xl1028439 style='border-top:none;border-left:none'>　</td>"+
														"	  <td class=xl1028439 style='border-top:none;border-left:none'>　</td>"+
														"	  <td class=xl1038439 style='border-top:none;border-left:none'>　</td>"+
														"	  <td class=xl1048439 style='border-top:none'>　</td>"+
														"	 </tr>";
														
														for(var i=0;i<secondArr.length;i++){
															tableval +=
																"<tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
																"	  <td height=29 class=xl728439 style='height:21.95pt;border-top:none'>"+secondArr[i].REPORT_TIME+"</td>"+
																"	  <td class=xl848439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3201A"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3201A+"'/></td>"+
																"	  <td class=xl848439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3202A"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3202A+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3203A"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3203A+"'/></td>"+
																"	  <td class=xl898439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PR_3201A"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].PR_3201A+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='TR_3201A"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TR_3201A+"'/></td>"+
																"	  <td class=xl848439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3201B"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3201B+"'/></td>"+
																"	  <td class=xl848439 width=72 style='border-top:none;border-left:none; width:54pt'><input id='LRC_3202B"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3202B+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3203B"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3203B+"'/></td>"+
																"	  <td class=xl898439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PR_3201B"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].PR_3201B+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='TR_3201B"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TR_3201B+"'/></td>"+
																"	  <td class=xl848439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3201C"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3201C+"'/></td>"+
																"	  <td class=xl848439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3202C"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3202C+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='LRC_3203C"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].LRC_3203C+"'/></td>"+
																"	  <td class=xl898439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='PR_3201C"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].PR_3201C+"'/></td>"+
																"	  <td class=xl878439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='TR_3201C"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TR_3201C+"'/></td>"+
																"	  <td class=xl798439 width=72 style='border-top:none;border-left:none; width:54pt'>"+secondArr[i].REPORT_TIME+"</td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_102"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_102+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_105"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_105+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='HRQ1LYHQWD"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].HRQ1LYHQWD+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_115"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_115+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='FIT_101"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT_101+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_101"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_101+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_106"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_106+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='HRQ2LYHQWD"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].HRQ2LYHQWD+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_115"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_115+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='FIT_102"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT_102+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_103"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_103+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_107"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_107+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='HRQ3LYHQWD"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].HRQ3LYHQWD+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='FIT_103"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT_103+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_112"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_112+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_104"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_104+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_108"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_108+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='HRQ4LYHQWD"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].HRQ4LYHQWD+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TT_112"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TT_112+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='FIT_104"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT_104+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TE_2102"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TE_2102+"'/></td>"+
																"	  <td class=xl1128439 style='border-top:none;border-left:none'><input id='FIT_2101"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT_2101+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TRC_3301"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TRC_3301+"'/></td>"+
																"	  <td class=xl1128439 style='border-top:none;border-left:none'><input id='FIT_2102"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT_2102+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TE_2113"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TE_2113+"'/></td>"+
																"	  <td class=xl1128439 style='border-top:none;border-left:none'><input id='FIT_2105"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT_2105+"'/></td>"+
																"	  <td class=xl1118439 style='border-top:none;border-left:none'><input id='TE_2114"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TE_2114+"'/></td>"+
																"	  <td class=xl1128439 style='border-top:none;border-left:none'><input id='FIT_2106"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FIT_2106+"'/></td>"+
																"	  <td class=xl1108439 width=72 style='border-top:none;border-left:none;width:54pt'><input id='GZQYL"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].GZQYL+"'/></td>"+
																"	  <td class=xl838439 width=72 style='border-top:none;border-left:none; width:54pt'><input id='GZQWD"+i+"'  type='text' "+disStr+" style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].GZQWD+"'/></td>"+
																"	  <td class=xl1028439 style='border-top:none;border-left:none'>　</td>"+
																"	  <td class=xl1028439 style='border-top:none;border-left:none'>　</td>"+
																"	  <td class=xl1038439 style='border-top:none;border-left:none'>　</td>"+
																"	  <td class=xl1048439 style='border-top:none'>　</td>"+
																"	 </tr>";
														}

														tableval +=
															"<tr height=29 style='mso-height-source:userset;height:21.95pt'>"+
															"  <td height=29 class=xl1348439 width=72 style='height:21.95pt;border-top:none;width:54pt'>备注：</td>"+
															"  <td colspan=50 class=xl2128439 width=3600 style='border-right:1.0pt solid black;width:2700pt'><input id='REMARK' type='text' style='background:transparent;border:0px solid;width:3600px;line-height:25px;height:30px;font-size: 12pt;text-align:left;' value='"+nongduArr[1].REMARK+"'/></td>"+
															" </tr>"+
															"</table>";

													
												$("#tablearea").html(tableval);
									    
									    
									    
										
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
        	var	ZBR = $("#ZBR").val();
 			var	REMARK = $("#REMARK").val();
 			var ZYGZ = $("#ZYGZ").val();
 			var	RQ = $("#RQ").text();
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行修改");
         	}else{
             	var firstArr = [];
             	var firstStr = "";
             	for(var i=0;i<7;i++){
             		firstArr.push($("#RD_U1_SAGD_PIVOTAL_ID"+i).val());
             		firstArr.push($("#REPORT_TIME"+i).text());
             		firstArr.push($("#YJCSLJ"+i).val());
             		firstArr.push($("#RHXCSLJ"+i).val());	
             		firstArr.push($("#RHX1JYLJ"+i).val());	
             		firstArr.push($("#RHX2JYLJ"+i).val());	
             		firstArr.push($("#RHX3JYLJ"+i).val());	
             		firstArr.push($("#ZCSL"+i).val());	
             		firstArr.push($("#BHL"+i).val());	
             		firstArr.push($("#DTCYLJ"+i).val());	
             		firstArr.push($("#DTCYLL"+i).val());	
             		firstArr.push($("#XXLLJLJ"+i).val());
             		firstArr.push($("#XXLLJLL"+i).val());
             		firstArr.push($("#CCLLJ"+i).val());		
             		firstArr.push($("#SXCSHY"+i).val());
             		firstArr.push($("#DTCSHY"+i).val());
             		
             		firstStr += arrayToString(firstArr,",");
             		firstStr += ";";
             		firstArr = [];
             	}

             	var secondArr = [];
             	var secondStr = "";
             	for(var i=0;i<13;i++){
             		secondArr.push($("#RD_U1_SAGD_AUTO_ID"+i).val());
             		secondArr.push($("#REPORT_TIME_2"+i).text());
             		secondArr.push($("#YCLJG1YW"+i).val());
             		secondArr.push($("#YCLJG2YW"+i).val());	
             		secondArr.push($("#YCLJJYL"+i).val());	
             		secondArr.push($("#ZXPRJG3YW"+i).val());	
             		secondArr.push($("#ZXPRJG4YW"+i).val());	
             		secondArr.push($("#ZXPRJJYL"+i).val());	
             		secondArr.push($("#YCLG5YW"+i).val());	
             		secondArr.push($("#YCLG5JYL"+i).val());	
             		secondArr.push($("#YCLG6YW"+i).val());	
             		secondArr.push($("#YCLG6JYL"+i).val());
             		secondArr.push($("#JYB1CKYL"+i).val());
             		secondArr.push($("#JYB1KD"+i).val());		
             		secondArr.push($("#JYB2CKYL"+i).val());
             		secondArr.push($("#JYB2KD"+i).val());
             		secondArr.push($("#JYB3CKYL"+i).val());
             		secondArr.push($("#JYB3KD"+i).val());	
             		secondArr.push($("#JYB4CKYL"+i).val());	
             		secondArr.push($("#JYB4KD"+i).val());	
             		secondArr.push($("#CCBCKYL"+i).val());	
             		secondArr.push($("#CCBPL"+i).val());	
             		secondArr.push($("#PR_3902"+i).val());	
             		secondArr.push($("#LRC_2101A"+i).val());	
             		secondArr.push($("#PT_2112"+i).val());	
             		secondArr.push($("#LRC_2101B"+i).val());
             		secondArr.push($("#PRC_3FLQ"+i).val());
             		secondArr.push($("#LRC_3FLQ"+i).val());		
             		secondArr.push($("#TR_3101A"+i).val());
             		secondArr.push($("#LIT_3101A"+i).val());
             		secondArr.push($("#PR_3101A"+i).val());
             		secondArr.push($("#TR_3101B"+i).val());	
             		secondArr.push($("#LIT_3101B"+i).val());	
             		secondArr.push($("#PR_3101B"+i).val());	
             		secondArr.push($("#TR_3101C"+i).val());	
             		secondArr.push($("#LIT_3101C"+i).val());	
             		secondArr.push($("#PR_3101C"+i).val());	
             		secondArr.push($("#LRC_3202D"+i).val());	
             		secondArr.push($("#PRA_3201D"+i).val());	
             		secondArr.push($("#TR_3201D"+i).val());
             		secondArr.push($("#PRC_3703"+i).val());
             		secondArr.push($("#PRC_3702"+i).val());		
             		secondArr.push($("#PRC_3706"+i).val());
             		secondArr.push($("#PRC_3704"+i).val());
             		secondArr.push($("#PRC_3705"+i).val());
             		secondArr.push($("#PRC_3707"+i).val());	
             		secondArr.push($("#YSJ_P1"+i).val());	
             		secondArr.push($("#YSJ_T1"+i).val());	
             		secondArr.push($("#YSJ_P2"+i).val());	
             		secondArr.push($("#YSJ_T2"+i).val());	
             		secondArr.push($("#LRC_3201A"+i).val());	
             		secondArr.push($("#LRC_3202A"+i).val());	
             		secondArr.push($("#LRC_3203A"+i).val());	
             		secondArr.push($("#PR_3201A"+i).val());
             		secondArr.push($("#TR_3201A"+i).val());
             		secondArr.push($("#LRC_3201B"+i).val());		
             		secondArr.push($("#LRC_3202B"+i).val());
             		secondArr.push($("#LRC_3203B"+i).val());
             		secondArr.push($("#PR_3201B"+i).val());
             		secondArr.push($("#TR_3201B"+i).val());	
             		secondArr.push($("#LRC_3201C"+i).val());	
             		secondArr.push($("#LRC_3202C"+i).val());	
             		secondArr.push($("#LRC_3203C"+i).val());	
             		secondArr.push($("#PR_3201C"+i).val());	
             		secondArr.push($("#TR_3201C"+i).val());	
             		secondArr.push($("#TT_102"+i).val());	
             		secondArr.push($("#TT_105"+i).val());	
             		secondArr.push($("#HRQ1LYHQWD"+i).val());
             		secondArr.push($("#TT_115"+i).val());
             		secondArr.push($("#FIT_101"+i).val());		
             		secondArr.push($("#TT_101"+i).val());
             		secondArr.push($("#TT_106"+i).val());
             		secondArr.push($("#HRQ2LYHQWD"+i).val());
             		secondArr.push($("#TT_115"+i).val());
             		secondArr.push($("#FIT_102"+i).val());
             		secondArr.push($("#TT_103"+i).val());
             		secondArr.push($("#TT_107"+i).val());
             		secondArr.push($("#HRQ3LYHQWD"+i).val());
             		secondArr.push($("#FIT_103"+i).val());
             		secondArr.push($("#TT_112"+i).val());
             		secondArr.push($("#TT_104"+i).val());
             		secondArr.push($("#TT_108"+i).val());
             		secondArr.push($("#HRQ4LYHQWD"+i).val());
             		secondArr.push($("#TT_112"+i).val());
             		secondArr.push($("#FIT_104"+i).val());
             		secondArr.push($("#TE_2102"+i).val());
             		secondArr.push($("#FIT_2101"+i).val());
             		secondArr.push($("#TRC_3301"+i).val());
             		secondArr.push($("#FIT_2102"+i).val());
             		secondArr.push($("#TE_2113"+i).val());
             		secondArr.push($("#FIT_2105"+i).val());
             		secondArr.push($("#TE_2114"+i).val());
             		secondArr.push($("#FIT_2106"+i).val());
             		secondArr.push($("#GZQYL"+i).val());
             		secondArr.push($("#GZQWD"+i).val());
             		
             		secondStr += arrayToString(secondArr,",");
             		secondStr += ";";
             		secondArr = [];
             	}

             	//流量
             	var RD_U1_SAGD_LIQUID_ID3 = $('#RD_U1_SAGD_LIQUID_ID3').val();
             	var RD_U1_SAGD_LIQUID_ID4 = $('#RD_U1_SAGD_LIQUID_ID4').val();
             	var RD_U1_SAGD_LIQUID_ID5 = $('#RD_U1_SAGD_LIQUID_ID5').val();
             	
             	var ZYL16 = $('#ZYL16').val();
				var SXCS16 = $('#SXCS16').val();
				var RHXCS16 = $('#RHXCS16').val();
				var DTCYL16 = $('#DTCYL16').val();

				var ZYL20 = $('#ZYL20').val();
				var SXCS20 = $('#SXCS20').val();
				var RHXCS20 = $('#RHXCS20').val();
				var DTCYL20 = $('#DTCYL20').val();

				var ZYL8 = $('#ZYL8').val();
				var SXCS8 = $('#SXCS8').val();
				var RHXCS8 = $('#RHXCS8').val();
				var DTCYL8 = $('#DTCYL8').val();

             	//浓度
             	var RD_U1_SAGD_GENERAL_ID = $('#RD_U1_SAGD_GENERAL_ID').val();
             	var RCCYL = $('#RCCYL').val();
             	var YCLJ = $('#YCLJ').val();
             	var ZXPRJ = $('#ZXPRJ').val();
             	

 				if (modifyDataFlag($("#txtDate").val())) {
 	        	
 					jQuery.ajax({
 									type : 'post',
 									url : 'mbzhrb_updateZHRB.action?nowdata='+parseInt(Math.random()*100000),
 									data: {"firstStr":firstStr,"secondStr":secondStr,
										"ZBR":ZBR,"REMARK":REMARK,"RQ":RQ,"ZYGZ":ZYGZ,
										"RD_U1_SAGD_LIQUID_ID3":RD_U1_SAGD_LIQUID_ID3,"RD_U1_SAGD_LIQUID_ID4":RD_U1_SAGD_LIQUID_ID4,"RD_U1_SAGD_LIQUID_ID5":RD_U1_SAGD_LIQUID_ID5,
				 						"ZYL16":ZYL16,"ZYL20":ZYL20,"ZYL8":ZYL8,"SXCS16":SXCS16,"SXCS20":SXCS20,"SXCS8":SXCS8,"RHXCS16":RHXCS16,"RHXCS20":RHXCS20,"RHXCS8":RHXCS8,"DTCYL16":DTCYL16,"DTCYL20":DTCYL20,"DTCYL8":DTCYL8,
				 						"RD_U1_SAGD_GENERAL_ID":RD_U1_SAGD_GENERAL_ID,"RCCYL":RCCYL,"YCLJ":YCLJ,"ZXPRJ":ZXPRJ
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
 				} else {
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
 					url : 'mbzhrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"RD_U1_SAGD_GENERAL_ID":RD_U1_SAGD_GENERAL_ID},
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
		var reportDate=$("#txtDate").val();
     		var url = "mbzhrb_exportData.action?reportDate="+encodeURIComponent(reportDate);
     		//if (onSearchByDate(reportDate)) {
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    				}
    			});
    		//} else {
    		//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
    		//}
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
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
       <!--table
			{mso-displayed-decimal-separator:"\.";
			mso-displayed-thousand-separator:"\,";}
		@page
			{margin:.2in .2in .2in .2in;
			mso-header-margin:0in;
			mso-footer-margin:0in;
			mso-page-orientation:landscape;}
		ruby
			{ruby-align:left;}
		rt
			{color:windowtext;
			font-size:9.0pt;
			font-weight:400;
			font-style:normal;
			text-decoration:none;
			font-family:宋体;
			mso-generic-font-family:auto;
			mso-font-charset:134;
			mso-char-type:none;
			display:none;}
		-->

    </style>

</head>
<body style="padding:10px"> 
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
<!--  -->
<div id="tablearea" style="OVERFLOW:auto; width: 99%;height: 89%;" align="center">

</div>

     
  </form>
    
</body>
</html>
