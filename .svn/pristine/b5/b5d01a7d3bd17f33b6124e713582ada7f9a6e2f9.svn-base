package com.echo.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.IpghdbDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdIpSegmentT;
import com.echo.dto.PcCdOildrillingStationT;
import com.echo.service.IpghdbService;

public class IpghdbServiceImpl implements IpghdbService{
	private IpghdbDao ipghdbDao;
	private PageDao pageDao;
	

	public void setIpghdbDao(IpghdbDao ipghdbDao) {
		this.ipghdbDao = ipghdbDao;
	}

	public IpghdbDao getIpghdbDao() {
		return ipghdbDao;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public HashMap<String, Object> queryIpghdb(String totalNumFlag,int pageNo,String sort,String order,int rowsPerpage)throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select ";
		String formTable = " from pc_cd_ip_segment_v t where 1=1";
		String totalNum = "select count(*)";

		String[] cloumnsName = {"segment_id","segment_code1","segment_code2","segment_name","ip_segment","ip_start","ip_end","is_used","used_count","explanation","remark","rlast_oper","rlast_odate"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += "to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE,";
			}else{
				kk=kk+cloumnsName[m]+",";
			}
		}
		kk = kk.substring(0, kk.length()-1);
		String ipInfo = cloums +kk+ formTable;
		if (!"".equals(totalNumFlag)) {
			ipInfo = cloums + " segment_code1,segment_code2,segment_name,ip_segment,ip_start,ip_end,is_used,used_count,explanation,remark " +formTable;
		}
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
		if(!"".equals(sort) && !"segment_name".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					ipInfo +=" order by t." + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			ipInfo +=" order by nlssort(t.segment_code1, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		
		List<Object[]> lo = null;
		if ("export".equals(totalNumFlag)) {
			lo = ipghdbDao.queryInfo(ipInfo);
			if (lo != null && 0 < lo.size()) {
				for (Object[] o : lo) {
					tjo = new JSONObject();
					for (int i = 0; i < o.length; i++) {
						if (o[i] == null) {
							o[i] = "";
						}
						tjo.put(cloumnsName[i], o[i].toString());
					}
				}
			}
		}else{
		//计算分页
			PageControl control = new PageControl();
			//当前页
			control.setInt_num(rowsPerpage);
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			
			lo = ipghdbDao.queryIpghdb(ipInfo,start,rowsPerpage);
			if (lo != null && 0 < lo.size()) {
				// 生成树节点json文档
				jsonArr = new JSONArray();
				for (Object[] o : lo) {
					tjo = new JSONObject();
					for (int i = 0; i < o.length; i++) {
						if (o[i] == null) {
							o[i] = "";
						}
						tjo.put(cloumnsName[i], o[i].toString());
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
	
	
	
	

	
	
	
	public boolean removeIpSeg(String arg){
		String sql = " DELETE from pc_cd_ip_segment_t d where d.segment_id = '"+arg+"'";
		
		return ipghdbDao.removeIpSeg(sql);
	}

	public List<PcCdOildrillingStationT> serachOild(String oildid,String oildname) throws Exception {
		String hql = "FROM PcCdOildrillingStationT R WHERE 1=1 ";
		
		if(oildid != null && !"".equals(oildid)){
			hql += "AND R.oildrillingStationid = '"+oildid+"'";
		}
		
		if(oildname != null && !"".equals(oildname)){
			
			hql += "AND R.oildrillingStationName = '"+oildname+"'";
		}
		
		List<PcCdOildrillingStationT> oildList = ipghdbDao.searchOild(hql);
		
		return oildList;
	}

	public List<PcCdIpSegmentT> searchIpSegByCode2(String segment_code2) throws Exception {
		List<PcCdIpSegmentT> ipSegList = null;
		String  hql = "from   PcCdIpSegmentT  o where 1=1";
		if(segment_code2 !=null  && !"".equals(segment_code2)){
			hql += " and  o.segmentCode2 = '"+segment_code2+"'";
		}
		ipSegList  =  ipghdbDao.searchIpSegByCode2(hql);
		return ipSegList;
	}

	public boolean addIpSeg(PcCdIpSegmentT ipSeg) throws Exception {
		
		return ipghdbDao.save(ipSeg);
	}

	public List<PcCdIpSegmentT> serachIpSegById(String segment_id, String segment_code2)
			throws Exception {
		List<PcCdIpSegmentT> ipSegList;
		String  hql = " from  PcCdIpSegmentT  o where 1=1";
		if(segment_id !=null && !"".equals(segment_id)){
			hql +="  and o.segmentId = '"+segment_id+"' ";
		}
		if(segment_code2 !=null && !"".equals(segment_code2)){
			hql += "and o.segmentCode2 = '"+segment_code2+"'";
		}
		ipSegList = ipghdbDao.searchIpSeg(hql);
		return ipSegList;
	}

	public boolean updateIpSeg(PcCdIpSegmentT ipSeg) throws Exception {
		boolean flag = true;
		try {
			flag = ipghdbDao.updateIpSeg(ipSeg);
		} catch (Exception e) {
			flag = false;
		}
		
		return false;
	}

}
