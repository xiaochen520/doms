<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>管汇点基础信息表</title>
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
		var manifoldid ; 		//管汇id
		var branchingid ; 		//分线id
		var branching ; 		//分线
		var ghdm;				//管汇代码
		var ghmc;				//管汇名称
		var dtfs;				//多通阀数
		var dtfmc1s;				//1#多通阀名称
		var dtftds1s;			//1#多通阀通道数
		var dtfmc2s;				//2#多通阀名称
		var dtftds2s;			//2#多通阀通道数
		var jrbz;				//接入标志
		var commissioning_date	//投产日期
		var scrapped_date;	 	//报废日期
		var ljjd_s;
		var status_value		//建设生产状态
		var org_id				//组织结构id
		var remark				//备注
		var grzhid;
		var  code ;
		var  dyear;
		var teamGid;
		var  pid2;
		var JLATIONNAME;
	   	var clearSelecteValue=0;
        
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'manifoldBasicInfo_pageInit.action',
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
			$("#oilationname").ligerComboBox({
				url:'rulewell_queryOilSationInfo.action?arg=manifold',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					clearSelecteValue=1;
					liger.get("teamGROUP1").setValue('');
					liger.get("blockstationname").setValue('');
					liger.get("teamGROUP1").setEnabled(true); 
					liger.get("blockstationname").setEnabled(true); 
					
					liger.get("boilersName").setValue('');
					if ($("#oilationid").val() !=null && $("#oilationid").val() !=null) {
						setOilChangeData($("#oilationname").val());
						//setStationData('',proData,$("#oilationid").val(),clearSelecteValue);
						//setGroupData('',proData,$("#oilationname").val(),clearSelecteValue);
						//setGUANHUIData('',proData,$("#oilationname").val(),clearSelecteValue);
					}
					//else {
						//getManifoldInitData();
					//}
				}
			});
			
			$("#teamGROUP1").ligerComboBox({
				url:'manifoldBasicInfo_queryTeamGInfo.action',
				valueField: 'id',
				valueFieldID: 'teamGROUPid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					liger.get("blockstationname").setDisabled(true);
					liger.get("boilersName").setValue('');
					//alert(data);
					//alert($("#teamGROUP1").val());
					
					if (data != null && typeof(data)!="undefined" && data != ''){
						ghChangeDatas("","",data,"");
						//setAreablockData($("#oilationid").val(),proData);
						//setGuanHuiData1('',proData,$("#teamGROUPid").val(),clearSelecteValue);
						//ghChangeDatas("","",data,"");
					}
					//else {
					//	getManifoldInitData();
					//}
				}
			});

			$("#blockstationname").ligerComboBox({
				url:'rulewell_queryStationInfo.action?oilid=manifold',
	            valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onSelected:function (data){
					liger.get("boilersName").setValue('');
					//liger.get("teamGROUP1").setValue('');
					liger.get("teamGROUP1").setDisabled(true);
					
					if (data != null && typeof(data)!="undefined" && data != ''){
						setGhData($("#stationid").val(),proData);
					}else{
						//getManifoldInitData();
						ghChangeDatas("",data,"","");
					}
					
				}
			});
			
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
				}
			});
			
			$("#zh2").ligerComboBox({
				url:'rulewell_queryStationInfo.action?oilid=alls',
	            valueField: 'id',
				valueFieldID: 'zhzid',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true
			});
			$("#teamG").ligerComboBox({
				url:'manifoldBasicInfo_queryTeamGInfo.action',
	            valueField: 'id',
				valueFieldID: 'teamGid',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true
			});
			
			$("#branching").ligerComboBox({
				url:'branchingBasicInfo_queryBranchingNameInfo.action',
	            valueField: 'id',
				valueFieldID: 'fenxianid',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true
			});
			
			$("#JLATIONNAME").ligerComboBox({
				url:'rulewell_queryStationInfo.action?oilid=alls',
	            valueField: 'id',
				valueFieldID: 'ORG2_ID',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true
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
			document.getElementById("jrbz1").value="全部";
			 $("#jrbz1").ligerComboBox({
				  url:'comboboxdata_searchSwitchInflg.action?args=ALL', 
	     			valueField: 'id',
	     			valueFieldID: 'jrbz1id',
	     			textField: 'text',
	     			selectBoxHeight:150,
	     			selectBoxWidth:100,
	     			width: 120,
	     			autocomplete:true,
	     			hideOnLoseFocus:true
		     		});
			 $("#INSTRU_STVA").ligerComboBox({
   				 hideOnLoseFocus:true,
	 			 autocomplete:true,
	 		                data: [
	 		                    { text: '正常', id: '0' },
	 		                    { text: '不正常', id: '1' }
	 		                    
	 		                ], valueFieldID: 'STVAId',
	 					initText :''
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
          
			  $("#commissioning_date").ligerDateEditor(
                {

                    format: "yyyy-MM-dd",
                   // label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
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

					var alam ='';
					var org_ids=$("#org_id").val();
					var manifoldid = $("#manifoldid").val();
					var zh2= "";
					var zh2name='';
					if($("#zh2").val() != ''){
						zh2 = $("#zhzid").val();
						zh2name = $("#zh2").val();
					}
					var ghdm2=$("#ghdm2").val();
					var branchingn = ""; 		//分线
					if($("#branching").val() != ''){
						branchingn = $("#fenxianid").val();
					}
					var pid2 =""; 
					if($("#JLATIONNAME").val() !=''){
						pid2 = $("#ORG2_ID").val()
						//alert(pid2)
						}
						
					
					var ghmc2=$("#ghmc2").val();
					var dtfs2=$("#dtfs2").val();
					var dtfmc1s='';
					if($("#dtfmc1s").val() !=''){
						var dtfmc1s= $("#dtfmc1s").val();
						}else{
							var dtfmc1s="";
							//alert("1#多通阀名称为空!");
							alam = '1#多通阀名称为空!'+'</br>';
							}
					var dtftds1s='';
					if($("#dtftds1s").val() !=''){
						var dtftds1s =$("#dtftds1s").val();
						}else{
							var dtftds1s="";
							alam +=  '1#多通阀通道号为空!'+'</br>';
							}
					var dtfmc2s='';
					if($("#dtfmc2s").val() !=''){
						var dtfmc2s =$("#dtfmc2s").val();
						}else{
							var dtfmc2s="";
							//alert("2#多通阀名称为空!");]
							alam += '2#多通阀名称为空!'+'</br>';
							}
					var dtftds2s='';
					if($("#dtftds2s").val() !=''){
						var dtftds2s =$("#dtftds2s").val();
						}else{
							var dtftds2s="";
							//alert("2#多通阀通道数为空!");
							alam += '2#多通阀通道数为空!'+'</br>';
							}

					
					var dyear = $("#dyear").val();
					var commissioning_date = $("#commissioning_date").val();
					var jrbz="";
					if($("#jrbz").val() != ''){
						jrbz = $("#jrbzid").val();
					}
					var ljjd_s  = $("#ljjd_s").val();
					if(ljjd_s != ''){
						ljjd_s =  $("#hidserverid").val() ;
					}
					var operFlag = false;
					var status_value  = $("#hidstatusid").val();
					var remark2 = $("#remark2").val();
					var code = $("#code").val();
					var teamG = $("#teamG").val();
					if(teamG != ''){
						teamG =  $("#teamGid").val() ;
					}
					var INSTRU_STVA = $("#INSTRU_STVA").val();
					//alert(teamG)
					if(alam != ''){

						
						$.ligerDialog.confirm(alam, function (yes, value)

			                     {
		                         	if (yes){
		                         		//判断修改还是添加操作 1-添加、2-修改
		                           	   if(operate == 1){
		                           	  		 
		                           	   	   jQuery.ajax({
		             									type : 'post',
		             									url : 'manifoldBasicInfo_saveOrUpdate.action',
		             									async : false,
		             									data:{"org_ids":org_ids,"zh2":zh2,"ghdm2":ghdm2,"ghmc2":ghmc2,"dtfs2":dtfs2,"dtfmc1s":dtfmc1s,"code":code,"dyear":dyear,
		             									"dtftds1s":dtftds1s,"dtfmc2s":dtfmc2s,"dtftds2s":dtftds2s,"branchingn":branchingn,"zh2name":zh2name,"teamG":teamG,
		             									"commissioning_date":commissioning_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"remark2":remark2,"status_value":status_value,"pid2":pid2,"INSTRU_STVA":INSTRU_STVA},
		             									success : function(jsondata) { 
		             									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
		             								
		             									if (jsondata != null && typeof(jsondata)!="undefined" && 1 ==jsondata){
		             											onSubmit();
		             											setpage1(1); //隐藏编辑界面
		             											setItemsd(2); //去掉隐藏，显示按钮
		             											$.ligerDialog.success('管汇点添加成功！');
		             											 operate = 0;
		             										}else if(typeof(eval('(' + jsondata + ')').ERRMSG) != "undefined"){
		             											var outData = eval('(' + jsondata + ')');
		             											$.ligerDialog.error(outData.ERRMSG);
		             										}else{
		             											$.ligerDialog.error(outerror(jsondata));
		             										}
		             									},
		             									error : function(data) {
		             										//alert(4);
		             									}
		             								});
		                           	   
		                           	   //2-修改
		                           	   }else if(operate == 2){
		                           	   		 jQuery.ajax({
		             									type : 'post',
		             									url : 'manifoldBasicInfo_saveOrUpdate.action',
		             									async : false,
		             									data: {"org_ids":org_ids,"zh2":zh2,"ghdm2":ghdm2,"ghmc2":ghmc2,"dtfs2":dtfs2,"dtfmc1s":dtfmc1s,"code":code,"dyear":dyear,"teamG":teamG,
		             									"dtftds1s":dtftds1s,"dtfmc2s":dtfmc2s,"dtftds2s":dtftds2s,"branchingn":branchingn,"manifoldid":manifoldid,"zh2name":zh2name,
		             									"commissioning_date":commissioning_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"remark2":remark2,"status_value":status_value,"pid2":pid2,"INSTRU_STVA":INSTRU_STVA},
		             									success : function(jsondata) { 
		             									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
		             									if (jsondata != null && typeof(jsondata)!="undefined" && 1 == jsondata){
		             											onSubmit();
		             											setpage1(1); //隐藏编辑界面
		             											setItemsd(2); //去掉隐藏，显示按钮
		             											$.ligerDialog.success('管汇点修改成功！');
		             											 operate = 0;
		             										}else if(typeof(eval('(' + jsondata + ')').ERRMSG) != "undefined"){
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
						}else{
							//判断修改还是添加操作 1-添加、2-修改
			              	   if(operate == 1){
			              	  		 
			              	   	   jQuery.ajax({
												type : 'post',
												url : 'manifoldBasicInfo_saveOrUpdate.action',
												async : false,
												data:{"org_ids":org_ids,"zh2":zh2,"ghdm2":ghdm2,"ghmc2":ghmc2,"dtfs2":dtfs2,"dtfmc1s":dtfmc1s,"code":code,"dyear":dyear,
												"dtftds1s":dtftds1s,"dtfmc2s":dtfmc2s,"dtftds2s":dtftds2s,"branchingn":branchingn,"zh2name":zh2name,"teamG":teamG,
												"commissioning_date":commissioning_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"remark2":remark2,"status_value":status_value,"pid2":pid2,"INSTRU_STVA":INSTRU_STVA},
												success : function(jsondata) { 
												$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
											
												if (jsondata != null && typeof(jsondata)!="undefined" && 1 ==jsondata){
														onSubmit();
														setpage1(1); //隐藏编辑界面
														setItemsd(2); //去掉隐藏，显示按钮
														$.ligerDialog.success('管汇点添加成功！');
														 operate = 0;
													}else if(typeof(eval('(' + jsondata + ')').ERRMSG) != "undefined"){
             											var outData = eval('(' + jsondata + ')');
             											$.ligerDialog.error(outData.ERRMSG);
             										}else{
														$.ligerDialog.error(outerror(jsondata));
													}
												},
												error : function(data) {
													//alert(4);
												}
											});
			              	   
			              	   //2-修改
			              	   }else if(operate == 2){
			              	   		 jQuery.ajax({
												type : 'post',
												url : 'manifoldBasicInfo_saveOrUpdate.action',
												async : false,
												data: {"org_ids":org_ids,"zh2":zh2,"ghdm2":ghdm2,"ghmc2":ghmc2,"dtfs2":dtfs2,"dtfmc1s":dtfmc1s,"code":code,"dyear":dyear,"teamG":teamG,
												"dtftds1s":dtftds1s,"dtfmc2s":dtfmc2s,"dtftds2s":dtftds2s,"branchingn":branchingn,"manifoldid":manifoldid,"zh2name":zh2name,
												"commissioning_date":commissioning_date,"jrbz":jrbz,"ljjd_s":ljjd_s,"remark2":remark2,"status_value":status_value,"pid2":pid2,"INSTRU_STVA":INSTRU_STVA},
												success : function(jsondata) { 
												$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
												if (jsondata != null && typeof(jsondata)!="undefined" && 1 == jsondata){
														onSubmit();
														setpage1(1); //隐藏编辑界面
														setItemsd(2); //去掉隐藏，显示按钮
														$.ligerDialog.success('管汇点修改成功！');
														 operate = 0;
													}else if(typeof(eval('(' + jsondata + ')').ERRMSG) != "undefined"){
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
					
						
              	   
		          
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
	    function setAreablockData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'boilerBasicInfo_queryAreablockInfo.action?nowdata='+parseInt(Math.random()*100000),
				data: {"orgid":data},
				//timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("areablock").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("areablock").setData(proData);
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
				url:'manifoldBasicInfo_queryStationInfo.action?nowdata='+parseInt(Math.random()*100000),
				data: {"areaid":areaid,"oilid":oilid,"selecteValue":clearSelecteValue},
				//timeout : '3000',
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
			return clearSelecteValue;
		}
	    function setGroupData(aaa,proData,oilid,clearSelecteValue) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_queryGroupInfo.action?nowdata='+parseInt(Math.random()*100000),
				data: {"oilid":oilid},
				//async:false, 
				//timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null){
						liger.get("teamGROUP1").setData(eval('(' + jsondata + ')'));
						///liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("teamGROUP1").setData(proData);
						//liger.get("blockstationname").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
			return clearSelecteValue;
		}

	  //caiyouzhan xia  guanhui 
	    function ghChangeDatas(cyzid,zzzid,teamid,param) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_searchGHcommon.action?nowdata='+parseInt(Math.random()*100000),
				data: {"cyzid":cyzid,"zzzid":zzzid,"teamid":teamid,"param":param},
				//timeout : '3000',
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
			return clearSelecteValue;
		}

		
	    //caiyouzhan xia  guanhui 
	    function setGUANHUIData(aaa,proData,oilid,clearSelecteValue) {
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_queryOilGuanHuiInfo.action?nowdata='+parseInt(Math.random()*100000),
				data: {"oilid":oilid},
				//timeout : '3000',
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
			return clearSelecteValue;
		}
		
	    function setGuanHuiData1(aaa,proData,oilid,clearSelecteValue) {
		   // alert(oilid);
			jQuery.ajax({
				type : 'post',
				url:'searchQueryAll_queryGuanHuiInfo.action?nowdata='+parseInt(Math.random()*100000),
				data: {"oilid":oilid},
				////timeout : '3000',
				async : false,
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=''){
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
			return clearSelecteValue;
		}
		
	    function setGhData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'manifoldBasicInfo_queryManifoldNameInfo.action?nowdata='+parseInt(Math.random()*100000),
				data: {"orgid":data},
				async : false,
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
			liger.get("blockstationname").setData(StationText);
			liger.get("boilersName").setData(ManiText);
			liger.get("teamGROUP1").setData(GroupText);
		}
	    function getManifoldInitData() {
			var oilText=[{ id: 1 , text: '' }];
			var areaText=[{ id: 1 , text: '' }];
			var stationText=[{ id: 1 , text: '' }];
			var ghmcText=[{ id: 1 , text: '' }];
			var GroupText =[{ id: 1 , text: '' }];
			jQuery.ajax({
				type : 'post',
				url : 'manifoldBasicInfo_cascadeInit.action',
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
						if (key == 'ghmctext'){
							ghmcText = val;
						}
						if (key == 'GroupText'){
							GroupText = val;
						}
					});
					setInitData(oilText,areaText,stationText,ghmcText,GroupText);
				}
			});
		}
		
	    function setInitData(oilText,areaText,stationText,ghmcText,GroupText) {
			liger.get("oilationname").setData(oilText);
			//liger.get("areablock").setData(areaText);
			liger.get("blockstationname").setData(stationText);
			liger.get("boilersName").setData(ghmcText);
			liger.get("teamGROUP1").setData(GroupText);
		}
       	 function fromAu(rowdata){
        	//用户选择修改数据
        							//alert(rowdata.MANIFOLDID);
        							manifoldid = rowdata.MANIFOLDID;
									zh2 = rowdata.BLOCKSTATIONNAME;
									ghmc2 = rowdata.GHMC;
									ghdm2 = rowdata.GHDM;
									dtfs2 = rowdata.DTFS;
									dtfmc1s = rowdata.DTFMC1;
									dtftds1s = rowdata.DTFTDS1;
									grzhid = rowdata.GRZHID;
									branchingid = rowdata.BRANCHINGID;
									JLATIONNAME = rowdata.JLATIONNAME;
									pid2 = rowdata.PID2;
									if (rowdata.DTFMC2 != null && typeof(rowdata.DTFMC2)!="undefined"){
									 	dtfmc2s = rowdata.DTFMC2; 
									 }else{
									 	dtfmc2s = "";
									 }
									 if (rowdata.DTFTDS2 != null && typeof(rowdata.DTFTDS2)!="undefined"){
									 	dtftds2s = rowdata.DTFTDS2; 
									 }else{
									 	dtftds2s = "";
									 }
									 if (rowdata.COMMISSIONING_DATE != null && typeof(rowdata.COMMISSIONING_DATE)!="undefined"){
										 commissioning_date = rowdata.COMMISSIONING_DATE;
										 }else{
											 commissioning_date = "";
										 }
									if (rowdata.DYEAR != null && typeof(rowdata.DYEAR)!="undefined"){
										dyear = rowdata.DYEAR;
									 }else{
										 dyear = "";
									 }
									jrbz = rowdata.JRBZ;
									 if (rowdata.CODE != null && typeof(rowdata.CODE)!="undefined"){
										 code = rowdata.CODE; 
										 }else{
											 code = "";
										 }
									if (rowdata.LJJDID != null && typeof(rowdata.LJJDID)!="undefined"){
									 	ljjd_s = rowdata.LJJDID;
									 }else{
									 	ljjd_s = "";
									 }										
									 org_id = rowdata.ORG_ID;
									 
									 if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
									 	remark = rowdata.REMARK; 
									 }else{
									 	remark = "";
									 }
									if (rowdata.STATUS_VALUE != null && typeof(rowdata.STATUS_VALUE)!="undefined"){
										status_value = rowdata.STATUS_VALUE; 
									}else{
										status_value = "";
									}
									if (rowdata.teamGroup != null && typeof(rowdata.teamGroup)!="undefined"){
										teamGroup = rowdata.teamGroup; 
									}else{
										teamGroup = "";
									}
									if (rowdata.INSTRU_STVA != null && typeof(rowdata.INSTRU_STVA)!="undefined"){
										INSTRU_STVA = rowdata.INSTRU_STVA; 
									}else{
										INSTRU_STVA = "";
									}
									
				                	if(operate == 2){
				                		assignM(2);
				                	}
				                	
        }
        
        //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			var oilstationname='全部';
			var blockstationname='';
			var ghname='';
			//alert(basid);
			getManifoldInitData();
			$("#oilationname").ligerGetComboBoxManager().setValue('');
			$("#boilersName").ligerGetComboBoxManager().setValue('');
			$("#blockstationname").ligerGetComboBoxManager().setValue('');
			//$("#jrbz1").ligerGetComboBoxManager().setValue('2');
			document.getElementById("jrbz1").value = "全部";
			document.getElementById("dyearC").value = "";
			
			if(datatype=='4'){
				oilstationname=name;
				$("#oilationname").val(name);
			}
			if(datatype=='7'){
				blockstationname=name;
				$("#blockstationname").val(name);
			}
			if(datatype=='8'){
				ghname=id;
				$("#boilersName").val(name);
			}
			grid.setOptions({
				parms: [{ name: 'oilationname', value: oilstationname},
					{ name: 'ghname', value: ghname},
					{ name: 'blockstationname',value: blockstationname}]
					//{ name: 'boilersName',value:wellnum}]
			});
			grid.loadData(true);
		}
		
		
    function onSubmit(){
		grid.setOptions({
			parms: [{ name: 'oilationname', value: $("#oilationname").val()},
				{ name: 'areablock', value: $("#areablock").val()},
				{ name: 'blockstationname',value: $("#blockstationname").val()},
				{ name: 'boilersName',value: $("#boilersName").val()},
				{ name: 'jrbz1',value: $("#jrbz1").val()},
				{ name:	'dyearC',value:$("#dyearC").val()},
				{ name:	'teamGROUP1',value:$("#teamGROUP1").val()}
				]
		});
         	grid.loadData(true);
        }
      function assignM(flg){
      		
			 if(flg == 2){
			 		//document.getElementById("zh2").value = zh2;
			 		$("#zh2").ligerGetComboBoxManager().selectValue(grzhid);
			 		$("#branching").ligerGetComboBoxManager().selectValue(branchingid);
			 		$("#JLATIONNAME").ligerGetComboBoxManager().selectValue(pid2);
			 		
			 		document.getElementById("manifoldid").value = manifoldid;
	               	document.getElementById("ghmc2").value = ghmc2;
	               	document.getElementById("ghdm2").value = ghdm2;
	               	document.getElementById("dtfs2").value= dtfs2;
	               	document.getElementById("dtfmc1s").value = dtfmc1s;
	               	document.getElementById("dtftds1s").value= dtftds1s;
	               	document.getElementById("dtfmc2s").value= dtfmc2s;
	               	document.getElementById("dtftds2s").value= dtftds2s;
	               	//document.getElementById("jrbz2").value = jrbz2;
	               	document.getElementById("commissioning_date").value= commissioning_date;
	              // document.getElementById("scrapped_date").value= scrapped_date;
	      			$("#ljjd_s").ligerGetComboBoxManager().selectValue(ljjd_s);
	      			var ac = $("#jrbz").ligerGetComboBoxManager().findValueByText(jrbz);
	               	$("#jrbz").ligerGetComboBoxManager().selectValue(ac);
	               	document.getElementById("dyear").value= dyear;
	               	document.getElementById("org_id").value= org_id;
	               	document.getElementById("remark2").value= remark;
	               	if (status_value != null && typeof(status_value)!="undefined" && status_value != '') {
						$("#status_value").ligerGetComboBoxManager().selectValue(status_value);
					}
					else {
						document.getElementById("status_value").value= status_value;
					}
	               	document.getElementById("code").value= code;
	            	//$("#teamG").ligerGetComboBoxManager().selectValue(teamGroup);
	            	document.getElementById("teamG").value= teamGroup;
	            	 if (INSTRU_STVA == '正常') {
							$("#INSTRU_STVA").ligerGetComboBoxManager().selectValue(0);
						}
						else if(INSTRU_STVA == '不正常'){
							$("#INSTRU_STVA").ligerGetComboBoxManager().selectValue(1);
						}else{
							$("#INSTRU_STVA").ligerGetComboBoxManager().selectValue('');
					}
			 }else if(flg == 1){
				
					$("#branching").ligerGetComboBoxManager().selectValue('');
					$("#JLATIONNAME").ligerGetComboBoxManager().selectValue('');
					 document.getElementById("branching").value = "";
			 		$("#zh2").ligerGetComboBoxManager().selectValue('');
			 		document.getElementById("zh2").value = "";
			 		
	               	document.getElementById("ghmc2").value = "";
	               	document.getElementById("ghdm2").value = "";
	               	document.getElementById("dtfs2").value= "";
	               	document.getElementById("dtfmc1s").value="";
	  
	               	document.getElementById("dtftds1s").value= "";
	               	document.getElementById("dtfmc2s").value= "";
	               	document.getElementById("dtftds2s").value= "";
	               	$("#ljjd_s").ligerGetComboBoxManager().selectValue("");
	               	$("#jrbz").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("ljjd_s").value= "";
	                document.getElementById("jrbz").value= "";
	               	document.getElementById("org_id").value= "";
	               	document.getElementById("remark2").value= "";
	               	$("#status_value").ligerGetComboBoxManager().selectValue("");
	               	document.getElementById("status_value").value= "";
	            	document.getElementById("code").value= "";
	            	//$("#commissioning_date").ligerGetComboBoxManager().selectValue("");
	            	 document.getElementById("commissioning_date").value= "";
	            	document.getElementById("dyear").value= "";
	            	document.getElementById("teamG").value= "";
	            	document.getElementById("INSTRU_STVA").value= "";
	            	
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
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要删除的数据！')
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'manifoldBasicInfo_removeManifoldBasicInfo.action',
											async : false,
											data: {"manifoldid":manifoldid,"org_id":org_id},
											success : function(data) { 
											if (data != null && typeof(data)!="undefined" && data == "1"){
													
													
													if(org_id != null && typeof(org_id)!="undefined" && org_id != ""){
														
													}else{
														assignM(1);
													}
													
													onSubmit();
													$.ligerDialog.success('管汇点删除成功！');
													operate = 0;
												}else{
													$.ligerDialog.error('删除失败！')
													$.ligerDialog.error(outerror(jsondata));
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
					setpage1(0); //显示编辑界面
				setItemsd(0);//0-显示编辑区，添加隐藏按钮
				break;   
				case "down":
						setpage1(1); //隐藏编辑界面
						setItemsd(1);//1-隐藏编辑区添加显示按钮
				break;   
              	
          }
      }
  	var exportFlag=false;
	 function onExport() {
		 //oilationname  blockstationname  boilersName  jrbz1
		     var Eoilationname=$("#oilationname").val();
 	         var Eblockstationname=$("#blockstationname").val();
 	         var EboilersName=$("#boilersName").val();
 	         var Ejrbz1=$("#jrbz1").val();
 	         var EdyearC = $("#dyearC").val();	

			if (exportFlag) {
				Eoilationname=oilationname;
				Eblockstationname = blockstationname;
				EboilersName=boilersName;
				Ejrbz1=jrbz1;
				EdyearC=dyearC;
			}
			var totalNum = 0;
			
			var url = 'manifoldBasicInfo_onExport.action?oilationname='+encodeURIComponent(Eoilationname)+'&blockstationname='+encodeURIComponent(Eblockstationname)+'&jrbz1='+encodeURIComponent(Ejrbz1)+'&dyearC='+encodeURIComponent(EdyearC)+'&totalNum=export'+'&boilersName='+encodeURIComponent(EboilersName);
			var urls = 'manifoldBasicInfo_onExport.action?oilationname='+encodeURIComponent(Eoilationname)+'&blockstationname='+encodeURIComponent(Eblockstationname)+'&jrbz1='+encodeURIComponent(Ejrbz1)+'&dyearC='+encodeURIComponent(EdyearC)+'&totalNum=totalNum'+'&boilersName='+encodeURIComponent(EboilersName);
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
					<td align="left" class="l-table-edit-td" style="width:100px">采油站：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="oilationname" name="oilationname"/>
	                </td>
					<td align="right" class="l-table-edit-td" style="width:60px;display:none">区块名称：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px;display:none">
	                	<input type="text" id="areablock" name="areablock"/>
	                </td>
					<td align="right" class="l-table-edit-td" style="width:60px">注转站：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="blockstationname" name="blockstationname"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:40px">班组：</td>
	                <td align="left" class="l-table-edit-td" style="width:40px">
	                	<input type="text" id="teamGROUP1" name="teamGROUP1"/>
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">管汇：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="boilersName" name="boilersName"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	            
	                 <td align="right" class="l-table-edit-td" style="width:60px">接入标志：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="jrbz1" name="jrbz1"/>
	                </td>
	                </tr>
	                <tr>
	                  <td align="left" class="l-table-edit-td" style="width:80px">设计产能年：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="dyearC" name="dyearC"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
		               
		                <td align="left" class="l-table-edit-td">
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
		             	 <td align="left" class="l-table-edit-td" style="width:150px">管汇名称:</td>
		              
		                <td align="left" class="l-table-edit-td" style="width:150px">所属注转站:</td>
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">所属班组:</td>
		                  
		                <td align="left" class="l-table-edit-td" style="width:150px">分线:</td>
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">管汇代码:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">所属计量注转站:</td>
		                <td align="left" class="l-table-edit-td" style="width:150px">站内编码:</td>
		                 
		               
		                
		              
		                
		               
		            </tr>
		            <tr>
		              
		            	 <td align="left" class="l-table-edit-td" style="">
		                	<input name="ghmc2" type="text" id="ghmc2"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		            	 <td align="left" class="l-table-edit-td" style="">
		                	<input name="zh2" type="text" id="zh2"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		                  <td align="left" class="l-table-edit-td" style="">
		                	<input name="teamG" type="text" id="teamG"  ltype="text"  />
		                </td>
		              
		              
		            	  <td align="left" class="l-table-edit-td" style="">
		                	<input name="branching" type="text" id="branching"  ltype="text"  />
		                </td>
		              
		                
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="ghdm2" type="text" id="ghdm2"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="JLATIONNAME" type="text" id="JLATIONNAME"  ltype="text"  validate="{required:true }" />
		                </td>
		                
		                  <td align="left" class="l-table-edit-td" style="">
		                	<input name="code" type="text" id="code" ltype="text"  validate="{minlength:1,maxlength:20}" />
		                </td>
		                
		              
		                
		                
		               
		                
 						
		            </tr>
		                 <tr>
		                  <td align="left" class="l-table-edit-td" style="width:150px">多通阀数:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">1#多通阀名称:</td>
		                 <td align="left" class="l-table-edit-td" style="">1#多通阀通道数:</td>
		                 <td align="left" class="l-table-edit-td" style="">2#多通阀名称:</td>
		                 <td align="left" class="l-table-edit-td" style="">2#多通阀通道数:</td>
		                 <td align="left" class="l-table-edit-td" >建设生产状态:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">投产日期:</td>
		              
		               
		             
		               
		            
		                
		            </tr>
		              <tr>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="dtfs2" type="text" id="dtfs2" ltype="text" validate="{required:true ,maxlength:1}" />
		                </td>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="dtfmc1s" type="text" id="dtfmc1s" ltype="text"  validate="{minlength:0,maxlength:20}" />
		                </td>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="dtftds1s" type="text" id="dtftds1s"  ltype="text" validate="{minlength:0,maxlength:2}" />
		                </td>
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="dtfmc2s" type="text" id="dtfmc2s"  ltype="text" validate="{minlength:0,maxlength:20}" />
		                </td>
		            	 
		            	 <td align="left" class="l-table-edit-td" style="">
		                	<input name="dtftds2s" type="text" id="dtftds2s"  ltype="text" validate="{minlength:0,maxlength:2}" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="status_value" type="text" id="status_value" ltype="text" />
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="commissioning_date" type="text" id="commissioning_date" ltype="text" />
		                </td>
		                 
		              
		                 
		                <!-- 
		                  <td align="left" class="l-table-edit-td" >
		                	<input name="scrapped_date" type="text" id="scrapped_date" ltype="text" />
		                </td>-->
		               
		                 
		                </tr>
		                <tr>
		                   <td align="left" class="l-table-edit-td" style="width:150px">产能设计年:</td>
		                   <td align="left" class="l-table-edit-td" style="">计量器状态:</td>
		                   <td align="left" class="l-table-edit-td" style="">接入标志:</td>
		                   <td align="left" class="l-table-edit-td" style="width:150px">服务器逻辑节点名:</td>
		                 <td align="left" class="l-table-edit-td" style="width:150px">备注:</td>
		                </tr>
		                
		                 <tr>
		                  <td align="left" class="l-table-edit-td" style="">
		                	<input name="dyear" type="text" id="dyear" ltype="text" validate="{number:true,minlength:0,maxlength:4}" />
		                </td>
		                
		                   <td align="left" class="l-table-edit-td" style="">
		                	<input name="INSTRU_STVA" type="text" id="INSTRU_STVA" ltype="text"  />
		                </td>
		                   <td align="left" class="l-table-edit-td" style="">
		                	<input name="jrbz" type="text" id="jrbz" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		               
		                 <td align="left" class="l-table-edit-td" >
		                	<input name="ljjd_s" type="text" id="ljjd_s" ltype="text"  />
		                </td>
		                   <td align="left" class="l-table-edit-td" style="">
		                	<input name="remark2" type="text" id="remark2" ltype="text"  validate="{minlength:1,maxlength:20}" />
		                </td>
		                </tr>
		                
						<tr>
		                <td align="left" class="l-table-edit-td" style="">
		                	
		                	<!-- <input name="audbshor_date" type="text" id="audbshor_date" ltype="text" /> -->
		                </td>
		                
		                
		                <td align="left" class="l-table-edit-td" style="">
		                	<input name="manifoldid" type="hidden" id="manifoldid" />
		               
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