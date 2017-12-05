﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>注转站信息管理</title>
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
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        
        //鼠标选择数据
		var blockstationid ; 	//注转站ID
		var blockstation_name;	//注转站名称
		var bs_type;			//注转站类型
		var qkmc;				//区块代码
		var oildrilling_name;	//采油井名称
		var ghs;					//管汇数
		var cygs;				//储油罐数
		var cyggg;				//储油罐规格
		var zybs;				//转油泵数
		var jrbz;				//接入标志
		var org_id;			//组织结构ID
		var remark;				//备注
		var status_value;		//建设生产状态
		var commissioning_date;	//投产日期
		var  steam_remark; //注汽站备注
		var  boiler_qty;   //锅炉数量
		var  blockstation_code; //注转站编码
		
		var ljjd_s;
		var clearSelecteValue=0;
		var lhzid;
		var yxzid;
		var gh_id;
		var qkid;
		var dyear;
		var jrbz1;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'station_pageInit.action',
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

			$("#status_value").ligerComboBox({
				url:'station_searchStatusValue.action',
				valueField: 'id',
				valueFieldID: 'hidstatusid',
				textField: 'text',
				selectBoxHeight:200,
				width: 120,
				autocomplete:true,
				hideOnLoseFocus:true
			});
			
			var proData = [{ id: 1 , text: '' }];
			$("#combination").ligerComboBox({
				url:'waterSoWell_queryCombinationInfo.action?arg=group',
				valueField: 'id',
				valueFieldID: 'combinationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect: function (newvalue){
					/* liger.get("areablock").setValue(''); */
					//liger.get("blockstationname").setValue('');
					//liger.get("blockstationtype").setValue('');
					//liger.get("oilstationname").setValue('');
				},
				onSelected:function (data){
					clearSelecteValue=1;
					if ($("#combinationid").val() != 1) {
						//setAreablockData($("#oilationid").val(),proData);
						//setStationData($("#combinationid").val(),'','',proData,clearSelecteValue);
					}
					else {
						//getBoilerInitData();
					}
				}
			});
			$("#oilstationname").ligerComboBox({
				url:'station_queryOilSationInfo.action',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect: function (newvalue){
				//liger.get("combination").setValue('');
				/* liger.get("areablock").setValue(''); */
				liger.get("blockstationname").setValue('');
				liger.get("blockstationtype").setValue('');
				},
				onSelected:function (data){
					clearSelecteValue=1;
					if ($("#oilationid").val() != 1) {
						//setAreablockData($("#oilationid").val(),proData);
						setStationData('',$("#oilationid").val(),$("#areablockid").val(),proData,clearSelecteValue);
					}
					else {
						getBoilerInitData();
					}
				}
			});
			$("#areablock").ligerComboBox({
				url:'station_queryAreablockInfo.action?orgid=',
				valueField: 'id',
				valueFieldID: 'areablockid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect: function (newvalue){
					/* liger.get("blockstationname").setValue('');
					liger.get("blockstationtype").setValue(''); */
				},
				onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						setStationData('',$("#oilationid").val(),$("#areablockid").val(),proData,clearSelecteValue);
						if (clearSelecteValue === 1) {
							clearSelecteValue = 2;
						}
					}
				}
			});
			$("#blockstationname").ligerComboBox({
				url:'station_queryStationInfo.action',
	            valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onBeforeSelect: function (newvalue){
					liger.get("blockstationtype").setValue('');
				},
	            onSelected:function (data){
				}
			});
			$("#oildrilling_name").ligerComboBox({
				url:'station_searchSationInfo.action',
				valueField: 'id',
				valueFieldID: 'zhanid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				hideOnLoseFocus:true,
				autocomplete:true,
				alwayShowInTop:false,
				onBeforeSelect: function (newvalue){
					//liger.get("yxz2").setValue(proData);
				},
				onSelected:function (data){
					//setGroupData($("#zhanid").val(),proData);
				}
			});
			var yxzs = $("#yxz2").ligerComboBox({
				url:'boilerBasicInfo_searchGroupInfo.action',
				valueField: 'id',
				valueFieldID: 'yxz2id',
				textField: 'text',
				selectBoxHeight:120,
				width: 120,
				hideOnLoseFocus:true,
	            autocomplete:true,
	            alwayShowInTop:false
			});
			
			$("#blockstationtype").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'typeid',
				textField: 'text',
				data: [
	                    { text: '注汽接转站', id: '1' },
	                    { text: '注汽站', id: '2' },
	                    { text: '接转站', id: '3' },
	                    { text: '管汇', id: '4' }
	                ],
				selectBoxHeight:100,
				width: 120,
				autocomplete:true,
				hideOnLoseFocus:true
			});
			$("#bs_type").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'typeid',
				textField: 'text',
				data: [
	                    { text: '注汽接转站', id: '1' },
	                    { text: '注汽站', id: '2' },
	                    { text: '接转站', id: '3' },
	                    { text: '管汇', id: '4' }
	                ],
				selectBoxHeight:100,
				width: 120,
				autocomplete:true,
				hideOnLoseFocus:true
			});

			$("#jrbz").ligerComboBox({
            	url:'comboboxdata_searchSwitchInflg.action',
 				valueField: 'id',
 				valueFieldID: 'jrbzid',
 				textField: 'text',
 				selectBoxHeight:200,
 				width: 120,
 				autocomplete:true,
 				hideOnLoseFocus:true
			});  
			$("#jrbz1").ligerComboBox({
				url:'comboboxdata_searchSwitchInflg.action?args=ALL',
				valueField: 'id',
				valueFieldID: 'jrbz1id',
				textField: 'text',
				selectBoxHeight:200,
				width: 120,
				autocomplete:true,
				hideOnLoseFocus:true
			});
			document.getElementById("jrbz1").value= "全部";
			  $("#ljjd_s").ligerComboBox({
	     			url:'sagd_getServerNode.action',
	     			valueField: 'id',
	     			valueFieldID: 'hidserverid',
	     			textField: 'text',
	     			selectBoxHeight:150,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true

	     		});

		$("#qkmc").ligerComboBox({
			url:'sagd_queryAreablockInfo.action?arg=all',
			valueField: 'id',
			valueFieldID: 'qksid',
			textField: 'text',
			selectBoxHeight:350,
			selectBoxWidth:100,
			autocomplete:true,
			hideOnLoseFocus:true
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
              	   
              	   	var blockstationid  = $("#blockstationid").val();
					var blockstation_name  = $("#blockstation_name").val();
					var bs_type = $("#bs_type").val();
					var qkmc  = $("#qkmc").val();
				//	var oildrilling_name  = $("#oildrilling_name").val();
				//zhanid
					if( $("#oildrilling_name").val() != ''){
						oildrilling_name = $("#zhanid").val();
					}
					var ghs  = $("#ghs").val();
					var cygs  = $("#cygs").val();
					var cyggg  = $("#cyggg").val();
					var zybs  = $("#zybs").val();
					//var jrbz  = $("#jrbz").val();
					//var jrbz  = $("#org_id").val();
					var remark  = $("#remark").val();
					var status_value  = $("#hidstatusid").val();
					var commissioning_date  = $("#commissioning_date").val();
					var scrapped_date = "";
					var jrbz="";
					if($("#jrbz").val() != ''){
						jrbz = $("#jrbzid").val();
					}
					var ljjd_s  = $("#ljjd_s").val();
					if(ljjd_s != ''){
						ljjd_s =  $("#hidserverid").val() ;
					}
					var yxz2 ="";
					if( $("#yxz2").val() != ''){
						yxz2 = $("#yxz2id").val();
					}
					var blockstationcode = $("#blockstation_code").val();
               		var boilerqty = $("#boiler_qty").val();
               		var steamremark = $("#steam_remark").val();
               		var dyear= $("#dyear").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'station_addSta.action',
									async : false,
									data: {"blockstation_name":blockstation_name ,"qkmc":qkmc,"oildrilling_name":oildrilling_name ,
											"ghs":ghs, "cygs":cygs ,"cyggg":cyggg,"zybs":zybs,"jrbz":jrbz,"bs_type":bs_type,
											"status_value":status_value,"commissioning_date":commissioning_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"remark":remark,"yxz2":yxz2,
											"blockstationcode":blockstationcode,"dyear":dyear,
											"boilerqty":boilerqty,
											"steamremark":steamremark
											},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage1(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('注转站添加成功！');
											operate = 0;
										}else{
											$.ligerDialog.error(outerror(jsondata));
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   //2-修改
              	   }else if(operate == 2){
              	   		 jQuery.ajax({
									type : 'post',
									url : 'station_updateStation.action',
									async : false,
									data: {"blockstation_name":blockstation_name ,"qkmc":qkmc,"oildrilling_name":oildrilling_name ,
											"ghs":ghs, "cygs":cygs ,"cyggg":cyggg,"zybs":zybs,"jrbz":jrbz,"bs_type":bs_type,
											"status_value":status_value,"commissioning_date":commissioning_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"remark":remark,"blockstationid":blockstationid,"yxz2":yxz2,
											"blockstationcode":blockstationcode,"dyear":dyear,
											"boilerqty":boilerqty,
											"steamremark":steamremark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage1(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('注转站修改成功！');
											operate = 0;
										}else{
											$.ligerDialog.error(outerror(jsondata));
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   }
		           
                }
            });
            $("form").ligerForm();
            //$("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"})
            $("#commissioning_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
			// $("#dyear").ligerDateEditor({labelWidth: 100, labelAlign: 'center' ,format :"yyyy-MM-dd",cancelable : false});
			// $("#dyear1").ligerDateEditor({labelWidth: 100, labelAlign: 'center' ,format :"yyyy-MM-dd",cancelable : false});
			 $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
           // $("#commissioning_date").ligerDateEditor({ showTime: true,absolute:true ,format :"yyyy-MM-dd"});
            $(".l-button-test").click(function () {
            });
        });  

		function setAreablockData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'station_queryAreablockInfo.action',
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

		function setGroupData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'boilerBasicInfo_searchGroupInfo.action',
				data: {"arg":data},
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("yxz2").setData(eval('(' + jsondata + ')'));
						$("#yxz2").ligerGetComboBoxManager().selectValue(yxzid);
					}
					else{
						liger.get("yxz2").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}

	    function setStationData(combinationid,oilid,areaid,proData,clearSelecteValue) {
			jQuery.ajax({
				type : 'post',
				url:'station_queryStationInfo.action',
				data: {"combinationid":combinationid,"areaid":areaid,"oilid":oilid,"selecteValue":clearSelecteValue,"views":'sation'},
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

	    function getBoilerInitData() {
			var oilText=[{ id: 1 , text: '' }];
			var areaText=[{ id: 1 , text: '' }];
			var stationText=[{ id: 1 , text: '' }];
			jQuery.ajax({
				type : 'post',
				url : 'station_cascadeInit.action',
				success : function(jsondata) {
				var dataObj = $.parseJSON(jsondata);
					$.each(dataObj, function(key,val){
						if (key == 'oiltext') {
							oilText = val;
						}
						if (key == 'areatext'){
							areaText = val;
						}
						if (key == 'stationtext'){
							stationText = val;
						}
					});
					setInitData(oilText,areaText,stationText);
				}
			});
		}
		
	    function setInitData(oilText,areaText,stationText) {
			liger.get("oilstationname").setData(oilText);
			liger.get("areablock").setData(areaText);
			liger.get("blockstationname").setData(stationText);
		}
       	 function fromAu(rowdata){
        	//用户选择修改数据
        							
        							blockstationid = rowdata.BLOCKSTATIONID;
        							org_id = rowdata.ORG_ID;
									blockstation_name = rowdata.BLOCKSTATION_NAME;
									gh_id = rowdata.GH_ID;
									yxz2 = rowdata.YXZ;
									yxzid = rowdata.YXZID;
									lhzid = rowdata.LHZID;
									qkid = rowdata.QKID;
									if (rowdata.QKMC != null && typeof(rowdata.QKMC)!="undefined"){
									 	qkmc = rowdata.QKMC;
									 }else{
									 	qkmc = "";
									 }
									
									bs_type = rowdata.BS_TYPE;
									typeid  =rowdata.TYPEID;
									oildrilling_name = rowdata.OILDRILLING_NAME;
									if (rowdata.BLOCKSTATION_CODE != null && typeof(rowdata.BLOCKSTATION_CODE)!="undefined"){
									 	blockstation_code = rowdata.BLOCKSTATION_CODE;
									 }else{
									 	blockstation_code = "";
									 }
									 if (rowdata.BOILER_QTY != null && typeof(rowdata.BOILER_QTY)!="undefined"){
									 	boiler_qty = rowdata.BOILER_QTY;
									 }else{
									 	boiler_qty = "";
									 }
									 if (rowdata.STEAM_REMARK != null && typeof(rowdata.STEAM_REMARK)!="undefined"){
									 	steam_remark = rowdata.STEAM_REMARK;
									 }else{
									 	steam_remark = "";
									 }
									if (rowdata.GHS != null && typeof(rowdata.GHS)!="undefined"){
									 	ghs = rowdata.GHS;
									 }else{
									 	ghs = "";
									 }
									if (rowdata.CYGS != null && typeof(rowdata.CYGS)!="undefined"){
									 	cygs = rowdata.CYGS;
									 }else{
									 	cygs = "";
									 }
									if (rowdata.CYGGG != null && typeof(rowdata.CYGGG)!="undefined"){
									 	cyggg = rowdata.CYGGG;
									 }else{
									 	cyggg = "";
									 }
									if (rowdata.ZYBS != null && typeof(rowdata.ZYBS)!="undefined"){
									 	zybs = rowdata.ZYBS;
									 }else{
									 	zybs = "";
									 }
									
									if (rowdata.JRBZ != null && typeof(rowdata.REMARK)!="undefined"){
									 	jrbz = rowdata.JRBZ;
									 }else{
									 	jrbz = "";
									 }
									if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
									 	remark = rowdata.REMARK; 
									 }else{
									 	remark = "";
									 }
								
									 if (rowdata.STATUS_VALUE != null && typeof(rowdata.STATUS_VALUE)!="undefined"){
									 	status_value = rowdata.STATUS_VALUE; 
									 }else{
									 	status_value = "";
									 }
				                	 if (rowdata.COMMISSIONING_DATE != null && typeof(rowdata.COMMISSIONING_DATE)!="undefined"){
									 	commissioning_date = rowdata.COMMISSIONING_DATE; 
									 }else{
									 	commissioning_date = "";
									 }
				                	 if (rowdata.DYEAR != null && typeof(rowdata.DYEAR)!="undefined"){
				                		 dyear = rowdata.DYEAR; 
										 }else{
											 dyear = "";
										 }
				                		//if (rowdata.SCRAPPED_DATE != null && typeof(rowdata.SCRAPPED_DATE)!="undefined"){
										//	scrapped_date = rowdata.SCRAPPED_DATE;
										// }else{
										//	 scrapped_date = "";
										// }
										jrbz = rowdata.JRBZ;
										if (rowdata.LJJDID != null && typeof(rowdata.LJJDID)!="undefined"){
										 	ljjd_s = rowdata.LJJDID;
										 }else{
										 	ljjd_s = "";
										 }				
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }
        
        //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			//alert(id);
			//alert(name);
			//alert(datatype);
			var oilstationname='全部';
			var areablock='';
			var blockstationname='';
			var ghname='';
			var combination='';
			$("#oilstationname").ligerGetComboBoxManager().setValue('');
			$("#combination").ligerGetComboBoxManager().setValue('');
			$("#areablock").ligerGetComboBoxManager().setValue('');
			$("#blockstationname").ligerGetComboBoxManager().setValue('');
			$("#blockstationtype").ligerGetComboBoxManager().setValue('');
			//$("#").ligerGetComboBoxManager().setValue('2');
			document.getElementById("jrbz1").value = "全部";
			getBoilerInitData();
			if(datatype=='4'){
				oilstationname=name;
				$("#oilstationname").val(name);
			}
			if(datatype=='14'){
				combination=name;
				$("#combination").val(name);
			}
			if(datatype=='7'){
				blockstationname=name;
				$("#blockstationname").val(name);
			}

			grid.setOptions({
        		parms: [{ name: 'oilstationname', value: $("#oilstationname").val()},
        		        { name: 'combination', value: $("#combination").val()},
        		        { name: 'areablock', value: $("#areablock").val()},
        		        { name: 'blockstationname', value: $("#blockstationname").val()},
        		        { name: 'blockstationtype', value: $("#blockstationtype").val()},
        		        { name: 'jrbz',value: $("#jrbz1").val()},
        		        {name: 'dyear1',value: $("#dyear1").val()}
				]
        	});
         	grid.loadData(true);
		}
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'oilstationname',
					value: $("#oilstationname").val()
				},{
					name: 'combination',
					value: $("#combination").val()
				},{
					name: 'areablock',
					value: $("#areablock").val()
				},{
					name: 'blockstationname',
					value: $("#blockstationname").val()
				},{name: 'dyear1',value: $("#dyear1").val()},{
					name: 'blockstationtype',
					value: $("#blockstationtype").val()
				},
				{ name: 'jrbz',value: $("#jrbz1").val()}
				]
        	});
         	grid.loadData(true);
        }
         var exportFlag=false;
    	 function onExport() {
    		     var Eoilationname=$("#oilstationname").val();
    	         var Eblockstationname=$("#blockstationtype").val();
    	         var EboilersName=$("#blockstationname").val();
    	         var Eareablock = $("#areablock").val();
    	         var Ejrbz1=$("#jrbz1").val();
    	         var Estatus_value =$("#combination").val();//注气单位	
    	         var dyear1=$("#dyear1").val();
    			if (exportFlag) {
    				Eoilationname=oilstationname;
    				Eblockstationname =blockstationtype;
    				EboilersName= blockstationname;
    				Eareablock = areablock;
    				Ejrbz1=jrbz1;
    				Estatus_value=combination;
    			}
    			var totalNum = 0;
    			
    			var url = 'station_onExport.action?oilstationname='+encodeURIComponent(Eoilationname)+'&blockstationtype='+encodeURIComponent(Eblockstationname)+'&areablock='+encodeURIComponent(Eareablock)+'&jrbz1='+encodeURIComponent(Ejrbz1)+"&dyear1="+encodeURIComponent(dyear1)+'&combination='+encodeURIComponent(Estatus_value)+'&totalNum=export'+'&blockstationname='+encodeURIComponent(EboilersName);
    			var urls = 'station_onExport.action?oilstationname='+encodeURIComponent(Eoilationname)+'&blockstationtype='+encodeURIComponent(Eblockstationname)+'&areablock='+encodeURIComponent(Eareablock)+'&jrbz1='+encodeURIComponent(Ejrbz1)+"&dyear1="+encodeURIComponent(dyear1)+'&combination='+encodeURIComponent(Estatus_value)+'&totalNum=totalNum'+'&blockstationname='+encodeURIComponent(EboilersName);
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
      function assignM(flg){
      		
			 if(flg == 2){
			 		document.getElementById("blockstationid").value = blockstationid;
	               	document.getElementById("blockstation_name").value = blockstation_name;
	               	$("#qkmc").ligerGetComboBoxManager().selectValue(qkid);
               		document.getElementById("blockstation_code").value= blockstation_code;
               		document.getElementById("boiler_qty").value= boiler_qty;
               		document.getElementById("steam_remark").value= steam_remark;
	               	document.getElementById("ghs").value= ghs;
	               	document.getElementById("cygs").value= cygs;
	               	document.getElementById("cyggg").value= cyggg;
	               	document.getElementById("zybs").value= zybs;
	               	$("#yxz2").ligerGetComboBoxManager().selectValue(yxzid);
	               	if (gh_id != '') {
	               		$("#oildrilling_name").ligerGetComboBoxManager().selectValue(gh_id);//caiyouzhan
					}
	               	//else {
	               //		$("#oildrilling_name").ligerGetComboBoxManager().selectValue(lhzid);//联合站
	              // 	}
	            
	               	document.getElementById("remark").value= remark;
	               	if (status_value != null && typeof(status_value)!="undefined" && status_value != '') {
						$("#status_value").ligerGetComboBoxManager().selectValue(status_value);
					}
					else {
						document.getElementById("status_value").value= status_value;
					}
	               	document.getElementById("commissioning_date").value= commissioning_date;
	               	//document.getElementById("dyear").value= dyear;
	               	$("#dyear").ligerGetDateEditorManager().setValue(dyear);
	      			$("#ljjd_s").ligerGetComboBoxManager().selectValue(ljjd_s);
	      			var ac = $("#jrbz").ligerGetComboBoxManager().findValueByText(jrbz);
	      			$("#jrbz").ligerGetComboBoxManager().selectValue(ac);
	            
	               
	               	document.getElementById("bs_type").value= bs_type;
			 }else if(flg == 1){
			 		document.getElementById("blockstationid").value = "";
	               	document.getElementById("blockstation_name").value = "";
	               	$("#qkmc").ligerGetComboBoxManager().selectValue("");
	               	$("#oildrilling_name").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("ghs").value="";
	               	document.getElementById("cygs").value= "";
	               	document.getElementById("cyggg").value= "";
	               	document.getElementById("zybs").value= "";
	               	document.getElementById("blockstation_code").value= "";
               		document.getElementById("boiler_qty").value= "";
               		document.getElementById("steam_remark").value= "";
	               	$("#ljjd_s").ligerGetComboBoxManager().selectValue("");
	               	$("#jrbz").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("remark").value= "";
	               	$("#status_value").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("status_value").value= "";
	               //	$("#commissioning_date").ligerGetComboBoxManager().selectValue("");
	            	document.getElementById("commissioning_date").value= "";
	               	$("#dyear").ligerGetDateEditorManager().setValue("");
	               	$("#bs_type").ligerGetComboBoxManager().selectValue("");
	            	$("#yxz2").ligerGetComboBoxManager().selectValue("");
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
              	    if(operate != 1 && operate != 2){
              	    	setpage1(0); //显示编辑界面
						setItemsd(0);//0-显示编辑区，添加隐藏按钮
					  }
              	  
              	   operate = 1;
              	   assignM(1);
              	   
              	   
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改信息的数据！');
              	    
              	     }else if(rows.length == 1){
							 if(operate != 1 && operate != 2){
								 setpage1(0); //显示编辑界面
								setItemsd(0);//0-显示编辑区，添加隐藏按钮
							  }
		              	   operate = 2;
		              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改信息的数据！');
              	     }
                  break;
              case "delete":
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要删除的数据！')
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'station_removeStation.action',
											async : false,
											data: {"stationId":blockstationid,"org_id":org_id},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else{
														
														onSubmit();
														assignM(1);
														$.ligerDialog.success('注转战基础删除成功！');
													}
														
													}
												},
											error : function(data) {
										
											}
										});
		                        	 } 
              	     		 });
							
							

              	     }else{
              	     	alert("请选择一条你要删除的数据！");
              	     }
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
						<td align="right" class="l-table-edit-td" style="width:100px">采油站：</td>
		                <td align="right" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname" name = "oilstationname"tyle="width:60px" />
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:80px">区块：</td>
		                <td align="right" class="l-table-edit-td" style="width:80px"  >
		                	<input type="text" id="areablock" name = "areablock"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">注转站：</td>
		                <td align="right" class="l-table-edit-td" style="width:60px">
		                	<input type="text" id="blockstationname" name = "blockstationname" style="width:60px"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">注汽单位：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="combination" name = "combination" />
		                </td>
		                </tr>
		                <tr>
		              	<td align="right" class="l-table-edit-td" style="width:100px">注转站类型：</td>
		                <td align="right" class="l-table-edit-td" >
		                	<input type="text" id="blockstationtype" name = "blockstationtype" style="width:100px"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">接入状态：</td>
		                <td align="right" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="jrbz1" name="jrbz1"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px;">设计产能年:</td>
					<td align="right" class="l-table-edit-td" style="width:60px;">
						<input name="dyear1" type="text" id="dyear1" ltype="number"  validate="{minlength:1,maxlength:4}"  />
					</td>
		                <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td"  >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:20px"></td>
		                <td align="right" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a>
		                </td>
					</tr>
				
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" >
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">注转站名称:</td>
		              	<td align="left" class="l-table-edit-td" style="width:150px">注转站编码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">注转站类型:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属区块:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属采油站:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">建设生产状态:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">投产日期:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">设计产能年:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">管汇数量:</td>
		                </tr>
		                <tr>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="blockstation_name" type="text" id="blockstation_name"  ltype="text" validate="{required:true}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="blockstation_code" type="text" id="blockstation_code"  ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="bs_type" type="text" id="bs_type" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="qkmc" type="text" id="qkmc" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="oildrilling_name" type="text" id="oildrilling_name" ltype="text" />
		                </td>
		               <td align="left" class="l-table-edit-td" >
		                	<input name="status_value" type="text" id="status_value"  ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="commissioning_date" type="text" id="commissioning_date" ltype="date"/>
		                </td>
		               <td align="left" class="l-table-edit-td" >
		                	<input name="dyear" type="text" id="dyear" ltype="number"  validate="{minlength:1,maxlength:4}" />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="ghs" type="text" id="ghs" validate="{required:true,minlength:1,maxlength:2}" ltype="text"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
		                </td>
		                 
		                </tr>
		                <tr>
		               
		               <td align="left" class="l-table-edit-td" >储油罐数量:</td>
		                
		                <td align="left" class="l-table-edit-td" >储油罐规格:</td>
		                
		                 <td align="left" class="l-table-edit-td" >转油泵数量:</td>
		                 
		                  <td align="left" class="l-table-edit-td" style="width:150px">注汽单位:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">锅炉数量:</td>
		                  <td align="left" class="l-table-edit-td"  >注汽站备注:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">接入标志:</td>
		                   
		                <td align="left" class="l-table-edit-td" style="width:150px">服务器逻辑节点名:</td>
		               	
		                  <td align="left" class="l-table-edit-td"  >备注:</td>
		               </tr>
		            <tr>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="cygs" type="text" id="cygs"  validate="{required:true,minlength:1,maxlength:2}" ltype="text"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="cyggg" type="text" id="cyggg" ltype="text" />
		                	</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="zybs" type="text" id="zybs"  validate="{required:true,minlength:1,maxlength:2}" ltype="text"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
		                </td>
		                 <td align="left" class="l-table-edit-td" style="">
		                	<input name="yxz2" type="text" id="yxz2"  ltype="text"  />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="boiler_qty" type="text" id="boiler_qty"  validate="{required:true,minlength:1,maxlength:2}" ltype="text"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="steam_remark" type="text" id="steam_remark" ltype="text"  />
		                </td>
		                 <td align="left" class="l-table-edit-td" style="">
		                	<input name="jrbz" type="text" id="jrbz" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ljjd_s" type="text" id="ljjd_s" ltype="text"  />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text"  />
		                </td>
		            </tr>
		            <tr>
		              
		             
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	
		                	<!-- <input name="audbshor_date" type="text" id="audbshor_date" ltype="text" /> -->
		               
		                	<input name="blockstationid" type="hidden"  id="blockstationid" />
		               
							<input name="org_id" type="hidden" id="org_id" />
						
		                	<!--  <input type="submit" value="提交" id="Button1" class="l-button" style="width:100px" /> -->
		                </td>
		                <td align="left">
		                </td>
		            </tr>
		            <tr>
		            	<td>
		            		<br/>
		            	</td>
		            </tr>
		            
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>