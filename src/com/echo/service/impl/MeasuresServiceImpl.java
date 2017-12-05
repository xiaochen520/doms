package com.echo.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.MeasuresDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcProWellStimDaily;
import com.echo.service.MeasuresService;
import com.echo.util.DateBean;

public class MeasuresServiceImpl  implements MeasuresService{
	private MeasuresDao measuresDao;
	private  CommonDao commonDao;
	private PageDao pageDao;
	public void setMeasuresDao(MeasuresDao measuresDao) {
		this.measuresDao = measuresDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	public HashMap<String, Object> searchData(String oilName, String groupTeam,
			String wellname, String stime, String etime, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumFlag,
			String deptype) throws Exception {

		
		

		//15 的时候查询A表
//		String  tableView = "";
//		if("15".equals(deptype)){
//			tableView= "pc_rpd_gas_daily_v";
//		}else{
//			tableView= "pc_rpd_gas_dailyb_v";
//		}
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = "  from pc_rpd_measure_v where 1=1 ";
							//"and start_datetime between TO_DATE('"+stime+"','yyyy-MM-dd hh24:mi') and TO_DATE('"+etime+"','yyyy-MM-dd hh24:mi') ";
		String totalNum = "select count(*) ";
		if(!oilName.equals("")&&oilName!=null&&!oilName.equals("undefined")&!oilName.equals("全部")){
			formTable=formTable+" and oil_station_name='"+oilName+"'";
		}
		if(!groupTeam.equals("")&&groupTeam!=null&&!groupTeam.equals("undefined")){
			formTable=formTable+" and team_station_name='"+groupTeam+"'";
		}
		if(!wellname.equals("")&&wellname!=null&&!wellname.equals("undefined")){
			formTable=formTable+" and WELLNAME like '%"+wellname+"%'";
		}
//		if(!stime.equals("")&&stime!=null&&!stime.equals("undefined")){
//			formTable=formTable+" and start_datetime =to_date('"+stime+"','yyyy-MM-dd hh24:mi')";
//		}
//		if(!etime.equals("")&&etime!=null&&!etime.equals("undefined")){
//			formTable=formTable+" and end_datetime =to_date('"+etime+"','yyyy-MM-dd hh24:mi')";
//		}
	String nowdate = DateBean.getSystemTime();
		
		if("".equals(stime)){
			stime = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			stime = stime + " 00:00:00";
		}
		if("".equals(etime)){
			etime = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			etime = etime + " 23:59:59";
		}
		
//		startDate = DateBean.getParamTime(startDate);
		formTable=formTable+" and START_DATETIME between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS')";
		
		//oil_station_name  team_station_name oil_satation_id team_satation_id
		String[] cloumnsName = {"WELLNAME","START_DATETIME","END_DATETIME","PKCODE","P_DESCRIPTION","KEY_DOWN_TAG","REMARK","RLAST_OPER","RLAST_ODATE",
								"ORG_ID","MEASURE_ID"};
		String kk="WELLNAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("START_DATETIME".equals(cloumnsName[m])){
				kk=kk+","+"to_char(START_DATETIME,'YYYY-MM-DD HH24:MI:SS') as START_DATETIME"; 
			} else if("END_DATETIME".equals(cloumnsName[m])){
				kk=kk+","+"to_char(END_DATETIME,'YYYY-MM-DD HH24:MI:SS') as END_DATETIME"; 
			} else  if("RLAST_ODATE".equals(cloumnsName[m])){
				kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE"; 
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsName2 = {"WELLNAME","START_DATETIME","END_DATETIME","P_DESCRIPTION","KEY_DOWN_TAG","REMARK"};
			
			 kk="WELLNAME";
			for(int n=1;n<cloumnsName2.length;n++){
				if("START_DATETIME".equals(cloumnsName2[n])){
					kk=kk+","+"to_char(START_DATETIME,'YYYY-MM-DD hh24:mi:ss') as START_DATETIME"; 
				} else if("END_DATETIME".equals(cloumnsName2[n])){
					kk=kk+","+"to_char(END_DATETIME,'YYYY-MM-DD hh24:mi:ss') as END_DATETIME"; 
				}else{ 
					kk=kk+","+cloumnsName2[n];
				} 
			
			}
		}
		String MosaicSql = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = pageDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"WELLNAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					MosaicSql +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			MosaicSql +=" order by WELLNAME ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = measuresDao.searchDataEX(MosaicSql);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(MosaicSql,start,rowsPerpage,cloumnsName);
		}

