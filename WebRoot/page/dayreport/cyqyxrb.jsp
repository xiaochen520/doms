<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>除氧器运行日报</title>
   
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
 	 <link href="../../page/dayreport/css/cyqyxrb.css" rel="stylesheet" type="text/css" /> 
    <script type="text/javascript">
    var tableval;
	var  SHR ="";
	var  RQ ="";
	var TBR2 = "";
	var TBR1 = "";
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
            [{ id: 1, text: '1#软化水站' }, 
            { id: 2, text: '2#软化水站'},
            {id: 3,text:'无盐'}
            ];
            var sbbhData =
            [
            { id: 1, text: '1', pid: 1 },
            { id: 2, text: '2', pid: 1 },
            { id: 3, text: '3', pid: 1 }, 
            { id: 4, text: '4', pid: 1},
            { id: 5, text: '5', pid: 1 },
            { id: 6, text: '6', pid: 1 },
            { id: 7, text: '7', pid: 1 }, 
            { id: 8, text: '8', pid: 1},
            { id: 9, text: '9', pid: 1 },
            { id: 10, text: '10', pid: 1 },
            
            { id: 11, text: '1', pid: 2 }, 
            { id: 12, text: '2', pid: 2},
            { id: 13, text: '3', pid: 2 },
            { id: 14, text: '4', pid: 2 },
            { id: 15, text: '5', pid: 2 }, 
            { id: 16, text: '6', pid: 2},
            { id: 17, text: '7', pid: 2 },
            { id: 18, text: '8', pid: 2 },
            { id: 19, text: '9', pid: 2 },
            
            { id: 31, text: '1', pid: 3 }, 
            { id: 32, text: '2', pid: 3}
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
				url : 'cyqrb_pageInit.action',
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
					url : 'cyqrb_searchDatas.action?nowdata='+parseInt(Math.random()*100000),
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
						     		tableval = "<table border=0 cellpadding=0 cellspacing=0 width=1276 style='border-collapse:collapse;table-layout:fixed;width:956pt'> "+
			 "<col class=xl65 width=134 style='mso-width-source:userset;mso-width-alt:4900;width:101pt'> "+
			 "<col class=xl65 width=184 style='mso-width-source:userset;mso-width-alt:6729;width:138pt'> "+
			 "<col class=xl65 width=129 style='mso-width-source:userset;mso-width-alt:4717;width:97pt'> "+
			 "<col class=xl65 width=174 style='mso-width-source:userset;mso-width-alt:6363;width:131pt'> "+
			 "<col class=xl65 width=311 style='mso-width-source:userset;mso-width-alt:11373;width:233pt'> "+
			 "<col class=xl65 width=43 span=8 style='mso-width-source:userset;mso-width-alt:1572;width:32pt'>"+
			 "<tr height=56 style='mso-height-source:userset;height:42.0pt'>"+
			 "<td colspan=5 height=56 class=xl89 width=932 style='height:42.0pt;width:700pt'>风城油田作业区供汽联合站<font class='font5'>"+$("#sclz").val()+$("#sbbh").val()+"号除氧器</font><font class='font6'>运行日报<span"+
			 " style='mso-spacerun:yes'>&nbsp;</span></font></td>"+
			
			 "</tr>"+
			 "<tr class=xl66 height=33 style='mso-height-source:userset;height:24.75pt'>"+
			 " <td colspan=2 height=33 class=xl96 style='border-right:.5pt solid black;"+
			  "height:24.75pt'>XYFC/QHSE-JL-[23]-2011-011</td>"+
			  "<td class=xl69 style='border-top:none;border-left:none'>审核人：</td>"+
			  "<td class=xl68 style='border-top:none;border-left:none'><input id='SHR' type='text' style='background:transparent;border:0px solid;width:115px;height:30px;font-size: 12pt;' disabled='disabled' value='"+SHR+"'/></td>"+
			  "<td class=xl69 style='border-top:none;border-left:none'> "+REPORTMESSAGE.RQ1+"</td>"+
		
			 "</tr>"+
			 "<tr height=56 style='mso-height-source:userset;height:42.0pt'>"+
			  "<td rowspan=2 height=94 class=xl93 width=134 style='height:70.5pt;border-top:none;width:101pt'>"+
				"  	<img   src='../../img/tablexiexian2.jpg'> "+
			"	</td>"+
			  "<td rowspan=2 class=xl90 width=184 style='border-bottom:.5pt solid black;border-top:none;width:138pt'>真空度<br> MPa</td>"+
			  "<td rowspan=2 class=xl90 width=129 style='border-top:none;width:97pt'>出口压力<br> MPa</td>"+
			  "<td rowspan=2 class=xl90 width=174 style='border-bottom:.5pt solid black; border-top:none;width:131pt'>出水含氧<br> mg/L</td>"+
			  "<td rowspan=2 class=xl90 width=311 style='border-bottom:.5pt solid black;border-top:none;width:233pt'>出水流量<br> m<font class='font7'><sup>3</sup></font><font class='font8'>/h</font></td>"+
			 
			 "</tr>"+
			 "<tr height=38 style='mso-height-source:userset;height:28.5pt'>"+
			 " <td height=38 class=xl65 style='height:28.5pt'></td>"+
			 " <td class=xl65></td>"+
			 " <td class=xl65></td>"+
			 " <td class=xl65></td>"+
			 " <td class=xl65></td>"+
			 " <td class=xl65></td>"+
			 " <td class=xl65></td>"+
			 " <td class=xl65></td>"+
			 "</tr>";
			
			var rowdisabled = "";
			 for ( var i = 0; i < DATAS.length; i++) {
			 	 rowdisabled = "";
				 if(DATAS[i].RPD_DEAERATOR_ID != null && DATAS[i].RPD_DEAERATOR_ID !="" && typeof(DATAS[i].RPD_DEAERATOR_ID)!="undefined"){
					 rowdisabled = "";
				 }else{
				 	 rowdisabled = "disabled='disabled'";
				 }
			 	tableval += "<tr class=xl65 height=29 style='mso-height-source:userset;height:21.95pt'>"+
			 	 "  <td style='display: none'><input id='RPD_DEAERATOR_ID"+i+"' type='hidden' value='"+DATAS[i].RPD_DEAERATOR_ID+"'></td>"+
				 " <td height=29 class=xl94 style='height:21.95pt;border-top:none'> <input id='ROWRQ"+i+"'  type='text' disabled='disabled'  style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;'  value="+DATAS[i].ROWRQ+"></td>"+
				 " <td class=xl70 style='border-top:none;border-left:none'>　<input id='ZKD"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' "+rowdisabled+" value="+DATAS[i].ZKD+"></td>"+
				 " <td class=xl68><input id='CYQ_CKYL"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' "+rowdisabled+" value="+DATAS[i].CYQ_CKYL+"></td>"+
				 " <td class=xl71 style='border-top:none;border-left:none'><input id='CYQ_DCLY"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' "+rowdisabled+" value="+DATAS[i].CYQ_DCLY+"></td>"+
				 " <td class=xl68 style='border-top:none;border-left:none'><input id='CYQ_DCLL1"+i+"'  type='text'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:120px;line-height:25px;height:30px;font-size: 10pt;text-align:center;' "+rowdisabled+" value="+DATAS[i].CYQ_DCLL1+"></td>"+
				 "</tr>";
			 }
			
			tableval += "<tr class=xl65 height=29 style='mso-height-source:userset;height:21.75pt'>"+
			 " <td height=29 class=xl79 width=134 style='height:21.75pt;border-top:none;"+
			 " width:101pt'>记事：白班</td>"+
			 " <td colspan=2 class=xl86 width=313 style='border-right:.5pt solid black; width:235pt'>　</td>"+
			 " <td colspan=2 class=xl79 width=485 style='border-right:.5pt solid black;  border-left:none;width:364pt'>记事：夜班</td>"+
			 "</tr>"+
			 "<tr class=xl65 height=29 style='mso-height-source:userset;height:21.75pt'>"+
			 " <td colspan=3 rowspan=3 height=87 class=xl82 width=447 style='border-right:.5pt solid black;height:65.25pt;width:336pt'>　<input id='BZ1' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+BZ1+"'/> </td>"+
			 " <td colspan=2 rowspan=3 class=xl82 width=485 style='border-right:.5pt solid black;width:364pt'>　<input id='BZ2' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+BZ2+"'/> </td>"+
			 "</tr>"+
			 
			 "<tr class=xl65 height=29 style='mso-height-source:userset;height:21.75pt'>"+
			 "</tr>"+
			 "<tr class=xl65 height=29 style='mso-height-source:userset;height:21.75pt'>"+
			 "</tr>"+
			 "<tr class=xl65 height=29 style='mso-height-source:userset;height:21.75pt'>"+
			 " <td height=29 class=xl80 style='height:21.75pt'>填表人：</td>"+
			 " <td colspan=2 class=xl85 style='border-right:.5pt solid black' align='left'>　<input id='TBR1' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+TBR1+"'/> </td>"+
			 " <td class=xl80 style='border-left:none'>填表人：</td>"+
			 " <td class=xl81 align='left'> <input id='TBR2' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+TBR2+"'/> 　</td>"+
		
			 "</tr>"+
			 " </table>";
						     								     	
						     								     	
									//↑
						     		$("#tableHtml").html(tableval);	
												 //alert(RPDREPORTMESSAGEID);
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
             		firstArr.push($("#RPD_DEAERATOR_ID"+i).val());
             		firstArr.push($("#ROWRQ"+i).val());
             		firstArr.push($("#ZKD"+i).val());
             		firstArr.push($("#CYQ_CKYL"+i).val());
             		firstArr.push($("#CYQ_DCLL1"+i).val());
             		firstArr.push($("#CYQ_DCLY"+i).val());
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'cyqrb_updateDatas.action?nowdata='+parseInt(Math.random()*100000),
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
         	 var sclz1 = $("#sclz").val();
        	 var sbbh1 = $("#sbbh").val();
        	 
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行再次审核");
         	}else{
         		if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'cyqrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"sclz1":sclz1,"sbbh1":sbbh1},
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
     			var url = "cyqrb_exportDatas.action?txtDate="+encodeURIComponent(txtDate)+"&sclz="+encodeURIComponent(sclz)+"&sbbh="+encodeURIComponent(sbbh)+"&RPDREPORTMESSAGEID="+encodeURIComponent(RPDREPORTMESSAGEID);
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
						 <td align="left" class="l-table-edit-td" style="width:80px">号除氧器</td>
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
