<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>SAGD18-3#站动态数据</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="../../lib/highcharts/highcharts.js"></script>
    <script src="../../lib/highcharts/exporting.js"></script>   
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
		var exportFlag = false;


			var  SHR ="";
	var  RQ ="";
	var ZBR1 = "";
	var ZBR2 = "";
	var  BZ1 ="";
	var  BZ2 ="";
	var EXPORTNAME ="";
	var mod ;
	var  LOG1;
	var  LOG2;
	var LOG3;
	var  waterA;
	var  waterB;
	var  waterC;
	var  waterD;
	var  WATERMESSAGEID ="";
	var RPD_MOLLIFIER_ID;
        $(function ()
        {
        	$("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
             var clzData = 
            [{ id: 1, text: '集中水'}, 
             { id: 2, text: '1#软化水站'},
             { id: 3, text:'2#软化水站'}
            ];
            var rhqData =
            [
            { id: 1, text: '1', pid: 1 },
            { id: 2, text: '2', pid: 1 },
            { id: 3, text: '3', pid: 1 }, 
 
            
            { id: 11, text: '一期1', pid: 2 }, 
            { id: 12, text: '一期2', pid: 2},
            { id: 13, text: '一期3', pid: 2 },
            { id: 14, text: '一期4', pid: 2 },
            { id: 15, text: '一期5', pid: 2 }, 
            { id: 16, text: '一期6', pid: 2},
            
            { id: 17, text: '二期1', pid: 2 },
            { id: 18, text: '二期2', pid: 2 },
            { id: 19, text: '二期3', pid: 2 },
            { id: 17, text: '二期4', pid: 2 },
            { id: 18, text: '二期5', pid: 2 },
            { id: 19, text: '二期6', pid: 2 },
            { id: 20, text: '二期7', pid: 2 },
            { id: 21, text: '二期8', pid: 2 },
            { id: 22, text: '二期9', pid: 2 },
            { id: 23, text: '二期10', pid: 2 },
            { id: 24, text: '二期11', pid: 2 },
			{ id: 25, text: '二期12', pid: 2 },

            { id: 25, text: '三期1', pid: 2 },
            { id: 26, text: '三期2', pid: 2 },
            { id: 27, text: '三期3', pid: 2 },
            { id: 28, text: '三期4', pid: 2 },
            { id: 29, text: '三期5', pid: 2 },
            { id: 30, text: '三期6', pid: 2 },
            
            { id: 31, text: '1', pid: 3 }, 
            { id: 32, text: '2', pid: 3},
            { id: 33, text: '3', pid: 3 }, 
            { id: 34, text: '4', pid: 3},
            { id: 35, text: '5', pid: 3 }, 
            { id: 36, text: '6', pid: 3},
            { id: 37, text: '7', pid: 3 }, 
            { id: 38, text: '8', pid: 3},
            { id: 39, text: '9', pid: 3 }, 
            { id: 40, text: '10', pid: 3},
            { id: 41, text: '11', pid: 3 }, 
            { id: 42, text: '12', pid: 3},
            { id: 43, text: '13', pid: 3 }, 
            { id: 44, text: '14', pid: 3},
            
            { id: 45, text: '216型1', pid: 3},
            { id: 46, text: '216型2', pid: 3 }, 
            { id: 47, text: '216型3', pid: 3}
            ];  
            
              $("#rhq").ligerComboBox({ data: null,isMultiSelect: false,  selectBoxWidth: 130,selectBoxHeight: 350 });
              $("#clz").ligerComboBox({
                data: clzData, isMultiSelect: false,selectBoxWidth:150,
                onSelected: function (newvalue)
                {
                    var newData = new Array();
                    for (i = 0; i < rhqData.length; i++)
                    {
                        if (rhqData[i].pid == newvalue)
                        {
                            newData.push(rhqData[i]);
                        }
                    }
                    liger.get("rhq").setData(newData);
                }
            });

		var line = 0;//0-显示动态数据  1-显示曲线
		Highcharts.setOptions({ global: { useUTC: false } }); 


            
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'rhqzs1_pageInit.action',
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


			
			  
			  
			   $("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
            $("#txtDate1").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
                    cancelable : false
            }).setValue(secondDate().Format("yyyy-MM-dd"));
            $("form").ligerForm();
            $(".l-button-test").click(function () {
            });
        });  



       function onSubmit()
        {
        	
        	grid.setOptions({
        		parms: [
					{
					name: 'clz',
					value: $("#clz").val()
				},{
					name: 'rhq',
					value: $("#rhq").val()
				},
        		{
					name: 'startDate',
					value: $("#txtDate").val()
				},{
					name: 'endDate',
					value: $("#txtDate1").val()
				}
				]
        	});
  
        	 	
         	grid.loadData(true);
        }

     
     	function onExport() {

		  var clz = $("#clz").val();
			var rhq = $("#rhq").val();
		   var txtDate = $("#txtDate").val();
       	   var txtDate1 = $("#txtDate1").val();		

		var totalNum = 0;


			var url = 'rhqzs1_searchRhqzs.action?totalNum=export'+'&clz='+encodeURIComponent(clz)+'&rhq='+encodeURIComponent(rhq)+'&txtDate='+encodeURIComponent(txtDate)+'&txtDate1='+encodeURIComponent(txtDate1);
			var urls = 'rhqzs1_searchRhqzs.action?totalNum=export'+'&clz='+encodeURIComponent(clz)+'&rhq='+encodeURIComponent(rhq)+'&txtDate='+encodeURIComponent(txtDate)+'&txtDate1='+encodeURIComponent(txtDate1);
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
				 $.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:green">' + totalNum + '</span>条',function (yes) { if(yes) window.open(url); });
			}else if(totalNum > 10000){
				$.ligerDialog.confirm('确定导出吗?<br/>您要导出的数据共有<span style="color:red">' + totalNum + '</span>条,<span style="color:red">将会占用较多内存</span>',function (yes) { if(yes) window.open(url);});
			}else{
				$.ligerDialog.confirm('没有可导出的数据！');
			};
		} ;
		

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
				
	                <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
					<td><input type="text" id="txtDate" ltype="date"/></td>
					<td valign="middle">--</td>
					<td><input type="text" id="txtDate1" ltype="date"/></td>
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>
						 
		                
						<td><input type="text" id="clz" ltype="sclz"/></td>
						 <td align="left" class="l-table-edit-td" style="width:80px">水处理站</td>
						 <td><input type="text" id="rhq" ltype="rhq"/></td>
						 <td align="left" class="l-table-edit-td" style="width:120px">号软化器 </td>
						 <td align="right" class="l-table-edit-td" style="width:10px"></td>

					<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:100px">查询</a></td>
<!--					 <td style="padding-left: 20px;"><a href="javascript:onExport()" class="l-button" style="width:100px">导出报表</a></td>			-->
				</tr>
			</table>
        </form>
    </div>
  <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
  <div id="maingrid"></div> 
 
</body>
</html>