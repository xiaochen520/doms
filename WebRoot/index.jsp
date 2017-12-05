<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<title>用户登录</title>
		
		<link href="img/login/skin.css" rel="stylesheet" type="text/css"/>
		<link href="img/mask.css" rel="stylesheet" type="text/css" />
        <script src="lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
		<style type="text/css">

		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #1D3647;
		}
	
	</style>
	<script type="text/javascript">
		var login = function(){
			
			var password = $("#password").val();
			if(password.length >= 1 && password.length <= 20){
				var url = "login_SystemLogin.action";
				document.forms[0].action=url;
				document.forms[0].submit();
			}else{
				alert("用户密码长度范围1~20");
			}
			
		}
		
		//回车事件 or Esc 重置
		document.onkeydown = function() {
			if(event.keyCode == 13){
				document.getElementById('loginBtn').click();
				return false;
			}
			if(event.keyCode == 27){
				document.forms[0].reset();
				return false;
			}
		}
		$(function(){

			//获取cookie字符串 
			var strCookie=document.cookie; 
			//将多cookie切割为多个名/值对 
			var arrCookie=strCookie.split("; "); 
			var cookieName; 
			var cookiePass;
			//遍历cookie数组，处理每个cookie对 
			for(var i=0;i<arrCookie.length;i++){ 
				var arr=arrCookie[i].split("="); 
				//找到名称为userId的cookie，并返回它的值 
				if("cookieName"==arr[0]){ 
					cookieName = arr[1]; 
					break; 
				} 
			} 
			if (cookieName != null && typeof(cookieName)!="undefined" && cookieName!=""){
					var userfo = cookieName.split("|");
					$("#username").val(userfo[0]);
					$("#password").val(userfo[1]);
					
				}
		});
	</script>

	</head>

	<body>


   <table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="center"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                 <!--  <td height="80" align="center" valign="top"><img src="images/first31.jpg"></td> -->
                </tr>
                <tr>
                  <td height="198" align="center" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                    
                      <td width="100%"><img src="img/login/loginfo1.bmp" width="500" height="298"> </td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              
              </td>
            </tr>
            
        </table>
        
        </td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom">
        	<div style="color: red" id="errermsg" > <s:fielderror></s:fielderror></div>
        <table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
        		
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">风城油气田生产管理自动化信息系统DMS</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
                    
                    <form name="myform" id="myform" action="" method="post" onsubmit="return checkValues()">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="15%" height="38" >
                            	<span class="login_txt">用户名：&nbsp; </span>
                            </td>
                            <td height="10" width="15%" colspan="2" align="left">
                           		 <input id="username" name="username" class="editbox4" value="" size="20">
                            </td>
                            <td width="35%" colspan="1">&nbsp;</td>
                          </tr>
                          <tr>
                            <td width="15%" height="38" class="top_hui_text">
                            	<span class="login_txt">&nbsp; 密 &nbsp;&nbsp;码： </span>
                            </td>
                            <td height="10" width="15%" colspan="2" align="left">
                            <input class="editbox4" type="password" id="password" name="password" size="21">
                              <img src="img/login/luck.gif" width="19" height="18">
                             </td>
                            <td width="35%" colspan="1">&nbsp;</td>
                          </tr>
                          
                          <tr>
                         	 <td width="15%" height="38" >
                          		<input id="autopas" type="checkbox" name="autopas" checked="true" ><span class="login_txt">记住密码&nbsp; </span>
                            </td>
                            <td height="10" width="15%" colspan="2" align="left">
                            	<!-- <input id="autologin" type="checkbox" name="autologin" checked="0"><span class="login_txt">自动登录&nbsp; </span> -->
                             </td>
                            <td width="35%" colspan="1">&nbsp;</td>
                          
                          </tr>
                       
                          <tr>
                         	 <td><input  name="input" type="button" id="loginBtn"  class="button"  value="登录" onclick="login()" /></td>
                            <td><input type="reset" name="reset" class="button"  value="重置" onClick="showConfirmMsg1()"/>
                            </td><td>
                            </td>
                             <td>&nbsp;</td>
                          </tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="img/login/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2013-2015 北京安控科技股份发展有限责任公司</span></td>
      </tr>
    </table></td>
  </tr>
</table>

			
	</body>
	 <script>
     function checkValues(){
      if(myform.logname.value.length==0){
        alert("用户名不能为空！");
        myform.logname.focus();
        return (false);
       }
      if(myform.logpass.value.length==0){
        alert("密码不能为空！");
        myform.logpass.focus();
        return (false);
       }
       return (true);
      }
 </script>
</html>
