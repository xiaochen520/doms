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
	var  LOG1;
	var  LOG2;
	var LOG3;
	var  waterA;
	var  waterB;
	var  waterC;
	var  waterD;
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
            
             var clzData = 
            [{ id: 1, text: '集中水'}, 
             { id: 2, text: '1#软化水站'},
             { id: 3, text:'2#软化水站'}
            ];
            var rhqData =
            [
            { id: 1, text: '1', pid: 1 },
            { id: 2, text: '2', pid: 1 },
            { id: 3, text: '3', pid: 1 }, 
 
            
            { id: 11, text: '一期1', pid: 2 }, 
            { id: 12, text: '一期2', pid: 2},
            { id: 13, text: '一期3', pid: 2 },
            { id: 14, text: '一期4', pid: 2 },
            { id: 15, text: '一期5', pid: 2 }, 
            { id: 16, text: '一期6', pid: 2},
            
            { id: 17, text: '二期1', pid: 2 },
            { id: 18, text: '二期2', pid: 2 },
            { id: 19, text: '二期3', pid: 2 },
            { id: 17, text: '二期4', pid: 2 },
            { id: 18, text: '二期5', pid: 2 },
            { id: 19, text: '二期6', pid: 2 },
            { id: 20, text: '二期7', pid: 2 },
            { id: 21, text: '二期8', pid: 2 },
            { id: 22, text: '二期9', pid: 2 },
            { id: 23, text: '二期10', pid: 2 },
            { id: 24, text: '二期11', pid: 2 },
			{ id: 25, text: '二期12', pid: 2 },

            { id: 25, text: '三期1', pid: 2 },
            { id: 26, text: '三期2', pid: 2 },
            { id: 27, text: '三期3', pid: 2 },
            { id: 28, text: '三期4', pid: 2 },
            { id: 29, text: '三期5', pid: 2 },
            { id: 30, text: '三期6', pid: 2 },
            
            { id: 31, text: '1', pid: 3 }, 
            { id: 32, text: '2', pid: 3},
            { id: 33, text: '3', pid: 3 }, 
            { id: 34, text: '4', pid: 3},
            { id: 35, text: '5', pid: 3 }, 
            { id: 36, text: '6', pid: 3},
            { id: 37, text: '7', pid: 3 }, 
            { id: 38, text: '8', pid: 3},
            { id: 39, text: '9', pid: 3 }, 
            { id: 40, text: '10', pid: 3},
            { id: 41, text: '11', pid: 3 }, 
            { id: 42, text: '12', pid: 3},
            { id: 43, text: '13', pid: 3 }, 
            { id: 44, text: '14', pid: 3},
            
            { id: 45, text: '216型1', pid: 3},
            { id: 46, text: '216型2', pid: 3 }, 
            { id: 47, text: '216型3', pid: 3},
			{ id: 48, text: '216型4', pid: 3}
            ];  
            
              $("#rhq").ligerComboBox({ data: null,isMultiSelect: false,  selectBoxWidth: 130,selectBoxHeight: 350 });
              $("#clz").ligerComboBox({
                data: clzData, isMultiSelect: false,selectBoxWidth:150,
                onSelected: function (newvalue)
                {
                    var newData = new Array();
                    for (i = 0; i < rhqData.length; i++)
                    {
                        if (rhqData[i].pid == newvalue)
                        {
                            newData.push(rhqData[i]);
                        }
                    }
                    liger.get("rhq").setData(newData);
                }
            });
            
            
           	//获取报表及功能按钮ｊｓ
            jQuery.ajax({
				type : 'post',
				url : 'rhqyxrb_pageInit.action',
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
					url : 'rhqyxrb_searchDatas.action?nowdata='+parseInt(Math.random()*100000),
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
									    var disData = outData.JSONDATA.syjyx;
									    var WATERMESSAGE = outData.JSONDATA.WATERMESSAGE;
									  //  var waterArr = outData.JSONDATA.waterArr; 
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
									     			 if(WATERMESSAGE.LOG1 != null && typeof(WATERMESSAGE.LOG1)!="undefined"){
										     			  LOG1 =WATERMESSAGE.LOG1;
										     			}else{
										     				LOG1 = "";
										     			}
									     			 if(WATERMESSAGE.LOG2 != null && typeof(WATERMESSAGE.LOG2)!="undefined"){
										     			  LOG2 =WATERMESSAGE.LOG2;
										     			}else{
										     				LOG2 = "";
										     			}
									     			 if(WATERMESSAGE.LOG3 != null && typeof(WATERMESSAGE.LOG3)!="undefined"){
										     			  LOG3 =WATERMESSAGE.LOG3;
										     			}else{
										     				LOG3 = "";
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
									     			 if(WATERMESSAGE.ZBR1 != null && typeof(WATERMESSAGE.ZBR1)!="undefined"){
									     				ZBR1 =WATERMESSAGE.ZBR1;
										     			}else{
										     				ZBR1 = "";
										     			}
									     			 if(WATERMESSAGE.ZBR2 != null && typeof(WATERMESSAGE.ZBR2)!="undefined"){
									     				ZBR2 =WATERMESSAGE.ZBR2;
										     			}else{
										     				ZBR2 = "";
										     			}
									     			
										     		$("#tableHtml").html('');
tableval ="<table border=0 cellpadding=0 cellspacing=0 width=2337 style='border-collapse:"+
"	 collapse;table-layout:fixed;width:1758pt'>"+
"	 <col width=105 style='mso-width-source:userset;mso-width-alt:3840;width:79pt'>"+
"	 <col class=xl66 width=78 style='mso-width-source:userset;mso-width-alt:2852;"+
"	 width:59pt'>"+
"	 <col class=xl66 width=85 style='mso-width-source:userset;mso-width-alt:3108;"+
"	 width:64pt'>"+
"	 <col class=xl66 width=84 style='mso-width-source:userset;mso-width-alt:3072;"+
"	 width:63pt'>"+
"	 <col class=xl66 width=82 style='mso-width-source:userset;mso-width-alt:2998;"+
"	 width:62pt'>"+
"	 <col class=xl66 width=78 span=8 style='mso-width-source:userset;mso-width-alt:"+
"	 2852;width:59pt'>"+
"	 <col width=75 style='mso-width-source:userset;mso-width-alt:2742;width:56pt'>"+
"	 <col width=84 style='mso-width-source:userset;mso-width-alt:3072;width:63pt'>"+
"	 <col width=69 style='mso-width-source:userset;mso-width-alt:2523;width:52pt'>"+
"	 <col width=91 style='mso-width-source:userset;mso-width-alt:3328;width:68pt'>"+
"	 <col width=64 span=15 style='width:48pt'>"+


"<tr height=56 class=xl67 style='mso-height-source:userset;height:42.0pt'>"+
"<td colspan=17 height=36 class=xl79 width=1377  style='height:42.0pt;width:700pt'>风城油田作业区供汽联合站<font class='xl7912'>"+$("#clz").val()+$("#rhq").val()+"号软化器</font><font class='xl7912'>运行日报<span"+
" style='mso-spacerun:yes'>&nbsp;</span></font></td>"+

"</tr>"+
"	 <tr class=xl67 height=31 style='mso-height-source:userset;height:23.25pt'>"+
"	  <td colspan=3 height=31 class=xl98 style='height:23.25pt'>XYFC/QHSE-JL-[23]-2011-008</td>"+
"	  <td colspan=4 class=xl86 width=322 style='border-left:none;width:243pt'>　</td>"+
"	  <td colspan=3 class=xl85 width=234 style='border-left:none;width:177pt'>审核人：</td>"+
"	  <td colspan=3 class=xl84 style='border-left:none'>"+WATERMESSAGE.SHR+"</td>"+
"	  <td colspan=4 class=xl84 style='border-left:none'>"+WATERMESSAGE.RQ+"</td>"+

"	 </tr>"+


"<tr>"+
"	    <td height=24 class=xl70 width=105 style='height:18.0pt;border-top:none;"+
"	    width:79pt'><span style='mso-spacerun:yes'>&nbsp; </span>项</td>"+
"	  </span></td>"+

"	  <td colspan=4 rowspan=2 class=xl80>A<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
"	  </span><font class='font9'>组</font></td>"+
"	  <td colspan=4 rowspan=2 class=xl80>B<span"+
"	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
"	  </span><font class='font9'>组</font></td>"+
"	  <td colspan=4 rowspan=2 class=xl80>C<span"+
"	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
"	  </span><font class='font9'>组</font></td>"+
"	  <td colspan=4 rowspan=2 class=xl80>D<span"+
"	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
"	  </span><font class='font9'>组</font></td>"+

"	 </tr>"+

"	 <tr class=xl65 height=22 style='mso-height-source:userset;height:16.5pt'>"+
	"<td height=22 class=xl71 style='height:16.5pt'><span"+
"  style='mso-spacerun:yes'>&nbsp;</span><font class='font9'>数</font><font"+
"  class='font10'><span"+
"  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
"  </span></font><font class='font9'>目</font></td>"+
"	 </tr>"+
"	 <tr class=xl65 height=22 style='mso-height-source:userset;height:16.9pt'>"+
"	  <td height=22 class=xl71 style='height:16.9pt'><span"+
"	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><font"+
"	  class='font9'>据</font><font class='font10'><span"+
"	  style='mso-spacerun:yes'>&nbsp;</span></font></td>"+
"	  <td rowspan=3 class=xl72 width=78 style='border-top:none;width:59pt'>一级硬度<br>"+
"	    <font class='font10'>mg/l</font></td>"+
"	  <td rowspan=3 class=xl72 width=85 style='border-top:none;width:64pt'>二级硬度<br>"+
"	    <font class='font10'>mg/l</font></td>"+
"	  <td rowspan=3 class=xl87 width=84 style='border-bottom:.5pt solid black;"+
"	  border-top:none;width:63pt'>瞬时流量<br>"+
"	    <font class='font9'>m</font><font class='font9'>3</font><font class='font9'>/h</font></td>"+
"	  <td rowspan=3 class=xl87 width=82 style='border-bottom:.5pt solid black;"+
"	  border-top:none;width:62pt'>累积流量<br>"+
"	    <font class='font9'>m3/h</font></td>"+
"	  <td rowspan=3 class=xl72 width=78 style='border-top:none;width:59pt'>一级硬度<br>"+
"	    <font class='font10'>mg/l</font></td>"+
"	  <td rowspan=3 class=xl72 width=78 style='border-top:none;width:59pt'>二级硬度<br>"+
"	    <font class='font10'>mg/l</font></td>"+
"	  <td rowspan=3 class=xl87 width=78 style='border-bottom:.5pt solid black;"+
"	  border-top:none;width:59pt'>瞬时流量<br>"+
"	    <font class='font9'>m</font><font class='font9'>3</font><font class='font9'>/h</font></td>"+
"	  <td rowspan=3 class=xl87 width=78 style='border-bottom:.5pt solid black;"+
"	  border-top:none;width:59pt'>累积流量<br>"+
"	    <font class='font9'>m3/h</font></td>"+
"	  <td rowspan=3 class=xl72 width=78 style='border-top:none;width:59pt'>一级硬度<br>"+
"	    <font class='font10'>mg/l</font></td>"+
"	  <td rowspan=3 class=xl72 width=78 style='border-top:none;width:59pt'>二级硬度<br>"+
"	    <font class='font10'>mg/l</font></td>"+
"	  <td rowspan=3 class=xl87 width=78 style='border-bottom:.5pt solid black;"+
"	  border-top:none;width:59pt'>瞬时流量<br>"+
"	    <font class='font9'>m</font><font class='font9'>3</font><font class='font9'>/h</font></td>"+
"	  <td rowspan=3 class=xl87 width=78 style='border-bottom:.5pt solid black;"+
"	  border-top:none;width:59pt'>累积流量<br>"+
"	    <font class='font9'>m3/h</font></td>"+
"	  <td rowspan=3 class=xl72 width=75 style='border-top:none;width:56pt'>一级硬度<br>"+
"	    <font class='font10'>mg/l</font></td>"+
"	  <td rowspan=3 class=xl72 width=84 style='border-top:none;width:63pt'>二级硬度<br>"+
"	    <font class='font10'>mg/l</font></td>"+
"	  <td rowspan=3 class=xl87 width=69 style='border-bottom:.5pt solid black;"+
"	  border-top:none;width:52pt'>瞬时流量<br>"+
"	    <font class='font9'>m</font><font class='font9'>3</font><font class='font9'>/h</font></td>"+
"	  <td rowspan=3 class=xl87 width=91 style='border-bottom:.5pt solid black;"+
"	  border-top:none;width:68pt'>累积流量<br>"+
"	    <font class='font9'>m3/h</font></td>"+

"	 </tr>"+
"	 <tr class=xl65 height=22 style='mso-height-source:userset;height:16.9pt'>"+
"	  <td height=22 class=xl73 style='height:16.9pt'>时</td>"+

"	 </tr>"+
"	 <tr class=xl65 height=19 style='height:14.25pt'>"+
"	  <td height=19 class=xl74 style='height:14.25pt'><span"+
"	  style='mso-spacerun:yes'>&nbsp;&nbsp; </span>间</td>"+

"	 </tr>";
var rowdisabled = "";
 for ( var i = 0; i < disData.length; i++) {
	 rowdisabled = "";
	 if(disData[i].RPD_MOLLIFIER_ID != null && disData[i].RPD_MOLLIFIER_ID !="" && typeof(disData[i].RPD_MOLLIFIER_ID)!="undefined"){
		 rowdisabled = "";
	 }else{
		 rowdisabled = "disabled='disabled'";
	 }
	//alert(disData.RPD_MOLLIFIER_ID)
	tableval += "	 <tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
"	  <td height=23 class=xl75 width=105 style='height:17.25pt;width:79pt'><input id='BBSJ"+i+"' onkeyup='checkData(this)' disabled='disabled' type='text' style='background:transparent;border:0px solid;width:120px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+disData[i].BBSJ+"'/></td>"+
"	  <td class=xl76 width=55 style='border-top:none;border-left:none;width:64pt'><input id='SCL_AHARDNESS1"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_AHARDNESS1+"'/></td>"+
"	  <td class=xl72 width=55 style='border-top:none;border-left:none;width:63pt'><input id='SCL_AHARDNESS2"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_AHARDNESS2+"'/></td>"+
"	  <td class=xl72 width=55 style='border-top:none;border-left:none;width:62pt'><input id='SCL_ALL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_ALL+"'/></td>"+
"	  <td class=xl76 width=55 style='border-top:none;border-left:none;width:59pt'><input id='SCL_ALLLJ"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_ALLLJ+"'/></td>"+
"	  <td class=xl76 width=55 style='border-top:none;border-left:none;width:59pt'><input id='SCL_BHARDNESS1"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_BHARDNESS1+"'/></td>"+
"	  <td class=xl72 width=55 style='border-top:none;border-left:none;width:59pt'><input id='SCL_BHARDNESS2"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_BHARDNESS2+"'/></td>"+
"	  <td class=xl72 width=78 style='border-top:none;border-left:none;width:59pt'><input id='SCL_BLL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_BLL+"'/></td>"+
"	  <td class=xl76 width=78 style='border-top:none;border-left:none;width:59pt'><input id='SCL_BLLLJ"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_BLLLJ+"'/></td>"+
"	  <td class=xl76 width=78 style='border-top:none;border-left:none;width:59pt'><input id='SCL_CHARDNESS1"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_CHARDNESS1+"'/></td>"+
"	  <td class=xl72 width=78 style='border-top:none;border-left:none;width:59pt'><input id='SCL_CHARDNESS2"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_CHARDNESS2+"'/></td>"+
"	  <td class=xl72 width=78 style='border-top:none;border-left:none;width:59pt'><input id='SCL_CLL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_CLL+"'/></td>"+
"	  <td class=xl76 width=75 style='border-top:none;border-left:none;width:56pt'><input id='SCL_CLLLJ"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_CLLLJ+"'/></td>"+
"	  <td class=xl76 width=84 style='border-top:none;border-left:none;width:63pt'><input id='SCL_DHARDNESS1"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_DHARDNESS1+"'/></td>"+
"	  <td class=xl72 width=69 style='border-top:none;border-left:none;width:52pt'><input id='SCL_DHARDNESS2"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_DHARDNESS2+"'/></td>"+
"	  <td class=xl72 width=91 style='border-top:none;border-left:none;width:68pt'><input id='SCL_DLL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_DLL+"'/></td>"+
"	  <td class=xl72 width=91 style='border-top:none;border-left:none;width:68pt'><input id='SCL_DLLLJ"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:83px;line-height:30px;height:30px;font-size: 12pt;text-align:center;'  "+rowdisabled+" value='"+disData[i].SCL_DLLLJ+"'/></td>"+
"		<td width=69 style='border-top:none;border-left:none;width:52pt'><input type='hidden' id='RPD_MOLLIFIER_ID"+i+"' value='"+disData[i].RPD_MOLLIFIER_ID+"'/>　</td>"+	    			

"	 </tr>";
}
tableval += "<tr height=23 style='mso-height-source:userset;height:17.25pt'>"+
"	  <td height=23 class=xl78 width=105 style='height:17.25pt;border-top:none;"+
"	  width:79pt'>周期制水量</td>"+
"<td colspan=4 height=23 class=xl81 width=1272 style='border-right:.5pt solid black;"+
"height:17.25pt;border-left:none;width:959pt'><input id='waterA'  type='text' style='background:transparent;border:0px solid;width:300px;line-height:30px;height:30px;font-size: 12pt;text-align:left;' value='"+WATERMESSAGE.waterA+"'/></td>"+

"<td colspan=4 height=23 class=xl81 width=1272 style='border-right:.5pt solid black;"+
"height:17.25pt;border-left:none;width:959pt'><input id='waterB'  type='text' style='background:transparent;border:0px solid;width:300px;line-height:30px;height:30px;font-size: 12pt;text-align:left;' value='"+WATERMESSAGE.waterB+"'/></td>"+

"<td colspan=4 height=23 class=xl81 width=1272 style='border-right:.5pt solid black;"+
"height:17.25pt;border-left:none;width:959pt'><input id='waterC'  type='text' style='background:transparent;border:0px solid;width:300px;line-height:30px;height:30px;font-size: 12pt;text-align:left;' value='"+WATERMESSAGE.waterC+"'/></td>"+

"<td colspan=4 height=23 class=xl81 width=1272 style='border-right:.5pt solid black;"+
"height:17.25pt;border-left:none;width:959pt'><input id='waterD'  type='text' style='background:transparent;border:0px solid;width:300px;line-height:30px;height:30px;font-size: 12pt;text-align:left;' value='"+WATERMESSAGE.waterD+"'/></td>"+

"	 </tr>"+
"	 <tr rowspan=3 height=23 style='mso-height-source:userset;height:17.25pt'>"+
"	  <td rowspan=3 height=69 class=xl93 width=105 style='height:51.75pt;"+
"	  border-top:none;width:79pt'>设备再生<br>情况记录：</td>"+
"	  <td colspan=16 class=xl81 width=1272 style='border-right:.5pt solid black;border-left:none;width:959pt'><input id='LOG1'  type='text' style='background:transparent;border:0px solid;width:900px;line-height:30px;height:30px;font-size: 12pt;text-align:left;' value='"+WATERMESSAGE.LOG1+"'/></td>"+

"	 </tr>"+




" <tr height=20 style='mso-height-source:userset;height:15.0pt'> "+
"  <td height=20 class=xl9316028 width=85 style='height:15.0pt;width:64pt'>白班记事：<br> "+
"    <br>"+
"    <br>"+
"    <br>"+
"    <br>"+
"    <font class='font1116028'><span "+
"  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></font></td>"+
"  <td colspan=8 class=xl9416028 width=615 style='border-right:.5pt solid black;"+
"  width:461pt'>　</td>"+
"  <td colspan=2 class=xl9416028 width=144 style='width:108pt'>夜班记事：<br>"+
"    <br>"+
"    <br>"+
"    <br>"+
"    <br>"+
"    </td>"+
"  <td colspan=6 class=xl9416028 width=432 style='border-right:.5pt solid black;"+
"  width:324pt'>　</td>"+
" </tr>"+
" <tr height=110 style='mso-height-source:userset;height:82.5pt'>"+
"  <td height=110 class=xl9516028 width=85 style='height:82.5pt;width:64pt'>　</td>"+
"  <td colspan=8 class=xl9916028 width=615 style='border-right:.5pt solid black;"+
"  width:461pt'><input id='BZ1' type='text' style='background:transparent;border:0px solid;font-size: 16pt;text-align:left;width: 560; height: 40; padding-bottom: 0px;margin-bottom: 0px; font-size: 13px; border: 0; overflow: hidden;' value='"+WATERMESSAGE.BZ1+"'/><br></td>"+
"  <td colspan=2 class=xl6416028 width=144 style='width:108pt'></td>"+
"  <td colspan=6 class=xl9916028 width=432 style='border-right:.5pt solid black;"+
"  width:324pt'><input id='BZ2' type='text' style='background:transparent;border:0px solid;font-size: 16pt;text-align:left;width: 560; height: 40; padding-bottom: 0px;margin-bottom: 0px; font-size: 13px; border: 0; overflow: hidden;' value='"+WATERMESSAGE.BZ2+"'/><br></td>"+
" </tr>"+
" <tr height=19 style='height:14.25pt'>"+
"  <td height=19 class=xl9616028 width=85 style='height:14.25pt;width:64pt'>填表人：</td>"+
"  <td colspan=8 class=xl10116028 width=615 style='border-right:.5pt solid black;"+
"  width:461pt'>　<input id='TBR1' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+WATERMESSAGE.TBR1+"'/></td>"+
"  <td colspan=2 class=xl9716028 width=144 style='width:108pt'>填表人：</td>"+
"  <td colspan=6 class=xl10116028 width=432 style='border-right:.5pt solid black;"+
"  width:324pt'>　<input id='TBR2' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+WATERMESSAGE.TBR2+"'/></td>"+
" </tr>"+

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
			//周期制水量
			var waterA = $("#waterA").val();
			var waterB = $("#waterB").val();
			var waterC = $("#waterC").val();
			var waterD = $("#waterD").val();
			
			// 设备再生记录
			var LOG1 = $("#LOG1").val();
			var LOG2 = $("#LOG2").val();
			var LOG3 = $("#LOG3").val();
			 //白班 夜班记事
  			var	BZ1 = $("#BZ1").val();
  			var	BZ2 = $("#BZ2").val();
  			 //白班 夜班 填报人
  			var	TBR1 = $("#TBR1").val();
  			var	TBR2 = $("#TBR2").val();
  			
  			var clz = $("#clz").val();
  			var rhq = $("#rhq").val();
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<13;i++){
             		firstArr.push($("#RPD_MOLLIFIER_ID"+i).val());
             		firstArr.push($("#BBSJ"+i).val());
             		firstArr.push($("#SCL_AHARDNESS1"+i).val());
             		firstArr.push($("#SCL_AHARDNESS2"+i).val());
             		firstArr.push($("#SCL_ALL"+i).val());
             		firstArr.push($("#SCL_ALLLJ"+i).val());

             		firstArr.push($("#SCL_BHARDNESS1"+i).val());
             		firstArr.push($("#SCL_BHARDNESS2"+i).val());
             		firstArr.push($("#SCL_BLL"+i).val());
             		firstArr.push($("#SCL_BLLLJ"+i).val());
             		
             		firstArr.push($("#SCL_CHARDNESS1"+i).val());
             		firstArr.push($("#SCL_CHARDNESS2"+i).val());
             		firstArr.push($("#SCL_CLL"+i).val());
             		firstArr.push($("#SCL_CLLLJ"+i).val());
             		
             		firstArr.push($("#SCL_DHARDNESS1"+i).val());
             		firstArr.push($("#SCL_DHARDNESS2"+i).val());
             		firstArr.push($("#SCL_DLL"+i).val());
             		firstArr.push($("#SCL_DLLLJ"+i).val());
             		
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'rhqyxrb_updateDatas.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"WATERMESSAGEID":WATERMESSAGEID,"waterA":waterA,"waterB":waterB,"waterC":waterC,"waterD":waterD,"LOG1":LOG1,"LOG2":LOG2,"LOG3":LOG3,
   							"BZ1":BZ1,"BZ2":BZ2,"TBR1":TBR1,"TBR2":TBR2,"clz":clz,"rhq":rhq,"RQ":RQ,"firstStr":firstStr},
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
     		var clz=$("#clz").val();
     		var rhq=$("#rhq").val();
     		
     		//var url = 'cyqrb_exportDatas.action?nowdata='+parseInt(Math.random()*100000);
     		var url = "rhqyxrb_exportExcel.action?txtDate="+encodeURIComponent(txtDate)+"&clz="+encodeURIComponent(clz)+"&rhq="+encodeURIComponent(rhq)+"&WATERMESSAGEID="+encodeURIComponent(WATERMESSAGEID);
     		
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
						 
		                
						<td><input type="text" id="clz" ltype="sclz"/></td>
						 <td align="left" class="l-table-edit-td" style="width:80px">水处理站</td>
						 <td><input type="text" id="rhq" ltype="rhq"/></td>
						 <td align="left" class="l-table-edit-td" style="width:120px">号软化器 </td>
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