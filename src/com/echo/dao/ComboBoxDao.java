package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdSwitchInFlgT;

public interface ComboBoxDao {

	
	public List<PcCdSwitchInFlgT> searchSwitchInflg(String hql);
	
}
