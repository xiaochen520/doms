<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>综合日报</title>
   
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
   
 	 <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/inject.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	
    <script type="text/javascript">
    	
        $(function ()
        {
        
        	
        	$("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
           //获取报表及功能按钮ｊｓ
            jQuery.ajax({
					type : 'post',
					url : 'zhrb_pageInit.action',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
               
        });
        
         function onSubmit()
        {
        
        }
        
         function onSave()
        {
        
        }
        
         function onExport() {
   		var blockstationname1=$("#blockstationname").val();
		var areablock1=$("#areablock1").val()+'';
		var wellnum1=$("#wellnum").val();
		var prod_stages1=$("#prod_stages1").val();
		var status_value1=$("#status_value1").val();
		var jrbz1=$("#jrbz1").val();
		var dyear1=$("#dyear1").val();
  		var totalNum = 0;
  		var url = "sagd_searchSagdBaisc.action?blockstationname="+encodeURIComponent(blockstationname1)+"&areablock="+encodeURIComponent(areablock1)+"&wellnum="+encodeURIComponent(wellnum1)+"&prod_stages="+encodeURIComponent(prod_stages1)+"&status_value="+encodeURIComponent(status_value1)+"&dyear1="+encodeURIComponent(dyear1)+"&jrbz="+encodeURIComponent(jrbz1)+'&totalNum=export';
  		var urls = "sagd_searchSagdBaisc.action?blockstationname="+encodeURIComponent(blockstationname1)+"&areablock="+encodeURIComponent(areablock1)+"&wellnum="+encodeURIComponent(wellnum1)+"&prod_stages="+encodeURIComponent(prod_stages1)+"&status_value="+encodeURIComponent(status_value1)+"&dyear1="+encodeURIComponent(dyear1)+"&jrbz="+encodeURIComponent(jrbz1)+'&totalNum=totalNum';
  		$.ajax({
  			type : 'post',
  			url : urls,
  			async : false,
  			timeout : '30000',
  			success : function (data){
  				totalNum = data;
  			}
  		});
  		if (totalNum < 10000 && totalNum > 0) {
  			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url;});
  		}
  		else if(totalNum > 10000){
  			$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url;});
  		}
  		else {
  			$.ligerDialog.confirm('没有可导出的数据！');
  		}
  	}
        
    </script>
       <style type="text/css">
             html,body
			{
					font-size:12px;
			       	margin: 0px;
					padding: 0px;
					background: #FAFCFC;
					position: absolute;
					width: 100%;
					height: 100%;
					hoverflow:scroll;
					overflow-y:hidden;
					overflow-x:hidden;
			   }
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
</head>
<body style="padding:10px"> 
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table>
					<tr>	
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td align="right" class="l-table-edit-td" style="width:80px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						<td align="right" class="l-table-edit-td" style="width:80px">
							<a href="javascript:onSave()" class="l-button"
							style="width:100px">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:80px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

		<div style="margin:5px;float:left;height: 80%">
	         		<div id="ribaotable" style=" width:100%;height:100%;margin:5px; float:left; border:1px solid #ccc; overflow:auto;">
					<iframe frameborder="1" name="Itable" src="zhrbtable.jsp" style=" width:100%;height:100%;"></iframe> 
					</div> 
	    		 </div>

		<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

     
  </form>
    
</body>
</html>
