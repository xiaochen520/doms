<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>一号稠油联合处理站日生产数据</title>  
   
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
    <link href="../../lib/css/fxjl.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/js/common.js" type="text/javascript"></script> 
          
   <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
    var tableval;

	var  SHR ="";
	var  RQ ="";
	var ID = "";
	var rwsq = "";
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
				url : 'cyfxsc1_pageInit.action',
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


       function onSubmit()
        {
        	 jQuery.ajax({
					type : 'post',
					url : 'cyfxsc1_searchFxjl1.action?txtDate='+$("#txtDate").val(),
					success : function(data) { 
					if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');

							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
									if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
									    var U2OILS = outData.JSONDATA.U2OILS;	
									    
									   
									    								   									    
//									    var U1S1SS = outData.JSONDATA.U1S1SS;									    
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									    if (U2OILS != null && typeof(U2OILS)!="undefined"){
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
									    	//↓
						    	$("#tableHtml").html('');
						     		tableval ="<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:"+
					     		 "collapse;table-layout:fixed;width:1400pt'>"+
					     		 "<tr height=28 style='mso-height-source:userset;height:21.0pt'>"+
					     		 " <td colspan=21 height=28 class=xl71 style='height:21.0pt'"+
					     		  ">一号稠油联合处理站日生产数据</td>"+
					     		" </tr>"+	
							     		"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
 										" <td class=xl65>值班人：</td>"+
											"<td colspan=6 class=xl65><input id='ZBR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled'/></td> "+							     		
							     		" <td  class=xl65>审核：</td>"+
											"<td colspan=6 class=xl65><input id='SHR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+SHR+"'/></td> "+							     		
							     		" <td  class=xl65>日期：</td>"+
										" <td colspan=6 class=xl65><input id='RQ' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+RQ+"'/></td> "+							     		
							     		 "</tr>"+			
							     		 
							     		"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
							     		 " <td bgColor=#CCCCFF rowspan=2 class=xl65 style='border-bottom:.5pt solid black;border-top:none'>日期</td>"+
							     		 " <td bgColor=#CCCCFF colspan=2 class=xl65 style='border-left:none'>净化罐</td>"+				     		 				     		 
							     		" <td bgColor=#CCCCFF class=xl65 style='border-left:none'>提升泵</td>"+
							     		" <td bgColor=#CCCCFF  class=xl65 style='border-left:none'>污油</td>"+
							     		 " <td bgColor=#CCCCFF  class=xl65 style='border-left:none'>分线掺柴油</td>"+
							     		" <td bgColor=#CCCCFF  class=xl65 style='border-left:none'>SAGD采油一站</td>"+
							     		" <td bgColor=#CCCCFF colspan=2 colspan=3 class=xl65 style='border-left:none'>盘库数据</td>"+
							     		 " <td bgColor=#CCCCFF colspan=12 colspan=8 class=xl65 style='border-left:none'>备注</td>"+
							     		"</tr>"+							     		
							     				"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
							     		 " <td bgColor=#CCCCFF class=xl65 style='border-bottom:.5pt solid black;border-top:none'>油量（t）</td>"+	
							     		 " <td bgColor=#CCCCFF  class=xl65 style='border-left:none'>底水（t）</td>"+
							     		 " <td bgColor=#CCCCFF  class=xl65 style='border-left:none'>（t）</td>"+
							     		 " <td bgColor=#CCCCFF  class=xl65 style='border-left:none'>（t）</td>"+							     		  
							     		  " <td bgColor=#CCCCFF   class=xl65 style='border-left:none'>（t）</td>"+							     		  
							     		   " <td bgColor=#CCCCFF class=xl65 style='border-left:none'>水量（t）</td>"+
							     		 " <td bgColor=#CCCCFF  class=xl65 style='border-left:none'>污油盘库（t）</td>"+
							     		 " <td bgColor=#CCCCFF class=xl65 style='border-left:none'>盘库（t）</td>"+
							     		 " <td bgColor=#CCCCFF colspan=12 class=xl65 style='border-left:none'>分线仪表及自动化采集情况</td>"+							     		  
							     	
							     		"</tr>";							     									     								     		
							     		
							     		
							     		var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
							     	for ( var i = 0; i < U2OILS.length; i++) {
							     	
							     		tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
								     		   "<td style='display: none'><input id='rpd_cy1_sc_messure_id' type='hidden' value='"+U2OILS[i].rpd_cy1_sc_messure_id+"'></td>"+							
								     		 "<td id='report_date' height=24 class=xl65  style='height:18.0pt;border-top:none'>"+U2OILS[i].report_date+"</td>"+	
								     		  "<td class=xl65  style='border-bottom:.5pt solid black;border-top:none'><input id='jhg_youl'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[i].jhg_youl+"></td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'><input id='jhg_ds'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[i].jhg_ds+"></td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'><input id='tsb'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[i].tsb+"></td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'><input id='wuy'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[i].wuy+"></td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'><input id='fxccy'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[i].fxccy+"></td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'><input id='ftq_s1s'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[i].ftq_s1s+"></td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'><input id='wuypk'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[i].wuypk+"></td>"+							     		 
								     		  "<td class=xl65  style='border-top:none;border-left:none'><input id='pank'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:18.0px;font-size: 10pt;text-align:center;' value="+U2OILS[i].pank+"></td>"+
								     		 "<td class=xl65 colspan=12 style='border-top:none;border-left:none;text-align:left'><input id='remark'  type='text'  "+disStr+"    onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:1150px;line-height:25px;height:18.0px;font-size: 10pt;text-align:left;' value="+U2OILS[i].remark+"></td>"+

											"</tr>";
						     	 	 }	
					     	 	 } 

					     		"</table>";					     								     	
									//↑
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
        	var	RQ = $("#RQ").val();    		
          	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
          		$.ligerDialog.error("数据已审核过，不能进行修改");
          	}else{
          		
          		var firstArr = [];
              	var firstStr = "";																												
             		firstArr.push($("#rpd_cy1_sc_messure_id").val());
             		firstArr.push($("#report_date").val());
             		firstArr.push($("#jhg_youl").val());
             		firstArr.push($("#jhg_ds").val());
             		firstArr.push($("#tsb").val());
             		firstArr.push($("#wuy").val());
             		firstArr.push($("#wuypk").val());
             		firstArr.push($("#fxccy").val());
             		firstArr.push($("#pank").val());
             		firstArr.push($("#ftq_s1s").val());
             		firstArr.push($("#remark").val());
             		
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
              
                var myday = new Date().Format("yyyy-MM-dd");
             	if (modifyDataFlag(myday)) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'cyfxsc1_update.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"RQ":RQ,"firstStr":firstStr},
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
         	//	if (chekAduitByDate($("#txtDate").val())) {
         		 jQuery.ajax({
 					type : 'post',
 					url : 'cyfxsc1_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
////         		} else {
//    				$.ligerDialog.error("审核时间无效,不能进行审核！");
//    			}
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
            .datege{mso-style-parent:style0;
				 mso-number-format:"Short Date";
				 text-align:center;
				 vertical-align:middle;
				 border:.5pt solid windowtext;
				 white-space:nowrap;}
		.searchtitle{ padding-left:28px; position:relative;}
		.searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
		.searchtitle span{ font-size:14px; font-weight:bold;}
		.searchtitle .togglebtn{ position:absolute; top:6px; right:15px; background:url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
		.searchtitle .togglebtn-down{ background-position:0px -10px;}
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:1px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
    
    
    </style>
</head>
<body style="padding:10px"> 
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table>
					<tr>	
		                <td align="right" style="width: 60px">日期：</td>
				
				<td><input type="text" id="txtDate" ltype="date"/></td>
				
						
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						
<!-- 						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td> -->
<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						</tr>
				
				</table>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		


<div id="tableHtml" align=center x:publishsource="Excel" style="height:450px;width:98%;height: 89%; overflow:auto;text-align: center;">

	</div>
    
  </form>
    
</body>
</html>
