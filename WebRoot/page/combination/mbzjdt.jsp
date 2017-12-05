<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>密闭转接站动态数据</title>
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
	var grid= null;
	var exportFlag = false;
        $(function () {
        	var scData = 
	            [{ id: 1, text: '全部' }, 
	            { id: 2, text: '50'},
	            { id: 3, text: '51'},
	            { id: 4, text: '52'}];
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'mbzjdt_pageInit.action',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
						//alert(data)
							grid = $("#maingrid").ligerGrid(data);					
							
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
        		
                $("#txtDate").ligerDateEditor(
                   {
                       format: "yyyy-MM-dd hh:mm",
                       labelWidth: 100,
                       showTime: true,
                       labelAlign: 'center',
                       cancelable : false
               });
                $("#txtDate1").ligerDateEditor(
                   {
                       format: "yyyy-MM-dd hh:mm",
                       labelWidth: 100,
                       showTime: true,
                       labelAlign: 'center',
                       cancelable : false
               });
                $("#well_site").ligerComboBox({
					data: scData,
	                valueFieldID: 'id',
	                initText :'全部',
	                selectBoxHeight:150,
					width: 120,
					hideOnLoseFocus:true,
					autocomplete:true
				});
                    $("#pageloading").hide();
        			$("#txtDate").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(myDate().Format("yyyy-MM-dd hh:mm"));
        			$("#txtDate1").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));
                    
			
        });
  
           function onSubmit()
        {
			//exportFlag = false;
		 
        	grid.setOptions({
        		parms: [{
					name: 'date',
					value: $("#txtDate").val()
				}
				,{
					name: 'date1',
					value: $("#txtDate1").val()
				},{
					name: 'name',
					value: $("#well_site").val()
				}
				]
        	});
         	grid.loadData(true);
        }
           function onExport() {
        	   var txtDate = $("#txtDate").val();
       		var txtDate1 = $("#txtDate1").val();
       		var name=$("#well_site").val();
       		if (exportFlag) {
       			oilstationname=oilstation;
       			areablock=areaname;
       			blockstationname=stationname;
       			gh=ghname;
       			rulewellname1=wellname;
       		}
       		var totalNum = 0;
       		var url = 'mbzjdt_serarchmbzjdt.action?name='+encodeURIComponent(name)+'&date='+encodeURIComponent(txtDate)+'&totalNum='+encodeURIComponent('export')+'&date1='+encodeURIComponent(txtDate1);
       		var urls = 'mbzjdt_serarchmbzjdt.action?name='+encodeURIComponent(name)+'&date='+encodeURIComponent(txtDate)+'&totalNum='+encodeURIComponent('totalNum')+'&date1='+encodeURIComponent(txtDate1);
       		$.ajax({
       			type : 'post',
       			url : urls,
       			async : false,
       			timeout : '30000',
       			success : function (data){
       				totalNum = data;
       			}
       		});
       		if (totalNum > 0) {
       			 $.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.open(url); });
       		}
       		else {
       			$.ligerDialog.confirm('没有可导出的数据！');
       		};
       		
       	};
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
					<td align="right" class="l-table-edit-td" style="width:60px">站号：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="well_site" name="well_site"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
					<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td>
				</tr>
			</table>
        </form>
    </div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
 
</body>
</html>