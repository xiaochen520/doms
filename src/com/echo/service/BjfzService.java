package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdAlarmManagT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface BjfzService {

	public HashMap<String, Object> searchBjxzcx(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);

	
	public HashMap<String, Object> searchDxtscx(String startDate,
			String endDate, String jsbm, String jsr, String szd, String startDate2, String endDate2, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum);


	public JSONObject searchBjzsk(int pageNo, String sort, String order,
			int rowsPerpage);


	public boolean removeBjzsk(String zskid);

	public JSONObject searchDxyhgl(String depname, String username, int pageNo,
			String sort, String order, int rowsPerpage);


	public List<Object[]> searchUserChick(String username);


	public JSONArray searchUserDep();


	public List<Object[]> searchDatas(String sql);


	public boolean addUser(PcCdAlarmManagT alarmMana);


	 List<PcCdAlarmManagT> searchUser(String userid);


	public boolean updateUser(PcCdAlarmManagT user);


	public boolean removeUser(String userId);


	public JSONObject searchBjxzsp(String sqname, String zxzt, int pageNo,
			String sort, String order, int rowsPerpage);


}
