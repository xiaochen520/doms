/**
 * DateTimeUtils.java
 * 
 * Copyright (c) 2003,���ؿƼ����޹�˾
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
 * ��������ʱ����صĹ�����
 */
public class DateTimeUtils
{

    /**
     * ��ʱ��ת��Ϊ�����ַ���(���뼶)
     * @param date Ҫ����ת����ʱ��
     * @return ת������ַ���
     */
    public static final String dateToMillis(Date date)
    {
        return dateFormat(date, "yyyyMMddHHmmssSSS");
    }

    /**
     * ��ָ���ĸ�ʽ�����ڽ��и�ʽ��
     * @param date ����
     * @param format ��ʽ
     * @return ��ʽ���������
     */
    public static final String dateFormat(Date date, String format)
    {
        SimpleDateFormat formatter = null;
        formatter = new SimpleDateFormat(format, Locale.getDefault());
        return formatter.format(date);
    }

    /**
     * ȡ�õ�ǰʱ���ַ���
     * @return ��ǰʱ���ַ���
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
     * ����ǰ��ʱ��ת��Ϊ�����ַ���(���뼶)
     * @return ת������ַ���
     */
    public static final String dateToMillis()
    {
        Date date = new Date();
        return dateToMillis(date);
    }

    /**
     * ���������ַ���ת����Calendar.
     * ���ｫʹ��ȱʡ��ʱ�������ҽ����е�ʱ���ֶζ���Ϊ���ʱ��"23:59:59.999"
     * ����������������ϢΪNULL���ֵ�򷵻ص�ǰ���ڣ�
     * �����������ȷ�򷵻�1970��1��1��
     * 
     * @param ymd ���������յ��ַ���,�磺"1970-1-1"��"1970/1/1"
     * @param flag �������ַ����ķָ�������������������Ϊ"-"��"/"
     * @return Calendarʵ��
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
     * ���������ַ���ת����Calendar.
     * ���ｫʹ��ȱʡ��ʱ�������ҽ����е�ʱ���ֶζ���Ϊ���ʱ��"23:59:59.999"
     * ����������������ϢΪNULL���ֵ�򷵻ص�ǰ���ڣ�
     * �����������ȷ�򷵻�1970��1��1��
     * 
     * @param ymd ���������յ��ַ���,�磺"1970-1-1"
     * @return Calendarʵ��
     * @see Calendar
     */
    public static final Calendar convert2Calendar(String ymd)
    {
        return convert2Calendar(ymd, "-");
    }

    /**
     * ���������ַ���ת����Date.
     * ���ｫʹ��ȱʡ��ʱ�������ҽ����е�ʱ���ֶζ���Ϊ���ʱ��"23:59:59.999"
     * ����������������ϢΪNULL���ֵ�򷵻ص�ǰ���ڣ�
     * �����������ȷ�򷵻�1970��1��1��
     * 
     * @param ymd ���������յ��ַ���,�磺"1970-1-1"��"1970/1/1"
     * @param flag �������ַ����ķָ�������������������Ϊ"-"��"/"
     * @return Dateʵ��
     * @see Date
     */
    public static final Date convert2Date(String ymd, String flag)
    {
        Calendar cal = convert2Calendar(ymd, flag);
        return cal.getTime();
    }

    /**
     * ���������ַ���ת����Date.
     * ���ｫʹ��ȱʡ��ʱ�������ҽ����е�ʱ���ֶζ���Ϊ���ʱ��"23:59:59.999"
     * ����������������ϢΪNULL���ֵ�򷵻ص�ǰ���ڣ�
     * �����������ȷ�򷵻�1970��1��1��
     * 
     * @param ymd ���������յ��ַ���,�磺"1970-1-1"
     * @return Dateʵ��
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
     * ��ָ����ʱ���ַ���ת����Date��ʽ
     * @param hms ʱ���ַ��� ʱ:��:��
     * @return Date��ʽ��ʱ��
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
     * ������ʱ���ַ���ת��ΪDate��������
     * Ĭ�Ϸָ���Ϊ","
     * @param datetime ��������ʱ����Ϣ���ַ���
     * @return Date��������
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
     * ��ָ���������л�ȡ�����Ϣ
     * @param date ����
     * @return ���
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
     * ��ȡϵͳ��ǰ���
     * @return ��ǰ���
     */
    public static int getYear()
    {
        return getYear(System.currentTimeMillis());
    }

    /**
     * ��ָ���������л�ȡ�·���Ϣ
     * @param date ����
     * @return �·�
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
     * ��ȡϵͳ��ǰ�·�
     * @return ��ǰ�·�
     */
    public static int getMonth()
    {
        return getMonth(System.currentTimeMillis());
    }

    /**
     * ��ָ���������л�ȡ����Ϣ
     * @param date ����
     * @return ��
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
     * ��ȡϵͳ��ǰ����
     * @return ��
     */
    public static int getDate()
    {
        return getDate(System.currentTimeMillis());
    }

    /**
     * ���ַ����л�ȡ����ʱ����Ϣ
     * �ַ�����ʽ��"��,��,��,ʱ,��,��"
     * @param date �����ַ���
     * @return ����ָ������ʱ��ĳ�����ֵ
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
     * ��ָ�����ַ����л�ȡ����ʱ����Ϣ
     * �ַ�����ʽ��"��,��,��,ʱ,��,��"
     * @param date ����ʱ���ַ���
     * @return ����
     */
    public static Date getTime(String date)
    {
        return new Date(getTimeInMillis(date));
    }

    /**
     * ��ָ���������л�ȡ��Ӧ����Ϣ
     * @param date ����
     * @param field ��Ϣ����(ͬCalendar�еĶ���)
     * @return ������ָ�����͵�ֵ
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
