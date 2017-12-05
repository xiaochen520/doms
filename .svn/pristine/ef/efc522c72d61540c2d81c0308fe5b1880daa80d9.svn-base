package com.echo.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcCdBlCellT;
import com.echo.dto.PcCdDepartmentT;

public interface BoilerlineService {
	
	public List<PcCdDepartmentT> searchDepartment (String department,String departmentname) throws Exception;
	
	public JSONObject searchBlines (String txtdate ,int pageNo,String sort,String order,int rowsPerpage) throws Exception;
	
	public JSONObject  seachGLJKS (String cell_id) throws Exception;
	
	public boolean   removeAllWELLS(String jkname,String glname,String cellId) throws Exception;
	
	public List<String> getSAGDFP () throws Exception;
	
	public boolean addDep(PcCdDepartmentT dep) throws Exception;
	
	public boolean updateDep(PcCdDepartmentT dep) throws Exception;
	
	public boolean removeLX(String cell_id) throws Exception;
	public boolean removeLXchrid(String cell_id) throws Exception;
	
	public boolean removeSY(List<String> boileridlist ,List<String> boiler_cell_idlist,List<String> wellidlist ,List<String> well_cell_idlist  ) throws Exception;
	public JSONArray seachSelectData();
	public boolean addOrUpdateBoilerLine(PcCdBlCellT blCell)throws Exception;
	
	public boolean addBoilerLine(PcCdBlCellT blCell)throws Exception;
	public List<PcCdBlCellT> searchBoilerLineByName(String cellId,String name, String validDate) throws Exception;
}
