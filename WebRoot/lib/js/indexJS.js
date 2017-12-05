/*--------------------------------------------------|

| Tab选项卡 1.0 |yanlong    |

|---------------------------------------------------|

| Copyright (c) 2014-2014  Index首页                    |

| 检测session失效与客户端与服务器端链接       |

| Updated: 24.03.2014                               |

|--------------------------------------------------*/

// 屏蔽页面
function killErrors() {
	return true;
}
window.onerror = killErrors;

/**
 * 实时监控session是否存在, 每隔10分钟自动检测session是否为空,如果为空则调整
 */
setInterval(function() {
	/** ajax 向后台请求并跳转 */
    jQuery.ajax({
		type : 'post',
		url : 'login_sessionFilter.action', //连接ACTION  
		timeout : '30000',
		success : function(data) {
		 	// 长时间未操作,导致Seesion失效 ,获取后台返回值 1 通过 0 不通过
            if (data != 1)
            {
            	// 取得历史网页记录
			    var len = history.length;   
			    // 回到IE启动时的第一页
			    history.go(-len);   
			    // 再將网址转向到目的頁面 ( 注意: 一定要用 location.replace 函式 )   
			    location.replace(data); 
			    return false; 
            }
		},
		error : function() {
			if (confirm("已与服务器断开链接!!")) {
				window.opener = null;
				window.open('', '_self');
				window.close();
			}else{
			    return false; 
			};
		}
	});
},60000);//10分钟检测一次


/**
 * 检测页面是否最大化,如果不是将自动扩张
 */
window.moveTo(0, 0);
if (document.all) {
	top.window.resizeTo(screen.availWidth, screen.availHeight);
} else if (document.layers || document.getElementById) {
	if (top.window.outerHeight < screen.availHeight
			|| top.window.outerWidth < screen.availWidth) {
		top.window.outerHeight = screen.availHeight;
		top.window.outerWidth = screen.availWidth;
	}
}

var updatePwd = function(){
	var PageHref = './WebMain/page/UserInfo/UpdateUserPwd.jsp';//Link address
		showWindows('UpdateUserPwd',"修改用户密码",'450','240',PageHref)
}

var exc = function(){
	var her ="./SystemLogin.jsp";
		// 取得历史网页记录
	    var len = history.length;  
	    // 回到IE启动时的第一页
	    history.go(-1);   
	    // 再將网址转向到目的頁面 ( 注意: 一定要用 location.replace 函式 )   
	    location.replace(her);
	jQuery.ajax({
		type : 'post',
		url : 'login_EscLogin.action',
		timeout : '30000',
		success : function(data) {
		},
		error : function() {

		}
	});
}

// 禁止右键弹出菜单
document.oncontextmenu = function() {
	return false;
}
