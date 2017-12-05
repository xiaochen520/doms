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
<head><title>分线计量流量日报表</title>
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
	var  ZBR1 ="";
	var  RQ ="";
	var  BZ ="";
    var tableHtml;
    var mod ;
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
				url : 'u1fxjl_pageInit.action',
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
		function changeValue(){
			if($('#te00212').val()!="" && $('#te0020').val()!="")
				$('#tdte002').html(Number($('#te00212').val()-$('#te0020').val()));
			else
				$('#tdte002').html("");
			
			if($('#pt00212').val()!="" && $('#pt0020').val()!="")
				$('#tdpt002').html(Number($('#pt00212').val()-$('#pt0020').val()).toFixed(2));
			else
				$('#tdpt002').html("");
			if($('#te00512').val()!="" && $('#te0050').val()!="")
				$('#tdte005').html(Number($('#te00512').val()-$('#te0050').val()));
			else
				$('#tdte005').html("");
			
			if($('#pt00512').val()!="" && $('#pt0050').val()!="")
				$('#tdpt005').html(Number($('#pt00512').val()-$('#pt0050').val()).toFixed(2));
			else
				$('#tdpt005').html("");
			if($('#te00712').val()!="" && $('#te0070').val()!="")
				$('#tdte007').html(Number($('#te00712').val()-$('#te0070').val()));
			else
				$('#tdte007').html("");
			if($('#pt00712').val()!="" && $('#pt0070').val()!="")
				$('#tdpt007').html(Number($('#pt00712').val()-$('#pt0070').val()).toFixed(2));
			else
				$('#tdpt007').html("");
			if($('#te00812').val()!="" && $('#te0080').val()!="")
				$('#tdte008').html(Number($('#te00812').val()-$('#te0080').val()));
			else
				$('#tdte008').html("");
			if($('#pt00812').val()!="" && $('#pt0080').val()!="")
				$('#tdpt008').html(Number($('#pt00812').val()-$('#pt0080').val()).toFixed(2));
			else
				$('#tdpt008').html("");
			if($('#pt00412').val()!="" && $('#pt0040').val()!="")
				$('#tdpt004').html(Number($('#pt00412').val()-$('#pt0040').val()).toFixed(2));
			else
				$('#tdpt004').html("");
			if($('#te2hz12').val()!="" && $('#te2hz0').val()!="")
				$('#tdte2hz').html(Number($('#te2hz12').val()-$('#te2hz0').val()));
			else
				$('#tdte2hz').html("");
			if($('#pt2hz12').val()!="" && $('#pt2hz0').val()!="")
				$('#tdpt2hz').html(Number($('#pt2hz12').val()-$('#pt2hz0').val()).toFixed(2));
			else
				$('#tdpt2hz').html("");
			if($('#te20112').val()!="" && $('#te2010').val()!="")
				$('#tdte201').html(Number($('#te20112').val()-$('#te2010').val())); 
			else
				$('#tdte201').html("");
			if($('#pt20112').val()!="" && $('#pt2010').val()!="")
				$('#tdpt201').html(Number($('#pt20112').val()-$('#pt2010').val()).toFixed(2));
			else
				$('#tdpt201').html("");
			if($('#x2z32bxlj12').val()!="" && $('#x2z32bxlj0').val()!="")
				$('#tdx2z32bxlj').html(Number($('#x2z32bxlj12').val()-$('#x2z32bxlj0').val()).toFixed(1));
			else
				$('#tdx2z32bxlj').html("");
			if($('#x5_13_11lj12').val()!="" && $('#x5_13_11lj0').val()!="")
				$('#tdx5_13_11lj').html(Number($('#x5_13_11lj12').val()-$('#x5_13_11lj0').val()).toFixed(1));
			else
				$('#tdx5_13_11lj').html("");
			if($('#x7z32nxlj12').val()!="" && $('#x7z32nxlj0').val()!="")
				$('#tdx7z32nxlj').html(Number($('#x7z32nxlj12').val()-$('#x7z32nxlj0').val()).toFixed(1));
			else
				$('#tdx7z32nxlj').html("");		
			if($('#x8xytxlj12').val()!="" && $('#x8xytxlj0').val()!="")	
				$('#tdx8xytxlj').html(Number($('#x8xytxlj12').val()-$('#x8xytxlj0').val()).toFixed(1));	
			else
				$('#tdx8xytxlj').html("");
			if($('#ghjxtcclj12').val()!="" && $('#ghjxtcclj0').val()!="")	
				$('#tdghjxtcclj').html(Number($('#ghjxtcclj12').val()-$('#ghjxtcclj0').val()).toFixed(1));	
			else
				$('#tdghjxtcclj').html("");
			if($('#tsbccyl12').val()!="" && $('#tsbccyl0').val()!="")
				$('#tdtsbccyl').html(Number($('#tsbccyl12').val()-$('#tsbccyl0').val()).toFixed(2));
			else
				$('#tdtsbccyl').html("");	
			if($('#tsbcclj12').val()!="" && $('#tsbcclj0').val()!="")
				$('#tdtsbcclj').html(Number($('#tsbcclj12').val()-$('#tsbcclj0').val()).toFixed(1));
			else
				$('#tdtsbcclj').html("");	
			if($('#wyccyl12').val()!="" && $('#wyccyl0').val()!="")
				$('#tdwyccyl').html(Number($('#wyccyl12').val()-$('#wyccyl0').val()).toFixed(2));	
			else
				$('#tdwyccyl').html("");
			if($('#wycclj12').val()!="" && $('#wycclj0').val()!="")
				$('#tdwycclj').html(Number($('#wycclj12').val()-$('#wycclj0').val()).toFixed(1));	
			else
				$('#tdwycclj').html("");
			if($('#q2zlj12').val()!="" && $('#q2zlj0').val()!="")		
				$('#tdq2zlj').html(Number($('#q2zlj12').val()-$('#q2zlj0').val()).toFixed(1));	
			else
				$('#tdq2zlj').html("");
			if($('#ghzlj12').val()!="" && $('#ghzlj0').val()!="")		
				$('#tdghzlj').html(Number($('#ghzlj12').val()-$('#ghzlj0').val()).toFixed(1));	
			else
				$('#tdghzlj').html("");
			if($('#bdcjcyglj12').val()!="" && $('#bdcjcyglj0').val()!="")
				$('#tdbdcjcyglj').html(Number($('#bdcjcyglj12').val()-$('#bdcjcyglj0').val()).toFixed(1));
			else
				$('#tdbdcjcyglj').html("");
			
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

        function checkDataInt(obj){
        	if(obj.value.length==1)
            {
                obj.value=obj.value.replace(/[^1-9]/g,'')
            }else{
                obj.value=obj.value.replace(/\D/g,'')
            }
        }

        function checkData2p(obj){
        	if(obj.value!="")
            {
                obj.value=Number(obj.value).toFixed(2);
            }
        }

        function checkData1p(obj){
        	if(obj.value!="")
            {
                obj.value=Number(obj.value).toFixed(1);
            }
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
        	//if (onSearchByDate($("#reportDate").val())){
          	 jQuery.ajax({
    			type : 'post',
    			url : 'u1fxjl_searchU1FXJL.action?reportDate='+$("#reportDate").val(),
    			success : function(data) { 
    			
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
    							    var U1FXJL = outData.JSONDATA.U1FXJL;
    							    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
    							    if (U1FXJL != null && typeof(U1FXJL)!="undefined"){
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
    					    		tableHtml = "<table border=0 cellpadding=0 cellspacing=0 width=1800 style='border-collapse:collapse;table-layout:fixed'>"+
    					    			" <tr height=53 style='mso-height-source:userset;height:39.95pt'>"+
    					    			"  <td colspan=34 height=53 class=xl11023387 style='height:27.0pt'>一号稠油联合处理站分线计量流量日报表</td>"+
    					    			" </tr>"+
    					    			" <tr class=xl1523387 height=33 style='mso-height-source:userset;height:24.95pt'>"+
    					    			"  <td colspan=3 height=33 class=xl11223387 style='height:24.95pt'>值班人：</td>"+
    					    			"  <td colspan=19 class=xl11323387 style='border-left:none'><input id='zbr' type='text' style='background:transparent;border:0px solid;width:645px;height:30px;font-size: 10pt;' value='"+ZBR1+"'/></td>"+
    					    			"  <td colspan=2 class=xl11223387 style='border-top:none;border-left:none'>审核：</td>"+
    					    			"  <td colspan=5 class=xl11423387 style='border-left:none'>"+SHR+"</td>"+
    					    			"  <td colspan=2 class=xl11523387 style='border-top:none;border-left:none'>日期：</td>"+
    					    			"  <td colspan=3 class=xl11323387 style='border-left:none'><input id='rq' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+RQ+"'/></td>"+
    					    			" </tr>"+
    					    			" <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
    					    			"  <td rowspan=3 height=105 class=xl10423387 style='height:80.15pt;border-top:none'>时间</td>"+
    					    			"  <td colspan=20 class=xl10123387 style='border-right:.5pt solid black;border-left:none'>分线计量</td>"+
    					    			"  <td colspan=6 class=xl10223387 style='border-right:.5pt solid black'>掺柴油量</td>"+
    					    			"  <td colspan=3 rowspan=2 class=xl9423387 style='border-right:.5pt solid black;border-bottom:.5pt solid black'>去2#站流量</td>"+
    					    			"  <td colspan=3 rowspan=2 class=xl9423387 style='border-right:.5pt solid black;border-bottom:.5pt solid black'>管汇总液</td>"+
    					    			"  <td rowspan=2 class=xl10523387 width=73 style='border-bottom:.5pt solid black;border-top:none;width:55pt'>博达池进除油罐</td>"+
    					    			" </tr>"+
    					    			" <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
    					    			"  <td colspan=5 height=26 class=xl10423387 style='height:20.1pt;border-left:none'>2#重32北线</td>"+
    					    			"  <td colspan=5 class=xl10423387 style='border-left:none'>5#13-11线</td>"+
    					    			"  <td colspan=5 class=xl10723387 style='border-left:none'>7#重32南线</td>"+
    					    			"  <td colspan=5 class=xl10723387 style='border-left:none'>8#卸油台线</td>"+
    					    			"  <td colspan=2 class=xl10823387 style='border-right:.5pt solid black'>管汇柴油量</td>"+
    					    			"  <td colspan=2 class=xl10223387 style='border-right:.5pt solid black'>提升泵柴油量</td>"+
    					    			"  <td class=xl7023387 colspan=2 style='border-right:.5pt solid black'>污油柴油量</td>"+
    					    			" </tr>"+
    					    			" <tr class=xl6523387 height=53 style='mso-height-source:userset;height:39.95pt'>"+
    					    			"  <td height=53 class=xl6923387 width=49 style='height:39.95pt;border-top:none;border-left:none;width:37pt'>温度℃</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>含水%</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none;width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>油量t</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>温度℃</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>含水%</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none;width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>油量t</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>温度℃</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>含水%</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none;width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>油量t</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>温度℃</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>含水%</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none;width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>油量t</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none; width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none; width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none;width:55pt'>累积流量m<font class='font1023387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>温度℃</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none;width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>温度℃</td>"+
    					    			"  <td class=xl6923387 width=50 style='border-top:none;border-left:none;width:38pt'>压力Mpa</td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none;width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			"  <td class=xl6923387 width=73 style='border-top:none;border-left:none;width:55pt'>累积流量m<font class='font523387'><sup>3</sup></font></td>"+
    					    			" </tr>";
    					    			
	    					    		var disStr="";
										if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
    									 for(var i=0; i<U1FXJL.length; i++) {
        									 if(i<U1FXJL.length-1){
        										 tableHtml += "<tr height=33 style='mso-height-source:userset;height:24.95pt'>"+
        										 	 " <td style='display: none'><input id='RPD_U1_OIL_AUTO_ID"+i+"' type='hidden' value='"+U1FXJL[i].RPD_U1_OIL_AUTO_ID+"'></td>"+
	        										 " <td id='REPORT_TIME"+i+"' height=33 class=xl6623387 style='height:24.95pt;border-top:none'>"+U1FXJL[i].REPORT_TIME+"</td>"+
	        										 " <td class=xl7423387 style='border-top:none;border-left:none'><input id='te002"+i+"' type='text' "+disStr+" onblur='changeValue()' onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].te002+"'/></td>"+
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='pt002"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()' onkeyup='checkData(this)'  style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].pt002+"'/></td>"+
	        										 
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='AIT002"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].AIT002+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='x2z32bxlj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].x2z32bxlj+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='FTQ_102OQ"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].FTQ_102OQ+"'/></td>"+
	        										 
	        										 " <td class=xl7423387 style='border-top:none;border-left:none'><input id='te005"+i+"' type='text' "+disStr+" onblur='changeValue()'  onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].te005+"'/></td>"+
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='pt005"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].pt005+"'/></td>"+
	        										 
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='AIT005"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].AIT005+"'/></td>"+	        										 
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='x5_13_11lj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)'  style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].x5_13_11lj+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='FTQ_105OQ"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].FTQ_105OQ+"'/></td>"+
	        										 
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='te007"+i+"' type='text' "+disStr+" onblur='changeValue()'  onkeyup='checkDataInt(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].te007+"'/></td>"+
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='pt007"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].pt007+"'/></td>"+
	        										 
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='AIT007"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].AIT007+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='x7z32nxlj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)'  style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].x7z32nxlj+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='FTQ_107OQ"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].FTQ_107OQ+"'/></td>"+
	        										 
	        										 " <td class=xl7423387 style='border-top:none;border-left:none'><input id='te008"+i+"' type='text' "+disStr+" onblur='changeValue()' onkeyup='checkDataInt(this)'  style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].te008+"'/></td>"+
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='pt008"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()' onkeyup='checkData(this)'  style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].pt008+"'/></td>"+
	        										 
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='AIT008"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].AIT008+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='x8xytxlj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)'  style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].x8xytxlj+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='FTQ_108OQ"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].FTQ_108OQ+"'/></td>"+
	        										 
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='pt004"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()'  onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].pt004+"'/></td>"+
	        										 " <td class=xl7523387 style='border-top:none;border-left:none'><input id='ghjxtcclj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)'  style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].ghjxtcclj+"'/></td>"+
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='tsbccyl"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].tsbccyl+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='tsbcclj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].tsbcclj+"'/></td>"+
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='wyccyl"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].wyccyl+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='wycclj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].wycclj+"'/></td>"+
	        										 " <td class=xl7423387 style='border-top:none;border-left:none'><input id='te2hz"+i+"' type='text' "+disStr+" onblur='changeValue()' onkeyup='checkDataInt(this)'  style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].te2hz+"'/></td>"+
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='pt2hz"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].pt2hz+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='q2zlj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].q2zlj+"'/></td>"+
	        										 " <td class=xl7423387 style='border-top:none;border-left:none'><input id='te201"+i+"' type='text' "+disStr+" onblur='changeValue()' onkeyup='checkDataInt(this)'  style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].te201+"'/></td>"+
	        										 " <td class=xl7323387 style='border-top:none;border-left:none'><input id='pt201"+i+"' type='text' "+disStr+" onblur='checkData2p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:45px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].pt201+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='ghzlj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].ghzlj+"'/></td>"+
	        										 " <td class=xl7223387 style='border-top:none;border-left:none'><input id='bdcjcyglj"+i+"' type='text' "+disStr+" onblur='checkData1p(this);changeValue()' onkeyup='checkData(this)' style='background:transparent;border:0px solid;width:70px;height:20px;font-size: 10pt;text-align:center;' value='"+U1FXJL[i].bdcjcyglj+"'/></td>"+
	        									   " </tr>";
        									 }else{
        										 tableHtml += "<tr height=33 style='mso-height-source:userset;height:24.95pt'>"+
        										 " <td height=33 class=xl6623387 style='height:24.95pt;border-top:none'>日累计</td>"+
        										 " <td id='tdte002' class=xl7423387 style='border-top:none;border-left:none'>"+U1FXJL[i].te002+"</td>"+
        										 " <td id='tdpt002' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].pt002+"</td>"+
        										 
        										 " <td id='AIT002' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].AIT002+"</td>"+
        										 " <td id='tdx2z32bxlj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].x2z32bxlj+"</td>"+
        										 " <td id='FTQ_102OQ' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].FTQ_102OQ+"</td>"+
        										 
        										 " <td id='tdte005' class=xl7423387 style='border-top:none;border-left:none'>"+U1FXJL[i].te005+"</td>"+
        										 " <td id='tdpt005' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].pt005+"</td>"+
        										 
        										 " <td id='AIT005' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].AIT005+"</td>"+
        										 " <td id='tdx5_13_11lj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].x5_13_11lj+"</td>"+
        										 " <td id='FTQ_105OQ' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].FTQ_105OQ+"</td>"+
        										 
        										 " <td id='tdte007' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].te007+"</td>"+
        										 " <td id='tdpt007' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].pt007+"</td>"+
        										 
        										 " <td id='AIT007' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].AIT007+"</td>"+
        										 " <td id='tdx7z32nxlj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].x7z32nxlj+"</td>"+
        										 " <td id='FTQ_107OQ' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].FTQ_107OQ+"</td>"+
        										 
        										 " <td id='tdte008' class=xl7423387 style='border-top:none;border-left:none'>"+U1FXJL[i].te008+"</td>"+
        										 " <td id='tdpt008' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].pt008+"</td>"+
        										 
        										 " <td id='AIT008' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].AIT008+"</td>"+
        										 " <td id='tdx8xytxlj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].x8xytxlj+"</td>"+
        										 " <td id='FTQ_108OQ' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].FTQ_108OQ+"</td>"+
        										 
        										 " <td id='tdpt004' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].pt004+"</td>"+
        										 " <td id='tdghjxtcclj' class=xl7523387 style='border-top:none;border-left:none'>"+U1FXJL[i].ghjxtcclj+"</td>"+
        										 " <td id='tdtsbccyl' id='td' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].tsbccyl+"</td>"+
        										 " <td id='tdtsbcclj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].tsbcclj+"</td>"+
        										 " <td id='tdwyccyl' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].wyccyl+"</td>"+
        										 " <td id='tdwycclj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].wycclj+"</td>"+
        										 " <td id='tdte2hz' class=xl7423387 style='border-top:none;border-left:none'>"+U1FXJL[i].te2hz+"</td>"+
        										 " <td id='tdpt2hz' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].pt2hz+"</td>"+
        										 " <td id='tdq2zlj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].q2zlj+"</td>"+
        										 " <td id='tdte201' class=xl7423387 style='border-top:none;border-left:none'>"+U1FXJL[i].te201+"</td>"+
        										 " <td id='tdpt201' class=xl7323387 style='border-top:none;border-left:none'>"+U1FXJL[i].pt201+"</td>"+
        										 " <td id='tdghzlj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].ghzlj+"</td>"+
        										 " <td id='tdbdcjcyglj' class=xl7223387 style='border-top:none;border-left:none'>"+U1FXJL[i].bdcjcyglj+"</td>"+
        									   " </tr>";
        									 }
    										 
    									 }
    									i = U1FXJL.length-1;
    									var llLj=0;
    									if (!isNaN(parseFloat(U1FXJL[i].x2z32bxlj))){llLj+=parseFloat(U1FXJL[i].x2z32bxlj);}
    									if (!isNaN(parseFloat(U1FXJL[i].x5_13_11lj))){llLj+=parseFloat(U1FXJL[i].x5_13_11lj);}
    									if (!isNaN(parseFloat(U1FXJL[i].x7z32nxlj))){llLj+=parseFloat(U1FXJL[i].x7z32nxlj);}
    									var llLj1=0;
    									if (!isNaN(parseFloat(U1FXJL[i].FTQ_102OQ))){llLj1+=parseFloat(U1FXJL[i].FTQ_102OQ);}
    									if (!isNaN(parseFloat(U1FXJL[i].FTQ_105OQ))){llLj1+=parseFloat(U1FXJL[i].FTQ_105OQ);}
    									if (!isNaN(parseFloat(U1FXJL[i].FTQ_107OQ))){llLj1+=parseFloat(U1FXJL[i].FTQ_107OQ);}
    									if (!isNaN(parseFloat(U1FXJL[i].FTQ_108OQ))){llLj1+=parseFloat(U1FXJL[i].FTQ_108OQ);}
    								 tableHtml += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
    									 " <td colspan=3 rowspan=4 height=81 class=xl10023387 width=144 style='height:60.75pt;width:109pt'>分线日计量总液（m<font class='font623387'><sup>3</sup></font><font class='font723387'>）</font></td>"+
    									 " <td colspan=6 rowspan=4 class=xl7623387 width=173 style='border-bottom:.5pt solid black;width:131pt'>"+llLj.toFixed(1)+"</td>"+
    									 " <td colspan=3 rowspan=4 class=xl10023387 width=173 style='border-bottom:.5pt solid black'>分线日计量油量总液（m<font class='font623387'><sup>3</sup></font><font class='font723387'>）</font></td>"+
    									 " <td colspan=3 rowspan=4 class=xl7623387 width=173 style='border-bottom:.5pt solid black;width:131pt'>"+llLj1.toFixed(1)+"</td>"+
    									 " <td colspan=2 rowspan=4 class=xl7923387 style='border-bottom:.5pt solid black'>备注</td>"+
    									 " <td colspan=17 rowspan=4 class=xl8523387 style='border-right:.5pt solid black;border-bottom:.5pt solid black'><input id='bz' type='text' style='background:transparent;border:0px solid;width:1090px;height:76px;font-size: 10pt;' value='"+BZ+"'/></td>"+
    									" </tr>"+
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
	function onSave()
    {
		var	ZBR1 = $("#zbr").val();
		var	BZ = $("#bz").val();
		var	RQ = $("#rq").val();
		
		if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
    		$.ligerDialog.error("数据已审核过，不能进行修改");
    	}else{
    		var firstArr = [];
         	var firstStr = "";
         	for(var i=0;i<13;i++){
         		firstArr.push($("#RPD_U1_OIL_AUTO_ID"+i).val());
         		firstArr.push($("#REPORT_TIME"+i).text());
         		firstArr.push($("#te002"+i).val());
         		firstArr.push($("#pt002"+i).val());	
         		
         		firstArr.push($("#AIT002"+i).val());	
         		firstArr.push($("#x2z32bxlj"+i).val());
         		firstArr.push($("#FTQ_102OQ"+i).val());	
         		
         		firstArr.push($("#te005"+i).val());	
         		firstArr.push($("#pt005"+i).val());	
         		
         		firstArr.push($("#AIT005"+i).val());	
         		firstArr.push($("#x5_13_11lj"+i).val());
         		firstArr.push($("#FTQ_105OQ"+i).val());	
         		
         		firstArr.push($("#te007"+i).val());	
         		firstArr.push($("#pt007"+i).val());	
         		
         		firstArr.push($("#AIT007"+i).val());	
         		firstArr.push($("#x7z32nxlj"+i).val());
         		firstArr.push($("#FTQ_107OQ"+i).val());	
         		
         		firstArr.push($("#te008"+i).val());
         		firstArr.push($("#pt008"+i).val());
         		
         		firstArr.push($("#AIT008"+i).val());	
         		firstArr.push($("#x8xytxlj"+i).val());	
         		firstArr.push($("#FTQ_108OQ"+i).val());	
         		
         		firstArr.push($("#pt004"+i).val());
         		firstArr.push($("#ghjxtcclj"+i).val());			
         		firstArr.push($("#tsbccyl"+i).val());	
         		firstArr.push($("#tsbcclj"+i).val());
         		firstArr.push($("#wyccyl"+i).val());	
         		firstArr.push($("#wycclj"+i).val());	
         		firstArr.push($("#te2hz"+i).val());	
         		firstArr.push($("#pt2hz"+i).val());	
         		firstArr.push($("#q2zlj"+i).val());	
         		firstArr.push($("#te201"+i).val());	
         		firstArr.push($("#pt201"+i).val());	
         		firstArr.push($("#ghzlj"+i).val());	
         		firstArr.push($("#bdcjcyglj"+i).val());	
         		
         		firstStr += arrayToString(firstArr,",");
         		firstStr += ";";
         		firstArr = [];
         	}
    		if (modifyDataFlag($("#reportDate").val())) {
    			jQuery.ajax({
    				type : 'post',
    				url : 'u1fxjl_updateU1FXJL.action?nowdata='+parseInt(Math.random()*100000),
    				data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"SHR":SHR,"ZBR1":ZBR1,"BZ":BZ,"RQ":RQ,"firstStr":firstStr},
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
    				url : 'u1fxjl_onAudit.action?nowdata='+parseInt(Math.random()*100000),
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
		var url = "u1fxjl_exportU2_WATER.action?reportDate="+encodeURIComponent(reportDate) + '&reportName='+encodeURIComponent('水处理加药系统日报表');
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
			<div id="tableHtml" x:publishsource="Excel" style="OVERFLOW:auto; width: 99%;height: 87%;">

			</div> 
			</form>
</body>
</html>