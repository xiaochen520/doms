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
    	//var fenchanmoshiiframe;
    	var tabledate;
    	var cellid ;
    	
    	var SPLIT_MODELFLG;  //分产模式 :0计量分配 1平均分配
    	
    	var boilerid ;
		var boilername ;
		var boileridtype ;
		
		var jklbid;
		var jklbname;
		var jklbtype;
    	var grid;
    	var jkgrid;
    	var jkmanager ;
    	var gltreemanager;
    	//var p_flowmeterlist;
   		var i_flowmeterlist;
		var modeData = [{ mode: 1, text: '计量分配' }, { mode: 2, text: '平均分配'}];
		var nulljson1 = [{ id: '', text: ''}];
		var nulljson2 = [{ id: '', text: '',noded:'',nodkid:'',SPLIT_COEFFICIENT:'',P_FLOWMETER:'',I_FLOWMETER:'' }];
		
		var I_FLOWMETERData = [{ I_FLOWMETER: 1, text: '1号流量计' }, { I_FLOWMETER: 2, text: '2号流量计'}, { I_FLOWMETER: 3, text: '双流量计'}];
		var P_FLOWMETERData = [{ P_FLOWMETER: 1, text: '1号流量计' }, { P_FLOWMETER: 2, text: '2号流量计'}, { P_FLOWMETER: 3, text: '双流量计'}];
		//var CustomersData = { Rows: [{ "WELL_CELL_ID": 1, "ORG_ID": "2","CELL_ID": "3","STRUCTURENAME":"fwh4443","SPLIT_COEFFICIENT": 1, "P_FLOWMETER": 1, "I_FLOWMETER": 2 }], Total: 1 };
		//var bdb={ Rows: [{ "WELL_CELL_ID": 1, "ORG_ID": "2","CELL_ID": "3","STRUCTURENAME":"fwh4443","SPLIT_COEFFICIENT": 1, "P_FLOWMETER": 1, "I_FLOWMETER": 2 },{ "WELL_CELL_ID": 1, "ORG_ID": "2","CELL_ID": "3","STRUCTURENAME":"fwh4443","SPLIT_COEFFICIENT": 1, "P_FLOWMETER": 1, "I_FLOWMETER": 2 }], Total: 2 };
		var ownerData;
		var gllistbox;
        $(function ()
        {
        	seachSelectData();
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
												
							//toolbar = grid.topbar.ligerGetToolBarManager();	
							toolbar = grid.topbar.ligerGetToolBarManager();	
						
							if (toolbar != null && typeof(toolbar)!="undefined"){
								var toolteams = toolbar.options.items;
								for(var i=0; i<toolteams.length ; i++){
									if(toolteams[i].id == 'allocationid'){
										toolbar.removeItem("allocationid"); 
										jQuery("#sagdbtn").css('display','block');
										
									}
								}
							}
							
							
							
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
				grid.set('height',document.body.clientHeight * 0.35);
   				jQuery("#edituser").css('height',document.body.clientHeight * 0.55);
   				
   				 jkgrid = $("#jkmaingrid").ligerGrid({
	                columns: [
	                { display: '炉线井口ID', name: 'WELL_CELL_ID', width: 50,hide: true},
	                { display: '井口ID', name: 'ORG_ID', width: 50, hide: true},
	                { display: '炉线ID', name: 'CELL_ID', width: 50, hide: true},
	                { display: '井号名称', name: 'STRUCTURENAME', width: 80},
	                { display: '注汽分配系数', name: 'SPLIT_COEFFICIENT', width: 80,  editor:{ type: 'float' } },
	                { display: 'P井注汽流量计', width: 80, name: 'P_FLOWMETER',
	                    editor: { type: 'select', data: P_FLOWMETERData, valueField: 'P_FLOWMETER'},
	                    render: function (item)
	                    {
	                        if (parseInt(item.P_FLOWMETER) == 1) return '1号流量计';
	                        else if (parseInt(item.P_FLOWMETER) == 2) return '2号流量计';
	                        else if (parseInt(item.P_FLOWMETER) == 3) return '双流量计';
	                        else return '';
	                    }
	                },
	                
	               { display: 'I井注汽流量计', width: 80, name: 'I_FLOWMETER',
	                    editor: { type: 'select', data: I_FLOWMETERData, valueField: 'I_FLOWMETER' },
	                    render: function (item)
	                    {
	                        if (parseInt(item.I_FLOWMETER) == 1) return '1号流量计';
	                        else if (parseInt(item.I_FLOWMETER) == 2) return '2号流量计';
	                        else if (parseInt(item.I_FLOWMETER) == 3) return '双流量计';
	                        else return '';
	                    }
	                }],
	                onSelectRow: function (rowdata, rowindex)
	                {
	                    $("#txtrowindex").val(rowindex);
	                },
	                enabledEdit: true, rownumbers:true,
	                width: '100%',
	                height:'98%',
	                usePager : false
	                //pageSize :50
	            }); 
	   			
	         jkmanager = $("#jkmaingrid").ligerGetGridManager();
	         
   			
            gllistbox = $("#listbox1").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: false,
                height: 210,
                valueFieldID: 'test4'
            });
          
          
          
           $("#sagdbtn").ligerTip();
           //var date21 = myDate().Format("yyyy年MM月dd日");
          // $("#jieshi").html("&nbsp;&nbsp;<font color='red'>注汽量分配： SAGD井"+date21+"注汽量分配</font>");
            // $("#jieshi").html("&nbsp;&nbsp;<font color='red'>注汽量分配： SAGD井当日注汽量分配</font>");
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
            	var gladdDate = [{ text: boilername, id: boilerid,noded:"",nodkid:cellid  }];
            		if(cellid != null &&typeof(cellid)!="undefined"){
            		var flag = 0;
            		var gldata = box1.data;
            		
            		if(gldata != null && typeof(gldata)!="undefined"){
            			for( var i= 0;i<gldata.length ;i++){
	           				if(boilerid == gldata[i].id){
	           					flag = 1;	
	           				 }
	           			}
            		}
           			
           			if(flag == 0){
           				var gladdDate = [{ text: boilername, id: boilerid }];
		            	box1.addItems(gladdDate);
           			}else{
           				$.ligerDialog.warn('您选择的锅炉已存在该炉线单元节点中');
           			}
            	}else{
            		$.ligerDialog.warn('请选择一条炉线单元数据进行添加锅炉');
            	}
            	
            	
            }else{
            	$.ligerDialog.warn('请选择可选锅炉树中锅炉进行添加');
            }
        }
       
        function moveToLeftJK()
        {
           
          
            if(jklbtype == 9){
            	
            	if(cellid != null &&typeof(cellid)!="undefined"){
           			var flag = 0;
          				var jkdata = jkmanager.getData();
          				
            		if(jkdata != null && typeof(jkdata)!="undefined"){
            			for( var i= 0;i<jkdata.length ;i++){
	           				if(jklbid == jkdata[i].ORG_ID){
	           					flag = 1;	
	           				 }
	           			
	           			}
        	   		}
           			if(flag == 0){
           				  jkmanager.addRow({
			                WELL_CELL_ID : '',
			                ORG_ID: jklbid,
			                CELL_ID: cellid,
			                STRUCTURENAME:jklbname
			            });
        
           			}else{
           				$.ligerDialog.warn('您选择的井口已存在该炉线单元节点中');
          			}
            		
           	}else{
            		$.ligerDialog.warn('请选择一条路线单元数据进行添加井口');
            	}
            }else{
            	$.ligerDialog.warn('请选择可选井口树中井口进行添加');
            }
           
            
        }
        function moveToRightJK()
        {
       		 jkmanager.deleteSelectedRow();
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
         	clearText();
        }
		//SAGD注汽分配
		function SAGDFP(){
			jQuery.ajax({
				type : 'post',
				url : 'boilerline_getSAGDFP.action',
				async : false,
				success : function(data) {
					//ownerData=JSON2.stringify(data);
					//if (data != null && typeof(data)!="undefined"){
						//if(data < 0){
						//	$.ligerDialog.error(outerror(data));
						//}else{
						//	$.ligerDialog.success(data);
						//}
					//}
					if (data != null && typeof(data)!="undefined"){
							var outData = eval('(' + data + ')');
							if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.warn(outData.ERRMSG);
							}else{
								$.ligerDialog.success("操作成功");
							}
					}
				},
				error : function(data) {
			
				}
			});
		}
          function seachSelectData()
          {
			jQuery.ajax({
				type : 'post',
				url : 'boilerline_seachSelectData.action',
				async : false,
				//data: {"wellid":wellid,"org_id":org_id},
				success : function(data) {
					//ownerData=JSON2.stringify(data);
					ownerData=eval('(' + data + ')');
				},
				error : function(data) {
			
				}
			});
          }
           //工具条事件
      function itemclick(item) {
       	$("#txtDate").focus();
      		var rows = grid.getCheckedRows();
      		var myDate = new Date();
			var myDayStr = myDate.Format("yyyy-MM-dd");
          switch (item.icon) {
              case "add":
            	  grid.addRow({
            		  CELL_ID:'',
            		  CELL_NAME: '',
            		  SPLIT_MODEL: '计量分配',
            		  VALID_DATE: myDayStr,
            		  OWNER_ORG: '',
            		  RLAST_OPER : '',
            		  RLAST_ODATE : '',
                     // IncomeDay: new Date(1306108800000),
                      REMARK : ''
                  });
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
              	    	$(rows).each(function (){
    					//var myDate2 = new Date(this.VALID_DATE);
    					if (this.VALID_DATE != myDayStr) {
							$.ligerDialog.error("不能删除日期不为当天的数据!");
						}else{
							$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'boilerline_removeLX.action',
											async : false,
											data: {"CELL_ID":cellid},
											success : function(data) { 
												if (data != null && typeof(data)!="undefined"){
													var outData = eval('(' + data + ')');
													if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
														$.ligerDialog.error(outerror(outData.ERRCODE));
													}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
														$.ligerDialog.error(outData.ERRMSG);
													}else{
														
														onSubmit();
														assignM(1);
														$.ligerDialog.success('炉线基础删除成功！');
													}
														
													}
												},
											error : function(data) {
										
											}
										});
		                        	 } 
              	     		 });
						}
						});

              	     }else{
              	     	alert("请选择一条你要删除的数据！");
              	     }
                  break;
              case "save":
              	
            	var lb = JSON2.stringify(liger.get("listbox1").data);
            	var tb = JSON2.stringify(jkmanager.getData());
					var rows = grid.getCheckedRows();
					if (rows.length != 1) {
						alert("请选择一条你需更新或添加的数据！");
						return;
					}
				
					$(rows).each(function (){
					
						if (this.__status == 'update') {
							var toolteams = toolbar.options.items;
							var authorityflg = judgeAuthority(toolteams,2);
							if(authorityflg == 0){
								$.ligerDialog.error("您无修改权限，请联系管理员添加权限!");
								return;
							}
							
						}
						if (this.VALID_DATE != myDayStr) {
							$.ligerDialog.error("不能修改日期不为当天的数据!");
						}else{
							var updateRowDate = JSON2.stringify(rows);
					jQuery.ajax({
						type : 'post',
						url : 'boilerline_addOrUpdateBoilerLine.action',
						data: {"rowData":updateRowDate,"listbox1":lb,"listbox12":tb},
						async : false,
						success : function(data) { 
							if (data != null && typeof(data)!="undefined"){
										var outData = eval('(' + data + ')');
										if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
											$.ligerDialog.error(outerror(outData.ERRCODE));
										}else if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
											$.ligerDialog.error(outData.ERRMSG);
										}else{
											onSubmit();
											$.ligerDialog.success('保存成功！');
										}
								}
						},
						error : function(data) {
						}
					});
						}
					});
					
					break;
               
          }
      }
      
        
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
        	
        	//清空列表和分产模式
        	function clearText(){
        		cellid = "";
		    	SPLIT_MODELFLG ="";  //分产模式 :0计量分配 1平均分配
		    	boilerid ="";
				boilername ="";
				boileridtype ="";
				
				jklbid = "";
				jklbname = "";
				jklbtype = "";
				liger.get("listbox1").setData(nulljson1) ;
				jkgrid.set({ data: { Rows: [{}], Total: 0  }}); 
		   		
        		
        	}
        	//井口 分产变更
        	function jkonchange(){
        		var hidid = $("#hidjkid").val();
        		var splitcoefficient = $("#SPLIT_COEFFICIENT").val();
        		var pflowmeter = $("#P_FLOWMETER").val();
        		var iflowmeter = $("#I_FLOWMETER").val();
        		
        		var jklistbox2 = liger.get("jklistbox");

				var selecteds = jklistbox2.getSelectedItems();
				if (!selecteds || !selecteds.length) return;
				//alert(JSON2.stringify(selecteds[0].text));
				var jkdata = jklistbox2.data;
           			for( var i= 0;i<jkdata.length ;i++){
           				if(selecteds[0].id == jkdata[i].id){
           					jkdata[i].SPLIT_COEFFICIENT = splitcoefficient;
           					jkdata[i].P_FLOWMETER = pflowmeter;
           					jkdata[i].I_FLOWMETER = iflowmeter;
           				}
           			}
				//var jkaddDate = [{ text: selecteds[0].text, id: selecteds[0].id ,noded:selecteds[0].noded,SPLIT_COEFFICIENT:splitcoefficient,P_FLOWMETER:pflowmeter,I_FLOWMETER:iflowmeter}];
            	liger.get("jklistbox").setData(jkdata);
            	//jklistbox2.removeItems(selecteds);
            	
            	//jklistbox2.addItems(jkaddDate);
        		
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
						<td style="padding-left: 20px;"><a href="javascript:SAGDFP()" class="l-button" style="width:120px;display:none" id="sagdbtn" title="注汽量分配： SAGD井当日注汽量分配; " >注汽量分配</a> </td>
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
<div id="maingrid" ></div> 
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
