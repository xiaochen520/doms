package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.BoilerlineDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdBlCellT;
import com.echo.dto.PcCdDepartmentT;
import com.echo.service.BoilerlineService;
import com.echo.util.DateBean;

public class BoilerlineServiceImpl implements BoilerlineService{
	private BoilerlineDao boilerlineDao;
	private PageDao pageDao;

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	
	public void setBoilerlineDao(BoilerlineDao boilerlineDao) {
		this.boilerlineDao = boilerlineDao;
	}


	public List<PcCdDepartmentT> searchDepartment(String department,String departmentname)
			throws Exception {
		String hql = "FROM PcCdDepartmentT d WHERE 1=1 " ;
			if(department != null && !"".equals(department)){
				hql += " and d.departmentid = '"+department+"'";
			}
			
			if(departmentname != null && !"".equals(departmentname)){
				hql += " and d.departmentName = '"+departmentname+"'";
			}
		List<PcCdDepartmentT> depList = null;
		//departmentDao.searchDep(hql);
		return depList;
	}
	
	public JSONObject seachGLJKS(String cellId) throws Exception {
		String hql = " from String p where 1=1 and p.cellId = '"+cellId+"'";
		
		
		String glsql = "select b.boiler_cell_id,b.boilerid,c.boiler_name,b.cell_id from PC_CD_BOILER_CELL_T b "+
						" left join pc_cd_bl_cell_t j on j.cell_id = b.cell_id "+
						" left join pc_cd_boiler_t c on b.boilerid = c.boilerid "+
						" where b.cell_id = '"+cellId+"' order by nlssort(c.boiler_name, 'NLS_SORT=SCHINESE_STROKE_M')";
		
		String jksql = "select a.*,c.structurename from pc_cd_well_cell_t a "+
						" left join pc_cd_bl_cell_t j on a.cell_id = j.cell_id "+
						" left join pc_cd_org_t c on a.org_id = c.org_id "+
						" where a.cell_id = '"+cellId+"' order by nlssort(c.structurename, 'NLS_SORT=SCHINESE_STROKE_M')";

		JSONObject outobj = new JSONObject();
		JSONArray glarr = null;
		JSONArray jkarr = null;
		List<Object[]> gllist =  boilerlineDao.searchObj(glsql);
		
		List<Object[]> jklist =  boilerlineDao.searchObj(jksql);
		
		if(gllist != null && gllist.size()>0){
			glarr = new JSONArray();
			JSONObject gls = null;
			for(Object[] obj :gllist){
				gls = new JSONObject();
				gls.put("id", obj[1]);
				gls.put("text", obj[2]);
				gls.put("noded", obj[0]);
				gls.put("nodkid", obj[3]);
				glarr.add(gls);
			}
			
			
		}
		outobj.put("GLDATA", glarr);
		if(jklist != null && jklist.size()>0){
			jkarr = new JSONArray();
			JSONObject jks = null;
			for(Object[] obj :jklist){
				jks = new JSONObject();
				jks.put("WELL_CELL_ID", obj[0]);
				jks.put("ORG_ID", obj[1]);
				jks.put("CELL_ID", obj[2]);
				jks.put("SPLIT_COEFFICIENT", obj[3]);
//				if(obj[3] != null){
//					
//					
//				}else{
//					jks.put("SPLIT_COEFFICIENT", "");
//				}
//				
				jks.put("STRUCTURENAME", obj[6]);
//				jks.put("P_FLOWMETERID", obj[4]);
//				jks.put("I_FLOWMETERID", obj[5]);
				jks.put("P_FLOWMETER", obj[4]);
				jks.put("I_FLOWMETER", obj[5]);
//				if(obj[4] != null){
//					jks.put("P_FLOWMETERID", obj[4]);
//					if("1".equals(obj[4].toString())){
//						
//						jks.put("P_FLOWMETER", "1号流量计");
//						
//					}else if("2".equals(obj[4].toString())){
//						jks.put("P_FLOWMETER", "2号流量计");
//					}else if("3".equals(obj[4].toString())){
//						jks.put("P_FLOWMETER", "双流量计");
//					}else{
//						jks.put("P_FLOWMETERID", obj[4]);
//						jks.put("P_FLOWMETER", obj[4]);
//					}
//					
//				}else{
//					jks.put("P_FLOWMETERID", "");
//					jks.put("P_FLOWMETER", "");
//				}

//				if(obj[5] != null){
//					jks.put("I_FLOWMETERID", obj[5]);
//					if("1".equals(obj[5].toString())){
//						
//						jks.put("I_FLOWMETER", "1号流量计");
//						
//					}else if("2".equals(obj[5].toString())){
//						jks.put("I_FLOWMETER", "2号流量计");
//					}else if("2".equals(obj[5].toString())){
//						jks.put("I_FLOWMETER", "双流量计");
//					}else{
//						jks.put("I_FLOWMETERID", obj[5]);
//						jks.put("I_FLOWMETER", obj[5]);
//					}
//				}else{
//					jks.put("I_FLOWMETERID", "");
//					jks.put("I_FLOWMETER", "");
//				}
				
				
				jkarr.add(jks);
			}
			
		}
		JSONObject obj = new JSONObject();
		obj.put("Rows", jkarr);
		obj.put("Total", jklist.size());
		 
		outobj.put("JKDATA", obj);
//		List<String> blCelllist = boilerlineDao.seachGLJKS(hql);
//		
//		if(blCelllist != null && blCelllist.size()>0){
//			arr = new JSONArray();
//			String  blCellT = blCelllist.get(0);
//			//锅炉所属路线节点
//			Set<PcCdBoilerCellT>  boilerCells = blCellT.getPcCdBoilerCellTs();
//			Iterator its = boilerCells.iterator();
//			List<PcCdBoilerCellT> boilerCellTlist = new ArrayList<PcCdBoilerCellT>();
//			while(its.hasNext()){
//				PcCdBoilerCellT bc = (PcCdBoilerCellT)its.next();
//				 //过滤获取所有用户权限
//				boilerCellTlist.add(bc);
//			 }
//			//井口所属路线节点
//			Set<PcCdWellCellT>  wellCellTs= blCellT.getPcCdWellCellTs();
//			Iterator itss = wellCellTs.iterator();
//			List<PcCdWellCellT> sclist = new ArrayList<PcCdWellCellT>();
//			while(its.hasNext()){
//				PcCdWellCellT sc = (PcCdWellCellT)itss.next();
//				 //过滤获取所有用户权限
//				sclist.add(sc);
//			 }
//		}
		
		return outobj;
	}

