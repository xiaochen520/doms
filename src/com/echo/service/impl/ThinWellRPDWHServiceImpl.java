package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.PageDao;
import com.echo.dao.ThinWellRPDWHDao;
import com.echo.dto.PcRpdRuleWellProdbT;
import com.echo.dto.PcRpdThinWellwT;
import com.echo.dto.PcRpdThinWellwbT;
import com.echo.dto.PcRpdWaterfloodingWellT;
import com.echo.service.ThinWellRPDWHService;
import com.echo.util.CommonsUtil;

public class ThinWellRPDWHServiceImpl implements ThinWellRPDWHService{
	private ThinWellRPDWHDao thinWellRPDWHDao;
	private  CommonDao commonDao;
	private PageDao pageDao;
	
	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setThinWellRPDWHDao(ThinWellRPDWHDao thinWellRPDWHDao) {
		this.thinWellRPDWHDao = thinWellRPDWHDao;
	}

	@Override
	public HashMap<String, Object> searchData(String oilName,String groupTeam, String maniName,
			String wellName, String stime, String etime, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumFlag,String deptype) {
		//15 的时候查询A表
		String  tableView = "";
		if("15".equals(deptype)){
			tableView= "pc_rpd_thin_wellw_v";
		}else{
			tableView= "pc_rpd_thin_wellwB_v";
		}
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = "  from "+tableView+" where 1=1 "+
							"and report_date between TO_DATE('"+stime+"','YYYY-MM-DD') and TO_DATE('"+etime+"','YYYY-MM-DD') ";
		String totalNum = "select count(*) ";
		if(!oilName.equals("")&&oilName!=null&&!oilName.equals("undefined")&!oilName.equals("全部")){
			formTable=formTable+" and oilname='"+oilName+"'";
		}
		if(!groupTeam.equals("")&&groupTeam!=null&&!groupTeam.equals("undefined")){
			formTable=formTable+" and team='"+groupTeam+"'";
		}

		if(!maniName.equals("")&&maniName!=null&&!maniName.equals("undefined")){
			formTable=formTable+" and maniname='"+maniName+"'";
		}
		if(!wellName.equals("")&&wellName!=null&&!wellName.equals("undefined")){
			formTable=formTable+" and well_name like '%"+wellName+"%'";
		}
		String[] cloumnsName = null;
		if ("export".equals(totalNumFlag)) {
			cloumnsName = new String[]{"well_name","report_date","oilname","team","maniname","prott","stroke","at_times","nozzle",
					"pressure","tcpr","backpre","tot","nfv","gasp","sampli","pumping_time","pumping_machine",
					"pumping_description","remark"};
		}else{
			cloumnsName = new String[]{"well_name","oilname","maniname","team","report_date","prott","stroke","at_times","nozzle",
					"pressure","tcpr","backpre","tot","nfv","gasp","sampli","pumping_time","pumping_machine",
					"pumping_description","remark","liquid_flag","class_check_oper","class_check_date",
					"geology_check_oper","geology_check_date","rlast_oper","rlast_odate","thinwellrpd","wellid"};
		}
		
		String kk="well_name";
		for(int m=1;m<cloumnsName.length;m++){
			if("report_date".equals(cloumnsName[m])){
				kk=kk+","+"to_char(report_date,'YYYY-MM-DD') as report_date"; 
			}else  if("class_check_date".equals(cloumnsName[m])){
				kk=kk+","+"to_char(class_check_date,'YYYY-MM-DD hh24:mi:ss') as class_check_date"; 
			} else if("geology_check_date".equals(cloumnsName[m])){
				kk=kk+","+"to_char(geology_check_date,'YYYY-MM-DD hh24:mi:ss') as geology_check_date"; 
			} else  if("rlast_odate".equals(cloumnsName[m])){
				kk=kk+","+"to_char(rlast_odate,'YYYY-MM-DD hh24:mi:ss') as rlast_odate"; 
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"report_date".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by report_date ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = thinWellRPDWHDao.searchDataEX(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
		}
		if ("export".equals(totalNumFlag)) {

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
		
		}else{
			if (lo != null && 0 < lo.size()) {
				// 生成树节点json文档
				jsonArr = new JSONArray();
				for (Object[] o : lo) {
					tjo = new JSONObject();
	
					for (int i = 0; i < o.length; i++) {
//						if(i == 13){
//							
//							if (!"".equals(CommonsUtil.getClearNullData(o[20])) ) {
//								if(CommonsUtil.getClearNullData(o[20]).equals("1")){
//									tjo.put(cloumnsName[i], "<span style='color: green;'>"+CommonsUtil.getClearNullData(o[i])+"</span>");
//									
//								}else if(CommonsUtil.getClearNullData(o[20]).toString().equals("2")){
//									tjo.put(cloumnsName[i], "<span style='color: purple;'>"+CommonsUtil.getClearNullData(o[i])+"</span>");
//								}else if(CommonsUtil.getClearNullData(o[20]).toString().equals("3")){
//									tjo.put(cloumnsName[i], "<span style='color: red;'>"+CommonsUtil.getClearNullData(o[i])+"</span>");
//									
//								}else{
//									tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
//								}
//							}else{
//								tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
//							}
//						}else{
							tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
//						}
						
							
						
					}
					jsonArr.add(tjo);
				}
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
	public List<PcRpdThinWellwT> searchRpdId(String wellNameEdit, String rq,String THINWELLRPD) throws Exception {
		String  hql = " from PcRpdThinWellwT a where 1=1";
		if(wellNameEdit !=null && !"".equals(wellNameEdit)){
			hql +="  and a.wellid = '"+wellNameEdit+"'"; 
		}
		if(rq !=null && !"".equals(rq)){
			hql +=" and a.reportDate  = to_date('"+rq+"' , 'YYYY--MM-DD')";
		}
		if(THINWELLRPD !=null && !"".equals(THINWELLRPD)){
			hql +=" and a.thinwellrpd = '"+THINWELLRPD+"'";
		}
		List<?> list = null;
		list = commonDao.searchClassQuery(hql);
		return (List<PcRpdThinWellwT>) list;
	}

	@Override
	public boolean saveRPD(PcRpdThinWellwT rpdT) throws Exception {
		return thinWellRPDWHDao.saveRPD(rpdT);
	}

	@Override
	public boolean removeThinRPD(String rpdId,String deptype) throws Exception {
		String sql ="";
		if( "15".equals(deptype)){
			 sql = "delete  from  pc_rpd_thin_wellw_t a  where a.thinwellrpd='"+rpdId+"'";
		}else{
			 sql = "delete  from  pc_rpd_thin_wellwB_t a  where a.thinwellrpd='"+rpdId+"'";
		}
		//String sql = "delete  from  pc_rpd_thin_wellw_t a  where a.thinwellrpd='"+rpdId+"'";
		return commonDao.removeData(sql);
	}

	@Override
	public String searchWellID(String wellNameEdit) {
		String sql = "select   a.org_id from  pc_cd_thinoil_well_t a  where a.well_name = '"+wellNameEdit+"'";
		List<Object[]> objList = new ArrayList<Object[]>();
		objList = thinWellRPDWHDao.searchWellID(sql);
		Object  wellID = objList.get(0);
		return (String) wellID;
	}

	@Override
	public List<PcRpdThinWellwbT> searchRpdBId(String wellid, String rq,String rpdBId) throws Exception {
		String hql = " from PcRpdThinWellwbT a  where 1=1";
		if(wellid !=null && !"".equals(wellid)){
			hql +="  and a.wellid = '"+wellid+"'"; 
		}
		if(rq !=null && !"".equals(rq)){
			hql +=" and a.reportDate  = to_date('"+rq+"' , 'YYYY--MM-DD')";
		}
		if(rpdBId !=null && !"".equals(rpdBId)){
			hql +=" and a.thinwellrpd = '"+rpdBId+"'";
		}
		//List<?> list = null;
		//list = commonDao.searchClassQuery(hql);
		//return (List<PcRpdThinWellwbT>) list;
		return (List<PcRpdThinWellwbT>) commonDao.searchClassQuery(hql);
	}

	@Override
	public boolean saveRPDB(PcRpdThinWellwbT rpdT) throws Exception {
	
		return thinWellRPDWHDao.saveRPDB(rpdT);
	}

	@Override
	public List<String> Dataready(String proceduresName, String date,	String param) throws Exception {
		return commonDao.getProcedures(proceduresName, date, param);
	}
	
	public List<String> Automate(String proceduresName, String date,
			String userid,String param) throws Exception {
		String timeCalssql = "select * from PC_CD_TRIGGERED_PROCESS_T where 1=1 and parameter_name='"+param+"'";
		int calcNum = -16;
		List<Object[]> dateNum = commonDao.searchEverySql(timeCalssql);
		if(dateNum != null && dateNum.size()>0){
			calcNum = Integer.parseInt(String.valueOf(dateNum.get(0)[1]));
		}
		return commonDao.getProcedures(proceduresName, date,userid,calcNum);
	}
}
