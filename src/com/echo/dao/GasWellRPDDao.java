package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdGasWelldT;

public interface GasWellRPDDao {

public 	int getCounts(String totalNum);

public List<Object[]> searchGWRPD(final String sql, final int start, final int rowsPerpage,final String[] cloumnsName);

public List<Object[]> searchGWRPDE(String sql);

public List<Object[]> searchWelID(String sql);

public boolean saveOrupdateGasRPDWH(PcRpdGasWelldT wh)throws Exception;

public List<PcRpdGasWelldT> searchOnly(String hql)throws Exception;

public boolean removeGasRPDWH(String sql)throws Exception;

}
