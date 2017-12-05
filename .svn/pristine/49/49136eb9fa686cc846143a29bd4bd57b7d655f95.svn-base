(function ($)
{
    $.validator.addMethod(
            "notnull",
            function (value, element)
            {
                if (!value) return true;
                return !$(element).hasClass("l-text-field-null");
            },
            "不能为空"
    );

    //字母数字
    jQuery.validator.addMethod("alnum", function (value, element)
    {
        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
    }, "只能包括英文字母和数字");

    // 手机号码验证   
    jQuery.validator.addMethod("cellphone", function (value, element)
    {
        var length = value.length;
        return this.optional(element) || (length == 11 && /^(1\d{10})$/.test(value));
    }, "请正确填写手机号码");

    // 电话号码验证   
    jQuery.validator.addMethod("telephone", function (value, element)
    {
        var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写电话号码");

    // 邮政编码验证
    jQuery.validator.addMethod("zipcode", function (value, element)
    {
        var tel = /^[0-9]{6}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写邮政编码");

    // 汉字
    jQuery.validator.addMethod("chcharacter", function (value, element)
    {
        var tel = /^[\u4e00-\u9fa5]+$/;
        return this.optional(element) || (tel.test(value));
    }, "请输入汉字");



    // QQ
    jQuery.validator.addMethod("qq", function (value, element)
    {
        var tel = /^[1-9][0-9]{4,}$/;
        return this.optional(element) || (tel.test(value));
    }, "请输入正确的QQ");

    // 用户名
    jQuery.validator.addMethod("username", function (value, element)
    {
        return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]+$/.test(value);
    }, "用户名格式不正确");
    
    //两个输入框计较大小
    $.validator.addMethod("compare",function(value,element,params) {
		return parseInt(value) > parseInt($(params).val());
	}, "您所输入的最大值不能小于最小值！");
    
    //比较输入框时间大小
    $.validator.addMethod("compDate",function(value,element,params) {
    	alert(value)
    	var start = new Date($(params).val().replace(/\-/g, "\/"));
    	var end = new Date(value.replace(/\-/g, "\/"));
		return start >= end;
	}, "您所输入的结束时间不能小于开始时间！");
    
})(jQuery);