	public JSONObject searchBlines(String txtdate,int pageNo,String sort,String order,int rowsPerpage)
			throws Exception {
		
		//获取当前日期
		String nowtime = DateBean.getSystemTime().substring(0, 10);
		if(txtdate != null && !"".equals(txtdate)){
			
		}else{
			txtdate = nowtime;
		}
		JSONObject tjo = null;
		JSONArray jsonArr = null;
		String sqlcount = "select count(*) from  pc_cd_bl_cell_t d";
		String sql = "select  ";
		String[] cloumnsName = {"CELL_ID","SPLIT_MODEL","VALID_DATE","CELL_NAME","OWNER_ORG","REMARK","RLAST_OPER","RLAST_ODATE"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += "to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE,";
			}else{
				kk +="d."+cloumnsName[m]+",";
			}
			
		}
		kk = kk.substring(0, kk.length()-1);
		sql = sql+kk +" from  pc_cd_bl_cell_t d ";
		String where = "";
		if(txtdate != null && !"".equals(txtdate)){
			//sqlcount += " where 1=1  and  d.VALID_DATE = TO_DATE('"+txtdate+"','YYYY-MM-DD')";
			where = " where 1=1  and  d.VALID_DATE = TO_DATE('"+txtdate+"','YYYY-MM-DD')";
			
			
		}
		
		//获取总条数
		int total1= 0;
		total1 = pageDao.getCounts(sqlcount+where);
		
