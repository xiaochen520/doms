package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.GasWellRPDDao;
import com.echo.dto.PcRpdGasWelldT;
import com.echo.service.GasWellRPDService;

public class GasWellRPDServiceImpl implements GasWellRPDService{
	private GasWellRPDDao gaswellrpddao;

	public void setGaswellrpddao(GasWellRPDDao gaswellrpddao) {
		this.gaswellrpddao = gaswellrpddao;
	}
	@Override
	public HashMap<String, Object> searchGWRPD(String oil, String areablock1,String blockstationname1,String rulewellname1, String startDate, String endDate, String totalNumFlag,
			int pageNo, String sort, String order, int rowsPerpage) {
		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_rpd_gaswh_v  a where 1=1 " +
							" and a.report_date between TO_DATE('"+startDate+"','YYYY-MM-DD') and TO_DATE('"+endDate+"','YYYY-MM-DD')";
		String totalNum = "select count(*) ";
		if(!oil.equals("")&&oil!=null&&!oil.equals("undefined") ){
			formTable=formTable+" and a.oilname ='"+oil+"'";
		}
		if(!areablock1.equals("")&&areablock1!=null&&!areablock1.equals("undefined") ){
			formTable=formTable+" and a.qkmc ='"+areablock1+"'";
		}
		if(!blockstationname1.equals("")&&blockstationname1!=null&&!blockstationname1.equals("undefined") ){
			formTable=formTable+" and a.qzname ='"+blockstationname1+"'";
		}
		if(!rulewellname1.equals("")&&rulewellname1!=null&&!rulewellname1.equals("undefined") ){
			formTable=formTable+" and a.well_name ='"+rulewellname1+"'";
		}
		String[] cloumnsName ={"well_name","oilname","qzname","qkmc","REPORT_DATE","TUBING_PRES","WELL_TEMP","INSTANTANEOUS_DELIVERY","CUMULATIVE_DISCHARGE","DALIY_CUMULATIVE_FLOW","GAS_WELLDID"};
		String kk="well_name";
		for(int m=1;m<cloumnsName.length;m++){
			if("REPORT_DATE".equals(cloumnsName[m])){
				kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD') as REPORT_DATE";
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		if ("export".equals(totalNumFlag)) {
		String[] cloumnsNames2 = {"well_name","REPORT_DATE","oilname","qzname","qkmc","TUBING_PRES","WELL_TEMP","INSTANTANEOUS_DELIVERY","CUMULATIVE_DISCHARGE","DALIY_CUMULATIVE_FLOW"  };

			kk="well_name";
			for(int m=1;m<cloumnsNames2.length;m++){
				if("REPORT_DATE".equals(cloumnsNames2[m])){
					kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD') as REPORT_DATE";
				}else{
					kk=kk+","+cloumnsNames2[m];
				}
			}
					
		}
		//	 product = cloums + kk+formTable;
		//}
		String product = cloums + kk  + formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = gaswellrpddao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"REPORT_DATE".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					product +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			product +=" order by REPORT_DATE desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = gaswellrpddao.searchGWRPDE(product);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = gaswellrpddao.searchGWRPD(product,start,rowsPerpage,cloumnsName);
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
	//↓	

	@Override
	public JSONObject searchGasWelRPDWH(String gasName, String startDate,String endStart, int pageNo, String sort, String order,
			int rowsPerpage) throws Exception {

		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select  * ";
		String formTable = "from pc_cd_area_info_v t where 1=1";
		String totalNum = "select count(*)";
		if(!gasName.equals("")&&gasName!=null){
			formTable=formTable+" and t.qkmc = '"+gasName+"'";
		}


		String boilersInfo = cloums + formTable;
		 String[] cloumnsName = {"QKID","ZONE_CODE","QKMC","QKMC_S","ZONE_NAME","ORG_ID"};
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = gaswellrpddao.getCounts(totalNum);
		}
		//排序
		if(!"".equals(sort) && !"QKMC".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by t." + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(t.QKMC, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = gaswellrpddao.searchGWRPD(boilersInfo,start,rowsPerpage,cloumnsName);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
//				if (o[0] != null) {
//					o[0] = String.valueOf(o[0]).substring(0,10);
//				}
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null) {
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
		return tjo;
	
}

	public String searchWelID(String welName) {
		String sql = "select a.gas_wellid  from  pc_cd_gas_well_t a where a.well_name= '"+welName+"'";
		List<Object[]> lo =  new ArrayList<Object[]>();
		lo = gaswellrpddao.searchWelID(sql);
		Object wellId= lo.get(0);
		return (String) wellId;
	}

	@Override
	public boolean saveOrupdateGasRPDWH(PcRpdGasWelldT wh) throws Exception {
		return gaswellrpddao.saveOrupdateGasRPDWH(wh);
	}

	@Override
	public List<PcRpdGasWelldT> searchOnly(String gasWelldid, String wellName,
			String reportDate) throws Exception {
		List<PcRpdGasWelldT> list = null;
		
		String hql = "from PcRpdGasWelldT a where 1=1";
		if(gasWelldid !=null && !"".equals(gasWelldid)){
			hql += " and a.gasWelldid  = '"+gasWelldid+"'";
		}
		if(wellName !=null && !"".equals(wellName)){
			hql += " and a.gaswellid  = '"+wellName+"'";
		}
		if(reportDate !=null && !"".equals(reportDate)){
			hql += " and  reportDate = TO_DATE('"+reportDate+"','YYYY-MM-DD ') ";
		}
		list = gaswellrpddao.searchOnly(hql);
		return list;
	}

	@Override
	public boolean removeGasRPDWH(String whId) throws Exception {
		String sql = "delete from pc_rpd_gas_welld_t a  where a.gas_welldid='"+whId+"'";
		return gaswellrpddao.removeGasRPDWH(sql);
	}
	
}
