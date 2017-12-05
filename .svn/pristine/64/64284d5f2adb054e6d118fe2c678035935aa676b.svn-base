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
  <!--   <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>   --> 
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/js/ligerui.expand.js" type="text/javascript"></script> 
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../lib/js/LG.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
	<script type="text/javascript">
   	var grid, g;
   	var clearSelecteValue=0;
    $(function () {
		grid = $("#maingrid").ligerGrid({ columns: [
			{ display: '管汇id', name: 'manifoldid', align: 'left', width: 120 ,hide: true},
			{ display: 'orgid', name: 'org_id', minWidth: 140 ,hide:true},
            { display: '站号id', name: 'grzhid', minWidth: 140 ,hide:true},
			{ display: '注转站号', name: 'grzh', minWidth: 140 ,frozen:true},
            { display: '管汇代码', name: 'ghdm', minWidth: 80},
            { display: '管汇号', name: 'ghmc', width: 60,align:'left' },
            { display: '多通阀数', name: 'dtfs', minWidth: 80 },
            { display: '1#多通阀名称', name: 'dtfmc1', minWidth: 120 },
            { display: '1#多通阀通道数', name: 'dtftds1', minWidth: 120 },
            { display: '2#多通阀名称', name: 'dtfmc2', minWidth: 120 },
            { display: '2#多通阀通道数', name: 'dtftds2', minWidth: 120 },
            { display: '上次操作者', name: 'rlast_oper', minWidth: 120 },
            { display: '上次操作时间', name: 'rlast_odate', minWidth: 140 },
            { display: '接入标志', name: 'jrbz', minWidth: 50},
            { display: '备注', name: 'remark', minWidth: 140}], pageSize:30,rownumbers:true,width: '100%',height:'100%',dataAction: 'server',
            url:'manifoldBasicInfo_queryManifoldBasicInfo.action',pageParmName :'pageNo', sortnameParmName:'sort',
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
		$("#oilationname").ligerComboBox({
			url:'manifoldBasicInfo_queryOilSationInfo.action',
			valueField: 'id',
			valueFieldID: 'oilationid',
			textField: 'text',
			selectBoxHeight:350,
			selectBoxWidth:100,
			autocomplete:true,
			hideOnLoseFocus:true,
			onSelected:function (data){
				clearSelecteValue=1;
				liger.get("blockstationname").setValue('');
				liger.get("ghmc").setValue('');
				liger.get("areablock").setValue('');
				
				if ($("#oilationid").val() != 1) {
					setAreablockData($("#oilationid").val(),proData);
					setStationData('',proData,$("#oilationid").val(),clearSelecteValue);
				}
				else {
					getBoilerInitData();
				}
			}
		});
		$("#areablock").ligerComboBox({
			url:'manifoldBasicInfo_queryAreablockInfo.action?orgid=',
			valueField: 'id',
			valueFieldID: 'areablockid',
			textField: 'text',
			selectBoxHeight:350,
			selectBoxWidth:100,
			autocomplete:true,
			hideOnLoseFocus:true,
			onSelected:function (data){
				liger.get("blockstationname").setValue('');
				liger.get("ghmc").setValue('');
				
				if (data != null && typeof(data)!="undefined" && data != ''){
					var se = setStationData($("#areablockid").val(),proData,$("#oilationid").val(),clearSelecteValue);
					if (clearSelecteValue === 1) {
						clearSelecteValue = 2;
					}
				}
			}
		});
		$("#blockstationname").ligerComboBox({
			url:'manifoldBasicInfo_queryStationInfo.action',
            valueField: 'id',
			valueFieldID: 'stationid',
			textField: 'text',
			selectBoxHeight:400,
			selectBoxWidth:140,
            hideOnLoseFocus:true,
            autocomplete:true,
            onSelected:function (data){
				liger.get("ghmc").setValue('');
				if (data != null && typeof(data)!="undefined" && data != ''){
					setGhData($("#stationid").val(),proData);
				}
			}
		});
		$("#ghmc").ligerComboBox({
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
    });
    function setAreablockData(data,proData) {
		jQuery.ajax({
			type : 'post',
			url:'boilerBasicInfo_queryAreablockInfo.action',
			data: {"orgid":data},
			timeout : '3000',
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
			url:'manifoldBasicInfo_queryStationInfo.action',
			data: {"areaid":areaid,"oilid":oilid,"selecteValue":clearSelecteValue},
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
		return clearSelecteValue;
	}

    function setGhData(data,proData) {
		jQuery.ajax({
			type : 'post',
			url:'manifoldBasicInfo_queryManifoldNameInfo.action',
			data: {"orgid":data},
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

    function getManifoldInitData() {
		var oilText=[{ id: 1 , text: '' }];
		var areaText=[{ id: 1 , text: '' }];
		var stationText=[{ id: 1 , text: '' }];
		var ghmcText=[{ id: 1 , text: '' }];
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
				});
				setInitData(oilText,areaText,stationText,ghmcText);
			}
		});
	}
	
    function setInitData(oilText,areaText,stationText,ghmcText) {
		liger.get("oilationname").setData(oilText);
		liger.get("areablock").setData(areaText);
		liger.get("blockstationname").setData(stationText);
		liger.get("ghmc").setData(ghmcText);
	}
    
    //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			alert(id);
			alert(name);
			alert(datatype);
		}
		
    function onSubmit(){
		grid.setOptions({
			parms: [{ name: 'oilationname', value: $("#oilationname").val()},
				{ name: 'areablock', value: $("#areablock").val()},
				{ name: 'blockstationname',value: $("#blockstationname").val()},
				{ name: 'ghmc',value: $("#ghid").val() }]
		});
    	grid.loadData(true);
    }

    function onSubmit2(){
		var org_ids=$("#org_ids").val();
		var zh2=$("#zh2").val();
		var ghdm2=$("#ghdm2").val();
		var ghmc2=$("#ghmc2").val();
		var dtfs2=$("#dtfs2").val();
		var dtfmc1s=$("#dtfmc1s").val();
		var dtftds1s=$("#dtftds1s").val();
		var dtfmc2s=$("#dtfmc2s").val();
		var dtftds2s=$("#dtftds2s").val();
		var rlast_oper2=$("#rlast_oper2").val();
		var rlast_odate2=$("#rlast_odate2").val();
		var jrbz2=$("#jrbz2").val();
		var operFlag = false;
		jQuery.ajax({
			type : 'post',
			url : 'boilerBasicInfo_saveOrUpdate.action?orgid='+orgid,
			data: {"org_ids":org_ids,"zh2":zh2,"ghdm2":ghdm2,"ghmc2":ghmc2,"dtfs2":dtfs2,"dtfmc1s":dtfmc1s,"dtftds1s":dtftds1s,"dtfmc2s":dtfmc2s,"dtftds2s":dtftds2s,"rlast_oper2":rlast_oper2,"rlast_odate2":rlast_odate2,"jrbz2":jrbz2,"remark2":remark2},
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
			rowEdit(org_ids,zh2,ghdm2,ghmc2,dtfs2,dtfmc1s,dtftds1s,dtfmc2s,dtftds2s,rlast_oper2,rlast_odate2,jrbz2,remark2);
		}
		if (grzh='' && operFlag) {
			grid.loadData(true);
		}
    }
    
    function rowEdit(org_ids,zh2,ghdm2,ghmc2,dtfs2,dtfmc1s,dtftds1s,dtfmc2s,dtftds2s,rlast_oper2,rlast_odate2,jrbz2,remark2)
    {
        var row = grid.getSelectedRow();
    	//row.org_id=row.org_id;
		row.zh=zh2;
		row.ghdm=ghdm2;
		row.ghmc=ghmc2;
		row.dtfs=dtfs2;
		row.dtfmc1=dtfmc1s;
		row.dtftds1=dtftds1s;
		row.dtfmc2=dtfmc2s;
		row.dtftds2=dtftds2s;
		row.rlast_oper=rlast_oper2;
		row.jrbz=jrbz2;
		row.remark=remark2;
        row.__status = 'update';
        grid.update(row);
    }
	
    function itemclick(item) {
  		var rows = grid.getCheckedRows();
      switch (item.icon) {
          case "add":
				grid.set('height',document.body.clientHeight * 0.6);
				jQuery("#editbar").css('height',document.body.clientHeight * 0.3);
				//$("#editbar").toggle();
				jQuery("#editbar").css('display','block');
				$("#org_ids").val("");
				$("#zh2").val("");
				$("#gudm2").val("");
				$("#ghmc2").val("");
				$("#dtfs2").val("");
				$("#dtfmc1s").val("");
				$("#dtftds1s").val("");
				$("#dtfmc2s").val("");
				$("#dtftds2s").val("");
				$("#rlast_oper2").val("");
				$("#rlast_odate2").val("");
				$("#jrbz2").val("");
				$("#zh2").focus().select("");
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
						//var org_ids = this.org_id;
						$("#org_ids").val($.trim(this.org_id));
						$("#zh2").val($.trim(this.zh));
						$("#gudm2").val($.trim(this.gudm));
						$("#ghmc2").val($.trim(this.ghmc));
						$("#dtfs2").val($.trim(this.dtfmc1));
						$("#dtfmc1s").val($.trim(this.dtfmc1));
						$("#dtftds1s").val($.trim(this.dtftds1));
						$("#dtfmc2s").val($.trim(this.dtfmc2));
						$("#dtftds2s").val($.trim(this.dtftds2));
						$("#rlast_oper2").val($.trim(this.rlast_oper));
						$("#rlast_odate2").val($.trim(this.rlast_odate));
						$("#jrbz2").val($.trim(this.jrbz));
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
          	    	var orgid;
          	    	$(rows).each(function (){
          	    		orgid = this.org_id;
					});
          	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
              	     		 if(yes){
		                          jQuery.ajax({
										type : 'post',
										url : 'boilerBasicInfo_removeBoilerBasicInfo.action?orgid='+orgid,
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
					<td align="right" class="l-table-edit-td" style="width:60px">采油站：</td>
	                <td align="right" class="l-table-edit-td" >
	                	<input type="text" id="oilationname" name="oilationname"/>
	                </td>
					<td align="right" class="l-table-edit-td" style="width:60px">区块名称：</td>
	                <td align="right" class="l-table-edit-td" >
	                	<input type="text" id="areablock" name="areablock"/>
	                </td>
	                
					<td align="right" class="l-table-edit-td" style="width:60px">注转站号：</td>
	                <td align="right" class="l-table-edit-td" >
	                	<input type="text" id="blockstationname" name="blockstationname"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">管汇号：</td>
	                <td align="left" class="l-table-edit-td"  >
	                	<input type="text" id="ghmc" name="ghmc"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
				<td align="left" class="l-table-edit-td"  >
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
				<td><input name="org_ids" type="hidden" id="org_ids" /></td>
				<td><input name="org_id2" type="hidden" id="org_id2" /></td>
			</tr>
			<tr>
			<td align="right" class="l-table-edit-td" style="width:60px">注转站号：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                <input type="text" id="zh2" name="zh2"/>
	        </td>
			<td align="right" class="l-table-edit-td" style="width:60px">管汇代码：</td>
			<td align="right" class="l-table-edit-td" style="width:40px">
				<input type="text" id="ghdm2" name="ghdm2"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">管汇号：</td>
			<td align="right" class="l-table-edit-td" style="width:40px">
				<input type="text" id="ghmc2" name="ghmc2"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">多通阀数：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="dtfs2" name="dtfs2"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">锅炉编号：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="dtfs2" name="dtfs2"/>
			</td>
			<td align="left" ></td>
			<td align="right" class="l-table-edit-td" style="width:60px">1#多通阀名称：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="dtfmc1s" name="dtfmc1s"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">1#多通阀通道数：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="dtftds1s" name="dtftds1s"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">2#多通阀名称：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="dtfmc2s" name="dtfmc2s"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">2#多通阀通道数：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="dtftds2s" name="dtftds2s"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">接入标志：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="jrbz2" name="jrbz2"/>
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