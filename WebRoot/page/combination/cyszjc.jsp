<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>稠油水质监测报表</title>
   
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
   
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
    <script src="../../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script>
    <script src="../../noBackspace.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script src="../../columnnamedisplay.js" type="text/javascript"></script>
    <script type="text/javascript">
      var grid = null;
      var toolbar ;
      var textValue ="";
      var namedisplay ="";
      var columnname ="";
      var pwaterparam = "";
      var d ;  //日期
      var datestr = "";   //当前系统时间
      
      var authoruty = 0;  //0 无需该权限， 1 有修改权限
      var exportFlag = false;
          
     $(function(){
            var yearid = $('#SYear')    //年所在的控件
            var monthid = $('#SMonth')    //月所在的控件
            //var dayid = $('#SDay')    //天所在的控件
            var myDate = new Date();
            var year = myDate.getFullYear();
            
            var month=myDate.getMonth()+1;
            
            for(var i=(year-20);i<=(year+20);i++){
                yearid.append('<option value="'+i+'">'+i+'</option>')
            }
                for(var i=1;i<=12;i++){
                    monthid.append('<option value="'+i+'">'+i+'</option>')
                }
            
           $("#SYear").val(year);
           $("#SMonth").val(month);
           
            //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'u2szjc_pageInit.action',
					async : false,
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
							var data = eval('(' + jsondata + ')');
							if (data.toolbar != null && typeof(data.toolbar)!="undefined" && data.toolbar.items != null && typeof(data.toolbar.items)!="undefined"){
								var toolteams = data.toolbar.items;
								for(var i=0; i<toolteams.length ; i++){
									if(toolteams[i].id == 'modifyid'){
										authoruty  = 1;
									}
								}
							}
							grid = $("#maingrid").ligerGrid(data);					
							toolbar = grid.topbar.ligerGetToolBargrid();	
							toolbar.removeItem("modifyid"); 
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
           
           
        })
        
      
         function fromAu(rowdata){
        	//用户选择修改数据
				                
        } 
     function onSubmit()
        {
        	exportFlag = false;
        	grid.setOptions({
        		parms: [
        		{
					name: 'SYear',
					value: $("#SYear").val()
				},{
					name: 'SMonth',
					value: $("#SMonth").val()
				}
				]
        	});
         	grid.loadData(true);
        }
        
	function onExport() {
		var SYear = $("#SYear").val();
		var SMonth = $("#SMonth").val();
		var url = "u2szjc_exportU2_SZJC.action?SYear="+encodeURIComponent(SYear) +"&SMonth="+encodeURIComponent(SMonth)+ "&reportName="+encodeURIComponent('二号稠油联合处理站-水质监测报表');
			$.ligerDialog.confirm('确定导出吗?',function (yes) {
				if (yes) {
					window.location.href= url;
					/* if (!(window.location.href= url)) {
						$.ligerDialog.confirm('没有可导出的数据！');
					} */
				}
			});
		
	}
       function itemclick(item) {
		var rows = grid.getCheckedRows();
		var sagddidArrayObj = new Array();
		if (rows.length > 0) {
			$(rows).each(function (){
				//alert('VERIFIER' + this.VERIFIER);
				if (this.VERIFIER == '') {//排除审核过的数据
					sagddidArrayObj.push(this.SAGDDID);
				}
			});
		}
		var sagddid='';
		if(rows.length == 1){
			$(rows).each(function (){
				sagddid=this.SAGDDID+'';
			});
		}
		switch (item.icon) {
			
			case "save":
			
				var rows = grid.getCheckedRows();
				var rowstatus ;
				var rowName='';
				var rowIndex=0;
				$(rows).each(function (){
					
					rowstatus = this.__status ;
					REPORT_DATE = this.REPORT_DATE;
					REPORT_TIME =  this.REPORT_TIME;
					rowIndex=this.__index+1;
				});
			
				
				
				if (rowstatus == 'nochanged') {//
						$.ligerDialog.error("没有更改的数据!");
						return false;
					}else{
						var updateRowDate = JSON2.stringify(rows);
							$.ligerDialog.confirm('确定修改第'+rowIndex+'行 ，所属日期：'+REPORT_DATE+' 报表时间：'+REPORT_TIME+'的记录吗?', function (yes) {
								if (yes) {
									jQuery.ajax({
										type : 'post',
										url : 'u2szjc_updateU2szjc.action',
										data: {"rowData":updateRowDate},
										async : false,
										timeout : '3000',
										success : function(data) { 
											if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else if(outData.CONFIRMMSG != null && typeof(outData.CONFIRMMSG)!="undefined"){
														$.ligerDialog.success(outData.CONFIRMMSG);
													}else{
														onSubmit();
														$.ligerDialog.success("修改成功");
													}
											}
										},
										error : function(data) {
										}
									});
								}
							});
					}
				
			break;
			case "audit":
				//alert(sagddidArrayObj);
				var rows = grid.getSelectedRow() ;
				var flag=false;
				$(rows).each(function (){
					if (this.VERIFIER == '' || typeof(this.VERIFIER)=="undefined") {
						flag=true;
						return;
					}
				});
				if (!flag) {
					$.ligerDialog.error("不存在需要审核数据!");
					return;
				}
				jQuery.ajax({
					type : 'post',
					url : 'sagddrpd_addOrUpdateSagddRPD.action',
					data: {"sagddids":sagddidArrayObj+''},
					async : false,
					timeout : '3000',
					success : function(jsondata) { 
						$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
						if (jsondata == 1){
							//$.ligerDialog.close();
							//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
							//parent.window.onSubmit();
							window.onSubmit();
							$.ligerDialog.success("审核成功！");
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
					}
				});
			break;
		}
	} 
	
	
		function f_onBeforeEdit(e)
		{
			columnname = "";
			textValue = "";
			namedisplay ="";
			//判断用户 是否拥有修改权限
			if(authoruty  == 1){
				columnname = e.column.columnname;
	        	//alert(columnname);
	        	if(e.record.RPD_U2_WATER_QUALITY_ID != "空行"){
					
					d  = new Date();
					datestr = d.Format("yyyy-MM-dd");
					//alert(datestr +"------"+e.record.REPORT_DATE);
					//alert(e.record.REPORT_DATE == datestr);
					//alert(getTomorrow());
					if(e.record.REPORT_DATE == datestr || e.record.REPORT_DATE == getTomorrow()) {
	
						if (columnname != '' && columnname != null && typeof(this.VERIFIER)!=columnname &&
				        	columnname != "HYCJC" && columnname != "XFCJC" && columnname != "YDCJC" && 
							columnname != "YDDJ" && columnname != "YDDC" && columnname != "YDFC" && 
							columnname != "YDHNC" && columnname != "YDYJJ" && columnname != "HNYJC" && columnname != "HNEJC") {
			        		
							if (textValue != null && typeof(textValue)!="undefined"){
			        		textValue = e.value;
			        		if(textValue.indexOf("span") > 0 ){
				        			textValue=textValue.replace("<span style='color: red;'>","");  
				        			textValue=textValue.replace("</span>","");  
				        		}
				        	}
				        	namedisplay = e.column.display.replace("≤","");
							$.ligerDialog.open({
					    			title: getColumName(e.column.columnname),
					    			url:'u2szjcValidate1.jsp?columnname='+e.column.columnname+'&namedisplay='+namedisplay+'&textValue='+textValue,
					    			width: 375,
					    			height: 150
					    	});
			        	}
	
	
					}else{
						$.ligerDialog.error("只能修改所属日期为"+datestr+"或"+getTomorrow()+"的数据！");
						return false;
					}
	        	
	        		
	        	}else{
	        		$.ligerDialog.error("空行不能进行数据修改！");
	        		return false;
	        	}
			
			}else{
				return false;
			}
        	
        	
        	
        	
		
		}

		function f_onAfterEdit(e)
		{
			columnname = e.column.columnname;
        	if (columnname != '' && columnname != null && typeof(this.VERIFIER)!=columnname &&
	        	columnname != "HYCJC" && columnname != "XFCJC" && columnname != "YDCJC" && 
				columnname != "YDDJ" && columnname != "YDDC" && columnname != "YDFC" && 
				columnname != "YDHNC" && columnname != "YDYJJ" && columnname != "HNYJC" && columnname != "HNEJC") {
				
				grid.updateCell(e.column.columnname, pwaterparam, e.record);
				
			}
			
			
		}
        function getSelected()
        { 
            var row = grid.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
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
        <!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{mso-header-data:&A;
	mso-footer-data:"Page &P";
	margin:.75in .7in .75in .7in;
	mso-header-margin:.3in;
	mso-footer-margin:.3in;
	mso-page-orientation:landscape;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;
	display:none;}
-->
    </style>

</head>
<body style="padding:10px"> 
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

				<table>
					<tr>	
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td>
						
						 <select name="SYear" id="SYear">
						　　 </select>&nbsp;&nbsp;年
						　　 <select name="SMonth" id="SMonth" >
						　　 </select>&nbsp;&nbsp;月
						</td>
						<td align="right" class="l-table-edit-td" style="width:20px"></td>
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>
						<!--
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						
						 <td id="jieshi"></td> -->
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

<div id="maingrid"></div> 

     
  </form>
    
</body>
</html>
