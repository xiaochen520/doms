<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>过热锅炉维护数据</title>
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
    <script src="../../lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerPopupEdit.js"></script>
    
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../../lib/json2.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var oilationnameORG;
        var eee;
        var grid1 = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        var dataflg = 1;
        var ghname='';
        var oilstation;
        var areaname='';
		var stationname='';
		var wellname='';
		var areaData;
		var boilerData;
		var yxzData;
		var oilData;
		var injeData;
		var dailyOutput=0;
		var waterRation=0;
		var pdailyOutput=0;
		var pwaterRation=0;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
			seachSelectData();
                jQuery.ajax({
					type : 'post',
					url : 'blilercdrpd_pageInit.action?arg=wh',
					async : false,
					success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid1 = $("#maingrid").ligerGrid(data);					
							toolbar = grid1.topbar.ligerGetToolBarManager(); 
							if (toolbar != null && typeof(toolbar)!="undefined"){ 
								var toolteams = toolbar.options.items; 
								removeSaveButton(toolteams); 
							}	
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
				
			        
                var proData = [{ id: 1 , text: '' }];
                $("#oilationname").ligerComboBox({
    				url:'rulewell_queryOilSationInfo.action?arg=boiler',
    				valueField: 'id',
    				valueFieldID: 'oilationid',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:100,
    				autocomplete:true,
    				hideOnLoseFocus:true,
    				onBeforeSelect: function (newvalue){
    					liger.get("blockstationname").setValue('');
    					liger.get("boilersName").setValue('');
    					//liger.get("areablock").setValue('');
    				},
    				onSelected:function (data){
    					clearSelecteValue=1;
    					if ($("#oilationid").val() != 1) {
    						//setAreablockData($("#oilationid").val(),proData);
    						setStationData('',proData,$("#oilationid").val(),'5');
    					}
    					else {
    						getBoilerInitData();
    					}
    				}
    			});
    			 $("#areablock").ligerComboBox({
    				url:'boilerBasicInfo_searchAcceptunit.action?orgid=boilerbasicinfo',
    				valueField: 'id',
    				valueFieldID: 'hidareablockid',
    				textField: 'text',
    				selectBoxHeight:300,
    				selectBoxWidth:100,
    				autocomplete:true,
    				hideOnLoseFocus:true
    			}); 
    			
    			 $("#acceptunit").ligerComboBox({
    				url:'station_queryAreablockInfo.action?orgid=sqdw',
    				valueField: 'id',
    				valueFieldID: 'hidacceptunitid',
    				textField: 'text',
    				selectBoxHeight:300,
    				selectBoxWidth:100,
    				autocomplete:true,
    				hideOnLoseFocus:true
    				
    			}); 
    			$("#blockstationname").ligerComboBox({
    				url:'boilerBasicInfo_queryStationInfo.action',
    	            valueField: 'id',
    				valueFieldID: 'stationid',
    				textField: 'text',
    				selectBoxHeight:300,
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
    				url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
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
    				valueFieldID: 'boilersid',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				}
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
            $("#dayreport").ligerTip();
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
            
            
        });
        
        //为公用页面提供接口方法 
	function onJKSubmit(id,name,datatype,basid){
		//var oilationname = '';
		var acceptunit='';
		var areablock='';
		var blockstationname='';
		var ghname='';
		var wellnum='';
		oilationnameORG='';
		
		//$("#areablock").ligerGetComboBoxManager().setValue('');
		getBoilerInitData();
		$("#oilationname").ligerGetComboBoxManager().setValue('');
		$("#boilersName").ligerGetComboBoxManager().setValue('');
		$("#blockstationname").ligerGetComboBoxManager().setValue('');
		$("#acceptunit").ligerGetComboBoxManager().setValue('');
		$("#areablock").ligerGetComboBoxManager().setValue('');
		if(datatype=='4'){
			//oilationname = name;
			//$("#acceptunit").val(name);
			oilationnameORG = name;
		}
		else if(datatype=='7'){
			blockstationname=name;
			$("#blockstationname").val(name);
		}
		else if(datatype=='14'){
			ghname=id;
		}
		else if(datatype=='10'){
			wellnum=name;
			$("#boilersName").val(name);
			
		}else{
			alert("没有找到该节点");
			return ;			
		}
		
		
		grid1.setOptions({
		parms: [{ name: 'acceptunit', value: ''},
			{ name: 'oilstationname', value: oilationnameORG},
			{ name: 'areablock', value: $("#areablock").val()},
			{ name: 'blockstationname',value: blockstationname},
			{ name: 'boilersName',value:wellnum},
			{
				name: 'startDate',
				value: $("#txtDate").val()
			},{
				name: 'endDate',
				value: $("#txtDate1").val()
			}]
		});
     	grid1.loadData(true);
	}
		

	function onSubmit()
	{
		datastypeid = '';
		if($("#datastype").val() != ''){
			datastypeid = $("#datastypeid").val()
		}
		oilationnameORG="";
		grid1.setOptions({
			parms: [
			{name: 'datastype',value: datastypeid},
			{name: 'oilstationname',value: oilationnameORG},
			{name: 'areablock',value: $("#areablock").val()},
			{name: 'blockstationname',value: $("#blockstationname").val()},
			{name: 'boilersName',value: $("#boilersName").val()},
			{name: 'acceptunit',value: $("#acceptunit").val()},
			{name: 'wellnum',value: $("#wellnum").val()},
			{name: 'startDate',value: $("#txtDate").val()},
			{name: 'endDate',value: $("#txtDate1").val()}]
		});
		//setGridHid();
		grid1.loadData(true);
	}
	
	function dayreport(){
			jQuery.ajax({
				type : 'post',
				url : 'sagddrpd_dayreport.action?nowdata='+parseInt(Math.random()*100000),
				async : false,
				success : function(data) {
					//ownerData=JSON2.stringify(data);
					if (data != null && typeof(data)!="undefined"){
							var outData = eval('(' + data + ')');
							if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
								$.ligerDialog.success(outData.CONFIRMMSG);
							}else{
								$.ligerDialog.success("操作成功");
							}
					}
				},
				error : function(data) {
			
				}
			});
		}
		
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

    function setStationData(areaid,proData,oilid,clearSelecteValue) {
		jQuery.ajax({
			type : 'post',
			url:'boilerBasicInfo_queryStationInfo.action',
			data: {"areaid":areaid,"oilid":oilid,"selecteValue":clearSelecteValue},
			timeout : '3000',
			success : function(jsondata) {
				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
					if (clearSelecteValue == '4') {
						liger.get("grzh2").setData(eval('(' + jsondata + ')'));
					} else {
						liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
					}
				}
				else{
					if (clearSelecteValue == '4') {
						liger.get("grzh2").setData(proData);
					} else {
						liger.get("blockstationname").setData(proData);
					}
				}
			},
			error : function(jsondata) {
				alert("请求数据失败，请重新查询");
			}
		});
	}

    function setBoilerData(data,proData) {
		jQuery.ajax({
			type : 'post',
			url:'boilerBasicInfo_queryBoilersNameInfo.action',
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
			url : 'boilerBasicInfo_cascadeInit.action',
			data: {"pageid":'基础信息'},
			success : function(jsondata) {
				var dataObj = $.parseJSON(jsondata);
				$.each(dataObj, function(key,val){
					if (key == 'oiltext') {
					oilText = val;
					}
					/* if (key == 'areatext'){
						areaText = val;
					} */
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
		//liger.get("areablock").setData(areaText);
		liger.get("blockstationname").setData(stationText);
		liger.get("boilersName").setData(boilerText);
	}
	
	function itemclick(item) {
		var rows = grid1.getCheckedRows();
		var id='';
		if(rows.length == 1){
			$(rows).each(function (){
				id=this.RPD_BOILER_SUPERHEAT_ID+'';
			});
		}
		switch (item.icon) {
			case "add":
				/**弹出编辑*/
				titleName='添加过热锅炉日报维护数据';
				$.ligerDialog.open({
					title: titleName,
					url:'AddBoilerCrossdRPD.jsp',
					width: 1050,
					height: 550
				});
				/** 直接编辑
				var md = myDate().Format("yyyy-MM-dd")+'';
				grid1.addEditRow({
					REPORT_DATE: md
				}); */
			break;
			case "modify":
				/** 弹出编辑*/
				if(rows.length == 1){
					titleName='修改过热锅炉日报维护数据';
					$.ligerDialog.open({
						title: titleName,
						url:'UpdateBoilerCrossdRPD.jsp',
						width: 1050,
						height: 550
					});
				}else{
					alert('请选择一条你要修改信息的数据！');
				}
			/** 直接编辑
				if(rows.length == 1){
				}else{
					alert('请选择一条你要修改信息的数据！');
				}*/
			break;
			case "delete":
				if(rows.length == 1){
					$.ligerDialog.confirm('确定删除吗?', function (yes) {
						if (yes) {
							if (id != '' && id != null && id!="undefined") {
								if(yes){
									jQuery.ajax({
									type : 'post',
									url : 'blilercdrpd_removeBoilerCrpssdRPD.action',
									data: {"id":id},
									async : false,
									success : function(data) {
										if (data != null && typeof(data)!="undefined" && data == "1"){
											onSubmit();
										}else{
											$.ligerDialog.error(outerror(jsondata));
										}
									},
									error : function(data) {
									}
									});
								}
							}
							grid1.deleteSelectedRow();
							$.ligerDialog.success("删除成功！");
						}
						
					});
				}else{
					alert("请选择一条你要删除的数据！");
				}
			break;
			case "save":
				var rows = grid1.getCheckedRows();
				if (rows.length != 1) {
					alert("只能选择一条你需更新或添加的数据！");
					return;
				}
				var flag=false;
				var op='';
				var rowName='';
				var rowIndex=0;
				$(rows).each(function (){
					var myDate = new Date();
					//var myDate2 = new Date(this.REPORT_DATE);
					if (this.__status == 'nochanged') {//
						$.ligerDialog.error("没有更改的数据!");
						return;
					}
					var now = new Date();
					var myDayStr = now.Format("yyyy-MM-dd");

					if (this.REPORT_DATE != myDayStr ) {
						$.ligerDialog.error("不能添加或修改日期不为当天的数据!");
						return;
					}
					var bn= this.BOILER_NAME;
					var id=this.RPD_BOILER_SUPERHEAT_ID;
					
					var now = new Date();
					var myDayStr = now.Format("yyyy-MM-dd");

					if (this.__status == 'add') {
						op='添加';
					}
					if (this.__status == 'update') {
						op='修改';
					}
					rowName=this.BOILER_NAME;
					rowIndex=this.__index+1;
					this.rpd_boiler_superheat_id=id;
					flag=true;
				});
				if (!flag) {
					return;
				}
				var updateRowDate = JSON2.stringify(rows);
				$.ligerDialog.confirm('确定'+op+'第'+rowIndex+'行井名为'+rowName+'的记录吗?', function (yes) {
					if (yes) {
						jQuery.ajax({
							type : 'post',
							url : 'blilercdrpd_SaveOrUpadateBoilerCrossdRPD.action',
							data: {"rowData":updateRowDate},
							async : false,
							timeout : '3000',
							success : function(jsondata) { 
								$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
								if (jsondata == 1){
									//$.ligerDialog.close();
									//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
									window.onSubmit();
									$.ligerDialog.success("保存成功！");
								}else{
									$.ligerDialog.error(outerror(jsondata));
								}
							},
							error : function(data) {
							}
						});
					}
				});
			break;
		}
	}
	function seachSelectData()
    {
		jQuery.ajax({
			type : 'post',
			url : 'station_queryAreablockInfo.action?orgid=',
			async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				areaData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});
		jQuery.ajax({
			type : 'post',
			url : 'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
			async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				boilerData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});
		jQuery.ajax({
			type : 'post',
			url : 'boilerBasicInfo_searchGroupInfo.action',
			async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				yxzData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});
		jQuery.ajax({
			type : 'post',
			url : 'station_queryAreablockInfo.action?orgid=sqdw',
			async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				oilData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});
		jQuery.ajax({
			type : 'post',
			url : 'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
			async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				injeData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});
    }
	function f_onBeforeEdit(e)
	{
		if (e.column.columnname == "I_DAILY_OUTPUT") {
			//alert(JSON2.stringify(e));
			$.ligerDialog.open({
	    			title: "液量",
	    			url:'dailyOutput1.jsp?orgId='+e.record.ORG_ID,
	    			width: 350,
	    			height: 450
	    	});
		}
		if (e.column.columnname == "I_WATER_RATION") {
			$.ligerDialog.open({
	    			title: "含水",
	    			url:'waterRation.jsp?wellName='+e.record.JHMC,
	    			width: 350,
	    			height: 450
	    	});
		}
		if (e.column.columnname == "P_DAILY_OUTPUT") {
			$.ligerDialog.open({
	    			title: "液量",
	    			url:'dailyOutput1.jsp?orgId='+e.record.ORG_ID+'&argp=p',
	    			width: 350,
	    			height: 450
	    	});
		}
		if (e.column.columnname == "P_WATER_RATION") {
			$.ligerDialog.open({
	    			title: "含水",
	    			url:'waterRation.jsp?wellName='+e.record.JHMC+'&argp=p',
	    			width: 350,
	    			height: 450
	    	});
		}
		if(e.record.VERIFIER==''|| typeof(e.record.VERIFIER)=="undefined") {
			return true;
		}
		return false;
	}
	function f_onBeforeCheckRow(checked, data, rowid, rowdata)
	{
		if(data.VERIFIER==''|| typeof(data.VERIFIER)=="undefined") {
			return true;
		}
		$.ligerDialog.error("审核后的数据不能进行操作！");
		return false;
	}
	function f_onAfterEdit(e)
	{
		if (dailyOutput!=0.0 && typeof(dailyOutput)!="undefined") {
			grid1.updateCell('I_DAILY_OUTPUT', dailyOutput, e.record);
			dailyOutput=0.0;
			if (e.record.I_DAILY_OUTPUT!=0.0&&e.record.I_DAILY_OUTPUT!=''&&e.record.I_WATER_RATION!=0.0&&e.record.I_WATER_RATION!='') {
				var i_water_ration=e.record.I_WATER_RATION.split('>')[1].split('<')[0];
				grid1.updateCell('I_DAILY_OIL_OUTPUT', e.record.I_DAILY_OUTPUT * (1-i_water_ration/100), e.record);
			}
		}
		if (waterRation!=0.0 && typeof(waterRation)!="undefined") {
			grid1.updateCell('I_WATER_RATION', '<span style="color:red">' + waterRation + '</span>', e.record);
			waterRation=0.0;
			if (e.record.I_DAILY_OUTPUT!=0.0&&e.record.I_DAILY_OUTPUT!=''&&e.record.I_WATER_RATION!=0.0&&e.record.I_WATER_RATION!='') {
				var i_water_ration=e.record.I_WATER_RATION.split('>')[1].split('<')[0];
				grid1.updateCell('I_DAILY_OIL_OUTPUT', e.record.I_DAILY_OUTPUT * (1-i_water_ration/100), e.record);
			}
		}
		if (pdailyOutput!=0.0 && typeof(pdailyOutput)!="undefined") {
			grid1.updateCell('P_DAILY_OUTPUT', pdailyOutput, e.record);
			pdailyOutput=0.0;
			if (e.record.P_DAILY_OUTPUT!=0.0&&e.record.P_DAILY_OUTPUT!=''&&e.record.P_WATER_RATION!=0.0&&e.record.P_WATER_RATION!='') {
				var p_water_ration=e.record.I_WATER_RATION.split('>')[1].split('<')[0];
				grid1.updateCell('P_DAILY_OIL_OUTPUT', e.record.P_DAILY_OUTPUT * (1-p_water_ration/100), e.record);
			}
		}
		if (pwaterRation!=0.0 && typeof(pwaterRation)!="undefined") {
			grid1.updateCell('P_WATER_RATION', '<span style="color:red">' + pwaterRation + '</span>', e.record);
			pwaterRation=0.0;
			if (e.record.P_DAILY_OUTPUT!=0.0&&e.record.P_DAILY_OUTPUT!=''&&e.record.P_WATER_RATION!=0.0&&e.record.P_WATER_RATION!='') {
				var p_water_ration=e.record.I_WATER_RATION.split('>')[1].split('<')[0];
				grid1.updateCell('P_DAILY_OIL_OUTPUT', e.record.P_DAILY_OUTPUT * (1-p_water_ration/100), e.record);
			}
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
           
          
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;">
	 <div id="mainsearch" style=" width:99%">
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
						<tr>
			            <td align="right" class="l-table-edit-td" style="width:60px;display:none">采油站：</td>
			                <td align="right" class="l-table-edit-td" style="width:80px;display:none">
			                	<input type="text" id="oilationname" name="oilationname"/>
			                </td>
							<td align="right" class="l-table-edit-td" style="width:60px;">受汽区块：</td>
			                <td align="right" class="l-table-edit-td" style="width:80p;">
			                	<input type="text" id="areablock" name="areablock"/>
			                </td>
							<td align="right" class="l-table-edit-td" style="width:60px">供热站：</td>
			                <td align="right" class="l-table-edit-td" style="width:80px">
			                	<input type="text" id="blockstationname" name="blockstationname"/>
			                </td>
			                <td align="left" style="width:10px">
			                </td>
			                <td align="right" class="l-table-edit-td" style="width:60px">锅炉名称：</td>
			                <td align="left" class="l-table-edit-td" style="width:80px">
			                	<input type="text" id="boilersName" name="boilersName"/>
			                </td>
			                <td align="right" class="l-table-edit-td" style="width:60px;display:none">运行组：</td>
			                <td align="left" class="l-table-edit-td" style="width:80px;display:none">
			                	<input type="text" id="group" name="group"/>
			                </td>
			                
			                 <td align="right" class="l-table-edit-td" style="width:80px">受汽单位：</td>
			                <td align="left" class="l-table-edit-td" style="width:80px">
			                	<input type="text" id="acceptunit" name="acceptunit"/>
			                </td>
			               	 <td align="left">
			                </td>
			                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
							<td><input type="text" id="txtDate" ltype="date"/></td>
							<td valign="middle">--</td>
							<td><input type="text" id="txtDate1" ltype="date"/></td>
							<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
						
					</tr>
				</table>
			
			</form>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<div id="maingrid"></div> 
		</div>
</body>
</html>