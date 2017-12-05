<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.echo.beans.CCSCL" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">
<head><title>采出水处理</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    <meta name=ProgId content=Excel.Sheet />
	<meta name=Generator content="Microsoft Excel 14" />
	<link rel=File-List href="file3272.files/filelist.xml" />
	<link href="../../lib/css/ccscl.css" rel="stylesheet" type="text/css" /> 
	
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
     <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
     
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerPopupEdit.js"></script>
  
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    
    <script src="../../lib/json2.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
 	<script src="../../lib/checkDate.js" type="text/javascript"></script>
 	<script src="../../lib/U2_check.js" type="text/javascript"></script>
 	<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
 	
 	<script language="javascript" type="text/javascript" src="../../lib/WdatePicker.js"></script>
    <script type="text/javascript">
    var tableHtml;
    var  RPDREPORTMESSAGEID ="";
	var  SHR ="";
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
				url : 'ccscl_pageInit.action',
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
		//if (onSearchByDate($("#reportDate").val())){
      	 jQuery.ajax({
			type : 'post',
			url : 'ccscl_searchCCSCLRPD.action?reportDate='+$("#reportDate").val(),
			success : function(data) { 
			var  ZBR1 ="";
			//var  SHR ="";
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
							    var CCSCL = outData.JSONDATA.CCSCL;
							    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
							    if (CCSCL != null && typeof(CCSCL)!="undefined"){
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
					    		$("#tableHtml").html('');
					    		tableHtml = "<table border=0 cellpadding=0 cellspacing=0 width=1017 style='border-collapse:"+
									 "collapse;table-layout:fixed;width:763pt;overflow:auto;margin-left:50px'>"+
									 "<col width=64 style='width:48pt'>"+
									 "<col width=76 span=9 style='mso-width-source:userset;mso-width-alt:2779;"+
									 "width:57pt'>"+
									 "<col class=xl6712907 width=76 style='mso-width-source:userset;mso-width-alt:"+
									 "2779;width:57pt'>"+
									 "<col width=76 style='mso-width-source:userset;mso-width-alt:2779;width:57pt'>"+
									 "<col width=117 style='mso-width-source:userset;mso-width-alt:4278;width:88pt'>"+
									 "<tr height=33 style='mso-height-source:userset;height:24.75pt'>"+
									 " <td colspan=13 height=33 class=xl7012907 width=1017 style='BORDER-RIGHT: black 0.5pt solid;height:24.75pt;"+
									 " width:763pt'>风城二号稠油联合处理站 采出水处理系统日报表</td>"+
									 "</tr>"+
									 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
									 " <td height=26 class=xl6812907 style='height:20.1pt;border-top:none'>值班人：</td>"+
									 " <td colspan=8 class=xl7912907 style='border-left:none'>　"+
									 " <input id='zbr' type='text' style='background:transparent;border:0px solid;width:602px;height:23px;font-size: 10pt;' value='"+ZBR1+"'/>"+
									 " </td>"+
									 " <td class=xl6812907 style='border-top:none'>审核人：</td>"+
									 " <td class=xl6912907 style='border-top:none;border-left:none'>"+ SHR +
									 //"  <input id='shr' type='text' style='background:transparent;border:0px solid;width:115px;height:30px;font-size: 10pt;'/>"+
									 " </td>"+
									 " <td class=xl6812907 style='border-top:none;border-left:none'>日期：</td>"+
									 " <td class=xl8212907 style='border-top:none;border-left:none'>"+ RQ +
									// " <input id='rq' type='text' onfocus='WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d {%H-20}:%m:%s',maxDate:'%y-%M-%d {%H+30}:%m:%s'})' style='background:transparent;border:0px solid;width:120px;height:30px;font-size: 10pt;'/>"+
									 " </td>"+
									 "</tr>"+
									 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
									"  <td rowspan=2 height=52 class=xl7412907 style='border-bottom:.5pt solid black;"+
									"  height:40.2pt;border-top:none'>时间</td>"+
									 " <td rowspan=2 class=xl7612907 width=76 style='border-bottom:.5pt solid black;"+
									 " border-top:none;width:57pt'>来水量<br>"+
									 "   (m3/h)</td>"+
									 " <td rowspan=2 class=xl7612907 width=76 style='border-bottom:.5pt solid black;"+
									 " border-top:none;width:57pt'>来水量<br>"+
									 "   (m3/h)</td>"+
									 " <td rowspan=2 class=xl7612907 width=76 style='border-bottom:.5pt solid black;"+
									 " border-top:none;width:57pt'>来水量累计<br>"+
									   " （m3）</td>"+
									 " <td rowspan=2 class=xl7612907 width=76 style='border-bottom:.5pt solid black;"+
									 " border-top:none;width:57pt'>污水回收量 <br>"+
									 "   (m3/h)</td>"+
									 " <td rowspan=2 class=xl7712907 width=76 style='border-bottom:.5pt solid black;"+
									 " border-top:none;width:57pt'>污水回收量<br>"+
									   " 累计（m3）</td>"+
									  "<td colspan=2 class=xl7212907 style='border-right:.5pt solid black;"+
									  "border-left:none'>调储罐液位(m)</td>"+
									  "<td colspan=2 class=xl7212907 style='border-right:.5pt solid black;"+
									  "border-left:none'>缓冲罐液位（m）</td>"+
									  "<td rowspan=2 class=xl7612907 width=76 style='border-bottom:.5pt solid black;"+
									  "border-top:none;width:57pt'>外输流量<br>"+
									  "  (m3/h)</td>"+
									  "<td rowspan=2 class=xl7612907 width=76 style='border-bottom:.5pt solid black;"+
									  "border-top:none;width:57pt'>外输量累计<br>"+
									    "（m3）</td>"+
									  "<td rowspan=2 class=xl7612907 width=117 style='border-bottom:.5pt solid black;"+
									  "border-top:none;width:88pt'>净化罐液位<br>"+
									    "（m）</td>"+
									 "</tr>"+
									 "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
									 " <td height=26 class=xl6812907 style='height:20.1pt;border-top:none;"+
									 " border-left:none'>T1</td>"+
									 " <td class=xl6812907 style='border-top:none;border-left:none'>T2</td>"+
									 " <td class=xl6812907 style='border-top:none;border-left:none'>1#</td>"+
									 " <td class=xl6812907 style='border-top:none;border-left:none'>2#</td>"+
									 "</tr>";
					
									 for(var i=0; i<CCSCL.length; i++) {
										 if(CCSCL[i].REPORT_TIME=='班累积'){
											 tableHtml += "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
								              "<td height=26 class=xl6512908 style='height:20.1pt;border-top:none'>"+CCSCL[i].REPORT_TIME+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].FIT_20201A+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].FIT_20201B+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].FIT20201AZ+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].FIT_20202+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].FIT20202Z+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].LIT_20201A+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].LIT_20201B+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].LIT_20401A+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].LIT_20401B+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].FIT_20401+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].FIT20401Z+"</td>"+
								              "<td class=xl6512908 style='border-top:none;border-left:none'>"+CCSCL[i].JINGHUAGUAN+"</td>"+
								              "</tr>";
										}else{
											 tableHtml += "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
								              "<td height=26 class=xl6512907 style='height:20.1pt;border-top:none'>"+CCSCL[i].REPORT_TIME+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].FIT_20201A+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].FIT_20201B+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].FIT20201AZ+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].FIT_20202+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].FIT20202Z+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].LIT_20201A+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].LIT_20201B+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].LIT_20401A+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].LIT_20401B+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].FIT_20401+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].FIT20401Z+"</td>"+
								              "<td class=xl6512907 style='border-top:none;border-left:none'>"+CCSCL[i].JINGHUAGUAN+"</td>"+
								              "</tr>";
										}
									 }
								 tableHtml += "<tr height=53 style='mso-height-source:userset;height:39.75pt'>"+
									  "<td height=53 class=xl7212907 style='height:39.75pt;border-top:none'>备注：</td>"+
									  "<td colspan=12 class=xl8112907 style='border-right:.5pt solid black'>　"+
									  "<input id='bz' type='text' style='vertical-align : center;background:transparent;border:0px solid;width:920px;height:44px;font-size: 10pt;' value='"+BZ+"'/>"+
									  "</td>"+
									 "</tr>"+
									 "<![if supportMisalignedColumns]>"+
									 "<tr height=0 style='display:none'>"+
									 " <td width=64 style='width:48pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=76 style='width:57pt'></td>"+
									 " <td width=117 style='width:88pt'></td>"+
									 "</tr>"+
									 "<![endif]>"+
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
				 /*document.getElementById('zbr').value = ZBR1;
				 document.getElementById('shr').value = SHR;
				 document.getElementById('rq').value = RQ;
				 document.getElementById('bz').value = BZ;
				 document.getElementById('reportID').value = RPDREPORTMESSAGEID;*/
			},
			error : function(data) {
		
			}
		});
		//}
    	//else {
    	//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
    	//}
 	}
	function onSave()
    {
		//var	SHR = $("#shr").val();
		var	ZBR1 = $("#zbr").val();
		var	BZ = $("#bz").val();
		var	RQ = $("#rq").val();
		//var RPDREPORTMESSAGEID = $("#reportID").val();
		//alert(RPDREPORTMESSAGEID);
		if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
    		$.ligerDialog.error("数据已审核过，不能进行修改");
    	}else{
    		if (modifyDataFlag($("#reportDate").val())) {
    		jQuery.ajax({
				type : 'post',
				url : 'ccscl_updateRPDREPORTMESSAGE.action?nowdata='+parseInt(Math.random()*100000),
				data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"SHR":SHR,"ZBR1":ZBR1,"BZ":BZ,"RQ":RQ},
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
				url : 'ccscl_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
		var url = "ccscl_exportU2_WATER.action?reportDate="+encodeURIComponent(reportDate) + '&reportName='+encodeURIComponent('采出水处理系统日报表');
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

<body style="overflow-x:hidden; padding:2px;">
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	         <input type="hidden" id="reportID" />
	        	<table border="0" cellspacing="1">
	        		<tr>
		                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="reportDate" ltype="date"/></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" id="dayreport" style="width:80px;" >导出报表</a></td>
						
						<td style="padding-left: 20px;"><a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onAduit()" class="l-button"  style="width:100px;display:none" id="onAuditid">审核</a></td>
						
						
					</tr>
				</table>
				<input type="hidden" >
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			
			<div id="tableHtml" align=center x:publishsource="Excel" style="OVERFLOW:auto; width: 99%;height: 87%;">

			</div>
			
		</form>
</body>
</html>