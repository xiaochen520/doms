/**
 * DateTimeUtils.java
 * 
 * Copyright (c) 2003,安控科技有限公司
 * All rights reserved.
 * 
 * @author WangChunQiao
 * @version 1.0
 * Date:2003-9-10
 */
package com.echo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 处理日期时间相关的工具类
 */
public class DateTimeUtils
{

    /**
     * 将时间转换为数字字符串(毫秒级)
     * @param date 要进行转换的时间
     * @return 转换后的字符串
     */
    public static final String dateToMillis(Date date)
    {
        return dateFormat(date, "yyyyMMddHHmmssSSS");
    }

    /**
     * 按指定的格式对日期进行格式化
     * @param date 日期
     * @param format 格式
     * @return 格式化后的日期
     */
    public static final String dateFormat(Date date, String format)
    {
        SimpleDateFormat formatter = null;
        formatter = new SimpleDateFormat(format, Locale.getDefault());
        return formatter.format(date);
    }

    /**
     * 取得当前时间字符串
     * @return 当前时间字符串
     */
    public static final String getNowString()
    {
        Date now = new Date();
        SimpleDateFormat formatter = null;
        formatter =
            new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS",
                Locale.getDefault());
        return formatter.format(now);
    }

    /**
     * 将当前的时间转换为数字字符串(毫秒级)
     * @return 转换后的字符串
     */
    public static final String dateToMillis()
    {
        Date date = new Date();
        return dateToMillis(date);
    }

    /**
     * 将年月日字符串转换成Calendar.
     * 这里将使用缺省的时区，并且将所有的时间字段都置为最大时间"23:59:59.999"
     * 如果传入的年月日信息为NULL或空值则返回当前日期，
     * 如果参数不正确则返回1970年1月1日
     * 
     * @param ymd 代表年月日的字符串,如："1970-1-1"或"1970/1/1"
     * @param flag 年月日字符串的分割符，如在上面的例子中为"-"或"/"
     * @return Calendar实例
     * @see Calendar
     */
    public static final Calendar convert2Calendar(String ymd, String flag)
    {
        if (ymd == null || flag == null || "".equals(ymd) || "".equals(flag))
        {
            return Calendar.getInstance(Locale.getDefault());
        }
        String[] dates = StringUtils.split(ymd, flag);
        int year = 1970;
        int month = 1;
        int date = 1;
        if (dates != null && dates.length == 3)
        {
            try
            {
                year = Integer.parseInt(dates[0]);
                month = Integer.parseInt(dates[1]) - 1;
                if (month < 0 || month > 11)
                {
                    throw new Exception();
                }
                date = Integer.parseInt(dates[2]);
                if (date < 1 || date > 31)
                {
                    throw new Exception();
                }
            }
            catch (Exception ex)
            {
                year = 1970;
                month = 1;
                date = 1;
            }
        }
        Calendar ret = Calendar.getInstance(Locale.getDefault());
        ret.set(year, month, date, 23, 59, 59);
        ret.set(Calendar.MILLISECOND, 999);
        return ret;
    }

    /**
     * 将年月日字符串转换成Calendar.
     * 这里将使用缺省的时区，并且将所有的时间字段都置为最大时间"23:59:59.999"
     * 如果传入的年月日信息为NULL或空值则返回当前日期，
     * 如果参数不正确则返回1970年1月1日
     * 
     * @param ymd 代表年月日的字符串,如："1970-1-1"
     * @return Calendar实例
     * @see Calendar
     */
    public static final Calendar convert2Calendar(String ymd)
    {
        return convert2Calendar(ymd, "-");
    }

    /**
     * 将年月日字符串转换成Date.
     * 这里将使用缺省的时区，并且将所有的时间字段都置为最大时间"23:59:59.999"
     * 如果传入的年月日信息为NULL或空值则返回当前日期，
     * 如果参数不正确则返回1970年1月1日
     * 
     * @param ymd 代表年月日的字符串,如："1970-1-1"或"1970/1/1"
     * @param flag 年月日字符串的分割符，如在上面的例子中为"-"或"/"
     * @return Date实例
     * @see Date
     */
    public static final Date convert2Date(String ymd, String flag)
    {
        Calendar cal = convert2Calendar(ymd, flag);
        return cal.getTime();
    }

    /**
     * 将年月日字符串转换成Date.
     * 这里将使用缺省的时区，并且将所有的时间字段都置为最大时间"23:59:59.999"
     * 如果传入的年月日信息为NULL或空值则返回当前日期，
     * 如果参数不正确则返回1970年1月1日
     * 
     * @param ymd 代表年月日的字符串,如："1970-1-1"
     * @return Date实例
     * @see Date
     */
    public static final Date convert2Date(String ymd)
    {
        return convert2Date(ymd, "-");
    }

    public static final Calendar convert2TimeCalendar(String hms)
    {
        if (hms == null || "".equals(hms))
        {
            return null;
        }
        String[] tmp = StringUtils.split(hms, ":");
        if (tmp == null || tmp.length != 3)
        {
            return null;
        }
        int h = 0;
        int m = 0;
        int s = 0;
        try
        {
            h = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            s = Integer.parseInt(tmp[2]);
        }
        catch (Exception ex)
        {
            return null;
        }
        Calendar today = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.set(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DATE),
            h,
            m,
            s);
        return cal;
    }

    /**
     * 将指定的时间字符串转换成Date格式
     * @param hms 时间字符串 时:分:秒
     * @return Date格式的时间
     */
    public static final Date convert2Time(String hms)
    {
        Calendar cal = convert2TimeCalendar(hms);
        if(cal == null)
        {
            return null;
        }
        else
        {
            return cal.getTime();
        }
    }

    /**
     * 将日期时间字符串转换为Date类型数据
     * 默认分隔符为","
     * @param datetime 保护日期时间信息的字符串
     * @return Date类型数据
     */
    public static final Date convert2DateTime(String datetime)
    {
        if (datetime == null || "".equals(datetime))
        {
            return null;
        }
        String[] tmp = StringUtils.split(datetime, ",");
        if (tmp == null || tmp.length != 6)
        {
            return null;
        }
        int year = 0;
        int month = 0;
        int date = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        try
        {
            year = Integer.parseInt(tmp[0]);
            month = Integer.parseInt(tmp[1]);
            date = Integer.parseInt(tmp[2]);
            hour = Integer.parseInt(tmp[3]);
            minute = Integer.parseInt(tmp[4]);
            second = Integer.parseInt(tmp[5]);
        }
        catch (Exception ex)
        {
            return null;
        }
        Calendar ret = Calendar.getInstance();
        ret.set(year, month, date, hour, minute, second);
        return ret.getTime();
    }

    /**
     * 从指定的日期中获取年份信息
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date)
    {
        return getField(date, Calendar.YEAR);
    }

    public static int getYear(long date)
    {
        return getYear(new Date(date));
    }

    /**
     * 获取系统当前年份
     * @return 当前年份
     */
    public static int getYear()
    {
        return getYear(System.currentTimeMillis());
    }

    /**
     * 从指定的日期中获取月份信息
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date)
    {
        return getField(date, Calendar.MONTH) + 1;
    }

    public static int getMonth(long date)
    {
        return getMonth(new Date(date));
    }

    /**
     * 获取系统当前月份
     * @return 当前月份
     */
    public static int getMonth()
    {
        return getMonth(System.currentTimeMillis());
    }

    /**
     * 从指定的日期中获取日信息
     * @param date 日期
     * @return 日
     */
    public static int getDate(Date date)
    {
        return getField(date, Calendar.DATE);
    }

    public static int getDate(long date)
    {
        return getDate(new Date(date));
    }

    /**
     * 获取系统当前的日
     * @return 日
     */
    public static int getDate()
    {
        return getDate(System.currentTimeMillis());
    }

    /**
     * 从字符串中获取日期时间信息
     * 字符串格式："年,月,日,时,分,秒"
     * @param date 日期字符串
     * @return 代表指定日期时间的长整型值
     */
    public static long getTimeInMillis(String date)
    {
        if (date == null || "".equals(date))
        {
            return 0L;
        }
        String[] tmp = null;
        tmp = StringUtils.split(date, ",");
        if (tmp == null)
        {
            return 0L;
        }
        int count = tmp.length;
        if (count != 6)
        {
            return 0L;
        }
        int tmpYear = 0;
        int tmpMonth = 0;
        int tmpDate = 0;
        int tmpHour = 0;
        int tmpMinute = 0;
        int tmpSecond = 0;
        try
        {
            tmpYear = Integer.parseInt(tmp[0]);
            tmpMonth = Integer.parseInt(tmp[1]) - 1;
            tmpDate = Integer.parseInt(tmp[2]);
            tmpHour = Integer.parseInt(tmp[3]);
            tmpMinute = Integer.parseInt(tmp[4]);
            tmpSecond = Integer.parseInt(tmp[5]);
        }
        catch (Exception ex)
        {
            return 0L;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(tmpYear, tmpMonth, tmpDate, tmpHour, tmpMinute, tmpSecond);
        return cal.getTimeInMillis();
    }

    /**
     * 从指定的字符串中获取日期时间信息
     * 字符串格式："年,月,日,时,分,秒"
     * @param date 日期时间字符串
     * @return 日期
     */
    public static Date getTime(String date)
    {
        return new Date(getTimeInMillis(date));
    }

    /**
     * 从指定的日期中获取相应的信息
     * @param date 日期
     * @param field 信息类型(同Calendar中的定义)
     * @return 日期中指定类型的值
     */
    public static int getField(Date date, int field)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }
    
    public static String getDateStr()
    {
    	return dateFormat(new Date(),"yyyy-MM-dd");
    }

	public static String getTimeStr()
	{
		return dateFormat(new Date(),"HH:mm:ss");
	}

	public static String getYesterdayStr()
	{
		return dateFormat(new Date(System.currentTimeMillis() - 24*3600000) ,"yyyy-MM-dd");
	}
}
