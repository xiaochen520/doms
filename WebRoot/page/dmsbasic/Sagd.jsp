﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>SAGD井基础信息管理</title>
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
        
        //鼠标选择数据
		var sagdid ; 		//sagd井id
		var ljjd_s ; 		//scada服务器逻辑节点名
		var jhmc;			//井号名称
		var jhmc_s;			//井号编码
		var dtfmc_s;			//多通阀编码
		var tdh;				//通道号
		var auf;				//上自动化标志（1-上，0-不上）
		var whrtu_cnf;		//井口控制器接入标志（1-已接，0-未接
		var wsrtu_cnf;		//井口蒸气控制器接入标志（1-已接，0-未接）
		var p_ugrtu_cnf;		//井下控制器接入标志（1-已接，0-未接）
		var p_ugtem_num;		//井下温度点数
		var p_purtu_cnf;		//抽油机控制器接入标志（1-已接，0-未接）
		var remark;			//备注
		var rlast_oper;		//此记录上次操作者
		var rlast_odate;		//此记录上次操作日期
		var wwork_flag;		//施工井标志
		var wwork_date;		//施工开始日期
		var wabnormal_flag;	//异常井标志
		var wabnormal_date;	//异常井开始时间
		var wells_num;		//井口数
		var wells_nam;		//井口名称列举（p，i）
		var status_value;	//建设生产状态
		var produ_date;		//投产日期
		var scan_time;		//ifix触发器扫描时间
		var phase;			//ifix触发器相位时间
		var dyear;			//设计产能年
		var p_dlt_cnf;		//有无电流图标志
		var org_id;			//组织结构ID
		var well_rtu_adr;	//井口rtu地址
		
		var output_coefficient;  //分产系数
		var prod_stages; //生产阶段	
		var p_prod_mode; //P井生产方式
		var i_prod_mode; //I井生产方式
		
		var zzz_id;
		var gh_id;
		var jrbz; 
		var QKMC;
		var wellAreaSelf; //所属井区
		var wellAreaSelfid;
		var jqkid;
		var hidserverid;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'sagd_pageInit.action',
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
                
                
                 //查询所属SAGD采油站
        		 $("#oilationname").ligerComboBox({
                	url:'rulewell_queryOilSationInfo.action?arg=sagdcaiyou',
        			valueField: 'id',
        			valueFieldID: 'oilationnameid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			autocomplete:true,
        			hideOnLoseFocus:true,
        			onSelected:function (data){
        			liger.get("ghmc").setValue('');	        		
                		if ($("#oilationnameid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
        					setGHData($("#oilationnameid").val(),proData);       					
        				}
         				else {
        					setGHData();
        				} 
                	}
        		});
        	
        	
        		//查询采油站下管汇
        		$("#ghmc").ligerComboBox({
        			url:'sagd_queryGhmcInfo.action',
                    valueField: 'id',
        			valueFieldID: 'ghmcid',
        			textField: 'text',
        			selectBoxHeight:400,
        			selectBoxWidth:140,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                    onSelected:function (data){
        				if (data != null && typeof(data)!="undefined" && data != ''){
        					setWellInitData($("#ghmcid").val(),proData);        					
        					}
        				}       			
        			}        		
        		);

		
			//关联只显示SGAD井下的管汇
			function setGHData(oilationnameid,proData) {
  			//alert(oilationnameid);
			jQuery.ajax({
				type : 'post',
				url:'sagd_queryGhmcInfo.action',
				data: {"oilationnameid":oilationnameid},	
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("ghmc").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("ghmc").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}
                
                
                
                
                
                
                $("#areablock1").ligerComboBox({
        			url:'sagd_queryAreablockInfo.action?arg=sagd',
        			valueField: 'id',
        			valueFieldID: 'areablockid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			autocomplete:true,
        			hideOnLoseFocus:true,
        			onBeforeSelect:function (data){
        				/* liger.get("blockstationname").setValue('');
        				liger.get("wellnum").setValue(''); */
        			},
        			onSelected:function (data){
    				if ($("#areablockid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
    					//setStationData($("#areablockid").val(),proData);
    				}
    				else {
    					getWellInitData();
    				}
                	}
        		});
        		$("#blockstationname").ligerComboBox({
        			url:'sagd_queryStationInfo.action',
                    valueField: 'id',
        			valueFieldID: 'stationid',
        			textField: 'text',
        			selectBoxHeight:400,
        			selectBoxWidth:140,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                    onBeforeSelect:function (data){
                    	//liger.get("areablock1").setValue('');
                    	liger.get("wellnum").setValue('');
        			},
                    onSelected:function (data){
        				if (data != null && typeof(data)!="undefined" && data != ''){
        					//$("#areablock1").ligerGetComboBoxManager().setValue('');
        					setWellInitData($("#stationid").val(),proData);
        				}
        			}
        		});
        		$("#wellnum").ligerComboBox({
        			url:'sagd_queryWellNameInfo.action',
        			valueField: 'id',
        			valueFieldID: 'wellnumsid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			hideOnLoseFocus:true,
                    autocomplete:true,
                    onBeforeSelect:function (data){
                    	if(liger.get("areablock1").getValue() != ''){
                    		//liger.get("areablock1").setValue('');
                    	}
        			},
        			onSelected:function (data){
        			}
        		});
        		$("#blockstationname1").ligerComboBox({
        			url:'sagd_queryStationInfo.action?areaid=noall',
                    valueField: 'id',
        			valueFieldID: 'stationsid',
        			textField: 'text',
        			selectBoxHeight:300,
        			selectBoxWidth:140,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                    onBeforeSelect: function (newvalue){
    					liger.get("manifold").setValue('');
    				},
                    onSelected:function (data){
        				if (data != null && typeof(data)!="undefined" && data != ''){
        					setGhData($("#stationsid").val(),proData);
        				}
        			}
        		});
        		$("#manifold").ligerComboBox({
        			url:"manifoldBasicInfo_queryManifoldNameInfo.action",
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
        		$("#wellAreaSelf").ligerComboBox({
        			url:"rulewell_querywellAreaSelf.action",
        			valueField: 'id',
        			valueFieldID: 'wellAreaSelfid',
        			textField: 'text',
        			selectBoxHeight:200,
        			selectBoxWidth:99,
        			hideOnLoseFocus:true,
                    autocomplete:true,
        			onSelected:function (data){
        			}
        		});
			  $("#commissioning_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
             
             $("#wwork_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
             $("#wabnormal_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
             $("#rlast_odate").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
            $("#rlast_odate").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
            $("#start_increase_freq_time").ligerDateEditor({ format: "yyyy-MM-dd  hh:mm",showTime: true, labelWidth: 100, labelAlign: 'center', cancelable : false});
            $("#end_increase_freq_time").ligerDateEditor({ format: "yyyy-MM-dd  hh:mm",showTime: true, labelWidth: 100, labelAlign: 'center', cancelable : false});
             $("#whrtu_cnf").ligerComboBox({
       			hideOnLoseFocus:true,
 					autocomplete:true,
 		                data: [
 		                    { text: '未接', id: '0' },
 		                    { text: '已接', id: '1' }
 		                    
 		                ], valueFieldID: 'hidwhrtu_cnfid',
 					initText :''
 		            });  
 		            

             $("#wsrtu_cnf").ligerComboBox({
        			hideOnLoseFocus:true,
  					autocomplete:true,
  		                data: [
  		                    { text: '未接', id: '0' },
  		                    { text: '已接', id: '1' }
  		                    
  		                ], valueFieldID: 'wsrtu_cnfid',
  					initText :''
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
		     //收索条件建设状态
	         $("#status_value1").ligerComboBox({
				url:'station_searchStatusValueAll.action',
				valueField: 'id',
				valueFieldID: 'hidstatus1id',
				textField: 'text',
				selectBoxHeight:200,
				width: 120,
				autocomplete:true,
				hideOnLoseFocus:true
				//initText :'全部'
			});
	         document.getElementById("status_value1").value= "全部";
 		      $("#auf").ligerComboBox({
       			hideOnLoseFocus:true,
 					autocomplete:true,
 		                data: [
 		                    { text: '未上', id: '0' },
 		                    { text: '已上', id: '1' }
 		                    
 		                ], valueFieldID: 'hidaufid',
 					initText :''
 		            });  
             $("#p_ugrtu_cnf").ligerComboBox({
     			hideOnLoseFocus:true,
					autocomplete:true,
		                data: [
		                    { text: '未接', id: '0' },
		                    { text: '已接', id: '1' }
		                    
		                ], valueFieldID: 'hidp_ugrtu_cnfid',
					initText :''
		            });   
		            
		       $("#wwork_flag").ligerComboBox({
			hideOnLoseFocus:true,
					autocomplete:true,
					data: [
						{ text: '未施工', id: '0' },
						{ text: '施工', id: '1' }
					], valueFieldID: 'hidwwork_flagid',
					initText :'',
					onSelected:function (data){
						if (data === '0') {
							$("#wwork_date").ligerGetDateEditorManager().setValue('');
							$("#wwork_date").ligerGetDateEditorManager().setDisabled();
							//liger.get("wwork_date").setValue('');
						}else{
							$("#wwork_date").ligerGetDateEditorManager().setEnabled(); 
							//$("#wwork_date").val('');
							}
					}
			});
             $("#p_purtu_cnf").ligerComboBox({
      			hideOnLoseFocus:true,
 					autocomplete:true,
 		                data: [
 		                    { text: '未接', id: '0' },
 		                    { text: '已接', id: '1' }
 		                    
 		                ], valueFieldID: 'hidp_purtu_cnfid',
 					initText :''
 		            });   
             $("#p_dlt_cnf").ligerComboBox({
       			hideOnLoseFocus:true,
  					autocomplete:true,
  		                data: [
  		                    { text: '无', id: '0' },
  		                    { text: '有', id: '1' }
  		                    
  		                ], valueFieldID: 'hidp_dlt_cnfid',
  					initText :''
  		            });   
             $("#wabnormal_flag").ligerComboBox({
        			hideOnLoseFocus:true,
   					autocomplete:true,
   		                data: [
   		                    { text: '正常', id: '0' },
   		                    { text: '异常', id: '1' }
   		                    
   		                ], valueFieldID: 'hidwabnormal_flagid',
   					initText :'',
					onSelected:function (data){
					if (data === '0') {
						$("#wabnormal_date").ligerGetDateEditorManager().setValue('');
						$("#wabnormal_date").ligerGetDateEditorManager().setDisabled();
					}else {
						$("#wabnormal_date").ligerGetDateEditorManager().setEnabled(); 
					}
				}
   		            });   
             $("#status_value").ligerComboBox({
     			url:'station_searchStatusValue.action',
     			valueField: 'id',
     			valueFieldID: 'hidstatusids',
     			textField: 'text',
     			selectBoxHeight:200,
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
     			selectBoxHeight:200,
     			selectBoxWidth:150,
     			width: 120,
     			autocomplete:true,
     			hideOnLoseFocus:true

     		});
     		
     		 $("#prod_stages").ligerComboBox({ //生产阶段	
      			url:'sagd_SearchPstages.action',
      			valueField: 'id',
      			valueFieldID: 'stagesid',
      			textField: 'text',
      			selectBoxHeight:200,
      			selectBoxWidth:99,
      			width: 120,
      			autocomplete:true,
      			hideOnLoseFocus:true

      		});
     		$("#prod_stages1").ligerComboBox({ //生产阶段	
      			url:'sagd_SearchPstages.action',
      			valueField: 'id',
      			valueFieldID: 'stagesid',
      			textField: 'text',
      			selectBoxHeight:200,
      			selectBoxWidth:99,
      			width: 120,
      			autocomplete:true,
      			hideOnLoseFocus:true

      		});
     		 $("#p_prod_mode").ligerComboBox({  //P井生产方式
      			url:'sagd_SearchPmode.action',
      			valueField: 'id',
      			valueFieldID: 'pmodeid',
      			textField: 'text',
      			selectBoxHeight:200,
      			selectBoxWidth:99,
      			width: 120,
      			autocomplete:true,
      			hideOnLoseFocus:true

      		});
     		 $("#i_prod_mode").ligerComboBox({   //I井生产方式
       			url:'sagd_SearchImode.action',
       			valueField: 'id',
       			valueFieldID: 'imodeid',
       			textField: 'text',
       			selectBoxHeight:200,
       			selectBoxWidth:99,
       			width: 120,
       			autocomplete:true,
       			hideOnLoseFocus:true

       		});
     		$("#increase_freq_flag").ligerComboBox({
     			valueField: 'id',
     			valueFieldID: 'hidstatusid',
     			textField: 'text',
     			selectBoxHeight:200,
     			selectBoxWidth:100,
     			width: 120,
     			autocomplete:true,
     			hideOnLoseFocus:true,
     			data: [ { text: '已加密', id: '0' }, { text: '未加密', id: '1' } ]
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
                    var org_id = $("#org_id").val();
              	   	var sagdid  = $("#sagdid").val();
					var ljjd_s  = $("#ljjd_s").val();
					//alert(ljjd_s)
					var jhmc = $("#jhmc").val();
					var manifolds ="";
					var manifoldname='';
					if( $("#manifold").val() != ''){
					manifolds = $("#wellnumsid").val();
					manifoldname=$("#manifold").val();
					}
					var servernode = "";
					if(ljjd_s != ''){
						servernode =  $("#hidserverid").val() ;
					}
					var wellAreaSelf = "";
					if($(wellAreaSelf).val() !=''){
						wellAreaSelf = $("#wellAreaSelfid").val();
						}
					var jhmc_s  = $("#jhmc_s").val();
					var dtfmc_s  = $("#dtfmc_s").val();
					var tdh  = $("#tdh").val();
					var auf  = $("#auf").val();
					var whrtu_cnf  = $("#whrtu_cnf").val();
					var wsrtu_cnf  = $("#wsrtu_cnf").val();
					var jrbz  = "";
					if($("#jrbz").val() !=''){
						jrbz = $("#jrbzid").val();
						}
					var p_ugrtu_cnf  = $("#p_ugrtu_cnf").val();
					var p_ugtem_num  = $("#p_ugtem_num").val();
					var p_purtu_cnf  = $("#p_purtu_cnf").val();
					var remark  = $("#remark").val();
					var wwork_flag  = $("#wwork_flag").val();
					var wwork_date  = $("#wwork_date").val();
					var wabnormal_flag  = $("#wabnormal_flag").val();
					var wabnormal_date  = $("#wabnormal_date").val();
					var wells_num  = $("#wells_num").val();
					var wells_nam  = $("#wells_nam").val();
					var status_value  ="";
					if($("#status_value").val() !=''){
						status_value= $("#hidstatusids").val();
						}
					var produ_date  = $("#commissioning_date").val();
					var scan_time  = $("#scan_time").val();
					var phase  = $("#phase").val();
					var dyear  = $("#dyear").val();
					var p_dlt_cnf  = $("#p_dlt_cnf").val();
					var org_id  = $("#org_id").val();
					var well_rtu_adr  = $("#well_rtu_adr").val();
					var output_coefficient= $("#output_coefficient").val(); //分产系数
					var prod_stages= $("#prod_stages").val(); //生产阶段	
					var p_prod_mode= $("#p_prod_mode").val(); //P井生产方式
					var i_prod_mode= $("#i_prod_mode").val(); //I井生产方式

					var gh_id1 = $("#gh_id1").val();
					var datatype = $("#datatype").val();
					var start_increase_freq_time = $("#start_increase_freq_time").val();
					var end_increase_freq_time = $("#end_increase_freq_time").val();
					var increase_freq_flag = $("#increase_freq_flag").val();
					var increase_interval = $("#increase_interval").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	   	   jQuery.ajax({
									type : 'post',
									url : 'sagd_addOrUpdateSagd.action',
									async : false,
									data: {"org_id":org_id,"sagdid":sagdid ,"ljjd_s":ljjd_s ,"jhmc":jhmc,"manifold":manifolds,"wellAreaSelf":wellAreaSelf,
											"jhmc_s":jhmc_s, "dtfmc_s":dtfmc_s ,"tdh":tdh,"auf":auf,"whrtu_cnf":whrtu_cnf,"wsrtu_cnf":wsrtu_cnf,"jrbz":jrbz,
											"p_ugrtu_cnf":p_ugrtu_cnf,"p_ugtem_num":p_ugtem_num,"p_purtu_cnf":p_purtu_cnf,"remark":remark,"manifoldname":manifoldname,"increase_freq_flag":increase_freq_flag,"increase_interval":increase_interval,
											"wwork_flag":wwork_flag,"wwork_date":wwork_date,"wabnormal_flag":wabnormal_flag,"wabnormal_date":wabnormal_date,"start_increase_freq_time":start_increase_freq_time,"end_increase_freq_time":end_increase_freq_time
											,"wells_num":wells_num,"wells_nam":wells_nam,"status_value":status_value,"commissioning_date":produ_date,"scan_time":scan_time,"phase":phase,"dyear":dyear,
											"p_dlt_cnf":p_dlt_cnf,"well_rtu_adr":well_rtu_adr,"output_coefficient":output_coefficient,"prod_stages":prod_stages,"p_prod_mode":p_prod_mode,"i_prod_mode":i_prod_mode},
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
											$.ligerDialog.success('SAGD井添加成功！');
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
									url : 'sagd_addOrUpdateSagd.action',
									async : false,
									data: {"org_id":org_id,"sagdid":sagdid , "ljjd_s":ljjd_s ,"jhmc":jhmc,"manifold":manifolds,"wellAreaSelf":wellAreaSelf,
											"jhmc_s":jhmc_s, "dtfmc_s":dtfmc_s ,"tdh":tdh,"auf":auf,"whrtu_cnf":whrtu_cnf,"wsrtu_cnf":wsrtu_cnf,"jrbz":jrbz,
											"p_ugrtu_cnf":p_ugrtu_cnf,"p_ugtem_num":p_ugtem_num,"p_purtu_cnf":p_purtu_cnf,"remark":remark,"manifoldname":manifoldname,"increase_freq_flag":increase_freq_flag,"increase_interval":increase_interval,
											"wwork_flag":wwork_flag,"wwork_date":wwork_date,"wabnormal_flag":wabnormal_flag,"wabnormal_date":wabnormal_date,"start_increase_freq_time":start_increase_freq_time,"end_increase_freq_time":end_increase_freq_time
											,"wells_num":wells_num,"wells_nam":wells_nam,"status_value":status_value,"commissioning_date":produ_date,"scan_time":scan_time,"phase":phase,"dyear":dyear,
											"p_dlt_cnf":p_dlt_cnf,"well_rtu_adr":well_rtu_adr,"output_coefficient":output_coefficient,"prod_stages":prod_stages,"p_prod_mode":p_prod_mode,"i_prod_mode":i_prod_mode},
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
											$.ligerDialog.success('SAGD井修改成功！');
											operate = 0;
											
										}
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
        							zzz_id = rowdata.ZZZ_ID;
        							gh_id = rowdata.GH_ID;
        							sagdid = rowdata.SAGDID;
        							org_id = rowdata.ORG_ID;
        							wellAreaSelf = rowdata.QKMC;
        							jrbz = rowdata.JRBZ;
        							if (rowdata.LJJD_S != null && typeof(rowdata.LJJD_S)!="undefined"){
									 	ljjd_s = rowdata.LJJD_S;
									 }else{
									 	ljjd_s = "";
									 }
										//ljjd_s = rowdata.HIDSERVERID;
									jhmc = rowdata.JHMC;
									jhmc_s = rowdata.JHMC_S;
									if (rowdata.DTFMC_S != null && typeof(rowdata.DTFMC_S)!="undefined"){
									 	dtfmc_s = rowdata.DTFMC_S;
									 }else{
									 	dtfmc_s = "";
									 }
									
									tdh = rowdata.TDH;
									auf = rowdata.AUF;
									if (rowdata.QKID != null && typeof(rowdata.QKID)!="undefined"){
									 	jqkid = rowdata.QKID;
									 }else{
									 	jqkid = "";
									 }
									
									if (rowdata.WHRTU_CNF != null && typeof(rowdata.WHRTU_CNF)!="undefined"){
									 	whrtu_cnf = rowdata.WHRTU_CNF;
									 }else{
									 	whrtu_cnf = "";
									 }
									if (rowdata.WSRTU_CNF != null && typeof(rowdata.WSRTU_CNF)!="undefined"){
									 	wsrtu_cnf = rowdata.WSRTU_CNF;
									 }else{
									 	wsrtu_cnf = "";
									 }
									
									if (rowdata.P_UGRTU_CNF != null && typeof(rowdata.P_UGRTU_CNF)!="undefined"){
									 	p_ugrtu_cnf = rowdata.P_UGRTU_CNF;
									 }else{
									 	p_ugrtu_cnf = "";
									 }
									if (rowdata.P_UGTEM_NUM != null && typeof(rowdata.P_UGTEM_NUM)!="undefined"){
									 	p_ugtem_num = rowdata.P_UGTEM_NUM;
									 }else{
									 	p_ugtem_num = "";
									 }
									
									if (rowdata.P_PURTU_CNF != null && typeof(rowdata.P_PURTU_CNF)!="undefined"){
									 	p_purtu_cnf = rowdata.P_PURTU_CNF;
									 }else{
									 	p_purtu_cnf = "";
									 }
									if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
									 	remark = rowdata.REMARK; 
									 }else{
									 	remark = "";
									 }
									rlast_oper = rowdata.RLAST_OPER;
									rlast_odate = rowdata.RLAST_ODATE;
									 if (rowdata.WWORK_FLAG != null && typeof(rowdata.WWORK_FLAG)!="undefined"){
									 	wwork_flag = rowdata.WWORK_FLAG; 
									 }else{
									 	wwork_flag = "";
									 }
									 if (rowdata.WWORK_DATE != null && typeof(rowdata.WWORK_DATE)!="undefined"){
									 	wwork_date = rowdata.WWORK_DATE; 
									 }else{
									 	wwork_date = "";
									 }
									 if (rowdata.WABNORMAL_FLAG != null && typeof(rowdata.WABNORMAL_FLAG)!="undefined"){
									 	wabnormal_flag = rowdata.WABNORMAL_FLAG; 
									 }else{
									 	wabnormal_flag = "";
									 }
									 if (rowdata.WABNORMAL_DATE != null && typeof(rowdata.WABNORMAL_DATE)!="undefined"){
									 	wabnormal_date = rowdata.WABNORMAL_DATE; 
									 }else{
									 	wabnormal_date = "";
									 }
									 if (rowdata.WELLS_NUM != null && typeof(rowdata.WELLS_NUM)!="undefined"){
									 	wells_num = rowdata.WELLS_NUM; 
									 }else{
									 	wells_num = "";
									 }
									 if (rowdata.WELLS_NAM != null && typeof(rowdata.WELLS_NAM)!="undefined"){
									 	wells_nam = rowdata.WELLS_NAM; 
									 }else{
									 	wells_nam = "";
									 }
									 if (rowdata.PROD_SNS != null && typeof(rowdata.PROD_SNS)!="undefined"){
									 	status_value = rowdata.PROD_SNS; 
									 	
									 }else{
									 	status_value = "";
									 }
								
									 if (rowdata.COMMISSIONING_DATE != null && typeof(rowdata.COMMISSIONING_DATE)!="undefined"){
									 	commissioning_date = rowdata.COMMISSIONING_DATE; 
									 }else{
									 	commissioning_date = "";
									 }
									 scan_time = rowdata.SCAN_TIME;
									 phase = rowdata.PHASE;
									 dyear = rowdata.DYEAR;
									 if (rowdata.P_DLT_CNF != null && typeof(rowdata.P_DLT_CNF)!="undefined"){
									 	p_dlt_cnf = rowdata.P_DLT_CNF; 
									 }else{
									 	p_dlt_cnf = "";
									 }
									 if (rowdata.WELL_RTU_ADR != null && typeof(rowdata.WELL_RTU_ADR)!="undefined"){
									 	well_rtu_adr = rowdata.WELL_RTU_ADR; 
									 }else{
									 	well_rtu_adr = "";
									 }

									 if (rowdata.OUTPUT_COEFFICIENT != null && typeof(rowdata.OUTPUT_COEFFICIENT)!="undefined"){
										 output_coefficient = rowdata.OUTPUT_COEFFICIENT; 
										 }else{
											 output_coefficient = "";
										 }
									 if (rowdata.PROD_STAGES != null && typeof(rowdata.PROD_STAGES)!="undefined"){
										 prod_stages = rowdata.PROD_STAGES; 
										 }else{
											 prod_stages = "";
										 }
									 if (rowdata.P_PROD_MODE != null && typeof(rowdata.P_PROD_MODE)!="undefined"){
										 p_prod_mode = rowdata.P_PROD_MODE; 
										 }else{
											 p_prod_mode = "";
										 }
									 if (rowdata.INCREASE_INTERVAL != null && typeof(rowdata.INCREASE_INTERVAL)!="undefined"){
										 increase_interval = rowdata.INCREASE_INTERVAL; 
										 }else{
											 increase_interval = "";
										 }
									 if (rowdata.END_INCREASE_FREQ_TIME != null && typeof(rowdata.END_INCREASE_FREQ_TIME)!="undefined"){
										 end_increase_freq_time = rowdata.END_INCREASE_FREQ_TIME; 
										 }else{
											 end_increase_freq_time = "";
										 }
									 if (rowdata.START_INCREASE_FREQ_TIME != null && typeof(rowdata.START_INCREASE_FREQ_TIME)!="undefined"){
										 start_increase_freq_time = rowdata.START_INCREASE_FREQ_TIME; 
										 }else{
											 start_increase_freq_time = "";
										 }
									 if (rowdata.INCREASE_FREQ_FLAG != null && typeof(rowdata.INCREASE_FREQ_FLAG)!="undefined"){
										 increase_freq_flag = rowdata.INCREASE_FREQ_FLAG; 
										 }else{
											 increase_freq_flag = "";
										 }
									 
									 if (rowdata.I_PROD_MODE != null && typeof(rowdata.I_PROD_MODE)!="undefined"){
										 i_prod_mode = rowdata.I_PROD_MODE; 
										 }else{
											 i_prod_mode = "";
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
			var oilstationname='';
			var areablock='';
			var blockstationname='';
			var ghname='';
			var wellnum='';
			$("#areablock1").ligerGetComboBoxManager().setValue('');
			getWellInitData();
			if(datatype=='4'){
				oilstationname=name;
				$("#oilstationname").val(name);
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
				$("#blockstationname").val(name);
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
				//$("#wellnum").ligerGetComboBoxManager().selectValue(basid);
				$("#wellnum").val(name);
				document.getElementById("datatype").value="";
				document.getElementById("gh_id1").value="";
				
			}
			
			
			grid.setOptions({
        		parms: [{ name: 'oilstationname', value: $("#oilstationname").val() },
				{ name: 'areablock', value: areablock },
				{ name: 'blockstationname', value: blockstationname },
				{ name: 'ghname', value: ghname },
				{name: 'jrbz',value: $("#jrbz1").val()},
				{name: 'dyear1',value: $("#dyear1").val()},
				{name: 'prod_stages',value: $("#prod_stages1").val()},
				{name: 'status_value',value: $("#status_value1").val()},
				{ name: 'wellnum', value: wellnum }
				]
        	});
         	grid.loadData(true);
		}
		


         function onSubmit()
        {
        	 document.getElementById("datatype").value="";
			document.getElementById("gh_id1").value="";
        	grid.setOptions({
        		parms: [
				
				{ name: 'oilationname', value: $("#oilationname").val() },
				{ name: 'ghmc', value: $("#ghmc").val() }, 				
				{name: 'jrbz',value: $("#jrbz1").val()},
				{name: 'dyear1',value: $("#dyear1").val()},
				{name: 'prod_stages',value: $("#prod_stages1").val()},
				{name: 'status_value',value: $("#status_value1").val()},
				{ name: 'wellnum', value: $("#wellnum").val() }
				]
        	});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
				 	document.getElementById("sagdid").value = sagdid;
				 	$("#blockstationname1").ligerGetComboBoxManager().selectValue(zzz_id);
				 	$("#manifold").ligerGetComboBoxManager().selectValue(gh_id);
				 	$("#wellAreaSelf").ligerGetComboBoxManager().selectValue(jqkid);
	               	document.getElementById("ljjd_s").value = ljjd_s;
	               //	$("#ljjd_s").ligerGetComboBoxManager().setValue(ljjd_s)
	               	
	               	//alert("hidserverid"+$("#hidserverid").val());
	               	
	               //$("#whrtu_cnf").ligerGetComboBoxManager().selectValue();
					//$("#whrtu_cnf").ligerGetComboBoxManager().selectValue(ljjd_s);
	               	document.getElementById("jhmc").value = jhmc;
	               	document.getElementById("jhmc_s").value= jhmc_s;
	               	document.getElementById("dtfmc_s").value= dtfmc_s;
	               	document.getElementById("tdh").value= tdh;
	               	document.getElementById("auf").value= auf;
	               	var jb = $("#jrbz").ligerGetComboBoxManager().findValueByText(jrbz);
	               	$("#jrbz").ligerGetComboBoxManager().selectValue(jb);
	               	//alert(jb.getValue());
	               //	jb.setText(jrbz);
	               	//jb.selectValue(jb.getSelected(jrbz));
	               	/* if (jrbz == '未接入') {
	               		$("#jrbz").ligerGetComboBoxManager().selectValue('0');
					} else {
						$("#jrbz").ligerGetComboBoxManager().selectValue('1');
					} */
					if (whrtu_cnf == '未接') {
						$("#whrtu_cnf").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#whrtu_cnf").ligerGetComboBoxManager().selectValue(1);
					}
					if (wsrtu_cnf == '未接') {
						$("#wsrtu_cnf").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#wsrtu_cnf").ligerGetComboBoxManager().selectValue(1);
					}
					if (auf == '未上') {
						$("#auf").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#auf").ligerGetComboBoxManager().selectValue(1);
					}
					if (p_ugrtu_cnf == '未接') {
						$("#p_ugrtu_cnf").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#p_ugrtu_cnf").ligerGetComboBoxManager().selectValue(1);
					}
					if (wwork_flag == '未施工') {
						$("#wwork_flag").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#wwork_flag").ligerGetComboBoxManager().selectValue(1);
					}
					if (p_purtu_cnf == '未接') {
						$("#p_purtu_cnf").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#p_purtu_cnf").ligerGetComboBoxManager().selectValue(1);
					}
					if (p_dlt_cnf == '无') {
						$("#p_dlt_cnf").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#p_dlt_cnf").ligerGetComboBoxManager().selectValue(1);
					}
					if (wabnormal_flag == '正常') {
						$("#wabnormal_flag").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#wabnormal_flag").ligerGetComboBoxManager().selectValue(1);
					}
	               	document.getElementById("p_ugtem_num").value= p_ugtem_num;
	               	document.getElementById("remark").value= remark;
	               	document.getElementById("wwork_date").value= wwork_date;
	               	document.getElementById("wabnormal_date").value= wabnormal_date;
	               	document.getElementById("wells_num").value= wells_num;
	               	document.getElementById("wells_nam").value= wells_nam;
	               	document.getElementById("status_value").value= status_value;
	               	
	               	/*if (status_value != null && typeof(status_value)!="undefined" && status_value != '') {
						$("#status_value").ligerGetComboBoxManager().selectValue(status_value);
						
					}
					else {
						document.getElementById("status_value").value= status_value;
					}*/
	               	document.getElementById("commissioning_date").value= commissioning_date;
	               	document.getElementById("scan_time").value= scan_time;
	               	document.getElementById("phase").value= phase;
	               	document.getElementById("dyear").value= dyear;
	               	document.getElementById("org_id").value= org_id;
	               	document.getElementById("well_rtu_adr").value= well_rtu_adr;
	               	document.getElementById("output_coefficient").value= output_coefficient ; //分产系数
	               	document.getElementById("prod_stages").value= prod_stages ; //生产阶段	
	               	if (p_prod_mode != null && typeof(p_prod_mode)!="undefined" && p_prod_mode != '') {
	               			$("#p_prod_mode").ligerGetComboBoxManager().selectValue(p_prod_mode);
	               	}else{
	               			document.getElementById("p_prod_mode").value= "" ; //P井生产方式
	               	}
	            	$("#i_prod_mode").ligerGetComboBoxManager().selectValue(i_prod_mode);
	               /*	if (i_prod_mode != null && typeof(i_prod_mode)!="undefined" && i_prod_mode != '') {
	               			$("#i_prod_mode").ligerGetComboBoxManager().selectValue(i_prod_mode);
	               	}else{
	               			document.getElementById("i_prod_mode").value= "" ; //I井生产方式
	               	}*/
	               	//加密时间-加密间隔
	               	/*if (increase_freq_flag == '已加密') {
						$("#increase_freq_flag").ligerGetComboBoxManager().selectValue(0);
					}
					else {
						$("#increase_freq_flag").ligerGetComboBoxManager().selectValue(1);
					}*/
					document.getElementById("increase_freq_flag").value=increase_freq_flag;
	               	document.getElementById("start_increase_freq_time").value=start_increase_freq_time;
	                document.getElementById("end_increase_freq_time").value=end_increase_freq_time;
	                document.getElementById("increase_interval").value=increase_interval;
			 }else if(flg == 1){
			 		document.getElementById("sagdid").value = "";
	               	document.getElementById("ljjd_s").value = "";
	               	document.getElementById("jhmc").value = "";
	               	document.getElementById("jhmc_s").value= "";
	               	document.getElementById("dtfmc_s").value="";
	               	document.getElementById("tdh").value= "";
	               	//document.getElementById("auf").value= "";
	               	document.getElementById("whrtu_cnf").value= "";
	            	$("#jrbz").ligerGetComboBoxManager().selectValue("");
	            	$("#whrtu_cnf").ligerGetComboBoxManager().selectValue("");
	            	$("#auf").ligerGetComboBoxManager().selectValue("");
	            	$("#p_ugrtu_cnf").ligerGetComboBoxManager().selectValue("");
	            	$("#wwork_flag").ligerGetComboBoxManager().selectValue("");
	            	$("#p_purtu_cnf").ligerGetComboBoxManager().selectValue("");
	            	$("#p_dlt_cnf").ligerGetComboBoxManager().selectValue("");
	            	$("#wabnormal_flag").ligerGetComboBoxManager().selectValue("");
	               	//document.getElementById("wsrtu_cnf").value= "";
	               //	document.getElementById("p_ugrtu_cnf").value= "";
	               	document.getElementById("p_ugtem_num").value= "";
	               	//document.getElementById("p_purtu_cnf").value= "";
	               	document.getElementById("remark").value= "";
	               	//document.getElementById("wwork_flag").value= "";
	               	document.getElementById("wwork_date").value= "";
	               	//document.getElementById("wabnormal_flag").value= "";
	               	document.getElementById("wabnormal_date").value= "";
	               	document.getElementById("wells_num").value= "";
	               	document.getElementById("wells_nam").value= "";
	               	document.getElementById("status_value").value= "";
	               	document.getElementById("commissioning_date").value= "";
	               	document.getElementById("scan_time").value= "";
	               	document.getElementById("phase").value= "";
	               	document.getElementById("dyear").value= "";
	               	//document.getElementById("p_dlt_cnf").value= "";
	               	document.getElementById("org_id").value= "";
	               	document.getElementById("well_rtu_adr").value= "";
	             	document.getElementById("output_coefficient").value= ""; //分产系数
	               	document.getElementById("prod_stages").value= ""; //生产阶段	
	               	$("#p_prod_mode").ligerGetComboBoxManager().selectValue("");
	               	$("#i_prod_mode").ligerGetComboBoxManager().selectValue("");
	               //	document.getElementById("p_prod_mode").value= ""; //P井生产方式
	               //	document.getElementById("i_prod_mode").value= ""; //I井生产方式
	               	$("#wellAreaSelf").ligerGetComboBoxManager().selectValue("");
	              	//加密时间-加密间隔
					$("#increase_freq_flag").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("start_increase_freq_time").value=start_increase_freq_time;
	                document.getElementById("end_increase_freq_time").value=end_increase_freq_time;
	                document.getElementById("increase_interval").value=increase_interval;
			 }
			 		
      }

      function setStationData(areaid,proData) {
  		jQuery.ajax({
  			type : 'post',
  			url:'sagd_queryStationInfo.action',
  			data: {"areaid":areaid},
  			
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

      function setWellInitData(data,proData) {
  		jQuery.ajax({
  			type : 'post',
  			url:'sagd_queryWellNameInfo.action',
  			data: {"orgid":data},
  			
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
  	}
      function setGhData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'manifoldBasicInfo_queryManifoldNameInfo.action',
				data: {"orgid":data,"ghorg":'orgid'},
				success : function(jsondata) {
					//alert("data:" + data);
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("manifold").setData(eval('(' + jsondata + ')'));
						var dataObj = $.parseJSON(jsondata);
						//$.each(dataObj, function(key,val){
							//$.each(val, function(keys,vals){
								//alert(keys);
								//alert(vals);
							//});
							//return false;
						//});
						$("#manifold").ligerGetComboBoxManager().selectValue(gh_id);
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
    	//$("#areablock1").ligerGetComboBoxManager().setValue('');
    	$("#blockstationname").ligerGetComboBoxManager().setValue('');
    	$("#wellnum").ligerGetComboBoxManager().setValue('');
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
  		liger.get("areablock1").setData(areaText);
  		liger.get("wellnum").setData(wellText);
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
											url : 'sagd_removeSagd.action',
											data: {"sagdId":sagdid,"org_id":org_id},
											async : false,
											success : function(data) { 
											if (data != null && typeof(data)!="undefined"){
												var outData = eval('(' + data + ')');
												if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
													$.ligerDialog.error(outerror(jsondata));
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
													$.ligerDialog.success('SAGD井基础删除成功！');
												}
													
												}
											},
											error : function(data) {
													$.ligerDialog.error(outerror(data));
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
      function onExport() {
   		var blockstationname1=$("#blockstationname").val();
		var areablock1=$("#areablock1").val()+'';
		var wellnum1=$("#wellnum").val();
		var prod_stages1=$("#prod_stages1").val();
		var status_value1=$("#status_value1").val();
		var jrbz1=$("#jrbz1").val();
		var dyear1=$("#dyear1").val();
  		var totalNum = 0;
  		var url = "sagd_searchSagdBaisc.action?blockstationname="+encodeURIComponent(blockstationname1)+"&areablock="+encodeURIComponent(areablock1)+"&wellnum="+encodeURIComponent(wellnum1)+"&prod_stages="+encodeURIComponent(prod_stages1)+"&status_value="+encodeURIComponent(status_value1)+"&dyear1="+encodeURIComponent(dyear1)+"&jrbz="+encodeURIComponent(jrbz1)+'&totalNum=export';
  		var urls = "sagd_searchSagdBaisc.action?blockstationname="+encodeURIComponent(blockstationname1)+"&areablock="+encodeURIComponent(areablock1)+"&wellnum="+encodeURIComponent(wellnum1)+"&prod_stages="+encodeURIComponent(prod_stages1)+"&status_value="+encodeURIComponent(status_value1)+"&dyear1="+encodeURIComponent(dyear1)+"&jrbz="+encodeURIComponent(jrbz1)+'&totalNum=totalNum';
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

		<form name="formsearch" method="post" id="form1">
			<input type="hidden" id="gh_id1" name="gh_id1" /> <input
				type="hidden" id="datatype" name="datatype" />
			<table border="0" cellspacing="1">
				<tr>
					<td align="right" class="l-table-edit-td"
						style="width:80px;display:none">区块：</td>
					<td align="left" class="l-table-edit-td"
						style="width:80px;display:none"><input type="text"
						id="areablock" name="areablock" /></td>
					<td align="right" class="l-table-edit-td" style="width:80px; display: none">井区块：</td>
					<td align="left" class="l-table-edit-td" style="width:80px;display: none"><input
						type="text" id="areablock1" name="areablock1" /></td>
					<td align="right" class="l-table-edit-td" style="width:80px;display: none">注转站：</td>
					<td align="left" class="l-table-edit-td" style="width:80px; display: none"><input
						type="text" id="blockstationname" name="blockstationname" /></td>
						
					<td align="right" class="l-table-edit-td" style="width:80px">所属采油站：</td>
					<td align="left" class="l-table-edit-td" style="width:80px"><input
						type="text" id="oilationname" name="oilationname" /></td>
					<td align="right" class="l-table-edit-td" style="width:80px">管汇：</td>
					<td align="left" class="l-table-edit-td" style="width:80px"><input
						type="text" id="ghmc" name="ghmc" /></td>
						
						
						
					<td align="right" class="l-table-edit-td" style="width:80px">井号：</td>
					<td align="left" class="l-table-edit-td" style="width:80px"><input
						type="text" id="wellnum" name="wellnum" /></td>
					<td align="right" class="l-table-edit-td" style="width:80px">接入标志：</td>
					<td align="right" class="l-table-edit-td" style="width:80px">
						<input type="text" id="jrbz1" name="jrbz1" />
					</td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td" style="width:80px">生产阶段：</td>
					<td align="right" class="l-table-edit-td" style="width:80px">
						<input type="text" id="prod_stages1" name="prod_stages1" />
					</td>
					<td align="right" class="l-table-edit-td" style="width:80px;">建设状态：</td>
					<td align="right" class="l-table-edit-td" style="width:80p;">
						<input type="text" id="status_value1" name="status_value1" />
					</td>
					<td align="right" class="l-table-edit-td" style="width:80px;">设计产能年:</td>
					<td align="right" class="l-table-edit-td" style="width:80px;">
						<input type="text" id="dyear1" name="dyear1" />
					</td>
					<td align="right" class="l-table-edit-td" style="width:80px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
					<td align="right" class="l-table-edit-td" style="width:80px">
						<a href="javascript:onExport()" class="l-button"
						style="width:100px">导出报表</a>
					</td>
				</tr>

			</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

			<div id="maingrid"></div>

			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<div id="mainsearch2" style=" width:99%">
				<div id="edituser" style="width:99%;height: 100px; display:none;">
					<div id="errorLabelContainer"></div>
					<table cellpadding="0" cellspacing="0" class="l-table-edit"
						border="0">
						<tr>
							<td align="left" class="l-table-edit-td" >井号名称:</td>
							<td align="left" class="l-table-edit-td" >所属井区块:</td>
							<td align="left" class="l-table-edit-td" >所属注转站:</td>
							<td align="left" class="l-table-edit-td" >所属管汇:</td>
							<td align="left" class="l-table-edit-td">建设生产状态:</td>
							<td align="left" class="l-table-edit-td">生产阶段:</td>
							<td align="left" class="l-table-edit-td">P井生产方式:</td>
							<td align="left" class="l-table-edit-td">I井生产方式:</td>
							<td align="left" class="l-table-edit-td">投产日期:</td>
							<td align="left" class="l-table-edit-td">设计产能年:</td>
							<td align="left" class="l-table-edit-td" >井号编码:</td>
							<td align="left" class="l-table-edit-td" >多通阀编码:</td>
							<%--
		                
		                  <td align="left" class="l-table-edit-td" >蒸气控制器接入:</td>
		            --%>
						</tr>
						<tr>
							<td align="left" class="l-table-edit-td"><input name="jhmc"
								type="text" id="jhmc" ltype="text"
								validate="{required:true,minlength:1,maxlength:20}" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="wellAreaSelf" type="text" id="wellAreaSelf" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="blockstationname1" type="text" id="blockstationname1"
								ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
							</td>

							<td align="left" class="l-table-edit-td"><input
								name="manifold" type="text" id="manifold" ltype="text"
								validate="{required:true,minlength:1,maxlength:20}" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="status_value" type="text" id="status_value" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="prod_stages" type="text" id="prod_stages" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="p_prod_mode" type="text" id="p_prod_mode" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="i_prod_mode" type="text" id="i_prod_mode" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="commissioning_date" type="text" id="commissioning_date"
								ltype="date" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="dyear" type="text" id="dyear" ltype="text"
								validate="{required:true}" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="jhmc_s" type="text" id="jhmc_s" ltype="text" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="dtfmc_s" type="text" id="dtfmc_s" ltype="text" /></td>
							</tr>
							<tr>
							
							<td align="left" class="l-table-edit-td" >通道号:</td>
							<td align="left" class="l-table-edit-td">施工井标志:</td>
							<td align="left" class="l-table-edit-td">施工开始日期:</td>
							<td align="left" class="l-table-edit-td">异常井标志:</td>
							<td align="left" class="l-table-edit-td">异常井开始时间:</td>
							<td align="left" class="l-table-edit-td">加密标志:</td>
							<td align="left" class="l-table-edit-td">加密起始时间:</td>
							<td align="left" class="l-table-edit-td">加密结束时间:</td>
							<td align="left" class="l-table-edit-td">加密间隔:</td>
							<!-- <td align="left" class="l-table-edit-td" >上自动化标志:</td> -->
							<td align="left" class="l-table-edit-td" >井口控制器接入:</td>
							<td align="left" class="l-table-edit-td">井下控制器接入:</td>
							<td align="left" class="l-table-edit-td">井下温度点数:</td>
							</tr>
							<tr>
							
							<td align="left" class="l-table-edit-td"><input name="tdh"
								type="text" id="tdh" ltype="text" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="wwork_flag" type="text" id="wwork_flag" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="wwork_date" type="text" id="wwork_date" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="wabnormal_flag" type="text" id="wabnormal_flag"
								ltype="number" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="wabnormal_date" type="text" id="wabnormal_date"
								ltype="date" /></td>
								
							<td align="left" class="l-table-edit-td"><input
								name="increase_freq_flag" type="text" id="increase_freq_flag"
								ltype="number" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="start_increase_freq_time" type="text" id="start_increase_freq_time"
								ltype="date" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="end_increase_freq_time" type="text" id="end_increase_freq_time"
								ltype="date" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="increase_interval" type="text" id="increase_interval"
								ltype="number" /></td>
							
							<td align="left" class="l-table-edit-td"><input
								name="whrtu_cnf" type="text" id="whrtu_cnf" ltype="text"
								validate="{required:true,minlength:1,maxlength:2}" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="p_ugrtu_cnf" type="text" id="p_ugrtu_cnf" ltype="number" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="p_ugtem_num" type="text" id="p_ugtem_num" ltype="text"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
							</tr>
							<tr>
							
							<td align="left" class="l-table-edit-td">抽油机控制器接入:</td>
							<td align="left" class="l-table-edit-td">井口数:</td>
							<td align="left" class="l-table-edit-td">井口名称列举:</td>
							<td align="left" class="l-table-edit-td">ifix扫描时间:</td>
							<td align="left" class="l-table-edit-td">ifix相位时间:</td>
							<td align="left" class="l-table-edit-td">有无电流图:</td>
							<td align="left" class="l-table-edit-td">井口RTU地址:</td>
							<td align="left" class="l-table-edit-td" >服务器逻辑节点名:</td>
							<td align="left" class="l-table-edit-td">接入标志:</td>
							<td align="left" class="l-table-edit-td">分产系数:</td>
							<td align="left" class="l-table-edit-td">备注:</td>
							</tr>
							<tr>
							
							<td align="left" class="l-table-edit-td"><input
								name="p_purtu_cnf" type="text" id="p_purtu_cnf" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="wells_num" type="text" id="wells_num" ltype="text"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="wells_nam" type="text" id="wells_nam" ltype="text" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="scan_time" type="text" id="scan_time" ltype="text"
								validate="{required:true}" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="phase" type="text" id="phase" ltype="text"
								validate="{required:true}" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="p_dlt_cnf" type="text" id="p_dlt_cnf" ltype="text" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="well_rtu_adr" type="text" id="well_rtu_adr" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td"><input
								name="ljjd_s" type="text" id="ljjd_s" ltype="text" /></td>
							<td align="left" class="l-table-edit-td"><input name="jrbz"
								type="text" id="jrbz" ltype="text" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="output_coefficient" type="text" id="output_coefficient"
								validate="{required:true,min:0,max:9.999}" ltype="number" /></td>
							<td align="left" class="l-table-edit-td"><input
								name="remark" type="text" id="remark" ltype="text" /></td>
						</tr>
						<tr>
							<td align="left" class="l-table-edit-td" style="display:none">井口蒸汽控制器接入标志:</td>
							<td align="left" class="l-table-edit-td"
								style="width:80px;display:none"><input name="wsrtu_cnf"
								type="text" id="wsrtu_cnf" ltype="text"
								style="width:80px;display:none" /></td>
							<td align="center" class="l-table-edit-td" colspan="3"><input
								name="sagdid" type="hidden" id="sagdid" /></td>
							<td><input name="org_id" type="hidden" id="org_id" /></td>
							<td align="left" class="l-table-edit-td" style="display:none">
							<input name="auf"id="auf" ltype="text" type="hidden" /></td> 
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>