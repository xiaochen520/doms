<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>采出日报审核统计</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta content='width=330, height=400, initial-scale=1' name='viewport' />
     <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
     <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
     
     	<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerPopupEdit.js"></script>
  
    <script src="../../lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<script src="../../lib/U2_check.js" type="text/javascript"></script>
 	
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
 	<script src="../../lib/checkDate.js" type="text/javascript"></script>
 	<script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script> 
    <script type="text/javascript">
        var grid;
        var toolbar;
        $(function ()
        {
        
        		//获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'cczr_pageCCInit.action',
					async : false,
					success : function(jsondata) { 
						if (jsondata != null && typeof(jsondata)!="undefined"){
							var data = eval('(' + jsondata + ')');
								grid = $("#maingrid").ligerGrid(data);					
								toolbar = grid.topbar.ligerGetToolBarManager();	
								
							}else{
								$.ligerDialog.error(outerror(jsondata));
							}
					},
					error : function(data) {
				
					}
				});
				
			 $("#stautsType").ligerComboBox({
             			hideOnLoseFocus:true,
						autocomplete:true,
			                data: [
			                    { text: '全部', id: '0' },
			                    { text: '未审核', id: '1' },
			                    { text: '已审核', id: '2' }
			                ], valueFieldID: 'stautsid'
			            }).selectValue('0');
         	$("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
           
           
			            
			            
        });
        
       
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [
					{ name: 'stautsid',
					value: $("#stautsid").val() },
					{ name: 'txtDate',
					value: $("#txtDate").val() }
					]
        	});
         	grid.loadData(true);
			
        }
        
       
        //工具条事件
      function itemclick(item) {
          switch (item.icon) {
              case "bzaudit":
                    if (chekAduitByDate($("#txtDate").val())) {
	    			jQuery.ajax({
	    				type : 'post',
	    				url : 'cczr_onAudit.action?nowdata='+parseInt(Math.random()*100000),
	    				data: {"arg":"bzaudit","txtDate":$("#txtDate").val()},
	    				success : function(data) { 
	    					if (data != null && typeof(data)!="undefined"){
	    						var outData = eval('(' + data + ')');
	    						if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
	    							$.ligerDialog.error(outData.ERRMSG);
	    						}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
	    							$.ligerDialog.error(outerror(outData.ERRCODE));
	    						}else{
	    							onSubmit();
	    							$.ligerDialog.success('审核成功！');
	    						}
	    					}
	    				},
	    				error : function(data) {
	    			
	    				}
	    			});
				} else {
					$.ligerDialog.error("审核时间无效,不能进行审核！");
				}
              	 break;
               case "dzzaudit":
               
                    if (chekAduitByDate($("#txtDate").val())) {
	    			jQuery.ajax({
	    				type : 'post',
	    				url : 'cczr_onAudit.action?nowdata='+parseInt(Math.random()*100000),
	    				data: {"arg":"dzzaudit","txtDate":$("#txtDate").val()},
	    				success : function(data) { 
	    					if (data != null && typeof(data)!="undefined"){
	    						var outData = eval('(' + data + ')');
	    						if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
	    							$.ligerDialog.error(outData.ERRMSG);
	    						}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
	    							$.ligerDialog.error(outerror(outData.ERRCODE));
	    						}else{
	    							onSubmit();
	    							$.ligerDialog.success('审核成功！');
	    						}
	    					}
	    				},
	    				error : function(data) {
	    			
	    				}
	    			});
				} else {
					$.ligerDialog.error("审核时间无效,不能进行审核！");
				}
              	 break;
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
           
            /* 搜索框 */
		.searchtitle{ padding-left:28px; position:relative;}
		.searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
		.searchtitle span{ font-size:14px; font-weight:bold;}
		.searchtitle .togglebtn{ position:absolute; top:6px; right:15px; background:url(../../lib/ligerUI/skins/icons/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
		.searchtitle .togglebtn-down{ background-position:0px -10px;}
		   
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:100px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
    </style>
</head>
<body style="overflow-x:hidden; padding:2px;" >
 
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form"> 
        	<table border="0" cellspacing="1">
				<tr>
	                
	                <td align="right" class="l-table-edit-td">班组审核状态：</td>
	                <td align="left" class="l-table-edit-td" style="width:180px">
	                	<input type="text" id="stautsType" name="stautsType"/>
	                </td>
	                <td align="left">
	                </td>
	                
	                
	                <td align="right" class="l-table-edit-td">日期：</td>
	                <td align="left" class="l-table-edit-td" style="width:180px">
	                	<input type="text" id="txtDate" name="txtDate"/>
	                </td>
	                <td align="left">
	                </td>
	               
	                <td align="right" class="l-table-edit-td"></td>
	                <td align="left" class="l-table-edit-td" style="width:180px">
	                	<a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a>
	                </td>
	                <td align="left">
	                </td>
				</tr>
				
			</table>
        </form>
    </div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
 
</body>
</html>
