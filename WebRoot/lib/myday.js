﻿	var today = new Date();
	var myDate = function getDate() {
		today.Format("yyyy-MM-dd hh:mm:ss");
		today.setDate(today.getDate());
		today.setHours(00);
		today.setMinutes(00);
		return today;
	};
	
	/*var today = function getTodayDate() {
		var today = new Date();
		var hours = today.getHours();
		var minutes = today.getMinutes();
		today.Format("yyyy-MM-dd hh:mm");
		today.setDate(today.getDate());
		return today;
	};*/
	var secondDate = function getSecondDate() {
		today.Format("yyyy-MM-dd hh:mm:ss");
		today.setDate(today.getDate()+1);
		today.setHours(00);
		today.setMinutes(00);
		return today;
	};
	
	var getTomorrow = function getTomorrow() {
		//获取系统时间 
		var LSTR_ndate=new Date(); 
		var LSTR_Year=LSTR_ndate.getYear(); 
		var LSTR_Month=LSTR_ndate.getMonth();
		var LSTR_Date=LSTR_ndate.getDate(); 
		//处理 
		var uom = new Date(LSTR_Year,LSTR_Month,LSTR_Date); 
		uom.setDate(uom.getDate()+1);//取得系统时间的前一天,重点在这里,负数是前几天,正数是后几天 
		var LINT_MM=uom.getMonth(); 
		LINT_MM++; 
		var LSTR_MM=LINT_MM >= 10?LINT_MM:("0"+LINT_MM) ;
		var LINT_DD=uom.getDate(); 
		var LSTR_DD=LINT_DD >= 10?LINT_DD:("0"+LINT_DD) ;
		//得到最终结果 
		uom = uom.getFullYear() + "-" + LSTR_MM + "-" + LSTR_DD; 
		return uom;
	};
	Date.prototype.Format = function(fmt) {
		var o = {
			"M+" : this.getMonth()+1,                 //月份 
			"d+" : this.getDate(),                    //日 
			"h+" : this.getHours(),                   //小时 
			"m+" : this.getMinutes(),                 //分 
			"s+" : this.getSeconds(),                 //秒 
			"q+" : Math.floor((this.getMonth()+3)/3), //季度 
			"S"  : this.getMilliseconds()             //毫秒 
		}; 
		if(/(y+)/.test(fmt)) 
			fmt = fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		for(var k in o) 
			if(new RegExp("("+ k +")").test(fmt)) 
		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
		return fmt; 
};
