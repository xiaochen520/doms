package com.echo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.echo.dao.U1s2gyDao;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU1WaterAutoT;
import com.echo.service.U1s2gyService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;


public class U1s2gyServiceImpl implements U1s2gyService{
	private U1s2gyDao u1s2gyDao;

	public void setU1s2gyDao(U1s2gyDao u1s2gyDao) {
		this.u1s2gyDao = u1s2gyDao;
	}
	
	public String searchCalcNum() throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1s2gyDao.searchEverySql(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		return calcNum;
	}
	
	public List<Object[]> searchExportData(String rq, String sql) throws Exception{
		if (!"".equals(rq)) {
			String calcNum = searchCalcNum();
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
			sql += " where BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
		}
		
		List<Object[]> list = (List<Object[]>) u1s2gyDao.searchEverySql(sql);
		return list;
	}
	
	public boolean updateU1s2gy(String arr, String zbr, String remarks, String h_id, String rq) throws Exception{
		String[] datas = arr.split("],");//行数据
		String[] hIds = h_id.split(",");//行数据id 按时间排序 第一个id 为值班人id
		String[] zhibanren = new String[1];
		zhibanren[0] = hIds[0];
		String[] iddatas = null;
		if(hIds != null && hIds.length >1){
			iddatas = new String[hIds.length -1];
			for(int i= 1; i<hIds.length ; i++){
				iddatas[i-1] = hIds[i];
			}
		}
		int jMax = datas.length - 1;
		PcRpdU1WaterAutoT pc = null;
		String id = null;
		int i = 0;
		for (int j = 0; j < jMax ; j++) {
			pc = new PcRpdU1WaterAutoT();
			String[] ds = datas[j].split(",");
			id = null;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String beforetime = DateBean.getBeforeDAYTime(rq);
			int calcNum = Integer.parseInt(searchCalcNum());
			int zeroIndex = -calcNum/2;
			String bbsjStr = beforetime +" "+ ds[13];
			if(j >= zeroIndex){
				bbsjStr = rq +" "+ ds[13];
			}
			Date bbsj = sf.parse(bbsjStr);
			String timeEndStr = ds[13];
			int iMax = iddatas.length - 1;
			for ( ; i <= iMax; ) {
				String idStr = iddatas[i];
				if ("00:00:00".indexOf(idStr) != -1) {
					id = idStr.split("&")[0];
					i++;
					break;
				}
				if (idStr.indexOf(timeEndStr) != -1) {
					id = idStr.split("&")[0];
					i++;
					break;
				}
			}
			if (!"".equals(id) && id != null) {
				String sql = "From PcRpdU1WaterAutoT a where a.rpdU1WaterAutoId='" + id + "'";
				try {
					pc = u1s2gyDao.SreachU1S1gy(sql).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
//			for (int i = 0; i < ints.length; i++) {
//				ints[i] = Integer.parseInt(ds[i]);
//			}
//			for (int i = 0; i < douValue.length; i++) {
//				douValue[i] = Double.parseDouble(ds[i + 6]);
//			}
			pc.setS2lsllj(CommonsUtil.isNullOrEmpty(ds[0]));
			pc.setS2hssllj(CommonsUtil.isNullOrEmpty(ds[1]));
			pc.setS2qf1lj(CommonsUtil.isNullOrEmpty(ds[2]));
			pc.setS2qf2lj(CommonsUtil.isNullOrEmpty(ds[3]));
			pc.setS2wssllj(CommonsUtil.isNullOrEmpty(ds[4]));
			pc.setS2rxlj(CommonsUtil.isNullOrEmpty(ds[5]));
			pc.setLit601b(CommonsUtil.isNullOrEmpty(ds[6]));
			pc.setLit601a(CommonsUtil.isNullOrEmpty(ds[7]));
			pc.setLit603(CommonsUtil.isNullOrEmpty(ds[8]));
			pc.setLit602a(CommonsUtil.isNullOrEmpty(ds[9]));
			pc.setLit602b(CommonsUtil.isNullOrEmpty(ds[10]));
			pc.setLt11015(CommonsUtil.isNullOrEmpty(ds[11]));
			pc.setLt11016(CommonsUtil.isNullOrEmpty(ds[12]));
			pc.setBbsj(bbsj);
			u1s2gyDao.updateU1s2gys(pc);
			
		}
		
		PcRpdReportMessageT rm = new PcRpdReportMessageT();
		id = zhibanren[0];
		if (id != null && !"".equals(id) && !"null".equals(id)) {
			String hql = "From PcRpdReportMessageT rm where rm.rpdReportMessageId = '" + id + "'";
			rm = u1s2gyDao.SearchReportMessageTs(hql).get(0);
			//比较日期 存在？
		}
		
//		rm.setRpdReportMessageId(id);
		rm.setZbr1(zbr);
		rm.setRq(DateBean.getStrDate(rq));
		rm.setBbmc("一号稠油联合处理站水二区罐液位及流量报表");
		rm.setBz(remarks);
		boolean upFlag = u1s2gyDao.updateReportMessages(rm);
		
		return upFlag;
	}
}
