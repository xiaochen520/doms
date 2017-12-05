<%@ page language="java" import="java.util.*,com.echo.dto.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)session.getAttribute("userInfo");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>常规油井日报维护</title>
 	<!-- <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> -->
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
     <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
     
     	<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerPopupEdit.js"></script>
  
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    
    <script src="../../lib/json2.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
 	<script src="../../lib/checkDate.js" type="text/javascript"></script>
 	<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script type="text/javascript">
    var eee;
    var grid = null;
    var toolbar ;
    var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
    var dataflg = 1;
    var filepath ="";
    var ghname='';
    var oilstation;
    var areaname='';
	var stationname='';
	var wellname='';
	var exportFlag = false;
	var  oilstationname='';
	var RPDRULEWELLPRODID ='';
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
			
                jQuery.ajax({
					type : 'post',
					url : 'ruleWellRPD_ruleWellRPDpageInit.action?arg=wh',
					//async : false,
					success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(data);					
							toolbar = grid.topbar.ligerGetToolBarManager();	
							//toolbar.removeItem("modifyid"); 
							if (toolbar != null && typeof(toolbar)!="undefined"){
								var toolteams = toolbar.options.items;
								//var authorityflg = 0;
								
					 	       	for(var i=0; i<toolteams.length ; i++){
									if(toolteams[i].id == 'importid'){
										//authorityflg = 1;
										//toolbar.removeItem("statementid"); 
										
										jQuery("#importidtable").css('display','block');
										jQuery("#fileBrowser").css('display','block');
										
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
				//seachSelectData();
				//getButtonInit();
                var proData = [{ id: 1 , text: '' }];
                //采油站
                $("#oilstationname").ligerComboBox({
					url:'rulewell_queryOilSationInfo.action',
					valueField: 'id',
					valueFieldID: 'oilationid',
					textField: 'text',
					selectBoxHeight:350,
					selectBoxWidth:100,
					autocomplete:true,
					hideOnLoseFocus:true,
				      onSelected:function (data){
    				liger.get("banzu").setValue('');
    				liger.get("blockstationname").setValue('');
    				liger.get("blockstationname").setEnabled(true); 
    				liger.get("boilersName").setValue('');
    				liger.get("wellName").setValue('');
    				liger.get("banzu").setEnabled(true); 
    					if (data != null && typeof(data)!="undefined" && data != ''){
    						setOilChangeDataq($("#oilstationname").val());
    					}
    				}
				});
        		//班组
                $("#banzu").ligerComboBox({
        			//url:'ruleWellRPD_queryTeamInfo.action',
        			url:'searchQueryAll_searchAllGroup.action',
                    valueField: 'id',
        			valueFieldID: 'banzuid',
        			textField: 'text',
        			selectBoxHeight:400,
        			selectBoxWidth:140,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                	onSelected:function (data){
    				//liger.get("StationEdit").setValue('');
    				liger.get("blockstationname").setDisabled(true); 
    				liger.get("boilersName").setValue('');
    				if (data != null && typeof(data)!="undefined" && data != ''){
    					ghChangeDatasq("","",$("#banzuid").val(),"");
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
    				liger.get("banzu").setDisabled(true); 
    				//liger.get("groupTeamEdit").setValue('');
    				liger.get("boilersName").setValue('');
        			if (data != null && typeof(data)!="undefined" && data != ''){
        				setGhDataq($("#stationsid").val(),proData);
    					//ghChangeDatas("",$("#StationEditID").val(),"","");
    				}else{
    					//ghChangeDatas("",data,"","");
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
    				if(data !=null && data !=''){
						onChangeManiWellq( "","",$("#boilersName").val())
        				}
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
    				//url:'comboboxdata_searchSwitchInflg.action?args=ALL',
    				hideOnLoseFocus:true,
 					autocomplete:true,
 		                data: [
 		                    { text: '未审核', id: '0' },
 		                    { text: '已审核', id: '1' }
 		                    
 		                ], valueFieldID: 'shstatusId',
 					initText :''
 		         });  
 		         
 		          
 		         
    			//编辑  单位 采油站↓
    			$("#unitOil").ligerComboBox({
    				url:'rulewell_queryOilSationInfo.action?arg=manifold',
    				valueField: 'id',
    				valueFieldID: 'unitOilid',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:98,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    				onSelected:function (data){
    				liger.get("groupTeamEdit").setValue('');
    				liger.get("StationEdit").setValue('');
    				liger.get("StationEdit").setEnabled(true); 
    				liger.get("maniEdit").setValue('');
    				liger.get("WellNameEdit").setValue('');
    				liger.get("groupTeamEdit").setEnabled(true); 
    					if (data != null && typeof(data)!="undefined" && data != ''){
    						setOilChangeData($("#unitOil").val());
    					}
    				}
    			});
    			//班组
    			$("#groupTeamEdit").ligerComboBox({
    				url:'searchQueryAll_searchAllGroup.action',
        			valueField: 'id',
        			valueFieldID: 'groupTeamEditid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:98,
        			hideOnLoseFocus:true,
                    autocomplete:true,
        			onSelected:function (data){
    				//liger.get("StationEdit").setValue('');
    				liger.get("StationEdit").setDisabled(true); 
    				liger.get("maniEdit").setValue('');
    				if (data != null && typeof(data)!="undefined" && data != ''){
    					ghChangeDatas("","",$("#groupTeamEditid").val(),"");
    				}
        			}
        		});
        		//接转战
        		$("#StationEdit").ligerComboBox({
        			url:'rulewell_queryStationInfo.action?oilid=manifold',
        			valueField: 'id',
        			valueFieldID: 'StationEditID',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:98,
        			hideOnLoseFocus:true,
                    autocomplete:true,
        			onSelected:function (data){
        		
    				liger.get("groupTeamEdit").setDisabled(true); 
    				//liger.get("groupTeamEdit").setValue('');
    				liger.get("maniEdit").setValue('');
        			if (data != null && typeof(data)!="undefined" && data != ''){
        				setGhData($("#StationEditID").val(),proData);
    					//ghChangeDatas("",$("#StationEditID").val(),"","");
    				}else{
    					//ghChangeDatas("",data,"","");
        				}
        			}
        		});
        		//管汇
    			$("#maniEdit").ligerComboBox({
    				url:'manifoldBasicInfo_queryManifoldNameInfo.action',
        			valueField: 'id',
        			valueFieldID: 'maniEditid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:98,
        			hideOnLoseFocus:true,
                    autocomplete:true,
        			onSelected:function (data){
        				if(data !=null && data !=''){
							onChangeManiWell( "","",$("#maniEdit").val())
            				}
        			}
        		});
        		//常规稠油井
    			$("#WellNameEdit").ligerComboBox({
    				url:'rulewell_queryWellInfo.action',
    				valueField: 'id',
    				valueFieldID: 'WellNameEditId',
    				textField: 'text',
    				selectBoxHeight:350,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            autocomplete:true
    			});
    			//编辑↑
			   $("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));

			 $("#rq").ligerDateEditor({
                   format: "yyyy-MM-dd",
                 //  label: '格式化日期',
                   labelWidth: 100,
                   //showTime: true,
                   labelAlign: 'center',
                   cancelable : false
           });
            $("#txtDate1").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            $("#dayreport").ligerTip();

			
			
		    liger.get("blockstationname").setDisabled(true); 
			$("#banzu").val("<%=user.getTeam()%>");
			$("#oilstationname").val("<%=user.getCyz()%>");
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
                	 	var RPDRULEWELLPRODID  = $("#RPDRULEWELLPRODID").val();
                	  	var THINWELLRPD   = $("#RULEWELLRPDID").val();
                     	var WELLID   = $("#ORGID").val();
                  		var WellNameEdit = $("#WellNameEdit").val();
                	   	var rq = $("#rq").val();
                		var PROTT = $("#PROTT").val();
                		var STROKE = $("#STROKE").val();
                		var AT_TIMES = $("#AT_TIMES").val();
                		var NOZZLE = $("#NOZZLE").val();
                		var PRESSURE = $("#PRESSURE").val();
                		var TCPR = $("#TCPR").val();
                		var BACKPRE = $("#BACKPRE").val();
                		var TOT = $("#TOT").val();
                		var NFV = $("#NFV").val();
                		var GASP = $("#GASP").val();
                		var SAMPLI = $("#SAMPLI").val();
                		var PUMPING_TIME = $("#PUMPING_TIME").val();
                		var PUMPING_MACHINE = $("#PUMPING_MACHINE").val();
                		var PUMPING_DESCRIPTION = $("#PUMPING_DESCRIPTION").val();
                		var REMARK = $("#REMARK").val();
                		
                 
					
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1  ||  operate == 2){
              	  		
              	   	   jQuery.ajax({
									type : 'post',
									url : 'ruleWellRPD_addOrUpdateRuleRPD.action',
									//async : false,
									data: {"WellNameEdit":WellNameEdit,"rq":rq ,"PROTT":PROTT,"STROKE":STROKE ,"AT_TIMES":AT_TIMES,"RPDRULEWELLPRODID":RPDRULEWELLPRODID,
											"NOZZLE":NOZZLE,"PRESSURE":PRESSURE,"TCPR":TCPR,"BACKPRE":BACKPRE,"TOT":TOT,"NFV":NFV,
											"GASP":GASP,"SAMPLI":SAMPLI,"PUMPING_TIME":PUMPING_TIME,"PUMPING_MACHINE":PUMPING_MACHINE,
											"PUMPING_DESCRIPTION":PUMPING_DESCRIPTION,"REMARK":REMARK,"THINWELLRPD":THINWELLRPD,"WELLID":WELLID},
									success : function(data) { 
										//$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
										if (data != null && typeof(data)!="undefined" && "1" == data){
												onSubmit();
												setpage1(1); //隐藏编辑界面
												setItemsd(2); //去掉隐藏，显示按钮
												alert('操作成功!')
												operate = 0;
											}else if(typeof(typeof(eval('(' + data + ')').ERRMSG)) != "undefined"){
	 											var outData = eval('(' + data + ')');
	 											$.ligerDialog.error(outData.ERRMSG);
	 										}else{
												$.ligerDialog.error(outerror(data));
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
        							
									if (rowdata.WELL_NAME != null && typeof(rowdata.WELL_NAME)!="undefined"){
										WELL_NAME= rowdata.WELL_NAME;
									 }else{
										 WELL_NAME= "";
									 }
									if (rowdata.REPORT_DATE != null && typeof(rowdata.REPORT_DATE)!="undefined"){
										REPORT_DATE= rowdata.REPORT_DATE;
									 }else{
										 REPORT_DATE= "";
									 }
									if (rowdata.OILSTATIONNAME != null && typeof(rowdata.OILSTATIONNAME)!="undefined"){
										OILSTATIONNAME= rowdata.OILSTATIONNAME;
									 }else{
										 OILSTATIONNAME= "";
									 }
									if (rowdata.TEAMNAME != null && typeof(rowdata.TEAMNAME)!="undefined"){
										TEAMNAME= rowdata.TEAMNAME;
									 }else{
										 TEAMNAME= "";
									 }
									if (rowdata.BLOCKSTATIONNAME != null && typeof(rowdata.BLOCKSTATIONNAME)!="undefined"){
										BLOCKSTATIONNAME= rowdata.BLOCKSTATIONNAME;
									 }else{
										 BLOCKSTATIONNAME= "";
									 }
									if (rowdata.MANIFOLD != null && typeof(rowdata.MANIFOLD)!="undefined"){
										MANIFOLD= rowdata.MANIFOLD;
									 }else{
										 MANIFOLD= "";
									 }
									if (rowdata.PROC_TIME_RATION != null && typeof(rowdata.PROC_TIME_RATION)!="undefined"){
										PROC_TIME_RATION= rowdata.PROC_TIME_RATION;
									 }else{
										 PROC_TIME_RATION= "";
									 }
									if (rowdata.STROKE != null && typeof(rowdata.STROKE)!="undefined"){
										STROKE= rowdata.STROKE;
									 }else{
										 STROKE= "";
									 }
									if (rowdata.JIG_FREQUENCY != null && typeof(rowdata.JIG_FREQUENCY)!="undefined"){
										JIG_FREQUENCY= rowdata.JIG_FREQUENCY;
									 }else{
										 JIG_FREQUENCY= "";
									 }
									if (rowdata.FLOW_NIPPLE != null && typeof(rowdata.FLOW_NIPPLE)!="undefined"){
										FLOW_NIPPLE= rowdata.FLOW_NIPPLE;
									 }else{
										 FLOW_NIPPLE= "";
									 }
									if (rowdata.TUBING_PRES != null && typeof(rowdata.TUBING_PRES)!="undefined"){
										TUBING_PRES= rowdata.TUBING_PRES;
									 }else{
										 TUBING_PRES= "";
									 }
									if (rowdata.CASING_PRES != null && typeof(rowdata.CASING_PRES)!="undefined"){
										CASING_PRES= rowdata.CASING_PRES;
									 }else{
										 CASING_PRES= "";
									 }
									if (rowdata.BACK_PRES != null && typeof(rowdata.BACK_PRES)!="undefined"){
										BACK_PRES= rowdata.BACK_PRES;
									 }else{
										 BACK_PRES= "";
									 }
									if (rowdata.OIL_TEMP != null && typeof(rowdata.OIL_TEMP)!="undefined"){
										OIL_TEMP= rowdata.OIL_TEMP;
									 }else{
										 OIL_TEMP= "";
									 }
									if (rowdata.LIQUID_OUTPUT != null && typeof(rowdata.LIQUID_OUTPUT)!="undefined"){
										LIQUID_OUTPUT= getRemoveData(rowdata.LIQUID_OUTPUT);
									 }else{
										 LIQUID_OUTPUT= "";
									 }
									if (rowdata.GAS_OUTPUT != null && typeof(rowdata.GAS_OUTPUT)!="undefined"){
										GAS_OUTPUT= rowdata.GAS_OUTPUT;
									 }else{
										 GAS_OUTPUT= "";
									 }
									if (rowdata.SAMPLING != null && typeof(rowdata.SAMPLING)!="undefined"){
										SAMPLING= rowdata.SAMPLING;
									 }else{
										 SAMPLING= "";
									 }
									if (rowdata.RUNTIME != null && typeof(rowdata.RUNTIME)!="undefined"){
										RUNTIME= rowdata.RUNTIME;
									 }else{
										RUNTIME= "";
									 }
									if (rowdata.PUMP_ERROR_TIME != null && typeof(rowdata.PUMP_ERROR_TIME)!="undefined"){
										PUMP_ERROR_TIME= rowdata.PUMP_ERROR_TIME;
									 }else{
										 PUMP_ERROR_TIME= "";
									 }
									if (rowdata.PUMP_ERROR_DESC != null && typeof(rowdata.PUMP_ERROR_DESC)!="undefined"){
										PUMP_ERROR_DESC= rowdata.PUMP_ERROR_DESC;
									 }else{
										 PUMP_ERROR_DESC= "";
									 }
									if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
										REMARK= rowdata.REMARK;
									 }else{
										 REMARK= "";
									 }
									if (rowdata.RULE_ORG_ID != null && typeof(rowdata.RULE_ORG_ID)!="undefined"){
										RULEWELLRPDID= rowdata.RULE_ORG_ID;
									 }else{
										 RULEWELLRPDID= "";
									 }
									//shenheren
									if (rowdata.CLASS_CHECK_OPER != null && typeof(rowdata.CLASS_CHECK_OPER)!="undefined"){
										CLASS_CHECK_OPER= rowdata.CLASS_CHECK_OPER;
									 }else{
										 CLASS_CHECK_OPER= "";
									 }
									if (rowdata.GEOLOGY_CHECK_OPER != null && typeof(rowdata.GEOLOGY_CHECK_OPER)!="undefined"){
										GEOLOGY_CHECK_OPER= rowdata.GEOLOGY_CHECK_OPER;
									 }else{
										 GEOLOGY_CHECK_OPER= "";
									 }
									
									RPDRULEWELLPRODID = rowdata.RPD_RULE_WELL_PROD_ID;
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }
         function assignM(flg){
			 if(flg == 2){

				 	document.getElementById("unitOil").value = OILSTATIONNAME;
			 		document.getElementById("groupTeamEdit").value = TEAMNAME;
			 		document.getElementById("StationEdit").value = BLOCKSTATIONNAME;
			 		document.getElementById("maniEdit").value = MANIFOLD;
	             	document.getElementById("WellNameEdit").value =WELL_NAME ;
	           	   	document.getElementById("rq").value = REPORT_DATE;
	           		document.getElementById("PROTT").value =PROC_TIME_RATION ;
	           		document.getElementById("STROKE").value =STROKE ;
	           		document.getElementById("AT_TIMES").value =JIG_FREQUENCY ;
	           		document.getElementById("NOZZLE").value =FLOW_NIPPLE ;
	           		document.getElementById("PRESSURE").value =TUBING_PRES ;
	           		document.getElementById("TCPR").value =CASING_PRES ;
	           		document.getElementById("BACKPRE").value =BACK_PRES ;
	           		document.getElementById("TOT").value =OIL_TEMP ;
	           		document.getElementById("NFV").value =LIQUID_OUTPUT ;
	           		document.getElementById("GASP").value =GAS_OUTPUT ;
	           		document.getElementById("SAMPLI").value =SAMPLING ;
	           		document.getElementById("PUMPING_TIME").value =RUNTIME ;
	           		document.getElementById("PUMPING_MACHINE").value =PUMP_ERROR_TIME ;
	           		document.getElementById("PUMPING_DESCRIPTION").value =PUMP_ERROR_DESC ;
	           		document.getElementById("REMARK").value =REMARK ;
	           		
	           		document.getElementById("RULEWELLRPDID").value =RULEWELLRPDID ;
	                document.getElementById("RPDRULEWELLPRODID").value =RPDRULEWELLPRODID ;
           	
			 }else if(flg == 1){
					
				 	document.getElementById("unitOil").value = "";
			 		document.getElementById("groupTeamEdit").value = "";
			 		document.getElementById("StationEdit").value = "";
			 		document.getElementById("maniEdit").value = "";
			 		
					document.getElementById("RULEWELLRPDID").value = "";
					document.getElementById("RPDRULEWELLPRODID").value ="" ;
	             	document.getElementById("WellNameEdit").value = "";
	           	   	document.getElementById("rq").value = "";
	           		document.getElementById("PROTT").value = "";
	           		document.getElementById("STROKE").value = "";
	           		document.getElementById("AT_TIMES").value = "";
	           		document.getElementById("NOZZLE").value = "";
	           		document.getElementById("PRESSURE").value = "";
	           		document.getElementById("TCPR").value = "";
	           		document.getElementById("BACKPRE").value = "";
	           		document.getElementById("TOT").value = "";
	           		document.getElementById("NFV").value = "";
	           		document.getElementById("GASP").value = "";
	           		document.getElementById("SAMPLI").value = "";
	           		document.getElementById("PUMPING_TIME").value = "";
	           		document.getElementById("PUMPING_MACHINE").value = "";
	           		document.getElementById("PUMPING_DESCRIPTION").value = "";
	           		document.getElementById("REMARK").value = "";
			 }
			 		
      }
        
        function setWellInitData(data,proData,wellid) {
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
      		if (wellid != '') {
				$("#wellName").ligerGetComboBoxManager().selectValue(wellid);
			}
      	}
        function setOilChangeData(oilid){

 			var StationText=[{ id: 1 , text: '' }];
 			var GroupText=[{ id: 1 , text: '' }];
 			var ManiText =[{ id: 1 , text: '' }];
 			jQuery.ajax({
 				type : 'post',
 				url:'searchQueryAll_searchOilChangeData.action?nowdata='+parseInt(Math.random()*100000),
 				data: {"oilid":oilid},
 				success : function(jsondata) {
 				var dataObj = $.parseJSON(jsondata);
 					$.each(dataObj, function(key,val){
 						
 						if (key == 'StationText'){
 							StationText = val;
 						}
 						if (key == 'GroupText'){
 							GroupText = val;
 						}
 						if (key == 'ManiText'){
 							ManiText = val;
 						}
 					});
 					setChangeInitData(StationText,GroupText,ManiText);
 				}
 			});
 		   }
 	   function setChangeInitData(StationText,GroupText,ManiText) {
 			liger.get("StationEdit").setData(StationText);
 			liger.get("maniEdit").setData(ManiText);
 			liger.get("groupTeamEdit").setData(GroupText);
 		}
	    function ghChangeDatas(cyzid,zzzid,teamid,param) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchGHcommon.action?nowdata='+parseInt(Math.random()*100000),
				data: {"cyzid":cyzid,"zzzid":zzzid,"teamid":teamid,"param":param},
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("maniEdit").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("maniEdit").setData("");
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
			//return clearSelecteValue;
		}
	    function onChangeManiWell(groupName,StationName, ManiName){
	    	jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchOnChangeManiRuleWell.action?nowdata='+parseInt(Math.random()*100000),
				data: {"groupName":groupName,"StationName":StationName,"ManiName":ManiName},
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("WellNameEdit").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("WellNameEdit").setData("");
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
				url:'manifoldBasicInfo_queryManifoldNameInfo.action?nowdata='+parseInt(Math.random()*100000),
				data: {"orgid":data},
				//async : false,
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("maniEdit").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("maniEdit").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}
		//chaxun
		    function setOilChangeDataq(oilid){

 			var StationText=[{ id: 1 , text: '' }];
 			var GroupText=[{ id: 1 , text: '' }];
 			var ManiText =[{ id: 1 , text: '' }];
 			jQuery.ajax({
 				type : 'post',
 				url:'searchQueryAll_searchOilChangeData.action?nowdata='+parseInt(Math.random()*100000),
 				data: {"oilid":oilid},
 				success : function(jsondata) {
 				var dataObj = $.parseJSON(jsondata);
 					$.each(dataObj, function(key,val){
 						
 						if (key == 'StationText'){
 							StationText = val;
 						}
 						if (key == 'GroupText'){
 							GroupText = val;
 						}
 						if (key == 'ManiText'){
 							ManiText = val;
 						}
 					});
 					setChangeInitDataq(StationText,GroupText,ManiText);
 				}
 			});
 		   }
 	   function setChangeInitDataq(StationText,GroupText,ManiText) {
 			liger.get("blockstationname").setData(StationText);
 			liger.get("boilersName").setData(ManiText);
 			liger.get("banzu").setData(GroupText);
 		}

		    function ghChangeDatasq(cyzid,zzzid,teamid,param) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchGHcommon.action?nowdata='+parseInt(Math.random()*100000),
				data: {"cyzid":cyzid,"zzzid":zzzid,"teamid":teamid,"param":param},
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("boilersName").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("boilersName").setData("");
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
			//return clearSelecteValue;
		}

	function setGhDataq(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'manifoldBasicInfo_queryManifoldNameInfo.action?nowdata='+parseInt(Math.random()*100000),
				data: {"orgid":data},
				//async : false,
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

			    function onChangeManiWellq(groupName,StationName, ManiName){
			    	jQuery.ajax({
						type : 'post',
						url:'searchQueryAll_searchOnChangeManiRuleWell.action?nowdata='+parseInt(Math.random()*100000),
						data: {"groupName":groupName,"StationName":StationName,"ManiName":ManiName},
						success : function(jsondata) {
							if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
								liger.get("wellName").setData(eval('(' + jsondata + ')'));
							}
							else{
								liger.get("wellName").setData("");
							}
						},
						error : function(jsondata) {
							alert("请求数据失败，请重新查询");
						}
					});
				    }
		//
        //为公用页面提供接口方法 
	function onJKSubmit(id,name,datatype,basid){
		var areaname='';
		var stationname='';
		var wellname='';
		var oilstationname='';
		ghname = '';
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
			{name: 'datastype', value: $("#datastype").val() },
			{name: 'oilstationname', value: oilstationname },
			{name: 'areablock', value: areaname },
			{name: 'blockstationname', value: stationname },
			{name: 'ghname', value: ghname },
			{name: 'wellnum', value: wellname},
			{name: 'startDate', value: $("#txtDate").val()},
			{name: 'endDate', value: $("#txtDate1").val()},
			]
		});
		grid.loadData(true);
	}
		

	function onSubmit()
	{
		datastypeid = '';
		if($("#datastype").val() != ''){
			datastypeid = $("#datastypeid").val()
		}
		grid.setOptions({
			parms: [
			{name: 'datastype',value: datastypeid},
			{name: 'oilstationname',value: $("#oilstationname").val()},
			{name: 'groupname',value: $("#banzu").val()},
			{name: 'blockstationname',value: $("#blockstationname").val()},
			{name: 'wellnum',value: $("#wellName").val()},
			{name: 'boilersName' ,value : $("#boilersName").val()},
			{name: 'shstatus' ,value :$("#shstatus").val()},
			{name: 'startDate',value: $("#txtDate").val()},
			{name: 'endDate',value: $("#txtDate1").val()}]
		});
		//setGridHid();
		grid.loadData(true);
	}
	
	
		function getButtonInit(){
				//获取生成日报权限
					 jQuery.ajax({
						type : 'post',
						url : 'sagddrpd_ButtonInit.action',
						//async : false,
						success : function(data) {
						if (data != null && typeof(data)!="undefined"){
							var data1 = eval('(' + data + ')');
							}else{
								$.ligerDialog.error(outerror(data));
							}
						},
						error : function(data) {
					
						}
					});
		}
			
	function dayreport(){
			jQuery.ajax({
				type : 'post',
				url : 'sagddrpd_dayreport.action?nowdata='+parseInt(Math.random()*100000),
				//async : false,
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
      	    	 var myDate = new Date().Format("yyyy-MM-dd");
					if(REPORT_DATE !=myDate){
						//$.ligerDialog.error('cjsj'+ '不是当前系统时间 不让修改');
						$.ligerDialog.error(REPORT_DATE + '不是当前系统时间不让修改')
						break;
						}
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
				if(rows.length == 1){
				
					$.ligerDialog.confirm('确定删除吗?', function (yes) {
						if (yes) {
							//if (CLASS_CHECK_OPER != '' && CLASS_CHECK_OPER != null && CLASS_CHECK_OPER!="undefined" && GEOLOGY_CHECK_OPER != '' && GEOLOGY_CHECK_OPER != null && GEOLOGY_CHECK_OPER!="undefined") {
							//if (CLASS_CHECK_OPER != ''  GEOLOGY_CHECK_OPER != '' ) {
								
							if (RULEWELLRPDID != '' && RULEWELLRPDID != null && RULEWELLRPDID!="undefined") {
								
								//if (GEOLOGY_CHECK_OPER != '' && GEOLOGY_CHECK_OPER != null && GEOLOGY_CHECK_OPER!="undefined"){
									//$.ligerDialog.error('地质组已审核，不许删除')
									//}
								if(yes){
									jQuery.ajax({
									type : 'post',
									url : 'ruleWellRPD_removeRullRPDId.action',
									data: {"RULEWELLRPDID":RPDRULEWELLPRODID,"wellName":WELL_NAME},
									//async : false,
									success : function(data) {
										if (data != null && typeof(data)!="undefined"){
											var outData = eval('(' + data + ')');
												if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
													$.ligerDialog.error(outData.ERRMSG);
												}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
													$.ligerDialog.error(outerror(outData.ERRCODE));
												}else{
													$.ligerDialog.success('删除成功！');
													onSubmit();
												}
											}
										},
									error : function(data) {
									}
									});
								}
							}
						
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
			case "dataready":
				jQuery.ajax({
									type : 'post',
									url : 'ruleWellRPD_Dataready.action?nowdata='+parseInt(Math.random()*100000),
									//async : false,
									success : function(data) {
										if (data != null && typeof(data)!="undefined"){
										var outData = eval('(' + data + ')');
											if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
												$.ligerDialog.error(outData.ERRMSG);
											}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
												$.ligerDialog.error(outerror(outData.ERRCODE));
											}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
												onSubmit();
												$.ligerDialog.success(outData.CONFIRMMSG);
											} else{
												onSubmit();
												$.ligerDialog.success('数据准备成功！');
												
											}
										}
									},
									error : function(data) {
									}
									});
			break;
			case "automate":
				jQuery.ajax({
									type : 'post',
									url : 'ruleWellRPD_Automate.action?nowdata='+parseInt(Math.random()*100000),
									//async : false,
									success : function(data) {
										if (data != null && typeof(data)!="undefined"){
										var outData = eval('(' + data + ')');
											if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
												$.ligerDialog.error(outData.ERRMSG);
											}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
												$.ligerDialog.error(outerror(outData.ERRCODE));
											}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
												onSubmit();
												$.ligerDialog.success(outData.CONFIRMMSG);
											}else{
												onSubmit();
												$.ligerDialog.success('自动化提取成功！');
												
											}
										}
									},
									error : function(data) {
									}
									});
			break;
			case "importd":
				$.ligerDialog.open({
              	    			title: "Excel报表导入",
              	    			//url:'FimportExcel.jsp?pageid=cgcyrb',
              	    			url:'importExcel.jsp?pageid=cgcyrb',
              	    			width: 600,
              	    			height: 260
              	    		});
			break;
			
			
		}
	}
	function seachSelectData()
    {
		jQuery.ajax({
			type : 'post',
			url : 'sagd_queryWellNameInfo.action',
			//async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				wellData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});
		jQuery.ajax({
			type : 'post',
			url : 'sagd_queryDescInfo.action',
			//async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				gjData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});
		jQuery.ajax({
			type : 'post',
			url : 'sagd_queryDescInfo.action?type=cs',
			//async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				csData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});

    }

	function f_onBeforeCheckRow(checked, data, rowid, rowdata)
	{
		if(data.VERIFIER==''|| typeof(data.VERIFIER)=="undefined") {
			return true;
		}
		$.ligerDialog.error("审核后的数据不能进行操作！");
		return false;
	}

	function checkDateTime(date){
	    var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d(:[0-5]?\d)?$/;
	    var r = date.match(reg);
	    if(r == null){
	    	$.ligerDialog.error("日期格式或数据范围不正确  \n (正确格式: yyyy-MM-dd HH:mm)");
	        return false;
	    }else{
	    	return true;
	    }        
	}
	function submitDateTime(date){
	    var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d(:[0-5]?\d)?$/;
	    var r = date.match(reg);
	    if(r == null){
	        return true;
	    }else{
	    	return false;
	    }        
	}

	function readFile(fileBrowser) { 
	    if (navigator.userAgent.indexOf("MSIE")!=-1) {
	    	var picSrc=$('#fileBrowser');
			filepath=picSrc[0].createTextRange();		
	    	//var isIE7 = navigator.userAgent.indexOf('MSIE 7.0') != -1;
           // var isIE8 = navigator.userAgent.indexOf('MSIE 8.0') != -1;
           // var isIE9 = navigator.userAgent.indexOf('MSIE 9.0') != -1;
            // $("#fileBrowser").select();
            //var  sel=document.selection.CreateRange();
           // sel.text = obj;
            //var picSrc=$('#fileBrowser');//取值都是这样
           // filepath = picSrc[0].createTextRange();
            //if(navigator.userAgent.substr(navigator.userAgent.indexOf("MSIE") + 5, 1) >= 9){ 
           	// getObj('onSubmit').focus(); 
          //  }
            // filepath = document.selection.createRange().text;
	        ///    if (isIE7 || isIE8|| isIE9) {
	       //     	 $("#fileBrowser").select();
	        //    	if(isIE9){
	        //    		getObj('onSubmit').focus();  
	        //    	}
                    
            //         filepath = document.selection.createRange().text;
                //IE6获得文件路径
          //      } else { 
           //    	filepath = file.value; 
             //   
             //   }
               alert(filepath);
	    } else if(navigator.userAgent.indexOf("Firefox")!=-1 || navigator.userAgent.indexOf("Mozilla")!=-1)  {
	    	 readFileFirefox(fileBrowser); 
	    } else {
	    	 alert("Not IE or Firefox (userAgent=" + navigator.userAgent + ")"); 
	    }
	       
	} 
	
	function readFileFirefox(fileBrowser) { 
	    try { 
	        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
	    }  
	    catch (e) { 
	        alert('路径错误!'); 
	        return; 
	    } 
	
	    var fileName=fileBrowser.value; 
	    var file = Components.classes["@mozilla.org/file/local;1"] 
	        .createInstance(Components.interfaces.nsILocalFile); 
	    try { 
	        file.initWithPath( fileName.replace(/\//g, "\\\\") ); 
	    } 
	    catch(e) { 
	        if (e.result!=Components.results.NS_ERROR_FILE_UNRECOGNIZED_PATH) throw e; 
	        return; 
	    } 
	
	    if ( file.exists() == false ) { 
	        alert("File '" + fileName + "' not found."); 
	        return; 
	    } 
	    filepath = file.path;
	    alert(file.path); 
	} 
	 var exportFlag=false;
	 function onExport() {
		 //oilationname  blockstationname  boilersName  jrbz1
		     var Eoilationname=$("#oilstationname").val();
		     var GROUPTEAMSear = $("#banzu").val();
 	         var Eblockstationname=$("#blockstationname").val();
 	         var EboilersName = $("#boilersName").val();
 	         var EwellName = $("#wellName").val();
 	         var Eshstatus = $("#shstatus").val();
 	    	 var txtDate = $("#txtDate").val();
 			 var txtDate1 = $("#txtDate1").val();

			if (exportFlag) {
				Eoilationname=oilstationname;
				GROUPTEAMSear = banzu;
				Eblockstationname = blockstationname;
				EboilersName= boilersName;
				EwellName = wellName;
				Eshstatus = shstatus;
				txtDate = startDate;
				txtDate1 = endDate;
			}
			var totalNum = 0;
			
			var url = 'ruleWellRPD_searchRuleRPD.action?totalNum=export'+'&oilstationname='+encodeURIComponent(Eoilationname)+'&blockstationname='+encodeURIComponent(Eblockstationname)+'&wellnum='+encodeURIComponent(EwellName)+'&groupname='+encodeURIComponent(GROUPTEAMSear)+'&boilersName='+encodeURIComponent(EboilersName)+'&shstatus='+encodeURIComponent(Eshstatus)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1);
			var urls = 'ruleWellRPD_searchRuleRPD.action?totalNum=totalNum'+'&oilstationname='+encodeURIComponent(Eoilationname)+'&blockstationname='+encodeURIComponent(Eblockstationname)+'&wellnum='+encodeURIComponent(EwellName)+'&groupname='+encodeURIComponent(GROUPTEAMSear)+'&boilersName='+encodeURIComponent(EboilersName)+'&shstatus='+encodeURIComponent(Eshstatus)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1);
			$.ajax({
				type : 'post',
				url : urls,
				//data :{"oilstationname":Eoilationname,"blockstationname":Eblockstationname,"wellnum":EwellName,"groupname":GROUPTEAMSear,
				//"boilersName":EboilersName,"shstatus":Eshstatus,"startDate":txtDate,"endDate":txtDate1},
				async : false,
				//timeout : '30000',
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
          
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;">
	  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    <div class="searchbox">
	        <form id="form1" class="l-form"> 
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
					
						<td align="right" class="l-table-edit-td" style="width:80px">审核状态：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="shstatus" name="shstatus"/>
		                </td>
		                
		                 <td align="right" class="l-table-edit-td" style="width:60px">日期：</td>
						<td ><input type="text" id="txtDate" ltype="date"/></td>
						<td align="center" class="l-table-edit-td" style="width:40px">--</td>
						<td ><input type="text" id="txtDate1" ltype="date"/></td>
		                
		                
		                <td align="right" class="l-table-edit-td" style="width:10px"></td>
		                <td align="left" class="l-table-edit-td" >
		                	<a href="javascript:onSubmit()" class="l-button" id="onSubmit" style="width:100px">查询</a>
		                </td>
		                 <td align="left" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a>
		                </td>
		                 <!-- <td align="right" class="l-table-edit-td" style="width:80px"  style="display:none" id="importidtable">EXCEL导入：</td>
		                 <td align="left" colspan="2" class="l-table-edit-td" style="width:100px">
		                 <input type="file" name="fileBrowser" id="fileBrowser" onchange="readFile(this)" size="15" style="display:none"/>
		                </td> -->
		                
					</tr>
				</table>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<div id="maingrid"></div> 
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="" style="width:100%;height: 100px; display:none;">
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">单位:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">班组:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">转油站:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">管汇:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">日期:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">生产时间h:</td>
		             <tr/>
		             <tr>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="unitOil" type="text" id="unitOil"  ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="groupTeamEdit" type="text" id="groupTeamEdit" ltype="text" />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="StationEdit" type="text" id="StationEdit"  ltype="text" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="maniEdit" type="text" id="maniEdit"  ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="WellNameEdit" type="text" id="WellNameEdit" ltype="text" />
		                </td>
		            	 
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="rq" type="text" id="rq" ltype="date"  />
		                </td>
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="PROTT" type="text" id="PROTT" ltype="text" />
		                </td>
		                </tr>
		            <tr>
		                  <td align="left" class="l-table-edit-td" style="width:150px">冲程m:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">冲次/min:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">油嘴 mm:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">油压Mpa:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">套压Mpa:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">回压Mpa:</td>
		               	<td align="left" class="l-table-edit-td" style="width:150px">油温℃:</td>
		            </tr>
		            <tr>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="STROKE" type="text" id="STROKE" ltype="text"  />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="AT_TIMES" type="text" id="AT_TIMES" ltype="text"   />
		                </td>
		               
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="NOZZLE" type="text" id="NOZZLE" ltype="text"  />
		                </td>
		            
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="PRESSURE" type="text" id="PRESSURE" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="TCPR" type="text" id="TCPR" ltype="text"  />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="BACKPRE" type="text" id="BACKPRE" />
		                </td>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="TOT" type="text" id="TOT" ltype="text"  />
		                </td>
		               </tr>
		               <tr>
		                 <td align="left" class="l-table-edit-td" style="width:150px">日产液量t:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">日产气量m3:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">取样(油水):</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">抽油机运转时间h:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">抽油机故障时间h:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">抽油机故障描述:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		                 </tr>
		                 <tr>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="NFV" type="text" id="NFV" ltype="text"  />
		                </td>
		            
		                <td align="left" class="l-table-edit-td" >
		                	<input name="GASP" type="text" id="GASP"   ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="SAMPLI" type="text" id="SAMPLI" />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="PUMPING_TIME" type="text" id="PUMPING_TIME" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="PUMPING_MACHINE" type="text" id="PUMPING_MACHINE" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="PUMPING_DESCRIPTION" type="text" id="PUMPING_DESCRIPTION" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="REMARK" type="text" id="REMARK" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		              		 <input name="RULEWELLRPDID" type="hidden" id="RULEWELLRPDID" ltype="text" />
		              		 <input name="RPDRULEWELLPRODID" type="hidden" id="RPDRULEWELLPRODID" ltype="text" />
		                </td>
		                <td align="left">
		                </td>
		            </tr>
		        </table>
		       </div>
		    </form>
		</div>
    
</body>
</html>