		if(total1 == 0){
			if(nowtime.equals(txtdate)){
				//调用存储过程 将前一天数据复制
				boolean flg = boilerlineDao.seachSystemInit();
				//生成当日的炉线信息失败
				if(flg == false){
					tjo = new JSONObject();
					tjo.put("ERRORS", -17103);
					return tjo;
				}
				
				//TODO..
				//再次获取总条数
				total1 = pageDao.getCounts(sqlcount+where);
			}
			
			
		}
		//排序
		String orderby = " order by nlssort(d.CELL_NAME, 'NLS_SORT=SCHINESE_STROKE_M')";
		if(!"".equals(sort)){
				
			orderby = "  order by d."+ sort+" "+order;
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total1);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo =  boilerlineDao.searchBlines(sql+where+orderby,start,rowsPerpage);
		
		
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if(o[i] != null){
						tjo.put(cloumnsName[i], o[i].toString());
					}else{
						tjo.put(cloumnsName[i], "");
					}
					
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total1);
		
	
		return tjo;
	}

	public boolean addDep(PcCdDepartmentT dep) throws Exception {
		return true;
		//departmentDao.save(dep);
	}

	public boolean updateDep(PcCdDepartmentT dep) throws Exception {
		boolean flag = true;
		try {
			//flag = departmentDao.updateDep(dep);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public boolean removeLX(String cell_id) throws Exception {
		String[] sqls = new String[3];
		sqls[0] = " DELETE from PC_CD_BOILER_CELL_T d where d.CELL_ID = '"+cell_id+"'";
		sqls[1] = " DELETE from PC_CD_WELL_CELL_T t where t.CELL_ID = '"+cell_id+"'";
		sqls[2] = " DELETE from PC_CD_BL_CELL_T K where k.CELL_ID = '"+cell_id+"'";
		return  boilerlineDao.removeLX(sqls);
	}
	
	public boolean removeLXchrid(String cellId) throws Exception {
		String[] sqls = new String[2];
		sqls[0] = " DELETE from PC_CD_BOILER_CELL_T d where d.CELL_ID = '"+cellId+"'";
		sqls[1] = " DELETE from PC_CD_WELL_CELL_T t where t.CELL_ID = '"+cellId+"'";
		return  boilerlineDao.removeLX(sqls);
	}
	/**
	 * 添加的锅炉ID List<String> boileridlist
	 * 添加的锅炉炉线ID List<String> boilerCellIdlist
	 * 添加的井口 List<String> wellidlist
		添加的井口炉线ID List<String> wellCellIdlist
	 */
	public boolean removeSY(List<String> boileridlist,
			List<String> boilerCellIdlist, List<String> wellidlist,
			List<String> wellCellIdlist) throws Exception {
		String[] sqls = new String[2];
		StringBuffer glstr =  new StringBuffer();
		StringBuffer glstrid =  new StringBuffer();
		StringBuffer jkstr =  new StringBuffer();
		
		StringBuffer jkstrid =  new StringBuffer();
		
		if(boilerCellIdlist != null && boilerCellIdlist.size() >0 && boileridlist != null && boileridlist.size()>0){
			for(int i = 0;i<boileridlist.size();i++){
				glstr.append("'"+boileridlist.get(i)+"',");
			}
			
			for(int i = 0;i<boilerCellIdlist.size();i++){
				glstrid.append("'"+boilerCellIdlist.get(i)+"',");
			}
			
			sqls[0] = "delete from pc_cd_boiler_cell_t f where f.boiler_cell_id in ( "+
					"	select e.boiler_cell_id from  "+
					"	( "+
					"	select * from ( "+
					"	select b.boiler_cell_id, b.boilerid  "+
					"	from pc_cd_boiler_cell_t b  "+
					"	where b.cell_id in ( "+
					"	      select c.cell_id from pc_cd_bl_cell_t c where c.valid_date = to_date(to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD')"+
					"	)  ) d  "+
					"	where  d.boilerid in ( "+
					//-- 添加的锅炉ID
					"	       "+glstr.substring(0, glstr.length()-1).toString()+
					"	) ) e "+
					"	where e.boiler_cell_id not in("+
					//-- 添加的锅炉炉线ID
					"	      "+glstrid.substring(0, glstrid.length()-1).toString()+
					"	))";
			
		}
		
		if(wellCellIdlist != null && wellCellIdlist.size() >0 && wellidlist != null && wellidlist.size()>0){
			for(int i = 0;i<wellidlist.size();i++){
				jkstr.append("'"+wellidlist.get(i)+"',");
			}
			
			for(int i = 0;i<wellCellIdlist.size();i++){
				jkstrid.append("'"+wellCellIdlist.get(i)+"',");
			}
			
			sqls[1] = "delete from pc_cd_well_cell_t f where f.well_cell_id in ( "+
					"	select e.well_cell_id from  "+
					"	( "+
					"	select * from ( "+
					"	select b.well_cell_id, b.org_id "+
					"	from pc_cd_well_cell_t b  "+
					"	where b.cell_id in ( "+
					"	      select c.cell_id from pc_cd_bl_cell_t c where c.valid_date = to_date(to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD')"+
					"	)  ) d "+
					"	where  d.org_id in ( "+
					// -- 添加的井口
					"	       "+jkstr.substring(0, jkstr.length()-1).toString()+
					"	) )e "+
					"	where e.well_cell_id not in( "+
					//-- 添加的井口炉线ID
					"	      "+jkstrid.substring(0, jkstrid.length()-1).toString()+
					"	))";
			
			
		}
		//if(boilerCellIdlist)
		//sqls[0] = " DELETE from PC_CD_BOILER_CELL_T d where d.CELL_ID = '"+cell_id+"'";
		//sqls[1] = " DELETE from PC_CD_WELL_CELL_T t where t.CELL_ID = '"+cell_id+"'";
		if((sqls[0] == null || "".equals(sqls[0]))&&(sqls[1] == null || "".equals(sqls[1]))){
			return true;
		}else{
			return  boilerlineDao.removeLX(sqls);
		}
		
	}

	public JSONArray seachSelectData() {
//		String bolierLineSql = "select distinct bc.cell_name from pc_cd_bl_cell_t bc";
		String ownerSql = "select t.org_id,t.oildrilling_station_name from PC_CD_OILDRILING_V t order by nlssort(t.oildrilling_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
//		JSONArray bolierLineArr = null;
		JSONArray ownerArr = null;
		JSONObject jsobj = null;
//		List<Object[]> bolierLineSet = boilerlineDao.searchInfo(bolierLineSql);
		List<Object[]> ownerSet = boilerlineDao.searchInfo(ownerSql);
		
		/*if(bolierLineSet != null && bolierLineSet.size()>0){
			bolierLineArr = new JSONArray();   
			for(Object[] entry : bolierLineSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				bolierLineArr.add(jsobj);
			}
		}*/
		if(ownerSet != null && ownerSet.size()>0){
			ownerArr = new JSONArray();
			for(Object[] entry : ownerSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				ownerArr.add(jsobj);
			}
			return ownerArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		ownerArr = new JSONArray();
		ownerArr.add(jsobj);
		return ownerArr;
	}


	public List<String> getSAGDFP() throws Exception {
		return boilerlineDao.getSAGDFP();
	}
	
	public boolean addOrUpdateBoilerLine(PcCdBlCellT blCell)throws Exception{
		return boilerlineDao.addOrUpdateBoilerLine(blCell);
	}
	public List<PcCdBlCellT> searchBoilerLineByName(String cellId,String name, String validDate) throws Exception{
		String hql = "From PcCdBlCellT p where 1=1 " ;
		if(cellId != null && !"".equals(cellId)){
			hql += " and p.cellId = '"+cellId+"'";
		}
		
		if(name != null && !"".equals(name)){
			hql += " and p.cellName = '"+name+"'";
		}

		if(validDate != null && !"".equals(validDate)){
			hql += " and p.validDate = to_date('" + validDate + "','yyyy-mm-dd')";
		}
		List<PcCdBlCellT> BoilerLineList = boilerlineDao.searchBoilerLineByName(hql);
		return BoilerLineList;
	}


	public boolean addBoilerLine(PcCdBlCellT blCell) throws Exception {
		return boilerlineDao.addBoilerLine(blCell);
	}


	public boolean removeAllWELLS(String jkname, String glname, String cellId)
			throws Exception {
		List<String> sqls = new ArrayList<String>();
		String jksql = "";
		String glsql = "";
		if(jkname != null && !"".equals(jkname)){
			jksql = "delete from pc_cd_well_cell_t f where f.well_cell_id in ( select distinct(well_cell_id) from  "+
			"(select w.well_cell_id,w.cell_id from pc_cd_well_cell_t w "+
			"where w.org_id in ("+jkname+")) a "+
			"left join pc_cd_bl_cell_t b  on a.cell_id = b.cell_id  "+
			"where b.valid_date = to_date(to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD'))";
		}
		
		if(jksql != null && !"".equals(jksql)){
			
			sqls.add(jksql);
		}
		
		
		if(glname != null && !"".equals(glname)){
			glsql = "delete from pc_cd_boiler_cell_t x where x.boiler_cell_id in ( select distinct(boiler_cell_id) from  "+
					"(select bo.boiler_cell_id,bo.cell_id from pc_cd_boiler_cell_t bo "+ 
					"where bo.boilerid in ("+glname+")) a "+
					"left join pc_cd_bl_cell_t b  on a.cell_id = b.cell_id "+
					"where b.valid_date = to_date(to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD'))";
			
		}
		
		if(glsql != null && !"".equals(glsql)){
			sqls.add(glsql);
		}
		if(cellId != null && !"".equals(cellId)){
			sqls.add(" DELETE from PC_CD_BOILER_CELL_T d where d.CELL_ID = '"+cellId+"'");
			sqls.add(" DELETE from PC_CD_WELL_CELL_T t where t.CELL_ID = '"+cellId+"'");
		}
		if(sqls != null && sqls.size()>0){
			return  boilerlineDao.removeLXs(sqls);
		}else{
			return true;
		}
		
	}

	


	


	



}
