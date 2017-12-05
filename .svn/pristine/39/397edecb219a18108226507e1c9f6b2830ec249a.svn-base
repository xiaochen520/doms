<%@ page language="java" import="java.util.*,com.echo.dto.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)session.getAttribute("userInfo");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>注汽井日报维护数据</title>
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
        var wellname;
         var cjsj;
         var zqzwater;
         var	pqjby;
         var	jkyy;
         var	ty	;
         var	pqj	 ;
         var	jk ;
         var pzl ;
         var	rzl;
         var	remark;
         var GASWELLRPDID;
         var ORG_ID;
       $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'gasDailyWH_pageInit.action',
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
				liger.get("maniName").setValue('');
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
				liger.get("maniName").setValue('');
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
				liger.get("maniName").setValue('');
    			if (data != null && typeof(data)!="undefined" && data != ''){
    				setGhDataq($("#stationsid").val(),proData);
					//ghChangeDatas("",$("#StationEditID").val(),"","");
				}else{
					//ghChangeDatas("",data,"","");
    				}
    			}
    		});
    		//管汇
    		$("#maniName").ligerComboBox({
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
					onChangeManiWellq( "","",$("#maniName").val())
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
    		//注汽井
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
			// $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
             $("#txtDate").ligerDateEditor(
                {

                    format: "yyyy-MM-dd hh:mm",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    showTime: true,
                    labelAlign: 'center',
                    cancelable : false
            });
        	   //为编辑区下拉列表
        	   $("#CJSJWH").ligerDateEditor({
                         format: "yyyy-MM-dd",
                       //  label: '格式化日期',
                         labelWidth: 100,
                         //showTime: true,
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
                 	wellname  = $("#WellNameEdit").val();
                	cjsj      = $("#CJSJWH").val();
                 	zqzwater  = $("#ZQZSHoureWH").val();
                	pqjby	  = $("#PQJbengyaWH").val();
                	jkyy	 = $("#WellKYoYaWH").val();
                	ty	     = $("#TaoyaWH").val();
                	pqj	 	 = $("#PQJianWH").val();
                	jk 		= $("#wellKouWH").val();
                 	pzl 	= $("#PeiZLWH").val();
                	rzl		= $("#DayZLWH").val();
                	remark = $("#remarkWH").val();
                 	GASWELLRPDID = $("#GASWELLRPDID").val();
                 	ORG_ID = $("#ORG_ID").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1 || operate == 2){

              		   jQuery.ajax({
									type : 'post',
									url : 'gasDailyWH_saveOrUpdate.action',
									async : false,
									data: {"wellname":wellname ,"cjsj":cjsj,"zqzwater":zqzwater,"pqjby":pqjby,"jkyy":jkyy,
              	   							"ty":ty,"pqj":pqj,"jk":jk,"pzl":pzl,"rzl":rzl,"remark":remark,"GASWELLRPDID":GASWELLRPDID,"ORG_ID":ORG_ID},
									success : function(data) { 
              	   							if (data != null && typeof(data)!="undefined" && "1" == data){
												onSubmit();
												setpage1(1); //隐藏编辑界面
												setItemsd(2); //去掉隐藏，显示按钮
												alert('保存成功！');
												//var dialog = $.ligerDialog.success('修改成功！');
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
  	 		if (rowdata.gaswellrpdid != null && typeof(rowdata.gaswellrpdid)!="undefined"){
  	 			GASWELLRPDID = rowdata.gaswellrpdid; 
			 }else{
				 GASWELLRPDID = "";
			 }
  	 		if (rowdata.org_id != null && typeof(rowdata.org_id)!="undefined"){
  	 			ORG_ID = rowdata.org_id; 
			 }else{
				 ORG_ID = "";
			 }
  	 		if (rowdata.oilname != null && typeof(rowdata.oilname)!="undefined"){
  	 			oilName = rowdata.oilname; 
			 }else{
				 oilName = "";
			 }
  	 		if (rowdata.team != null && typeof(rowdata.team)!="undefined"){
  	 			groupname = rowdata.team; 
			 }else{
				 groupname = "";
			 }
  	 
  	 		if (rowdata.stationname != null && typeof(rowdata.stationname)!="undefined"){
  	 			stationname = rowdata.stationname; 
			 }else{
				 stationname = "";
			 }
  	 		if (rowdata.maniname != null && typeof(rowdata.maniname)!="undefined"){
  	 			maniname = rowdata.maniname; 
			 }else{
				 maniname = "";
			 }
  	 		if (rowdata.well_name != null && typeof(rowdata.well_name)!="undefined"){
  	 			wellname = rowdata.well_name; 
			 }else{
				 wellname = "";
			 }
  	 		if (rowdata.report_date != null && typeof(rowdata.report_date)!="undefined"){
  	 			cjsj = rowdata.report_date; 
			 }else{
				 cjsj = "";
			 }
  	 		if (rowdata.zqzwater != null && typeof(rowdata.zqzwater)!="undefined"){
  	 			zqzwater = rowdata.zqzwater; 
			 }else{
				 zqzwater = "";
			 }
  	 		if (rowdata.pqjby != null && typeof(rowdata.pqjby)!="undefined"){
  	 			pqjby = rowdata.pqjby; 
			 }else{
				 pqjby = "";
			 }
  	 		if (rowdata.jkyy != null && typeof(rowdata.jkyy)!="undefined"){
  	 			jkyy = rowdata.jkyy; 
			 }else{
				 jkyy = "";
			 }
  	 		if (rowdata.ty != null && typeof(rowdata.ty)!="undefined"){
  	 			ty = rowdata.ty; 
			 }else{
				 ty = "";
			 }
  	 		if (rowdata.pqj != null && typeof(rowdata.pqj)!="undefined"){
  	 			pqj = rowdata.pqj; 
			 }else{
				 pqj = "";
			 }
  	 		if (rowdata.jk != null && typeof(rowdata.jk)!="undefined"){
  	 			jk = rowdata.jk; 
			 }else{
				 jk = "";
			 }
  	 		if (rowdata.pzl != null && typeof(rowdata.pzl)!="undefined"){
  	 			pzl = rowdata.pzl; 
			 }else{
				 pzl = "";
			 }
  	 		if (rowdata.rzl != null && typeof(rowdata.rzl)!="undefined"){
  	 			rzl = rowdata.rzl; 
			 }else{
				 rzl = "";
			 }
  	 		if (rowdata.liquid_flag != null && typeof(rowdata.liquid_flag)!="undefined"){
  	 			liquid_flag = rowdata.liquid_flag; 
			 }else{
				 liquid_flag = "";
			 }
  	 		if (rowdata.remark != null && typeof(rowdata.remark)!="undefined"){
  	 			remark = rowdata.remark; 
			 }else{
				 remark = "";
			 }
			 
           	if(operate == 2){
           		assignM(2);
           	}
  	 	}
  	  function assignM(flg){
    		
  		 if(flg == 2){
	  			 document.getElementById("unitOil").value =oilName  ;
				 document.getElementById("groupTeamEdit").value =groupname  ;
				 document.getElementById("StationEdit").value =stationname  ;
				 document.getElementById("maniEdit").value =maniname ;
             	 document.getElementById("WellNameEdit").value =wellname;
				 document.getElementById("CJSJWH").value =cjsj  ;
				 document.getElementById("ZQZSHoureWH").value =zqzwater  ;
				 document.getElementById("PQJbengyaWH").value = pqjby ;
				 document.getElementById("WellKYoYaWH").value =jkyy  ;
				 document.getElementById("TaoyaWH").value =ty  ;
				 document.getElementById("PQJianWH").value =pqj  ;
				 document.getElementById("wellKouWH").value =jk  ;
				 document.getElementById("PeiZLWH").value =pzl  ;
				 document.getElementById("DayZLWH").value =rzl  ;
				 document.getElementById("remarkWH").value =remark  ;
				 document.getElementById("GASWELLRPDID").value = GASWELLRPDID ;
				 document.getElementById("ORG_ID").value = ORG_ID ;


			 }else if(flg == 1){
				 document.getElementById("unitOil").value =""  ;
				 document.getElementById("groupTeamEdit").value =""  ;
				 document.getElementById("StationEdit").value =""  ;
				 document.getElementById("maniEdit").value =""  ;
	             document.getElementById("WellNameEdit").value ="";
				 document.getElementById("CJSJWH").value ="";
				 document.getElementById("ZQZSHoureWH").value ="";
				 document.getElementById("PQJbengyaWH").value ="";  
				 document.getElementById("WellKYoYaWH").value ="";
				 document.getElementById("TaoyaWH").value ="";
				 document.getElementById("PQJianWH").value ="";
				 document.getElementById("wellKouWH").value ="";
				 document.getElementById("PeiZLWH").value =""  ;
				 document.getElementById("DayZLWH").value ="";
				 document.getElementById("remarkWH").value ="";
				 document.getElementById("GASWELLRPDID").value ="" ;
				 document.getElementById("ORG_ID").value ="" ;
			 }
			 		
   }
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
 			liger.get("maniName").setData(ManiText);
 			liger.get("banzu").setData(GroupText);
 		}

	    function ghChangeDatasq(cyzid,zzzid,teamid,param) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchGHcommon.action?nowdata='+parseInt(Math.random()*100000),
				data: {"cyzid":cyzid,"zzzid":zzzid,"teamid":teamid,"param":param},
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("maniName").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("maniName").setData("");
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
						liger.get("maniName").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("maniName").setData(proData);
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

	    //编辑区
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

    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
						//alert(id);
			//alert(name);
			//alert(datatype);
			var oilstationname="全部";
			var areablock='';
			var blockstationname='';
			var ghname='';
			var wellnum='';
			var gh='';
			
			getWellInitData();
			$("#oilstationname1").ligerGetComboBoxManager().setValue('');
			$("#rulewellname1").ligerGetComboBoxManager().setValue('');
			$("#waterflooding1").ligerGetComboBoxManager().setValue('');
			$("#areablock").ligerGetComboBoxManager().setValue('');
			if(datatype=='4'){
				oilstationname=name;
				$("#oilstationname1").val(name);
			}
			
			if(datatype=='13'){
				blockstationname=name;
				$("#rulewellname1").val(name);
			}
			
			if(datatype=='9'){
				wellnum=name;
				$("#waterflooding1").val(name);
				
				//alert(wellnum);
			}
	
			
			grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: oilstationname
				},{
					name: 'blockstationname1',
					value: blockstationname
				},{
					name: 'rulewellname1',
					value: wellnum
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
					if(cjsj !=myDate){
						//$.ligerDialog.warn(cjsj + '不是当天日报数据部允许修改')
						alert(cjsj + '不是当天日报数据部允许修改');
						}else{

							if(operate != 1 && operate != 2){
								setpage1(0); //显示编辑界面
								setItemsd(0);//0-显示编辑区，添加隐藏按钮
							  }
		              	   operate = 2;
		              	   assignM(2);
						}
          	     		
          	     }else{
          	     	alert('请选择一条你要修改信息的数据！');
          	     }
	          	     
				break;
				case "delete":
					if(rows.length == 1){
						if(confirm("确定删除吗?")){
								if (GASWELLRPDID != '' && GASWELLRPDID != null && GASWELLRPDID!="undefined") {
										jQuery.ajax({
										type : 'post',
										url : 'gasDailyWH_removeDataRPD.action',
										data: {"GASWELLRPDID":GASWELLRPDID,"wellname":wellname},
										async : false,
										success : function(data) {
											if (data != null && typeof(data)!="undefined"){
												var outData = eval('(' + data + ')');
													if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														alert(outData.ERRMSG);
													}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														alert(outerror(outData.ERRCODE));
													}else{
														alert('删除成功！');
														onSubmit();
													}
												}
											},
										error : function(data) {
										}
										});
								}else{
									$.ligerDialog.success("要删除的数据ID获取失败！");
									}
							
						};
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
						url : 'gasDailyWH_Dataready.action?nowdata='+parseInt(Math.random()*100000),
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
									$.ligerDialog.success('数据准备成功！');
									onSubmit();
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
							url : 'gasDailyWH_Automate.action?nowdata='+parseInt(Math.random()*100000),
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
      	    			url:'importExcel.jsp?pageid=zqrb',
      	    			width: 600,
      	    			height: 260
      	    		});
				break;
				
				
			}
		}
           function onSubmit()
        {
        	 var oilstationname=$("#oilstationname").val();
  	         var banzu=$("#banzu").val();
  	         var blockstationname=$("#blockstationname").val();
  	      	 var  maniName = $("#maniName").val();
	  	     var wellName = $("#wellName").val();
	  	     var txtDate = $("#txtDate").val();
	  	     var txtDate1 =$("#txtDate1").val();
	  	     
	  	     
	  	    
          
        	grid.setOptions({
        		parms: [{
					name: 'oilstationname',
					value: oilstationname
				},{
					name: 'banzu',
					value: banzu
				},{
					name: 'blockstationname',
					value: blockstationname
				},{
					name :'maniName',
					value :maniName
				},{
					name :'wellName',
					value :wellName
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
		var exportFlag=false;
	 function onExport() {
			
   	 	 var oilstationname=$("#oilstationname").val();
         var banzu=$("#banzu").val();
         var blockstationname=$("#blockstationname").val();
      	 var  maniName = $("#maniName").val();
  	     var wellName = $("#wellName").val();
  	     var txtDate = $("#txtDate").val();
  	     var txtDate1 =$("#txtDate1").val();
			if (exportFlag) {
				var oilstationname=oilstationname;
				var banzu=banzu;
				var blockstationname=blockstationname;
				var maniName = maniName;
				var wellName = wellName;
				var date = txtDate;
				var date1 =txtDate1; 
			}
			var totalNum = 0;
			//alert('in');
			var url = 'gasDailyWH_searchRRDData.action?oilstationname='+encodeURIComponent(oilstationname)+'&banzu='+encodeURIComponent(banzu)+'&blockstationname='+encodeURIComponent(blockstationname)+'&maniName='+encodeURIComponent(maniName)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=export'+'&wellName='+wellName;
			var urls = 'gasDailyWH_searchRRDData.action?oilstationname='+encodeURIComponent(oilstationname)+'&banzu='+encodeURIComponent(banzu)+'&blockstationname='+encodeURIComponent(blockstationname)+'&maniName='+encodeURIComponent(maniName)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=totalNum'+'&wellName='+wellName;
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
	                	<input type="text" id="maniName" name = "maniName"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">井口：</td>
					<td align="left" class="l-table-edit-td" style="width:60px">
						<input type="text" id="wellName" name = "wellName"/>	
					</td>
	                <td align="right" class="l-table-edit-td" style="width:40px">日期:</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
					<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:80px">导出报表</a></td>
				</tr>
			</table>

  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="width:100%;height: 100px; display:none;">
				
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		               	<td align="left" class="l-table-edit-td" style="width:150px">单位:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">班组:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">转油站:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">管汇:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">日期:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">注汽/注水小时h:</td>
		              
		            </tr>
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
		                	<input name="CJSJWH" type="text" id="CJSJWH"  ltype="date" />
		                </td>
		                
		           		 <td align="left" class="l-table-edit-td" >
		                	<input name="ZQZSHoureWH" type="text" id="ZQZSHoureWH"  validate="{number:true,min:0,max:24}" ltype="text" />
		                </td>
		                
		         
		            </tr>
		            <tr>
			            <td align="left" class="l-table-edit-td" style="width:150px">配汽间/泵压:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井口/油压:</td>
			            <td align="left" class="l-table-edit-td" style="width:150px">套压:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">配汽间:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井口:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">配注量:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">日注量:</td>
		                
		            </tr>
		             <tr>
		                
		              <td align="left" class="l-table-edit-td" >
		                	<input name="PQJbengyaWH" type="text" id="PQJbengyaWH" validate="{number:true,min:0,max:20}"  ltype="text" />
		               </td>
		               <td align="left" class="l-table-edit-td" >
		                	<input name="WellKYoYaWH" type="text" id="WellKYoYaWH" validate="{number:true,min:0,max:20}"  ltype="text" />
		                </td>
		       
		                <td align="left" class="l-table-edit-td" >
		                	<input name="TaoyaWH" type="text" id="TaoyaWH" ltype="text"  />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="PQJianWH" type="text" id="PQJianWH"  validate="{number:true,min:0,max:400}"  ltype="text" />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                 	<input name="wellKouWH" type="text" id="wellKouWH" validate="{number:true,min:0,max:350}"  ltype="text"  />
		                 </td>
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="PeiZLWH" type="text" id="PeiZLWH"  ltype="text" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="DayZLWH" type="text" id="DayZLWH"  ltype="text" />
		                </td>
		                
		                </tr>
		                <tr>
		                <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		                 </tr>
		                <tr>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remarkWH" type="text" id="remarkWH" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
							<input name="ORG_ID" type="hidden" id="ORG_ID" />
						</td>
						<td align="left" class="l-table-edit-td" >
							<input name="GASWELLRPDID" type="hidden" id="GASWELLRPDID" />
						</td>
		            </tr>
		             
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>