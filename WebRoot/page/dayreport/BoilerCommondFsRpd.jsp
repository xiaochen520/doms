<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>湿蒸汽锅炉分时日报数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
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
   var grid;
        $(function () {
        	//获取报表及功能按钮ｊｓ
			jQuery.ajax({
				type : 'post',
				url : 'szglfs_pageInit.action',
				success : function(jsondata) { 
				if (jsondata != null && typeof(jsondata)!="undefined"){
					var data = eval('(' + jsondata + ')');
						grid = $("#maingrid").ligerGrid(data);	
						toolbar = grid.topbar.ligerGetToolBarManager();				
						
					}else{
						$.ligerDialog.error(outerror(jsondata));
					}
				},
				error : function(data) {
				
				}
			});
			
			var proData = [{ id: 1 , text: '' }];
			$("#oilationname").ligerComboBox({
				//url:'rulewell_queryOilSationInfo.action?arg=boiler',
				//url:'srglrd_queryoilding.action',
				url:'station_queryAreablockInfo.action?orgid=sqdw',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect: function (newvalue){
					//liger.get("blockstationname").setValue('');
					//liger.get("boilersName").setValue('');
					//liger.get("areablock").setValue('');
				},
				onSelected:function (data){
					/*clearSelecteValue=1;
					if ($("#oilationid").val() !='') {
						//setAreablockData($("#oilationid").val(),proData);
						setStationData( $("#oilationid").val(), proData);
					}
					else {
						getBoilerInitData();
					}*/
				}
			});
			$("#oilNmame").ligerComboBox({
				//url:'rulewell_queryOilSationInfo.action?arg=boiler',
				url:'srglrd_queryoilding.action',
				//url:'station_queryAreablockInfo.action?orgid=sqdw',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect: function (newvalue){
					//liger.get("blockstationname").setValue('');
					//liger.get("boilersName").setValue('');
					//liger.get("areablock").setValue('');
				},
				onSelected:function (data){
					/*clearSelecteValue=1;
					if ($("#oilationid").val() !='') {
						//setAreablockData($("#oilationid").val(),proData);
						setStationData( $("#oilationid").val(), proData);
					}
					else {
						getBoilerInitData();
					}*/
				}
			});
			$("#areablock").ligerComboBox({
				//url:'boilerBasicInfo_queryAreablockInfo.action?orgid=boiler',
				//url:'srglrbwh_queryArea.action',
				url:'boilerBasicInfo_searchAcceptunit.action?orgid=boilerbasicinfo',
				valueField: 'id',
				valueFieldID: 'areablockid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect: function (newvalue){
					//liger.get("blockstationname").setValue('');
					//liger.get("boilersName").setValue('');
				},
				onSelected:function (data){
					//if (data != null && typeof(data)!="undefined" && data != ''){
						//var se = setStationData($("#areablockid").val(),proData,$("#oilationid").val(),clearSelecteValue);
					//	if (clearSelecteValue === 1) {
						//	clearSelecteValue = 2;
					//	}
					//}
				}
			});
			$("#blockstationname").ligerComboBox({
				//url:'boilerBasicInfo_queryStationInfo.action',
				url:'srglrd_queryStation.action',
	            valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onBeforeSelect: function (newvalue){
					liger.get("boilersName").setValue('');
				},
	            onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						setBoilerData($("#stationid").val(),proData);
					}
				}
			});
			$("#boilersName").ligerComboBox({
				//url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
				url:'srglrd_queryBoiler.action',
				valueField: 'id',
				valueFieldID: 'boilersid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
			});
			$("#group").ligerComboBox({
				url:'boilerBasicInfo_searchGroupInfo.action',
				valueField: 'id',
				valueFieldID: 'groupid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
			});
			// $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
			$("#txtDate").ligerDateEditor({format: "yyyy-MM-dd", labelWidth: 100, labelAlign: 'center', cancelable : false }).setValue(myDate().Format("yyyy-MM-dd"));
		$("#txtDate1").ligerDateEditor({ format: "yyyy-MM-dd", labelWidth: 100, labelAlign: 'center', cancelable : false }).setValue(myDate().Format("yyyy-MM-dd"));
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
        	function setAreablockData(data,proData) {
				jQuery.ajax({
					type : 'post',
					url:'boilerBasicInfo_queryAreablockInfo.action',
					data: {"orgid":data},
					timeout : '3000',
					success : function(jsondata) {
						if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
							liger.get("areablock").setData(eval('(' + jsondata + ')'));
						}
						else{
							liger.get("areablock").setData(proData);
						}
					},
					error : function(jsondata) {
						alert("请求数据失败，请重新查询");
					}
				});
			}

		    function setStationData(data,proData) {
				jQuery.ajax({
					type : 'post',
					//url:'boilerBasicInfo_queryStationInfo.action',
					url:'srglrd_queryStationInfo.action',
					data: {"oilid":data},
					timeout : '3000',
					success : function(jsondata) {
						if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
							liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
						}
						else{
							liger.get("blockstationname").setData(proData);
						}
					},
					error : function(jsondata) {
						alert("请求数据失败，请重新查询");
					}
				});
				return clearSelecteValue;
			}

		    function setBoilerData(data,proData) {
				jQuery.ajax({
					type : 'post',
					//url:'boilerBasicInfo_queryBoilersNameInfo.action',
					url:'srglrd_queryBoilersNameInfo.action',
					data: {"orgid":data},
					timeout : '3000',
					success : function(jsondata) {
						if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
							liger.get("boilersName").setData(eval('(' + jsondata + ')'));
						}
						else{
							liger.get("boilersName").setData(proData);
						}
					},
					error : function(jsondata) {
						alert("请求数据失败，请重新查询");
					}
				});
			}
			
		function getBoilerInitData() {
			var oilText=[{ id: 1 , text: '' }];
			//var areaText=[{ id: 1 , text: '' }];
			var stationText=[{ id: 1 , text: '' }];
			var boilerText=[{ id: 1 , text: '' }];
			jQuery.ajax({
				type : 'post',
				url : 'srglrd_cascadeInit.action?nowdata='+parseInt(Math.random()*100000),
				success : function(jsondata) {
					var dataObj = $.parseJSON(jsondata);
					$.each(dataObj, function(key,val){
						if (key == 'oiltext') {
						oilText = val;
						}
						//if (key == 'areatext'){
						//	areaText = val;
						//}
						if (key == 'stationtext'){
							stationText = val;
						}
						if (key == 'boilertext'){
							boilerText = val;
						}
					});
					setInitData(oilText,stationText,boilerText);
				}
			});
		}
			
	function setInitData(oilText,stationText,boilerText) {
		liger.get("oilationname").setData(oilText);
		liger.get("blockstationname").setData(stationText);
		liger.get("boilersName").setData(boilerText);
	}
        //为公用页面提供接口方法 
       
		function onJKSubmit(id,name,datatype,basid){
			/* var oilstationname='';
				var areablock='';
				var blockstationname='';
				var group='';
				var wellnum='';*/
				var oilationname='';
				var areablock='';
				var blockstationname='';
				var group='';
				var boilersName='';
				var oilNmame=''
			getBoilerInitData();
				//$("#oilNmame").ligerGetComboBoxManager().setValue('');
			$("#oilationname").ligerGetComboBoxManager().setValue('');
			$("#blockstationname").ligerGetComboBoxManager().setValue('');
			$("#boilersName").ligerGetComboBoxManager().setValue('');
			$("#areablock").ligerGetComboBoxManager().setValue('');
			if(datatype=='4'){
				oilNmame=name;
				$("#oilNmame").val(name);
			}
			else if(datatype=='7'){
				blockstationname=name;
				$("#blockstationname").val(name);
			}
			else if(datatype=='14'){
				group=id;
			}
			else if(datatype=='10'){
				boilersName=name;
				$("#boilersName").val(name);
				
			}else{
				alert("没有找到该节点");
				return ;			
			}
			
			
			grid.setOptions({
			parms: [{ name: 'oilationname', value: oilationname},
				{ name: 'group', value: group},
				{ name: 'blockstationname',value: blockstationname},
				{ name: 'oilNmame',value: oilNmame},
				{ name: 'boilersName',value: boilersName}
				,{
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
        	 var oilationname=$("#oilationname").val();
  	         var areablock=$("#areablock").val();
  	         var blockstationname=$("#blockstationname").val();
  	         var boilersName=$("#boilersName").val();	
  	       	 var group	 =	$("#group").val();
  	      var  oilNmame = $("#oilNmame").val();
          
        	grid.setOptions({
        		parms: [{
					name: 'oilationname',
					value: oilationname
				},{
					name: 'areablock',
					value: areablock
				},{
					name: 'blockstationname',
					value: blockstationname
				},{
					name: 'boilersName',
					value: boilersName
				},{
					name: 'date',
					value: $("#txtDate").val()
				}
				,{
					name: 'date1',
					value: $("#txtDate1").val()
				},
				{
					name: 'oilNmame',
					value: $("#oilNmame").val()
				}
				,{
					name: 'group',
					value: group
				}
				]
        	});
         	grid.loadData(true);
        }
		
	var exportFlag=false;
	 function onExport() {
			
		     var oilationname1=$("#oilationname").val();
  	         var areablock1=$("#areablock").val();
  	         var blockstationname1=$("#blockstationname").val();
  	         var boilersName1=$("#boilersName").val();	
			
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var group=$("#group").val();
			
			if (exportFlag) {
				oilationname1=oilationname;
				areablock1 = areablock
				blockstationname1=blockstationname;
				//blockstationname1=boilersName;
				boilersName1=boilersName;
				//group = group;
				
			}
			var totalNum = 0;
			
			var url = 'szglfs_searchSRGLFS.action?oilationname='+encodeURIComponent(oilationname1)+'&areablock='+encodeURIComponent(areablock1)+'&blockstationname='+encodeURIComponent(blockstationname1)+'&group='+encodeURIComponent(group)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=export'+'&boilersName='+encodeURIComponent(boilersName1);
			var urls = 'szglfs_searchSRGLFS.action?oilationname='+encodeURIComponent(oilationname1)+'&areablock='+encodeURIComponent(areablock1)+'&blockstationname='+encodeURIComponent(blockstationname1)+'&group='+encodeURIComponent(group)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=totalNum'+'&boilersName='+encodeURIComponent(boilersName1);
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
				  <td align="right" class="l-table-edit-td" style="width:60px;">受汽区块：</td>
		                <td align="right" class="l-table-edit-td" style="width:50px;">
		                	<input type="text" id="areablock" name = "areablock"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">供热站：</td>
		                <td align="right" class="l-table-edit-td" style="width:50px;" >
		                	<input type="text" id="blockstationname" name = "blockstationname"/>
		                </td>
		               
		                <td align="right" class="l-table-edit-td" style="width:60px">锅炉名称：</td>
		                <td align="right" class="l-table-edit-td"style="width:50px;" >
		                	<input type="text" id="boilersName" name = "boilersName"/>
		                </td>
						<td align="right" class="l-table-edit-td" style="width:60px">受汽单位：</td>
		                <td align="right" class="l-table-edit-td"style="width:50px;" >
		                	<input type="text" id="oilationname" name = "oilationname" />
		                </td>
		            <!-- <td align="right" class="l-table-edit-td" style="width:60px">运行组：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="group" name="group"/>
	                </td> -->
		                <td align="left">
		                </td>
	                <td align="left" class="l-table-edit-td" style="width:40px">日期：</td>
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