			if (lo != null && 0 < lo.size()) {
				// 生成树节点json文档
				jsonArr = new JSONArray();
				for (Object[] o : lo) {
					tjo = new JSONObject();
	
					for (int i = 0; i < o.length; i++) {
						if (o[i] == null||o[i].equals("null")) {
							o[i] = "";
						}
						tjo.put(cloumnsName[i], o[i].toString());
						
					}
					jsonArr.add(tjo);
				}
			}
		
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	
	
	}

	@Override
	public JSONArray searchStmiuType() throws Exception {
		JSONObject  obj = null;
		JSONArray arr = null;
		String sql ="select  a.stimu_code,a.p_description  from  pc_pk_stimu_type_t a  order by  nlssort(a.stimu_code,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = null;
			dataSet = commonDao.searchEverySql(sql);
			if(dataSet !=null && dataSet.size()>0){
				arr = new JSONArray();
				for (Object[] entry : dataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					arr.add(obj);
				}
				return arr;
			}
			arr = new JSONArray();
			obj = new JSONObject();
			obj.put("text", "");
			obj.put("id", "");
			arr.add(obj);
		return arr;
	}

	@Override
	public List<PcProWellStimDaily> serarchCheckOnly(String mEASUREID,String orgId, String wellName,
			String START_DATETIME,String stmiuCode) throws Exception {
		String hql = " from PcProWellStimDaily a where 1=1";
		if(mEASUREID !=null && !"".equals(mEASUREID)){
			hql += " and a.measureId = '"+mEASUREID+"'";
		}
		if(orgId !=null && !"".equals(orgId)){
			hql += " and a.orgId = '"+orgId+"'";
		}
		if(wellName !=null && !"".equals(wellName)){
			hql += " and a.wellId = '"+wellName+"'";
		}
		if(START_DATETIME !=null && !"".equals(START_DATETIME)){
			hql += " and a.startDatetime = to_date('"+START_DATETIME+"' ,'yyyy-MM-dd hh24:mi:ss') ";
		}
		if(stmiuCode !=null && !"".equals(stmiuCode)){
			hql += " and a.stimCode = '"+stmiuCode+"'";
		}
		List<?>  list = commonDao.searchClassQuery(hql);
		return (List<PcProWellStimDaily>) list;
	}

	@Override
	public boolean saveOrUpdate(List<PcProWellStimDaily> stimDList)throws Exception {
		return commonDao.addOrUpdateDatas(stimDList);
	}

	@Override
	public List<Object[]> searchCOnly(String pDESCRIPTION) throws Exception {
		String sql  ="select  a.stimu_code  from  pc_pk_stimu_type_t a  where a.p_description='"+pDESCRIPTION+"'";
		List<Object[]> list = commonDao.searchEverySql(sql);
		return list;
	}

	@Override
	public String searchRem(String pDESCRIPTION) {
		String sql  ="select  a.stimu_code  from  pc_pk_stimu_type_t a  where a.p_description='"+pDESCRIPTION+"'";
		List<Object[]> list = commonDao.searchEverySql(sql);
		Object id = list.get(0);
		return (String) id;
	}

	@Override
	public boolean removeDatas(String mEASUREID) throws Exception {
		String  sql ="delete  from  pc_pro_well_stim_daily a  where a.measure_id = '"+mEASUREID+"'";
		return commonDao.removeData(sql);
	}

	@Override
	public String searchOrd_Id(String wELLNAME) {
		String sql=" select  a.org_id from  pc_cd_orgall_v  a  where a.well_name='"+wELLNAME+"'";
		List<Object[]>  listO = commonDao.searchEverySql(sql);
		Object org_Id = listO.get(0);
		return (String) org_Id;
	}
	
}
