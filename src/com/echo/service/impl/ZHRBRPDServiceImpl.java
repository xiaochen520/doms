package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.PublicDao;
import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU2GeneralT;
import com.echo.service.ZHRBRPDService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.StringUtil;

public class ZHRBRPDServiceImpl implements ZHRBRPDService{
	private PublicDao publicDao;
	
	public PublicDao getPublicDao() {
		return publicDao;
	}

	public void setPublicDao(PublicDao publicDao) {
		this.publicDao = publicDao;
	}
	
	@Override
	public List<Object[]> searchU2_GENERAL(String rq,String fields,String talbelFalg) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = publicDao.searchObjectList(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from PC_RPD_U2_GENERAL_T u where u.rq between TO_DATE('" + stratime.substring(0, 10) + "','YYYY-MM-DD') and TO_DATE('" + endtime.substring(0, 10) + "','YYYY-MM-DD') order by u.rq";
		if ("CRUDE_TRANSITION".equals(talbelFalg)) {
			sql = "select " + fields + " from (select " + fields + ",rownum from PC_RPD_CRUDE_TRANSITION_T u where u.rq between TO_DATE('" + stratime.substring(0, 10) + "','YYYY-MM-DD') and TO_DATE('" + endtime.substring(0, 10) + "','YYYY-MM-DD')) where ROWNUM<=3 order by gh";
		}
		List<Object[]> yyList = publicDao.searchObjectList(sql);
		return yyList;
	}

	@Override
	public JSONObject searchZHRB(String rq) throws Exception {
		//0:产液量 1:产油量 2:外输油量 3:库存油量
		String sql = "select CYEL,CYOUL,WSYL,KCY,";    
		//正相破乳剂
		//4一段加药量（kg）	
		//5一段浓度（mg/L）	
		//6二段加药量（kg）	
		//7二段浓度（mg/L）	
		//8综合浓度（mg/L）	
		
		sql +="ZXPRJYDJYL,ZXPRJYDJYND,ZXPRJEDJYL,ZXPRJEDJYND,ZXPRJZHND,";
		//反相药剂
		//9一段加药量（kg）	
		//10一段浓度（mg/L）	
		//11二段加药量（kg）	
		//12二段浓度（mg/L）	
		//13综合浓度（mg/L）
		sql +="	FXPRJYDJYL,FXPRJYDJYND,FXPRJEDJYL,FXPRJEDJYND,FXPRJZHND, ";
		//分线计量
		//14采油二站来液（m3）	
		//15采油三站来液（m3）	
		//16掺柴油量（m3）	
		sql +="	CYEZLY,CYSZLY,CCYL,";
		//重点节点参数
		//17掺柴油浓度（%）		
		//18 1#沉降罐含水（0.5m）%		
		//19 2#沉降罐含水（0.5m）%		
		//20 提升泵含水（%）		
		//21 相变掺热温度（℃）	
		sql +="	CCYND,CJG1HS,CJG2HS,TSBHS,XBCRWD,";
		
		//22来水量（m3）	
		//23反应器量(m3)	
		//24过滤器量(m3)	
		//25污水回收（m3）	
		//26外输量（m3）
		sql +="	LSL,FYQL,GLQL,WSHS,WSL,";
		
//		CJGCSHY	NUMBER(8,2)	Y			沉降罐出水含油    27
//		TCGJKHY	NUMBER(8,2)	Y			调储罐进口含油    28
//		TCGJKXF	NUMBER(8,2)	Y			调储罐进口悬浮   29
//		TCGCKHY	NUMBER(8,2)	Y			调储罐出口含油   30
//		TCGCKXF	NUMBER(8,2)	Y			调储罐出口悬浮  31
//		FYGCKHY	NUMBER(8,2)	Y			反应罐出口含油   32
//		FYGCKXF	NUMBER(8,2)	Y			反应罐出口悬浮  33
//		YJGLQCKHY	NUMBER(8,2)	Y			一级过滤器出口含油  34
//		YJGLQCKXF	NUMBER(8,2)	Y			一级过滤器出口悬浮  35
//		EJGLQCKHY	NUMBER(8,2)	Y			二级过滤器出口含油   36
//		EJGLQCKXF	NUMBER(8,2)	Y			二级过滤器出口悬浮   37
		
		sql +="	CJGCSHY,TCGJKHY,TCGJKXF,TCGCKHY,TCGCKXF,FYGCKHY,FYGCKXF,YJGLQCKHY,YJGLQCKXF,EJGLQCKHY,EJGLQCKXF,";
		//45
		sql += " SXHYRYZLYYL,SXHYRYZLYWD,SXHYRYZYL,SXHYRYYJSL,SXHYRYZLYLSWD,SXHYRYZLYHSWD,SXHYRYNJSWD,SXHYRYHRHYWD,";		

		//日期 46
		sql +=" RQ,RPD_U2_GENERAL_ID from PC_RPD_U2_GENERAL_T  where 1=1 ";
		String beforetime = DateBean.getBeforeDAYTime(rq);
		sql += " and RQ between TO_DATE('"+beforetime+"','YYYY-MM-DD') and TO_DATE('"+rq+"','YYYY-MM-DD') order by RQ ";
		JSONObject obj = new JSONObject();
		
		List<Integer> standard = CommonsUtil.getStandardValue();
		List<Object[]> dataSet = publicDao.searchObjectList(sql);
		if(dataSet != null && dataSet.size()>0){
			obj = getTableDataJson(standard, dataSet, beforetime, rq);
		}else{
			obj = getInitTableJson(standard);
		}
		

		return obj;
	}
	
	
	public JSONObject getInitTableJson(List<Integer> norms){
		
		JSONObject obj = new JSONObject();
		JSONArray fristArr = new JSONArray(); 
		JSONArray secondArr = new JSONArray();
		JSONArray thirdArr = new JSONArray();
		JSONArray fourdArr = new JSONArray();
		JSONArray fiveArr = new JSONArray();
		obj.put("LINEDATA", "昨日");   //行标识 ：昨日，今日，增减
		//产量分析
		obj.put("RPD_U2_GENERAL_ID", "");   //id
		obj.put("CYEL", "");   //产液量
		obj.put("CYOUL", "");   //产油量 
		obj.put("WSYL", "");   //外输油量
		obj.put("KCY", "");   //库存油量
		//正相破乳剂
		obj.put("ZXPRJYDJYL", "");   //一段加药量
		obj.put("ZXPRJYDJYND", "");   //一段浓度
		obj.put("ZXPRJEDJYL", "");   //二段加药量
		obj.put("ZXPRJEDJYND", "");   //二段浓度
		obj.put("ZXPRJZHND", "");   //综合浓度
		
		//反相药剂
		obj.put("FXPRJYDJYL", "");   //一段加药量
		obj.put("FXPRJYDJYND", "");   //一段浓度
		obj.put("FXPRJEDJYL", "");   //二段加药量
		obj.put("FXPRJEDJYND", "");   //二段浓度
		obj.put("FXPRJZHND", "");   //综合浓度
		fristArr.add(obj);   //昨日
		obj = new JSONObject();
		obj.put("LINEDATA", "今日");   //行标识 ：昨日，今日，增减
		//产量分析
		obj.put("RPD_U2_GENERAL_ID", "");   //id
		obj.put("CYEL", "");   //产液量
		obj.put("CYOUL", "");   //产油量 
		obj.put("WSYL", "");   //外输油量
		obj.put("KCY", "");   //库存油量
		//正相破乳剂
		obj.put("ZXPRJYDJYL", "");   //一段加药量
		obj.put("ZXPRJYDJYND", "");   //一段浓度
		obj.put("ZXPRJEDJYL", "");   //二段加药量
		obj.put("ZXPRJEDJYND", "");   //二段浓度
		obj.put("ZXPRJZHND", "");   //综合浓度
		
		//反相药剂
		obj.put("FXPRJYDJYL", "");   //一段加药量
		obj.put("FXPRJYDJYND", "");   //一段浓度
		obj.put("FXPRJEDJYL", "");   //二段加药量
		obj.put("FXPRJEDJYND", "");   //二段浓度
		obj.put("FXPRJZHND", "");   //综合浓度
		fristArr.add(obj);   //今日
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");   //行标识 ：昨日，今日，增减
		//产量分析
		obj.put("CYEL", "0");   //产液量
		obj.put("CYOUL", "0");   //产油量 
		obj.put("WSYL", "0");   //外输油量
		obj.put("KCY", "0");   //库存油量
		//正相破乳剂
		obj.put("ZXPRJYDJYL", "0");   //一段加药量
		obj.put("ZXPRJYDJYND", "");   //一段浓度
		obj.put("ZXPRJEDJYL", "0");   //二段加药量
		obj.put("ZXPRJEDJYND", "");   //二段浓度
		obj.put("ZXPRJZHND", "");   //综合浓度
		
		//反相药剂
		obj.put("FXPRJYDJYL", "0");   //一段加药量
		obj.put("FXPRJYDJYND", "0");   //一段浓度
		obj.put("FXPRJEDJYL", "0");   //二段加药量
		obj.put("FXPRJEDJYND", "0");   //二段浓度
		obj.put("FXPRJZHND", "0");   //综合浓度
		fristArr.add(obj);   //增减
		
		obj = new JSONObject();
		obj.put("LINEDATA", "昨日");   //行标识 ：昨日，今日，增减
		//分线计量
		obj.put("CYEZLY", "");   //采油二站来液（m3）
		obj.put("CYSZLY", "");   //采油三站来液（m3）
		obj.put("CCYL", "");   //掺柴油量（m3）
	
		//重点节点参数
		obj.put("CCYND", "");   //掺柴油浓度（%）
		obj.put("CJG1HS", "");   //1#沉降罐含水（0.5m）%	
		obj.put("CJG2HS", "");   //2#沉降罐含水（0.5m）%	
		obj.put("TSBHS", "");   //提升泵含水（%）	
		obj.put("XBCRWD", "");   //相变掺热温度（℃）	
		secondArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "今日");   //行标识 ：昨日，今日，增减
		//分线计量
		obj.put("CYEZLY", "");   //采油二站来液（m3）
		obj.put("CYSZLY", "");   //采油三站来液（m3）
		obj.put("CCYL", "");   //掺柴油量（m3）
	
