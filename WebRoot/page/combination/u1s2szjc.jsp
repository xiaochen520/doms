<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>一号稠油联合处理站水二区水质监测记录报表</title>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="pragma" content="no-cache"></meta>
<meta http-equiv="cache-control" content="no-cache"></meta>
<meta http-equiv="expires" content="0"></meta>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>

<link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="../../lib/jquery/jquery-1.5.2.min.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerRadio.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script src="../../lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="../../lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script src="../../lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>

<script src="../../lib/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript"></script>
<script src="../../lib/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->
<link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />
<script src="../../lib/js/common.js" type="text/javascript"></script>

<script src="../../lib/json2.js" type="text/javascript"></script>
<script src="../../noBackspace.js" type="text/javascript"></script>
<script src="../../lib/myday.js" type="text/javascript"></script>
<!-- <script src="../../columnnamedisplay.js" type="text/javascript"></script> -->
<script type="text/javascript">
      var grid = null;
     var toolbar ;
     // var textValue ="";
      //var namedisplay ="";
      //var columnname ="";
      //var pwaterparam = "";
      var d ;  //日期
      var datestr = "";   //当前系统时间
      
      var authoruty = 0;  //0 无需该权限， 1 有修改权限
      //var exportFlag = false;
       var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
       
      // var selectrowdata ;
      // var newrowdata ;
       
      	var BBRQ = "";
		var BBSJ = "";
		var HYS2TCGJK = "";
		var HYS2TCGCK = "";
		var HYG4_3 = "";
		var HYS2QFCK = "";
		var HYS2HCC = "";
		var HYS2YJCK = "";
		var HYS2EJCK = "";
		var XFS2TCGJK = "";
		var XFS2TCGCK = "";
		var XFG4_3 = "";
		var XFS2QFCK = "";
		var XFS2HCC = "";
		var XFS2YJCK = "";
		var XFS2EJCK = "";
		var YDS2TCGCK = "";
		var YDS2EJCK = "";
		var YDS2TCGJK = "";var YD4_3 = "";var YDS2HCC = "";var YDS2QF = "";var YDS2YJCK = "";var BZ = "";
		var RPD_U1_WATER_QUALITY2_ID = "";
		
     $(function(){
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
            //获取报表及功能按钮ｊｓ
                jQuery.ajax({
                	
					type : 'post',
					url : 'u1s2szjc_pageInit.action',
					async : false,
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
							var data = eval('(' + jsondata + ')');
							if (data.toolbar != null && typeof(data.toolbar)!="undefined" && data.toolbar.items != null && typeof(data.toolbar.items)!="undefined"){
								var toolteams = data.toolbar.items;
								for(var i=0; i<toolteams.length ; i++){
									if(toolteams[i].id == 'modifyid'){
										authoruty  = 1;
									}
								}
							}
							grid = $("#maingrid").ligerGrid(data);					
							toolbar = grid.topbar.ligerGetToolBarManager();		
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
						
					}
				});
				
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
		                		
		                    
		                },
		                submitHandler: function ()
		                {
							var HYS2TCGJK1 = $("#HYS2TCGJK").val();
							var HYS2TCGCK1 = $("#HYS2TCGCK").val();
							var HYG4_31 = $("#HYG4_3").val();
							var HYS2QFCK1 = $("#HYS2QFCK").val();
							var HYS2HCC1 = $("#HYS2HCC").val();
							var HYS2YJCK1 = $("#HYS2YJCK").val();
							var HYS2EJCK1 = $("#HYS2EJCK").val();
							var XFS2TCGJK1 = $("#XFS2TCGJK").val();
							var XFS2TCGCK1 = $("#XFS2TCGCK").val();
							var XFG4_31 = $("#XFG4_3").val();
							var XFS2QFCK1 = $("#XFS2QFCK").val();
							var XFS2HCC1 = $("#XFS2HCC").val();
							var XFS2YJCK1 = $("#XFS2YJCK").val();
							var XFS2EJCK1 = $("#XFS2EJCK").val();
							var YDS2TCGCK1 = $("#YDS2TCGCK").val();
							var YDS2EJCK1 = $("#YDS2EJCK").val();
							
							var YDS2TCGJK1 = $("#YDS2TCGJK").val(); var YD4_31 = $("#YD4_3").val(); var YDS2HCC1 = $("#YDS2HCC").val();
							var YDS2QF1 = $("#YDS2QF").val(); var YDS2YJCK1 = $("#YDS2YJCK").val(); var BZ1 = $("#BZ").val();
							var RPD_U1_WATER_QUALITY2_ID1 = $("#RPD_U1_WATER_QUALITY2_ID").val();	  
							
		              	   //判断修改还是添加操作 1-添加、2-修改
		              	   if(operate == 1){
		              	   
		              	   //2-修改
		              	   }else if(operate == 2){
		              	   		jQuery.ajax({
										type : 'post',
										url : 'u1s2szjc_updateU2szjc.action',
										data: {
											//"BBRQ":BBRQ1,
											//"BBSJ":BBSJ1,
											"HYS2TCGJK":HYS2TCGJK1,
											"HYS2TCGCK":HYS2TCGCK1,
											"HYG4_3":HYG4_31,
											"HYS2QFCK":HYS2QFCK1,
											"HYS2HCC":HYS2HCC1,
											"HYS2YJCK":HYS2YJCK1,
											"HYS2EJCK":HYS2EJCK1,
											"XFS2TCGJK":XFS2TCGJK1,
											"XFS2TCGCK":XFS2TCGCK1,
											"XFG4_3":XFG4_31,
											"XFS2QFCK":XFS2QFCK1,
											"XFS2HCC":XFS2HCC1,
											"XFS2YJCK":XFS2YJCK1,
											"XFS2EJCK":XFS2EJCK1,
											"YDS2TCGCK":YDS2TCGCK1,
											"YDS2EJCK":YDS2EJCK1,
											"YDS2TCGJK":YDS2TCGJK1,
											"YD4_3":YD4_31,
											"YDS2HCC":YDS2HCC1,
											"YDS2QF":YDS2QF1,
											"YDS2YJCK":YDS2YJCK1,
											"BZ":BZ1,
											"RPD_U1_WATER_QUALITY2_ID":RPD_U1_WATER_QUALITY2_ID1
										},
										async : false,
										timeout : '3000',
										success : function(data) { 
											if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
														$.ligerDialog.success(outData.CONFIRMMSG);
													}else{
														
														onSubmit();
														operate = 0;
														setpage1(1); //隐藏编辑界面
														setItemsd(2); //去掉隐藏，显示按钮
														//var dialog = $.ligerDialog.success('修改成功！');
														alert('修改成功！');
														//setTimeout(function () { dialog.close(); }, 1000);
														
														
													}
											}
										},
										error : function(data) {
										}
									});
		              	   
		              	   }
				          
		                }
		            });
		            $("form").ligerForm();
		            $(".l-button-test").click(function () {
		            });
        });
        
      
         function fromAu(rowdata){
         	//alert(JSON2.stringify(rowdata));
         	
         	//columnname = "";
			//textValue = "";
			//namedisplay ="";
			//判断用户 是否拥有修改权限
			if(authoruty  == 1){
				if(rowdata.RPD_U1_WATER_QUALITY2_ID != "空行"){
					d  = new Date();
					datestr = d.Format("yyyy-MM-dd");
					if(rowdata.BBRQ == datestr || rowdata.BBRQ == getTomorrow()) {
						//selectrowdata = rowdata;
			        	//用户选择修改数据
			        	BBRQ = getRemoveData(rowdata.BBRQ);
						BBSJ = getRemoveData(rowdata.BBSJ);
						HYS2TCGJK = getRemoveData(rowdata.HYS2TCGJK);
						HYS2TCGCK = getRemoveData(rowdata.HYS2TCGCK);
						HYG4_3 = getRemoveData(rowdata.HYG4_3);
						HYS2QFCK = getRemoveData(rowdata.HYS2QFCK);
						HYS2HCC = getRemoveData(rowdata.HYS2HCC);
						HYS2YJCK = getRemoveData(rowdata.HYS2YJCK);
						HYS2EJCK = getRemoveData(rowdata.HYS2EJCK);
						XFS2TCGJK = getRemoveData(rowdata.XFS2TCGJK);
						XFS2TCGCK = getRemoveData(rowdata.XFS2TCGCK);
						XFG4_3 = getRemoveData(rowdata.XFG4_3);
						XFS2QFCK = getRemoveData(rowdata.XFS2QFCK);
						XFS2HCC = getRemoveData(rowdata.XFS2HCC);
						XFS2YJCK = getRemoveData(rowdata.XFS2YJCK);
						XFS2EJCK = getRemoveData(rowdata.XFS2EJCK);
						YDS2TCGCK = getRemoveData(rowdata.YDS2TCGCK);
						YDS2EJCK = getRemoveData(rowdata.YDS2EJCK);
						
						YDS2TCGJK = getRemoveData(rowdata.YDS2TCGJK);
						YD4_3 = getRemoveData(rowdata.YD4_3);
						YDS2HCC = getRemoveData(rowdata.YDS2HCC);
						YDS2QF = getRemoveData(rowdata.YDS2QF);
						YDS2YJCK = getRemoveData(rowdata.YDS2YJCK);
						BZ = getRemoveData(rowdata.BZ);
						RPD_U1_WATER_QUALITY2_ID = getRemoveData(rowdata.RPD_U1_WATER_QUALITY2_ID);
						
						if(operate == 2){
			               		assignM(2);
			               	}
					}else{
						$.ligerDialog.error("只能修改所属日期为"+datestr+"或"+getTomorrow()+"的数据！");
						return false;
					}
					
				}else{
					$.ligerDialog.error("空行不能进行数据修改！");
        			return false;
				}
					
			}
        	
				                
        } 
        
        function assignM(flg){
				 if(flg == 2){
				 	document.getElementById("BBRQ").value = BBRQ;
					document.getElementById("BBSJ").value = BBSJ;
					document.getElementById("HYS2TCGJK").value = HYS2TCGJK;
					document.getElementById("HYS2TCGCK").value = HYS2TCGCK;
					document.getElementById("HYG4_3").value = HYG4_3;
					document.getElementById("HYS2QFCK").value = HYS2QFCK;
					document.getElementById("HYS2HCC").value = HYS2HCC;
					document.getElementById("HYS2YJCK").value = HYS2YJCK;
					document.getElementById("HYS2EJCK").value = HYS2EJCK;
					document.getElementById("XFS2TCGJK").value = XFS2TCGJK;
					document.getElementById("XFS2TCGCK").value = XFS2TCGCK;
					document.getElementById("XFG4_3").value = XFG4_3;
					document.getElementById("XFS2QFCK").value = XFS2QFCK;
					document.getElementById("XFS2HCC").value = XFS2HCC;
					document.getElementById("XFS2YJCK").value = XFS2YJCK;
					document.getElementById("XFS2EJCK").value = XFS2EJCK;
					document.getElementById("YDS2TCGCK").value = YDS2TCGCK;
					document.getElementById("YDS2EJCK").value = YDS2EJCK;
					
					document.getElementById("YDS2TCGJK").value = YDS2TCGJK;
					document.getElementById("YD4_3").value = YD4_3;
					document.getElementById("YDS2HCC").value = YDS2HCC;
					document.getElementById("YDS2QF").value = YDS2QF;
					document.getElementById("YDS2YJCK").value = YDS2YJCK;
					document.getElementById("BZ").value = BZ;
					document.getElementById("RPD_U1_WATER_QUALITY2_ID").value = RPD_U1_WATER_QUALITY2_ID;
				 }else if(flg == 1){
				 	document.getElementById("BBRQ").value = "";
					document.getElementById("BBSJ").value = "";
					document.getElementById("HYS2TCGJK").value = "";
					document.getElementById("HYS2TCGCK").value = "";
					document.getElementById("HYG4_3").value = "";
					document.getElementById("HYS2QFCK").value = "";
					document.getElementById("HYS2HCC").value = "";
					document.getElementById("HYS2YJCK").value = "";
					document.getElementById("HYS2EJCK").value = "";
					document.getElementById("XFS2TCGJK").value = "";
					document.getElementById("XFS2TCGCK").value = "";
					document.getElementById("XFG4_3").value = "";
					document.getElementById("XFS2QFCK").value = "";
					document.getElementById("XFS2HCC").value = "";
					document.getElementById("XFS2YJCK").value = "";
					document.getElementById("XFS2EJCK").value = "";
					document.getElementById("YDS2TCGCK").value = "";
					document.getElementById("YDS2EJCK").value = "";
					
					document.getElementById("YDS2TCGJK").value = "";
					document.getElementById("YD4_3").value = "";
					document.getElementById("YDS2HCC").value = "";
					document.getElementById("YDS2QF").value = "";
					document.getElementById("YDS2YJCK").value = "";
					document.getElementById("BZ").value = "";
					document.getElementById("RPD_U1_WATER_QUALITY2_ID").value = "";
				 }	
			 }
     function onSubmit()
        {
        	//exportFlag = false;
        	grid.setOptions({
        		parms: [
        		{
					name: 'SYear',
					value: $("#SYear").val()
				},{
					name: 'SMonth',
					value: $("#SMonth").val()
				}
				]
        	});
         	grid.loadData(true);
        }
        
	function onExport() {
		var SYear = $("#SYear").val();
		var SMonth = $("#SMonth").val();
		var url = "u1s2szjc_exportU2_SZJC.action?SYear="+encodeURIComponent(SYear) +"&SMonth="+encodeURIComponent(SMonth)+ "&reportName="+encodeURIComponent('二号稠油联合处理站-水质监测报表');
			$.ligerDialog.confirm('确定导出吗?',function (yes) {
				if (yes) {
					window.location.href= url;
					/* if (!(window.location.href= url)) {
						$.ligerDialog.confirm('没有可导出的数据！');
					} */
				}
			});
		
	}
       function itemclick(item) {
		//var rows = grid.grid.getSelectedRow();
	
		switch (item.icon) {
			 case "modify":
                	
						if(RPD_U1_WATER_QUALITY2_ID != null && RPD_U1_WATER_QUALITY2_ID !=""  && typeof(RPD_U1_WATER_QUALITY2_ID)!="undefined" && RPD_U1_WATER_QUALITY2_ID != "空行"){	
							if(operate != 1 && operate != 2){
		              	    		setpage1(0); //显示编辑界面
							 		setItemsd(0);//1-隐藏编辑区添加显示按钮
								}
		              	   operate = 2;
		              	   assignM(2);
						}
                  break;
			
			case "audit":
				
			break;
			case "save":
					$("#form1").submit();
					break;
              case "up":
           		setpage1(0); //显示编辑界面
            	    setItemsd(0);//0-显示编辑区，添加隐藏按钮
                break;   
                case "down":
				 	setpage1(1); //隐藏编辑界面
				 	setItemsd(1);//1-隐藏编辑区添加显示按钮
                break;  
		}
	} 
	
	
		
        function getSelected()
        { 
            var row = grid.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
        }
      
	 
    </script>
