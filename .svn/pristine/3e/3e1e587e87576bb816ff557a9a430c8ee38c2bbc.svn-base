<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>集中水处理日报</title>
   
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
 	 <link href="../../page/dayreport/css/jzsclrb.css" rel="stylesheet" type="text/css" /> 
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
            
           
            
            
            
           	//获取报表及功能按钮ｊｓ
            jQuery.ajax({
				type : 'post',
				url : 'jzsclrb_pageInit.action',
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
        	 jQuery.ajax({
					type : 'post',
					url : 'jzsclrb_searchDatas.action?nowdata='+parseInt(Math.random()*100000),
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
						     						tableval = "<table border=0 cellpadding=0 cellspacing=0 width=1008 class=xl7531319style='border-collapse:collapse;table-layout:fixed;width:759pt'> "+
" <col class=xl7531319 width=91 style='mso-width-source:userset;mso-width-alt:2912;width:68pt'> "+
" <col class=xl8131319 width=70 style='mso-width-source:userset;mso-width-alt:2240;width:53pt'>"+
" <col class=xl7831319 width=70 style='mso-width-source:userset;mso-width-alt:2240;width:53pt'>"+
" <col class=xl7831319 width=53 style='mso-width-source:userset;mso-width-alt:1696;width:40pt'>"+
" <col class=xl7831319 width=44 style='mso-width-source:userset;mso-width-alt:1408;width:33pt'> "+
" <col class=xl7831319 width=47 style='mso-width-source:userset;mso-width-alt:1504;width:35pt'> "+
" <col class=xl7831319 width=53 style='mso-width-source:userset;mso-width-alt:1696;width:40pt'> "+
" <col class=xl7831319 width=59 style='mso-width-source:userset;mso-width-alt:1888;width:44pt'> "+
" <col class=xl7831319 width=57 style='mso-width-source:userset;mso-width-alt:1824;width:43pt'> "+
" <col class=xl7831319 width=53 span=2 style='mso-width-source:userset;mso-width-alt:1696;width:40pt'> "+
" <col class=xl7831319 width=42 span=4 style='mso-width-source:userset;mso-width-alt:1344;width:32pt'> "+
" <col class=xl7831319 width=99 style='mso-width-source:userset;mso-width-alt:3168;width:74pt'> "+
" <col class=xl7831319 width=91 style='mso-width-source:userset;mso-width-alt:2912;width:68pt'> "+
" <tr class=xl7531319 height=12 style='mso-height-source:userset;height:9.6pt'> "+
"  <td colspan=17 rowspan=2 height=48 class=xl11831319 width=1008 style='height:37.2pt;width:759pt'>风城供汽联合站集中水处理日报<font class='font531319'><span style='mso-spacerun:yes'>&nbsp;</span></font></td> "+
" </tr> "+
" <tr class=xl7631319 height=36 style='mso-height-source:userset;height:27.6pt'> "+
" </tr> "+
" <tr class=xl7631319 height=30 style='mso-height-source:userset;height:22.5pt'> "+
"  <td colspan=6 height=30 width=375 style='height:22.5pt;width:282pt' "+
"  align=left valign=top> "+
"  <![if !vml]><span style='mso-ignore:vglayout;position:absolute;z-index:1;margin-left:1px;margin-top:28px;width:92px;height:85px'> "+
"  <img width=92 height=85 src='集中水处理日报%20.files/集中水处理日报%20_31319_image002.png' v:shapes='Line_x0020_3'></span> "+
"  <![endif]> "+
"  <span style='mso-ignore:vglayout2'> "+
"  <table cellpadding=0 cellspacing=0> "+
"   <tr> "+
"    <td colspan=6 height=30 class=xl10231319 width=375 style='height:22.5pt;width:282pt'>　</td> "+
"   </tr> "+
"  </table> "+
"  </span></td> "+
"  <td colspan=3 class=xl10431319 width=169 style='border-left:none;width:127pt' align='right' >审核人：</td> "+
"  <td colspan=3 class=xl10431319 width=148 style='border-left:none;width:112pt' ><input id='SHR' type='text' style='background:transparent;border:0px solid;width:115px;height:30px;font-size: 12pt;' disabled='disabled' value='"+SHR+"'/>　　</td> "+
"  <td colspan=5 class=xl10431319 width=316 style='border-left:none;width:238pt' align='right'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+REPORTMESSAGE.RQ1+"</td> "+
" </tr> "+
" <tr class=xl7731319 height=24 style='mso-height-source:userset;height:18.0pt'> "+
"  <td rowspan=3 height=80 class=xl11331319 width=91 style='border-bottom:.5pt solid black;height:60.0pt;border-top:none;width:68pt'>"+
 "  	<img   src='../../img/tablexiexian2.jpg'> "+
" </td> "+
"  <td colspan=10 class=xl8931319 width=559 style='border-right:.5pt solid black;border-left:none;width:421pt'>设备关键数据</td> "+
"  <td colspan=4 class=xl9631319 style='border-right:.5pt solid black;border-left:none'>水质（清水、污水）</td> "+
"  <td colspan=2 rowspan=3 class=xl9631319 style='border-right:.5pt solid black;border-bottom:.5pt solid black'>其它</td> "+
" </tr> "+
" <tr class=xl7731319 height=20 style='mso-height-source:userset;height:15.0pt'> "+
"  <td colspan=2 height=20 class=xl7931319 width=140 style='height:15.0pt;border-left:none;width:106pt'>水量</td> "+
"  <td colspan=4 class=xl11631319 width=197 style='border-left:none;width:148pt'>罐液位</td> "+
"  <td colspan=4 class=xl11731319 style='border-left:none'>压力</td> "+
"  <td rowspan=2 class=xl11931319 width=42 style='width:32pt'>硬度</td> "+
"  <td rowspan=2 class=xl11931319 width=42 style='width:32pt'>含氧</td> "+
"  <td rowspan=2 class=xl11931319 width=42 style='width:32pt'>氯根</td> "+
"  <td rowspan=2 class=xl11931319 width=42 style='width:32pt'>碱度</td> "+
" </tr> "+
" <tr class=xl7731319 height=36 style='height:27.0pt'> "+
"  <td height=36 class=xl8031319 width=70 style='height:27.0pt;border-top:none; "+
"  border-left:none;width:53pt'>软化器总出水量</td> "+
"  <td class=xl8031319 width=70 style='border-top:none;border-left:none; "+
"  width:53pt'>除氧塔总出水量</td> "+
"  <td class=xl8031319 width=53 style='border-top:none;border-left:none; "+
"  width:40pt'>5<font class='font631319'>00方转水</font></td> "+
"  <td class=xl8031319 width=44 style='border-top:none;border-left:none; "+
"  width:33pt'>清水生水</td> "+
"  <td class=xl8031319 width=47 style='border-top:none;border-left:none; "+
"  width:35pt'>清水软水</td> "+
"  <td class=xl8031319 width=53 style='border-top:none;border-left:none; "+
"  width:40pt'>外排池</td> "+
"  <td class=xl8031319 width=59 style='border-top:none;border-left:none; "+
"  width:44pt'>一号增压泵</td> "+
"  <td class=xl8031319 width=57 style='border-top:none;border-left:none; "+
"  width:43pt'>二号增压泵</td> "+
"  <td class=xl8031319 width=53 style='border-top:none;border-left:none; "+
"  width:40pt'>三号增压泵</td> "+
"  <td class=xl8031319 width=53 style='border-top:none;border-left:none; "+
"  width:40pt'>压缩空气</td> "+
" </tr> ";

var rowdisabled = "";
 for ( var i = 0; i < DATAS.length; i++) {
	 rowdisabled = "";
	 if(DATAS[i].RPD_JZSCL_ID != null && DATAS[i].RPD_JZSCL_ID !="" && typeof(DATAS[i].RPD_JZSCL_ID)!="undefined"){
		 rowdisabled = "";
	 }else{
		 rowdisabled = "disabled='disabled'";
	 }
  tableval += " <tr class=xl7731319 height=23 style='mso-height-source:userset;height:17.25pt'> "+
  "  <td style='display: none'><input id='RPD_JZSCL_ID"+i+"' type='hidden' value='"+DATAS[i].RPD_JZSCL_ID+"'></td>"+
"  <td height=23 class=xl8231319 width=91 style='height:17.25pt;width:68pt'><input id='ROWRQ"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+DATAS[i].ROWRQ+"></td> "+
"  <td class=xl8331319 width=70 style='width:53pt'><input id='RHQZCSL"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].RHQZCSL+">　</td> "+
"  <td class=xl8331319 width=70 style='border-left:none;width:53pt'><input id='CYTZCSL"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].CYTZCSL+">　</td> "+
"  <td class=xl8331319 width=53 style='border-left:none;width:40pt'><input id='LT401"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].LT401+">　</td> "+
"  <td class=xl8331319 width=44 style='border-left:none;width:33pt'><input id='LT102"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].LT102+">　</td> "+
"  <td class=xl8331319 width=47 style='border-left:none;width:35pt'><input id='LT101"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].LT101+">　</td> "+
"  <td class=xl8331319 width=53 style='border-left:none;width:40pt'><input id='LT403"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].LT403+">　</td> "+
"  <td class=xl8331319 width=59 style='border-left:none;width:44pt'><input id='ZYBYL1"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].ZYBYL1+">　</td> "+
"  <td class=xl8331319 width=57 style='border-left:none;width:43pt'><input id='ZYBYL2"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].ZYBYL2+">　</td> "+
"  <td class=xl8331319 width=53 style='border-left:none;width:40pt'><input id='ZYBYL3"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].ZYBYL3+">　</td> "+
"  <td class=xl8331319 width=53 style='border-left:none;width:40pt'><input id='PI_201"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].PI_201+">　</td> "+
"  <td class=xl9331319 width=42 style='width:32pt'><input id='HARDNESS"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].HARDNESS+">　</td> "+
"  <td class=xl9331319 width=42 style='border-left:none;width:32pt'><input id='OXYGEN_BEARING"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].OXYGEN_BEARING+">　</td> "+
"  <td class=xl9331319 width=42 style='border-left:none;width:32pt'><input id='CHLORIDE"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].CHLORIDE+">　</td> "+
"  <td class=xl9331319 width=42 style='border-left:none;width:32pt'><input id='BASICITY"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:40px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  "+rowdisabled+" value="+DATAS[i].BASICITY+">　</td> ";

	if(i == 0){
	 tableval +=  "  <td colspan=2 class=xl11131319 width=190 style='border-right:.5pt solid black;width:142pt'>盐车登记：</td> "+
	" </tr> ";
	}else if(i == 1){
	tableval += "  <td class=xl9031319 width=99 style='width:74pt'>车号：</td> "+
	"  <td class=xl8831319 width=91 style='border-left:none;width:68pt'>重量：</td> "+
	" </tr> ";
	}else if(i == 2){
	tableval += "  <td class=xl9031319 width=99 style='border-top:none;width:74pt'>　<input id='CAR_NO1'  type='text'   style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.CAR_NO1+">　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none; width:68pt'>　<input id='CAR_WEIGHT1'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.CAR_WEIGHT1+">　</td> "+
	" </tr> ";
	}else if(i == 3){
	tableval += "  <td class=xl9131319 width=99 style='border-top:none;width:74pt'>　<input id='CAR_NO2'  type='text'  style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.CAR_NO2+">　　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none;width:68pt'>　<input id='CAR_WEIGHT2'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.CAR_WEIGHT2+"></td> "+
	" </tr> ";
	}else if(i == 4){
	tableval += "  <td class=xl9131319 width=99 style='border-top:none;width:74pt'>　<input id='CAR_NO3'  type='text'   style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.CAR_NO3+">　　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none;width:68pt'>　<input id='CAR_WEIGHT3'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.CAR_WEIGHT3+"></td> "+
	" </tr> ";
	}else if(i == 5){
	tableval += "  <td class=xl9131319 width=99 style='border-top:none;width:74pt'>　<input id='CAR_NO4'  type='text'   style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.CAR_NO4+">　　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none;width:68pt'>　<input id='CAR_WEIGHT4'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.CAR_WEIGHT4+"></td> "+
	" </tr> ";
	}else if(i == 6){
	tableval += "  <td colspan=2 class=xl10931319 width=190 style='border-right:.5pt solid black; "+
"  width:142pt'>亚硫酸钠加药：</td> "+
" </tr> ";
	}else if(i == 7){
	tableval += "  <td class=xl9031319 width=99 style='border-top:none;width:74pt'>加药：</td> "+
"  <td class=xl8831319 width=91 style='border-top:none;border-left:none; "+
"  width:68pt'>重量：</td> "+
" </tr> ";
	}else if(i == 8){
	tableval += "  <td class=xl9031319 width=99 style='border-top:none;width:74pt'><input id='SODIUMS_DOSING1'  type='text'   style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.SODIUMS_DOSING1+">　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none;width:68pt'><input id='DOS_WEIGHT1'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.DOS_WEIGHT1+">　</td> "+
	" </tr> ";
	}else if(i == 9){
		tableval += "  <td class=xl9031319 width=99 style='border-top:none;width:74pt'><input id='SODIUMS_DOSING2'  type='text'   style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.SODIUMS_DOSING2+">　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none;width:68pt'><input id='DOS_WEIGHT2'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.DOS_WEIGHT2+">　</td> "+
	" </tr>  ";
	}else if(i == 10){
		tableval += "  <td class=xl9031319 width=99 style='border-top:none;width:74pt'><input id='SODIUMS_DOSING3'  type='text'   style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.SODIUMS_DOSING3+">　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none;width:68pt'><input id='DOS_WEIGHT3'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.DOS_WEIGHT3+">　</td> "+
	" </tr>  ";
	}else if(i == 11){
		tableval += "  <td class=xl9031319 width=99 style='border-top:none;width:74pt'><input id='SODIUMS_DOSING4'  type='text'   style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.SODIUMS_DOSING4+">　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none;width:68pt'><input id='DOS_WEIGHT4'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.DOS_WEIGHT4+">　</td> "+
	" </tr>  ";
	}else if(i == 12){
		tableval += "  <td class=xl9031319 width=99 style='border-top:none;width:74pt'><input id='SODIUMS_DOSING5'  type='text'   style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.SODIUMS_DOSING5+">　</td> "+
	"  <td class=xl8831319 width=91 style='border-top:none;border-left:none;width:68pt'><input id='DOS_WEIGHT5'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' value="+REPORTMESSAGE.DOS_WEIGHT5+">　</td> "+
	" </tr>  ";
	}
  
  }


