<%@ page language="java" import="java.util.*,com.echo.dto.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)session.getAttribute("userInfo");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>稀油油井日报维护</title>
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
       var clearSelecteValue = 0;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({

					type : 'post',
					url : 'thinWellRPDWH_pageInit.action',
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
					liger.get("GROUPTEAMSear").setValue('');
					liger.get("ManiSearch").setValue('');
				
    			},
				onSelected:function (data){
					clearSelecteValue=1;
					if ( data != null && typeof(data)!="undefined" && data != '') {
						setOilChangeDataQ($("#oilstationname1").val());
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
			
			$("#GROUPTEAMSear").ligerComboBox({
				url:'manifoldBasicInfo_queryTeamGInfo.action',
	            valueField: 'id',
				valueFieldID: 'GROUPTEAMSearid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onBeforeSelect:function (data){
					liger.get("rulewellname1").setValue('');
				 	liger.get("ManiSearch").setValue('');
    			},
	            onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						ghChangeDatasQ("","",$("#GROUPTEAMSearid").val(),"");
					}
				}
			});
			
			$("#ManiSearch").ligerComboBox({
				url:'manifoldBasicInfo_queryManifoldNameInfo.action',
	            valueField: 'id',
				valueFieldID: 'ManiSearchid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onBeforeSelect:function (data){
					liger.get("rulewellname1").setValue('');
					
    			},
	            onSelected:function (data){
					if (data != null && typeof(data)!="undefined" && data != ''){
						onChangeManiWell("","",$("#ManiSearch").val());
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
			//编辑区↓
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
				liger.get("maniEdit").setValue('');
				liger.get("WellNameEdit").setValue('');
					if (data != null && typeof(data)!="undefined" && data != ''){
						setOilChangeData($("#unitOil").val());
					}
				}
			});
			$("#groupTeamEdit").ligerComboBox({
				url:'searchQueryAll_searchAllGroup.action',
    			valueField: 'id',
    			valueFieldID: 'groupTeamEditid',
    			textField: 'text',
    			selectBoxHeight:350,
    			selectBoxWidth:120,
    			hideOnLoseFocus:true,
                autocomplete:true,
    			onSelected:function (data){
				//liger.get("StationEdit").setValue('');
				//liger.get("StationEdit").setDisabled(true); 
				liger.get("maniEdit").setValue('');
				if (data != null && typeof(data)!="undefined" && data != ''){
					ghChangeDatas("","",$("#groupTeamEditid").val(),"");
					changeGroupOnWell( $("#groupTeamEdit").val())
				}
    			}
    		});
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
					if (data != null && typeof(data)!="undefined" && data != ''){
						onChangeManiWellq( "","",$("#maniEdit").val())
					}
    			}
    		});
			$("#WellNameEdit").ligerComboBox({
				url:'thinoil_queryWellInfo.action',
    			valueField: 'id',
    			valueFieldID: 'WellNameEditid',
    			textField: 'text',
    			selectBoxHeight:350,
    			selectBoxWidth:98,
    			hideOnLoseFocus:true,
                autocomplete:true,
    			onSelected:function (data){
    			}
    		});
			// $("#rq").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
    		$("#rq").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
    		   $("#txtDate").ligerDateEditor(
    	                {

    	                    format: "yyyy-MM-dd hh:mm",
    	                  //  label: '格式化日期',
    	                    labelWidth: 100,
    	                    showTime: true,
    	                    labelAlign: 'center',
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
   			$("#txtDate").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(myDate().Format("yyyy-MM-dd"));
   			$("#txtDate1").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(secondDate().Format("yyyy-MM-dd"));
               
	     		/*$("#endTIME").ligerDateEditor(
		                {
		                    format: "yyyy-MM-dd hh:mm",
		                    labelWidth: 120,
		                    labelAlign: 'center',
		                    showTime: true,
		                    cancelable : false
		      });*/
		      /*$("#jrbz").ligerComboBox({
				  url:'comboboxdata_searchSwitchInflg.action', 
	     			valueField: 'id',
	     			valueFieldID: 'jrbzid',
	     			textField: 'text',
	     			selectBoxHeight:150,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true
		     		});*/
		     		
		     		
		     		 $("#oilstationname1").val("<%=user.getCyz()%>");
					$("#GROUPTEAMSear").val("<%=user.getTeam()%>");
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
                    var THINWELLRPD   = $("#THINWELLRPD").val();
                    var WELLID   = $("#WELLID").val();
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
									url : 'thinWellRPDWH_addOrUpdate.action',
									async : false,
									data: {"WellNameEdit":WellNameEdit,"rq":rq ,"PROTT":PROTT,"STROKE":STROKE ,"AT_TIMES":AT_TIMES,
											"NOZZLE":NOZZLE,"PRESSURE":PRESSURE,"TCPR":TCPR,"BACKPRE":BACKPRE,"TOT":TOT,"NFV":NFV,
											"GASP":GASP,"SAMPLI":SAMPLI,"PUMPING_TIME":PUMPING_TIME,"PUMPING_MACHINE":PUMPING_MACHINE,
											"PUMPING_DESCRIPTION":PUMPING_DESCRIPTION,"REMARK":REMARK,"THINWELLRPD":THINWELLRPD,"WELLID":WELLID},
									success : function(data) { 
										//$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
										if (data != null && typeof(data)!="undefined" && "1" == data){
												onSubmit();
												setpage1(1); //隐藏编辑界面
												setItemsd(2); //去掉隐藏，显示按钮
												$.ligerDialog.success('操作成功！');
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
              	   
              	   //2-修改
              	   }
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
        							
									if (rowdata.well_name != null && typeof(rowdata.well_name)!="undefined"){
										WELL_NAME = rowdata.well_name;
									 }else{
										WELL_NAME= "";
									 }

									if (rowdata.report_date != null && typeof(rowdata.report_date)!="undefined"){
										rq= rowdata.report_date;
									 }else{
										rq= "";
									 }
									if (rowdata.oilname != null && typeof(rowdata.oilname)!="undefined"){
										oilName = rowdata.oilname;
									 }else{
										 oilName = "";
									 }
									if (rowdata.team != null && typeof(rowdata.team)!="undefined"){
										TEAMNAME= rowdata.team;
									 }else{
										 TEAMNAME= "";
									 }
									//if (rowdata.stationName != null && typeof(rowdata.stationName)!="undefined"){
									//	BLOCKSTATIONNAME = rowdata.stationName;
									 //}else{
										 //BLOCKSTATIONNAME = "";
									// }
									if (rowdata.maniname != null && typeof(rowdata.maniname)!="undefined"){
										MANIFOLD = rowdata.maniname;
									 }else{
										 MANIFOLD = "";
									 }
									if (rowdata.prott != null && typeof(rowdata.prott)!="undefined"){
										PROTT= rowdata.prott;
									 }else{
										 PROTT= "";
									 }
									if (rowdata.stroke != null && typeof(rowdata.stroke)!="undefined"){
										STROKE= rowdata.stroke;
									 }else{
										 STROKE= "";
									 }
									if (rowdata.at_times != null && typeof(rowdata.at_times)!="undefined"){
										AT_TIMES = rowdata.at_times;
									 }else{
										 AT_TIMES = "";
									 }
									if (rowdata.nozzle != null && typeof(rowdata.nozzle)!="undefined"){
										NOZZLE = rowdata.nozzle;
									 }else{
										 NOZZLE = "";
									 }
									if (rowdata.pressure != null && typeof(rowdata.pressure)!="undefined"){
										PRESSURE= rowdata.pressure;
									 }else{
										 PRESSURE = "";
									 }
									if (rowdata.tcpr != null && typeof(rowdata.tcpr)!="undefined"){
										TCPR= rowdata.tcpr;
									 }else{
										 TCPR = "";
									 }
									if (rowdata.backpre != null && typeof(rowdata.backpre)!="undefined"){
										BACKPRE= rowdata.backpre;
									 }else{
										 BACKPRE = "";
									 }
									if (rowdata.tot != null && typeof(rowdata.tot)!="undefined"){
										TOT= rowdata.tot;
									 }else{
										 TOT = "";
									 }
									if (rowdata.nfv != null && typeof(rowdata.nfv)!="undefined"){
										NFV= getRemoveData(rowdata.nfv);
									 }else{
										 NFV= "";
									 }
									if (rowdata.gasp != null && typeof(rowdata.gasp)!="undefined"){
										GASP= rowdata.gasp;
									 }else{
										 GASP = "";
									 }
									if (rowdata.sampli != null && typeof(rowdata.sampli)!="undefined"){
										SAMPLI= rowdata.sampli;
									 }else{
										 SAMPLI = "";
									 }
									if (rowdata.pumping_time != null && typeof(rowdata.pumping_time)!="undefined"){
										PUMPING_TIME= rowdata.pumping_time;
									 }else{
										 PUMPING_TIME = "";
									 }

									if (rowdata.pumping_machine != null && typeof(rowdata.pumping_machine)!="undefined"){
										PUMPING_MACHINE= rowdata.pumping_machine;
									 }else{
										 PUMPING_MACHINE  = "";
									 }
									if (rowdata.pumping_description != null && typeof(rowdata.pumping_description)!="undefined"){
										PUMPING_DESCRIPTION= rowdata.pumping_description;
									 }else{
										 PUMPING_DESCRIPTION = "";
									 }
									if (rowdata.remark != null && typeof(rowdata.remark)!="undefined"){
										REMARK= rowdata.remark;
									 }else{
										 REMARK = "";
									 }
									if (rowdata.thinwellrpd != null && typeof(rowdata.thinwellrpd)!="undefined"){
										THINWELLRPD= rowdata.thinwellrpd;
									 }else{
										 THINWELLRPD  = "";
									 }
									if (rowdata.wellid != null && typeof(rowdata.wellid)!="undefined"){
										WELLID= rowdata.wellid;
									 }else{
										 WELLID = "";
									 }
									if (rowdata.liquid_flag != null && typeof(rowdata.liquid_flag)!="undefined"){
										LIQUID_FLAG= rowdata.liquid_flag;
									 }else{
										 LIQUID_FLAG = "";
									 }
									LIQUID_FLAG
				                	if(operate == 2){
				                		assignM(2);
				                	}
        }
         function assignM(flg){
			 if(flg == 2){
					//$("#wellAreaSelf").ligerGetComboBoxManager().selectValue(jqkid);
			 		//document.getElementById("wellid").value = wellid;
		 		document.getElementById("unitOil").value = oilName;
		 		document.getElementById("groupTeamEdit").value = TEAMNAME;
		 		//document.getElementById("StationEdit").value = BLOCKSTATIONNAME;
		 		document.getElementById("maniEdit").value = MANIFOLD;
			 		
             	document.getElementById("WellNameEdit").value =WELL_NAME ;
           	   	document.getElementById("rq").value = rq;
           		document.getElementById("PROTT").value =PROTT ;
           		document.getElementById("STROKE").value =STROKE ;
           		document.getElementById("AT_TIMES").value =AT_TIMES ;
           		document.getElementById("NOZZLE").value =NOZZLE ;
           		document.getElementById("PRESSURE").value =PRESSURE ;
           		document.getElementById("TCPR").value =TCPR ;
           		document.getElementById("BACKPRE").value =BACKPRE ;
           		document.getElementById("TOT").value =TOT ;
           		document.getElementById("NFV").value =NFV ;
           		document.getElementById("GASP").value =GASP ;
           		document.getElementById("SAMPLI").value =SAMPLI ;
           		document.getElementById("PUMPING_TIME").value =PUMPING_TIME ;
           		document.getElementById("PUMPING_MACHINE").value =PUMPING_MACHINE ;
           		document.getElementById("PUMPING_DESCRIPTION").value =PUMPING_DESCRIPTION ;
           		document.getElementById("REMARK").value =REMARK ;
           		//document.getElementById("LIQUID_FLAG").value =LIQUID_FLAG ;
           		
           		
           		document.getElementById("THINWELLRPD").value =THINWELLRPD ;
                document.getElementById("WELLID").value =WELLID ;	
			 }else if(flg == 1){
					document.getElementById("unitOil").value = "";
			 		document.getElementById("groupTeamEdit").value = "";
			 		//document.getElementById("StationEdit").value = "";
			 		document.getElementById("maniEdit").value = "";
			 		
					document.getElementById("THINWELLRPD").value = "";
	                document.getElementById("WELLID").value = "";
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
	           		//document.getElementById("LIQUID_FLAG").value ="" ;
			 }
			 		
      }

		    function onChangeManiWellq(groupName,StationName, ManiName){
		    	jQuery.ajax({
					type : 'post',
					url:'searchQueryAll_searchOnChangeManiWell.action?nowdata='+parseInt(Math.random()*100000),
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
		    function onChangeManiWell(groupName,StationName, ManiName){
		    	jQuery.ajax({
					type : 'post',
					url:'searchQueryAll_searchOnChangeManiWell.action?nowdata='+parseInt(Math.random()*100000),
					data: {"groupName":groupName,"StationName":StationName,"ManiName":ManiName},
					success : function(jsondata) {
						if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
							liger.get("rulewellname1").setData(eval('(' + jsondata + ')'));
						}
						else{
							liger.get("rulewellname1").setData("");
						}
					},
					error : function(jsondata) {
						alert("请求数据失败，请重新查询");
					}
				});
			    }
 	    function ghChangeDatas(cyzid,zzzid,teamid,param) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchGHcommon.action?nowdata='+parseInt(Math.random()*100000),
				data: {"cyzid":cyzid,"zzzid":zzzid,"teamid":teamid,"param":param},
				//timeout : '3000',
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
			return clearSelecteValue;
		}
 	   
 	  function changeGroupOnWell(groupName) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchChangeGroupOnWell.action?nowdata='+parseInt(Math.random()*100000),
				data: {"groupName":groupName},
				//timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("WellNameEdit").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("WellNameEdit").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
			return clearSelecteValue;
		}
		
	    function ghChangeDatasQ(cyzid,zzzid,teamid,param) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchGHcommon.action?nowdata='+parseInt(Math.random()*100000),
				data: {"cyzid":cyzid,"zzzid":zzzid,"teamid":teamid,"param":param},
				//timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("ManiSearch").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("ManiSearch").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
			return clearSelecteValue;
		}
       	 function setOilChangeData(oilid){

 			//var StationText=[{ id: 1 , text: '' }];
 			var GroupText=[{ id: 1 , text: '' }];
 			var ManiText =[{ id: 1 , text: '' }];
 			jQuery.ajax({
 				type : 'post',
 				url:'searchQueryAll_searchOilChangeData.action?nowdata='+parseInt(Math.random()*100000),
 				data: {"oilid":oilid},
 				success : function(jsondata) {
 				var dataObj = $.parseJSON(jsondata);
 					$.each(dataObj, function(key,val){
 						
 						//if (key == 'StationText'){
 						//	StationText = val;
 						//}
 						if (key == 'GroupText'){
 							GroupText = val;
 						}
 						if (key == 'ManiText'){
 							ManiText = val;
 						}
 					});
 					setChangeInitData(GroupText,ManiText);
 				}
 			});
 		   }
 	   function setChangeInitData(GroupText,ManiText) {
 			//liger.get("StationEdit").setData(StationText);
 			liger.get("maniEdit").setData(ManiText);
 			liger.get("groupTeamEdit").setData(GroupText);
 		}

 		 function setOilChangeDataQ(oilid){

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
  					setChangeInitDataQ(GroupText,ManiText);
  				}
  			});
  		   }
  	   function setChangeInitDataQ(GroupText,ManiText) {
  			//liger.get("StationEdit").setData(StationText);
  			liger.get("ManiSearch").setData(ManiText);
  			liger.get("GROUPTEAMSear").setData(GroupText);
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
        	 var oilstationname1=$("#oilstationname1").val();
        	 var GROUPTEAMSear = $("#GROUPTEAMSear").val();
  	         var ManiSearch=$("#ManiSearch").val();
  	         var rulewellname1 = $("#rulewellname1").val();
  	         var areablock = $("#areablock").val();
          
          
        	grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: oilstationname1
				},{
					name: 'GROUPTEAMSear',
					value: GROUPTEAMSear
				},{
					name : 'ManiSearch',
					value :ManiSearch
				},
				{
					name :'rulewellname1',
					value : rulewellname1
				},
				{
					name :'areablock',
					value :areablock
				}
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
         var exportFlag=false;
    	 function onExport() {
    		 //oilationname  blockstationname  boilersName  jrbz1
    		     var Eoilationname=$("#oilstationname1").val();
    		     var GROUPTEAMSear = $("#GROUPTEAMSear").val();
     	         var EboilersName=$("#rulewellname1").val();
     	         var Eareablock = $("#areablock").val();
     	    	var txtDate = $("#txtDate").val();
     			var txtDate1 = $("#txtDate1").val();

    			if (exportFlag) {
    				Eoilationname=oilstationname1;
    				EgROUPTEAMSear = GROUPTEAMSear
    				EboilersName= rulewellname1;
    				Eareablock = areablock
 
    			}
    			var totalNum = 0;
    			
    			var url = 'thinWellRPDWH_searchThinoRRD.action?oilstationname1='+encodeURIComponent(Eoilationname)+'&EgROUPTEAMSear='+encodeURIComponent(GROUPTEAMSear)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=export'+'&rulewellname1='+encodeURIComponent(EboilersName);
    			var urls = 'thinWellRPDWH_searchThinoRRD.action?oilstationname1='+encodeURIComponent(Eoilationname)+'&EgROUPTEAMSear='+encodeURIComponent(GROUPTEAMSear)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=totalNum'+'&rulewellname1='+encodeURIComponent(EboilersName);
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
						var  MyDate = new Date().Format("yyyy-MM-dd");
						if(rq != MyDate){
							$.ligerDialog.error(rq + '不是当前系统时间不让修改')
							break;
						}
						//alert(MyDate)
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
								if (THINWELLRPD != '' && THINWELLRPD != null && THINWELLRPD!="undefined") {
									if(yes){
										jQuery.ajax({
										type : 'post',
										url : 'thinWellRPDWH_removeThinRPD.action',
										data: {"THINWELLRPD":THINWELLRPD,"WellNameEdit":WELL_NAME},
										async : false,
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
						url : 'thinWellRPDWH_Dataready.action?nowdata='+parseInt(Math.random()*100000),
						async : false,
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
									$.ligerDialog.success('数据准备成功！');
									
								}
							}
						},
						error : function(data) {
						}
						});
    			break;
            	case "automate":
                	//自动化提取
    				jQuery.ajax({
    					type : 'post',
							url : 'thinWellRPDWH_Automate.action?nowdata='+parseInt(Math.random()*100000),
							async : false,
							success : function(data) {
								if (data != null && typeof(data)!="undefined"){
								var outData = eval('(' + data + ')');
									if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
										$.ligerDialog.error(outData.ERRMSG);
									}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
										$.ligerDialog.error(outerror(outData.ERRCODE));
									}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
										$.ligerDialog.success(outData.CONFIRMMSG);
									}else{
										$.ligerDialog.success('自动化提取成功！');
										onSubmit();
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
      	    			url:'importExcel.jsp?pageid=xyrb',
      	    			width: 600,
      	    			height: 260
      	    		});
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
						
						<td align="left" class="l-table-edit-td" style="width:60px">采油站</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname1" name = "oilstationname1" />
		                </td>
		                
		               	  <td align="left" class="l-table-edit-td" style="width:40px">班组</td>
	                	<td align="left" class="l-table-edit-td" style="width:40px">
	                		<input type="text" id="GROUPTEAMSear" name="GROUPTEAMSear"/>
	                	</td>
		                <td align="left" class="l-table-edit-td" style="width:40px">管汇</td>
	                	<td align="left" class="l-table-edit-td" style="width:40px">
	                		<input type="text" id="ManiSearch" name="ManiSearch"/>
	               		 </td>
		                <td align="left" class="l-table-edit-td" style="width:40px">井名</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="rulewellname1" name = "rulewellname1"/>
		                </td>
		               <!--   <td align="left" class="l-table-edit-td" style="width:60px">井区块</td>
						<td align="left" class="l-table-edit-td" style="width:60px">
							<input type="text" id="areablock" name = "areablock"/>	
						</td>-->
						 <td align="left" class="l-table-edit-td" style="width:40px">日期:</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
		                <td align="left" class="l-table-edit-td" style="width:20px"></td>
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
				
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">单位:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">班组:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">计量间:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">日期:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">生产时间h:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">冲程m:</td>
		             <tr/>
		             <tr>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="unitOil" type="text" id="unitOil"  ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="groupTeamEdit" type="text" id="groupTeamEdit" ltype="text" />
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
		                	<input name="PROTT" type="text" id="PROTT" validate="{number:true,min:0,max:24}" ltype="text"  />
		                </td>
		                   <td align="left" class="l-table-edit-td" >
		                	<input name="STROKE" type="text" id="STROKE" validate="{number:true,min:0,max:10}" ltype="text"  />
		                </td>
		                </tr>
		            <tr>
		              
		                <td align="left" class="l-table-edit-td" style="width:150px">冲次/min:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">油嘴 mm:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">油压Mpa:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">套压Mpa:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">回压Mpa:</td>
		               	<td align="left" class="l-table-edit-td" style="width:150px">油温℃:</td>
		               	<td align="left" class="l-table-edit-td" style="width:150px">日产液量t:</td>
		            </tr>
		            <tr>
		             
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="AT_TIMES" type="text" id="AT_TIMES" validate="{number:true,min:0,max:10}" ltype="text"   />
		                </td>
		               
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="NOZZLE" type="text" id="NOZZLE" validate="{number:true,min:0,max:50}" ltype="text"  />
		                </td>
		            
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="PRESSURE" type="text" id="PRESSURE" validate="{number:true,min:0,max:50}" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="TCPR" type="text" id="TCPR" validate="{number:true,min:0,max:50}" ltype="text"  />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="BACKPRE" type="text" id="BACKPRE" validate="{number:true,min:0,max:50}" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="TOT" type="text" id="TOT" ltype="text"  />
		                </td>
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="NFV" type="text" id="NFV" validate="{number:true,min:0,max:250}" ltype="text"  />
		                </td>
		               </tr>
		               <tr>
		                 <td align="left" class="l-table-edit-td" style="width:150px">日产气量m3:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">取样(油水):</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">抽油机运转时间h:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">抽油机故障时间h:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">抽油机故障描述:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		                 </tr>
		                 <tr>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="GASP" type="text" id="GASP"   validate="{number:true,min:0,max:100000}"  ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="SAMPLI" type="text" id="SAMPLI" />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="PUMPING_TIME" type="text" id="PUMPING_TIME" validate="{number:true,min:0,max:24}"  ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="PUMPING_MACHINE" type="text" id="PUMPING_MACHINE"  validate="{number:true,min:0,max:24}"  ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="PUMPING_DESCRIPTION" type="text" id="PUMPING_DESCRIPTION" ltype="text" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="REMARK" type="text" id="REMARK" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		              		 <input name="THINWELLRPD" type="hidden" id="THINWELLRPD" ltype="text" />
		              		 <input name="WELLID" type="hidden" id="WELLID" ltype="text" />
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