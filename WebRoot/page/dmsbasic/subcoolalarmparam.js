var grid;
var toolbar;
var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
$(function () {
	$("#areablock").ligerComboBox();
	$("#oilstationname").ligerComboBox();
	$("#blockstationname").ligerComboBox();
	$("#wellnum").ligerComboBox();

	var proData = [{ id: 1 , text: '' }];
	jQuery.ajax({
		type : 'post',
		url : 'subcool_alarmParamInit.action',
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
	
	$("#areablock").ligerComboBox({
		url:'sagd_queryAreablockInfo.action?arg=sagd',
		valueField: 'id',
		valueFieldID: 'areablockid',
		textField: 'text',
		selectBoxHeight:350,
		selectBoxWidth:100,
		autocomplete:true,
		hideOnLoseFocus:true,
		onBeforeSelect:function (data){
			liger.get("blockstationname").setValue('');
			liger.get("wellnum").setValue('');	
		},
		onSelected:function (data){
			$('#area_block').text(liger.get("areablock").getText());
			if (data != null && typeof(data)!="undefined" && data != '') {
				getStationInitData(data, proData);
			}
    	}
	});
	
	$("#blockstationname").ligerComboBox({
		url:'sagd_queryStationInfo.action',
        valueField: 'id',
		valueFieldID: 'stationid',
		textField: 'text',
		selectBoxHeight:400,
		selectBoxWidth:140,
        hideOnLoseFocus:true,
        autocomplete:true,
        onBeforeSelect:function (data){
        	liger.get("wellnum").setValue('');
		},
        onSelected:function (data){
			$('#blockstation_name').text(liger.get("blockstationname").getText());
			if (data != null && typeof(data)!="undefined" && data != ''){
				setWellInitData(data, proData);
			}
		}
	});
	
	$("#wellnum").ligerComboBox({
		url:'sagd_queryWellNameInfo.action',
		valueField: 'id',
		valueFieldID: 'wellnumsid',
		textField: 'text',
		selectBoxHeight:350,
		selectBoxWidth:100,
		hideOnLoseFocus:true,
        autocomplete:true,
        onBeforeSelect:function (data){
        	if(liger.get("areablock").getValue() != ''){
        		
        	}
		},
		onSelected:function (data){
			$('#well_num').text(liger.get("wellnum").getText());
		}
	});
	
	$("#form").ligerForm();
	$.metadata.setType("attr", "validate");
	var v = $("#form").validate({
		debug: true,
		errorPlacement: function (lable, element) {
			if (element.hasClass("l-textarea")) {
				element.addClass("l-textarea-invalid");
			} else if (element.hasClass("l-text-field")) {
				element.parent().addClass("l-text-invalid");
			}
			$(element).removeAttr("title").ligerHideTip();
			$(element).attr("title", lable.html()).ligerTip(); 
		},
		success: function (lable) {
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
		submitHandler: function () {
			var calid = $("#calid").text();
			var areablock = $("#area_block").text();
			var blockstationname = $("#blockstation_name").text();
			var wellnum = $("#well_num").text();
			var min = $('#min_subcool').val();
			var max = $('#max_subcool').val();
			var cal = $('input[name="cal"]:checked').val();
			var flow = $('input[name="flow"]:checked').val();
			switch(operate) {//判断修改还是添加操作 1-添加、2-修改
				case 1:
					if(liger.get("areablock").getValue() != "") {
						jQuery.ajax({
							type : 'post',
							url : 'subcool_addAlarmParam.action',
							data : {"areablock" : areablock, "blockstationname" : blockstationname, "wellnum" : wellnum, "min" : min, "max" : max, "cal" : cal, "flow" : flow},
							success : function(jsondata) {
								$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
								if (jsondata != null && typeof(jsondata) != "undefined"){
									var data = eval('(' + jsondata + ')');
									var count = data['count'];
									if(count > 0) {
										$.ligerDialog.success(count + '条SubCool井组参数添加成功！');
										onSubmit();
									}
									else {
										$.ligerDialog.question('SubCool井组参数数据已经存在！');
									}
									setpage5(1); //隐藏编辑界面
									setItemsd(2); //去掉隐藏，显示按钮
									operate = 0;
								}
							},
							error : function(data) {
								$.ligerDialog.question('SubCool井组参数添加失败！');
							}
						});
					} else {
						$.ligerDialog.question('请选择区块、转注站或井号信息！'); 
					}
					break;
				case 2:
					if(cal == 3) {
						if($("#subtract").val() == '') {
	        				$.ligerDialog.success("I井注汽压力的减数不能为空！");
	            			return;
	        			}
						if(isNaN($("#subtract").val())) {
							$.ligerDialog.success(" 您输入的减数不是数字！");
	            			return;
						}
					}
					
					jQuery.ajax({
						type : 'post',
						url : 'subcool_modifyAlarmParam.action',
						data : {"calid" : calid, "min" : min, "max" : max, "cal" : cal, "flow" : flow, "sub" : $("#subtract").val()},
						success : function(jsondata) {
							$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
							if (jsondata != null && typeof(jsondata) != "undefined"){
								var data = eval('(' + jsondata + ')');
								var result = data['result'];
								if(result) {
									$.ligerDialog.success('SubCool井组参数修改成功！');
									onSubmit();
								} else {
									$.ligerDialog.question('SubCool井组参数修改失败！');
								}
								setpage5(1); //隐藏编辑界面
								setItemsd(2); //去掉隐藏，显示按钮
								operate = 0;
							}
						},
						error : function(data) {
							$.ligerDialog.question('SubCool井组参数修改失败！');
						}
					});
					break;
			}
		}
	});
});

function onSubmit() {
	grid.setOptions({
		parms: [
	        { name: 'oilstationname', value: $("#oilstationname").val() },
	        { name: 'areablock', value: $("#areablock").val() },
	        { name: 'blockstationname', value: $("#blockstationname").val() },
	        { name: 'wellnum', value: $("#wellnum").val() }
		]
	});
 	grid.loadData(true);
}

function itemclick(item) {
	var rows = grid.getCheckedRows();
	switch (item.icon) {
		case "add":
			if($("#subtract").attr('disabled') != true) {
				$("#subtract").attr('disabled', true);
			}
			if(operate != 1 && operate != 2){
				setpage5(0); //显示编辑界面
				setItemsd(0);//0-显示编辑区，添加隐藏按钮
			}
			operate = 1;
			assignM(1);
			break;
		case "modify":
			if($("#subtract").attr('disabled') == true) {
				$("#subtract").attr('disabled', false);
			}
			if (rows.length == 0) {
				$.ligerDialog.question('请选择一条你要修改信息的数据！');   	    
			} else if(rows.length == 1) {
				if(operate != 1 && operate != 2){
					setpage5(0); //显示编辑界面
					setItemsd(0);//1-隐藏编辑区添加显示按钮
				}
				operate = 2;
				assignM(2, rows[0]);
			} else {
				$.ligerDialog.question('请选择一条你要修改信息的数据！');
			}
			break;
		case "delete":
			if (rows.length == 0) {
				$.ligerDialog.question('请选择一条你要删除的数据！');
			}else if(rows.length == 1){
				var calid = rows[0].CALID;
				$.ligerDialog.confirm('确定删除吗?', function (yes) {
					if(yes) {
						jQuery.ajax({
							type : 'post',
							url:'subcool_removeAlarmParam.action',
							data: {"calid":calid},
							success : function(jsondata) {
								var data = eval('(' + jsondata + ')');
								var result = data['result'];
								if(result) {
									$.ligerDialog.success('SubCool井组参数删除成功！');
									onSubmit();
								} else {
									$.ligerDialog.question('SubCool井组参数删除失败！');
								}
							},
							error : function(jsondata) {
								$.ligerDialog.question('SubCool井组参数删除失败！');
							}
						});
					}
				});	
			}else{
				$.ligerDialog.question("请选择一条你要删除的数据！");				
			}
			operate = 0;
			break;
		case "save":
			$("#form").submit();
			break;
		case "up":
			setpage5(0); //显示编辑界面
			setItemsd(0);//0-显示编辑区，添加隐藏按钮
			break;   
		case "down":
			setpage5(1); //隐藏编辑界面
			setItemsd(1);//1-隐藏编辑区添加显示按钮
			break;
	}
}

function fromAu(data){
	if(operate == 2) {
		if (data != null && typeof(data)!="undefined"){
			$("#calid").text(data.CALID);
			$("#area_block").text(data.AREABLOCK);
			$("#oilstation_name").text(data.OILSTATIONNAME);
			$("#blockstation_name").text(data.BLOCKSTATIONNAME);
			$("#well_num").text(data.JHMC);
			$("#prod_stages").text(data.PRODSTAGES);
			$("input[name='cal'][value='"+data.calculate_methodsid+"']").attr('checked', true);
			$("#min_subcool").val(data.min_subcool);
			$("#max_subcool").val(data.max_subcool);
			$("input[name='flow'][value='"+data.flowid+"']").attr('checked', true);
			$("#subtract").val(data.subtract);
		}
	}
}

function assignM(flg, data){
	switch (flg) {
		case 1:
			$("#calid").text("");
			$("#area_block").text(liger.get("areablock").getText());
			$("#oilstation_name").text("");
			$("#blockstation_name").text(liger.get("blockstationname").getText());
			$("#well_num").text(liger.get("wellnum").getText());
			$("#prod_stages").text("");
			jQuery.ajax({
				type : 'post',
				url : 'subcool_defaultParamInit.action',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata) != "undefined"){
						if (jsondata == '-16001') {
							$("input[name='cal'][value='1']").attr('checked', true);
							$("#min_subcool").val(5);
							$("#max_subcool").val(15);
							$("input[name='flow'][value='1']").attr('checked', true);
						} else {
							var data = eval('(' + jsondata + ')');
							$('input[name="cal"][value="' + data['cal'] + '"]').attr('checked', true);
							$("#min_subcool").val(data['min_subcool']);
							$("#max_subcool").val(data['max_subcool']);
							$('input[name="flow"][value="' + data['flow'] + '"]').attr('checked', true);
						}
					}
				},
				error : function(data) {
					$.ligerDialog.question("初始化默认参数失败！");
				}
			});
			$("input[name='cal'][value='1']").attr('checked', true);
			$("#min_subcool").val(5);
			$("#max_subcool").val(15);
			$("input[name='flow'][value='1']").attr('checked', true);
			$("#subtract").val(1);
			break;
		case 2:
			if (data != null && typeof(data)!="undefined"){
				$("#calid").text(data.CALID);
				$("#area_block").text(data.AREABLOCK);
				$("#oilstation_name").text(data.OILSTATIONNAME);
				$("#blockstation_name").text(data.BLOCKSTATIONNAME);
				$("#well_num").text(data.JHMC);
				$("#prod_stages").text(data.PRODSTAGES);
				$("input[name='cal'][value='"+data.calculate_methodsid+"']").attr('checked', true);
				$("#min_subcool").val(data.min_subcool);
				$("#max_subcool").val(data.max_subcool);
				$("input[name='flow'][value='"+data.flowid+"']").attr('checked', true);
				$("#subtract").val(data.subtract);
			}
			break
	}
}

function setWellInitData(data,proData) {
	jQuery.ajax({
		type : 'post',
		url:'sagd_queryWellNameInfo.action',
		data: {"orgid":data},
		
		success : function(jsondata) {
			if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
				liger.get("wellnum").setData(eval('(' + jsondata + ')'));
			}
			else{
				liger.get("wellnum").setData(proData);
			}
		},
		error : function(jsondata) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
}

function getStationInitData(data,proData) {
	var url;
	if(data == '1') 
		url = 'sagd_queryStationInfo.action';
	else
		url = 'sagd_queryStationInfo.action?areaid='+data;
	jQuery.ajax({
		type : 'post',
		url : url,
		success : function(jsondata) {
			if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
				liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
				liger.get("wellnum").setData(proData);
			}
			else{
				liger.get("blockstationname").setData(proData);
				liger.get("wellnum").setData(proData);
			}
		},
		error : function(jsondata) {
			$.ligerDialog.question("请求数据失败，请重新查询");
		}
	});
}