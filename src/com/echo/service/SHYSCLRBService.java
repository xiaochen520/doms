package com.echo.service;

import java.util.List;

import com.echo.dto.PcRpdRhsclT2T;
import com.echo.dto.PcRpdShysclT;

import net.sf.json.JSONArray;

public interface SHYSCLRBService {

	public JSONArray searchDataSet(String txtDate)throws Exception;

	public int searchCalcNum();

	public boolean updateData(PcRpdShysclT wt)throws Exception;

	public List<PcRpdShysclT> SreachCheckOn(String rpdid,String bbsj)throws Exception;

	public List<Object[]> searchExportData(String txtDate, String jZSCLMainSql)throws Exception;

	public List<Object[]> searchExportData(String punSql)throws Exception;

}
