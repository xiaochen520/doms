﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>脱氧塔动态数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
   <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>    -->
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
	var eee;
	var oilstationname='';
	var stationname='';
	var wellname='';
	var exportFlag = false;
        $(function () {
        
        
        	  var clzData = 
            [{ id: 1, text: '集中' }
            ];
            var sbbhData =
            [
            { id: 1, text: '1', pid: 1 },
            { id: 2, text: '2', pid: 1 },
            { id: 3, text: '3', pid: 1 }
            ];  
            
              $("#sbbh").ligerComboBox({ data: null,isMultiSelect: false });
              $("#sclz").ligerComboBox({
                data: clzData, isMultiSelect: false,selectBoxWidth:150,
                onSelected: function (newvalue)
                {
                    var newData = new Array();
                    for (i = 0; i < sbbhData.length; i++)
                    {
                        if (sbbhData[i].pid == newvalue)
                        {
                            newData.push(sbbhData[i]);
                        }
                    }
                    liger.get("sbbh").setData(newData);
                }
            });
            
            
			// $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
             $("#txtDate").ligerDateEditor(
                {

                    format: "yyyy-MM-dd hh:mm",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
            });
             $("#txtDate1").ligerDateEditor(
                {

                    format: "yyyy-MM-dd hh:mm",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    showTime: true,
                    labelAlign: 'center',
                    cancelable : false
            });
            
            $("#txtDate").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(myDate().Format("yyyy-MM-dd hh:mm"));
			$("#txtDate1").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));
			
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'jzsclrd_pageInit.action?arg=TYT',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(data);					
							
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
			
			
            $("#pageloading").hide();
			
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
        });  
       
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			
		}
        
           function onSubmit()
        {
			exportFlag = false;
			var sclz=$("#sclz").val();
			var sbbh=$("#sbbh").val();
           var start = $("#txtDate").val();
	var end = $("#txtDate1").val();
	if(typeof(start) == "undefined" || start == "" ){
		$.ligerDialog.question("开始时间不能为空");
		return;
	}
	if(typeof(end) == "undefined" || end == "" ){
		$.ligerDialog.question("结束时间不能为空");
		return;
	}
	if(new Date(start.replace(/\-/g, "\/")) > new Date(end.replace(/\-/g, "\/"))) {
		$.ligerDialog.question("结束时间必须大于开始时间！");
		return;
	}
        	grid.setOptions({
        		parms: [{
					name: 'sclz',
					value: sclz
				},{
					name: 'sbbh',
					value: sbbh
				},{
					name: 'date',
					value: $("#txtDate").val()
				}
				,{
					name: 'date1',
					value: $("#txtDate1").val()
				}
				]
        	});
         	grid.loadData(true);
        }
	
    </script>
    <style type="text/css"> 
    html,body
	{
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
    </style>
</head>
<body style="overflow-x:hidden; padding:2px;">
 
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form"> 
        	<table border="0" cellspacing="1">
				<tr>
					<td><input type="text" id="sclz" ltype="sclz"/></td>
						 <td align="left" class="l-table-edit-td" style="width:80px">水处理站</td>
						 <td><input type="text" id="sbbh" ltype="sbbh"/></td>
						 <td align="left" class="l-table-edit-td" style="width:80px">号脱氧塔</td>
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>
		                <td align="left">
		                </td>
	                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
					<!-- <td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td> -->
				</tr>
			</table>
        </form>
    </div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
 
</body>
</html>