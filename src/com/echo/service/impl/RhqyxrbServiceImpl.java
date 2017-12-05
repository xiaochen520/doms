package com.echo.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.SyjyxrbDao;
import com.echo.dto.PcRpdMollifierT;
import com.echo.service.RhqyxrbService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class RhqyxrbServiceImpl implements RhqyxrbService{
	private  SyjyxrbDao syjyxrbDao;				
	private CommonDao commonDao;
	public void setSyjyxrbDao(SyjyxrbDao syjyxrbDao) {
		this.syjyxrbDao = syjyxrbDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@SuppressWarnings("unused")
	@Override
	public JSONArray searchSyjyxrb(String clz, String rhq, String txtDate,String setRHQ) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		int dataflg = 0;
		Object[] tableData = null;
		String calcNum = "-16";		
		String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
		
		String sql = "select  a.rpd_mollifier_id,to_char(a.bbsj ,'YYYY-MM-DD HH24:MI:SS') as bbsj,a.sclzmc,a.sbbh," +
				"a.SCL_AHARDNESS1,a.SCL_AHARDNESS2,a.SCL_ALL,a.SCL_ALLLJ,"+
		        "a.SCL_BHARDNESS1,a.SCL_BHARDNESS2,a.SCL_BLL,a.SCL_BLLLJ,"+
		        "a.SCL_CHARDNESS1,a.SCL_CHARDNESS2,a.SCL_CLL,a.SCL_CLLLJ,"+
		        "a.SCL_DHARDNESS1,a.SCL_DHARDNESS2,a.SCL_DLL,a.SCL_DLLLJ " +
		        "from  PC_RPD_MOLLIFIER_T a where 1=1";
		sql += " and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') ";
		sql += " and SCLZMC = '"+clz+"'";
		sql += " and SBBH = '"+rhq+"' ";
		
		sql += " order by a.BBSJ";
		List<Object[]> RGQData = commonDao.searchEverySql(sql);
		String[][] dates = DateBean.getReportTime("time", txtDate);
		
		if(RGQData !=null && RGQData.size()>0){
		
			for(String[] dateList:                                                                                                                                                 dates){
			obj = new JSONObject();

					 
					 for(String data:dateList){
							dataflg = 0;
							for(Object[] rhqSet:RGQData){
								obj = new JSONObject();
								
								if(data.toString().equals(rhqSet[1].toString())){
									dataflg = 1;
									tableData = rhqSet;

								
							}
						}
					 if(dataflg == 1){
							obj.put("RPD_MOLLIFIER_ID", CommonsUtil.getClearNullData(tableData[0]));
							obj.put("BBSJ", data.substring(11, 16));
							
//							obj.put("SCL_AHARDNESS1",CommonsUtil.getZeroData(tableData[4]));
//							obj.put("SCL_AHARDNESS2",CommonsUtil.getZeroData(tableData[5]));
							obj.put("SCL_ALL",CommonsUtil.getZeroData(tableData[6]));
							obj.put("SCL_ALLLJ",CommonsUtil.getZeroData(tableData[7]));
							
								obj.put("SCL_AHARDNESS1",CommonsUtil.getZeroData(tableData[4]));
								obj.put("SCL_AHARDNESS2",CommonsUtil.getZeroData(tableData[5]));

//							obj.put("SCL_BHARDNESS1",CommonsUtil.getZeroData(tableData[8]));
//							obj.put("SCL_BHARDNESS2",CommonsUtil.getZeroData(tableData[9]));
							obj.put("SCL_BLL",CommonsUtil.getZeroData(tableData[10]));
							obj.put("SCL_BLLLJ",CommonsUtil.getZeroData(tableData[11]));

								obj.put("SCL_BHARDNESS1",CommonsUtil.getZeroData(tableData[8]));
								obj.put("SCL_BHARDNESS2",CommonsUtil.getZeroData(tableData[9]));

							
							
//							obj.put("SCL_CHARDNESS1",CommonsUtil.getZeroData(tableData[12]));
//							obj.put("SCL_CHARDNESS2",CommonsUtil.getZeroData(tableData[13]));
							obj.put("SCL_CLL",CommonsUtil.getZeroData(tableData[14]));
							obj.put("SCL_CLLLJ",CommonsUtil.getZeroData(tableData[15]));
							
							

								obj.put("SCL_CHARDNESS1",CommonsUtil.getZeroData(tableData[12]));
								obj.put("SCL_CHARDNESS2",CommonsUtil.getZeroData(tableData[13]));

							
							
//							obj.put("SCL_DHARDNESS1",CommonsUtil.getZeroData(tableData[16]));
//							obj.put("SCL_DHARDNESS2",CommonsUtil.getZeroData(tableData[17]));
							obj.put("SCL_DLL",CommonsUtil.getZeroData(tableData[18]));
							obj.put("SCL_DLLLJ",CommonsUtil.getZeroData(tableData[19]));
						
							

								obj.put("SCL_DHARDNESS1",CommonsUtil.getZeroData(tableData[16]));
								obj.put("SCL_DHARDNESS2",CommonsUtil.getZeroData(tableData[17]));

						
					 	}else{
					 		obj.put("RPD_MOLLIFIER_ID","");
							obj.put("BBSJ",data.substring(11, 16));
							
							obj.put("SCL_AHARDNESS1","");
							obj.put("SCL_AHARDNESS2","");
							obj.put("SCL_ALL","");
							obj.put("SCL_ALLLJ","");
							
							obj.put("SCL_BHARDNESS1","");
							obj.put("SCL_BHARDNESS2","");
							obj.put("SCL_BLL","");
							obj.put("SCL_BLLLJ","");
							
							obj.put("SCL_CHARDNESS1","");
							obj.put("SCL_CHARDNESS2","");
							obj.put("SCL_CLL","");
							obj.put("SCL_CLLLJ","");
							
							obj.put("SCL_DHARDNESS1","");
							obj.put("SCL_DHARDNESS2","");
							obj.put("SCL_DLL","");
							obj.put("SCL_DLLLJ","");
					 	
					 }
					 arr.add(obj);
				 }
			}
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();

			 		obj.put("RPD_MOLLIFIER_ID","");
					obj.put("BBSJ",data.substring(11, 16));
					
					obj.put("SCL_AHARDNESS1","");
					obj.put("SCL_AHARDNESS2","");
					obj.put("SCL_ALL","");
					obj.put("SCL_ALLLJ","");
					
					obj.put("SCL_BHARDNESS1","");
					obj.put("SCL_BHARDNESS2","");
					obj.put("SCL_BLL","");
					obj.put("SCL_BLLLJ","");
					
					obj.put("SCL_CHARDNESS1","");
					obj.put("SCL_CHARDNESS2","");
					obj.put("SCL_CLL","");
					obj.put("SCL_CLLLJ","");
					
					obj.put("SCL_DHARDNESS1","");
					obj.put("SCL_DHARDNESS2","");
					obj.put("SCL_DLL","");
					obj.put("SCL_DLLLJ","");
			 
					arr.add(obj);
				}
			}
		
		}
		String waterSql="";
		//arr.add(obj);
		return arr;
	
	
	}

	@Override
	public List<Object[]> searchDataEXcep(String txtDate, String fields,String jid) {
		String sql = "select " + fields + " from pc_rpd_water_source_welld_t u where u.report_date = TO_DATE('"+txtDate+"','YYYY-MM-DD') and u.water_source_wellid ='"+jid+"'";
		List<Object[]> yyList =commonDao.searchEverySql(sql);
		return yyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdMollifierT> SreachCheckOn(String rpdid, String clz,String rhq)throws Exception{
		String  sname ="";
		if(clz !=null && !"".equals(clz)){
		  sname = clz+"水处理站";
	}
		String hql = " from  PcRpdMollifierT a  where 1=1";
		if(rpdid !=null && !"".equals(rpdid) ){
			hql += "  and  a.rpdMollifierId ='"+rpdid+"'";
		} 
		if(sname !=null && !"".equals(sname) ){
			hql += "  and  a.sclzmc ='"+sname+"'";
		}
		if(rhq !=null && !"".equals(rhq) ){
			hql += "  and  a.sbbh ='"+rhq+"'";
		}
		List<?>  list = null;
		list = commonDao.searchClassQuery(hql);
		return (List<PcRpdMollifierT>) list;
	}

	@Override
	public boolean updateData(PcRpdMollifierT wt)
			throws Exception {
		return syjyxrbDao.saveOrUpdateDatas(wt);
	}

	@Override
	public List<Object[]> searchNotNull(String sqlNo) {
		return commonDao.searchEverySql(sqlNo);
	}

	@SuppressWarnings("unused")
	@Override
	public JSONArray searchWaterMany(String clz, String rhq, String txtDate)throws Exception {
		String StationName="";
		if(clz.equals("特2软化")){
			 StationName  = "2号软化水处理站";
		}
		//String calcNum = "-16";
		String stratime ="";
		String endtime ="";
//		if(txtDate.equals("")||txtDate.equals("undefined")||txtDate==null){
//			Date date=new Date();
//			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//			stratime=sf.format(date);
//		}
//		if(txtDate.equals("")||txtDate.equals("undefined")||txtDate==null){
//			Date date=new Date();
//			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//			endtime=sf.format(date);
//			
//		}
		if("".equals(stratime)){
			stratime = txtDate + " 00:00:00";
		}
		if("".equals(endtime)){
			endtime = txtDate + " 23:59:59";
		}
		//String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
		//String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
		//a.zh,a.rhqbh,a.zuh,a.sj,
		String sql = "select a.zuh, a.LJLL , ifixtag  from  PC_RD_SCL2_T  a  " +
					 "where a.zh='"+StationName+"'  and a.rhqbh='"+rhq+"'  ";
		sql += " and a.sj between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') ";
		sql += " ORDER BY NLSSORT(a.zuh,'NLS_SORT = SCHINESE_PINYIN_M')";
		List<Object[]> MangList = null;
		JSONArray arr= null;
		JSONObject obj =new JSONObject();
		obj.put("waterA", "");
		obj.put("waterB", "");
		obj.put("waterC", "");
		obj.put("waterD", "");
		obj.put("ifixtagIDA","");
		obj.put("ifixtagIDB","");
		obj.put("ifixtagIDC","");
		obj.put("ifixtagIDD","");
		
		MangList = commonDao.searchEverySql(sql);
		Object argValue[] = {"waterA","waterB","waterC","waterD"};
		Object argParam = "";
		if(MangList !=null &&  MangList.size()>0){
			
			arr = new JSONArray();
			for (int i = 0; i < MangList.size(); i++) {
				argParam =MangList.get(i)[0];
				
				if(argParam.equals("A")){
					obj.put("waterA", CommonsUtil.getNotOneData(MangList.get(i)[1]));
					 obj.put("ifixtagIDA", MangList.get(i)[3].toString());
					 obj.put("SJA", MangList.get(i)[3].toString());
				}else  if(argParam.equals("B")){
					obj.put("waterB", CommonsUtil.getNotOneData(MangList.get(i)[1]));
					 obj.put("ifixtagIDB", MangList.get(i)[2].toString());
					 obj.put("SJB", MangList.get(i)[3].toString());
				}else  if(argParam.equals("C")){
					obj.put("waterC", CommonsUtil.getNotOneData(MangList.get(i)[1]));
					 obj.put("ifixtagIDC", MangList.get(i)[2].toString());
					 obj.put("SJC", MangList.get(i)[3].toString());
				}else{
					obj.put("waterD", CommonsUtil.getNotOneData(MangList.get(i)[1]));
					 obj.put("ifixtagIDD", MangList.get(i)[2].toString());
					 obj.put("SJD", MangList.get(i)[3].toString());
					
				}
				
			}
			arr.add(obj);
		}else{
			arr = new JSONArray();
			obj = new JSONObject();
			obj.put("waterA", "");
			obj.put("waterB", "");
			obj.put("waterC", "");
			obj.put("waterD", "");
			obj.put("ifixtagIDA","");
			obj.put("ifixtagIDB","");
			obj.put("ifixtagIDC","");
			obj.put("ifixtagIDD","");
			arr.add(obj);
		}
		return arr;
	}

	@Override
	public int searchCalcNum() {
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = commonDao.searchEverySql(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			return Integer.parseInt(calcNum);
	}

	@Override
	public List<Object[]> searchExportData(String punSql) {
		
		return commonDao.searchEverySql(punSql);
	}

	@Override
	public List<Object[]> searchExportData(String txtDate, String insMainSql,String sclzmc,String rhq)throws Exception {
		if (!"".equals(txtDate)) {
			//String calcNum = searchCalcNum();
			String calcNum = "-16";
			String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
			String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
			insMainSql +="  where  SCLZMC ='"+sclzmc+"'";
			insMainSql +="  and SBBH ='"+rhq+"'";
			insMainSql += " and BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
			
		}
		
		List<Object[]> list = (List<Object[]>) commonDao.searchEverySql(insMainSql);
		return list;
	}

	@SuppressWarnings("unused")
	@Override
	public JSONArray searchSyjyxrb1(String clz, String rhq, String txtDate,
			String setRHQ) throws Exception{
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		int dataflg = 0;
		Object[] tableData = null;
		String calcNum = "-16";		
		String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
		
		String sql = "select  a.rpd_mollifier_id,to_char(a.bbsj ,'YYYY-MM-DD HH24:MI:SS') as bbsj,a.sclzmc,a.sbbh," +
				"a.SCL_AHARDNESS1,a.SCL_AHARDNESS2,a.SCL_ALL,a.SCL_ALLLJ,"+
		        "a.SCL_BHARDNESS1,a.SCL_BHARDNESS2,a.SCL_BLL,a.SCL_BLLLJ,"+
		        "a.SCL_CHARDNESS1,a.SCL_CHARDNESS2,a.SCL_CLL,a.SCL_CLLLJ,"+
		        "a.SCL_DHARDNESS1,a.SCL_DHARDNESS2,a.SCL_DLL,a.SCL_DLLLJ " +
		        "from  PC_RPD_MOLLIFIER_T a where 1=1";
		sql += " and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') ";
		sql += " and SCLZMC = '"+clz+"'";
		sql += " and SBBH = '"+rhq+"' ";
		
		sql += " order by a.BBSJ";
		List<Object[]> RGQData = commonDao.searchEverySql(sql);
		String[][] dates = DateBean.getReportTime("time", txtDate);
		
		if(RGQData !=null && RGQData.size()>0){
		
			for(String[] dateList:                                                                                                                                                 dates){
			obj = new JSONObject();

					 
					 for(String data:dateList){
							dataflg = 0;
							for(Object[] rhqSet:RGQData){
								obj = new JSONObject();
								
								if(data.toString().equals(rhqSet[1].toString())){
									dataflg = 1;
									tableData = rhqSet;

								
							}
						}
					 if(dataflg == 1){
							obj.put("RPD_MOLLIFIER_ID", CommonsUtil.getClearNullData(tableData[0]));
							obj.put("BBSJ", data.substring(11, 16));
							
//							obj.put("SCL_AHARDNESS1",CommonsUtil.getZeroData(tableData[4]));
//							obj.put("SCL_AHARDNESS2",CommonsUtil.getZeroData(tableData[5]));
							obj.put("SCL_ALL",CommonsUtil.getZeroData(tableData[6]));
							obj.put("SCL_ALLLJ",CommonsUtil.getZeroData(tableData[7]));
							
								obj.put("SCL_AHARDNESS1",CommonsUtil.getZeroData(tableData[4]));
								obj.put("SCL_AHARDNESS2",CommonsUtil.getZeroData(tableData[5]));

//							obj.put("SCL_BHARDNESS1",CommonsUtil.getZeroData(tableData[8]));
//							obj.put("SCL_BHARDNESS2",CommonsUtil.getZeroData(tableData[9]));
							obj.put("SCL_BLL",CommonsUtil.getZeroData(tableData[10]));
							obj.put("SCL_BLLLJ",CommonsUtil.getZeroData(tableData[11]));

								obj.put("SCL_BHARDNESS1",CommonsUtil.getZeroData(tableData[8]));
								obj.put("SCL_BHARDNESS2",CommonsUtil.getZeroData(tableData[9]));

							
							
//							obj.put("SCL_CHARDNESS1",CommonsUtil.getZeroData(tableData[12]));
//							obj.put("SCL_CHARDNESS2",CommonsUtil.getZeroData(tableData[13]));
							obj.put("SCL_CLL",CommonsUtil.getZeroData(tableData[14]));
							obj.put("SCL_CLLLJ",CommonsUtil.getZeroData(tableData[15]));
							
							

								obj.put("SCL_CHARDNESS1",CommonsUtil.getZeroData(tableData[12]));
								obj.put("SCL_CHARDNESS2",CommonsUtil.getZeroData(tableData[13]));

							
							
//							obj.put("SCL_DHARDNESS1",CommonsUtil.getZeroData(tableData[16]));
//							obj.put("SCL_DHARDNESS2",CommonsUtil.getZeroData(tableData[17]));
							obj.put("SCL_DLL",CommonsUtil.getZeroData(tableData[18]));
							obj.put("SCL_DLLLJ",CommonsUtil.getZeroData(tableData[19]));
						
							

								obj.put("SCL_DHARDNESS1",CommonsUtil.getZeroData(tableData[16]));
								obj.put("SCL_DHARDNESS2",CommonsUtil.getZeroData(tableData[17]));

						
					 	}else{
					 		obj.put("RPD_MOLLIFIER_ID","");
							obj.put("BBSJ",data.substring(11, 16));
							
							obj.put("SCL_AHARDNESS1","");
							obj.put("SCL_AHARDNESS2","");
							obj.put("SCL_ALL","");
							obj.put("SCL_ALLLJ","");
							
							obj.put("SCL_BHARDNESS1","");
							obj.put("SCL_BHARDNESS2","");
							obj.put("SCL_BLL","");
							obj.put("SCL_BLLLJ","");
							
							obj.put("SCL_CHARDNESS1","");
							obj.put("SCL_CHARDNESS2","");
							obj.put("SCL_CLL","");
							obj.put("SCL_CLLLJ","");
							
							obj.put("SCL_DHARDNESS1","");
							obj.put("SCL_DHARDNESS2","");
							obj.put("SCL_DLL","");
							obj.put("SCL_DLLLJ","");
					 	
					 }
					 arr.add(obj);
				 }
			}
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();

			 		obj.put("RPD_MOLLIFIER_ID","");
					obj.put("BBSJ",data.substring(11, 16));
					
					obj.put("SCL_AHARDNESS1","");
					obj.put("SCL_AHARDNESS2","");
					obj.put("SCL_ALL","");
					obj.put("SCL_ALLLJ","");
					
					obj.put("SCL_BHARDNESS1","");
					obj.put("SCL_BHARDNESS2","");
					obj.put("SCL_BLL","");
					obj.put("SCL_BLLLJ","");
					
					obj.put("SCL_CHARDNESS1","");
					obj.put("SCL_CHARDNESS2","");
					obj.put("SCL_CLL","");
					obj.put("SCL_CLLLJ","");
					
					obj.put("SCL_DHARDNESS1","");
					obj.put("SCL_DHARDNESS2","");
					obj.put("SCL_DLL","");
					obj.put("SCL_DLLLJ","");
			 
					arr.add(obj);
				}
			}		
		}
		String waterSql="";
		return arr;
	}

	@Override
	public HashMap<String, Object> searchRhqzs(String clz, String rhq,
			String startDate, String endDate, int pageNo, String sort,
			String order, int rowsPerpage, String totalNumf) throws Exception {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";	

		String formTable = " from PC_RPD_WATER_MESSAGE_RHQ_T a where 1=1 and a.rq between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		String totalNum = "select count(*) ";
		
		if(!clz.equals("")&&clz!=null&&!clz.equals("undefined") ){
			formTable=formTable+" and zh ='"+clz+"'";
		}
		if(!rhq.equals("")&&rhq!=null&&!rhq.equals("undefined")){
			formTable=formTable+" and rhqbh='"+rhq+"'";
		}
		
		
		String[] cloumnsName = {"zh","rq","rhqbh","situation_log1"};			
		String kk="zh";
		for(int m=1;m<cloumnsName.length;m++){
			if("rq".equals(cloumnsName[m])){
				kk=kk+","+"to_char(rq,'YYYY-MM-DD hh24:mi:ss') as rq";
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = {"zh","rq","rhqbh","situation_log1"};	
			kk = "zh";
			for(int m=1;m<cloumnsName2.length;m++){
				if("rq".equals(cloumnsName2[m])){
					kk=kk+","+"to_char(rq,'YYYY-MM-DD hh24:mi:ss') as rq";
				}else{
					kk=kk+","+cloumnsName2[m];
				}
			}
		}
		String sql = cloums + kk+formTable + " order by rq desc ";
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = commonDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = commonDao.searchRhqzs1(sql);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchRhqzs1(sql,start,rowsPerpage);
		}
		
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			int ZSCS = 0;
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length-1; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}
						tjo.put(cloumnsName[i], o[i].toString());					
				}
				for(int i = o.length-1; i < o.length; i++){
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}
					else{
						String str = o[i].toString();

						String[] str1 = str.split(",");

						for(int j=0;j<str1.length;j++){
							int id1 = str1[j].indexOf("进盐");
							int id2 = str1[j].indexOf("置换");
							int id3 = str1[j].indexOf("正洗");
							if(-1!=id1 &&  -1!=id2 && -1!=id3){
								ZSCS = ZSCS + 1;
							}
						}
						
						for(int j=0;j<str1.length;j++){
							int id1 = str1[j].indexOf("A");
							int id2 = str1[j].indexOf("B");
							int id3 = str1[j].indexOf("C");
							if(-1!=id1){
								String a = str1[j];
								tjo.put("A", a);
								a = "";
							}
							if(-1!=id2){
								String b = str1[j];
								tjo.put("B", b);
								b = "";
							}
							if(-1!=id3){
								String c = str1[j];
								tjo.put("C", c);
								c = "";
							}
						}
				
//						String a;
//						for(int j=0;j<1;j++){
//							a = str1[j];
//							tjo.put("A", a);
//							a = "";
//						}
//						String b;
//						for(int j=1;j<2;j++){
//							b = str1[j];
//							tjo.put("B", b);
//							b = "";
//						}
//
//						String c;
//						for(int j=2;j<3;j++){
//							c = str1[j];
//							tjo.put("C", c);
//							c = "";
//							}					
						
					}
					tjo.put("ZSCS", ZSCS);
					ZSCS = 0;
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
	
	
	
	
}
