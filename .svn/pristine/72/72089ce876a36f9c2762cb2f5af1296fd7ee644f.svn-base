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
    	var p_flowmeterlist;
   		var i_flowmeterlist;
		var modeData = [{ mode: 1, text: '计量分配' }, { mode: 2, text: '平均分配'}];
		var nulljson1 = [{ id: '', text: '',noded:'',nodkid:'' }];
		var nulljson2 = [{ id: '', text: '',noded:'',nodkid:'',SPLIT_COEFFICIENT:'',P_FLOWMETER:'',I_FLOWMETER:'' }];
		//var bolierLineName;
		var ownerData;
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
                height: 210,
                valueFieldID: 'test4'
            });
          
          	 $("#jklistbox").ligerListBox({
                textField: 'name',
                columns: dataGridColumns,
                isMultiSelect: false,
                width: 200,
                height:210,
                onSelect:function (data,name){
					//alert(data);
					var jklistbox1 = liger.get("jklistbox");
					//alert(JSON2.stringify(jklistbox1.data));
           			var jkdata = jklistbox1.data;
           			for( var i= 0;i<jkdata.length ;i++){
           				if(data == jkdata[i].id){
           					//alert(JSON2.stringify(jkdata[i]));
           					//分产模式 :0计量分配 1平均分配
           					$("#jkmcsz").html("井口分产模式设置："+ jkdata[i].text);
           					$("#hidjkid").val(jkdata[i].id);
           					
           					if(SPLIT_MODELFLG == 0){
           						$("#SPLIT_COEFFICIENT").attr("disabled",true);
           						//$("#SPLIT_COEFFICIENT").attr("disabled",true);
           						if("1号流量计" == jkdata[i].P_FLOWMETER){
           							p_flowmeterlist.selectValue(1);
           						
           						}else if("2号流量计" == jkdata[i].P_FLOWMETER){
           							p_flowmeterlist.selectValue(2);
           						}else if("双流量计" == jkdata[i].P_FLOWMETER){
           							p_flowmeterlist.selectValue(3);
           						}else{
           							p_flowmeterlist.selectValue("");
           						}
           						
           						
           						if("1号流量计" == jkdata[i].I_FLOWMETER){
           							i_flowmeterlist.selectValue(1);
           						
           						}else if("2号流量计" == jkdata[i].I_FLOWMETER){
           							i_flowmeterlist.selectValue(2);
           						}else if("双流量计" == jkdata[i].I_FLOWMETER){
           							i_flowmeterlist.selectValue(3);
           						}else{
           							i_flowmeterlist.selectValue("");
           						}
           					
           					}else{
           						$("#SPLIT_COEFFICIENT").attr("disabled",false);
           						//$("#SPLIT_COEFFICIENT").attr("disabled",false);
           						$("#SPLIT_COEFFICIENT").val(jkdata[i].SPLIT_COEFFICIENT);
           					}
           				}
           			}
           			
					
				}
            });
          
            $("#P_FLOWMETER").ligerComboBox({
			 			selectBoxHeight:150,
             			hideOnLoseFocus:true,
						autocomplete:true,
			                data: [
			                    { text: '1号流量计', id: '1' },
			                    { text: '2号流量计', id: '2' },
			                    { text: '双流量计', id: '3' }
			                    
			                ], valueFieldID: 'test3',
						initText :''
			            }); 
			           p_flowmeterlist = $("#P_FLOWMETER").ligerGetComboBoxManager();
			             $("#I_FLOWMETER").ligerComboBox({
			             selectBoxHeight:150,
             			hideOnLoseFocus:true,
						autocomplete:true,
			                data: [
			                    { text: '1号流量计', id: '1' },
			                    { text: '2号流量计', id: '2' },
			                    { text: '双流量计', id: '3' }
			                ], valueFieldID: 'test3',
						initText :''
			            }); 
					 i_flowmeterlist = $("#I_FLOWMETER").ligerGetComboBoxManager();
           // fenchanmoshiiframe = document.getElementById("fenchanmoshi1");
           
           var date21 = myDate().Format("yyyy年MM月dd日");
           $("#jieshi").html("&nbsp;&nbsp;<font color='red'>注汽量分配： SAGD井"+date21+"注汽量分配</font>");
            
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
            	alert(JSON2.stringify(gladdDate));
            	box1.addItems(gladdDate);
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
		            	//alert(JSON2.stringify(gladdDate));
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
           
            var box2 = liger.get("jklistbox");
          
           var jklistbox3 = null;
           jklistbox3 = liger.get("jklistbox");
            if(jklbtype == 9){
            	
            	if(cellid != null &&typeof(cellid)!="undefined"){
            		var jkaddDate = [{ text: jklbname, id: jklbid ,noded:"",nodkid:cellid,SPLIT_COEFFICIENT:"",P_FLOWMETER:"",I_FLOWMETER:""}];
            		var flag = 0;
            		var jkdata = jklistbox3.data;
            		var  kkk;
            		//alert(JSON2.stringify(jkdata));
            		if(jkdata != null && typeof(jkdata)!="undefined"){
            			for( var i= 0;i<jkdata.length ;i++){
	           				if(jklbid == jkdata[i].id){
	           					flag = 1;	
	           				 }
	           				 if(jkdata[i].id == ''){
	           				 	kkk = jkdata[i];
	           				 }
	           			}
            		}
            		
           			jklistbox3.removeItems(kkk);
           			if(flag == 0){
           				
           				var jkaddDate = [{ text: jklbname, id: jklbid ,noded:cellid,SPLIT_COEFFICIENT:"",P_FLOWMETER:"",I_FLOWMETER:""}];
           				//alert(JSON2.stringify(jkaddDate));
           				jklistbox3.addItems(jkaddDate);
           			}else{
           				$.ligerDialog.warn('您选择的井口已存在该炉线单元节点中');
           			}
            		
            		//alert(JSON2.stringify(jkaddDate));
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
					if (data != null && typeof(data)!="undefined"){
						if(data < 0){
							$.ligerDialog.error(outerror(data));
						}else{
							$.ligerDialog.success(data);
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
      		var rows = grid.getCheckedRows();
      		var myDate = new Date();
          switch (item.icon) {
              case "add":
            	  grid.addRow({
            		  CELL_ID:'',
            		  CELL_NAME: '',
            		  SPLIT_MODEL: '计量分配',
            		  VALID_DATE: myDate.getFullYear()+'-'+myDate.getMonth() + 1+'-'+myDate.getDate(),
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
    						var myDate2 = new Date(this.VALID_DATE);
    						if ((myDate2.toDateString()!=myDate.toDateString())) {
    							$.ligerDialog.error("不能删除日期不为当天的数据!");
    							return;
    						}
    					});
              	     		$.ligerDialog.confirm('确定删除吗?', function (yes) {
	              	     		 if(yes){
			                          jQuery.ajax({
											type : 'post',
											url : 'boilerline_removeLX.action',
											async : false,
											data: {"CELL_ID":cellid},
											success : function(data) { 
											if (data != null && typeof(data)!="undefined" && data == "1"){
													onSubmit();
												}else{
													$.ligerDialog.error(outerror(data));
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
            	var lb = JSON2.stringify(liger.get("listbox1").data);
            	var tb = JSON2.stringify(liger.get("jklistbox").data);
            	/* alert(JSON2.stringify(lb));
            	alert(JSON2.stringify(tb)); */
					//$("#form1").submit();
					var rows = grid.getCheckedRows();
					if (rows.length != 1) {
						alert("请选择一条你需更新或添加的数据！");
						return;
					}
					
					$(rows).each(function (){
						var myDate2 = new Date(this.VALID_DATE);
						if ((myDate2.toDateString()!=myDate.toDateString())) {
							$.ligerDialog.error("不能修改日期不为当天的数据!");
							return;
						}
					});
					var updateRowDate = JSON2.stringify(rows);
					jQuery.ajax({
						type : 'post',
						url : 'boilerline_addOrUpdateBoilerLine.action',
						data: {"rowData":updateRowDate,"listbox1":lb,"listbox12":tb},
						async : false,
						timeout : '3000',
						success : function(jsondata) { 
							$.ligerDialog.closeWaitting("数据正在保存请稍后。。。");
							if (jsondata == 1){
								//$.ligerDialog.close();
								//parent.$(".l-dialog,.l-window-mask").remove();//关闭遮罩
								window.onSubmit();
								$.ligerDialog.success("保存成功！");
							}else{
								$.ligerDialog.error(outerror(jsondata));
							}
						},
						error : function(data) {
						}
					});
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
			//var validdate  = rowdata.VALID_DATE;
			var date1 = myDate().Format("yyyy-MM-dd");
			//alert(date1);
			if (myDate().Format("yyyy-MM-dd") == rowdata.VALID_DATE){
				$("#jkonchange1").attr("disabled",false);
				//var SPLIT_MODELFLG;  //分产模式 :0计量分配 1平均分配
				if(SPLIT_MODEL == '计量分配'){
					SPLIT_MODELFLG = 0;
					$("#SPLIT_COEFFICIENT").attr("disabled",true);
					  p_flowmeterlist.setEnabled();
					  i_flowmeterlist.setEnabled();
					
				}else{
					SPLIT_MODELFLG = 1;
					$("#SPLIT_COEFFICIENT").attr("disabled",false);
					 p_flowmeterlist.setDisabled();
					 i_flowmeterlist.setDisabled();
				}
				
			}else{
				SPLIT_MODELFLG = 1;
				$("#SPLIT_COEFFICIENT").attr("disabled",false);
				p_flowmeterlist.setEnabled();
				i_flowmeterlist.setEnabled();
				$("#jkonchange1").attr("disabled",true);
			}
			
			 	jQuery.ajax({
					type : 'post',
					url : 'boilerline_seachGLJKS.action?nowdata='+parseInt(Math.random()*100000),
					data: {"CELL_ID":CELL_ID},
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" ){
							//alert(jsondata);
								if(jsondata < 0){
									 $.ligerDialog.error(outerror(jsondata));
								}else{
								
									var data = eval('(' + jsondata + ')');
								    var gldata = data.GLDATA;
								    var jkdata = data.JKDATA;
								    
								    //alert(JSON2.stringify(jkdata));
								    if(gldata != null && typeof(gldata)!="undefined"){
								    	liger.get("listbox1").setData(gldata);
								    }else{
								    	liger.get("listbox1").setData(nulljson1) ;
								    }
								    
								    if(jkdata != null && typeof(jkdata)!="undefined"){
								    	liger.get("jklistbox").setData(jkdata);
								    }else{
								    	//liger.get("jklistbox").clearContent() ;
								    	liger.get("jklistbox").setData(nulljson2) ;
								    	i_flowmeterlist.selectValue("");
								    	p_flowmeterlist.selectValue("");
								    	$("#SPLIT_COEFFICIENT").val("");
								    }
								
								}
						}else{
							liger.get("listbox1").setData(nulljson1) ;
							liger.get("jklistbox").setData(nulljson2) ;
							
							i_flowmeterlist.selectValue("");
					    	p_flowmeterlist.selectValue("");
					    	$("#SPLIT_COEFFICIENT").val("");
							
						}
						
						//liger.get("listbox1").refresh();
						//liger.get("jklistbox").refresh();
						
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
				liger.get("jklistbox").setData(nulljson2) ;
				$("#SPLIT_COEFFICIENT").val('');
		    	p_flowmeterlist.selectValue("");
		   		i_flowmeterlist.selectValue("");
		   		
        		
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
						<td style="padding-left: 20px;"><a href="javascript:SAGDFP()" class="l-button" style="width:120px" id="sagdbtn">注汽量分配</a> </td>
						
						<td id="jieshi"></td>
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
<div id="maingrid" ></div> 
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
<div id="edituser" style="" style="width:100%;">

<div style="width:360px; margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
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
		         		<div id="gltreehi" style="width:150px;height:210px; margin:4px; float:left; border:1px solid #ccc; overflow:auto;">
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
<div style="width:610px;  margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
	<table border="0">
	<tr>
		
		<td>井口列表</td>
		<td><input name="hidjkid" type="hidden" id="hidjkid"  /></td>
		<td>可选井口树</td>
		<td><div id="jkmcsz">井口分产模式设置：</div></td>
		
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
		    
		    <div id="jktreehi" style="width:150px;height:210px; margin:4px; float:left; border:1px solid #ccc; overflow:auto;">
				<iframe frameborder="2" name="kxjktree" src="kxjktree.jsp" style="height: 1200px"></iframe> 
			</div> 
		</td>
		<td>
			<!-- <div id="fenchanmoshi" style="width:150px;height:140px; margin:4px; float:left; border:1px solid #ccc; ">
				<iframe frameborder="3" name="fenchanmoshi1" id="fenchanmoshi1" src="fenchanmoshi.jsp" ></iframe> 
			</div>
			 -->
			  <table cellpadding="0" cellspacing="0" class="l-table-edit" style="width:200px" border="0">
		            <tr>
		                <td align="left" class="l-table-edit-td" style="width:100px">注汽分配系数:</td>
		            </tr>   
		             <tr>
			             <td align="left" class="l-table-edit-td" style="width:100px">
			                	<input name="SPLIT_COEFFICIENT"  id="SPLIT_COEFFICIENT"   />
			               </td>
			               
		                </tr>
		              <tr>  
		                <td align="left" class="l-table-edit-td" style="width:100px">P井注汽流量计:</td>
		              </tr> 
		              <tr>
			              <td align="left" class="l-table-edit-td" style="width:100px">
		                	<input name="P_FLOWMETER" type="text" id="P_FLOWMETER"  />
		                </td>
		                </tr>
		                 <tr>  
		               <td align="left" class="l-table-edit-td" style="width:100px">I井注汽流量计:</td>
		              </tr> 
		              <tr>
		                 <td align="left" class="l-table-edit-td" style="width:100px">
			                	<input name="I_FLOWMETER" type="text" id="I_FLOWMETER"   />
			              </td>
		               </tr>
		               <tr>
		                <td align="left" ><a href="javascript:jkonchange()" class="l-button" style="width:120px" id="jkonchange1">&lt;&lt;井口变更</a></td>
		               </tr>
		           
            	</table>
		</td>
	
	</tr>

</table>
</div>


     
  </div>   
    
    
     
  </form>
    
</body>
</html>
