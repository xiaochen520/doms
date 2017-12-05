<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>SAGD井日报数据维护</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
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
        var grid1 = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        var dataflg = 1;
        var ghname='';
        var oilstation;
        var areaname='';
		var stationname='';
		var wellname='';
		var wellData;
		var csData;
		var gjData;
		var dailyOutput=0;
		var waterRation=0;
		var pdailyOutput=0;
		var pwaterRation=0;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
			seachSelectData();
                jQuery.ajax({
					type : 'post',
					url : 'sagddrpd_SagdRPDpageInit.action?arg=sc',
					async : false,
					success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid1 = $("#maingrid").ligerGrid(data);					
							toolbar = grid1.topbar.ligerGetToolBarManager();	
							toolbar.removeItem("modifyid"); 
							if (toolbar != null && typeof(toolbar)!="undefined"){
								var toolteams = toolbar.options.items;
								//var authorityflg = 0;
								
					 	       	for(var i=0; i<toolteams.length ; i++){
									if(toolteams[i].id == 'statementid'){
										//authorityflg = 1;
										toolbar.removeItem("statementid"); 
										jQuery("#dayreport").css('display','block');
										
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
				
				//getButtonInit();
                var proData = [{ id: 1 , text: '' }];

                   $("#areablock").ligerComboBox({
                	url:'sagd_queryAreablockInfo.action?arg=sagd',
        			valueField: 'id',
        			valueFieldID: 'areablockid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			autocomplete:true,
        			hideOnLoseFocus:true,
        			onSelected:function (data){
                		liger.get("blockstationname").setValue('');
                		if ($("#areablockid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
        					setStationData($("#areablockid").val(),proData);
        				}
        				else {
        					getWellInitData();
        				}
                	}
        		});                
                 
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
        			//url:'sagd_queryGhmcInfo.action',
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

		
        		
        		
       			 $("#wellnum").ligerComboBox({
        			//url:'sagd_queryWellNameInfo.action',
        			valueField: 'id',
        			valueFieldID: 'wellnumsid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			hideOnLoseFocus:true,
        			data:wellData,
                    autocomplete:true/* ,
                    onSuccess:function (data){
                    	wellData=JSON2.stringify(data);
                    	alert(JSON2.stringify(data));
        			} */
        		});
        		
        		
                 $("#BZHF").ligerComboBox({
                	url:'sagd_queryBZInfo.action?arg=sagd',
        			valueField: 'id',
        			valueFieldID: 'bzhfid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			autocomplete:true,
        			hideOnLoseFocus:true,
        			onSelected:function (data){
                		liger.get("wellnum").setValue('');
                		if ($("#bzhfid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
        					setStationData($("#bzhfid").val(),proData);
        				}
        				else {
        					getWellInitData();
        				}
                	}
        		});  
        		
		
		  $("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
            $("#txtDate1").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            $("#dayreport").ligerTip();
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
            
            
        });

      	function setBZData(oilationnameid,proData) {
  			//alert(oilationnameid);
			jQuery.ajax({
				type : 'post',
				url:'sagd_queryBZInfo.action',
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
	function setStationData(areaid,proData) {
		jQuery.ajax({
			type : 'post',
			url:'sagd_queryStationInfo.action',
			data: {"areaid":areaid},
			timeout : '3000',
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
		$("#ghmc").ligerGetComboBoxManager().setValue('');
		$("#wellnum").ligerGetComboBoxManager().setValue('');
		var oilText=[{ id: 1 , text: '' }];
		var ghmcText=[{ id: 1 , text: '' }];
		var wellText=[{ id: 1 , text: '' }];
		jQuery.ajax({
			type : 'post',
			url : 'sagd_cascadeInit.action',
			success : function(jsondata) {
			var dataObj = $.parseJSON(jsondata);
				$.each(dataObj, function(key,val){
					if (key == 'oilText'){
						oilText = val;
					}
					if (key == 'ghmcText'){
						ghmcText = val;
					}
					if (key == 'welltext'){
						wellText = val;
					}
				});
				setInitData(oilText,ghmcText,wellText);
			}
		});
	}

      		
      		 //关联井号 和管汇	 
       		 function setWellInitData(data,proData,wellid) {
      		jQuery.ajax({
      			type : 'post',
      			url:'sagd_queryWellNameInfo.action',
      			data: {"ghmcid":data},
      			timeout : '3000',
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
				$("#wellnum").ligerGetComboBoxManager().selectValue(wellid);
			}
      	}
      		



      		function setInitData(oilText,stationText,wellText,areablockText) {
		liger.get("oilationname").setData(oilText);
		liger.get("ghmc").setData(ghmcText);
		liger.get("wellnum").setData(wellText);
		liger.get("areablock").setData(areablockText);
	}
      	
      	
   

	function onSubmit()
	{
		datastypeid = '';
		if($("#datastype").val() != ''){
			datastypeid = $("#datastypeid").val()
		}
		grid1.setOptions({
			parms: [
			{name: 'datastype',value: datastypeid},
			{name: 'oilationname',value: $("#oilationname").val()},
			{name: 'ghmc',value: $("#ghmc").val()},
		    {name: 'area',value: $("#areablock").val()},
		    {name: 'BZHF',value: $("#BZHF").val()},
			{name: 'wellnum',value: $("#wellnum").val()},
			{name: 'startDate',value: $("#txtDate").val()},
			{name: 'endDate',value: $("#txtDate1").val()}]
		});
		//setGridHid();
		grid1.loadData(true);
	}
		
		
		
		
		function getButtonInit(){
				//获取生成日报权限
					 jQuery.ajax({
						type : 'post',
						url : 'sagddrpd_ButtonInit.action',
						async : false,
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


	    	 var txtDate = $("#txtDate").val();
			jQuery.ajax({
				type : 'post',
				url : 'sagddrpd_dayreport.action?nowdata='+parseInt(Math.random()*100000)+'&txtDate='+encodeURIComponent(txtDate),
				async : false,
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
		

	
	
	function itemclick(item) {
		var rows = grid1.getCheckedRows();
		var sagddidArrayObj = new Array();
		if (rows.length > 0) {
			$(rows).each(function (){
				//alert('VERIFIER' + this.VERIFIER);
				if (this.VERIFIER == '') {//排除审核过的数据
					sagddidArrayObj.push(this.SAGDDID);
				}
			});
		}
		var sagddid='';
		if(rows.length == 1){
			$(rows).each(function (){
				sagddid=this.SAGDDID+'';
			});
		}
		switch (item.icon) {
			case "add":
				operate = 1;
				var md = myDate().Format("yyyy-MM-dd")+'';
				grid1.addEditRow({
					REPORT_DATE: md
				}); 
			break;
			case "modify":
				if(rows.length == 1){
				}else{
					alert('请选择一条你要修改信息的数据！');
				}
			break;
			case "delete":
				if(rows.length == 1){
					$.ligerDialog.confirm('确定删除吗?', function (yes) {
						if (yes) {
							if (sagddid != '' && sagddid != null && sagddid!="undefined") {
								if(yes){
									jQuery.ajax({
									type : 'post',
									url : 'sagddrpd_removeSagddRPD.action',
									data: {"sagddid":sagddid},
									async : false,
									success : function(data) {
										if (data != null && typeof(data)!="undefined" && data == "1"){
											onSubmit();
										}else{
											$.ligerDialog.error(outerror(jsondata));
										}
									},
									error : function(data) {
									}
									});
								}
							}
						}
						grid1.deleteSelectedRow();
						$.ligerDialog.success("删除成功！");
					});
				}else{
					alert("请选择一条你要删除的数据！");
				}
			break;
			case "save":
			
			
				//var toolteams = toolbar.options.items;
				//var authorityflg = judgeAuthority(toolteams,1);
				
				//if(authorityflg == 1){
				
			//	}
				var rows = grid1.getCheckedRows();
				if (rows.length != 1) {
					alert("只能选择一条你需更新或添加的数据！");
					return;
				}
				var flag=false;
				var op='';
				var rowName='';
				var rowIndex=0;
				$(rows).each(function (){
					var myDate = new Date();
					//var myDate2 = new Date(this.REPORT_DATE);
					if (this.__status == 'nochanged') {//
						$.ligerDialog.error("没有更改的数据!");
						return;
					}else if (this.__status == 'update') {
						var toolteams = toolbar.options.items;
						var authorityflg = judgeAuthority(toolteams,2);
						if(authorityflg == 0){
							$.ligerDialog.error("您无修改权限，请联系管理员添加权限!");
							return;
						}
						
					}
					if (this.JHMC == '' || typeof(this.JHMC)=="undefined" ) {
						$.ligerDialog.error("井号名称不能为空");
						return;
					}
					var now = new Date();
					var myDayStr = now.Format("yyyy-MM-dd");

					if (this.REPORT_DATE != myDayStr ) {
						$.ligerDialog.error("不能添加或修改日期不为当天的数据!");
						return;
					}
					if (this.VERIFIER != '' && typeof(this.VERIFIER)!="undefined") {//排除审核过的数据
						$.ligerDialog.error("有审核人及审核时间信息后不能进行再次修改数据!");
						return;
					}

					if (this.I_SHUTIN_TIME != '' && typeof(this.I_SHUTIN_TIME)!="undefined" && submitDateTime(this.I_SHUTIN_TIME)) {
						$.ligerDialog.error("注汽井关机时间 格式或数据范围不正确  \n (正确格式: yyyy-MM-dd HH:mm)");
						return;
					}
					if (this.I_OPEN_TIME != '' && typeof(this.I_OPEN_TIME)!="undefined" && submitDateTime(this.I_OPEN_TIME)) {
						$.ligerDialog.error("注汽井开井 时间 格式或数据范围不正确  \n (正确格式: yyyy-MM-dd HH:mm)");
						return;
					}
					if (this.P_SHUTIN_TIME != '' && typeof(this.P_SHUTIN_TIME)!="undefined" && submitDateTime(this.P_SHUTIN_TIME)) {
						$.ligerDialog.error("生产井关机时间 格式或数据范围不正确  \n (正确格式: yyyy-MM-dd HH:mm)");
						return;
					}
					if (this.P_OPEN_TIME != '' && typeof(this.P_OPEN_TIME)!="undefined" && submitDateTime(this.P_OPEN_TIME)) {
						$.ligerDialog.error("生产井开井时间 格式或数据范围不正确  \n (正确格式: yyyy-MM-dd HH:mm)");
						return;
					}
					var jmc= this.JHMC;
					var jid='';
					$(wellData).each(function (){
						if (this.text == jmc) {//排除审核过的数据
							jid=this.id;
						}
					});
					if (this.__status == 'add') {
						op='添加';
					}
					if (this.__status == 'update') {
						op='修改';
					}
					rowName=this.JHMC;
					rowIndex=this.__index+1;
					this.SAGDID=jid;
					flag=true;
				});
				if (!flag) {
					return;
				}
				var updateRowDate = JSON2.stringify(rows);
				$.ligerDialog.confirm('确定'+op+'第'+rowIndex+'行井名为'+rowName+'的记录吗?', function (yes) {
					if (yes) {
						jQuery.ajax({
							type : 'post',
							url : 'sagddrpd_addOrUpdateSagddRPD.action',
							data: {"rowData":updateRowDate},
							async : false,
							timeout : '3000',
							success : function(jsondata) { 
								$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
								if (jsondata == 1){
									//$.ligerDialog.close();
									//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
									window.onSubmit();
									$.ligerDialog.success("保存成功！");
								}else{
									$.ligerDialog.error(outerror(jsondata));
								}
							},
							error : function(data) {
							}
						});
					}
				});
				break;
				case "importd":
				$.ligerDialog.open({
  	    			title: "Excel报表导入",
  	    			url:'importExcelSAGD.jsp?pageid=sagdrb',
  	    			width: 600,
  	    			height: 260
  	    		});
			break;
			case "audit":
				//alert(sagddidArrayObj);
				var rows = grid1.getCheckedRows();
				var flag=false;
				$(rows).each(function (){
					if (this.VERIFIER == '' || typeof(this.VERIFIER)=="undefined") {
						flag=true;
						return;
					}
				});
				if (!flag) {
					$.ligerDialog.error("不存在需要审核数据!");
					return;
				}
				jQuery.ajax({
					type : 'post',
					url : 'sagddrpd_addOrUpdateSagddRPD.action',
					data: {"sagddids":sagddidArrayObj+''},
					async : false,
					timeout : '3000',
					success : function(jsondata) { 
						$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
						if (jsondata == 1){
							//$.ligerDialog.close();
							//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
							//parent.window.onSubmit();
							window.onSubmit();
							$.ligerDialog.success("审核成功！");
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
					}
				});
			break;
		}
	}
	function seachSelectData()
    {
		jQuery.ajax({
			type : 'post',
			url : 'sagd_queryWellNameInfo.action',
			async : false,
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
			async : false,
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
			async : false,
			//data: {"wellid":wellid,"org_id":org_id},
			success : function(data) {
				//ownerData=JSON2.stringify(data);
				csData=eval('(' + data + ')');
			},
			error : function(data) {
		
			}
		});

    }
	function f_onBeforeEdit(e)
	{
		if (e.column.columnname == "I_DAILY_OUTPUT") {
			//alert(JSON2.stringify(e));
			$.ligerDialog.open({
	    			title: "液量",
	    			url:'dailyOutput1.jsp?orgId='+e.record.ORG_ID+'&argp=I',
	    			width: 350,
	    			height: 450
	    	});
		}
		if (e.column.columnname == "I_WATER_RATION") {
			$.ligerDialog.open({
	    			title: "含水",
	    			url:'waterRation.jsp?wellName='+e.record.JHMC+'&argp=I',
	    			width: 350,
	    			height: 450
	    	});
		}
		if (e.column.columnname == "P_DAILY_OUTPUT") {
			$.ligerDialog.open({
	    			title: "液量",
	    			url:'dailyOutput1.jsp?orgId='+e.record.ORG_ID+'&argp=P',
	    			width: 350,
	    			height: 450
	    	});
		}
		if (e.column.columnname == "P_WATER_RATION") {
			$.ligerDialog.open({
	    			title: "含水",
	    			url:'waterRation.jsp?wellName='+e.record.JHMC+'&argp=P',
	    			width: 350,
	    			height: 450
	    	});
		}
		/*if (e.column.columnname == "I_SHUTIN_TIME") {
			$('#ITime');
			$.ligerDialog.open({
	    			title: "时间选择",
	    			url:'time.jsp',
	    			width: 350,
	    			height: 350
	    	});
		}*/
		if(e.record.VERIFIER==''|| typeof(e.record.VERIFIER)=="undefined") {
			return true;
		}
		return false;
	}

	function f_onBeforeCheckRow(checked, data, rowid, rowdata)
	{
		if(data.VERIFIER==''|| typeof(data.VERIFIER)=="undefined") {
			return true;
		}
		$.ligerDialog.error("审核后的数据不能进行操作！");
		return false;
	}
	function f_onAfterEdit(e)
	{
		if (dailyOutput!=0.0 && typeof(dailyOutput)!="undefined") {
			grid1.updateCell('I_DAILY_OUTPUT', dailyOutput, e.record);
			dailyOutput=0.0;
			if (e.record.I_DAILY_OUTPUT!=0.0&&e.record.I_DAILY_OUTPUT!=''&&e.recordf_onAfterEdit.I_WATER_RATION!=0.0&&e.record.I_WATER_RATION!='') {
				var i_water_ration=e.record.I_WATER_RATION;
				if (i_water_ration.indexOf('>') != -1) {
					i_water_ration=i_water_ration.split('>')[1].split('<')[0];
				}
				grid1.updateCell('I_DAILY_OIL_OUTPUT', (e.record.I_DAILY_OUTPUT * (1-i_water_ration/100)).toFixed(2), e.record);
			}
		}
		if (waterRation!=0.0 && typeof(waterRation)!="undefined") {
			grid1.updateCell('I_WATER_RATION', '<span style="color:red">' + waterRation + '</span>', e.record);
			waterRation=0.0;
			if (e.record.I_DAILY_OUTPUT!=0.0&&e.record.I_DAILY_OUTPUT!=''&&e.record.I_WATER_RATION!=0.0&&e.record.I_WATER_RATION!='') {
				var i_water_ration=e.record.I_WATER_RATION.split('>')[1].split('<')[0];
				grid1.updateCell('I_DAILY_OIL_OUTPUT', (e.record.I_DAILY_OUTPUT * (1-i_water_ration/100)).toFixed(2), e.record);
			}
		}
		if (pdailyOutput!=0.0 && typeof(pdailyOutput)!="undefined") {
			grid1.updateCell('P_DAILY_OUTPUT', pdailyOutput, e.record);
			pdailyOutput=0.0;
			if (e.record.P_WATER_RATION!=0.0&&e.record.P_WATER_RATION!='') {
				var p_water_ration=e.record.P_WATER_RATION;
				if (p_water_ration.indexOf('>') != -1) {
					p_water_ration=p_water_ration.split('>')[1].split('<')[0];
				}
				grid1.updateCell('P_DAILY_OIL_OUTPUT', (e.record.P_DAILY_OUTPUT * (1-p_water_ration/100)).toFixed(2), e.record);
			}
		}
		if (pwaterRation!=0.0 && typeof(pwaterRation)!="undefined") {
			grid1.updateCell('P_WATER_RATION', '<span style="color:red">' + pwaterRation + '</span>', e.record);
			pwaterRation=0.0;
			if (e.record.P_DAILY_OUTPUT!=0.0&&e.record.P_DAILY_OUTPUT!=''&&e.record.P_WATER_RATION!=0.0&&e.record.P_WATER_RATION!='') {
				var p_water_ration=e.record.P_WATER_RATION.split('>')[1].split('<')[0];
				grid1.updateCell('P_DAILY_OIL_OUTPUT', (e.record.P_DAILY_OUTPUT * (1-p_water_ration/100)).toFixed(2), e.record);
			}
		}
		if (e.column.columnname == "I_SHUTIN_TIME" && e.record.I_SHUTIN_TIME!="" && typeof(e.record.I_SHUTIN_TIME)!="undefined") {
			checkDateTime(e.record.I_SHUTIN_TIME);
		}
		if (e.column.columnname == "I_OPEN_TIME" && e.record.I_OPEN_TIME!="" && typeof(e.record.I_OPEN_TIME)!="undefined") {
			checkDateTime(e.record.I_OPEN_TIME);
		}
		if (e.column.columnname == "P_SHUTIN_TIME" && e.record.P_SHUTIN_TIME!="" && typeof(e.record.P_SHUTIN_TIME)!="undefined") {
			checkDateTime(e.record.P_SHUTIN_TIME);
		}
		if (e.column.columnname == "P_OPEN_TIME" && e.record.P_OPEN_TIME!="" && typeof(e.record.P_OPEN_TIME)!="undefined") {
			checkDateTime(e.record.P_OPEN_TIME);
		}
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
	
	
	
	 var exportFlag=false;
	 function onExport() {
		 //oilationname  blockstationname  boilersName  jrbz1
		     var Eoilationname=$("#oilationname").val();
 	         var Eghmc=$("#ghmc").val();
 	         var EwellName = $("#wellName").val();
 	         var Earea=$("#areablock").val();
 	          var Ebzhf=$("#BZHF").val();
 	    	 var txtDate = $("#txtDate").val();
 			 var txtDate1 = $("#txtDate1").val();

			if (exportFlag) {
				Eoilationname=oilationname;
				Eghmc = ghmc;
				EwellName = wellName;
				Earea = areablock;
				Eshstatus = shstatus;
				txtDate = startDate;
				txtDate1 = endDate;
			}
			var totalNum = 0;
			
			var url = 'sagddrpd_searchSagdRPD1.action?totalNum=export'+'&oilationname='+encodeURIComponent(Eoilationname)+'&ghmc='+encodeURIComponent(Eghmc)+'&wellnum='+encodeURIComponent(EwellName)+'&areablock='+encodeURIComponent(Earea)+'&BZHF='+encodeURIComponent(Ebzhf)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1);
			var urls = 'sagddrpd_searchSagdRPD1.action?totalNum=totalNum'+'&oilationname='+encodeURIComponent(Eoilationname)+'&ghmc='+encodeURIComponent(Eghmc)+'&wellnum='+encodeURIComponent(EwellName)+'&areablock='+encodeURIComponent(Earea)+'&BZHF='+encodeURIComponent(Ebzhf)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1);
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
		
		
		
	/* 	 function onExport1() {
		 //oilationname  blockstationname  boilersName  jrbz1
		     var Eoilationname=$("#oilationname").val();
 	         var Eghmc=$("#ghmc").val();
 	         var EwellName = $("#wellName").val();
 	         var Earea=$("#areablock").val();
 	         
 	    	 var txtDate = $("#txtDate").val();
 			 var txtDate1 = $("#txtDate1").val();
 			 
 			 var date = new Date(txtDate.replace(/-/,"/"));
 			 var date1 = new Date(txtDate1.replace(/-/,"/"));
			var preDate = new Date(date.getTime() - 24*60*60*1000);
			var preDate1 = new Date(date1.getTime() - 24*60*60*1000);
		var ydate = preDate.getYear()+'-'+(preDate.getMonth()+1)+'-'+preDate.getDate();
		var ydate1 = preDate1.getYear()+'-'+(preDate1.getMonth()+1)+'-'+preDate1.getDate(); 

 //			 alert(ydate);
 //			 alert(ydate1);
 			 
			if (exportFlag) {
				Eoilationname=oilationname;
				Eghmc = ghmc;
				EwellName = wellName;
				Earea = areablock;
				Eshstatus = shstatus;
				txtDate = startDate;
				txtDate1 = endDate;
			}
			var totalNum = 0;
			
			var url = 'sagddrpd_searchSagdRPD1.action?totalNum=export'+'&oilationname='+encodeURIComponent(Eoilationname)+'&ghmc='+encodeURIComponent(Eghmc)+'&wellnum='+encodeURIComponent(EwellName)+'&areablock='+encodeURIComponent(Earea)+'&startDate='+encodeURIComponent(ydate)+'&endDate='+encodeURIComponent(ydate1);
			var urls = 'sagddrpd_searchSagdRPD1.action?totalNum=totalNum'+'&oilationname='+encodeURIComponent(Eoilationname)+'&ghmc='+encodeURIComponent(Eghmc)+'&wellnum='+encodeURIComponent(EwellName)+'&areablock='+encodeURIComponent(Earea)+'&startDate='+encodeURIComponent(ydate)+'&endDate='+encodeURIComponent(ydate1);
			$.ajax({
				type : 'post',
				url : urls,
				async : false,
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
		} */

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

<body style="overflow-x:hidden; padding:2px;"><div align="right"> 
	 </div><div align="right"> 
	    </div><div class="searchbox"><div align="right"> 
	        </div><form id="formsearch" class="l-form"><div align="right">  
	        	</div><table cellspacing="1" border="0"> 
					<tr> 
		               <td align="right" class="l-table-edit-td" style="width: 60px;">所属采油站：</td> 
		                <td align="right" class="l-table-edit-td" style="width: 80px;"> 
		                	<input type="text" id="oilationname" name="oilationname" />	 
		                </td> 
		                <td align="right"> 
		                <br /></td> 
		                  <td align="right" class="l-table-edit-td" style="width: 60px;">管汇：</td> 
		                <td align="right" class="l-table-edit-td" style="width: 80px;"> 
		                	<input type="text" id="ghmc" name="ghmc" /> 
		                </td> 
		                <td align="right"> 
		                <br /></td> 
		                 
		                <td class="l-table-edit-td" style="width: 60px;" align="right">井号：</td> 
		                <td class="l-table-edit-td" style="width: 80px;" align="right"> 
		                	<input type="text" id="wellnum" name="wellnum" /> 
		                </td> 
		                <td align="right"> 
		                </td> 
		               <td align="right" class="l-table-edit-td" style="width:60px">所属区块：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="areablock" name = "areablock"/>	
		                </td>

		                <td align="right"> 
		                </td> 
		               <td align="right" class="l-table-edit-td" style="width:60px">班组：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="BZHF" name = "BZHF"/>	
		                </td>
		                
		                 <td class="l-table-edit-td" style="width: 40px;" align="right">日期：</td> 
						<td align="right"><input type="text" id="txtDate" ltype="date" /></td> 
						<td valign="middle" align="right">--</td> 
						<td align="right"><input type="text" id="txtDate1" ltype="date" /></td> 
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width: 80px;">查询</a></td>						 
 
 						<td style="padding-left: 20px;"><a href="javascript:dayreport()" class="l-button" id="dayreport" style="width: 80px;" title="8点后才可以生成当天的日报; ">生成日报</a></td>  
						<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width: 80px;">导出报表</a></td>	 
<!-- 						<td style="padding-left: 20px;"><a href="javascript:onExport1()" class="l-button" style="width: 80px;">导出昨日报表</a></td>	  -->
					
					</tr> 
				</table>
				</form>
		</div>
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			<div id="maingrid"></div> 
			
</body>
</html>