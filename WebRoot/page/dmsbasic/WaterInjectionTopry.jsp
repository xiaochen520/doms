<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>注水撬基础信息管理</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    
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
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        
        //鼠标选择数据
		var water_injectiontoprid ; 	//注水撬id
		var water_injectiontopr_name;		//注水撬名称
		var blockstationname;	//所属采油站
		var gh_id;			//采油站ID
		var qkid;			//所属区块
		var commissioning_date;	//投产日期
		var scrapped_date;	//报废日期
		var jrbz;
		var ljjd_s;
		var status_value;	//建设生产状态
		var org_id;			//组织结构id
		var remark;			//备注
		var gh_id;
		var clearSelecteValue=0;
		var serverid;
		var code;
		var dyear;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'waterIT_pageInit.action',
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
				url:'rulewell_queryOilSationInfo.action?arg=injectionTopry',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					liger.get("rulewellname1").setValue('');
					liger.get("groupTeam").setValue('');
					if (data != null && typeof(data)!="undefined" && data != '' && data !=1) {
						//setOilGroupWaterInjec($("#oilstationname1").val(),"","","");
						setWellData($("#oilstationname1").val());
					}
					else {
						getWellInitData();
					}
				}
			});
			$("#groupTeam").ligerComboBox({
				url:'manifoldBasicInfo_queryTeamGInfo.action',//组织结构ID
				valueField: 'id',
				valueFieldID: 'groupTeamid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				hideOnLoseFocus:true,
	            autocomplete:true,
	        	onSelected :function (data){
				liger.get("rulewellname1").setValue('');
				if ($("#groupTeam").val() != null  && $("#groupTeam").val() != '') {
					//setOilGroupWaterInjec("",$("#groupTeam").val(),"");
					setGroupWellData($("#groupTeam").val());
				}
				//else {
					//getWellInitData();
				//}
			}
			});
			
			$("#rulewellname1").ligerComboBox({
				url:'waterFL_injectName.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
			});
			/*$("#oilstationname").ligerComboBox({
				url:'rulewell_queryOilSationInfo.action?arg=all',
				valueField: 'id',
				valueFieldID: 'oilid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
				}
			});*/
			// $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
             $("#commissioning_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
             $("#scrapped_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });
             document.getElementById("JRBZ1").value= "全部";
		 	  $("#JRBZ1").ligerComboBox({
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
            
            $("#oilstationname").ligerComboBox({
				url:'rulewell_queryOilSationInfo.action?arg=all',
				valueField: 'id',
				valueFieldID: 'textoilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
            	//liger.get("groupName").setValue('');
				//if (data != null && typeof(data)!="undefined" && data != ''){
				//	setOGIW($("#textoilationid").val(),"","","");
				 // }
            }
			});
            $("#groupName").ligerComboBox({
            	url:'manifoldBasicInfo_queryTeamGInfo.action',
				valueField: 'id',
				valueFieldID: 'groupNameid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true
			});
            
            $("#areablock1").ligerComboBox({
            	url:'rulewell_queryAreablockInfo.action?orgid=wi',
    			valueField: 'id',
    			valueFieldID: 'areablockid',
    			textField: 'text',
    			selectBoxHeight:350,
    			selectBoxWidth:100,
    			autocomplete:true,
    			hideOnLoseFocus:true
    		});
			  $("#areablock").ligerComboBox({
        			url:'sagd_queryAreablockInfo.action?arg='+'a',
        			valueField: 'id',
        			valueFieldID: 'areaid',
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
                    
					
              	   
              	   	var water_injectiontoprid  = $("#water_injectiontoprid").val();
					var water_injectiontopr_name  = $("#water_injectiontopr_name").val();
					var commissioning_date  = $("#commissioning_date").val();
					var scrapped_date  = $("#scrapped_date").val();
					var code =  $("#code").val();
					var jrbz="";
					if($("#jrbz").val() != ''){
						jrbz = $("#jrbzid").val();
					}
					var ljjd_s  = $("#ljjd_s").val();
					if(ljjd_s != ''){
						ljjd_s =  $("#hidserverid").val() ;
					}
					var status_value  = $("#status_value").val();
				//	var blockstationname = $("#blockstationname").val();
					areaid = "";
					if($("#areablock").val() != ''){
						areaid = $("#areaid").val();
					}
					var oilstation = "";
					if($("#groupName").val() != ''){
						
						oilstation = $("#groupNameid").val();
					}
					var org_id  = $("#org_id").val();
					var remark  = $("#remark").val();
					var dyear = $("#dyear").val();
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	  		 
              	   	   jQuery.ajax({
									type : 'post',
									url : 'waterIT_addOrUpdateWIT.action',
									async : false,
									data: {"water_injectiontopr_name":water_injectiontopr_name ,"commissioning_date":commissioning_date,
              	   		"scrapped_date":scrapped_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"oilstation":oilstation,"areaid":areaid,"code":code,
											"dyear":dyear,"status_value":status_value,"remark":remark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage2(1); //隐藏编辑界面
											  setItemsd(2); //去掉隐藏，显示按钮
																						
											$.ligerDialog.success('注水撬添加成功！');
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
									url : 'waterIT_addOrUpdateWIT.action',
									async : false,
									data: {"water_injectiontopr_name":water_injectiontopr_name ,"commissioning_date":commissioning_date,
              	   		"scrapped_date":scrapped_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"id":water_injectiontoprid,"oilstation":oilstation,"areaid":areaid,
              	   	"dyear":dyear,	"code":code,"status_value":status_value,"remark":remark},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
											onSubmit();
											setpage2(1); //隐藏编辑界面
											  setItemsd(2); //去掉隐藏，显示按钮
																						
											$.ligerDialog.success('注水撬修改成功！');
											operate = 0;
										}else{
											$.ligerDialog.error(outerror(jsondata));
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
        							//gh_id = rowdata.GH_ID;
        							water_injectiontoprid = rowdata.WATER_INJECTIONTOPRID;
									water_injectiontopr_name = rowdata.WATER_INJECTIONTOPR_NAME;
									
									if (rowdata.BLOCKSTATIONNAME != null && typeof(rowdata.BLOCKSTATIONNAME)!="undefined"){
										groupTeam = rowdata.BLOCKSTATIONNAME;
									 }else{
										 groupTeam = "";
									 }
									 
									if (rowdata.COMMISSIONING_DATE != null && typeof(rowdata.COMMISSIONING_DATE)!="undefined"){
									 	commissioning_date = rowdata.COMMISSIONING_DATE;
									 }else{
									 	commissioning_date = "";
									 }
									 if (rowdata.CODE != null && typeof(rowdata.CODE)!="undefined"){
										 code = rowdata.CODE;
									 }else{
										 code = "";
									 }
									 jrbz = rowdata.JRBZ;
										if (rowdata.LJJD_S != null && typeof(rowdata.LJJD_S)!="undefined"){
										 	ljjd_s = rowdata.LJJD_S;
										 }else{
										 	ljjd_s = "";
										 }				
									if (rowdata.GH_ID != null && typeof(rowdata.GH_ID)!="undefined"){
									 	gh_id = rowdata.GH_ID;
									 }else{
										 gh_id = "";
									 }
							    	 				
										if (rowdata.DYEAR != null && typeof(rowdata.DYEAR)!="undefined"){
											dyear = rowdata.DYEAR;
										 }else{
										 	dyear = "";
										 }
																 
									 if(rowdata.QKID != null && typeof(rowdata.QKID)!="undefined"){
									 	qkid = rowdata.QKID;
									 }else{
									 	qkid = "";
									 }
									 
									if (rowdata.STATUS_VALUE != null && typeof(rowdata.STATUS_VALUE)!="undefined"){
									 	status_value = rowdata.STATUS_VALUE;
									 }else{
									 	status_value = "";
									 }
									 
									 
									if (rowdata.LJJDID != null && typeof(rowdata.LJJDID)!="undefined"){
									 	serverid = rowdata.LJJDID; 
									 }else{
									 	serverid = "";
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

        function setWellData(data,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'waterIT_queryWellInfo.action',
    			data: {"orgid":data},
    			timeout : '3000',
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("groupTeam").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("groupTeam").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				
    			}
    		});
    	}
        
        function setGroupWellData(data,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'searchQueryAll_searchGroupWellOnchange.action',
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
    				
    			}
    		});
    	}
        function setOilGroupWaterInjec(OilName,groupName,injeName) {
            var groupText =[{ id: 1 , text: '' }];
            var injectText =[{ id: 1 , text: '' }];
          //  var wellText =[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url:'searchQueryAll_searchOilGroupWaterInjec.action',
    			data: {"OilName":OilName,"groupName":groupName,"injeId":injeName},
    			timeout : '3000',
    			success : function(jsondata) {

        			var dataObj = $.parseJSON(jsondata);
        				$.each(dataObj, function(key,val){
        					if (key == 'groupText') {
        						groupText = val;
        					}
        					if (key == 'injectText'){
        						injectText = val;
        					}
        				});
        				setReturnData(groupText,injectText);
        			
    			},
    			error : function(jsondata) {
    				
    			}
    		});
    	}
    	function setReturnData(groupText,injectText){
    		liger.get("groupTeam").setData(groupText);
    		liger.get("rulewellname1").setData(injectText);
    		//liger.get("rulewellname1").setData(wellText);
        	}
        function getWellInitData() {
    		var oilText=[{ id: 1 , text: '' }];
    		var TeamText=[{ id: 1 , text: '' }];
    		var wellText=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'waterFL_cascadeInit.action',
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'oiltext') {
    						oilText = val;
    					}
    					if (key == 'TeamText') {
    						TeamText = val;
    					}
    					if (key == 'welltext'){
    						wellText = val;
    					}
    				});
    				setInitData(oilText,TeamText,wellText);
    			}
    		});
    	}
    	
        function setInitData(oilText,TeamText,wellText) {
    		liger.get("oilstationname1").setData(oilText);
    		liger.get("groupTeam").setData(TeamText);
    		liger.get("rulewellname1").setData(wellText);
    	}
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
						//alert(id);
			//alert(name);
			//alert(datatype);
			var oilstationname="全部";
			/* var areablock='';
			var blockstationname='';
			var ghname=''; */
			var wellnum='';
			var groupName ='';
			$("#oilstationname1").ligerGetComboBoxManager().setValue('');
			//$("#JRBZ1").ligerGetComboBoxManager().setValue('2');
			getWellInitData();
			$("#areablock1").ligerGetComboBoxManager().setValue('');
			document.getElementById("dyearC").value="";
			document.getElementById("JRBZ1").value="全部";
			//document.getElementById("areablock1").value="";
			//document.getElementById("blockstationname1").value="";
			//document.getElementById("wellnum1").value="";
			 
			if(datatype=='4'){
				oilstationname=name;
				$("#oilstationname1").val(name);
			}
			
			
			if(datatype=='13'){
				wellnum=name;
			$("#rulewellname1").val(name);
				//alert(wellnum);
			}
			if(datatype=='15'){
				groupName=name;
				$("#groupTeam").val(name);
			}
			
			grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: oilstationname
				},{
					name: 'rulewellname1',
					value: wellnum
				},{
					name: 'areablock1',
					value: $("#areablock1").val()
				},{
					name :'groupTeam',
					value:groupName
					}
				]
        	});
         	grid.loadData(true);
		}
		
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: $("#oilstationname1").val()
				},
				{
					name: 'groupTeam',
					value: $("#groupTeam").val()
					},{
					name: 'rulewellname1',
					value: $("#rulewellname1").val()
				},{
					name: 'areablock1',
					value: $("#areablock1").val()
				},{
					name:'JRBZ1',
					value:$("#JRBZ1").val()
					},
					{
						name:'dyearC',
						value:$("#dyearC").val()}
				]
        	});
         	grid.loadData(true);
        }
         var exportFlag=false;
         function onExport(){
        	 var Eoilationname=$("#oilstationname1").val();
 	         var Eblockstationname=$("#rulewellname1").val();
 	        	var EboilersName=$("#areablock1").val();
 	      
 	         //var Ewaterflooding=$("#waterflooding1").val();
 	         var Ejrbz1=$("#JRBZ1").val();
 	         var EdyearC = $("#dyearC").val();	
 	        var groupTeam = $("#groupTeam").val();

			if (exportFlag) {
				Eoilationname=oilstationname1;
				Eblockstationname =rulewellname1;
				EboilersName=areablock1;
				Ejrbz1=JRBZ1;
				EdyearC = dyearC;
				groupTeam=groupTeam;
			}
			var totalNum = 0;
			
			var url = 'waterIT_onExport.action?oilstationname1='+encodeURIComponent(Eoilationname)+'&groupTeam='+encodeURIComponent(groupTeam)+'&areablock1='+encodeURIComponent(EboilersName)+'&JRBZ1='+encodeURIComponent(Ejrbz1)+'&dyearC='+encodeURIComponent(EdyearC)+'&totalNum=export'+'&rulewellname1='+encodeURIComponent(Eblockstationname);
			var urls = 'waterIT_onExport.action?oilstationname1='+encodeURIComponent(Eoilationname)+'&groupTeam='+encodeURIComponent(groupTeam)+'&areablock1='+encodeURIComponent(EboilersName)+'&JRBZ1='+encodeURIComponent(Ejrbz1)+'&dyearC='+encodeURIComponent(EdyearC)+'&totalNum=totalNum'+'&rulewellname1='+encodeURIComponent(Eblockstationname);
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
				 //$("#groupName").ligerGetComboBoxManager().selectValue(groupNameid); 
				 	document.getElementById("groupName").value= groupTeam;
				 
				// $("#oilstationname").ligerGetComboBoxManager().selectValue(gh_id);
				 $("#areablock").ligerGetComboBoxManager().selectValue(qkid); 
			 		document.getElementById("water_injectiontoprid").value = water_injectiontoprid;
	               	document.getElementById("water_injectiontopr_name").value = water_injectiontopr_name;
	               	document.getElementById("commissioning_date").value= commissioning_date;
	               	document.getElementById("code").value= code;
	      			$("#ljjd_s").ligerGetComboBoxManager().selectValue(serverid);
	            
	            	 var ac=$("#jrbz").ligerGetComboBoxManager().findValueByText(jrbz);
				 	$("#jrbz").ligerGetComboBoxManager().selectValue(ac);
	            	 
	               	if (status_value != null && typeof(status_value)!="undefined" && status_value != '') {
						$("#status_value").ligerGetComboBoxManager().selectValue(status_value);
					}
					else {
						document.getElementById("status_value").value= status_value;
					}
	               
	              	document.getElementById("dyear").value= dyear;
	               //	document.getElementById("blockstationname").value= blockstationname;
	               	document.getElementById("org_id").value= org_id;
	               	document.getElementById("remark").value= remark;
			 }else if(flg == 1){
					$("#groupName").ligerGetComboBoxManager().selectValue(""); 
					 //$("#oilstationname").ligerGetComboBoxManager().selectValue("");
	               	$("#areablock").ligerGetComboBoxManager().selectValue("");
			 		document.getElementById("water_injectiontoprid").value = "";
	               	document.getElementById("water_injectiontopr_name").value = "";
	               	document.getElementById("commissioning_date").value="";
	               	document.getElementById("code").value= "";
	            	$("#ljjd_s").ligerGetComboBoxManager().selectValue("");
	               	$("#jrbz").ligerGetComboBoxManager().selectValue("");
	               	$("#status_value").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("status_value").value= "";
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
											url : 'waterIT_removeGasWell.action',
											async : false,
											data: {"water_injectiontoprid":water_injectiontoprid,"org_id":org_id},
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
														$.ligerDialog.success('注水撬基础删除成功！');
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
						<td align="left" class="l-table-edit-td" style="width:80px">所属采油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname1" name = "oilstationname1" />
		                </td>
		               <td align="left" class="l-table-edit-td" style="width:60px">所属班组：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="groupTeam" name = "groupTeam"/>
		                </td>
		               
		                <td align="left" class="l-table-edit-td" style="width:60px">注水撬名：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="rulewellname1" name = "rulewellname1"/>
		                </td>
		                <td align="left" class="l-table-edit-td" style="width:80px">注水撬区块：</td>
						<td align="left" class="l-table-edit-td" style="width:80px">
							<input type="text" id="areablock1" name = "areablock1"/>	
						</td>
						</tr>
						<tr>
						    <td align="left" class="l-table-edit-td" style="width:60px">接入标志：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="JRBZ1" name = "JRBZ1"/>	
		                </td>
		                   <td align="left" class="l-table-edit-td" style="width:80px">设计产能年：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="dyearC" name = "dyearC"/>	
		                </td>
		               
		                <td align="left" class="l-table-edit-td" style="width:20px"></td>
		                <td align="left" class="l-table-edit-td"  >
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
	  
		   <div id="edituser" >
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		            	<td align="left" class="l-table-edit-td" style="width:150px">注水撬名称:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px ; display :none">所属采油站:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属班组:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属区块:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">注水撬编码:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">建设生产状态:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">投产日期:</td>
		               
		                 </tr>
		                 <tr>
		                 	 
		            	 <td align="left" class="l-table-edit-td" >
		                	<input name="water_injectiontopr_name" type="text" id="water_injectiontopr_name"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                 <!-- <td align="left" class="l-table-edit-td"  style="dispaly :none">
		                	<input name="oilstationname" type="text" id="oilstationname"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td> -->
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="groupName" type="text" id="groupName"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="areablock" type="text" id="areablock"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		               
		               <!--  <td align="left" class="l-table-edit-td" >
		                	<input name="blockstationname" type="text" id="blockstationname"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                -->
		            
		               <td align="left" class="l-table-edit-td" style="">
		                	<input name="code" type="text" id="code" ltype="text"  />
		                </td>
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="status_value" type="text" id="status_value" ltype="text" />
		                </td>
		                
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="commissioning_date" type="text" id="commissioning_date" ltype="date"  />
		                </td>
		             
		            
		            
		                 <tr/>
		                 <tr>
		                  <td align="left" class="l-table-edit-td" style="width:150px">产能设计年:</td>
		                  <td align="left" class="l-table-edit-td" style="width:150px">接入标志:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">服务器逻辑节点名:</td>
		                
		                 <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		            </tr>
		            <tr>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="dyear" type="text" id="dyear" ltype="text" validate="{minlength:0,maxlength:4}"  />
		                </td>
		            	 <td align="left" class="l-table-edit-td" style="">
		                	<input name="jrbz" type="text" id="jrbz" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		             	<td align="left" class="l-table-edit-td" >
		                	<input name="ljjd_s" type="text" id="ljjd_s" ltype="text"  />
		                </td>
		               
		               
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	
		                	<!-- <input name="audbshor_date" type="text" id="audbshor_date" ltype="text" /> -->
		             
		                	<input name="water_injectiontoprid" type="hidden" id="water_injectiontoprid" />
		              
		                	<input name="org_id" type="hidden" id="org_id" />
		               
		                	<!-- <input type="submit" value="提交" id="Button1" class="l-button" style="width:100px" />  -->
		                </td>
		            </tr>
		            
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>