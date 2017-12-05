<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>分线信息管理</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
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
     <link href="../../lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    



    <script type="text/javascript">
        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        
        //鼠标选择数据
		var branchingid ; 	//分线id
		var comstationid;	//联合站id
		var combination_station;  //所属联合站
		var branchname;  //分线名称
		var branching_code;  //分线编码
		var remark;
        $(function () {
        
        	jQuery.ajax({ 
			type : 'post',
			url : 'branchingBasicInfo_pageInit.action',
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
		$("#combination_station_name").ligerComboBox({
			url:'branchingBasicInfo_queryCombinationStationName.action',
			valueField: 'id',
			valueFieldID: 'combinationid',
			textField: 'text',
			selectBoxHeight:200,
			width: 120,
			autocomplete:true,
			hideOnLoseFocus:true,
			onSelected:function (data){
				if ($("#oilationid").val() != 1) {
					setBranchingData($("#combinationid").val(),proData);
				}
				else {
					//getBranchingInitData();
					setBranchingData("",proData);
				}
			}
		});
		
		$("#combination_station").ligerComboBox({
			url:'branchingBasicInfo_queryCombinationStationName.action?arg=a',
			valueField: 'id',
			valueFieldID: 'textcombinationid',
			textField: 'text',
			selectBoxHeight:200,
			width: 120,
			autocomplete:true,
			hideOnLoseFocus:true
			
		});
		$("#branching_name").ligerComboBox({
			url:'branchingBasicInfo_queryBranchingNameInfo.action',
			valueField: 'id',
			valueFieldID: 'idtest',
			textField: 'text',
			selectBoxHeight:350,
			width: 120,
			hideOnLoseFocus:true,
            autocomplete:true,
			onSelected:function (data){
			}
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
              	   	var branchingid2  = $("#branchingid").val();
					var branching_name2  = $("#branchname").val();
					var branching_code2 = $("#branching_code").val();
					var combination_station2  = '';
					if($("#combination_station").val() != null){
						combination_station2 = $("#textcombinationid").val();
					}
				
					var remark2 = $("#remark").val();
					var operFlag = false;
              	   //判断修改还是添加操作 1-添加、2-修改
              	   if(operate == 1){
              	   	   jQuery.ajax({
									type : 'post',
									url : 'branchingBasicInfo_saveOrUpdate.action',
									async : false,
									data: {"branching_name2":branching_name2,"branching_code2":branching_code2,
									"combination_station2":combination_station2,"remark2":remark2},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && 1 == jsondata){
											onSubmit();
											  setpage(1); //隐藏编辑界面
 											 setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('分线添加成功！');
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
									url : 'branchingBasicInfo_saveOrUpdate.action',
									async : false,
									data: {"branchingid2":branchingid2,"branching_name2":branching_name2,"branching_code2":branching_code2,
									"combination_station2":combination_station2,"remark2":remark2},
									success : function(jsondata) { 
									$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
									if (jsondata != null && typeof(jsondata)!="undefined" && 1 == jsondata){
											onSubmit();
											 setpage(1); //隐藏编辑界面
 											 setItemsd(2); //去掉隐藏，显示按钮
											$.ligerDialog.success('分线修改成功！');
											operate = 0;
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
            $("form").ligerForm();
            $(".l-button-test").click(function () {
             	
            });
        });  
        
       	 function fromAu(rowdata){
        	//用户选择修改数据
    				branchingid =  rowdata.BRANCHINGID; 	//分线id
    				if (rowdata.COMSTATIONID != null && typeof(rowdata.COMSTATIONID)!="undefined"){
						comstationid = rowdata.COMSTATIONID; 	//联合站id
					}else{
						comstationid = ""; 	//联合站id
					}
					
					if (rowdata.COMBINATION_STATION != null && typeof(rowdata.COMBINATION_STATION)!="undefined"){
						combination_station = rowdata.COMBINATION_STATION;  //所属联合站
					}else{
						combination_station = "";  //所属联合站	
					}
					
					branchname = rowdata.BRANCHING_NAME;  //分线名称
					if (rowdata.BRANCHING_CODE != null && typeof(rowdata.BRANCHING_CODE)!="undefined"){
						branching_code = rowdata.BRANCHING_CODE;  //分线编码
					}else{
						branching_code = "";  //分线编码
					}
					
					if (rowdata.REMARK != null && typeof(rowdata.REMARK)!="undefined"){
						remark = rowdata.REMARK;
					}else{
						remark = "";
					}
					
					
                	if(operate == 2){
                		assignM(2);
                	}
        }
        
        //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			
			var oilstationname="全部";
			var areablock='';
			$("#combination_station_name").ligerGetComboBoxManager().setValue('');
			$("#branching_name").ligerGetComboBoxManager().setValue('');
			getBranchingInitData();
			 
			if(datatype=='5'){
				oilstationname=name;
				$("#combination_station_name").val(name);
			}
			if(datatype=='16'){
				areablock=name;
				$("#branching_name").val(name);
			}
			
			
			
		grid.setOptions({
    		parms: [
    			{ name: 'stationNumber',
    			value: oilstationname },
    			
    			{ name: 'branching_name',
    			value: areablock }
    			]
    		});
    	grid.loadData(true);
		}
		

    function onSubmit(){
		var stationNumber = $("#combination_station_name").val();
		var branching_name = $("#branching_name").val();
		
		stationNumber = stationNumber.replace(/\s+/g,"");
		branching_name = branching_name.replace(/\s+/g,"");
		
    	grid.setOptions({

    		parms: [
    			{ name: 'stationNumber',
    			value: stationNumber },
    			
    			{ name: 'branching_name',
    			value: branching_name }
    			]
    		});
    	grid.loadData(true);
        }
        
        function getBranchingInitData() {
		jQuery.ajax({
			type : 'post',
			url : 'branchingBasicInfo_queryBranchingNameInfo.action',
			success : function(jsondata) {
			var dataObj = $.parseJSON(jsondata);
				$.each(dataObj, function(key,val){
					liger.get("branching_name").setData(val);
				});
			}
		});
	}
         function setBranchingData(data,proData) {
		jQuery.ajax({
			type : 'post',
			url:'branchingBasicInfo_queryBranchingNameInfo.action',
			data: {"orgid":data},
			timeout : '3000',
			success : function(jsondata) {
				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
					liger.get("branching_name").setData(eval('(' + jsondata + ')'));
				}
				else{
					liger.get("branching_name").setData(proData);
				}
				}
			});
			
		

            
          
        } 
      function assignM(flg){
      		
			 if(flg == 2){
					$("#combination_station").ligerGetComboBoxManager().selectValue(comstationid);
			 		document.getElementById("branchname").value = branchname;
	               	document.getElementById("branching_code").value = branching_code;
	               	document.getElementById("remark").value = remark;
	               	document.getElementById("branchingid").value = branchingid;

			 }else if(flg == 1){
				 $("#combination_station").ligerGetComboBoxManager().selectValue("");
			 		document.getElementById("branchname").value = "";
	               	document.getElementById("branching_code").value = "";
	               	document.getElementById("remark").value = "";
	               	document.getElementById("branchingid").value = "";
			 }
			 		
      }
       //工具条事件
 function itemclick(item) {
 		var rows = grid.getCheckedRows();
     switch (item.icon) {
         case "add":
         	   
         	    if(operate != 1 && operate != 2){
	              	setpage(0); //显示编辑界面
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
		              	setpage(0); //显示编辑界面
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
										url : 'branchingBasicInfo_removeBranchingBasicInfo.action',
										async : false,
										data: {"branchingid":branchingid,"comstationid":comstationid},
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
													$.ligerDialog.success('分线基础删除成功！');
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
			setpage(0); //显示编辑界面
			setItemsd(0);//0-显示编辑区，添加隐藏按钮
			break;   
			case "down":
					setpage(1); //隐藏编辑界面
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
					<td align="right" class="l-table-edit-td" style="width:60px">站号：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="combination_station_name" name="combination_station_name"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">分线名称：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="branching_name" name="branching_name"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
				<td align="left" class="l-table-edit-td" style="width:120px">
	                <a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
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
		           		<td align="left" class="l-table-edit-td" style="width:80px">所属联合站：</td>
		               
		             	<td align="left" class="l-table-edit-td" style="width:80px">分线名称：</td>
		               
		                 <td align="left" class="l-table-edit-td" style="width:80px">分线编码：</td>
		                 
		                  <td align="left" class="l-table-edit-td" style="width:80px">备注：</td>
		                  
		                  <td align="left" class="l-table-edit-td" style="width:80px">
		                  	<input type="hidden" id="branchingid" name="branchingid"/>
		                  	<input type="hidden" id="comstationid" name="comstationid"/>
		                  </td>
		   
		            </tr>
		            <tr>
		            	<td align="left" class="l-table-edit-td" >
		                	<input type="text" id="combination_station" name="combination_station"  ltype="text"  validate="{required:true,minlength:1,maxlength:20}"/>
		                </td>
		               
		                
		               	<td align="left" class="l-table-edit-td" >
		                	<input type="text" id="branchname" name="branchname" ltype="text"  validate="{required:true,minlength:1,maxlength:20}"/>
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="branching_code" name="branching_code"/>
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<input name="remark" type="text" id="remark" ltype="text"  />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" >
		                	<!-- <input type="submit" value="提交" id="Button1" class="l-button" style="width:100px" />  -->
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