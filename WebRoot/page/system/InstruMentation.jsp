<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>仪表设备信息</title>
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

        var INSTRUMENT_ID;
        //鼠标选择数据
		var  edit_CODE;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                 jQuery.ajax({
					type : 'post',
					url : 'instru_pageInit.action',
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
             	$("#oilName").ligerComboBox({
    				valueField: 'id',
    				valueFieldID: 'oilId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:130,
    				//autocomplete:true,
    				hideOnLoseFocus:true
    			});
            	$("#objectCode").ligerComboBox({
    				//url:'rulewell_queryStationInfo.action?oilid=im',
    	            valueField: 'id',
    				valueFieldID: 'objectCodeId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    	            // autocomplete:true,
    	            hideOnLoseFocus:true,
    	            onSelected:function (data){
    				if ($("#objectCodeId").val() != '') {
    					setSuperStnsData(liger.get("objectCode").getValue());
    					}
    				}
    			});
            	$("#areaName").ligerComboBox({
    				valueField: 'id',
    				valueFieldID: 'areaNameId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            //autocomplete:true,
    	            onSelected:function (data){
    				if ($("#areaNameId").val() != '') {
    					setStaNameData(liger.get("oilName").getValue(),liger.get("objectCode").getValue(),liger.get("areaName").getValue());
    					}
    				}
    			});
    			$("#staName").ligerComboBox({
    				//url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
    				valueField: 'id',
    				valueFieldID: 'staNameId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    				autocomplete:true,
    				 onSelected:function (data){
    				if ($("#staNameId").val() != '') {
    					setOwnObjectData(liger.get("oilName").getValue(),liger.get("objectCode").getValue(),liger.get("areaName").getValue(),liger.get("staName").getValue());
    					}
    				}
    			});
    			//所属对象初始化没有对象，根据条件显示相应的数据
    			$("#ownObject").ligerComboBox({
    				//url:'rulewell_queryOilSationInfo.action?arg=im',
    				valueField: 'id',
    				valueFieldID: 'ownObjectId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				autocomplete:true,
    				hideOnLoseFocus:true
    			});
    			$("#INSTRU_1TN").ligerComboBox({
    				//url:'rulewell_queryStationInfoA.action?oilid=im',
    	            valueField: 'id',
    				valueFieldID: 'instru_1tnId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    	            width: 100,
    	            hideOnLoseFocus:true,
    	            autocomplete:true,
    	            onSelected:function (data){
    				if ($("#instru_1tnId").val() != '') {
    					setINSTRUData(liger.get("objectCode").getValue(),liger.get("INSTRU_1TN").getValue());
    					}
    				}
    			});
    			$("#INSTRU_CLN").ligerComboBox({
    				//url:'rulewell_queryWellInfo.action?orgid=im',
    				valueField: 'id',
    				valueFieldID: 'instru_clnId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    	            autocomplete:true,
    	            onSelected:function (data){
    				if ($("#instru_clnId").val() != '') {
    					setFACTORYNSData(liger.get("INSTRU_CLN").getValue());
    					}
    				}
    	            
    			});
    			$("#FACTORY_NS").ligerComboBox({
    				//url:'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
    				valueField: 'id',
    				valueFieldID: 'factory_nsId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    				autocomplete:true,
    				onSelected:function (data){
    				if ($("#factory_nsId").val() != '') {
    					setINSTRUC3NData(liger.get("INSTRU_CLN").getValue(),liger.get("FACTORY_NS").getValue());
    					}
    				}
    			});
    			$("#INSTRU_C3N").ligerComboBox({  
    				//url:'instru_searchCode.action',
    				valueField: 'id',
    				valueFieldID: 'instru_c3nId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    				autocomplete:true
    			});
    			$("#INSTRU_STNS").ligerComboBox({  
    				//url:'instru_searchCode.action',
    				valueField: 'id',
    				valueFieldID: 'instru_stnsId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    				autocomplete:true
    			});
    			$("#superStns").ligerComboBox({    
    				//url:'instru_searchStva.action',
    				valueField: 'id',
    				valueFieldID: 'superStnsId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    				hideOnLoseFocus:true,
    				autocomplete:true
    			});
    			$("#alamOr").ligerComboBox({  //
   				 hideOnLoseFocus:true,
   					autocomplete:true,
   		                data: [
   		                    { text: '是', id: '0' },
   		                    { text: '否', id: '1' }
   		                    
   		                ], valueFieldID: 'alamId',
   					initText :''
   			});
    			 $("#txtDate").ligerDateEditor(
    						{
    				          format: "yyyy-MM-dd ",
    				          labelWidth: 100,
    				          labelAlign: 'center',
    				          cancelable : false         
    				        });
    					$("#txtDate1").ligerDateEditor(
    				       {
    				        format: "yyyy-MM-dd ",
    				        labelWidth: 100,
    				        labelAlign: 'center',
    				        cancelable : false
    				      });
  			//编辑
  			$("#EditOilName").ligerComboBox({
    				valueField: 'id',
    				valueFieldID: 'EoilId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:130,
    				//autocomplete:true,
    				hideOnLoseFocus:true
    			});

  			$("#EditObjectCode").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'EOBCId',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:100,
				//autocomplete:true,
				hideOnLoseFocus:true,
				 onSelected:function (data){
				if ($("#EOBCId").val() != '') {
					setEditSJsheBData(liger.get("EditObjectCode").getValue());
					}
				}
			});
  			$("#EditAreaName").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'EAREAId',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:100,
				//autocomplete:true,
				hideOnLoseFocus:true,
				 onSelected:function (data){
				if ($("#EAREAId").val() != '') {
					setEditStaNameData(liger.get("EditOilName").getValue(),liger.get("EditObjectCode").getValue(),liger.get("EditAreaName").getValue());
					}
				}
			});
  			$("#EditStaName").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'ESTAId',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:100,
				//autocomplete:true,
				hideOnLoseFocus:true,
				 onSelected:function (data){
				if ($("#ESTAId").val() != '') {
					setSuperNameData(liger.get("EditOilName").getValue(),liger.get("EditObjectCode").getValue(),liger.get("EditAreaName").getValue(),liger.get("EditStaName").getValue());
					}
				}
			});
  			$("#EditSuperName").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'ESuoNId',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:100,
				//autocomplete:true,
				hideOnLoseFocus:true
			});
  			$("#EditInstruDL").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'EDlId',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:100,
				//autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
				if ($("#EDlId").val() != '') {
					setSheBNameData(liger.get("EditObjectCode").getValue(),liger.get("EditInstruDL").getValue());
					setYBGhuoData(liger.get("EditObjectCode").getValue(),liger.get("EditInstruDL").getValue());
					}
				}
			});
  			$("#EditInstruSBMC").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'EIsbmcId',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:100,
				//autocomplete:true,
				hideOnLoseFocus:true
				
				
			});
  			$("#EditInstruSJSB").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'EsjsbId',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:100,
				//autocomplete:true,
				hideOnLoseFocus:true
			});
  			$("#EditInstruZT").ligerComboBox({
				valueField: 'id',
				valueFieldID: 'EZTId',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:100,
				//autocomplete:true,
				hideOnLoseFocus:true
			});
  			$("#EditInstruGHID").ligerComboBox({
  				url:'instru_searchGHID.action',
  				valueField: 'id',
				valueFieldID: 'GHid',
				textField: 'text',
				selectBoxHeight:250,
				selectBoxWidth:300,
				hideOnLoseFocus:true,
				autocomplete:true,
				onBeforeSelect:function (data){
					 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'instru_DispalyParam.action',
					data : {
         				"data" :data 
         			},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
						$("#EditFactoryGH").val(data.EditFactoryGH);
						$("#EditFactoryZC").val(data.EditFactoryZC);
						$("#EditInstruSBXH").val(data.EditInstruSBXH);
						
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
					
				}
			});
  			/*$("#instru_nam").ligerComboBox({  //
				url:'instru_searchNamecid.action',
				valueField: 'id',
				valueFieldID: 'instrunamecid',
				textField: 'text',
				selectBoxHeight:300,
				selectBoxWidth:250,
				hideOnLoseFocus:true,
				autocomplete:true,
				onBeforeSelect:function (data){
					 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'instru_DispalyParam.action?data='+data,
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
						
						$("#instru_nac").val(data.YBBM);
						$("#instrumentation_type").val(data.KZQ);
						$("#KZQID").val(data.KZQID);
						$("#instru_cln").val(data.YBXL);
						$("#factory_nf1").val(data.CJMC);
						
						
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
					
				}
			});*/
  				//初始化显示数据信息
    			getWellInitData();
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
                	 var EditObjectCode ="" ;
                	 if($("#EditObjectCode").val() !=''){
                		 EditObjectCode = $("#EOBCId").val();
                    	 }
                	 var EditSuperName = "";
                	 if($("#EditSuperName").val() !=''){
                		 EditSuperName = $("#ESuoNId").val();
                    	 }
                	 var EditInstruSBMC ="" ;
                	 if($("#EditInstruSBMC").val() !=''){
                		 EditInstruSBMC = $("#EIsbmcId").val();
                    	 }
                	 var EditInstruSJSB = "";
                	 if( $("#EditInstruSJSB").val() !=''){
                		 EditInstruSJSB = $("#EsjsbId").val();
                    	 }
                	 var EditInstruZT = "";
                	 if($("#EditInstruZT").val() !=''){
                		 EditInstruZT = $("#EZTId").val();
                    	 }
                	 var EditRemark = $("#EditRemark").val();
                	 var EditInstruGHID = $("#EditInstruGHID").val();
                	 var EditInstruGHIDN = "";
                	 if($("#EditInstruGHID").val() !=''){
                		 EditInstruGHIDN = $("#GHid").val();
                    	 }
                	var stationNo = $("#stationNo").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'instru_saveorUpdateData.action',
									async : false,
									data: {"EditObjectCode":EditObjectCode, "EditSuperName":EditSuperName,
						              	   	"EditInstruSBMC":EditInstruSBMC, "EditInstruSJSB":EditInstruSJSB, 
						              	   	"EditInstruZT":EditInstruZT, "EditRemark":EditRemark, 
						              	    "EditInstruGHID":EditInstruGHID,"EditInstruGHIDN":EditInstruGHIDN,
						              	    "stationNo":stationNo},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"&& data!=""){
																var outData = eval('(' + data + ')');
																//alert(data);
																if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
																	$.ligerDialog.error(outData.ERRMSG);
																}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
																	$.ligerDialog.error(outerror(outData.ERRCODE));
																}else{
																	onSubmit();
																	setpage4(1); //隐藏编辑界面
																	setItemsd(2); //去掉隐藏，显示按钮
																	$.ligerDialog.success('添加成功！');
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
									url : 'instru_saveorUpdateData.action',
									async : false,
									data: {"EditObjectCode":EditObjectCode, "EditSuperName":EditSuperName,
						              	   	"EditInstruSBMC":EditInstruSBMC, "EditInstruSJSB":EditInstruSJSB, 
						              	   	"EditInstruZT":EditInstruZT, "EditRemark":EditRemark, 
						              	    "EditInstruGHID":EditInstruGHID,"EditInstruGHIDN":EditInstruGHIDN,"INSTRUMENT_ID":INSTRUMENT_ID,
						              	  "stationNo":stationNo},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"&& data!=""){
																var outData = eval('(' + data + ')');
																//alert(data);
																if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
																	$.ligerDialog.error(outData.ERRMSG);
																}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
																	$.ligerDialog.error(outerror(outData.ERRCODE));
																}else{
																	onSubmit();
																	setpage4(1); //隐藏编辑界面
																	setItemsd(2); //去掉隐藏，显示按钮
																	$.ligerDialog.success('修改成功！');
																	operate = 0;
																		
																}
									
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
       		if (rowdata.DWNAME != null && typeof(rowdata.DWNAME)!="undefined"){
       			DWNAME = rowdata.DWNAME;
			 }else{
				DWNAME = "";
			 }
       		if (rowdata.OBJECT_TYPE_NAME != null && typeof(rowdata.OBJECT_TYPE_NAME)!="undefined"){
       			OBJECT_TYPE_NAME = rowdata.OBJECT_TYPE_NAME;
			 }else{
				OBJECT_TYPE_NAME = "";
			 }
       		if (rowdata.QKNAME != null && typeof(rowdata.QKNAME)!="undefined"){
       			QKNAME = rowdata.QKNAME;
			 }else{
				QKNAME = "";
			 }
       		if (rowdata.ZHNAME != null && typeof(rowdata.ZHNAME)!="undefined"){
       			ZHNAME = rowdata.ZHNAME;
			 }else{
				ZHNAME = "";
			 }
       		if (rowdata.STRUCTURENAME != null && typeof(rowdata.STRUCTURENAME)!="undefined"){
       			STRUCTURENAME = rowdata.STRUCTURENAME;
			 }else{
				 STRUCTURENAME = "";
			 }
       		if (rowdata.INSTRU_1TN != null && typeof(rowdata.INSTRU_1TN)!="undefined"){
       			INSTRU_1TN = rowdata.INSTRU_1TN;
			 }else{
				 INSTRU_1TN = "";
			 }
       		if (rowdata.INSTRU_CLN != null && typeof(rowdata.INSTRU_CLN)!="undefined"){
       			INSTRU_CLN = rowdata.INSTRU_CLN;
			 }else{
				 INSTRU_CLN = "";
			 }
       		if (rowdata.SJRTU_CLN != null && typeof(rowdata.SJRTU_CLN)!="undefined"){
       			SJRTU_CLN = rowdata.SJRTU_CLN;
			 }else{
				 SJRTU_CLN = "";
			 }
       		if (rowdata.INSTRU_STNS != null && typeof(rowdata.INSTRU_STNS)!="undefined"){
       			INSTRU_STNS = rowdata.INSTRU_STNS;
			 }else{
				 INSTRU_STNS = "";
			 }
       		
       		if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
       			REMARK = rowdata.REMARK;
			 }else{
				 REMARK = "";
			 }
       		if (rowdata.INSTRUS_ID != null && typeof(rowdata.INSTRUS_ID)!="undefined"){
       			INSTRUS_ID = rowdata.INSTRUS_ID;
			 }else{
				 INSTRUS_ID = "";
			 }
       		if (rowdata.INSTRUMENT_ID != null && typeof(rowdata.INSTRUMENT_ID)!="undefined"){
       			INSTRUMENT_ID = rowdata.INSTRUMENT_ID;
			 }else{
				 INSTRUMENT_ID = "";
			 }
       		if (rowdata.SFACTORY_NS != null && typeof(rowdata.SFACTORY_NS)!="undefined"){
       			SFACTORY_NS = rowdata.SFACTORY_NS;
			 }else{
				 SFACTORY_NS = "";
			 }

       		if (rowdata.MFACTORY_NS != null && typeof(rowdata.MFACTORY_NS)!="undefined"){
       			MFACTORY_NS = rowdata.MFACTORY_NS;
			 }else{
				 MFACTORY_NS = "";
			 }
       		if (rowdata.INSTRU_C3N != null && typeof(rowdata.INSTRU_C3N)!="undefined"){
       			INSTRU_C3N = rowdata.INSTRU_C3N;
			 }else{
				 INSTRU_C3N = "";
			 }
			 // ID
       		if (rowdata.DWID != null && typeof(rowdata.DWID)!="undefined"){
       			DWID = rowdata.DWID;
			 }else{
				 DWID = "";
			 }
       		if (rowdata.QKID != null && typeof(rowdata.QKID)!="undefined"){
       			QKID = rowdata.QKID;
			 }else{
				 QKID = "";
			 }
       		if (rowdata.ZHID != null && typeof(rowdata.ZHID)!="undefined"){
       			ZHID = rowdata.ZHID;
			 }else{
				 ZHID = "";
			 }
       		if (rowdata.ORG_ID != null && typeof(rowdata.ORG_ID)!="undefined"){
       			ORG_ID = rowdata.ORG_ID;
			 }else{
				 ORG_ID = "";
			 }
       		if (rowdata.OBJECT_CODE != null && typeof(rowdata.OBJECT_CODE)!="undefined"){
       			OBJECT_CODE = rowdata.OBJECT_CODE;
			 }else{
				 OBJECT_CODE = "";
			 }
       		if (rowdata.INSTRU_CLC != null && typeof(rowdata.INSTRU_CLC)!="undefined"){
       			INSTRU_CLC = rowdata.INSTRU_CLC;
			 }else{
				 INSTRU_CLC = "";
			 }
       		if (rowdata.INSTRU_1TC != null && typeof(rowdata.INSTRU_1TC)!="undefined"){
       			INSTRU_1TC = rowdata.INSTRU_1TC;
			 }else{
				 INSTRU_1TC = "";
			 }
       		if (rowdata.SJRTU_CLC != null && typeof(rowdata.SJRTU_CLC)!="undefined"){
       			SJRTU_CLC = rowdata.SJRTU_CLC;
			 }else{
				 SJRTU_CLC = "";
			 }
       		if (rowdata.INSTRU_STVA != null && typeof(rowdata.INSTRU_STVA)!="undefined"){
       			INSTRU_STVA = rowdata.INSTRU_STVA;
			 }else{
				 INSTRU_STVA = "";
			 }
       		if (rowdata.STATION_NO != null && typeof(rowdata.STATION_NO)!="undefined"){
       			stationNo = rowdata.STATION_NO;
			 }else{
				 stationNo = "";
			 }
        	if(operate == 2){
        		assignM(2);
        	}						
									
        }
      
         function getWellInitData() {
     		var oilNameText =[{ id: 1 , text: '' }];
     		var objectCodeText=[{ id: 1 , text: '' }];
     		var areaNameText=[{ id: 1 , text: '' }];
     		var staNameText=[{ id: 1 , text: '' }];
     		var instru1TnText=[{ id: 1 , text: '' }];
     		var factoryNsText=[{ id: 1 , text: '' }];
     		var instruC3nText=[{ id: 1 , text: '' }];
     		var instruStnsText=[{ id: 1 , text: '' }];
     		var superStnsText=[{ id: 1 , text: '' }];
     		jQuery.ajax({
     			type : 'post',
     			url : 'instru_cascadeInit.action',
     			success : function(jsondata) {
     			var dataObj = $.parseJSON(jsondata);
     				$.each(dataObj, function(key,val){
     					if (key == 'oilNameText') {
     						oilNameText = val;
     					}
     					if (key == 'objectCodeText'){
     						objectCodeText = val;
     					}
     					if (key == 'areaNameText'){
     						areaNameText = val;
     					}
     					if (key == 'staNameText'){
     						staNameText = val;
     					}
     					if (key == 'instru1TnText') {
     						instru1TnText = val;
     					}
     					if (key == 'factoryNsText'){
     						factoryNsText = val;
     					}
     					if (key == 'instruC3nText'){
     						instruC3nText = val;
     					}
     					if (key == 'instruStnsText'){
     						instruStnsText = val;
     					}
     					if (key == 'superStnsText'){
     						superStnsText = val;
     					}
     				});
     				setInitData(oilNameText,objectCodeText,areaNameText,staNameText,instru1TnText,factoryNsText,instruC3nText,instruStnsText,superStnsText);
     			}
     		});
     	}
     	
         function setInitData(oilNameText,objectCodeText,areaNameText,staNameText,instru1TnText,factoryNsText,instruC3nText,instruStnsText,superStnsText) {
     		liger.get("oilName").setData(oilNameText);
     		liger.get("objectCode").setData(objectCodeText);
     		liger.get("areaName").setData(areaNameText);
     		liger.get("staName").setData(staNameText);
     		liger.get("INSTRU_1TN").setData(instru1TnText);
     		liger.get("FACTORY_NS").setData(factoryNsText);
     		liger.get("INSTRU_C3N").setData(instruC3nText);
     		liger.get("INSTRU_STNS").setData(instruStnsText);
     		liger.get("superStns").setData(superStnsText);
     		//编辑

     		liger.get("EditOilName").setData(oilNameText);
     		liger.get("EditObjectCode").setData(objectCodeText);
     		liger.get("EditAreaName").setData(areaNameText);
     		liger.get("EditStaName").setData(staNameText);
     		liger.get("EditInstruDL").setData(instru1TnText);
     		
     		liger.get("EditInstruZT").setData(instruStnsText);
     		liger.get("EditInstruSJSB").setData(superStnsText);
     	}
         function setStaNameData(oilName, objectCode,areaName) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeArea.action',
         			data : {
         				"oilName" : oilName,
         				"objectCode" : objectCode,
         				"areaName" : areaName
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("staName").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("staName").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
         function setOwnObjectData(oilName, objectCode,areaName,staName) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeOwnObject.action',
         			data : {
         				"oilName" : oilName,
         				"objectCode" : objectCode,
         				"areaName" : areaName,
         				"staName" :staName
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("ownObject").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("ownObject").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
         function setINSTRUData(objectCode,INSTRU_1TN) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeInstru.action',
         			data : {
         				"objectCode" : objectCode,
         				"INSTRU_1TN" :INSTRU_1TN
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("INSTRU_CLN").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("INSTRU_CLN").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
         function setFACTORYNSData(INSTRU_CLN) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeFactoryns.action',
         			data : {
         				"INSTRU_CLN" :INSTRU_CLN
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("FACTORY_NS").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("FACTORY_NS").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
         function setINSTRUC3NData(INSTRU_CLN,FACTORY_NS) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeInstruc3n.action',
         			data : {
         				"INSTRU_CLN" :INSTRU_CLN,
         				"FACTORY_NS" :FACTORY_NS 
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("INSTRU_C3N").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("INSTRU_C3N").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
         function setSuperStnsData(objectCode) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeSuperStns.action',
         			data : {
         				"objectCode" :objectCode 
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("superStns").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("superStns").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
      	//编辑 
      	   function setEditStaNameData(oilName, objectCode,areaName) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeArea.action?args=ALL',
         			data : {
         				"oilName" : oilName,
         				"objectCode" : objectCode,
         				"areaName" : areaName
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("EditStaName").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("EditStaName").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
        	//编辑
         function setSuperNameData(oilName, objectCode,areaName,staName) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeOwnObject.action?args=ALL',
         			data : {
         				"oilName" : oilName,
         				"objectCode" : objectCode,
         				"areaName" : areaName,
         				"staName" :staName
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("EditSuperName").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("EditSuperName").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
         function setSheBNameData(objectCode,INSTRU_1TN) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeInstru.action',
         			data : {
         				"objectCode" : objectCode,
         				"INSTRU_1TN" :INSTRU_1TN
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("EditInstruSBMC").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("EditInstruSBMC").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
         //供货ID级联
         function setYBGhuoData(objectCode,INSTRU_1TN) {
      		jQuery.ajax( {
      			type : 'post',
      			url : 'instru_searchOnChangeYBGhuo.action',
      			data : {
      				"objectCode" : objectCode,
      				"INSTRU_1TN" :INSTRU_1TN
      			},
      			success : function(jsondata) {
      				if (jsondata != null && typeof (jsondata) != "undefined"
      						&& jsondata != 0) {
      					liger.get("EditInstruGHID").setData(eval('(' + jsondata + ')'));
      				} else {
      					liger.get("EditInstruGHID").setData('');
      				}
      			},
      			error : function(jsondata) {
      			}
      		});
      	}
         function setEditSJsheBData(objectCode) {
         		jQuery.ajax( {
         			type : 'post',
         			url : 'instru_searchOnChangeSuperStns.action',
         			data : {
         				"objectCode" :objectCode 
         			},
         			success : function(jsondata) {
         				if (jsondata != null && typeof (jsondata) != "undefined"
         						&& jsondata != 0) {
         					liger.get("EditInstruSJSB").setData(eval('(' + jsondata + ')'));
         				} else {
         					liger.get("EditInstruSJSB").setData('');
         				}
         			},
         			error : function(jsondata) {
         			}
         		});
         	}
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){
			var oilName='';
			var stationName='';
			var NETWORK='';
			$("#oilName").ligerGetComboBoxManager().setValue('');
			$("#stationName").ligerGetComboBoxManager().setValue('');
			$("#NETWORK").ligerGetComboBoxManager().setValue('');
			
			if(datatype=='4'){
				oilName=name;
				$("#oilName").val(name);
			}
			
			if(datatype=='7'){
				stationName=name;
				$("#stationName").val(name);
			}
			
			if(datatype=='21'){
				NETWORK=name;
				$("#NETWORK").val(name);
			}
			
			grid.setOptions({
        		parms: [{name: 'oilName', value: oilName},
				{name: 'stationName', value: stationName},
				{name: 'NETWORK',value: NETWORK}
				]
        	});
         	grid.loadData(true);
		}
		
		function onSubmit(){
			grid.setOptions({
				parms: [{name: 'oilName',value: $("#oilName").val()},
						{name: 'objectCode',value: $("#objectCode").val()},
						{name: 'areaName',value: $("#areaName").val()},
						{name: 'staName',value: $("#staName").val()},
						{name: 'ownObject',value: $("#ownObject").val()},
						{name: 'INSTRU_1TN',value: $("#INSTRU_1TN").val()},
						{name: 'INSTRU_CLN',value: $("#INSTRU_CLN").val()},
						{name: 'FACTORY_NS',value: $("#FACTORY_NS").val()},
						{name: 'INSTRU_C3N',value: $("#INSTRU_C3N").val()},
						{name: 'INSTRU_STNS',value: $("#INSTRU_STNS").val()},
						{name: 'superStns',value: $("#superStns").val()},
						{name: 'txtDate',value: $("#txtDate").val()},
						{name: 'txtDate1',value: $("#txtDate1").val()}
						]});
			grid.loadData(true);
		}
      function assignM(flg){
      		
			 if(flg == 2){
				  $("#EditOilName").ligerGetComboBoxManager().selectValue(DWID);
				 $("#EditObjectCode").ligerGetComboBoxManager().selectValue(OBJECT_CODE);
				 $("#EditAreaName").ligerGetComboBoxManager().selectValue(QKID);
				 $("#EditStaName").ligerGetComboBoxManager().selectValue(ZHID);
				 $("#EditSuperName").ligerGetComboBoxManager().selectValue(ORG_ID);
				 $("#EditInstruDL").ligerGetComboBoxManager().selectValue(INSTRU_1TC);
				 $("#EditInstruSBMC").ligerGetComboBoxManager().selectValue(INSTRU_CLC);
				 $("#EditInstruSJSB").ligerGetComboBoxManager().selectValue(SJRTU_CLC);
				 $("#EditInstruZT").ligerGetComboBoxManager().selectValue(INSTRU_STVA);
				 document.getElementById("EditRemark").value =REMARK;
				 //document.getElementById("EditInstruGHID").value =INSTRUS_ID;
				  $("#EditInstruGHID").ligerGetComboBoxManager().selectValue(INSTRUS_ID);
				 document.getElementById("EditFactoryGH").value =SFACTORY_NS;	
				 document.getElementById("EditInstruSBXH").value =INSTRU_C3N;	
				 document.getElementById("EditFactoryZC").value =MFACTORY_NS;
				 document.getElementById("stationNo").value =stationNo;
				 //document.getElementById("EditInstruZT").value =INSTRU_STNS;
				
				 //document.getElementById("INSTRUMENT_ID").value =INSTRUMENT_ID;

				  /*document.getElementById("EditOilName").value =DWNAME;
				  document.getElementById("EditObjectCode").value =OBJECT_TYPE_NAME;
				  document.getElementById("EditAreaName").value =QKNAME;
				  document.getElementById("EditStaName").value =ZHNAME;
				  document.getElementById("EditSuperName").value =STRUCTURENAME;
				  document.getElementById("EditInstruDL").value =INSTRU_1TN;
				  document.getElementById("EditInstruSBMC").value =INSTRU_CLN;
				  document.getElementById("EditInstruSJSB").value =SJRTU_CLN;
				 
				 document.getElementById("EditInstruZT").value =INSTRU_STNS;
				 document.getElementById("EditRemark").value =REMARK;
				 document.getElementById("EditInstruGHID").value =INSTRUS_ID;

				 document.getElementById("EditFactoryGH").value =SFACTORY_NS;	
				 document.getElementById("EditInstruSBXH").value =INSTRU_C3N;	
				 document.getElementById("EditFactoryZC").value =MFACTORY_NS;	*/
				 //document.getElementById("INSTRUMENT_ID").value =INSTRUMENT_ID;	
				
			 }else if(flg == 1){
				  document.getElementById("EditOilName").value ="";
				  document.getElementById("EditObjectCode").value ="";
				  document.getElementById("EditAreaName").value ="";
				  document.getElementById("EditStaName").value ="";
				  document.getElementById("EditSuperName").value ="";
				  document.getElementById("EditInstruDL").value ="";
				  document.getElementById("EditInstruSBMC").value ="";
				  document.getElementById("EditInstruSJSB").value ="";
				 
				  document.getElementById("EditInstruZT").value ="";
				  document.getElementById("EditRemark").value ="";
				  document.getElementById("EditInstruGHID").value ="";

				  document.getElementById("EditFactoryGH").value ="";	
				  document.getElementById("EditInstruSBXH").value ="";	
				  document.getElementById("EditFactoryZC").value ="";	
				  document.getElementById("stationNo").value ="";
			 }
      }
         //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
            	  if(operate != 1 && operate != 2){
            		  setpage4(0); //显示编辑界面
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
              	    		setpage4(0); //显示编辑界面
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
        	    		alert('请选择一条你要删除的数据！')
        	     }else if(rows.length == 1){
        	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
            	     		 if(yes){
		                          jQuery.ajax({
										type : 'post',
										url : 'instru_removeInstru.action',
										async : false,
										data: {"INSTRUMENT_ID":INSTRUMENT_ID},
										success : function(data) {
											if (data != null && typeof(data)!="undefined"){
												var outData = eval('(' + data + ')');
													if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														alert(outData.ERRMSG);
													}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														alert(outerror(outData.ERRCODE));
													}else{
														$.ligerDialog.success('删除成功');
														onSubmit();
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
                  /**  case "involvesTable":
              	
            	  var oilstationname =	 $("#oilstationname").val()
	      	        var blockstationname 		= $("#blockstationname").val();
	      	        var wellnum 	= $("#wellnum").val();
	      	        var boiler 	= $("#boiler").val();
	      	        var YBtype 		= $("#YBtype").val();
	      	        var factory_nf 		= $("#factory_nf").val();
	      	        var object_type_name 	= $("#object_type_name").val();
	
		      		var totalNum = 0;
		      		var url = "instru_exceptDatas.action?oilstationname="+encodeURIComponent(oilstationname)+"&blockstationname="+encodeURIComponent(blockstationname)+"&wellnum="+encodeURIComponent(wellnum)
		      											  +"&boiler="+encodeURIComponent(boiler)+"&YBtype="+encodeURIComponent(YBtype)+"&factory_nf="+encodeURIComponent(factory_nf)
		      												   +"&object_type_name="+encodeURIComponent(object_type_name)
		      												+'&ptable=Eptable';
		      		var urls = "instru_exceptDatas.action?oilstationname="+encodeURIComponent(oilstationname)+"&blockstationname="+encodeURIComponent(blockstationname)+"&wellnum="+encodeURIComponent(wellnum)
		      												  +"&boiler="+encodeURIComponent(boiler)+"&YBtype="+encodeURIComponent(YBtype)+"&factory_nf="+encodeURIComponent(factory_nf)
		      												   +"&object_type_name="+encodeURIComponent(object_type_name)
		      												+'&ptable=ptable';
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
		      			$.ligerDialog.confirm('确定生成点表吗?<br/>您要生成点表的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url;});
		      		}
		      		else if(totalNum > 10000){
		      			$.ligerDialog.confirm('确定生成点表吗?<br/>您要生成点表的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url;});
		      		}
		      		else {
		      			$.ligerDialog.confirm('没有可生成点表的数据！');
		      		}
                  break;*/
		   		 case "involvesTable":
		   		  var MyDate = new Date().Format('yyyy-MM-dd hh:mm:ss');
		   			//alert(MyDate)
		   			
		     	  	var oilName = $("#oilName").val();
		   	        var objectCode  = $("#objectCode").val();
		   	        var ownObject 	= $("#ownObject").val();
		   	        var staName 	= $("#staName").val();
		   	        var areaName 	= $("#areaName").val();
		   	        var INSTRU_CLN 	= $("#INSTRU_CLN").val();
		   	        var INSTRU_STNS = $("#INSTRU_STNS").val();
		   	        var superStns   = $("#superStns").val();
		   	        var pointCode   = $("#pointCode").val();
		   	        var alamOr      = $("#alamOr").val();
		   	      	var url = "instru_exceptDatas.action?oilName="+encodeURIComponent(oilName)
		   	      		  +"&objectCode="+encodeURIComponent(objectCode)+"&ownObject="+encodeURIComponent(ownObject)
						  +"&staName="+encodeURIComponent(staName)+"&areaName="+encodeURIComponent(areaName)
						  +"&INSTRU_CLN="+encodeURIComponent(INSTRU_CLN)
						  +"&INSTRU_STNS="+encodeURIComponent(INSTRU_STNS)
						  
						   +"&superStns="+encodeURIComponent(superStns)
						  +"&pointCode="+encodeURIComponent(pointCode)
						   +"&alamOr="+encodeURIComponent(alamOr)
						  +"&MyDate="+encodeURIComponent(MyDate);
							//+'&ptable=Eptable';
		   	      $.ligerDialog.confirm('确定生成点表?',function (yes) {
		 				if (yes) {
		 					window.location.href= url;
		 				}
		 			});
		           break;
		            
              case "save":
					$("#form1").submit();
					break;
              case "up":
            	  setpage4(0); //显示编辑界面
            	    setItemsd(0);//0-显示编辑区，添加隐藏按钮
                break;   
                case "down":
                	setpage4(1); //隐藏编辑界面
				 	setItemsd(1);//1-隐藏编辑区添加显示按钮
                break;    
          }
      }
      function onExport() {
        
    	    		var oilName = $("#oilName").val();
					var objectCode = $("#objectCode").val();
					var areaName = $("#areaName").val();
					var staName = $("#staName").val();
					var ownObject = $("#ownObject").val();
					var INSTRU_1TN = $("#INSTRU_1TN").val();
					var INSTRU_CLN = $("#INSTRU_CLN").val();
					var FACTORY_NS = $("#FACTORY_NS").val();
					var INSTRU_C3N = $("#INSTRU_C3N").val();
					var INSTRU_STNS = $("#INSTRU_STNS").val();
					var superStns = $("#superStns").val();
					var txtDate = $("#txtDate").val();
					var txtDate1 = $("#txtDate1").val();

		var totalNum = 0;
		var url = "instru_searchInstru.action?oilName="+encodeURIComponent(oilName)
			+"&objectCode="+encodeURIComponent(objectCode)+"&areaName="+encodeURIComponent(areaName)
			+"&staName="+encodeURIComponent(staName)+"&ownObject="+encodeURIComponent(ownObject)
			+"&INSTRU_1TN="+encodeURIComponent(INSTRU_1TN)+"&INSTRU_CLN="+encodeURIComponent(INSTRU_CLN)
			+"&FACTORY_NS="+encodeURIComponent(FACTORY_NS)+"&INSTRU_C3N="+encodeURIComponent(INSTRU_C3N)
			+"&INSTRU_STNS="+encodeURIComponent(INSTRU_STNS)+"&superStns="+encodeURIComponent(superStns)
			+"&txtDate="+encodeURIComponent(txtDate)+"&txtDate1="+encodeURIComponent(txtDate1)
			+'&totalNum=export';
		var urls = "instru_searchInstru.action?oilName="+encodeURIComponent(oilName)
			+"&objectCode="+encodeURIComponent(objectCode)+"&areaName="+encodeURIComponent(areaName)
			+"&staName="+encodeURIComponent(staName)+"&ownObject="+encodeURIComponent(ownObject)
			+"&INSTRU_1TN="+encodeURIComponent(INSTRU_1TN)+"&INSTRU_CLN="+encodeURIComponent(INSTRU_CLN)
			+"&FACTORY_NS="+encodeURIComponent(FACTORY_NS)+"&INSTRU_C3N="+encodeURIComponent(INSTRU_C3N)
			+"&INSTRU_STNS="+encodeURIComponent(INSTRU_STNS)+"&superStns="+encodeURIComponent(superStns)
			+"&txtDate="+encodeURIComponent(txtDate)+"&txtDate1="+encodeURIComponent(txtDate1)
													+'&totalNum=totalNum';
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

<body style="overflow-x:heddin; padding:2px;">
	 <div id="mainsearch" style=" width:99%">
	 
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
						<tr>
						<td align="right" class="l-table-edit-td" style="width:120px">生产管理单位：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="oilName" name = "oilName" />
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:120px">所属对象类型：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="objectCode" name = "objectCode"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">区块名：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="areaName" name = "areaName"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px">站号名：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="staName" name = "staName"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">所属对象：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="ownObject" name = "ownObject"/>
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:100px">仪表大类名：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="INSTRU_1TN" name = "INSTRU_1TN"/>
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:120px">仪表设备名称：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="INSTRU_CLN" name = "INSTRU_CLN"/>
		                </td>
		               
		                <td align="right" class="l-table-edit-td" style="width:80px">供货厂家：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="FACTORY_NS" name = "FACTORY_NS"/>
		                </td>
		                  </tr>
		                <tr>
		                <td align="right" class="l-table-edit-td" style="width:120px">仪表设备型号：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="INSTRU_C3N" name = "INSTRU_C3N"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">仪表状态：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="INSTRU_STNS" name = "INSTRU_STNS"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:80px">上级设备：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="superStns" name = "superStns"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td valign="middle">--</td>
						<td><input type="text" id="txtDate1" ltype="date"/></td>
						<td align="right" class="l-table-edit-td" style="width:60px">点代码：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="pointCode" name = "pointCode"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px">报警否：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="alamOr" name = "alamOr"/>
		                </td>
		                <td align="right" class="l-table-edit-td"  >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:60px">查询</a>
		                </td>
		                <td align="left" class="l-table-edit-td"  >
		                <a href="javascript:onExport()" class="l-button" style="width:80px">导出报表</a>
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
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">生产管理单位:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属对象类型:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">区块名:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">站号名:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属对象:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">仪表大类名:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">仪表设备名称:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">上级设备:</td>
		                 </tr>
		                   <tr>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditOilName" type="text" id="EditOilName"  ltype="text"  />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditObjectCode" type="text" id="EditObjectCode"  ltype="text" validate="{required:true}" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="EditAreaName" type="text" id="EditAreaName"  ltype="text"  />
		                </td>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="EditStaName" type="text" id="EditStaName"  ltype="text"  />
		                </td>
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="EditSuperName" type="text" id="EditSuperName"  ltype="text" validate="{required:true}" />
		                </td>
		             
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="EditInstruDL" type="text" id="EditInstruDL"  ltype="text" validate="{required:true}" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditInstruSBMC" type="text" id="EditInstruSBMC" ltype="text" validate="{required:true}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditInstruSJSB" type="text" id="EditInstruSJSB" ltype="text"  />
		                </td>
		                   </tr>
		            	<tr>
		                 
		                  <td align="left" class="l-table-edit-td" style="width:150px">仪表状态:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">仪表供货ID名:</td>
		               	  <td align="left" class="l-table-edit-td" style="width:150px">供货厂家:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">仪表设备型号:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">制造厂家:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">通讯站号:</td>
		            	  </tr>
		           		 <tr>
		               
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditInstruZT" type="text" id="EditInstruZT" ltype="text" validate="{required:true}" />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="EditRemark" type="text" id="EditRemark" ltype="text" />
		                </td>
		          
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditInstruGHID" type="text" id="EditInstruGHID" ltype="text" validate="{required:true}" />
		                	
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditFactoryGH" type="text" id="EditFactoryGH" ltype="text"  disabled="disabled"/>
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditInstruSBXH" type="text" id="EditInstruSBXH" ltype="text"   disabled="disabled"/>
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="EditFactoryZC" type="text" id="EditFactoryZC" ltype="text"   disabled="disabled"/>
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="stationNo" type="text" id="stationNo" ltype="text"  />
		                </td>
		              
		            </tr>
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>