		//重点节点参数
		obj.put("CCYND", "");   //掺柴油浓度（%）
		obj.put("CJG1HS", "");   //1#沉降罐含水（0.5m）%	
		obj.put("CJG2HS", "");   //2#沉降罐含水（0.5m）%	
		obj.put("TSBHS", "");   //提升泵含水（%）	
		obj.put("XBCRWD", "");   //相变掺热温度（℃）	
		secondArr.add(obj);
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");   //行标识 ：昨日，今日，增减
		//分线计量
		obj.put("CYEZLY", "0");   //采油二站来液（m3）
		obj.put("CYSZLY", "0");   //采油三站来液（m3）
		obj.put("CCYL", "0");   //掺柴油量（m3）
	
		//重点节点参数
		obj.put("CCYND", "");   //掺柴油浓度（%）
		obj.put("CJG1HS", "");   //1#沉降罐含水（0.5m）%	
		obj.put("CJG2HS", "");   //2#沉降罐含水（0.5m）%	
		obj.put("TSBHS", "");   //提升泵含水（%）	
		obj.put("XBCRWD", "");   //相变掺热温度（℃）	
		secondArr.add(obj);
		
		
		obj = new JSONObject();
		obj.put("LINEDATA", "昨日");   //行标识 ：昨日，今日，增减
		obj.put("LSL", "");   //来水量（m3）
		obj.put("FYQL", "");   //反应器量(m3)	
		obj.put("GLQL", "");   //过滤器量(m3)	
		obj.put("WSHS", "");   //污水回收（m3）
		obj.put("WSL", "");   //外输量（m3）

		thirdArr.add(obj);
		obj = new JSONObject();
		obj.put("LINEDATA", "今日");   //行标识 ：昨日，今日，增减
		obj.put("LSL", "");   //来水量（m3）
		obj.put("FYQL", "");   //反应器量(m3)	
		obj.put("GLQL", "");   //过滤器量(m3)	
		obj.put("WSHS", "");   //污水回收（m3）
		obj.put("WSL", "");   //外输量（m3）

		thirdArr.add(obj);
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");   //行标识 ：昨日，今日，增减
		obj.put("LSL", "0");   //来水量（m3）
		obj.put("FYQL", "0");   //反应器量(m3)	
		obj.put("GLQL", "0");   //过滤器量(m3)	
		obj.put("WSHS", "0");   //污水回收（m3）
		obj.put("WSL", "0");   //外输量（m3）
		thirdArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "指标(mg/l)");   //行标识 ：指标(mg/l),实测(mg/l)
//		for(String ag : norms){
//		CJGCSHY	NUMBER(8,2)	Y			沉降罐出水含油    27
		obj.put("CJGCSHY", norms.get(0));   //来水量（m3）
//		TCGJKHY	NUMBER(8,2)	Y			调储罐进口含油    28
		obj.put("TCGJKHY", norms.get(1));   //来水量（m3）
//		TCGJKXF	NUMBER(8,2)	Y			调储罐进口悬浮   29
		obj.put("TCGJKXF", norms.get(2));   //来水量（m3）
//		TCGCKHY	NUMBER(8,2)	Y			调储罐出口含油   30
		obj.put("TCGCKHY", norms.get(3));   //来水量（m3）
//		TCGCKXF	NUMBER(8,2)	Y			调储罐出口悬浮  31
		obj.put("TCGCKXF", norms.get(4));   //来水量（m3）
//		FYGCKHY	NUMBER(8,2)	Y			反应罐出口含油   32
		obj.put("FYGCKHY", norms.get(5));   //来水量（m3）
//		FYGCKXF	NUMBER(8,2)	Y			反应罐出口悬浮  33
		obj.put("FYGCKXF", norms.get(6));   //来水量（m3）
//		YJGLQCKHY	NUMBER(8,2)	Y			一级过滤器出口含油  34
		obj.put("YJGLQCKHY", norms.get(7));   //来水量（m3）
//		YJGLQCKXF	NUMBER(8,2)	Y			一级过滤器出口悬浮  35
		obj.put("YJGLQCKXF", norms.get(8));   //来水量（m3）
//		EJGLQCKHY	NUMBER(8,2)	Y			二级过滤器出口含油   36
		obj.put("EJGLQCKHY", norms.get(9));   //来水量（m3）
//		EJGLQCKXF	NUMBER(8,2)	Y			二级过滤器出口悬浮   37
		obj.put("EJGLQCKXF", norms.get(10));   //来水量（m3）
		fourdArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "实测(mg/l)");   //行标识 ：指标(mg/l),实测(mg/l)
//		for(String ag : norms){
//		CJGCSHY	NUMBER(8,2)	Y			沉降罐出水含油    27
		obj.put("CJGCSHY", "");   //来水量（m3）
//		TCGJKHY	NUMBER(8,2)	Y			调储罐进口含油    28
		obj.put("TCGJKHY", "");   //来水量（m3）
//		TCGJKXF	NUMBER(8,2)	Y			调储罐进口悬浮   29
		obj.put("TCGJKXF", "");   //来水量（m3）
//		TCGCKHY	NUMBER(8,2)	Y			调储罐出口含油   30
		obj.put("TCGCKHY", "");   //来水量（m3）
//		TCGCKXF	NUMBER(8,2)	Y			调储罐出口悬浮  31
		obj.put("TCGCKXF", "");   //来水量（m3）
//		FYGCKHY	NUMBER(8,2)	Y			反应罐出口含油   32
		obj.put("FYGCKHY", "");   //来水量（m3）
//		FYGCKXF	NUMBER(8,2)	Y			反应罐出口悬浮  33
		obj.put("FYGCKXF", "");   //来水量（m3）
//		YJGLQCKHY	NUMBER(8,2)	Y			一级过滤器出口含油  34
		obj.put("YJGLQCKHY", "");   //来水量（m3）
//		YJGLQCKXF	NUMBER(8,2)	Y			一级过滤器出口悬浮  35
		obj.put("YJGLQCKXF", "");   //来水量（m3）
//		EJGLQCKHY	NUMBER(8,2)	Y			二级过滤器出口含油   36
		obj.put("EJGLQCKHY", "");   //来水量（m3）
//		EJGLQCKXF	NUMBER(8,2)	Y			二级过滤器出口悬浮   37
		obj.put("EJGLQCKXF", "");   //来水量（m3）
		fourdArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "昨日");
		obj.put("SXHYRYZLYYL", "");
		obj.put("SXHYRYZLYWD", "");
		obj.put("SXHYRYZYL", "");
		obj.put("SXHYRYYJSL", "");
		obj.put("SXHYRYZLYLSWD", "");
		obj.put("SXHYRYZLYHSWD", "");
		obj.put("SXHYRYNJSWD", "");
		obj.put("SXHYRYHRHYWD", "");
		fiveArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "今日");
		obj.put("SXHYRYZLYYL", "");
		obj.put("SXHYRYZLYWD", "");
		obj.put("SXHYRYZYL", "");
		obj.put("SXHYRYYJSL", "");
		obj.put("SXHYRYZLYLSWD", "");
		obj.put("SXHYRYZLYHSWD", "");
		obj.put("SXHYRYNJSWD", "");
		obj.put("SXHYRYHRHYWD", "");
		fiveArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");
		obj.put("SXHYRYZLYYL", "0");
		obj.put("SXHYRYZLYWD", "0");
		obj.put("SXHYRYZYL", "0");
		obj.put("SXHYRYYJSL", "0");
		obj.put("SXHYRYZLYLSWD", "0");
		obj.put("SXHYRYZLYHSWD", "0");
		obj.put("SXHYRYNJSWD", "0");
		obj.put("SXHYRYHRHYWD", "0");
		fiveArr.add(obj);
		
		obj = new JSONObject();
		
		obj.put("fristArr", fristArr);
		obj.put("secondArr", secondArr);
		obj.put("thirdArr", thirdArr);
		obj.put("fourdArr", fourdArr);
		obj.put("fiveArr", fiveArr);
		
		return obj;
		
	}
	
