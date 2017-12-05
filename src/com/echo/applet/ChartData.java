package com.echo.applet;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

public abstract class ChartData implements IChart
{
	public int save(File file, String fileType)
	{
		int result = 0;
		return result;
	}

	public int print()
	{
		int result = 0;
		return result;
	}


	/**
	 * 根据实际的最大、最小值、极值、超出最值比例以及刻度数量计算合适的刻度标签序列
	 * @param max 实际的最大值
	 * @param min 实际的最小值
	 * @param maxLimit 最大值极限
	 * @param minLimit 最小值极限
	 * @param blankScale 允许的超出最大、最小值的比例
	 * @param refNum 刻度数量
	 * @return 刻度标签序列
	 */
	public static float[] calculateReferrenceValue(float max, float min, float maxLimit,float minLimit,float blankScale, int refNum)
	{
		System.out.println(max+"---"+min);
		if(max>140||max<=min){
			max=140;
		}
		if(min>140||min<=0){
			min=0;
		}
		if (refNum <=0) return new float[]{min,max};
		if (blankScale <= 0.01) return calculateReferrenceValue(max,min,refNum);
		
		float[] result = new float[refNum];
		float blank = (max-min)* blankScale/100f;
		float tMax = Math.min(max + blank, Math.max(max, maxLimit));
		float tMin = Math.max(min - blank, Math.min(min, minLimit));
		float average = (tMax - tMin)/(refNum - 1);
		float scale=1f;
		
		if (average==0) return new float[]{0.9f*max,max,1.1f*max};
		
		float step=average;
		if (step < 0)
		{
			step *= -1f;
			float tmp = tMax;
			tMax = tMin;
			tMin = tmp;
		}

		while (step >= 10 )
		{
			step *= 0.1f;
			scale *= 10f;
		}
		while (step < 1)
		{
			step *= 10f;
			scale *= 0.1f;
		}
		
		while (true)
		{
			if (Math.abs((Math.round(step) - step))/step <= 1f / (refNum - 1))
			{
				//step = Math.round(step) * scale;
				break;
			}
			step *= 10f;
			scale *= 0.1f;
		}
		
		//step *= scale;
		step = Math.round(average / scale)*scale;
		float middle;
		if(scale<1f)
		{
			middle=Math.round((max+min) / 2 / scale)*scale;
		}
		else
		{
			middle=Math.round((max+min) / 2);
		}
		
		while(true)
		{
			tMin = middle - step * (refNum - 1) / 2;
			tMax = middle + step * (refNum - 1) / 2;
			if ((tMin >= min)||(tMax <= max))
			{
				step += scale;
				continue;
			}
			break;
		}
		
		if ((tMin <= minLimit && min >= minLimit ) && (tMax >= maxLimit && max <= maxLimit ))
		{
			return calculateReferrenceValue(max,min,refNum);
		}
		else if(tMin <= minLimit && min >= minLimit)
		{
			tMin = minLimit;
		}
		else if(tMax >= maxLimit && max <= maxLimit )
		{
			tMin=maxLimit - step * (refNum -1);
		}
		
		for (int i = 0; i < refNum; i++)
		{
			result[i] = tMin + step * i;
		}
			
		//System.out.println(scale);
		//System.out.println(step);
		
		return result;
	}
	
	/**
	 * 根据实际的最大、最小值、以及刻度数量计算合适的刻度标签序列
	 * @param max 实际的最大值
	 * @param min 实际的最小值
	 * @param refNum 刻度数量
	 * @return 刻度标签序列
	 */
	public static float[] calculateReferrenceValue(float max, float min, int refNum)
	{
		if (refNum <=0) return new float[]{min,max};
		
		float[] result = new float[refNum];
		float average = (max - min)/(refNum - 1);
		float scale=1f;
		
		if (average==0) return new float[]{0.9f*max,max,1.1f*max};
		
		float step=average;
		if (step < 0)
		{
			step *= -1f;
			float tmp = max;
			max = min;
			min = tmp;
		}

		while (step >= 10 )
		{
			step *= 0.1f;
			scale *= 10f;
		}
		while (step < 1)
		{
			step *= 10f;
			scale *= 0.1f;
		}
		
		while (true)
		{
			if (Math.abs((Math.round(step) - step))/step <= 1f / (refNum - 1))
			{
				break;
			}
			step *= 10f;
			scale *= 0.1f;
		}
		
		//step = Math.round(average / scale)*scale;
		if(scale >= 10f)
		{
			for(int i = 1; i < refNum-1; i++)
			{
				result[i]= Math.round(min + average * i);
			}
		}
		else
		{
			for(int i = 1; i < refNum-1; i++)
			{
				result[i]= Math.round(min + average * i / scale*10)*scale/10;
			}
		}
			
		result[0]=min;
		result[refNum-1]=max;
		
		//System.out.println(scale);
		//System.out.println(step);
		//System.out.println("max,min,refNum");

		return result;
	}
	
}
