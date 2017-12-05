package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dto.PcRpdU1SagdAutoT;
import com.echo.dto.PcRpdU1SagdGeneralT;
import com.echo.dto.PcRpdU1SagdLiquidT;
import com.echo.dto.PcRpdU1SagdPivotalT;
import com.echo.dto.PcRpdUThinOilAutoT;
import com.echo.dto.PcRpdUThinOilGeneralT;
import com.echo.service.XyyyclService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;

public class XyyyclServiceImpl implements XyyyclService{
	private CommonDao commonDao;
	


	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}




	@SuppressWarnings("unchecked")
	@Override
	public JSONObject searchZHRB(String rq) throws Exception {
		
		String nowtime = DateBean.getSystemTime().substring(0, 10);
		if(rq != null && !"".equals(rq)){
			
		}else{
			rq = nowtime;
		}
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dateNum = commonDao.searchEverySql(timeCalssql);
		if(dateNum != null && dateNum.size()>0){
			calcNum = dateNum.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		
		PropertiesConfig pc = new PropertiesConfig();
		String XYYYCLSQL1 = pc.getSystemConfiguration("XYYYCLSQL1");
		String XYYYCLSQL2 = pc.getSystemConfiguration("XYYYCLSQL2");
		
		String sql = XYYYCLSQL1;
//			"select RPD_U_THIN_OIL_AUTO_ID,to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS') as BBSJ,LIT1201,LIT1202,LIT1203,LIT1204,LIT1205,LIT1206,LIT1207,LIT1208,LIT1209,LIT1210,LIT1601,LIT1101,LIT1102,LIT1103,PT1101,FLQYL,TE1101,W51LYWD,W52LYWD,TE1103,MT1402,SEI1201,MT1401,SEO1201,"+
//					 "PT1303,TE1301,TE1302,PT1301,PT1302,TES1304,TES1301,TES1302,TES1404,TES1401,TES1402,WEH_FT1,WEHXFWLJ,WEH_HS1,WEHYL,WEH_FT2,XZJXFWLJ,WEH_HS2,XZJYL,FIT11103,FIT11103_Q,FIT1601,W33BSXFWLJ,"+
//					 "FIT1101,FIT1101_Q,FIT1301,FIT1301_Q ";
		
		
		//日期 
//		sql +="  from PC_RPD_U_THIN_OIL_AUTO_T  where 1=1 ";
		sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ ";
		JSONObject obj = new JSONObject();
		List<Object[]> dataSet = commonDao.searchEverySql(sql);
		
//		sql = "select * from PC_RPD_U_THIN_OIL_GENERAL_T where 1=1 " +
		sql =	XYYYCLSQL2+	" and BBRQ = TO_DATE('"+rq+"','YYYY-MM-DD') ";

		List<Object[]> dataSet1 = commonDao.searchEverySql(sql);
		if(dataSet1 == null || dataSet1.size() == 0){
			List<String> returnmsg = commonDao.getProcedures("p_gene_daily_u_thin_oil_g",rq);
			
			if("1".equals(returnmsg.get(0)) || returnmsg.get(1).indexOf("日报数据，不能再次生成") != -1){
				dataSet1 = commonDao.searchEverySql(sql);
			}else{
				
				
			}
		}
		
		obj = getTableDataJson(dataSet,dataSet1, rq);

		return obj;
	}
	
	
	
	
	public JSONObject getTableDataJson(List<Object[]> dataSet,List<Object[]> dataSet1,String rq){
	
		JSONObject obj = new JSONObject();
		JSONArray firstArr = new JSONArray(); 
		JSONArray secondArr = new JSONArray();
		JSONArray ThirdArr = new JSONArray();
		JSONArray fourArr = new JSONArray();
		JSONArray fineArr = new JSONArray();
		Object[] objflg = null;
		
		
		String[][] dates = DateBean.getReportTime("time1", rq);
		if(dates != null && dates.length >0){
			for(String[] dateList:dates){
				for(int i = 0 ; i < dateList.length; i++){
					obj = new JSONObject();
					if(dataSet != null && dataSet.size()>0){
						objflg = null;
						for(int j=0; j<dataSet.size(); j ++){
							if(dateList[i].toString().equals(dataSet.get(j)[1].toString())){
								objflg = dataSet.get(j);
							}
							
						}
						if(objflg != null){
							obj.put("RPD_U_THIN_OIL_AUTO_ID", objflg[0]);
							obj.put("BBSJ", dateList[i].toString().substring(11, 16));
							obj.put("LIT1201", CommonsUtil.getClearNullData(objflg[2]));
							obj.put("LIT1202", CommonsUtil.getClearNullData(objflg[3]));
							obj.put("LIT1203", CommonsUtil.getClearNullData(objflg[4]));
							obj.put("LIT1204", CommonsUtil.getClearNullData(objflg[5]));
							obj.put("LIT1205", CommonsUtil.getClearNullData(objflg[6]));
							obj.put("LIT1206", CommonsUtil.getClearNullData(objflg[7]));
							obj.put("LIT1207", CommonsUtil.getClearNullData(objflg[8]));
							obj.put("LIT1208", CommonsUtil.getClearNullData(objflg[9]));
							obj.put("LIT1209", CommonsUtil.getClearNullData(objflg[10]));
							obj.put("LIT1210", CommonsUtil.getClearNullData(objflg[11]));
							obj.put("LIT1601", CommonsUtil.getClearNullData(objflg[12]));
							obj.put("LIT1101", CommonsUtil.getClearNullData(objflg[13]));
							obj.put("LIT1102", CommonsUtil.getClearNullData(objflg[14]));
							obj.put("LIT1103", CommonsUtil.getClearNullData(objflg[15]));
							obj.put("PT1101", CommonsUtil.getClearNullData(objflg[16]));
							obj.put("FLQYL", CommonsUtil.getClearNullData(objflg[17]));
							obj.put("TE1101", CommonsUtil.getClearNullData(objflg[18]));
							obj.put("W51LYWD", CommonsUtil.getClearNullData(objflg[19]));
							obj.put("W52LYWD", CommonsUtil.getClearNullData(objflg[20]));
							obj.put("TE1103", CommonsUtil.getClearNullData(objflg[21]));
							obj.put("MT1402", CommonsUtil.getClearNullData(objflg[22]));
							obj.put("SEI1201", CommonsUtil.getClearNullData(objflg[23]));
							obj.put("MT1401", CommonsUtil.getClearNullData(objflg[24]));
							obj.put("SEO1201", CommonsUtil.getClearNullData(objflg[25]));
							firstArr.add(obj);


							obj = new JSONObject();
							obj.put("PT1303", CommonsUtil.getClearNullData(objflg[26]));
							obj.put("TE1301", CommonsUtil.getClearNullData(objflg[27]));
							obj.put("TE1302", CommonsUtil.getClearNullData(objflg[28]));
							obj.put("PT1301", CommonsUtil.getClearNullData(objflg[29]));
							obj.put("PT1302", CommonsUtil.getClearNullData(objflg[30]));
							obj.put("TES1304", CommonsUtil.getNotTwoData1(objflg[31]));
							obj.put("TES1301", CommonsUtil.getClearNullData(objflg[32]));
							obj.put("TES1302", CommonsUtil.getClearNullData(objflg[33]));
							obj.put("TES1404", CommonsUtil.getClearNullData(objflg[34]));
							obj.put("TES1401", CommonsUtil.getClearNullData(objflg[35]));
							obj.put("TES1402", CommonsUtil.getClearNullData(objflg[36]));
							obj.put("WEH_FT1", CommonsUtil.getClearNullData(objflg[37]));
							obj.put("WEHXFWLJ", CommonsUtil.getClearNullData(objflg[38]));
							obj.put("WEH_HS1", CommonsUtil.getClearNullData(objflg[39]));
							obj.put("WEHYL", CommonsUtil.getClearNullData(objflg[40]));
							obj.put("WEH_FT2", CommonsUtil.getClearNullData(objflg[41]));
							obj.put("XZJXFWLJ", CommonsUtil.getClearNullData(objflg[42]));
							obj.put("WEH_HS2", CommonsUtil.getClearNullData(objflg[43]));
							obj.put("XZJYL", CommonsUtil.getClearNullData(objflg[44]));
							obj.put("FIT11103", CommonsUtil.getClearNullData(objflg[45]));
							obj.put("FIT11103_Q", CommonsUtil.getClearNullData(objflg[46]));
							obj.put("FIT1601", CommonsUtil.getClearNullData(objflg[47]));
							obj.put("W33BSXFWLJ", CommonsUtil.getClearNullData(objflg[48]));
							secondArr.add(obj);
							obj = new JSONObject();
							obj.put("FIT1101", CommonsUtil.getClearNullData(objflg[49]));
							obj.put("FIT1101_Q", CommonsUtil.getClearNullData(objflg[50]));
							obj.put("FIT1301", CommonsUtil.getClearNullData(objflg[51]));
							obj.put("FIT1301_Q", CommonsUtil.getClearNullData(objflg[52]));
							ThirdArr.add(obj);
							
						}else{
							obj.put("RPD_U_THIN_OIL_AUTO_ID", "");
							obj.put("BBSJ", dateList[i].toString().substring(11, 16));
							obj.put("LIT1201", "");
							obj.put("LIT1202", "");
							obj.put("LIT1203", "");
							obj.put("LIT1204", "");
							obj.put("LIT1205", "");
							obj.put("LIT1206", "");
							obj.put("LIT1207", "");
							obj.put("LIT1208", "");
							obj.put("LIT1209", "");
							obj.put("LIT1210", "");
							obj.put("LIT1601", "");
							obj.put("LIT1101", "");
							obj.put("LIT1102", "");
							obj.put("LIT1103", "");
							obj.put("PT1101", "");
							obj.put("FLQYL", "");
							obj.put("TE1101", "");
							obj.put("W51LYWD", "");
							obj.put("W52LYWD", "");
							obj.put("TE1103", "");
							obj.put("MT1402", "");
							obj.put("SEI1201", "");
							obj.put("MT1401", "");
							obj.put("SEO1201", "");
							firstArr.add(obj);
							
							obj = new JSONObject();
							obj.put("PT1303", "");
							obj.put("TE1301", "");
							obj.put("TE1302", "");
							obj.put("PT1301", "");
							obj.put("PT1302", "");
							obj.put("TES1304", "");
							obj.put("TES1301", "");
							obj.put("TES1302", "");
							obj.put("TES1404", "");
							obj.put("TES1401", "");
							obj.put("TES1402", "");
							obj.put("WEH_FT1", "");
							obj.put("WEHXFWLJ", "");
							obj.put("WEH_HS1", "");
							obj.put("WEHYL", "");
							obj.put("WEH_FT2", "");
							obj.put("XZJXFWLJ", "");
							obj.put("WEH_HS2", "");
							obj.put("XZJYL", "");
							obj.put("FIT11103", "");
							obj.put("FIT11103_Q", "");
							obj.put("FIT1601", "");
							obj.put("W33BSXFWLJ", "");
							secondArr.add(obj);
							obj = new JSONObject();
							obj.put("FIT1101", "");
							obj.put("FIT1101_Q", "");
							obj.put("FIT1301", "");
							obj.put("FIT1301_Q", "");
							ThirdArr.add(obj);
							
						}
						
					}else{
						
						obj.put("RPD_U_THIN_OIL_AUTO_ID", "");
						obj.put("BBSJ", dateList[i].toString().substring(11, 16));
						obj.put("LIT1201", "");
						obj.put("LIT1202", "");
						obj.put("LIT1203", "");
						obj.put("LIT1204", "");
						obj.put("LIT1205", "");
						obj.put("LIT1206", "");
						obj.put("LIT1207", "");
						obj.put("LIT1208", "");
						obj.put("LIT1209", "");
						obj.put("LIT1210", "");
						obj.put("LIT1601", "");
						obj.put("LIT1101", "");
						obj.put("LIT1102", "");
						obj.put("LIT1103", "");
						obj.put("PT1101", "");
						obj.put("FLQYL", "");
						obj.put("TE1101", "");
						obj.put("W51LYWD", "");
						obj.put("W52LYWD", "");
						obj.put("TE1103", "");
						obj.put("MT1402", "");
						obj.put("SEI1201", "");
						obj.put("MT1401", "");
						obj.put("SEO1201", "");
						firstArr.add(obj);
						
						
						obj = new JSONObject();
						obj.put("PT1303", "");
						obj.put("TE1301", "");
						obj.put("TE1302", "");
						obj.put("PT1301", "");
						obj.put("PT1302", "");
						obj.put("TES1304", "");
						obj.put("TES1301", "");
						obj.put("TES1302", "");
						obj.put("TES1404", "");
						obj.put("TES1401", "");
						obj.put("TES1402", "");
						obj.put("WEH_FT1", "");
						obj.put("WEHXFWLJ", "");
						obj.put("WEH_HS1", "");
						obj.put("WEHYL", "");
						obj.put("WEH_FT2", "");
						obj.put("XZJXFWLJ", "");
						obj.put("WEH_HS2", "");
						obj.put("XZJYL", "");
						obj.put("FIT11103", "");
						obj.put("FIT11103_Q", "");
						obj.put("FIT1601", "");
						obj.put("W33BSXFWLJ", "");
						secondArr.add(obj);
						obj = new JSONObject();
						obj.put("FIT1101", "");
						obj.put("FIT1101_Q", "");
						obj.put("FIT1301", "");
						obj.put("FIT1301_Q", "");
						ThirdArr.add(obj);
					}
					
				}
			}
			
		}
		
		String startTime ="";
		String endTime ="";
		Object FIRWEHXFWLJ = null;   //---
		Object LASWEHXFWLJ = null;
		
		Object FIRXZJXFWLJ = null;    //--
		Object LASXZJXFWLJ = null;
		
		Object FIRFIT11103_Q = null;
		Object LASFIT11103_Q = null;
		
		Object FIRW33BSXFWLJ = null;
		Object LASW33BSXFWLJ = null;
		
		
		Object FIRFIT1101_Q = null;
		Object LASFIT1101_Q = null;
		
		Object FIRFIT1301_Q = null;
		Object LASFIT1301_Q = null;
		
		String SUMWEHYL = "";
		String SUMXZJYL = "";
		//日累计  第四组计算
		obj = new JSONObject();
		if(dataSet != null && dataSet.size()>0){
			startTime = dates[0][0].toString();
			endTime = dates[0][dates[0].length-1].toString();
			for(int i = 0; i<dataSet.size(); i++){
				
				if(startTime.equals(dataSet.get(i)[1].toString())){
					FIRWEHXFWLJ = dataSet.get(i)[38];
					FIRXZJXFWLJ = dataSet.get(i)[42];
					FIRFIT11103_Q = dataSet.get(i)[46];
					FIRW33BSXFWLJ = dataSet.get(i)[48];
					
					FIRFIT1101_Q = dataSet.get(i)[50];
					FIRFIT1301_Q = dataSet.get(i)[52];
				}
				
				if(endTime.equals(dataSet.get(i)[1].toString())){
					LASWEHXFWLJ = dataSet.get(i)[38];
					LASXZJXFWLJ = dataSet.get(i)[42];
					LASFIT11103_Q = dataSet.get(i)[46];
					LASW33BSXFWLJ = dataSet.get(i)[48];
					
					LASFIT1101_Q = dataSet.get(i)[50];
					LASFIT1301_Q = dataSet.get(i)[52];
					
					SUMWEHYL= CommonsUtil.getClearNullData(dataSet.get(i)[40]);
					SUMXZJYL = CommonsUtil.getClearNullData(dataSet.get(i)[44]);
				}
				
//				SUMWEHYL = CommonsUtil.getSUMData(SUMWEHYL, dataSet.get(i)[40]);   //---
//				SUMXZJYL = CommonsUtil.getSUMData(SUMXZJYL, dataSet.get(i)[44]);  //--
			}
			//obj.put("WEHXFWLJ", CommonsUtil.getClearNullData(objflg[38]));
//			obj.put("WEHXFWLJSUM", CommonsUtil.getRegulation2(LASWEHXFWLJ, FIRWEHXFWLJ));
			obj.put("WEHXFWLJSUM", CommonsUtil.getClearNullData(LASWEHXFWLJ));
//			obj.put("WEHYLSUM", SUMWEHYL);
			obj.put("WEHYLSUM", SUMWEHYL);
			obj.put("XZJYLSUM", SUMXZJYL);
//			obj.put("XZJXFWLJSUM", CommonsUtil.getRegulation2(LASXZJXFWLJ, FIRXZJXFWLJ));
			obj.put("XZJXFWLJSUM", CommonsUtil.getClearNullData(LASXZJXFWLJ));
//			obj.put("XZJYLSUM", SUMXZJYL);
			obj.put("FIT11103_QSUM", CommonsUtil.getRegulation2(LASFIT11103_Q, FIRFIT11103_Q));
			obj.put("W33BSXFWLJSUM", CommonsUtil.getRegulation2(LASW33BSXFWLJ, FIRW33BSXFWLJ));
			
			obj.put("FIT1101_QSUM", CommonsUtil.getRegulation2(LASFIT1101_Q, FIRFIT1101_Q));
			obj.put("FIT1301_QSUM", CommonsUtil.getRegulation2(LASFIT1301_Q, FIRFIT1301_Q));
			fourArr.add(obj);
			
		}else{
			obj.put("WEHXFWLJSUM", "");
			obj.put("WEHYLSUM", "");
			obj.put("XZJXFWLJSUM", "");
			obj.put("XZJYLSUM", "");
			obj.put("FIT11103_QSUM", "");
			obj.put("W33BSXFWLJSUM", "");
			fourArr.add(obj);
		}
		obj = new JSONObject();
		if(dataSet1 != null && dataSet1.size()>0){
			obj.put("RPD_U_THIN_OIL_GENERAL_ID", dataSet1.get(0)[0]);
			obj.put("BBRQ", DateBean.getChinaDate(rq));
			obj.put("BBRQ1", rq);
			obj.put("YDJYL", CommonsUtil.getClearNullData(dataSet1.get(0)[2]));
			obj.put("EDJYL", CommonsUtil.getClearNullData(dataSet1.get(0)[3]));
			obj.put("ZYQL", CommonsUtil.getClearNullData(dataSet1.get(0)[4]));
			obj.put("EZYW", CommonsUtil.getClearNullData(dataSet1.get(0)[5]));
			obj.put("EZCQ", CommonsUtil.getClearNullData(dataSet1.get(0)[6]));
			obj.put("XYCS", CommonsUtil.getClearNullData(dataSet1.get(0)[7]));
			obj.put("XZJYW", CommonsUtil.getClearNullData(dataSet1.get(0)[8]));
			obj.put("XZJBSQ", CommonsUtil.getClearNullData(dataSet1.get(0)[9]));
			obj.put("XZJGQ", CommonsUtil.getClearNullData(dataSet1.get(0)[10]));
			obj.put("JYL", CommonsUtil.getClearNullData(dataSet1.get(0)[11]));
			obj.put("WEHJQL", CommonsUtil.getClearNullData(dataSet1.get(0)[12]));
			obj.put("XZJJQL", CommonsUtil.getClearNullData(dataSet1.get(0)[13]));
			obj.put("ZBR", CommonsUtil.getClearNullData(dataSet1.get(0)[14]));
			obj.put("SHR", CommonsUtil.getClearNullData(dataSet1.get(0)[15]));
//			obj.put("SHSJ", CommonsUtil.getClearNullData(dataSet1.get(0)[16]));
//			obj.put("XGR", CommonsUtil.getClearNullData(dataSet1.get(0)[17]));
//			obj.put("XGSJ", CommonsUtil.getClearNullData(dataSet1.get(0)[18]));
			obj.put("BZ", CommonsUtil.getClearNullData(dataSet1.get(0)[16]));
			fineArr.add(obj);
			
		}else{
			obj.put("RPD_U_THIN_OIL_GENERAL_ID", "");
			obj.put("BBRQ", DateBean.getChinaDate(rq));
			obj.put("BBRQ1", rq);
			obj.put("YDJYL", "");
			obj.put("EDJYL", "");
			obj.put("ZYQL", "");
			obj.put("EZYW", "");
			obj.put("EZCQ", "");
			obj.put("XYCS", "");
			obj.put("XZJYW", "");
			obj.put("XZJBSQ", "");
			obj.put("XZJGQ", "");
			obj.put("JYL", "");
			obj.put("WEHJQL", "");
			obj.put("XZJJQL", "");
			obj.put("ZBR", "");
			obj.put("SHR", "");
			obj.put("SHSJ", "");
			obj.put("XGR", "");
			obj.put("XGSJ", "");
			obj.put("BZ", "");
			fineArr.add(obj);
		}
		
		obj = new JSONObject();
		obj.put("firstArr", firstArr);
		obj.put("secondArr", secondArr);
		obj.put("thirdArr", ThirdArr);
		obj.put("fourArr", fourArr);
		obj.put("fineArr", fineArr);
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
		return null;
//		mbzhrbDao.searchU1SagdPivotal(hql);
}

public int searchCalcNum() {
	// TODO Auto-generated method stub
	String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
	String calcNum = "-16";
	List<Object[]> dataSet = null;
//	mbzhrbDao.searchCalcNum(timeCalssql);
	if(dataSet != null && dataSet.size()>0){
		calcNum = dataSet.get(0) + "";
	}
	return Integer.parseInt(calcNum);
}

@Override
public boolean updatePivotal(PcRpdU1SagdPivotalT pivotalRec)
		throws Exception {
	// TODO Auto-generated method stub
	return true;
//	mbzhrbDao.updatePivotal(pivotalRec);
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
	return null;
//	numbzhrbDao.searchU1SagdAuto(hql);
}

@Override
public boolean updateAuto(PcRpdU1SagdAutoT autoRec) throws Exception {
	// TODO Auto-generated method stub
	return true;
//	mbzhrbDao.updateAuto(autoRec);
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
	return null;
//	mbzhrbDao.SreachGeneral(hql);
}

@Override
public boolean updateGeneral(PcRpdU1SagdGeneralT sagdGen)throws Exception {
	// TODO Auto-generated method stub
	return true;
//	mbzhrbDao.updateGen(sagdGen);
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
	return null;
//	mbzhrbDao.SreachLiuqid(hql);
}

@Override
public boolean updateLiq(PcRpdU1SagdLiquidT sagdliq) throws Exception {
	// TODO Auto-generated method stub
	return true;
//	mbzhrbDao.updateLiq(sagdliq);
}

@Override
public List<List<Object[]>> searchExportData(String rq) throws Exception {
	List<List<Object[]>> lists = new ArrayList<List<Object[]>>();
	String nowtime = DateBean.getSystemTime().substring(0, 10);
	if(rq != null && !"".equals(rq)){
		
	}else{
		rq = nowtime;
	}
	String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
	String calcNum = "-16";
	List<Object[]> dateNum = commonDao.searchEverySql(timeCalssql);
	if(dateNum != null && dateNum.size()>0){
		calcNum = dateNum.get(0) + "";
	}
	
	String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
	String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
	
	PropertiesConfig pc = new PropertiesConfig();
	String XYYYCLSQL1 = pc.getSystemConfiguration("XYYYCLSQL1");
	String XYYYCLSQL2 = pc.getSystemConfiguration("XYYYCLSQL2");
	
	String sql = XYYYCLSQL1;
	//日期 
	sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ ";
	List<Object[]> dataSet = commonDao.searchEverySql(sql);
	
	sql =	XYYYCLSQL2+	" and BBRQ = TO_DATE('"+rq+"','YYYY-MM-DD') ";

	List<Object[]> dataSet1 = commonDao.searchEverySql(sql);
	if(dataSet1 == null || dataSet1.size() == 0){
		List<String> returnmsg = commonDao.getProcedures("p_gene_daily_u_thin_oil_g",rq);
		
		if("1".equals(returnmsg.get(0)) || returnmsg.get(1).indexOf("日报数据，不能再次生成") != -1){
			dataSet1 = commonDao.searchEverySql(sql);
		}else{
			
			
		}
	}
		lists.add(dataSet);
		lists.add(dataSet1);
		return lists;
}

@Override
public List<Object[]> searchExportOther(String rq, String sql, String dateField, String talbelFalg) throws Exception{
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = commonDao.searchEverySql(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		sql += " where " + dateField + " between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by " + dateField;
		if ("PC_RPD_U_THIN_WATER_GENERAL_T".equals(talbelFalg)) {
			sql = " where " + dateField + " between TO_DATE('" + stratime.substring(0, 10) + "','YYYY-MM-DD') and TO_DATE('" + endtime.substring(0, 10) + "','YYYY-MM-DD')";
		}
		List<Object[]> yyList = commonDao.searchEverySql(sql);
		return yyList;
	}

@Override
public List<Object[]> searchExportDataTime(String rq, String sql,String dateField, String talbelFalg) throws Exception {
	String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
	String calcNum = "-16";
	List<Object[]> dataSet = commonDao.searchEverySql(timeCalssql);
	if(dataSet != null && dataSet.size()>0){
		calcNum = dataSet.get(0) + "";
	}
	String stratime =DateBean.getBefore2DAYTime(rq);
	sql += " where " + dateField + " between TO_DATE('" + stratime + " 16:00:00','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+rq+" 08:00:00','YYYY-MM-DD HH24:mi:ss') order by " + dateField;

	List<Object[]> yyList = commonDao.searchEverySql(sql);
	return yyList;
}

	public List<Object[]> searchExportDataDay(String rq, String sql,String dateField, String talbelFalg) throws Exception {

		String stratime =DateBean.getBeforeDAYTime(rq);
		sql += " where " + dateField + " between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+rq+"','YYYY-MM-DD HH24:mi:ss') order by " + dateField;
	
		List<Object[]> yyList = commonDao.searchEverySql(sql);
		return yyList;
	}



	// 原油处理
	@Override
	public List<PcRpdUThinOilGeneralT> SreachOilGeneral(String zhId, String date)throws Exception {
		String hql = "from PcRpdUThinOilGeneralT a where 1=1";
		if(zhId !=null && !"".equals(zhId)){
			hql +=  " and a.rpdUThinOilGeneralId = '"+zhId+"' ";
		}
		if(date !=null && !"".equals(date)){
			hql +=" and a.bbrq = '"+date+"'";
		}
		return commonDao.SreachOilGeneral(hql);
	}
	// 原油处理
	@Override
	public boolean updateOilGeneral(PcRpdUThinOilGeneralT oil) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.updateOilGeneral(oil);
	}

	@Override
	public List<PcRpdUThinOilAutoT> SreachAutp(String id, String name)throws Exception {
		String hql = " from  PcRpdUThinOilAutoT a  where 1=1";
		if(id !=null && !"".equals(id)){
			hql += " and  a.rpdUThinOilAutoId = '"+id+"' ";
		}
		return commonDao.SreachAutp(hql);
	}

	@Override
	public boolean updateAuto(PcRpdUThinOilAutoT auto) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.updateAuto(auto);
	}
}
