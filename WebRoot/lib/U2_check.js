/**
 * 
 */
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, //月份 
		"d+" : this.getDate(), //日 
		"h+" : this.getHours(), //小时 
		"m+" : this.getMinutes(), //分 
		"s+" : this.getSeconds(), //秒 
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
		"S" : this.getMilliseconds()
	//毫秒 
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
/** 当前系统日期字符串 **/
/*function setAduitByDate(startDate,endDate) {
	var strDate1 = new Date().Format("yyyy-MM-dd");
	$.ajax({
		async : false,
		type : 'post',
		url : 'scljy_searchFlagDate.action?nowdata=' + parseInt(Math.random() * 100000),
		data : { "reportDate" : strDate1 },
		success : function(data) {
			if (data != null && typeof (data) != "undefined" && data != -1) {
				startDate = parseInt(data.split('&')[1].replace(/[^\d]/g,''));
				endDate = parseInt(parseInt(strDate1.replace(/[^\d]/g,''))+1+data.split('&')[1].replace(/[^\d]/g,'').slice(8));
			}
		},
		error : function(data) {
//			alert('out');
		}
	});
	alert(startDate);
	if (startDate!=0) {
		return true;
	} else {
		return false;
	}
}*/

var setAduitByDate = (function() {
	var strDate1 = new Date().Format("yyyy-MM-dd");
	var result=0;
	$.ajax({
//		dataType : 'json',
		async : false,
		type : 'post',
		url : 'scljy_searchFlagDate.action?nowdata='+ parseInt(Math.random() * 100000),
		data : {"reportDate" : strDate1},
		success : function(data) {
			result = data;
		}
	});
	return result;
})();

/** 当前时间在当天规定时间内到第二天规定时间内允许审核 例如：当天为14号 现在时间在14号8点-15号8点之间 * */
function chekAduitByDate(reportDate) {
	var strDate1 = new Date().Format("yyyy-MM-dd");
	var startDate = 0;
	var endDate = 0;
	if (setAduitByDate.length) {
		startDate = parseInt(setAduitByDate.split('&')[1].replace(/[^\d]/g,''));
		endDate = parseInt(parseInt(strDate1.replace(/[^\d]/g,''))+1+setAduitByDate.split('&')[1].replace(/[^\d]/g,'').slice(8));
		var strDate2 = new Date().Format("yyyy-MM-dd hh:mm:ss");
		var date2=strDate2.replace(/[^\d]/g,'');
		//查询数据日期等于当前日期且当前时间在允许审核的时间范围内
		if ((strDate1.replace(/[^\d]/g,'') == reportDate.replace(/[^\d]/g,'')) && date2 > startDate && date2 < endDate) {
			return true;
		}
		return false;
	}
}

/** 查询日期没有超过当前日期2天可查询 导出**/
function onSearchByDate(reportDate) {
	var strDate1 = new Date().Format("yyyy-MM-dd");
	var date1=strDate1.replace(/[^\d]/g,'');
	var date2=reportDate.replace(/[^\d]/g,'');
	var flagDate = date2 - date1;
	if (flagDate > 1) {
		return false;
	}
	return true;
}

/** 修改 **/
function modifyDataFlag(reportDate) {
	var strDate1 = new Date().Format("yyyy-MM-dd");
	//var date1=strDate1.replace(/[^\d]/g,'');
	//var date2=reportDate.replace(/[^\d]/g,'');
	//var flagDate = date2 - date1;
	//if (flagDate > 1 || flagDate < 0) {
	//	return false;
	//}
	var date1 = new Date(Date.parse(strDate1.replace(/-/g, "/")));
    var date2 = new Date(Date.parse(reportDate.replace(/-/g, "/")));
    var datetimeTemp;
    var isLater = true;

    if (date1.getTime() > date2.getTime()) {
        //isLater = false;
        //datetimeTemp = date1;
        //date1 = date2;
        //date2 = datetimeTemp;
    	return false;
    }

    difference = date2.getTime() - date1.getTime();
    thisdays = Math.floor(difference / (1000 * 60 * 60 * 24));
    
    if (thisdays > 1 || thisdays < 0) {
		return false;
	}

    //difference = difference - thisdays * (1000 * 60 * 60 * 24);
    //thishours = Math.floor(difference / (1000 * 60 * 60));


   // var strRet = thisdays + '天' + thishours + '小时';
	
	
	
	return true;
}

/** 日期比较 **//*
function compareDate(strDate1, strDate2) {
	var date1 = new Date(strDate1.replace(/\-/g, "\/"));
	var date2 = new Date(strDate2.replace(/\-/g, "\/"));
	return date1 - date2;
}

 *//** 比较 **//*
function doCompare() {
	var strDate1 = document.getElementById("strDate1").value;
	var strDate2 = document.getElementById("strDate2").value;
	var result = compareDate(strDate1, strDate2);
	if (result > 0) {
		alert("strDate1晚于strDate2");
	} else if (result < 0) {
		alert("strDate1早于strDate2");
	} else if (result == 0) {
		alert("strDate1等于strDate2");
	}
}*/