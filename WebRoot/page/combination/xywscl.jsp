<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>污水处理</title>
   
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
    <script type="text/javascript">
    	var tableval;
    	var  RPD_U_THIN_WATER_GENERAL_ID ="";
    	var  SHR ="";
    	var  BBRQ1 ="";
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
					url : 'xywscl_pageInit.action',
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
												jQuery("#bc").css('display','block');
											}
										//alert(JSON2.stringify(bottons.audit ));
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
            var yqls0 = $('#YQLSXFWLJ0').val(); //油区来水
            var yqls12 = $('#YQLSXFWLJ12').val(); 
            
            var glqcs0 = $('#GLQCSXFWLJ0').val(); // 过滤器出水
            var glqcs12 = $('#GLQCSXFWLJ12').val(); 
            
            var wsbck0 = $('#WSBCKXFWLJ0').val(); //喂水泵出口
            var wsbck12 = $('#WSBCKXFWLJ12').val(); 

            if(yqls12!="" && yqls0!=""){
                $('#yqls').html((yqls12-yqls0).toFixed(2));
            }
            if(glqcs12!="" && glqcs0!=""){
                $('#glqcs').html((glqcs12-glqcs0).toFixed(2));
            }
            if(wsbck12!="" && wsbck0!=""){
                $('#wsbck').html((wsbck12-wsbck0).toFixed(2));
            }

            var syj11 = $('#SYJ1SL1').text();	//水源井 昨日
            var syj21 = $('#SYJ2SL1').text(); 
            var syj31 = $('#SYJ3SL1').text(); 

            var syj13 = $('#SYJ1SL3').val(); 	//水源井 今日
            var syj23 = $('#SYJ2SL3').val(); 
            var syj33 = $('#SYJ3SL3').val();  

            if(syj13!="" && syj11!=""){
            	$('#sl1').html((syj13-syj11).toFixed(2)); //水量
            }
            if(syj23!="" && syj21!=""){
            	$('#sl2').html((syj23-syj21).toFixed(2));
            }
            if(syj33!="" && syj31!=""){
            	$('#sl3').html((syj33-syj31).toFixed(2));
            }

            var sl1 = $('#sl1').text();
            var sl2 = $('#sl2').text();
            var sl3 = $('#sl3').text();
            if(sl1=="" && sl2=="" && sl3==""){ //合计
            	$('#slSum').html(""); 
            }else{
            	$('#slSum').html((Number(sl1)+Number(sl2)+Number(sl3)).toFixed(2));
            }

            //反应器
            var yj10 = $('#YJ1XFWLJ0').val(); //1#药剂
            var yj112 = $('#YJ1XFWLJ12').val();
            //1#
            var jsll0_1 = $('#FYQ1JSXFWLJ0').val(); //进水流量
            var jsll12_1 = $('#FYQ1JSXFWLJ12').val();
            var yjll20_1 = $('#FYQ1YL2XFWLJ0').val(); //2#药剂流量
            var yjll212_1 = $('#FYQ1YL2XFWLJ12').val(); 
            var yjll30_1 = $('#FYQ1YJ3XFWLJ0').val() //3#药剂流量
            var yjll312_1 = $('#FYQ1YJ3XFWLJ12').val()
			//2#
            var jsll0_2 = $('#FYQ2JSXFWLJ0').val(); //进水流量
            var jsll12_2 = $('#FYQ2JSXFWLJ12').val();
            var yjll20_2 = $('#FYQ2YL2XFWLJ0').val(); //2#药剂流量
            var yjll212_2 = $('#FYQ2YL2XFWLJ12').val(); 
            var yjll30_2 = $('#FYQ2YJ3XFWLJ0').val() //3#药剂流量
            var yjll312_2 = $('#FYQ2YJ3XFWLJ12').val()
			//3#
            var jsll0_3 = $('#FYQ3JSXFWLJ0').val(); //进水流量
            var jsll12_3 = $('#FYQ3JSXFWLJ12').val();
            var yjll20_3 = $('#FYQ3YL2XFWLJ0').val(); //2#药剂流量
            var yjll212_3 = $('#FYQ3YL2XFWLJ12').val(); 
            var yjll30_3 = $('#FYQ3YJ3XFWLJ0').val() //3#药剂流量
            var yjll312_3 = $('#FYQ3YJ3XFWLJ12').val()
            
            if(yj10!="" && yj112!=""){
            	document.getElementById('YJ1XFWLJ13').value = (yj112-yj10).toFixed(2);
            }
            if(jsll12_1!="" && jsll0_1!=""){
            	document.getElementById('FYQ1JSXFWLJ13').value = (jsll12_1-jsll0_1).toFixed(2);
            }
            if(yjll212_1!="" && yjll20_1!=""){
            	document.getElementById('FYQ1YL2XFWLJ13').value = (yjll212_1-yjll20_1).toFixed(2);
            }
            if(yjll312_1!="" && yjll30_1!=""){
            	document.getElementById('FYQ1YJ3XFWLJ13').value = (yjll312_1-yjll30_1).toFixed(2);
            }
            if(jsll12_2!="" && jsll0_2!=""){
            	document.getElementById('FYQ2JSXFWLJ13').value = (jsll12_2-jsll0_2).toFixed(2);
            }
            if(yjll212_2!="" && yjll20_2!=""){
            	document.getElementById('FYQ2YL2XFWLJ13').value = (yjll212_2-yjll20_2).toFixed(2);
            }
            if(yjll312_2!="" && yjll30_2!=""){
            	document.getElementById('FYQ2YJ3XFWLJ13').value = (yjll312_2-yjll30_2).toFixed(2);
            }
            if(jsll12_3!="" && jsll0_3!=""){
            	document.getElementById('FYQ3JSXFWLJ13').value = (jsll12_3-jsll0_3).toFixed(2);
            }
            if(yjll212_3!="" && yjll20_3!=""){
            	document.getElementById('FYQ3YL2XFWLJ13').value = (yjll212_3-yjll20_3).toFixed(2);
            }
            if(yjll312_3!="" && yjll30_3!=""){
            	document.getElementById('FYQ3YJ3XFWLJ13').value = (yjll312_3-yjll30_3).toFixed(2);
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
					url : 'xywscl_searchXYWSDatas.action?txtDate='+$("#txtDate").val(),
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
									if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
									    var firstArr  = outData.JSONDATA.firstArr;
										var firstArrSum = outData.JSONDATA.firstArrSum;
										var secondArr  = outData.JSONDATA.secondArr;
										var otherArr  = outData.JSONDATA.OTHERDATA;
										if (otherArr[0].RPD_U_THIN_WATER_GENERAL_ID != null && typeof(otherArr[0].RPD_U_THIN_WATER_GENERAL_ID)!="undefined" && otherArr[0].RPD_U_THIN_WATER_GENERAL_ID != ""){
											RPD_U_THIN_WATER_GENERAL_ID = otherArr[0].RPD_U_THIN_WATER_GENERAL_ID;
										}else{
											RPD_U_THIN_WATER_GENERAL_ID = ""	;
										}
										if(otherArr[0].SHR != null && typeof(otherArr[0].SHR)!="undefined"){
											SHR =otherArr[0].SHR;
						     			}else{
						     				SHR = "";
						     			}


										if(otherArr[0].BBRQ1 != null && typeof(otherArr[0].BBRQ1)!="undefined"){
											BBRQ1 =otherArr[0].BBRQ1;
						     			}else{
						     				BBRQ1 = "";
						     			}
						     			
									    	$("#tablearea").html("");
									    	
									    	tableval = "<table x:str border=0 cellpadding=0 cellspacing=0 width=1512 style='border-collapse:"+
											    		" collapse;table-layout:fixed;width:1134pt'>"+
											    		" <col width=72 span=21 style='width:54pt'>"+
											    		" <tr class=xl24 height=39 style='mso-height-source:userset;height:29.25pt'>"+
											    		"  <td colspan=21 height=39 class=xl34 width=1512 style='border-right:1.0pt solid black;"+
											    		"  height:29.25pt;width:1134pt'>污 水 处 理<span style='mso-spacerun:yes'>&nbsp;"+
											    		"  </span>综 合 日 报</td>"+
											    		" </tr>"+
											    		" <tr height=20 style='mso-height-source:userset;height:15.0pt'>"+
											    		"  <td colspan=4 height=20 class=xl25 style='height:15.0pt'>单位：稀油注输联合站</td>"+
											    		"  <td class=xl25 style='border-left:none'>值班人：</td>"+
											    		"  <td colspan=8 class=xl37 style='border-left:none'><input id='ZBR'  type='text' style='background:transparent;border:0px solid;width:609px;line-height:25px;height:20px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZBR+"'/></td>"+
											    		"  <td class=xl25 style='border-left:none'>审核：</td>"+
											    		"  <td colspan=3 class=xl37 style='border-left:none'><input id='SHR'  type='text' disabled='disabled' style='background:transparent;border:0px solid;width:229px;line-height:25px;height:20px;font-size: 12pt;text-align:center;' value='"+otherArr[0].SHR+"'/></td>"+
											    		"  <td colspan=3 class=xl38 style='border-left:none'>"+otherArr[0].BBRQ+"</td>"+
											    		"  <td class=xl25 style='border-left:none'>　</td>"+
											    		" </tr>"+
											    		" <tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
											    		"  <td colspan=11 height=23 class=xl39 style='height:17.25pt'>一、大罐液位</td>"+
											    		"  <td colspan=10 class=xl39 style='border-left:none'>二、流量数据</td>"+
											    		" </tr>"+
											    		" <tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
											    		"  <td rowspan=2 height=46 class=xl44 width=72 style='height:34.5pt;border-top:"+
											    		"  none;width:54pt'>"+
											    		"  <img   src='../../img/tablexiexian.jpg'>"+   
								
											    		"  </td>"+
											    		"  <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>调储罐液位(m)</td>"+
											    		"  <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>缓冲罐液位(m)</td>"+
											    		"  <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>净水罐液位(m)</td>"+
											    		"  <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>污水池液位(m)</td>"+
											    		"  <td rowspan=2 class=xl26 width=72 style='border-top:none;width:54pt'>污油罐液位(m)</td>"+
											    		"  <td rowspan=2 class=xl26 width=72 style='border-top:none;width:54pt'>消防罐液位(m)</td>"+
											    		"  <td rowspan=2 class=xl44 width=72 style='border-top:none;width:54pt'>"+
											    		"  <img   src='../../img/tablexiexian.jpg'> "+
											    		"  </td>"+
											    		"  <td colspan=2 class=xl28 width=144 style='border-left:none;width:108pt'>油区来水</td>"+
											    		"  <td colspan=2 class=xl28 width=144 style='border-left:none;width:108pt'>过滤器出水</td>"+
											    		"  <td colspan=2 class=xl28 width=144 style='border-left:none;width:108pt'>喂水泵出口</td>"+
											    		"  <td colspan=3 class=xl28 width=216 style='border-left:none;width:162pt'>水源井</td>"+
											    		" </tr>"+
											    		" <tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
											    		"  <td height=23 class=xl26 width=72 style='height:17.25pt;border-top:none;"+
											    		"  border-left:none;width:54pt'>1#罐</td>"+
											    		"  <td class=xl26 width=72 style='border-top:none;border-left:none;width:54pt'>2#罐</td>"+
											    		"  <td class=xl26 width=72 style='border-top:none;border-left:none;width:54pt'>1#罐</td>"+
											    		"  <td class=xl26 width=72 style='border-top:none;border-left:none;width:54pt'>2#罐</td>"+
											    		"  <td class=xl26 width=72 style='border-top:none;border-left:none;width:54pt'>1#罐</td>"+
											    		"  <td class=xl26 width=72 style='border-top:none;border-left:none;width:54pt'>2#罐</td>"+
											    		"  <td class=xl26 width=72 style='border-top:none;border-left:none;width:54pt'>1#池</td>"+
											    		"  <td class=xl26 width=72 style='border-top:none;border-left:none;width:54pt'>2#池</td>"+
											    		"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>瞬时流量</td>"+
											    		"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>累积流量</td>"+
											    		"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>瞬时流量</td>"+
											    		"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>累积流量</td>"+
											    		"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>瞬时流量</td>"+
											    		"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>累积流量</td>"+
											    		"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>1#</td>"+
											    		"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>2#</td>"+
											    	    " <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>4#</td>"+
											    		" </tr>"
											    		
											    		for(var i=0; i<firstArr.length; i++) {
												    		tableval +=  
													    		"<tr height=22 style='mso-height-source:userset;height:16.5pt'>"+
													    		"  <td style='display: none'><input id='RPD_U_THIN_WATER_AUTO_ID"+i+"' type='hidden' value='"+firstArr[i].RPD_U_THIN_WATER_AUTO_ID+"'></td>"+
												    		  	"  <td id='report_time"+i+"' height=22 class=xl27 style='height:16.5pt;border-top:none' x:num='0.33333333333333298'>"+firstArr[i].REPORT_TIME+"</td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='L1_1_TCG"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].L1_1_TCG+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LI_2_TCG"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LI_2_TCG+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LI_1_HCG"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LI_1_HCG+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LI_2_HCG"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LI_2_HCG+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LI_1_JHG"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LI_1_JHG+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LI_2_JHG"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LI_2_JHG+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LI_1_WSC"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LI_1_WSC+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LI_2_WSC"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LI_2_WSC+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LI_WYG"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LI_WYG+"'/></td>"+
												    			"  <td class=xl27 style='border-top:none;border-left:none'><input id='LT_XFYW"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].LT_XFYW+"'/></td>"+
												    							
												    			"  <td class=xl27 style='border-top:none;border-left:none'x:num='0.33333333333333298'>"+firstArr[i].REPORT_TIME+"</td>"+
												    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FI_YYCLZLS"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].FI_YYCLZLS+"'/></td>"+
												    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YQLSXFWLJ"+i+"'  type='text' onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].YQLSXFWLJ+"'/></td>"+
												    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FI_YTZS"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].FI_YTZS+"'/></td>"+
												    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='GLQCSXFWLJ"+i+"'  type='text' onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].GLQCSXFWLJ+"'/></td>"+
												    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='FI_JHSO1"+i+"'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].FI_JHSO1+"'/></td>"+
												    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='WSBCKXFWLJ"+i+"'  type='text' onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].WSBCKXFWLJ+"'/></td>";
												    			if(i==0){
												    				tableval +=
												    				"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>昨日底数</td>"+
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>昨日底数</td>"+
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>昨日底数</td>";
													    		}else if(i==1){
													    			tableval +=
													    			"  <td id='SYJ1SL1' class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[i].SYJ1SL+"</td>"+
													    			"  <td id='SYJ2SL1' class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[i].SYJ2SL+"</td>"+
													    			"  <td id='SYJ3SL1' class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[i].SYJ3SL+"</td>";
													    		}else if(i==2){
													    			tableval +=
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>今日抄表</td>"+
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>今日抄表</td>"+
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>今日抄表</td>";
													    		}else if(i==3){
													    			tableval +=
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='SYJ1SL"+i+"'  type='text' onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].SYJ1SL+"'/></td>"+
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='SYJ2SL"+i+"'  type='text' onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].SYJ2SL+"'/></td>"+
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='SYJ3SL"+i+"'  type='text' onkeyup='checkData(this)' onblur='changValue()' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+firstArr[i].SYJ3SL+"'/></td>";
													    		}else if(i==4){
													    			tableval +=
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>水量</td>"+
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>水量</td>"+
													    			"  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>水量</td>";
													    		}else if(i==5){
													    			tableval +=
													    			"  <td id='sl1' class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[i].SYJ1SL+"</td>"+
													    			"  <td id='sl2' class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[i].SYJ2SL+"</td>"+
													    			"  <td id='sl3' class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>"+firstArr[i].SYJ3SL+"</td>";
													    		}else if(i==6){
													    			tableval +=
													    			"  <td colspan=3 class=xl28 width=216 style='border-left:none;width:162pt'>合计水量</td>";
													    		}else if(i==7){
													    			tableval +=
													    			"  <td id='slSum' colspan=3 class=xl28 width=216 style='border-left:none;width:162pt'>"+firstArr[i].SYJ1SL+"</td>";
													    		}else if(i>=8){
													    			tableval +=
														    		"  <td colspan=3 class=xl28 width=216 style='border-left:none;width:162pt'></td>";
													    		}
												    			tableval += " </tr>";
											    		}

											    		tableval += 				
												    		" <tr height=25 style='mso-height-source:userset;height:18.75pt'>"+
												    		"	  <td colspan=11 height=25 class=xl27 style='height:18.75pt'>　</td>"+
												    		"	  <td class=xl30 style='border-top:none;border-left:none;text-align:center'>累积值</td>"+
												    		"	  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>　</td>"+
												    		"	  <td id='yqls' class=xl28 style='border-top:none;border-left:none' >"+firstArrSum[0].YQLSXFWLJ+"</td>"+
												    		"	  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>　</td>"+
												    		"	  <td id='glqcs' class=xl28 style='border-top:none;border-left:none' >"+firstArrSum[0].GLQCSXFWLJ+"</td>"+
												    		"	  <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'>　</td>"+
												    		"	  <td id='wsbck' class=xl28 style='border-top:none;border-left:none'>"+firstArrSum[0].WSBCKXFWLJ+"</td>"+
												    		"	  <td colspan=3 class=xl28 width=216 style='border-left:none;width:162pt'>　</td>"+
											    			"</tr>";
											    tableval += 
													"<tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
													"    <td colspan=21 height=23 class=xl40 style='height:17.25pt'>三、反应器</td>"+
													"    </tr>"+
													"    <tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
													"     <td rowspan=3 height=69 class=xl46 width=72 style='height:51.75pt;border-top:none;width:54pt'>"+
													"     <img   src='../../img/tablexiexian1.jpg'>"+ 
													"     </td>"+
													"     <td colspan=2 class=xl41 style='border-left:none'>1#药剂</td>"+
													"     <td colspan=6 class=xl41 style='border-left:none'>1#反应器</td>"+
													"     <td colspan=6 class=xl41 style='border-left:none'>2#反应器</td>"+
													"     <td colspan=6 class=xl41 style='border-left:none'>3#反应器</td>"+
													"    </tr>"+
													"    <tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
													"     <td rowspan=2 height=46 class=xl41 style='height:34.5pt;border-top:none'>瞬时流量</td>"+
													"     <td rowspan=2 class=xl41 style='border-top:none'>累积流量</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>进水流量L/h</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>2#药剂流量L/h</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>3#药剂流量L/h</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>进水流量L/h</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>2#药剂流量L/h</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>3#药剂流量L/h</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>进水流量L/h</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>2#药剂流量L/h</td>"+
													"     <td colspan=2 class=xl26 width=144 style='border-left:none;width:108pt'>3#药剂流量L/h</td>"+
													"    </tr>"+
													"    <tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
													"    <td height=23 class=xl29 style='height:17.25pt;border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"    <td class=xl29 style='border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>瞬时流量</td>"+
													"     <td class=xl29 style='border-top:none;border-left:none'>累积流量</td>"+
													"    </tr>";
													var disabl;												
													for(var i=0; i<secondArr.length; i++) {
														if(i==13){
															disabl = "disabled='disabled'";
														}else if(i==12 || i==0){
															disabl = "onblur='changValue()'";
														}else{
															disabl = "";
														}
												    		tableval +=  
												    			"<tr height=22 style='mso-height-source:userset;height:16.5pt'>"+
													    		"  <td height=22 class=xl27 style='height:16.5pt;border-top:none'x:num='0.33333333333333298'>"+secondArr[i].REPORT_TIME+"</td>"+
													    		"  <td class=xl27 style='border-top:none;border-left:none'><input id='FI_1_FYJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_1_FYJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='YJ1XFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].YJ1XFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_1_FYQ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_1_FYQ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ1JSXFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ1JSXFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_2_YJO1"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_2_YJO1+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ1YL2XFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ1YL2XFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_3_FYJO1"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_3_FYJO1+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ1YJ3XFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ1YJ3XFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_2_FYQ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_2_FYQ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ2JSXFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ2JSXFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_2_YJO2"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_2_YJO2+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ2YL2XFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ2YL2XFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_3_FYJO2"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_3_FYJO2+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ2YJ3XFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ2YJ3XFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_3_FYQ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_3_FYQ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ3JSXFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ3JSXFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_2_YJO3"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_2_YJO3+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ3YL2XFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ3YL2XFWLJ+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FI_3_FYJO3"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FI_3_FYJO3+"'/></td>"+
													    		"  <td class=xl30 style='border-top:none;border-left:none'><input id='FYQ3YJ3XFWLJ"+i+"'  type='text' "+disabl+" onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].FYQ3YJ3XFWLJ+"'/></td>"+
													    		" </tr>";
													}
													tableval += 
														"<tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
														"  <td colspan=21 height=23 class=xl42 style='height:17.25pt'>四、其他数据</td>"+
														"</tr>"+
														"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
														"   <td height=24 class=xl28 width=72 style='height:18.0pt;width:54pt'>　</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>抽检时间</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>调进</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>调出</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>斜进</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>斜出</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>一级进口</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>一级出口</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>二级出口</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>注水进口</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>乌33</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>高压</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>次高压</td>"+
														"   <td rowspan=3 class=xl28 width=72 style='width:54pt'>药剂用量</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>杀菌剂</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>阻垢剂</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>反相</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>防膨剂</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>助凝剂</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>收泥量</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>　</td>"+
														"   <td class=xl28 width=72 style='border-left:none;width:54pt'>　</td>"+
														"</tr>"+
														
														"<tr height=24  style='mso-height-source:userset;height:18.0pt'>"+
														"   <td height=24  class=xl28 width=72 style='height:18.0pt;border-top:none;width:54pt'>含油</td>"+
														"   <td class=xl28 width=72 style='border-left:none'><input id='CJSJHY'  type='text' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:35px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CJSJHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DJHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DJHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DCHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DCHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XJHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XJHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XCHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XCHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJJKHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJJKHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJCKHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJCKHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='EJCKHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].EJCKHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZSJKHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZSJKHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='W33HY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].W33HY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='GYHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].GYHY+"'/></td>"+																												
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CGYHY'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CGYHY+"'/></td>"+
														
														
														"   <td rowspan=2 class=xl28 width=72 style='border-top:none;width:54pt'><input id='XJJYL'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XJJYL+"'/></td>"+
														"   <td rowspan=2 class=xl28 width=72 style='border-top:none;width:54pt'><input id='ZGJYL'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZGJYL+"'/></td>"+
														"   <td rowspan=2 class=xl28 width=72 style='border-top:none;width:54pt'><input id='FXYL'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].FXYL+"'/></td>"+
														"   <td rowspan=2 class=xl28 width=72 style='border-top:none;width:54pt'><input id='FPJYL'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].FPJYL+"'/></td>"+
														"   <td rowspan=2 class=xl28 width=72 style='border-top:none;width:54pt'><input id='ZYJYL'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZYJYL+"'/></td>"+
														"   <td rowspan=2 class=xl28 width=72 style='border-top:none;width:54pt'><input id='SNL'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].SNL+"'/></td>"+
														"   <td rowspan=2 class=xl28 width=72 style='border-top:none;width:54pt'><input id='RPD_U_THIN_WATER_GENERAL_ID' type='hidden' value='"+otherArr[0].RPD_U_THIN_WATER_GENERAL_ID+"'></td>"+
														"   <td rowspan=2 class=xl28 width=72 style='border-top:none;width:54pt'>　</td>"+
														"</tr>"+
														"<tr height=24  style='mso-height-source:userset;height:18.0pt'>"+
														"   <td height=24  class=xl28 width=72 style='height:18.0pt;border-top:none;width:54pt'>含油</td>"+
														"   <td class=xl28 width=72 style='border-left:none'><input id='CJSJHY1'  type='text' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:35px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CJSJHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DJHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DJHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DCHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DCHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XJHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XJHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XCHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XCHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJJKHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJJKHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJCKHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJCKHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='EJCKHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].EJCKHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZSJKHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZSJKHY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='W33HY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].W33HY1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='GYHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].GYHY1+"'/></td>"+																												
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CGYHY1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CGYHY1+"'/></td>"+
														"</tr>"+
														"<tr height=24  style='mso-height-source:userset;height:18.0pt'>"+
														"   <td height=24  class=xl28 width=72 style='height:18.0pt;border-top:none;width:54pt'>含油</td>"+
														"   <td class=xl28 width=72 style='border-left:none'><input id='CJSJHY2'  type='text' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:35px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CJSJHY2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DJHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DJHY2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DCHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DCHY2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XJHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XJHY2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XCHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XCHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJJKHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJJKHY2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJCKHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJCKHY2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='EJCKHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].EJCKHY+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZSJKHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZSJKHY2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='W33HY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].W33HY2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='GYHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].GYHY2+"'/></td>"+																												
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CGYHY2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CGYHY2+"'/></td>"+
														"</tr>"+
														"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
														"   <td height=24  class=xl28 width=72 style='height:18.0pt;border-top:none;width:54pt'>悬浮物</td>"+
														"   <td class=xl28 width=72 style='border-left:none'><input id='CJSJXFW'  type='text' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:35px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CJSJXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DJXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DJXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DCXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DCXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XJXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XJXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XCXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XCXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJJKXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJJKXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJCKXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJCKXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='EJCKXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].EJCKXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZSJKXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZSJKXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='W33XFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].W33XFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='GYXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].GYXFW+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CGYXFW'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CGYXFW+"'/></td>"+
														"</tr>"+
														"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
														"   <td height=24  class=xl28 width=72 style='height:18.0pt;border-top:none;width:54pt'>悬浮物</td>"+
														"   <td class=xl28 width=72 style='border-left:none'><input id='CJSJXFW1'  type='text' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:35px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CJSJXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DJXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DJXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DCXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DCXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XJXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XJXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XCXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XCXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJJKXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJJKXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJCKXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJCKXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='EJCKXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].EJCKXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZSJKXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZSJKXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='W33XFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].W33XFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='GYXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].GYXFW1+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CGYXFW1'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CGYXFW1+"'/></td>"+
														"</tr>"+
														"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
														"   <td height=24  class=xl28 width=72 style='height:18.0pt;border-top:none;width:54pt'>悬浮物</td>"+
														"   <td class=xl28 width=72 style='border-left:none'><input id='CJSJXFW2'  type='text' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:35px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CJSJXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DJXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DJXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='DCXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].DCXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XJXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XJXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='XCXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].XCXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJJKXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJJKXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='YJCKXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].YJCKXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='EJCKXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].EJCKXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='ZSJKXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].ZSJKXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='W33XFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].W33XFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='GYXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].GYXFW2+"'/></td>"+
														"   <td class=xl28 width=72 style='border-top:none;border-left:none;width:54pt'><input id='CGYXFW2'  type='text' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:75px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' value='"+otherArr[0].CGYXFW2+"'/></td>"+
														"</tr>"+
														"<tr height=32 style='mso-height-source:userset;height:24.0pt'>"+
														"   <td height=32 class=xl32 style='height:24.0pt;border-top:none'>备注：</td>"+
														"   <td colspan=20 class=xl43 style='border-left:none'><input id='BZ'  type='text' style='background:transparent;border:0px solid;width:1500px;line-height:25px;height:35px;font-size: 12pt;text-align:center;' value='"+otherArr[0].BZ+"'/></td>"+
														"</tr>";
												
												$("#tablearea").html(tableval);
									    
									    
									    //}else{
										//	$.ligerDialog.error("当前日期无数据，请选择其他日期！");
										//}
										
									}else{
										$.ligerDialog.error("当前日期无数据，请选择其他日期！");
									}
							
								}
							}
						
					},
					error : function(data) {
						alert(data);
					}
				});
       // }else{
        //	$.ligerDialog.error("选择日期无效，请选择其他日期！");
       // }
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
 			var	BZ = $("#BZ").val();
 			var	RQ = $("#RQ").val();
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行修改");
         	}else{
             	var firstArr = [];
             	var firstStr = "";
             	for(var i=0;i<13;i++){
             		firstArr.push($("#RPD_U_THIN_WATER_AUTO_ID"+i).val());
             		firstArr.push($("#report_time"+i).text());
             		firstArr.push($("#L1_1_TCG"+i).val());
             		firstArr.push($("#LI_2_TCG"+i).val());	
             		firstArr.push($("#LI_1_HCG"+i).val());	
             		firstArr.push($("#LI_2_HCG"+i).val());	
             		firstArr.push($("#LI_1_JHG"+i).val());	
             		firstArr.push($("#LI_2_JHG"+i).val());	
             		firstArr.push($("#LI_1_WSC"+i).val());	
             		firstArr.push($("#LI_2_WSC"+i).val());	
             		firstArr.push($("#LI_WYG"+i).val());	
             		firstArr.push($("#LT_XFYW"+i).val());
             		firstArr.push($("#FI_YYCLZLS"+i).val());
             		firstArr.push($("#YQLSXFWLJ"+i).val());		
             		firstArr.push($("#FI_YTZS"+i).val());
             		firstArr.push($("#GLQCSXFWLJ"+i).val());			
             		firstArr.push($("#FI_JHSO1"+i).val());	
             		firstArr.push($("#WSBCKXFWLJ"+i).val());

             		firstArr.push($("#FI_1_FYJ"+i).val());	
             		firstArr.push($("#YJ1XFWLJ"+i).val());	
             		firstArr.push($("#FI_1_FYQ"+i).val());	
             		firstArr.push($("#FYQ1JSXFWLJ"+i).val());	
             		firstArr.push($("#FI_2_YJO1"+i).val());	
             		firstArr.push($("#FYQ1YL2XFWLJ"+i).val());	
             		firstArr.push($("#FI_3_FYJO1"+i).val());	
             		firstArr.push($("#FYQ1YJ3XFWLJ"+i).val());	
             		firstArr.push($("#FI_2_FYQ"+i).val());	
             		firstArr.push($("#FYQ2JSXFWLJ"+i).val());	
             		firstArr.push($("#FI_2_YJO2"+i).val());	
             		firstArr.push($("#FYQ2YL2XFWLJ"+i).val());	
             		firstArr.push($("#FI_3_FYJO2"+i).val());	
             		firstArr.push($("#FYQ2YJ3XFWLJ"+i).val());	
             		firstArr.push($("#FI_3_FYQ"+i).val());	
             		firstArr.push($("#FYQ3JSXFWLJ"+i).val());	
             		firstArr.push($("#FI_2_YJO3"+i).val());	
             		firstArr.push($("#FYQ3YL2XFWLJ"+i).val());	
             		firstArr.push($("#FI_3_FYJO3"+i).val());	
             		firstArr.push($("#FYQ3YJ3XFWLJ"+i).val());
             		
             		firstStr += arrayToString(firstArr,",");
             		firstStr += ";";
             		firstArr = [];
             	}

             	//水源井
             	var SYJ1SL = $('#SYJ1SL3').val();	
             	var SYJ2SL = $('#SYJ2SL3').val();	
             	var SYJ3SL = $('#SYJ3SL3').val();

             	//其他数据
             	var CJSJHY = $('#CJSJHY').val();
             	var DJHY = $('#DJHY').val();
             	var DCHY = $('#DCHY').val();	
             	var XJHY = $('#XJHY').val();	
             	var XCHY = $('#XCHY').val();	
             	var YJJKHY = $('#YJJKHY').val();	
             	var YJCKHY = $('#YJCKHY').val();	
             	var EJCKHY = $('#EJCKHY').val();	
             	var ZSJKHY = $('#ZSJKHY').val();	
             	var W33HY = $('#W33HY').val();	
             	var GYHY = $('#GYHY').val();	
             	var CGYHY = $('#CGYHY').val();
             	
             	var CJSJHY1 = $('#CJSJHY1').val();
             	var DJHY1 = $('#DJHY1').val();
             	var DCHY1 = $('#DCHY1').val();	
             	var XJHY1 = $('#XJHY1').val();	
             	var XCHY1 = $('#XCHY1').val();	
             	var YJJKHY1 = $('#YJJKHY1').val();	
             	var YJCKHY1 = $('#YJCKHY1').val();	
             	var EJCKHY1 = $('#EJCKHY1').val();	
             	var ZSJKHY1 = $('#ZSJKHY1').val();	
             	var W33HY1 = $('#W33HY1').val();	
             	var GYHY1 = $('#GYHY1').val();	
             	var CGYHY1 = $('#CGYHY1').val();
             	
             	var CJSJHY2 = $('#CJSJHY2').val();
             	var DJHY2 = $('#DJHY2').val();
             	var DCHY2 = $('#DCHY2').val();	
             	var XJHY2 = $('#XJHY2').val();	
             	var XCHY2 = $('#XCHY2').val();	
             	var YJJKHY2 = $('#YJJKHY2').val();	
             	var YJCKHY2 = $('#YJCKHY2').val();	
             	var EJCKHY2 = $('#EJCKHY2').val();	
             	var ZSJKHY2 = $('#ZSJKHY2').val();	
             	var W33HY2 = $('#W33HY2').val();	
             	var GYHY2 = $('#GYHY2').val();	
             	var CGYHY2 = $('#CGYHY2').val();
             	
             	var CJSJXFW = $('#CJSJXFW').val();
             	var DJXFW = $('#DJXFW').val();	
             	var DCXFW = $('#DCXFW').val();	
             	var XJXFW = $('#XJXFW').val();	
             	var XCXFW = $('#XCXFW').val();	
             	var YJJKXFW = $('#YJJKXFW').val();	
             	var YJCKXFW = $('#YJCKXFW').val();	
             	var EJCKXFW = $('#EJCKXFW').val();	
             	var ZSJKXFW = $('#ZSJKXFW').val();	
             	var W33XFW = $('#W33XFW').val();	
             	var GYXFW = $('#GYXFW').val();	
             	var CGYXFW = $('#CGYXFW').val();
             	
             	var CJSJXFW1 = $('#CJSJXFW1').val();
             	var DJXFW1 = $('#DJXFW1').val();	
             	var DCXFW1 = $('#DCXFW1').val();	
             	var XJXFW1 = $('#XJXFW1').val();	
             	var XCXFW1 = $('#XCXFW1').val();	
             	var YJJKXFW1 = $('#YJJKXFW1').val();	
             	var YJCKXFW1 = $('#YJCKXFW1').val();	
             	var EJCKXFW1 = $('#EJCKXFW1').val();	
             	var ZSJKXFW1 = $('#ZSJKXFW1').val();	
             	var W33XFW1 = $('#W33XFW1').val();	
             	var GYXFW1 = $('#GYXFW1').val();	
             	var CGYXFW1 = $('#CGYXFW1').val();
             	
             	var CJSJXFW2 = $('#CJSJXFW2').val();
             	var DJXFW2 = $('#DJXFW2').val();	
             	var DCXFW2 = $('#DCXFW2').val();	
             	var XJXFW2 = $('#XJXFW2').val();	
             	var XCXFW2 = $('#XCXFW2').val();	
             	var YJJKXFW2 = $('#YJJKXFW2').val();	
             	var YJCKXFW2 = $('#YJCKXFW2').val();	
             	var EJCKXFW2 = $('#EJCKXFW2').val();	
             	var ZSJKXFW2 = $('#ZSJKXFW2').val();	
             	var W33XFW2 = $('#W33XFW2').val();	
             	var GYXFW2 = $('#GYXFW2').val();	
             	var CGYXFW2 = $('#CGYXFW2').val();
             	
             	
             	var XJJYL = $('#XJJYL').val();	
             	var ZGJYL = $('#ZGJYL').val();	
             	var FXYL = $('#FXYL').val();	
				var FPJYL = $('#FPJYL').val();	
				var SNL = $('#SNL').val();	
				var ZYJYL = $('#ZYJYL').val();
				var RPD_U_THIN_WATER_GENERAL_ID = $('#RPD_U_THIN_WATER_GENERAL_ID').val();
             	
 				if (modifyDataFlag($("#txtDate").val())) {
 	        	
 					jQuery.ajax({
 									type : 'post',
 									url : 'xywscl_updateRPDREPORTMESSAGE.action?nowdata='+parseInt(Math.random()*100000),
 									data: {"firstStr":firstStr,
						"ZBR":ZBR,"BZ":BZ,"BBRQ1":BBRQ1,
						"SYJ1SL":SYJ1SL,"SYJ2SL":SYJ2SL,"SYJ3SL":SYJ3SL,
 						"CJSJHY":CJSJHY,"DJHY":DJHY,"DCHY":DCHY,"XJHY":XJHY,"XCHY":XCHY,"YJJKHY":YJJKHY,"YJCKHY":YJCKHY,"EJCKHY":EJCKHY,"ZSJKHY":ZSJKHY,"W33HY":W33HY,"GYHY":GYHY,"CGYHY":CGYHY,
 						"CJSJHY1":CJSJHY1,"DJHY1":DJHY1,"DCHY1":DCHY1,"XJHY1":XJHY1,"XCHY1":XCHY1,"YJJKHY1":YJJKHY1,"YJCKHY1":YJCKHY1,"EJCKHY1":EJCKHY1,"ZSJKHY1":ZSJKHY1,"W33HY1":W33HY1,"GYHY1":GYHY1,"CGYHY1":CGYHY1,
 						"CJSJHY2":CJSJHY2,"DJHY2":DJHY2,"DCHY2":DCHY2,"XJHY2":XJHY2,"XCHY2":XCHY2,"YJJKHY2":YJJKHY2,"YJCKHY2":YJCKHY2,"EJCKHY2":EJCKHY2,"ZSJKHY2":ZSJKHY2,"W33HY2":W33HY2,"GYHY2":GYHY2,"CGYHY2":CGYHY2,
 						
 						"CJSJXFW":CJSJXFW,"DJXFW":DJXFW,"DCXFW":DCXFW,"XJXFW":XJXFW,"XCXFW":XCXFW,"YJJKXFW":YJJKXFW,"YJCKXFW":YJCKXFW,
 						"EJCKXFW":EJCKXFW,"ZSJKXFW":ZSJKXFW,"W33XFW":W33XFW,"GYXFW":GYXFW,"CGYXFW":CGYXFW,
 						"CJSJXFW1":CJSJXFW1,"DJXFW1":DJXFW1,"DCXFW1":DCXFW1,"XJXFW1":XJXFW1,"XCXFW1":XCXFW1,"YJJKXFW1":YJJKXFW1,"YJCKXFW1":YJCKXFW1,
 						"EJCKXFW1":EJCKXFW1,"ZSJKXFW1":ZSJKXFW1,"W33XFW1":W33XFW1,"GYXFW1":GYXFW1,"CGYXFW1":CGYXFW1,
 						"CJSJXFW2":CJSJXFW2,"DJXFW2":DJXFW2,"DCXFW2":DCXFW2,"XJXFW2":XJXFW2,"XCXFW2":XCXFW2,"YJJKXFW2":YJJKXFW2,"YJCKXFW2":YJCKXFW2,
 						"EJCKXFW2":EJCKXFW2,"ZSJKXFW2":ZSJKXFW2,"W33XFW2":W33XFW2,"GYXFW2":GYXFW2,"CGYXFW2":CGYXFW2,
 						
 						"XJJYL":XJJYL,"ZGJYL":ZGJYL,"FXYL":FXYL,"FPJYL":FPJYL,"SNL":SNL,"ZYJYL":ZYJYL,
 						"RPD_U_THIN_WATER_GENERAL_ID":RPD_U_THIN_WATER_GENERAL_ID
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
        	//var RPDREPORTMESSAGEID = $("#RPDREPORTMESSAGEID").val();
        	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
        		$.ligerDialog.error("数据已审核过，不能进行再次审核");
        	}else{
        		if (chekAduitByDate($("#txtDate").val())) {
        		 jQuery.ajax({
					type : 'post',
					url : 'xywscl_onAudit.action?nowdata='+parseInt(Math.random()*100000),
					data: {"RPD_U_THIN_WATER_GENERAL_ID":RPD_U_THIN_WATER_GENERAL_ID},
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
		var url = "xywscl_exportU2_OIL.action?txtDate="+encodeURIComponent(txtDate);
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
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        <!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{mso-header-data:&A;
	mso-footer-data:"Page &P";
	margin:.75in .7in .75in .7in;
	mso-header-margin:.3in;
	mso-footer-margin:.3in;
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
						
						<td id='bc' align="right" class="l-table-edit-td" style="width:100px;display:none">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td  align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

<div id="tablearea"  style="OVERFLOW:auto; width: 99%;height: 89%;">
	<table>
		<tr>
			<td style="display: none"><input type="hidden" value="1"> </td>
		</tr>
	</table>
</div> 
  </form>
    
</body>
</html>
