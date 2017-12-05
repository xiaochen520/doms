<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>锅炉基础信息管理</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
			rel="stylesheet" type="text/css" />
		<script src="../../lib/jquery/jquery-1.5.2.min.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerForm.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerComboBox.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerButton.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerDialog.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerRadio.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerTextBox.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerTip.js"
			type="text/javascript"></script>
		<script src="../../lib/jquery-validation/jquery.validate.min.js"
			type="text/javascript"></script>
		<script src="../../lib/jquery-validation/jquery.metadata.js"
			type="text/javascript"></script>
		<script src="../../lib/jquery-validation/messages_cn.js"
			type="text/javascript"></script>

		<script src="../../lib/ligerUI/js/plugins/ligerGrid.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerToolBar.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerResizable.js"
			type="text/javascript"></script>
		<%--<script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>--%>
		<link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />
		<script src="../../lib/js/common.js" type="text/javascript"></script>
		<script src="../../lib/json2.js" type="text/javascript"></script>
		<script src="../../noBackspace.js" type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js"
			type="text/javascript"></script>
		<script src="../../lib/myday.js" type="text/javascript"></script>
		<script type="text/javascript">
	var eee;
	var grid = null;
	var toolbar;
	var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0

	//鼠标选择数据
	var boilerid; //锅炉id
	var boiler_name; //锅炉名称
	var org_id; //组织结构id
	var yxzid; //运行注ID
	var yxz;
	var gr_id; //供热站id
	var grzh; //供热站
	//var  srqk;   //受汽区块
	var srqkid; //受汽区块ID
	var accept_unit; //受汽区块
	var sqdw; //受汽区块ID

	var boiler_type; //锅炉类型
	var boiler_code; //锅炉编号
	var rating_capacity; //额定水量
	var injection_allocation_rate; //配注量
	var commissioning_date
	//投产日期
	var jrbz; //接入标志
	var ljjd_s; //服务器逻辑节点名
	var status_value
	//建设生产状态
	var factory_uc
	//厂家ID

	var remark
	//备注
    var dyear;
	var clearSelecteValue = 0;
	var serverid;
	$(function() {
		//获取报表及功能按钮ｊｓ
		jQuery.ajax( {
			type : 'post',
			url : 'boilerBasicInfo_pageInit.action',
			success : function(jsondata) {
				if (jsondata != null && typeof (jsondata) != "undefined") {
					var data = eval('(' + jsondata + ')');
					grid = $("#maingrid").ligerGrid(data);
					toolbar = grid.topbar.ligerGetToolBarManager();

				} else {
					$.ligerDialog.error(outerror(jsondata));
				}
			},
			error : function(data) {

			}
		});

		var proData = [ {
			id : 1,
			text : ''
		} ];
		$("#oilationname").ligerComboBox( {
			url : 'rulewell_queryOilSationInfo.action?arg=boiler',
			valueField : 'id',
			valueFieldID : 'oilationid',
			textField : 'text',
			selectBoxHeight : 350,
			selectBoxWidth : 100,
			autocomplete : true,
			hideOnLoseFocus : true,
			onBeforeSelect : function(newvalue) {
				liger.get("blockstationname").setValue('');
				liger.get("boilersName").setValue('');
				//liger.get("areablock").setValue('');
		},
		onSelected : function(data) {
			clearSelecteValue = 1;
			if ($("#oilationid").val() != 1) {
				//setAreablockData($("#oilationid").val(),proData);
			setStationData('', proData, $("#oilationid").val(), '5');
		} else {
			getBoilerInitData();
		}
	}
		});
		$("#areablock")
				.ligerComboBox(
						{
							url : 'boilerBasicInfo_searchAcceptunit.action?orgid=boilerbasicinfo',
							valueField : 'id',
							valueFieldID : 'hidareablockid',
							textField : 'text',
							selectBoxHeight : 300,
							selectBoxWidth : 100,
							autocomplete : true,
							hideOnLoseFocus : true
						/*,
						onBeforeSelect: function (newvalue){
							liger.get("blockstationname").setValue('');
							liger.get("boilersName").setValue('');
						},
						onSelected:function (data){
							if (data != null && typeof(data)!="undefined" && data != ''){
								var se = setStationData($("#areablockid").val(),proData,$("#oilationid").val(),clearSelecteValue);
								if (clearSelecteValue === 1) {
									clearSelecteValue = 2;
								}
							}
						}*/
						});

		$("#acceptunit").ligerComboBox( {
			url : 'station_queryAreablockInfo.action?orgid=sqdw',
			valueField : 'id',
			valueFieldID : 'hidacceptunitid',
			textField : 'text',
			selectBoxHeight : 300,
			selectBoxWidth : 100,
			autocomplete : true,
			hideOnLoseFocus : true

		});
		$("#blockstationname").ligerComboBox(
				{
					url : 'boilerBasicInfo_queryStationInfo.action',
					valueField : 'id',
					valueFieldID : 'stationid',
					textField : 'text',
					selectBoxHeight : 300,
					selectBoxWidth : 140,
					hideOnLoseFocus : true,
					autocomplete : true,
					onBeforeSelect : function(newvalue) {
						liger.get("boilersName").setValue('');
					},
					onSelected : function(data) {
						if (data != null && typeof (data) != "undefined"
								&& data != '') {
							setBoilerData($("#stationid").val(), proData);
						}
					}
				});
		$("#boilersName").ligerComboBox( {
			url : 'boilerBasicInfo_queryBoilersNameInfo.action?orgid=',
			valueField : 'id',
			valueFieldID : 'boilersid',
			textField : 'text',
			selectBoxHeight : 350,
			selectBoxWidth : 100,
			hideOnLoseFocus : true,
			autocomplete : true,
			onSelected : function(data) {
			}
		});
		//收索条件接入状态
		$("#jrbz1").ligerComboBox({
				url:'comboboxdata_searchSwitchInflg.action?args=ALL',
				valueField: 'id',
				valueFieldID: 'hidjrbzid1',
				textField: 'text',
				selectBoxHeight:150,
				width: 100,
				autocomplete:true,
				hideOnLoseFocus:true
		});
		document.getElementById("jrbz1").value= "全部";
		//收索条件建设状态
		$("#status_value1").ligerComboBox( {
			url : 'station_searchStatusValueAll.action',
			valueField : 'id',
			valueFieldID : 'hidstatus1id',
			textField : 'text',
			selectBoxHeight : 200,
			width : 120,
			autocomplete : true,
			hideOnLoseFocus : true,
			initText : '全部'
		});

		//$("#status_value1").val("全部");
		document.getElementById("status_value1").value = "全部"; //额定水量
		$("#group").ligerComboBox( {
			url : 'boilerBasicInfo_searchGroupInfo.action',
			valueField : 'id',
			valueFieldID : 'boilersid',
			textField : 'text',
			selectBoxHeight : 350,
			selectBoxWidth : 100,
			hideOnLoseFocus : true,
			autocomplete : true,
			onSelected : function(data) {
			}
		});

		$("#sqqk").ligerComboBox( {
			url : 'boilerBasicInfo_searchSrqk.action?orgid=srqk',
			valueField : 'id',
			valueFieldID : 'hidsqqk2id',
			textField : 'text',
			selectBoxHeight : 300,
			selectBoxWidth : 100,
			autocomplete : true,
			hideOnLoseFocus : true

		});
		$("#yxz2").ligerComboBox( {
			url : 'boilerBasicInfo_searchGroupInfo.action',
			valueField : 'id',
			valueFieldID : 'yxz2id',
			textField : 'text',
			selectBoxHeight : 120,
			width : 120,
			hideOnLoseFocus : true,
			autocomplete : true,
			alwayShowInTop : false,
			onBeforeSelect : function(newvalue) {
				liger.get("grzh2").setValue('');
			},
			onSelected : function(data) {
				setStationData('', proData, $("#yxz2id").val(), '4');
			}
		});

		$("#grzh2").ligerComboBox( {
			url : 'boilerBasicInfo_queryStationInfo.action?areaid=all',
			valueField : 'id',
			valueFieldID : 'grzh2id',
			textField : 'text',
			selectBoxHeight : 120,
			selectBoxWidth : 140,
			hideOnLoseFocus : true,
			autocomplete : true,
			alwayShowInTop : false,
			onSelected : function(data) {
				//alert(data+"data");
		}
		});

		$("#sqdw").ligerComboBox( {
			url : 'station_queryAreablockInfo.action?orgid=sqdw',
			valueField : 'id',
			valueFieldID : 'hidsqdw2id',
			textField : 'text',
			selectBoxHeight : 300,
			selectBoxWidth : 100,
			autocomplete : true,
			hideOnLoseFocus : true

		});
		//1，湿蒸；2，过热；3，高干度；4，燃煤
		$("#boilertype2").ligerComboBox( {
			valueField : 'id',
			valueFieldID : 'typeid',
			textField : 'text',
			data : [ {
				text : '湿蒸',
				id : '1'
			}, {
				text : '过热',
				id : '2'
			}, {
				text : '高干度',
				id : '3'
			}, {
				text : '燃煤',
				id : '4'
			} ],
			selectBoxHeight : 150,
			width : 120,
			autocomplete : true,
			hideOnLoseFocus : true
		});

		$("#commissioning_date").ligerDateEditor( {
			format : "yyyy-MM-dd",
			labelWidth : 100,
			labelAlign : 'center',
			cancelable : false
		});
		$("#jrbz").ligerComboBox({
			url:'comboboxdata_searchSwitchInflg.action',
			valueField: 'id',
			valueFieldID: 'hidjrbzid',
			textField: 'text',
			selectBoxHeight:100,
			width: 100,
			autocomplete:true,
			hideOnLoseFocus:true
		});
		$("#status_value").ligerComboBox( {
			url : 'station_searchStatusValue.action',
			valueField : 'id',
			valueFieldID : 'hidstatusid',
			textField : 'text',
			selectBoxHeight:240,
			width: 120,
			autocomplete:true,
			hideOnLoseFocus:true
		});
		$("#ljjd_s").ligerComboBox( {
			url : 'sagd_getServerNode.action',
			valueField : 'id',
			valueFieldID : 'hidserverid',
			textField : 'text',
			selectBoxHeight : 150,
			selectBoxWidth : 100,
			width : 120,
			autocomplete : true,
			hideOnLoseFocus : true

		});

		$("#factory_nf").ligerComboBox( {
			url : 'station_queryAreablockInfo.action?orgid=factory',
			valueField : 'id',
			valueFieldID : 'hidfactoryid',
			textField : 'text',
			selectBoxHeight : 150,
			selectBoxWidth : 200,
			width : 120,
			autocomplete : true,
			hideOnLoseFocus : true

		});
		$.metadata.setType("attr", "validate");
		var v = $("form")
				.validate(
						{
							//调试状态，不会提交数据的
							debug : true,
							errorPlacement : function(lable, element) {

								if (element.hasClass("l-textarea")) {
									element.addClass("l-textarea-invalid");
								} else if (element.hasClass("l-text-field")) {
									element.parent().addClass("l-text-invalid");
								}
								$(element).removeAttr("title").ligerHideTip();
								$(element).attr("title", lable.html())
										.ligerTip();
							},
							success : function(lable) {
								if (lable.attr("for") != "") {
									var element = $("#" + lable.attr("for"));
									if (element.hasClass("l-textarea")) {
										element
												.removeClass("l-textarea-invalid");
									} else if (element.hasClass("l-text-field")) {
										element.parent().removeClass(
												"l-text-invalid");
									}
									$(element).removeAttr("title")
											.ligerHideTip();
								}

							},
							submitHandler : function() {
								//	var #grzh  = $("#grzh2").val();
							//	var grzh  = $("#grzh2").val();
							var grzhName = '';
							if ($("#grzh2").val() != '') {
								grzh = $("#grzh2id").val();
								grzhName = $("#grzh2").val();
							}
							var org_id = $("#org_id").val();
							var boilerid = $("#boilerid2").val();
							var boilername2 = $("#boilername2").val();
							var hidsqqk2id22 = '';
							//var sqqk2  = $("#sqqk").val(); //受汽区块
							if ($("#sqqk").val() != '') {
								hidsqqk2id22 = $("#hidsqqk2id").val();
							}
							var hidsqdw2id2 = '';
							//var sqdw2  = $("#sqdw").val();//受汽单位
							if ($("#sqdw").val() != '') {
								hidsqdw2id2 = $("#hidsqdw2id").val();//受汽单位
							}
						
							var jrbz='';
							if($("#jrbz").val() !=''){
								jrbz=$("#hidjrbzid").val();
								}
							var boilertype2 = $("#boilertype2").val();
							var boilercode2 = $("#boilercode2").val();
							//定额水量
							var rating_capacity = $("#rating_capacity").val();
							var injection_allocation_rate = $(
									"#injection_allocation_rate").val(); //配注量

							var status_value = $("#status_value").val();

						
							var ljjd_s = $("#ljjd_s").val();
							if (ljjd_s != '') {
								ljjd_s = $("#hidserverid").val();
							}
							var commissioning_date = $("#commissioning_date")
									.val();

							var sjcnn = $("#sjcnn").val();
							//厂家
							var factory_nf = '';
							if ($("#factory_nf").val() != '') {
								factory_nf = $("#hidfactoryid").val();
							}
							var remark2 = $("#remark2").val();
							if ($("#yxz2").val() != '') {
								yxz2 = $("#yxz2id").val();
							}
							var operFlag = false;
							//判断修改还是添加操作 1-添加、2-修改
							if (operate == 1) {

								jQuery
										.ajax( {
											type : 'post',
											url : 'boilerBasicInfo_saveOrUpdate.action',
											async : false,
											data : {
												"grzh" : grzh,
												"grzhName" : grzhName,
												"boilername2" : boilername2,
												"hidsqqk2id" : hidsqqk2id22,
												"hidsqdw2id" : hidsqdw2id2,
												"boilertype2" : boilertype2,
												"boilercode2" : boilercode2,
												"rating_capacity" : rating_capacity,
												"injection_allocation_rate" : injection_allocation_rate,
												"status_value" : status_value,
												"jrbz" : jrbz,
												"ljjd_s" : ljjd_s,
												"yxz2" : yxz2,
												"commissioning_date" : commissioning_date,
												"sjcnn":sjcnn,
												"factory_nf" : factory_nf,
												"remark2" : remark2
											},
											success : function(data) {
												if (data != null
														&& typeof (data) != "undefined") {
													var outData = eval('(' + data + ')');

													if (outData.ERRMSG != null
															&& typeof (outData.ERRMSG) != "undefined") {
														$.ligerDialog
																.error(outData.ERRMSG);
													} else if (outData.ERRCODE != null
															&& typeof (outData.ERRCODE) != "undefined") {
														$.ligerDialog
																.error(outerror(outData.ERRCODE));
													} else {
														onSubmit();
														setpage2(1); //隐藏编辑界面
														setItemsd(2); //去掉隐藏，显示按钮
														$.ligerDialog
																.success('锅炉添加成功！');
														operate = 0;

													}

												}
											},
											error : function(data) {

											}
										});

								//2-修改
							} else if (operate == 2) {
								jQuery
										.ajax( {
											type : 'post',
											url : 'boilerBasicInfo_saveOrUpdate.action',
											async : false,
											data : {
												"grzh" : grzh,
												"grzhName" : grzhName,
												"org_id" : org_id,
												"boilerid" : boilerid,
												"boilername2" : boilername2,
												"hidsqqk2id" : hidsqqk2id22,
												"hidsqdw2id" : hidsqdw2id2,
												"boilertype2" : boilertype2,
												"boilercode2" : boilercode2,
												"rating_capacity" : rating_capacity,
												"injection_allocation_rate" : injection_allocation_rate,
												"status_value" : status_value,
												"jrbz" : jrbz,
												"ljjd_s" : ljjd_s,
												"yxz2" : yxz2,
												"commissioning_date" : commissioning_date,
												"sjcnn":sjcnn,
												"factory_nf" : factory_nf,
												"remark2" : remark2
											},
											success : function(data) {
												if (data != null
														&& typeof (data) != "undefined") {
													var outData = eval('(' + data + ')');

													if (outData.ERRMSG != null
															&& typeof (outData.ERRMSG) != "undefined") {
														$.ligerDialog
																.error(outData.ERRMSG);
													} else if (outData.ERRCODE != null
															&& typeof (outData.ERRCODE) != "undefined") {
														$.ligerDialog
																.error(outerror(outData.ERRCODE));
													} else {
														onSubmit();
														setpage2(1); //隐藏编辑界面
														setItemsd(2); //去掉隐藏，显示按钮
														$.ligerDialog
																.success('锅炉修改成功！');
														operate = 0;

													}

												}
											},
											error : function(data) {

											}
										});

							}
						}
						});
		$("form").ligerForm();
		$(".l-button-test").click(function() {
		});
	});

	function fromAu(rowdata) {
		//用户选择修改数据
		boiler_name = rowdata.BOILER_NAME;
		org_id = rowdata.ORG_ID;
		boilerid = rowdata.BOILERID
		if (rowdata.YXZID != null && typeof (rowdata.YXZID) != "undefined") {
			yxzid = rowdata.YXZID; //运行注ID
		} else {
			yxzid = "";
		}

		if (rowdata.BLOCKSTATIONNAMEID != null
				&& typeof (rowdata.BLOCKSTATIONNAMEID) != "undefined") {
			gr_id = rowdata.BLOCKSTATIONNAMEID; //供热站ID
		} else {
			gr_id = "";
		}

		//grzh = rowdata.BLOCKSTATIONNAME;
		//yxz = rowdata.YXZ;

		if (rowdata.QKID != null && typeof (rowdata.QKID) != "undefined") {
			srqkid = rowdata.QKID; //受汽区块ID
		} else {
			srqkid = "";
		}

		if (rowdata.ACCEPT_UNIT != null
				&& typeof (rowdata.ACCEPT_UNIT) != "undefined") {
			accept_unit = rowdata.ACCEPT_UNIT; //受汽单位ID
		} else {
			accept_unit = "";
		}
		boilertype = rowdata.BOILER_TYPE; //锅炉类型
		if (rowdata.BOILER_CODE != null
				&& typeof (rowdata.BOILER_CODE) != "undefined") {
			boilercode = rowdata.BOILER_CODE; //锅炉编号
		} else {
			boilercode = "";
		}
		if (rowdata.RATING_CAPACITY != null
				&& typeof (rowdata.RATING_CAPACITY) != "undefined") {
			rating_capacity = rowdata.RATING_CAPACITY; //额定水量
		} else {
			rating_capacity = "";
		}

		if (rowdata.INJECTION_ALLOCATION_RATE != null
				&& typeof (rowdata.INJECTION_ALLOCATION_RATE) != "undefined") {
			injection_allocation_rate = rowdata.RATING_CAPACITY; //配注量
		} else {
			injection_allocation_rate = "";
		}

		if (rowdata.JRBZ != null && typeof (rowdata.JRBZ) != "undefined") {
			jrbz = rowdata.JRBZ;
		} else {
			jrbz = "";
		}

		if (rowdata.LJJDID != null && typeof (rowdata.LJJDID) != "undefined") {
			ljjd_s = rowdata.LJJDID;
		} else {
			ljjd_s = "";
		}
		if (rowdata.STATUS_VALUE != null
				&& typeof (rowdata.STATUS_VALUE) != "undefined") {
			status_value = rowdata.STATUS_VALUE;
		} else {
			status_value = "";
		}
		if (rowdata.COMMISSIONING_DATE != null
				&& typeof (rowdata.COMMISSIONING_DATE) != "undefined") {
			commissioning_date = rowdata.COMMISSIONING_DATE;
		} else {
			commissioning_date = "";
		}

		if (rowdata.DYEAR != null
				&& typeof (rowdata.DYEAR) != "undefined") {
			dyear = rowdata.DYEAR; //厂家ID
		} else {
			dyear = "";
		}
		
		if (rowdata.FACTORY_UC != null
				&& typeof (rowdata.FACTORY_UC) != "undefined") {
			factory_uc = rowdata.FACTORY_UC; //厂家ID
		} else {
			factory_uc = "";
		}

		if (rowdata.REMARK != null && typeof (rowdata.REMARK) != "undefined") {
			remark = rowdata.REMARK;
		} else {
			remark = "";
		}
		if (operate == 2) {
			assignM(2);
		}

	}

	function setAreablockData(data, proData) {
		jQuery.ajax( {
			type : 'post',
			url : 'boilerBasicInfo_queryAreablockInfo.action',
			data : {
				"orgid" : data
			},
			timeout : '3000',
			success : function(jsondata) {
				if (jsondata != null && typeof (jsondata) != "undefined"
						&& jsondata != 0) {
					liger.get("areablock").setData(eval('(' + jsondata + ')'));
				} else {
					liger.get("areablock").setData(proData);
				}
			},
			error : function(jsondata) {
			}
		});
	}

	function setStationData(areaid, proData, oilid, clearSelecteValue) {
		jQuery.ajax( {
			type : 'post',
			url : 'boilerBasicInfo_queryStationInfo.action',
			data : {
				"areaid" : areaid,
				"oilid" : oilid,
				"selecteValue" : clearSelecteValue
			},
			timeout : '3000',
			success : function(jsondata) {
				if (jsondata != null && typeof (jsondata) != "undefined"
						&& jsondata != 0) {
					if (clearSelecteValue == '4') {
						liger.get("grzh2").setData(eval('(' + jsondata + ')'));
					} else {
						liger.get("blockstationname").setData(
								eval('(' + jsondata + ')'));
					}
				} else {
					if (clearSelecteValue == '4') {
						liger.get("grzh2").setData(proData);
					} else {
						liger.get("blockstationname").setData(proData);
					}
				}
			},
			error : function(jsondata) {
			}
		});
	}

	function setBoilerData(data, proData) {
		jQuery.ajax( {
			type : 'post',
			url : 'boilerBasicInfo_queryBoilersNameInfo.action',
			data : {
				"orgid" : data
			},
			timeout : '3000',
			success : function(jsondata) {
				if (jsondata != null && typeof (jsondata) != "undefined"
						&& jsondata != 0) {
					liger.get("boilersName")
							.setData(eval('(' + jsondata + ')'));
				} else {
					liger.get("boilersName").setData(proData);
				}
			},
			error : function(jsondata) {
			}
		});
	}

	function getBoilerInitData() {
		var oilText = [ {
			id : 1,
			text : ''
		} ];
		//var areaText=[{ id: 1 , text: '' }];
		var stationText = [ {
			id : 1,
			text : ''
		} ];
		var boilerText = [ {
			id : 1,
			text : ''
		} ];
		jQuery.ajax( {
			type : 'post',
			url : 'boilerBasicInfo_cascadeInit.action',
			success : function(jsondata) {
				var dataObj = $.parseJSON(jsondata);
				$.each(dataObj, function(key, val) {
					if (key == 'oiltext') {
						oilText = val;
					}
					/* if (key == 'areatext'){
						areaText = val;
					} */
					if (key == 'stationtext') {
						stationText = val;
					}
					if (key == 'boilertext') {
						boilerText = val;
					}
				});
				setInitData(oilText, stationText, boilerText);
			}
		});
	}

	function setInitData(oilText, stationText, boilerText) {
		liger.get("oilationname").setData(oilText);
		//liger.get("areablock").setData(areaText);
		liger.get("blockstationname").setData(stationText);
		liger.get("boilersName").setData(boilerText);
	}

	//为公用页面提供接口方法 
	function onJKSubmit(id, name, datatype, basid) {
		//alert(id);
		//alert(name);
		//alert(datatype);
		var oilationname = '';
		var acceptunit = '';
		var areablock = '';
		var blockstationname = '';
		var ghname = '';
		var wellnum = '';

		//$("#areablock").ligerGetComboBoxManager().setValue('');
		getBoilerInitData();
		$("#oilationname").ligerGetComboBoxManager().setValue('');
		$("#boilersName").ligerGetComboBoxManager().setValue('');
		$("#blockstationname").ligerGetComboBoxManager().setValue('');
		$("#acceptunit").ligerGetComboBoxManager().setValue('');
		$("#areablock").ligerGetComboBoxManager().setValue('');
		$("#jrbz1").ligerGetComboBoxManager().setValue('2');
		document.getElementById("status_value1").value = "全部"; //额定水量
		if (datatype == '4') {
			oilationname = name;
			$("#oilationname").val(name);
		} else if (datatype == '7') {
			blockstationname = name;
			$("#blockstationname").val(name);
		}
		//else if(datatype=='14'){
		//	ghname=id;
		//}
		else if (datatype == '10') {
			wellnum = name;
			$("#boilersName").val(name);

		} else {
			alert("没有找到该节点");
			return;
		}

		grid.setOptions( {
			parms : [ {
				name : 'acceptunit',
				value : $("#acceptunit").val()
			}, {
				name : 'oilationname',
				value : $("#oilationname").val()
			}, {
				name : 'areablock',
				value : $("#areablock").val()
			}, {
				name : 'blockstationname',
				value : blockstationname
			}, {
				name : 'boilersName',
				value : wellnum
			} ]
		});
		grid.loadData(true);
	}

	function onSubmit() {
		grid.setOptions( {
			parms : [ {
				name : 'oilationname',
				value : $("#oilationname").val()
			}, {
				name : 'acceptunit',
				value : $("#acceptunit").val()
			}, {
				name : 'areablock',
				value : $("#areablock").val()
			}, {
				name : 'blockstationname',
				value : $("#blockstationname").val()
			}, {
				name : 'boilersName',
				value : $("#boilersName").val()
			}, {
				name : 'jrbz',
				value : $("#jrbz1").val()
			}, {
				name : 'status_value',
				value : $("#status_value1").val()
			}, {
				name : 'dyear',
				value : $("#dyear").val()
			} ]
		});
		grid.loadData(true);
	}
	var exportFlag = false;
	function onExport() {
		//oilationname  blockstationname  boilersName  jrbz1
		var Eoilationname = $("#acceptunit").val();
		var Eblockstationname = $("#blockstationname").val();
		var EboilersName = $("#boilersName").val();
		var Eareablock = $("#areablock").val();
		var Ejrbz1 = $("#jrbz1").val();
		var Estatus_value = $("#status_value1").val();
		var Edyear = $("#dyear").val();

		if (exportFlag) {
			Eoilationname = acceptunit;
			Eblockstationname = blockstationname;
			EboilersName = boilersName;
			Eareablock = areablock;
			Ejrbz1 = jrbz1;
			Estatus_value = status_value1;
			Edyear = dyear;
		}
		var totalNum = 0;

		var url = 'boilerBasicInfo_onExport.action?acceptunit='
				+ encodeURIComponent(Eoilationname) + '&blockstationname='
				+ encodeURIComponent(Eblockstationname) + '&areablock='
				+ encodeURIComponent(Eareablock) + '&jrbz1='
				+ encodeURIComponent(Ejrbz1) + '&status_value1='
				+ encodeURIComponent(Estatus_value)+ '&dyear='
				+ encodeURIComponent(Edyear) + '&totalNum=export'
				+ '&boilersName=' + encodeURIComponent(EboilersName);
		var urls = 'boilerBasicInfo_onExport.action?acceptunit='
				+ encodeURIComponent(Eoilationname) + '&blockstationname='
				+ encodeURIComponent(Eblockstationname) + '&areablock='
				+ encodeURIComponent(Eareablock) + '&jrbz1='
				+ encodeURIComponent(Ejrbz1) + '&status_value1='
				+ encodeURIComponent(Estatus_value)+ '&dyear='
				+ encodeURIComponent(Edyear)  + '&totalNum=totalNum'
				+ '&boilersName=' + encodeURIComponent(EboilersName);
		$.ajax( {
			type : 'post',
			url : urls,
			async : false,
			timeout : '30000',
			success : function(data) {
				totalNum = data;
			}
		});
		if (totalNum < 10000 && totalNum > 0) {
			$.ligerDialog
					.confirm(
							'确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',
							function(yes) {
								if (yes)
									window.location.href = url
							});
		} else if (totalNum > 10000) {
			$.ligerDialog
					.confirm(
							'确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',
							function(yes) {
								if (yes)
									window.location.href = url
							});
		} else {
			$.ligerDialog.confirm('没有可导出的数据！');
		}
	}
	function assignM(flg) {

		if (flg == 2) {
			document.getElementById("boilername2").value = boiler_name;
			$("#yxz2").ligerGetComboBoxManager().selectValue(yxzid);
			$("#grzh2").ligerGetComboBoxManager().selectValue(gr_id);
			//document.getElementById("grzh2").value = grzh2;
			document.getElementById("boilerid2").value = boilerid;
			$("#sqqk").ligerGetComboBoxManager().selectValue(srqkid); //受汽区块ID
			$("#sqdw").ligerGetComboBoxManager().selectValue(accept_unit); //受汽单位ID

			if (boilertype == '湿蒸') {
				$("#boilertype2").ligerGetComboBoxManager().selectValue(1);
			} else if (boilertype == '过热') {
				$("#boilertype2").ligerGetComboBoxManager().selectValue(2);
			} else if (boilertype == '高干度') {
				$("#boilertype2").ligerGetComboBoxManager().selectValue(3);
			} else if (boilertype == '燃煤') {
				$("#boilertype2").ligerGetComboBoxManager().selectValue(4);
			} else {
				$("#boilertype2").ligerGetComboBoxManager().selectValue(
						boilertype);
			}
			//document.getElementById("boilertype2").value= boilertype2;
			document.getElementById("boilercode2").value = boilercode; //锅炉编号

			document.getElementById("rating_capacity").value = rating_capacity; //额定水量
			document.getElementById("injection_allocation_rate").value = injection_allocation_rate; //配注量

			document.getElementById("commissioning_date").value = commissioning_date; //投产日期
			$("#ljjd_s").ligerGetComboBoxManager().selectValue(ljjd_s);

			var ac = $("#jrbz").ligerGetComboBoxManager().findValueByText(jrbz);
           		$("#jrbz").ligerGetComboBoxManager().selectValue(ac);
		
			
			if (status_value != null && typeof (status_value) != "undefined"
					&& status_value != '') {
				$("#status_value").ligerGetComboBoxManager().selectValue(
						status_value);
			} else {
				document.getElementById("status_value").value = status_value;
			}
			$("#factory_nf").ligerGetComboBoxManager().selectValue(factory_uc);//厂家ID
			document.getElementById("org_id").value = org_id;
			document.getElementById("remark2").value = remark;

			document.getElementById("sjcnn").value = dyear;
		} else if (flg == 1) {
			document.getElementById("boilername2").value = "";
			$("#yxz2").ligerGetComboBoxManager().selectValue("");
			$("#grzh2").ligerGetComboBoxManager().selectValue("");
			document.getElementById("boilerid2").value = "";
			$("#sqqk").ligerGetComboBoxManager().selectValue(""); //受汽区块ID
			$("#sqdw").ligerGetComboBoxManager().selectValue(""); //受汽单位ID

			$("#boilertype2").ligerGetComboBoxManager().selectValue("");
			document.getElementById("boilercode2").value = ""; //锅炉编号

			document.getElementById("rating_capacity").value = ""; //额定水量
			document.getElementById("injection_allocation_rate").value = ""; //配注量

			document.getElementById("commissioning_date").value = ""; //投产日期
			$("#ljjd_s").ligerGetComboBoxManager().selectValue("");
			$("#jrbz").ligerGetComboBoxManager().selectValue("");

			//$("#status_value").ligerGetComboBoxManager().selectValue("");
			document.getElementById("status_value").value = "";
			$("#factory_nf").ligerGetComboBoxManager().selectValue("");//厂家ID
			document.getElementById("org_id").value = "";
			document.getElementById("remark2").value = "";
			document.getElementById("sjcnn").value = "";
		}

	}
	//工具条事件
	function itemclick(item) {
		var rows = grid.getCheckedRows();
		switch (item.icon) {
		case "add":

			if (operate != 1 && operate != 2) {
				setpage2(0); //显示编辑界面
				setItemsd(0);//0-显示编辑区，添加隐藏按钮
			}
			operate = 1;
			assignM(1);

			break;
		case "modify":
			if (rows.length == 0) {
				alert('请选择一条你要修改信息的数据！');

			} else if (rows.length == 1) {

				if (operate != 1 && operate != 2) {
					setpage2(0); //显示编辑界面
					setItemsd(0);//0-显示编辑区，添加隐藏按钮
				}
				operate = 2;
				assignM(2);

			} else {
				alert('请选择一条你要修改信息的数据！');
			}
			break;
		case "delete":
			if (rows.length == 0) {
				alert('请选择一条你要删除的数据！')
			} else if (rows.length == 1) {
				$.ligerDialog
						.confirm(
								'确定删除吗?',
								function(yes) {
									if (yes) {
										jQuery
												.ajax( {
													type : 'post',
													url : 'boilerBasicInfo_removeBoilerBasicInfo.action',
													async : false,
													data : {
														"boilerid" : boilerid,
														"org_id" : org_id
													},
													success : function(data) {
														if (data != null
																&& typeof (data) != "undefined") {
															var outData = eval('(' + data + ')');
															if (outData.ERRCODE != null
																	&& typeof (outData.ERRCODE) != "undefined") {
																$.ligerDialog
																		.error(outerror(outData.ERRCODE));
															} else if (outData.ERRMSG != null
																	&& typeof (outData.ERRMSG) != "undefined") {
																$.ligerDialog
																		.error(outData.ERRMSG);
															} else {

																onSubmit();
																//assignM(1);
																$.ligerDialog
																		.success('锅炉基础删除成功！');
															}

														}
													},
													error : function(data) {

													}
												});
									}
								});

			} else {
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
html,body {
	font-size: 12px;
	margin: 0px;
	padding: 0px;
	background: #FAFCFC;
	position: absolute;
	width: 100%;
	height: 100%;
	hoverflow: scroll;
	overflow-y: hidden;
	overflow-x: hidden;
}

/* 搜索框 */
.searchtitle {
	padding-left: 28px;
	position: relative;
}

.searchtitle img {
	width: 22px;
	height: 22px;
	position: absolute;
	left: 0;
	top: 0;
}

.searchtitle span {
	font-size: 14px;
	font-weight: bold;
}

.searchtitle .togglebtn {
	position: absolute;
	top: 6px;
	right: 15px;
	background: url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px
		0px;
	height: 10px;
	width: 9px;
	cursor: pointer;
}

.searchtitle .togglebtn-down {
	background-position: 0px -10px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 100px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

#errorLabelContainer {
	padding: 10px;
	width: 300px;
	border: 1px solid #FF4466;
	display: none;
	background: #FFEEEE;
	color: Red;
}
</style>

	</head>

	<body style="overflow-x: hidden; padding: 2px;">
		<div id="mainsearch" style="width: 99%">

			<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

			<form name="formsearch" method="post" id="form1">
				<table border="0" cellspacing="1">
					<tr>
						<td align="right" class="l-table-edit-td"
							style="width: 60px; display: none">
							采油站：
						</td>
						<td align="right" class="l-table-edit-td"
							style="width: 80px; display: none">
							<input type="text" id="oilationname" name="oilationname" />
						</td>
						<td align="right" class="l-table-edit-td" style="width: 60px;">
							受汽区块：
						</td>
						<td align="right" class="l-table-edit-td" style="width: 80p;">
							<input type="text" id="areablock" name="areablock" />
						</td>
						<td align="right" class="l-table-edit-td" style="width: 60px">
							供热站：
						</td>
						<td align="right" class="l-table-edit-td" style="width: 80px">
							<input type="text" id="blockstationname" name="blockstationname" />
						</td>
						<td align="left" style="width: 10px">
						</td>
						<td align="right" class="l-table-edit-td" style="width: 60px">
							锅炉名称：
						</td>
						<td align="left" class="l-table-edit-td" style="width: 80px">
							<input type="text" id="boilersName" name="boilersName" />
						</td>
						<td align="right" class="l-table-edit-td"
							style="width: 60px; display: none">
							运行组：
						</td>
						<td align="left" class="l-table-edit-td"
							style="width: 80px; display: none">
							<input type="text" id="group" name="group" />
						</td>

						<td align="right" class="l-table-edit-td" style="width: 60px">
							受汽单位：
						</td>
						<td align="left" class="l-table-edit-td" style="width: 80px">
							<input type="text" id="acceptunit" name="acceptunit" />
						</td>

					</tr>
					<tr>
						<td align="right" class="l-table-edit-td" style="width: 60px">
							接入状态：
						</td>
						<td align="right" class="l-table-edit-td" style="width: 80px">
							<input type="text" id="jrbz1" name="jrbz1" />
						</td>
						<td align="right" class="l-table-edit-td" style="width: 60px;">
							建设状态：
						</td>
						<td align="right" class="l-table-edit-td" style="width: 80p;">
							<input type="text" id="status_value1" name="status_value1" />
						</td>
						<td align="left" style="width: 10px">
						</td>
						<td align="center" class="l-table-edit-td" style="width:80px">设计产能年：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="dyear" name="dyear"/>
		                </td>
						<td align="right" class="l-table-edit-td" style="width: 20px"></td>
						<td align="right" class="l-table-edit-td" style="width: 20px"></td>
						<td align="left" class="l-table-edit-td" style="width: 100px">
							<a href="javascript:onSubmit()" class="l-button"
								style="width: 100px">查询</a>
						</td>

						<td align="right" class="l-table-edit-td" style="width: 20px"></td>
						<td align="left" class="l-table-edit-td" style="width: 100px">
							<a href="javascript:onExport()" class="l-button"
								style="width: 100px">导出报表</a>
						</td>

					</tr>

				</table>
				<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

				<div id="maingrid"></div>

				<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

				<div id="edituser">
					<div id="errorLabelContainer">
					</div>
					<table cellpadding="0" cellspacing="0" class="l-table-edit"
						border="0">
						<tr>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								锅炉名称:
							</td>

							<td align="left" class="l-table-edit-td" style="width: 150px">
								运行组:
							</td>

							<td align="left" class="l-table-edit-td" style="width: 150px">
								供热站:
							</td>

							<td align="left" class="l-table-edit-td" style="width: 150px">
								锅炉编号:
							</td>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								锅炉类型:
							</td>

							<td align="left" class="l-table-edit-td" style="width: 150px">
								额定水量:
							</td>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								配注量:
							</td>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								受汽区块:
							</td>
						</tr>
						<tr>
							<td align="left" class="l-table-edit-td" style="">
								<input name="boilername2" type="text" id="boilername2"
									ltype="text"
									validate="{required:true,minlength:1,maxlength:20}" />
							</td>

							<td align="left" class="l-table-edit-td" style="">
								<input name="yxz2" type="text" id="yxz2" ltype="text" />
							</td>


							<td align="left" class="l-table-edit-td" style="">
								<input name="grzh2" type="text" id="grzh2" ltype="text"
									validate="{required:true,minlength:1,maxlength:20}" />
							</td>

							<td align="left" class="l-table-edit-td" style="">
								<input name="boilercode2" type="text" id="boilercode2"
									ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td" style="">
								<input name="boilertype2" type="text" id="boilertype2"
									ltype="text" validate="{required:true}" />
							</td>


							<td align="left" class="l-table-edit-td">
								<input name="rating_capacity" id="rating_capacity"
									ltype="number" />
							</td>
							<td align="left" class="l-table-edit-td">
								<input name="injection_allocation_rate"
									id="injection_allocation_rate" ltype="number" />
							</td>
							<td align="left" class="l-table-edit-td" style="">
								<input name="sqqk" type="text" id="sqqk" ltype="text" />
							</td>
						</tr>
						<tr>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								受汽单位:
							</td>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								建设生产状态:
							</td>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								投产日期:
							</td>
							<td align="left" class="l-table-edit-td" style="width:150px">产能设计年:</td>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								厂家:
							</td>
							<td align="left" class="l-table-edit-td" style="width: 150px">
								接入标志:
							</td>

							<td align="left" class="l-table-edit-td" style="width: 150px">
								服务器逻辑节点名:
							</td>


							<td align="left" class="l-table-edit-td" style="width: 150px">
								备注:
							</td>

						</tr>
						<tr>
							<td align="left" class="l-table-edit-td" style="">
								<input name="sqdw" type="text" id="sqdw" ltype="text" />
							</td>

							<td align="left" class="l-table-edit-td">
								<input name="status_value" type="text" id="status_value"
									ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td">
								<input name="commissioning_date" type="text"
									id="commissioning_date" ltype="date" />
							</td>
							<td align="left" class="l-table-edit-td" >
		                	<input name="sjcnn" type="text" id="sjcnn" ltype="text" />
		               		 </td>
							<td align="left" class="l-table-edit-td">
								<input name="factory_nf" type="text" id="factory_nf"
									ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td" style="">
								<input name="jrbz" type="text" id="jrbz" ltype="text"
									validate="{required:true,minlength:1,maxlength:20}" />
							</td>
							<td align="left" class="l-table-edit-td">
								<input name="ljjd_s" type="text" id="ljjd_s" ltype="text" />
							</td>




							<td align="left" class="l-table-edit-td" style="">
								<input name="remark2" type="text" id="remark2" ltype="text" />
							</td>
							<td align="left" class="l-table-edit-td" style="">
								<input name="boilerid2" type="hidden" id="boilerid2" />
								<input name="org_id" type="hidden" id="org_id" />
							</td>
						</tr>

						<tr>
							<td>
								<br />
							</td>
						</tr>

					</table>
				</div>
			</form>

		</div>

	</body>
</html>