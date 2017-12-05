<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>原油交接台账</title>
   
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
     <link href="../../lib/css/cyyyjjzt.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var tableval;
    	var SYear;
        var SMonth;
        $(function(){
            var yearid = $('#SYear')    //年所在的控件
            var monthid = $('#SMonth')    //月所在的控件
            //var dayid = $('#SDay')    //天所在的控件
            var myDate = new Date();
            var year = myDate.getFullYear();
            
            var month=myDate.getMonth()+1;
            
            for(var i=(year-20);i<=(year+20);i++){
                yearid.append('<option value="'+i+'">'+i+'</option>')
            }
                for(var i=1;i<=12;i++){
                    monthid.append('<option value="'+i+'">'+i+'</option>')
                }
            
           $("#SYear").val(year);
           $("#SMonth").val(month);
        })

         function onSubmit(){
        	 SYear = $("#SYear").val();
             SMonth =  $("#SMonth").val();
        	//if (onSearchByDate($("#txtDate").val())){
        	 jQuery.ajax({
					type : 'post',
					url : 'crudeoil_searchCrudeOil.action',
					data :{"SYear" : SYear, "SMonth":SMonth},
					success : function(data) { 
        			if (data != null && typeof(data)!="undefined"&& data!=""){
						var outData = eval('(' + data + ')');
						//alert(data);
						if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
							$.ligerDialog.error(outData.ERRMSG);
						}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
							$.ligerDialog.error(outerror(outData.ERRCODE));
						}else{
							if (outData.oilAll != null && typeof(outData.oilAll)!="undefined"){
									    var oilAll = outData.oilAll;
									   // alert(oilAll)
									    	//↓
										$("#tableHtml").html('');
								     	tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1706 style='border-collapse:collapse;table-layout:fixed;width:1281pt'>"+
										 "<col class=xl66 width=117 style='mso-width-source:userset;mso-width-alt:3744; width:88pt'>"+
										 "<col class=xl68 width=55 style='mso-width-source:userset;mso-width-alt:1760; width:41pt'>"+
										 "<col class=xl69 width=93 span=2 style='mso-width-source:userset;mso-width-alt:2976;width:70pt'>"+
										 "<col class=xl72 width=68 style='mso-width-source:userset;mso-width-alt:2176;width:51pt'>"+
										 "<col class=xl72 width=82 span=2 style='mso-width-source:userset;mso-width-alt: 2624;width:62pt'>"+
										 "<col class=xl69 width=93 span=2 style='mso-width-source:userset;mso-width-alt:2976;width:70pt'>"+
										 "<col class=xl70 width=92 style='mso-width-source:userset;mso-width-alt:2944; width:69pt'>"+
										 "<col class=xl71 width=75 style='mso-width-source:userset;mso-width-alt:2400; width:56pt'>"+
										 "<col class=xl68 width=124 span=2 style='mso-width-source:userset;mso-width-alt: 3968;width:93pt'>"+
										 "<col class=xl68 width=83 style='mso-width-source:userset;mso-width-alt:2656; width:62pt'>"+
										 "<col class=xl67 width=72 span=6 style='width:54pt'><tr height=42 style='mso-height-source:userset;height:31.5pt'> <td colspan=14 height=42 class=xl97 width=1274 style='border-right:.5pt solid black;height:31.5pt;width:957pt'>交油记录情况</td>"+
										 "<td class=xl67 width=72 style='width:54pt'></td>"+
										 "<td class=xl67 width=72 style='width:54pt'></td>"+
										 "<td class=xl67 width=72 style='width:54pt'></td>"+
										 "<td class=xl67 width=72 style='width:54pt'></td>"+
										 "<td class=xl67 width=72 style='width:54pt'></td>"+
										 "<td class=xl67 width=72 style='width:54pt'></td>"+
										 "</tr>"+
										 "<tr class=xl96 height=19 style='height:14.25pt'>"+
										 "<td height=19 class=xl73 style='height:14.25pt;border-top:none'>日期</td>"+
										 "<td class=xl74 style='border-top:none;border-left:none'>罐号</td>"+
										 "<td class=xl75 width=93 style='border-top:none;border-left:none;width:70pt'>高油位(m)</td>"+
										 "<td class=xl75 width=93 style='border-top:none;border-left:none;width:70pt'>底油位(m)</td>"+
										 "<td class=xl76 style='border-top:none;border-left:none'>纯油位</td>"+
										 "<td class=xl76 style='border-top:none;border-left:none'>每米纯油</td>"+
										 "<td class=xl76 style='border-top:none;border-left:none'>每米毛油</td>"+
										 "<td class=xl77 style='border-top:none;border-left:none'>毛油量(t)</td>"+
										 "<td class=xl77 style='border-top:none;border-left:none'>纯油量(t)</td>"+
										 "<td class=xl78 style='border-top:none;border-left:none'>水量（t）</td>"+
										 "<td class=xl79 style='border-top:none;border-left:none'>含水(%)</td>"+
										 "<td class=xl80 width=124 style='border-top:none;border-left:none;width:93pt'>视密(mg/cm&sup3;)</td>"+
										 "<td class=xl80 width=124 style='border-top:none;border-left:none;width:93pt'>标密(mg/cm&sup3;)</td>"+
										 "<td class=xl73 style='border-top:none;border-left:none'>罐温(℃)</td>"+
										 "<td colspan=6 class=xl96 style='mso-ignore:colspan'></td>"+
										 "</tr>";
										 for(var i=0; i<oilAll.length; i++) {
											// alert(oilAll[i].rq)
											if(oilAll[i].rq =="合计交罐"){
										 tableval +="<tr height=25 style='height:18.75pt'>"+
										 "<td height=25 class=x202 style='height:18.75pt;border-top:none'>"+oilAll[i].rq+"</td>"+
										 "<td class=x202 style='border-top:none;border-left:none'>"+oilAll[i].gh+"</td>"+
										 "<td class=xl83 style='border-top:none;border-left:none'>"+oilAll[i].gyw+"</td>"+
										 "<td class=xl83 style='border-top:none;border-left:none'>"+oilAll[i].dyw+"</td>"+
										 "<td class=xl84 style='border-top:none;border-left:none'>"+oilAll[i].cyw+"</td>"+
										 "<td class=xl85 style='border-top:none;border-left:none'>"+oilAll[i].mmcy+"</td>"+
										 "<td class=xl85 style='border-top:none;border-left:none'>"+oilAll[i].mmmy+"</td>"+
										 "<td class=xl83 style='border-top:none;border-left:none'>"+oilAll[i].myl+"</td>"+
										 "<td class=xl83 style='border-top:none;border-left:none'>"+oilAll[i].cyl+"</td>"+
										 "<td class=xl86 style='border-top:none;border-left:none'>"+oilAll[i].sl+"</td>"+
										 "<td class=xl86 style='border-top:none;border-left:none'>"+oilAll[i].hs+"</td>"+
										 "<td class=xl82 style='border-top:none;border-left:none'>"+oilAll[i].sm+"</td>"+
										 "<td class=xl82 style='border-top:none;border-left:none'>"+oilAll[i].bm+"</td>"+
										 "<td class=xl87 style='border-top:none;border-left:none'>"+oilAll[i].gw+"</td>"+
										 "</tr>";
										 }else{
											 tableval +="<tr height=25 style='height:18.75pt'>"+
											 "<td height=25 class=xl81 style='height:18.75pt;border-top:none'>"+oilAll[i].rq+"</td>"+
											 "<td class=xl82 style='border-top:none;border-left:none'>"+oilAll[i].gh+"</td>"+
											 "<td class=xl83 style='border-top:none;border-left:none'>"+oilAll[i].gyw+"</td>"+
											 "<td class=xl83 style='border-top:none;border-left:none'>"+oilAll[i].dyw+"</td>"+
											 "<td class=xl84 style='border-top:none;border-left:none'>"+oilAll[i].cyw+"</td>"+
											 "<td class=xl85 style='border-top:none;border-left:none'>"+oilAll[i].mmcy+"</td>"+
											 "<td class=xl85 style='border-top:none;border-left:none'>"+oilAll[i].mmmy+"</td>"+
											 "<td class=xl83 style='border-top:none;border-left:none'>"+oilAll[i].myl+"</td>"+
											 "<td class=xl83 style='border-top:none;border-left:none'>"+oilAll[i].cyl+"</td>"+
											 "<td class=xl86 style='border-top:none;border-left:none'>"+oilAll[i].sl+"</td>"+
											 "<td class=xl86 style='border-top:none;border-left:none'>"+oilAll[i].hs+"</td>"+
											 "<td class=xl82 style='border-top:none;border-left:none'>"+oilAll[i].sm+"</td>"+
											 "<td class=xl82 style='border-top:none;border-left:none'>"+oilAll[i].bm+"</td>"+
											 "<td class=xl87 style='border-top:none;border-left:none'>"+oilAll[i].gw+"</td>"+
											 "</tr>";
											 }
										 }
										 "</table>";
											//↑
								     		$("#tableHtml").html(tableval);	
							}else{
								$.ligerDialog.error("未知错误！");
								}
						}
					}
					error : function(data) {
				
					}
        	 }
				});
        	//}else {
	        	//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
	        	//}
         }
        function onExport() {
        	 SYear = $("#SYear").val();
             SMonth =  $("#SMonth").val();
    		var url = "crudeoil_exportCrude.action?SYear="+encodeURIComponent(SYear) + '&SMonth='+encodeURIComponent(SMonth);
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

<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table>
					<tr>	
		                  <td align="right" class="l-table-edit-td" style="width:60px">日期：</td>
						<td>
						
						 <select name="SYear" id="SYear">
						　　 </select>&nbsp;&nbsp;年
						　　 <select name="SMonth" id="SMonth" >
						　　 </select>&nbsp;&nbsp;月
						</td>
						<td align="right" class="l-table-edit-td" style="width:20px"></td>
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>
						<!--
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						
						 <td id="jieshi"></td> -->
						</tr>
				
				</table>
	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div id="tableHtml" style="OVERFLOW:auto; width: 99%;height: 87%;" align="center">
	<!--<div id="tableHtml" align=center x:publishsource="Excel" style="height:450px;width:98%;height: 89%; overflow:auto">-->
</div>
</body>

</html>
