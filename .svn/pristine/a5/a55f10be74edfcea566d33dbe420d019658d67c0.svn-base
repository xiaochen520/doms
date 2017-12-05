package com.echo.applet.ergogram;

import com.echo.util.StringUtils;

public class DetailErgogramApplet extends ErgogramApplet
{
	public void init()
	{
		super.init();
		
	}

	/**
	 * 创建chart,对父类的覆盖
	 */
	protected void createChart()
	{
		Ergogram chart = new DetailErgogram();
		this.chart = chart;
	}
	
	/**
	 * 初始化详细工图图形数据
	 */
	protected int createData()
	{
		int result=0;
		//初始化图形数据
		String[] strArr1,strArr2;
		ErgogramData ergData ;
		strArr1 = StringUtils.split(getParameter("datas"), "|");
		if (strArr1!=null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
				ergData = DetailErgogramData.create(strArr1[i]);
				if (ergData!=null)
				{
					strArr2 = StringUtils.split(strArr1[i],";");
					strArr2 = StringUtils.split(strArr2[1],",");
					for(int j = 3;j<strArr2.length;j++)//前面三个分别是对象名称、时间、主图数据
					{
						((DetailErgogramData)ergData).addDataEx(strArr2[j]);
					}
					result=((Ergogram)chart).addSeries(ergData);
				}
			}
		}
		return result;
		
	}
	

}
