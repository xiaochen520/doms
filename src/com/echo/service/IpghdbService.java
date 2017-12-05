package com.echo.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcCdIpSegmentT;
import com.echo.dto.PcCdOildrillingStationT;

public interface IpghdbService {

	public HashMap<String, Object> queryIpghdb(String totalNum, int pageNo,String sort,String order,int rowsPerpage)throws Exception;

	public boolean removeIpSeg(String segment_id);

	public List<PcCdOildrillingStationT> serachOild(String oildid, String oildname) throws Exception;

	public List<PcCdIpSegmentT> searchIpSegByCode2(String segment_code2)throws  Exception;
	

	public boolean addIpSeg(PcCdIpSegmentT ipSeg) throws Exception;

	public List<PcCdIpSegmentT> serachIpSegById(String segment_id,String segment_code2) throws Exception;

	public boolean updateIpSeg(PcCdIpSegmentT ipSeg)throws Exception;

	

}
