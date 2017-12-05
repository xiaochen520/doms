<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>脱氧塔运行日报</title>
   
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
 	 <link href="../../page/dayreport/css/tytyxrb.css" rel="stylesheet" type="text/css" /> 
    <script type="text/javascript">
    var tableval;
	var  SHR ="";
	var  RQ ="";
	var TBR1 = "";
	var TBR2 = "";
	var  BZ1 ="";
	var  BZ2 ="";
	var EXPORTNAME ="";
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
            
             var clzData = 
            [{ id: 1, text: '集中' }
            ];
            var sbbhData =
            [
            { id: 1, text: '1', pid: 1 },
            { id: 2, text: '2', pid: 1 },
            { id: 3, text: '3', pid: 1 }
            ];  
            
              $("#sbbh").ligerComboBox({ data: null,isMultiSelect: false });
              $("#sclz").ligerComboBox({
                data: clzData, isMultiSelect: false,selectBoxWidth:150,
                onSelected: function (newvalue)
                {
                    var newData = new Array();
                    for (i = 0; i < sbbhData.length; i++)
                    {
                        if (sbbhData[i].pid == newvalue)
                        {
                            newData.push(sbbhData[i]);
                        }
                    }
                    liger.get("sbbh").setData(newData);
                }
            });
            
            
           	//获取报表及功能按钮ｊｓ
            jQuery.ajax({
				type : 'post',
				url : 'tytrb_pageInit.action',
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
        	 var sclz1 = $("#sclz").val();
        	 var sbbh1 = $("#sbbh").val();
        	 jQuery.ajax({
					type : 'post',
					url : 'tytrb_searchDatas.action?nowdata='+parseInt(Math.random()*100000),
					data: {"txtDate":txtDate1,"sclz":sclz1,"sbbh":sbbh1},
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
									    var DATAS = outData.JSONDATA.DATAS;
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
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
									     			
									     			 if(REPORTMESSAGE.TBR1 != null && typeof(REPORTMESSAGE.TBR1)!="undefined"){
									     				TBR1 =REPORTMESSAGE.TBR1;
									     			}else{
									     				TBR1 = "";
									     			}
									     			
									     			 if(REPORTMESSAGE.TBR2 != null && typeof(REPORTMESSAGE.TBR2)!="undefined"){
									     				TBR2 =REPORTMESSAGE.TBR2;
									     			}else{
									     				TBR2 = "";
									     			}
									     			
									     			 if(REPORTMESSAGE.BZ1 != null && typeof(REPORTMESSAGE.BZ1)!="undefined"){
									     			  BZ1 =REPORTMESSAGE.BZ1;
									     			}else{
									     				BZ1 = "";
									     			}
									     			
									     			 if(REPORTMESSAGE.BZ2 != null && typeof(REPORTMESSAGE.BZ2)!="undefined"){
									     			  BZ2 =REPORTMESSAGE.BZ2;
									     			}else{
									     				BZ2 = "";
									     			}
												
												 if(REPORTMESSAGE.EXPORTNAME != null && typeof(REPORTMESSAGE.EXPORTNAME)!="undefined"){
									     			  EXPORTNAME =REPORTMESSAGE.EXPORTNAME;
									     			}else{
									     				EXPORTNAME = "";
									     			}
												
										     		$("#tableHtml").html('');
						     						tableval = "<table border=0 cellpadding=0 cellspacing=0 width=1926 style='border-collapse:collapse;table-layout:fixed;width:1445pt'> "+
" <col width=116 style='mso-width-source:userset;mso-width-alt:4242;width:87pt'> "+
" <col width=125 style='mso-width-source:userset;mso-width-alt:4571;width:94pt'> "+
" <col width=124 span=2 style='mso-width-source:userset;mso-width-alt:4534;width:93pt'> "+
" <col width=141 span=3 style='mso-width-source:userset;mso-width-alt:5156;width:106pt'> "+
" <col width=147 style='mso-width-source:userset;mso-width-alt:5376;width:110pt'> "+
" <col width=163 style='mso-width-source:userset;mso-width-alt:5961;width:122pt'> "+
" <col width=64 span=11 style='width:48pt'> "+
" <tr class=xl66 height=38 style='mso-height-source:userset;height:28.5pt'> "+
"  <td colspan=9 rowspan=2 height=57 class=xl84 width=1222 style='height:42.75pt;width:917pt'>风城供汽联合站<font class='font5'> </font><font class='font6'>"+$("#sclz").val()+"水处理</font><font class='font6'>站</font><font class='font5'> "+$("#sbbh").val()+"</font><font class='font6'>号脱氧塔运行日报</font></td> "+

" </tr> "+
" <tr class=xl66 height=19 style='mso-height-source:userset;height:14.25pt'> "+
"  <td height=19 class=xl66 width=64 style='height:14.25pt;width:48pt'></td> "+

" </tr> "+
 "<tr class=xl67 height=32 style='mso-height-source:userset;height:24.6pt'> "+
 " <td colspan=5 height=32 class=xl85 style='height:24.6pt'>　</td> "+
 " <td class=xl69 width=141 style='border-top:none;border-left:none;width:106pt'>审核人：</td> "+
 " <td class=xl70 width=141 style='border-top:none;border-left:none;width:106pt'><input id='SHR' type='text' style='background:transparent;border:0px solid;width:115px;height:30px;font-size: 12pt;' disabled='disabled' value='"+SHR+"'/>　</td> "+
 " <td colspan=2 class=xl82 style='border-left:none'><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+REPORTMESSAGE.RQ1+"</td> "+
  
 "</tr> "+
 "<tr class=xl65 height=68 style='mso-height-source:userset;height:51.0pt'>"+
 " <td height=68 class=xl90 width=116 style='height:51.0pt;border-top:none; width:87pt'><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
 "  	<img   src='../../img/tablexiexian2.jpg'> "+
 " </td> "+
 " <td class=xl71 width=125 style='border-top:none;border-left:none;width:94pt'>循环泵压力<br> "+
 "   <font class='font10'>MPa</font></td> "+
 " <td class=xl71 width=124 style='border-top:none;border-left:none;width:93pt'>供水泵压力<br> "+
 "   <font class='font10'>MPa</font></td> "+
 " <td class=xl72 width=124 style='border-top:none;width:93pt'>增压泵压力<br> "+
 "   <font class='font10'>MPa</font></td> "+
 " <td class=xl71 width=141 style='border-top:none;width:106pt'>除氧塔液位<br> "+
 "   <font class='font10'>m</font></td> "+
 " <td class=xl71 width=141 style='border-top:none;border-left:none;width:106pt'>供水泵频率<br> "+
 "   <font class='font11'>Hz</font></td> "+
 " <td class=xl71 width=141 style='border-top:none;border-left:none;width:106pt'>增压泵频率<br> "+
 "   <font class='font11'>Hz</font></td> "+
 " <td class=xl71 width=147 style='border-top:none;border-left:none;width:110pt'>出水量<br> "+
 "   <font class='font10'>m&sup3;/h</font></td> "+
 " <td class=xl71 width=163 style='border-top:none;border-left:none;width:122pt'>溶解氧<br> "+
 "   <font class='font10'>mg/l</font></td> "+
 "  </tr> ";
 
var rowdisabled = "";
 for ( var i = 0; i < DATAS.length; i++) {
	 rowdisabled = "";
	 if(DATAS[i].RPD_DTOWER_ID != null && DATAS[i].RPD_DTOWER_ID !="" && typeof(DATAS[i].RPD_DTOWER_ID)!="undefined"){
		 rowdisabled = "";
	 }else{
		 rowdisabled = "disabled='disabled'";
	 }
	 
				tableval +=  "  <tr class=xl65 height=28 style='mso-height-source:userset;height:21.0pt'> "+
				
				 "  <td height=28 class=xl91 width=116 style='height:21.0pt;border-top:none; width:87pt'><input id='ROWRQ"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  value="+DATAS[i].ROWRQ+"></td> "+
				 "  <td class=xl71 width=125 style='border-top:none;border-left:none;width:94pt'><input id='XH_PUMP_P"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].XH_PUMP_P+">　</td> "+
				 "  <td class=xl71 width=124 style='border-top:none;border-left:none;width:93pt'><input id='GS_PUMP_P"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].GS_PUMP_P+">　</td> "+
				 " <td class=xl71 width=124 style='border-top:none;border-left:none;width:93pt'><input id='ZY_PUMP_P"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].ZY_PUMP_P+">　</td> "+
				 " <td class=xl73 width=141 style='border-top:none;border-left:none;width:106pt'><input id='W1LT"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].W1LT+">　</td> "+
				 " <td class=xl73 width=141 style='border-top:none;border-left:none;width:106pt'><input id='W1YO"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].W1YO+">　</td> "+
				 " <td class=xl73 width=141 style='border-top:none;border-left:none;width:106pt'><input id='ZY_PUMP_FREQ"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].ZY_PUMP_FREQ+">　</td> "+
				 " <td class=xl73 width=147 style='border-top:none;border-left:none;width:110pt'><input id='W1FT"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].W1FT+">　</td> "+
				 " <td class=xl74 style='border-top:none;border-left:none'><input id='CYQ_DO"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].CYQ_DO+">　</td> "+
				 " <td class=xl74 style='border-top:none;border-right:none;border-left:none'><input id='RPD_DTOWER_ID"+i+"'  type='hidden'  style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+DATAS[i].RPD_DTOWER_ID+">　</td> "+
				 "  </tr> ";
 }
 
 tableval += " <tr class=xl65 height=18 style='height:13.5pt'> "+
 " <td height=18 class=xl78 width=116 style='height:13.5pt;width:87pt'>记事：白班</td> "+
 " <td colspan=3 class=xl86 width=373 style='border-right:.5pt solid black;  width:280pt'>　</td> "+
 "  <td class=xl78 width=141 style='border-left:none;width:106pt'>记事：夜班</td> "+
 "  <td colspan=4 class=xl86 width=592 style='border-right:.5pt solid black; width:444pt'>　</td> "+
 "  </tr> "+
 " <tr class=xl65 height=36 style='mso-height-source:userset;height:27.0pt'> "+
 " <td colspan=4 rowspan=5 height=117 class=xl89 width=489 style='border-right:.5pt solid black;height:88.35pt;width:367pt'>　　<input id='BZ1' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+BZ1+"'/> </td> "+
 "  <td colspan=5 rowspan=5 class=xl89 width=733 style='border-right:.5pt solid black; width:550pt'>　　<input id='BZ2' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+BZ2+"'/> </td> "+

 " </tr> "+
 " <tr class=xl65 height=27 style='mso-height-source:userset;height:20.45pt'> "+
 "  <td height=27 class=xl65 width=64 style='height:20.45pt;width:48pt'></td> "+

 "  </tr> "+
 " <tr class=xl65 height=18 style='height:13.5pt'> "+
 " <td height=18 class=xl65 width=64 style='height:13.5pt;width:48pt'></td> "+

 "   <td class=xl65 width=64 style='width:48pt'></td> "+
 " </tr> "+
 " <tr class=xl65 height=18 style='height:13.5pt'> "+
 " <td height=18 class=xl65 width=64 style='height:13.5pt;width:48pt'></td> "+

 "  </tr> "+
 " <tr class=xl65 height=18 style='mso-height-source:userset;height:13.9pt'> "+
 " <td height=18 class=xl65 width=64 style='height:13.9pt;width:48pt'></td> "+

 "  </tr> "+
 " <tr class=xl65 height=20 style='mso-height-source:userset;height:15.6pt'> "+
 " <td height=20 class=xl79 style='height:15.6pt'>填表人：</td> "+
 " <td colspan=3 class=xl80 style='border-right:.5pt solid black'> <input id='TBR1' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+TBR1+"'/> 　　</td> "+
 " <td class=xl79 style='border-left:none'>填表人：</td> "+
 " <td colspan=4 class=xl80 style='border-right:.5pt solid black'>　 <input id='TBR2' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+TBR2+"'/> </td> "+
 " <td class=xl65 width=64 style='width:48pt'></td> "+
 "  </tr> "+
 " </table> ";
						     						$("#tableHtml").html(tableval);	
									     	}else{
									     		RPDREPORTMESSAGEID ="";
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


  			var	TBR1 = $("#TBR1").val();
  			var	TBR2 = $("#TBR2").val();
  			
  			var	bz1 = $("#BZ1").val();
  			var	bz2 = $("#BZ2").val();
  			var sclz = $("#sclz").val();
  			var sbbh = $("#sbbh").val();
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<13;i++){
             		firstArr.push($("#RPD_DTOWER_ID"+i).val());
             		firstArr.push($("#ROWRQ"+i).val());
             		firstArr.push($("#XH_PUMP_P"+i).val());
             		firstArr.push($("#GS_PUMP_P"+i).val());
             		firstArr.push($("#ZY_PUMP_P"+i).val());
             		firstArr.push($("#W1LT"+i).val());
             		
             		firstArr.push($("#W1YO"+i).val());
             		firstArr.push($("#ZY_PUMP_FREQ"+i).val());
             		firstArr.push($("#W1FT"+i).val());
             		firstArr.push($("#CYQ_DO"+i).val());
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'tytrb_updateDatas.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"TBR1":TBR1,"TBR2":TBR2,"BZ1":bz1,"BZ2":bz2,"RQ":RQ,"SCLZ":sclz,"SBBH":sbbh,"EXPORTNAME":EXPORTNAME,"firstStr":firstStr},
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
 					url : 'tytrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		var sclz = $("#sclz").val();
     		var sbbh = $("#sbbh").val();
     		if (sclz != "" && typeof(sclz)!="undefined" && sbbh != "" && typeof(sbbh)!="undefined"){
     			var url = "tytrb_exportDatas.action?txtDate="+encodeURIComponent(txtDate)+"&sclz="+encodeURIComponent(sclz)+"&sbbh="+encodeURIComponent(sbbh)+"&RPDREPORTMESSAGEID="+encodeURIComponent(RPDREPORTMESSAGEID);
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    				
    				}
    			});
     		}else{
     			$.ligerDialog.error("水处理，除氧器不能为空");
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
<body link=blue vlink=fuchsia style="padding:10px">
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table >
					<tr>	
						<td align="right"  class="l-table-edit-td" style="width:60px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>
						 
		                
						<td><input type="text" id="sclz" ltype="sclz"/></td>
						 <td align="left" class="l-table-edit-td" style="width:80px">水处理站</td>
						 <td><input type="text" id="sbbh" ltype="sbbh"/></td>
						 <td align="left" class="l-table-edit-td" style="width:80px">号脱氧塔</td>
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
					<div id="tableHtml" style="OVERFLOW:auto; height: 87%;" align="center">
					
					
					
					
					</div>
				</div>
					
				

</form>
</body>
</html>
