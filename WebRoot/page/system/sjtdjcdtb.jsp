<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>数据通断检查动态表</title>
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
        //鼠标选择数据
		var  edit_CODE;
        $(function () {
        
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'sjtdjctjb_DTpageInit.action',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(data);					
							//toolbar = grid.topbar.ligerGetToolBarManager();	
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
                //对象类型代码
    			$("#OBJECT_TYPE_NAME").ligerComboBox({
    				url:'sjtdjctjb_searchObjectName.action',
    	            valueField: 'id',
    				valueFieldID: 'objTypeId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    	            hideOnLoseFocus:true,
    	            autocomplete:true,
    	            onSelected:function (data){
    				if ($("#objTypeId").val() != '') {
    					setOnchangData($("#objTypeId").val());
    					}
    				}
    			});
                //对象名称
    			$("#OBJECT_NAME").ligerComboBox({
    				//url:'sjtdjctjb_searchObjectName.action',
    	            valueField: 'id',
    				valueFieldID: 'objId',
    				textField: 'text',
    				selectBoxHeight:200,
    				selectBoxWidth:100,
    	            hideOnLoseFocus:true,
    	            autocomplete:true
    			});

    			 $("#CHECK_DATE").ligerDateEditor(
    		                {
    		                    format: "yyyy-MM-dd ",
    		                  //  label: '格式化日期',
    		                    labelWidth: 100,
    		                    labelAlign: 'center',
    		                    cancelable : false
    		            });
		            
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
        
		
         function onSubmit()
        {
        	grid.setOptions({
        		parms: [
                		{name: 'OBJECT_TYPE_NAME',value: $("#objTypeId").val()},
                		{name: 'OBJECT_NAME',value: $("#OBJECT_NAME").val()},
        		        {name: 'CHECK_DATE',value: $("#CHECK_DATE").val()},
                		{name: 'DATA_INTERNUM1',value: $("#DATA_INTERNUM1").val()},
                		{name: 'DATA_INTERACT1',value: $("#DATA_INTERACT1").val()},
        		        {name: 'DATA_INTERNUM2',value: $("#DATA_INTERNUM2").val()},
        		        {name: 'DATA_INTERACT2',value: $("#DATA_INTERACT2").val()},
                		{name: 'DATA_INTERNUM3',value: $("#DATA_INTERNUM3").val()},
        		        {name: 'DATA_INTERACT3',value: $("#DATA_INTERACT3").val()}
				]
        	});
         	grid.loadData(true);
        }
         function setOnchangData(typeName) {
      		jQuery.ajax( {
      			type : 'post',
      			url : 'sjtdjctjb_searchOnChangeName.action',
      			data : {
      				"typeName" : typeName
      			},
      			success : function(jsondata) {
      				if (jsondata != null && typeof (jsondata) != "undefined"
      						&& jsondata != 0) {
      					liger.get("OBJECT_NAME").setData(eval('(' + jsondata + ')'));
      				} else {
      					liger.get("OBJECT_NAME").setData('');
      				}
      			},
      			error : function(jsondata) {
      			}
      		});
      	}
      function onExport() {
    	    var SYSTEM_CODE_Q 		= $("#SYSTEM_CODE_Q").val();
	        var POINT_TYPE_Q 		= $("#POINT_TYPE_Q").val();
	        var CREATE_DATE_Q 	= $("#CREATE_DATE_Q").val();
	        var FLOW_CODE_Q 	= $("#staid").val();
	        var ALARM_OR_Q 		= $("#ALARM_OR_Q").val();
	        var ALARM_CODE_Q 		= $("#ALARM_CODE_Q").val();
	        var DEVICE_CODE_Q 	= $("#DEVICE_CODE_Q").val();

	    	var  OBJECT_TYPE_NAME =  $("#objTypeId").val();
    		var  OBJECT_NAME  =  $("#OBJECT_NAME").val();
	        var  CHECK_DATE  =  $("#CHECK_DATE").val();
    		var  DATA_INTERNUM1 =  $("#DATA_INTERNUM1").val();
    		var  DATA_INTERACT1 =  $("#DATA_INTERACT1").val();
	        var  DATA_INTERNUM2 =  $("#DATA_INTERNUM2").val();
	        var  DATA_INTERACT2 =  $("#DATA_INTERACT2").val();
    		var  DATA_INTERNUM3 =  $("#DATA_INTERNUM3").val();
	        var  DATA_INTERACT3 =  $("#DATA_INTERACT3").val();

		var totalNum = 0;
		var url = "sjtdjctjb_searchDTDatas.action?OBJECT_TYPE_NAME="+encodeURIComponent(OBJECT_TYPE_NAME)+"&OBJECT_NAME="+encodeURIComponent(OBJECT_NAME)+"&CHECK_DATE="+encodeURIComponent(CHECK_DATE)
											  +"&DATA_INTERNUM1="+encodeURIComponent(DATA_INTERNUM1)+"&DATA_INTERACT1="+encodeURIComponent(DATA_INTERACT1)+"&DATA_INTERNUM2="+encodeURIComponent(DATA_INTERNUM2)
												   +"&DATA_INTERNUM3="+encodeURIComponent(DATA_INTERNUM3)+"&DATA_INTERACT3="+encodeURIComponent(DATA_INTERACT3)
												+'&totalNum=export';
		var urls = "sjtdjctjb_searchDTDatas.action?OBJECT_TYPE_NAME="+encodeURIComponent(OBJECT_TYPE_NAME)+"&OBJECT_NAME="+encodeURIComponent(OBJECT_NAME)+"&CHECK_DATE="+encodeURIComponent(CHECK_DATE)
		  +"&DATA_INTERNUM1="+encodeURIComponent(DATA_INTERNUM1)+"&DATA_INTERACT1="+encodeURIComponent(DATA_INTERACT1)+"&DATA_INTERNUM2="+encodeURIComponent(DATA_INTERNUM2)
		   +"&DATA_INTERNUM3="+encodeURIComponent(DATA_INTERNUM3)+"&DATA_INTERACT3="+encodeURIComponent(DATA_INTERACT3)
												+'&totalNum=totalNum';
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

<body style="overflow-x:hidden; padding:2px;">
	 <div id="mainsearch" style=" width:99%">
	 
	    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	    
	         <form name="formsearch" method="post"  id="form1">
	        	<table border="0" cellspacing="1">
					<tr>
						<td align="right" class="l-table-edit-td" style="width:100px">对象类型:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="OBJECT_TYPE_NAME" name ="OBJECT_TYPE_NAME" />
		                </td>
		                	<td align="right" class="l-table-edit-td" style="width:100px">对象名称:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input type="text" id="OBJECT_NAME" name ="OBJECT_NAME" />
		                </td>
		               <td align="right" class="l-table-edit-td" style="width:100px">检查时间:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="CHECK_DATE" name = "CHECK_DATE"/>
		                </td>
		               
		                <td align="right" class="l-table-edit-td" style="width:100px">单元1中断次数:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="DATA_INTERNUM1" name = "DATA_INTERNUM1"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:120px">单元1中断累计时长:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="DATA_INTERACT1" name = "DATA_INTERACT1"/>
		                </td>
		                </tr>
		                <tr>
		                <td align="right" class="l-table-edit-td" style="width:100px">单元2中断次数:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="DATA_INTERNUM2" name = "DATA_INTERNUM2"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:120px">单元2中断累计时长:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="DATA_INTERACT2" name = "DATA_INTERACT2"/>
		                </td>
		                 <td align="right" class="l-table-edit-td" style="width:100px">单元3中断次数:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="DATA_INTERNUM3" name = "DATA_INTERNUM3"/>
		                </td>
		                  <td align="right" class="l-table-edit-td" style="width:120px">单元3中断累计时长:</td>
		                <td align="left" class="l-table-edit-td"  >
		                	<input type="text" id="DATA_INTERACT3" name = "DATA_INTERACT3"/>
		                </td>
		                <td align="left" class="l-table-edit-td" >
		                	<a href="javascript:onSubmit()" class="l-button" style="width:60px">查询</a>
		                </td>
		                
		                <td align="left" class="l-table-edit-td" style="width:100px">
		                	<a href="javascript:onExport()" class="l-button" style="width:80px">导出报表</a>
		                </td>
					</tr>
				
				</table>
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		  
		  <div id="maingrid"></div> 
	  
		  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	  
		   <div id="edituser" style="width:100%;height: 100px; display:none;">
				<div id="errorLabelContainer">
				</div>
		        <table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		           
		        </table>
		       </div>
		    </form>
		    
		</div>
    
</body>
</html>