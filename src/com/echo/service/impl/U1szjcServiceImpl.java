package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dto.PcRpdU1WaterQuality1T;
import com.echo.dto.PcRpdU1WaterQuality2T;
import com.echo.service.U1szjcService;
import com.echo.util.CommonsUtil;
import com.echo.util.PropertiesConfig;

public class U1szjcServiceImpl implements U1szjcService{
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	


	public JSONObject searchU2szjc(List<String> date) throws Exception {
		JSONObject tjo = null;
		JSONObject kongtjo = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		//获取当前系统日期
		String REPORT_DATE ="";
		String[] cloumnsName = {"BBRQ","BBSJ","HYTCGJK","HYG2_1","HYTCGCK","HYGYGCK","HYXBGCK","HYYJJK","HYYKCK","HYEJCK","XFWTCGJK","XFWG2_1","XFWTCGCK","XFWFYGCK","XFWXBGCK","XFWYJJK","XFWYJCK","XFWEJCK","YDFYGCK","YDEJCK",
				"YDTCGJK","YD2_1","YDTCGCK","YDXBGCK","YDYJJK","YDYJCK","GTCGJK","GFYQCK","GXBGCK","BZ","RPD_U1_WATER_QUALITY1_ID"};
		String timeCalssql = "select ";
		for (String cn : cloumnsName) {
			if("BBSJ".equals(cn)){
				timeCalssql += "to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS') as BBSJ ,";
			}else{
				timeCalssql += cn +",";
			}
		}
		timeCalssql = timeCalssql.substring(0, timeCalssql.length()-1);
//		RPD_U2_WATER_QUALITY_ID,report_date,to_char(report_time,'YYYY-MM-DD HH24:MI:SS') as report_time,hycjc,hydj,hy1_2,hydc,hyfc,hyhnc,hyyjj,hyyjc,hyejc,hyrhs,xfcjc,xfdj,xf1_2,xfdc,xffc,xfhnc,xfyjj,xfyjc,xfejc,xfrhs,ydcjc,yddj,yddc,ydfc,ydhnc,ydyjj,hnyjc,hnejc "+
		timeCalssql +=	" from PC_RPD_U1_WATER_QUALITY1_T t" ;
		
		timeCalssql += " where t.BBRQ between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD')";
		timeCalssql += 	" order by t.BBRQ ,t.BBSJ";
		List<Object[]> lo = commonDao.searchEverySql(timeCalssql);
		PropertiesConfig pc = new PropertiesConfig();//写在adction 里 在查询拼接sql及导出报表时都要用到 
		if (lo != null && 0 < lo.size()) {
			for(int k =0 ; k<cloumnsName.length; k++){
				if(k == cloumnsName.length -1){
//					kongtjo.put(cloumnsName[cloumnsName.length -1],"<span style='background-color:red;'>空行</span>");
					kongtjo.put(cloumnsName[cloumnsName.length -1],"空行");
				}else{
//					kongtjo.put(cloumnsName[k],"<span style='background-color:red;'> </span>");
					kongtjo.put(cloumnsName[k],"");
				}
			}
			//获取数据范围值
			String[] compreCloumnValue = new String[cloumnsName.length];
			compreCloumnValue[2] = pc.getSystemConfiguration("hytcgjk");
			compreCloumnValue[3] = pc.getSystemConfiguration("hyg2_1");
			compreCloumnValue[4] = pc.getSystemConfiguration("hytcgck");
			compreCloumnValue[5] = pc.getSystemConfiguration("hygygck");
			compreCloumnValue[6] = pc.getSystemConfiguration("hyxbgck");
			compreCloumnValue[7] = pc.getSystemConfiguration("hyyjjk");
			compreCloumnValue[8] = pc.getSystemConfiguration("hyykck");
			compreCloumnValue[9] = pc.getSystemConfiguration("hyejck");

			compreCloumnValue[10] = pc.getSystemConfiguration("xfwtcgjk");
			compreCloumnValue[11] = pc.getSystemConfiguration("xfwg2_1");
			compreCloumnValue[12] = pc.getSystemConfiguration("xfwtcgck");
			compreCloumnValue[13] = pc.getSystemConfiguration("xfwfygck");
			compreCloumnValue[14] = pc.getSystemConfiguration("xfwxbgck");
			compreCloumnValue[15] = pc.getSystemConfiguration("xfwyjjk");
			compreCloumnValue[16] = pc.getSystemConfiguration("xfwyjck");
			compreCloumnValue[17] = pc.getSystemConfiguration("xfwejck");
			
			compreCloumnValue[18] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[19] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[20] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[21] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[22] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[23] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[24] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[25] = pc.getSystemConfiguration("wsyd");
			
			compreCloumnValue[26] = pc.getSystemConfiguration("eyhg");

			
			// 生成树节点json文档
			jsonArr = new JSONArray();
			REPORT_DATE = lo.get(0)[0].toString();
			for (Object[] o : lo) {
				o = getNewOBJ(o);
				//所属日期相同
				if(REPORT_DATE.equals(o[0].toString())){
					tjo = new JSONObject();
					for (int i = 0; i < o.length; i++) {
						
							if(i ==2 ||i ==3 ||i ==4 ||i ==5 ||i ==6 ||i ==7 ||i ==8 ||i ==9 ||i ==10 ||i ==11 ||i ==12 ||i ==13 ||i ==14 ||i ==15 || i ==16 || i ==17 ||
									i ==18 || i ==19 ||i ==20 ||i ==21 ||i ==22 ||i ==23 ||i ==24 ||i ==25 ||i ==26){
								tjo =CommonsUtil.U2compreData(cloumnsName[i], o[i], compreCloumnValue[i],tjo);
							}else{
								if (o[i] == null||o[i].equals("null")) {
									tjo.put(cloumnsName[i],"");
								}else{
									if(i == 1){
										if(o[i] != null && o[i].toString().length() == 19){
											tjo.put(cloumnsName[i],o[i].toString().substring(11, 16));
										}else{
											tjo.put(cloumnsName[i],o[i].toString());	
										}
									}else{
										tjo.put(cloumnsName[i],o[i].toString());
									}
									
								}
							}
					}
					
					//所属日期不相同
				}else{
				//添加一条空白记录
					jsonArr.add(kongtjo);
				//更换日期标示
					REPORT_DATE = o[0].toString();
					tjo = new JSONObject();
					for (int i = 0; i < o.length; i++) {
						
						if(i ==2 ||i ==3 ||i ==4 ||i ==5 ||i ==6 ||i ==7 ||i ==8 ||i ==9 ||i ==10 ||i ==11 ||i ==12 ||i ==13 ||i ==14 ||i ==15 || i ==16 || i ==17 ||
								i ==18 || i ==19 ||i ==20 ||i ==21 ||i ==22 ||i ==23 ||i ==24 ||i ==25 ||i ==26){
								tjo =CommonsUtil.U2compreData(cloumnsName[i], o[i], compreCloumnValue[i],tjo);
							}else{
								if (o[i] == null||o[i].equals("null")) {
									tjo.put(cloumnsName[i],"");
								}else{
									if(i == 1){
										if(o[i] != null && o[i].toString().length() == 19){
											tjo.put(cloumnsName[i],o[i].toString().substring(11, 16));
										}else{
											tjo.put(cloumnsName[i],o[i].toString());	
										}
									}else{
										tjo.put(cloumnsName[i],o[i].toString());
									}
								}
							}
					}
				}
//				if(nowtime.equals(o[0].toString())){
//					tjo.put("OPERATION", "<a href='javascript:beginEdit('" + o[o.length-1] + "')'>修改</a> ");	
//				}else{
//					tjo.put("OPERATION","");
//				}
//				
				
				jsonArr.add(tjo);
			}
		}
		jsonArr.add(kongtjo);
		tjo = new JSONObject();
 		tjo.put("Rows",jsonArr);
 		if(lo != null && lo.size()>0){
 			tjo.put("Total",lo.size());
 		}else{
 			tjo.put("Total",0);
 		}

		return tjo;
	}

	
	public Object[] getNewOBJ(Object[] args){
		Object[] newobj = new Object[args.length]; 
		for(int i = 0; i<args.length ; i++){
			if(i != 0 && i != 1 &&i != args.length-1&&i != args.length-2){
				
//				if(i == 9 ||i == 10 ||i == 11 ||i == 12 ||i == 13 ||i == 20 ||i == 21 ||i == 22 ||i == 23 ||i == 24){
					newobj[i] =  CommonsUtil.getNotTwoData(args[i]);
//				}else{
//					newobj[i] =  CommonsUtil.getIntData(args[i]);
//				}
			}else{
				newobj[i] =  args[i];
				
			}
			
		}
		return newobj;
	}
	@Override
	public List<PcRpdU1WaterQuality1T> searchU2WaterQuality(String id)
			throws Exception {
		String sql = "FROM PcRpdU1WaterQuality1T t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			sql += " and t.rpdU1WaterQuality1Id = '"+id+"'";
		}
//		return u2szjcDao.searchU2WaterQuality(sql);
		return (List<PcRpdU1WaterQuality1T>) commonDao.searchClassQuery(sql);
	}

	@Override
	public boolean addOrUpdateData(PcRpdU1WaterQuality1T prws) throws Exception {
		List<PcRpdU1WaterQuality1T> t = new ArrayList<PcRpdU1WaterQuality1T>();
		t.add(prws);
		return commonDao.addOrUpdateDatas(t);
		
	}
	
	@Override
	public boolean addOrUpdateData(PcRpdU1WaterQuality2T prws) throws Exception {
		List<PcRpdU1WaterQuality2T> t = new ArrayList<PcRpdU1WaterQuality2T>();
		t.add(prws);
		return commonDao.addOrUpdateDatas(t);
		
	}

	@Override
	public List<Object[]> searchDatas(List<String> date)
			throws Exception {
		String[] cloumnsName = {"REPORT_DATE","REPORT_TIME","HYCJC","HYDJ","HY1_2","HYDC","HYFC","HYHNC","HYYJJ","HYYJC","HYEJC","HYRHS","XFCJC","XFDJ","XF1_2","XFDC","XFFC","XFHNC","XFYJJ","XFYJC","XFEJC","XFRHS","YDCJC","YDDJ","YDDC","YDFC","YDHNC","YDYJJ","HNYJC","HNEJC"};
		String timeCalssql = "select ";
		for (String cn : cloumnsName) {
			if("REPORT_TIME".equals(cn)){
				timeCalssql += "to_char(REPORT_TIME,'YYYY-MM-DD HH24:MI:SS') as REPORT_TIME ,";
			}else{
				timeCalssql += cn +",";
			}
		}
		timeCalssql = timeCalssql.substring(0, timeCalssql.length()-1);
		timeCalssql +=	" from PC_RPD_U2_WATER_QUALITY_T t" ;
		
		timeCalssql += " where t.report_date between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD')";
		timeCalssql += 	" order by t.report_date ,t.report_time";
		return commonDao.searchEverySql(timeCalssql);
	}

	@Override
	public List<Object[]> searchExportData(List<String> date, String fields)
			throws Exception {
		String sql = fields + "  and t.BBRQ between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD') order by t.BBRQ ,t.BBSJ";
	
		List<Object[]> list = commonDao.searchEverySql(sql);
		return list;
	}




	@Override
	public JSONObject searchU1Q2szjc(List<String> date) throws Exception {

		JSONObject tjo = null;
		JSONObject kongtjo = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		//获取当前系统日期
		String REPORT_DATE ="";
		String[] cloumnsName = {"BBRQ","BBSJ","HYS2TCGJK","HYS2TCGCK","HYG4_3","HYS2QFCK","HYS2HCC","HYS2YJCK","HYS2EJCK","XFS2TCGJK","XFS2TCGCK","XFG4_3","XFS2QFCK","XFS2HCC","XFS2YJCK","XFS2EJCK","YDS2TCGCK","YDS2EJCK","YDS2TCGJK","YD4_3"
				,"YDS2HCC","YDS2QF","YDS2YJCK","BZ","RPD_U1_WATER_QUALITY2_ID"};
		String timeCalssql = "select ";
		for (String cn : cloumnsName) {
			if("BBSJ".equals(cn)){
				timeCalssql += "to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS') as BBSJ ,";
			}else{
				timeCalssql += cn +",";
			}
		}
		timeCalssql = timeCalssql.substring(0, timeCalssql.length()-1);
//		RPD_U2_WATER_QUALITY_ID,report_date,to_char(report_time,'YYYY-MM-DD HH24:MI:SS') as report_time,hycjc,hydj,hy1_2,hydc,hyfc,hyhnc,hyyjj,hyyjc,hyejc,hyrhs,xfcjc,xfdj,xf1_2,xfdc,xffc,xfhnc,xfyjj,xfyjc,xfejc,xfrhs,ydcjc,yddj,yddc,ydfc,ydhnc,ydyjj,hnyjc,hnejc "+
		timeCalssql +=	" from PC_RPD_U1_WATER_QUALITY2_T t" ;
		
		timeCalssql += " where t.BBRQ between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD')";
		timeCalssql += 	" order by t.BBRQ ,t.BBSJ";
		List<Object[]> lo = commonDao.searchEverySql(timeCalssql);
		PropertiesConfig pc = new PropertiesConfig();//写在adction 里 在查询拼接sql及导出报表时都要用到 
		if (lo != null && 0 < lo.size()) {
			for(int k =0 ; k<cloumnsName.length; k++){
				if(k == cloumnsName.length -1){
//					kongtjo.put(cloumnsName[cloumnsName.length -1],"<span style='background-color:red;'>空行</span>");
					kongtjo.put(cloumnsName[cloumnsName.length -1],"空行");
				}else{
//					kongtjo.put(cloumnsName[k],"<span style='background-color:red;'> </span>");
					kongtjo.put(cloumnsName[k],"");
				}
			}
			//获取数据范围值
			String[] compreCloumnValue = new String[cloumnsName.length];
			compreCloumnValue[2] = pc.getSystemConfiguration("hys2tcgjk");
			compreCloumnValue[3] = pc.getSystemConfiguration("hys2tcgck");
			compreCloumnValue[4] = pc.getSystemConfiguration("hyg4_3");
			compreCloumnValue[5] = pc.getSystemConfiguration("hys2qfck");
			compreCloumnValue[6] = pc.getSystemConfiguration("hys2hcc");
			compreCloumnValue[7] = pc.getSystemConfiguration("hys2yjck");
			compreCloumnValue[8] = pc.getSystemConfiguration("hys2ejck");
			compreCloumnValue[9] = pc.getSystemConfiguration("xfs2tcgjk");
			compreCloumnValue[10] = pc.getSystemConfiguration("xfs2tcgck");
			compreCloumnValue[11] = pc.getSystemConfiguration("xfg4_3");
			compreCloumnValue[12] = pc.getSystemConfiguration("xfs2qfck");
			compreCloumnValue[13] = pc.getSystemConfiguration("xfs2hcc");
			compreCloumnValue[14] = pc.getSystemConfiguration("xfs2yjck");
			compreCloumnValue[15] = pc.getSystemConfiguration("xfs2ejck");

			compreCloumnValue[16] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[17] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[18] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[19] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[20] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[21] = pc.getSystemConfiguration("wsyd");
			compreCloumnValue[22] = pc.getSystemConfiguration("wsyd");
			
			// 生成树节点json文档
			jsonArr = new JSONArray();
			REPORT_DATE = lo.get(0)[0].toString();
			for (Object[] o : lo) {
				
				//所属日期相同
				if(REPORT_DATE.equals(o[0].toString())){
					tjo = new JSONObject();
					for (int i = 0; i < o.length; i++) {
						
							if(i != 0 && i != 1 && i != 23 && i != 24){
								if(i == 2 || i == 3 ||i == 4 || i == 9 ||i == 10 ||i == 11 ){
									tjo =CommonsUtil.U2compreData(cloumnsName[i], CommonsUtil.getIntData(o[i]), compreCloumnValue[i],tjo);
								}else{
									tjo =CommonsUtil.U2compreData(cloumnsName[i], CommonsUtil.getNotTwoData(o[i]), compreCloumnValue[i],tjo);
								}
								
							}else{
								if (o[i] == null||o[i].equals("null")) {
									tjo.put(cloumnsName[i],"");
								}else{
									if(i == 1){
										if(o[i] != null && o[i].toString().length() == 19){
											tjo.put(cloumnsName[i],o[i].toString().substring(11, 16));
										}else{
											tjo.put(cloumnsName[i],o[i].toString());	
										}
									}else{
										
										tjo.put(cloumnsName[i],o[i].toString());
										
									}
									
								}
							}
					}
					
					//所属日期不相同
				}else{
				//添加一条空白记录
					jsonArr.add(kongtjo);
				//更换日期标示
					REPORT_DATE = o[0].toString();
					tjo = new JSONObject();
					for (int i = 0; i < o.length; i++) {
						
						if(i != 0 && i != 1 && i != 23 && i != 24){
								if(i == 2 || i == 3 ||i == 4 || i == 9 ||i == 10 ||i == 11 ){
									tjo =CommonsUtil.U2compreData(cloumnsName[i], CommonsUtil.getIntData(o[i]), compreCloumnValue[i],tjo);
								}else{
									tjo =CommonsUtil.U2compreData(cloumnsName[i], CommonsUtil.getNotTwoData(o[i]), compreCloumnValue[i],tjo);
								}
							}else{
								if (o[i] == null||o[i].equals("null")) {
									tjo.put(cloumnsName[i],"");
								}else{
									if(i == 1){
										if(o[i] != null && o[i].toString().length() == 19){
											tjo.put(cloumnsName[i],o[i].toString().substring(11, 16));
										}else{
											tjo.put(cloumnsName[i],o[i].toString());	
										}
									}else{
										if(i == 16 ||i == 17){
											tjo.put(cloumnsName[i],CommonsUtil.getIntData(o[i]));
										}else{
											tjo.put(cloumnsName[i],o[i].toString());
										}
									}
								}
							}
					}
				}
//				if(nowtime.equals(o[0].toString())){
//					tjo.put("OPERATION", "<a href='javascript:beginEdit('" + o[o.length-1] + "')'>修改</a> ");	
//				}else{
//					tjo.put("OPERATION","");
//				}
//				
				
				jsonArr.add(tjo);
			}
		}
		jsonArr.add(kongtjo);
		tjo = new JSONObject();
 		tjo.put("Rows",jsonArr);
 		if(lo != null && lo.size()>0){
 			tjo.put("Total",lo.size());
 		}else{
 			tjo.put("Total",0);
 		}

		return tjo;
	
	}




	@Override
	public List<PcRpdU1WaterQuality2T> searchU1WaterQuality(String id)
			throws Exception {
		String sql = "FROM PcRpdU1WaterQuality2T t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			sql += " and t.rpdU1WaterQuality2Id = '"+id+"'";
		}
//		return u2szjcDao.searchU2WaterQuality(sql);
		return (List<PcRpdU1WaterQuality2T>) commonDao.searchClassQuery(sql);
	}





	

}
