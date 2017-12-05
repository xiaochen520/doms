package com.echo.applet.pieChart;

import java.util.Hashtable;


import com.echo.util.StringUtils;


public class PieChartData
{
	public static final int DATA_X_TIME = 2;
	public static final int DATA_X_NUMBER = 1;
	public static final int DATA_X_STRING = 3;
	
	
	String desc;
	String title;
	String aideTitle;
	String unit;
	float sum=0;
	Hashtable<String, Float> data = new Hashtable<String,Float>();
	
	public void clearDatas()
	{
		data.clear();
		sum = 0;
	}
	
	public int addData(String x,float y)
	{
		if(x==null) return -1;
		data.put(x, Float.valueOf(y));
		sum += y;
		return data.size();
	}

	public int setDatas(String strData)
	{
		if (strData == null || strData.trim().length()==0) return -1;

		int result = 0;
		String [] strArr1,strArr2;
		float f;
		if(data==null)
		{
			data = new Hashtable<String,Float>();
		}
		else
		{
			data.clear();
		}
		strArr1=StringUtils.split(strData, ";");
		if (strArr1 != null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
				strArr2 = StringUtils.split(strArr1[i], ",");
				if(strArr2!=null && strArr2.length >=2)
				{
					
					f = StringUtils.parseFloat(strArr2[1], 0f);
					addData(strArr2[0],f);
					// 获取图形数据的纵坐标最大最小值
				}

			}
		}

		return result;
	}

	public static PieChartData create(String str)
	{
		if (str == null || str.length() == 0) return null;

		PieChartData result = new PieChartData();
		String [] strArr1 = StringUtils.split(str, "|");

		//System.out.println(str);
		if (strArr1 != null && strArr1.length >1)
		{
			//System.out.println(strArr1.length);
			String []strArr2 = StringUtils.split(strArr1[0], ",");
			if (strArr2 != null || strArr2.length >= 4)
			{
				result.title = strArr2[0];
				result.aideTitle = strArr2[1];
				result.desc = strArr2[2];
				result.unit = strArr2[3];
			}
			result.setDatas(strArr1[1]);
		}
		return result;
	}

}
