<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
     <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
        <script src="../../lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../../lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../../lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="../../lib/json2.js" type="text/javascript"></script>
    <script type="text/javascript">
    var orgId = '${param.orgId}';
    var argp = '${param.argp}';
    var radioflg = 0;  //0:选择生成数据      1:手动录入数据 
        var g;
        $(function ()
        {
            g = $("#maingrid1").ligerGrid({
                columns: [
                 { display: '产量', name: 'cl', minWidth: 60 },
                { display: '采集时间', name: 'cjsj', align: 'left', width: 140,format:'yyyy-MM-dd hh:mm:ss'}
                ], pageSize: 10,checkbox: true,
                width: '100%', height: '80%',usePager :false,isChecked: f_isChecked

            });
            $.ajax({
				type : 'post',
				url : 'sagddrpdwh_queryGridDataInfo.action?orgId='+orgId,
				async : false,
				success : function(jsondata) {
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata != -1){
						//alert(jsondata);
						g.set({ data: { Rows: eval('(' + jsondata + ')')} });
					}else{
						g.set({ data:''});
					}
				},
				error : function(data) {
			
				}
			});
            $("#cl").focus().select();
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
                },
                submitHandler: function ()
                {
                	//1:手动录入数据
                	if(radioflg == 1){
                		if ($("#cl").val() != null) {
	                		 if (argp=='P') {
	                         	parent.pdailyOutput=$("#cl").val();
	             			}
	                         else {
	                         	parent.dailyOutput=$("#cl").val();
	                         }
						}
						parent.$.ligerDialog.close();
                		parent.$(".l-dialog,.l-window-mask").remove();
                	}else{
                		alert("请选择生成数据方式");
                	}
                	
                	
					//parent.window.onSubmit();
                }
            });
            $("form").ligerForm();
            $("#pageloading").hide();
        });
        function f_isChecked(rowdata)
        {
        	//alert(JSON2.stringify(rowdata));
            return true;
        }
        function radioclick(){
        	flg = true;
        	 //var str = "";
                 $("#d1 input:radio").each(function () {
                     flg = this.checked ;
                 });
                 if(flg){
                 	jQuery("#gridarea").css('display','block'); //显示
                 	jQuery("#tablearea").css('display','none');  //隐藏 
                 	radioflg = 0;
                 }else{
                	 jQuery("#tablearea").css('display','block');  //显示
                	jQuery("#gridarea").css('display','none');  //隐藏  
                	radioflg = 1;
                 }
               //  
        }
        
        function commitData()
        {
        	var manager = $("#maingrid1").ligerGetGridManager();
            var data = manager.getSelecteds();
            if (data != null && data != '') {
	            var clAvg=0;
	            var countSelectNums=0;
	            $(data).each(function (){
	            	clAvg=clAvg+this.cl;
	            	countSelectNums++;
	    		});
	            clAvg=clAvg/countSelectNums;
	            if (argp=='P') {
	            	parent.pdailyOutput=clAvg;
				}
	            else {
	            	parent.dailyOutput=clAvg;
	            }
			}
            parent.$.ligerDialog.close();
            parent.$(".l-dialog,.l-window-mask").remove();
        }
    </script>
     <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    </style>
</head>
<body style="padding: 6px; overflow: hidden;">
    <div class="l-loading" style="display: block" id="pageloading">
    </div>
    <form name="formsearch" method="post"  id="form1">
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div id="d1">
	     <table>
	        <tr><td><input type="radio"  name="putintype" checked="checked" onclick="radioclick()"/></td>
	        <td>选择生成数据&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        <td><input type="radio" name="putintype" onclick="radioclick()"/>
	        </td><td>手动录入数据&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        </tr>
	     </table>
  	 </div> 
		    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		    <div id="gridarea" style="display: block;">
		    		
		    				<div id="maingrid1" style="margin: 0; padding: 0"></div>
		    				<table>
		    					<tr>
		    						<td style="padding-left: 20px;text-align:center;" colspan="2"><a href="javascript:commitData()" class="l-button" style="width:100px">确定</a></td>
		    					</tr>
		    				</table>
		    	
		    	
		    </div>
		    	
		    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
		    
			    <div id="tablearea" style="display: none;">
			    	<table border="0" cellspacing="1">
						<tr>
			                <td align="right" class="l-table-edit-td" style="width:60px">产量：</td>
			                <td align="left" class="l-table-edit-td" style="width:80px">
			                	<input type="text" id="cl" name = "cl" ltype="number"/>
			                </td>
			                 <td align="left">
			                </td>
							<td valign="middle">--</td>
							<td style="padding-left: 20px;"><input type="submit" value="确认" id="Button1" class="l-button l-button-submit" /></td>
						
						</tr>
						<!--  <tr>
						 <td align="left">
			                </td>
							<td style="padding-left: 20px;text-align:center;" colspan="2"><a href="javascript:commitData()" class="l-button" style="width:100px">提交选择项</a></td>
						</tr> -->
					</table>
			    </div>
		        	
			<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
			
		</form>
    <div style="display: none;">
    </div>
</body>
</html>