<style type="text/css">
html,body {
	font-size: 12px;
	margin: 0px;
	padding: 0px;
	background: #FAFCFC;
	position: absolute;
	width: 100%;
	height: 100%;
	hoverflow: scroll;
	overflow-y: hidden;
	overflow-x: hidden;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 100px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

.l-table-select {
	padding: 4px;
}

<!--
table {
	mso-displayed-decimal-separator: "\.";
	mso-displayed-thousand-separator: "\,";
}

@page {
	mso-header-data: &A;
	mso-footer-data: "Page &P";
	margin: .75in .7in .75in .7in;
	mso-header-margin: .3in;
	mso-footer-margin: .3in;
	mso-page-orientation: landscape;
}

ruby {
	ruby-align: left;
}

rt {
	color: windowtext;
	font-size: 9.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-char-type: none;
	display: none;
}
-->
</style>

</head>
<body style="padding: 10px">
	<form name="formsearch" method="post" id="form1">
		<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

		<table>
			<tr>
				<td align="right" style="width: 40px">日期：</td>
				<td><select name="SYear" class="l-table-select" align="left"
					style="width: 20px" id="SYear">
				</select></td>
				<td align="left" style="width: 20px">年</td>
				<td><select name="SMonth" class="l-table-select" align="left"
					style="width: 20px" id="SMonth">
				</select></td>
				<td align="left" style="width: 20px">月</td>
				<td align="right" class="l-table-edit-td" style="width: 20px"></td>
				<td align="right" class="l-table-edit-td" style="width: 100px"><a
					href="javascript:onSubmit()" class="l-button" style="width: 100px">查询</a></td>

				<td align="right" class="l-table-edit-td" style="width: 100px">
					<a href="javascript:onExport()" class="l-button"
					style="width: 100px">导出报表</a>
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
		<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

		<div id="maingrid"></div>

		<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

		<div id="edituser" style=""
			style="width:100%;height: 100px; display:none;">
			<div id="errorLabelContainer"></div>
			<table cellpadding="0" cellspacing="0" class="l-table-edit"
				border="0">
				<tr>
					<td align="left" class="l-table-edit-td">所属日期:</td>
					<td align="left" class="l-table-edit-td">报表时间:</td>
					
					<td align="left" class="l-table-edit-td">含油水2区调储罐进口:</td>
					<td align="left" class="l-table-edit-td">含油水2区调储罐出口:</td>
					<td align="left" class="l-table-edit-td">含油4#-3#:</td>
					<td align="left" class="l-table-edit-td">含油水2区气浮出口:</td>
					<td align="left" class="l-table-edit-td">含油水2区缓冲池:</td>
					<td align="left" class="l-table-edit-td">含油水2区一级出口:</td>
					<td align="left" class="l-table-edit-td">含油水2区二级出口:</td>

					<tr />
					<tr>
						<td align="left" class="l-table-edit-td"><input
							name="BBRQ" id="BBRQ" ltype="text" readonly="true" />
							<input name="RPD_U1_WATER_QUALITY2_ID" type="hidden" id="RPD_U1_WATER_QUALITY2_ID" />
						</td>
						<td align="left" class="l-table-edit-td"><input
							name="BBSJ" id="BBSJ" ltype="text" readonly="true" />
						</td>
						
						<td align="left" class="l-table-edit-td"><input name="HYS2TCGJK" id="HYS2TCGJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYS2TCGCK" id="HYS2TCGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYG4_3" id="HYG4_3" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYS2QFCK" id="HYS2QFCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYS2HCC" id="HYS2HCC" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYS2YJCK" id="HYS2YJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYS2EJCK" id="HYS2EJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td">悬浮水2区调储罐进口:</td>
						<td align="left" class="l-table-edit-td">悬浮水2区调储罐出口:</td>
						<td align="left" class="l-table-edit-td">悬浮4#-3#:</td>
						<td align="left" class="l-table-edit-td">悬浮水2区气浮出口:</td>
						<td align="left" class="l-table-edit-td">悬浮水2区缓冲池:</td>
						<td align="left" class="l-table-edit-td">悬浮水2区一级出口:</td>
						<td align="left" class="l-table-edit-td">悬浮水2区二级出口:</td>
						
						<td align="left" class="l-table-edit-td">硬度水2区调储罐出口:</td>
						<td align="left" class="l-table-edit-td">硬度水2区二级出口:</td>
					</tr>
					<tr>
						
					<td align="left" class="l-table-edit-td"><input name="XFS2TCGJK" id="XFS2TCGJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
					<td align="left" class="l-table-edit-td"><input name="XFS2TCGCK" id="XFS2TCGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
					<td align="left" class="l-table-edit-td"><input name="XFG4_3" id="XFG4_3" ltype="number" validate="{number:true,max:99999999.99}" /></td>
					<td align="left" class="l-table-edit-td"><input name="XFS2QFCK" id="XFS2QFCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
					<td align="left" class="l-table-edit-td"><input name="XFS2HCC" id="XFS2HCC" ltype="number" validate="{number:true,max:99999999.99}" /></td>
					<td align="left" class="l-table-edit-td"><input name="XFS2YJCK" id="XFS2YJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
					<td align="left" class="l-table-edit-td"><input name="XFS2EJCK" id="XFS2EJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
					<td align="left" class="l-table-edit-td"><input name="YDS2TCGCK" id="YDS2TCGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
					<td align="left" class="l-table-edit-td"><input name="YDS2EJCK" id="YDS2EJCK" ltype="XFWG2_1" validate="{number:true,max:99999999.99}" /></td>
																	

					</tr>
					<tr>
						<td align="left" class="l-table-edit-td">硬度水2区调储罐进口:</td>
						<td align="left" class="l-table-edit-td">硬度4#-3#:</td>
						<td align="left" class="l-table-edit-td">硬度水2区缓冲池:</td>
						<td align="left" class="l-table-edit-td">硬度水2区气浮:</td>
						<td align="left" class="l-table-edit-td">硬度水2区一级出口:</td>
						<td align="left" class="l-table-edit-td">备注:</td>
						

					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><input name="YDS2TCGJK" id="YDS2TCGJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YD4_3" id="YD4_3" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDS2HCC" id="YDS2HCC" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDS2QF" id="YDS2QF" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDS2YJCK" id="YDS2YJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="BZ" id="BZ" ltype="text" /></td>
												
					</tr>
			</table>
		</div>


	</form>

</body>
</html>
