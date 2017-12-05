<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>稀油油井基础信息管理</title>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
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
        var pipesink; //管汇
        var blockstationname ;
        //鼠标选择数据
		var wellid ; 	//稀油油井ID
		var well_name;		//稀油井号
		var wellCode;		//稀油编码
		var bewell_name;	//曾用井号
		var commissioning_date	//投产日期
		var scrapped_date;	 	//报废日期
		var jrbz;
		var ljjd_s;
	//	var dyear			//设计产能年
		var valve_coding	//多通阀编码（1、2）
		var channel_no		//通道号（1-12）
		var status_value	//建设生产状态
		var org_id			//组织结构ID
		var remark			//备注
		var zzz_id;
		var gh_id;
		var clearSelecteValue=0;
		var wellAreaSelf; //所属井区
		var serverid;
		var jqkid;
		var output_coefficient;
		var stratTIME;
		var endTIME;
		var flag;
		var dyear;
		var interval;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'thinoil_pageInit.action',
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
			$("#oilstationname1").ligerComboBox({
				url:'rulewell_queryOilSationInfo.action?arg=thinoil',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect:function (data){
					liger.get("rulewellname1").setValue('');
					liger.get("blockstationname1").setValue('');
					/* if(liger.get("areablock").getValue() != ''){
                		liger.get("areablock").setValue('');
                	} */
    			},
				onSelected:function (data){
					clearSelecteValue=1;
					//liger.get("	").setValue('');
					if ($("#oilationid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
						//setAreablockData($("#oilationid").val(),proData);
						setStationData('',proData,$("#oilationid").val(),clearSelecteValue);
					}
					else {
						getWellInitData();
					}
				}
			});
			$("#areablock").ligerComboBox({
				url:'rulewell_queryAreablockInfo.action?orgid=thinoil',
				valueField: 'id',
				valueFieldID: 'areablockid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onBeforeSelect:function (data){
					/* liger.get("rulewellname1").setValue('');
					liger.get("blockstationname1").setValue('');
					liger.get("oilstationname1").setValue(''); */
    			},
				onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						
						//clearSelecteValue = 2;
						//var se = setStationData($("#areablockid").val(),proData,$("#oilationid").val(),clearSelecteValue);
					}
				}
			});
			$("#blockstationname1").ligerComboBox({
				url:'rulewell_queryStationInfo.action?oilid=thinoil',
	            valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onBeforeSelect:function (data){
					liger.get("rulewellname1").setValue('');
					/* if(liger.get("areablock").getValue() != ''){
                		liger.get("areablock").setValue('');
                	} */
    			},
	            onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						setWellData($("#stationid").val(),proData);
					}
				}
			});
			$("#rulewellname1").ligerComboBox({
				url:'thinoil_queryWellInfo.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
	            onBeforeSelect:function (data){
					/* if(liger.get("areablock").getValue() != ''){
                		liger.get("areablock").setValue('');
                	} */
    			},
				onSelected:function (data){
				}
			});
			$("#blockstationname").ligerComboBox({
				url:'rulewell_queryStationInfo.action?oilid=noall',
				valueField: 'id',
				valueFieldID: 'stationsid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				liger.get("manifold").setValue('');
					if (data != null && typeof(data)!="undefined" && data != ''){
						setGhData($("#stationsid").val(),proData);
					}
				}
			});
			$("#manifold").ligerComboBox({
    			url:"manifoldBasicInfo_queryManifoldNameInfo.action?ghorg=all",
    			valueField: 'id',
    			valueFieldID: 'wellnumsid',
    			textField: 'text',
    			selectBoxHeight:300,
    			selectBoxWidth:140,
    			hideOnLoseFocus:true,
                autocomplete:true,
    			onSelected:function (data){
    			}
    		});
			$("#BRANCHING").ligerComboBox({
				url:'branchingBasicInfo_queryBranchingNameInfo.action',
    			valueField: 'id',
    			valueFieldID: 'BRANCHINGID',
    			textField: 'text',
    			selectBoxHeight:300,
    			selectBoxWidth:140,
    			hideOnLoseFocus:true,
                autocomplete:true,
    			onSelected:function (data){
    			}
    		});
			$("#wellAreaSelf").ligerComboBox({
    			url:"rulewell_querywellAreaSelf.action",
    			valueField: 'id',
    			valueFieldID: 'wellAreaSelfid',
    			textField: 'text',
    			selectBoxHeight:200,
    			selectBoxWidth:98,
    			hideOnLoseFocus:true,
                autocomplete:true,
    			onSelected:function (data){
    			}
    		});
			// $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
             $("#commissioning_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
             $("#stratTIME").ligerDateEditor({
            	  format: "yyyy-MM-dd hh:mm",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime : true,
                    cancelable : false
                 });
             $("#flag").ligerComboBox({
	     			valueField: 'id',
	     			valueFieldID: 'hidjmbzid',
	     			textField: 'text',
	     			selectBoxHeight:200,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true,
	     			data: [ { text: '未加密', id: '0' }, { text: '已加密', id: '1' } ]
	     		});
             $("#endTIME").ligerDateEditor(
		                {
		                    format: "yyyy-MM-dd hh:mm",
		                    labelWidth: 120,
		                    labelAlign: 'center',
		                    showTime: true,
		                    cancelable : false
		      });
			  $("#jrbz").ligerComboBox({
				  url:'comboboxdata_searchSwitchInflg.action', 
	     			valueField: 'id',
	     			valueFieldID: 'jrbzid',
	     			textField: 'text',
	     			selectBoxHeight:150,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true
		     		});
	            //搜索条件
	            document.getElementById("jrbz1").value="全部";
			  $("#jrbz1").ligerComboBox({
				  url:'comboboxdata_searchSwitchInflg.action?args=ALL', 
	     			valueField: 'id',
	     			valueFieldID: 'jrbz1id',
	     			textField: 'text',
	     			selectBoxHeight:150,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true
		     		});
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
              	   	var wellid  = $("#wellid").val();
					var well_name  = $("#well_name").val();
					var wellCode = $("#wellCode").val();
					var bewell_name  = $("#bewell_name").val();
					var commissioning_date  = $("#commissioning_date").val();
					var scrapped_date = "";
					//$("scrapped_date").val();
					var wellAreaSelf="";
					if($("#wellAreaSelf").val() !=''){
						wellAreaSelf = $("#wellAreaSelfid").val();
						}
					var jrbz="";
					if($("#jrbz").val() != ''){
						jrbz = $("#jrbzid").val();
					}
					var ljjd_s  = $("#ljjd_s").val();
					if(ljjd_s != ''){
						ljjd_s =  $("#hidserverid").val() ;
					}
					var valve_coding  = $("#valve_coding").val();
					var channel_no  = $("#channel_no").val();
					var status_value  = $("#status_value").val();
					var blockstationname = $("#blockstationname").val();
					var org_id  = $("#org_id").val();
					var remark  = $("#remark").val();
					var  output_coefficient = $("#output_coefficient").val();
					var stationsid ="";
					if(blockstationname != ''){
						stationsid = $("#stationsid").val();
					}
					var manifold  = "";
					if($("#manifold").val() != ''){
						manifold  = $("#wellnumsid").val();
					}

					var gh_id1 = $("#gh_id1").val();
					var datatype = $("#datatype").val();
					var stratTIME = $("#stratTIME").val();
					var endTIME = $("#endTIME").val();
					var flag ='';
					if( $("#flag").val()!=''){
						flag = $("#hidjmbzid").val();
						}
					var dyear = $("#dyear").val();
					var interval = $("#interval").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		
              	   	   jQuery.ajax({
									type : 'post',
									url : 'thinoil_addThinoil.action',
									async : false,
									data: {"org_id":org_id,"well_name":well_name ,"wellCode":wellCode,"bewell_name":bewell_name ,"wellAreaSelf":wellAreaSelf,
											"commissioning_date":commissioning_date,"scrapped_date":scrapped_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"valve_coding":valve_coding,"channel_no":channel_no,
											"status_value":status_value,"blockstationname":stationsid,"pipesink":manifold,"remark":remark,"output_coefficient":output_coefficient,
											"dyear":dyear,"flag":flag,"stratTIME":stratTIME,"endTIME":endTIME,"interval":interval},
									success : function(data) { 
									//$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (data != null && typeof(data)!="undefined"){
										var outData = eval('(' + data + ')');
										if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
											$.ligerDialog.error(outData.ERRMSG);
										}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
											$.ligerDialog.error(outerror(outData.ERRCODE));
										}else{
											if(datatype == 8){
												onJKSubmit(gh_id1,'',datatype,'');
											}else{
												onSubmit();
											}
											setpage1(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('稀油油井添加成功！');
											operate = 0;
										}
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   //2-修改
              	   }else if(operate == 2){
              	   		 jQuery.ajax({
									type : 'post',
									url : 'thinoil_updateThinoil.action',
									async : false,
									data: {"org_id":org_id,"wellid":wellid,"well_name":well_name ,"wellCode":wellCode,"bewell_name":bewell_name ,"wellAreaSelf":wellAreaSelf,
											"commissioning_date":commissioning_date,"scrapped_date":scrapped_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"valve_coding":valve_coding,"channel_no":channel_no,
											"status_value":status_value,"blockstationname":stationsid,"pipesink":manifold,"remark":remark,"output_coefficient":output_coefficient,
											"dyear":dyear,"flag":flag,"stratTIME":stratTIME,"endTIME":endTIME,"interval":interval},
									success : function(data) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (data != null && typeof(data)!="undefined"){
										var outData = eval('(' + data + ')');
										if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
											$.ligerDialog.error(outData.ERRMSG);
										}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
											$.ligerDialog.error(outerror(outData.ERRCODE));
										}else{
											if(datatype == 8){
												onJKSubmit(gh_id1,'',datatype,'');
											}else{
												onSubmit();
											}
											setpage1(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('稀油油井修改成功！');
											operate = 0;
										}
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   }
		          // operate = 0;
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
        							zzz_id = rowdata.ZZZ_ID;
        							gh_id = rowdata.GH_ID;
        							wellid = rowdata.WELLID;
									well_name = rowdata.WELL_NAME;
									//wellCode = rowdata.WELLCODE;
									if (rowdata.LJJDID != null && typeof(rowdata.LJJDID)!="undefined"){
										serverid = rowdata.LJJDID;
									 }else{
										 serverid = "";
									 }
									 if (rowdata.QKID != null && typeof(rowdata.QKID)!="undefined"){
										jqkid = rowdata.QKID;
									 }else{
										 jqkid = "";
									 }
									 
									if (rowdata.MANIFOLD != null && typeof(rowdata.MANIFOLD)!="undefined"){
										manifold = rowdata.MANIFOLD;
									 }else{
										 manifold = "";
									 }
									if (rowdata.WELL_COLE != null && typeof(rowdata.WELL_COLE)!="undefined"){
										wellCode = rowdata.WELL_COLE;
										
									 }else{
										 wellCode = "";
									 }
									if (rowdata.BEWELL_NAME != null && typeof(rowdata.BEWELL_NAME)!="undefined"){
									 	bewell_name = rowdata.BEWELL_NAME;
									 }else{
									 	bewell_name = "";
									 }
									
									if (rowdata.COMMISSIONING_DATE != null && typeof(rowdata.COMMISSIONING_DATE)!="undefined"){
									 	commissioning_date = rowdata.COMMISSIONING_DATE;
									 }else{
									 	commissioning_date = "";
									 }
									
									jrbz = rowdata.JRBZ;
									output_coefficient = rowdata.OUTPUT_COEFFICIENT;
									if (rowdata.LJJD_S != null && typeof(rowdata.LJJD_S)!="undefined"){
									 	ljjd_s = rowdata.LJJD_S;
									 }else{
									 	ljjd_s = "";
									 }
									if (rowdata.DYEAR != null && typeof(rowdata.DYEAR)!="undefined"){
									 	dyear = rowdata.DYEAR;
									 }else{
									 	dyear = "";
									 }
									if (rowdata.INCREASE_FREQ_FLAG != null && typeof(rowdata.INCREASE_FREQ_FLAG)!="undefined"){
										flag = rowdata.INCREASE_FREQ_FLAG;
									 }else{
										 flag = "";
									 }
									if (rowdata.START_INCREASE_FREQ_TIME != null && typeof(rowdata.START_INCREASE_FREQ_TIME)!="undefined"){
										stratTIME = rowdata.START_INCREASE_FREQ_TIME;
									 }else{
										 stratTIME = "";
									 }
									if (rowdata.END_INCREASE_FREQ_TIME!= null && typeof(rowdata.END_INCREASE_FREQ_TIME)!="undefined"){
										endTIME = rowdata.END_INCREASE_FREQ_TIME;
									 }else{
										 endTIME = "";
									 }
									 if (rowdata.INCREASE_INTERVAL!= null && typeof(rowdata.INCREASE_INTERVAL)!="undefined"){
										 interval = rowdata.INCREASE_INTERVAL;
										 }else{
											 interval = "";
										 }
									if (rowdata.VALVE_CODING != null && typeof(rowdata.VALVE_CODING)!="undefined"){
									 	valve_coding = rowdata.VALVE_CODING;
									 }else{
									 	valve_coding = "";
									 }
									if (rowdata.CHANNEL_NO != null && typeof(rowdata.CHANNEL_NO)!="undefined"){
									 	channel_no = rowdata.CHANNEL_NO;
									 }else{
									 	channel_no = "";
									 }
									
									if (rowdata.STATUS_VALUE != null && typeof(rowdata.STATUS_VALUE)!="undefined"){
									 	status_value = rowdata.STATUS_VALUE;
									 }else{
									 	status_value = "";
									 }
									 
									if (rowdata.BLOCKSTATIONNAME != null && typeof(rowdata.BLOCKSTATIONNAME)!="undefined"){
									 	blockstationname = rowdata.BLOCKSTATIONNAME; 
									 }else{
									 	blockstationname = "";
									 }
									 
									 org_id = rowdata.ORG_ID;
									 
									 if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
									 	remark = rowdata.REMARK; 
									 }else{
									 	remark = "";
									 }
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }

       	function setAreablockData(data,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'rulewell_queryAreablockInfo.action',
    			data: {"orgid":data},
    			
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("areablock1").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("areablock1").setData(proData);
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
    			url:'rulewell_queryStationInfo.action',
    			data: {"areaid":areaid,"oilid":oilid,"selecteValue":clearSelecteValue},
    			
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("blockstationname1").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("blockstationname1").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				alert("请求数据失败，请重新查询");
    			}
    		});
    		return clearSelecteValue;
    	}

        function setWellData(data,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'thinoil_queryWellInfo.action',
    			data: {"orgid":data},
    			
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("rulewellname1").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("rulewellname1").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				alert("请求数据失败，请重新查询");
    			}
    		});
    	}
        function setGhData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'manifoldBasicInfo_queryManifoldNameInfo.action',
				data: {"orgid":data,"ghorg":'orgid'},
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("manifold").setData(eval('(' + jsondata + ')'));
						if (gh_id != '') {
							$("#manifold").ligerGetComboBoxManager().selectValue(gh_id);
						}
						gh_id = '';
					}
					else{
						liger.get("manifold").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}
    	
        function getWellInitData() {
        	$("#areablock").ligerGetComboBoxManager().setValue('');
			$("#blockstationname1").ligerGetComboBoxManager().setValue('');
			$("#rulewellname1").ligerGetComboBoxManager().setValue('');
    		var oilText=[{ id: 1 , text: '' }];
    		var areaText=[{ id: 1 , text: '' }];
    		var stationText=[{ id: 1 , text: '' }];
    		var wellText=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'thinoil_cascadeInit.action',
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
    					if (key == 'welltext'){
    						wellText = val;
    					}
    				});
    				setInitData(oilText,areaText,stationText,wellText);
    			}
    		});
    	}
    	
        function setInitData(oilText,areaText,stationText,wellText) {
    		liger.get("oilstationname1").setData(oilText);
    		liger.get("areablock").setData(areaText);
    		liger.get("blockstationname1").setData(stationText);
    		liger.get("rulewellname1").setData(wellText);
    	}
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){
			var oilstationname="全部";
			var areablock='';
			var blockstationname='';
			var ghname='';
			var wellnum='';
			$("#oilstationname1").ligerGetComboBoxManager().setValue('');
		    document.getElementById("jrbz1").value= "全部";
			document.getElementById("dyearC").value="";
			getWellInitData();
			
			if(datatype=='4'){
				oilstationname=name;
				$("#oilstationname1").val(name);
				document.getElementById("datatype").value="";
				document.getElementById("gh_id1").value="";
			}
			if(datatype=='6'){
				areablock=name;
				document.getElementById("datatype").value="";
				document.getElementById("gh_id1").value="";
			}
			if(datatype=='7'){
				blockstationname=name;
				$("#blockstationname1").val(name);
				document.getElementById("datatype").value="";
				document.getElementById("gh_id1").value="";
			}
			if(datatype=='8'){
				ghname=id;
				$("#gh_id1").val(id);
				$("#datatype").val(datatype);
			}
			if(datatype=='9'){
				wellnum=name;
				$("#rulewellname1").val(name);
				document.getElementById("datatype").value="";
				document.getElementById("gh_id1").value="";
			}
			
			grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: oilstationname
				},{
					name: 'areablock1',
					value: areablock
				},{
					name: 'blockstationname1',
					value: blockstationname
				},{
					name: 'ghname',
					value: ghname
				}
				,{
					name: 'rulewellname1',
					value: wellnum
				}
				]
        	});
         	grid.loadData(true);
		}
		
         function onSubmit()
        {
        	 document.getElementById("datatype").value="";
			document.getElementById("gh_id1").value="";
        	grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: $("#oilstationname1").val()
				},{
					name: 'areablock1',
					value: $("#areablock").val()
				},{
					name: 'blockstationname1',
					value: $("#blockstationname1").val()
				},{
					name: 'rulewellname1',
					value: $("#rulewellname1").val()
				},{name :'jrbz1',
					value:$("#jrbz1").val()
					},{
						name:'dyearC',
						value:$("#dyearC").val()
						}
				]
        	});
         	grid.loadData(true);
        }
         var exportFlag=false;
    	 function onExport() {
    		 //oilationname  blockstationname  boilersName  jrbz1
    		     var Eoilationname=$("#oilstationname1").val();
     	         var Eblockstationname=$("#blockstationname1").val();
     	         var EboilersName=$("#rulewellname1").val();
     	         var Eareablock = $("#areablock").val();
     	         var Ejrbz1=$("#jrbz1").val();	
     	         var EdyearC = $("#dyearC").val();

    			if (exportFlag) {
    				Eoilationname=oilstationname1;
    				Eblockstationname = blockstationname1
    				EboilersName= rulewellname1;
    				Eareablock = areablock
    				Ejrbz1=jrbz1;
    				EdyearC=dyearC;
    			}
    			var totalNum = 0;
    			
    			var url = 'thinoil_onExport.action?oilstationname1='+encodeURIComponent(Eoilationname)+'&blockstationname1='+encodeURIComponent(Eblockstationname)+'&areablock='+encodeURIComponent(Eareablock)+'&jrbz1='+encodeURIComponent(Ejrbz1)+'&dyearC='+encodeURIComponent(EdyearC)+'&totalNum=export'+'&rulewellname1='+encodeURIComponent(EboilersName);
    			var urls = 'thinoil_onExport.action?oilstationname1='+encodeURIComponent(Eoilationname)+'&blockstationname1='+encodeURIComponent(Eblockstationname)+'&areablock='+encodeURIComponent(Eareablock)+'&jrbz1='+encodeURIComponent(Ejrbz1)+'&dyearC='+encodeURIComponent(EdyearC)+'&totalNum=totalNum'+'&rulewellname1='+encodeURIComponent(EboilersName);
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
					$("#wellAreaSelf").ligerGetComboBoxManager().selectValue(jqkid);
			 		document.getElementById("wellid").value = wellid;
	               	document.getElementById("well_name").value = well_name;
	               	document.getElementById("wellCode").value = wellCode;
	               	document.getElementById("bewell_name").value= bewell_name;
	               	document.getElementById("commissioning_date").value= commissioning_date;
	            	//document.getElementById("scrapped_date").value= scrapped_date;
	            	
	      			$("#ljjd_s").ligerGetComboBoxManager().selectValue(serverid);
	              
	               	var ac = $("#jrbz").ligerGetComboBoxManager().findValueByText(jrbz);
	             	$("#jrbz").ligerGetComboBoxManager().selectValue(ac);
	               	
	               	document.getElementById("valve_coding").value= valve_coding;
	               	document.getElementById("channel_no").value= channel_no;
	               //	document.getElementById("status_value").value= status_value;
	               	//document.getElementById("blockstationname").value= blockstationname;
	               	$("#blockstationname").ligerGetComboBoxManager().selectValue(zzz_id);
	               	$("#manifold").ligerGetComboBoxManager().selectValue(gh_id);
	               	if (status_value != null && typeof(status_value)!="undefined" && status_value != '') {
						$("#status_value").ligerGetComboBoxManager().selectValue(status_value);
					}else {
						$("#status_value").ligerGetComboBoxManager().selectValue("");
						document.getElementById("status_value").value= "";
					}
	               	document.getElementById("org_id").value= org_id;
	               	document.getElementById("remark").value= remark;
	               	document.getElementById("output_coefficient").value= output_coefficient;
	              	document.getElementById("dyear").value= dyear;
	             	//document.getElementById("flag").value= flag;
	             	
	             	var bc= $("#flag").ligerGetComboBoxManager().findValueByText(flag);
	             	$("#flag").ligerGetComboBoxManager().selectValue(bc);
	             	document.getElementById("stratTIME").value= stratTIME;
	             	document.getElementById("endTIME").value= endTIME;
	             	document.getElementById("interval").value= interval;
	             	
			 }else if(flg == 1){
				 $("#wellAreaSelf").ligerGetComboBoxManager().selectValue("");
			 		document.getElementById("wellid").value = "";
	               	document.getElementById("well_name").value = "";
	               	document.getElementById("wellCode").value = "";
	               	document.getElementById("bewell_name").value= "";
	               	document.getElementById("commissioning_date").value="";
	            	//document.getElementById("scrapped_date").value= "";
	               	$("#ljjd_s").ligerGetComboBoxManager().selectValue("");
	               	$("#jrbz").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("valve_coding").value= "";
	               	document.getElementById("channel_no").value= "";
	               	$("#status_value").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("status_value").value= "";
	               //	document.getElementById("blockstationname").value= "";
	               	$("#blockstationname").ligerGetComboBoxManager().selectValue("");
	            	$("#manifold").ligerGetComboBoxManager().selectValue("");
	               //	document.getElementById("manifold").value= "";
	               	document.getElementById("org_id").value= "";
	               	document.getElementById("remark").value= "";
	               	document.getElementById("output_coefficient").value= "";
	               	document.getElementById("dyear").value= "";
	             	document.getElementById("flag").value= "";
	             	document.getElementById("stratTIME").value= "";
	             	document.getElementById("endTIME").value= "";
	              	document.getElementById("interval").value= "";
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
											url : 'thinoil_removeThinoil.action',
											async : false,
											data: {"wellid":wellid,"org_id":org_id},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else{
														
														var datatype = $("#datatype").val();
														var gh_id1 = $("#gh_id1").val();
														if(datatype == 8){
															onJKSubmit(gh_id1,'',datatype,'');
														}else{
															onSubmit();
														}
														assignM(1);
														$.ligerDialog.success('稀油油井基础信息删除成功！');
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
	    	<input type="hidden" id="gh_id1"  name="gh_id1"/>
	    	<input type="hidden" id="datatype" name="datatype"/>
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
						<td align="right" class="l-table-edit-td" style="width:50px;display:none">区块：</td>
		                <td align="left" class="l-table-edit-td" style="width:50px;display:none">
		                	<input type="text" id="areablock1" name = "areablock1"/>
		                </td>
						
						<td align="right" class="l-table-edit-td" style="width:50px">采油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname1" name = "oilstationname1" />
		                </td>
		                <td align="left">
		                </td>
		               
		                <td align="left">
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">注转站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="blockstationname1" name = "blockstationname1"/>
		                </td>
		                <td align="left"></td>
		               
		                <td align="right" class="l-table-edit-td" style="width:40px">井名：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="rulewellname1" name = "rulewellname1"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px">井区块：</td>
						<td align="left" class="l-table-edit-td" style="width:60px">
							<input type="text" id="areablock" name = "areablock"/>	
						</td>
						  <td align="right" class="l-table-edit-td" style="width:80px">接入标志：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="jrbz1" name="jrbz1"/>
	                </td>
	                  <td align="right" class="l-table-edit-td" style="width:80px">设计产能年：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="dyearC" name="dyearC"/>
	                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:40px"></td>
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
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="" style="width:100%;height: 100px; display:none;">
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">稀油井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井区块:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属注转站:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">管汇点:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">建设生产状态:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">投产日期:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">产能设计年:</td>
		                
		               
		                
		              
		                
		                <tr/>
		                <tr>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="well_name" type="text" id="well_name"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="wellAreaSelf" type="text" id="wellAreaSelf" ltype="text" validate="{required:true }" />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="blockstationname" type="text" id="blockstationname"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="manifold" type="text" id="manifold"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="status_value" type="text" id="status_value" ltype="text" />
		                </td>
		            	 
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="commissioning_date" type="text" id="commissioning_date" ltype="date"  />
		                </td>
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="dyear" type="text" id="dyear" ltype="text" validate="{minlength:0,maxlength:4}" />
		                </td>
		               
		              
		                
		              
		                </tr>
		                <tr>
		                  <td align="left" class="l-table-edit-td" style="width:150px">稀油井编码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">曾用井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">加密标志:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">加密起始时间:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">加密结束时间:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">加密间隔:</td>
		               	<td align="left" class="l-table-edit-td" style="width:150px">接入标志:</td>
		            </tr>
		            <tr>
		             
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="wellCode" type="text" id="wellCode" ltype="text" validate="{minlength:0,maxlength:20}" />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="bewell_name" type="text" id="bewell_name" ltype="text"   />
		                </td>
		               
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="flag" type="text" id="flag" ltype="text"  />
		                </td>
		            
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="stratTIME" type="text" id="stratTIME" ltype="date" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="endTIME" type="text" id="endTIME" ltype="date"  />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="interval" type="text" id="interval" validate="{minlength:0, maxlength:3}"  />
		                </td>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="jrbz" type="text" id="jrbz" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		               </tr>
		                <tr>
		                        <td align="left" class="l-table-edit-td" style="width:150px">服务器逻辑节点名:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">多通阀编码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">通道号:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">分产系数:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		                 </tr>
		                 <tr>
		                 	<td align="left" class="l-table-edit-td" >
		                	<input name="ljjd_s" type="text" id="ljjd_s" ltype="text"  />
		                </td>
		            
		                <td align="left" class="l-table-edit-td" >
		                	<input name="valve_coding" type="text" id="valve_coding"   ltype="text"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="channel_no" type="text" id="channel_no"   ltype="text"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="output_coefficient" type="text" id="output_coefficient" ltype="number" validate="{number:true,min:0,max:9.999}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	
		                	<!-- <input name="audbshor_date" type="text" id="audbshor_date" ltype="text" /> -->
		              
		                	<input name="wellid" type="hidden" id="wellid" />
		               
		                	<input name="org_id" type="hidden" id="org_id" />
		              
		                	<!-- <input type="submit" value="提交" id="Button1" class="l-button" style="width:100px" />  -->
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