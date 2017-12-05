package com.echo.service.impl;

 
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.ComboBoxDao;
import com.echo.dto.PcCdSwitchInFlgT;
import com.echo.service.ComboBoxService;

public class ComboBoxServiceImpl implements ComboBoxService{
	
	private ComboBoxDao comboBoxDao;
	public void setComboBoxDao(ComboBoxDao comboBoxDao) {
		this.comboBoxDao = comboBoxDao;
	}
	@Override
	public JSONArray searchSwitchInflg()throws Exception {
		JSONArray arr = null;
		JSONObject obj;
		String hql = "FROM PcCdSwitchInFlgT WHERE 1=1";
		List<PcCdSwitchInFlgT> switchInFlgTs = comboBoxDao.searchSwitchInflg(hql);
		if(switchInFlgTs != null && switchInFlgTs.size()>0){
			arr = new JSONArray();
			for(PcCdSwitchInFlgT dto :switchInFlgTs){
				obj = new JSONObject();
				obj.put("id", dto.getSwitchInFlag());
				obj.put("text", dto.getDescription());
				arr.add(obj);
			}
		}
		return arr;
	}


}
