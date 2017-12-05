package com.echo.service;

import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.echo.dto.PcRpdBoilerCommonT;

public interface SRGLRDService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> searchData(String qk,String zh,String jh,String gh,String name,String stime,String etime,String oilNmame,int pageNo,String sort,String order,int rowsPerpage,String totalNum);
	public HashMap<String,Object> searchRPData(String oilname,String areablock,String stationNumber,String group,String boilersName,String stime,String etime,String oilNmame ,String pageCode ,int pageNo,String sort,String order,int rowsPerpage,String totalNum);
	public List<Object[]> searchboilerRPDview(String rbid,String[] cloumnsName)throws Exception;
	public List<Object[]> searchboilerLine(String glid,String prvarid,String startDate,String endDate)throws Exception;
	public boolean removeBoilerCommondRP(String arg) throws Exception ;
	public List<Object[]> searchCommobid(String id , String boilerid,String name,String date)throws Exception;
	public List<PcRpdBoilerCommonT> searchCommobs(String id , String boilerid,String name,String date)throws Exception;
	public boolean updatePsgl(PcRpdBoilerCommonT psgl)throws Exception ;
	public boolean addPsgl(PcRpdBoilerCommonT psgl)throws Exception;
	public List<Object[]> searchggboilerRPDview(String rbid,String[] cloumnsName)throws Exception;
	public List<Object[]> searchGGboilerLine(String glid,String prvarid,String startDate,String endDate)throws Exception;
	public JSONArray queryOilding(String oilid)throws Exception;
	public JSONArray queryStation(String arg)throws Exception;
	public JSONArray queryBoiler(String arg)throws Exception;
	public JSONArray queryArea(String arg)throws Exception;
	public JSONArray queryStationInfo(String arg)throws Exception;
	public JSONArray queryBoilersNameInfo(String arg)throws Exception;
	public JSONObject cascadeInit()throws Exception;
	public String searchBlockName(String boilerName)throws Exception;
	
	public List<Object[]> searchArea(String bLOCKNAME)throws Exception;
	public String serachBoilerid(String boilerName)throws Exception;
	public HashMap<String, Object> searchRPData1(String oilname,
			String areablock, String stationNumber, String group,
			String boilersName, String stime, String etime, String oilNmame,
			String pageCode, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);
	public JSONObject searchSrglResults (String oilationname,
			String areablock, String blockstationname, String boilersName,
			String startDate, String endDate) throws Exception;
	public JSONObject searchSrglResults1(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate)throws Exception;
	public JSONObject searchBolierSupResults(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate) throws Exception;
	
}