public JSONObject getTableDataJson(List<Integer> standard,List<Object[]> dataSet,String beforetime,String nowtime){
	
		Object zero = 0.00;
		String ZXPRJYDJYNDSUM ="";  //昨日一段浓度（mg/L）
		String ZXPRJEDJYNDSUM ="";  //昨日二段浓度（mg/L）
		
		String ZXPRJYDJYNDSUMT ="";  //今日一段浓度（mg/L）
		String ZXPRJEDJYNDSUMT ="";  //今日二段浓度（mg/L）
		
		String ZXPRJZHNDT = "";   //昨日综合浓度（mg/L）
		String ZXPRJZHNDY = "";  //今日综合浓度（mg/L）
		
		
		String CCYNDy ="";      //昨日掺柴油浓度（%）
		String CCYNDt ="";      //今日掺柴油浓度（%）
		
	
		int yeardayflag = 0; // 昨日数据存在   0：不存在，1存在
		int nowflag = 0; // 今日数据存在   0：不存在，1存在
		Object[] yearData = null;
		Object[]  nowData = null;
		
		for(Object[] data:dataSet){
			if(data[46] != null && beforetime.equals(String.valueOf(data[46]))){
				yeardayflag = 1;
				yearData = data;
			}
			
			if(data[46] != null && nowtime.equals(String.valueOf(data[46]))){
				nowflag = 1; 
				nowData = data;
			}
		}
		
		
		JSONObject obj = new JSONObject();
		JSONArray fristArr = new JSONArray(); 
		JSONArray secondArr = new JSONArray();
		JSONArray thirdArr = new JSONArray();
		JSONArray fourdArr = new JSONArray();
		JSONArray fiveArr = new JSONArray();
		obj.put("LINEDATA", "昨日");   //行标识 ：昨日，今日，增减
		if(yeardayflag == 1){
			//产量分析
			obj.put("RPD_U2_GENERAL_ID", StringUtil.isNullStr(String.valueOf(yearData[47])));   //id
			obj.put("CYEL", CommonsUtil.getIntData(yearData[0]));   //产液量
			obj.put("CYOUL", CommonsUtil.getIntData(yearData[1]));   //产油量 
			obj.put("WSYL", CommonsUtil.getIntData(yearData[2]));   //外输油量
			obj.put("KCY", CommonsUtil.getIntData(yearData[3]));   //库存油量
			//正相破乳剂
			obj.put("ZXPRJYDJYL", CommonsUtil.getIntData(yearData[4]));   //一段加药量
			ZXPRJYDJYNDSUM = CommonsUtil.getRegulation1(yearData[4], yearData[0]);
			obj.put("ZXPRJYDJYND", ZXPRJYDJYNDSUM);   //一段浓度
			obj.put("ZXPRJEDJYL", CommonsUtil.getIntData(yearData[6]));   //二段加药量
			ZXPRJEDJYNDSUM = CommonsUtil.getRegulation1(yearData[6], yearData[1]);
			obj.put("ZXPRJEDJYND", ZXPRJEDJYNDSUM);   //二段浓度
			ZXPRJZHNDT = CommonsUtil.getRegulation2(yearData[6], yearData[4], yearData[0]);
			obj.put("ZXPRJZHND", ZXPRJZHNDT);  //综合浓度
			
			//反相药剂
			obj.put("FXPRJYDJYL", CommonsUtil.getIntData(yearData[9]));   //一段加药量
			obj.put("FXPRJYDJYND", CommonsUtil.getIntData(yearData[10]));   //一段浓度
			obj.put("FXPRJEDJYL", CommonsUtil.getIntData(yearData[11]));   //二段加药量
			obj.put("FXPRJEDJYND", CommonsUtil.getIntData(yearData[12]));   //二段浓度
			obj.put("FXPRJZHND", CommonsUtil.getIntData(yearData[13]));   //综合浓度
		}else{
			//产量分析
			obj.put("RPD_U2_GENERAL_ID", "");   //id
			obj.put("CYEL", "");   //产液量
			obj.put("CYOUL", "");   //产油量 
			obj.put("WSYL", "");   //外输油量
			obj.put("KCY", "");   //库存油量
			//正相破乳剂
			obj.put("ZXPRJYDJYL", "");   //一段加药量
			obj.put("ZXPRJYDJYND", "");   //一段浓度
			obj.put("ZXPRJEDJYL", "");   //二段加药量
			obj.put("ZXPRJEDJYND", "");   //二段浓度
			obj.put("ZXPRJZHND", "");   //综合浓度
			
			//反相药剂
			obj.put("FXPRJYDJYL", "");   //一段加药量
			obj.put("FXPRJYDJYND", "");   //一段浓度
			obj.put("FXPRJEDJYL", "");   //二段加药量
			obj.put("FXPRJEDJYND", "");   //二段浓度
			obj.put("FXPRJZHND", "");   //综合浓度
		}
		
		fristArr.add(obj);   //昨日
		obj = new JSONObject();
		obj.put("LINEDATA", "今日");   //行标识 ：昨日，今日，增减
		if(nowflag == 1){
			//产量分析
			obj.put("RPD_U2_GENERAL_ID", StringUtil.isNullStr(String.valueOf(nowData[47])));   //id
			obj.put("CYEL", CommonsUtil.getIntData(nowData[0]));   //产液量
			obj.put("CYOUL", CommonsUtil.getIntData(nowData[1]));   //产油量 
			obj.put("WSYL", CommonsUtil.getIntData(nowData[2]));   //外输油量
			obj.put("KCY", CommonsUtil.getIntData(nowData[3]));   //库存油量
			//正相破乳剂
			obj.put("ZXPRJYDJYL", CommonsUtil.getIntData(nowData[4]));   //一段加药量
			ZXPRJYDJYNDSUMT = CommonsUtil.getRegulation1(nowData[4], nowData[0]);
			obj.put("ZXPRJYDJYND", ZXPRJYDJYNDSUMT);   //一段浓度
			obj.put("ZXPRJEDJYL", CommonsUtil.getIntData(nowData[6]));   //二段加药量
			ZXPRJEDJYNDSUMT = CommonsUtil.getRegulation1(nowData[6], nowData[1]);
			obj.put("ZXPRJEDJYND", ZXPRJEDJYNDSUMT);   //二段浓度
			ZXPRJZHNDY = CommonsUtil.getRegulation2(nowData[6], nowData[4], nowData[0]);
			obj.put("ZXPRJZHND", ZXPRJZHNDY);  //综合浓度
			
			//反相药剂
			obj.put("FXPRJYDJYL", CommonsUtil.getIntData(nowData[9]));   //一段加药量
			obj.put("FXPRJYDJYND", CommonsUtil.getIntData(nowData[10]));   //一段浓度
			obj.put("FXPRJEDJYL", CommonsUtil.getIntData(nowData[11]));   //二段加药量
			obj.put("FXPRJEDJYND", CommonsUtil.getIntData(nowData[12]));   //二段浓度
			obj.put("FXPRJZHND", CommonsUtil.getIntData(nowData[13]));   //综合浓度
		}else{
			//产量分析
			obj.put("RPD_U2_GENERAL_ID", "");   //id
			obj.put("CYEL", "");   //产液量
			obj.put("CYOUL", "");   //产油量 
			obj.put("WSYL", "");   //外输油量
			obj.put("KCY", "");   //库存油量
			//正相破乳剂
			obj.put("ZXPRJYDJYL", "");   //一段加药量
			obj.put("ZXPRJYDJYND", "");   //一段浓度
			obj.put("ZXPRJEDJYL", "");   //二段加药量
			obj.put("ZXPRJEDJYND", "");   //二段浓度
			obj.put("ZXPRJZHND", "");   //综合浓度
			
			//反相药剂
			obj.put("FXPRJYDJYL", "");   //一段加药量
			obj.put("FXPRJYDJYND", "");   //一段浓度
			obj.put("FXPRJEDJYL", "");   //二段加药量
			obj.put("FXPRJEDJYND", "");   //二段浓度
			obj.put("FXPRJZHND", "");   //综合浓度
		}
		fristArr.add(obj);   //今日
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");   //行标识 ：昨日，今日，增减
		if(nowflag == 1 && yeardayflag == 1){
			//产量分析
			obj.put("CYEL", CommonsUtil.getRegulation0(nowData[0], yearData[0]));   //产液量
			obj.put("CYOUL", CommonsUtil.getRegulation0(nowData[1], yearData[1]));   //产油量 
			obj.put("WSYL", CommonsUtil.getRegulation0(nowData[2], yearData[2]));   //外输油量
			obj.put("KCY", CommonsUtil.getRegulation0(nowData[3], yearData[3]));   //库存油量
			//正相破乳剂
			obj.put("ZXPRJYDJYL", CommonsUtil.getRegulation0(nowData[4], yearData[4]));   //一段加药量
//			obj.put("ZXPRJYDJYND", CommonsUtil.getRegulation(nowData[5], yearData[5]));   //一段浓度
			obj.put("ZXPRJEDJYL", CommonsUtil.getRegulation0(nowData[6], yearData[6]));   //二段加药量
//			obj.put("ZXPRJEDJYND", CommonsUtil.getRegulation(nowData[7], yearData[7]));   //二段浓度
//			obj.put("ZXPRJZHND", CommonsUtil.getRegulation(nowData[8], yearData[8]));  //综合浓度
			
			//反相药剂
			obj.put("FXPRJYDJYL", CommonsUtil.getRegulation0(nowData[9], yearData[9]));   //一段加药量
			obj.put("FXPRJYDJYND", CommonsUtil.getRegulation(nowData[10], yearData[10]));   //一段浓度
			obj.put("FXPRJEDJYL", CommonsUtil.getRegulation0(nowData[11], yearData[11]));   //二段加药量
			obj.put("FXPRJEDJYND", CommonsUtil.getRegulation(nowData[12], yearData[12]));   //二段浓度
			obj.put("FXPRJZHND", CommonsUtil.getRegulation(nowData[13], yearData[13]));   //综合浓度
		}else{
			
			if(nowflag == 1 && yeardayflag == 0){
				//产量分析
				obj.put("CYEL", CommonsUtil.getRegulation0(nowData[0], zero));   //产液量
				obj.put("CYOUL", CommonsUtil.getRegulation0(nowData[1], zero));   //产油量 
				obj.put("WSYL", CommonsUtil.getRegulation0(nowData[2], zero));   //外输油量
				obj.put("KCY", CommonsUtil.getRegulation0(nowData[3], zero));   //库存油量
				//正相破乳剂
				obj.put("ZXPRJYDJYL", CommonsUtil.getRegulation0(nowData[4], zero));   //一段加药量
//				obj.put("ZXPRJYDJYND", CommonsUtil.getRegulation(nowData[5], zero));   //一段浓度
				obj.put("ZXPRJEDJYL", CommonsUtil.getRegulation0(nowData[6], zero));   //二段加药量
//				obj.put("ZXPRJEDJYND", CommonsUtil.getRegulation(nowData[7], zero));   //二段浓度
//				obj.put("ZXPRJZHND", CommonsUtil.getRegulation(nowData[8], zero));  //综合浓度
				
				//反相药剂
				obj.put("FXPRJYDJYL", CommonsUtil.getRegulation0(nowData[9], zero));   //一段加药量
				obj.put("FXPRJYDJYND", CommonsUtil.getRegulation(nowData[10], zero));   //一段浓度
				obj.put("FXPRJEDJYL", CommonsUtil.getRegulation0(nowData[11], zero));   //二段加药量
				obj.put("FXPRJEDJYND", CommonsUtil.getRegulation(nowData[12], zero));   //二段浓度
				obj.put("FXPRJZHND", CommonsUtil.getRegulation(nowData[13], zero));   //综合浓度
				
			}else if(nowflag == 0 && yeardayflag == 1){
				//产量分析
				obj.put("CYEL", CommonsUtil.getRegulation0(zero, yearData[0]));   //产液量
				obj.put("CYOUL", CommonsUtil.getRegulation0(zero, yearData[1]));   //产油量 
				obj.put("WSYL", CommonsUtil.getRegulation0(zero, yearData[2]));   //外输油量
				obj.put("KCY", CommonsUtil.getRegulation0(zero, yearData[3]));   //库存油量
				//正相破乳剂
				obj.put("ZXPRJYDJYL", CommonsUtil.getRegulation0(zero, yearData[4]));   //一段加药量
//				obj.put("ZXPRJYDJYND", CommonsUtil.getRegulation(zero, yearData[5]));   //一段浓度
				obj.put("ZXPRJEDJYL", CommonsUtil.getRegulation0(zero, yearData[6]));   //二段加药量
//				obj.put("ZXPRJEDJYND", CommonsUtil.getRegulation(zero, yearData[7]));   //二段浓度
//				obj.put("ZXPRJZHND", CommonsUtil.getRegulation(zero, yearData[8]));  //综合浓度
				
				//反相药剂
				obj.put("FXPRJYDJYL", CommonsUtil.getRegulation0(zero, yearData[9]));   //一段加药量
				obj.put("FXPRJYDJYND", CommonsUtil.getRegulation(zero, yearData[10]));   //一段浓度
				obj.put("FXPRJEDJYL", CommonsUtil.getRegulation0(zero, yearData[11]));   //二段加药量
				obj.put("FXPRJEDJYND", CommonsUtil.getRegulation(zero, yearData[12]));   //二段浓度
				obj.put("FXPRJZHND", CommonsUtil.getRegulation(zero, yearData[13]));   //综合浓度
			}else{
				//产量分析
				obj.put("CYEL", "");   //产液量
				obj.put("CYOUL", "");   //产油量 
				obj.put("WSYL", "");   //外输油量
				obj.put("KCY", "");   //库存油量
				//正相破乳剂
				obj.put("ZXPRJYDJYL", "");   //一段加药量
//				obj.put("ZXPRJYDJYND", "");   //一段浓度
				obj.put("ZXPRJEDJYL", "");   //二段加药量
//				obj.put("ZXPRJEDJYND", "");   //二段浓度
//				obj.put("ZXPRJZHND", "");   //综合浓度
				
				//反相药剂
				obj.put("FXPRJYDJYL", "");   //一段加药量
				obj.put("FXPRJYDJYND", "");   //一段浓度
				obj.put("FXPRJEDJYL", "");   //二段加药量
				obj.put("FXPRJEDJYND", "");   //二段浓度
				obj.put("FXPRJZHND", "");   //综合浓度
			}
			
		}
		obj.put("ZXPRJYDJYND", CommonsUtil.getRegulation(ZXPRJYDJYNDSUMT, ZXPRJYDJYNDSUM));   //一段浓度
		obj.put("ZXPRJEDJYND", CommonsUtil.getRegulation(ZXPRJEDJYNDSUMT, ZXPRJEDJYNDSUM));   //二段浓度
		obj.put("ZXPRJZHND", CommonsUtil.getRegulation(ZXPRJZHNDY, ZXPRJZHNDT));   //综合浓度
		
		fristArr.add(obj);   //增减
		
		obj = new JSONObject();
		obj.put("LINEDATA", "昨日");   //行标识 ：昨日，今日，增减
		if(yeardayflag == 1){
			//分线计量
			obj.put("CYEZLY", CommonsUtil.getIntData(yearData[14]));   //采油二站来液（m3）
			obj.put("CYSZLY", CommonsUtil.getIntData(yearData[15]));   //采油三站来液（m3）
			
			obj.put("CCYL", CommonsUtil.getIntData(yearData[16]));   //掺柴油量（m3）
			CCYNDy = CommonsUtil.getCCYND(yearData[16], yearData[1]);
			//重点节点参数
			obj.put("CCYND", CCYNDy);   //掺柴油浓度（%）
			obj.put("CJG1HS", CommonsUtil.getNotOneData(yearData[18]));   //1#沉降罐含水（0.5m）%	
			obj.put("CJG2HS", CommonsUtil.getNotOneData(yearData[19]));   //2#沉降罐含水（0.5m）%	
			obj.put("TSBHS", CommonsUtil.getNotOneData(yearData[20]));   //提升泵含水（%）	
			obj.put("XBCRWD", CommonsUtil.getNotOneData(yearData[21]));   //相变掺热温度（℃）	
		}else{
			//分线计量
			obj.put("CYEZLY", "");   //采油二站来液（m3）
			obj.put("CYSZLY", "");   //采油三站来液（m3）
			obj.put("CCYL", "");   //掺柴油量（m3）
		
			//重点节点参数
			obj.put("CCYND", "");   //掺柴油浓度（%）
			obj.put("CJG1HS", "");   //1#沉降罐含水（0.5m）%	
			obj.put("CJG2HS", "");   //2#沉降罐含水（0.5m）%	
			obj.put("TSBHS", "");   //提升泵含水（%）	
			obj.put("XBCRWD", "");   //相变掺热温度（℃）	
		}
		
		secondArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "今日");   //行标识 ：昨日，今日，增减
		if(nowflag == 1){
			//分线计量
			obj.put("CYEZLY", CommonsUtil.getIntData(nowData[14]));   //采油二站来液（m3）
			obj.put("CYSZLY", CommonsUtil.getIntData(nowData[15]));   //采油三站来液（m3）
			obj.put("CCYL", CommonsUtil.getIntData(nowData[16]));   //掺柴油量（m3）
			CCYNDt = CommonsUtil.getCCYND(nowData[16], nowData[1]);
			//重点节点参数
			obj.put("CCYND", CCYNDt);   //掺柴油浓度（%）
			obj.put("CJG1HS", CommonsUtil.getNotOneData(nowData[18]));   //1#沉降罐含水（0.5m）%	
			obj.put("CJG2HS", CommonsUtil.getNotOneData(nowData[19]));   //2#沉降罐含水（0.5m）%	
			obj.put("TSBHS", CommonsUtil.getNotOneData(nowData[20]));   //提升泵含水（%）	
			obj.put("XBCRWD", CommonsUtil.getNotOneData(nowData[21]));   //相变掺热温度（℃）	
		}else{
			//分线计量
			obj.put("CYEZLY", "");   //采油二站来液（m3）
			obj.put("CYSZLY", "");   //采油三站来液（m3）
			obj.put("CCYL", "");   //掺柴油量（m3）
		
			//重点节点参数
			obj.put("CCYND", "");   //掺柴油浓度（%）
			obj.put("CJG1HS", "");   //1#沉降罐含水（0.5m）%	
			obj.put("CJG2HS", "");   //2#沉降罐含水（0.5m）%	
			obj.put("TSBHS", "");   //提升泵含水（%）	
			obj.put("XBCRWD", "");   //相变掺热温度（℃）	
		}
		
		secondArr.add(obj);
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");   //行标识 ：昨日，今日，增减
		if(nowflag == 1 && yeardayflag ==1){
			//分线计量
			obj.put("CYEZLY", CommonsUtil.getRegulation0(nowData[14], yearData[14]));   //采油二站来液（m3）
			obj.put("CYSZLY", CommonsUtil.getRegulation0(nowData[15], yearData[15]));   //采油三站来液（m3）
			obj.put("CCYL", CommonsUtil.getRegulation0(nowData[16], yearData[16]));   //掺柴油量（m3）
		
			//重点节点参数
//			obj.put("CCYND", CommonsUtil.getRegulation(nowData[17], yearData[17]));   //掺柴油浓度（%）
//			obj.put("CJG1HS", CommonsUtil.getRegulation(nowData[18], yearData[18]));   //1#沉降罐含水（0.5m）%	
//			obj.put("CJG2HS", CommonsUtil.getRegulation(nowData[19], yearData[19]));   //2#沉降罐含水（0.5m）%	
//			obj.put("TSBHS", CommonsUtil.getRegulation(nowData[20], yearData[20]));   //提升泵含水（%）	
//			obj.put("XBCRWD", CommonsUtil.getRegulation(nowData[21], yearData[21]));   //相变掺热温度（℃）	
		}else{
			if(nowflag == 1 && yeardayflag ==0){
				//分线计量
				obj.put("CYEZLY", CommonsUtil.getRegulation0(nowData[14], zero));   //采油二站来液（m3）
				obj.put("CYSZLY", CommonsUtil.getRegulation0(nowData[15], zero));   //采油三站来液（m3）
				obj.put("CCYL", CommonsUtil.getRegulation0(nowData[16], zero));   //掺柴油量（m3）
			
				//重点节点参数
//				obj.put("CCYND", CommonsUtil.getRegulation(nowData[17], zero));   //掺柴油浓度（%）
//				obj.put("CJG1HS", CommonsUtil.getRegulation(nowData[18], zero));   //1#沉降罐含水（0.5m）%	
//				obj.put("CJG2HS", CommonsUtil.getRegulation(nowData[19], zero));   //2#沉降罐含水（0.5m）%	
//				obj.put("TSBHS", CommonsUtil.getRegulation(nowData[20], zero));   //提升泵含水（%）	
//				obj.put("XBCRWD", CommonsUtil.getRegulation(nowData[21], zero));   //相变掺热温度（℃）	
			}else if(nowflag == 0 && yeardayflag ==1){
				//分线计量
				obj.put("CYEZLY", CommonsUtil.getRegulation0(zero, yearData[14]));   //采油二站来液（m3）
				obj.put("CYSZLY", CommonsUtil.getRegulation0(zero, yearData[15]));   //采油三站来液（m3）
				obj.put("CCYL", CommonsUtil.getRegulation0(zero, yearData[16]));   //掺柴油量（m3）
			
				//重点节点参数
//				obj.put("CCYND", CommonsUtil.getRegulation(zero, yearData[17]));   //掺柴油浓度（%）
//				obj.put("CJG1HS", CommonsUtil.getRegulation(zero, yearData[18]));   //1#沉降罐含水（0.5m）%	
//				obj.put("CJG2HS", CommonsUtil.getRegulation(zero, yearData[19]));   //2#沉降罐含水（0.5m）%	
//				obj.put("TSBHS", CommonsUtil.getRegulation(zero, yearData[20]));   //提升泵含水（%）	
//				obj.put("XBCRWD", CommonsUtil.getRegulation(zero, yearData[21]));   //相变掺热温度（℃）
			}else{
				//分线计量
				obj.put("CYEZLY", "0");   //采油二站来液（m3）
				obj.put("CYSZLY", "0");   //采油三站来液（m3）
				obj.put("CCYL", "0");   //掺柴油量（m3）
			
				//重点节点参数
//				obj.put("CCYND", "");   //掺柴油浓度（%）
//				obj.put("CJG1HS", "");   //1#沉降罐含水（0.5m）%	
//				obj.put("CJG2HS", "");   //2#沉降罐含水（0.5m）%	
//				obj.put("TSBHS", "");   //提升泵含水（%）	
//				obj.put("XBCRWD", "");   //相变掺热温度（℃）	
			}
		}
		obj.put("CCYND", CommonsUtil.getRegulationStr(CCYNDt, CCYNDy));   //掺柴油浓度（%）
		obj.put("CJG1HS", "");   //1#沉降罐含水（0.5m）%	
		obj.put("CJG2HS", "");   //2#沉降罐含水（0.5m）%	
		obj.put("TSBHS", "");   //提升泵含水（%）	
		obj.put("XBCRWD", "");   //相变掺热温度（℃）	
		secondArr.add(obj);
		
		
		obj = new JSONObject();
		obj.put("LINEDATA", "昨日");   //行标识 ：昨日，今日，增减
		if(yeardayflag == 1){
			obj.put("LSL", CommonsUtil.getIntData(yearData[22]));   //来水量（m3）
			obj.put("FYQL", CommonsUtil.getIntData(yearData[23]));   //反应器量(m3)	
			obj.put("GLQL", CommonsUtil.getIntData(yearData[24]));   //过滤器量(m3)	
			obj.put("WSHS", CommonsUtil.getIntData(yearData[25]));   //污水回收（m3）
			obj.put("WSL", CommonsUtil.getIntData(yearData[26]));   //外输量（m3）
		}else{
			obj.put("LSL", "");   //来水量（m3）
			obj.put("FYQL", "");   //反应器量(m3)	
			obj.put("GLQL", "");   //过滤器量(m3)	
			obj.put("WSHS", "");   //污水回收（m3）
			obj.put("WSL", "");   //外输量（m3）
		}
		

		thirdArr.add(obj);
		obj = new JSONObject();
		obj.put("LINEDATA", "今日");   //行标识 ：昨日，今日，增减
		if(nowflag == 1){
			obj.put("LSL", CommonsUtil.getIntData(nowData[22]));   //来水量（m3）
			obj.put("FYQL", CommonsUtil.getIntData(nowData[23]));   //反应器量(m3)	
			obj.put("GLQL", CommonsUtil.getIntData(nowData[24]));   //过滤器量(m3)	
			obj.put("WSHS", CommonsUtil.getIntData(nowData[25]));   //污水回收（m3）
			obj.put("WSL", CommonsUtil.getIntData(nowData[26]));   //外输量（m3）
		}else{
			obj.put("LSL", "");   //来水量（m3）
			obj.put("FYQL", "");   //反应器量(m3)	
			obj.put("GLQL", "");   //过滤器量(m3)	
			obj.put("WSHS", "");   //污水回收（m3）
			obj.put("WSL", "");   //外输量（m3）
		}
		

		thirdArr.add(obj);
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");   //行标识 ：昨日，今日，增减
		if(nowflag == 1 && yeardayflag == 1){
			obj.put("LSL", CommonsUtil.getRegulation0(nowData[22], yearData[22]));   //来水量（m3）
			obj.put("FYQL", CommonsUtil.getRegulation0(nowData[23], yearData[23]));   //反应器量(m3)	
			obj.put("GLQL", CommonsUtil.getRegulation0(nowData[24], yearData[24]));   //过滤器量(m3)	
			obj.put("WSHS", CommonsUtil.getRegulation0(nowData[25], yearData[25]));   //污水回收（m3）
			obj.put("WSL", CommonsUtil.getRegulation0(nowData[26], yearData[26]));   //外输量（m3）
		}else{
			if(nowflag == 1 && yeardayflag == 0){
				obj.put("LSL", CommonsUtil.getRegulation0(nowData[22], zero));   //来水量（m3）
				obj.put("FYQL", CommonsUtil.getRegulation0(nowData[23], zero));   //反应器量(m3)	
				obj.put("GLQL", CommonsUtil.getRegulation0(nowData[24], zero));   //过滤器量(m3)	
				obj.put("WSHS", CommonsUtil.getRegulation0(nowData[25], zero));   //污水回收（m3）
				obj.put("WSL", CommonsUtil.getRegulation0(nowData[26], zero));   //外输量（m3）
			}else if(nowflag == 0 && yeardayflag == 1){
				obj.put("LSL", CommonsUtil.getRegulation0(zero, yearData[22]));   //来水量（m3）
				obj.put("FYQL", CommonsUtil.getRegulation0(zero, yearData[23]));   //反应器量(m3)	
				obj.put("GLQL", CommonsUtil.getRegulation0(zero, yearData[24]));   //过滤器量(m3)	
				obj.put("WSHS", CommonsUtil.getRegulation0(zero, yearData[25]));   //污水回收（m3）
				obj.put("WSL", CommonsUtil.getRegulation0(zero, yearData[26]));   //外输量（m3）
			}else{
				obj.put("LSL", "0");   //来水量（m3）
				obj.put("FYQL", "0");   //反应器量(m3)	
				obj.put("GLQL", "0");   //过滤器量(m3)	
				obj.put("WSHS", "0");   //污水回收（m3）
				obj.put("WSL", "0");   //外输量（m3）
			}
		}
		
		thirdArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "指标(mg/l)");   //行标识 ：指标(mg/l),实测(mg/l)
