<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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
    
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>   
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/js/ligerui.expand.js" type="text/javascript"></script> 
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../lib/js/LG.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
	<script type="text/javascript">
   	var grid, g;
    
    $(function () {
		grid = $("#maingrid").ligerGrid({ columns: [
			{ display: '分线ID', name: 'branchingid', align: 'left', width: 120 ,hide: true},
            { display: '站号', name: 'zh', minWidth: 140 ,frozen:true},
            { display: '分线名称', name: 'branching_name', minWidth: 80 },
            { display: '分线编码', name: 'branching_code', width: 80,align:'left' },
            { display: '所属联合站', name: 'combination_station', minWidth: 90 },
            { display: '上次操作者', name: 'rlast_oper', minWidth: 90 },
            { display: '上次操作时间', name: 'rlast_odate', minWidth: 140 },
            { display: '备注', name: 'remark' }], pageSize:30,rownumbers:true,width: '100%',height:'100%',dataAction: 'server',
            url:'boilerBasicInfo_queryBoilerBasicInfo.action',pageParmName :'pageNo', sortnameParmName:'sort',
            sortorderParmName: 'order', pagesizeParmName:'rowsPerpage', selectRowButtonOnly:true,frozen:true,
            sePaper:true,checkbox :false,
						toolbar: { items: [
											{ text: '修改', click: itemclick, img: '../../lib/ligerUI/skins/icons/modify.gif',icon: 'modify' },
											{ text: '删除', click: itemclick, img: '../../lib/ligerUI/skins/icons/delete.gif',icon: 'delete' },
											{ text: '添加', click: itemclick, img: '../../lib/ligerUI/skins/icons/add.gif',icon: 'add' }
											]
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
					getBranchingInitData();
				}
			}
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
    });

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
			},
			error : function(jsondata) {
				alert("请求数据失败，请重新查询");
			}
		});
	}

