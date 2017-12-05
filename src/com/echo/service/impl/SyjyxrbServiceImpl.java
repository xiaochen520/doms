package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dao.SyjyxrbDao;
import com.echo.dto.PcRpdWaterSourceWelldT;
import com.echo.service.SearchQueryAllService;
import com.echo.service.SyjyxrbService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;

public class SyjyxrbServiceImpl implements SyjyxrbService{
	private  SyjyxrbDao syjyxrbDao;
	private CommonDao commonDao;
	public void setSyjyxrbDao(SyjyxrbDao syjyxrbDao) {
		this.syjyxrbDao = syjyxrbDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public JSONArray searchSyjyxrb(String txtDate) throws Exception {
		
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
	
		PropertiesConfig pc = new PropertiesConfig();
		String[] syjName = pc.getSystemConfiguration("syjName").split(",");
		String[] syjId = pc.getSystemConfiguration("syjId").split(",");
		

		int flagValue = 0;//数据存在表示 0：不存在； 1： 存在
		Object[] tableValue = null;

	String nowdate = DateBean.getSystemTime();
		
			String stime = txtDate + " 00:00:00";
			
			String etime = txtDate + " 23:59:59";
		
			for (int i = 0; i < syjName.length; i++) {
				String name = syjName[i];

				String id = syjId[i];
			obj = new JSONObject();
			tableValue = null;
			
			String sql ="        select a.watersource_welldid ,a.water_source_wellid, a.INSTANTANEOUS_DELIVERY,a.CUMULATIVE_DISCHARGE,a.PRES,LIQUID_LEVEL,remark  from  pc_rpd_water_source_welld_t a where 1=1";
			sql += " and a.REPORT_DATE between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') and a.water_source_wellid ='"+id+"'";
					//"and  a.water_source_wellrid  ='9154AC82ECD64B568F07F248A2AD33F5'";

			List<Object[]> dataList = null;
			dataList =commonDao.searchEverySql(sql);
			if(dataList !=null && dataList.size()>0){
				obj = new JSONObject();
				obj.put("syjName", name);
				obj.put("id", id);
				if(dataList.get(0)[0] !=null){
					obj.put("rpdid", dataList.get(0)[0].toString());
				}else{
					obj.put("rpdid", "");
				}
				if(dataList.get(0)[2] !=null){
					obj.put("INSTANTANEOUS_DELIVERY", CommonsUtil.getNotOneData(dataList.get(0)[2].toString()));
				}else{
					obj.put("INSTANTANEOUS_DELIVERY", "");
				}
				if(dataList.get(0)[3] !=null){
					obj.put("TAC_FLOW", CommonsUtil.getNotOneData(dataList.get(0)[3].toString()));
				}else{
					obj.put("TAC_FLOW", "");
				}
				if(dataList.get(0)[4] !=null){
					obj.put("PRES", CommonsUtil.getNotTwoData(dataList.get(0)[4].toString()));
				}else{
					obj.put("PRES", "");
				}
				if(dataList.get(0)[5] !=null){
					obj.put("LIQUID_LEVEL", CommonsUtil.getNotTwoData(dataList.get(0)[5].toString()));
				}else{
					obj.put("LIQUID_LEVEL", "");
				}
				if(dataList.get(0)[6] !=null){
					obj.put("REMARK",dataList.get(0)[6].toString());
				}else{
					obj.put("REMARK", "");
				}
			}else{

				obj = new JSONObject();
				obj.put("syjName", name);
				
					obj.put("id", id);
					obj.put("rpdid", "");
					obj.put("INSTANTANEOUS_DELIVERY", "");
				
					obj.put("TAC_FLOW", "");
				
					obj.put("PRES", "");
				
					obj.put("LIQUID_LEVEL", "");
				
					obj.put("REMARK", "");
			
			}
			arr.add(obj);
			//break;
		}
//		}

		//arr.add(obj);
		return arr;
	}

	@Override
	public List<Object[]> searchDataEXcep(String txtDate, String fields,String jid) {
		String sql = "select " + fields + " from pc_rpd_water_source_welld_t u where u.report_date = TO_DATE('"+txtDate+"','YYYY-MM-DD') and u.water_source_wellid ='"+jid+"'";
		List<Object[]> yyList =commonDao.searchEverySql(sql);
		return yyList;
	}

	@Override
	public List<PcRpdWaterSourceWelldT> SreachSYJRBid(String id, String Jid) {
		String hql = " from  PcRpdWaterSourceWelldT a  where 1=1";
		if(id !=null && !"".equals(id) ){
			hql += "  and  a.watersourceWelldid ='"+id+"'";
		} 
		if(Jid !=null && !"".equals(Jid) ){
			hql += "  and  a.waterSourceWellid ='"+Jid+"'";
		}
		List<?>  list = null;
		list = commonDao.searchClassQuery(hql);
		return (List<PcRpdWaterSourceWelldT>) list;
	}

	@Override
	public boolean updateData(PcRpdWaterSourceWelldT wt)
			throws Exception {
		return syjyxrbDao.addOrUpdateDatas(wt);
	}

	@Override
	public List<Object[]> searchNotNull(String sqlNo) {
		return commonDao.searchEverySql(sqlNo);
	}
	
}
