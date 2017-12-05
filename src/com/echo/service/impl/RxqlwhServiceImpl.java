package com.echo.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.PageDao;
import com.echo.dao.RxqlwhDao;
import com.echo.dto.PcProWellHotWashing;
import com.echo.dto.PcProWellStimDaily;
import com.echo.service.RxqlwhService;
import com.echo.util.DateBean;

public class RxqlwhServiceImpl  implements RxqlwhService{
	private RxqlwhDao rxqlwhDao;
	private  CommonDao commonDao;
	private PageDao pageDao;


	public void setRxqlwhDao(RxqlwhDao rxqlwhDao) {
		this.rxqlwhDao = rxqlwhDao;
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

		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = "  from PC_PRO_WELL_HOT_WASHING_V where 1=1 ";
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
		formTable=formTable+" and event_date between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS')";
		
		//oil_station_name  team_station_name oil_satation_id team_satation_id
		String[] cloumnsName = {"WELLNAME","EVENT_DATE","PARAFFIN_STIM_TYPE","WHTYPE","XRFSWH","HOT_WASH_TYPE","PARAFFIN_MELT_TEMP","PARAFFIN_MELT_VOL","REMARK","RLAST_OPER","RLAST_ODATE",
								"ORG_ID","HOTWASH_PARAFFIN_ID"};
		String kk="WELLNAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("EVENT_DATE".equals(cloumnsName[m])){
				kk=kk+","+"to_char(EVENT_DATE,'YYYY-MM-DD HH24:MI:SS') as EVENT_DATE"; 
			} else  if("RLAST_ODATE".equals(cloumnsName[m])){
				kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE"; 
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsName2 = {"WELLNAME","EVENT_DATE","WHTYPE","XRFSWH","PARAFFIN_MELT_TEMP","PARAFFIN_MELT_VOL","REMARK"};
			
			 kk="WELLNAME";
			for(int n=1;n<cloumnsName2.length;n++){
				if("EVENT_DATE".equals(cloumnsName2[n])){
					kk=kk+","+"to_char(EVENT_DATE,'YYYY-MM-DD HH24:MI:SS') as EVENT_DATE"; 
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
			lo = rxqlwhDao.searchDataEX(MosaicSql);
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
	public boolean removeDatas(String flowing_paraffin_id) throws Exception {
		String  sql ="delete  from  PC_PRO_WELL_HOT_WASHING a  where a.hotwash_paraffin_id = '"+flowing_paraffin_id+"'";
		return commonDao.removeData(sql);
	}

	@Override
	public List<Object[]> searchDatas(String sql) {
		
		return commonDao.searchEverySql(sql);
	}


	@Override
	public List<PcProWellHotWashing> searchCheck(String hotWashId,
			String orgId, String wELLNAME, String eventDate, String stimCode) {
		String hql = " from PcProWellHotWashing a where 1=1";
		if(hotWashId !=null && !"".equals(hotWashId)){
			hql += " and a.hotwashParaffinId = '"+hotWashId+"'";
		}
		if(orgId !=null && !"".equals(orgId)){
			hql += " and a.orgId = '"+orgId+"'";
		}
		if(wELLNAME !=null && !"".equals(wELLNAME)){
			hql += " and a.wellId = '"+wELLNAME+"'";
		}
		if(eventDate !=null && !"".equals(eventDate)){
			hql += " and a.eventDate = to_date('"+eventDate+"' ,'yyyy-MM-dd hh24:mi:ss') ";
		}
		if(stimCode !=null && !"".equals(stimCode)){
			hql += " and a.paraffinStimType = '"+stimCode+"'";
		}
		List<?> list = commonDao.searchClassQuery(hql);
		return (List<PcProWellHotWashing>) list;
	}

	@Override
	public boolean saveOrUpdate(List<PcProWellHotWashing> hotList) throws Exception {
		return commonDao.addOrUpdateDatas(hotList);
	}

	@Override
	public JSONArray searchHOT_WASH_TYPE() throws Exception {
		JSONObject  obj = null;
		JSONArray arr = null;
		String sql ="select  b.code,b.p_description  from pc_pk_heat_wahing_type b  order by  nlssort(b.code,'NLS_SORT=SCHINESE_STROKE_M')";
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

	
}
