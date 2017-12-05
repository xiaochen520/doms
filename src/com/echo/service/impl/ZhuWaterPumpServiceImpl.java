package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.PublicDao;
import com.echo.dao.ZhuWaterPumpDao;
import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.service.ZhuWaterPumpService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class ZhuWaterPumpServiceImpl implements ZhuWaterPumpService{
		private ZhuWaterPumpDao  zhuWaterPumpDao;

		public ZhuWaterPumpDao getZhuWaterPumpDao() {
			return zhuWaterPumpDao;
		}

		public void setZhuWaterPumpDao(ZhuWaterPumpDao zhuWaterPumpDao) {
			this.zhuWaterPumpDao = zhuWaterPumpDao;
		}
		
		@SuppressWarnings("null")
		@Override
		public JSONArray searchZhuPump(String rq) throws Exception {
			double  ARWS = 0.0;
			double  ARZS = 0.0;
			double  ARZS1 = 0.0;
			double  ARZS2 = 0.0;
			double  BRWS = 0.0;
			double  BRZS = 0.0;
			double  BRZS1 = 0.0;
			double  BRZS2 = 0.0;
			
			JSONObject obj= null;
			JSONObject obj1= new JSONObject();
			JSONArray arr = new JSONArray();
			JSONArray arr1 = new JSONArray();
			JSONArray firstArrSum = new JSONArray();
			int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = zhuWaterPumpDao.searchZhuPump(timeCalssql);
			if(dataSet !=null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
			//to_char(  a.cjsj,'YYYY-MM-DD HH24:MI:SS')as cjsj ,
			String sql =" select a.rpd_u_thin_water_auto_id,to_char(  a.bbsj,'YYYY-MM-DD HH24:MI:SS')as bbsj,ZSBF_ZDY,pt_101a,pt_101b,TT_101,zsb1dl,pt_102a,pt_102b,TT_102,zsb2dl,sc12_zsb_alm_sv,pt_103a,pt_103b,TT_103,zsb3dl,pt_104a,pt_104b,TT_104,zsb4dl,sc34_zsb_alm_sv,FT_WSB"+
					",wsbxfwlj,wsb1pl,li_1_jhg,li_2_jhg,PT_ZSB,PT_DX_ZSB,FT_DX,ft_dx_add,PT_D2X,PT_XX_ZSB,FT_XX,ft_xx_add,ZSBF_XXXYL,ZSBF_XXXSSLL,ZSBF_XXXLLLJ"+
					"  from  PC_RPD_U_THIN_WATER_AUTO_T a where 1=1 ";
			sql += " and a.bbsj between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by bbsj";
			Object[] pumptable = null;
			List<Object[]> pumpList = zhuWaterPumpDao.searchZhuPump(sql);

			String[][] dates = DateBean.getReportTime("time10", rq);
			if(pumpList !=null && pumpList.size()>0){
				int i=0;
				for(String[] dateList :dates){
					 obj= new JSONObject();
					 
					 for(String data:dateList){
						 
						 pumptable=null;
						 dataflg = 0;
							for(Object[] pump:pumpList){
								obj = new JSONObject();
								
								if(data.toString().equals(pump[1].toString())){
									dataflg = 1;
									pumptable =pump;
							}
						}	
								 if(dataflg == 1){
									 obj.put("rpd_u_thin_water_auto_id", pumptable[0]);
									 obj.put("cjsj", data.substring(11, 16));
									 obj.put("ZSBF_ZDY",CommonsUtil.getClearNullData(pumptable[2]));
									 obj.put("pt_101a",CommonsUtil.getClearNullData(pumptable[3]));
									 obj.put("pt_101b",CommonsUtil.getClearNullData(pumptable[4]));
									 obj.put("TT_101",CommonsUtil.getClearNullData(pumptable[5]));
									 obj.put("zsb1dl",CommonsUtil.getClearNullData(pumptable[6]));
									 obj.put("pt_102a",CommonsUtil.getClearNullData(pumptable[7]));
									 obj.put("pt_102b",CommonsUtil.getClearNullData(pumptable[8]));
									 obj.put("TT_102",CommonsUtil.getClearNullData(pumptable[9]));
									 obj.put("zsb2dl",CommonsUtil.getClearNullData(pumptable[10]));
									 obj.put("sc12_zsb_alm_sv",CommonsUtil.getClearNullData(pumptable[11]));
									 obj.put("pt_103a",CommonsUtil.getClearNullData(pumptable[12]));
									 obj.put("pt_103b",CommonsUtil.getClearNullData(pumptable[13]));
									 obj.put("TT_103",CommonsUtil.getClearNullData(pumptable[14]));
									 obj.put("zsb3dl",CommonsUtil.getClearNullData(pumptable[15]));
									 obj.put("pt_104a",CommonsUtil.getClearNullData(pumptable[16]));
									 obj.put("pt_104b",CommonsUtil.getClearNullData(pumptable[17]));
									 obj.put("TT_104",CommonsUtil.getClearNullData(pumptable[18]));
									 obj.put("zsb4dl",CommonsUtil.getClearNullData(pumptable[19]));
									 obj.put("sc34_zsb_alm_sv",CommonsUtil.getClearNullData(pumptable[20]));
									 obj.put("FT_WSB",CommonsUtil.getClearNullData(pumptable[21]));									 
									 obj.put("wsbxfwlj",CommonsUtil.getClearNullData(pumptable[22]));
									 if(i==0){
										 ARWS = CommonsUtil.getDoubleData(pumptable[22]);
									 }
									 if(i==24){
										 BRWS = CommonsUtil.getDoubleData(pumptable[22]);
									 }
									 obj.put("wsb1pl",CommonsUtil.getClearNullData(pumptable[23]));
									 obj.put("li_1_jhg",CommonsUtil.getClearNullData(pumptable[24]));
									 obj.put("li_2_jhg",CommonsUtil.getClearNullData(pumptable[25]));
									 obj.put("PT_WSB",CommonsUtil.getClearNullData(pumptable[26]));
									 obj.put("PT_DX_ZSB",CommonsUtil.getClearNullData(pumptable[27]));
									 obj.put("FT_DX",CommonsUtil.getClearNullData(pumptable[28]));
									 obj.put("ft_dx_add",CommonsUtil.getClearNullData(pumptable[29]));
									 if(i==0){
										 ARZS = CommonsUtil.getDoubleData(pumptable[29]);
									 }
									 if(i==24){
										 BRZS = CommonsUtil.getDoubleData(pumptable[29]);
									 }
									 obj.put("PT_D2X",CommonsUtil.getClearNullData(pumptable[30]));
									 obj.put("PT_XX_ZSB",CommonsUtil.getClearNullData(pumptable[31]));
									 obj.put("FT_XX",CommonsUtil.getClearNullData(pumptable[32]));
									 obj.put("ft_xx_add",CommonsUtil.getClearNullData(pumptable[33]));
									 if(i==0){
										 ARZS1 = CommonsUtil.getDoubleData(pumptable[33]);
									 }
									 if(i==24){
										 BRZS1 = CommonsUtil.getDoubleData(pumptable[33]);
									 }
									 obj.put("ZSBF_XXXYL",CommonsUtil.getClearNullData(pumptable[34]));
									 obj.put("ZSBF_XXXSSLL",CommonsUtil.getClearNullData(pumptable[35]));
									 obj.put("ZSBF_XXXLLLJ",CommonsUtil.getClearNullData(pumptable[36]));
									 if(i==0){
										 ARZS2 = CommonsUtil.getDoubleData(pumptable[36]);
									 }
									 if(i==24){
										 BRZS2 = CommonsUtil.getDoubleData(pumptable[36]);
									 }
								 }else{
									 obj.put("rpd_u_thin_water_auto_id", "");
									 obj.put("cjsj", data.substring(11, 16));
									 obj.put("ZSBF_ZDY","");
									 obj.put("pt_101a","");
									 obj.put("pt_101b","");
									 obj.put("TT_101","");
									 obj.put("zsb1dl","");
									 obj.put("pt_102a","");
									 obj.put("pt_102b","");
									 obj.put("TT_102","");
									 obj.put("zsb2dl","");
									 obj.put("sc12_zsb_alm_sv","");
									 obj.put("pt_103a","");
									 obj.put("pt_103b","");
									 obj.put("TT_103","");
									 obj.put("zsb3dl","");
									 obj.put("pt_104a","");
									 obj.put("pt_104b","");
									 obj.put("TT_104","");
									 obj.put("zsb4dl","");
									 obj.put("sc34_zsb_alm_sv","");
									 obj.put("FT_WSB","");
									 obj.put("wsbxfwlj","");
									 obj.put("wsb1pl","");
									 obj.put("li_1_jhg","");
									 obj.put("li_2_jhg","");
									 obj.put("PT_WSB","");
									 obj.put("PT_DX_ZSB","");
									 obj.put("FT_DX","");
									 obj.put("ft_dx_add","");
									 obj.put("PT_D2X","");
									 obj.put("PT_XX_ZSB","");
									 obj.put("FT_XX","");
									 obj.put("ft_xx_add","");
									 obj.put("ZSBF_XXXYL","");
									 obj.put("ZSBF_XXXSSLL","");
									 obj.put("ZSBF_XXXLLLJ","");
								 }
								 i++;
								 arr.add(obj);
					 }
				}
			}else{
				for(String[] dataList :dates){
					for(String data:dataList){
						obj=new JSONObject();
						obj.put("rpd_u_thin_water_auto_id","");
						 obj.put("cjsj", data.substring(11, 16));
						 obj.put("ZSBF_ZDY","");
						 obj.put("pt_101a","");
						 obj.put("pt_101b","");
						 obj.put("TT_101","");
						 obj.put("zsb1dl","");
						 obj.put("pt_102a","");
						 obj.put("pt_102b","");
						 obj.put("TT_102","");
						 obj.put("zsb2dl","");
						 obj.put("sc12_zsb_alm_sv","");
						 obj.put("pt_103a","");
						 obj.put("pt_103b","");
						 obj.put("TT_103","");
						 obj.put("zsb3dl","");
						 obj.put("pt_104a","");
						 obj.put("pt_104b","");
						 obj.put("TT_104","");
						 obj.put("zsb4dl","");
						 obj.put("sc34_zsb_alm_sv","");
						 obj.put("FT_WSB","");
						 obj.put("wsbxfwlj","");
						 obj.put("wsb1pl","");
						 obj.put("li_1_jhg","");
						 obj.put("li_2_jhg","");
						 obj.put("PT_WSB","");
						 obj.put("PT_DX_ZSB","");
						 obj.put("FT_DX","");
						 obj.put("ft_dx_add","");
						 obj.put("PT_D2X","");
						 obj.put("PT_XX_ZSB","");
						 obj.put("FT_XX","");
						 obj.put("ft_xx_add","");
						 obj.put("ZSBF_XXXYL","");
						 obj.put("ZSBF_XXXSSLL","");
						 obj.put("ZSBF_XXXLLLJ","");
						 arr.add(obj);
					 }
				}
			}
			
			obj1.put("RWS",String.format("%.2f",BRWS - ARWS));
			obj1.put("RZS",String.format("%.2f",BRZS - ARZS));
			obj1.put("RZS1",String.format("%.2f",BRZS1 - ARZS1));
			obj1.put("RZS2",String.format("%.2f",BRZS2 - ARZS2));
			arr1.add(0,arr);
			arr1.add(1,obj1);
			return arr1;
		}

		@Override
		public List<PcRpdUThinWaterAutoT> SreachPumpAuto(String id, String date)throws Exception {
			String hql = "from PcRpdUThinWaterAutoT a   where 1=1"; 
			if(id !=null && !"".equals(id)){
				hql += " and a.rpdUThinWaterAutoId  ='"+id+"'";
			}
			if(date !=null && !"".equals(date)){
				hql += " and a.bbsj = '"+date+"'";
			}
			return zhuWaterPumpDao.SreachPumpAuto(hql);
		}

		@Override
		public boolean updatePumpAuto(PcRpdUThinWaterAutoT waterA)
				throws Exception {
			// TODO Auto-generated method stub
			return zhuWaterPumpDao.updatePumpAuto(waterA);
		}
		public int searchCalcNum() {
			// TODO Auto-generated method stub
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = zhuWaterPumpDao.searchZhuPump(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			return Integer.parseInt(calcNum);
		}
		public List<Object[]> searchWSCLOther(String rq,String fields) throws Exception {
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = zhuWaterPumpDao.searchZhuPump(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
			String sql = "select " + fields + " from PC_RPD_U_THIN_WATER_AUTO_T u where u.BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by u.BBSJ";
			List<Object[]> yyList = zhuWaterPumpDao.searchZhuPump(sql);
			return yyList;
		}
		
}
