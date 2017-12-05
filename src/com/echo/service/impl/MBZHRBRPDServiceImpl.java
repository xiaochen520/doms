package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.MBZHRBDao;
import com.echo.dto.PcRpdU1SagdAutoT;
import com.echo.dto.PcRpdU1SagdGeneralT;
import com.echo.dto.PcRpdU1SagdLiquidT;
import com.echo.dto.PcRpdU1SagdPivotalT;
import com.echo.service.MBZHRBRPDService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class MBZHRBRPDServiceImpl implements MBZHRBRPDService{
	private MBZHRBDao mbzhrbDao;
	
	public void setMbzhrbDao(MBZHRBDao mbzhrbDao) {
		this.mbzhrbDao = mbzhrbDao;
	}

	public MBZHRBDao getMbzhrbDao() {
		return mbzhrbDao;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public JSONObject searchZHRB(String rq) throws Exception {
		
		String nowtime = DateBean.getSystemTime().substring(0, 10);
		if(rq != null && !"".equals(rq)){
			
		}else{
			rq = nowtime;
		}
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dateNum = mbzhrbDao.searchCalcNum(timeCalssql);
		if(dateNum != null && dateNum.size()>0){
			calcNum = dateNum.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		Map map = mbzhrbDao.seachSystemInit(DateBean.getTimeStr(new Date()));
		if(map!=null){
//			throw new Exception(map.get(0).toString());
		}
		
//		String sql = "select YJCSLJ,RHXCSLJ,RHX1JYLJ,RHX2JYLJ,RHX3JYLJ,ZCSL,BHL,DTCYLJ,DTCYLL,XXLLJLJ,XXLLJLL,CCLLJ from PC_RPD_U1_SAGD_PIVOTAL_T"; //重要生产参数
//		
//		String sql1 = "select ZYL,SXCS,RHXCS,DTCYL from PC_RPD_U1_SAGD_LIQUID_T"; //汇报信息-流量
//		String sql2 = "select RCCYL,YCLJ,ZXPRJ from PC_RPD_U1_SAGD_GENERAL_T";//汇报信息-浓度
//		
//		String sql3 = "select SXCSHY,DTCSHY from PC_RPD_U1_SAGD_PIVOTAL_T"; //汇报信息-含油
		
		String sql = "select YCLJG1YW,YCLJG2YW,YCLJJYL,ZXPRJG3YW,ZXPRJG4YW,ZXPRJJYL,YCLG5YW,YCLG5JYL,YCLG6YW,YCLG6JYL,JYB1CKYL,JYB1KD,JYB2CKYL,JYB2KD,JYB3CKYL,JYB3KD,JYB4CKYL,JYB4KD,CCBCKYL,CCBPL,PR_3902,LRC_2101A,PT_2112,LRC_2101B,PRC_3FLQ,LRC_3FLQ,TR_3101A,LIT_3101A,PR_3101A,TR_3101B,LIT_3101B,PR_3101B,TR_3101C,LIT_3101C,PR_3101C,LRC_3202D,PRA_3201D,TR_3201D,PRC_3703,PRC_3702,PRC_3706,PRC_3704,PRC_3705,PRC_3707,YSJ_P1,YSJ_T1,YSJ_P2,YSJ_T2," +
				"LRC_3201A,LRC_3202A,LRC_3203A,PR_3201A,TR_3201A,LRC_3201B,LRC_3202B,LRC_3203B,PR_3201B,TR_3201B,LRC_3201C,LRC_3202C,LRC_3203C,PR_3201C,TR_3201C,TT_102,TT_105,HRQ1LYHQWD,TT_115,FIT_101,TT_101,TT_106,HRQ2LYHQWD,TT_115,FIT_102,TT_103,TT_107,HRQ3LYHQWD,FIT_103,TT_112,TT_104,TT_108,HRQ4LYHQWD,TT_112,FIT_104,TE_2102,FIT_2101,TRC_3301,FIT_2102,TE_2113,FIT_2105,TE_2114,FIT_2106,GZQYL,GZQWD,"; // 第二个表格
		
		
		//日期 
		sql +=" to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS') as BBSJ,RPD_U1_SAGD_AUTO_ID from PC_RPD_U1_SAGD_AUTO_T  where 1=1 ";
		String beforetime = DateBean.getBeforeDAYTime(rq);
		sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ ";
		JSONObject obj = new JSONObject();
		List<Object[]> dataSet = mbzhrbDao.searchObjectList(sql);
		
		sql = "select YJCSLJ,RHXCSLJ,RHX1JYLJ,RHX2JYLJ,RHX3JYLJ,ZCSL,BHL,DTCYLJ,DTCYLL,XXLLJLJ,XXLLJLL,CCLLJ,SXCSHY,DTCSHY,"; //重要生产参数
		sql +=" to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS') as BBSJ,RPD_U1_SAGD_PIVOTAL_ID from PC_RPD_U1_SAGD_PIVOTAL_T  where 1=1 ";
		sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ ";
		List<Object[]> dataSet1 = mbzhrbDao.searchObjectList(sql);
		
		String beforetime2 = DateBean.getBefore2DAYTime(rq);
		sql = "select ZYL,SXCS,RHXCS,DTCYL,to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS') as BBSJ,RPD_U1_SAGD_LIQUID_ID from PC_RPD_U1_SAGD_LIQUID_T where 1=1"; //汇报信息-流量
		sql += " and BBSJ between TO_DATE('"+beforetime2+" 16:00:00','YYYY-MM-DD HH24:mi:ss') and TO_DATE('"+rq+" 08:00:00','YYYY-MM-DD HH24:mi:ss') order by BBSJ ";
		List<Object[]> dataSet2 = mbzhrbDao.searchObjectList(sql);
		
		
		sql = "select RCCYL,YCLJ,ZXPRJ,ZBR,SHR,REMARK,BBRQ,RPD_U1_SAGD_GENERAL_ID,ZYGZ from PC_RPD_U1_SAGD_GENERAL_T";//汇报信息-浓度
		sql += " where BBRQ between TO_DATE('"+beforetime+"','YYYY-MM-DD') and TO_DATE('"+rq+"','YYYY-MM-DD') order by BBRQ ";
		List<Object[]> dataSet3 = mbzhrbDao.searchObjectList(sql);
		
		obj = getTableDataJson(dataSet,dataSet1,dataSet2,dataSet3, rq);

		return obj;
	}
	
	
	
	
	public JSONObject getTableDataJson(List<Object[]> dataSet,List<Object[]> dataSet1,List<Object[]> dataSet2,List<Object[]> dataSet3,String rq){
	
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONArray firstArr = new JSONArray(); 
		JSONArray secondArr = new JSONArray();
		JSONArray ThirdArr = new JSONArray();
		JSONArray llArr = new JSONArray();
		JSONArray nongduArr = new JSONArray();
		
		List<String> YCLJJYL = new ArrayList<String>();
		List<String> ZXPRJJYL = new ArrayList<String>();
		List<String> YCLG5JYL = new ArrayList<String>();
		List<String> YCLG6JYL = new ArrayList<String>();
		
		
		Object[] downTab = null;
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String[][] dates = DateBean.getReportTime("time", rq);
		if(dataSet != null && dataSet.size()>0){
			for(String[] dateList:dates){
				obj = new JSONObject();
	
				for(String data:dateList){
					downTab = null;
					dataflg = 0;
					for(Object[] down:dataSet){
						if(null != down[93] && data.toString().equals(down[93].toString())){
							dataflg = 1;
							downTab = down;
						}
					}
					if(dataflg == 1){
						obj = new JSONObject(); 
						obj.put("RD_U1_SAGD_AUTO_ID", downTab[94]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(downTab[0] != null){
							obj.put("YCLJG1YW", downTab[0]);
						}else{
							obj.put("YCLJG1YW", "");
						}
						if(downTab[1]!=null){
							obj.put("YCLJG2YW", downTab[1]);
						}else{
							obj.put("YCLJG2YW", "");
						}
						if(downTab[2]!=null){
							obj.put("YCLJJYL", downTab[2]);
							YCLJJYL.add(downTab[2].toString());
						}else{
							obj.put("YCLJJYL", "");
						}
						if(downTab[3]!=null){
							obj.put("ZXPRJG3YW", downTab[3]);
						}else{
							obj.put("ZXPRJG3YW", "");
						}
						if(downTab[4]!=null){
							obj.put("ZXPRJG4YW", downTab[4]);
						}else{
							obj.put("ZXPRJG4YW", "");
						}
						if(downTab[5]!=null){
							obj.put("ZXPRJJYL", downTab[5]);
							ZXPRJJYL.add(downTab[5].toString());
						}else{
							obj.put("ZXPRJJYL", "");
						}
						if(downTab[6]!=null){
							obj.put("YCLG5YW", downTab[6]);
						}else{
							obj.put("YCLG5YW", "");
						}
						if(downTab[7]!=null){
							obj.put("YCLG5JYL", downTab[7]);
							YCLG5JYL.add(downTab[7].toString());
						}else{
							obj.put("YCLG5JYL", "");
						}
						if(downTab[8]!=null){
							obj.put("YCLG6YW", downTab[8]);
						}else{
							obj.put("YCLG6YW", "");
						}
						
						if(downTab[9]!=null){
							obj.put("YCLG6JYL", downTab[9]);
							YCLG6JYL.add(downTab[9].toString());
						}else{
							obj.put("YCLG6JYL", "");
						}
						//obj.put("REPORT_TIME", data.substring(11, 16));
	
						if(downTab[10]!=null){
							obj.put("JYB1CKYL", CommonsUtil.format2pData(downTab[10].toString()));
						}else{
							obj.put("JYB1CKYL", "");
						}
						if(downTab[11]!=null){
							obj.put("JYB1KD", downTab[11]);
						}else{
							obj.put("JYB1KD", "");
						}
						if(downTab[12]!=null){
							obj.put("JYB2CKYL", CommonsUtil.format2pData(downTab[12].toString()));
						}else{
							obj.put("JYB2CKYL", "");
						}
						
						if(downTab[13]!=null){
							obj.put("JYB2KD", downTab[13]);
						}else{
							obj.put("JYB2KD", "");
						}
						if(downTab[14]!=null){
							obj.put("JYB3CKYL", CommonsUtil.format2pData(downTab[14].toString()));
						}else{
							obj.put("JYB3CKYL", "");
						}
						if(downTab[15]!=null){
							obj.put("JYB3KD", downTab[15]);
						}else{
							obj.put("JYB3KD", "");
						}

						if(downTab[16] != null){
							obj.put("JYB4CKYL", CommonsUtil.format2pData(downTab[16].toString()));
						}else{
							obj.put("JYB4CKYL", "");
						}
						if(downTab[17]!=null){
							obj.put("JYB4KD", downTab[17]);
						}else{
							obj.put("JYB4KD", "");
						}
						if(downTab[18]!=null){
							obj.put("CCBCKYL", CommonsUtil.format2pData(downTab[18].toString()));
						}else{
							obj.put("CCBCKYL", "");
						}
						if(downTab[19]!=null){
							obj.put("CCBPL", downTab[19]);
						}else{
							obj.put("CCBPL", "");
						}
						if(downTab[20]!=null){
							obj.put("PR_3902", CommonsUtil.format2pData(downTab[20].toString()));
						}else{
							obj.put("PR_3902", "");
						}
						if(downTab[21]!=null){
							obj.put("LRC_2101A", CommonsUtil.format1pData(downTab[21].toString()));
						}else{
							obj.put("LRC_2101A", "");
						}
						if(downTab[22]!=null){
							obj.put("PT_2112", CommonsUtil.format2pData(downTab[22].toString()));
						}else{
							obj.put("PT_2112", "");
						}
						if(downTab[23]!=null){
							obj.put("LRC_2101B", CommonsUtil.format1pData(downTab[23].toString()));
						}else{
							obj.put("LRC_2101B", "");
						}
						if(downTab[24]!=null){
							obj.put("PRC_3FLQ", CommonsUtil.format2pData(downTab[24].toString()));
						}else{
							obj.put("PRC_3FLQ", "");
						}
						
						if(downTab[25]!=null){
							obj.put("LRC_3FLQ", CommonsUtil.format1pData(downTab[25].toString()));
						}else{
							obj.put("LRC_3FLQ", "");
						}
						if(downTab[26]!=null){
							obj.put("TR_3101A", CommonsUtil.format1pData(downTab[26].toString()));
						}else{
							obj.put("TR_3101A", "");
						}
						if(downTab[27]!=null){
							obj.put("LIT_3101A", CommonsUtil.format2pData(downTab[27].toString()));
						}else{
							obj.put("LIT_3101A", "");
						}
						if(downTab[28]!=null){
							obj.put("PR_3101A", CommonsUtil.format2pData(downTab[28].toString()));
						}else{
							obj.put("PR_3101A", "");
						}
						
						if(downTab[29]!=null){
							obj.put("TR_3101B", CommonsUtil.format1pData(downTab[29].toString()));
						}else{
							obj.put("TR_3101B", "");
						}
						if(downTab[30]!=null){
							obj.put("LIT_3101B", CommonsUtil.format2pData(downTab[30].toString()));
						}else{
							obj.put("LIT_3101B", "");
						}
						
						if(downTab[31]!=null){
							obj.put("PR_3101B", CommonsUtil.format2pData(downTab[31].toString()));
						}else{
							obj.put("PR_3101B", "");
						}
						
						if(downTab[32]!=null){
							obj.put("TR_3101C", CommonsUtil.format1pData(downTab[32].toString()));
						}else{
							obj.put("TR_3101C", "");
						}
						
						if(downTab[33]!=null){
							obj.put("LIT_3101C", CommonsUtil.format2pData(downTab[33].toString()));
						}else{
							obj.put("LIT_3101C", "");
						}
						if(downTab[34]!=null){
							obj.put("PR_3101C", CommonsUtil.format2pData(downTab[34].toString()));
						}else{
							obj.put("PR_3101C", "");
						}
						if(downTab[35]!=null){
							obj.put("LRC_3202D", CommonsUtil.format2pData(downTab[35].toString()));
						}else{
							obj.put("LRC_3202D", "");
						}
						//------------------------------------------------------------------------------------------
						if(downTab[36]!=null){
							obj.put("PRA_3201D", CommonsUtil.format2pData(downTab[36].toString()));
						}else{
							obj.put("PRA_3201D", "");
						}
						if(downTab[37]!=null){
							obj.put("TR_3201D", CommonsUtil.format1pData(downTab[37].toString()));
						}else{
							obj.put("TR_3201D", "");
						}
						if(downTab[38]!=null){
							obj.put("PRC_3703", CommonsUtil.format2pData(downTab[38].toString()));
						}else{
							obj.put("PRC_3703", "");
						}
						if(downTab[39]!=null){
							obj.put("PRC_3702", CommonsUtil.format2pData(downTab[39].toString()));
						}else{
							obj.put("PRC_3702", "");
						}
						if(downTab[40]!=null){
							obj.put("PRC_3706", CommonsUtil.format2pData(downTab[40].toString()));
						}else{
							obj.put("PRC_3706", "");
						}
						if(downTab[41]!=null){
							obj.put("PRC_3704", CommonsUtil.format2pData(downTab[41].toString()));
						}else{
							obj.put("PRC_3704", "");
						}
						if(downTab[42]!=null){
							obj.put("PRC_3705", CommonsUtil.format2pData(downTab[42].toString()));
						}else{
							obj.put("PRC_3705", "");
						}
						if(downTab[43]!=null){
							obj.put("PRC_3707", CommonsUtil.format2pData(downTab[43].toString()));
						}else{
							obj.put("PRC_3707", "");
						}
						if(downTab[44]!=null){
							obj.put("YSJ_P1", CommonsUtil.format1pData(downTab[44].toString()));
						}else{
							obj.put("YSJ_P1", "");
						}
						if(downTab[45]!=null){
							obj.put("YSJ_T1", CommonsUtil.format1pData(downTab[45].toString()));
						}else{
							obj.put("YSJ_T1", "");
						}
						if(downTab[46]!=null){
							obj.put("YSJ_P2", CommonsUtil.format1pData(downTab[46].toString()));
						}else{
							obj.put("YSJ_P2", "");
						}
						if(downTab[47]!=null){
							obj.put("YSJ_T2", CommonsUtil.format1pData(downTab[47].toString()));
						}else{
							obj.put("YSJ_T2", "");
						}
						
						//下表
						obj2 = new JSONObject(); //液位，流量
						//obj2.put("RD_U1_SAGD_AUTO_ID", downTab[37]);
						obj2.put("REPORT_TIME", data.substring(11, 16));
						if(downTab[48] != null){
							obj2.put("LRC_3201A", CommonsUtil.format2pData(downTab[48].toString()));
						}else{
							obj2.put("LRC_3201A", "");
						}
						if(downTab[49] != null){
							obj2.put("LRC_3202A", CommonsUtil.format2pData(downTab[49].toString()));
						}else{
							obj2.put("LRC_3202A", "");
						}
						if(downTab[50] != null){
							obj2.put("LRC_3203A", CommonsUtil.format2pData(downTab[50].toString()));
						}else{
							obj2.put("LRC_3203A", "");
						}
						if(downTab[51] != null){
							obj2.put("PR_3201A", CommonsUtil.format2pData(downTab[51].toString()));
						}else{
							obj2.put("PR_3201A", "");
						}
						if(downTab[52] != null){
							obj2.put("TR_3201A", CommonsUtil.format1pData(downTab[52].toString()));
						}else{
							obj2.put("TR_3201A", "");
						}
						if(downTab[53] != null){
							obj2.put("LRC_3201B", CommonsUtil.format2pData(downTab[53].toString()));
						}else{
							obj2.put("LRC_3201B", "");
						}
						if(downTab[54] != null){
							obj2.put("LRC_3202B", CommonsUtil.format2pData(downTab[54].toString()));
						}else{
							obj2.put("LRC_3202B", "");
						}
						if(downTab[55] != null){
							obj2.put("LRC_3203B", CommonsUtil.format2pData(downTab[55].toString()));
						}else{
							obj2.put("LRC_3203B", "");
						}
						if(downTab[56] != null){
							obj2.put("PR_3201B", CommonsUtil.format2pData(downTab[56].toString()));
						}else{
							obj2.put("PR_3201B", "");
						}
						if(downTab[57] != null){
							obj2.put("TR_3201B", CommonsUtil.format1pData(downTab[57].toString()));
						}else{
							obj2.put("TR_3201B", "");
						}
						if(downTab[58] != null){
							obj2.put("LRC_3201C", CommonsUtil.format2pData(downTab[58].toString()));
						}else{
							obj2.put("LRC_3201C", "");
						}
						if(downTab[59] != null){
							obj2.put("LRC_3202C", CommonsUtil.format2pData(downTab[59].toString()));
						}else{
							obj2.put("LRC_3202C", "");
						}
						if(downTab[60] != null){
							obj2.put("LRC_3203C", CommonsUtil.format2pData(downTab[60].toString()));
						}else{
							obj2.put("LRC_3203C", "");
						}
						if(downTab[61] != null){
							obj2.put("PR_3201C", CommonsUtil.format2pData(downTab[61].toString()));
						}else{
							obj2.put("PR_3201C", "");
						}
						if(downTab[62] != null){
							obj2.put("TR_3201C", CommonsUtil.format1pData(downTab[62].toString()));
						}else{
							obj2.put("TR_3201C", "");
						}
						if(downTab[63] != null){
							obj2.put("TT_102", CommonsUtil.format1pData(downTab[63].toString()));
						}else{
							obj2.put("TT_102", "");
						}
						if(downTab[64] != null){
							obj2.put("TT_105", CommonsUtil.format1pData(downTab[64].toString()));
						}else{
							obj2.put("TT_105", "");
						}
						if(downTab[65] != null){
							obj2.put("HRQ1LYHQWD", CommonsUtil.format1pData(downTab[65].toString()));
						}else{
							obj2.put("HRQ1LYHQWD", "");
						}
						if(downTab[66] != null){
							obj2.put("TT_115", CommonsUtil.format1pData(downTab[66].toString()));
						}else{
							obj2.put("TT_115", "");
						}
						if(downTab[67] != null){
							obj2.put("FIT_101", CommonsUtil.format1pData(downTab[67].toString()));
						}else{
							obj2.put("FIT_101", "");
						}
						if(downTab[68] != null){
							obj2.put("TT_101", CommonsUtil.format1pData(downTab[68].toString()));
						}else{
							obj2.put("TT_101", "");
						}
						if(downTab[69] != null){
							obj2.put("TT_106", CommonsUtil.format1pData(downTab[69].toString()));
						}else{
							obj2.put("TT_106", "");
						}
						if(downTab[70] != null){
							obj2.put("HRQ2LYHQWD", CommonsUtil.format1pData(downTab[70].toString()));
						}else{
							obj2.put("HRQ2LYHQWD", "");
						}
						if(downTab[71] != null){
							obj2.put("TT_115", CommonsUtil.format1pData(downTab[71].toString()));
						}else{
							obj2.put("TT_115", "");
						}
						if(downTab[72] != null){
							obj2.put("FIT_102", CommonsUtil.format1pData(downTab[72].toString()));
						}else{
							obj2.put("FIT_102", "");
						}
						if(downTab[73] != null){
							obj2.put("TT_103", CommonsUtil.format1pData(downTab[73].toString()));
						}else{
							obj2.put("TT_103", "");
						}
						if(downTab[74] != null){
							obj2.put("TT_107", CommonsUtil.format1pData(downTab[74].toString()));
						}else{
							obj2.put("TT_107", "");
						}
						if(downTab[75] != null){
							obj2.put("HRQ3LYHQWD", CommonsUtil.format1pData(downTab[75].toString()));
						}else{
							obj2.put("HRQ3LYHQWD", "");
						}
						if(downTab[76] != null){
							obj2.put("FIT_103", CommonsUtil.format1pData(downTab[76].toString()));
						}else{
							obj2.put("FIT_103", "");
						}
						if(downTab[77] != null){
							obj2.put("TT_112", CommonsUtil.format1pData(downTab[77].toString()));
						}else{
							obj2.put("TT_112", "");
						}
						if(downTab[78] != null){
							obj2.put("TT_104", CommonsUtil.format1pData(downTab[78].toString()));
						}else{
							obj2.put("TT_104", "");
						}
						if(downTab[79] != null){
							obj2.put("TT_108", CommonsUtil.format1pData(downTab[79].toString()));
						}else{
							obj2.put("TT_108", "");
						}
						if(downTab[80] != null){
							obj2.put("HRQ4LYHQWD", CommonsUtil.format1pData(downTab[80].toString()));
						}else{
							obj2.put("HRQ4LYHQWD", "");
						}
						if(downTab[81] != null){
							obj2.put("TT_112", CommonsUtil.format1pData(downTab[81].toString()));
						}else{
							obj2.put("TT_112", "");
						}
						if(downTab[82] != null){
							obj2.put("FIT_104", CommonsUtil.format1pData(downTab[82].toString()));
						}else{
							obj2.put("FIT_104", "");
						}
						if(downTab[83] != null){
							obj2.put("TE_2102", CommonsUtil.format1pData(downTab[83].toString()));
						}else{
							obj2.put("TE_2102", "");
						}
						if(downTab[84] != null){
							obj2.put("FIT_2101", CommonsUtil.format2pData(downTab[84].toString()));
						}else{
							obj2.put("FIT_2101", "");
						}
						if(downTab[85] != null){
							obj2.put("TRC_3301", CommonsUtil.format1pData(downTab[85].toString()));
						}else{
							obj2.put("TRC_3301", "");
						}
						if(downTab[86] != null){
							obj2.put("FIT_2102", CommonsUtil.format2pData(downTab[86].toString()));
						}else{
							obj2.put("FIT_2102", "");
						}
						if(downTab[87] != null){
							obj2.put("TE_2113", CommonsUtil.format1pData(downTab[87].toString()));
						}else{
							obj2.put("TE_2113", "");
						}
						if(downTab[88] != null){
							obj2.put("FIT_2105", CommonsUtil.format2pData(downTab[88].toString()));
						}else{
							obj2.put("FIT_2105", "");
						}
						if(downTab[89] != null){
							obj2.put("TE_2114", CommonsUtil.format1pData(downTab[89].toString()));
						}else{
							obj2.put("TE_2114", "");
						}
						if(downTab[90] != null){
							obj2.put("FIT_2106", CommonsUtil.format2pData(downTab[90].toString()));
						}else{
							obj2.put("FIT_2106", "");
						}
						if(downTab[91] != null){
							obj2.put("GZQYL", CommonsUtil.format2pData(downTab[91].toString()));
						}else{
							obj2.put("GZQYL", "");
						}
						if(downTab[92] != null){
							obj2.put("GZQWD", downTab[92]);
						}else{
							obj2.put("GZQWD", "");
						}
						
						
					}else{
						obj = new JSONObject(); //液位，流量
						obj.put("RD_U1_SAGD_AUTO_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("YCLJG1YW", "");
						obj.put("YCLJG2YW", "");
						obj.put("YCLJJYL", "");
						obj.put("ZXPRJG3YW", "");
						obj.put("ZXPRJG4YW", "");
						obj.put("ZXPRJJYL", "");
						obj.put("YCLG5YW", "");
						obj.put("YCLG5JYL", "");
						obj.put("YCLG6YW", "");
						obj.put("YCLG6JYL", "");
						obj.put("JYB1CKYL", "");
						obj.put("JYB1KD", "");
						obj.put("JYB2CKYL", "");
						obj.put("JYB2KD", "");
						obj.put("JYB3CKYL", "");
						obj.put("JYB3KD", "");
						obj.put("JYB4CKYL", "");
						obj.put("JYB4KD", "");
						obj.put("CCBCKYL", "");
						obj.put("CCBPL", "");
						obj.put("PR_3902", "");
						obj.put("LRC_2101A", "");
						obj.put("PT_2112", "");
						obj.put("LRC_2101B", "");
						obj.put("PRC_3FLQ", "");
						obj.put("LRC_3FLQ", "");
						obj.put("TR_3101A", "");
						obj.put("LIT_3101A", "");
						obj.put("PR_3101A", "");
						obj.put("TR_3101B", "");
						obj.put("LIT_3101B", "");
						obj.put("PR_3101B", "");
						obj.put("TR_3101C", "");
						obj.put("LIT_3101C", "");
						obj.put("PR_3101C", "");
						obj.put("LRC_3202D", "");
						obj.put("PRA_3201D", "");
						obj.put("TR_3201D", "");
						obj.put("PRC_3703", "");
						obj.put("PRC_3702", "");
						obj.put("PRC_3706", "");
						obj.put("PRC_3704", "");
						obj.put("PRC_3705", "");
						obj.put("PRC_3707", "");
						obj.put("YSJ_P1", "");
						obj.put("YSJ_T1", "");
						obj.put("YSJ_P2", "");
						obj.put("YSJ_T2", "");
						
						
						obj2 = new JSONObject(); //液位，流量
						obj2.put("REPORT_TIME", data.substring(11, 16));
						obj2.put("LRC_3201A", "");
						obj2.put("LRC_3202A", "");
						obj2.put("LRC_3203A", "");
						obj2.put("PR_3201A", "");
						obj2.put("TR_3201A", "");
						obj2.put("LRC_3201B", "");
						obj2.put("LRC_3202B", "");
						obj2.put("LRC_3203B", "");
						obj2.put("PR_3201B", "");
						obj2.put("TR_3201B", "");
						obj2.put("LRC_3201C", "");
						obj2.put("LRC_3202C", "");
						obj2.put("LRC_3203C", "");
						obj2.put("PR_3201C", "");
						obj2.put("TR_3201C", "");
						obj2.put("TT_102", "");
						obj2.put("TT_105", "");
						obj2.put("HRQ1LYHQWD", "");
						obj2.put("TT_115", "");
						obj2.put("FIT_101", "");
						obj2.put("TT_101", "");
						obj2.put("TT_106", "");
						obj2.put("HRQ2LYHQWD", "");
						obj2.put("TT_115", "");
						obj2.put("FIT_102", "");
						obj2.put("TT_103", "");
						obj2.put("TT_107", "");
						obj2.put("HRQ3LYHQWD", "");
						obj2.put("FIT_103", "");
						obj2.put("TT_112", "");
						obj2.put("TT_104", "");
						obj2.put("TT_108", "");
						obj2.put("HRQ4LYHQWD", "");
						obj2.put("TT_112", "");
						obj2.put("FIT_104", "");
						obj2.put("TE_2102", "");
						obj2.put("FIT_2101", "");
						obj2.put("TRC_3301", "");
						obj2.put("FIT_2102", "");
						obj2.put("TE_2113", "");
						obj2.put("FIT_2105", "");
						obj2.put("TE_2114", "");
						obj2.put("FIT_2106", "");
						obj2.put("GZQYL", "");
						obj2.put("GZQWD", "");

					}
					firstArr.add(obj);
					secondArr.add(obj2);
				}
			}
		}else{
			for(String[] dateList:dates){
				for(String data:dateList){
					obj = new JSONObject(); 
					obj.put("RD_U1_SAGD_AUTO_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("YCLJG1YW", "");
					obj.put("YCLJG2YW", "");
					obj.put("YCLJJYL", "");
					obj.put("ZXPRJG3YW", "");
					obj.put("ZXPRJG4YW", "");
					obj.put("ZXPRJJYL", "");
					obj.put("YCLG5YW", "");
					obj.put("YCLG5JYL", "");
					obj.put("YCLG6YW", "");
					obj.put("YCLG6JYL", "");
					obj.put("JYB1CKYL", "");
					obj.put("JYB1KD", "");
					obj.put("JYB2CKYL", "");
					obj.put("JYB2KD", "");
					obj.put("JYB3CKYL", "");
					obj.put("JYB3KD", "");
					obj.put("JYB4CKYL", "");
					obj.put("JYB4KD", "");
					obj.put("CCBCKYL", "");
					obj.put("CCBPL", "");
					obj.put("PR_3902", "");
					obj.put("LRC_2101A", "");
					obj.put("PT_2112", "");
					obj.put("LRC_2101B", "");
					obj.put("PRC_3FLQ", "");
					obj.put("LRC_3FLQ", "");
					obj.put("TR_3101A", "");
					obj.put("LIT_3101A", "");
					obj.put("PR_3101A", "");
					obj.put("TR_3101B", "");
					obj.put("LIT_3101B", "");
					obj.put("PR_3101B", "");
					obj.put("TR_3101C", "");
					obj.put("LIT_3101C", "");
					obj.put("PR_3101C", "");
					obj.put("LRC_3202D", "");
					obj.put("PRA_3201D", "");
					obj.put("TR_3201D", "");
					obj.put("PRC_3703", "");
					obj.put("PRC_3702", "");
					obj.put("PRC_3706", "");
					obj.put("PRC_3704", "");
					obj.put("PRC_3705", "");
					obj.put("PRC_3707", "");
					obj.put("YSJ_P1", "");
					obj.put("YSJ_T1", "");
					obj.put("YSJ_P2", "");
					obj.put("YSJ_T2", "");
					
					
					obj2 = new JSONObject(); 
					obj2.put("REPORT_TIME", data.substring(11, 16));
					obj2.put("LRC_3201A", "");
					obj2.put("LRC_3202A", "");
					obj2.put("LRC_3203A", "");
					obj2.put("PR_3201A", "");
					obj2.put("TR_3201A", "");
					obj2.put("LRC_3201B", "");
					obj2.put("LRC_3202B", "");
					obj2.put("LRC_3203B", "");
					obj2.put("PR_3201B", "");
					obj2.put("TR_3201B", "");
					obj2.put("LRC_3201C", "");
					obj2.put("LRC_3202C", "");
					obj2.put("LRC_3203C", "");
					obj2.put("PR_3201C", "");
					obj2.put("TR_3201C", "");
					obj2.put("TT_102", "");
					obj2.put("TT_105", "");
					obj2.put("HRQ1LYHQWD", "");
					obj2.put("TT_115", "");
					obj2.put("FIT_101", "");
					obj2.put("TT_101", "");
					obj2.put("TT_106", "");
					obj2.put("HRQ2LYHQWD", "");
					obj2.put("TT_115", "");
					obj2.put("FIT_102", "");
					obj2.put("TT_103", "");
					obj2.put("TT_107", "");
					obj2.put("HRQ3LYHQWD", "");
					obj2.put("FIT_103", "");
					obj2.put("TT_112", "");
					obj2.put("TT_104", "");
					obj2.put("TT_108", "");
					obj2.put("HRQ4LYHQWD", "");
					obj2.put("TT_112", "");
					obj2.put("FIT_104", "");
					obj2.put("TE_2102", "");
					obj2.put("FIT_2101", "");
					obj2.put("TRC_3301", "");
					obj2.put("FIT_2102", "");
					obj2.put("TE_2113", "");
					obj2.put("FIT_2105", "");
					obj2.put("TE_2114", "");
					obj2.put("FIT_2106", "");
					obj2.put("GZQYL", "");
					obj2.put("GZQWD", "");
					firstArr.add(obj);
					secondArr.add(obj2);
				}
			}
		}
		
		
		if(dataSet != null && dataSet.size()>0){
			obj = new JSONObject();
			obj.put("REPORT_TIME", "合计");
			
			obj.put("YCLJG1YW", "");
			obj.put("YCLJG2YW", "");
			obj.put("YCLJJYL", CommonsUtil.getSum(YCLJJYL));
			obj.put("ZXPRJG3YW", "");
			obj.put("ZXPRJG4YW", "");
			obj.put("ZXPRJJYL", CommonsUtil.getSum(ZXPRJJYL));
			obj.put("YCLG5YW", "");
			obj.put("YCLG5JYL", CommonsUtil.getSum(YCLG5JYL));
			obj.put("YCLG6YW", "");
			obj.put("YCLG6JYL", CommonsUtil.getSum(YCLG6JYL));
			obj.put("JYB1CKYL", "");
			obj.put("JYB1KD", "");
			obj.put("JYB2CKYL", "");
			obj.put("JYB2KD", "");
			obj.put("JYB3CKYL", "");
			obj.put("JYB3KD", "");
			obj.put("JYB4CKYL", "");
			obj.put("JYB4KD", "");
			obj.put("CCBCKYL", "");
			obj.put("CCBPL", "");
			obj.put("PR_3902", "");
			obj.put("LRC_2101A", "");
			obj.put("PT_2112", "");
			obj.put("LRC_2101B", "");
			obj.put("PRC_3FLQ", "");
			obj.put("LRC_3FLQ", "");
			obj.put("TR_3101A", "");
			obj.put("LIT_3101A", "");
			obj.put("PR_3101A", "");
			obj.put("TR_3101B", "");
			obj.put("LIT_3101B", "");
			obj.put("PR_3101B", "");
			obj.put("TR_3101C", "");
			obj.put("LIT_3101C", "");
			obj.put("PR_3101C", "");
			obj.put("LRC_3202D", "");
			obj.put("PRA_3201D", "");
			obj.put("TR_3201D", "");
			obj.put("PRC_3703", "");
			obj.put("PRC_3702", "");
			obj.put("PRC_3706", "");
			obj.put("PRC_3704", "");
			obj.put("PRC_3705", "");
			obj.put("PRC_3707", "");
			obj.put("YSJ_P1", "");
			obj.put("YSJ_T1", "");
			obj.put("YSJ_P2", "");
			obj.put("YSJ_T2", "");
			obj2.put("FI_1_FYJ", "");
			
			firstArr.add(obj);
		}
		
		//流量--------------------------------------
		
		List<String> ZYL = new ArrayList<String>();
		List<String> SXCS = new ArrayList<String>();
		List<String> RHXCS = new ArrayList<String>();
		List<String> DTCYL = new ArrayList<String>();
		Object[] llTab = null;
		int n = 0;
		String zyl = "",dtcyl = "";
		dates = DateBean.getReport2Time("time8", rq);
		if(dataSet2 != null && dataSet2.size()>0){
			for(String[] dateList:dates){
				obj = new JSONObject();
	
				for(String data:dateList){
					llTab = null;
					dataflg = 0;
					for(Object[] ll:dataSet2){
						if(null != ll[4] && data.toString().equals(ll[4].toString())){
							dataflg = 1;
							llTab = ll;
						}
					}
					
					if(dataflg == 1){
						obj = new JSONObject(); //流量
						obj.put("RD_U1_SAGD_LIQUID_ID", llTab[5]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(llTab[0] != null){
							obj.put("ZYL", llTab[0]);
							ZYL.add(llTab[0].toString());
							if(n==5){
								zyl = llTab[0].toString();
							}
						}else{
							obj.put("ZYL", "");
							ZYL.add("");
						}
						
						if(llTab[1]!=null){
							obj.put("SXCS", llTab[1]);
							SXCS.add(llTab[1].toString());
						}else{
							obj.put("SXCS", "");
							SXCS.add("");
						}
						if(llTab[2]!=null){
							obj.put("RHXCS", llTab[2]);
							RHXCS.add(llTab[2].toString());
						}else{
							obj.put("RHXCS", "");
							RHXCS.add("");
						}
						if(llTab[3]!=null){
							obj.put("DTCYL", llTab[3]);
							DTCYL.add(llTab[3].toString());
							if(n==5){
								dtcyl = llTab[3].toString();
							}
						}else{
							obj.put("DTCYL", "");
							DTCYL.add("");
						}
						
					}else{
						obj = new JSONObject(); 
						obj.put("RD_U1_SAGD_LIQUID_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("ZYL", "");
						obj.put("SXCS", "");
						obj.put("RHXCS", "");
						obj.put("DTCYL", "");
						
						ZYL.add("");
						SXCS.add("");
						RHXCS.add("");
						DTCYL.add("");
					}
					llArr.add(obj);
					n++;
				}
			}
		}else{
			for(String[] dateList:dates){
				for(String data:dateList){
					obj.put("RD_U1_SAGD_LIQUID_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("ZYL", "");
					obj.put("SXCS", "");
					obj.put("RHXCS", "");
					obj.put("DTCYL", "");
					
					llArr.add(obj);
				}
			}
		}
		if(dataSet2!=null){
			obj = new JSONObject();
			obj.put("ZYL16", CommonsUtil.getLLSumData(ZYL,0,3));
			obj.put("ZYL20", CommonsUtil.getLLSumData(ZYL,1,4));
			obj.put("ZYL8", CommonsUtil.getLLSumData(ZYL,2,5));
			obj.put("SXCS16", CommonsUtil.getLLSumData(SXCS,0,3));
			obj.put("SXCS20", CommonsUtil.getLLSumData(SXCS,1,4));
			obj.put("SXCS8", CommonsUtil.getLLSumData(SXCS,2,5));
			obj.put("RHXCS16", CommonsUtil.getLLSumData(RHXCS,0,3));
			obj.put("RHXCS20", CommonsUtil.getLLSumData(RHXCS,1,4));
			obj.put("RHXCS8", CommonsUtil.getLLSumData(RHXCS,2,5));
			obj.put("DTCYL16", CommonsUtil.getLLSumData(DTCYL,0,3));
			obj.put("DTCYL20", CommonsUtil.getLLSumData(DTCYL,1,4));
			obj.put("DTCYL8", CommonsUtil.getLLSumData(DTCYL,2,5));
			llArr.add(obj);
		}
		
		//浓度信息 ----------------------------------
		String RCCYL = "",YCLJ = "",ZXPRJ = "";
		int m = 0;
		Object[] ndTab = null;
		String beforetime = DateBean.getBeforeDAYTime(rq);
		String[][] days = {{beforetime,rq}};
		if(dataSet3 != null && dataSet3.size()>0){
			for(String[] dateList:days){
				obj = new JSONObject();
	
				for(String data:dateList){
					ndTab = null;
					dataflg = 0;
					for(Object[] nd:dataSet3){
						if(null != nd[6] && data.toString().equals(nd[6].toString())){
							dataflg = 1;
							ndTab = nd;
						}
					}
					
					if(dataflg == 1){
						obj = new JSONObject(); 
						obj.put("RD_U1_SAGD_GENERAL_ID", ndTab[7]);
						obj.put("REPORT_TIME", data);
						if(ndTab[0]!=null){
							obj.put("RCCYL", ndTab[0]);
							if(m==1)
								RCCYL = ndTab[0].toString();
						}else{
							obj.put("RCCYL", "");
						}
						if(ndTab[1]!=null){
							obj.put("YCLJ", ndTab[1]);
							if(m==1)
								YCLJ = ndTab[1].toString();
						}else{
							obj.put("YCLJ", "");
						}
						if(ndTab[2]!=null){
							obj.put("ZXPRJ", ndTab[2]);
							if(m==1)
								ZXPRJ = ndTab[2].toString();
						}else{
							obj.put("ZXPRJ", "");
						}
						if(ndTab[3]!=null)
							obj.put("ZBR", ndTab[3]);
						else
							obj.put("ZBR", "");
						if(ndTab[4]!=null)
							obj.put("SHR", ndTab[4]);
						else
							obj.put("SHR", "");
						if(ndTab[5]!=null)
							obj.put("REMARK", ndTab[5]);
						else
							obj.put("REMARK", "");
						
						if(ndTab[8]!=null)
							obj.put("ZYGZ", ndTab[8]);
						else
							obj.put("ZYGZ", "");
					}else{
						obj = new JSONObject(); 
						obj.put("RD_U1_SAGD_GENERAL_ID", "");
						obj.put("REPORT_TIME", data);
						obj.put("RCCYL", "");
						obj.put("YCLJ", "");
						obj.put("ZXPRJ", "");
						obj.put("ZBR", "");
						obj.put("SHR", "");
						obj.put("REMARK", "");
						obj.put("ZYGZ", "");
						
					}
					nongduArr.add(obj);
					m++;
				}
			}
		}else{
			for(String[] dateList:days){
				for(String data:dateList){
					obj = new JSONObject(); 
					obj.put("RD_U1_SAGD_GENERAL_ID", "");
					obj.put("REPORT_TIME", data);
					obj.put("RCCYL", "");
					obj.put("YCLJ", "");
					obj.put("ZXPRJ", "");
					obj.put("ZBR", "");
					obj.put("SHR", "");
					obj.put("REMARK", "");
					obj.put("ZYGZ", "");
					
					nongduArr.add(obj);
				}
			}
		}
		
		obj = new JSONObject();
		obj.put("RCCYL_ND", CommonsUtil.getNDSumData(RCCYL,dtcyl));
		obj.put("YCLJ_ND", CommonsUtil.getNDSumData(YCLJ,zyl));
		obj.put("ZXPRJ_ND", CommonsUtil.getNDSumData(ZXPRJ,zyl));
		nongduArr.add(obj);
		
		
		
		List<String> YJCSLJ = new ArrayList<String>();		
		List<String> RHXCSLJ = new ArrayList<String>();		
		List<String> RHX1JYLJ = new ArrayList<String>();		
		List<String> RHX2JYLJ = new ArrayList<String>();		
		List<String> RHX3JYLJ = new ArrayList<String>();		
		List<String> DTCYLJ = new ArrayList<String>();		
		List<String> XXLLJLJ = new ArrayList<String>();		
		List<String> CCLLJ = new ArrayList<String>();	
		Object[] csTab = null;
		// dataSet1 
		dates = DateBean.getReportTime("time4", rq);
		if(dataSet1 != null && dataSet1.size()>0){
			for(String[] dateList:dates){
				obj = new JSONObject();
	
				for(String data:dateList){
					csTab = null;
					dataflg = 0;
					for(Object[] cs:dataSet1){
						if(null != cs[14] && data.toString().equals(cs[14].toString())){
							dataflg = 1;
							csTab = cs;
						}
					}
					
					
					if(dataflg == 1){
						obj = new JSONObject(); 
						obj.put("RD_U1_SAGD_PIVOTAL_ID", csTab[15]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(csTab[0] != null){
							obj.put("YJCSLJ", csTab[0]);
							YJCSLJ.add(csTab[0].toString());
						}else{
							obj.put("YJCSLJ", "");
							YJCSLJ.add("");
						}
						if(csTab[1]!=null){
							obj.put("RHXCSLJ", csTab[1]);
							RHXCSLJ.add(csTab[1].toString());
						}else{
							obj.put("RHXCSLJ", "");
							RHXCSLJ.add("");
						}
						if(csTab[2]!=null){
							obj.put("RHX1JYLJ", csTab[2]);
							RHX1JYLJ.add(csTab[2].toString());
						}else{
							obj.put("RHX1JYLJ", "");
							RHX1JYLJ.add("");
						}
						if(csTab[3]!=null){
							obj.put("RHX2JYLJ", csTab[3]);
							RHX2JYLJ.add(csTab[3].toString());
						}else{
							obj.put("RHX2JYLJ", "");
							RHX2JYLJ.add("");
						}
						if(csTab[4]!=null){
							obj.put("RHX3JYLJ", csTab[4]);
							RHX3JYLJ.add(csTab[4].toString());
						}else{
							obj.put("RHX3JYLJ", "");
							RHX3JYLJ.add("");
						}
						if(csTab[5]!=null){
							obj.put("ZCSL", csTab[5]);
						}else{
							obj.put("ZCSL", "");
						}
						if(csTab[6]!=null){
							obj.put("BHL", csTab[6]);
						}else{
							obj.put("BHL", "");
						}
						if(csTab[7]!=null){
							obj.put("DTCYLJ", csTab[7]);
							DTCYLJ.add(csTab[7].toString());
						}else{
							obj.put("DTCYLJ", "");
							DTCYLJ.add("");
						}
						if(csTab[8]!=null){
							obj.put("DTCYLL", csTab[8]);
						}else{
							obj.put("DTCYLL", "");
						}
						
						if(csTab[9]!=null){
							obj.put("XXLLJLJ", csTab[9]);
							XXLLJLJ.add(csTab[9].toString());
						}else{
							obj.put("XXLLJLJ", "");
							XXLLJLJ.add("");
						}
						//obj.put("REPORT_TIME", data.substring(11, 16));
	
						if(csTab[10]!=null){
							obj.put("XXLLJLL", csTab[10]);
						}else{
							obj.put("XXLLJLL", "");
						}
						if(csTab[11]!=null){
							obj.put("CCLLJ", csTab[11]);
							CCLLJ.add(csTab[11].toString());
						}else{
							obj.put("CCLLJ", "");
							CCLLJ.add("");
						}
						
						if(csTab[12]!=null){
							obj.put("SXCSHY", csTab[12]);
						}else{
							obj.put("SXCSHY", "");
						}
						
						if(csTab[13]!=null){
							obj.put("DTCSHY", CommonsUtil.format2pData(csTab[13].toString()));
						}else{
							obj.put("DTCSHY", "");
						}
						
						
					}else{
						obj = new JSONObject(); 
						obj.put("RD_U1_SAGD_PIVOTAL_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("YJCSLJ", "");
						obj.put("RHXCSLJ", "");
						obj.put("RHX1JYLJ", "");
						obj.put("RHX2JYLJ", "");
						obj.put("RHX3JYLJ", "");
						obj.put("ZCSL", "");
						obj.put("BHL", "");
						obj.put("DTCYLJ", "");
						obj.put("DTCYLL", "");
						obj.put("XXLLJLJ", "");
						obj.put("XXLLJLL", "");
						obj.put("CCLLJ", "");
						obj.put("SXCSHY", "");
						obj.put("DTCSHY", "");
						
						YJCSLJ.add("");
						RHXCSLJ.add("");
						RHX1JYLJ.add("");
						RHX2JYLJ.add("");
						RHX3JYLJ.add("");
						DTCYLJ.add("");
						XXLLJLJ.add("");
						CCLLJ.add("");
					}
					ThirdArr .add(obj);
				}
			}
		}else{
			for(String[] dateList:dates){
				for(String data:dateList){
					obj = new JSONObject(); 
					obj.put("RD_U1_SAGD_PIVOTAL_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("YJCSLJ", "");
					obj.put("RHXCSLJ", "");
					obj.put("RHX1JYLJ", "");
					obj.put("RHX2JYLJ", "");
					obj.put("RHX3JYLJ", "");
					obj.put("ZCSL", "");
					obj.put("BHL", "");
					obj.put("DTCYLJ", "");
					obj.put("DTCYLL", "");
					obj.put("XXLLJLJ", "");
					obj.put("XXLLJLL", "");
					obj.put("CCLLJ", "");
					obj.put("SXCSHY", "");
					obj.put("DTCSHY", "");
					
					ThirdArr .add(obj);
				}
			}
		}
		
		if(dataSet1!=null){
			obj = new JSONObject(); 
			obj.put("REPORT_TIME", "合计");
			obj.put("YJCSLJ", CommonsUtil.getPivotalSumData(YJCSLJ));
			obj.put("RHXCSLJ", CommonsUtil.getPivotalSumData(RHXCSLJ));
			obj.put("RHX1JYLJ", CommonsUtil.getPivotalSumData(RHX1JYLJ));
			obj.put("RHX2JYLJ", CommonsUtil.getPivotalSumData(RHX2JYLJ));
			obj.put("RHX3JYLJ", CommonsUtil.getPivotalSumData(RHX3JYLJ));
			int B13 = 0,D13 = 0;
			if(!CommonsUtil.getPivotalSumData(YJCSLJ).equals("")){
				B13 = Integer.parseInt(CommonsUtil.getPivotalSumData(YJCSLJ));
			}
			if(!CommonsUtil.getPivotalSumData(RHXCSLJ).equals("")){
				D13 = Integer.parseInt(CommonsUtil.getPivotalSumData(RHXCSLJ));
			}
			obj.put("ZCSL", B13+D13);
			obj.put("BHL", "");
			obj.put("DTCYLJ", CommonsUtil.getPivotalSumData(DTCYLJ));
			obj.put("DTCYLL", "");
			obj.put("XXLLJLJ", CommonsUtil.getPivotalSumData(XXLLJLJ));
			obj.put("XXLLJLL", "");
			obj.put("CCLLJ", CommonsUtil.getPivotalSumData(CCLLJ));
			
			ThirdArr .add(obj);
		}
		
		obj = new JSONObject();
		obj.put("firstArr", firstArr);
		obj.put("secondArr", secondArr);
		obj.put("thirdArr", ThirdArr);
		obj.put("nongduArr", nongduArr);
		obj.put("llArr", llArr);
		
		return obj;
		
	}

@Override
public List<PcRpdU1SagdPivotalT> searchU1SagdPivotal(String id, String rq)
		throws Exception {
		String hql = " FROM PcRpdU1SagdPivotalT t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			hql +=" and t.rpdU1SagdPivotalId = '"+id+"'";
		}
		
		if(rq != null && !"".equals(rq)){
			hql +=" and t.rq = TO_DATE('"+rq+"','YYYY-MM-DD')";
		}
		return mbzhrbDao.searchU1SagdPivotal(hql);
}

public int searchCalcNum() {
	// TODO Auto-generated method stub
	String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
	String calcNum = "-16";
	List<Object[]> dataSet = mbzhrbDao.searchCalcNum(timeCalssql);
	if(dataSet != null && dataSet.size()>0){
		calcNum = dataSet.get(0) + "";
	}
	return Integer.parseInt(calcNum);
}

@Override
public boolean updatePivotal(PcRpdU1SagdPivotalT pivotalRec)
		throws Exception {
	// TODO Auto-generated method stub
	return mbzhrbDao.updatePivotal(pivotalRec);
}

@Override
public List<PcRpdU1SagdAutoT> searchU1SagdAuto(String id, String rq)
		throws Exception {
	// TODO Auto-generated method stub
	String hql = " FROM PcRpdU1SagdAutoT t WHERE 1=1 ";
	if(id != null && !"".equals(id)){
		hql +=" and t.rpdU1SagdAutoId = '"+id+"'";
	}
	
	if(rq != null && !"".equals(rq)){
		hql +=" and t.rq = TO_DATE('"+rq+"','YYYY-MM-DD')";
	}
	return mbzhrbDao.searchU1SagdAuto(hql);
}

@Override
public boolean updateAuto(PcRpdU1SagdAutoT autoRec) throws Exception {
	// TODO Auto-generated method stub
	return mbzhrbDao.updateAuto(autoRec);
}

@Override
public List<PcRpdU1SagdGeneralT> SreachGeneral(String id, String rq)
		throws Exception {
	// TODO Auto-generated method stub
	String hql = " FROM PcRpdU1SagdGeneralT t WHERE 1=1 ";
	if(id != null && !"".equals(id)){
		hql +=" and t.rpdU1SagdGeneralId = '"+id+"'";
	}
	
	if(rq != null && !"".equals(rq)){
		hql +=" and t.rq = TO_DATE('"+rq+"','YYYY-MM-DD')";
	}
	return mbzhrbDao.SreachGeneral(hql);
}

@Override
public boolean updateGeneral(PcRpdU1SagdGeneralT sagdGen)throws Exception {
	// TODO Auto-generated method stub
	return mbzhrbDao.updateGen(sagdGen);
}

@Override
public List<PcRpdU1SagdLiquidT> SreachLiquid(String id, String rq)
		throws Exception {
	// TODO Auto-generated method stub
	String hql = " FROM PcRpdU1SagdLiquidT t WHERE 1=1 ";
	if(id != null && !"".equals(id)){
		hql +=" and t.rpdU1SagdLiquidId = '"+id+"'";
	}
	
	if(rq != null && !"".equals(rq)){
		hql +=" and t.rq = TO_DATE('"+rq+"','YYYY-MM-DD')";
	}
	return mbzhrbDao.SreachLiuqid(hql);
}

@Override
public boolean updateLiq(PcRpdU1SagdLiquidT sagdliq) throws Exception {
	// TODO Auto-generated method stub
	return mbzhrbDao.updateLiq(sagdliq);
}

@Override
public List<Object[]> searchExportData(String rq, String sql, String dateField,
		String talbelFalg) throws Exception {
	// TODO Auto-generated method stub
	String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = mbzhrbDao.searchObjectList(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		sql += " where " + dateField + " between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by " + dateField;
		if ("PC_RPD_U_THIN_WATER_GENERAL_T".equals(talbelFalg)) {
			sql = " where " + dateField + " between TO_DATE('" + stratime.substring(0, 10) + "','YYYY-MM-DD') and TO_DATE('" + endtime.substring(0, 10) + "','YYYY-MM-DD')";
		}
		List<Object[]> yyList = mbzhrbDao.searchObjectList(sql);
		return yyList;
}

@Override
public List<Object[]> searchExportOther(String rq, String sql, String dateField, String talbelFalg) throws Exception{
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = mbzhrbDao.searchObjectList(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		sql += " where " + dateField + " between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by " + dateField;
		if ("PC_RPD_U_THIN_WATER_GENERAL_T".equals(talbelFalg)) {
			sql = " where " + dateField + " between TO_DATE('" + stratime.substring(0, 10) + "','YYYY-MM-DD') and TO_DATE('" + endtime.substring(0, 10) + "','YYYY-MM-DD')";
		}
		List<Object[]> yyList = mbzhrbDao.searchObjectList(sql);
		return yyList;
	}

@SuppressWarnings("unused")
@Override
public List<Object[]> searchExportDataTime(String rq, String sql,String dateField, String talbelFalg) throws Exception {
	String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
	String calcNum = "-16";
	List<Object[]> dataSet = mbzhrbDao.searchObjectList(timeCalssql);
	if(dataSet != null && dataSet.size()>0){
		calcNum = dataSet.get(0) + "";
	}
	String stratime =DateBean.getBefore2DAYTime(rq);
	String midtime = DateBean.getBeforeDAYTime(rq);
	sql += " where " + dateField + " = TO_DATE('" + stratime + " 16:00:00','YYYY-MM-DD HH24:MI:SS') OR "+dateField+" = TO_DATE('" + stratime + " 20:00:00','YYYY-MM-DD HH24:MI:SS') OR " 
			+dateField +" =  TO_DATE('"+midtime+" 08:00:00','YYYY-MM-DD HH24:mi:ss') OR " + dateField +" =  TO_DATE('"+midtime+" 16:00:00','YYYY-MM-DD HH24:mi:ss') OR " + dateField +" =  TO_DATE('"+midtime+" 20:00:00','YYYY-MM-DD HH24:mi:ss') OR "
			+dateField +" =  TO_DATE('"+rq+" 08:00:00','YYYY-MM-DD HH24:mi:ss') order by " + dateField;
	List<Object[]> yyList = mbzhrbDao.searchObjectList(sql);
	return yyList;
}

	public List<Object[]> searchExportDataDay(String rq, String sql,String dateField, String talbelFalg) throws Exception {

		String stratime =DateBean.getBeforeDAYTime(rq);
		sql += " where " + dateField + " between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+rq+"','YYYY-MM-DD HH24:mi:ss') order by " + dateField;
	
		List<Object[]> yyList = mbzhrbDao.searchObjectList(sql);
		return yyList;
	}
}
