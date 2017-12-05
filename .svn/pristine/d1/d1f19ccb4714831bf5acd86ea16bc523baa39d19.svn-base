package com.echo.applet.ergogram;

import java.util.Vector;

import com.echo.util.StringUtils;

public class DetailErgogramData extends ErgogramData
{
	Vector<float[]> detailDataX=new Vector<float[]>();
	Vector<float[]> detailDataY=new Vector<float[]>();

	public static ErgogramData create(String str)
	{
		/*此段代码基本上是父类中的拷贝，除了new DetailErgogramData；以及新加了addDataEx();的调用*/
		if (str == null) return null;
		DetailErgogramData result = null;
		String [] strArr1 = StringUtils.split(str, ";");
		//System.out.println(str);
		if (str != null && strArr1.length >= 1)
		{
			//System.out.println(strArr1.length);
			result = new DetailErgogramData();
			String []strArr2 = StringUtils.split(strArr1[0], ",");
			if (strArr2 != null || strArr2.length >= 3)
			{
				result.setWellNo(strArr2[0]);
				result.setTime(strArr2[1]);
				result.setData(strArr2[2]);
				for(int i = 3;i<strArr2.length;i++)
				{
					result.addDataEx(strArr2[i]);
				}
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

	public int addDataEx(String data)
	{
		if(data==null || data.trim().length()<1) return -1;
		float[][] fData = deCode(data);
		if(detailDataX==null) detailDataX =new Vector<float[]>();
		if(detailDataX==null) detailDataX =new Vector<float[]>();
		
		if (fData != null)
		{
			detailDataX.add(fData[0]);
			detailDataY.add(fData[1]);
			for (int i = 0; i < fData[0].length; i++)
			{
				// 获取图形数据的横坐标最大最小值
				if (fData[0][i] > maxX)
				{
					maxX = fData[0][i];
				} else if (fData[0][i] < minX)
				{
					minX = fData[0][i];
				}

				// 获取图形数据的纵坐标最大最小值
				if (fData[1][i] > maxY)
				{
					maxY = fData[1][i];
				} else if (fData[1][i] < minY)
				{
					minY = fData[1][i];
				}

			}
		}

		return detailDataX.size();
	}


}
