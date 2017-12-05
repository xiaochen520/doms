package com.echo.service;


import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdZoneT;

public interface AreaInfoService {

	public List<PcCdAreaInfoT> searchRole(String areaid,String areaname) throws Exception;
	
	public JSONObject searchAreaInfo(String stationNumber,String boilersName,String area,int pageNo,String sort,String order,int rowsPerpage);

	public boolean removeStationInfo(String arg ,String org_id);

	public List<PcCdZoneT> searchZonefo(String name ,String id) throws Exception;

	public List<PcCdAreaInfoT> searchQkmc(String qkmc) throws Exception;

	public boolean addArea(PcCdAreaInfoT area) throws Exception;

	public List<PcCdAreaInfoT> searchByName(String qkid, String qkmc) throws Exception;

	public boolean updateArea(PcCdAreaInfoT area) throws Exception;

	public List<PcCdAreaInfoT> searchByQkmc_s(String qkmcS, String qkmcS2)throws Exception;

	


}
