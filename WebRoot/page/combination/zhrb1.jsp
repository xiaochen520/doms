<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>二号稠油综合日报</title>
   
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	 <meta http-equiv="pragma" content="no-cache"></meta>
    <meta http-equiv="cache-control" content="no-cache"></meta>
    <meta http-equiv="expires" content="0"></meta>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
   
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
        <script src="../../lib/U2_check.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 	<script src="../../lib/myday.js" type="text/javascript"></script>
 	<link href="./二号稠油联合处理站日生产简报-新（综合日报）.files/stylesheet.css" rel="stylesheet" type="text/css" />  
    <script type="text/javascript">
    	var tableval;
    	var  RPDREPORTMESSAGEID ="";
    	var  SHR ="";
    	var TRPD_U2_GENERAL_ID ="";
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
					url : 'zhrb_pageInit.action',
					success : function(data) { 
					
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							//alert(JSON2.stringify(outData));
							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
								var bottons = outData.JSONDATA;
								//alert(JSON2.stringify(bottons.modify ));
								//alert(bottons.audit != null && typeof(bottons.audit)!="undefined"&& bottons.audit =="1");
								if (bottons != null && typeof(bottons)!="undefined"&& bottons!=""){
										//alert( bottons.modify != null && typeof(bottons.modify)!="undefined"&& bottons.modify =="1");
										if (bottons.modify != null && typeof(bottons.modify)!="undefined"&& bottons.modify =="1"){
										
												jQuery("#onSaveid").css('display','block');
											}
											
										if (bottons.audit != null && typeof(bottons.audit)!="undefined"&& bottons.audit =="1"){
												jQuery("#onAuditid").css('display','block');
										}
								
								}
								
							}
						}
					
					},
					error : function(data) {
				
					}
				});
           	
               
        });

        function changValue(){
         var jydjyl = Number($('#ZXPRJYDJYL').val()); //今一段加药
            var jedjyl = Number($('#ZXPRJEDJYL').val()); //今二段加药
            var zydjyl = Number($('#zydjy').text()); 	// 昨一段加药
            var zedjyl = Number($('#zedjy').text());	// 昨二段加药
            var zydnd = Number($('#zydnd').text());		// 昨一段浓度
            var zednd = Number($('#zednd').text());		// 昨二段浓度
            var zzhnd = Number($('#zzhnd').text());		// 昨综合浓度

            var jcyel = Number($('#jcyel').val());		// 今产液量
            var jcyoul = Number($('#jcyoul').val());	// 今产油量
            var zcyel = Number($('#zcyel').text());		// 昨产液量
            var zcyoul = Number($('#zcyoul').text());	// 昨产油量

            var jwsyl = Number($('#jwsyl').val());		// 今外输油量
            var jkcyl = Number($('#jkcyl').val());		// 今库存油量
            var zwsyl = Number($('#zwsyl').text());		// 昨外输油量
            var zkcyl = Number($('#zkcyl').text());		// 昨库存油量
            
            
            $('#tdcyel').html((jcyel-zcyel).toFixed(1));   //产液量zj
            $('#tdcyoul').html((jcyoul-zcyoul).toFixed(1));   //产液量zj
            $('#tdwsyl').html((jwsyl-zwsyl).toFixed(1));   //产液量zj
            $('#tdkcyl').html((jkcyl-zkcyl).toFixed(1));   //产液量zj
            
            
            
			$('#tdydjy').html((jydjyl-zydjyl).toFixed(1)); //一段加药量 zj
			$('#tdedjy').html((jedjyl-zedjyl).toFixed(1)); //二段加药量 zj
			if(jcyel.toFixed(1) == "0.0"){
				document.getElementById('ZXPRJYDJYND').value="0.0";
			}else{
				document.getElementById('ZXPRJYDJYND').value=((jydjyl/jcyel)*1000).toFixed(1);
			}
			if(jcyoul.j(1) == "0.0"){
				document.getElementById('ZXPRJEDJYND').value="0.0";
			}else{
				document.getElementById('ZXPRJEDJYND').value=((jedjyl/jcyoul)*1000).toFixed(1);
			}
			var jydnd = Number($('#ZXPRJYDJYND').val()); 		//今一段浓度
			var jednd = Number($('#ZXPRJEDJYND').val());		//今二段浓度
			$('#tdydnd').html((jydnd-zydnd).toFixed(1));//一段浓度 zj
			$('#tdednd').html((jednd-zednd).toFixed(1));//二段浓度 zj
			if(jcyel.toFixed(1) == "0.0"){
				document.getElementById('ZXPRJZHND').value="0.0";
			}else{
				document.getElementById('ZXPRJZHND').value=((jydjyl+jedjyl)/jcyel*1000).toFixed(1);
			}
			var jzhnd = Number($('#ZXPRJZHND').val());			//今综合浓度
			$('#tdzhnd').html((jzhnd-zzhnd).toFixed(1)); //综合浓度增减


			//第二表格
			var ccyl0 = Number($('#ccyl0').text());		//掺柴油量
            var ccyl1 = Number($('#ccyl1').val());

            if(zcyoul.toFixed(1) == "0.0"){
            	$('#ccyndq0').html("0.0");
			}else{
				$('#ccyndq0').html((ccyl0*0.87/zcyoul).toFixed(1));
			}
            if(jcyoul.toFixed(1) == "0.0"){
            	$('#ccyndq1').html("0.0");
			}else{
				$('#ccyndq1').html((ccyl1*0.87/jcyoul).toFixed(1));
			}
			
            var ccyndq0 = Number($('#ccyndq0').text());	//掺柴油浓度
            var ccyndq1 = Number($('#ccyndq1').text());
            $('#ccyndq2').html((ccyndq1-ccyndq0).toFixed(1));

            
        }

        function changValue2(){
            var cyezly0 = Number($('#cyezly0').text());	//采油二站来液
            var cyezly1 = Number($('#cyezly1').val());
            var cyszly0 = Number($('#cyszly0').text());	//采油三站来液
            var cyszly1 = Number($('#cyszly1').val());
            var ccyl0 = Number($('#ccyl0').text());		//掺柴油量
            var ccyl1 = Number($('#ccyl1').val());

            var cjg1hs0 = Number($('#cjg1hs0').text());	//1#沉降罐含水
            var cjg1hs1 = Number($('#cjg1hs1').val());
            var cjg2hs0 = Number($('#cjg2hs0').text());	//2#沉降罐含水
            var cjg2hs1 = Number($('#cjg2hs1').val());
            var tsbhs0 = Number($('#tsbhs0').text());	//提升泵含水
            var tsbhs1 = Number($('#tsbhs1').val());
            var xbcrwd0 = Number($('#xbcrwd0').text());	//相变掺热温度
            var xbcrwd1 = Number($('#xbcrwd1').val());

            var jcyoul = Number($('#jcyoul').val());	// 今产油量
            var zcyoul = Number($('#zcyoul').text());	// 昨产油量

            if(zcyoul.toFixed(1) == "0.0"){
            	$('#ccyndq0').html("0.0");
			}else{
				$('#ccyndq0').html((ccyl0*0.87/zcyoul).toFixed(1));
			}
            if(jcyoul.toFixed(1) == "0.0"){
            	$('#ccyndq1').html("0.0");
			}else{
				$('#ccyndq1').html((ccyl1*0.87/jcyoul).toFixed(1));
			}
			
            var ccyndq0 = Number($('#ccyndq0').text());	//掺柴油浓度
            var ccyndq1 = Number($('#ccyndq1').text());
            $('#ccyndq2').html((ccyndq1-ccyndq0).toFixed(1));

            $('#cyezly2').html((cyezly1-cyezly0).toFixed(1)); //采油二站来液zj
            $('#cyszly2').html((cyszly1-cyszly0).toFixed(1)); //采油三站来液zj
            $('#ccyl2').html((ccyl1-ccyl0).toFixed(1)); 	  //掺柴油量zj
            $('#cjg1hs2').html((cjg1hs1-cjg1hs0).toFixed(1)); //1#沉降罐含水zj
            $('#cjg2hs2').html((cjg2hs1-cjg2hs0).toFixed(1)); //2#沉降罐含水zj
            $('#tsbhs2').html((tsbhs1-tsbhs0).toFixed(1)); 	  //提升泵含水zj
            $('#xbcrwd2').html((xbcrwd1-xbcrwd0).toFixed(1)); //相变掺热温度zj

            
        }

        function changValue3(){
            var jlsl = Number($('#jlsl').val());	//来水量
            var zlsl = Number($('#zlsl').text());
            var jfyql = Number($('#jfyql').val());	//反应器量
            var zfyql = Number($('#zfyql').text());
            var jglql = Number($('#jglql').val());	//过滤器量
            var zglql = Number($('#zglql').text());
            var jwshs = Number($('#jwshs').val());	//污水回收
            var zwshs = Number($('#zwshs').text());
            var jwsl = Number($('#jwsl').val());	//外输量
            var zwsl = Number($('#zwsl').text());

            $('#tdlsl').html((jlsl-zlsl).toFixed(1)); //
            $('#tdfyql').html((jfyql-zfyql).toFixed(1));
            $('#tdglql').html((jglql-zglql).toFixed(1));
            $('#tdwshs').html((jwshs-zwshs).toFixed(1));
            $('#tdwsl').html((jwsl-zwsl).toFixed(1));
            
        }

        function changValue4(){
        	var jsxhyryzlyyl = Number($('#jsxhyryzlyyl').val());
        	var zsxhyryzlyyl = Number($('#zsxhyryzlyyl').text());
        	
        	var jsxhyryzlywd = Number($('#jsxhyryzlywd').val());
        	var zsxhyryzlywd = Number($('#zsxhyryzlywd').text());
        	
        	var jsxhyryzyl = Number($('#jsxhyryzyl').val());
        	var zsxhyryzyl = Number($('#zsxhyryzyl').text());
        	
        	var jsxhyryyjsl = Number($('#jsxhyryyjsl').val());
        	var zsxhyryyjsl = Number($('#zsxhyryyjsl').text());
        	
        	var jsxhyryzlylswd = Number($('#jsxhyryzlylswd').val());
        	var zsxhyryzlylswd = Number($('#zsxhyryzlylswd').text());
        	
        	var jsxhyryzlyhswd = Number($('#jsxhyryzlyhswd').val());
        	var zsxhyryzlyhswd = Number($('#zsxhyryzlyhswd').text());
        	
        	var jsxhyrynjswd = Number($('#jsxhyrynjswd').val());
        	var zsxhyrynjswd = Number($('#zsxhyrynjswd').text());
        	
        	var jsxhyryhrhywd = Number($('#jsxhyryhrhywd').val());
        	var zsxhyryhrhywd = Number($('#zsxhyryhrhywd').text());
        	
        	
        	$('#tdsxhyryzlyyl').html((jsxhyryzlyyl-zsxhyryzlyyl).toFixed(1));
        	$('#tdsxhyryzlywd').html((jsxhyryzlywd-zsxhyryzlywd).toFixed(1));
        	$('#tdsxhyryzyl').html((jsxhyryzyl-zsxhyryzyl).toFixed(1));
        	$('#tdsxhyryyjsl').html((jsxhyryyjsl-zsxhyryyjsl).toFixed(1));
        	$('#tdsxhyryzlylswd').html((jsxhyryzlylswd-zsxhyryzlylswd).toFixed(1));
        	$('#tdsxhyryzlyhswd').html((jsxhyryzlyhswd-zsxhyryzlyhswd).toFixed(1));
        	$('#tdsxhyrynjswd').html((jsxhyrynjswd-zsxhyrynjswd).toFixed(1));
        	$('#tdsxhyryhrhywd').html((jsxhyryhrhywd-zsxhyryhrhywd).toFixed(1));
        
        }

        function changeFvalue(){
			var jfydjyl = Number($('#FXPRJYDJYL').val()); //今一段加药
			var jfedjyl = Number($('#FXPRJEDJYL').val()); //今二段加药
			var jfydnd = Number($('#FXPRJYDJYND').val()); //今一段浓度
			var jfednd = Number($('#FXPRJEDJYND').val()); //今二段浓度
			var zfydjyl = Number($('#zfydjy').text()); //昨一段加药
			var zfedjyl = Number($('#zfedjy').text()); //昨二段加药
			var zfydnd = Number($('#zfydnd').text()); //昨一段浓度
			var zfednd = Number($('#zfednd').text()); //昨二段浓度

			var jfzhnd = Number($('#FXPRJZHND').val()); //今综合浓度
			var zfzhnd = Number($('#zfzhnd').text()); //昨综合浓度

			$('#tdjfydjyl').html((jfydjyl-zfydjyl).toFixed(1)); //一段加药增减
			$('#tdjfydnd').html((jfydnd-zfydnd).toFixed(1)); //一段浓度增减
			$('#tdjfedjyl').html((jfedjyl-zfedjyl).toFixed(1)); //二段加药增减
			$('#tdjfednd').html((jfednd-zfednd).toFixed(1)); //二段浓度增减
			$('#tdjfzhnd').html((jfzhnd-zfzhnd).toFixed(1)); //综合浓度增减
        }

        function checkData(obj){
        	//先把非数字的都替换掉，除了数字和.
    		obj.value = obj.value.replace(/[^\d.]/g,"");
    		//必须保证第一个为数字而不是.
    		obj.value = obj.value.replace(/^\./g,"");
    		//保证只有出现一个.而没有多个.
    		obj.value = obj.value.replace(/\.{2,}/g,".");
    		//保证.只出现一次，而不能出现两次以上
    		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        }

        function changeRed(obj){  //改变值后 判断是否变红
        	if(obj.id=='CJGCSHY'){
            	if(obj.value>6000){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	
        	if(obj.id=='TCGJKHY'){
				if(obj.value>3000){
	        		obj.style.color='red';
	        	}else{
	        		obj.style.color='windowtext';
	        	}
			}
        	
        	if(obj.id=='TCGJKXF'){
        		if(obj.value>300){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
            }
        	
        	if(obj.id=='TCGCKHY'){
        		if(obj.value>150){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
        	}
        	
        	if(obj.id=='TCGCKXF'){
        		if(obj.value>150){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
        	}
        	
        	if(obj.id=='FYGCKHY'){
	        	if(obj.value>15){
	        		obj.style.color='red';
	        	}else{
	        		obj.style.color='windowtext';
	        	}
        	}

        	if(obj.id=='FYGCKXF'){
            	if(obj.value>15){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
    		}

            if(obj.id=='YJGLQCKHY'){
            	if(obj.value>5){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
        	}

        	if(obj.id=='YJGLQCKXF'){
        		if(obj.value>5){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
        	}
        	
        	if(obj.id=='EJGLQCKHY'){
            	if(obj.value>2){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
        	}

        	if(obj.id=='EJGLQCKXF'){
            	if(obj.value>5){
            		obj.style.color='red';
            	}else{
            		obj.style.color='windowtext';
            	}
        	}
        
    		
        }
        
        function onSubmit()
        {
        	//if (onSearchByDate($("#txtDate").val())){
        	 jQuery.ajax({
					type : 'post',
					url : 'zhrb_searchZHRB.action?txtDate='+$("#txtDate").val(),
					success : function(data) { 
					var  TBR ="";
					var  RQ ="";
					var  BZ ="";
					var CCYNDq = "";
					var CCYNDqs = "";
						if (data != null && typeof(data)!="undefined"&& data!=""){
							var outData = eval('(' + data + ')');
							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
									if (outData.JSONDATA != null && typeof(outData.JSONDATA)!="undefined"){
										var fristArr  = outData.JSONDATA.fristArr;
										//alert(fristArr[1].RPD_U2_GENERAL_ID );
										if (fristArr[1].RPD_U2_GENERAL_ID != null && typeof(fristArr[1].RPD_U2_GENERAL_ID)!="undefined" && fristArr[1].RPD_U2_GENERAL_ID != ""){
											TRPD_U2_GENERAL_ID = fristArr[1].RPD_U2_GENERAL_ID;
										}else{
											TRPD_U2_GENERAL_ID = ""	;
										}
										var secondArr  = outData.JSONDATA.secondArr;
										var thirdArr  = outData.JSONDATA.thirdArr;
										var fourdArr  = outData.JSONDATA.fourdArr;
										var fiveArr  = outData.JSONDATA.fiveArr;
										var YYJJDATA  = outData.JSONDATA.YYJJDATA;

									    var REPORTMESSAGE = outData.JSONDATA.REPORTMESSAGE;
									     	if(REPORTMESSAGE != null && typeof(REPORTMESSAGE)!="undefined"){
									     		if(REPORTMESSAGE.RPDREPORTMESSAGEID != null && typeof(REPORTMESSAGE.RPDREPORTMESSAGEID)!="undefined"){
									     				RPDREPORTMESSAGEID =REPORTMESSAGE.RPDREPORTMESSAGEID;	
									     			}else{
									     				RPDREPORTMESSAGEID ="";
									     			}
									     		 if(REPORTMESSAGE.TBR != null && typeof(REPORTMESSAGE.TBR)!="undefined"){
									     			 TBR =REPORTMESSAGE.TBR;	
									     			}else{
									     				TBR = "";
									     			}
												if(REPORTMESSAGE.SHR != null && typeof(REPORTMESSAGE.SHR)!="undefined"){
									     			 SHR =REPORTMESSAGE.SHR;
									     			}else{
									     				SHR = "";
									     			}
												 if(REPORTMESSAGE.RQ != null && typeof(REPORTMESSAGE.RQ)!="undefined"){
									     			  RQ =REPORTMESSAGE.RQ;
									     			}else{
									     				RQ = "";
									     			}
												if(REPORTMESSAGE.BZ != null && typeof(REPORTMESSAGE.BZ)!="undefined"){
									     			  BZ =REPORTMESSAGE.BZ;
									     			}else{
									     				BZ = "";
									     			}
												 //alert(RPDREPORTMESSAGEID);
									     	}else{
									     		RPDREPORTMESSAGEID ="";
									     	}
									     	
									     	
									    	$("#tablearea").html("");
									    	
									    	tableval = "<table border=0 cellpadding=0 cellspacing=0 width=1448 style='border-collapse: "+
												" collapse;table-layout:fixed;width:1090pt'> "+
												" <col width=51 style='mso-width-source:userset;mso-width-alt:1632;width:38pt'> "+
												" <col width=69 span=5 style='mso-width-source:userset;mso-width-alt:2208; "+
												" width:52pt'> "+
												" <col width=77 style='mso-width-source:userset;mso-width-alt:2464;width:58pt'> "+
												" <col width=69 span=11 style='mso-width-source:userset;mso-width-alt:2208; "+
												" width:52pt'> "+
												" <col width=72 span=3 style='width:54pt'> "+
												" <tr height=48 style='mso-height-source:userset;height:36.0pt'> "+
												"  <td colspan=18 height=48 class=xl107 width=1232 style='height:36.0pt; "+
												"  width:928pt'>二号稠油联合处理站日生产简报</td> "+
												"  <td width=72 style='width:54pt'></td> "+
												"  <td width=72 style='width:54pt'></td> "+
												"  <td width=72 style='width:54pt'></td> "+
												" </tr> "+
												" <tr class=xl66 height=26 style='mso-height-source:userset;height:20.1pt'> "+
												"  <td colspan=2 height=26 class=xl67 width=120 style='height:20.1pt;width:90pt'>填报：</td> "+
												"  <td colspan=9 class=xl125 width=629 style='width:474pt'><input id='TBR' type='text' style='background:transparent;border:0px solid;width:629px;height:30px;font-size: 12pt;' value='"+TBR+"'/> </td> "+
												"  <td class=xl67 width=69 style='width:52pt'>审核：</td> "+
												"  <td colspan=2 class=xl123 width=138 style='width:104pt' ><input id='SHR' type='text' style='background:transparent;border:0px solid;width:115px;height:30px;font-size: 12pt;' disabled='disabled' value='"+SHR+"'/></td> "+
												"  <td class=xl66 width=69 style='width:52pt'>日期：</td> "+
												"  <td colspan=3 class=xl123 width=207 style='width:156pt'><input id='RQ' type='text'  style='background:transparent;border:0px solid;width:120px;height:30px;font-size: 12pt;' disabled='disabled' value='"+RQ+"'/></td> "+
												
												" </tr> "+
												" <tr height=33 style='mso-height-source:userset;height:24.95pt'> "+
												"  <td colspan=18 height=33 class=xl1100 width=1232 style='height:24.95pt; "+
												"  width:928pt'>一 原油处理<ruby><font class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td colspan=3 style='mso-ignore:colspan'></td> "+
												" </tr> "+
												" <tr height=26 style='mso-height-source:userset;height:20.1pt'> "+
												"  <td colspan=5 height=26 class=xl102 width=327 style='height:20.1pt; "+
												"  width:246pt'>产量分析<ruby><font class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td colspan=13 class=xl111 width=905 style='border-left:none;width:682pt'>破乳剂使用分析<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td colspan=3 style='mso-ignore:colspan'></td> "+
												" </tr> "+
												" <tr height=26 style='mso-height-source:userset;height:20.1pt'> "+
												"  <td rowspan=2 height=59 class=xl70 width=51 style='height:45.05pt;border-top: "+
												"  none;width:38pt'>　</td> "+
												"  <td rowspan=2 class=xl70 width=69 style='border-top:none;width:52pt'>产液量<br> "+
												  "  （m<font class='font11'><sup>3</sup></font><font class='font7'>）</font></td> "+
												"  <td rowspan=2 class=xl70 width=69 style='border-top:none;width:52pt'>产油量<br> "+
												 "   （t）</td> "+
												"  <td rowspan=2 class=xl70 width=69 style='border-top:none;width:52pt'>外输油量<br> "+
												  "  （t）</td> "+
												"  <td rowspan=2 class=xl70 width=69 style='border-top:none;width:52pt'>库存油量<br> "+
												   " （t）</td> "+
												 " <td colspan=5 class=xl94 width=353 style='border-left:none;width:266pt'>正相破乳剂<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td colspan=5 class=xl94 width=345 style='border-left:none;width:260pt'>反相药剂</td> "+
												"  <td colspan=3 class=xl94 width=207 style='border-left:none;width:156pt'>　</td> "+
												"  <td colspan=3 style='mso-ignore:colspan'></td> "+
												" </tr> "+
												" <tr height=33 style='mso-height-source:userset;height:24.95pt'> "+
												"  <td height=33 class=xl84 width=69 style='height:24.95pt;border-top:none; "+
												"  border-left:none;width:52pt'>一段加药量（kg）<ruby><font class='font5'><rt "+
												"  class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=77 style='border-top:none;border-left:none;width:58pt'>一段浓度（mg/L）<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=69 style='border-top:none;border-left:none;width:52pt'>二段加药量（kg）<ruby><font  "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=69 style='border-top:none;border-left:none;width:52pt'>二段浓度（mg/L）<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=69 style='border-top:none;border-left:none;width:52pt'>综合浓度（mg/L）<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=69 style='border-top:none;border-left:none;width:52pt'>一段加药量（kg）<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=69 style='border-top:none;border-left:none;width:52pt'>一段浓度（mg/L）<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=69 style='border-top:none;border-left:none;width:52pt'>二段加药量（kg）<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=69 style='border-top:none;border-left:none;width:52pt'>二段浓度（mg/L）<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl84 width=69 style='border-top:none;border-left:none;width:52pt'>综合浓度（mg/L）<ruby><font "+
												"  class='font5'><rt class=font5></rt></font></ruby></td> "+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>　</td> "+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>　</td> "+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>　</td> "+
												"  <td colspan=3 style='mso-ignore:colspan'></td> "+
												" </tr> "+
												" <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
												"  <td height=26 class=xl69 width=51 style='height:20.1pt;border-top:none;"+
												"  width:38pt'>昨日</td>"+
												"  <td id='zcyel' class=xl69 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].CYEL+"　</td>"+
												"  <td id='zcyoul' class=xl69 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].CYOUL+"　</td>"+
												"  <td id='zwsyl' class=xl69 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].WSYL+"　</td>"+
												"  <td id='zkcyl' class=xl69 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].KCY+"　　</td>"+
												"  <td id='zydjy' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].ZXPRJYDJYL+"　　</td>"+
												"  <td id='zydnd' class=xl68 width=77 style='border-top:none;border-left:none;width:58pt'>"+fristArr[0].ZXPRJYDJYND+"　</td>"+
												"  <td id='zedjy' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].ZXPRJEDJYL+"　　</td>"+
												"  <td id='zednd' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].ZXPRJEDJYND+"　</td>"+
												"  <td id='zzhnd' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].ZXPRJZHND+"　</td>"+
												"  <td id='zfydjy' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].FXPRJYDJYL+"　　</td>"+
												"  <td id='zfydnd' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].FXPRJYDJYND+"　　</td>"+
												"  <td id='zfedjy' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].FXPRJEDJYL+"　　</td>"+
												"  <td id='zfednd' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].FXPRJEDJYND+"　　</td>"+
												"  <td id='zfzhnd' class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[0].FXPRJZHND+"　　</td>"+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'><input type='hidden' id='YRPD_U2_GENERAL_ID"+i+"' value='"+fristArr[0].RPD_U2_GENERAL_ID+"'/>　　</td>"+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td colspan=3 style='mso-ignore:colspan'></td>"+
												" </tr>"+
												" <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
												"  <td height=26 class=xl70 width=51 style='height:20.1pt;border-top:none;"+
												"  width:38pt'>今日</td>"+
												"  <td class=xl70 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jcyel' onblur='changValue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].CYEL+"'/></td>"+
												"  <td class=xl70 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jcyoul' onblur='changValue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].CYOUL+"'/></td>"+
												"  <td class=xl70 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jwsyl' onblur='changValue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].WSYL+"'/></td>"+
												"  <td class=xl70 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jkcyl' onblur='changValue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].KCY+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='ZXPRJYDJYL' onblur='changValue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].ZXPRJYDJYL+"'/></td>"+
												"  <td class=xl87 width=77 style='border-top:none;border-left:none;width:58pt'><input id='ZXPRJYDJYND' type='text' style='background:transparent;border:0px solid;width:62px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' disabled='disabled' value='"+fristArr[1].ZXPRJYDJYND+"'/>　</td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='ZXPRJEDJYL' onblur='changValue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].ZXPRJEDJYL+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='ZXPRJEDJYND' type='text' style='background:transparent;border:0px solid;width:62px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' disabled='disabled' value='"+fristArr[1].ZXPRJEDJYND+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='ZXPRJZHND' type='text' style='background:transparent;border:0px solid;width:62px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' disabled='disabled' value='"+fristArr[1].ZXPRJZHND+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='FXPRJYDJYL' onblur='changeFvalue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].FXPRJYDJYL+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='FXPRJYDJYND' onblur='changeFvalue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].FXPRJYDJYND+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='FXPRJEDJYL' onblur='changeFvalue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].FXPRJEDJYL+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='FXPRJEDJYND' onblur='changeFvalue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].FXPRJEDJYND+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'><input id='FXPRJZHND' onblur='changeFvalue()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fristArr[1].FXPRJZHND+"'/></td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td class=xl87 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td colspan=3 style='mso-ignore:colspan'></td>"+
												" </tr>"+
												" <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
												"  <td height=26 class=xl70 width=51 style='height:20.1pt;border-top:none;"+
												"  width:38pt'>增减</td>"+
												"  <td id='tdcyel' class=xl85 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].CYEL+"</td>"+
												"  <td id='tdcyoul' class=xl85 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].CYOUL+" </td>"+
												"  <td id='tdwsyl' class=xl85 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].WSYL+"</td>"+
												"  <td id='tdkcyl' class=xl85 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].KCY+"</td>"+
												"  <td id='tdydjy' class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].ZXPRJYDJYL+"</td>"+
												"  <td id='tdydnd' class=xl86 width=77 style='border-top:none;border-left:none;width:58pt'>"+fristArr[2].ZXPRJYDJYND+"</td>"+
												"  <td id='tdedjy' class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].ZXPRJEDJYL+"</td>"+
												"  <td id='tdednd' class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].ZXPRJEDJYND+"</td>"+
												"  <td id='tdzhnd'class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].ZXPRJZHND+"</td>"+
												"  <td id='tdjfydjyl' class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].FXPRJYDJYL+"　</td>"+
												"  <td id='tdjfydnd' class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].FXPRJYDJYND+"</td>"+
												"  <td id='tdjfedjyl' class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].FXPRJEDJYL+"　</td>"+
												"  <td id='tdjfednd' class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].FXPRJEDJYND+"</td>"+
												"  <td id='tdjfzhnd' class=xl86 width=69 style='border-top:none;border-left:none;width:52pt'>"+fristArr[2].FXPRJZHND+"</td>"+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td class=xl68 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td colspan=3 style='mso-ignore:colspan'></td>"+
												" </tr>"+
												" <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
												"  <td colspan=7 height=26 class=xl109 width=473 style='height:20.1pt;"+
												"  width:356pt'>分线计量<ruby><font class='font5'><rt class=font5></rt></font></ruby></td>"+
												"  <td colspan=11 class=xl108 width=759 style='border-left:none;width:572pt'>重点节点参数<ruby><font"+
												"  class='font5'><rt class=font5></rt></font></ruby></td>"+
												"  <td colspan=3 style='mso-ignore:colspan'></td>"+
												" </tr>"+
												" <tr class=xl65 height=40 style='mso-height-source:userset;height:30.0pt'>"+
												"  <td height=40 class=xl71 width=51 style='height:30.0pt;border-top:none;"+
												"  width:38pt'>　</td>"+
												"  <td colspan=2 class=xl71 width=138 style='border-left:none;width:104pt'>采油二站来液（m<font"+
												"  class='font11'><sup>3</sup></font><font class='font7'>）</font></td>"+
												"  <td colspan=2 class=xl71 width=138 style='border-left:none;width:104pt'>采油三站来液（m<font"+
												"  class='font11'><sup>3</sup></font><font class='font7'>）</font></td>"+
												"  <td colspan=2 class=xl71 width=146 style='border-left:none;width:110pt'>掺柴油量（m<font"+
												"  class='font11'><sup>3</sup></font><font class='font7'>）</font></td>"+
												"  <td colspan=2 class=xl72 width=138 style='border-left:none;width:104pt'>掺柴油浓度（%）<ruby><font"+
												"  class='font5'><rt class=font5></rt></font></ruby></td>"+
												"  <td colspan=2 class=xl72 width=138 style='border-left:none;width:104pt'>1<font"+
												"  class='font11'><sup>#</sup></font><font class='font7'>沉降罐含水（0.5m）%</font></td>"+
												"  <td colspan=2 class=xl72 width=138 style='border-left:none;width:104pt'>2<font"+
												"  class='font11'><sup>#</sup></font><font class='font7'>沉降罐含水（0.5m）%</font></td>"+
												"  <td colspan=2 class=xl72 width=138 style='border-left:none;width:104pt'>提升泵含水（%）</td>"+
												"  <td colspan=2 class=xl72 width=138 style='border-left:none;width:104pt'>相变掺热温度（℃）</td>"+
												"  <td class=xl72 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												"  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
												" </tr>";
												for(var i=0; i<secondArr.length; i++) {
													var LINEDATAclass = "xl73";
													if(i == 1){
														LINEDATAclass = "xl71";
													}else if(i == 2){
														LINEDATAclass = "xl71";
													}
													if(i==1){
														tableval +=" <tr class=xl65 height=26 style='mso-height-source:userset;height:20.1pt'>"+
															"  <td height=26 class="+LINEDATAclass+" width=51 style='height:20.1pt;border-top:none; width:38pt'>"+secondArr[i].LINEDATA+"</td>"+
															"  <td  colspan=2 class=xl104 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'><input id='cyezly1' onblur='changValue2()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:139px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].CYEZLY+"'/>　</td>"+
															"  <td  colspan=2 class=xl104 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'><input id='cyszly1' onblur='changValue2()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:139px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].CYSZLY+"'/>　</td>"+
															"  <td  colspan=2 class=xl104 width=146 style='border-right:.5pt solid black; border-left:none;width:110pt'><input id='ccyl1' onblur='changValue2()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:139px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].CCYL+"'/>　</td>";
													}else{
														tableval +=" <tr class=xl65 height=26 style='mso-height-source:userset;height:20.1pt'>"+
															"  <td height=26 class="+LINEDATAclass+" width=51 style='height:20.1pt;border-top:none; width:38pt'>"+secondArr[i].LINEDATA+"</td>"+
															"  <td id='cyezly"+i+"' colspan=2 class=xl104 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'>"+secondArr[i].CYEZLY+"　</td>"+
															"  <td id='cyszly"+i+"' colspan=2 class=xl104 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'>"+secondArr[i].CYSZLY+"　</td>"+
															"  <td id='ccyl"+i+"' colspan=2 class=xl104 width=146 style='border-right:.5pt solid black; border-left:none;width:110pt'>"+secondArr[i].CCYL+"　</td>";
													}
													
														if(secondArr[i].CCYND !=null && secondArr[i].CCYND != "" ){
														  CCYNDq = secondArr[i].CCYND ;
														}else{
														   CCYNDq = "0.0";
														}
														if(i==1){
															tableval +="  <td id='ccyndq1' colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'>"+CCYNDq+"</td>"+
																"  <td colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'><input id='cjg1hs1' onblur='changValue2()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:139px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].CJG1HS+"'/>　</td>"+
																"  <td colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'><input id='cjg2hs1' onblur='changValue2()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:139px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].CJG2HS+"'/>　</td>"+
																"  <td colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'><input id='tsbhs1' onblur='changValue2()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:139px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].TSBHS+"'/>　</td>"+
																"  <td colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'><input id='xbcrwd1' onblur='changValue2()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:139px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+secondArr[i].XBCRWD+"'/>　</td>"+
																"  <td class=xl72 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
																"  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
																" </tr>";
														}else{
															tableval +="  <td id='ccyndq"+i+"' colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'>"+CCYNDq+"</td>"+
																"  <td id='cjg1hs"+i+"' colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'>"+secondArr[i].CJG1HS+"　</td>"+
																"  <td id='cjg2hs"+i+"' colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'>"+secondArr[i].CJG2HS+"　</td>"+
																"  <td id='tsbhs"+i+"' colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'>"+secondArr[i].TSBHS+"　</td>"+
																"  <td id='xbcrwd"+i+"' colspan=2 class=xl120 width=138 style='border-right:.5pt solid black; border-left:none;width:104pt'>"+secondArr[i].XBCRWD+"　</td>"+
																"  <td class=xl72 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
																"  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
																" </tr>";
														}
												}
												
												
												 
												tableval += "<tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
												 " <td colspan=18 height=26 class=xl106 width=1232 style='height:20.1pt;"+
												 " width:928pt'>原油交接情况<ruby><font class='font5'><rt class=font5></rt></font></ruby></td>"+
												 " <td colspan=3 style='mso-ignore:colspan'></td>"+
												 "</tr>"+
												 "<tr height=40 style='mso-height-source:userset;height:30.0pt'>"+
												 " <td height=40 class=xl74 width=51 style='height:30.0pt;border-top:none;"+
												 " width:38pt'>罐号</td>"+
												 " <td class=xl74 width=69 style='border-top:none;border-left:none;width:52pt'>高液位（m）</td>"+
												 " <td class=xl74 width=69 style='border-top:none;border-left:none;width:52pt'>低液位（m）</td>"+
												 " <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'>视密(mg/cm<font"+
												 " class='font15'><sup>3</sup></font><font class='font16'>)</font></td>"+
												 " <td colspan=2 class=xl74 width=146 style='border-left:none;width:110pt'>标密(mg/cm<font"+
												 " class='font15'><sup>3</sup></font><font class='font16'>)</font></td>"+
												 " <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'>罐温℃</td>"+
												 " <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'>含水%</td>"+
												 " <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'>毛油量（t）</td>"+
												 " <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'>纯油量（t）</td>"+
												 " <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'>水量（t）</td>"+
												 " <td class=xl74 width=69 style='border-top:none;border-left:none;width:52pt'>　</td>"+
												 " <td colspan=3 style='mso-ignore:colspan'></td>"+
												 "</tr>";
												for(var i=0; i<YYJJDATA.length; i++) {
										tableval += 	"	 <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
														"	  <td height=26 class=xl75 width=51 style='height:20.1pt;border-top:none;width:38pt'><input id='GH"+i+"' type='text' style='background:transparent;border:0px solid;width:38px;line-height:20px;height:20px;font-size: 12pt;color:red;text-align:center;' value='"+YYJJDATA[i].GH+"'/>　　</td>"+
														"	  <td class=xl74 width=69 style='border-top:none;border-left:none;width:52pt'><input id='GYW"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].GYW+"'/></td>"+
														"	  <td class=xl74 width=69 style='border-top:none;border-left:none;width:52pt'><input id='DYW"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].DYW+"'/></td>"+
														"	  <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'><input id='SM"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:104px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].SM+"'/></td>"+
														"	  <td colspan=2 class=xl74 width=146 style='border-left:none;width:110pt'><input id='BM"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:110px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].BM+"'/>　</td>"+
														"	  <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'><input id='GW"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:104px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].GW+"'/></td>"+
														"	  <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'><input id='HS"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:104px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].HS+"'/>　</td>"+
														"	  <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'><input id='MYL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:104px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].MYL+"'/>　</td>"+
														"	  <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'><input id='CYL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:104px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].CYL+"'/></td>"+
														"	  <td colspan=2 class=xl74 width=138 style='border-left:none;width:104pt'><input id='SL"+i+"' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:104px;line-height:20px;height:20px;font-size: 12pt;text-align:center;' value='"+YYJJDATA[i].SL+"'/>　</td>"+
														"	  <td class=xl74 width=69 style='border-top:none;border-left:none;width:52pt'><input type='hidden' id='RPD_CRUDE_TRANSITION_ID"+i+"' value='"+YYJJDATA[i].RPD_CRUDE_TRANSITION_ID+"'/>　</td>"+
														"	  <td></td>"+
														"	  <td colspan=2 class=xl65 style='mso-ignore:colspan'></td>"+
														"	 </tr>";
												
												} 
											
											
											tableval += "	 <tr height=33 style='mso-height-source:userset;height:24.95pt'>"+
											"	  <td colspan=18 height=33 class=xl97 width=1232 style='height:24.95pt;"+
											"	  width:928pt'>二、污水处理<ruby><font class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td colspan=3 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr height=26 style='mso-height-source:userset;height:20.1pt'>"+
											"	  <td colspan=6 height=26 class=xl90 width=396 style='height:20.1pt;width:298pt'>水量<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td colspan=12 class=xl89 width=836 style='border-left:none;width:630pt'>水质指标<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td colspan=3 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr class=xl65 height=40 style='mso-height-source:userset;height:30.0pt'>"+
											"	  <td height=40 class=xl76 width=51 style='height:30.0pt;border-top:none;"+
											"	  width:38pt'>　</td>"+
											"	  <td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'>来水量（m<font"+
											"	  class='font11'><sup>3</sup></font><font class='font7'>）</font></td>"+
											"	  <td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'>反应器量(m<font"+
											"	  class='font11'><sup>3</sup></font><font class='font7'>)</font></td>"+
											"	  <td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'>过滤器量(m<font"+
											"	  class='font11'><sup>3</sup></font><font class='font7'>)</font></td>"+
											"	  <td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'>污水回收（m<font"+
											"	  class='font11'><sup>3</sup></font><font class='font7'>）</font></td>"+
											"	  <td class=xl76 width=69 style='border-top:none;border-left:none;width:52pt'>外输量（m<font"+
											"	  class='font11'><sup>3</sup></font><font class='font7'>）</font></td>"+
											"	  <td rowspan=2 class=xl77 width=77 style='border-top:none;width:58pt'>　</td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>沉降罐出水</td>"+
											"	  <td colspan=2 class=xl77 width=138 style='border-left:none;width:104pt'>调储罐进口</td>"+
											"	  <td colspan=2 class=xl77 width=138 style='border-left:none;width:104pt'>调储罐出口</td>"+
											"	  <td colspan=2 class=xl77 width=138 style='border-left:none;width:104pt'>反应罐出口</td>"+
											"	  <td colspan=2 class=xl77 width=138 style='border-left:none;width:104pt'>一级过滤器出口<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td colspan=2 class=xl77 width=138 style='border-left:none;width:104pt'>二级过滤器出口<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr class=xl65 height=26 style='mso-height-source:userset;height:20.1pt'>"+
											"	  <td height=26 class=xl78 width=51 style='height:20.1pt;border-top:none;"+
											"	  width:38pt'>昨日</td>"+
											"	  <td id='zlsl' class=xl78 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[0].LSL+"　</td>"+
											"	  <td id='zfyql' class=xl78 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[0].FYQL+"　</td>"+
											"	  <td id='zglql' class=xl78 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[0].GLQL+"　</td>"+
											"	  <td id='zwshs' class=xl78 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[0].WSHS+"　</td>"+
											"	  <td id='zwsl' class=xl78 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[0].WSL+"　</td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>含油<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>含油<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>悬浮<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>含油<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>悬浮<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>含油<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>悬浮<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>含油<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>悬浮<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>含油<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'>悬浮<ruby><font"+
											"	  class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr class=xl65 height=26 style='mso-height-source:userset;height:20.1pt'>"+
											"	  <td height=26 class=xl79 width=51 style='height:20.1pt;border-top:none;"+
											"	  width:38pt'>今日</td>"+
											"	  <td class=xl79 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jlsl' onblur='changValue3()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].LSL+"'/>　</td>"+
											"	  <td class=xl79 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jfyql' onblur='changValue3()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].FYQL+"'/>　</td>"+
											"	  <td class=xl79 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jglql' onblur='changValue3()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].GLQL+"'/>　</td>"+
											"	  <td class=xl79 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jwshs' onblur='changValue3()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].WSHS+"'/>　</td>"+
											"	  <td class=xl79 width=69 style='border-top:none;border-left:none;width:52pt'><input id='jwsl' onblur='changValue3()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+thirdArr[1].WSL+"'/>　</td>"+
											"	  <td class=xl80 width=77 style='border-top:none;border-left:none;width:58pt'>指标<font"+
											"	  class='font13'>(mg/l)</font><ruby><font class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].CJGCSHY+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].TCGJKHY+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].TCGJKXF+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].TCGCKHY+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].TCGCKXF+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].FYGCKHY+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].FYGCKXF+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].YJGLQCKHY+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].YJGLQCKXF+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].EJGLQCKHY+"</td>"+
											"	  <td class=xl80 width=69 style='border-top:none;border-left:none;width:52pt'>≤"+fourdArr[0].EJGLQCKXF+"</td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr class=xl65 height=26 style='mso-height-source:userset;height:20.1pt'>"+
											"	  <td height=26 class=xl79 width=51 style='height:20.1pt;border-top:none;"+
											"	  width:38pt'>增减</td>"+
											"	  <td id='tdlsl' class=xl81 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[2].LSL+"</td>"+
											"	  <td id='tdfyql' class=xl81 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[2].FYQL+"</td>"+
											"	  <td id='tdglql' class=xl81 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[2].GLQL+"</td>"+
											"	  <td id='tdwshs' class=xl81 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[2].WSHS+"</td>"+
											"	  <td id='tdwsl' class=xl81 width=69 style='border-top:none;border-left:none;width:52pt'>"+thirdArr[2].WSL+"</td>"+
											"	  <td class=xl77 width=77 style='border-top:none;border-left:none;width:58pt'>实测<font"+
											"	  class='font14'>(mg/l)</font><ruby><font class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'><input id='CJGCSHY' onkeyup='checkData(this)'  onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].CJGCSHY >= fourdArr[0].CJGCSHY){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].CJGCSHY+"'/>　</td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'><input id='TCGJKHY' onkeyup='checkData(this)'  onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].TCGJKHY >= fourdArr[0].TCGJKHY){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].TCGJKHY+"'/>　</td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'><input id='TCGJKXF'  onkeyup='checkData(this)'  onblur='changeRed(this)'  type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].TCGJKXF >= fourdArr[0].TCGJKXF){
												tableval += "color:red;";
											}
											tableval +="text-align:center;' value='"+fourdArr[1].TCGJKXF+"'/>　</td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'><input id='TCGCKHY' onkeyup='checkData(this)'  onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].TCGCKHY >= fourdArr[0].TCGCKHY){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].TCGCKHY+"'/>　</td>"+
											"	  <td class=xl77 width=69 style='border-top:none;border-left:none;width:52pt'><input id='TCGCKXF' onkeyup='checkData(this)' onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].TCGCKXF >= fourdArr[0].TCGCKXF){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].TCGCKXF+"'/>　</td>"+
											"	  <td class=xl82 width=69 style='border-top:none;border-left:none;width:52pt'><input id='FYGCKHY' onkeyup='checkData(this)' onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].FYGCKHY >= fourdArr[0].FYGCKHY){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].FYGCKHY+"'/>　</td>"+
											"	  <td class=xl83 width=69 style='border-top:none;border-left:none;width:52pt'><input id='FYGCKXF' onkeyup='checkData(this)' onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].FYGCKXF >= fourdArr[0].FYGCKXF){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].FYGCKXF+"'/>　</td>"+
											"	  <td class=xl82 width=69 style='border-top:none;border-left:none;width:52pt'><input id='YJGLQCKHY' onkeyup='checkData(this)' onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].YJGLQCKHY >= fourdArr[0].YJGLQCKHY){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].YJGLQCKHY+"'/>　</td>"+
											"	  <td class=xl82 width=69 style='border-top:none;border-left:none;width:52pt'><input id='YJGLQCKXF' onkeyup='checkData(this)' onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].YJGLQCKXF >= fourdArr[0].YJGLQCKXF){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].YJGLQCKXF+"'/>　</td>"+
											"	  <td class=xl82 width=69 style='border-top:none;border-left:none;width:52pt'><input id='EJGLQCKHY' onkeyup='checkData(this)' onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].EJGLQCKHY >= fourdArr[0].EJGLQCKHY){
												tableval += "color:red;";
											}
											tableval += "text-align:center;' value='"+fourdArr[1].EJGLQCKHY+"'/>　</td>"+
											"	  <td class=xl82 width=69 style='border-top:none;border-left:none;width:52pt'><input id='EJGLQCKXF' onkeyup='checkData(this)' onblur='changeRed(this)' type='text' style='background:transparent;border:0px solid;width:69px;line-height:30px;height:30px;font-size: 12pt;";
											if(fourdArr[1].EJGLQCKXF >= fourdArr[0].EJGLQCKXF){
												tableval += "color:red;";
											}
											
											tableval += "text-align:center;' value='"+fourdArr[1].EJGLQCKXF+"'/>　</td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>";
											
											tableval += "	 <tr height=33 style='mso-height-source:userset;height:24.95pt'>"+
											"	  <td colspan=18 height=33 class=xl97 width=1232 style='height:24.95pt;"+
											"	  width:928pt'>三、SAGD循环预热液处理<ruby><font class='font5'><rt class=font5></rt></font></ruby></td>"+
											"	  <td colspan=3 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr class=xl65 height=40 style='mso-height-source:userset;height:30.0pt'>"+
											"	  <td height=40 class=xl76 width=51 style='height:30.0pt;border-top:none;"+
											"	  width:38pt'>　</td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>总来液压力（MPa）</td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>总来液温度（℃）</td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>总液量（m<font"+
											"	  class='font11'><sup>3</sup></font><font class='font7'>）</font></td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>凝结水量（m<font"+
											"	  class='font11'><sup>3</sup></font><font class='font7'>）</font></td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>总冷源来水温度（℃）</td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>总冷源回水温度（℃）</td>"+
											"	  <td colspan=2 class=xl77 width=138 style='border-top:none;border-left:none;width:104pt'>凝结水温温度（℃）</td>"+
											"	  <td colspan=3 class=xl77 width=138 style='border-top:none;border-left:none;width:104pt'>换热后油温度（℃）</td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr class=xl65 height=40 style='mso-height-source:userset;height:30.0pt'>"+
											"	  <td height=40 class=xl76 width=51 style='height:30.0pt;border-top:none;"+
											"	  width:38pt'>昨日</td>"+
											"	  <td id='zsxhyryzlyyl' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[0].SXHYRYZLYYL+"</td>"+
											"	  <td id='zsxhyryzlywd' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[0].SXHYRYZLYWD+"</td>"+
											"	  <td id='zsxhyryzyl' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[0].SXHYRYZYL+"</td>"+
											"	  <td id='zsxhyryyjsl' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[0].SXHYRYYJSL+"</td>"+
											"	  <td id='zsxhyryzlylswd' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[0].SXHYRYZLYLSWD+"</td>"+
											"	  <td id='zsxhyryzlyhswd' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[0].SXHYRYZLYHSWD+"</td>"+
											"	  <td id='zsxhyrynjswd' colspan=2 class=xl77 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[0].SXHYRYNJSWD+"</td>"+
											"	  <td id='zsxhyryhrhywd' colspan=3 class=xl77 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[0].SXHYRYHRHYWD+"</td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr class=xl65 height=40 style='mso-height-source:userset;height:30.0pt'>"+
											"	  <td height=40 class=xl76 width=51 style='height:30.0pt;border-top:none;"+
											"	  width:38pt'>今日</td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'><input id='jsxhyryzlyyl' onblur='changValue4()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[1].SXHYRYZLYYL+"'/></td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'><input id='jsxhyryzlywd' onblur='changValue4()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[1].SXHYRYZLYWD+"'/></td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'><input id='jsxhyryzyl' onblur='changValue4()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[1].SXHYRYZYL+"'/></td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'><input id='jsxhyryyjsl' onblur='changValue4()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[1].SXHYRYYJSL+"'/></td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'><input id='jsxhyryzlylswd' onblur='changValue4()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[1].SXHYRYZLYLSWD+"'/></td>"+
											"	  <td colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'><input id='jsxhyryzlyhswd' onblur='changValue4()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[1].SXHYRYZLYHSWD+"'/></td>"+
											"	  <td colspan=2 class=xl77 width=138 style='border-top:none;border-left:none;width:104pt'><input id='jsxhyrynjswd' onblur='changValue4()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[1].SXHYRYNJSWD+"'/></td>"+
											"	  <td colspan=3 class=xl77 width=138 style='border-top:none;border-left:none;width:104pt'><input id='jsxhyryhrhywd' onblur='changValue4()' onkeyup='checkData(this)' type='text' style='background:transparent;border:0px solid;width:52px;line-height:30px;height:30px;font-size: 12pt;text-align:center;' value='"+fiveArr[1].SXHYRYHRHYWD+"'/></td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr class=xl65 height=40 style='mso-height-source:userset;height:30.0pt'>"+
											"	  <td height=40 class=xl76 width=51 style='height:30.0pt;border-top:none;"+
											"	  width:38pt'>增减</td>"+
											"	  <td id='tdsxhyryzlyyl' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[2].SXHYRYZLYYL+"</td>"+
											"	  <td id='tdsxhyryzlywd' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[2].SXHYRYZLYWD+"</td>"+
											"	  <td id='tdsxhyryzyl' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[2].SXHYRYZYL+"</td>"+
											"	  <td id='tdsxhyryyjsl' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[2].SXHYRYYJSL+"</td>"+
											"	  <td id='tdsxhyryzlylswd' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[2].SXHYRYZLYLSWD+"</td>"+
											"	  <td id='tdsxhyryzlyhswd' colspan=2 class=xl76 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[2].SXHYRYZLYHSWD+"</td>"+
											"	  <td id='tdsxhyrynjswd' colspan=2 class=xl77 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[2].SXHYRYNJSWD+"</td>"+
											"	  <td id='tdsxhyryhrhywd' colspan=3 class=xl77 width=138 style='border-top:none;border-left:none;width:104pt'>"+fiveArr[2].SXHYRYHRHYWD+"</td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>";
											
											tableval += "	 <tr class=xl65 height=45 style='mso-height-source:userset;height:33.75pt'>"+
											"	  <td colspan=18 height=45 class=xl124 width=1232 style='height:33.75pt;"+
											"	  width:928pt'>备注：<input id='BZ' type='text' style='background:transparent;border:0px solid;width:475px;height:30px;font-size: 12pt;' value='"+BZ+"'/> </td>"+
											"	  <td colspan=3 class=xl65 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <tr height=57 style='mso-height-source:userset;height:42.75pt'>"+
											"	  <td height=57 style='height:42.75pt'></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td></td>"+
											"	  <td colspan=3 style='mso-ignore:colspan'></td>"+
											"	 </tr>"+
											"	 <![if supportMisalignedColumns]>"+
											"	 <tr height=0 style='display:none'>"+
											"	  <td width=51 style='width:38pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=77 style='width:58pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=69 style='width:52pt'></td>"+
											"	  <td width=72 style='width:54pt'></td>"+
											"	  <td width=72 style='width:54pt'></td>"+
											"	  <td width=72 style='width:54pt'></td>"+
											"	 </tr>"+
											"	 <![endif]>"+
											"	</table>";
												
												$("#tablearea").html(tableval);
									    
									    
									    
										
									}else{
										$.ligerDialog.error("当前日期无数据，请选择其他日期！");
									}
							
								}
							}
						
					},
					error : function(data) {
				
					}
				});
        	//}else {
        	//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
        	//}
        }
        
         function onSave()
        {
			var	TBR = $("#TBR").val();
			var	BZ = $("#BZ").val();
			var	RQ = $("#RQ").val();
        	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
        		$.ligerDialog.error("数据已审核过，不能进行修改");
        	}else{
        	   
				var ZXPRJYDJYL=$("#ZXPRJYDJYL").val();
				var ZXPRJYDJYND=$("#ZXPRJYDJYND").val();
				var ZXPRJEDJYL=$("#ZXPRJEDJYL").val();
				var ZXPRJEDJYND=$("#ZXPRJEDJYND").val();
				var ZXPRJZHND=$("#ZXPRJZHND").val();
													
				var FXPRJYDJYL=$("#FXPRJYDJYL").val();
				var FXPRJYDJYND=$("#FXPRJYDJYND").val();
				var FXPRJEDJYL=$("#FXPRJEDJYL").val();
				var FXPRJEDJYND=$("#FXPRJEDJYND").val();
				var FXPRJZHND=$("#FXPRJZHND").val();
				var RPD_CRUDE_TRANSITION_ID0=$("#RPD_CRUDE_TRANSITION_ID0").val();
				var GH0=$("#GH0").val();
				var GYW0=$("#GYW0").val();
				var DYW0=$("#DYW0").val();
				var SM0=$("#SM0").val();
				var BM0=$("#BM0").val();
				var GW0=$("#GW0").val();
				var HS0=$("#HS0").val();
				var MYL0=$("#MYL0").val();
				var CYL0=$("#CYL0").val();
				var SL0=$("#SL0").val();
				
				var RPD_CRUDE_TRANSITION_ID1=$("#RPD_CRUDE_TRANSITION_ID1").val();
				var GH1=$("#GH1").val();
				var GYW1=$("#GYW1").val();
				var DYW1=$("#DYW1").val();
				var SM1=$("#SM1").val();
				var BM1=$("#BM1").val();
				var GW1=$("#GW1").val();
				var HS1=$("#HS1").val();
				var MYL1=$("#MYL1").val();
				var CYL1=$("#CYL1").val();
				var SL1=$("#SL1").val();
				
				var RPD_CRUDE_TRANSITION_ID2=$("#RPD_CRUDE_TRANSITION_ID2").val();
				var GH2=$("#GH2").val();
				var GYW2=$("#GYW2").val();
				var DYW2=$("#DYW2").val();
				var SM2=$("#SM2").val();
				var BM2=$("#BM2").val();
				var GW2=$("#GW2").val();
				var HS2=$("#HS2").val();
				var MYL2=$("#MYL2").val();
				var CYL2=$("#CYL2").val();
				var SL2=$("#SL2").val();
				
				var CJGCSHY=$("#CJGCSHY").val();
				var TCGJKHY=$("#TCGJKHY").val();
				var TCGJKXF=$("#TCGJKXF").val();
				var TCGCKHY=$("#TCGCKHY").val();
				var TCGCKXF=$("#TCGCKXF").val();
				var FYGCKHY=$("#FYGCKHY").val();
				var FYGCKXF=$("#FYGCKXF").val();
				var YJGLQCKHY=$("#YJGLQCKHY").val();
				var YJGLQCKXF=$("#YJGLQCKXF").val();
				var EJGLQCKHY=$("#EJGLQCKHY").val();
				var EJGLQCKXF=$("#EJGLQCKXF").val();

				var CYEL=$('#jcyel').val();
				var CYOUL=$('#jcyoul').val();
				var WSYL=$('#jwsyl').val();
				var KCYL=$('#jkcyl').val();
				
				var CYEZLY=$('#cyezly1').val();
				var CYSZLY=$('#cyszly1').val();
				var CCYL=$('#ccyl1').val();
				var CCYNDQ=$('#ccyndq1').val();
				var CJG1HS=$('#cjg1hs1').val();
				var CJG2HS=$('#cjg2hs1').val();
				var TSBHS=$('#tsbhs1').val();
				var XBCRWD=$('#xbcrwd1').val();

				var LSL=$('#jlsl').val();
				var FYQL=$('#jfyql').val();
				var GLQL=$('#jglql').val();
				var WSHS=$('#jwshs').val();
				var WSL=$('#jwsl').val();

				var SXHYRYZLYYL = $('#jsxhyryzlyyl').val();
				var SXHYRYZLYWD = $('#jsxhyryzlywd').val();
				var SXHYRYZYL = $('#jsxhyryzyl').val();
				var SXHYRYYJSL = $('#jsxhyryyjsl').val();
				var SXHYRYZLYLSWD = $('#jsxhyryzlylswd').val();
				var SXHYRYZLYHSWD = $('#jsxhyryzlyhswd').val();
				var SXHYRYNJSWD = $('#jsxhyrynjswd').val();
				var SXHYRYHRHYWD = $('#jsxhyryhrhywd').val();
				
				if (modifyDataFlag($("#txtDate").val())) {
	        	
					jQuery.ajax({
									type : 'post',
									url : 'zhrb_updateZHRB.action?nowdata='+parseInt(Math.random()*100000),
									data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"TBR":TBR,"BZ":BZ,"RQ":RQ,"TRPD_U2_GENERAL_ID":TRPD_U2_GENERAL_ID,
									"ZXPRJYDJYL":ZXPRJYDJYL,"ZXPRJYDJYND":ZXPRJYDJYND,"ZXPRJEDJYL":ZXPRJEDJYL,"ZXPRJEDJYND":ZXPRJEDJYND,"ZXPRJZHND":ZXPRJZHND,
									"FXPRJYDJYL":FXPRJYDJYL,"FXPRJYDJYND":FXPRJYDJYND,"FXPRJEDJYL":FXPRJEDJYL,"FXPRJEDJYND":FXPRJEDJYND,"FXPRJZHND":FXPRJZHND,
									"RPD_CRUDE_TRANSITION_ID0":RPD_CRUDE_TRANSITION_ID0,"GH0":GH0,"GYW0":GYW0,"DYW0":DYW0,"SM0":SM0,"BM0":BM0,"GW0":GW0,"HS0":HS0,"MYL0":MYL0,"CYL0":CYL0,"SL0":SL0,
									"RPD_CRUDE_TRANSITION_ID1":RPD_CRUDE_TRANSITION_ID1,"GH1":GH1,"GYW1":GYW1,"DYW1":DYW1,"SM1":SM1,"BM1":BM1,"GW1":GW1,"HS1":HS1,"MYL1":MYL1,"CYL1":CYL1,"SL1":SL1,
									"RPD_CRUDE_TRANSITION_ID2":RPD_CRUDE_TRANSITION_ID2,"GH2":GH2,"GYW2":GYW2,"DYW2":DYW2,"SM2":SM2,"BM2":BM2,"GW2":GW2,"HS2":HS2,"MYL2":MYL2,"CYL2":CYL2,"SL2":SL2,
									"CJGCSHY":CJGCSHY,"TCGJKHY":TCGJKHY,"TCGJKXF":TCGJKXF,
									"TCGCKHY":TCGCKHY,"TCGCKXF":TCGCKXF,"FYGCKHY":FYGCKHY,"FYGCKXF":FYGCKXF,
									"YJGLQCKHY":YJGLQCKHY,"YJGLQCKXF":YJGLQCKXF,"EJGLQCKHY":EJGLQCKHY,"EJGLQCKXF":EJGLQCKXF,

									"CYEL":CYEL,"CYOUL":CYOUL,"WSYL":WSYL,"KCYL":KCYL,
									"CYEZLY":CYEZLY,"CYSZLY":CYSZLY,"CCYL":CCYL,"CCYNDQ":CCYNDQ,"CJG1HS":CJG1HS,"CJG2HS":CJG2HS,"TSBHS":TSBHS,"XBCRWD":XBCRWD,"LSL":LSL,"FYQL":FYQL,"GLQL":GLQL,"WSHS":WSHS,"WSL":WSL,
									"SXHYRYZLYYL":SXHYRYZLYYL,"SXHYRYZLYWD":SXHYRYZLYWD,"SXHYRYZYL":SXHYRYZYL,"SXHYRYYJSL":SXHYRYYJSL,"SXHYRYZLYLSWD":SXHYRYZLYLSWD,"SXHYRYZLYHSWD":SXHYRYZLYHSWD,"SXHYRYNJSWD":SXHYRYNJSWD,"SXHYRYHRHYWD":SXHYRYHRHYWD
									},
									success : function(data) { 
										if (data != null && typeof(data)!="undefined"){
											var outData = eval('(' + data + ')');
											if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
												$.ligerDialog.error(outData.ERRMSG);
											}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
												$.ligerDialog.error(outerror(outData.ERRCODE));
											}else{
												onSubmit();
												$.ligerDialog.success('修改成功！');
											}
										}
									},
									error : function(data) {
								
									}
								});
				} else {
    				$.ligerDialog.error("只能对当日和明日报表数据进行修改");
    			}
				
        		 
        	}
			
			//alert(RPDREPORTMESSAGEID);
			
        }
        
          function onAudit()
        {
        	//var RPDREPORTMESSAGEID = $("#RPDREPORTMESSAGEID").val();
        	if (SHR != null && typeof(SHR)!="undefined" && SHR != ""){
        		$.ligerDialog.error("数据已审核过，不能进行再次审核");
        	}else{
        		if (chekAduitByDate($("#txtDate").val())) {
        		 jQuery.ajax({
					type : 'post',
					url : 'zhrb_onAudit.action?nowdata='+parseInt(Math.random()*100000),
					data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID},
					success : function(data) { 
						if (data != null && typeof(data)!="undefined"){
							var outData = eval('(' + data + ')');
							if(outData.ERRMSG != null && typeof(outData.ERRMSG)!="undefined"){
								$.ligerDialog.error(outData.ERRMSG);
							}else if(outData.ERRCODE != null && typeof(outData.ERRCODE)!="undefined"){
								$.ligerDialog.error(outerror(outData.ERRCODE));
							}else{
								onSubmit();
								$.ligerDialog.success('审核成功！');
							}
						}
					},
					error : function(data) {
				
					}
				});
        		} else {
    				$.ligerDialog.error("审核时间无效,不能进行审核！");
    			}
        	}
			
			//alert(RPDREPORTMESSAGEID);
			
        }
        
        
	function onExport() {
		var reportDate=$("#txtDate").val();
     		var url = "zhrb_exportU2_GENERAL.action?reportDate="+encodeURIComponent(reportDate);
     		//if (onSearchByDate(reportDate)) {
    			$.ligerDialog.confirm('确定导出吗?',function (yes) {
    				if (yes) {
    					window.location.href= url;
    				}
    			});
    		//} else {
    		//	$.ligerDialog.error("选择日期无效，请选择其他日期！");
    		//}
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
       <!--table
			{mso-displayed-decimal-separator:"\.";
			mso-displayed-thousand-separator:"\,";}
		@page
			{margin:.2in .2in .2in .2in;
			mso-header-margin:0in;
			mso-footer-margin:0in;
			mso-page-orientation:landscape;}
		ruby
			{ruby-align:left;}
		rt
			{color:windowtext;
			font-size:9.0pt;
			font-weight:400;
			font-style:normal;
			text-decoration:none;
			font-family:宋体;
			mso-generic-font-family:auto;
			mso-font-charset:134;
			mso-char-type:none;
			display:none;}
		-->

    </style>

</head>
<body style="padding:10px"> 
<form name="formsearch" method="post"  id="form1">
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>


				<table>
					<tr>	
		                 <td align="right" class="l-table-edit-td" style="width:60px">日期：</td>
						<td><input type="text" id="txtDate" ltype="date"/></td>
						<td align="right" class="l-table-edit-td" style="width:100px"><a href="javascript:onSubmit()" class="l-button"
						style="width:100px">查询</a></td>
						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onExport()" class="l-button"
							style="width:100px">导出报表</a>
						</td>
						
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onSave()" class="l-button" style="width:100px;display:none" id="onSaveid">保存</a>
						</td>
						<td align="right" class="l-table-edit-td" style="width:100px">
							<a href="javascript:onAudit()" class="l-button" style="width:100px;display:none" id="onAuditid">审核</a>
						</td>
						
						
						<!-- <td id="jieshi"></td> -->
						</tr>
				
				</table>
<div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
<!--  -->
<div id="tablearea" style="OVERFLOW:auto; width: 99%;height: 89%;" align="center">

</div>

     
  </form>
    
</body>
</html>