//		for(String ag : norms){
//		CJGCSHY	NUMBER(8,2)	Y			沉降罐出水含油    27
		obj.put("CJGCSHY", standard.get(0));   //来水量（m3）
//		TCGJKHY	NUMBER(8,2)	Y			调储罐进口含油    28
		obj.put("TCGJKHY", standard.get(1));   //来水量（m3）
//		TCGJKXF	NUMBER(8,2)	Y			调储罐进口悬浮   29
		obj.put("TCGJKXF", standard.get(2));   //来水量（m3）
//		TCGCKHY	NUMBER(8,2)	Y			调储罐出口含油   30
		obj.put("TCGCKHY", standard.get(3));   //来水量（m3）
//		TCGCKXF	NUMBER(8,2)	Y			调储罐出口悬浮  31
		obj.put("TCGCKXF", standard.get(4));   //来水量（m3）
//		FYGCKHY	NUMBER(8,2)	Y			反应罐出口含油   32
		obj.put("FYGCKHY", standard.get(5));   //来水量（m3）
//		FYGCKXF	NUMBER(8,2)	Y			反应罐出口悬浮  33
		obj.put("FYGCKXF", standard.get(6));   //来水量（m3）
//		YJGLQCKHY	NUMBER(8,2)	Y			一级过滤器出口含油  34
		obj.put("YJGLQCKHY", standard.get(7));   //来水量（m3）
