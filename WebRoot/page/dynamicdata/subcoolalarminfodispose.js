$(function () {
	var sagdid = $("#sagdid").val();
	
	jQuery.ajax({
		type : 'post',
		url : 'subcool_getAlarmInfo.action',
		data: {"sagdid" : sagdid},
		success : function(jsondata) {
			if (jsondata != null && typeof(jsondata)!="undefined"){
				var data = eval('(' + jsondata + ')');
				fillData(data);
			}else{
				$.ligerDialog.error(outerror(jsondata));
			}
		},
		error : function(data) {
			$.ligerDialog.error("获取数据失败！");
		}
	});

	$('input[name="mode"]').click(function () {
		if($(this).val() == 1) {
			$("#modifyflow").attr("disabled", true);
			$("#modifyjig").attr("disabled", true);
			$("#modifynip").attr("disabled", true);
			$("#modifyflow").val($("#sugflow").text());
			$("#modifyjig").val($("#sugjig").text());
			$("#modifynip").val($("#sugnip").text());
		}else{
			$("#modifyflow").attr("disabled", false);
			$("#modifyjig").attr("disabled", false);
			$("#modifynip").attr("disabled", false);
			$("#modifyflow").val($("#sugflow").text());
			$("#modifyjig").val($("#sugjig").text());
			$("#modifynip").val($("#sugnip").text());
		}
	});
});

function fillData(data) {
	$("#areablock").text(data.AREABLOCK);
	$("#oilstationname").text(data.OILSTATIONNAME);
	$("#blockstationname").text(data.BLOCKSTATIONNAME);
	$("#wellnum").text(data.JHMC);
	$("#prodstages").text(data.PRODSTAGES);
	$("#p_temp1").text(data.P_TEMP1);
	$("#p_temp2").text(data.P_TEMP2);
	$("#p_temp3").text(data.P_TEMP3);
	$("#p_temp4").text(data.P_TEMP4);
	$("#p_temp5").text(data.P_TEMP5);
	$("#p_temp6").text(data.P_TEMP6);
	$("#p_temp7").text(data.P_TEMP7);
	$("#p_temp8").text(data.P_TEMP8);
	$("#p_temp9").text(data.P_TEMP9);
	$("#p_temp10").text(data.P_TEMP10);
	$("#p_temp11").text(data.P_TEMP11);
	$("#p_temp12").text(data.P_TEMP12);
	$("#daily_cumulative_steam").text(data.DAILY_CUMULATIVE_STEAM);
	$("#p_daily_output").text(data.P_DAILY_OUTPUT);
	$("#curflow").text(data.CURFLOW);
	$("#curjig").text(data.CURJIG);
	$("#curnip").text(data.CURNIP);
	$("#sugflow").text(data.SUGFLOW);
	$("#sugjig").text(data.SUGJIG);
	$("#sugnip").text(data.SUGNIP);
	
	if($('input[name="mode"]:checked').val() == 2) {
		$("#modifyflow").attr("disabled", true);
		$("#modifyjig").attr("disabled", true);
		$("#modifynip").attr("disabled", true);
		$("#modifyflow").val(data.SUGFLOW);
		$("#modifyjig").val(data.SUGJIG);
		$("#modifynip").val(data.SUGNIP);
	}
}