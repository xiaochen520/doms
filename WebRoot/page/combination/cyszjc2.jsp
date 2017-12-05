<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>稠油水质监测报表</title>

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
      var textValue ="";
      var namedisplay ="";
      var columnname ="";
      var pwaterparam = "";
      var d ;  //日期
      var datestr = "";   //当前系统时间
      
      var authoruty = 0;  //0 无需该权限， 1 有修改权限
      var exportFlag = false;
       var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
       
       var selectrowdata ;
       var newrowdata ;
       
      	var REPORT_DATE;
		var REPORT_TIME;
		var HYCJC;
		var HYDJ;
		var HY1_2;
		var HYDC;
		var HYFC;
		var HYHNC;
		var HYYJJ;
		var HYYJC;
		var HYEJC;
		var HYRHS;
		var XFCJC;
		var XFDJ;
		var XF1_2;
		var XFDC;
		var XFFC;
		var XFHNC;
		var XFYJJ;
		var XFYJC;
		var XFEJC;
		var XFRHS;
		var YDCJC;
		var YDDJ;
		var YDDC;
		var YDFC;
		var YDHNC;
		var YDYJJ;
		var HNYJC;
		var HNEJC;
		var RPD_U2_WATER_QUALITY_ID;
		
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
					url : 'u2szjc_pageInit.action',
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
		                 	var REPORT_DATE1 = $("#REPORT_DATE").val();
							var REPORT_TIME1 = $("#REPORT_TIME").val();
							var HYCJC1 = $("#HYCJC").val();
							var HYDJ1 = $("#HYDJ").val();
							var HY1_21 = $("#HY1_2").val();
							var HYDC1 = $("#HYDC").val();
							var HYFC1 = $("#HYFC").val();
							var HYHNC1 = $("#HYHNC").val();
							var HYYJJ1 = $("#HYYJJ").val();
							var HYYJC1 = $("#HYYJC").val();
							
							var HYEJC1 = $("#HYEJC").val();
							var HYRHS1 = $("#HYRHS").val();
							var XFCJC1 = $("#XFCJC").val();
							var XFDJ1 = $("#XFDJ").val();
							var XF1_21 = $("#XF1_2").val();
							var XFDC1 = $("#XFDC").val();
							var XFFC1 = $("#XFFC").val();
							var XFHNC1 = $("#XFHNC").val();
							var XFYJJ1 = $("#XFYJJ").val();
							var XFYJC1 = $("#XFYJC").val();
							
							var XFEJC1 = $("#XFEJC").val();
							var XFRHS1 = $("#XFRHS").val();
							var YDCJC1 = $("#YDCJC").val();
							
							var YDDJ1 = $("#YDDJ").val();
							var YDDC1 = $("#YDDC").val();
							var YDFC1 = $("#YDFC").val();
							
							var YDHNC1 = $("#YDHNC").val();
							var YDYJJ1 = $("#YDYJJ").val();
							var HNYJC1 = $("#HNYJC").val();
							var HNEJC1 = $("#HNEJC").val();
							
							var RPD_U2_WATER_QUALITY_ID1 = $("#RPD_U2_WATER_QUALITY_ID").val();		              	  
		              	   //判断修改还是添加操作 1-添加、2-修改
		              	   if(operate == 1){
		              	   
		              	   //2-修改
		              	   }else if(operate == 2){
		              	   		jQuery.ajax({
										type : 'post',
										url : 'u2szjc_updateU2szjc.action',
										data: {
											"REPORT_DATE":REPORT_DATE1,
											"REPORT_TIME":REPORT_TIME1,
											"HYCJC":HYCJC1,
											"HYDJ":HYDJ1,
											"HY1_2":HY1_21,
											"HYDC":HYDC1,
											"HYFC":HYFC1,
											"HYHNC":HYHNC1,
											"HYYJJ":HYYJJ1,
											"HYYJC":HYYJC1,
											
											"HYEJC":HYEJC1,
											"HYRHS":HYRHS1,
											"XFCJC":XFCJC1,
											"XFDJ":XFDJ1,
											"XF1_2":XF1_21,
											"XFDC":XFDC1,
											"XFFC":XFFC1,
											"XFHNC":XFHNC1,
											"XFYJJ":XFYJJ1,
											"XFYJC":XFYJC1,
											
											"XFEJC":XFEJC1,
											"XFRHS":XFRHS1,
											"YDCJC":YDCJC1,
											"YDDJ":YDDJ1,
											"YDDC":YDDC1,
											"YDFC":YDFC1,
											"YDHNC":YDHNC1,
											"YDYJJ":YDYJJ1,
											"HNYJC":HNYJC1,
											"HNEJC":HNEJC1,
											
											"RPD_U2_WATER_QUALITY_ID":RPD_U2_WATER_QUALITY_ID1
										
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
		           // $(".l-button-test").click(function () {
		           // });
           
           
        });
        
      
         function fromAu(rowdata){
         	//alert(JSON2.stringify(rowdata));
         	
         	columnname = "";
			textValue = "";
			namedisplay ="";
			//判断用户 是否拥有修改权限
			if(authoruty  == 1){
				if(rowdata.RPD_U2_WATER_QUALITY_ID != "空行"){
					d  = new Date();
					datestr = d.Format("yyyy-MM-dd");
					if(rowdata.REPORT_DATE == datestr || rowdata.REPORT_DATE == getTomorrow()) {
						selectrowdata = rowdata;
			        	//用户选择修改数据
			        	REPORT_DATE = getRemoveData(rowdata.REPORT_DATE);
						REPORT_TIME = getRemoveData(rowdata.REPORT_TIME);
						HYCJC = getRemoveData(rowdata.HYCJC);
						HYDJ = getRemoveData(rowdata.HYDJ);
						HY1_2 = getRemoveData(rowdata.HY1_2);
						HYDC = getRemoveData(rowdata.HYDC);
						HYFC = getRemoveData(rowdata.HYFC);
						HYHNC = getRemoveData(rowdata.HYHNC);
						HYYJJ = getRemoveData(rowdata.HYYJJ);
						HYYJC = getRemoveData(rowdata.HYYJC);
						
						HYEJC = getRemoveData(rowdata.HYEJC);
						HYRHS = getRemoveData(rowdata.HYRHS);
						XFCJC = getRemoveData(rowdata.XFCJC);
						XFDJ = getRemoveData(rowdata.XFDJ);
						XF1_2 = getRemoveData(rowdata.XF1_2);
						XFDC = getRemoveData(rowdata.XFDC);
						XFFC = getRemoveData(rowdata.XFFC);
						XFHNC = getRemoveData(rowdata.XFHNC);
						XFYJJ = getRemoveData(rowdata.XFYJJ);
						XFYJC = getRemoveData(rowdata.XFYJC);
						
						XFEJC = getRemoveData(rowdata.XFEJC);
						XFRHS = getRemoveData(rowdata.XFRHS);
						YDCJC = getRemoveData(rowdata.YDCJC);
						YDDJ = getRemoveData(rowdata.YDDJ);
						YDDC = getRemoveData(rowdata.YDDC);
						YDFC = getRemoveData(rowdata.YDFC);
						YDHNC = getRemoveData(rowdata.YDHNC);
						YDYJJ = getRemoveData(rowdata.YDYJJ);
						HNYJC = getRemoveData(rowdata.HNYJC);
						HNEJC = getRemoveData(rowdata.HNEJC);
						
						RPD_U2_WATER_QUALITY_ID = getRemoveData(rowdata.RPD_U2_WATER_QUALITY_ID);
						
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
				 	document.getElementById("REPORT_DATE").value = REPORT_DATE;
					document.getElementById("REPORT_TIME").value = REPORT_TIME;
					document.getElementById("HYCJC").value = HYCJC;
					document.getElementById("HYDJ").value = HYDJ;
					document.getElementById("HY1_2").value = HY1_2;
					document.getElementById("HYDC").value = HYDC;
					document.getElementById("HYFC").value = HYFC;
					document.getElementById("HYHNC").value = HYHNC;
					document.getElementById("HYYJJ").value = HYYJJ;
					document.getElementById("HYYJC").value = HYYJC;
					
					document.getElementById("HYEJC").value = HYEJC;
					document.getElementById("HYRHS").value = HYRHS;
					document.getElementById("XFCJC").value = XFCJC;
					document.getElementById("XFDJ").value = XFDJ;
					document.getElementById("XF1_2").value = XF1_2;
					document.getElementById("XFDC").value = XFDC;
					document.getElementById("XFFC").value = XFFC;
					document.getElementById("XFHNC").value = XFHNC;
					document.getElementById("XFYJJ").value = XFYJJ;
					document.getElementById("XFYJC").value = XFYJC;
					
					document.getElementById("XFEJC").value = XFEJC;
					document.getElementById("XFRHS").value = XFRHS;
					document.getElementById("YDCJC").value = YDCJC;
					document.getElementById("YDDJ").value = YDDJ;
					document.getElementById("YDDC").value = YDDC;
					document.getElementById("YDFC").value = YDFC;
					document.getElementById("YDHNC").value = YDHNC;
					document.getElementById("YDYJJ").value = YDYJJ;
					document.getElementById("HNYJC").value = HNYJC;
					document.getElementById("HNEJC").value = HNEJC;
					
					document.getElementById("RPD_U2_WATER_QUALITY_ID").value = RPD_U2_WATER_QUALITY_ID;		
				 }else if(flg == 1){
				 	document.getElementById("REPORT_DATE").value = "";
					document.getElementById("REPORT_TIME").value = "";
					document.getElementById("HYCJC").value = "";
					document.getElementById("HYDJ").value = "";
					document.getElementById("HY1_2").value = "";
					document.getElementById("HYDC").value = "";
					document.getElementById("HYFC").value = "";
					document.getElementById("HYHNC").value = "";
					document.getElementById("HYYJJ").value = "";
					document.getElementById("HYYJC").value = "";
					
					document.getElementById("HYEJC").value = "";
					document.getElementById("HYRHS").value = "";
					document.getElementById("XFCJC").value = "";
					document.getElementById("XFDJ").value = "";
					document.getElementById("XF1_2").value = "";
					document.getElementById("XFDC").value = "";
					document.getElementById("XFFC").value = "";
					document.getElementById("XFHNC").value = "";
					document.getElementById("XFYJJ").value = "";
					document.getElementById("XFYJC").value = "";
					
					document.getElementById("XFEJC").value = "";
					document.getElementById("XFRHS").value = "";
					document.getElementById("YDCJC").value = "";
					document.getElementById("YDDJ").value = "";
					document.getElementById("YDDC").value = "";
					document.getElementById("YDFC").value = "";
					document.getElementById("YDHNC").value = "";
					document.getElementById("YDYJJ").value = "";
					document.getElementById("HNYJC").value = "";
					document.getElementById("HNEJC").value = "";
					
					document.getElementById("RPD_U2_WATER_QUALITY_ID").value = "";
				 }	
			 }
     function onSubmit()
        {
        	exportFlag = false;
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
		var url = "u2szjc_exportU2_SZJC.action?SYear="+encodeURIComponent(SYear) +"&SMonth="+encodeURIComponent(SMonth)+ "&reportName="+encodeURIComponent('二号稠油联合处理站-水质监测报表');
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
                	
						if(RPD_U2_WATER_QUALITY_ID != null && RPD_U2_WATER_QUALITY_ID !=""  && typeof(RPD_U2_WATER_QUALITY_ID)!="undefined" && RPD_U2_WATER_QUALITY_ID != "空行"){	
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
					<td align="left" class="l-table-edit-td">含油沉降出:</td>
					<td align="left" class="l-table-edit-td">含油调进:</td>
					<td align="left" class="l-table-edit-td">含油1#-2#:</td>
					<td align="left" class="l-table-edit-td">含油调出:</td>
					<td align="left" class="l-table-edit-td">含油反出:</td>
					<td align="left" class="l-table-edit-td">含油混凝出:</td>
					<td align="left" class="l-table-edit-td">含油一级进:</td>
					<td align="left" class="l-table-edit-td">含油一级出:</td>

					<tr />
					<tr>
						<td align="left" class="l-table-edit-td"><input
							name="REPORT_DATE" id="REPORT_DATE" ltype="text" readonly="true" />
							<input name="RPD_U2_WATER_QUALITY_ID" type="hidden"
							id="RPD_U2_WATER_QUALITY_ID" /></td>
						<td align="left" class="l-table-edit-td"><input
							name="REPORT_TIME" id="REPORT_TIME" ltype="text" readonly="true" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="HYCJC"
							id="HYCJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYDJ"
							id="HYDJ" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="HY1_2"
							id="HY1_2" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYDC"
							id="HYDC" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="HYFC"
							id="HYFC" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="HYHNC"
							id="HYHNC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYYJJ"
							id="HYYJJ" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYYJC"
							id="HYYJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td">含油二级出:</td>
						<td align="left" class="l-table-edit-td">含油软化水:</td>
						<td align="left" class="l-table-edit-td">悬浮沉降出:</td>
						<td align="left" class="l-table-edit-td">悬浮调进:</td>
						<td align="left" class="l-table-edit-td">悬浮1#-2#:</td>
						<td align="left" class="l-table-edit-td">悬浮调出:</td>
						<td align="left" class="l-table-edit-td">悬浮反出:</td>
						<td align="left" class="l-table-edit-td">悬浮混凝出:</td>
						<td align="left" class="l-table-edit-td">悬浮一级进:</td>
						<td align="left" class="l-table-edit-td">悬浮一级出:</td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><input name="HYEJC"
							id="HYEJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYRHS"
							id="HYRHS" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFCJC"
							id="XFCJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>

						<td align="left" class="l-table-edit-td"><input name="XFDJ"
							id="XFDJ" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="XF1_2"
							id="XF1_2" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFDC"
							id="XFDC" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>

						<td align="left" class="l-table-edit-td"><input name="XFFC"
							id="XFFC" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="XFHNC"
							id="XFHNC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFYJJ"
							id="XFYJJ" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFYJC"
							id="XFYJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>

					</tr>


					<tr>
						<td align="left" class="l-table-edit-td">悬浮二级出:</td>
						<td align="left" class="l-table-edit-td">悬浮软化水:</td>
						<td align="left" class="l-table-edit-td">硬度沉降出:</td>
						<td align="left" class="l-table-edit-td">硬度调进:</td>
						<td align="left" class="l-table-edit-td">硬度调出:</td>
						<td align="left" class="l-table-edit-td">硬度反出:</td>
						<td align="left" class="l-table-edit-td">硬度混凝出:</td>
						<td align="left" class="l-table-edit-td">硬度一级进:</td>
						<td align="left" class="l-table-edit-td">硬度一级进:</td>
						<td align="left" class="l-table-edit-td">硬度二级出:</td>

					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><input name="XFEJC"
							id="XFEJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFRHS"
							id="XFRHS" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDCJC"
							id="YDCJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>

						<td align="left" class="l-table-edit-td"><input name="YDDJ"
							id="YDDJ" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="YDDC"
							id="YDDC" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="YDFC"
							id="YDFC" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>

						<td align="left" class="l-table-edit-td"><input name="YDHNC"
							id="YDHNC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDYJJ"
							id="YDYJJ" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HNYJC"
							id="HNYJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HNEJC"
							id="HNEJC" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
					</tr>
			</table>
		</div>


	</form>

</body>
</html>
