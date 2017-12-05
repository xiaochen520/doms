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
		
		
	
		var proData = [{ id: 1 , text: '' }];
		$("#areablock").ligerComboBox({
			url:'station_queryAreablockInfo.action',
			valueField: 'id',
			valueFieldID: 'orgid',
			textField: 'text',
			selectBoxHeight:200,
			width: 120 ,
			autocomplete:true,
			hideOnLoseFocus:true,
			onSelected:function (data){
				if (data != null && typeof(data)!="undefined" && data != ''){
					setStationData($("#orgid").val(),proData);
				}
			}
		});
		$("#stationNumber").ligerComboBox({
            valueField: 'id',
			valueFieldID: 'orgid',
			textField: 'text',
			selectBoxHeight:350,
            width: 120,
            hideOnLoseFocus:true,
            autocomplete:true,
            onSelected:function (data){
				if (data != null && typeof(data)!="undefined" && data != ''){
			//	alert("wo cao");
					setBoilerData($("#orgid").val(),proData);
				}
			}
		});
		$("#boilersName").ligerComboBox({
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
		manager = $("#grzh2").ligerComboBox({
			url:"station_queryStationInfo.action?orgid=",
			valueField: 'id',
			valueFieldID: 'idtest',
			textField: 'text',
			selectBoxHeight:120,
			width: 120,
			hideOnLoseFocus:true,
            autocomplete:true,
            alwayShowInTop:false,
			onSelected:function (data){
				//alert(data+"data");
			}
		});
    });


    function setStationData(data,proData) {
		jQuery.ajax({
			type : 'post',
			url:'station_queryStationInfo.action',
			data: {"orgid":data},
			timeout : '3000',
			success : function(jsondata) {
				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
				//	alert(jsondata + ' come in');
					liger.get("stationNumber").setData(eval('(' + jsondata + ')'));
				}
				else{
					liger.get("stationNumber").setData(proData);
				}
			},
			error : function(jsondata) {
				alert("请求数据失败，请重新查询");
			}
		});
	}

    function setBoilerData(data,proData) {
		jQuery.ajax({
			type : 'post',
			url:'station_queryBoilersNameInfo.action',
			data: {"orgid":data},
			timeout : '3000',
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

    function onSubmit(){
		var stationNumber = $("#stationNumber").val();
		var boilername = $("#boilersName").val();
		var areablock = $("areablock").val();
		stationNumber = stationNumber.replace(/\s+/g,"");
		boilername = boilername.replace(/\s+/g,"");
		areablock = areablock.replace(/\s+/g,"");
    	grid.setOptions({
    		parms: [
    			{ name: 'areablock',
    			value: areablock },
    			{ name: 'stationNumber',
    			value: stationNumber },
    			{ name: 'boilersName',
    			value: boilersName }
    			]
    		});
    	grid.loadData(true);
    }
    function onSubmit2(){
    	var zzzmc2		=$("#zzzmc2").val();
    	var stationid2	=$("#stationid2").val();
    	var org_id2		=$("#org_id2").val();
		var ghs2		=$("#ghs2").val();
		var cygs2		=$("#cygs2").val();
		var cyggg2		=$("#cyggg2").val();
		var zybs2		=$("#zybs2").val();
		var jrbz2		=$("#jrbz2").val();
		var zzzlx2		=$("#zzzlx2").val();
		var remark2		=$("#remark2").val();
		var operFlag = false;
		jQuery.ajax({
			type : 'post',
			url : 'station_saveOrUpdate.action?orgid='+orgid,
			data: {"zzzmc2":zzzmc2,"stationid2":stationid2,"org_id2":org_id2,"ghs2":ghs2,"cygs2":cygs2,"cyggg2":cyggg2,"zybs2":zybs2,"jrbz2":jrbz2,"zzzlx2":zzzlx2,"remark2":remark2},
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
			rowEdit(stationid2,boilername2,boilertype2,boilercode2,rlastoper2,rlastodate2,remark2);
		}
		if (grzh='' && operFlag) {
			grid.loadData(true);
		}
    }
    
    function rowEdit(stationid2,boilername2,boilertype2,boilercode2,rlastoper2,rlastodate2,remark2)
    {
        var row = grid.getSelectedRow();
    	row.stationid2=row.stationid2;
		row.boilername2=boilername2;
		row.boilertype2=boilertype2;
		row.boilercode2=boilercode2;
		row.rlastoper2=rlastoper2;
		row.rlastodate2=rlastodate2;
		row.remark2=remark2;
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
				$("#grzh").val("");
				$("#stationid2").val("");
				$("#boilername2").val("");
				$("#boilertype2").val("");
				$("#boilercode2").val("");
				$("#rlastoper2").val("");
				$("#rlastodate2").val("");
				$("#remark2").val("");
				$("#grzh").focus().select();
				//$("editbar").toggle(
						 // function () {
						 //   $(this).addClass("editbar");
						 //   $('#editbar').show();
						 // },
						 // function () {
						 //   $(this).removeClass("editbar");
						 //   $('#editbar').hide();
						 // }
				//);
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
						$("#stationid2").val($.trim(orgid));
						$("#grzh").val($.trim(this.boilername));
						$("#boilername2").val($.trim(this.boilername));
						$("#boilertype2").val($.trim(this.boilertype));
						$("#boilercode2").val($.trim(this.boilercode));
						$("#rlastoper2").val($.trim(this.rlastoper));
						$("#rlastodate2").val($.trim(this.rlastodate));
						$("#remark2").val($.trim(this.remark));
					});
					$("#grzh").focus().select();
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
          	    		orgid = this.boilerid;
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
					<td align="right" class="l-table-edit-td" style="width:60px">区块名称：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="areablock" name="areablock"/>
	                </td>
	                
					<td align="right" class="l-table-edit-td" style="width:60px">站号：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="stationNumber" name="stationNumber"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">锅炉名称：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="boilersName" name="boilersName"/>
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
				<td><input name="stationid2" type="hidden" id="stationid2" /></td>
				<td><input name="org_id2" type="hidden" id="org_id2" /></td>
			</tr>
			<tr>
			<td align="right" class="l-table-edit-td" style="width:60px">注转站名称：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                <input type="text" id="zzzmc2" name="zzzmc2"/>
	        </td>
			<td align="right" class="l-table-edit-td" style="width:60px">管汇数：</td>
			<td align="right" class="l-table-edit-td" style="width:40px">
				<input type="text" id="ghs2" name="ghs2"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">储油罐数：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="cygs2" name="cygs2"/>
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">储油罐规模：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="cyggg2" name="cyggg2"/>
			</td>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">转油泵数：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="zybs2" name="zybs2"/>
			</td>
			<td align="left" >
			</td>
			</tr>
			<tr>
			<td align="left" >
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">接入标志：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="jrbz2" name="jrbz2"/>
			</td>
			<td align="right" class="l-table-edit-td" style="width:60px">注转站类型：</td>
			<td align="left" class="l-table-edit-td" style="width:40px">
				<input type="text" id="zzzlx2" name="zzzlx2"/>
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