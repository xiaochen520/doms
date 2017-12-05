var eee;
	var oilstationname='';
	var stationname='';
	var wellname='';

        $(function () {
        
        
        	 var clzData = 
            [{ id: 1, text: '1#软化水站' }, 
            { id: 2, text: '2#软化水站'},
            {id: 3,text:'无盐'}
            ];
            var sbbhData =
            [
            { id: 1, text: '1', pid: 1 },
            { id: 2, text: '2', pid: 1 },
            { id: 3, text: '3', pid: 1 }, 
            { id: 4, text: '4', pid: 1},
            { id: 5, text: '5', pid: 1 },
            { id: 6, text: '6', pid: 1 },
            { id: 7, text: '7', pid: 1 }, 
            { id: 8, text: '8', pid: 1},
            { id: 9, text: '9', pid: 1 },
            { id: 10, text: '10', pid: 1 },
            
            { id: 11, text: '1', pid: 2 }, 
            { id: 12, text: '2', pid: 2},
            { id: 13, text: '3', pid: 2 },
            { id: 14, text: '4', pid: 2 },
            { id: 15, text: '5', pid: 2 }, 
            { id: 16, text: '6', pid: 2},
            { id: 17, text: '7', pid: 2 },
            { id: 18, text: '8', pid: 2 },
            { id: 19, text: '9', pid: 2 },
            
            { id: 31, text: '1', pid: 3 }, 
            { id: 32, text: '2', pid: 3}
            ];  
            
              $("#sbbh").ligerComboBox({ data: null,isMultiSelect: false });
              $("#sclz").ligerComboBox({
                data: clzData, isMultiSelect: false,selectBoxWidth:150,
                onSelected: function (newvalue)
                {
                    var newData = new Array();
                    for (i = 0; i < sbbhData.length; i++)
                    {
                        if (sbbhData[i].pid == newvalue)
                        {
                            newData.push(sbbhData[i]);
                        }
                    }
                    liger.get("sbbh").setData(newData);
                }
            });
            
            
			// $("#commissioning_date").ligerDateEditor({ showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd"});
             $("#txtDate").ligerDateEditor(
                {

                    format: "yyyy-MM-dd hh:mm",
                  //  label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    showTime: true,
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
            
            $("#txtDate").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(myDate().Format("yyyy-MM-dd hh:mm"));
			$("#txtDate1").ligerDateEditor({ showTime: true,showTime: true, labelWidth: 160, labelAlign: 'left' ,format :"yyyy-MM-dd hh:mm"}).setValue(secondDate().Format("yyyy-MM-dd hh:mm"));
			
        	 //获取报表及功能按钮ｊｓ
                jQuery.ajax({
					type : 'post',
					url : 'jzsclrd_pageInit.action?arg=CYQ',
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined"){
						var data = eval('(' + jsondata + ')');
							grid = $("#maingrid").ligerGrid(data);					
							
						}else{
							$.ligerDialog.error(outerror(jsondata));
						}
					},
					error : function(data) {
				
					}
				});
			
			
            $("#pageloading").hide();
			
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
       
    	
    	//为公用页面提供接口方法 
		function onJKSubmit(id,name,datatype,basid){ 
			
		}
        
           function onSubmit()
        {
        
 

			var sclz=$("#sclz").val();
			var sbbh=$("#sbbh").val();
          
        	grid.setOptions({
        		parms: [{
					name: 'sclz',
					value: sclz
				},{
					name: 'sbbh',
					value: sbbh
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