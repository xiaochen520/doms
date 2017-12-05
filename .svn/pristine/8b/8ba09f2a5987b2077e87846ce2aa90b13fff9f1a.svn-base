package com.echo.applet.ergogram;

import java.util.Hashtable;

import com.echo.util.StringUtils;

public class ErgogramData
{
	String wellNo;
	String time;
	float[] dataX;
	float maxX=Float.MIN_VALUE;
	float minX=Float.MAX_VALUE;
	float[] dataY;
	float maxY=Float.MIN_VALUE;
	float minY=Float.MAX_VALUE;
	Hashtable<String, String> fields = new Hashtable<String,String>();

	float[][] deCode(String source)
	{
//		if (source == null || source.length() < 9)
//			return null;
//		int num = source.length() / 9;
//		
//		float[][] result = new float[2][num];
//		for (int i = 0; i < num; i++)
//		{
//			result[0][i] = Integer.parseInt(source.substring(i * 9, i * 9 + 4),
//					10) / 1000.0f;
//			result[1][i] = Integer.parseInt(source.substring(i * 9 + 4,
//					i * 9 + 9), 10) / 100.0f;
//			if (result[1][i] > 200) result[1][i] = result[1][i] - 500;
//		}

		if (source == null || source.length() < 8)
			return null;
		int num = source.length() / 8;
		
		float[][] result = new float[2][num];
		for (int i = 0; i < num; i++)
		{
			result[0][i] = Integer.parseInt(source.substring(i * 8, i * 8 + 3),
					10) / 100.0f;
			result[1][i] = Integer.parseInt(source.substring(i * 8 + 3,
					i * 8 + 8), 10) / 100.0f;
			if (result[1][i] > 200) result[1][i] = result[1][i] - 500;
		}		
		return result;
	}

	public void setWellNo(String wellNo)
	{
		this.wellNo = wellNo;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public int setData(String data)
	{
		int result = 0;
		float[][] fData = deCode(data);
		if (fData != null)
		{
			dataX = fData[0];
			
			dataY = fData[1];
			
			for (int i = 0; i < dataX.length; i++)
			{
				// 获取图形数据的横坐标最大最小值
				if (dataX[i] > maxX)
				{
					maxX = dataX[i];
				} else if (dataX[i] < minX)
				{
					minX = dataX[i];
				}
				
				// 获取图形数据的纵坐标最大最小值
				if (dataY[i] > maxY)
				{
					maxY = dataY[i];
				} else if (dataY[i] < minY)
				{
					minY = dataY[i];
				}

			}
		}
		return result;
	}

	public int addField(String fieldName, String fieldValue)
	{
		int result = 0;
		if (fieldName != null && fieldValue != null)
		{
			fields.put(fieldName, fieldValue);
			result = fields.size();
		}
		return result;
	}

	public static ErgogramData create(String str)
	{
		if (str == null) return null;
		ErgogramData result = null;
		String [] strArr1 = StringUtils.split(str, ";");
		//System.out.println(str);
		if (str != null && strArr1.length >= 1)
		{
			System.out.println(strArr1.length);
			result = new ErgogramData();
			String []strArr2 = StringUtils.split(strArr1[0], ",");
			if (strArr2 != null || strArr2.length >= 4)
			{
				result.setWellNo(strArr2[0]);
				result.setTime(strArr2[1]);
				result.setData(strArr2[2]);
			}
			for (int j = 1; j < strArr1.length; j++)
			{
				System.out.print(j);
				System.out.println(strArr1[j]);
				strArr2 = StringUtils.split(strArr1[j], ",");
				if (strArr2 != null && strArr2.length >= 2)
				{
					result.addField(strArr2[0], strArr2[1]);
				}
			}
		}
		return result;
	}

}
