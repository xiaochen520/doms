<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>一号稠油处理站分线计量日报表</title>  
   
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
	var SHSJ="";
	var rwsq = "";
	var mod ;
        $(function ()
        {
        
 			var yearid = $('#SYear');    //年所在的控件
            var monthid = $('#SMonth');    //月所在的控件
            //var dayid = $('#SDay')    //天所在的控件
            var myDate = new Date();
            var year = myDate.getFullYear();
            
            var month=myDate.getMonth()+1;
            
            for(var i=(year-20);i<=(year+20);i++){
                yearid.append('<option value="'+i+'">'+i+'</option>');
            }
                for(var i=1;i<=12;i++){
                    monthid.append('<option value="'+i+'">'+i+'</option>');
                }
            
           $("#SYear").val(year);
           $("#SMonth").val(month);	
         $.metadata.setType("attr", "validate");
	            var v = $("form").validate({
	                //调试状态，不会提交数据的
	                debug: true,
	                errorPlacement: function (lable, element)
	                {	
	                    if (element.hasClass("l-textarea"))
	                    {
	                        element.addClass("l-textarea-invalid");
	                    }
	                    else if (element.hasClass("l-text-field"))
	                    {
	                        element.parent().addClass("l-text-invalid");
	                    }
	                    $(element).removeAttr("title").ligerHideTip();
	                    $(element).attr("title", lable.html()).ligerTip();
	                },
	                success: function (lable)
	                {
	                	if(lable.attr("for")!=""){
	                		var element = $("#" + lable.attr("for"));
		                    if (element.hasClass("l-textarea"))
		                    {
		                        element.removeClass("l-textarea-invalid");
		                    }
		                    else if (element.hasClass("l-text-field"))
		                    {
		                        element.parent().removeClass("l-text-invalid");
		                    }
		                    $(element).removeAttr("title").ligerHideTip();
	                	}	                    
	                }	               
	            });
	            $("form").ligerForm();
	            $(".l-button-test").click(function () {
	            });          
          
            //获取报表及功能按钮ｊｓ
           jQuery.ajax({
				type : 'post',
				url : 'cyfx1_pageInit.action',
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
        		var SYear = $("#SYear").val();
				var SMonth = $("#SMonth").val();
        	 jQuery.ajax({
					type : 'post',
					url : 'cyfx1_searchFxjl1.action?SYear='+encodeURIComponent(SYear)+'&SMonth='+encodeURIComponent(SMonth),
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
									    ID = U2OILS[0].ID;									   									    
//									    var U1S1SS = outData.JSONDATA.U1S1SS;									    
									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									   
									    if (U2OILS != null && typeof(U2OILS)!="undefined"){
									     	if(REPORTMESSAGE != null && typeof(REPORTMESSAGE)!="undefined"){
									     		if(REPORTMESSAGE.RPDREPORTMESSAGEID != null && typeof(REPORTMESSAGE.RPDREPORTMESSAGEID)!="undefined"){
									     				RPDREPORTMESSAGEID =REPORTMESSAGE.RPDREPORTMESSAGEID;	
									     			}else{
									     				RPDREPORTMESSAGEID ="";
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
												if(REPORTMESSAGE.SHBZ != null && typeof(REPORTMESSAGE.SHBZ)!="undefined"){
									     			  SHBZ =REPORTMESSAGE.SHBZ;
									     	
									     			}else{
									     				SHBZ = "";
									     			}
												 //alert(RPDREPORTMESSAGEID);
									     	}else{
									     		RPDREPORTMESSAGEID ="";
									     	}
									    	//↓
						    	$("#tableHtml").html('');
						     		tableval ="<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:"+
					     		 "collapse;table-layout:fixed;width:1850pt'>"+
					     		 "<tr height=28 style='mso-height-source:userset;height:21.0pt'>"+
					     		 " <td colspan=35 height=28 class=xl71 style='height:21.0pt'"+
					     		  ">一号稠油处理站分线日报表</td>"+
					     		" </tr>"+	
							     		"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
 										" <td colspan=2 class=xl65>值班人：</td>"+
											"<td colspan=9 class=xl65><input id='ZBR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled'/></td> "+							     		
							     		" <td colspan=2 class=xl65>审核：</td>"+
											"<td colspan=10 class=xl65><input id='SHR' type='text' style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+SHR+"'/></td> "+							     		
							     		" <td colspan=2 class=xl65>日期：</td>"+
										" <td colspan=10 class=xl65><input id='RQ' type='text'  style='background:transparent;border:0px solid;font-size: 12pt;text-align:center' disabled='disabled' value='"+RQ+"'/></td> "+							     		
							     		 "</tr>"+							     		 
							     		"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
							     		 " <td bgColor=#CCCCFF rowspan=3 height=58 class=xl69 style='border-bottom:.5pt solid black;"+
					     		 		"height:36pt;border-top:none'>单位</td>"+
							     		 " <td bgColor=#CCCCFF rowspan=2 colspan=8 class=xl65 style='border-left:none'>处理站</td>"+
							     		" <td bgColor=#CCCCFF colspan=23 class=xl65 style='border-left:none'>分线计量</td>"+
							     		" <td bgColor=#CCCCFF rowspan=4 colspan=3 class=xl65 style='border-left:none'>备注</td>"+
							     		"</tr>"+							     		
							     		"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
							     		 " <td bgColor=#CCCCFF colspan=11 class=xl65 style='border-left:none'>风城采油一站</td>"+
							     		 " <td bgColor=#CCCCFF colspan=3 class=xl65 style='border-left:none'>风城采油二站</td>"+
							     		 " <td bgColor=#CCCCFF colspan=4 class=xl65 style='border-left:none'>SAGD采油二站</td>"+
							     		 " <td bgColor=#CCCCFF colspan=3 class=xl65 style='border-left:none'>SAGD采油一站</td>"+							     		  
							     		  " <td bgColor=#CCCCFF rowspan=2 colspan=2 class=xl65 style='border-left:none'>分线总</td>"+
							     		"</tr>"+								     									     								     		
							     		"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
							     		"<td bgColor=#CCCCFF colspan=3 class=xl65 style='border-top:none;text-align:center'>净化罐</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>提升泵</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>污油</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>污油盘库</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>分线掺柴油</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>盘库</td>"+
							     		 "<td bgColor=#CCCCFF colspan=3 class=xl65 style='border-left:none'>重32北线</td>"+
							     		 "<td bgColor=#CCCCFF colspan=3 class=xl65 style='border-left:none'>13-11线</td>"+
							     		 "<td bgColor=#CCCCFF colspan=3 class=xl65 style='border-left:none'>重32南线</td>"+							     		
							     		 "<td bgColor=#CCCCFF rowspan=2 class=xl65 style='border-left:none'>液量（m3）</td>"+
							     		 "<td bgColor=#CCCCFF rowspan=2 class=xl65 style='border-left:none'>油量（t）</td>"+							     		
										"<td bgColor=#CCCCFF colspan=3 class=xl65 style='border-left:none'>18-1线（特一联）</td>"+
							     		"<td bgColor=#CCCCFF colspan=4 class=xl65 style='border-left:none'>SAGD线</td>"+
							     		"<td bgColor=#CCCCFF colspan=3 class=xl65 style='border-left:none'>30万密闭</td>"+
							     		"</tr>"+						     		 							     		
							     		"<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-bottom:.5pt solid black;"+
					     		 		"height:18.0pt;border-top:none'> 日期</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>液量（m³）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>油量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>底水（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>液量（m³）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>油量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>含水（%）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>液量（m³）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>油量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>含水（%）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>液量（m³）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>油量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>含水（%）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>液量（m³）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>油量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>含水（%）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>液量（m³）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>减法油量(t)</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>DMS油量(t)</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>含水（%）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>油量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>水量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>总液量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>液量（t）</td>"+
							     		"<td bgColor=#CCCCFF class=xl65 style='border-left:none'>油量（t）</td>"+
							     		"</tr>";							     								     		
							     		var disStr="";
							     		if(mod==1){
											disStr = "onclick='isChange(this)'";
										}
					     	 	 for(var i=0; i<U2OILS.length; i++) {
						     	 	 if(U2OILS[i].report_date == '旬合计'){						     	 		 							     	 		 	
										tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+											
										 "<td  height=24 class=xl65111  type='hidden' style='display: none'>"+U2OILS[i].rpd_cy1_line_messure_id+"</td>"+
							    			  "<td  height=24 class=xl65111 style='height:18.0pt;border-top:none'>"+U2OILS[i].report_date+"</td>"+
								     		  "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].jhg_yel+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].jhg_youl+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].jhg_ds+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].tsb+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].wuy+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].wuypk+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].fxccy+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].pank+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102oq+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102hs+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105oq+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105hs+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107oq+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107hs+"</td>"+
								     		    "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_c1+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_c1y+"</td>"+							
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103oq+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103hs+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01+"</td>"+								     		
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].zjfyl+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01oq+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01hs+"</td>"+							     		 								     		 
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s1y+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s1s+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].zyel+"</td>"+								     		
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].yel+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].youl+"</td>"+
								     		  "<td class=xl65111 colspan=3 style='border-top:none;border-left:none;text-align:left'>"+U2OILS[i].remark+"</td>"+
											"</tr>";							     	
						     	 	 } else if(U2OILS[i].report_date == '月总产量'){
						     	 			tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
								     		"<td  height=24 class=xl65111  type='hidden' style='display: none'>"+U2OILS[i].rpd_cy1_line_messure_id+"</td>"+
							    			  "<td  height=24 class=xl65111 style='height:18.0pt;border-top:none'>"+U2OILS[i].report_date+"</td>"+
							    			    "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].jhg_yel+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].jhg_youl+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].jhg_ds+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].tsb+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].wuy+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].wuypk+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].fxccy+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].pank+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102oq+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102hs+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105oq+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105hs+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107oq+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107hs+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_c1+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_c1y+"</td>"+								     		  
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103oq+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103hs+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01+"</td>"+								     		
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].zjfyl+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01oq+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01hs+"</td>"+							     		 								     		 
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s1y+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s1s+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].zyel+"</td>"+								     		
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].yel+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].youl+"</td>"+
								     		  "<td class=xl65111 colspan=3 style='border-top:none;border-left:none;text-align:left'>"+U2OILS[i].remark+"</td>"+
											"</tr>";
 						     	 	 }else if(U2OILS[i].report_date == '日平均产量'){
						     	 	 tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
								     		"<td  height=24 class=xl65111  type='hidden' style='display: none'>"+U2OILS[i].rpd_cy1_line_messure_id+"</td>"+
							    			  "<td  height=24 class=xl65111 style='height:18.0pt;border-top:none'>"+U2OILS[i].report_date+"</td>"+
							    			    "<td class=xl65111 style='border-top:none;border-left:none'>"+U2OILS[i].jhg_yel+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].jhg_youl+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].jhg_ds+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].tsb+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].wuy+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].wuypk+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].fxccy+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].pank+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102oq+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102hs+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105oq+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105hs+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107oq+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107hs+"</td>"+
								     		    "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_c1+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_c1y+"</td>"+								     		
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103oq+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103hs+"</td>"+
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01+"</td>"+								     		
								     		"<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].zjfyl+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01oq+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01hs+"</td>"+							     		 								     		 
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s1y+"</td>"+
								     		  "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s1s+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].zyel+"</td>"+								     		
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].yel+"</td>"+
								     		 "<td class=xl65111  style='border-top:none;border-left:none'>"+U2OILS[i].youl+"</td>"+
								     		  "<td class=xl65111 colspan=3 style='border-top:none;border-left:none;text-align:left'>"+U2OILS[i].remark+"</td>"+
											"</tr>";						     	 	 
						     	 	 }else{
						     	 	 	tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
								     		"<td  height=24 class=xl65  type='hidden' style='display: none'>"+U2OILS[i].rpd_cy1_line_messure_id+"</td>"+
							    			  "<td  height=24 class=xl65 style='height:18.0pt;border-top:none'>"+U2OILS[i].report_date+"</td>"+
							    			    "<td class=xl65 style='border-top:none;border-left:none'>"+U2OILS[i].jhg_yel+"</td>"+
								     		"<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].jhg_youl+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].jhg_ds+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].tsb+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].wuy+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].wuypk+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].fxccy+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].pank+"</td>"+
								     		 "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102+"</td>"+
								     		 "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102oq+"</td>"+
								     		"<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_102hs+"</td>"+
								     		"<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105oq+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_105hs+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107oq+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_107hs+"</td>"+
								     		    "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_c1+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_c1y+"</td>"+								     		
								     		 "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103+"</td>"+
								     		 "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103oq+"</td>"+
								     		"<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_103hs+"</td>"+
								     		"<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01+"</td>"+								     		
								     		"<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].zjfyl+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01oq+"</td>"+
								     		 "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s01hs+"</td>"+							     		 								     		 
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s1y+"</td>"+
								     		  "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].ftq_s1s+"</td>"+
								     		 "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].zyel+"</td>"+								     		
								     		 "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].yel+"</td>"+
								     		 "<td class=xl65  style='border-top:none;border-left:none'>"+U2OILS[i].youl+"</td>"+
								     		  "<td class=xl65 colspan=3 style='border-top:none;border-left:none;text-align:left'>"+U2OILS[i].remark+"</td>"+
											"</tr>";
						     	 	 }	
					     	 	 } 
					     		"</table>";
					     								     	
									//↑
						     		$("#tableHtml").html(tableval);	
						    
									    }else{
											$.ligerDialog.error("当前日期无数据，请选择其他日期！");
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
        }
        

         
   
          function onExport() {
     		var SYear = $("#SYear").val();
			var SMonth = $("#SMonth").val();
     		var url = "cyfx1_exportcyfx1.action?SYear="+encodeURIComponent(SYear)+'&SMonth='+encodeURIComponent(SMonth);
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
				<td><select name="SYear" class="l-table-select" align="left"
					style="width: 20px" id="SYear">
				</select></td>
				<td align="left" style="width: 20px">年</td>
				<td><select name="SMonth" class="l-table-select" align="left"
					style="width: 20px" id="SMonth">
				</select></td>
				<td align="left" style="width: 20px">月</td>
				<td align="right" class="l-table-edit-td" style="width: 20px"></td>						
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>						
						</tr>				
				</table>	
	<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		


<div id="tableHtml" align=center x:publishsource="Excel" style="height:450px;width:98%;height: 89%; overflow:auto;text-align: center;">

	</div>
    
  </form>
    
</body>
</html>
