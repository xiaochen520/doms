<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>一号稠油联合处理站水一区水质监测记录报表</title>

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
		var HYTCGJK = "";
		var HYCJG11GCS = "";
		var HYSAGDCS = "";
		var HYXLJK = "";
		var HYXLCK = "";
		var HYG2_1 = "";
		var HYTCGCK = "";
		var HYGYGCK = "";
		var HYXBGCK = "";
		var HYYJJK = "";
		var HYYKCK = "";
		var HYEJCK = "";
		var XFWTCGJK = "";
		var XFWCJGGCS = "";
		var XFWXLJK = "";
		var XFWXLCK1J = "";
		var XFWG2_1 = "";
		var XFWTCGCK = "";
		var XFWFYGCK = "";
		var XFWXBGCK = "";
		var XFWYJJK = "";
		var XFWYJCK = "";
		var XFWEJCK = "";
		var YDFYGCK = "";
		var YDEJCK = "";
		var RPD_U1_WATER_QUALITY1_ID ="";
		var YDTCGJK="";var YD2_1 = "";var YDTCGCK="";var YDXBGCK="";var YDYJJK="";var YDYJCK="";var GTCGJK="";var GFYQCK="";var GXBGCK="";var BZ="";
		
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
					url : 'u1q1szjc_pageInit.action',
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
		                 	var BBRQ1 = $("#BBRQ").val();
							var BBSJ1 = $("#BBSJ").val();
							var HYTCGJK1 = $("#HYTCGJK").val();

							var HYG2_11 = $("#HYG2_1").val();
							var HYTCGCK1 = $("#HYTCGCK").val();
							var HYGYGCK1 = $("#HYGYGCK").val();
							var HYXBGCK1 = $("#HYXBGCK").val();
							var HYYJJK1 = $("#HYYJJK").val();
							var HYYKCK1 = $("#HYYKCK").val();
							var HYEJCK1 = $("#HYEJCK").val();
							var XFWTCGJK1 = $("#XFWTCGJK").val();

							var XFWG2_11 = $("#XFWG2_1").val();
							var XFWTCGCK1 = $("#XFWTCGCK").val();
							var XFWFYGCK1 = $("#XFWFYGCK").val();
							var XFWXBGCK1 = $("#XFWXBGCK").val();
							var XFWYJJK1 = $("#XFWYJJK").val();
							var XFWYJCK1 = $("#XFWYJCK").val();
							var XFWEJCK1 = $("#XFWEJCK").val();
							var YDFYGCK1 = $("#YDFYGCK").val();
							var YDEJCK1 = $("#YDEJCK").val();
							var RPD_U1_WATER_QUALITY1_ID1 = $("#RPD_U1_WATER_QUALITY1_ID").val();	  
							var YDTCGJK1=$("#YDTCGJK").val();
							var YD2_11 = $("#YD2_1").val();
							var YDTCGCK1=$("#YDTCGCK").val();
							var YDXBGCK1=$("#YDXBGCK").val();
							var YDYJJK1=$("#YDYJJK").val();
							var YDYJCK1=$("#YDYJCK").val();
							var GTCGJK1=$("#GTCGJK").val();
							var GFYQCK1=$("#GFYQCK").val();
							var GXBGCK1=$("#GXBGCK").val();
							var BZ1=$("#BZ").val();
							
		              	   //判断修改还是添加操作 1-添加、2-修改
		              	   if(operate == 1){
		              	   
		              	   //2-修改
		              	   }else if(operate == 2){
		              	   		jQuery.ajax({
										type : 'post',
										url : 'u1q1szjc_updateU2szjc.action',
										data: {
											//"BBRQ":BBRQ1,
											//"BBSJ":BBSJ1,
											"HYTCGJK":HYTCGJK1,

											"HYG2_1":HYG2_11,
											"HYTCGCK":HYTCGCK1,
											"HYGYGCK":HYGYGCK1,
											"HYXBGCK":HYXBGCK1,
											"HYYJJK":HYYJJK1,
											"HYYKCK":HYYKCK1,
											"HYEJCK":HYEJCK1,
											"XFWTCGJK":XFWTCGJK1,

											"XFWG2_1":XFWG2_11,
											"XFWTCGCK":XFWTCGCK1,
											"XFWFYGCK":XFWFYGCK1,
											"XFWXBGCK":XFWXBGCK1,
											"XFWYJJK":XFWYJJK1,
											"XFWYJCK":XFWYJCK1,
											"XFWEJCK":XFWEJCK1,
											"YDFYGCK":YDFYGCK1,
											"YDEJCK":YDEJCK1,
											"RPD_U1_WATER_QUALITY1_ID":RPD_U1_WATER_QUALITY1_ID1,"YDTCGJK":YDTCGJK1, "YD2_1":YD2_11,"YDTCGCK": YDTCGCK1, 
											"YDXBGCK":YDXBGCK1 ,"YDYJJK":YDYJJK1, "YDYJCK":YDYJCK1, "GTCGJK":GTCGJK1 ,"GFYQCK":GFYQCK1 ,"GXBGCK":GXBGCK1,"BZ": BZ1
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
				if(rowdata.RPD_U1_WATER_QUALITY1_ID != "空行"){
					d  = new Date();
					datestr = d.Format("yyyy-MM-dd");
					if(rowdata.BBRQ == datestr || rowdata.BBRQ == getTomorrow()) {
						//selectrowdata = rowdata;
			        	//用户选择修改数据
			        	BBRQ = getRemoveData(rowdata.BBRQ);
						BBSJ = getRemoveData(rowdata.BBSJ);
						HYTCGJK = getRemoveData(rowdata.HYTCGJK);

						HYG2_1 = getRemoveData(rowdata.HYG2_1);
						HYTCGCK = getRemoveData(rowdata.HYTCGCK);
						HYGYGCK = getRemoveData(rowdata.HYGYGCK);
						HYXBGCK = getRemoveData(rowdata.HYXBGCK);
						HYYJJK = getRemoveData(rowdata.HYYJJK);
						HYYKCK = getRemoveData(rowdata.HYYKCK);
						HYEJCK = getRemoveData(rowdata.HYEJCK);
						XFWTCGJK = getRemoveData(rowdata.XFWTCGJK);

						XFWG2_1 = getRemoveData(rowdata.XFWG2_1);
						XFWTCGCK = getRemoveData(rowdata.XFWTCGCK);
						XFWFYGCK = getRemoveData(rowdata.XFWFYGCK);
						XFWXBGCK = getRemoveData(rowdata.XFWXBGCK);
						XFWYJJK = getRemoveData(rowdata.XFWYJJK);
						XFWYJCK = getRemoveData(rowdata.XFWYJCK);
						XFWEJCK = getRemoveData(rowdata.XFWEJCK);
						YDFYGCK = getRemoveData(rowdata.YDFYGCK);
						YDEJCK = getRemoveData(rowdata.YDEJCK);
						RPD_U1_WATER_QUALITY1_ID = getRemoveData(rowdata.RPD_U1_WATER_QUALITY1_ID);
						YDTCGJK = getRemoveData(rowdata.YDTCGJK);
						YD2_1 = getRemoveData(rowdata.YD2_1);
						YDTCGCK = getRemoveData(rowdata.YDTCGCK);
						YDXBGCK = getRemoveData(rowdata.YDXBGCK);
						YDYJJK = getRemoveData(rowdata.YDYJJK);
						YDYJCK = getRemoveData(rowdata.YDYJCK);
						GTCGJK = getRemoveData(rowdata.GTCGJK);
						GFYQCK = getRemoveData(rowdata.GFYQCK);
						GXBGCK = getRemoveData(rowdata.GXBGCK);
						BZ = getRemoveData(rowdata.BZ);
						
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
					document.getElementById("HYTCGJK").value = HYTCGJK;

					document.getElementById("HYG2_1").value = HYG2_1;
					document.getElementById("HYTCGCK").value = HYTCGCK;
					document.getElementById("HYGYGCK").value = HYGYGCK;
					document.getElementById("HYXBGCK").value = HYXBGCK;
					document.getElementById("HYYJJK").value = HYYJJK;
					document.getElementById("HYYKCK").value = HYYKCK;
					document.getElementById("HYEJCK").value = HYEJCK;
					document.getElementById("XFWTCGJK").value = XFWTCGJK;

					document.getElementById("XFWG2_1").value = XFWG2_1;
					document.getElementById("XFWTCGCK").value = XFWTCGCK;
					document.getElementById("XFWFYGCK").value = XFWFYGCK;
					document.getElementById("XFWXBGCK").value = XFWXBGCK;
					document.getElementById("XFWYJJK").value = XFWYJJK;
					document.getElementById("XFWYJCK").value = XFWYJCK;
					document.getElementById("XFWEJCK").value = XFWEJCK;
					document.getElementById("YDFYGCK").value = YDFYGCK;
					document.getElementById("YDEJCK").value = YDEJCK;
					document.getElementById("RPD_U1_WATER_QUALITY1_ID").value = RPD_U1_WATER_QUALITY1_ID;	
					document.getElementById("YDTCGJK").value = YDTCGJK ;
					document.getElementById("YD2_1").value = YD2_1 ;
					document.getElementById("YDTCGCK").value = YDTCGCK ;
					document.getElementById("YDXBGCK").value = YDXBGCK ;
					document.getElementById("YDYJJK").value = YDYJJK ;
					document.getElementById("YDYJCK").value = YDYJCK;
					document.getElementById("GTCGJK").value = GTCGJK;
					document.getElementById("GFYQCK").value = GFYQCK;
					document.getElementById("GXBGCK").value = GXBGCK;
					document.getElementById("BZ").value = BZ;
				 }else if(flg == 1){
				 	document.getElementById("BBRQ").value = "";
					document.getElementById("BBSJ").value = "";
					document.getElementById("HYTCGJK").value = "";

					document.getElementById("HYG2_1").value = "";
					document.getElementById("HYTCGCK").value = "";
					document.getElementById("HYGYGCK").value = "";
					document.getElementById("HYXBGCK").value = "";
					document.getElementById("HYYJJK").value = "";
					document.getElementById("HYYKCK").value = "";
					document.getElementById("HYEJCK").value = "";
					document.getElementById("XFWTCGJK").value = "";

					document.getElementById("XFWG2_1").value = "";
					document.getElementById("XFWTCGCK").value = "";
					document.getElementById("XFWFYGCK").value = "";
					document.getElementById("XFWXBGCK").value = "";
					document.getElementById("XFWYJJK").value = "";
					document.getElementById("XFWYJCK").value = "";
					document.getElementById("XFWEJCK").value = "";
					document.getElementById("YDFYGCK").value = "";
					document.getElementById("YDEJCK").value = "";
					document.getElementById("RPD_U1_WATER_QUALITY1_ID").value ="";
					document.getElementById("YDTCGJK").value = "" ;
					document.getElementById("YD2_1").value = "" ;
					document.getElementById("YDTCGCK").value = "" ;
					document.getElementById("YDXBGCK").value = "" ;
					document.getElementById("YDYJJK").value = "" ;
					document.getElementById("YDYJCK").value = "";
					document.getElementById("GTCGJK").value = "";
					document.getElementById("GFYQCK").value = "";
					document.getElementById("GXBGCK").value = "";
					document.getElementById("BZ").value = "";
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
		var url = "u1q1szjc_exportU2_SZJC.action?SYear="+encodeURIComponent(SYear) +"&SMonth="+encodeURIComponent(SMonth)+ "&reportName="+encodeURIComponent('二号稠油联合处理站-水质监测报表');
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
                	
						if(RPD_U1_WATER_QUALITY1_ID != null && RPD_U1_WATER_QUALITY1_ID !=""  && typeof(RPD_U1_WATER_QUALITY1_ID)!="undefined" && RPD_U1_WATER_QUALITY1_ID != "空行"){	
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
					<td align="left" class="l-table-edit-td">含油调储罐进口:</td>
					<!--  
					<td align="left" class="l-table-edit-td">含油11#沉降罐高出水:</td>
					<td align="left" class="l-table-edit-td">含油SAGD出水 :</td>
					<td align="left" class="l-table-edit-td">含油旋流进口:</td>
					<td align="left" class="l-table-edit-td">含油旋流出口:</td>
					-->
					<td align="left" class="l-table-edit-td">含油2#到1#罐:</td>
					<td align="left" class="l-table-edit-td">含油调储罐:</td>
					<td align="left" class="l-table-edit-td">含油反应罐出口:</td>

					<tr />
					<tr>
						<td align="left" class="l-table-edit-td"><input
							name="BBRQ" id="BBRQ" ltype="text" readonly="true" />
							<input name="RPD_U1_WATER_QUALITY1_ID" type="hidden" id="RPD_U1_WATER_QUALITY1_ID" />
						</td>
						<td align="left" class="l-table-edit-td"><input
							name="BBSJ" id="BBSJ" ltype="text" readonly="true" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="HYTCGJK"
							id="HYTCGJK" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
							<!--  
						<td align="left" class="l-table-edit-td"><input name="HYCJG11GCS"
							id="HYCJG11GCS" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						<td align="left" class="l-table-edit-td"><input name="HYSAGDCS"
							id="HYSAGDCS" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYXLJK"
							id="HYXLJK" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						
						<td align="left" class="l-table-edit-td"><input name="HYXLCK"
							id="HYXLCK" ltype="number" validate="{number:true,max:99999999.99}" />
						</td>
						-->
						<td align="left" class="l-table-edit-td"><input name="HYG2_1"
							id="HYG2_1" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYTCGCK"
							id="HYTCGCK" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYGYGCK"
							id="HYGYGCK" ltype="number"
							validate="{number:true,max:99999999.99}" /></td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td">含油斜板罐出口:</td>
						<td align="left" class="l-table-edit-td">含油一级进口:</td>
						<td align="left" class="l-table-edit-td">含油一级出口:</td>
						<td align="left" class="l-table-edit-td">含油二级出口:</td>
						<td align="left" class="l-table-edit-td">悬浮调储罐进口:</td>
						<!-- 
						<td align="left" class="l-table-edit-td">悬浮沉降罐高出水:</td>
						<td align="left" class="l-table-edit-td">悬浮旋流进口:</td>
						<td align="left" class="l-table-edit-td">悬浮旋流出口:</td>
						-->
						<td align="left" class="l-table-edit-td">悬浮2#到1#罐:</td>
						<td align="left" class="l-table-edit-td">悬浮调储罐出口:</td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><input name="HYXBGCK" id="HYXBGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYYJJK" id="HYYJJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYYKCK" id="HYYKCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYEJCK" id="HYEJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFWTCGJK" id="XFWTCGJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<!--
						<td align="left" class="l-table-edit-td"><input name="XFWCJGGCS" id="XFWCJGGCS" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFWXLJK" id="XFWXLJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFWXLCK1J" id="XFWXLCK1J" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						-->
						<td align="left" class="l-table-edit-td"><input name="HYEJC" id="XFWG2_1" ltype="XFWG2_1" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="HYEJC" id="XFWTCGCK" ltype="XFWTCGCK" validate="{number:true,max:99999999.99}" /></td>
												

					</tr>


					<tr>
						<td align="left" class="l-table-edit-td">悬浮反应罐出口:</td>
						<td align="left" class="l-table-edit-td">悬浮斜板罐出口:</td>
						<td align="left" class="l-table-edit-td">悬浮一级进口:</td>
						<td align="left" class="l-table-edit-td">悬浮一级出口:</td>
						<td align="left" class="l-table-edit-td">悬浮二级出口:</td>
						<td align="left" class="l-table-edit-td">硬度反应罐出口:</td>
						<td align="left" class="l-table-edit-td">硬度二级出口:</td>
						<td align="left" class="l-table-edit-td">硬度调储罐进口:</td>
						<td align="left" class="l-table-edit-td">硬度2#到1#罐:</td>
						<td align="left" class="l-table-edit-td">硬度调储罐出口:</td>
						

					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><input name="XFWFYGCK" id="XFWFYGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFWXBGCK" id="XFWXBGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFWYJJK" id="XFWYJJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFWYJCK" id="XFWYJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="XFWEJCK" id="XFWEJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDFYGCK" id="YDFYGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDEJCK" id="YDEJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDTCGJK" id="YDTCGJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YD2_1" id="YD2_1" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDTCGCK" id="YDTCGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
												
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td">硬度斜板罐出口:</td>
						<td align="left" class="l-table-edit-td">硬度一级进口:</td>
						<td align="left" class="l-table-edit-td">硬度一级出口:</td>
						<td align="left" class="l-table-edit-td">二氧化硅调储罐进口:</td>
						<td align="left" class="l-table-edit-td">二氧化硅反应器出口:</td>
						<td align="left" class="l-table-edit-td">二氧化硅斜板罐出口:</td>
						<td align="left" class="l-table-edit-td">备注:</td>
						

					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><input name="YDXBGCK" id="YDXBGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDYJJK" id="YDYJJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="YDYJCK" id="YDYJCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="GTCGJK" id="GTCGJK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="GFYQCK" id="GFYQCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="GXBGCK" id="GXBGCK" ltype="number" validate="{number:true,max:99999999.99}" /></td>
						<td align="left" class="l-table-edit-td"><input name="BZ" id="BZ" ltype="text" /></td>
												
					</tr>
			</table>
		</div>


	</form>

</body>
</html>
