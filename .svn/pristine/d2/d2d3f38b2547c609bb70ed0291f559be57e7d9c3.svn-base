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
     <script src="../../lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
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
    <!-- <script src="../../lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  -->  
    <link href="../../lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="../../lib/js/common.js" type="text/javascript"></script>   
    
    <script src="../../lib/json2.js" type="text/javascript"></script> 
    <script src="../../noBackspace.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	
    <script type="text/javascript">
    	var gltreeid = "MENU014";
    	var jktreeid = "MENU002";
    	var fenchanmoshiiframe;
    	var cellid ;
    	
    	var SPLIT_MODELFLG;  //分产模式 :0计量分配 1平均分配
    	
    	var boilerid ;
		var boilername ;
		var boileridtype ;
		
		var jklbid;
		var jklbname;
		var jklbtype;
    	var grid;
    	var gltreemanager;
        $(function ()
        {
        
        	$("#txtDate").ligerDateEditor(
                {
                    format: "yyyy-MM-dd",
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            }).setValue(myDate().Format("yyyy-MM-dd"));
            
           	//获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'boilerline_pageInit.action',
					async : false,
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(data);
							grid.set('height',document.body.clientHeight * 0.35);					
							//toolbar = grid.topbar.ligerGetToolBarManager();	
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
         	
   			jQuery("#edituser").css('height',document.body.clientHeight * 0.55);
   			
   		
	           var dataGridColumns = [
              
              	//{ header: '井名称id', name: 'id' ,hide: true},
              	//{ header: '路线节点id', name: 'noded' ,hide: true},
                { header: '井名称', name: 'text' },
                { header: '注汽分配系数', name: 'SPLIT_COEFFICIENT' },
                { header: 'P井注汽流量计', name: 'P_FLOWMETER' },
                { header: 'I井注汽流量计', name: 'I_FLOWMETER' }
            ];  
   			
            $("#listbox1").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: false,
                height: 140
            });
          
          	 $("#jklistbox").ligerListBox({
                textField: 'name',
                columns: dataGridColumns,
                isMultiSelect: false,
                width: 200,height:140
            });
          
            
            fenchanmoshiiframe = document.getElementById("fenchanmoshi1");
            
        });
        function moveToLeft()
        {
            var box1 = liger.get("listbox1");
            var selecteds = box1.getSelectedItems();
           // alert(JSON2.stringify(selecteds));
            if (!selecteds || !selecteds.length) return;
            box1.removeItems(selecteds);
           
           
            
        }
        function moveToRight()
        {
            var box1 = liger.get("listbox1"); 
            if(boileridtype == 10){
            	var gladdDate = [{ text: boilername, id: boilerid }];
            	//alert(JSON2.stringify(gladdDate));
            	box1.addItems(gladdDate);
            	
            }else{
            	$.ligerDialog.warn('请选择可选锅炉树中锅炉进行添加');
            }
        }
       
        function moveToLeftJK()
        {
           
            var box2 = liger.get("jklistbox");
            if(jklbtype == 9){
            	
            	//alert(JSON2.stringify(gladdDate));
            	if(cellid != null &&typeof(cellid)!="undefined"){
            		var jkaddDate = [{ text: jklbname, id: jklbid ,noded:cellid,SPLIT_COEFFICIENT:"",P_FLOWMETER:"",I_FLOWMETER:""}];
            	}else{
            		$.ligerDialog.warn('请选择一条路线单元数据进行添加井口');
            	}
            	box2.addItems(jkaddDate);
            	
            }else{
            	$.ligerDialog.warn('请选择可选井口树中井口进行添加');
            }
           
            
        }
        function moveToRightJK()
        {
            var box2 = liger.get("jklistbox");
            var selecteds = box2.getSelectedItems();
           // alert(JSON2.stringify(selecteds));
            if (!selecteds || !selecteds.length) return;
            box2.removeItems(selecteds);
           
        }
       
       
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
        }
        
           //工具条事件
      function itemclick(item) {
      		var rows = grid.getCheckedRows();
          switch (item.icon) {
              case "add":
              	   
            	  if(operate != 1 && operate != 2){
            		  setpage2(0); //显示编辑界面
  	              	setItemsd(0);//0-显示编辑区，添加隐藏按钮
  	              }
              	  
              	   operate = 1;
              	   assignM(1);
              	   
              	   
                  break;
              case "modify":
                   if (rows.length == 0) { 
              	    		alert('请选择一条你要修改信息的数据！');
              	    
              	     }else if(rows.length == 1){
							
              	    	 if(operate != 1 && operate != 2){
              	    		setpage2(0); //显示编辑界面
         	              	setItemsd(0);//0-显示编辑区，添加隐藏按钮
         	              }
		              	   operate = 2;
		              	   assignM(2);
              	     		
              	     }else{
              	     	alert('请选择一条你要修改信息的数据！');
              	     }
                  break;
              case "delete":
              	  if (rows.length == 0) { 
              	    		alert('请选择一条你要删除的数据！')
              	     }else if(rows.length == 1){
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'thinoil_removeThinoil.action',
											async : false,
											data: {"wellid":wellid,"org_id":org_id},
											success : function(data) { 
											if (data != null && typeof(data)!="undefined" && data == "1"){
													
													onSubmit();
													assignM(1);
												}else{
													$.ligerDialog.error(outerror(jsondata));
												}
											},
											error : function(data) {
										
											}
										});
		                        	 } 
              	     		 });
							
							

              	     }else{
              	     	alert("请选择一条你要删除的数据！");
              	     }
                  break;
              case "save":
					$("#form1").submit();
					break;
              case "up":
            	  setpage2(0); //显示编辑界面
            	    setItemsd(0);//0-显示编辑区，添加隐藏按钮
                break;   
                case "down":
                	setpage2(1); //隐藏编辑界面
				 	setItemsd(1);//1-隐藏编辑区添加显示按钮
                break;    
          }
      }
      
        
       	 function fromAu(rowdata){
        	//获取炉线锅炉及井口数据
			var CELL_ID = rowdata.CELL_ID;
			var SPLIT_MODEL = rowdata.SPLIT_MODEL;
			cellid = CELL_ID;
			//var SPLIT_MODELFLG;  //分产模式 :0计量分配 1平均分配
			if(SPLIT_MODEL == '计量分配'){
				SPLIT_MODELFLG = 0;
				
			}else{
				SPLIT_MODELFLG = 1;
			}
			 	jQuery.ajax({
					type : 'post',
					url : 'boilerline_seachGLJKS.action?nowdata='+parseInt(Math.random()*100000),
					data: {"CELL_ID":CELL_ID},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" ){
							alert(jsondata);
								if(jsondata < 0){
									 $.ligerDialog.error(outerror(jsondata));
								}else{
								
									var data = eval('(' + jsondata + ')');
								    var gldata = data.GLDATA;
								    var jkdata = data.JKDATA;
								    
								    alert(JSON2.stringify(jkdata));
								    if(gldata != null && typeof(gldata)!="undefined"){
								    	liger.get("listbox1").setData(gldata);
								    }else{
								    	liger.get("listbox1").clearContent() ;
								    }
								    
								    if(jkdata != null && typeof(jkdata)!="undefined"){
								    	liger.get("jklistbox").setData(jkdata);
								    }else{
								    	liger.get("jklistbox").clearContent() ;
								    	fenchanmoshiiframe.p_flowmeterlist.setValue(''); 
								    	fenchanmoshiiframe.p_flowmeterlist.setValue(''); 
								    	fenchanmoshiiframe.document.getElementById("SPLIT_COEFFICIENT").value= "";
								    }
								
								}
						}else{
							liger.get("listbox1").clearContent() ;
							liger.get("jklistbox").clearContent() ;
							
							fenchanmoshiiframe.p_flowmeterlist.setValue(''); 
					    	fenchanmoshiiframe.p_flowmeterlist.setValue(''); 
					    	fenchanmoshiiframe.document.getElementById("SPLIT_COEFFICIENT").value= "";
							
						}
						
							//liger.get("listbox1").refresh() ;
							//liger.get("jklistbox").refresh() ;
						
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
		                 <td align="right" class="l-table-edit-td" style="width:40px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:80px">查询</a></td>
						<td style="padding-left: 20px;"><a href="javascript:onSubmit()" class="l-button" style="width:120px">SAGD井注汽量分配</a></td>
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
<div id="maingrid" ></div> 
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
<div id="edituser" style="" style="width:100%;">

<div style="width:370px; margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
		<table>
			<tr>
				<td>锅炉列表</td>
				<td></td>
				<!-- <td>可选锅炉树</td> -->
				<td>可选锅炉树</td>
			
			</tr>
			<tr>
				<td>
					<div style="margin:4px;float:left;">
		         		<div id="gltreehi" style="width:150px;height:140px; margin:4px; float:left; border:1px solid #ccc; overflow:auto;">
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
				<!-- <td>
					<div style="margin:4px;float:left;">
				        <div id="listbox2"></div> 
				    </div>
				</td> -->
				<td>
				
					<div id="listbox1"></div>  
					
				</td>
			
			</tr>
		
		</table>
</div>
<div style="width:660px;  margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
	<table border="0">
	<tr>
		
		<td>井口列表</td>
		<td></td>
		<td>可选井口树</td>
		<td>分产模式设置</td>
		<!-- <td rowspan="2">
			

		</td> -->
	
	</tr>
	<tr>
		<td>
			<div style="margin:4px;float:left;">
		         <div id="jklistbox" ></div>  
		    </div>
		</td>
		<td>
				<div style="margin:4px;float:left;" class="middle">
	    	  <input type="button" onclick="moveToLeftJK()" value="&lt;" />
	          <input type="button" onclick="moveToRightJK()" value=">" />
	         	
	     </div>
		</td>
		<td>
		    
		    <div id="jktreehi" style="width:150px;height:140px; margin:4px; float:left; border:1px solid #ccc; overflow:auto;">
				<iframe frameborder="2" name="kxjktree" src="kxjktree.jsp" style="height: 1200px"></iframe> 
			</div> 
		</td>
		<td>
			<div id="fenchanmoshi" style="width:150px;height:140px; margin:4px; float:left; border:1px solid #ccc; ">
				<iframe frameborder="3" name="fenchanmoshi1" id="fenchanmoshi1" src="fenchanmoshi.jsp" ></iframe> 
			</div>
		
		</td>
	
	</tr>

</table>
</div>


     
  </div>   
    
    
     
  </form>
    
</body>
</html>
