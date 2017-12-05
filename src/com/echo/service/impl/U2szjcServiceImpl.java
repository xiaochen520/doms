package com.echo.service.impl;

 
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dao.U2szjcDao;
import com.echo.dto.PcRpdU2WaterQualityT;
import com.echo.service.U2szjcService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;

public class U2szjcServiceImpl implements U2szjcService{
	private U2szjcDao u2szjcDao;
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setU2szjcDao(U2szjcDao u2szjcDao) {
		this.u2szjcDao = u2szjcDao;
	}

	@Override
	public JSONArray searchU2szjc(String rq) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();

		
		return arr;
	}

	public JSONObject searchU2szjc(List<String> date) throws Exception {
		JSONObject tjo = null;
		JSONObject kongtjo = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		//获取当前系统日期
		String nowtime = DateBean.getSystemTime1();
		String REPORT_DATE ="";
		String[] cloumnsName = {"REPORT_DATE","REPORT_TIME","HYCJC","HYDJ","HY1_2","HYDC","HYFC","HYHNC","HYYJJ","HYYJC","HYEJC","HYRHS","XFCJC","XFDJ","XF1_2","XFDC","XFFC","XFHNC","XFYJJ","XFYJC","XFEJC","XFRHS","YDCJC","YDDJ","YDDC","YDFC","YDHNC","YDYJJ","HNYJC","HNEJC","RPD_U2_WATER_QUALITY_ID"};
		String timeCalssql = "select ";
		for (String cn : cloumnsName) {
			if("REPORT_TIME".equals(cn)){
				timeCalssql += "to_char(REPORT_TIME,'YYYY-MM-DD HH24:MI:SS') as REPORT_TIME ,";
			}else{
				timeCalssql += cn +",";
			}
		}
		timeCalssql = timeCalssql.substring(0, timeCalssql.length()-1);
//		RPD_U2_WATER_QUALITY_ID,report_date,to_char(report_time,'YYYY-MM-DD HH24:MI:SS') as report_time,hycjc,hydj,hy1_2,hydc,hyfc,hyhnc,hyyjj,hyyjc,hyejc,hyrhs,xfcjc,xfdj,xf1_2,xfdc,xffc,xfhnc,xfyjj,xfyjc,xfejc,xfrhs,ydcjc,yddj,yddc,ydfc,ydhnc,ydyjj,hnyjc,hnejc "+
		timeCalssql +=	" from PC_RPD_U2_WATER_QUALITY_T t" ;
		
		timeCalssql += " where t.report_date between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD')";
		timeCalssql += 	" order by t.report_date ,t.report_time";
		List<Object[]> lo = u2szjcDao.searchU2szjc(timeCalssql);
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
			compreCloumnValue[3] = pc.getSystemConfiguration("HYDJ");
			compreCloumnValue[4] = pc.getSystemConfiguration("HY1_2");
			compreCloumnValue[5] = pc.getSystemConfiguration("HYDC");
			compreCloumnValue[6] = pc.getSystemConfiguration("HYFC");
			compreCloumnValue[7] = pc.getSystemConfiguration("HYHNC");
			compreCloumnValue[8] = pc.getSystemConfiguration("HYYJJ");
			compreCloumnValue[9] = pc.getSystemConfiguration("HYYJC");
			compreCloumnValue[10] = pc.getSystemConfiguration("HYEJC");
			compreCloumnValue[11] = pc.getSystemConfiguration("HYRHS");
			
			compreCloumnValue[13] = pc.getSystemConfiguration("XFDJ");
			compreCloumnValue[14] = pc.getSystemConfiguration("XF1_2");
			compreCloumnValue[15] = pc.getSystemConfiguration("XFDC");
			compreCloumnValue[16] = pc.getSystemConfiguration("XFFC");
			compreCloumnValue[17] = pc.getSystemConfiguration("XFHNC");
			compreCloumnValue[18] = pc.getSystemConfiguration("XFYJJ");
			compreCloumnValue[19] = pc.getSystemConfiguration("XFYJC");
			compreCloumnValue[20] = pc.getSystemConfiguration("XFEJC");
			compreCloumnValue[21] = pc.getSystemConfiguration("XFRHS");
			
//			String HYDJ_Value = pc.getSystemConfiguration("HYDJ");
//			String HY1_2_Value = pc.getSystemConfiguration("HY1_2");
//			String HYDC_Value = pc.getSystemConfiguration("HYDC");
//			String HYFC_Value = pc.getSystemConfiguration("HYFC");
//			String HYHNC_Value = pc.getSystemConfiguration("HYHNC");
//			String HYYJJ_Value = pc.getSystemConfiguration("HYYJJ");
//			String HYYJC_Value = pc.getSystemConfiguration("HYYJC");
//			String HYEJC_Value = pc.getSystemConfiguration("HYEJC");
//			String HYRHS_Value = pc.getSystemConfiguration("HYRHS");
//			String XFDJ_Value = pc.getSystemConfiguration("XFDJ");
//			String XF1_2_Value = pc.getSystemConfiguration("XF1_2");
//			String XFDC_Value = pc.getSystemConfiguration("XFDC");
//			String XFFC_Value = pc.getSystemConfiguration("XFFC");
//			String XFHNC_Value = pc.getSystemConfiguration("XFHNC");
//			String XFYJJ_Value = pc.getSystemConfiguration("XFYJJ");
//			String XFYJC_Value = pc.getSystemConfiguration("XFYJC");
//			String XFEJC_Value = pc.getSystemConfiguration("XFEJC");
//			String XFRHS_Value = pc.getSystemConfiguration("XFRHS");

			
			// 生成树节点json文档
			jsonArr = new JSONArray();
			REPORT_DATE = lo.get(0)[0].toString();
			for (Object[] o : lo) {
				o = getNewOBJ(o);
				//所属日期相同
				if(REPORT_DATE.equals(o[0].toString())){
					tjo = new JSONObject();
					for (int i = 0; i < o.length; i++) {
						
							if(i >=3 && i <12){
								tjo =CommonsUtil.U2compreData(cloumnsName[i], o[i], compreCloumnValue[i],tjo);
							}else if(i >= 13 && i <22){
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
						
							if(i >=3 && i <12){
								tjo =CommonsUtil.U2compreData(cloumnsName[i], o[i], compreCloumnValue[i],tjo);
							}else if(i >= 13 && i <22){
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
			if(i != 0 && i != 1 &&i != args.length-1){
				
				if(i == 2 ||i == 3 ||i == 4 ||i == 5 ||i == 13 ||i == 14 ||i == 15){
					newobj[i] =  CommonsUtil.getIntData(args[i]);
				}else{
					newobj[i] =  CommonsUtil.getNotTwoData(args[i]);
				}
			}else{
				newobj[i] =  args[i];
				
			}
			
		}
		return newobj;
	}
	@Override
	public List<PcRpdU2WaterQualityT> searchU2WaterQuality(String id)
			throws Exception {
		String sql = "FROM PcRpdU2WaterQualityT t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			sql += " and t.rpdU2WaterQualityId = '"+id+"'";
		}
//		return u2szjcDao.searchU2WaterQuality(sql);
		return (List<PcRpdU2WaterQualityT>) commonDao.searchClassQuery(sql);
	}

	@Override
	public boolean addOrUpdateData(PcRpdU2WaterQualityT prws) throws Exception {
		return u2szjcDao.addOrUpdateDatas(prws);
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
		return u2szjcDao.searchU2szjc(timeCalssql);
	}

	@Override
	public List<Object[]> searchExportData(List<String> date, String fields)
			throws Exception {
		String sql = fields + "  and t.report_date between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD') order by t.report_date ,t.report_time";
	
		List<Object[]> list = u2szjcDao.searchU2szjc(sql);
		return list;
	}

	

}