tableval +=" <tr class=xl7731319 height=30 style='mso-height-source:userset;height:22.5pt'> "+
"  <td colspan=2 height=30 class=xl13231319 width=161 style='height:22.5pt; "+
"  width:121pt'>记事：白班</td> "+
"  <td colspan=7 class=xl12131319 width=383 style='border-right:.5pt solid black; width:288pt'>　</td> "+
"  <td colspan=2 class=xl12831319 width=106 style='width:80pt'>记事：夜班</td> "+
"  <td colspan=6 class=xl12831319 width=358 style='border-right:.5pt solid black;width:270pt'>　</td> "+
" </tr> "+
" <tr class=xl7731319 height=36 style='mso-height-source:userset;height:27.0pt'> "+
"  <td colspan=9 rowspan=4 height=120 class=xl12331319 width=544  style='border-right:.5pt solid black;height:90.0pt;width:409pt;valign：middle'></br></br></br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='BZ1' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+BZ1+"'/>　</td> "+
"  <td colspan=8 rowspan=4 class=xl12631319 width=464 style='border-right:.5pt solid black; width:350pt;valign：middle'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='BZ2' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+BZ2+"'/>　</td> "+
" </tr> "+
" <tr class=xl7731319 height=28 style='mso-height-source:userset;height:21.0pt'> "+
" </tr> "+
" <tr class=xl7731319 height=28 style='mso-height-source:userset;height:21.0pt'> "+
" </tr> "+
" <tr class=xl7731319 height=28 style='mso-height-source:userset;height:21.0pt'> "+
" </tr> "+
" <tr class=xl7731319 height=26 style='mso-height-source:userset;height:19.5pt'> "+
"  <td colspan=2 height=26 class=xl8531319 width=161 style='height:19.5pt; "+
"  width:121pt'><span style='mso-spacerun:yes'>&nbsp;</span>填表人：</td> "+
"  <td colspan=7 class=xl10931319 width=383 style='border-right:.5pt solid black; width:288pt'>　　 <input id='TBR1' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+TBR1+"'/> </td> "+
"  <td colspan=2 class=xl8531319 width=106 style='border-left:none;width:80pt'><span "+
"  style='mso-spacerun:yes'>&nbsp;</span>填表人：</td> "+
"  <td colspan=6 class=xl13031319 width=358 style='border-right:.5pt solid black; width:270pt'>　　 <input id='TBR2' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+TBR2+"'/> </td> "+
" </tr> "+
" <![if supportMisalignedColumns]> "+
" <tr height=0 style='display:none'> "+
"  <td width=91 style='width:68pt'></td> "+
"  <td width=70 style='width:53pt'></td> "+
"  <td width=70 style='width:53pt'></td> "+
"  <td width=53 style='width:40pt'></td> "+
"  <td width=44 style='width:33pt'></td> "+
"  <td width=47 style='width:35pt'></td> "+
"  <td width=53 style='width:40pt'></td> "+
"  <td width=59 style='width:44pt'></td> "+
"  <td width=57 style='width:43pt'></td> "+
"  <td width=53 style='width:40pt'></td> "+
"  <td width=53 style='width:40pt'></td> "+
"  <td width=42 style='width:32pt'></td> "+
"  <td width=42 style='width:32pt'></td> "+
"  <td width=42 style='width:32pt'></td> "+
"  <td width=42 style='width:32pt'></td> "+
"  <td width=99 style='width:74pt'></td> "+
"  <td width=91 style='width:68pt'></td> "+
" </tr> "+
" <![endif]> "+
"</table> ";
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

          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
	  			var	TBR1 = $("#TBR1").val();
	  			var	TBR2 = $("#TBR2").val();
	  			var	bz1 = $("#BZ1").val();
	  			var	bz2 = $("#BZ2").val();
	  			
	  				var CAR_NO1 =  $("#CAR_NO1").val();
					var CAR_NO2 =  $("#CAR_NO2").val();
					var CAR_NO3 =  $("#CAR_NO3").val();
					var CAR_NO4 =  $("#CAR_NO4").val();
					
					var CAR_WEIGHT1 =  $("#CAR_WEIGHT1").val();
					var CAR_WEIGHT2 =  $("#CAR_WEIGHT2").val();
					var CAR_WEIGHT3 =  $("#CAR_WEIGHT3").val();
					var CAR_WEIGHT4 =  $("#CAR_WEIGHT4").val();
					
					var SODIUMS_DOSING1 =  $("#SODIUMS_DOSING1").val();
					var SODIUMS_DOSING2 =  $("#SODIUMS_DOSING2").val();
					var SODIUMS_DOSING3 =  $("#SODIUMS_DOSING3").val();
					var SODIUMS_DOSING4 =  $("#SODIUMS_DOSING4").val();
					var SODIUMS_DOSING5 =  $("#SODIUMS_DOSING5").val();
					
					var DOS_WEIGHT1 =  $("#DOS_WEIGHT1").val();
					var DOS_WEIGHT2 =  $("#DOS_WEIGHT2").val();
					var DOS_WEIGHT3 =  $("#DOS_WEIGHT3").val();
					var DOS_WEIGHT4 =  $("#DOS_WEIGHT4").val();
					var DOS_WEIGHT5 =  $("#DOS_WEIGHT5").val();
					
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<13;i++){
             		firstArr.push($("#RPD_JZSCL_ID"+i).val());
             		firstArr.push($("#ROWRQ"+i).val());
             		firstArr.push($("#RHQZCSL"+i).val());
             		firstArr.push($("#CYTZCSL"+i).val());
             		firstArr.push($("#LT401"+i).val());
             		
             		firstArr.push($("#LT102"+i).val());
             		firstArr.push($("#LT101"+i).val());
             		firstArr.push($("#LT403"+i).val());
             		firstArr.push($("#ZYBYL1"+i).val());
             		
             		firstArr.push($("#ZYBYL2"+i).val());
             		firstArr.push($("#ZYBYL3"+i).val());
             		firstArr.push($("#PI_201"+i).val());
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
  					url : 'jzsclrb_updateDatas.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"TBR1":TBR1,"TBR2":TBR2,"BZ1":bz1,"BZ2":bz2,"RQ":RQ,"EXPORTNAME":EXPORTNAME,
  							"CAR_NO1": CAR_NO1,
							"CAR_NO2": CAR_NO2,
							"CAR_NO3": CAR_NO3,
							"CAR_NO4": CAR_NO4,
							"CAR_WEIGHT1": CAR_WEIGHT1,
							"CAR_WEIGHT2": CAR_WEIGHT2,
							"CAR_WEIGHT3": CAR_WEIGHT3,
							"CAR_WEIGHT4": CAR_WEIGHT4,
							"SODIUMS_DOSING1": SODIUMS_DOSING1,
							"SODIUMS_DOSING2": SODIUMS_DOSING2,
							"SODIUMS_DOSING3": SODIUMS_DOSING3,
							"SODIUMS_DOSING4": SODIUMS_DOSING4,
							"SODIUMS_DOSING5": SODIUMS_DOSING5,
							"DOS_WEIGHT1": DOS_WEIGHT1,
							"DOS_WEIGHT2": DOS_WEIGHT2,
							"DOS_WEIGHT3": DOS_WEIGHT3,
							"DOS_WEIGHT4": DOS_WEIGHT4,
							"DOS_WEIGHT5": DOS_WEIGHT5,
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
         	//var RPDREPORTMESSAGEID = $("#RPDREPORTMESSAGEID").val();
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行再次审核");
         	}else{
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'jzsclrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
     		if (txtDate != "" && typeof(txtDate)!="undefined"){
     			var url = "jzsclrb_exportDatas.action?txtDate="+encodeURIComponent(txtDate)+"&RPDREPORTMESSAGEID="+encodeURIComponent(RPDREPORTMESSAGEID);
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
			
				<div class="navline"  style="margin-bottom:4px; margin-top:4px;"></div>
				<div style="text-align: center;">
					<div id="tableHtml" style="OVERFLOW:auto; height: 87%;" align="center">
					
					
						
					
					</div>
				</div>
					
				

</form>
</body>
</html>
