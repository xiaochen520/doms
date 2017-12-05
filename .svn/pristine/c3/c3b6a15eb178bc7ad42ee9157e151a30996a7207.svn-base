<%@ page language="java" import="java.util.*,com.echo.dto.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)session.getAttribute("userInfo");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>注水井日报维护数据</title>
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
     var cjsj ;
     var wellname ;
     var zqzwater ;
     var	pqjby ;
     var	jkyy ;
     var	ty;
     var	pqj	;
     var	jk;
     var 	pzl ;
     var	rzl ;
     var	remark;
     var wellrpdid;
     var waterfloodingWellid;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
    
       $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'floodingRPD_pageInit.action',
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
				url:'rulewell_queryOilSationInfo.action?arg=injectionFlood',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					clearSelecteValue=1;
					liger.get("rulewellname1").setValue('');
					liger.get("waterflooding1").setValue('');
					liger.get("areablock").setValue('');
					if ($("#oilationid").val() != 1) {
						setzb($("#oilstationname1").val());
					}
					else {
						//getWellInitData();
					}
				}
			});
			//班组
			$("#areablock").ligerComboBox({
				//url:'rulewell_queryAreablockInfo.action?orgid=waterf',
				url:'searchQueryAll_searchAllGroup.action',
				valueField: 'id',
				valueFieldID: 'wellAreaSelfid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:130,
				hideOnLoseFocus:true,
	            autocomplete:true,
	    		onSelected:function (data){
				liger.get("rulewellname1").setValue('');
				liger.get("waterflooding1").setValue('');
				if (data !=null && data !='' && typeof(data) !="undefined") {
					setSearch($("#areablock").val());
				}
				else {
					//getWellInitData();
				}
			}
			});
			$("#rulewellname1").ligerComboBox({
				//url:'waterIT_queryWellInfo.action',
				url:'waterFL_injectName.action',
				valueField: 'id',
				valueFieldID: 'wateritid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:160,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
					liger.get("waterflooding1").setValue('');
					if (data != null && typeof(data)!="undefined" && data != ''){
						setSearchInje("",$("#rulewellname1").val());
					}
				}
			});
			$("#waterflooding1").ligerComboBox({
				url:'waterFL_queryWaterflooding.action',
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

			//编辑区下拉列表↓
			$("#GroupWH").ligerComboBox({
				url:'searchQueryAll_searchAllGroup.action',
				valueField: 'id',
				valueFieldID: 'GroupWHid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:120,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					clearSelecteValue=1;
					liger.get("InhectWH").setValue('');
					liger.get("wellNameWH").setValue('');
					if (data !=null && data !='' && typeof(data) !="undefined") {
						setChangeGroupOnQWO($("#GroupWH").val());
					}
					else {
						//getWellInitData();
					}
				}
			});
			$("#InhectWH").ligerComboBox({
				url:'waterFL_injectName.action',
				valueField: 'id',
				valueFieldID: 'InhectWHid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					liger.get("wellNameWH").setValue('');
					if ( data !=null && data !='' && typeof(data) !="undefined") {
						setChangeGroupOnQW("",$("#InhectWH").val());
					}
					else {
						//getWellInitData();
					}
				}
			});
			
			$("#wellNameWH").ligerComboBox({
				url:'waterFL_queryWaterflooding.action',
				valueField: 'id',
				valueFieldID: 'wellNameWHid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
				}
			});
			//编辑区下拉列表↑
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
            $("#oilstationname1").val("<%=user.getCyz()%>");
			$("#areablock").val("<%=user.getTeam()%>");
             
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
                wellname  = $("#wellNameWH").val();
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
                 	wellrpdid = $("#wellrpdid").val();
                 	waterfloodingWellid = $("#waterfloodingWellid").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if (operate == 1 || operate == 2){

              		   jQuery.ajax({
									type : 'post',
									url : 'floodingRPD_saveOrUpdate.action',
									async : false,
									data: {"wellname":wellname ,"cjsj":cjsj,"zqzwater":zqzwater,"pqjby":pqjby,"jkyy":jkyy,
              	   							"ty":ty,"pqj":pqj,"jk":jk,"pzl":pzl,"rzl":rzl,"remark":remark,"wellrpdid":wellrpdid,"waterfloodingWellid":waterfloodingWellid},
									success : function(data) { 
              	   							if (data != null && typeof(data)!="undefined" && "1" == data){
												onSubmit();
												//$.ligerDialog.success('保存成功！');
												alert('保存成功！');
												setpage2(1); //隐藏编辑界面
												setItemsd(2); //去掉隐藏，显示按钮
												
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
  	 		if (rowdata.wellrpdid != null && typeof(rowdata.wellrpdid)!="undefined"){
  	 			wellrpdid = rowdata.wellrpdid; 
			 }else{
				 wellrpdid = "";
			 }
  	 		if (rowdata.waterfloodingWellid != null && typeof(rowdata.waterfloodingWellid)!="undefined"){
  	 			waterfloodingWellid = rowdata.waterfloodingWellid; 
			 }else{
				 waterfloodingWellid = "";
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
  	 
  	 		if (rowdata.injectname != null && typeof(rowdata.injectname)!="undefined"){
  	 			injectname = rowdata.injectname; 
			 }else{
				 injectname = "";
			 }
  	 		if (rowdata.wellName != null && typeof(rowdata.wellName)!="undefined"){
  	 			wellname = rowdata.wellName; 
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
				// document.getElementById("wellNameWH").value =oilName  ;
				 document.getElementById("GroupWH").value =groupname  ;
				 document.getElementById("InhectWH").value =injectname;
				 document.getElementById("wellNameWH").value =wellname  ;
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
				 document.getElementById("wellrpdid").value = wellrpdid ;
				 document.getElementById("waterfloodingWellid").value = waterfloodingWellid ;


			 }else if(flg == 1){
				 document.getElementById("GroupWH").value =""  ;
				 document.getElementById("InhectWH").value =""  ;
	             document.getElementById("wellNameWH").value ="";
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
				 document.getElementById("wellrpdid").value ="";
				 document.getElementById("waterfloodingWellid").value ="";
			 }
			 		
   }
  		function setChangeGroupOnQW(groupName , injectName){
			jQuery.ajax({
				type : 'post',
				url:'floodingRPD_searchChangeGroupOnQW.action',
				data: {"groupName":groupName,"injectName":injectName},
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("wellNameWH").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("wellNameWH").setData("");
    				}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
  	  	}
	  		function setChangeGroupOnQWO(groupName){
	  			var wellGRText=[{ id: 1 , text: '' }];
	    		var wellinText=[{ id: 1 , text: '' }];
	    		jQuery.ajax({
	    			type : 'post',
	    			url : 'floodingRPD_searchOnChangeMany.action',
	    			data :{"groupName":groupName},
	    			success : function(jsondata) {
	    			var dataObj = $.parseJSON(jsondata);
	    				$.each(dataObj, function(key,val){
	    					if (key == 'wellGRText') {
	    						wellGRText = val;
	    					}
	    					if (key == 'wellinText'){
	    						wellinText = val;
	    					}
	    				});
	    				setInitDataQW(wellGRText,wellinText);
	    			}
	    		});
	    	}
	    	
	        function setInitDataQW(wellGRText,wellinText) {
	        	liger.get("InhectWH").setData(wellinText);
	    		liger.get("wellNameWH").setData(wellGRText);
	    	}
	    		
        function getWellInitData() {
    		var oilText=[{ id: 1 , text: '' }];
    		var wellText=[{ id: 1 , text: '' }];
    		var waterfloodText=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'floodingRPD_cascadeInit.action',
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'oiltext') {
    						oilText = val;
    					}
    					if (key == 'welltext'){
    						wellText = val;
    					}
    					if (key == 'waterfloodtext'){
    						waterfloodText = val;
    					}
    				});
    				setInitData(oilText,wellText,waterfloodText);
    			}
    		});
    	}
    	
        function setInitData(oilText,wellText,waterfloodText) {
    		liger.get("oilstationname1").setData(oilText);
    		liger.get("rulewellname1").setData(wellText);
    		liger.get("waterflooding1").setData(waterfloodText);
    	}

  		function setSearch(groupName){
  			var wellGRText=[{ id: 1 , text: '' }];
    		var wellinText=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'floodingRPD_searchOnChangeMany.action',
    			data :{"groupName":groupName},
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'wellGRText') {
    						wellGRText = val;
    					}
    					if (key == 'wellinText'){
    						wellinText = val;
    					}
    				});
    				setSearchDispl(wellGRText,wellinText);
    			}
    		});
    	}
    	
        function setSearchDispl(wellGRText,wellinText) {
        	liger.get("rulewellname1").setData(wellinText);
    		liger.get("waterflooding1").setData(wellGRText);
    	}
  		function setSearchInje(groupName , injectName){
			jQuery.ajax({
				type : 'post',
				url:'floodingRPD_searchChangeGroupOnQW.action',
				data: {"groupName":groupName,"injectName":injectName},
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("waterflooding1").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("waterflooding1").setData("");
    				}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
  	  	}
        function setzb(data) {
    		jQuery.ajax({
    			type : 'post',
    			url:'waterIT_queryWellInfo.action',
    			data: {"orgid":data},
    			timeout : '3000',
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("areablock").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("areablock").setData("");
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
							setpage2(0); //显示编辑界面
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
					if(cjsj != MyDate){
						alert(cjsj + '不是当前系统时间不让修改')
						break;
					}
					 	if(operate != 1 && operate != 2){
							setpage2(0); //显示编辑界面
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
					
						if(confirm("确定删除吗?")){
								if (wellrpdid != '' && wellrpdid != null && wellrpdid!="undefined") {
										jQuery.ajax({
										type : 'post',
										url : 'floodingRPD_removeWaterRPD.action',
										data: {"wellrpdid":wellrpdid,"wellname":wellname},
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
								}
						}
						
						
						
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
                case "dataready":
                	//alert(1);
    				jQuery.ajax({
    					type : 'post',
						url : 'floodingRPD_Dataready.action?nowdata='+parseInt(Math.random()*100000),
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
							url : 'floodingRPD_Automate.action?nowdata='+parseInt(Math.random()*100000),
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
      	    			url:'importExcel.jsp?pageid=zsrb',
      	    			width: 600,
      	    			height: 260
      	    		});
				break;
				
				
			}
		}
           function onSubmit()
        {
        	    var oilstationname1=$("#oilstationname1").val();
  	         	var rulewellname1=$("#rulewellname1").val();
  	         	var waterflooding1=$("#waterflooding1").val();
  	      		var areablock=$("#areablock").val();

  	      		grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: oilstationname1
				},{
					name: 'rulewellname1',
					value: rulewellname1
				},{
					name: 'waterflooding1',
					value: waterflooding1
				},{
					name: 'areablock',
					value: areablock
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
			
		   	 var oilstationname1=$("#oilstationname1").val();
  	         var rulewellname1=$("#rulewellname1").val();//qiao
  	         var waterflooding1=$("#waterflooding1").val();//jing
  	         var areablock=$("#areablock").val();
			 var txtDate = $("#txtDate").val();
			 var txtDate1 = $("#txtDate1").val();
			if (exportFlag) {
				oilstationname1=oilstationname1;
				rulewellname1=rulewellname1;
				waterflooding1=waterflooding1;
				areablock=areablock;
				
			}
			var totalNum = 0;
			var url = 'floodingRPD_searchwaterFLRRD.action?oilstationname1='+encodeURIComponent(oilstationname1)+'&rulewellname1='+encodeURIComponent(rulewellname1)+'&waterflooding1='+encodeURIComponent(waterflooding1)+'&areablock='+encodeURIComponent(areablock)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=export';
			var urls = 'floodingRPD_searchwaterFLRRD.action?oilstationname1='+encodeURIComponent(oilstationname1)+'&rulewellname1='+encodeURIComponent(rulewellname1)+'&waterflooding1='+encodeURIComponent(waterflooding1)+'&areablock='+encodeURIComponent(areablock)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=totalNum';
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
					<td align="left" class="l-table-edit-td" style="width:50px">采油站:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname1" name = "oilstationname1" />
		                </td>
		                  <td align="left" class="l-table-edit-td" style="width:40px">班组:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="areablock" name ="areablock"/>	
		                </td>
		                <td align="left" class="l-table-edit-td" style="width:50px">注水撬:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="rulewellname1" name = "rulewellname1"/>
		                </td>
		                
		                <td align="left" class="l-table-edit-td" style="width:50px">注水井:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="waterflooding1" name = "waterflooding1"/>
		                </td>
		              
		                <td align="left">
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
		             <td align="left" class="l-table-edit-td" style="width:150px">所属班组:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属注水撬:</td>
		           		<td align="left" class="l-table-edit-td" style="width:150px">井号:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">日期:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">注汽/注水小时h:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">配汽间/泵压:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">井口/油压:</td>
		            </tr>
		            <tr>
		              	<td align="left" class="l-table-edit-td" >
		                	<input name="GroupWH" type="text" id="GroupWH" ltype="text" />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="InhectWH" type="text" id="InhectWH" ltype="text"  />
		                </td>
		                
		            	<td align="left" class="l-table-edit-td" >
		                	<input name="wellNameWH" type="text" id="wellNameWH"  ltype="text" validate="{required:true }" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="CJSJWH" type="text" id="CJSJWH"  ltype="date" />
		                </td>
		                
		           		 <td align="left" class="l-table-edit-td" >
		                	<input name="ZQZSHoureWH" type="text" id="ZQZSHoureWH"  validate="{number:true,min:0,max:24}" ltype="text" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="PQJbengyaWH" type="text" id="PQJbengyaWH"  validate="{number:true,min:0,max:20}" ltype="text" />
		                </td>
		               <td align="left" class="l-table-edit-td" >
		                	<input name="WellKYoYaWH" type="text" id="WellKYoYaWH" validate="{number:true,min:0,max:20}" ltype="text" />
		                </td>
		            </tr>
		            <tr>
			           
			            <td align="left" class="l-table-edit-td" style="width:150px">套压:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">配汽间:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">井口:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">配注量:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">日注量:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		            </tr>
		             <tr>
		                
		               
		       
		                <td align="left" class="l-table-edit-td" >
		                	<input name="TaoyaWH" type="text" id="TaoyaWH" validate="{number:true,min:0,max:20}" ltype="text"  />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="PQJianWH" type="text" id="PQJianWH" ltype="text" />
		                </td>
		                
		                 <td align="left" class="l-table-edit-td" >
		                 	<input name="wellKouWH" type="text" id="wellKouWH" ltype="text"  />
		                 </td>
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="PeiZLWH" type="text" id="PeiZLWH"  validate="{number:true,min:0,max:100}" ltype="text" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="DayZLWH" type="text" id="DayZLWH"  validate="{number:true,min:0,max:100}" ltype="text" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remarkWH" type="text" id="remarkWH" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
							<input name="wellrpdid" type="hidden" id="wellrpdid" />
						</td>
						<td align="left" class="l-table-edit-td" >
							<input name="waterfloodingWellid" type="hidden" id="waterfloodingWellid" />
						</td>
		            </tr>
		             
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>