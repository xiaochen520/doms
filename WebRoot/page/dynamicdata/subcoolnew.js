var grid;
$(function () {
	$("#areablock").ligerComboBox();
	$("#blockstationname").ligerComboBox();
	$("#wellnum").ligerComboBox();
	
	
	
	
	var proData = [{ id: 1 , text: '' }];
	jQuery.ajax({
		type : 'post',
		url : 'subcool_alarmNewInit.action',
		success : function(jsondata) {
		if (jsondata != null && typeof(jsondata)!="undefined"){
				var data = eval('(' + jsondata + ')');
				grid = $("#maingrid").ligerGrid(data);
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

});

function onSubmit() {
	loadData();
}

function loadData() {
	grid.setOptions({
		parms: [
	        { name: 'areablock', value: $("#areablock").val() },
	        { name: 'blockstationname', value: $("#blockstationname").val() },
	        { name: 'wellnum', value: $("#wellnum").val() }
		]
	});
 	grid.loadData(true);
}

function onDispose() {
	var rows = grid.getCheckedRows();
	if (rows.length == 0) {
		$.ligerDialog.question('请选择一条报警数据！');    
	} else if(rows.length == 1) {
		var cur = parseInt(rows[0].CURSUBCOOL);
		var min = parseInt(rows[0].MINSUBCOOL);
		var max = parseInt(rows[0].MAXSUBCOOL);
		if(cur < min || cur > max) {
			$.ligerDialog.open({
				height:520,
				width: 710,
				title : '报警信息处理',
				url: 'subcoolalarminfodispose.jsp?sagdid='+rows[0].SAGDID,
				showMax: false,
				showToggle: true,
				showMin: false,
				isResize: false,
				slide: false,
				buttons: [
		            {
		            	text: '保存',
		            	onclick: function (item, dialog) {
			            	var data = {
		            				"rdid" : rows[0].CALID,
		            				"modifyflow" : '',
		            				"modifyjig" : '',
		            				"modifynip" : '',
		            				"modifymode" : ''
		            		};
		            		var mode = dialog.frame.document.getElementsByName('mode');
		            		var modifyflow = dialog.frame.document.getElementById('modifyflow');
		            		var modifyjig = dialog.frame.document.getElementById('modifyjig');
		            		var modifynip = dialog.frame.document.getElementById('modifynip');
		            		var mode_val;
		            		for(var i=0; i<mode.length; i ++){
		            	        if(mode[i].checked){
		            	            mode_val = mode[i].value;
		            	        }
		            	    }
		            		if(mode_val == 1) {
		            			data.modifyflow = modifyflow.value;
		            			data.modifyjig = modifyjig.value;
		            			data.modifynip = modifynip.value;
		            			data.modifymode = mode_val;
		            		} else if(mode_val == 2) {
		            			if(modifyflow.value == '' || modifyjig.value == '' || modifynip.value == '') {
		            				$.ligerDialog.success("人工调整控制参数没有填写完整！");
			            			return;
		            			}
		            			if(isNaN(modifyflow.value) || isNaN(modifyjig.value) || isNaN(modifynip.value)) {
		            				$.ligerDialog.success("人工调整控制参数必须填写数字！");
			            			return;
		            			}
		            			data.modifyflow = modifyflow.value;
		            			data.modifyjig = modifyjig.value;
		            			data.modifynip = modifynip.value;
		            			data.modifymode = mode_val;
		            		} else {
		            			$.ligerDialog.success("请选择人工调整控制参数的方式！");
		            			return;
		            		}
		            		
		            		jQuery.ajax({
		            			type : 'post',
		            			url:'subcool_modifySuggestParam.action',
		            			data: data,
		            			success : function(jsondata) {
		            				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
		            					$.ligerDialog.success("Subcool建议控制参数更新成功！");
		            					dialog.close();
		            					loadData();
		            				}
		            				else{
		            					$.ligerDialog.error(outerror(jsondata));
		            				}
		            			},
		            			error : function(jsondata) {
		            				$.ligerDialog.question("Subcool建议控制参数更新失败！");
		            			}
		            		});
	            		},
	            		cls:'l-dialog-btn-highlight'
        			},
		            {
        				text: '取消',
        				onclick: function (item, dialog) {
        					dialog.close();
        				}
        			}
	            ]
			});
		} else {
			$.ligerDialog.question('您选择的数据不需要进行处理！');
		}
	} else {
		$.ligerDialog.question('请选择一条报警数据！');
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

function onExport() {
	var rows = grid.getCheckedRows();
    var str = "";
    $(rows).each(function ()
    {
        str += this.SAGDID + ",";
    });
    str = str.substring(0, str.length - 1);
    if (rows.length > 0) {
		 $.ligerDialog.confirm('确定要生成调控单吗?',function (yes) { if(yes) postSubmit(str) });
	} else {
		$.ligerDialog.question('您没有选择需要生成调控单的数据！');
	}
}

function postSubmit(data) {
	var url = "subcool_exportSuggest.action";
	  
	var postForm = document.createElement("form");
	postForm.method="post";
	postForm.action = url;
	
	var sagdsInput = document.createElement("input");
	sagdsInput.setAttribute("name", "sagds");
	sagdsInput.setAttribute("value", data);
	postForm.appendChild(sagdsInput);
	
	document.body.appendChild(postForm) ;  
	postForm.submit() ;     
	document.body.removeChild(postForm) ;
}