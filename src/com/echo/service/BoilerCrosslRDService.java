package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcRpdBoilerSuperheatT;

public interface BoilerCrosslRDService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> searchData(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNum);


	public List<PcRpdBoilerSuperheatT> searchCross(String id, String boilerid,
			String name, String date) throws Exception;

	public boolean updatePsc(PcRpdBoilerSuperheatT psc)throws Exception;

	public boolean addPsc(PcRpdBoilerSuperheatT psc)throws Exception;

	public HashMap<String,Object> searchRPDData(String oilationname,String blockstationname,String group,String ghname,String boilersName,String acceptunit,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNum);
	public boolean removeBoilerCrpssdRPD(String boilerCrossddid)throws Exception;


	public List<Object[]> queryOilStation(String blockstationName);


	public PcRpdBoilerSuperheatT searchCross(String cid);


	public String searchBlockName(String boilerName);


	public HashMap<String, Object> searchRPDData1(String block_name,
			String blockstationname, String group, String ghname,
			String boilersName, String acceptunit, String stime, String etime,
			int pageNo, String sort, String order, int rowsPerpage,
			String totalNum);
}
