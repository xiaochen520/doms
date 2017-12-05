package com.echo.service;



import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdWellSagdT;
import com.echo.dto.PcRpdWellSagddT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SagdService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public HashMap<String,Object>  searchSagdWell(String dyear,String jrbz,String prodStages,String statusValue,String totalNum,String qk,String zh,String jh,String gh,String name,int pageNo,String sort,String order,int rowsPerpage);
	
	public HashMap<String,Object>  searchSagdRDWell(String datastype,String qk,String zh,String jh,String gh,String name,int pageNo,String sort,String order,int rowsPerpage,String totalNumf);
	public HashMap<String,Object>  searchSagdRPD(String oilNumber,String ghmc,String well,String area,String startDate,String endDate, int pageNo,String sort,String order,int rowsPerpage,String totalNumf,String search,String bzhf) throws Exception;
	
	public HashMap<String,Object>  searchSagdRPD1(String statoilNumberion,String ghmc,String well,String area,String startDate,String endDate,int pageNo,String sort,String order,int rowsPerpage,String totalNumf,String search,String bzhf) throws Exception;
	
	
	
	
	public boolean addOrUpdateSagddRPD(PcRpdWellSagddT prws)throws Exception;
	public boolean removeStationInfo(String arg,String orgid);
	
	public JSONArray queryAreablockInfo(String arg);
	public JSONArray searchAreablockInfo(String arg);
	public JSONArray queryStationInfo(String areaid);
	public JSONArray queryWellNameInfo(String arg);
	public JSONArray queryDescInfo(String arg);
	public JSONObject cascadeInit();

	public List<PcCdWellSagdT> searchSagdname(String sagdid,String sagdwellname) throws Exception;

	public boolean addSagd(PcCdWellSagdT sagd) throws Exception;
	
	public boolean updateSagd(PcCdWellSagdT sagd) throws Exception;
	
	public List<PcCdServerNodeT>  getServerNode(String id) throws Exception;

	public boolean removeSagddRPD(String sagdId)throws Exception;
	public PcRpdWellSagddT searchSagrd(String sagddid) throws Exception;
	public List<Object[]> searchSagdRPDview(String sagddid,String[] cloumnsName) throws Exception;
	
	public List<Object[]> searchSagdLine(String sagdid,String prvarid,String startDate,String endDate)throws Exception;
	public List<Object[]> searchSagdLine2(String sagdid,String prvarid,String startDate,String endDate)throws Exception;

	public List<PcRpdWellSagddT> searchSagrdByName(String sagdid, String string) throws Exception;

	public JSONArray SearchPstages(String arg)throws Exception;
	public JSONArray SearchPmode(String arg)throws Exception;
	
	public List<Object[]> SearchMode(String id,String name)throws Exception;
	//public JSONArray SearchImode(String arg)throws Exception;

	public List<PcCdWellSagdT> searchSagdjhmc_s(String jhmcS)throws Exception;
	public JSONArray queryGridDataInfo(String wellName,String orgId)throws Exception;
	
	public List<String> dayreport(String txtDate)throws Exception;
	
	public JSONArray searchAutoCompleteData(String flg);

	public JSONArray queryGhmcInfo(String areaid);

	public JSONObject cascadeInit1();

	public JSONArray queryWellNameInfo1(String boilername);

	public JSONArray queryoilationnameInfo(String arg);

	public JSONArray searchoilationnameInfo(String arg);

	public HashMap<String, Object> searchSagdgh(String oilationname,
			String ghmc, String startDate, String endDate, int pageNo,
			String sort, String order, int rowsPerpage, String totalNum);

	public HashMap<String, Object> searchSagd183dt(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);

	public HashMap<String, Object> searchSagdnow(String oilationname,
			String ghmc, String startDate, String endDate, int pageNo,
			String sort, String order, int rowsPerpage, String totalNum);



	public HashMap<String, Object> searchSagdss(String oilNumber, String ghmc,
			String oilname, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);

	public HashMap<String, Object> searchGrglzb1(String txtDate, int pageNo,
			String sort, String order, int rowsPerpage, String totalNum);

	public JSONObject searchSagdghResults(String oilationname, String ghmc,
			String startDate, String endDate);


	public JSONArray queryBZInfo(String arg);

	public JSONObject searchSagd183Results(String startDate, String endDate);


	public HashMap<String, Object> searchZqPqqdt(String zqpqq,
			String startDate, String endDate, int pageNo, String sort,
			String order, int rowsPerpage, String totalNum);

	public JSONArray searchPqq();

	public HashMap<String, Object> searchYcgxdt(String ycgx, String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);

	public HashMap<String, Object> searchSagd1hrdt(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);

	public HashMap<String, Object> searchFc160wfdt(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);

	public HashMap<String, Object> searchSagd1lng(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);

	public HashMap<String, Object> searchSagd1qtjc(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);
	
	/**
	 * sagd 动态曲线
	 * @param wellName
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public JSONObject searchSagdCurve (String wellName,String startDate, String endDate) throws Exception;

}
