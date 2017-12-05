package com.echo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateObjUtil {
	
	
	/**
	 * 将时间点添加4分59秒
	 * @param time 时间点
	 * @return
	 * @author yanlong
	 */
	public static String[] timeUtil(String time){
		
		String[] newTime = new String[2];
		Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。

		//当前时间类型	
		 SimpleDateFormat  nowFmt =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		//开始时间
		String startTime = time + ":00";
		newTime[0] = startTime;
		try {
			date = nowFmt.parse(startTime);
			cal.setTime(date);
			//为指定时间加299秒
			cal.add(Calendar.MINUTE, 10);
			newTime[1] = nowFmt.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newTime;
	}
	/**
	 * 获取每隔月的天数个月
	 * @return timeArr 起始时间  距现在2个月
	 */
	public static List<DateBean> getDateM(String date ,String klineType){
		List<DateBean> dateList = new ArrayList<DateBean>();
		int[] timeArr = null;
		if("1440".equals(klineType)){
			timeArr = new int[5];
		}else if("10080".equals(klineType)){
			timeArr = new int[12];
		}
		
		int month = 0;
		int year = 0;
		String newdate = "";
		int dateOfMonth =0;
		//		当前时间类型	
		DateFormat nowFmt =  new SimpleDateFormat("yyyy-MM");
		//使用默认时区和语言环境获得一个日历。
		Calendar cal = Calendar.getInstance();
		Date endDate=null;
		for(int i = 0; i< timeArr.length;i++){
			
			try {
				DateBean dbean = new DateBean();
				
				endDate = nowFmt.parse(date);
				cal.setTime(endDate);
				//为指定时间减2个月
				cal.add(Calendar.MONTH, -i);
				
				newdate = nowFmt.format(cal.getTime());
				year = Integer.parseInt(newdate.substring(0, 4));
				month = Integer.parseInt(newdate.substring(5, 7)); 
				dbean.setDataYear(year);
				dbean.setDataMonth(month);
				cal.set(Calendar.YEAR,year); 
				
				cal.set(Calendar.MONTH, month-1);//Java月份才0开始算  6代表上一个月7月 
				
				dateOfMonth = cal.getActualMaximum(Calendar.DATE); 
				
				dbean.setDayOfMonth(dateOfMonth);
				//timeArr[i] = dateOfMonth;
				
				
				dateList.add(dbean);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		List<DateBean> newDateList = new ArrayList<DateBean>();
		for(int i = dateList.size() - 1; i >= 0 ; i--){
			newDateList.add(dateList.get(i));
		}
	
		
		return newDateList;
	}
	
	
	/**
	 * 获取所需 年月 周数和 日期
	 * @return timeArr 起始时间  距现在2个月
	 */
	public static List<DateBean> getDateWeek(String stdate ,String enddate ,String klineType){
		DateFormat nowFmt =  new SimpleDateFormat("yyyy-MM-dd");
		List<DateBean> dateList = new ArrayList<DateBean>();
		//今年
		int nowYear = Integer.parseInt(enddate.substring(0, 4));
		//去年
		int beforeYear = nowYear -1;
		
		
		Date endDate = null;
		Date startDate = null;
		try {
			//结束日期
			endDate = nowFmt.parse(enddate);
			//开始日期
			startDate = nowFmt.parse(stdate);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(endDate);
			//今天为 今年的第几周
			int weekOfMonthCount = cal1.get(Calendar.WEEK_OF_YEAR); 
			
			for(int i = 0 ; i < weekOfMonthCount; i++){
				
				DateBean dbean = new DateBean();
				//d当前 年份
				dbean.setDataYear(nowYear);
				//当前第几周
				dbean.setWeekOfMonthCount(weekOfMonthCount - i);
				
				dbean.setFstDayOfWeekInMonth(getFirstDayOfWeek(nowYear,weekOfMonthCount - i));
				
				dateList.add(dbean);
			}
			
			//当年第一周的第一天
			String nowYearDay = getFirstDayOfWeek(nowYear,1);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(nowFmt.parse(nowYear+"-01-01"));
			//将当前年的第一天减去1天。。。 去年的最后一天
			cal.add(Calendar.DAY_OF_YEAR, -1);
			
			//去年最后一天
			String beforeYearDay = nowFmt.format(cal.getTime());
			 Date befDate = nowFmt.parse(beforeYearDay);
			
			 cal.setTime(befDate);
			//去年左后一天为多少周
			int berWeekOfMonthCount  = cal.get(Calendar.WEEK_OF_YEAR); 
			
			Calendar strcal = Calendar.getInstance();
			strcal.setTime(startDate);
			//去你那开始那天为第几周
			int WeekOfbeforeYear = strcal.get(Calendar.WEEK_OF_YEAR); 
			//去左后一周的第一天
			String befYearDayOfWeek = getFirstDayOfWeek(beforeYear,berWeekOfMonthCount);
			//如果今年第一周第一天和去年最后一周第一天相等
			if(befYearDayOfWeek.equals(nowYearDay)){
				
				berWeekOfMonthCount -= 1;
			}
			
			for(int i = berWeekOfMonthCount ; i >= WeekOfbeforeYear ; i--){
				DateBean dbean = new DateBean();
				//d当前 年份
				dbean.setDataYear(beforeYear);
				//当前第几周
				dbean.setWeekOfMonthCount(i);
				
				dbean.setFstDayOfWeekInMonth(getFirstDayOfWeek(beforeYear,i));
				
				dateList.add(dbean);
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<DateBean> newDateList = new ArrayList<DateBean>();
		for(int i = dateList.size() - 1; i >= 0 ; i--){
			newDateList.add(dateList.get(i));
		}
	
		
		return newDateList;
	}
	
	/**
	 * 获取每周第一天日期
	 * @param year 年份
	 * @param week 周数
	 * @return 改周第一天日期
	 */
	 public static String getFirstDayOfWeek(int year, int week) {
		   
		   Calendar firDay = Calendar.getInstance();
		 
		  // 先滚动到该年
		   firDay.set(Calendar.YEAR, year);
		 
		  // 滚动到周
		   firDay.set(Calendar.WEEK_OF_YEAR, week);
		 
		  // 得到该周第一天
		   firDay.set(Calendar.DAY_OF_WEEK, 2);
		   
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		         String firstDay = sdf.format(firDay.getTime());
		 
		  return firstDay;
		  }
	 
	 /*
	  * 获取相隔天数
	  */
	 public static int getDaySub(String rzDate, String tcDate) {
			long day=0;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    
	        Date beginDate;
	        Date endDate;
	        try
	        {
	            beginDate = format.parse(rzDate);
	            endDate= format.parse(tcDate);    
	            day=(beginDate.getTime()-endDate.getTime())/(24*60*60*1000);    
	            //System.out.println("相隔的天数="+day);   
	        } catch (ParseException e)
	        {
	            // TODO 自动生成 catch 块
	            e.printStackTrace();
	        }   
	        return (int) day;
		}

}


