<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>炉线管理</title>
   
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="../../lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>  
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/core/inject.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>  
    <script src="../../lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
    
    <script src="../../lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
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
            
            $("#boilerlinebox").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: false,
                height: 140
            });
         
            $("#listbox1").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: false,
                height: 140
            });
            
            $("#listbox2").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: false,
                height: 140
            });
            
            $("#jklistbox").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: false,
                height: 140
            });
            
            $("#kxjklistbox").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: false,
                height: 140
            });

            var data = [
                    { text: '张三', id: '1' },
                    { text: '李四', id: '2' },
                    { text: '赵武2', id: '3' },
                    { text: '赵武3', id: '4' },
                    { text: '赵武4', id: '5' },
                    { text: '赵武5', id: '6' },
                    { text: '赵武6', id: '7' },
                    { text: '赵武7', id: '8' }
            ];
            liger.get("boilerlinebox").setData(data);
            liger.get("listbox1").setData(data);
            liger.get("jklistbox").setData(data);
            
        });
        function moveToLeft()
        {
            var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
            var selecteds = box2.getSelectedItems();
            if (!selecteds || !selecteds.length) return;
            box2.removeItems(selecteds);
            box1.addItems(selecteds);
        }
        function moveToRight()
        {
            var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
            var selecteds = box1.getSelectedItems();
            if (!selecteds || !selecteds.length) return;
            box1.removeItems(selecteds);
            box2.addItems(selecteds);
        }
        function moveAllToLeft()
        { 
            var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
            var selecteds = box2.data;
            if (!selecteds || !selecteds.length) return;
            box1.addItems(selecteds);
            box2.removeItems(selecteds); 
        }
        function moveAllToRight()
        { 
            var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
            var selecteds = box1.data;
            if (!selecteds || !selecteds.length) return;
            box2.addItems(selecteds);
            box1.removeItems(selecteds);
           
        }
    </script>
    <style type="text/css">
        .middle input {
            display: block;width:30px; margin:2px;
        }
    </style>
</head>
<body style="padding:10px"> 
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
				<table>
					<tr>	
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">添加</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">删除</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">保存</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">还原</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:120px">SAGD井注汽量分配</a></td>
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
	<div style="margin:4px;float:left;">
		<table>
		<tr>
			<td>
				路线列表
			</td>
		</tr>
		<tr>
			<td>
				<div id="boilerlinebox"></div>  
			</td>
		</tr>
		
		</table>
	</div>
	
	<div style="margin:4px;float:left;">
		<table cellpadding="0" cellspacing="0" class="l-table-edit" border="0">
		            <tr>
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">炉线单元名称:</td>
		               <td align="left" class="l-table-edit-td" >
		                	<input name="CELL_NAME" type="text" id="CELL_NAME"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">炉线名称:</td>
		               <td align="left" class="l-table-edit-td" >
		                	<input name="LXMC" type="text" id="LXMC"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                
		                <td align="left" class="l-table-edit-td" style="width:150px">所属单位:</td>
		                <td align="left" class="l-table-edit-td" >
		                	<input name="OWNER_ORG" type="text" id="OWNER_ORG"  ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
		                </td>
		                </tr>
		                
		                <tr>
		                	<td align="left" class="l-table-edit-td" style="width:150px">注汽分配系数:</td>
		                	<td align="left" class="l-table-edit-td" >
		                	<input name="SPLIT_COEFFICIENT" type="text" id="SPLIT_COEFFICIENT"  ltype="text"  />
		                </td>
			                <td align="left" class="l-table-edit-td" style="width:150px">P井注汽流量计:</td>
			                <td align="left" class="l-table-edit-td" >
		                	<input name="P_FLOWMETER" type="text" id="P_FLOWMETER"  ltype="text"  />
		                </td>
			               	<td align="left" class="l-table-edit-td" style="width:150px">I井注汽流量计:</td>
			               	<td align="left" class="l-table-edit-td" >
		                	<input name="I_FLOWMETER" type="text" id="I_FLOWMETER"  ltype="text"  />
		                </td>
		                </tr>
		</table>	
	</div>
     

<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>

<table>
	<tr>
		<td>锅炉列表</td>
		<td></td>
		<td>可选锅炉树</td>
		<td>井口列表</td>
		<td></td>
		<td>可选井口树</td>
	
	</tr>
	<tr>
		<td>
			<div style="margin:4px;float:left;">
         		<div id="listbox1"></div>  
    		 </div>
		</td>
		<td>
			<div style="margin:4px;float:left;" class="middle">
		    	  <input type="button" onclick="moveToLeft()" value="&lt;" />
		          <input type="button" onclick="moveToRight()" value=">" />
    		 </div>
		</td>
		<td>
			<div style="margin:4px;float:left;">
		        <div id="listbox2"></div> 
		    </div>
		</td>
		<td>
			<div style="margin:4px;float:left;">
		         <div id="jklistbox"></div>  
		    </div>
		</td>
		<td>
				<div style="margin:4px;float:left;" class="middle">
	    	  <input type="button" onclick="moveToLeft()" value="&lt;" />
	          <input type="button" onclick="moveToRight()" value=">" />
	         	
	     </div>
		</td>
		<td>
			<div style="margin:4px;float:left;">
        <div id="kxjklistbox"></div> 
    </div>
		</td>
	
	</tr>

</table>
     
     
    
    
     
  </form>
    
</body>
</html>
