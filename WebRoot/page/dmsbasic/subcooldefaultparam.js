var min_subcool;
var max_subcool;

$(function () {
	min_subcool = $("#min_subcool").ligerTextBox();
	max_subcool = $("#max_subcool").ligerTextBox();
	
	jQuery.ajax({
		type : 'post',
		url : 'subcool_defaultParamInit.action',
		success : function(jsondata) {
			if (jsondata != null && typeof(jsondata) != "undefined"){
				if (jsondata == '-16001') {
					$('input[name="cal"][value="1"]').attr('checked', true);
					min_subcool.setValue(5);
					max_subcool.setValue(15);
					$('input[name="flow"][value="3"]').attr('checked', true);
				} else {
					var data = eval('(' + jsondata + ')');
					$('input[name="cal"][value="' + data['cal'] + '"]').attr('checked', true);
					min_subcool.setValue(data['min_subcool']);
					max_subcool.setValue(data['max_subcool']);
					$('input[name="flow"][value="' + data['flow'] + '"]').attr('checked', true);
				}
			}
		},
		error : function(data) {
			$.ligerDialog.question("初始化默认参数失败！");
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
			var cal = $('input[name="cal"]:checked').val();
			var max = max_subcool.getValue();
			var min = min_subcool.getValue();
			var flow = $('input[name="flow"]:checked').val();
			jQuery.ajax({
				type : 'post',
				url : 'subcool_setDefaultParam.action',
				data : {
					"cal" : cal,
					"max_subcool" : max,
					"min_subcool" : min,
					"flow" : flow
				},
				async : false,
				success : function(jsondata) {
					$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
					if (jsondata != null && typeof(jsondata)!="undefined" && "1" == jsondata){
						$.ligerDialog.success('设置默认参数成功！');
					} else {
						$.ligerDialog.error(outerror(jsondata));
					}
				},
				error : function(data) {
			
				}
			});
		}
	});
	
});

function onSubmit() {
	$("#form").submit();
}