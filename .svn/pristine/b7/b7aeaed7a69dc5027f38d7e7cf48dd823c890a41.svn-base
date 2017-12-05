package com.echo.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcCdManifoldT;


public interface ManifoldBasicService {
	
	public JSONObject queryManifoldBasicInfo(String stationNumber,String ghmc,String area,String hh,String jrbz1,String dyearC,String teamGROUP,int pageNo,String sort,String order,int rowsPerpage);
	public JSONArray queryOilSationInfo();
	public JSONArray queryAreablockInfo(String arg);
	public JSONArray queryStationInfo(String oilid,String areaid,String selecteValue);
	public JSONArray queryManifoldNameInfo(String arg,String ghorg);
	public boolean saveOrUpdate(String grzh,String boilerid2,String boilername2,String boilertype2,String boilercode2,String rlastoper2,String rlastodate2,String remark2);
	public boolean removeManifoldBasicInfo(String manifoldid , String orgid)throws Exception;
	public JSONObject cascadeInit();
	public List<Object[]> searchMani(String stationmc, String ghmc) throws Exception;
	public List<Object[]> searchManid(String ghmc)throws Exception;
	public List<Object[]> searchPidBymanifoldid(String id)throws Exception;
	public List<Object[]> searchManiCheck(String stationid, String ghmc) throws Exception;
	
	public List<PcCdManifoldT> searchManiByid(String id ,String  name)throws Exception;
	
	public boolean addManifold(PcCdManifoldT pcCdManifoldT) throws Exception;
	
	public boolean updateManifold(PcCdManifoldT pcCdManifoldT) throws Exception;
	
	public List<Object[]> searchStationByName(String stationName) throws Exception;
	public List<PcCdManifoldT> searchManiBydm(String ghdm2, String blockstationid) throws Exception;
	
	public HashMap<String, Object> searchOnExport(String oilationname,
			String blockstationname, String boilersName, String jrbz1,
			int pageNo, String sort, String order, int rowsPerpage,
			String dyearC,String totalNum) throws Exception;
	//区块ID
	public List<Object[]> searchQKID(String qkid,String ghdm2)throws Exception;
	public String searchQUKUAI(String manifoldid)throws Exception;
	public JSONArray queryTeamGInfo( String group)throws Exception;
	
}
