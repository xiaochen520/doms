﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>常规油井日报数据</title>
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
    
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
    <script type="text/javascript">
    var eee;
    var grid = null;
    var toolbar ;
    var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
    
    //鼠标选择数据
	var rule_wellid ; 	//常规油井id
	var well_name;		//常规井号
	var well_cole;		//常规编码
	var status_value	//建设生产状态
	var org_id			//组织结构ID
	var remark			//备注
	var zzz_id;
	var gh_id;
	var clearSelecteValue=0;

        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'ruleWellRPD_ruleWellRPDpageInit.action',
					async : false,
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
                //采油站
                /*$("#oilationname").ligerComboBox({
				url:'rulewell_queryOilSationInfo.action',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					clearSelecteValue=1;
					//liger.get("areablock").setValue('');
					liger.get("blockstationname").setValue('');
					liger.get("boilersName").setValue('');
					liger.get("banzu").setValue('');
					if ($("#oilationid").val() != 1) {
						//setAreablockData($("#oilationid").val(),proData);
						setStationData('',proData,$("#oilationid").val(),clearSelecteValue);
						setBanzuData('',proData,$("#oilationid").val(),clearSelecteValue);
					}
					else {
						getBoilerInitData();
					}
				}
				});*/
				$("#oilstationname").ligerComboBox({
					url:'rulewell_queryOilSationInfo.action',
					valueField: 'id',
					valueFieldID: 'oilationid',
					textField: 'text',
					selectBoxHeight:350,
					selectBoxWidth:100,
					autocomplete:true,
					hideOnLoseFocus:true,
					onBeforeSelect: function (newvalue){
						//liger.get("areablock1").setValue('');
						liger.get("blockstationname").setValue('');
						liger.get("boilersName").setValue('');
						liger.get("banzu").setValue('');
					},
					onSelected:function (data){
						clearSelecteValue=1;
						if ($("#oilationid").val() != 1) {
							setStationData($("#oilationid").val(),proData);
							setBanzuData($("#oilationid").val(),proData);
						}
						else {
							getWellInitData();
						}
					}
				});
        		//班组
                $("#banzu").ligerComboBox({
        			url:'ruleWellRPD_queryTeamInfo.action',
                    valueField: 'id',
        			valueFieldID: 'banzuid',
        			textField: 'text',
        			selectBoxHeight:400,
        			selectBoxWidth:140,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                    onSelected:function (data){
        				liger.get("wellName").setValue('');
        				ghname='';
        				if (data != null && typeof(data)!="undefined" && data != ''){
        					setWellInitData($("#banzuid").val(),proData);
        				}
        			}
        		});
        		//转油站
        		$("#blockstationname").ligerComboBox({
        			url:'rulewell_queryStationInfo.action',
                    valueField: 'id',
        			valueFieldID: 'stationsid',
        			textField: 'text',
        			selectBoxHeight:400,
        			selectBoxWidth:140,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                    onSelected:function (data){
        				liger.get("wellName").setValue('');
        				ghname='';
        				if (data != null && typeof(data)!="undefined" && data != ''){
        					setWellInitData($("#stationsid").val(),proData);
        				}
        			}
        		});
        		//管汇
        		$("#boilersName").ligerComboBox({
    				url:'manifoldBasicInfo_queryManifoldNameInfo.action',
    				valueField: 'id',
    				valueFieldID: 'ghid',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				}
    			});

    			//井口
    			$("#wellName").ligerComboBox({
				url:'rulewell_queryWellInfo.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
					if(liger.get("wellName").getValue() != ''){
                		/* liger.get("areablock1").setValue(''); */
                	}
				}
				});
				//审核状态
    			$("#shstatus").ligerComboBox({
    				url:'comboboxdata_searchSwitchInflg.action?args=ALL',
    				valueField: 'id',
    				valueFieldID: 'hidshstatus',
    				textField: 'text',
    				selectBoxHeight:100,
    				width: 100,
    				autocomplete:true,
    				hideOnLoseFocus:true
    			});
        		
			   $("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
            $("#txtDate1").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
        function setWellInitData(data,proData,wellid) {
      		jQuery.ajax({
      			type : 'post',
      			url:'sagd_queryWellNameInfo.action',
      			data: {"orgid":data},
      			timeout : '3000',
      			success : function(jsondata) {
      				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
      					liger.get("wellnum").setData(eval('(' + jsondata + ')'));
      				}
      				else{
      					liger.get("wellnum").setData(proData);
      				}
      			},
      			error : function(jsondata) {
      				alert("请求数据失败，请重新查询");
      			}
      		});
      		if (wellid != '') {
				$("#wellName").ligerGetComboBoxManager().selectValue(wellid);
			}
      	}
        //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){
			exportFlag = true;
			oilstationname = "";
			areaname ="";
			stationname = "";
			ghname = "";
			wellname = "";
			$("#areablock").ligerGetComboBoxManager().setValue('');
			getWellInitData();
			
			if(datatype=='4'){
				oilstationname=name;
				oilstation=name;
			}
			
			if(datatype=='6'){
				areaname=name;
			}
			
			if(datatype=='7'){
				stationname=name;
				$("#blockstationname").val(name);
			}
			
			if(datatype=='8'){
				ghname=id;
			}
			
			if(datatype=='9'){
				wellname=name;
				$("#wellnum").val(name);
			}
			grid.setOptions({
        		parms: [
        		{ name: 'oilstationname', value: oilstationname },
        		{ name: 'areablock', value: areaname },
				{ name: 'blockstationname', value: stationname },
				{ name: 'ghname', value: ghname },
				{ name: 'wellnum', value: wellname},
				{ name: 'startDate', value: $("#txtDate").val()},
				{ name: 'endDate', value: $("#txtDate1").val()},
				{ name: 'search',value: 'search'}
				]
        	});
         	grid.loadData(true);
		}
		

         function onSubmit()
        {
        	exportFlag = false;
        	
        	grid.setOptions({
        		parms: [
        		{
					name: 'oilstationname',
					value: $("#oilstationname").val()
				},{
					name: 'banzu',
					value: $("#banzu").val()
				},{
					name: 'blockstationname',
					value: $("#blockstationname").val()
				},{
					name: 'boilersName',
					value: $("#boilersName").val()
				},{
					name: 'wellnum',
					value: $("#wellnum").val()
				},{
					name: 'startDate',
					value: $("#txtDate").val()
				},{
					name: 'endDate',
					value: $("#txtDate1").val()
				},{
					name: 'shstatus',
					value: $("#shstatus").val()
				}
				]
        	});
        	//setGridHid();
         	grid.loadData(true);
         	
        }
         
	function setStationData(oilid,proData) {
		jQuery.ajax({
			type : 'post',
			url:'rulewell_queryStationInfo.action',
			data: {"oilid":oilid},
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
	}

	function setBanzuData(oilid,proData) {
		jQuery.ajax({
			type : 'post',
			url:'ruleWellRPD_queryTeamInfo.action',
			data: {"oilid":oilid},
			timeout : '3000',
			success : function(jsondata) {
				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
					liger.get("banzu").setData(eval('(' + jsondata + ')'));
				}
				else{
					liger.get("banzu").setData(proData);
				}
			},
			error : function(jsondata) {
				alert("请求数据失败，请重新查询");
			}
		});
	}

	function getWellInitData() {
		var areaText=[{ id: 1 , text: '' }];
		var stationText=[{ id: 1 , text: '' }];
		var wellText=[{ id: 1 , text: '' }];
		jQuery.ajax({
			type : 'post',
			url : 'sagd_cascadeInit.action',
			success : function(jsondata) {
			var dataObj = $.parseJSON(jsondata);
				$.each(dataObj, function(key,val){
					if (key == 'areatext'){
						areaText = val;
					}
					if (key == 'stationtext'){
						stationText = val;
					}
					if (key == 'welltext'){
						wellText = val;
					}
				});
				setInitData(areaText,stationText,wellText);
			}
		});
	}
  	
	function setInitData(areaText,stationText,wellText) {
		liger.get("blockstationname").setData(stationText);
		liger.get("areablock").setData(areaText);
		liger.get("wellnum").setData(wellText);
	}
      
      function onExport() {
			var oilstationname=$("#oilstationname").val();
			var areablock=$("#areablock").val();
			var blockstationname=$("#blockstationname").val();
			var wellnum=$("#wellnum").val();
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var gh='';
			if (exportFlag) {
				oilstationname=oilstation;
				areablock=areaname;
				blockstationname=stationname;
				gh=ghname;
				wellnum=wellname;
			}
			var totalNum = 0;
			var url = 'sagddrpd_searchSagdRPD.action?oilstationname='+encodeURIComponent(oilstationname)+'&areablock='+encodeURIComponent(areablock)+'&blockstationname='+encodeURIComponent(blockstationname)+'&wellnum='+encodeURIComponent(wellnum)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum=export'+'&ghname='+gh;
			var urls = 'sagddrpd_searchSagdRPD.action?oilstationname='+encodeURIComponent(oilstationname)+'&areablock='+encodeURIComponent(areablock)+'&blockstationname='+encodeURIComponent(blockstationname)+'&wellnum='+encodeURIComponent(wellnum)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum=totalNum'+'&ghname='+gh;
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

<body style="overflow-x:hidden; padding:2px;">
	 <div id="mainsearch" style=" width:99%">
	 
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
						<td align="right" class="l-table-edit-td" style="width:60px">采油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname" name = "oilstationname" />
		                </td>
		               
		                <td align="right" class="l-table-edit-td" style="width:60px">班组：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="banzu" name = "banzu" />
		                </td>
		               
		                  <td align="right" class="l-table-edit-td" style="width:60px">转油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="blockstationname" name = "blockstationname"/>
		                </td>
		                
		                <td align="right" class="l-table-edit-td" style="width:40px">管汇：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="boilersName" name = "boilersName"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px">井口：</td>
						<td align="left" class="l-table-edit-td" style="width:60px">
							<input type="text" id="wellName" name = "wellName"/>	
						</td>
						
						
						
						</tr>
		              <tr>
						<td align="right" class="l-table-edit-td" style="width:80px">审核状态：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="shstatus" name="shstatus"/>
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td ><input type="text" id="txtDate" ltype="date"/></td>
						<td valign="middle">--</td>
						<td ><input type="text" id="txtDate1" ltype="date"/></td>
		                <td align="right" class="l-table-edit-td" style="width:10px"></td>
		                <td align="left" class="l-table-edit-td" >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                 <td align="left" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a>
		                </td>
					</tr>
				
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
	  
		  
		    </form>
		    
		</div>
    
</body>
</html>