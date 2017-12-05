package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dto.PcRpdGqlhzzhT;
import com.echo.service.GQLHZZHService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class GQLHZZHServiceImpl implements GQLHZZHService{
	 private CommonDao commonDao;
	 
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public JSONObject searchDataSet(String txtDate) throws Exception {
		String beforetime = DateBean.getBeforeDAYTime(txtDate);
		String  sqls ="select RPD_GQLHZ_ID , BBSJ, CJSJ, DLS , SZQZQL,GRGGZQL,TLTC,GLZYXSJ,JHTLSJ,FJHTLSJ,";
		//蒸汽质量
		sqls +="GGDCJHGL, GRDCJHGL ,SLCJHGL ,DHCJHGL ,";
		//能耗统计
		sqls +="SZQGLRHTRQ ,GRGLRHTRQ,SZQGLRHD,GRGLRHD,SCLZRHD,HML,";
		//用水情况
		sqls +="	SYJ,LJS,TJHWS1,TJHWS2,RHQZGSL,CYQZGSL,";
		//1#特净化水
		sqls +="HYA,XFA,YDA,LGA,";
		sqls +="HYB,XFB,YDB,LGB,";
		sqls +="YDC,LGC,JZCYQCKHY ,YDD,LGD ,CYQCKHY1,YDE,LGE,CYQCKHY2,YDF,LGF,XDF,HYF,PHZ,WYSCLCK ";
		sqls +="   from  PC_RPD_GQLHZZH_T  a  where 1=1 ";
		sqls += " and BBSJ between TO_DATE('"+beforetime+"','YYYY-MM-DD') and TO_DATE('"+txtDate+"','YYYY-MM-DD') order by BBSJ ";
		//sqls +="   and BBSJ = to_date('"+txtDate+"','YYYY-MM-DD') ";
		//无盐水处理出口电导率
		String sql ="select JZSSGYW,JZZSGYW,JZRSGYW,TQSSSGYW1,TQRSSGYW1,TJHSSSGYW1,QSSSGYW2,QSRSGYW2,JHSSGYW2,JHRSGYW2,";
		sql +="JZZSBYL,JZZYBYL,YQZYBYL1,EQZYBYL1 ,SQZYBYL1,YQZYBYL2,EQZYBYL2,JZZSSBSL,JZKYJYL,ZSSBSL1,";
		sql +="TKYJYL1,ZSSBSL2,TKYJYL2 ,FSTGSSS ,GSLJL,NYSPSL,RHGYW,ZGJJY,KYJYL,ZYBYL,BTJYL,";
		sql +="JZJY,JZYL,JY1,YY1,JY2,YY2,ZQL,YSL,GHYCSL,GYSPFL,CML,YDL,XPJYL,LSEQNYL,SHSYL";
		sql +="   from  PC_RPD_GQLHZZH_T  a  where 1=1 ";
		sql +="   and BBSJ = to_date('"+txtDate+"','YYYY-MM-DD') ";
		
		List<Object[]> dataSetOne = commonDao.searchEverySql(sqls);
		List<Object[]> dataSetTwo = commonDao.searchEverySql(sql);
		
		JSONObject obj = new JSONObject();
		obj = GetJSonObj(dataSetOne,dataSetTwo,beforetime,txtDate);
		return obj;
	}
  public JSONObject GetJSonObj(List<Object[]>dataSetOne,List<Object[]>dataSetTwo,String beforetime,String txtDate){
	  
	    int yeardayflag = 0; // 昨日数据存在   0：不存在，1存在
		int nowflag = 0; // 今日数据存在   0：不存在，1存在
		Object[] yearData = null;
		Object[]  nowData = null;
		
		for(Object[] data:dataSetOne){
			if(data[1] != null && beforetime.equals(String.valueOf(data[1]))){
				yeardayflag = 1;
				yearData = data;
			}
			
			if(data[1] != null && txtDate.equals(String.valueOf(data[1]))){
				nowflag = 1; 
				nowData = data;
			}
		}
		
		JSONObject  obj =null;
		JSONArray   oneArr =null;
		JSONArray   twoArr = null;
		JSONArray   thrArr = null;
		JSONArray   forArr = null;
		
		obj = new JSONObject();
		if(yeardayflag == 1){
			//昨日
			
			oneArr = new JSONArray();
			obj.put("RPD_GQLHZ_ID", yearData[0].toString());
			// --锅炉运行数据
			obj.put("DLS", CommonsUtil.remUndef(yearData[3]));
			obj.put("SZQZQL", CommonsUtil.getNotOneDataZ(yearData[4]));
			obj.put("GRGGZQL", CommonsUtil.getNotOneDataZ(yearData[5]));
			obj.put("TLTC", CommonsUtil.remUndef(yearData[6]));
			obj.put("GLZYXSJ", CommonsUtil.getNotOneDataZ(yearData[7]));
			obj.put("JHTLSJ", CommonsUtil.getNotOneDataZ(yearData[8]));
			obj.put("FJHTLSJ", CommonsUtil.getNotOneDataZ(yearData[9]));
			//蒸汽质量
			obj.put("GGDCJHGL", CommonsUtil.remUndef(yearData[10]));
			obj.put("GRDCJHGL", CommonsUtil.remUndef(yearData[11]));
			obj.put("SLCJHGL", CommonsUtil.remUndef(yearData[12]));
			obj.put("DHCJHGL", CommonsUtil.remUndef(yearData[13]));
			//能耗统计
			obj.put("SZQGLRHTRQ", CommonsUtil.getNotOneDataZ(yearData[14]));
			obj.put("GRGLRHTRQ", CommonsUtil.getNotOneDataZ(yearData[15]));
			obj.put("SZQGLRHD", CommonsUtil.getNotOneDataZ(yearData[16]));
			obj.put("GRGLRHD", CommonsUtil.getNotOneDataZ(yearData[17]));
			obj.put("SCLZRHD", CommonsUtil.getNotOneDataZ(yearData[18]));
			obj.put("HML", CommonsUtil.getNotTwoData(yearData[19]));
			//--用水情况
			obj.put("SYJ", CommonsUtil.getNotOneDataZ(yearData[20]));
			obj.put("LJS", CommonsUtil.getNotOneDataZ(yearData[21]));
			obj.put("TJHWS1", CommonsUtil.getNotOneDataZ(yearData[22]));
			obj.put("TJHWS2", CommonsUtil.getNotOneDataZ(yearData[23]));
			obj.put("RHQZGSL", CommonsUtil.getNotOneDataZ(yearData[24]));
			obj.put("CYQZGSL", CommonsUtil.getNotOneDataZ(yearData[25]));
			//1#特净化水
			obj.put("HYA", CommonsUtil.getNotOneDataZ(yearData[26]));
			obj.put("XFA", CommonsUtil.getNotOneDataZ(yearData[27]));
			obj.put("YDA", CommonsUtil.getNotOneDataZ(yearData[28]));
			obj.put("LGA", CommonsUtil.getNotOneDataZ(yearData[29]));
			
			obj.put("HYB", CommonsUtil.getNotOneDataZ(yearData[30]));
			obj.put("XFB", CommonsUtil.getNotOneDataZ(yearData[31]));
			obj.put("YDB", CommonsUtil.getNotOneDataZ(yearData[32]));
			obj.put("LGB", CommonsUtil.getNotOneDataZ(yearData[33]));
			obj.put("YDC", CommonsUtil.getNotOneDataZ(yearData[34]));
			obj.put("LGC", CommonsUtil.getNotOneDataZ(yearData[35]));
			obj.put("JZCYQCKHY", CommonsUtil.getNotOneDataZ(yearData[36]));
			obj.put("YDD", CommonsUtil.getNotOneDataZ(yearData[37]));
			obj.put("LGD", CommonsUtil.getNotOneDataZ(yearData[38]));
			
			//1#除氧器出口含氧
			obj.put("CYQCKHY1", CommonsUtil.getNotOneDataZ(yearData[39]));
			//2#特生水
			obj.put("YDE", CommonsUtil.getNotOneDataZ(yearData[40]));
			obj.put("LGE", CommonsUtil.getNotOneDataZ(yearData[41]));
			obj.put("CYQCKHY2", CommonsUtil.getNotOneDataZ(yearData[42]));
			//--锅炉软水
			obj.put("YDF", CommonsUtil.getNotOneDataZ(yearData[43]));
			obj.put("LGF", CommonsUtil.getNotOneDataZ(yearData[44]));
			obj.put("XDF", CommonsUtil.getNotOneDataZ(yearData[45]));
			obj.put("HYF", CommonsUtil.getNotOneDataZ(yearData[46]));
			obj.put("PHZ", CommonsUtil.getNotOneDataZ(yearData[47]));
			obj.put("WYSCLCK", CommonsUtil.getNotOneDataZ(yearData[48]));
			oneArr.add(obj);
		}else{
			oneArr = new JSONArray();
			obj.put("RPD_GQLHZ_ID","");
			// --锅炉运行数据
			obj.put("DLS", "");
			obj.put("SZQZQL","");
			obj.put("GRGGZQL","");
			obj.put("TLTC", "");
			obj.put("GLZYXSJ", "");
			obj.put("JHTLSJ", "");
			obj.put("FJHTLSJ", "");
			//蒸汽质量
			obj.put("GGDCJHGL", "");
			obj.put("GRDCJHGL", "");
			obj.put("SLCJHGL", "");
			obj.put("DHCJHGL", "");
			//能耗统计
			obj.put("SZQGLRHTRQ","");
			obj.put("GRGLRHTRQ", "");
			obj.put("SZQGLRHD", "");
			obj.put("GRGLRHD", "");
			obj.put("SCLZRHD", "");
			obj.put("HML", "");
			//--用水情况
			obj.put("SYJ","");
			obj.put("LJS", "");
			obj.put("TJHWS1", "");
			obj.put("TJHWS2", "");
			obj.put("RHQZGSL", "");
			obj.put("CYQZGSL", "");
			//1#特净化水
			obj.put("HYA", "");
			obj.put("XFA", "");
			obj.put("YDA", "");
			obj.put("LGA", "");
			
			obj.put("HYB", "");
			obj.put("XFB", "");
			obj.put("YDB", "");
			obj.put("LGB", "");
			obj.put("YDC", "");
			obj.put("LGC", "");
			obj.put("JZCYQCKHY", "");
			obj.put("YDD", "");
			obj.put("LGD", "");
			
			//1#除氧器出口含氧
			obj.put("CYQCKHY1", "");
			//2#特生水
			obj.put("YDE", "");
			obj.put("LGE", "");
			obj.put("CYQCKHY2", "");
			//--锅炉软水
			obj.put("YDF", "");
			obj.put("LGF", "");
			obj.put("XDF", "");
			obj.put("HYF", "");
			obj.put("PHZ", "");
			obj.put("WYSCLCK", "");
			oneArr.add(obj);
		}
		 obj = new JSONObject();
		//obj.put("LINEyearData", "今日");
		if(nowflag == 1){
			twoArr = new JSONArray();
			obj.put("RPD_GQLHZ_ID", nowData[0].toString());
			// --锅炉运行数据
			obj.put("DLS", CommonsUtil.remUndef(nowData[3]));
			obj.put("SZQZQL", CommonsUtil.getNotOneDataZ(nowData[4]));
			obj.put("GRGGZQL", CommonsUtil.getNotOneDataZ(nowData[5]));
			obj.put("TLTC", CommonsUtil.remUndef(nowData[6]));
			obj.put("GLZYXSJ", CommonsUtil.getNotOneDataZ(nowData[7]));
			obj.put("JHTLSJ", CommonsUtil.getNotOneDataZ(nowData[8]));
			obj.put("FJHTLSJ", CommonsUtil.getNotOneDataZ(nowData[9]));
			//蒸汽质量
			obj.put("GGDCJHGL", CommonsUtil.remUndef(nowData[10]));
			obj.put("GRDCJHGL", CommonsUtil.remUndef(nowData[11]));
			obj.put("SLCJHGL", CommonsUtil.remUndef(nowData[12]));
			obj.put("DHCJHGL", CommonsUtil.remUndef(nowData[13]));
			//能耗统计
			obj.put("SZQGLRHTRQ", CommonsUtil.getNotOneDataZ(nowData[14]));
			obj.put("GRGLRHTRQ", CommonsUtil.getNotOneDataZ(nowData[15]));
			obj.put("SZQGLRHD", CommonsUtil.getNotOneDataZ(nowData[16]));
			obj.put("GRGLRHD", CommonsUtil.getNotOneDataZ(nowData[17]));
			obj.put("SCLZRHD", CommonsUtil.getNotOneDataZ(nowData[18]));
			obj.put("HML", CommonsUtil.getNotTwoData(nowData[19]));
			//--用水情况
			obj.put("SYJ", CommonsUtil.getNotOneDataZ(nowData[20]));
			obj.put("LJS", CommonsUtil.getNotOneDataZ(nowData[21]));
			obj.put("TJHWS1", CommonsUtil.getNotOneDataZ(nowData[22]));
			obj.put("TJHWS2", CommonsUtil.getNotOneDataZ(nowData[23]));
			obj.put("RHQZGSL", CommonsUtil.getNotOneDataZ(nowData[24]));
			obj.put("CYQZGSL", CommonsUtil.getNotOneDataZ(nowData[25]));
			//1#特净化水
			obj.put("HYA", CommonsUtil.getNotOneDataZ(nowData[26]));
			obj.put("XFA", CommonsUtil.getNotOneDataZ(nowData[27]));
			obj.put("YDA", CommonsUtil.getNotOneDataZ(nowData[28]));
			obj.put("LGA", CommonsUtil.getNotOneDataZ(nowData[29]));
			
			obj.put("HYB", CommonsUtil.getNotOneDataZ(nowData[30]));
			obj.put("XFB", CommonsUtil.getNotOneDataZ(nowData[31]));
			obj.put("YDB", CommonsUtil.getNotOneDataZ(nowData[32]));
			obj.put("LGB", CommonsUtil.getNotOneDataZ(nowData[33]));
			obj.put("YDC", CommonsUtil.getNotOneDataZ(nowData[34]));
			obj.put("LGC", CommonsUtil.getNotOneDataZ(nowData[35]));
			obj.put("JZCYQCKHY", CommonsUtil.getNotOneDataZ(nowData[36]));
			obj.put("YDD", CommonsUtil.getNotOneDataZ(nowData[37]));
			obj.put("LGD", CommonsUtil.getNotOneDataZ(nowData[38]));
			
			//1#除氧器出口含氧
			obj.put("CYQCKHY1", CommonsUtil.getNotOneDataZ(nowData[39]));
			//2#特生水
			obj.put("YDE", CommonsUtil.getNotOneDataZ(nowData[40]));
			obj.put("LGE", CommonsUtil.getNotOneDataZ(nowData[41]));
			obj.put("CYQCKHY2", CommonsUtil.getNotOneDataZ(nowData[42]));
			//--锅炉软水
			obj.put("YDF", CommonsUtil.getNotOneDataZ(nowData[43]));
			obj.put("LGF", CommonsUtil.getNotOneDataZ(nowData[44]));
			obj.put("XDF", CommonsUtil.getNotOneDataZ(nowData[45]));
			obj.put("HYF", CommonsUtil.getNotOneDataZ(nowData[46]));
			obj.put("PHZ", CommonsUtil.getNotOneDataZ(nowData[47]));
			obj.put("WYSCLCK", CommonsUtil.getNotOneDataZ(nowData[48]));
			twoArr.add(obj);
		}else{
			twoArr = new JSONArray();
			obj.put("RPD_GQLHZ_ID","");
			// --锅炉运行数据
			obj.put("DLS", "");
			obj.put("SZQZQL","");
			obj.put("GRGGZQL","");
			obj.put("TLTC", "");
			obj.put("GLZYXSJ", "");
			obj.put("JHTLSJ", "");
			obj.put("FJHTLSJ", "");
			//蒸汽质量
			obj.put("GGDCJHGL", "");
			obj.put("GRDCJHGL", "");
			obj.put("SLCJHGL", "");
			obj.put("DHCJHGL", "");
			//能耗统计
			obj.put("SZQGLRHTRQ","");
			obj.put("GRGLRHTRQ", "");
			obj.put("SZQGLRHD", "");
			obj.put("GRGLRHD", "");
			obj.put("SCLZRHD", "");
			obj.put("HML", "");
			//--用水情况
			obj.put("SYJ","");
			obj.put("LJS", "");
			obj.put("TJHWS1", "");
			obj.put("TJHWS2", "");
			obj.put("RHQZGSL", "");
			obj.put("CYQZGSL", "");
			//1#特净化水
			obj.put("HYA", "");
			obj.put("XFA", "");
			obj.put("YDA", "");
			obj.put("LGA", "");
			
			obj.put("HYB", "");
			obj.put("XFB", "");
			obj.put("YDB", "");
			obj.put("LGB", "");
			obj.put("YDC", "");
			obj.put("LGC", "");
			obj.put("JZCYQCKHY", "");
			obj.put("YDD", "");
			obj.put("LGD", "");
			
			//1#除氧器出口含氧
			obj.put("CYQCKHY1", "");
			//2#特生水
			obj.put("YDE", "");
			obj.put("LGE", "");
			obj.put("CYQCKHY2", "");
			//--锅炉软水
			obj.put("YDF", "");
			obj.put("LGF", "");
			obj.put("XDF", "");
			obj.put("HYF", "");
			obj.put("PHZ", "");
			obj.put("WYSCLCK", "");
			twoArr.add(obj);
		}
		obj = new JSONObject();
		//obj.put("LINEnowData", "增减");
		if( yeardayflag ==1 && nowflag ==1 ){
			thrArr = new JSONArray();
			obj.put("RPD_GQLHZ_ID", yearData[0].toString());
			// --锅炉运行数据
			obj.put("DLS",CommonsUtil.getRegulationZero(nowData[3],yearData[3]));
			obj.put("SZQZQL", CommonsUtil.getRegulationnul(nowData[4], yearData[4]));
			obj.put("GRGGZQL", CommonsUtil.getRegulationnul(nowData[5],yearData[5]));
			obj.put("TLTC", CommonsUtil.getRegulationZero(nowData[6],yearData[6]));
			obj.put("GLZYXSJ", CommonsUtil.getRegulationnul(nowData[7], yearData[7]));
			obj.put("JHTLSJ", CommonsUtil.getRegulationnul(nowData[8], yearData[8]));
			obj.put("FJHTLSJ", CommonsUtil.getRegulationnul(nowData[9], yearData[9]));
			//蒸汽质量
			obj.put("GGDCJHGL", CommonsUtil.getRegulationZero(nowData[10],yearData[10]));
			obj.put("GRDCJHGL", CommonsUtil.getRegulationZero(nowData[11],yearData[11]));
			obj.put("SLCJHGL", CommonsUtil.getRegulationZero(nowData[12],yearData[12]));
			obj.put("DHCJHGL", CommonsUtil.getRegulationZero(nowData[13],yearData[13]));
			//能耗统计
			obj.put("SZQGLRHTRQ", CommonsUtil.getRegulationnul(nowData[14], yearData[14]));
			obj.put("GRGLRHTRQ", CommonsUtil.getRegulationnul(nowData[15], yearData[15]));
			obj.put("SZQGLRHD", CommonsUtil.getRegulationnul(nowData[16], yearData[16]));
			obj.put("GRGLRHD", CommonsUtil.getRegulationnul(nowData[17], yearData[17]));
			obj.put("SCLZRHD", CommonsUtil.getRegulationnul(nowData[18], yearData[18]));
			obj.put("HML", CommonsUtil.getRegulation2(nowData[19], yearData[19]));
			//--用水情况
			obj.put("SYJ", CommonsUtil.getRegulationnul(nowData[20], yearData[20]));
			obj.put("LJS", CommonsUtil.getRegulationnul(nowData[21], yearData[21]));
			obj.put("TJHWS1", CommonsUtil.getRegulationnul(nowData[22], yearData[22]));
			obj.put("TJHWS2", CommonsUtil.getRegulationnul(nowData[23], yearData[23]));
			obj.put("RHQZGSL", CommonsUtil.getRegulationnul(nowData[24], yearData[24]));
			obj.put("CYQZGSL", CommonsUtil.getRegulationnul(nowData[25], yearData[25]));
			//1#特净化水
			obj.put("HYA", CommonsUtil.getRegulationnul(nowData[26], yearData[26]));
			obj.put("XFA", CommonsUtil.getRegulationnul(nowData[27], yearData[27]));
			obj.put("YDA", CommonsUtil.getRegulationnul(nowData[28], yearData[28]));
			obj.put("LGA", CommonsUtil.getRegulationnul(nowData[29], yearData[29]));
			
			obj.put("HYB", CommonsUtil.getRegulationnul(nowData[30], yearData[30]));
			obj.put("XFB", CommonsUtil.getRegulationnul(nowData[31], yearData[31]));
			obj.put("YDB", CommonsUtil.getRegulationnul(nowData[32], yearData[32]));
			obj.put("LGB", CommonsUtil.getRegulationnul(nowData[33], yearData[33]));
			obj.put("YDC", CommonsUtil.getRegulationnul(nowData[34], yearData[34]));
			obj.put("LGC", CommonsUtil.getRegulationnul(nowData[35], yearData[35]));
			obj.put("JZCYQCKHY", CommonsUtil.getRegulationnul(nowData[36], yearData[36]));
			obj.put("YDD", CommonsUtil.getRegulationnul(nowData[37], yearData[37]));
			obj.put("LGD", CommonsUtil.getRegulationnul(nowData[38], yearData[38]));
			
			//1#除氧器出口含氧
			obj.put("CYQCKHY1", CommonsUtil.getRegulationnul(nowData[39], yearData[39]));
			//2#特生水
			obj.put("YDE", CommonsUtil.getRegulationnul(nowData[40], yearData[40]));
			obj.put("LGE", CommonsUtil.getRegulationnul(nowData[41], yearData[41]));
			obj.put("CYQCKHY2", CommonsUtil.getRegulationnul(nowData[42], yearData[42]));
			//--锅炉软水
			obj.put("YDF", CommonsUtil.getRegulationnul(nowData[43], yearData[43]));
			obj.put("LGF", CommonsUtil.getRegulationnul(nowData[44], yearData[44]));
			obj.put("XDF", CommonsUtil.getRegulationnul(nowData[45], yearData[45]));
			obj.put("HYF", CommonsUtil.getRegulationnul(nowData[46], yearData[46]));
			obj.put("PHZ", CommonsUtil.getRegulationnul(nowData[47], yearData[47]));
			obj.put("WYSCLCK", CommonsUtil.getRegulationnul(nowData[48], yearData[48]));
			thrArr.add(obj);
		}else{

			thrArr = new JSONArray();
			obj.put("RPD_GQLHZ_ID","");
			// --锅炉运行数据
			obj.put("DLS", "");
			obj.put("SZQZQL","");
			obj.put("GRGGZQL","");
			obj.put("TLTC", "");
			obj.put("GLZYXSJ", "");
			obj.put("JHTLSJ", "");
			obj.put("FJHTLSJ", "");
			//蒸汽质量
			obj.put("GGDCJHGL", "");
			obj.put("GRDCJHGL", "");
			obj.put("SLCJHGL", "");
			obj.put("DHCJHGL", "");
			//能耗统计
			obj.put("SZQGLRHTRQ","");
			obj.put("GRGLRHTRQ", "");
			obj.put("SZQGLRHD", "");
			obj.put("GRGLRHD", "");
			obj.put("SCLZRHD", "");
			obj.put("HML", "");
			//--用水情况
			obj.put("SYJ","");
			obj.put("LJS", "");
			obj.put("TJHWS1", "");
			obj.put("TJHWS2", "");
			obj.put("RHQZGSL", "");
			obj.put("CYQZGSL", "");
			//1#特净化水
			obj.put("HYA", "");
			obj.put("XFA", "");
			obj.put("YDA", "");
			obj.put("LGA", "");
			
			obj.put("HYB", "");
			obj.put("XFB", "");
			obj.put("YDB", "");
			obj.put("LGB", "");
			obj.put("YDC", "");
			obj.put("LGC", "");
			obj.put("JZCYQCKHY", "");
			obj.put("YDD", "");
			obj.put("LGD", "");
			
			//1#除氧器出口含氧
			obj.put("CYQCKHY1", "");
			//2#特生水
			obj.put("YDE", "");
			obj.put("LGE", "");
			obj.put("CYQCKHY2", "");
			//--锅炉软水
			obj.put("YDF", "");
			obj.put("LGF", "");
			obj.put("XDF", "");
			obj.put("HYF", "");
			obj.put("PHZ", "");
			obj.put("WYSCLCK", "");
			thrArr.add(obj);
		
		}
		obj = new JSONObject();
		if(dataSetTwo !=null && dataSetTwo.size()>0){
			for (Object[] tData : dataSetTwo) {
				
				forArr = new JSONArray();
				//液位情况
				obj.put("JZSSGYW", CommonsUtil.getNotTwoData(tData[0]));
				obj.put("JZZSGYW", CommonsUtil.getNotTwoData(tData[1]));
				obj.put("JZRSGYW", CommonsUtil.getNotTwoData(tData[2]));
				
				obj.put("TQSSSGYW1", CommonsUtil.getNotTwoData(tData[3]));
				obj.put("TQRSSGYW1", CommonsUtil.getNotTwoData(tData[4]));
				obj.put("TJHSSSGYW1", CommonsUtil.getNotTwoData(tData[5]));
				obj.put("QSSSGYW2", CommonsUtil.getNotTwoData(tData[6]));
				obj.put("QSRSGYW2", CommonsUtil.getNotTwoData(tData[7]));
				obj.put("JHSSGYW2", CommonsUtil.getNotTwoData(tData[8]));
				obj.put("JHRSGYW2", CommonsUtil.getNotTwoData(tData[9]));
				//重要机泵情况
				obj.put("JZZSBYL", CommonsUtil.getNotTwoData(tData[10]));
				obj.put("JZZYBYL", CommonsUtil.getNotTwoData(tData[11]));
				obj.put("YQZYBYL1", CommonsUtil.getNotTwoData(tData[12]));
				obj.put("EQZYBYL1", CommonsUtil.getNotTwoData(tData[13]));
				obj.put("SQZYBYL1", CommonsUtil.getNotTwoData(tData[14]));
				obj.put("YQZYBYL2", CommonsUtil.getNotTwoData(tData[15]));
				obj.put("EQZYBYL2", CommonsUtil.getNotTwoData(tData[16]));
				//水处理情况
				obj.put("JZZSSBSL", CommonsUtil.remUndef(tData[17]));
				obj.put("JZKYJYL", CommonsUtil.getNotTwoData(tData[18]));
				obj.put("ZSSBSL1", CommonsUtil.remUndef(tData[19]));
				obj.put("TKYJYL1", CommonsUtil.getNotTwoData(tData[20]));
				obj.put("ZSSBSL2", CommonsUtil.remUndef(tData[21]));
				obj.put("TKYJYL2", CommonsUtil.getNotTwoData(tData[22]));
				// 无盐水情况
				obj.put("FSTGSSS", CommonsUtil.getZeroData(tData[23]));
				obj.put("GSLJL", CommonsUtil.getZeroData(tData[24]));
				obj.put("NYSPSL", CommonsUtil.getZeroData(tData[25]));
				obj.put("RHGYW", CommonsUtil.getNotTwoData(tData[26]));
				obj.put("ZGJJY", CommonsUtil.getNotTwoData(tData[27]));
				obj.put("KYJYL", CommonsUtil.getNotTwoData(tData[28]));
				obj.put("ZYBYL", CommonsUtil.getNotTwoData(tData[29]));
				obj.put("BTJYL", CommonsUtil.getNotTwoData(tData[30]));
				//药剂情况
				obj.put("JZJY", CommonsUtil.getNotTwoData(tData[31]));
				obj.put("JZYL", CommonsUtil.getNotTwoData(tData[32]));
				obj.put("JY1", CommonsUtil.getNotTwoData(tData[33]));
				obj.put("YY1", CommonsUtil.getNotTwoData(tData[34]));
				obj.put("JY2", CommonsUtil.getNotTwoData(tData[35]));
				obj.put("YY2", CommonsUtil.getNotTwoData(tData[36]));
				//--燃煤锅炉情况
				obj.put("ZQL", CommonsUtil.getZeroData(tData[37]));
				obj.put("YSL", CommonsUtil.getZeroData(tData[38]));
				obj.put("GHYCSL", CommonsUtil.getZeroData(tData[39]));
				obj.put("GYSPFL", CommonsUtil.getZeroData(tData[40]));
				obj.put("CML", CommonsUtil.getNotTwoData(tData[41]));
				obj.put("YDL", CommonsUtil.getZeroData(tData[42]));
				obj.put("XPJYL", CommonsUtil.remUndef(tData[43]));
				obj.put("LSEQNYL", CommonsUtil.remUndef(tData[44]));
				obj.put("SHSYL", CommonsUtil.getNotTwoData(tData[45]));	
				forArr.add(obj);
			}
		}else{
			forArr = new JSONArray();
			obj.put("JZSSGYW", "");
			obj.put("JZZSGYW", "");
			obj.put("JZRSGYW", "");
			
			obj.put("TQSSSGYW1", "");
			obj.put("TQRSSGYW1", "");
			obj.put("TJHSSSGYW1", "");
			obj.put("QSSSGYW2", "");
			obj.put("QSRSGYW2", "");
			obj.put("JHSSGYW2", "");
			obj.put("JHRSGYW2", "");
			//重要机泵情况
			obj.put("JZZSBYL", "");
			obj.put("JZZYBYL", "");
			obj.put("YQZYBYL1", "");
			obj.put("EQZYBYL1", "");
			obj.put("SQZYBYL1", "");
			obj.put("YQZYBYL2", "");
			obj.put("EQZYBYL2", "");
			//水处理情况
			obj.put("JZZSSBSL","");
			obj.put("JZKYJYL", "");
			obj.put("ZSSBSL1", "");
			obj.put("TKYJYL1", "");
			obj.put("ZSSBSL2", "");
			obj.put("TKYJYL2", "");
			// 无盐水情况
			obj.put("FSTGSSS", "");
			obj.put("GSLJL", "");
			obj.put("NYSPSL", "");
			obj.put("RHGYW", "");
			obj.put("ZGJJY", "");
			obj.put("KYJYL", "");
			obj.put("ZYBYL", "");
			obj.put("BTJYL", "");
			//药剂情况
			obj.put("JZJY", "");
			obj.put("JZYL", "");
			obj.put("JY1", "");
			obj.put("YY1", "");
			obj.put("JY2", "");
			obj.put("YY2", "");
			//--燃煤锅炉情况
			obj.put("ZQL", "");
			obj.put("YSL", "");
			obj.put("GHYCSL", "");
			obj.put("GYSPFL", "");
			obj.put("CML", "");
			obj.put("YDL", "");
			obj.put("XPJYL", "");
			obj.put("LSEQNYL", "");
			obj.put("SHSYL", "");	
			forArr.add(obj);
		}
			obj = new JSONObject();
			obj.put("firstArr", oneArr);
			obj.put("secondArr", twoArr);
			obj.put("thirdArr", thrArr);
			obj.put("fourArr", forArr);
		return obj;
  }

@Override
public boolean saveData(List<PcRpdGqlhzzhT> gqList) throws Exception {
	return commonDao.addOrUpdateDatas(gqList);
}

@Override
public List<PcRpdGqlhzzhT> searchCheckData(String rPDGQLHZID, String bbsj)
		throws Exception {
	String hql =" from  PcRpdGqlhzzhT a where 1=1";
	if(rPDGQLHZID !=null &&  !"".equals(rPDGQLHZID)){
		hql +="  and a.rpdGqlhzId ='"+rPDGQLHZID+"'";
	}
	if(bbsj !=null &&  !"".equals(bbsj)){
		hql +="  and  a.bbsj = to_char('"+bbsj+"','YYYY-MM-DD')";
	}
	List<?> rList = null;
	rList = commonDao.searchClassQuery(hql);
	return (List<PcRpdGqlhzzhT>) rList;
}

@Override
public List<Object[]> searchExportDataFirst(String txtDate, String gqfirstSql) {
	String beforetime = DateBean.getBeforeDAYTime(txtDate);
	gqfirstSql+="  where 1=1 and BBSJ between TO_DATE('" + beforetime + "','YYYY-MM-DD') and TO_DATE('" + txtDate + "','YYYY-MM-DD') order by BBSJ";
	return commonDao.searchEverySql(gqfirstSql);
}

@Override
public List<Object[]> searchExportDataThree(String txtDate, String gqfourSql) {
	gqfourSql+="  where 1=1 and BBSJ = TO_DATE('" + txtDate + "','YYYY-MM-DD') order by BBSJ";
	return commonDao.searchEverySql(gqfourSql);
	}

@Override
public List<Object[]> searchExportDataZH(String punSql) {
	return commonDao.searchEverySql(punSql);
}

@Override
public List<String> dayreport() throws Exception {
	
	return commonDao.dayreport();
}
}
