<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>水源井运行日报</title>
   
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
       <link href="../../lib/css/fyq.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <link href="../../lib/css/syjyxrb.css" rel="stylesheet" type="text/css" />  	
    <script type="text/javascript">
    var tableval;
	var  ZBR1 ="";
	var  SHR ="";
	var  RQ ="";
	var  BZ ="";
	var  WATERMESSAGEID ="";
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
				url : 'syjyxrb_pageInit.action',
				success : function(data) { 
				
					if (data != null && typeof(data)!="undefined"&& data!=""){
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
         function onSubmit()
        {
        	//if (onSearchByDate($("#txtDate").val())){
        	 jQuery.ajax({
					type : 'post',
					url : 'syjyxrb_searchSYJYXRB.action?txtDate='+$("#txtDate").val(),
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
									    var SYJYX = outData.JSONDATA.syjyx;
									    var WATERMESSAGE = outData.JSONDATA.WATERMESSAGE;
									    if (SYJYX != null && typeof(SYJYX)!="undefined"){
									     	if(WATERMESSAGE != null && typeof(WATERMESSAGE)!="undefined"){
									     	
									     		if(WATERMESSAGE.WATERMESSAGEID != null && typeof(WATERMESSAGE.WATERMESSAGEID)!="undefined"){
									     			WATERMESSAGEID =WATERMESSAGE.WATERMESSAGEID;
									     			}else{
									     				WATERMESSAGEID ="";
									     			}
												if(WATERMESSAGE.SHR != null && typeof(WATERMESSAGE.SHR)!="undefined" && WATERMESSAGE.SHR != ''){
														SHR =WATERMESSAGE.SHR;
									     			}else{
									     				SHR ="";
										     			}
												 if(WATERMESSAGE.RQ != null && typeof(WATERMESSAGE.RQ)!="undefined"){
									     			  RQ =WATERMESSAGE.RQ;
									     			}else{
									     				RQ = "";
									     			}
									     			//alert(RQ)
									     	}else{
									     		WATERMESSAGEID ="";
									     	}
									    	//↓
						     	$("#tableHtml").html('');
						     	tableval ="<table border=0 cellpadding=0 cellspacing=0 width=1406 style='border-collapse:"+
						     		 "collapse;table-layout:fixed;width:1056pt'>"+
						     		 "<col width=116 style='mso-width-source:userset;mso-width-alt:4242;width:87pt'>"+
						     		 "<col width=162 style='mso-width-source:userset;mso-width-alt:5924;width:122pt'>"+
						     		 "<col width=141 style='mso-width-source:userset;mso-width-alt:5156;width:106pt'>"+
						     		 "<col width=158 style='mso-width-source:userset;mso-width-alt:5778;width:119pt'>"+
						     		 "<col width=171 style='mso-width-source:userset;mso-width-alt:6253;width:128pt'>"+
						     		 "<col width=260 style='mso-width-source:userset;mso-width-alt:9508;width:195pt'>"+
						     		 "<col width=150 style='mso-width-source:userset;mso-width-alt:5485;width:113pt'>"+
						     		 "<col width=124 span=2 style='mso-width-source:userset;mso-width-alt:4534;width:93pt'>"+
						     		 "<tr class=xl67 height=34 style='height:25.5pt'>"+
						     		 "<td colspan=6 height=34 class=xl76 width=1008 style='height:25.5pt;"+
						     		 "width:757pt'>风城供汽联合站集中水处理站水源井运行日报</td>"+
						     		 "</tr>"+
						     		 "<tr class=xl67 height=34 style='height:25.5pt'>"+
						     		 "<td colspan=3 height=34 class=xl77 style='height:25.5pt'>　</td>"+
						     		 "<td class=xl74 style='border-top:none;border-left:none'>审核人：</td>"+
						     		 "<td class=xl75 style='border-top:none;border-left:none'>"+WATERMESSAGE.SHR+"</td>"+
						     		 "<td class=xl75 style='border-top:none;border-left:none'>"+WATERMESSAGE.RQ+"</td>"+
						     		 "</tr>"+
						     		  "<td height=38 class=xl70 width=116 style='height:28.5pt;border-top:none;width:87pt'>井号</td>"+
						     		  "<td class=xl65 width=162 style='border-top:none;border-left:none;width:122pt'>供水瞬时流量<br>m3/h</td>"+
						     		  "<td class=xl65 width=141 style='border-top:none;border-left:none;width:106pt'>供水累积流量<br>m3</td>"+
						     		  "<td class=xl65 width=158 style='border-top:none;border-left:none;width:119pt'>供水压力<br><font class='font7'>MPa</font></td>"+
						     		  "<td class=xl65 width=171 style='border-top:none;border-left:none;width:128pt'>水位<br>m</td>"+
						     		  "<td class=xl65 width=260 style='border-top:none;border-left:none;width:195pt'>备注</td>"+
						     		 "</tr>";
						     		 for(var i =0; i<SYJYX.length; i++){
							     		 //alert(SYJYX[i].id)
						     			tableval += "<tr class=xl66 height=18 style='mso-height-source:userset;height:13.5pt'>"+
							     		  "<td height=18 class=xl69 width=116 style='height:13.5pt;border-top:none;width:87pt'>"+SYJYX[i].syjName+"</td>"+
							     		  "<td class=xl65 width=162 style='border-top:none;border-left:none;width:122pt'><input id='INSTANTANEOUS_DELIVERY"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:120px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+SYJYX[i].INSTANTANEOUS_DELIVERY+"'/></td>"+
							     		  "<td class=xl65 width=141 style='border-top:none;border-left:none;width:106pt'><input id='TAC_FLOW"+i+"'  onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:120px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+SYJYX[i].TAC_FLOW+"'/></td>"+
							     		  "<td class=xl65 width=158 style='border-top:none;border-left:none;width:119pt'><input id='PRES"+i+"'  onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:120px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+SYJYX[i].PRES+"'/></td>"+
							     		  "<td class=xl65 width=171 style='border-top:none;border-left:none;width:128pt'><input id='LIQUID_LEVEL"+i+"'  onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:120px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+SYJYX[i].LIQUID_LEVEL+"'/></td>"+
							     		  "<td class=xl73 width=260 style='border-top:none;border-left:none;width:195pt'><input id='REMARK"+i+"'   type='text' style='background:transparent;border:0px solid;width:120px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+SYJYX[i].REMARK+"'/></td>"+
										  "<td width=69 style='border-top:none;border-left:none;width:52pt'><input type='hidden' id='rpdid"+i+"' value='"+SYJYX[i].rpdid+"'/>　</td>"+	    			
										  "<td width=69 style='border-top:none;border-left:none;width:52pt'><input type='hidden' id='id"+i+"' value='"+SYJYX[i].id+"'/>　</td>"+	    			

											"</tr>";
							     		 }
						     			tableval += "</table>";
						     								     	
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
 				var RQ = $("#txtDate").val();
 				//alert(RQ)
         	//var RPDREPORTMESSAGEID = $("#RPDREPORTMESSAGEID").val();
         	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
         		$.ligerDialog.error("数据已审核过，不能进行修改");
         	}else{

          		
          		var firstArr = [];
              	var firstStr = "";
             	for(var i=0;i<14;i++){
             		firstArr.push($("#rpdid"+i).val());
             		firstArr.push($("#id"+i).val());
             		firstArr.push($("#INSTANTANEOUS_DELIVERY"+i).val());
             		firstArr.push($("#TAC_FLOW"+i).val());
             		firstArr.push($("#PRES"+i).val());
             		firstArr.push($("#LIQUID_LEVEL"+i).val());
             		firstArr.push($("#REMARK"+i).val());
              		firstStr += arrayToString(firstArr,",");
              		firstStr += ";";
              		firstArr = [];
                 	}
             	//alert(firstStr)
             	if (modifyDataFlag($("#txtDate").val())) {
          		 jQuery.ajax({
  					type : 'post',
  					url : 'syjyxrb_updateSYJyxrb.action?nowdata='+parseInt(Math.random()*100000),
  					data: {"firstStr":firstStr,"RQ":RQ},
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
     				$.ligerDialog.error("只能对当日报表数据进行修改");
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
 					url : 'syjyxrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
 					data: {"WATERMESSAGEID":WATERMESSAGEID},
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
 			
 			//alert(RPDREPORTMESSAGEID);
 			
         }
         
         function onExport() {
     		var txtDate=$("#txtDate").val();
     		var url = "syjyxrb_exportExcel.action?txtDate="+encodeURIComponent(txtDate) + '&reportName='+encodeURIComponent('水源井运行日报');
     		//if (onSearchByDate(txtDate)) {
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
<body link=blue vlink=fuchsia>
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table>
					<tr>	
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>
						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
			
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<div id="tableHtml" style="OVERFLOW:auto; width: 99%;height: 87%; align=center" >

				
		
	

</div>
	

     
  </form>
    
</body>
</html>
