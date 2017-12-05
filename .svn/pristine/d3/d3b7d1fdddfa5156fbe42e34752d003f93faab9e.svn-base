<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>气井日报维护</title>
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
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        
        //鼠标选择数据
		var welName;
		var reportDate;
		var tubingPres;
		var wellTemp;
		var delivery;
		var discharge;
		var daliyFlow;
		var exportFlag = false;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'gasRPDWH_pageInit.action',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(data);
							toolbar = grid.topbar.ligerGetToolBarManager();					
							if (toolbar != null && typeof(toolbar)!="undefined"){
								var toolteams = toolbar.options.items;
								//var authorityflg = 0;
								
					 	       	for(var i=0; i<toolteams.length ; i++){
									if(toolteams[i].id == 'importid'){
										//authorityflg = 1;
										//toolbar.removeItem("statementid"); 
										
										jQuery("#importidtable").css('display','block');
										jQuery("#importidfile").css('display','block');
										
									}
								}
							}
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
			
                $("#welName").ligerComboBox({
    				url:'gaswell_queryWellInfo.action',
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
                 $("#reportDate").ligerDateEditor( {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                   // showTime: true,
                    cancelable : false
            });
                 var proData = [{ id: 1 , text: '' }];
     			$("#oilstationname1").ligerComboBox({
     				url:'rulewell_queryOilSationInfo.action?arg=gas',
     				valueField: 'id',
     				valueFieldID: 'oilationid',
     				textField: 'text',
     				selectBoxHeight:350,
     				selectBoxWidth:100,
     				autocomplete:true,
     				hideOnLoseFocus:true,
     				onSelected:function (data){
     					clearSelecteValue=1;
     					if ($("#oilationid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
     						liger.get("rulewellname1").setValue('');
     						//liger.get("areablock1").setValue('');
     						liger.get("blockstationname1").setValue('');
     						//setAreablockData($("#oilationid").val(),proData);
     						setStationData('',proData,$("#oilationid").val(),clearSelecteValue);
     					}
     					else {
     						getWellInitData();
     					}
     				}
     			});
     			$("#areablock1").ligerComboBox({
     				url:'rulewell_queryAreablockInfo.action?orgid=gas',
     				valueField: 'id',
     				valueFieldID: 'areablockid',
     				textField: 'text',
     				selectBoxHeight:350,
     				selectBoxWidth:100,
     				autocomplete:true,
     				hideOnLoseFocus:true,
     				onSelected:function (data){
     					/*if (data != null && typeof(data)!="undefined" && data != ''){
     					 liger.get("blockstationname1").setValue('');
     					liger.get("rulewellname1").setValue('');
     					clearSelecteValue = 2;
     					var se = setStationData($("#areablockid").val(),proData,$("#oilationid").val(),clearSelecteValue);
     				} */
     				}
     			});
     			$("#blockstationname1").ligerComboBox({
     				url:'gaswell_queryStationInfo.action',
     	            valueField: 'id',
     				valueFieldID: 'stationid',
     				textField: 'text',
     				selectBoxHeight:400,
     				selectBoxWidth:140,
     	            width: 100,
     	            hideOnLoseFocus:true,
     	            autocomplete:true,
     	            onSelected:function (data){
     					liger.get("rulewellname1").setValue('');
     					if (data != null && typeof(data)!="undefined" && data != ''){
     						ghname='';
     						setWellData($("#stationid").val(),proData);
     					}
     				}
     			});
     			$("#rulewellname1").ligerComboBox({
     				url:'gaswell_queryWellInfo.action',
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

                         format: "yyyy-MM-dd",
                       //  label: '格式化日期',
                         labelWidth: 100,
                         labelAlign: 'center',
                        // showTime: true,
                         cancelable : false
                 });
                  $("#txtDate1").ligerDateEditor(
                     {

                         format: "yyyy-MM-dd",
                       //  label: '格式化日期',
                         labelWidth: 100,
                         labelAlign: 'center',
                         //showTime: true,
                         cancelable : false
                 });
                 $("#pageloading").hide();
     			$("#txtDate").ligerDateEditor({ labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(myDate().Format("yyyy-MM-dd"));
     			$("#txtDate1").ligerDateEditor({  labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(secondDate().Format("yyyy-MM-dd"));
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
                	gas_welldid = $("#gas_welldid").val();
                	welName = $("#welName").val();
                	reportDate = $("#reportDate").val();
                	tubingPres = $("#tubingPres").val();
                	wellTemp = $("#wellTemp").val();
                	delivery = $("#delivery").val();
                	discharge = $("#discharge").val();
                	daliyFlow = $("#daliyFlow").val();
                	
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'gasRPDWH_saveOrupdateGasRPDWH.action',
									async : false,
									data: {"welName":welName ,"reportDate":reportDate,"tubingPres":tubingPres,
              	   							"wellTemp":wellTemp ,"delivery":delivery,"discharge":discharge,"daliyFlow":daliyFlow},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('添加成功！');
											operate = 0;
										}else if(typeof(typeof(eval('(' + jsondata + ')').ERRMSG)) != "undefined"){
 											var outData = eval('(' + jsondata + ')');
 											$.ligerDialog.error(outData.ERRMSG);
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
									url : 'gasRPDWH_saveOrupdateGasRPDWH.action',
									async : false,
									data: {"gas_welldid":gas_welldid,"welName":welName ,"reportDate":reportDate,"tubingPres":tubingPres,
          	   							"wellTemp":wellTemp ,"delivery":delivery,"discharge":discharge,"daliyFlow":daliyFlow},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('修改成功！');
											operate = 0;
										}else if(typeof(typeof(eval('(' + jsondata + ')').ERRMSG)) != "undefined"){
 											var outData = eval('(' + jsondata + ')');
 											$.ligerDialog.error(outData.ERRMSG);
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
            $(".l-button-test").click(function () {
             	
            });
        });  
        
       	 function fromAu(rowdata){
	        	//用户选择修改数据
       		if (rowdata.GAS_WELLDID != null && typeof(rowdata.GAS_WELLDID)!="undefined"){
       			gas_welldid = rowdata.GAS_WELLDID;
			 }else{
				 gas_welldid = "";
			 }
       		if (rowdata.GAS_WELLID != null && typeof(rowdata.GAS_WELLID)!="undefined"){
       			gas_wellid = rowdata.GAS_WELLID;
			 }else{
				 gas_wellid = "";
			 }
	       		if (rowdata.well_name != null && typeof(rowdata.well_name)!="undefined"){
	       			welName = rowdata.well_name;
				 }else{
					 welName = "";
				 }
	       		if (rowdata.REPORT_DATE != null && typeof(rowdata.REPORT_DATE)!="undefined"){
	       			reportDate = rowdata.REPORT_DATE;
				 }else{
					 reportDate = "";
				 }
	       		if (rowdata.TUBING_PRES != null && typeof(rowdata.TUBING_PRES)!="undefined"){
	       			tubingPres = rowdata.TUBING_PRES;
				 }else{
					 tubingPres = "";
				 }
	       		if (rowdata.WELL_TEMP != null && typeof(rowdata.WELL_TEMP)!="undefined"){
	       			wellTemp = rowdata.WELL_TEMP;
				 }else{
					 wellTemp = "";
				 }
	       		if (rowdata.INSTANTANEOUS_DELIVERY != null && typeof(rowdata.INSTANTANEOUS_DELIVERY)!="undefined"){
	       			delivery = rowdata.INSTANTANEOUS_DELIVERY;
				 }else{
					 delivery = "";
				 }
	       		if (rowdata.CUMULATIVE_DISCHARGE != null && typeof(rowdata.CUMULATIVE_DISCHARGE)!="undefined"){
	       			discharge = rowdata.CUMULATIVE_DISCHARGE;
				 }else{
					 discharge = "";
				 }
	       		if (rowdata.DALIY_CUMULATIVE_FLOW != null && typeof(rowdata.DALIY_CUMULATIVE_FLOW)!="undefined"){
	       			daliyFlow = rowdata.DALIY_CUMULATIVE_FLOW;
				 }else{
					 daliyFlow = "";
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
     			timeout : '3000',
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
     			url:'gaswell_queryStationInfo.action',
     			data: {"areaid":areaid,"oilid":oilid,"selecteValue":clearSelecteValue},
     			timeout : '3000',
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
     			url:'gaswell_queryWellInfo.action',
     			data: {"orgid":data},
     			timeout : '3000',
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
     	
         function getWellInitData() {
         	//$("#oilstationname1").ligerGetComboBoxManager().setValue('');
 			$("#areablock1").ligerGetComboBoxManager().setValue('');
 			$("#blockstationname1").ligerGetComboBoxManager().setValue('');
 			$("#rulewellname1").ligerGetComboBoxManager().setValue('');
     		var oilText=[{ id: 1 , text: '' }];
     		var areaText=[{ id: 1 , text: '' }];
     		var stationText=[{ id: 1 , text: '' }];
     		var wellText=[{ id: 1 , text: '' }];
     		jQuery.ajax({
     			type : 'post',
     			url : 'gaswell_cascadeInit.action',
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
     		liger.get("rulewellname1").setData(wellText);
     		liger.get("blockstationname1").setData(stationText);
     		liger.get("areablock1").setData(areaText);
     		liger.get("oilstationname1").setData(oilText);
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
			var wellnum='';
			
			
			if(datatype=='6'){
				wellnum=name;
				//$("#areablock").ligerGetComboBoxManager().selectValue(basid);
				
			}
			else{
				alert("该节点错误，请查看出信息对应节点");
				return;
			}

			grid.setOptions({
        		parms: [{
					name: 'areablock',
					value: wellnum
				}
				]
        	});
         	grid.loadData(true);
		}
		
         function onSubmit(){	
         	
         	var oilstationname1=$("#oilstationname1").val();
			var areablock1=$("#areablock1").val();
			var blockstationname1=$("#blockstationname1").val();
        	var rulewellname1=$("#rulewellname1").val();	
        	grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: oilstationname1
				},{
					name: 'areablock1',
					value: areablock1
				},{
					name: 'blockstationname1',
					value: blockstationname1
				},{
					name: 'rulewellname1',
					value: rulewellname1 
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
			var oilstationname1=$("#oilstationname1").val();
			var areablock1=$("#areablock1").val();
			var blockstationname1=$("#blockstationname1").val();
        	var rulewellname1=$("#rulewellname1").val();
   			var txtDate = $("#txtDate").val();
   			var txtDate1 = $("#txtDate1").val();
   			
   			if (exportFlag) {
   				oilstationname1=oilstationname1;
   				areablock1=areablock1;
   				blockstationname1=blockstationname1;
   				rulewellname1=rulewellname1;
   			}
   			var totalNum = 0;
   			var url = 'gasRPD_searchGWRPD.action?oilstationname1='+encodeURIComponent(oilstationname1)+'&rulewellname1='+encodeURIComponent(rulewellname1)+'&blockstationname1='+encodeURIComponent(blockstationname1)+'&areablock1='+encodeURIComponent(areablock1)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('export');
   			var urls = 'gasRPD_searchGWRPD.action?oilstationname1='+encodeURIComponent(oilstationname1)+'&rulewellname1='+encodeURIComponent(rulewellname1)+'&blockstationname1='+encodeURIComponent(blockstationname1)+'&areablock1='+encodeURIComponent(areablock1)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum='+encodeURIComponent('totalNum');
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
				 
				 document.getElementById("gas_welldid").value = gas_welldid;
			 		document.getElementById("welName").value = welName;
	               	document.getElementById("reportDate").value = reportDate;
	               	document.getElementById("tubingPres").value = tubingPres;
	            	document.getElementById("wellTemp").value= wellTemp;
	                document.getElementById("delivery").value = delivery;
	                document.getElementById("discharge").value = discharge;
	               	document.getElementById("daliyFlow").value = daliyFlow;
			 }else if(flg == 1){
				 document.getElementById("gas_welldid").value = "";
				 document.getElementById("welName").value = "";
	               	document.getElementById("reportDate").value = "";
	               	document.getElementById("tubingPres").value = "";
	            	document.getElementById("wellTemp").value= "";
	                document.getElementById("delivery").value = "";
	                document.getElementById("discharge").value = "";
	               	document.getElementById("daliyFlow").value = "";
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
              	    if(operate != 1 && operate != 2){
						setpage(0); //显示编辑界面
						setItemsd(0);//0-显示编辑区，添加隐藏按钮
					  }
              	   operate = 1;
              	   assignM(1);
              	   
              	   
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改信息的数据！');
              	    
              	     }else if(rows.length == 1){
							var myDate = new Date().Format("yyyy-MM-dd");
							if(reportDate != myDate){
								$.ligerDialog.error(reportDate + '不是当前时间，不让修改');
								break;
							}
						 	if(operate != 1 && operate != 2){
								setpage(0); //显示编辑界面
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
											url : 'gasRPDWH_removeGasRPDWH.action',
											async : false,
											data: {"gas_welldid":gas_welldid},
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
														$.ligerDialog.success('删除成功！');
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
				setpage(0); //显示编辑界面
				setItemsd(0);//0-显示编辑区，添加隐藏按钮
				break;   
				case "down":
						setpage(1); //隐藏编辑界面
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
	        	<table border="0" cellspacing="1" style="top: 14px; height: 53px;">
	        	<tr>
					<td align="right" class="l-table-edit-td" style="width:50px">采油站：</td>
		                <td align="left" class="l-table-edit-td" style="width:50px" >
		                	<input type="text" id="oilstationname1" name = "oilstationname1" />
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:50px">气站：</td>
		                <td align="left" class="l-table-edit-td" style="width:50px">
		                	<input type="text" id="blockstationname1" name = "blockstationname1"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:50px">井名：</td>
		                <td align="left" class="l-table-edit-td"style="width:50px" >
		                	<input type="text" id="rulewellname1" name = "rulewellname1"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px">井区块：</td>
		                <td align="left" class="l-table-edit-td" style="width:60px">
		                	<input type="text" id="areablock1" name = "areablock1"/>
		                </td>
	                <td align="right" class="l-table-edit-td" style="width:50px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" align="left"  id="txtDate1" ltype="date"/></td>
					<td align="right" class="l-table-edit-td" style="width:10px"></td>
					<td align="left" class="l-table-edit-td" >
					<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
					<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td>
					
				</tr>
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="width:100%;height: 100px; display:none;">
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		           		<td align="left" class="l-table-edit-td" style="width:150px">井名:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">日期:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">油压:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井口温度:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">瞬时流量:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">累积流量:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">日累积流量:</td>
		  
		            </tr>
		            <tr>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="welName" type="text" id="welName"  ltype="text" validate="{required:true}" />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="reportDate" type="text" id="reportDate"  ltype="date" />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="tubingPres" type="text" id="tubingPres" ltype="text" />
		                </td>
		               
		       
		                <td align="left" class="l-table-edit-td" >
		                	<input name="wellTemp" type="text" id="wellTemp" ltype="text" />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="delivery" type="text" id="delivery" ltype="text" />
		                </td>
		          
		                <td align="left" class="l-table-edit-td" >
							<input name="discharge" type="text" id="discharge" ltype="text" />
		                </td>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="daliyFlow" type="text" id="daliyFlow" ltype="text" />
		                </td>
		                
		                  <td align="left" class="l-table-edit-td" >
		                	<input name=gas_welldid type="hidden" id="gas_welldid" ltype="text" />
		                </td>
		            </tr>
		          
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>