//		YJGLQCKXF	NUMBER(8,2)	Y			一级过滤器出口悬浮  35
		obj.put("YJGLQCKXF", standard.get(8));   //来水量（m3）
//		EJGLQCKHY	NUMBER(8,2)	Y			二级过滤器出口含油   36
		obj.put("EJGLQCKHY", standard.get(9));   //来水量（m3）
//		EJGLQCKXF	NUMBER(8,2)	Y			二级过滤器出口悬浮   37
		obj.put("EJGLQCKXF", standard.get(10));   //来水量（m3）
		fourdArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "实测(mg/l)");   //行标识 ：指标(mg/l),实测(mg/l)
		if(nowflag == 1){
//			for(String ag : norms){
//			CJGCSHY	NUMBER(8,2)	Y			沉降罐出水含油    27
			obj.put("CJGCSHY", CommonsUtil.getNotOneData(nowData[27]));   //来水量（m3）
//			TCGJKHY	NUMBER(8,2)	Y			调储罐进口含油    28
			obj.put("TCGJKHY", CommonsUtil.getNotOneData(nowData[28]));   //来水量（m3）
//			TCGJKXF	NUMBER(8,2)	Y			调储罐进口悬浮   29
			obj.put("TCGJKXF", CommonsUtil.getNotOneData(nowData[29]));   //来水量（m3）
//			TCGCKHY	NUMBER(8,2)	Y			调储罐出口含油   30
			obj.put("TCGCKHY", CommonsUtil.getNotOneData(nowData[30]));   //来水量（m3）
//			TCGCKXF	NUMBER(8,2)	Y			调储罐出口悬浮  31
			obj.put("TCGCKXF", CommonsUtil.getNotOneData(nowData[31]));   //来水量（m3）
//			FYGCKHY	NUMBER(8,2)	Y			反应罐出口含油   32
			obj.put("FYGCKHY", CommonsUtil.getNotOneData(nowData[32]));   //来水量（m3）
//			FYGCKXF	NUMBER(8,2)	Y			反应罐出口悬浮  33
			obj.put("FYGCKXF", CommonsUtil.getNotOneData(nowData[33]));   //来水量（m3）
//			YJGLQCKHY	NUMBER(8,2)	Y			一级过滤器出口含油  34
			obj.put("YJGLQCKHY", CommonsUtil.getNotOneData(nowData[34]));   //来水量（m3）
//			YJGLQCKXF	NUMBER(8,2)	Y			一级过滤器出口悬浮  35
			obj.put("YJGLQCKXF", CommonsUtil.getNotOneData(nowData[35]));   //来水量（m3）
//			EJGLQCKHY	NUMBER(8,2)	Y			二级过滤器出口含油   36
			obj.put("EJGLQCKHY", CommonsUtil.getNotOneData(nowData[36]));   //来水量（m3）
//			EJGLQCKXF	NUMBER(8,2)	Y			二级过滤器出口悬浮   37
			obj.put("EJGLQCKXF", CommonsUtil.getNotOneData(nowData[37]));   //来水量（m3）
		}else{
//			for(String ag : norms){
//			CJGCSHY	NUMBER(8,2)	Y			沉降罐出水含油    27
			obj.put("CJGCSHY", "");   //来水量（m3）
//			TCGJKHY	NUMBER(8,2)	Y			调储罐进口含油    28
			obj.put("TCGJKHY", "");   //来水量（m3）
//			TCGJKXF	NUMBER(8,2)	Y			调储罐进口悬浮   29
			obj.put("TCGJKXF", "");   //来水量（m3）
//			TCGCKHY	NUMBER(8,2)	Y			调储罐出口含油   30
			obj.put("TCGCKHY", "");   //来水量（m3）
//			TCGCKXF	NUMBER(8,2)	Y			调储罐出口悬浮  31
			obj.put("TCGCKXF", "");   //来水量（m3）
//			FYGCKHY	NUMBER(8,2)	Y			反应罐出口含油   32
			obj.put("FYGCKHY", "");   //来水量（m3）
//			FYGCKXF	NUMBER(8,2)	Y			反应罐出口悬浮  33
			obj.put("FYGCKXF", "");   //来水量（m3）
//			YJGLQCKHY	NUMBER(8,2)	Y			一级过滤器出口含油  34
			obj.put("YJGLQCKHY", "");   //来水量（m3）
//			YJGLQCKXF	NUMBER(8,2)	Y			一级过滤器出口悬浮  35
			obj.put("YJGLQCKXF", "");   //来水量（m3）
//			EJGLQCKHY	NUMBER(8,2)	Y			二级过滤器出口含油   36
			obj.put("EJGLQCKHY", "");   //来水量（m3）
//			EJGLQCKXF	NUMBER(8,2)	Y			二级过滤器出口悬浮   37
			obj.put("EJGLQCKXF", "");   //来水量（m3）
		}

		fourdArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "昨日");   //行标识 ：昨日，今日，增减
		if(yeardayflag == 1){
			obj.put("SXHYRYZLYYL", CommonsUtil.getNotOneData(yearData[38]));
			obj.put("SXHYRYZLYWD", CommonsUtil.getNotOneData(yearData[39]));
			obj.put("SXHYRYZYL", CommonsUtil.getNotOneData(yearData[40]));
			obj.put("SXHYRYYJSL", CommonsUtil.getNotOneData(yearData[41]));
			obj.put("SXHYRYZLYLSWD", CommonsUtil.getNotOneData(yearData[42]));
			obj.put("SXHYRYZLYHSWD", CommonsUtil.getNotOneData(yearData[43]));
			obj.put("SXHYRYNJSWD", CommonsUtil.getNotOneData(yearData[44]));
			obj.put("SXHYRYHRHYWD", CommonsUtil.getNotOneData(yearData[45]));
		}else{
			obj.put("SXHYRYZLYYL", "");
			obj.put("SXHYRYZLYWD", "");
			obj.put("SXHYRYZYL", "");
			obj.put("SXHYRYYJSL", "");
			obj.put("SXHYRYZLYLSWD", "");
			obj.put("SXHYRYZLYHSWD", "");
			obj.put("SXHYRYNJSWD", "");
			obj.put("SXHYRYHRHYWD", "");
		}
		
		fiveArr.add(obj);
		
		obj = new JSONObject();
		obj.put("LINEDATA", "今日");   //行标识 ：昨日，今日，增减
		if(nowflag == 1){
			obj.put("SXHYRYZLYYL", CommonsUtil.getNotOneData(nowData[38]));
			obj.put("SXHYRYZLYWD", CommonsUtil.getNotOneData(nowData[39]));
			obj.put("SXHYRYZYL", CommonsUtil.getNotOneData(nowData[40]));
			obj.put("SXHYRYYJSL", CommonsUtil.getNotOneData(nowData[41]));
			obj.put("SXHYRYZLYLSWD", CommonsUtil.getNotOneData(nowData[42]));
			obj.put("SXHYRYZLYHSWD", CommonsUtil.getNotOneData(nowData[43]));
			obj.put("SXHYRYNJSWD", CommonsUtil.getNotOneData(nowData[44]));
			obj.put("SXHYRYHRHYWD", CommonsUtil.getNotOneData(nowData[45]));
		}else{
			obj.put("SXHYRYZLYYL", "");
			obj.put("SXHYRYZLYWD", "");
			obj.put("SXHYRYZYL", "");
			obj.put("SXHYRYYJSL", "");
			obj.put("SXHYRYZLYLSWD", "");
			obj.put("SXHYRYZLYHSWD", "");
			obj.put("SXHYRYNJSWD", "");
			obj.put("SXHYRYHRHYWD", "");
		}
		
		fiveArr.add(obj);
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");   //行标识 ：昨日，今日，增减
		if(nowflag == 1 && yeardayflag ==1){
			obj.put("SXHYRYZLYYL", CommonsUtil.getRegulation0(nowData[38], yearData[38]));
			obj.put("SXHYRYZLYWD", CommonsUtil.getRegulation0(nowData[39], yearData[39]));
			obj.put("SXHYRYZYL", CommonsUtil.getRegulation0(nowData[40], yearData[40]));
			obj.put("SXHYRYYJSL", CommonsUtil.getRegulation0(nowData[41], yearData[41]));
			obj.put("SXHYRYZLYLSWD", CommonsUtil.getRegulation0(nowData[42], yearData[42]));
			obj.put("SXHYRYZLYHSWD", CommonsUtil.getRegulation0(nowData[43], yearData[43]));
			obj.put("SXHYRYNJSWD", CommonsUtil.getRegulation0(nowData[44], yearData[44]));
			obj.put("SXHYRYHRHYWD", CommonsUtil.getRegulation0(nowData[45], yearData[45]));
		}else{
			if(nowflag == 1 && yeardayflag ==0){
				obj.put("SXHYRYZLYYL", CommonsUtil.getRegulation0(nowData[38], zero));
				obj.put("SXHYRYZLYWD", CommonsUtil.getRegulation0(nowData[39], zero));
				obj.put("SXHYRYZYL", CommonsUtil.getRegulation0(nowData[40], zero));
				obj.put("SXHYRYYJSL", CommonsUtil.getRegulation0(nowData[41], zero));
				obj.put("SXHYRYZLYLSWD", CommonsUtil.getRegulation0(nowData[42], zero));
				obj.put("SXHYRYZLYHSWD", CommonsUtil.getRegulation0(nowData[43], zero));
				obj.put("SXHYRYNJSWD", CommonsUtil.getRegulation0(nowData[44], zero));
				obj.put("SXHYRYHRHYWD", CommonsUtil.getRegulation0(nowData[45], zero));	
			}else if(nowflag == 0 && yeardayflag ==1){
				obj.put("SXHYRYZLYYL", CommonsUtil.getRegulation0(zero, yearData[38]));
				obj.put("SXHYRYZLYWD", CommonsUtil.getRegulation0(zero, yearData[39]));
				obj.put("SXHYRYZYL", CommonsUtil.getRegulation0(zero, yearData[40]));
				obj.put("SXHYRYYJSL", CommonsUtil.getRegulation0(zero, yearData[41]));
				obj.put("SXHYRYZLYLSWD", CommonsUtil.getRegulation0(zero, yearData[42]));
				obj.put("SXHYRYZLYHSWD", CommonsUtil.getRegulation0(zero, yearData[43]));
				obj.put("SXHYRYNJSWD", CommonsUtil.getRegulation0(zero, yearData[44]));
				obj.put("SXHYRYHRHYWD", CommonsUtil.getRegulation0(zero, yearData[45]));
			}else{
				obj.put("SXHYRYZLYYL", "");
				obj.put("SXHYRYZLYWD", "");
				obj.put("SXHYRYZYL", "");
				obj.put("SXHYRYYJSL", "");
				obj.put("SXHYRYZLYLSWD", "");
				obj.put("SXHYRYZLYHSWD", "");
				obj.put("SXHYRYNJSWD", "");
				obj.put("SXHYRYHRHYWD", "");
			}
		}
		
		fiveArr.add(obj);
		
		obj = new JSONObject();
		obj.put("fristArr", fristArr);
		obj.put("secondArr", secondArr);
		obj.put("thirdArr", thirdArr);
		obj.put("fourdArr", fourdArr);
		obj.put("fiveArr", fiveArr);
		
		return obj;
		
	}

