<%@ page language="java" import="java.util.*,com.echo.dto.User,com.echo.util.CommonsUtil" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)session.getAttribute("userInfo");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>风城油气田生产管理自动化信息系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta content='width=330, height=400, initial-scale=1' name='viewport' />
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>  
    <script src="lib/ligerUI/js/core1.20/base.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core1.20/inject.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core1.20/ligerCheckBox.js" type="text/javascript"></script>  
    <script src="lib/ligerUI/js/core1.20/ligerButton.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/core1.20/ligerListBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
 	<script src="lib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
 	<script src="../../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
 	
 	<script src="lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script> 
	<script src="/lib/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>

    <script src="lib/json2.js" type="text/javascript"></script> 
     <script src="noBackspace.js" type="text/javascript"></script>
        <script type="text/javascript">
            var tab = null;
            var accordion = null;
            var pulic111 = null;
            var tree = null;
            //tabid计数器，保证tabid不会重复
       		 var tabidcounter = 0;
       		 var jklistbox;
       		 var nowtabid = 'home'; //当前tab id
            $(function ()
            {
                //布局
                $("#layout1").ligerLayout({leftWidth: 190, height: '105%', allowBottomResize:true,bottomHeight:10 ,heightDiff:-34,space:4, onHeightChanged: f_heightChanged,rightWidth:170,isRightCollapse :true });
               
                var height = $(".l-layout-center").height();

                //Tab
                $("#framecenter").ligerTab({ height: height });

                //面板
                $("#accordion1").ligerAccordion({ height: height - 24, speed: null });
				$("#pulic1").ligerAccordion({ height: height - 24, speed: null });
                $(".l-link").hover(function ()
                {
                    $(this).addClass("l-link-over");
                }, function ()
                {
                    $(this).removeClass("l-link-over");
                });
                
                
                 //获取菜单树
                jQuery.ajax({
					type : 'post',
					url : 'system_getMenuData.action?nowdata='+parseInt(Math.random()*100000),
					async : false,
					
					success : function(jsondata) { 
					if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
							//菜单树
			              $("#tree1").ligerTree({
			                  data : eval('(' + jsondata + ')') ,
			                  checkbox: false,
			                  slide: false,
			                  nodeWidth: 120,
			                  attribute: ['nodename', 'url'],
			                  onSelect: function (node)
			                  {
			                      if (!node.data.url) return;
			                      var tabid = $(node.target).attr("tabid");
			                      if (!tabid)
			                      {
			                      	var menuname = node.data.menuid;
			                          tabid = menuname+new Date().getTime();
			                          $(node.target).attr("tabid", tabid)
			                      } 
			                      f_addTab(tabid, node.data.text, node.data.url);
			                  }
			              });
							
							
							
						}else{
							alert("数据库数据存在错误！");
						}
					},
					error : function(data) {
				
					}
				});
				
					$("#framecenter").ligerTab({onAfterSelectTabItem: function (tabid){
		                nowtabid = tabid;
		                // JKsearchShow(nowtabid);
			            	} 
			            });
                $("#orgtree").ligerTree(
	            {
	                url: 'system_getStructureData.action?nowdata='+parseInt(Math.random()*100000),
	                nodeWidth : 100,
	                checkbox: false,
	                onBeforeExpand: onBeforeExpand,
	                onExpand: onExpand,
	                onSelect: function (node){
	                	menujudge(node.data.id,node.data.text,node.data.nodetype);
	                }
	            });
	            
	             jklistbox = $("#listbox1").ligerListBox({
	                isShowCheckBox: false,
	                isMultiSelect: false,
	                data: '' ,
	                valueFieldID: 'hidnodeid',
	                height:480,
	                onSelect:function (data,name){
	                	//获取节点类型
                		 jQuery.ajax({
							type : 'post',
							url : 'system_getNodeType.action?nowdata='+parseInt(Math.random()*100000),
							data: {"nodeid":data},
							async : false,
							success : function(jsondata) { 
							if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
									var datas = eval('(' + jsondata + ')');
									menujudge(datas.ID,datas.NAME,datas.NODETYPE);
									
								}
							},
							error : function(data) {
						
							}
						});
	                }
	            }); 
	            
	        	$("#filtertext").keyup(function(){
					//过滤数据以及下面子节点
					var noded = $("#filtertext").val();
					if(noded != null && typeof(noded)!="undefined" && noded != '' ){
						jQuery.ajax({
						type : 'post',
						url : 'system_getFilterOrgData.action?nowdata='+parseInt(Math.random()*100000),
						data: {"node":noded},
						async : false,
						success : function(jsondata) { 
						if (jsondata != null && typeof(jsondata)!="undefined" && jsondata!=0){
								jklistbox.setData(eval('(' + jsondata + ')')); 
								
							}else{
								jklistbox.setData('');
							}
						},
						error : function(data) {
					
						}
					});
					}
	                
				
				}).keyup();
			
				//window.status = "Copyright © 2013-2015 北京安控科技股份发展有限责任公司";
	            orgmanager = $("#orgtree").ligerGetTreeManager();

                tab = $("#framecenter").ligerGetTabManager();
                accordion = $("#accordion1").ligerGetAccordionManager();
                
                pulic111 = $("#pulic1").ligerGetAccordionManager();
                tree = $("#tree1").ligerGetTreeManager();
                $("#pageloading").hide();
                

            });
            
            
            function onBeforeExpand(note)
        {
            if (note.data.children && note.data.children.length == 0)
            {
            	//alert(JSON2.stringify(note.data));
            	var nowid = note.data.id;
            	orgmanager.loadData(note.target,'system_getOrgChridData.action?id='+nowid+'&nowdata='+parseInt(Math.random()*100000));
              
            }
        }
        function onExpand(note)
        { 
        } 
            
            function f_heightChanged(options)
            {
                if (tab)
                    tab.addHeight(options.diff);
                if (accordion && options.middleHeight - 24 > 0)
                    accordion.setHeight(options.middleHeight - 24);
            }
            function f_addTab(tabid,text, url)
            { 
            	 if (!tab) return;
	            if (!tabid)
	            {
	                tabidcounter++;
	                tabid = "tabid" + tabidcounter; 
	            }
	            nowtabid = tabid;
                tab.addTabItem({ tabid : tabid,text: text, url: url });
            } 
            
             function loginout()
            {
               var url = "login_SystemLoginOut.action";
				document.forms[0].action=url;
				document.forms[0].submit();
            } 
              function contactour()
            {
               $.ligerDialog.open({
              	    			title: "联系我们",
              	    			url:'contactour.html',
              	    			width: 600,
              	    			height: 500
              	    		});
            }
              function changePassWord(){
            	$.ligerDialog.open({
              	    			title: "修改密码",
              	    			url:'<%=path%>/page/basicInfo/changePassWord.jsp',
              	    			width: 400,
              	    			height:230
              	    		});
            }
            
            
          //将参数传入当前页面
            function menujudge(id,name,datatype){
            	//截取当前页面nowtabid 
            	var menud ;
            	 var tabflg = tab.isTabItemExist(nowtabid);
            	  if(tabflg){
		           		
		           		if(nowtabid != 'home'){
            		menud = nowtabid.substr(0, 7);
            		jQuery.ajax({
						type : 'post',
						url : 'user_getMenuNameJudge.action?nowdata='+parseInt(Math.random()*100000),
						data: {"menuid":menud,"datatype":datatype},
						async : false,
						success : function(jsondata) { 
					
						if (jsondata != null && typeof(jsondata)!="undefined" && jsondata == "SESSCUSS"){
								  //判断tab是否存在 
	          					document.getElementById(nowtabid).contentWindow.onJKSubmit(id,name,datatype);
								
							}else{
								$.ligerDialog.warn(jsondata);
							}
						},
						error : function(data) {
					
						}
					});
            		
            	}else{
            		$.ligerDialog.warn('系统首页无公共收索功能');
            	}
	          	 }	
            
            }
     </script> 
	<style type="text/css"> 
	    body,html{height:95%;padding:1px 2px 2px 2px; margin:0; padding-bottom:15px;hoverflow:scroll;overflow-y:hidden;overflow-x:hidden;}
	    body{ padding:0px; margin:0;   overflow:hidden;}  
	    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
	    .l-link2{text-decoration:underline; color:white; margin-left:2px;margin-right:2px;}
	    .l-layout-top{background:#102A49; color:White;}
	    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
	    #pageloading{position:absolute; left:0px; top:0px; background:white url('loading.gif') no-repeat center; height:100%;z-index:99999;}
	    .l-link{ display:block; line-height:22px; height:22px; padding-left:16px;border:1px solid white; margin:4px;}
	    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;} 
	    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
	    .space{ color:#E7E7E7;}
	    /* 顶部 */ 
	    .l-topmenu{ margin:0; padding:0; height:55px; line-height:55px; background:url('img/logo.jpg');background-repeat:no-repeat; position:relative; border-top:1px solid #1D438B;background-color: #5BA5EC;  }
	    .l-topmenu-logo{ color:#E7E7E7; padding-left:35px; line-height:26px;background:url('lib/images/topicon.gif') no-repeat 10px 5px;}
	    .l-topmenu-welcome{  position:absolute; height:24px; line-height:24px;  right:30px; top:2px;color:#070A0C;margin: 31px;}
	    .l-topmenu-welcome a{ color:#FFFFFF; text-decoration:underline;} 

 	</style>
  </head>
  
  <body style="padding:0px;background:#EAEEF5;height: 120%;">  
	<div id="pageloading"></div>
	<div id="topmenu" class="l-topmenu">
	    <!-- <div class="l-topmenu-logo">保德煤层气信息管理化平台主页</div> -->
	    <div class="l-topmenu-welcome">
	    	
	       	 <font color="#FFFFFF">欢迎您：${username}</font>
	        <span class="space">|</span>
	          <a onclick="javascript:changePassWord()" style="cursor:hand"><span id="k1" onmouseover="javascript:this.style.color='#0CF717'"  onmouseout="javascript:this.style.color='#FFFFFF'">修改密码</span></a>
	        <span class="space">|</span>
	        <a onclick="javascript:loginout()" style="cursor:hand"><span id="k2" onmouseover="javascript:this.style.color='#0CF717'"  onmouseout="javascript:this.style.color='#FFFFFF'">退出</span></a>
	        <span class="space">|</span>
	        <a onclick="javascript:contactour()" style="cursor:hand"><span id="k3" onmouseover="javascript:this.style.color='#0CF717'"  onmouseout="javascript:this.style.color='#FFFFFF'">联系我们</span></a>
	         
	    </div> 
	</div>
  <div id="layout1"  > 
        <div position="left"  title="主要菜单" id="accordion1"> 
                     <div title="功能列表" class="l-scroll">
                         <ul id="tree1" style="margin-top:3px;">
                    </div>
        </div>
        <div position="center" id="framecenter" style="height: 95%;"> 
            <div tabid="home" title="油田主页" style="height:400px" >
                <iframe frameborder="0" name="home" id="home" src="JsCode.html"></iframe>
            </div> 
        </div> 
        <div position="right" title="公共搜索区" id="pulic1" style="height: 100%;">
       			<div title="组织树搜索" class="l-scroll">
       					<ul id="orgtree" style="margin-top:3px;"></ul>
                    </div>  
                    
                    <div title="过滤框搜索" class="l-scroll" style="overflow: hidden;">
                    	<table border="0" cellspacing="1" style="width: 100%;">
							<tr>
				                <td align="left" class="l-table-edit-td" >
				                	<input type="text" id="filtertext" name = "filtertext"/>
				                </td>
				            </tr>
				         
				            <tr >
				                <td align="left" class="l-table-edit-td" colspan="2" >
				                	<div id="listbox1" ></div> 
				                </td>
				            </tr>
						</table>
                    </div>  
        </div>
    </div>
   	<div>
   	<form name="form" id="form" action="" method="post">
   	</form>
   	</div>
</body>
</html>
<script  type="text/javascript">
var ow = document.body.offsetWidth;
ow = ow / 11;
var kg = " ";
for (var i=0;i<ow;i++){ kg = kg + " ";}
window.status = kg + "Copyright  2013-2015 BeijingEchoTechnologiesCo.,Ltd";

</script>
