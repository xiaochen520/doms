<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>炉线管理</title>
   
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
   
 	 <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
     <script src="lib/ligerUI/js/core/base.js" type="text/javascript"></script>
     <script src="lib/ligerUI/js/core/inject.js" type="text/javascript"></script>
     <script src="lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    
    <script src="lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <!-- <script src="lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="lib/js/common.js" type="text/javascript"></script>   
    
    <script src="lib/json2.js" type="text/javascript"></script> 
    <script src="noBackspace.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="lib/myday.js" type="text/javascript"></script>
 	
    <script type="text/javascript">
    	
        $(function ()
        {
        	
            
           	//获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'boilerline_pageInit.action',
					async : false,
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
   				
   				 
	   			
	         
   			
            gllistbox = $("#listbox1").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: false,
                height: 210,
                valueFieldID: 'test4'
            });
          
          
          
           $("#sagdbtn").ligerTip();
        });
      
          function onSubmit()
        {
        	grid.setOptions({
        		parms: [{
					name: 'txtdate',
					value: $("#txtDate").val()
				}
				]
        	});
         	grid.loadData(true);
         	clearText();
        }
           //工具条事件
     
        
       	 function fromAu(rowdata){
        	//获取炉线锅炉及井口数据
			var CELL_ID = rowdata.CELL_ID;
			var SPLIT_MODEL = rowdata.SPLIT_MODEL;
			cellid = CELL_ID;
			tabledate = rowdata.VALID_DATE;
			 	jQuery.ajax({
					type : 'post',
					url : 'boilerline_seachGLJKS.action?nowdata='+parseInt(Math.random()*100000),
					data: {"CELL_ID":CELL_ID},
					success : function(jsondata) { 
					 //var box1 = liger.get("listbox1"); 
					  //var gldata = box1.data;
					if (jsondata != null && typeof(jsondata)!="undefined" ){
							//alert(jsondata);
								if(jsondata < 0){
									 $.ligerDialog.error(outerror(jsondata));
								}else{
								
									var data = eval('(' + jsondata + ')');
								    var gldata = data.GLDATA;
								    var jkdata = data.JKDATA;
								    
								    //alert(JSON2.stringify(gldata));
								    if(gldata != null && typeof(gldata)!="undefined"){
								    	//liger.get("listbox1").setData(gldata);
								    	gllistbox.setData(gldata);
								    }else{
								    	//liger.get("listbox1").setData(nulljson1) ;
								    	gllistbox.setData(null);
								    	//liger.get("listbox1").clearContent() ;
										//liger.get("listbox1").clear();
										//liger.get("listbox1").refresh() ;
								    	  //if(gldata != null && typeof(gldata)!="undefined"){
								    	  	//  box1.removeItem(box1.data);
								    	 // }
								    }
								    
								    if(jkdata != null && typeof(jkdata)!="undefined"){
										jkgrid.set({ data: jkdata }); 
								    }else{
								    	jkgrid.set({ data: { Rows: [{}], Total: 0  }}); 
								    
								    }
								
								}
						}else{
							//liger.get("listbox1").setData(nulljson1) ;
							gllistbox.setData(null);
							//liger.get("listbox1").clearContent() ;
							//liger.get("listbox1").clear();
							//liger.get("listbox1").refresh() ;
							 // if(gldata != null && typeof(gldata)!="undefined"){
							  			//for(var i = 0; i<gldata.length;i++)
								//    	  	 box1.removeItem(box1.data);
								//  }
							jkgrid.set({ data: { Rows: [{}], Total: 0  }}); 
						
							
						}
					
					},
					error : function(data) {
				
					}
				});		
        						
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
		                 <td align="right" class="l-table-edit-td" style="width:40px">和值：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
						<td style="padding-left: 20px;"><a href="javascript:SAGDFP()" class="l-button" style="width:120px;display:none" id="sagdbtn" title="注汽量分配： SAGD井当日注汽量分配; " >注汽量分配</a> </td>
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
<div id="edituser" style="" style="width:100%;">
<table border="0">
	<tr>
		<td>
			<div style="width:370px; margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
					<table>
						<tr>
							<td>可选锅炉树</td>
							<td></td>
							<!-- <td>可选锅炉树</td> -->
							<td>锅炉列表</td>
						
						</tr>
						<tr>
							<td>
								<div style="margin:5px;float:left;">
					         		<div id="gltreehi" style="width:160px;height:210px; margin:5px; float:left; border:1px solid #ccc; overflow:auto;">
									<iframe frameborder="1" name="kxgltree" src="kxgltree.jsp" style="height: 1200px"></iframe> 
									</div> 
					    		 </div>
							</td>
							<td>
								<div style="margin:4px;float:left;" class="middle">
							    	  <input type="button" onclick="moveToLeft()" value="&lt;" />
							          <input type="button" onclick="moveToRight()" value=">" />
					    		 </div>
							</td>
							<td>
								<div id="listbox1" style="margin:5px;float:left;"></div>  
							</td>
						
						</tr>
					
					</table>
			</div>
			<div style="width:620px;  margin:10px; float:left; border:1px solid #ccc;   ">
				<table border="0">
				<tr>
					
					<td>井口列表</td>
					<td><input name="hidjkid" type="hidden" id="hidjkid"  /></td>
					<td>可选井口树</td>
				</tr>
				<tr>
					<td>
						<div style="width: 370px;height: 210px;margin:5px;border:1px solid #ccc;" >
					         <div id="jkmaingrid" style="width: 370px;height: 200px;"></div> 
					    </div>
					</td>
					<td>
						 <div style="margin:4px;float:left;" class="middle">
				    	  <input type="button" onclick="moveToLeftJK()" value="&lt;" />
				          <input type="button" onclick="moveToRightJK()" value=">" />
				         	
				     </div>
					</td>
					<td>
					    
					    <div id="jktreehi" style="width:200px;height:210px; margin:5px; float:left; border:1px solid #ccc; overflow:auto;">
							<iframe frameborder="2" name="kxjktree" src="kxjktree.jsp" style="height: 1200px"></iframe> 
						</div> 
					</td>
				</tr>
			
			</table>
			</div>
		</td>
	</tr>
</table>



     
</div>   
    
    
     
  </form>
    
</body>
</html>