//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype){ 
			alert(id);
			alert(name);
			alert(datatype);
		}
		
    function onSubmit(){
		var stationNumber = $("#stationNumber").val();
		var branching_name = $("#branching_name").val();
		var areablock = $("areablock").val();
		stationNumber = stationNumber.replace(/\s+/g,"");
		branching_name = branching_name.replace(/\s+/g,"");
		areablock = areablock.replace(/\s+/g,"");
    	grid.setOptions({
    		parms: [
    			{ name: 'areablock',
    			value: areablock },
    			{ name: 'stationNumber',
    			value: stationNumber },
    			{ name: 'branching_name',
    			value: branching_name }
    			]
    		});
    	grid.loadData(true);
    }

    function onSubmit2(){
    	var branchingid2=$("#branchingid2").val();
		var zh2=$("#zh2").val();
		var branching_name2=$("#branching_name2").val();
		var branching_code=$("#branching_code2").val();
		var combination_station2=$("#combination_station2").val();
		var rlast_oper2=$("#rlast_oper2").val();
		var rlast_odate2=$("#rlast_odate2").val();
		var remark2=$("#remark2").val();
		var operFlag = false;
		jQuery.ajax({
			type : 'post',
			url : 'boilerBasicInfo_saveOrUpdate.action?orgid='+orgid,
			data: {"branchingid2":branchingid2,"zh2":zh2,"branching_name2":branching_name2,"branching_code2":branching_code2,"combination_station2":combination_station2,"rlast_oper2":rlast_oper2,"rlast_odate2":rlast_odate2,"remark2":remark2},
			success : function(jsondata) { 
				$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
				if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
						alert("操作员修改成功！");
						operFlag = true;
					}else{
						alert("操作员修改失败！");
					}
				},
				error : function(data) {
			
				}
		});
		if (grzh!='' && operFlag) {
			rowEdit(branchingid2,zh2,branching_name2,branching_code2,combination_station2,rlast_oper2,rlast_odate2,remark2);
		}
		if (grzh='' && operFlag) {
			grid.loadData(true);
		}
    }
    
    function rowEdit(branchingid2,zh2,branching_name2,branching_code2,combination_station2,rlast_oper2,rlast_odate2,remark2)
    {
        var row = grid.getSelectedRow();
        row.zh2=zh2;
		row.branching_name=branching_name2;
		row.branching_code=branching_code2;
		row.combination_station=combination_station2;
		row.rlast_oper=rlast_oper2;
		row.rlast_odate2=rlast_odate2;
		row.remark=remark2;
        row.__status = 'update';
        grid.update(row);
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
	
    function itemclick(item) {
  		var rows = grid.getCheckedRows();
      switch (item.icon) {
          case "add":
				grid.set('height',document.body.clientHeight * 0.6);
				jQuery("#editbar").css('height',document.body.clientHeight * 0.3);
				//$("#editbar").toggle();
				jQuery("#editbar").css('display','block');
				$("#branchingid2").val("");
				$("#zh2").val("");
				$("#branching_name2").val("");
				$("#branching_code2").val("");
				$("#combination_station2").val("");
				$("#rlast_oper2").val("");
				$("#rlast_odate2").val("");
				$("#remark2").val("");
				$("#zh2").focus().select();
              break;
          case "modify":
               if (rows.length == 0) { 
					alert('请选择一条你要修改用户信息的数据！');
          	     }else if(rows.length == 1){
					grid.set('height',document.body.clientHeight * 0.6);
					jQuery("#editbar").css('height',document.body.clientHeight * 0.3);
					//$("#editbar").toggle();
					jQuery("#editbar").css('display','block');
					$(rows).each(function (){
						var orgid = this.boilerid;
						$("#boilerid2").val($.trim(boilerid));
						$("#zh2").val($.trim(this.zh));
						$("#branching_name2").val($.trim(this.branching_name));
						$("#branching_code2").val($.trim(this.branching_code));
						$("#combination_station2").val($.trim(this.combination_station));
						$("#rlast_oper2").val($.trim(this.rlast_oper));
						$("#rlast_odate2").val($.trim(this.rlast_odate));
						$("#remark2").val($.trim(this.remark));
					});
					$("#zh2").focus().select();
          	     }else{
          	     	alert('请选择一条你要修改用户信息的数据！');
          	     }
              break;
          case "delete":
          	  if (rows.length == 0) { 
          	    		alert('请选择一条你要删除的数据！')
          	     }else if(rows.length == 1){
          	    	var boilerid;
          	    	$(rows).each(function (){
          	    		boilerid = this.boilerid;
					});
          	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
              	     		 if(yes){
		                          jQuery.ajax({
										type : 'post',
										url : 'boilerBasicInfo_removeBoilerBasicInfo.action?boilerid='+boilerid,
										success : function(data) { 
										if (data != null && typeof(data)!="undefined" && data == 1){
												onSubmit();
											}else{
												alert("不存在符合输入条件数据，请重新输入查询条件 ！");
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
      }
  }
    </script>

	<style type="text/css"> 
    .l-table-edit {}
    .l-table-edit-td{ padding:2px;}
    html,body
	   {
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
        <form id="formsearch" class="l-form" method="post"> 
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
        </form>
    </div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
  <div style="display:none;"></div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div id="editbar" ">
		<table border="0" cellspacing="1">
			<tr>
				<td><input name="branchingid2" type="hidden" id="branchingid2" /></td>
			</tr>
			<tr>
			<td align="right" class="l-table-edit-td" style="width:60px">站号：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                <input type="text" id="zh2" name="zh2"/>
	        </td>
			<td align="right" class="l-table-edit-td" style="width:60px">分线名称：</td>
			<td align="right" class="l-table-edit-td" style="width:40px">
				<input type="text" id="branching_name2" name="branching_name"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">分线编码：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="branching_code2" name="branching_code2"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">所属联合站：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="combination_station2" name="combination_station2"/>
			</td>
			<td align="left" >
			</td>
			</tr>
			<tr>
			<td align="right" class="l-table-edit-td" style="width:60px">操作者：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="rlast_oper2" name="rlast_oper2"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">操作时间：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="rlast_odate2" name="rlast_odate2"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">备注：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="remark2" name="remark2"/>
			</td>
			<td align="left" >
			</td>
			<td align="left" class="l-table-edit-td" style="width:120px">
				<a href="javascript:onSubmit2()" class="l-button" style="width:100px">保存</a>
			</td>
			</tr>
		</table>
	</div>
</body>
</html>