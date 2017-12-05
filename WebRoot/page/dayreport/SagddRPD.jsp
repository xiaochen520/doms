<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>SAGD井日报数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
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
    <script src="../../lib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    
  	<script type="text/javascript" src="../../lib/jqueryautocomplete/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../lib/jqueryautocomplete/jquery.autocomplete.css"></link>
    
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
 	<script src="../../lib/sagd.js" type="text/javascript"></script>
    <script type="text/javascript">
        var eee;
        var grid = null;
        var toolbar ;
        var operate = 0; //操作  0-无 、1-添加、2-修改   ||用户操作时将operate设置为对应值， 结束操作之后将operate置0
        var dataflg = 1;
        var ghname='';
        var oilstation;
        var areaname='';
		var stationname='';
		var wellname='';
		var exportFlag = false;
		var  oilstationname='';
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'sagddrpd_SagdRPDpageInit.action',
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
				
                var proData = [{ id: 1 , text: '' }];
               //查询所属SAGD采油站
        		 $("#oilationname").ligerComboBox({
                	url:'rulewell_queryOilSationInfo.action?arg=sagdcaiyou',
        			valueField: 'id',
        			valueFieldID: 'oilationnameid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			autocomplete:true,
        			hideOnLoseFocus:true,
        			onSelected:function (data){
        			liger.get("ghmc").setValue('');	        		
                		if ($("#oilationnameid").val() != 1 && data != null && typeof(data)!="undefined" && data != '') {
        					setGHData($("#oilationnameid").val(),proData);       					
        				}
         				else {
        					setGHData();
        				} 
                	}
        		});
        	
        	
        		//查询采油站下管汇
        		$("#ghmc").ligerComboBox({
        			url:'sagd_queryGhmcInfo.action',
                    valueField: 'id',
        			valueFieldID: 'ghmcid',
        			textField: 'text',
        			selectBoxHeight:400,
        			selectBoxWidth:140,
                    hideOnLoseFocus:true,
                    autocomplete:true,
                    onSelected:function (data){
        				if (data != null && typeof(data)!="undefined" && data != ''){
        					setWellInitData($("#ghmcid").val(),proData);        					
        					}
        				}       			
        			}        		
        		);

		
			//关联只显示SGAD井下的管汇
			function setGHData(oilationnameid,proData) {
  			//alert(oilationnameid);
			jQuery.ajax({
				type : 'post',
				url:'sagd_queryGhmcInfo.action',
				data: {"oilationnameid":oilationnameid},	
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

      	$("#wellnum").ligerComboBox({
        			url:'sagd_queryWellNameInfo.action',
        			valueField: 'id',
        			valueFieldID: 'wellnumsid',
        			textField: 'text',
        			selectBoxHeight:350,
        			selectBoxWidth:100,
        			hideOnLoseFocus:true,
                    autocomplete:true,
                    onBeforeSelect:function (data){
                    	if(liger.get("oilationname").getValue() != ''){
                    		//liger.get("areablock1").setValue('');
                    	}
        			},
        			onSelected:function (data){
        			}
        		});
		
			   $("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
            $("#txtDate1").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  
        
       function getWellInitData() {
		$("#ghmc").ligerGetComboBoxManager().setValue('');
		$("#wellnum").ligerGetComboBoxManager().setValue('');
		var oilText=[{ id: 1 , text: '' }];
		var ghmcText=[{ id: 1 , text: '' }];
		var wellText=[{ id: 1 , text: '' }];
		jQuery.ajax({
			type : 'post',
			url : 'sagd_cascadeInit.action',
			success : function(jsondata) {
			var dataObj = $.parseJSON(jsondata);
				$.each(dataObj, function(key,val){
					if (key == 'oilText'){
						oilText = val;
					}
					if (key == 'ghmcText'){
						ghmcText = val;
					}
					if (key == 'welltext'){
						wellText = val;
					}
				});
				setInitData(oilText,ghmcText,wellText);
			}
		});
	}

      		
      		 //关联井号 和管汇	 
       		 function setWellInitData(data,proData,wellid) {
      		jQuery.ajax({
      			type : 'post',
      			url:'sagd_queryWellNameInfo.action',
      			data: {"ghmcid":data},
      			timeout : '3000',
      			success : function(jsondata) {
      				if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
      					liger.get("wellnum").setData(eval('(' + jsondata + ')'));
      				}
      				else{
      					liger.get("wellnum").setData(proData);
      				}
      			},
      			error : function(jsondata) {
      				alert("请求数据失败，请重新查询");
      			}
      		});
      		if (wellid != '') {
				$("#wellnum").ligerGetComboBoxManager().selectValue(wellid);
			}
      	}
	
	         function onSubmit()
        {
        	exportFlag = false;
        	
        	grid.setOptions({
        		parms: [
        		{
					name: 'oilationname',
					value: $("#oilationname").val()
				},{
					name: 'ghmc',
					value: $("#ghmc").val()
				},{
					name: 'wellnum',
					value: $("#wellnum").val()
				},{
					name: 'startDate',
					value: $("#txtDate").val()
				},{
					name: 'endDate',
					value: $("#txtDate1").val()
				},{
					name: 'search',
					value: 'search'
				}
				]
        	});
        	//setGridHid();
         	grid.loadData(true);
         	
        }
	
	
	
	
	function setInitData(oilText,stationText,wellText) {
		liger.get("oilationname").setData(oilText);
		liger.get("ghmc").setData(ghmcText);
		liger.get("wellnum").setData(wellText);
	}
      	
      function onExport() {
			var oilationname=$("#oilationname").val();
			var ghmc=$("#ghmc").val();
			var wellnum=$("#wellnum").val();
			var txtDate = $("#txtDate").val();
			var txtDate1 = $("#txtDate1").val();
			var gh='';
			if (exportFlag) {
				oilstationname=oilstation;
				areablock=areaname;
				blockstationname=stationname;
				gh=ghname;
				wellnum=wellname;
			}
			var totalNum = 0;
			var url = 'sagddrpd_searchSagdRPD.action?oilationname='+encodeURIComponent(oilationname)+'&ghmc='+encodeURIComponent(ghmc)+'&wellnum='+encodeURIComponent(wellnum)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum=export'+'&ghname='+gh;
			var urls = 'sagddrpd_searchSagdRPD.action?oilationname='+encodeURIComponent(oilationname)+'&ghmc='+encodeURIComponent(ghmc)+'&wellnum='+encodeURIComponent(wellnum)+'&startDate='+encodeURIComponent(txtDate)+'&endDate='+encodeURIComponent(txtDate1)+'&totalNum=totalNum'+'&ghname='+gh;
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

      
    function renderButton(rowdata, index, value) {
  	  var html = '<a href="javascript:onErom('+index+')" class="l-button" style="width:60px">功图</a>';
        return html;
    }
	function onErom(index) {
	 	var row = grid.getRow(index);
	 	var aname=row.JHMC;
    	var startime=$("#txtDate").val();
    	var endtime=$("#txtDate1").val();
	 	var url = 'page/dynamicdata/wellErom.jsp?str='+aname+'&startime='+startime+'&endtime='+endtime;
	 	
	 	var tab = parent.tab;
	 	var tabid = 'tabidaaaaaa'
	 	if(tab.isTabItemExist(tabid)) {
		 	tab.removeTabItem(tabid);
	 	}
 		tab.addTabItem({ tabid : tabid, text: '功图', url: url });
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
           
    </style>

</head>

<body style="overflow-x:hidden; padding:2px;">
	 <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
   		 <div class="searchbox">
        <form id="formsearch" class="l-form"> 
	        	<table border="0" cellspacing="1">
					<tr>
		               <td align="right" class="l-table-edit-td" style="width:60px">所属采油站：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="oilationname" name = "oilationname"/>	
		                </td>
		                <td align="left">
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:60px">管汇：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="ghmc" name = "ghmc"/>
		                </td>
		                <td align="left">
		                </td>
		                <td align="right" class="l-table-edit-td" style="width:60px">井号：</td>
		                <td align="left" class="l-table-edit-td" style="width:80px">
		                	<input type="text" id="wellnum" name = "wellnum" />
		                </td>
		                <td align="left">
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td valign="middle">--</td>
						<td><input type="text" id="txtDate1" ltype="date"/></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:80px">导出报表</a></td>

					</tr>
				
				</table>
				 </form>
		    
			</div>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
	  
		  
		   
    
</body>
</html>