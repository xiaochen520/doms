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
		<title>锅炉房气体监测数据</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0"></meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
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
		<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerGrid.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerToolBar.js"
			type="text/javascript"></script>
		<script src="../../lib/ligerUI/js/plugins/ligerResizable.js"
			type="text/javascript"></script>
		<link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />
		<script src="../../lib/js/common.js" type="text/javascript"></script>
		<script src="../../lib/json2.js" type="text/javascript"></script>
		<script src="../../noBackspace.js" type="text/javascript"></script>
		<script src="../../lib/myday.js" type="text/javascript"></script>
		<script type="text/javascript">
	var eee;
	var grid = null;
	var toolbar;

	//鼠标选择数据
	var blockstationid; //注转站ID
	var blockstation_name; //注转站名称
	var bs_type; //注转站类型

	$(function() {
		//获取报表及功能按钮ｊｓ
		jQuery.ajax( {
			type : 'post',
			url : 'boilerGasMonitor_pageInit.action',
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

		$("#blockstationname").ligerComboBox( {//转注站
					url : 'station_queryStationInfo.action',
					valueField : 'id',
					valueFieldID : 'stationid',
					textField : 'text',
					selectBoxHeight : 400,
					selectBoxWidth : 140,
					hideOnLoseFocus : true,
					autocomplete : true,
					onBeforeSelect : function(newvalue) {
						liger.get("blockstationtype").setValue('');
					},
					onSelected : function(data) {
					}
				});

		$("#txtDate").ligerDateEditor( {
			format : "yyyy-MM-dd hh:mm",
			labelWidth : 100,
			showTime : true,
			labelAlign : 'center',
			cancelable : false
		}).setValue(myDate().Format("yyyy-MM-dd hh:mm"));

		$("#txtDate1").ligerDateEditor( {
			format : "yyyy-MM-dd hh:mm",
			labelWidth : 100,
			showTime : true,
			labelAlign : 'center',
			cancelable : false
		}).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));

		$.metadata.setType("attr", "validate");
		var v = $("form").validate( {
			//调试状态，不会提交数据的
			debug : true,
			errorPlacement : function(lable, element) {

				if (element.hasClass("l-textarea")) {
					element.addClass("l-textarea-invalid");
				} else if (element.hasClass("l-text-field")) {
					element.parent().addClass("l-text-invalid");
				}
				$(element).removeAttr("title").ligerHideTip();
				$(element).attr("title", lable.html()).ligerTip();
			},
			success : function(lable) {
				if (lable.attr("for") != "") {
					var element = $("#" + lable.attr("for"));
					if (element.hasClass("l-textarea")) {
						element.removeClass("l-textarea-invalid");
					} else if (element.hasClass("l-text-field")) {
						element.parent().removeClass("l-text-invalid");
					}
					$(element).removeAttr("title").ligerHideTip();
				}

			},
			submitHandler : function() {

			}
		});
		$("form").ligerForm();

		$(".l-button-test").click(function() {
		});
	});

	function setAreablockData(data, proData) {
		jQuery.ajax( {
			type : 'post',
			url : 'boilerGasMonitor_queryAreablockInfo.action',
			data : {
				"orgid" : data
			},
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

	function setGroupData(data, proData) {
		jQuery.ajax( {
			type : 'post',
			url : 'boilerBasicInfo_searchGroupInfo.action',
			data : {
				"arg" : data
			},
			success : function(jsondata) {
				if (jsondata != null && typeof (jsondata) != "undefined"
						&& jsondata != 0) {
					liger.get("yxz2").setData(eval('(' + jsondata + ')'));
					$("#yxz2").ligerGetComboBoxManager().selectValue(yxzid);
				} else {
					liger.get("yxz2").setData(proData);
				}
			},
			error : function(jsondata) {
				//alert("请求数据失败，请重新查询");
		}
		});
	}

	function setStationData(combinationid, oilid, areaid, proData,
			clearSelecteValue) {
		jQuery.ajax( {
			type : 'post',
			url : 'boilerGasMonitor_queryStationInfo.action',
			data : {
				"combinationid" : combinationid,
				"areaid" : areaid,
				"oilid" : oilid,
				"selecteValue" : clearSelecteValue,
				"views" : 'sation'
			},
			success : function(jsondata) {
				if (jsondata != null && typeof (jsondata) != "undefined"
						&& jsondata != 0) {
					liger.get("blockstationname").setData(
							eval('(' + jsondata + ')'));
				} else {
					liger.get("blockstationname").setData(proData);
				}
			},
			error : function(jsondata) {
				//alert("请求数据失败，请重新查询");
		}
		});
		return clearSelecteValue;
	}

	function getBoilerInitData() {
		var oilText = [ {
			id : 1,
			text : ''
		} ];
		var areaText = [ {
			id : 1,
			text : ''
		} ];
		var stationText = [ {
			id : 1,
			text : ''
		} ];
		jQuery.ajax( {
			type : 'post',
			url : 'boilerGasMonitor_cascadeInit.action',
			success : function(jsondata) {
				var dataObj = $.parseJSON(jsondata);
				$.each(dataObj, function(key, val) {
					if (key == 'oiltext') {
						oilText = val;
					}
					if (key == 'areatext') {
						areaText = val;
					}
					if (key == 'stationtext') {
						stationText = val;
					}
				});
				setInitData(oilText, areaText, stationText);
			}
		});
	}

	function setInitData(oilText, areaText, stationText) {
		liger.get("oilstationname").setData(oilText);
		liger.get("areablock").setData(areaText);
		liger.get("blockstationname").setData(stationText);
	}
	function fromAu(rowdata) {
	}

	//为公用页面提供接口方法 
	function onJKSubmit(id, name, datatype, basid) {
		var oilstationname = '全部';
		var areablock = '';
		var blockstationname = '';
		$("#oilstationname").ligerGetComboBoxManager().setValue('');
		$("#areablock").ligerGetComboBoxManager().setValue('');
		$("#blockstationname").ligerGetComboBoxManager().setValue('');
		getBoilerInitData();
		if (datatype == '4') {
			oilstationname = name;
			$("#oilstationname").val(name);
		}
		if (datatype == '7') {
			blockstationname = name;
			$("#blockstationname").val(name);
		}

		grid.setOptions( {
			parms : [ {
				name : 'oilstationname',
				value : $("#oilstationname").val()
			}, {
				name : 'blockstationname',
				value : $("#blockstationname").val()
			}, {
				name : 'areablock',
				value : $("#areablock").val()
			}, {
				name : 'date',
				value : $("#txtDate").val()
			}, {
				name : 'date1',
				value : $("#txtDate1").val()
			} ]
		});
		grid.loadData(true);
	}
	function onSubmit() {
		var start = $("#txtDate").val();
		var end = $("#txtDate1").val();
		if (typeof (start) == "undefined" || start == "") {
			$.ligerDialog.question("开始时间不能为空");
			return;
		}
		if (typeof (end) == "undefined" || end == "") {
			$.ligerDialog.question("结束时间不能为空");
			return;
		}
		if (new Date(start.replace(/\-/g, "\/")) > new Date(end.replace(/\-/g,
				"\/"))) {
			$.ligerDialog.question("结束时间必须大于开始时间！");
			return;
		}
		grid.setOptions( {
			parms : [ {
				name : 'oilstationname',
				value : $("#oilstationname").val()
			}, {
				name : 'areablock',
				value : $("#areablock").val()
			}, {
				name : 'blockstationname',
				value : $("#blockstationname").val()
			}, {
				name : 'date',
				value : $("#txtDate").val()
			}, {
				name : 'date1',
				value : $("#txtDate1").val()
			} ]
		});
		grid.loadData(true);
	}
	//工具条事件
	function itemclick(item) {
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

						<td align="right" class="l-table-edit-td" style="width: 60px">
							注转站：
						</td>
						<td align="left" class="l-table-edit-td">
							<input type="text" id="blockstationname" name="blockstationname" />
						</td>
						<td align="left">
						</td>

						<td align="right" class="l-table-edit-td"
							style="width: 60px; display: none"">
							注汽单位：
						</td>
						<td align="left" class="l-table-edit-td;"
							style="width: 60px; display: none">
							<input type="text" id="combination" name="combination" />
						</td>
						<td align="left">
						</td>
						<td align="right" class="l-table-edit-td"
							style="width: 100px; display: none"">
							注转站类型：
						</td>
						<td align="left" class="l-table-edit-td;"
							style="width: 100px; display: none">
							<input type="text" id="blockstationtype" name="blockstationtype" />
						</td>
						<td align="left">
						</td>
						<td align="right" class="l-table-edit-td" style="width: 40px">
							日期：
						</td>
						<td>
							<input type="text" id="txtDate" ltype="date" />
						</td>
						<td valign="middle">
							--
						</td>
						<td>
							<input type="text" id="txtDate1" ltype="date" />
						</td>
						<td align="right" class="l-table-edit-td" style="width: 20px"></td>
						<td align="left" class="l-table-edit-td">
							<a href="javascript:onSubmit()" class="l-button"
								style="width: 100px">查询</a>
						</td>
					</tr>

				</table>
				<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>

				<div id="maingrid"></div>



			</form>

		</div>

	</body>
</html>