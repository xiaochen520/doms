<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>注水井日报数据</title>
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
     var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
    
       $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'floodingRPD_pageInit.action',
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
			$("#oilstationname1").ligerComboBox({
				url:'rulewell_queryOilSationInfo.action?arg=injectionFlood',
				valueField: 'id',
				valueFieldID: 'oilationid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				autocomplete:true,
				hideOnLoseFocus:true,
				onSelected:function (data){
					clearSelecteValue=1;
					liger.get("rulewellname1").setValue('');
					liger.get("waterflooding1").setValue('');
					if ($("#oilationid").val() != 1) {
						setWellData($("#oilationid").val(),proData);
					}
					else {
						getWellInitData();
					}
				}
			});
			
			$("#rulewellname1").ligerComboBox({
				url:'waterIT_queryWellInfo.action',
				valueField: 'id',
				valueFieldID: 'wateritid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:160,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
					liger.get("waterflooding1").setValue('');
					if (data != null && typeof(data)!="undefined" && data != ''){
						setFloodData($("#wateritid").val(),proData);
					}
				}
			});
			$("#waterflooding1").ligerComboBox({
				url:'waterFL_queryWaterflooding.action',
				valueField: 'id',
				valueFieldID: 'wellid',
				textField: 'text',
				selectBoxHeight:350,
				selectBoxWidth:100,
				hideOnLoseFocus:true,
	            autocomplete:true,
				onSelected:function (data){
				}
			});
			$("#areablock").ligerComboBox({
				url:'rulewell_queryAreablockInfo.action?orgid=waterf',
				valueField: 'id',
				valueFieldID: 'wellAreaSelfid',
				textField: 'text',
				selectBoxHeight:200,
				selectBoxWidth:98,
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
                    showTime: true,
                    labelAlign: 'center',
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
			$("#txtDate").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(myDate().Format("yyyy-MM-dd"));
			$("#txtDate1").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"}).setValue(secondDate().Format("yyyy-MM-dd"));
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
    
          function setWellData(data,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'waterIT_queryWellInfo.action',
    			data: {"orgid":data},
    			timeout : '3000',
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("rulewellname1").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("rulewellname1").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				alert("请求数据失败，请重新查询");
    			}
    		});
    	}
        function setFloodData(data,proData) {
    		jQuery.ajax({
    			type : 'post',
    			url:'waterFL_queryWaterflooding.action',
    			data: {"orgid":data},
    			timeout : '3000',
    			success : function(jsondata) {
    				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
    					liger.get("waterflooding1").setData(eval('(' + jsondata + ')'));
    				}
    				else{
    					liger.get("waterflooding1").setData(proData);
    				}
    			},
    			error : function(jsondata) {
    				alert("请求数据失败，请重新查询");
    			}
    		});
    	}
    	
        function getWellInitData() {
    		var oilText=[{ id: 1 , text: '' }];
    		var wellText=[{ id: 1 , text: '' }];
    		var waterfloodText=[{ id: 1 , text: '' }];
    		jQuery.ajax({
    			type : 'post',
    			url : 'waterFL_cascadeInit.action',
    			success : function(jsondata) {
    			var dataObj = $.parseJSON(jsondata);
    				$.each(dataObj, function(key,val){
    					if (key == 'oiltext') {
    						oilText = val;
    					}
    					if (key == 'welltext'){
    						wellText = val;
    					}
    					if (key == 'waterfloodtext'){
    						waterfloodText = val;
    					}
    				});
    				setInitData(oilText,wellText,waterfloodText);
    			}
    		});
    	}
    	
        function setInitData(oilText,wellText,waterfloodText) {
    		liger.get("oilstationname1").setData(oilText);
    		liger.get("rulewellname1").setData(wellText);
    		liger.get("waterflooding1").setData(waterfloodText);
    	}
    		var oilstationname="全部";
			var areablock='';
			var blockstationname='';
			var ghname='';
			var wellnum='';
			var gh='';
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
						//alert(id);
			//alert(name);
			//alert(datatype);
		
			
			getWellInitData();
			$("#oilstationname1").ligerGetComboBoxManager().setValue('');
			$("#rulewellname1").ligerGetComboBoxManager().setValue('');
			$("#waterflooding1").ligerGetComboBoxManager().setValue('');
			$("#areablock").ligerGetComboBoxManager().setValue('');
			if(datatype=='4'){
				oilstationname=name;
				$("#oilstationname1").val(name);
			}
			
			if(datatype=='13'){
				blockstationname=name;
				$("#rulewellname1").val(name);
			}
			
			if(datatype=='9'){
				wellnum=name;
				$("#waterflooding1").val(name);
				
				//alert(wellnum);
			}
	
			
			grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: oilstationname
				},{
					name: 'blockstationname1',
					value: blockstationname
				},{
					name: 'rulewellname1',
					value: wellnum
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
		
           function onSubmit()
        {
        	   var oilstationname1=$("#oilstationname1").val();
  	         var areablock1=$("#rulewellname1").val();
  	         var blockstationname1=$("#waterflooding1").val();
          
          
        	grid.setOptions({
        		parms: [{
					name: 'oilstationname1',
					value: oilstationname1
				},{
					name: 'blockstationname1',
					value: areablock1
				},{
					name: 'rulewellname1',
					value: blockstationname1
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
			
		   var oilstationname1=$("#oilstationname1").val();
  	         var areablock1=$("#rulewellname1").val();
  	         var blockstationname1=$("#waterflooding1").val();
			
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var gh='';
			if (exportFlag) {
				oilstationname1=oilstationname;
				areablock1=blockstationname;
				blockstationname1=wellnum;
				
			}
			var totalNum = 0;
			//alert('in');
			var url = 'floodingRPD_searchwaterFLRRD.action?oilstationname1='+encodeURIComponent(oilstationname1)+'&blockstationname1='+encodeURIComponent(areablock1)+'&rulewellname1='+encodeURIComponent(blockstationname1)+'&wellnum='+encodeURIComponent(wellnum)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=export'+'&ghname='+gh;
			var urls = 'floodingRPD_searchwaterFLRRD.action?oilstationname1='+encodeURIComponent(oilstationname1)+'&blockstationname1='+encodeURIComponent(areablock1)+'&rulewellname1='+encodeURIComponent(blockstationname1)+'&wellnum='+encodeURIComponent(wellnum)+'&date='+encodeURIComponent(txtDate)+'&date1='+encodeURIComponent(txtDate1)+'&totalNum=totalNum'+'&ghname='+gh;
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
					<td align="right" class="l-table-edit-td" style="width:50px">采油站：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="oilstationname1" name = "oilstationname1" />
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:50px">注水撬：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="rulewellname1" name = "rulewellname1"/>
		                </td>
		                <td align="left">
		                </td>
		                
		                <td align="right" class="l-table-edit-td" style="width:50px">注水井：</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="waterflooding1" name = "waterflooding1"/>
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px">井区块：</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="areablock" name = "areablock"/>	
		                </td>
		                <td align="left">
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