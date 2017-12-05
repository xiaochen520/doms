        //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
        function forbidBackSpace(e) {
            var ev = e || window.event; //获取event对象 
            var obj = ev.target || ev.srcElement; //获取事件源 
            var t = obj.type || obj.getAttribute('type'); //获取事件源类型 
            //获取作为判断条件的事件类型 
            var vReadOnly = obj.readOnly;
            var vDisabled = obj.disabled;
            //处理undefined值情况 
            vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
            vDisabled = (vDisabled == undefined) ? true : vDisabled;
            //当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
            //并且readOnly属性为true或disabled属性为true的，则退格键失效 
            var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
            var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
            //判断 
            if (flag2 || flag1) return false;
        }
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
      //控制toolbar中隐藏域显示按钮 0-显示编辑区，添加隐藏按钮，1-隐藏编辑区添加显示按钮 ，2-去掉最后一个显示隐藏按钮
        var setItemsd =  function setItemsd(flag){
        		var toolteams = toolbar.options.items;
 	       	if(0 == flag ){
 	       		 var show = {id:'downid', text: '隐藏', click: itemclick, img: '../../lib/ligerUI/skins/icons/down.gif',icon: 'down' };
 	       		 toolbar.removeItem("upid"); 
              	 toolbar.addItem(show);
 	       	}else if(1 == flag ){
 	       		 var up = {id:'upid', text: '显示', click: itemclick, img: '../../lib/ligerUI/skins/icons/up.gif',icon: 'up' };
 	       		  toolbar.removeItem("downid"); 
              	  toolbar.addItem(up); 
 	       	}else if(2 == flag ){
	 	       	toolbar.removeItem("upid"); 	
	 	       	toolbar.removeItem("downid"); 
 	       	}
         }
        
      //设置页面隐藏显示 flag 0-显示，1-隐藏
        var setpage =  function setpage(flag){
 	       	if(0 == flag ){
 	       		grid.set('height',document.body.clientHeight * 0.7);
 				jQuery("#edituser").css('height',document.body.clientHeight * 0.2);
              	jQuery("#edituser").css('display','block');
 	       	}else{
 	       		jQuery("#edituser").css('height',0);
        	    jQuery("#edituser").css('display','none');
        	    grid.set('height',document.body.clientHeight*0.9);
 	       	}
         }
        
      //设置页面隐藏显示 flag 0-显示，1-隐藏
        var setpage1 =  function setpage1(flag){
 	       	if(0 == flag ){
 	       		grid.set('height',document.body.clientHeight * 0.5);
 				jQuery("#edituser").css('height',document.body.clientHeight * 0.4);
              	jQuery("#edituser").css('display','block');
 	       	}else{
 	       		jQuery("#edituser").css('height',0);
        	    jQuery("#edituser").css('display','none');
        	    grid.set('height',document.body.clientHeight*0.9);
 	       	}
         }
        
        
        //设置页面隐藏显示 flag 0-显示，1-隐藏
          var setpage2 =  function setpage2(flag){
   	       	if(0 == flag ){
   	       		grid.set('height',document.body.clientHeight * 0.6);
   				jQuery("#edituser").css('height',document.body.clientHeight * 0.3);
                	jQuery("#edituser").css('display','block');
   	       	}else{
   	       		jQuery("#edituser").css('height',0);
          	    jQuery("#edituser").css('display','none');
          	    grid.set('height',document.body.clientHeight*0.9);
   	       	}
           }
        
          //设置页面隐藏显示 flag 0-显示，1-隐藏
          var setpage4 =  function setpage4(flag){
   	       	if(0 == flag ){
   	       		grid.set('height',document.body.clientHeight * 0.3);
   				jQuery("#edituser").css('height',document.body.clientHeight * 0.2);
                	jQuery("#edituser").css('display','block');
   	       	}else{
   	       		jQuery("#edituser").css('height',0);
          	    jQuery("#edituser").css('display','none');
          	    grid.set('height',document.body.clientHeight*0.9);
   	       	}
           }
          
          var setpage5 =  function setpage5(flag){
     	       	if(0 == flag ){
     	       		grid.set('height', "70%");
     				jQuery("#edituser").css('height',document.body.clientHeight * 0.2);
     				jQuery("#edituser").css('display','block');
     	       	}else{
     	       		jQuery("#edituser").css('height',0);
            	    jQuery("#edituser").css('display','none');
            	    grid.set('height', "96%");
     	       	}
             }
        var outerror = function outErrer(jsondata){
        	var errStr = "操作失败！";
        	if(jsondata != '' && typeof(jsondata)!="undefined"){
        		if(jsondata == -10001){
        			errStr = "添加操作LOG信息添加失败";
        		}else if(jsondata == -10002){
        			errStr = "修改操作LOG信息添加失败";
        		}else if(jsondata == -10003){
        			errStr = "删除操作LOG信息添加失败";
        		}else if(jsondata == -10004){
        			errStr = "页面初始化失败";
        		}else if(jsondata == -10005){
        			errStr = "数据查询失败";
        		}else if(jsondata == -10010){
        			errStr = "建设状态获取失败";
        		}else if(jsondata == -10007){
        			errStr = "修改操作LOG信息添加失败";
        		}
        		//部门error
        		else if(jsondata == -11701){
        			errStr = "部门信息添加失败";
        		}else if(jsondata == -11702){
        			errStr = "部门数据加载失败";
        		}else if(jsondata == -11703){
        			errStr = "未获取部门ID标识";
        		}else if(jsondata == -11704){
        			errStr = "部门信息修改失败";
        		}else if(jsondata == -11705){
        			errStr = "部门信息删除失败";
        		}else if(jsondata == -11706){
        			errStr = "部门信息获取失败";
        		}else if(jsondata == -11707){
        			errStr = "部门名称已存在请使用其他部门名称";
        		}
        		//操作员error
        		else if(jsondata == -15801){
        			errStr = "操作员信息获取失败";
        		} else if(jsondata == -15802){
        			errStr = "操作员信息获取失败";
        		} else if(jsondata == -15803){
        			errStr = "操作员信息修改失败";
        		}else if(jsondata == -15806){
        			errStr = "操作员已存在请使用其他操作员名称";
        		}
        		
        		//角色error
        		else if(jsondata == -15901){
        			errStr = "角色信息获取失败";
        		}else if(jsondata == -15902){
        			errStr = "角色信息添加失败";
        		}else if(jsondata == -15903){
        			errStr = "角色信息修改失败";
        		}else if(jsondata == -15904){
        			errStr = "角色权限信息过去失败";
        		}else if(jsondata == -15905){
        			errStr = "角色信息授权失败";
        		}else if(jsondata == -15906){
        			errStr = "角色删除失败-改角色被其他用户使用";
        		}else if(jsondata == -15907){
        			errStr = "角色已存在请使用其他角色名称";
        		}
        		//角色error
        		else if(jsondata == -16901){
        			errStr = "权限删除失败-改角色被其他用户使用";
        		}
        		
        		//SAGD
        		else if(jsondata == -10201){
        			errStr = "SAGD井信息获取失败";
        		}else if(jsondata == -10202){
        			errStr = "SAGD井信息添加失败";
        		}else if(jsondata == -10203){
        			errStr = "SAGD井信息修改失败";
        		}else if(jsondata == -10206){
        			errStr = "SAGD井名已存在请使用其他井名";
        		}else if(jsondata == -10207){
        			errStr = "SAGD井ID未识别";
        		}else if(jsondata == -10208){
        			errStr = "服务器逻辑节点信息获取失败";
        		}else if(jsondata == -10209){
        			errStr = "SAGD井基础信息删除失败";
        		}else if(jsondata == -10210){
        			errStr = "SAGD井基础信息删除失败-违反完整约束条件 FK58_PC_RPD_WELL_SAGDD_T SAGD井日报信息中存在已找到子记录";
        		}else if(jsondata == -10211){
        			errStr = "SAGD井基础信息删除失败-";
        		}else if(jsondata == -10212){
        			errStr = "SAGD井基础信息删除失败-";
        		}else if(jsondata == -10213){
        			errStr = "P井生产方式信息获取失败请重新选择";
        		}else if(jsondata == -10214){
        			errStr = "I井生产方式信息获取失败请重新选择";
        		}
        		//稀油井
        		else if(jsondata == -10411){
        			errStr = "稀油井名称已经存在,请使用其他稀油井名称！";
        		}else if(jsondata == -10410){
        			errStr = "稀油井名称获取失败";
        		}
        		//气井
        		else if(jsondata == -10511){
        			errStr = "气井名称已存在，请换其他气井名称！";
        		}else if(jsondata == -10510){
        			errStr = "气井信息名称获取失败";
        		}
        		else if(jsondata == -10512){
        			errStr = "气井添加失败";
        		}else if(jsondata == -10503){
        			errStr = "所属气站更新失败";
        		}else if(jsondata == -11110){
        			errStr = "所属气站信息获取失败";
        		}
        		//管汇
        		else if(jsondata == -11507){
        			errStr = "未获取管汇标识请重新选择管汇";
        		}else if(jsondata == -11501){
        			errStr = "管汇信息获取失败";
        		}else if(jsondata == -11511){
        			errStr = "管汇信息获取失败";
        		}else if(jsondata == -11512){
        			errStr = "管汇信息添加失败";
        		}else if(jsondata == -11513){
        			errStr = " 管汇信息修改失败";
        		}else if(jsondata == -11516){
        			errStr = "注转站下已存在该管汇名称，请使用其他管汇名称";
        		}else if(jsondata == -11507){
        			errStr = "管汇ID未识别，请重新选择管汇信息";
        		}else  if(jsondata == -11518){
        			errStr = "管汇名称已存在，请使用其他名称";
        		}else  if(jsondata == -11519){
        			errStr ="管汇名称已存在，不许修改";
        		}
        		//水源井
        		else if(jsondata == -10601){
        			errStr = "水源井信息获取失败";
        		}else if(jsondata == -10602){
        			errStr = "水源井信息添加失败";
        		}else if(jsondata == -10603){
        			errStr = "水源井信息修改失败";
        		}else if(jsondata == -10607){
        			errStr = "水源井名已存在请使用其他水源井名";
        		}else if(jsondata == -11108){
        			errStr = "所属处理站信息获取失败";
        		}else if(jsondata == -11109){
        			errStr = "已存在相同名称的气站";
        		}
        		//注转站
        		else if(jsondata == -11110){
        			errStr = "注转站名称获取失败";
        		}else if(jsondata == -11111){
        			errStr = "注转站已存在";
        		}else if(jsondata == -11112){
        			errStr = "注转站添加失败";
        		}else if(jsondata == -11113){
        			errStr = "注转站更新失败";
        		}else if(jsondata == -11117){
        			errStr = "未识别注转站ID 请重新选择注转站";
        		}else if(jsondata == -11118){
        			errStr = "注转站信息获取失败";
        		}
        		//采油站
        		else if(jsondata == -11310){
        			errStr = "采油站名称获取失败";
        		}else if(jsondata == -11311){
        			errStr = "采油站名称已存在";
        		}else if(jsondata == -11312){
        			errStr = "采油站添加失败";
        		}else if(jsondata == -11313){
        			errStr = "采油站更新失败";
        		}else if(jsondata == -11314){
        			errStr = "采油站信息获取失败";
        		}else if(jsondata == -11316){
        			errStr = "采油站信息获取失败";
        		}else if(jsondata == -11315){
        			errStr = "未获取采油站ID请重新选择";
        		}
        		
        		//联合站
        		else if(jsondata == -11010){
        			errStr = "联合站名称获取失败";
        		}else if(jsondata == -11011){
        			errStr = "联合站已存在";
        		}else if(jsondata == -11012){
        			errStr = "联合站添加失败";
        		}else if(jsondata == -11013){
        			errStr = "联合站更新失败";
        		}else if(jsondata == -11014){
        			errStr = "联合站ID获取失败";
        		}
        		//区块
        		else if(jsondata == -11210){
        			errStr = "区块名称获取失败";
        		}else if(jsondata == -11211){
        			errStr = "区块名称已存在";
        		}else if(jsondata == -11212){
        			errStr = "区块添加失败";
        		}else if(jsondata == -11214){
        			errStr = "区块更新失败";
        		}else if(jsondata == -11213){
        			errStr = "区块id获取失败";
        		}else if(jsondata == -11215){
        			errStr = "区类代码获取失败";
        		}
        		//注水井
        		else if(jsondata == -10810){
        			errStr = "注水井井信息获取失败";
        		}else if(jsondata == -10811){
        			errStr = " 注水井名已存在请使用其他注水井名";
        		}else if(jsondata == -10812){
        			errStr = "注水井信息修改失败";
        		}else if(jsondata == -10813){
        			errStr = " 注水井信息添加失败";
        		}
        		//锅炉
        		else if(jsondata == -101301){
        			errStr = "此锅炉名称已被使用,请更改名称！";
        		}else if(jsondata == -101302){
        			errStr = "获取所属供热站标识错误,请重选所属供热站！";
        		}
        		//注水撬
        		else if(jsondata == -10710){
        			errStr = "所属注水撬信息获取失败";
        		}else if(jsondata == -10711){
        			errStr = "所属注水撬信息未识别";
        		}else if(jsondata == -10701){
        			errStr = " 注水撬信息获取错误";
        		}else if(jsondata == -10702){
        			errStr = "注水撬添加失败";
        		}else if(jsondata == -10703){
        			errStr = " 注水撬修改失败";
        		}else if(jsondata == -10706){
        			errStr = " 注水撬名称已存在，请使用其他注水撬名称";
        		}else if(jsondata == -10708){
        			errStr = " 注水撬ID未识别，请重新选择注水撬";
        		}
        		//气站
        		else if(jsondata == -10111){
        			errStr = "气站站名称已存在，请使用其他名称";
        		}else if(jsondata == -10166){
        			errStr = "气站添加失败";
        		}else if(jsondata == -10167){
        			errStr = "气站修改失败";
        		}
        		//分线
        		else if(jsondata == -11601){
        			errStr = "  分线信息获取错误";
        		}else if(jsondata == -11602){
        			errStr = " 分线信息添加失败";
        		}else if(jsondata == -11603){
        			errStr = " 分线信息修改失败";
        		}else if(jsondata == -11606){
        			errStr = " 分线名称已存在，请使用其他分线名称";
        		}else if(jsondata == -11607){
        			errStr = "  分线ID未识别，请重新选择分线信息";
        		}
        		
        		//观察井
        		else if(jsondata == -10910){
        			errStr = "观察井井信息获取失败";
        		}else if(jsondata == -10911){
        			errStr = "  观察井名已存在请使用其他水源井名";
        		}else if(jsondata == -10912){
        			errStr = " 观察井信息修改失败";
        		}else if(jsondata == -10913){
        			errStr = " 观察井信息添加失败";
        		}else if(jsondata == -11110){
        			errStr = "所属注转站信息获取失败";
        		}else if(jsondata == -11115){
        			errStr = "所属注转站信息未识别";
        		}
        		
        		//常规油井
        		else if(jsondata == -10311){
        			errStr = "常规油井名称已存在，请使用其他常规油井名称";
        		}else if(jsondata == -10312){
        			errStr = "常规油井添加失败";
        		}else if(jsondata == -10313){
        			errStr = " 常规油井名称已存在多条，请联系管理员";
        		}else if(jsondata == -10314){
        			errStr = " 稀油油井信息获取失败";
        		}else if(jsondata == -10315){
        			errStr = "未识别常规油井ID";
        		}else if(jsondata == -10317){
        			errStr = "常规油井更新失败";
        		}
        		
        		//SAGD井日报维护
        		else if(jsondata == -13401){
        			errStr = "SAGD井日报维护添加失败";
        		}else if(jsondata == -13402){
        			errStr = "SAGD井日报维护更新失败";
        		}else if(jsondata == -13403){
        			errStr = "井日报维护删除失败";
        		}else if(jsondata == -13405){
        			errStr = "SAGD井日报维护添加时 存在指定日期的 同井记录";
        		}else if(jsondata == -13404){
        			errStr = "SAGD井日报维护更新 存在指定日期的 同井同不同id的记录";
        		}else if(jsondata == -13406){
        			errStr = "只能修改、添加和审核当天的数据!";
        		}
        		
        		//湿蒸汽锅炉日报维护
        		else if(jsondata == -14211){
        			errStr = "湿蒸汽锅炉日报维护信息删除失败";
        		}else if(jsondata == -14201){
        			errStr = "湿蒸汽锅炉日报维护信息获取失败";
        		}else if(jsondata == -14202){
        			errStr = "湿蒸汽锅炉名称已存在，不许添加";
        		}else if(jsondata == -14203){
        			errStr = "湿蒸汽锅炉日报维护信息更新失败";
        		}else if(jsondata == -14204){
        			errStr = "湿蒸汽锅炉日报维护信息添加失败";
        		}else if(jsondata == -14205){
        			errStr = "湿蒸汽锅炉日报维护信息删除失败";
        		}else if(jsondata == -14206){
        			errStr = "数据库中存在另一条该锅炉日报记录不许修改";
        		}else if(jsondata == -14207){
        			errStr = "要修改数据数据库中存在多条";
        		}else if(jsondata == -14208){
        			errStr = "数据库中存在此记录不允许用户添加新纪录";
        		}
        		
        		//过热锅炉日报维护
        		else if(jsondata == -14305){
        			errStr = "过热锅炉日报维护信息删除失败";
        		}else if(jsondata == -14301){
        			errStr = "过热锅炉日报维护信息获取失败";
        		}else if(jsondata == -14302){
        			errStr = "数据库中存在另一条该锅炉日报记录不许修改";
        		}else if(jsondata == -14303){
        			errStr = "要修改数据数据库中存在多条";
        		}else if(jsondata == -14304){
        			errStr = "数据库中存在此记录不允许用户添加新纪录";
        		}else if(jsondata == -14306){
        			errStr = "过热锅炉日报维护信息更新失败";
        		}else if(jsondata == -14307){
        			errStr = "过热锅炉日报维护信息添加失败";
        		}
        		
        		//锅炉仪表
        		else if(jsondata == -16305){
        			errStr = "本锅炉已有仪表设备!!!";
        		}else if(jsondata == -16306){
        			errStr = "本井口已有仪表设备名称!!!";
        		}else if(jsondata == -16307){
        			errStr = "所属管汇已有仪表设备!!!";
        		}else if(jsondata == -16308){
        			errStr = "所属站,所属管汇,所属井,所属锅炉至少有一项不为空";
        		}else if(jsondata == -16309){
        			errStr = "仪表设备信息添加失败 ";
        		}else if(jsondata == -16310){
        			errStr = "仪表设备信息修改失败";
        		}else if(jsondata == -16311){
        			errStr = "所属站已有仪表设备!!!";
        		}else if(jsondata == -16312){
        			errStr = "仪表设备删除失败!!!";
        		}else if(jsondata == -16313){
        			errStr = "仪表名称数据获取失败，请重新选择!!!";
        		}
        		//控制器
        		else if(jsondata == -16299){
        			errStr = "此控制器已有相同的仪表设备!!!";
        		}else if(jsondata == -16209){
        			errStr = "控制器信息添加失败 !!!";
        		}else if(jsondata == -16210){
        			errStr = "控制器信息修改失败!!!";
        		}else if(jsondata == -16298){
        			errStr = "仪表设备信息未识别!!!";
        		}
        		else if(jsondata == -16210){
        			errStr = "仪表设备信息删除失败!!!";
        		}else if(jsondata == -16367){
        			errStr = "小类型名称信息获取失败!!!";
        		}else if(jsondata == -16366){
        			errStr = "厂家名称信息获取失败!!!";
        		}
        		
        		//炉线管理
        		else if(jsondata == -17101){
        			errStr = "炉线管理信息获取失败";
        		}else if(jsondata == -17102){
        			errStr = "炉线节点与锅炉、井口对应关系数据获取失败";
        		}else if(jsondata == -17103){
        			errStr = "生成当日的炉线信息失败";
        		}else if(jsondata == -17104){
        			errStr = "当前日期的注汽分配失败";
        		}else if(jsondata == -17105){
        			errStr = "炉线单元删除失败";
        		}else if(jsondata == -17106){
        			errStr = "炉线单元更新时存在指定日期的 同名，同日期的记录 报错";
        		}else if(jsondata == -17107){
        			errStr = "添加炉线单元在当日存在相同炉线名称";
        		}else if(jsondata == -17108){
        			errStr = "炉线单元数据格式错误";
        		}else if(jsondata == -17109){
        			errStr = "炉线单元数据更新失败";
        		}else if(jsondata == -17110){
        			errStr = "炉线单元数据添加失败";
        		}
        		//SubCool
        		else if(jsondata == -16001){
        			errStr = "SubCool默认参数获取失败";
        		}else if(jsondata == -16001){
        			errStr = "SubCool默认参数设置失败";
        		} else if(jsondata == -16002) {
        			errStr = "数据获取失败";
        		}
        		
        		
        		
        		
        		
        		
        	}
        	
        	return errStr;
        	
        }
        
        /**
         * 判断页面权限，修改和添加  通过保存 是否允许提交
         * toolteams  b表格功能按钮
         * flg 权限判断    var operate = 0; //操作  0-无 、1-添加、2-修改
         * return authorityflg  1表示允许保存， 0表示不允许保存
         */
        var judgeAuthority =  function judgeAuthority(toolteams,flg){
        	//添加判断
        	var authorityflg = 0;
 	       	if(1 == flg ){
	 	       	for(var i=0; i<toolteams.length ; i++){
					if(toolteams[i].id == 'addid'){
						authorityflg = 1;
					}
				}
	 	     // 修改判断 		
 	       	}else if(2 == flg){
	 	       	for(var i=0; i<toolteams.length ; i++){
					if(toolteams[i].id == 'modifyid'){
						authorityflg = 1;
					}
				}
 	       	}
 	       		return authorityflg;
         }
        /**
         * 去除页面 保存BUTTON 按钮
         * @param toolteams
         * @return
         */
        var removeSaveButton =  function removeSaveButton(toolteams){
        	for(var i=0; i<toolteams.length ; i++){
				if(toolteams[i].id == 'saveid'){
					toolbar.removeItem("saveid"); 
					
				}
			}
        	
        }
        
        
        function userAuthority(str){
        	var  auData = null;
        	if(str != null && typeof(str)!="undefine"){
        		
        		var addobj = { text: '添加', click: itemclick, icon: 'add' };
				var upobj = { text: '修改', click: itemclick, icon: 'modify' };
				var delobj = { text: '删除', click: itemclick, icon: 'delete' };
				var savedis = { id:'refers',text: '提交', click: itemclick, icon: 'save-disabled'};
				var save = { id:'refers',text: '提交', click: itemclick,icon: 'save'};
        		var flag = 0;
        		if(str != null && str.length >0){
        			auData = new Array();
					for(var i = 0 ; i <str.length ; i++){
					        if('添加' == str[i]){
					        	auData[i] = addobj;
					        	flag = 1;
					        }else if('修改' == str[i]){
					        	auData[i] = upobj;
					        	flag = 1;
					        }else if('删除' == str[i])	{
					        	auData[i] = delobj;
					        	
					        }
					   }
					
					if(flag == 1){
						
						auData[str.length +1] = savedis;
					}
        			
        		}
        		alert({items:auData});
        		return {items:auData};
        		
        	}
        	
        }
        
        var getRemoveData = function getRemoveData(textValue){
        	if (textValue != null && typeof(textValue)!="undefined"){
        		if(textValue.indexOf("span") > 0 ){
        			textValue=textValue.replace("<span style='color: red;'>","");  
        			
        			textValue=textValue.replace("<span style='color: purple;'>","");  
        			textValue=textValue.replace("<span style='color: green;'>","");  
        			
        			textValue=textValue.replace("</span>","");  
        		}
        	}else{
        		return "";
        	}
        	
        	return textValue;
        	
        }
        
        
       //读取文件路径
        
        
        
        