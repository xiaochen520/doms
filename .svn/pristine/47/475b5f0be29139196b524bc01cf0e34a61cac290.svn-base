package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcRpdGasDailyT;
import com.echo.dto.PcRpdGasDailybT;
import com.echo.dto.PcRpdThinWellwT;
import com.echo.dto.PcRpdThinWellwbT;

public interface GasDailyWHService {


	public	HashMap<String, Object> searchData(String oilName,String groupTeam, String stationName, String maniName,
				String wellName, String stime, String etime, int pageNo,String sort, String order, int rowsPerpage, String totalNum,String deptype);
	public List<PcRpdGasDailyT> searchCheckOnly(String orgId, String rq,String rpdId,String name)throws Exception;

	public boolean saveOrUpdate(PcRpdGasDailyT rpdT)throws  Exception;

	public boolean removeDataRPD(String rpdId ,String deptype)throws Exception;

	public String searchWellID(String wellNameEdit);
	public List<PcRpdGasDailybT> searchCheckbOnly(String wellid, String rq,String rpdBId,String name)throws Exception;
	public boolean saveOrUpdateB(PcRpdGasDailybT rpdT)throws Exception;
	public List<String> Dataready (String proceduresName, String date, String param) throws Exception;
	public List<String> Automate (String proceduresName, String date,String userid,String param) throws Exception;
	public String searchOrgId(String wellname)throws Exception;


}
