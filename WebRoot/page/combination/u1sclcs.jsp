<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- <html xmlns="http://www.w3.org/1999/xhtml"> -->
<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">
<head><title>水处理参数</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    
    <meta name=ProgId content=Excel.Sheet />
	<meta name=Generator content="Microsoft Excel 14" />
	<link rel=File-List href="file8632.files/filelist.xml" />
	<link href="../../lib/css/u1fxjl.css" rel="stylesheet" type="text/css" /> 
	
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
    var  RPDREPORTMESSAGEID ="";
	var  SHR ="";
    var tableHtml;
    //var mod ;
        $(function () {
        	$("#reportDate").ligerDateEditor(
                    {
                        format: "yyyy-MM-dd",
                        labelWidth: 100,
                        labelAlign: 'center',
                        cancelable : false
                }).setValue(myDate().Format("yyyy-MM-dd"));
        });
		
        function onSubmit()
    	{
        	//if (onSearchByDate($("#reportDate").val())){
          	 jQuery.ajax({
    			type : 'post',
    			url : 'u1sclcs_searchU1SCLCS.action?reportDate='+$("#reportDate").val(),
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
    							    var U1SCLCS = outData.JSONDATA.U1SCLCS;
    							    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
    							    if (U1SCLCS != null && typeof(U1SCLCS)!="undefined"){
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
    							     	}else{
    							     		RPDREPORTMESSAGEID ="";
    							     	}
    					    		$("#tableHtml").html('');
    					    		tableHtml = "<table border=0 cellpadding=0 cellspacing=0 width=724 style='border-collapse:collapse;table-layout:fixed;width:753pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>"+
    					    			" <tr height=53 style='mso-height-source:userset;height:39.95pt'>"+
    					    			"  <td colspan=11 height=53 class=xl11023387 width=724 style='height:39.95pt;width:753pt'>一号稠油联合处理站水处理参数日报表</td>"+
    					    			" </tr>"+
    					    			" <tr class=xl1523387 height=33 style='mso-height-source:userset;height:24.95pt'>"+
    					    			"  <td colspan=2 height=33 class=xl11223387 style='height:24.95pt'>值班人：</td>"+
    					    			"  <td colspan=3 class=xl11323387 style='border-left:none'><input id='zbr' type='text' style='background:transparent;border:0px solid;width:645px;height:30px;font-size: 10pt;' value='"+ZBR1+"'/></td>"+
    					    			"  <td class=xl11223387 style='border-top:none;border-left:none'>审核：</td>"+
    					    			"  <td colspan=2 class=xl11423387 style='border-left:none'>"+SHR+"</td>"+
    					    			"  <td class=xl11523387 style='border-top:none;border-left:none'>日期：</td>"+
    					    			"  <td id='rq' colspan=2 class=xl11323387 style='border-left:none'>"+RQ+"</td>"+
    					    			" </tr>"+
    					    			" <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;width:38pt'>时间</td>"+
    					    			"  <td class=xl6923387 width=60 style='border-top:none;border-left:none;width:60pt'>水一来水量(m3/h)</td>"+
    					    			"  <td class=xl6923387 width=60 style='border-top:none;border-left:none;width:60pt'>水二来水量(m3/h)</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:50pt'>1#反应器(L)</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:50pt'>2#反应器(L)</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:50pt'>3#反应器(L)</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:50pt'>4#反应器(L)</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:50pt'>A气浮</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:50pt'>B气浮"+
    					    			"  <td class=xl6923387 width=60 style='border-top:none;border-left:none;width:60pt'>水一外输量</td>"+
    					    			"  <td class=xl6923387 width=60 style='border-top:none;border-left:none;width:60pt'>水二外输量</td>"+
    					    			" </tr>";
    					    			
	    					    		//var disStr="";
										//if(mod==1){
										//	disStr = "onclick='isChange(this)'";
										//}
    									 for(var i=0; i<U1SCLCS.length; i++) {
        									 if(i<U1SCLCS.length-1){
        										 tableHtml += "<tr height=33 style='mso-height-source:userset;height:24.95pt'>"+
        										 	 " <td style='display: none'><input id='RPD_U1_OIL_AUTO_ID"+i+"' type='hidden' value='"+U1SCLCS[i].RPD_U1_WATER_AUTO_ID+"'></td>"+
	        										 " <td id='REPORT_TIME"+i+"' height=33 class=xl6623387 style='height:24.95pt;border-top:none'>"+U1SCLCS[i].REPORT_TIME+"</td>"+
	        										 " <td id='tdft1001' class=xl7423387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1001+"</td>"+
	        										 " <td id='tds2lsllj' class=xl7323387 style='border-top:none;border-left:none'>"+U1SCLCS[i].s2lsllj+"</td>"+
	        										 " <td id='tdft1005' class=xl7223387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1005+"</td>"+
	        										 " <td id='tdft1004' class=xl7423387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1004+"</td>"+
	        										 " <td id='tdft1003' class=xl7323387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1003+"</td>"+
	        										 " <td id='tdft1002' class=xl7223387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1002+"</td>"+
	        										 " <td id='tds2qf1lj' class=xl7323387 style='border-top:none;border-left:none'>"+U1SCLCS[i].s2qf1lj+"</td>"+
	        										 " <td id='tds2qf2lj' class=xl7323387 style='border-top:none;border-left:none'>"+U1SCLCS[i].s2qf2lj+"</td>"+
	        										 " <td id='tdft1016' class=xl7223387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1016+"</td>"+
	        										 " <td id='tds2wssllj' class=xl7423387 style='border-top:none;border-left:none'>"+U1SCLCS[i].s2wssllj+"</td>"+
	        									   " </tr>";
        									 }else{
        										 tableHtml += "<tr height=33 style='mso-height-source:userset;height:24.95pt'>"+
        										 " <td height=33 class=xl6623387 style='height:24.95pt;border-top:none'>日累计</td>"+
        										 " <td id='tdft1001' class=xl7423387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1001+"</td>"+
        										 " <td id='tds2lsllj' class=xl7323387 style='border-top:none;border-left:none'>"+U1SCLCS[i].s2lsllj+"</td>"+
        										 " <td id='tdft1005' class=xl7223387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1005+"</td>"+
        										 " <td id='tdft1004' class=xl7423387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1004+"</td>"+
        										 " <td id='tdft1003' class=xl7323387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1003+"</td>"+
        										 " <td id='tdft1002' class=xl7223387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1002+"</td>"+
        										 " <td id='tds2qf1lj' class=xl7323387 style='border-top:none;border-left:none'>"+U1SCLCS[i].s2qf1lj+"</td>"+
        										 " <td id='tds2qf2lj' class=xl7323387 style='border-top:none;border-left:none'>"+U1SCLCS[i].s2qf2lj+"</td>"+
        										 " <td id='tdft1016' class=xl7223387 style='border-top:none;border-left:none'>"+U1SCLCS[i].ft1016+"</td>"+
        										 " <td id='tds2wssllj' class=xl7423387 style='border-top:none;border-left:none'>"+U1SCLCS[i].s2wssllj+"</td>"+
        									   " </tr>";
        									 }
    										 
    									 }
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
    				/* document.getElementById('zbr').value = ZBR1;
    				 document.getElementById('shr').value = SHR;
    				 document.getElementById('rq').value = RQ;
    				 document.getElementById('bz').value = BZ;*/
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
	
	function onExport() {
		var reportDate=$("#reportDate").val();
		var url = "u1sclcs_exportU2_WATER.action?reportDate="+encodeURIComponent(reportDate) + '&reportName='+encodeURIComponent('水处理加药系统日报表');
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
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<div id="tableHtml" x:publishsource="Excel" style="OVERFLOW:auto; width: 99%;height: 87%;text-align: center;" >

			</div> 
			</form>
</body>
</html>