package com.echo.service;

import java.util.List;

import com.echo.dto.PcCdBranchingT;
import com.echo.dto.PcCdWellSagdT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface BranchingBasicService {
	public JSONObject queryBranchingBasicInfo(String stationNumber,String boilersName,String area,int pageNo,String sort,String order,int rowsPerpage);
	public JSONArray queryCombinationStationName(String arg);
	public JSONArray queryBranchingNameInfo(String arg);
	public boolean saveOrUpdate(String grzh,String boilerid2,String boilername2,String boilertype2,String boilercode2,String rlastoper2,String rlastodate2,String remark2);
	public boolean removeBranch(String arg) throws Exception;
	
	public List<PcCdBranchingT> searchBranchs(String id,String name) throws Exception;
	
	public boolean addbranch(PcCdBranchingT pcCdBranchingT) throws Exception;
	
	public boolean updatebranch(PcCdBranchingT pcCdBranchingT) throws Exception;
	
}
