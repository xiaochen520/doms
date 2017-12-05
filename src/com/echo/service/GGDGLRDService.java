package com.echo.service;

import java.util.HashMap;
import net.sf.json.JSONObject;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcRpdBoilerHighDryT;

public interface GGDGLRDService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> searchData(String oilationname,String areablock,String blockstationname,String ghname,String boilersName,String acceptunit,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String pageCode,String totalNum)throws Exception;
	public boolean saveOrUpdate(PcRpdBoilerHighDryT prbhd)throws Exception;
	public boolean removeGGDRDBoiler(String rpdBoilerHighDryId) throws Exception;
	public PcRpdBoilerHighDryT searchGGDRDBoiler(String rpdBoilerHighDryId)throws Exception;
	public PcCdBoilerT searchBoiler(String boilerid)throws Exception;
//	public HashMap<String,Object> searchRPData(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNum);
//	public List<Object[]> searchboilerRPDview(String rbid,String[] cloumnsName)throws Exception;
//	public List<Object[]> searchboilerLine(String glid,String prvarid,String startDate,String endDate)throws Exception;
//	public boolean removeBoilerCommondRP(String arg) throws Exception ;
//	public List<Object[]> searchCommobid(String id , String boilerid,String name,String date)throws Exception;
//	public List<PcRpdBoilerCommondT> searchCommobs(String id , String boilerid,String name,String date)throws Exception;
//	public boolean updatePsgl(PcRpdBoilerCommondT psgl)throws Exception ;
//	public boolean addPsgl(PcRpdBoilerCommondT psgl)throws Exception;
//	public List<Object[]> searchggboilerRPDview(String rbid,String[] cloumnsName)throws Exception;
//	public List<Object[]> searchGGboilerLine(String glid,String prvarid,String startDate,String endDate)throws Exception;
	public HashMap<String, Object> searchData1(String oilationname,
			String areablock, String blockstationname, String ghname,
			String boilersName, String acceptunit, String stime, String etime,
			int pageNo, String sort, String order, int rowsPerpage,
			String pageCode, String totalNum);
	public JSONObject searchGgdglResults(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate) throws Exception;
	public JSONObject searchGgdglResults1(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate)throws Exception;
}
