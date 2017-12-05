package com.echo.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcRpdBoilerSuperheatT;

public interface BoilerSuperheatRDService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> searchData(String qk,String zh,String jh,String gh,String name,String acceptunit,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNum,String group);


	public List<PcRpdBoilerSuperheatT> searchCross(String id, String boilerid,
			String name, String date) throws Exception;

	public boolean updatePsc(PcRpdBoilerSuperheatT psc)throws Exception;

	public boolean addPsc(PcRpdBoilerSuperheatT psc)throws Exception;

	public HashMap<String,Object> searchRPDData(String oilationname,String blockstationname,String group,String ghname,String boilersName,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNum);
	public boolean removeBoilerCrpssdRPD(String boilerCrossddid)throws Exception;


	public HashMap<String, Object> searchData1(String oilname,
			String blockname, String areablock, String ghname,
			String boilersname, String acceptunit, String stime, String etime,
			int pageNo, String sort, String order, int rowsPerpage,
			String totalNum, String group);


	public JSONObject searchBolierSupResults(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate);
}
