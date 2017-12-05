/**
 * StringUtils.java
 *
 * Copyright (c) 2003,安控科技有限公司
 * All rights reserved.
 *
 * @author Liudong
 * @version 1.2
 * Date:2003-09-03
 */
package com.echo.util;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * 封装了字符串操作的工具类
 */
public class StringUtils
{

	/**
	 * 字符串替换函数
	 * 2003-3-25修改oldString为空字符串直接返回line
	 * @param line 要进行操作的字符串
	 * @param oldString 要替换掉的字符串
	 * @param newString 准备替换的字符串
	 * @return 完成替换操作的字符串
	 */
	public static final String replace(
		String line,
		String oldString,
		String newString)
	{
		if (line == null)
		{
			return null;
		}
		if (oldString == null || newString == null)
		{
			return line;
		}

		if (oldString.equals(""))
		{
			return line;
		}

		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0)
		{
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i);
			buf.append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0)
			{
				buf.append(line2, j, i - j);
				buf.append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * Replaces all instances of oldString with newString in line with the
	 * added feature that matches of newString in oldString ignore case.
	 * The count paramater is set to the number of replaces performed.
	 *
	 * @param line the String to search to perform replacements on
	 * @param oldString the String that should be replaced by newString
	 * @param newString the String that will replace all instances of oldString
	 * @param count a value that will be updated with the number of replaces
	 *      performed.
	 *
	 * @return a String will all instances of oldString replaced by newString
	 */
	public static final String replaceIgnoreCase(
		String line,
		String oldString,
		String newString,
		int[] count)
	{
		if (line == null)
		{
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0)
		{
			int counter = 0;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i);
			buf.append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0)
			{
				counter++;
				buf.append(line2, j, i - j);
				buf.append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		}
		return line;
	}

	/**
	 * Replaces all instances of oldString with newString in line.
	 * The count Integer is updated with number of replaces.
	 *
	 * @param line the String to search to perform replacements on
	 * @param oldString the String that should be replaced by newString
	 * @param newString the String that will replace all instances of oldString
	 *
	 * @return a String will all instances of oldString replaced by newString
	 */
	public static final String replace(
		String line,
		String oldString,
		String newString,
		int[] count)
	{
		if (line == null)
		{
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0)
		{
			int counter = 0;
			counter++;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i);
			buf.append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0)
			{
				counter++;
				buf.append(line2, j, i - j);
				buf.append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		}
		return line;
	}

	/**
	 * 字符串替换函数,忽略大小写
	 * 2003-3-25修改在oldString为空字符串时直接返回line
	 * @param line 要进行操作的字符串
	 * @param oldString 要替换掉的字符串
	 * @param newString 准备替换的字符串
	 * @return 完成替换操作的字符串
	 */
	public static final String replaceIgnoreCase(
		String line,
		String oldString,
		String newString)
	{
		if (line == null)
		{
			return null;
		}
		if (oldString == null || newString == null)
		{
			return line;
		}

		if (oldString.equals(""))
		{
			return line;
		}

		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0)
		{
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i);
			buf.append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0)
			{
				buf.append(line2, j, i - j);
				buf.append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 判断两个char数组是否完全相等
	 * @param ch1 第一个char数组
	 * @param ch2 第二个char数组
	 * @return 是否完全相等
	 */
	public static final boolean charArryEquals(char[] ch1, char[] ch2)
	{
		if (ch1 == null && ch2 == null)
		{
			return true;
		}
		else if (ch1 == null || ch2 == null)
		{
			return false;
		}
		else
		{
			int len1 = ch1.length;
			int len2 = ch2.length;
			if (len1 == len2)
			{
				char c1, c2;
				for (int i = 0; i < len1; i++)
				{
					c1 = ch1[i];
					c2 = ch2[i];
					if (c1 != c2)
					{
						return false;
					}
				}
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	// XML special character
	private static final String QUOT_ENCODE = "&quot;"; // "
	private static final String AMP_ENCODE = "&amp;"; // &
	private static final String LT_ENCODE = "&lt;"; // <
	private static final String GT_ENCODE = "&gt;"; // >

	/**
	 * 去除字符串中的XML标志（如："<>&），使结果可以作为XML中内容
	 * @param string 要操作的字符串
	 * @return 去除XML标志的字符串
	 */
	public static final String escapeForXML(String string)
	{
		if (string == null)
		{
			return null;
		}
		char ch;
		char[] input = string.toCharArray();
		int len = input.length;
		if (len == 0)
		{
			return string;
		}
		StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (int i = 0; i < len; i++)
		{
			ch = input[i];
			switch (ch)
			{
				case '<' :
					out.append(LT_ENCODE);
					break;
				case '>' :
					out.append(GT_ENCODE);
					break;
				case '"' :
					out.append(QUOT_ENCODE);
					break;
				case '&' :
					out.append(AMP_ENCODE);
					break;
				default :
					try
					{
						out.append(ch);
					}
					catch(Exception e)
					{
					}
			}
		}
		return out.toString();
	}

	/**
	 * 反转字符串中的XML标志（如：&lt;等），使结果可以作为XML中内容
	 * @param string 要操作的字符串
	 * @return 反转XML标志的字符串
	 */
	public static final String unescapeFromXML(String string)
	{
		string = replace(string, LT_ENCODE, "<");
		string = replace(string, GT_ENCODE, ">");
		string = replace(string, QUOT_ENCODE, "\"");
		return replace(string, AMP_ENCODE, "&");
	}

	/**
	 * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
	 * &lt;table&gt;, etc) and converts the '&lt'' and '&gt;' characters to
	 * their HTML escape sequences.
	 *
	 * @param in the text to be converted.
	 * @return the input string with the characters '&lt;' and '&gt;' replaced
	 *  with their HTML escape sequences.
	 */
	public static final String escapeHTMLTags(String in)
	{
		if (in == null)
		{
			return null;
		}
		char ch;
		int i = 0;
		int last = 0;
		char[] input = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (; i < len; i++)
		{
			ch = input[i];
			if (ch > '>')
			{
				continue;
			}
			else if (ch == '<')
			{
				if (i > last)
				{
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(LT_ENCODE);
			}
			else if (ch == '>')
			{
				if (i > last)
				{
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(GT_ENCODE);
			}
		}
		if (last == 0)
		{
			return in;
		}
		if (i > last)
		{
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	private static final char[] zeroArray =
		"00000000000000000000".toCharArray();

	/**
	 * 将字符串从前面补0,如果输入字符串的长度比指定的长度长则返回原字符串
	 * @param string 要操作的字符串
	 * @param length 完成补0操作的后的长度，最长不能超过20个字符
	 * @return 完成补0操作的后的字符串
	 */
	public static final String zeroPadString(String string, int length)
	{
		if (string == null || string.length() > length)
		{
			return string;
		}
		StringBuffer buf = new StringBuffer(length);
		buf.append(zeroArray, 0, length - string.length());
		buf.append(string);
		return buf.toString();
	}

	/**
	 * 返回字符串的前几个字符
	 * @param string 源字符串
	 * @param len 取出字符的个数
	 * @return 指定的部分字符串
	 */
	public static final String getFirstString(String string, int len)
	{
		if (string == null)
		{
			return null;
		}

		if (string.equals("") || len <= 0)
		{
			return "";
		}

		if (string.length() <= len)
		{
			return string;
		}
		else
		{
			return string.substring(0, len);
		}
	}

	/**
	 * 测试字符串的实际长度 （中文算两个字符）
	 * @param string 要进行计算的源字符串
	 * @return 字符串的实际长度
	 */
	public static final int getCNStringLen(String string)
	{
		int fLen = 0; //字符串的伪长度
		int tLen = 0; //字符串的真实长度
		int i = 0;

		if (string == null || string.equals(""))
		{
			tLen = 0;
		}
		else
		{
			fLen = string.length();
			for (i = 0; i < fLen; i++)
			{
				if ((int) string.charAt(i) > 255)
				{
					tLen += 2;
				}
				else
				{
					tLen++;
				}
			}
		}
		return tLen;
	}

	/**
	 * 取得字符串的前几个字符 （中文算两个字符）
	 * @param string 要操作的源字符串
	 * @param len 要取出的字符串的长度
	 * @return 最终取出的子字符串
	 */
	public static final String getFirstCNString(String string, int len)
	{
		int fLen = 0; //字符串的伪长度
		int tLen = 0; //字符串的真实长度
		int i = 0;

		if (getCNStringLen(string) < len)
		{
			return string;
		}
		else
		{
			while (tLen < len)
			{
				if ((int) string.charAt(fLen) > 255)
					tLen += 2;
				else
					tLen++;
				if (tLen <= len)
					fLen++;
			}
			return getFirstString(string, fLen);
		}
	}

	/**
	 * 从字符串的右边删除指定的字符串public
	 * 如果 flag 的长度大于 src 的长度返回空字符串("")
	 * 如果 src 的右侧有多个 flag 出现，则全部删除
	 * 如果 src为 null，则返回 null
	 * 如果 flag为 null或空，则不作处理直接返回src
	 * 如果 src 为空字符串("")，则返回空字符串("")
	 * @param string 源字符串
	 * @param flag 要删除的字符串
	 * @return 已经删除了指定字符的字符串
	 */
	public static final String rtrim(String string, String flag)
	{
		int lenSrc = 0;
		int lenFlag = 0;
		String tmpStr = null;
		boolean hasNext = true;
		if (string == null)
		{
			return null;
		}
		if (flag == null || flag.equals(""))
		{
			return string;
		}
		if (string.equals(""))
		{
			return "";
		}
		lenSrc = string.length();
		lenFlag = flag.length();
		if (lenFlag > lenSrc)
		{
			return "";
		}
		while (lenSrc >= lenFlag && hasNext)
		{
			tmpStr = string.substring(lenSrc - lenFlag);
			if (tmpStr.equals(flag))
			{
				hasNext = true;
				string = string.substring(0, lenSrc - lenFlag);
				lenSrc = string.length();
			}
			else
			{
				hasNext = false;
			}
		}
		return string;
	}

	/**
	 * 从字符串的左边删除指定的字符串public
	 * @param src 源字符串
	 * @param flag 要删除的字符串
	 * @return 已经删除了指定字符的字符串
	 * 如果 flag 的长度大于 src 的长度返回空字符串("")
	 * 如果 src 的左侧有多个 flag 出现，则全部删除
	 * 如果 src 为 null，则返回 null
	 * 如果 flag 为 null或空，则返回原字符串src
	 * 如果 src 为空字符串("")，则返回空字符串("")
	 */
	public static final String ltrim(String src, String flag)
	{
		int lenSrc = 0;
		int lenFlag = 0;
		String tmpStr = null;
		boolean hasNext = true;
		if (src == null)
		{
			return null;
		}
		if (flag == null || flag.equals(""))
		{
			return src;
		}
		if (src.equals(""))
		{
			return "";
		}
		lenSrc = src.length();
		lenFlag = flag.length();
		if (lenFlag > lenSrc)
		{
			return "";
		}
		while (lenSrc >= lenFlag && hasNext)
		{
			tmpStr = src.substring(0, lenFlag);
			if (tmpStr.equals(flag))
			{
				hasNext = true;
				src = src.substring(lenFlag);
				lenSrc = src.length();
			}
			else
			{
				hasNext = false;
			}
		}
		return src;
	}

	/**
	 * 按照指定的标志分割字符串到一个数组中
	 * @param string 要操作的源字符串
	 * @param flag 分割标志字符串
	 * @return 分割操作完成后的字符数组
	 */
	public static final String[] split(String string, String flag)
	{
		if (string == null)
		{
			return null;
		}
		if (string.equals(""))
		{
			return new String[] { string };
		}
		string = rtrim(string, flag);

		int size = 1;
		int i = 0;
		int len = string.length();
		while (i < len && i != -1)
		{
			i = string.indexOf(flag, i);
			if (i != -1)
			{
				size++;
				i++;
			}
		}

		String[] resultArray = new String[size];
		StringTokenizer tokenizer = new StringTokenizer(string, flag);
		i = 0;
		while (tokenizer.hasMoreTokens())
		{
			resultArray[i] = tokenizer.nextToken();
			i++;
		}
		return resultArray;
	}

	/**
	 * 按指定编码方式对字符串进行编码
	 * @param str 要操作的字符串
	 * @param enc 编码方式
	 * @return String 编码后的字符串
	 */
	public static final String unescapeString(String str, String enc)
	{
		if (str == null || str.equals(""))
		{
			return str;
		}

		int length = str.length();
		int bytelen = 0;
		byte[] bytes = new byte[length];
		int tempB1, tempB2;
		char tempChar;

		for (int i = 0; i < length; i++)
		{
			tempChar = str.charAt(i);
			if (tempChar == '+')
			{
				//加号必须变为空格
				bytes[bytelen++] = ' ';
				continue;
			}
			if (tempChar == '%')
			{
				tempChar = str.charAt(++i);
				if (tempChar != 'u')
				{
					//国标码
					tempB1 = Character.digit(tempChar, 16);
					tempChar = str.charAt(++i);
					tempB2 = Character.digit(tempChar, 16);
					bytes[bytelen++] = (byte) (tempB1 * 16 + tempB2);
				}
				else
				{
					//UNICODE码
					tempChar = str.charAt(++i);
					tempB1 = Character.digit(tempChar, 16);

					tempChar = str.charAt(++i);
					tempB1 = tempB1 * 16 + Character.digit(tempChar, 16);

					tempChar = str.charAt(++i);
					tempB1 = tempB1 * 16 + Character.digit(tempChar, 16);

					tempChar = str.charAt(++i);
					tempB1 = tempB1 * 16 + Character.digit(tempChar, 16);

					tempChar = (char) tempB1;
					byte[] tempbytes = null;

					try
					{
						tempbytes = ("" + tempChar).getBytes(enc);
					}
					catch (java.io.UnsupportedEncodingException f)
					{
						tempbytes = ("" + tempChar).getBytes();
					}

					bytes[bytelen++] = tempbytes[0];
					bytes[bytelen++] = tempbytes[1];
				}
			}
			else
			{
				bytes[bytelen++] = (byte) tempChar;
			}
		}

		if (bytelen < length)
		{
			byte[] trimValue = new byte[bytelen];
			System.arraycopy(bytes, 0, trimValue, 0, bytelen);
			bytes = trimValue;
		}

		try
		{
			return new String(bytes, enc);
		}
		catch (java.io.UnsupportedEncodingException ex)
		{
			return new String(bytes);
		}
	}

	/**
	 * 使用GBK对字符串进行编码
	 * @param str 要操作的字符串
	 * @return String 编码后的字符串
	 */
	public static final String unescapeString(String str)
	{
		return unescapeString(str, "GBK");
	}

	/**
	 * 将byte数组转换成16进制的字符串
	 * @param ba byte数组
	 * @return String 16进制的字符串
	 */
	public static final String toHex(byte[] ba)
	{
		if (ba == null)
		{
			return null;
		}

		int length = ba.length;
		if (length <= 0)
		{
			return "";
		}

		StringBuffer buf = new StringBuffer(length * 2);
		int i;

		for (i = 0; i < length; i++)
		{
			if (((int) ba[i] & 0XFF) < 0X10)
			{
				buf.append("0");
			}
			buf.append(Long.toString((int) ba[i] & 0XFF, 16));
		}
		return buf.toString();
	}

	/**
	 * 将16进制的字符串转换成byte数组
	 * @param hex 16进制字符串
	 * @return byte[] byte数组
	 */
	public static final byte[] toByteArray(String hex)
	{
		if (hex == null)
		{
			return null;
		}
		int len = hex.length();
		if (len == 0 || (len % 2) > 0)
		{
			return null;
		}
		byte[] result = new byte[len / 2];

		String tmp;
		int i = 0, j = 0;
		while (i < len)
		{
			tmp = hex.substring(i, i + 2);
			result[j] = (byte) Long.parseLong(tmp, 16);
			i += 2;
			j++;
		}
		return result;
	}

	/**
	 * 取字符串左数n个字符
	 * @param str 字符串
	 * @param len 要取字符个数
	 * @return String 返回的字符串
	 */
	public static final String left(String str, int len)
	{
		if (str == null || len <= 0)
		{
			return null;
		}
		if (str.equals("") || str.length() <= len)
		{
			return str;
		}
		else
		{
			return str.substring(0, len);
		}
	}

	/**
	 * 取字符串右数n个字符
	 * @param str 字符串
	 * @param len 要取字符个数
	 * @return String 返回子串
	 */
	public static final String right(String str, int len)
	{
		if (str == null || len <= 0)
		{
			return null;
		}
		if (str.equals("") || str.length() <= len)
		{
			return str;
		}
		else
		{
			return str.substring(len - 1, str.length());
		}
	}

	/**
	 * 取字符串中间的字符
	 * @param str 字符串
	 * @param start 起始位置
	 * @param len 字符个数
	 * @return String 返回字符
	 */
	public static final String mid(String str, int start, int len)
	{
		if (str == null || start <= 0 || len <= 0)
		{
			return null;
		}
		if (str.equals("") || str.length() <= start)
		{
			return "";
		}
		else if (str.length() < (start + len))
		{
			return str.substring(start - 1, str.length());
		}
		else
		{
			return str.substring(start - 1, start + len - 1);
		}
	}

	/**
	 * 判断两个字符串是否相等(如果都是null也认为是相等的)
	 * @param s1 第一个字符串
	 * @param s2 第二个字符串
	 * @return 两个字符串相等返回true,否则返回false
	 */
	public static final boolean isEquals(String s1, String s2)
	{
		if (s1 == null && s2 == null)
		{
			return true;
		}
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null))
		{
			return false;
		}
		if (!s1.equals(s2))
		{
			return false;
		}

		return true;
	}

	/**
	 * 判断两个字符串数组是否相等
	 * @param s1 第一个字符串数组
	 * @param s2 第二个字符串数组
	 * @return 是否相等
	 */
	public static boolean isEquals(String[] s1, String[] s2)
	{
		if (s1 == null && s2 == null)
		{
			return true;
		}
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null))
		{
			return false;
		}
		int len1 = s1.length;
		int len2 = s2.length;
		if (len1 != len2)
		{
			return false;
		}
		for (int i = 0; i < len1; i++)
		{
			if (!s1[i].equals(s2[i]))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 返回一个对象的字符串值，如果为空，则返回输入的第二个字符串值。
	 * @param src
	 * @param des
	 * @return
	 */
	public static String isNull(String src,String des)
	{
		if (src == null){
			return des;
		}
		else
		{
			return src ;
		}
	}

	public static Color parseColor(String str)
	{
		Color result = null;
		if (str!=null)
		{
			if ("#".equals(str.substring(0, 1)))
			{
			/*
			String strZero="000000";
			String strTmp = str.substring(1);
			int r =0,g=0,b=0;
			int len=6-str.length() + 1;//str中除"#"字符外的字符长度与6位颜色编码长度的差值.
			if (len <= 0)
			{
				//如果字符串长度已经足够6位,表明编码不需进行补0操作
			}
			else
			{
				strTmp = strZero.substring(0, len).concat(strTmp);
			}
			r = Integer.parseInt(strTmp.substring(0,2),16);
			g = Integer.parseInt(strTmp.substring(2,4),16);
			b = Integer.parseInt(strTmp.substring(4,6),16);
			result = new Color(r,g,b);
			*/
			
			result = Color.decode(str);
			}
		}
		return result;
	}

	public static Font parseFont(String fontName,  String fontSize)
	{
		return parseFont(fontName,Font.PLAIN,fontSize);
	}
	
	public static Font parseFont(String fontName, int fontStyle, String fontSize)
	{
		if (fontName == null || fontName.trim().length() == 0)
		{
			fontName = "宋体";
		}
		int size = parseInt(fontSize, 10);
		if (size <= 5)
		{
			size = 10;
		}
		if (fontStyle <0)
		{
			fontStyle =0;
		}
		if(fontStyle > 3)
		{
			fontStyle = 3;
		}
		Font result = new Font(fontName, fontStyle, size);
		return result;
	}

	public static int parseInt(String source, int def)
	{
		if (source == null || source.trim().length() == 0)
		{
			return def;
		}
		int result = def;
		try
		{
			result = Integer.parseInt(source);
		} catch (Exception e)
		{
		}
		return result;
	}

	public static float parseFloat(String source, float def)
	{
		if (source == null || source.trim().length() == 0)
		{
			return def;
		}
		float result = def;
		try
		{
			result = Float.parseFloat(source);
		} catch (Exception e)
		{
		}
		return result;
	}
	//oracle.sql.Clob类型转换成String类型
    public static String ClobToString(Clob clob) {
        String reString = "";
        Reader is = null;
        try {
            is = clob.getCharacterStream();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 得到流
        BufferedReader br = new BufferedReader(is);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        while (s != null) {
            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            try {
                s = br.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        reString = sb.toString();
        return reString;
    }
}
