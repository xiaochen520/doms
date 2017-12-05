<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- <html xmlns="http://www.w3.org/1999/xhtml"> -->
<html xmlns:o="urn:schemas-microsoft-com:office:office"
	xmlns:x="urn:schemas-microsoft-com:office:excel"
	xmlns="http://www.w3.org/TR/REC-html40">
	<head>
		<title>掺柴油报表</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0"></meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>

		<meta name=ProgId content=Excel.Sheet />
		<meta name=Generator content="Microsoft Excel 14" />
		<link rel=File-List href="file8632.files/filelist.xml" />
		<link href="../../lib/css/u1czy.css" rel="stylesheet" type="text/css" />

		<link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
			rel="stylesheet" type="text/css" />
		<script src="../../lib/jquery/jquery-1.5.2.min.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>

		<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerGrid.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerForm.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerButton.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerDialog.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerDrag.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerRadio.js"
			type="text/javascript"></script>

		<script src="../../lib/ligerUI/js/plugins/ligerTextBox.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerTip.js"
			type="text/javascript"></script>
		<script src="../../lib/jquery-validation/jquery.validate.min.js"
			type="text/javascript"></script>
		<script src="../../lib/jquery-validation/jquery.metadata.js"
			type="text/javascript"></script>
		<script src="../../lib/jquery-validation/messages_cn.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerPopupEdit.js"></script>

		<script src="../../lib/ligerUI/js/plugins/ligerToolBar.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerResizable.js"
			type="text/javascript"></script>
		<!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->
		<link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />
		<script src="../../lib/js/common.js" type="text/javascript"></script>


		<script src="../../lib/json2.js" type="text/javascript"></script>
		<script src="../../noBackspace.js" type="text/javascript"></script>
		<script src="../../lib/myday.js" type="text/javascript"></script>
		<script src="../../lib/sagd.js" type="text/javascript"></script>
		<script src="../../lib/checkDate.js" type="text/javascript"></script>
		<script src="../../lib/U2_check.js" type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js"
			type="text/javascript"></script>
		<script language="javascript" type="text/javascript"
			src="../../lib/WdatePicker.js"></script>
		<script type="text/javascript">
    var  RPDREPORTMESSAGEID ="";
	var  SHR ="";
    var tableHtml;
    var mod ;
        $(function () {
        	$("#reportDate").ligerDateEditor(
                    {
                        format: "yyyy-MM-dd",
                        labelWidth: 100,
                        labelAlign: 'center',
                        cancelable : false
                }).setValue(myDate().Format("yyyy-MM-dd"));

			//获取报表及功能按钮ｊｓ
           	
            jQuery.ajax({
				type : 'post',
				url : 'u1czy_pageInit.action',
				success : function(data) { 
				
					if (data != null && typeof(data)!="undefined"&& data!=""){
						var outData = eval('(' + data + ')');
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

        function changValue(){

			var ghjxtccljTHsum = 0;
			var tsbccljTHsum = 0;
			var wyccljTHsum = 0;
			var bbclzljTHsum = 0;
			var yqccccljTHsum = 0;
            for(var i=1;i<13;i++){
                if($('#ghjxtcclj'+i).val()!="" && $('#ghjxtcclj'+(i-1)).val()!="")
                	document.getElementById('ghjxtccljTH'+i).value = Number($('#ghjxtcclj'+i).val()-$('#ghjxtcclj'+(i-1)).val()).toFixed(2);

                ghjxtccljTHsum += Number($("#ghjxtccljTH"+i).val());
            }
            $('#ghjxtccljTHsum').html(Number(ghjxtccljTHsum).toFixed(2));
            for(var i=1;i<13;i++){
                if($('#tsbcclj'+i).val()!="" && $('#tsbcclj'+(i-1)).val()!="")
                	document.getElementById('tsbccljTH'+i).value = Number($('#tsbcclj'+i).val()-$('#tsbcclj'+(i-1)).val()).toFixed(2);
                tsbccljTHsum += Number($("#tsbccljTH"+i).val());
            }
            $('#tsbccljTHsum').html(Number(tsbccljTHsum).toFixed(2));
            for(var i=1;i<13;i++){
                if($('#wycclj'+i).val()!="" && $('#wycclj'+(i-1)).val()!="")
                	document.getElementById('wyccljTH'+i).value = Number($('#wycclj'+i).val()-$('#wycclj'+(i-1)).val()).toFixed(2);
                wyccljTHsum += Number($("#wyccljTH"+i).val());
            }
            $('#wyccljTHsum').html(Number(wyccljTHsum).toFixed(2));
            for(var i=1;i<13;i++){
                if($('#bbclzlj'+i).val()!="" && $('#bbclzlj'+(i-1)).val()!="")
                	document.getElementById('bbclzljTH'+i).value = Number($('#bbclzlj'+i).val()-$('#bbclzlj'+(i-1)).val()).toFixed(2);
                bbclzljTHsum += Number($("#bbclzljTH"+i).val());
            }
            $('#bbclzljTHsum').html(Number(bbclzljTHsum).toFixed(2));
            for(var i=1;i<13;i++){
                if($('#yqcccclj'+i).val()!="" && $('#yqcccclj'+(i-1)).val()!="")
                	document.getElementById('yqccccljTH'+i).value = Number($('#yqcccclj'+i).val()-$('#yqcccclj'+(i-1)).val()).toFixed(2);
                yqccccljTHsum += Number($("#yqccccljTH"+i).val());
            }
            $('#yqccccljTHsum').html(Number(yqccccljTHsum).toFixed(2));



			var CZYSUMTHsum = 0.00;
			var CZYSUMsum = 0.00;
            for(var i=1;i<13;i++){
            	document.getElementById('CZYSUMTH'+i).value = (Number($("#ghjxtccljTH"+i).val())+Number($("#tsbccljTH"+i).val())+Number($("#wyccljTH"+i).val())+Number($("#bbclzljTH"+i).val())).toFixed(2);
            	CZYSUMTHsum += Number($("#CZYSUMTH"+i).val());
            	document.getElementById('CZYSUM'+i).value = (Number($("#CZYSUMTH"+i).val())*0.88).toFixed(2);
            }
            $('#CZYSUMTHsum').html(Number(CZYSUMTHsum).toFixed(2));
            $('#CZYSUMsum').html((Number(CZYSUMTHsum)*0.88).toFixed(2));
            
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
        	//if (onSearchByDate($("#reportDate").val())){
          	 jQuery.ajax({
    			type : 'post',
    			url : 'u1czy_searchU1CZY.action?reportDate='+$("#reportDate").val(),
    			success : function(data) { 
    			var  ZBR1 ="";
    			var  RQ ="";
    			var  BZ ="";
    				if (data != null && typeof(data)!="undefined"&& data!=""){
    					var outData = eval('(' + data + ')');
    					if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
    						$.ligerDialog.error(outData.ERRMSG);
    					}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
    						$.ligerDialog.error(outerror(outData.ERRCODE));
    					}else{
    						/*if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
							    var tableStr = outData.JSONDATA.tableStr;
							    var cssStr = outData.JSONDATA.cssStr;

							    $("#tableHtml").html(tableStr);
							   // document.write('<style type="text\/css">' + cssStr + '<\/style>'); 
							    document.styleSheets[0].value= cssStr;
    						}*/
        						
    							if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
    							    var U1CZY = outData.JSONDATA.U1CZY;
    							    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
    							    if (U1CZY != null && typeof(U1CZY)!="undefined"){
    							    	if(REPORTMESSAGE != null && typeof(REPORTMESSAGE)!="undefined"){
    							     		if(REPORTMESSAGE.RPDREPORTMESSAGEID != null && typeof(REPORTMESSAGE.RPDREPORTMESSAGEID)!="undefined"){
    							     				RPDREPORTMESSAGEID =REPORTMESSAGE.RPDREPORTMESSAGEID;	
    							     			}else{
    							     				RPDREPORTMESSAGEID ="";
    							     			}
    							     		 if(REPORTMESSAGE.ZBR1 != null && typeof(REPORTMESSAGE.ZBR1)!="undefined"){
    							     			 ZBR1 =REPORTMESSAGE.ZBR1;	
    							     			}else{
    							     				ZBR1 = "";
    							     			}
    							     		if(REPORTMESSAGE.ZBR3 != null && typeof(REPORTMESSAGE.ZBR3)!="undefined"){
	   							     			 ZBR3 =REPORTMESSAGE.ZBR3;	
	   							     			}else{
	   							     				ZBR3 = "";
	   							     			}
    										if(REPORTMESSAGE.SHR != null && typeof(REPORTMESSAGE.SHR)!="undefined"){
    							     			 SHR =REPORTMESSAGE.SHR;
    							     			}else{
    							     				SHR = "";
    							     			}
    										 if(REPORTMESSAGE.RQ != null && typeof(REPORTMESSAGE.RQ)!="undefined"){
    							     			  RQ =REPORTMESSAGE.RQ;
    							     			}else{
    							     				RQ = "";
    							     			}
    										if(REPORTMESSAGE.BZ != null && typeof(REPORTMESSAGE.BZ)!="undefined"){
    							     			  BZ =REPORTMESSAGE.BZ;
    							     			}else{
    							     				BZ = "";
    							     			}
    										 //alert(RPDREPORTMESSAGEID);
    							     	}else{
    							     		RPDREPORTMESSAGEID ="";
    							     	}
    					    		$("#tableHtml").html('');
    					    		tableHtml = "<table border=0 cellpadding=0 cellspacing=0 width=1152 style='border-collapse:collapse;table-layout:fixed;width:871pt'>"+
    					    			" <col width=60 style='mso-width-source:userset;mso-width-alt:1920;width:45pt'>"+
    					    			" <col width=78 span=14 style='mso-width-source:userset;mso-width-alt:2496;width:59pt'>"+
    					    			" <tr height=48 style='mso-height-source:userset;height:36.0pt'>"+
    					    			"  <td colspan=15 height=48 class=xl8320422 width=1152 style='height:36.0pt;width:871pt;text-align:center'><font class='font620422'>一号稠油联合处理站掺柴油报表</font></td>"+
    					    			" </tr>"+
    					    			" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
    					    			"  <td height=24 class=xl8420422 style='height:18.0pt;border-top:none'>日期：<span style='mso-spacerun:yes'>&nbsp;</span></td>"+
    					    			"  <td id='rq' colspan=2 class=xl8520422 style='border-left:none'>"+RQ+"</td>"+
    					    			"  <td class=xl8420422 style='border-top:none;border-left:none'>值班人：</td>"+
    					    			"  <td colspan=3 class=xl8520422 style='border-left:none'><input id='zbr1' type='text' style='background:transparent;border:0px solid;width:233px;height:20px;font-size: 10pt;' value='"+ZBR1+"'/></td>"+
    					    			"  <td colspan=2 class=xl8620422 width=156 style='border-left:none;width:118pt'>储运值班人：</td>"+
    					    			"  <td colspan=3 class=xl8720422 width=234 style='border-left:none;width:177pt'><input id='zbr3' type='text' style='background:transparent;border:0px solid;width:233px;height:20px;font-size: 10pt;' value='"+ZBR3+"'/></td>"+
    					    			"  <td class=xl8420422 style='border-top:none;border-left:none'>审核：</td>"+
    					    			"  <td colspan=2 class=xl8520422 style='border-left:none'>"+SHR+"</td>"+
    					    			" </tr>"+
    					    			" <tr class=xl6520422 height=24 style='mso-height-source:userset;height:18.0pt'>"+
    					    			"  <td colspan=11 height=24 class=xl7720422 width=840 style='border-right:.5pt solid black;height:18.0pt;width:635pt;text-align:center'>一号稠油处理站</td>"+
    					    			"  <td colspan=4 class=xl8020422 width=312 style='border-right:.5pt solid black; border-left:none;width:236pt'>油气储运</td>"+
    					    			" </tr>"+
    					    			" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
    					    			"  <td rowspan=2 height=48 class=xl6620422 width=60 style='height:36.0pt;border-top:none;width:45pt'>时间</td>"+
    					    			"  <td colspan=2 class=xl6620422 width=156 style='border-left:none;width:118pt'>管汇间系统掺柴</td>"+
    					    			"  <td colspan=2 class=xl6620422 width=156 style='border-left:none;width:118pt'>提升泵掺柴</td>"+
    					    			"  <td colspan=2 class=xl6620422 width=156 style='border-left:none;width:118pt'>污油掺柴</td>"+
    					    			"  <td colspan=2 class=xl6620422 width=156 style='border-left:none;width:118pt'>30万吨密闭处理站</td>"+
    					    			"  <td rowspan=2 class=xl6620422 width=78 style='border-top:none;width:59pt'>掺柴总量（m<font class='font520422'><sup>3</sup></font><font class='font020422'>）</font></td>"+
    					    			"  <td rowspan=2 class=xl6620422 width=78 style='border-top:none;width:59pt'>掺柴总量（t）</td>"+
    					    			"  <td rowspan=2 class=xl7320422 width=78 style='width:59pt'>柴油罐液位(m<font class='font1020422'><sup>3</sup></font><font class='font920422'>)</font></td>"+
    					    			"  <td rowspan=2 class=xl7320422 width=78 style='width:59pt'>掺柴流量计量读数</td>"+
    					    			"  <td rowspan=2 class=xl7420422 width=78 style='width:59pt'>瞬时流量</td>"+
    					    			"  <td rowspan=2 class=xl7520422 width=78 style='width:59pt'>2小时流量</td>"+
    					    			" </tr>"+
    					    			" <tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
    					    			"  <td height=24 class=xl6620422 width=78 style='height:18.0pt;border-top:none;border-left:none;width:59pt'>累计值</td>"+
    					    			"  <td class=xl6620422 width=78 style='border-top:none;border-left:none; width:59pt'>2小时流量</td>"+
    					    			"  <td class=xl6620422 width=78 style='border-top:none;border-left:none; width:59pt'>累计值</td>"+
    					    			"  <td class=xl6620422 width=78 style='border-top:none;border-left:none;width:59pt'>2小时流量</td>"+
    					    			"  <td class=xl6620422 width=78 style='border-top:none;border-left:none;width:59pt'>累计值</td>"+
    					    			"  <td class=xl6620422 width=78 style='border-top:none;border-left:none;width:59pt'>2小时流量</td>"+
    					    			"  <td class=xl6620422 width=78 style='border-top:none;border-left:none;width:59pt'>累计值</td>"+
    					    			"  <td class=xl6620422 width=78 style='border-top:none;border-left:none;width:59pt'>2小时流量</td>"+
    					    			" </tr>";
    					    			var disStr="";
										if(mod==1){
											disStr = "onclick='isChange(this)'";
										}	
    									 for(var i=0; i<U1CZY.length; i++) {
        									 if(i<U1CZY.length-1){
        										 tableHtml += "<tr class=xl6520422 height=24 style='mso-height-source:userset;height:18.0pt'>"+
        										 	"  <td style='display: none'><input id='RPD_U1_OIL_AUTO_ID"+i+"' type='hidden' value='"+U1CZY[i].RPD_U1_OIL_AUTO_ID+"'></td>"+
        											"  <td id='REPORT_TIME"+i+"' height=24 class=xl6820422 width=60 style='height:18.0pt;border-top:none; width:45pt'>"+U1CZY[i].REPORT_TIME+"</td>"+
        											"	  <td class=xl7120422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='ghjxtcclj"+i+"'  type='text' "+disStr+" onblur='changValue()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].ghjxtcclj+"'/></td>"+
        											"	  <td class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='ghjxtccljTH"+i+"' type='text' disabled='disabled' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].ghjxtccljTH+"'/></td>"+
        											"	  <td class=xl7120422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='tsbcclj"+i+"' type='text' "+disStr+" onblur='changValue()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].tsbcclj+"'/></td>"+
        											"	  <td class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='tsbccljTH"+i+"' type='text' disabled='disabled' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].tsbccljTH+"'/></td>"+
        											"	  <td class=xl7120422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='wycclj"+i+"' type='text' "+disStr+" onblur='changValue()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].wycclj+"'/></td>"+
        											"	  <td class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='wyccljTH"+i+"' type='text' disabled='disabled' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].wyccljTH+"'/></td>"+
        											"	  <td class=xl7120422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='bbclzlj"+i+"' type='text' "+disStr+" onblur='changValue()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].bbclzlj+"'/></td>"+
        											"	  <td class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='bbclzljTH"+i+"' type='text' disabled='disabled' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].bbclzlj+"'/></td>"+
        											"	  <td class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='CZYSUMTH"+i+"' type='text' disabled='disabled' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].CZYSUMTH+"'/></td>"+
        											"	  <td class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='CZYSUM"+i+"' type='text' disabled='disabled' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].CZYSUM+"'/></td>"+
        											"	  <td class=xl7020422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='yqcyccgyw"+i+"' type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].yqcyccgyw+"'/></td>"+
        											"	  <td class=xl7020422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='yqcccclj"+i+"' type='text' "+disStr+" onblur='changValue()' onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].yqcccclj+"'/></td>"+
        											"	  <td class=xl6920422 width=78 style='border-top:none;border-left:none;width:59pt'><input id='yqccccll"+i+"' type='text' "+disStr+" onkeyup='checkData(this)' onblur='checkData2p(this)' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].yqccccll+"'/></td>"+
        											"	  <td class=xl7020422 width=78 style='border-top:none;width:59pt'><input id='yqccccljTH"+i+"' type='text' disabled='disabled' style='background:transparent;border:0px solid;width:75px;height:20px;font-size: 10pt;text-align:center;' value='"+U1CZY[i].yqccccljTH+"'/></td>"+
        											"	 </tr>";
        									 }else{
        										 tableHtml += "<tr class=xl6520422 height=24 style='mso-height-source:userset;height:18.0pt'>"+
     											"  <td height=24 class=xl6820422 width=60 style='height:18.0pt;border-top:none; width:45pt'>日累计</td>"+
     											"	  <td class=xl7120422 width=78 style='border-top:none;border-left:none;width:59pt'></td>"+
     											"	  <td id='ghjxtccljTHsum' class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'>"+U1CZY[i].ghjxtccljTH+"</td>"+
     											"	  <td class=xl7120422 width=78 style='border-top:none;border-left:none;width:59pt'></td>"+
     											"	  <td id='tsbccljTHsum' class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'>"+U1CZY[i].tsbccljTH+"</td>"+
     											"	  <td class=xl7120422 width=78 style='border-top:none;border-left:none;width:59pt'></td>"+
     											"	  <td id='wyccljTHsum' class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'>"+U1CZY[i].wyccljTH+"</td>"+
     											"	  <td class=xl7120422 width=78 style='border-top:none;border-left:none;width:59pt'></td>"+
     											"	  <td id='bbclzljTHsum' class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'>"+U1CZY[i].bbclzljTH+"</td>"+
     											"	  <td id='CZYSUMTHsum' class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'>"+U1CZY[i].CZYSUMTH+"</td>"+
     											"	  <td id='CZYSUMsum' class=xl6720422 width=78 style='border-top:none;border-left:none;width:59pt'>"+U1CZY[i].CZYSUM+"</td>"+
     											"	  <td class=xl7020422 width=78 style='border-top:none;border-left:none;width:59pt'></td>"+
     											"	  <td class=xl7020422 width=78 style='border-top:none;border-left:none;width:59pt'></td>"+
     											"	  <td class=xl6920422 width=78 style='border-top:none;border-left:none;width:59pt'></td>"+
     											"	  <td id='yqccccljTHsum' class=xl7020422 width=78 style='border-top:none;width:59pt'>"+U1CZY[i].yqccccljTH+"</td>"+
     											"	 </tr>";
        									 }
    										 
    									 }
    								 tableHtml += "<tr height=80 style='mso-height-source:userset;height:60.0pt'>"+
	    									  	"	<td height=80 class=xl7220422 width=60 style='height:60.0pt;border-top:none;width:45pt'>备注：</td>"+
	    										" 	<td colspan=14 class=xl6620422 width=1092 style='border-left:none;width:826pt'><input id='bz' type='text' style='background:transparent;border:0px solid;width:1090px;height:75px;font-size: 10pt;' value='"+BZ+"'/></td>"+
    										 	"  </tr>"+
    											"</table>";
    									 $("#tableHtml").html(tableHtml);		
    							    }else{
    									$.ligerDialog.error("当前日期无数据，请选择其他日期！");
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
        	//}
        	//else {
        	//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
        	//}
     	}
        
        //为公用页面提供接口方法 
	function onJKSubmit(id,name,datatype,basid){
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
		var	ZBR1 = $("#zbr1").val();
		var	ZBR3 = $("#zbr3").val();
		var	BZ = $("#bz").val();
		var	RQ = $("#rq").text();

		if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
    		$.ligerDialog.error("数据已审核过，不能进行修改");
    	}else{
    		var firstArr = [];
         	var firstStr = "";
         	for(var i=0;i<13;i++){
         		firstArr.push($("#RPD_U1_OIL_AUTO_ID"+i).val());
         		firstArr.push($("#REPORT_TIME"+i).text());
         		firstArr.push($("#ghjxtcclj"+i).val());
         		firstArr.push($("#tsbcclj"+i).val());	
         		firstArr.push($("#wycclj"+i).val());	
         		firstArr.push($("#bbclzlj"+i).val());	
         		firstArr.push($("#yqcyccgyw"+i).val());	
         		firstArr.push($("#yqcccclj"+i).val());
         		firstArr.push($("#yqccccll"+i).val());
         		
         		firstStr += arrayToString(firstArr,",");
         		firstStr += ";";
         		firstArr = [];
         	}
         	
    		if (modifyDataFlag($("#reportDate").val())) {
    			jQuery.ajax({
    				type : 'post',
    				url : 'u1czy_updateU1CZY.action?nowdata='+parseInt(Math.random()*100000),
    				data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"SHR":SHR,"ZBR1":ZBR1,"ZBR3":ZBR3,"BZ":BZ,"RQ":RQ,"firstStr":firstStr},
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
	function onAduit()
	{
		if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
    		$.ligerDialog.error("数据已审核过，不能进行再次审核");
    	}else{
    		if (chekAduitByDate($("#reportDate").val())) {
    			jQuery.ajax({
    				type : 'post',
    				url : 'u1czy_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
		var reportDate=$("#reportDate").val();
		var url = "u1czy_exportU2_WATER.action?reportDate="+encodeURIComponent(reportDate) + '&reportName='+encodeURIComponent('水处理加药系统日报表');
		//if (onSearchByDate(reportDate)) {
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
html,body {
	font-size: 12px;
	margin: 0px;
	padding: 0px;
	background: #FAFCFC;
	position: absolute;
	width: 100%;
	height: 100%;
	hoverflow: scroll;
	overflow-y: hidden;
	overflow-x: hidden;
}

/* 搜索框 */
.searchtitle {
	padding-left: 28px;
	position: relative;
}

.searchtitle img {
	width: 22px;
	height: 22px;
	position: absolute;
	left: 0;
	top: 0;
}

.searchtitle span {
	font-size: 14px;
	font-weight: bold;
}

.searchtitle .togglebtn {
	position: absolute;
	top: 6px;
	right: 15px;
	background: url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px
		0px;
	height: 10px;
	width: 9px;
	cursor: pointer;
}

.searchtitle .togglebtn-down {
	background-position: 0px -10px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 100px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

#errorLabelContainer {
	padding: 10px;
	width: 300px;
	border: 1px solid #FF4466;
	display: none;
	background: #FFEEEE;
	color: Red;
}
</style>

	</head>

	<body style="overflow-x: hidden; padding: 2px;">
		<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

		<form name="formsearch" method="post" id="form1">
			<table border="0" cellspacing="1">
				<tr>
					<td align="right" class="l-table-edit-td" style="width: 40px">
						日期：
					</td>
					<td>
						<input type="text" id="reportDate" ltype="date" />
					</td>
					<td style="padding-left: 20px;">
						<a href="javascript:onSubmit()" class="l-button"
							style="width: 80px">查询</a>
					</td>
					<td style="padding-left: 20px;">
						<a href="javascript:onExport()" class="l-button" id="dayreport"
							style="width: 80px;">导出报表</a>
					</td>

					<td style="padding-left: 20px;">
						<a href="javascript:onSave()" class="l-button"
							style="width: 100px; display: none" id="onSaveid">保存</a>
					</td>
					<td style="padding-left: 20px;">
						<a href="javascript:onAduit()" class="l-button"
							style="width: 100px; display: none" id="onAuditid">审核</a>
					</td>

				</tr>
			</table>
			<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>
			<div id="tableHtml" x:publishsource="Excel"
				style="OVERFLOW: auto; width: 99%; height: 87%;">

			</div>
		</form>
	</body>
</html>