@Override
public JSONArray searchYYJJ(String rq) throws Exception{
	PropertiesConfig pc = new PropertiesConfig();//写在adction 里 在查询拼接sql及导出报表时都要用到 
	String sql ="Select * from PC_RPD_CRUDE_TRANSITION_T where 1=1 and ORG_ID = '"+pc.getSystemConfiguration("CRUDE_TRANSITIONorg_id")+"' and RQ=TO_DATE('"+rq+"','YYYY-MM-DD') order by GH";
	List<Object[]> dataSet = publicDao.searchObjectList(sql);
	JSONObject obj = new JSONObject();
	JSONArray arr = new JSONArray();
	
//	RPD_CRUDE_TRANSITION_ID	VARCHAR2(36)	N			原油交接记录ID
//	GH	VARCHAR2(10)	N			罐号
//	GYW	NUMBER(8,2)	Y			高液位
//	DYW	NUMBER(8,2)	Y			低液位
//	SM	NUMBER(8,2)	Y			视密
//	BM	NUMBER(8,2)	Y			标密
//	GW	NUMBER(8,2)	Y			罐温
//	HS	NUMBER(6,2)	Y			含水
//	MYL	NUMBER(10,2)	Y			毛油量
//	CYL	NUMBER(10,2)	Y			纯油量
//	SL	NUMBER(10,2)	Y			水量
	if(dataSet != null && dataSet.size()>0){
		if(dataSet.get(0) != null && dataSet.get(0)[0] != null){
//			RPD_CRUDE_TRANSITION_ID	VARCHAR2(36)	N			原油交接记录ID
			obj.put("RPD_CRUDE_TRANSITION_ID", String.valueOf(dataSet.get(0)[0]));
		}else{
			obj.put("RPD_CRUDE_TRANSITION_ID", "");
		}
//		GH	VARCHAR2(10)	N			罐号
		if(dataSet.get(0) != null && dataSet.get(0)[3] != null){
			obj.put("GH", String.valueOf(dataSet.get(0)[3]));
		}else{
			obj.put("GH", "");
		}
//		GYW	NUMBER(8,2)	Y			高液位
		if(dataSet.get(0) != null && dataSet.get(0)[4] != null){
			obj.put("GYW", CommonsUtil.getNotOneData(dataSet.get(0)[4]));
		}else{
			obj.put("GYW", "");
		}
//		DYW	NUMBER(8,2)	Y			低液位
		if(dataSet.get(0) != null && dataSet.get(0)[5] != null){
			obj.put("DYW", CommonsUtil.getNotOneData(dataSet.get(0)[5]));
		}else{
			obj.put("DYW", "");
		}
//		SM	NUMBER(8,2)	Y			视密
		if(dataSet.get(0) != null && dataSet.get(0)[6] != null){
			obj.put("SM", CommonsUtil.getNotOneData(dataSet.get(0)[6]));
		}else{
			obj.put("SM", "");
		}
//		BM	NUMBER(8,2)	Y			标密
		if(dataSet.get(0) != null && dataSet.get(0)[7] != null){
			obj.put("BM", CommonsUtil.getNotOneData(dataSet.get(0)[7]));
		}else{
			obj.put("BM", "");
		}
//		GW	NUMBER(8,2)	Y			罐温
		if(dataSet.get(0) != null && dataSet.get(0)[8] != null){
			obj.put("GW", CommonsUtil.getNotOneData(dataSet.get(0)[8]));
		}else{
			obj.put("GW", "");
		}
//		HS	NUMBER(6,2)	Y			含水
		if(dataSet.get(0) != null && dataSet.get(0)[9] != null){
			obj.put("HS", CommonsUtil.getNotOneData(dataSet.get(0)[9]));
		}else{
			obj.put("HS", "");
		}
//		MYL	NUMBER(10,2)	Y			毛油量
		if(dataSet.get(0) != null && dataSet.get(0)[10] != null){
			obj.put("MYL", CommonsUtil.getIntData(dataSet.get(0)[10]));
		}else{
			obj.put("MYL", "");
		}
//		CYL	NUMBER(10,2)	Y			纯油量
		if(dataSet.get(0) != null && dataSet.get(0)[11] != null){
			obj.put("CYL", CommonsUtil.getIntData(dataSet.get(0)[11]));
		}else{
			obj.put("CYL", "");
		}
//		SL	NUMBER(10,2)	Y			水量
		if(dataSet.get(0) != null && dataSet.get(0)[12] != null){
			obj.put("SL", CommonsUtil.getIntData(dataSet.get(0)[12]));
		}else{
			obj.put("SL", "");
		}
		arr.add(obj);
		
		obj = new JSONObject();
		if(dataSet.size()>1){
			if(dataSet.get(1) != null && dataSet.get(1)[0] != null){
	//			RPD_CRUDE_TRANSITION_ID	VARCHAR2(36)	N			原油交接记录ID
				obj.put("RPD_CRUDE_TRANSITION_ID", String.valueOf(dataSet.get(1)[0]));
			}else{
				obj.put("RPD_CRUDE_TRANSITION_ID", "");
			}
	//		GH	VARCHAR2(10)	N			罐号
			if(dataSet.get(1) != null && dataSet.get(1)[3] != null){
				obj.put("GH", String.valueOf(dataSet.get(1)[3]));
			}else{
				obj.put("GH", "");
			}
	//		GYW	NUMBER(8,2)	Y			高液位
			if(dataSet.get(1) != null && dataSet.get(1)[4] != null){
				obj.put("GYW", CommonsUtil.getNotOneData(dataSet.get(1)[4]));
			}else{
				obj.put("GYW", "");
			}
	//		DYW	NUMBER(8,2)	Y			低液位
			if(dataSet.get(1) != null && dataSet.get(1)[5] != null){
				obj.put("DYW", CommonsUtil.getNotOneData(dataSet.get(1)[5]));
			}else{
				obj.put("DYW", "");
			}
	//		SM	NUMBER(8,2)	Y			视密
			if(dataSet.get(1) != null && dataSet.get(1)[6] != null){
				obj.put("SM", CommonsUtil.getNotOneData(dataSet.get(1)[6]));
			}else{
				obj.put("SM", "");
			}
	//		BM	NUMBER(8,2)	Y			标密
			if(dataSet.get(1) != null && dataSet.get(1)[7] != null){
				obj.put("BM", CommonsUtil.getNotOneData(dataSet.get(1)[7]));
			}else{
				obj.put("BM", "");
			}
	//		GW	NUMBER(8,2)	Y			罐温
			if(dataSet.get(1) != null && dataSet.get(1)[8] != null){
				obj.put("GW", CommonsUtil.getNotOneData(dataSet.get(1)[8]));
			}else{
				obj.put("GW", "");
			}
	//		HS	NUMBER(6,2)	Y			含水
			if(dataSet.get(1) != null && dataSet.get(1)[9] != null){
				obj.put("HS", CommonsUtil.getNotOneData(dataSet.get(1)[9]));
			}else{
				obj.put("HS", "");
			}
	//		MYL	NUMBER(10,2)	Y			毛油量
			if(dataSet.get(1) != null && dataSet.get(1)[10] != null){
				obj.put("MYL", CommonsUtil.getIntData(dataSet.get(1)[10]));
			}else{
				obj.put("MYL", "");
			}
	//		CYL	NUMBER(10,2)	Y			纯油量
			if(dataSet.get(1) != null && dataSet.get(1)[11] != null){
				obj.put("CYL", CommonsUtil.getIntData(dataSet.get(1)[11]));
			}else{
				obj.put("CYL", "");
			}
	//		SL	NUMBER(10,2)	Y			水量
			if(dataSet.get(1) != null && dataSet.get(1)[12] != null){
				obj.put("SL", CommonsUtil.getIntData(dataSet.get(1)[12]));
			}else{
				obj.put("SL", "");
			}
			arr.add(obj);
		}else{
			obj = new JSONObject();
			obj.put("RPD_CRUDE_TRANSITION_ID", "");
//			GH	VARCHAR2(10)	N			罐号
			obj.put("GH", "");
//			GYW	NUMBER(8,2)	Y			高液位
			obj.put("GYW", "");
//			DYW	NUMBER(8,2)	Y			低液位
			obj.put("DYW", "");
//			SM	NUMBER(8,2)	Y			视密
			obj.put("SM", "");
//			BM	NUMBER(8,2)	Y			标密
			obj.put("BM", "");
//			GW	NUMBER(8,2)	Y			罐温
			obj.put("GW", "");
//			HS	NUMBER(6,2)	Y			含水
			obj.put("HS", "");
//			MYL	NUMBER(10,2)	Y			毛油量
			obj.put("MYL", "");
//			CYL	NUMBER(10,2)	Y			纯油量
			obj.put("CYL", "");
//			SL	NUMBER(10,2)	Y			水量
			obj.put("SL", "");
			arr.add(obj);
		}
		obj = new JSONObject();
		if(dataSet.size()>2){
			if(dataSet.get(2) != null && dataSet.get(2)[0] != null){
	//			RPD_CRUDE_TRANSITION_ID	VARCHAR2(36)	N			原油交接记录ID
				obj.put("RPD_CRUDE_TRANSITION_ID", String.valueOf(dataSet.get(2)[0]));
			}else{
				obj.put("RPD_CRUDE_TRANSITION_ID", "");
			}
	//		GH	VARCHAR2(10)	N			罐号
			if(dataSet.get(2) != null && dataSet.get(2)[3] != null){
				obj.put("GH", String.valueOf(dataSet.get(2)[3]));
			}else{
				obj.put("GH", "");
			}
	//		GYW	NUMBER(8,2)	Y			高液位
			if(dataSet.get(2) != null && dataSet.get(2)[4] != null){
				obj.put("GYW", CommonsUtil.getNotOneData(dataSet.get(2)[4]));
			}else{
				obj.put("GYW", "");
			}
	//		DYW	NUMBER(8,2)	Y			低液位
			if(dataSet.get(2) != null && dataSet.get(2)[5] != null){
				obj.put("DYW", CommonsUtil.getNotOneData(dataSet.get(1)[5]));
			}else{
				obj.put("DYW", "");
			}
	//		SM	NUMBER(8,2)	Y			视密
			if(dataSet.get(2) != null && dataSet.get(2)[6] != null){
				obj.put("SM", CommonsUtil.getNotOneData(dataSet.get(1)[6]));
			}else{
				obj.put("SM", "");
			}
	//		BM	NUMBER(8,2)	Y			标密
			if(dataSet.get(2) != null && dataSet.get(2)[7] != null){
				obj.put("BM", CommonsUtil.getNotOneData(dataSet.get(1)[7]));
			}else{
				obj.put("BM", "");
			}
	//		GW	NUMBER(8,2)	Y			罐温
			if(dataSet.get(2) != null && dataSet.get(2)[8] != null){
				obj.put("GW", CommonsUtil.getNotOneData(dataSet.get(1)[8]));
			}else{
				obj.put("GW", "");
			}
	//		HS	NUMBER(6,2)	Y			含水
			if(dataSet.get(2) != null && dataSet.get(2)[9] != null){
				obj.put("HS", CommonsUtil.getNotOneData(dataSet.get(1)[9]));
			}else{
				obj.put("HS", "");
			}
	//		MYL	NUMBER(10,2)	Y			毛油量
			if(dataSet.get(2) != null && dataSet.get(2)[10] != null){
				obj.put("MYL", CommonsUtil.getIntData(dataSet.get(2)[10]));
			}else{
				obj.put("MYL", "");
			}
	//		CYL	NUMBER(10,2)	Y			纯油量
			if(dataSet.get(2) != null && dataSet.get(2)[11] != null){
				obj.put("CYL", CommonsUtil.getIntData(dataSet.get(2)[11]));
			}else{
				obj.put("CYL", "");
			}
	//		SL	NUMBER(10,2)	Y			水量
			if(dataSet.get(2) != null && dataSet.get(2)[12] != null){
				obj.put("SL", CommonsUtil.getIntData(dataSet.get(2)[12]));
			}else{
				obj.put("SL", "");
			}
			arr.add(obj);	
		}else{
			obj = new JSONObject();
			obj.put("RPD_CRUDE_TRANSITION_ID", "");
//			GH	VARCHAR2(10)	N			罐号
			obj.put("GH", "");
//			GYW	NUMBER(8,2)	Y			高液位
			obj.put("GYW", "");
//			DYW	NUMBER(8,2)	Y			低液位
			obj.put("DYW", "");
//			SM	NUMBER(8,2)	Y			视密
			obj.put("SM", "");
//			BM	NUMBER(8,2)	Y			标密
			obj.put("BM", "");
//			GW	NUMBER(8,2)	Y			罐温
			obj.put("GW", "");
//			HS	NUMBER(6,2)	Y			含水
			obj.put("HS", "");
//			MYL	NUMBER(10,2)	Y			毛油量
			obj.put("MYL", "");
//			CYL	NUMBER(10,2)	Y			纯油量
			obj.put("CYL", "");
//			SL	NUMBER(10,2)	Y			水量
			obj.put("SL", "");
			arr.add(obj);
		}
	}else{
//		RPD_CRUDE_TRANSITION_ID	VARCHAR2(36)	N			原油交接记录ID
		obj.put("RPD_CRUDE_TRANSITION_ID", "");
//		GH	VARCHAR2(10)	N			罐号
		obj.put("GH", "");
//		GYW	NUMBER(8,2)	Y			高液位
		obj.put("GYW", "");
//		DYW	NUMBER(8,2)	Y			低液位
		obj.put("DYW", "");
//		SM	NUMBER(8,2)	Y			视密
		obj.put("SM", "");
//		BM	NUMBER(8,2)	Y			标密
		obj.put("BM", "");
//		GW	NUMBER(8,2)	Y			罐温
		obj.put("GW", "");
//		HS	NUMBER(6,2)	Y			含水
		obj.put("HS", "");
//		MYL	NUMBER(10,2)	Y			毛油量
		obj.put("MYL", "");
//		CYL	NUMBER(10,2)	Y			纯油量
		obj.put("CYL", "");
//		SL	NUMBER(10,2)	Y			水量
		obj.put("SL", "");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("RPD_CRUDE_TRANSITION_ID", "");
//		GH	VARCHAR2(10)	N			罐号
		obj.put("GH", "");
//		GYW	NUMBER(8,2)	Y			高液位
		obj.put("GYW", "");
//		DYW	NUMBER(8,2)	Y			低液位
		obj.put("DYW", "");
//		SM	NUMBER(8,2)	Y			视密
		obj.put("SM", "");
//		BM	NUMBER(8,2)	Y			标密
		obj.put("BM", "");
//		GW	NUMBER(8,2)	Y			罐温
		obj.put("GW", "");
//		HS	NUMBER(6,2)	Y			含水
		obj.put("HS", "");
//		MYL	NUMBER(10,2)	Y			毛油量
		obj.put("MYL", "");
//		CYL	NUMBER(10,2)	Y			纯油量
		obj.put("CYL", "");
//		SL	NUMBER(10,2)	Y			水量
		obj.put("SL", "");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("RPD_CRUDE_TRANSITION_ID", "");
//		GH	VARCHAR2(10)	N			罐号
		obj.put("GH", "");
//		GYW	NUMBER(8,2)	Y			高液位
		obj.put("GYW", "");
//		DYW	NUMBER(8,2)	Y			低液位
		obj.put("DYW", "");
//		SM	NUMBER(8,2)	Y			视密
		obj.put("SM", "");
//		BM	NUMBER(8,2)	Y			标密
		obj.put("BM", "");
//		GW	NUMBER(8,2)	Y			罐温
		obj.put("GW", "");
//		HS	NUMBER(6,2)	Y			含水
		obj.put("HS", "");
//		MYL	NUMBER(10,2)	Y			毛油量
		obj.put("MYL", "");
//		CYL	NUMBER(10,2)	Y			纯油量
		obj.put("CYL", "");
//		SL	NUMBER(10,2)	Y			水量
		obj.put("SL", "");
		arr.add(obj);
	}
	
	return arr;
}

