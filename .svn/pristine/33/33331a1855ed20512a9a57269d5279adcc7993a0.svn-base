package com.echo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdBoilerT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface BoilerBasicService {
	
	public JSONObject queryBoilerBasicInfo(String stationNumber,String boilersName,String area,String oilname,String ghname,String jrbz,String status_value,String dyear,int pageNo,String sort,String order,int rowsPerpage);
	public JSONArray queryOilSationInfo();
	public JSONArray queryAreablockInfo(String arg);
	public JSONArray queryStationInfo(String oilid,String areaid,String selecteValue);
	public JSONArray queryBoilersNameInfo(String arg,String pageid);
	public boolean addBoilerBasicInfo(String arg);
	public boolean saveOrUpdate(String yxz2,String grzh,String boilerid,String org_id,String boilername2,
			String boilertype2,String boilercode2,String rlastoper,Date rlastodate,String jrbz,String  scadaNode,String commissioningDate,
			String statusValue,String remark2)throws Exception;
	public boolean saveOrUpdate(PcCdBoilerT pcCdBoilerT,String flg)throws Exception;
	public boolean removeBoilerBasicInfo(String org_id,String bid);
	public JSONObject cascadeInit(String pageid);

	public JSONArray searchGroupInfo(String arg);
	public List<Object[]> searchBoilerByName(String boilerName) throws Exception;
	public List<Object[]> searchStationByName(String stationName) throws Exception;
	
	public List<PcCdBoilerT> searchBoilerByName(String boilerName,String id) throws Exception;
	public HashMap<String, Object> searchOnExport(String acceptunit,String blockstationname, String boilersName, String areablock,
			String jrbz1, String statusValue1,String dyear, String totalNum)throws Exception;
	
}
