       //稠油2号联合站 数据校验 列名中文对照
        var name = function getColumName(colum){
        	var chinaname = colum;
        	//HYDJ	HY1_2	HYDC	HYFC	HYHNC	HYYJJ	HYYJC	HYEJC	HYRHS
        	//XFDJ	XF1_2	XFDC	XFFC	XFHNC	XFYJJ	XFYJC	XFEJC	XFRHS

        	if(colum != '' && typeof(colum)!="undefined"){
        		if(colum == "HYDJ"){
        			chinaname = "含油--调进";
        		}else if(colum == "HY1_2"){
        			chinaname = "含油--1#-2#";
        		}else if(colum == "HYDC"){
        			chinaname = "含油--调出";
        		}else if(colum == "HYFC"){
        			chinaname = "含油--反出";
        		}else if(colum == "HYHNC"){
        			chinaname = "含油--混凝出";
        		}else if(colum == "HYYJJ"){
        			chinaname = "含油--一级进";
        		}else if(colum == "HYYJC"){
        			chinaname = "含油--一级出";
        		}else if(colum == "HYEJC"){
        			chinaname = "含油--二级出";
        		}else if(colum == "HYRHS"){
        			chinaname = "含油--软化水";
        		} 
        		
        		
        		else if(colum == "XFDJ"){
        			chinaname = "悬浮--调进";
        		}else if(colum == "XF1_2"){
        			chinaname = "悬浮--1#-2#";
        		}else if(colum == "XFDC"){
        			chinaname = "悬浮--调出";
        		}else if(colum == "XFFC"){
        			chinaname = "悬浮--反出";
        		}else if(colum == "XFHNC"){
        			chinaname = "悬浮--混凝出";
        		}else if(colum == "XFYJJ"){
        			chinaname = "悬浮--一级进";
        		}else if(colum == "XFYJC"){
        			chinaname = "悬浮--一级出";
        		}else if(colum == "XFEJC"){
        			chinaname = "悬浮--二级出";
        		}else if(colum == "XFRHS"){
        			chinaname = "悬浮--软化水";
        		}
        	}
        	
        	return chinaname;
        	
        }
        
      
        