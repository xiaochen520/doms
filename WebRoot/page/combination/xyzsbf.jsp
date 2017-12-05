<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>注水泵房</title>
   
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
 	
 	<link href="../../lib/css/pump.css" rel="stylesheet" type="text/css" />  
 	
    <script type="text/javascript">
    var tableval;
	var  ZBR1 ="";
	var  SHR ="";
	var  RQ ="";
	var  BZ ="";
	var  RPDREPORTMESSAGEID ="";
	var li_1_jhg="";
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
				url : 'zpump_pageInit.action',
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
            var ft_dx_add0 = $('#ft_dx_add0').val(); //东线流量
            var ft_dx_add12 = $('#ft_dx_add12').val();

            var ft_xx_add0 = $('#ft_xx_add0').val();
            var ft_xx_add12 = $('#ft_xx_add12').val();
            
            var ft_zsq10 = $('#ft_zsq10').val();
            var ft_zsq112 = $('#ft_zsq112').val();

            var wsbxfwlj0 = $('#wsbxfwlj0').val();
            var wsbxfwlj12 = $('#wsbxfwlj12').val();

            if(ft_dx_add0!="" && ft_dx_add12!=""){
            	document.getElementById('ft_dx_add13').value = (ft_dx_add12-ft_dx_add0);
            }
            if(ft_xx_add0!="" && ft_xx_add12!=""){
            	document.getElementById('ft_xx_add13').value = (ft_xx_add12-ft_xx_add0);
            }
            if(ft_zsq10!="" && ft_zsq112!=""){
            	document.getElementById('ft_zsq113').value = (ft_zsq112-ft_zsq10);
            }
            if(wsbxfwlj0!="" && wsbxfwlj12!=""){
            	document.getElementById('wsbxfwlj13').value = (wsbxfwlj12-wsbxfwlj0);
            }
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
        
         function onSubmit()
        {
        	// if (onSearchByDate($("#txtDate").val())){

        	 jQuery.ajax({
					type : 'post',
					url : 'zpump_searchZhuPump.action?txtDate='+$("#txtDate").val(),
					success : function(data) {
        		 
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							//alert(JSON2.stringify(outData));
							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
									if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
									    var pump = outData.JSONDATA.pump;
									    var pump1 = outData.JSONDATA.pump1;
									   // var firstArrSum =  outData.JSONDATA.firstArrSum;
									    	//alert(JSON2.stringify(pump));
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									    if (pump != null && typeof(pump)!="undefined"){
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
     	tableval ="<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;width:2200pt'>"+
    			 "<tr class=xl80 height=42 style='height:31.5pt'>"+
    			  "<td colspan=36 height=42 class=xl136 style='height:31.5pt;width:2200pt'>注输联合站注水泵房日报<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			 "</tr>"+
    			 "<tr height=20 style='mso-height-source:userset;height:15.0pt'>"+
    			  "<td colspan=11 height=20 class=xl70 colspan=3 style='height:15.0pt;mso-ignore:colspan'>单位：稀油注输联合站</td>"+
    			  "<td colspan=3 class=xl70>值班人：</td>"+
				  "<td colspan=5 class=xl70 ><input id='ZBR1' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+ZBR1+"'/> </td> "+
    			  "<td colspan=3 class=xl70>审核人：</td>"+
				 "<td colspan=8 class=xl70 ><input id='SHR' type='text' style='background:transparent;border:0px solid;width:115px;height:30px;font-size: 12pt;' disabled='disabled' value='"+SHR+"'/></td> "+
				 "<td colspan=2 class=xl70>日期：</td>"+
				" <td colspan=4 class=xl70 ><input id='RQ' type='text'  style='background:transparent;border:0px solid;width:120px;height:30px;font-size: 12pt;' disabled='disabled' value='"+RQ+"'/></td> "+

    			 "</tr>"+
    			 "<tr height=32 style='mso-height-source:userset;height:24.0pt'>"+
    			  "<td height=32 class=xl69 style='height:24.0pt;border-top:none'>　</td>"+
    			  "<td class=xl113 width=180 style='border-left:none;width:135pt'>电压</td>"+
    			  "<td colspan=4 class=xl113 width=180 style='border-left:none;width:135pt'>1#注水泵</td>"+
    			  "<td colspan=4 class=xl113 width=178 style='border-left:none;width:133pt'>2#注水泵<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl113 width=180 style='border-left:none;width:135pt'>1#2#频率</td>"+
    			  "<td colspan=4 class=xl113 width=176 style='border-left:none;width:132pt'>3#注水泵<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td colspan=4 class=xl113 width=179 style='border-left:none;width:134pt'>4#注水泵<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl113 width=180 style='border-left:none;width:135pt'>3#4#频率</td>"+
    			  "<td colspan=3 class=xl131 width=242 style='border-right:.5pt solid black;border-left:none;width:182pt'>喂水泵<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td colspan=2 class=xl113 width=136 style='border-left:none;width:102pt'>净化罐液位<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl113 width=293 style='border-left:none;width:221pt'>分水器<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td colspan=3 class=xl131 width=242 style='border-right:.5pt solid black;border-left:none;width:182pt'东线<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl113 width=293 style='border-left:none;width:221pt'>东二线<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td colspan=3 class=xl113 width=220 style='border-left:none;width:166pt'>西一线<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td colspan=3 class=xl113 width=220 style='border-left:none;width:166pt'>西二线<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "</tr>"+
    			 "<tr height=32 style='height:24.0pt'>"+
    			  "<td height=32 class=xl69 style='height:24.0pt;border-top:none'>时间<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>V<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>进口压力MPa<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>出口压力MPa<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>油温℃<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=54 style='border-top:none;border-left:none;width:41pt'>电流A</td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>进口压力MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>出口压力MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>油温℃<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=52 style='border-top:none;border-left:none;width:39pt'>电流A</td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>Hz<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>进口压力MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>出口压力MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>油温℃<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=50 style='border-top:none;border-left:none;width:38pt'>电流A</td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>进口压力MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>出口压力MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>油温℃<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=53 style='border-top:none;border-left:none;width:40pt'>电流A</td>"+
    			  "<td class=xl68 width=63 style='border-top:none;border-left:none;width:47pt'>Hz<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>瞬时流量m³/h<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>流量计读数m³<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>频率<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=68 style='border-top:none;border-left:none;width:51pt'>1#罐m<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=68 style='border-top:none;border-left:none;width:51pt'>2#罐m<ruby><font  class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=93 style='border-top:none;border-left:none;width:70pt'>压力MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=93 style='border-top:none;border-left:none;width:70pt'>压变MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>瞬时流量m³/h<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>流量计读数m³<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=93 style='border-top:none;border-left:none;width:70pt'>压变MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=93 style='border-top:none;border-left:none;width:70pt'>压变MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>瞬时流量m³/h<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>流量计读数m³<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=93 style='border-top:none;border-left:none;width:70pt'>压变MPa<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>瞬时流量m³/h<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			  "<td class=xl68 width=121 style='border-top:none;border-left:none;width:91pt'>流量计读数m³<ruby><font class='font7'><rt class=font7></rt></font></ruby></td>"+
    			 "</tr>";
    			var  disabl="";
				for(var i=0; i<pump.length; i++) {
					
     				tableval +="<tr height=21 style='mso-height-source:userset;height:15.95pt'>"+
		    		"  <td style='display: none'><input id='rpd_u_thin_water_auto_id"+i+"' type='hidden' value='"+pump[i].rpd_u_thin_water_auto_id+"'></td>"+
    			  "<td id='date"+i+"' height=21 class=xl74 width=63 style='height:15.95pt;border-top:none;width:47pt'>"+pump[i].cjsj+"</td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='ZSBF_ZDY"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].ZSBF_ZDY+"></td>"+
     			  "<td class=xl69 style='border-top:none;border-left:none'><input id='pt_101a"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].pt_101a+"></td>"+
     			  "<td class=xl69 style='border-top:none;border-left:none'><input id='pt_101b"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].pt_101b+"></td>"+
     			 "<td class=xl69 style='border-top:none;border-left:none'><input id='TT_101"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].TT_101+"></td>"+
     			  "<td class=xl69 style='border-top:none;border-left:none'><input id='zsb1dl"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].zsb1dl+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='pt_102a"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].pt_102a+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='pt_102b"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].pt_102b+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='TT_102"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].TT_102+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='zsb2dl"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].zsb2dl+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='sc12_zsb_alm_sv"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].sc12_zsb_alm_sv+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='pt_103a"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].pt_103a+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='pt_103b"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].pt_103b+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='TT_103"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].TT_103+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='zsb3dl"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].zsb3dl+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='pt_104a"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].pt_104a+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='pt_104b"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].pt_104b+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='TT_104"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].TT_104+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='zsb4dl"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].zsb4dl+"></td>"+   			  
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='sc34_zsb_alm_sv"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].sc34_zsb_alm_sv+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='FT_WSB"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].FT_WSB+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='wsbxfwlj"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:50px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value='"+pump[i].wsbxfwlj+"'></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='wsb1pl"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].wsb1pl+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='li_1_jhg"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].li_1_jhg+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='li_2_jhg"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].li_2_jhg+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='PT_WSB"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].PT_WSB+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='PT_DX_ZSB"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].PT_DX_ZSB+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='FT_DX"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].FT_DX+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='ft_dx_add"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].ft_dx_add+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='PT_D2X"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].PT_D2X+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='PT_XX_ZSB"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].PT_XX_ZSB+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='FT_XX"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].FT_XX+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='ft_xx_add"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].ft_xx_add+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='ZSBF_XXXYL"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].ZSBF_XXXYL+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='ZSBF_XXXSSLL"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].ZSBF_XXXSSLL+"></td>"+
    			  "<td class=xl69 style='border-top:none;border-left:none'><input id='ZSBF_XXXLLLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 10pt;text-align:left;' value="+pump[i].ZSBF_XXXLLLJ+"></td>"+
	    		  
					"	  <td class=xl74 width=69 style='border-top:none;border-left:none;width:52pt'><input type='hidden' id='rpd_u_thin_water_auto_id"+i+"' value='"+pump[i].rpd_u_thin_water_auto_id+"'/>　</td>"+	    			
					 "</tr>";

    			 }
    			tableval += "<tr height=33 style='mso-height-source:userset;height:24.75pt'>"+
    			  "<td height=20 class=xl84 style='height:24.75pt;border-top:none'>备注：</td>"+
    				"<td colspan=18 height=20 class=xl70  style='height:39.75pt'>"+
					  "<input id='BZ' type='text' style='vertical-align : left;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' value='"+BZ+"'/>"+
					  "</td>"+
					  "<td colspan=2 height=20 class=xl84 style='height:24.75pt;border-top:none'>日喂水量</td>"+
	    				"<td colspan=5 height=20 class=xl70  style='height:39.75pt'>"+
						  "<input id='RWS' type='text' style='vertical-align : left;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' disabled='disabled' value='"+pump1.RWS+"'/>"+
						  "</td>"+
						  "<td colspan=2 height=20 class=xl84 style='height:24.75pt;border-top:none'>东线日注水</td>"+
		    				"<td height=20 class=xl70  style='height:39.75pt'>"+
							  "<input id='RZS' type='text' style='vertical-align : left;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' disabled='disabled' value='"+pump1.RZS+"'/>"+
							  "</td>"+
							  "<td colspan=3 height=20 class=xl84 style='height:24.75pt;border-top:none'>西一线日注水</td>"+
			    				"<td height=20 class=xl70  style='height:39.75pt'>"+
								  "<input id='RZS1' type='text' style='vertical-align : left;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' disabled='disabled' value='"+pump1.RZS1+"'/>"+
								  "</td>"+
								  "<td colspan=2 height=20 class=xl84 style='height:24.75pt;border-top:none'>西二线线日注水</td>"+
				    				"<td height=20 class=xl70  style='height:39.75pt'>"+
									  "<input id='RZS2' type='text' style='vertical-align : left;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' disabled='disabled' value='"+pump1.RZS2+"'/>"+
									  "</td>"+
    			  "</tr>"+
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
						
					},
					error : function(data) {
				
					}
				}); 
        	// }else {
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
            	for(var i=0;i<25;i++){
            		firstArr.push($("#rpd_u_thin_water_auto_id"+i).val());
            		firstArr.push($("#date"+i).text());
            		firstArr.push($("#ZSBF_ZDY"+i).val());
            		firstArr.push($("#pt_101a"+i).val());
            		firstArr.push($("#pt_101b"+i).val());
            		firstArr.push($("#TT_101"+i).val());
            		firstArr.push($("#zsb1dl"+i).val());
            		firstArr.push($("#pt_102a"+i).val());
            		firstArr.push($("#pt_102b"+i).val());
            		firstArr.push($("#TT_102"+i).val());
            		firstArr.push($("#zsb2dl"+i).val());
            		firstArr.push($("#sc12_zsb_alm_sv"+i).val());
            		firstArr.push($("#pt_103a"+i).val());
            		firstArr.push($("#pt_103b"+i).val());
            		firstArr.push($("#TT_103"+i).val());
            		firstArr.push($("#zsb3dl"+i).val());
            		firstArr.push($("#pt_104a"+i).val());
            		firstArr.push($("#pt_104b"+i).val());
            		firstArr.push($("#TT_104"+i).val());
            		firstArr.push($("#zsb4dl"+i).val());
            		firstArr.push($("#sc34_zsb_alm_sv"+i).val());
            		firstArr.push($("#FT_WSB"+i).val());
            		firstArr.push($("#wsbxfwlj"+i).val());
            		firstArr.push($("#wsb1pl"+i).val());
            		firstArr.push($("#li_1_jhg"+i).val());
            		firstArr.push($("#li_2_jhg"+i).val());
            		firstArr.push($("#PT_WSB"+i).val());
            		firstArr.push($("#PT_DX_ZSB"+i).val());
            		firstArr.push($("#FT_DX"+i).val());
            		firstArr.push($("#ft_dx_add"+i).val());
            		firstArr.push($("#PT_D2X"+i).val());
            		firstArr.push($("#PT_XX_ZSB"+i).val());
            		firstArr.push($("#FT_XX"+i).val());
            		firstArr.push($("#ft_xx_add"+i).val());
            		firstArr.push($("#ZSBF_XXXYL"+i).val());
            		firstArr.push($("#ZSBF_XXXSSLL"+i).val());
            		firstArr.push($("#ZSBF_XXXLLLJ"+i).val());            		
             		firstStr += arrayToString(firstArr,",");
             		firstStr += ";";
             		firstArr = [];
                	}
            	if (modifyDataFlag($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'zpump_updateRPDREPORTMESSAGE.action?nowdata='+parseInt(Math.random()*100000),
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
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行再次审核");
         	}else{
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'zpump_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var url = "zpump_exportU2_OIL.action?txtDate="+encodeURIComponent(txtDate);
     		if (onSearchByDate(txtDate)) {
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    				}
    			});
    		} else {
    			$.ligerDialog.error("选择日期无效，请选择其他日期！");
    		}
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
						<td align="right" class="l-table-edit-td" style="width:100px">
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
