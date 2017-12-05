<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>水源井动态数据</title>
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
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'waterswrd_pageInit.action',
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
			
			var proData = [{ id: 1 , text: '' }];
			$("#combination1").ligerComboBox({
				url:'waterSoWell_queryCombinationInfo.action',
	            valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onSelected:function (data){
					if ($("#stationid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
						liger.get("wellnum1").setValue('');
						liger.get("watersowell1").setValue('');
						setWellData($("#stationid").val(),proData);
					}
					else {
						getWellInitData();
					}
				}
			});
			$("#wellnum1").ligerComboBox({
				url:'waterSoWell_queryWellInfo.action',
				valueField: 'id',
				valueFieldID: 'sowellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						liger.get("watersowell1").setValue('');
						setWaterWellData($("#sowellid").val(),proData);
					}
				}
			});
			$("#watersowell1").ligerComboBox({
				url:'waterSoWell_queryWaterSoWell.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
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
            $("#pageloading").hide();
			$("#txtDate").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(myDate().Format("yyyy-MM-dd hh:mm"));
			$("#txtDate1").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));
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
        function setWellData(stationid,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'waterSoWell_queryWellInfo.action',
    			data: {"stationid":stationid},
    			timeout : '3000',
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("wellnum1").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("wellnum1").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				alert("请求数据失败，请重新查询");
    			}
    		});
    	}

        function setWaterWellData(data,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'waterSoWell_queryWaterSoWell.action',
    			data: {"orgid":data},
    			timeout : '3000',
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("watersowell1").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("watersowell1").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				alert("请求数据失败，请重新查询");
    			}
    		});
    	}
        function getWellInitData() {
        	$("#watersowell1").ligerGetComboBoxManager().setValue('');
    		$("#wellnum1").ligerGetComboBoxManager().setValue('');
    		var combinationStationtext=[{ id: 1 , text: '' }];
    		var processingStationtext=[{ id: 1 , text: '' }];
    		var observationWelltext=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'waterSoWell_cascadeInit.action',
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'combinationStationtext') {
    						combinationStationtext = val;
    					}
    					if (key == 'processingStationtext'){
    						processingStationtext = val;
    					}
    					if (key == 'observationWelltext'){
    						observationWelltext = val;
    					}
    				});
    				setInitData(combinationStationtext,processingStationtext,observationWelltext);
    			}
    		});
    	}
    	
        function setInitData(combinationStationtext,processingStationtext,observationWelltext) {
    		liger.get("combination1").setData(combinationStationtext);
    		liger.get("wellnum1").setData(processingStationtext);
    		liger.get("watersowell1").setData(observationWelltext);
    	}
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			exportFlag = true;
			getWellInitData();
			$("#combination1").ligerGetComboBoxManager().setValue('');
			if(datatype=='5'){
				oilstationname=name;
				$("#combination1").val(name);
			}
			if(datatype=='11'){
				stationname=name;
				$("#wellnum1").val(name);
			}
			if(datatype=='9'){
				wellname=name;
				$("#watersowell1").val(name);
			}
	
			
			grid.setOptions({
        		parms: [{
					name: 'combination1',
					value: oilstationname
				},{
					name: 'wellnum1',
					value: stationname
				},{
					name: 'watersowell1',
					value: wellname
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
        
           function onSubmit()
        {
        
        
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
			exportFlag = false;
			var combination1=$("#combination1").val();
			var wellnum1=$("#wellnum1").val();
			var watersowell1=$("#watersowell1").val();
          
        	grid.setOptions({
        		parms: [{
					name: 'combination1',
					value: combination1
				},{
					name: 'wellnum1',
					value: wellnum1
				},{
					name: 'watersowell1',
					value: watersowell1
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
	function onExport() {
		var oilstationname=$("#combination1").val();
		var blockstationname=$("#wellnum1").val();
		var wellnum=$("#watersowell1").val();
		var txtDate = $("#txtDate").val();
		var txtDate1 = $("#txtDate1").val();
		var gh='';
		if (exportFlag) {
			oilstationname=oilstationname;
			blockstationname=stationname;
			wellnum=wellname;
		}
		var totalNum = 0;
		var url = 'waterswrd_searchInstru.action?oilstationname='+encodeURIComponent(oilstationname)+'&blockstationname='+encodeURIComponent(blockstationname)+'&wellnum='+encodeURIComponent(wellnum)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=export';
		var urls = 'waterswrd_searchInstru.action?oilstationname='+encodeURIComponent(oilstationname)+'&blockstationname='+encodeURIComponent(blockstationname)+'&wellnum='+encodeURIComponent(wellnum)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=totalNum';
		$.ajax({
			type : 'post',
			url : urls,
			async : false,
			timeout : '30000',
			success : function (data){
				totalNum = data;
			}
		});
		if (totalNum < 10000 && totalNum > 0) {
			 $.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url });
		}
		else if(totalNum > 10000){
			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url });
		}
		else {
			$.ligerDialog.confirm('没有可导出的数据！');
		}
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
					<td align="right" class="l-table-edit-td" style="width:50px">联合站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="combination1" name = "combination1" />
		                </td>
		                <td align="left">
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:50px">站号：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="wellnum1" name = "wellnum1"/>
		                </td>
		                <td align="left">
		                </td>

		                <td align="right" class="l-table-edit-td" style="width:50px">水源井：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="watersowell1" name = "watersowell1"/>
		                </td>
		                <td align="left">
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