@Override
public List<PcRpdCrudeTransitionT> searchCrudeTransition(String id, String gh,String rq,String lhzID)
		throws Exception {
	
	String hql = " FROM PcRpdCrudeTransitionT t WHERE 1=1 ";
	if(id != null && !"".equals(id)){
		hql +=" and t.rpdCrudeTransitionId = '"+id+"'";
	}
	
	if(gh != null && !"".equals(gh)){
		hql +=" and t.gh = '"+gh+"'";
	}
	
	if(rq != null && !"".equals(rq)){
		hql +=" and t.rq = TO_DATE('"+rq+"','YYYY-MM-DD')";
	}
	
	if(lhzID != null && !"".equals(lhzID)){
		hql +=" and t.orgId = '"+lhzID+"'";
	}
	
	return publicDao.searchCrudeTransition(hql);
}

@Override
public List<PcRpdU2GeneralT> searchU2General(String id, String rq)
		throws Exception {
		String hql = " FROM PcRpdU2GeneralT t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			hql +=" and t.rpdU2GeneralId = '"+id+"'";
		}
		
		if(rq != null && !"".equals(rq)){
			hql +=" and t.rq = TO_DATE('"+rq+"','YYYY-MM-DD')";
		}
		return publicDao.searchU2General(hql);
}

@Override
public boolean updateZHRB(PcRpdReportMessageT reportMessage,
		PcRpdU2GeneralT u2General, PcRpdCrudeTransitionT crudeTransition0,
		PcRpdCrudeTransitionT crudeTransition1,
		PcRpdCrudeTransitionT crudeTransition2) throws Exception {
	return publicDao.updateZHRB(reportMessage,u2General,crudeTransition0,crudeTransition1,crudeTransition2);
}
}
