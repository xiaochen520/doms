<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>水源井基础信息管理</title>
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
		var water_source_wellid ; 	//水源井ID
		var well_name;		//水源井号
		var well_cole;		//水源井码
		var bewell_name;	//曾用井号
		var commissioning_date;	//投产日期
		//var scrapped_date;	 	//报废日期
		var jrbz;
		var ljjd_s;
		var status_value;	//建设生产状态
		var org_id;			//组织结构ID
		var remark;			//备注
		var wellnumo;   	//编辑所属站号
		var gh_id;
		var serverid;
		var dyear;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'waterSoWell_pageInit.action',
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
			  $("#commissioning_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd ",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
			  $("#scrapped_date").ligerDateEditor(
		                {

		                    format: "yyyy-MM-dd",
		                 //   label: '格式化日期',
		                    labelWidth: 100,
		                    labelAlign: 'center',
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
             $("#wellnum").ligerComboBox({
				            	url:'waterSoWell_queryWellInfo.action',
				            	isShowCheckBox: false,
								valueField: 'id',
								valueFieldID: 'hidzzzid',
								textField: 'text',
								selectBoxWidth: 160,
								hideOnLoseFocus:true,
								initText :''
				            });
           //收索条件接入状态
           	document.getElementById("jrbz1").value= "全部";
 			  $("#jrbz1").ligerComboBox({
				  url:'comboboxdata_searchSwitchInflg.action?args=ALL', 
	     			valueField: 'id',
	     			valueFieldID: 'jrbz1',
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
                    
					
              	   
              	   	var water_source_wellid  = $("#water_source_wellid").val();
					var well_name  = $("#well_name").val();
					var well_cole = $("#well_cole").val();
					var bewell_name  = $("#bewell_name").val();
					var commissioning_date  = $("#commissioning_date").val();
					//var scrapped_date = $("scrapped_date").val();
					var jrbz="";
					if($("#jrbz").val() != ''){
						jrbz = $("#jrbzid").val();
					}
					var ljjd_s  = $("#ljjd_s").val();
					if(ljjd_s != ''){
						ljjd_s =  $("#hidserverid").val() ;
					}
					var status_value  = $("#status_value").val();
					var wellnums = $("#wellnum").val();
					//var org_id  = $("#org_id").val();
					var remark  = $("#remark").val();
					 var dyear = $("#dyear").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'waterSoWell_addorUpdateWaterSoWell.action',
									async : false,
									data: {"well_name":well_name ,"well_cole":well_cole,"bewell_name":bewell_name ,
											"commissioning_date":commissioning_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"dyear":dyear,
											"status_value":status_value,"wellnum":wellnums,"remark":remark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage2(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('水源井添加成功！');
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
									url : 'waterSoWell_addorUpdateWaterSoWell.action',
									async : false,
									data: {"well_name":well_name ,"well_cole":well_cole,"bewell_name":bewell_name ,
											"commissioning_date":commissioning_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"id":water_source_wellid,
											"dyear":dyear,"status_value":status_value,"wellnum":wellnums,"remark":remark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage2(1); //隐藏编辑界面
											setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('水源井修改成功！');
											operate = 0;
										}else{
											$.ligerDialog.error(outerror(jsondata));
										}
									},
									error : function(data) {
								
									}
								});
              	   
              	   }
		           //operate = 0;
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
        							//alert(JSON2.stringify(rowdata));
        							gh_id = rowdata.GH_ID;
        							water_source_wellid = rowdata.WATER_SOURCE_WELLID;
									well_name = rowdata.WELL_NAME;
									
									if (rowdata.LJJDID != null && typeof(rowdata.LJJDID)!="undefined"){
									 	serverid = rowdata.LJJDID;
									 }else{
									 	serverid = "";
									 }
									if (rowdata.WELL_CODE != null && typeof(rowdata.WELL_CODE)!="undefined"){
									 	well_cole = rowdata.WELL_CODE;
									 }else{
									 	well_cole = "";
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

									if (rowdata.SCRAPPED_DATE != null && typeof(rowdata.SCRAPPED_DATE)!="undefined"){
										scrapped_date = rowdata.SCRAPPED_DATE;
									 }else{
										 scrapped_date = "";
									 }
									jrbz = rowdata.JRBZ;
									if (rowdata.LJJD_S != null && typeof(rowdata.LJJD_S)!="undefined"){
									 	ljjd_s = rowdata.LJJD_S;
									 }else{
									 	ljjd_s = "";
									 }				
									if (rowdata.PROD_SNS != null && typeof(rowdata.PROD_SNS)!="undefined"){
									 	status_value = rowdata.PROD_SNS;
									 }else{
									 	status_value = "";
									 }
									 
									 
									if (rowdata.STATIONNUM != null && typeof(rowdata.STATIONNUM)!="undefined"){
									 	wellnumo = rowdata.STATIONNUM; 
									 }else{
									 	wellnumo = "";
									 }
									if (rowdata.DYEAR != null && typeof(rowdata.DYEAR)!="undefined"){
										dyear = rowdata.DYEAR; 
									 }else{
										 dyear = "";
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
			getWellInitData();
			var oilstationname="全部";
			//var areablock='';
			var blockstationname='';
			var ghname='';
			//var wellnum='';
			$("#combination1").ligerGetComboBoxManager().setValue('');
			$("#wellnum1").ligerGetComboBoxManager().setValue('');
			$("#watersowell1").ligerGetComboBoxManager().setValue('');
			getWellInitData();
			//$("#jrbz1").ligerGetComboBoxManager().setValue('');
			
           document.getElementById("jrbz1").value= "全部";
			
			if(datatype=='5'){
				oilstationname=name;
				$("#combination1").val(name);
			}
			
			if(datatype=='11'){
				blockstationname=name;
				$("#wellnum1").val(name);
			}
			
			if(datatype=='9'){
				ghname=id;
				$("#watersowell1").val(name);
				//$("#watersowell1").ligerGetComboBoxManager().selectValue(basid);
				//alert(wellnum);
			}
	
			
			grid.setOptions({
        		parms: [{name: 'combination1', value: oilstationname},
				{name: 'wellnum1', value: blockstationname},
				{name: 'jrbz',value: $("#jrbz1").val()},
				{name: 'ghname',value: ghname}
				]
        	});
         	grid.loadData(true);
		}
		
         function onSubmit()
        {
        	var texwater='';
        	if($("#watersowell1").val()==''||$("#watersowell1").val()==null){
        		
        	}else{
        		texwater=$("#wellid").val();
        	}
        	//alert(texwater);
        	grid.setOptions({
        		parms: [{name: 'combination1',value: $("#combination1").val()},
        		        {name: 'wellnum1',value: $("#wellnum1").val()},
				{name: 'jrbz',value: $("#jrbz1").val()},
				{name: 'watersowell1',value: texwater},
				{name: 'dyearC', value:$("#dyearC").val()
					}
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
				 $("#wellnum").ligerGetComboBoxManager().selectValue(gh_id);
			 		document.getElementById("water_source_wellid").value = water_source_wellid;
	               	document.getElementById("well_name").value = well_name;
	               	document.getElementById("well_cole").value = well_cole;
	               	document.getElementById("bewell_name").value= bewell_name;
	               	document.getElementById("commissioning_date").value= commissioning_date;
	            	document.getElementById("dyear").value= dyear;
	            	document.getElementById("status_value").value= status_value;
	               	document.getElementById("org_id").value= org_id;
	               	document.getElementById("remark").value= remark;
	               //	document.getElementById("ljjd_s").value= ljjd_s;
	              	//document.getElementById("jrbz").value= jrbz;
	              // $("#jrbz").ligerGetComboBoxManager().getValue(jrbz);
	              
					var oc=$("#jrbz").ligerGetComboBoxManager().findValueByText(jrbz);
					$("#jrbz").ligerGetComboBoxManager().selectValue(oc);

				//	var bc = $("#ljjd_s").ligerGetComboBoxManager.findValueByText(jrbz);
					$("#ljjd_s").ligerGetComboBoxManager().selectValue(ljjd_s);
					
	               
	               
	               	
			 }else if(flg == 1){
			 		document.getElementById("water_source_wellid").value = "";
	               	document.getElementById("well_name").value = "";
	               	document.getElementById("well_cole").value = "";
	               	document.getElementById("bewell_name").value= "";
	               	document.getElementById("commissioning_date").value="";
	               	//document.getElementById("scrapped_date").value= "";
	             	$("#ljjd_s").ligerGetComboBoxManager().selectValue("");
		            $("#jrbz").ligerGetComboBoxManager().selectValue("");
		              
	               	document.getElementById("status_value").value= "";
	               	//document.getElementById("wellnum").value= "";
	                $("#wellnum").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("org_id").value= "";
	               	document.getElementById("remark").value= "";
	               	document.getElementById("dyear").value= "";
			 }
			 		
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
            	  if(operate != 1 && operate != 2){
            		  setpage2(0); //显示编辑界面
				 		setItemsd(0);//1-隐藏编辑区添加显示按钮
					}
              	   operate = 1;
              	   assignM(1);
              	   
              	   
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改信息的数据！');
              	    
              	     }else if(rows.length == 1){
							
              	    	if(operate != 1 && operate != 2){
              	    		setpage2(0); //显示编辑界面
					 		setItemsd(0);//1-隐藏编辑区添加显示按钮
						}
		              	   operate = 2;
		              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改信息的数据！');
              	     }
                  break;
              case "delete":
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要删除的数据！');
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'waterSoWell_removeWaterSoWell.action',
											async : false,
											data: {"water_source_wellid":water_source_wellid,"org_id":org_id},
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
														$.ligerDialog.success('水源井基础删除成功！');
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
            	  setpage2(0); //显示编辑界面
            	    setItemsd(0);//0-显示编辑区，添加隐藏按钮
                break;   
                case "down":
                	setpage2(1); //隐藏编辑界面
				 	setItemsd(1);//1-隐藏编辑区添加显示按钮
                break;    
          }
      }
      function onExport() {
    	  	var texwater='';
        	if($("#watersowell1").val()==''||$("#watersowell1").val()==null){
        		
        	}else{
        		texwater=$("#wellid").val();
        	}
 		var combin=$("#combination1").val();
  	    var welln=$("#wellnum1").val()+'';
  	    var watersowell1=$("#texwater").val();
  	    var jrbz1=$("#jrbz1").val();
  	    var dyearC = $("#dyearC").val();	
		var totalNum = 0;
		var url = "waterSoWell_searchWaterSoWell.action?combination1="+encodeURIComponent(combin)+"&wellnum1="+encodeURIComponent(welln)+"&watersowell1="+encodeURIComponent(texwater)+"&jrbz="+encodeURIComponent(jrbz1)+'&dyearC='+encodeURIComponent(dyearC)+'&totalNum=export';
		var urls = "waterSoWell_searchWaterSoWell.action?combination1="+encodeURIComponent(combin)+"&wellnum1="+encodeURIComponent(welln)+"&watersowell1="+encodeURIComponent(texwater)+"&jrbz="+encodeURIComponent(jrbz1)+'&dyearC='+encodeURIComponent(dyearC)+'&totalNum=totalNum';
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
			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url;});
		}
		else if(totalNum > 10000){
			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url;});
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
		                <td align="right" class="l-table-edit-td" style="width:60px">接入状态：</td>
		                <td align="right" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="jrbz1" name="jrbz1"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px">设计产能年：</td>
		                <td align="right" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="dyearC" name="dyearC"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:50px"></td>
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
	  
		   <div id="edituser" style="width:100%;height: 100px; display:none;">
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		               
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">水源井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属站号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井号编码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">曾用井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">建设生产状态:</td>
		                 
		                 </tr>
		                <tr>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="well_name" type="text" id="well_name"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="wellnum" type="text" id="wellnum"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="well_cole" type="text" id="well_cole" ltype="text"  />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="bewell_name" type="text" id="bewell_name" ltype="text"  validate="{minlength:1,maxlength:20}" />
		                </td>
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="status_value" type="text" id="status_value" ltype="text" />
		                </td>
		                </tr>
		                <tr>
		                
		                 <td align="left" class="l-table-edit-td" style="width:150px">投产日期:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">设计产能年:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">接入标志:</td>
		               <td align="left" class="l-table-edit-td" style="width:150px">服务器逻辑节点名:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		            </tr>
		            <tr>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="commissioning_date" type="text" id="commissioning_date" ltype="date"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="dyear" type="text" id="dyear" ltype="text" validate="{minlength:0,maxlength:4}"  />
		                </td>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="jrbz" type="text" id="jrbz" ltype="text" validate="{required:true }" />
		                </td>
		        
		                <td align="left" class="l-table-edit-td" >
		                	<input name="ljjd_s" type="text" id="ljjd_s" ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<!-- <input name="audbshor_date" type="text" id="audbshor_date" ltype="text" /> -->
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="water_source_wellid" type="hidden" id="water_source_wellid" />
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