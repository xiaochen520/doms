package com.echo.dao;
import java.util.List;

import com.echo.dto.PcCdIpSegmentT;
import com.echo.dto.PcCdOildrillingStationT;

public interface IpghdbDao {

	/**
	 * 查看单井信息
	 * @param user
	 * @return
	 */
	public List<Object[]> queryIpghdb(String ipInfo,int start,int pagesize);

	public boolean removeIpSeg(String sql);

	public List<PcCdOildrillingStationT> searchOild(String hql) throws Exception;

	public List<PcCdIpSegmentT> searchIpSegByCode2(String hql) throws Exception;

	public boolean save(PcCdIpSegmentT ipSeg) throws Exception;

	public List<PcCdIpSegmentT> searchIpSeg(String hql) throws  Exception;

	public boolean updateIpSeg(PcCdIpSegmentT ipSeg) throws Exception;

	public List<Object[]> queryInfo(String ipInfo)throws Exception;
	

}
