<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>软化器再生一览表</title>
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
	var	 ZSCS;
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
            { id: 47, text: '216型3', pid: 3}
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
				url : 'rhqzs_pageInit.action',
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
					url : 'rhqzs_searchDatas.action?nowdata='+parseInt(Math.random()*100000),
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
													 if(WATERMESSAGE.ZSCS != null && typeof(WATERMESSAGE.ZSCS)!="undefined"){
									     				ZSCS =WATERMESSAGE.ZSCS;
										     			}else{
										     				ZSCS = "";
										     			}

								 if(WATERMESSAGE.A != null && typeof(WATERMESSAGE.A)!="undefined"){
									     				A =WATERMESSAGE.A;
										     			}else{
										     				A = " ";
										     			}
														
								 if(WATERMESSAGE.B != null && typeof(WATERMESSAGE.B)!="undefined"){
									     				B =WATERMESSAGE.B;
										     			}else{
										     				B = " ";
										     			}
														
								 if(WATERMESSAGE.C != null && typeof(WATERMESSAGE.C)!="undefined"){
									     				C =WATERMESSAGE.C;
										     			}else{
										     				C = " ";
										     			}


$("#tableHtml").html('');
						     	tableval ="<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:"+
									 "collapse;table-layout:fixed;width:800pt'>"+
					 "<tr height=28 style='mso-height-source:userset;height:21.0pt'>"+
					 " <td colspan=15 height=28 class=xl71 style='height:21.0pt'"+
									  ">风城油田作业区供汽联合站"+$("#clz").val()+$("#rhq").val()+"号软化器运行日报</td>"+
									" </tr>"+					     
				 "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
				 " <td bgColor=#CCCCFF colspan=2 height=58 class=xl69 style='border-bottom:.5pt solid black;"+
									 " height:44.1pt;border-top:none'>日期</td>"+
					" <td bgColor=#CCCCFF colspan=2 class=xl65 style='border-left:none'>站库</td>"+
					 " <td bgColor=#CCCCFF colspan=2 class=xl65 style='border-left:none'>软化器编号</td>"+
					 " <td bgColor=#CCCCFF class=xl65 style='border-left:none'>再生组数</td>"+
						 " <td bgColor=#CCCCFF colspan=8 class=xl65 style='border-left:none'>再生过程描述</td>"+
					 "</tr>";

												
tableval += "<tr height=24 style='mso-height-source:userset;height:18.0pt'>"+
		"<td height=24 colspan=2 class=xl65111 style='height:18.0pt;border-top:none'>"+$("#txtDate").val()+"</td>"+	
		"<td class=xl65111 colspan=2 style='border-top:none;border-left:none'>"+$("#clz").val()+"</td>"+
		"<td class=xl65111 colspan=2 style='border-top:none;border-left:none'>"+$("#rhq").val()+"</td>"+
		 "<td class=xl65111 style='border-top:none;border-left:none'>"+ZSCS+"</td>"+
		"<td class=xl65112 colspan=8 style='border-top:none;border-left'>"+A+"<br>"+B+"<br>"+C+"</td>"+
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
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
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
	<!--					<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>

						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
					-->	
						</tr>
				
				</table>
			
				<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<div style="text-align: center;">
					<div id="tableHtml" style="OVERFLOW:auto; height: 87%;" align="center"></div>
				</div>
					
				

</form>
</body>
</html>
