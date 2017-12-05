package com.echo.dao;

import java.util.List;

import net.sf.json.JSONArray;
import java.util.List;
import com.echo.dto.PcCdControllerT;
import com.echo.dto.PcCdInstruMentationT;

public interface ControllerDao {
	public List<Object[]> searchControl(final String instruInfo,final int start, final int pagesize,final String[] cloumnsName);
	public List<Object[]> queryInfo(String hql);
	public boolean removeController(String arg);
	public List<PcCdControllerT> searchControllerId(String hql)throws Exception;
	public boolean SaveOrUpdateControl(PcCdControllerT im)throws Exception;
	public List<Object[]> searchYBNameQuery(String sql) throws Exception;
}
