<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>管汇动态数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
   <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>    -->
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
    <script type="text/javascript">
   	var eee;
   
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'manifoldrd_pageInit.action',
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

			var proData = [{ id: 1 , text: '' }];
			$("#oilationname").ligerComboBox({
				url:'rulewell_queryOilSationInfo.action?arg=manifold',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					clearSelecteValue=1;
					liger.get("blockstationname").setValue('');
					liger.get("ghmc").setValue('');
					liger.get("areablock").setValue('');
					
					if ($("#oilationid").val() != 1) {
						setAreablockData($("#oilationid").val(),proData);
						setStationData('',proData,$("#oilationid").val(),clearSelecteValue);
					}
					else {
						getBoilerInitData();
					}
				}
			});
			$("#areablock").ligerComboBox({
				//url:'rulewell_queryAreablockInfo.action?orgid=',
				valueField: 'id',
				valueFieldID: 'areablockid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					/* liger.get("blockstationname").setValue('');
					liger.get("ghmc").setValue('');
					
					if (data != null && typeof(data)!="undefined" && data != ''){
						var se = setStationData($("#areablockid").val(),proData,$("#oilationid").val(),clearSelecteValue);
						if (clearSelecteValue === 1) {
							clearSelecteValue = 2;
						}
					} */
				}
			});
			$("#blockstationname").ligerComboBox({
				url:'rulewell_queryStationInfo.action?oilid=manifold',
	            valueField: 'id',
				valueFieldID: 'stationid',
				textField: 'text',
				selectBoxHeight:400,
				selectBoxWidth:140,
	            hideOnLoseFocus:true,
	            autocomplete:true,
	            onSelected:function (data){
					liger.get("ghmc").setValue('');
					if (data != null && typeof(data)!="undefined" && data != ''){
						setGhData($("#stationid").val(),proData);
					}
				}
			});
			
			$("#ghmc").ligerComboBox({
				url:'manifoldBasicInfo_queryManifoldNameInfo.action',
				valueField: 'id',
				valueFieldID: 'ghid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
			});
			// $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
             $("#txtDate").ligerDateEditor(
                {

                    format: "yyyy-MM-dd hh:mm",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
            });
             $("#txtDate1").ligerDateEditor(
                {

                    format: "yyyy-MM-dd hh:mm",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    showTime: true,
                    labelAlign: 'center',
                    cancelable : false
            });
            $("#pageloading").hide();
			$("#txtDate").ligerDateEditor({ showTime: true, showTime: true,labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(myDate().Format("yyyy-MM-dd hh:mm"));
			$("#txtDate1").ligerDateEditor({ showTime: true, showTime: true,labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));
             $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                //调试状态，不会提交数据的
                debug: true,
                errorPlacement: function (lable, element)
                {

                    if (element.hasClass("l-textarea"))
                    {
                        element.addClass("l-textarea-invalid");
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().addClass("l-text-invalid");
                    }
                    $(element).removeAttr("title").ligerHideTip();
                    $(element).attr("title", lable.html()).ligerTip();
                },
                success: function (lable)
                {
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
                		
                    
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
         function setAreablockData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'boilerBasicInfo_queryAreablockInfo.action',
				data: {"orgid":data},
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("areablock").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("areablock").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}
		 function setStationData(areaid,proData,oilid,clearSelecteValue) {
			jQuery.ajax({
				type : 'post',
				url:'manifoldBasicInfo_queryStationInfo.action',
				data: {"areaid":areaid,"oilid":oilid,"selecteValue":clearSelecteValue},
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("blockstationname").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("blockstationname").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
			return clearSelecteValue;
		}
		 function setGhData(data,proData) {
			jQuery.ajax({
				type : 'post',
				url:'manifoldBasicInfo_queryManifoldNameInfo.action',
				data: {"orgid":data},
				timeout : '3000',
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
						liger.get("ghmc").setData(eval('(' + jsondata + ')'));
					}
					else{
						liger.get("ghmc").setData(proData);
					}
				},
				error : function(jsondata) {
					alert("请求数据失败，请重新查询");
				}
			});
		}
			var oilstationname='全部';
			var areablock='';
			var blockstationname='';
			var ghname='';
			var wellnum='';
         //为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			$("#oilationname").ligerGetComboBoxManager().setValue('');
			$("#blockstationname").ligerGetComboBoxManager().setValue('');
			$("#ghmc").ligerGetComboBoxManager().setValue('');
			getManifoldInitData();
			if(datatype=='4'){
				areablock=name;
				$("#oilationname").val(name);
			}
			if(datatype=='7'){
				blockstationname=name;
				$("#blockstationname").val(name);
			}
			if(datatype=='8'){
				ghname=id;
				$("#ghmc").val(name);
			}
			
			grid.setOptions({
				parms: [{ name: 'oilationname', value: oilstationname},
					{ name: 'ghname', value: ghname},
					{ name: 'blockstationname',value: blockstationname}]
					//{ name: 'boilersName',value:wellnum}]
			});
			grid.loadData(true);
		}
		function getManifoldInitData() {
			var oilText=[{ id: 1 , text: ''}];
			var areaText=[{ id: 1 , text: ''}];
			var stationText=[{ id: 1 , text: ''}];
			var ghmcText=[{ id: 1 , text: ''}];
			jQuery.ajax({
				type : 'post',
				url : 'manifoldBasicInfo_cascadeInit.action',
				success : function(jsondata) {
				var dataObj = $.parseJSON(jsondata);
					$.each(dataObj, function(key,val){
						if (key == 'oiltext') {
							oilText = val;
						}
						if (key == 'areatext'){
							areaText = val;
						}
						if (key == 'stationtext'){
							stationText = val;
						}
						if (key == 'ghmctext'){
							ghmcText = val;
						}
					});
					setInitData(oilText,areaText,stationText,ghmcText);
				}
			});
		}
		
	    function setInitData(oilText,areaText,stationText,ghmcText) {
			liger.get("oilationname").setData(oilText);
			//liger.get("areablock").setData(areaText);
			liger.get("blockstationname").setData(stationText);
			liger.get("boilersName").setData(ghmcText);
		}
        	
           function onSubmit()
        {
        
        
	   var oilationname=$("#oilationname").val();
  	         var areablock=$("#areablock").val();
  	         var blockstationname1=$("#blockstationname").val();
  	         var boilersName=$("#ghmc").val();			
          
          
        	grid.setOptions({
        		parms: [{
					name: 'oilationname',
					value: oilationname
				},{
					name: 'areablock',
					value: areablock
				},{
					name: 'blockstationname',
					value: blockstationname1
				},{
					name: 'boilersName',
					value: boilersName
				},{
					name: 'date',
					value: $("#txtDate").val()
				}
				,{
					name: 'date1',
					value: $("#txtDate1").val()
				}
				]
        	});
         	grid.loadData(true);
        }
		
	var exportFlag=false;
	 function onExport() {
			
		     var oilationname=$("#oilationname").val();
  	         var areablock=$("#areablock").val();
  	         var blockstationname1=$("#blockstationname").val();
  	         var boilersName=$("#ghmc").val();		
			
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var gh='';
			if (exportFlag) {
				oilationname=areablock;
				blockstationname1=blockstationname;
				//blockstationname1=boilersName;
				boilersName=ghname;
				
				
			}
			var totalNum = 0;
			
			var url = 'manifoldrd_searchMainfoldRD.action?oilationname='+encodeURIComponent(oilationname)+'&areablock='+encodeURIComponent(areablock)+'&blockstationname='+encodeURIComponent(blockstationname1)+'&ghname='+encodeURIComponent(gh)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=export'+'&boilersName='+boilersName;
			var urls = 'manifoldrd_searchMainfoldRD.action?oilationname='+encodeURIComponent(oilationname)+'&areablock='+encodeURIComponent(areablock)+'&blockstationname='+encodeURIComponent(blockstationname1)+'&ghname='+encodeURIComponent(gh)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=totalNum'+'&boilersName='+boilersName;
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
				 $.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.location.href= url });
			}
			else if(totalNum > 10000){
				$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.location.href= url });
			}
			else {
				$.ligerDialog.confirm('没有可导出的数据！');
			}
		}
		       
		       
    </script>
    <style type="text/css"> 
    html,body
	{
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
    </style>
</head>
<body style="overflow-x:hidden; padding:2px;">
 
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form"> 
        	<table border="0" cellspacing="1">
				<tr>
					<td align="right" class="l-table-edit-td" style="width:60px">采油站：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="oilationname" name="oilationname"/>
	                </td>
					<td align="right" class="l-table-edit-td" style="width:60px;display:none;">区块：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px;display:none;">
	                	<input type="text" id="areablock" name="areablock"/>
	                </td>
					<td align="right" class="l-table-edit-td" style="width:60px">注转站：</td>
	                <td align="right" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="blockstationname" name="blockstationname"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:60px">管汇：</td>
	                <td align="left" class="l-table-edit-td" style="width:80px">
	                	<input type="text" id="ghmc" name="ghmc"/>
	                </td>
	                <td align="left" style="width:10px">
	                </td>
	                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
					<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td>
				</tr>
			</table>
        </form>
    </div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
 
</body>
</html>