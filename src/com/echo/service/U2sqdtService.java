package com.echo.service;

import java.util.HashMap;

public interface U2sqdtService {

	public HashMap<String, Object> searchProducedRD(String startDate,String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum)throws Exception;
}
