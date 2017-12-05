package com.echo.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcCdOildrillingStationT;

public interface OilDrillingService {

	public JSONObject queryOilStation(String stationNumber,String boilersName,String area,int pageNo,String sort,String order,int rowsPerpage);

	public boolean removeStationInfo(String arg,String org_id);

	public List<PcCdOildrillingStationT> serachOild(String oildid, String oildname) throws Exception;

	public List<PcCdOildrillingStationT> searchstatinByName(String oildrillingStationname)throws  Exception;
	

	public boolean addOil(PcCdOildrillingStationT oild) throws Exception;

	public List<PcCdOildrillingStationT> serachOildById(String oildrillingstationid,String oildrillingstationname) throws Exception;

	public boolean updateSta(PcCdOildrillingStationT oild)throws Exception;

	

}
