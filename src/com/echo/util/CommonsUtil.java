package com.echo.util;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;

/**
 * 提供通用方法类
 * @author yanloong E-mail:darklimb@hotmail.com
 * @version 创建时间：2013 FRI 07, 2013 14:56:20 PM
 *
 */

public class CommonsUtil {
	
	
	public static List<Integer> getStandardValue(){
		List<Integer> standard = new ArrayList<Integer>();
		PropertiesConfig pc = new PropertiesConfig();//写在adction 里 在查询拼接sql及导出报表时都要用到 
//		#  is '沉降罐出水含油'
//		cjgcshy=6000
		String cjgcshy = pc.getSystemConfiguration("cjgcshy");
		if(cjgcshy != null && !"".equals(cjgcshy)){
			standard.add(Integer.parseInt(cjgcshy));
		}else{
			standard.add(0);
		}
//		#  is '调储罐进口含油'
//		tcgjkhy=3000
		String tcgjkhy = pc.getSystemConfiguration("tcgjkhy");
		if(tcgjkhy != null && !"".equals(tcgjkhy)){
			standard.add(Integer.parseInt(tcgjkhy));
		}else{
			standard.add(0);
		}
//		#  is '调储罐进口悬浮'
//		tcgjkxf=300
		String tcgjkxf = pc.getSystemConfiguration("tcgjkxf");
		if(tcgjkxf != null && !"".equals(tcgjkxf)){
			standard.add(Integer.parseInt(tcgjkxf));
		}else{
			standard.add(0);
		}
//		#  is '调储罐出口含油'
//		tcgckhy=150
		String tcgckhy = pc.getSystemConfiguration("tcgckhy");
		if(tcgckhy != null && !"".equals(tcgckhy)){
			standard.add(Integer.parseInt(tcgckhy));
		}else{
			standard.add(0);
		}
//		#  is '调储罐出口悬浮'
//		tcgckxf=150
		String tcgckxf = pc.getSystemConfiguration("tcgckxf");
		if(tcgckxf != null && !"".equals(tcgckxf)){
			standard.add(Integer.parseInt(tcgckxf));
		}else{
			standard.add(0);
		}
//		#  is '反应罐出口含油'
//		fygckhy=15
		String fygckhy = pc.getSystemConfiguration("fygckhy");
		if(fygckhy != null && !"".equals(fygckhy)){
			standard.add(Integer.parseInt(fygckhy));
		}else{
			standard.add(0);
		}
//		#  is '反应罐出口悬浮'
//		fygckxf=15
		String fygckxf = pc.getSystemConfiguration("fygckxf");
		if(fygckxf != null && !"".equals(fygckxf)){
			standard.add(Integer.parseInt(fygckxf));
		}else{
			standard.add(0);
		}
//		#  is '一级过滤器出口含油'
//		yjglqckhy=5
		String yjglqckhy = pc.getSystemConfiguration("yjglqckhy");
		if(yjglqckhy != null && !"".equals(yjglqckhy)){
			standard.add(Integer.parseInt(yjglqckhy));
		}else{
			standard.add(0);
		}
//		#  is '一级过滤器出口悬浮'
//		yjglqckxf=5
		String yjglqckxf = pc.getSystemConfiguration("yjglqckxf");
		if(yjglqckxf != null && !"".equals(yjglqckxf)){
			standard.add(Integer.parseInt(yjglqckxf));
		}else{
			standard.add(0);
		}
//		#  is '二级过滤器出口含油'
//		ejglqckhy=2
		String ejglqckhy = pc.getSystemConfiguration("ejglqckhy");
		if(ejglqckhy != null && !"".equals(ejglqckhy)){
			standard.add(Integer.parseInt(ejglqckhy));
		}else{
			standard.add(0);
		}
//		#  is '二级过滤器出口悬浮'
//		ejglqckxf=5
		String ejglqckxf = pc.getSystemConfiguration("ejglqckxf");
		if(ejglqckxf != null && !"".equals(ejglqckxf)){
			standard.add(Integer.parseInt(ejglqckxf));
		}else{
			standard.add(0);
		}
		return standard;
	}
	public static boolean getDataBoolNull(Object data){
		boolean flag = false;
		
		if( data !=null && !"".equals(data) && ! data.equals("undefined")){
			flag = true;
		}
		return flag;
	}
	/**
	 * 将空数据去除
	 * @param data
	 * @return
	 */
	public static String getClearNullData(Object data){
		String str = "";
		if(data != null && !"".equals(data.toString()) && !"null".equals(data.toString()) && !"undefined".equals(data.toString())){
			str = data.toString();
		}
		return str;
		
	}
	
	public static String getClearNullData(Object[] data){
		String str = "";
		if(data != null && !"".equals(data.toString()) && !"null".equals(data.toString()) && !"undefined".equals(data.toString())){
			str = data.toString();
		}
		return str;
		
	}
	
	/**
	 * 将数据转换为DOUBLE类型
	 * @param data
	 * @return
	 */
	public static double getDoubleData(Object data){
		double  newdata = 0.00;
		if(data != null && !"".equals(String.valueOf(data))){
			newdata = Double.parseDouble(String.valueOf(data));
		}
		return newdata;
	}
	
	
	public static double getDoubleData1(String data){
		double  newdata = 0.00;
		if(data != null && !"".equals(data)){
			newdata = Double.parseDouble(data);
		}
		return newdata;
	}
	
	/**
	 * 返回一位四舍五入小数
	 * @param data
	 * @return
	 */
	public static String getNotOneData(Object data){
		
		double sumdata = 0.0;
		if(data != null && !"".equals(data) && getDoubleData(data) != 0){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.1f", sumdata);
	}
	public static String getNotOneDataZ(Object data){
		
		double sumdata = 0.0;
		if(data != null && !"".equals(data)){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.1f", sumdata);
	}
	public  static String remUndef(Object data){
		double sumdata =0;
		if(data != null && !"".equals(data)){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.0f", sumdata);
	}
	public static String getZeroData(Object data){
		
		double sumdata = 0.0;
		if(data != null && !"".equals(data) ){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.1f", sumdata);
	}
	
	public static String getSumTwoData(Object data,Object data1){
		double sumdata = 0.00;
	
			sumdata = Double.parseDouble(getNotTwoData(data))+Double.parseDouble(getNotTwoData(data1));
			
			if(sumdata != 0 && sumdata != 0.0 && sumdata != 0.00){
				return String.format("%.2f", sumdata);
			}else{
				return "";
			}
		
		
	}
	
	/**
	 * 返回2位四舍五入小数
	 * @param data
	 * @return
	 */

	public static String getNotTwoData(Object data){
		
		double sumdata = 0.00;
		if(data != null && !"".equals(data) && getDoubleData(data) != 0 
				&& !"null".equals(data) && getDoubleData(data) != 0&& !"0".equals(data) && getDoubleData(data) != 0 && getDoubleData(data) != 0.00){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.2f", sumdata);
	}
	

	
	
	public static String getNotTwoData1(Object data){
		
		double sumdata = 0.00;
		if(data != null && !"".equals(data) && getDoubleData(data) != 0 && getDoubleData(data) != 0.00
				&& !"null".equals(data)){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.2f", sumdata);
	}
	public static String getNotTwoDataZero(Object data){
		
		double sumdata = 0.00;
		if(data != null && !"".equals(data)){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.2f", sumdata);
		}
	/**
	 * 返回2位四舍五入小数
	 * @param data
	 * @return
	 */
	public static String getNotTwoData(String data){
		
		double sumdata = 0;
		if(data != null && !"".equals(data) && getDoubleData(data) != 0){
			sumdata = getDoubleData(data);
		}else if("0".equals(String.valueOf(data))){
			return String.format("%.2f", sumdata);
		}else{
			return "";
		}
		return String.format("%.2f", sumdata);
	}
	
	/**
	 * 返回3位四舍五入小数
	 * @param data
	 * @return
	 */
	public static String getNotThreeData(Object data){
		
		double sumdata = 0.000;
		if(data != null && !"".equals(data) && getDoubleData(data) != 0 && getDoubleData(data) != 0.00){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.3f", sumdata);
	}
	
	public static String getNotOneData0(Object data){
		
		double sumdata = 0;
		if(data != null && !"".equals(data) && getDoubleData(data) != 0){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.0f", sumdata);
	}
	
	public static String getNotOneData(String data){
		
		double sumdata = 0;
		if(data != null && !"".equals(data) && getDoubleData(data) != 0 && getDoubleData(data) != 0.00){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.1f", sumdata);
	}
	/**
	 * 获取整数位数值
	 * @param data
	 * @return
	 */
	public static String getIntData(String data){
		double sumdata = 0;
		if(data != null && !"".equals(data)){
			sumdata = getDoubleData(data);
		}else{
			return "";
		}
		return String.format("%.0f", sumdata);
	}
	
	public static String getIntData(Object data){
		double sumdata = 0;
		if(data != null && !"".equals(data.toString())){
			sumdata = getDoubleData(data.toString());
		}else{
			return "";
		}
		return String.format("%.0f", sumdata);
	}
	
	
	public static String getRegulation1(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		if(yeardata != null && !"".equals(yeardata) && getDoubleData(yeardata) != 0){
			sumdata = getDoubleData(nowdata)/getDoubleData(yeardata)*1000;
		}
		
		return String.format("%.1f", sumdata);
	}
	
	public static String getRegulation2(Object H7,Object F7,Object B7){
		
		double sumdata = 0;
		if(B7 != null && !"".equals(B7) && getDoubleData(B7) != 0){
			sumdata = (getDoubleData(H7)+getDoubleData(F7))/getDoubleData(B7)*1000;
		}
		return String.format("%.1f", sumdata);
	}
	
	public static String getRegulation0(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		sumdata = getDoubleData(nowdata) - getDoubleData(yeardata);
		return String.format("%.0f", sumdata);
	}
	
	public static String getResultSum(Object nowdata,Object yeardata){		
		double sumdata = 0;
		sumdata = getDoubleData(nowdata) + getDoubleData(yeardata);
		return String.format("%.0f", sumdata);
	}
	

	
	public static String getRegulation2(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		if(nowdata != null && !"".equals(nowdata) && yeardata != null && !"".equals(yeardata)){
			sumdata = getDoubleData(nowdata) - getDoubleData(yeardata);
			return String.format("%.2f", sumdata);
		}
		return "";
		
	}
	public static String getRegulationZero(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		if(nowdata != null && !"".equals(nowdata) && yeardata != null && !"".equals(yeardata)){
			sumdata = getDoubleData(nowdata) - getDoubleData(yeardata);
			return String.format("%.0f", sumdata);
		}
		return "";
		
	}
	/*
	 * 保留整数
	 */
	public static String getRegulationInt(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		if(nowdata !=null &&  yeardata != null){
			sumdata = getDoubleData(nowdata) - getDoubleData(yeardata);
			return String.format("%.0f", sumdata);
		}else{
			return String.format("", "");
		}
		
	}
	//保留三位小数点。
	public static String getDecPoint(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		sumdata = getDoubleData(nowdata) - getDoubleData(yeardata);
		return String.format("%.3f", sumdata);
	}
	//除 保留两位小数
	public static String getDivide(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		sumdata = getDoubleData(nowdata)/getDoubleData(yeardata);
		return String.format("%.2f", sumdata);
	}
	
	public static String getRegulation(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		sumdata = getDoubleData(nowdata) - getDoubleData(yeardata);
		return String.format("%.1f", sumdata);
	}
	//保存整数
	public static String getRegu(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		sumdata = getDoubleData(nowdata) - getDoubleData(yeardata);
		return String.format("%.0f", sumdata);
	}
	
	public static String getRegulationnul(Object nowdata,Object yeardata){
		
		double sumdata = 0;
		if(nowdata != null && !"".equals(nowdata) && yeardata != null && !"".equals(yeardata)){
			sumdata = getDoubleData(nowdata) - getDoubleData(yeardata);
			return String.format("%.1f", sumdata);
		}
		return "";
	}
	public static String getRegulationStr(String nowdata,String yeardata){
		
		double sumdata = 0;
//		if(!nowdata.equals(yeardata)){
			sumdata = getDoubleData1(nowdata) - getDoubleData1(yeardata);
//		}
		return String.format("%.1f", sumdata);
	}
	
	public static String getCCYND(Object F12,Object C7){
		double sumdata = 0;
		if(C7 != null && !"".equals(String.valueOf(C7)) && getDoubleData(C7) != 0.00){
			sumdata = getDoubleData(F12)*0.87/getDoubleData(C7)*100;
		}
		return String.format("%.1f", sumdata);
	}
	/**
	 * 计算联合站合计值
	 * @param data 班累积值  
	 * @return
	 */
	public static String getSumData(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){

			if(data.size()==13 && data.get(0)!="" && data.get(data.size()-1)!=""){
				sumdata = Double.parseDouble(data.get(data.size()-1))-Double.parseDouble(data.get(0));
			}
		}
		if(sumdata!=0.001){
			return String.format("%.1f", sumdata);
		}else{
			return "";
		}
	}
	
	
	public static String getResultAvg(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){
			if(data.get(0)!="" && data.get(data.size()-1)!=""){
				sumdata = Double.parseDouble(data.get(data.size()-1))+Double.parseDouble(data.get(0));
			}
			sumdata = sumdata/7;
		}
		if(sumdata!=0.001){
			return String.format("%.0f", sumdata);
		}else{
			return "";
		}
	}
	
	
	
	public static String getSumData0(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){

			if(data.get(0)!="" && data.get(data.size()-1)!=""){
				sumdata = Double.parseDouble(data.get(data.size()-1))-Double.parseDouble(data.get(0));
			}
		}
		if(sumdata!=0.001){
			return String.format("%.0f", sumdata);
		}else{
			return "";
		}
	}
	
	
	/**
	 * 计算联合站合计值
	 * @param data 保留2位小数
	 * @return
	 */
	public static String getSumData2(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){

			if(data.get(0)!="" && data.get(data.size()-1)!=""){
				sumdata = Double.parseDouble(data.get(data.size()-1))-Double.parseDouble(data.get(0));
			}
		}
		if(sumdata!=0.001){
			return String.format("%.2f", sumdata);
		}else{
			return "";
		}
	}
	//第三个班累计
	public static String getSumData00(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){

			if(data.size()==13 && data.get(8)!="" && data.get(data.size()-1)!=""){
				sumdata = Double.parseDouble(data.get(data.size()-1))-Double.parseDouble(data.get(8));
			}
		}
		if(sumdata!=0.001){
			return String.format("%.0f", sumdata);
		}else{
			return "";
		}
	}

	/**
	 * param1 - param2 返回整数 想减值
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static String getSubtractInt(Object param1,Object param2){
		double sumdata = 0.00;
		if(param1 != null && !"".equals(getClearNullData(param1)) && param2 != null && !"".equals(getClearNullData(param2))){
			sumdata = getDoubleData(param1) - getDoubleData(param2);
		}else{
			return "";
		}
		
		return String.format("%.0f", sumdata);
		
	}
	/**
	 * param1 - param2 返回1位数 想减值
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static String getSubtractOne(Object param1,Object param2){
		double sumdata = 0.00;
		if(param1 != null && !"".equals(getClearNullData(param1)) && param2 != null && !"".equals(getClearNullData(param2))){
			sumdata = getDoubleData(param1) - getDoubleData(param2);
		}else{
			return "";
		}
		
		return String.format("%.1f", sumdata);
		
	}
	/**
	 * param1 - param2 返回2位数 想减值
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static String getSubtractTwo(Object param1,Object param2){
		double sumdata = 0.00;
		if(param1 != null && !"".equals(getClearNullData(param1)) && param2 != null && !"".equals(getClearNullData(param2))){
			sumdata = getDoubleData(param1) - getDoubleData(param2);
		}else{
			return "";
		}
		
		return String.format("%.2f", sumdata);
		
	}
	/**
	 * 计算联合站班累积值计算
	 * @param data一班时间内数据
	 * @return
	 */
	public static String getBanSumData(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){
			if(data.size() == 1){
				sumdata = Double.parseDouble(data.get(0));
			}else{
				if(data.get(0) != "" && data.get(data.size()-1) != ""){
					sumdata = Double.parseDouble(data.get(data.size()-1)) - Double.parseDouble(data.get(0));
				}
			}
			
		}
		if(sumdata!=0.001){
			return String.format("%.1f", sumdata);
		}else{
			return "";
		}
	}
	
	/**
	 * 计算联合站班累积值计算
	 * @param data一班时间内数据
	 * @return
	 */
	public static String getBanSumData0(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){
			if(data.size() == 1){
				sumdata = Double.parseDouble(data.get(0));
			}else{
				if(data.get(0) != "" && data.get(data.size()-1) != ""){
					sumdata = Double.parseDouble(data.get(data.size()-1)) - Double.parseDouble(data.get(0));
				}
			}
			
		}
		if(sumdata!=0.001){
			return String.format("%.0f", sumdata);
		}else{
			return "";
		}
	}
	/**
	 * 通用方法获取本机IP地址
	 * @return ip 本地IP地址
	 */
	public static String getIpAddress(){
		String ip ="";
		
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return ip;
		
	}
	public static JSONObject getRrturnJson(String outCode,String errmsg ,String confirmmsg,JSONObject jsonobj ,JSONArray arr){
		JSONObject obj = new JSONObject();
		
		if(outCode != null && !"".equals(outCode)){
			obj.put("ERRCODE", outCode);
		}
		if(errmsg != null && !"".equals(errmsg)){
			obj.put("ERRMSG", errmsg);
		}
		if(confirmmsg != null && !"".equals(confirmmsg)){
			obj.put("CONFIRMMSG", confirmmsg);
		}
		if(jsonobj != null){
			obj.put("JSONDATA", jsonobj);
		}
		if(arr != null && arr.size()>0){
			obj.put("JSONDATAS", arr);
		}
		
		return obj;
	}
	/**
	 * 通用方法获取当前系统时间
	 * @return ts Timestamp类型当前时间
	 */
	public static Timestamp getNowTime(){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return ts;
		
	}
	
	/**
	 * 获取当前日期的下一天
	 * @param time
	 * @return
	 */
	public static String getNextDay(String time){
		String nextday = "";
		DateFormat nowFmt =  new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
	
		try {
			Date endDate = nowFmt.parse(time);
			cal.setTime(endDate);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			
			nextday = nowFmt.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return nextday;
	}
	/**
	 * 将log日志类型由数组转化为字符型
	 * 增：1；
	 * 删：2；
	 * 改：3；
	 * 查：4 正常基础信息查询不需要添加LOG ；
	 * 登录：5；
	 * 退出：6；
	 * 页面布局：7
	 * @param type数字型log类别
	 * @return str 字符型LOG类型
	 */
	public static String getLogTypeStr(String type){
		String str = "0";
		if(type != null && !"".equals(type)){
			
			if("1".equals(type)){
				str = "添加";
			}else if("2".equals(type)){
				str = "删除";
			}else if("3".equals(type)){
				str = "修改";
			}else if("4".equals(type)){
				str = "查询";
			}else if("5".equals(type)){
				str = "登录";
			}else if("6".equals(type)){
				str = "退出";
			}else if("7".equals(type)){
				str = "审核";
			}else if("8".equals(type)){
				str = "班组审核";
			}else if("9".equals(type)){
				str = "地质组审核 ";
			}else if("10".equals(type)){
				str = "数据导入";
			}else if("11".equals(type)){
				str = "数据准备";
			}else if("12".equals(type)){
				str = "自动化提取";
			}else{
				str = type;
			}
		}
		return str;
	}
	
	/**
	 * 将log日志类型由字符型转化为数字
	 * 增：1；
	 * 删：2；
	 * 改：3；
	 * 查：4 正常基础信息查询不需要添加LOG ；
	 * 登录：5；
	 * 退出：6；
	 * 页面布局：7
	 * @param name字符型log类别
	 * @return str 数字型LOG类型
	 */
	public static String getLogType(String name){
		String str ="";
		if(name != null && !"".equals(name)){
			
			if("添加".equals(name)){
				str = "1";
			}else if("删除".equals(name)){
				str = "2";
			}else if("修改".equals(name)){
				str = "3";
			}else if("查询".equals(name)){
				str = "4";
			}else if("登录".equals(name)){
				str = "5";
			}else if("退出".equals(name)){
				str = "6";
			}else if("审核".equals(name)){
				str = "7";
			}else if("班组审核".equals(name)){
				str = "8";
			}else if("地质组审核".equals(name)){
				str = "9";
			}else if("数据导入".equals(name)){
				str = "10";
			}else if("数据准备".equals(name)){
				str = "11";
			}else if("自动化提取".equals(name)){
				str = "12";
			}
		}
		return str;
	}
	/**
	 * 组装要存入LOG 的信息
	 * @param user session中USER 信息
	 * @param category 操作类别 
	 * 增：1；
	 * 删：2；
	 * 改：3；
	 * 查：4 正常基础信息查询不需要添加LOG ；
	 * 登录：5；
	 * 退出：6；
	 * 审核：7
	 * 班组审核 8
	 * 地质组审核 9
	 * 数据导入 10
	 * 数据准备 11
	 * 自动化提取 12
	 * @param target 操作对象 （页面名称）
	 * @param infoid 信息ID
	 * @return
	 */
	public static PcRdLoginfoT getLogInfo(User user,String category,String target,String infoid){
		PcRdLoginfoT loginfo = new PcRdLoginfoT();
		//		用户ID
		//loginfo.setUserid(user.getUserid());
		//		操作人
		loginfo.setRlastOper(user.getOperName());
		//		操作类别
		loginfo.setOperatingClass(category);
		//		操作对象
		if(target != null && !"".equals(target)){
//			boolean flg = false;
//			if(user.getMenuList() != null && user.getMenuList().size()>0){
//				for(TbFunctionmenu menu : user.getMenuList()){
//					if(target.equals(menu.getMenuname())){
//						loginfo.setTarget(menu.getMenuid());
//						flg = true;
//					}
//					
//				}
//			}
//			
//			if(flg == false){
//				loginfo.setTarget(target);
//			}
			
			loginfo.setOperand(target);
		}
		
		//		信息ID
		loginfo.setInfoid(infoid);
		//		操作时间
		
		loginfo.setOperationTime(new Date());
		//		IP地址
		loginfo.setIpAddress(user.getIp());
		
		return loginfo;
	}
	
	/**
	 * 
	 * @param returnStr  菜单名称
	 * @param node  节点ID
	 * @param methodtype  方法类型  1：各页面初始化获取树  2：过滤各页面条件 3：获取各页面树中子节点
	 * @return  每个菜单获取公共收索树的范围
	 */
	public static String getMenuSQL(String returnStr,String node,String methodtype){
		
		String tableview = "";
		String leafType = "";
		//SAGD井基础信息
		if("SAGD井基础信息".equals(returnStr) || "SAGD井动态数据".equals(returnStr) || "SAGD井日报维护".equals(returnStr) || "SAGD井日报".equals(returnStr)){
			tableview = "PC_CD_SAGDTREE_V";
			leafType = "9";
		}else if("常规油井基础信息".equals(returnStr) || "常规油井动态数据".equals(returnStr)){
			tableview = "PC_CD_RULETREE_V";
			leafType = "9";
		}else if("稀油油井基础信息".equals(returnStr) || "稀油油井动态数据".equals(returnStr)){
			tableview = "PC_CD_THINOILTREE_V";
			leafType = "9";
		}else if("气井基础信息".equals(returnStr) || "气井动态数据".equals(returnStr)){
			tableview = "PC_CD_GASTREE_V";
			leafType = "9";
		}else if("水源井基础信息".equals(returnStr) || "水源井动态数据".equals(returnStr)){
			tableview = "PC_CD_WATER_SOURCETREE_V";
			leafType = "9";
		}else if("注水撬基础信息".equals(returnStr)){
			tableview = "PC_CD_INJECTIONTOPRYTREE_V";
			leafType = "13";
		}else if("注水井基础信息".equals(returnStr) || "注水井动态数据".equals(returnStr)){
			//tableview = "PC_CD_WATERFLOODINGTREE_V";
			tableview = "pc_cd_waterfloodingtree1_v";
			leafType = "9";
		}else if("观察井基础信息".equals(returnStr) || "观察井动态数据".equals(returnStr)){
			tableview = "PC_CD_OBSERVATIONTREE_V";
			leafType = "9";
		}else if("联合站基础信息".equals(returnStr) || "联合站动态数据".equals(returnStr)){
			tableview = "PC_CD_COMBINATIONTREE_V";
			leafType = "5";
		}else if("注转站基础信息".equals(returnStr)){
			tableview = "PC_CD_STATIONTREE_V";
			leafType = "7";
		}else if("气站基础信息".equals(returnStr)){
			tableview = "PC_CD_GAS_STATIONTREE_V";
			leafType = "12";
		} else if("区块基础信息".equals(returnStr)){
			tableview = "PC_CD_AREATREE_V";
			leafType = "6";
		}else if("采油站基础信息".equals(returnStr)){
			
			tableview = "PC_CD_OILDRILLINGTREE_V";
			leafType = "4";
		}else if("锅炉基础信息".equals(returnStr)){
			tableview = "PC_CD_BOILERTREE_V";
			leafType = "10";
			
		}else if("可选井口树".equals(returnStr)){
			tableview = "PC_CD_CELLTREE_V";
			leafType = "9";
			
		}else if("管汇点基础信息".equals(returnStr)|| "管汇点动态数据".equals(returnStr)){
			tableview = "PC_CD_MANIFOLDTREE_V";
			leafType = "8";
		}else if("管网管汇基础信息".equals(returnStr)|| "管网管汇动态数据".equals(returnStr)){
			tableview = "PC_CD_NETWORKTREE_V";
			leafType = "21";
		}else if("阀池基础信息".equals(returnStr)|| "阀池动态数据".equals(returnStr)){
			tableview = "PC_CD_CLIQUETREE_V";
			leafType = "22";
		}else if("采出液动态数据".equals(returnStr) ){
//			tableview = "PC_CD_MANIFOLDTREE_V";
			tableview = "pc_cd_chytree_v";
			leafType = "9";
		}else if("分线基础信息".equals(returnStr)){
			tableview = "PC_CD_BRANCHINGTREE_V";
			leafType = "16";
		}else if("部门基础信息".equals(returnStr)){
			
		}else if("功图".equals(returnStr)){
			tableview = "pc_cd_celltree_v";
			leafType = "9";
			
		}else if("注转站(转油)动态数据".equals(returnStr)){
			tableview = "pc_cd_stationZYtree_v";
			leafType = "7";
		}else if("湿蒸汽锅炉动态数据".equals(returnStr) || "湿蒸汽锅炉日报维护".equals(returnStr) || "湿蒸汽锅炉日报".equals(returnStr)){
			tableview = "PC_CD_BOILERSRTREE_V";
			leafType = "10";
		}else if("过热锅炉动态数据".equals(returnStr) || "过热锅炉日报维护".equals(returnStr) || "过热锅炉日报".equals(returnStr)){
			tableview = "PC_CD_BOILERGRTREE_V";
			leafType = "10";
		}else if("高干度锅炉动态数据".equals(returnStr) || "高干度锅炉日报维护".equals(returnStr) || "高干度锅炉日报".equals(returnStr)){
			tableview = "PC_CD_BOILERGGTREE_V";
			leafType = "10";
		}else if("异常井查询".equals(returnStr)){
			tableview = "PC_RD_ABNORMAL_WELL_V";
			leafType = "9";
			
		}else if("稀油井日报维护".equals(returnStr)){
			
		}else if("观察井日报维护".equals(returnStr)){
			
		}else if("气井日报维护".equals(returnStr)){
			
		}else if("水源井日报维护".equals(returnStr)){
			
		}else if("注水井日报维护".equals(returnStr)){
			
		}else if("联合站日报维护".equals(returnStr)){
			
		}else if("注转站日报维护".equals(returnStr)){
			
		}else if("管汇点日报维护".equals(returnStr)){
			
		}else if("稀油井日报".equals(returnStr)){
			
		}else if("观察井日报".equals(returnStr)){
			
		}else if("气井日报".equals(returnStr)){
			
		}else if("水源井日报".equals(returnStr)){
			
		}else if("注水井日报".equals(returnStr)){
			
		}else if("联合站日报".equals(returnStr)){
			
		}else if("注转站(转油)日报".equals(returnStr)){
			tableview = "pc_cd_stationZYtree_v";
			leafType = "7";
			
		}else if("管汇点日报".equals(returnStr)){
			
		}else if("操作员信息管理".equals(returnStr)){
			
		}else if("角色信息管理".equals(returnStr)){
			
		}else if("操作日志查询".equals(returnStr)){
			
		}else if("Portal菜单维护".equals(returnStr)){
			
		}else if("控制器通信信息".equals(returnStr)){
			
		}else if("仪表设备信息".equals(returnStr)){
			tableview = "PC_CD_FACILITYTREE_V";
			leafType = "9,10";
			
		}else if("采集点对象规范".equals(returnStr)){
			
		}else if("采集参数点规范".equals(returnStr)){
			
		}else if("对象采集参数管理".equals(returnStr)){
			
		}
		
		String sql = "";
		if("2".equals(methodtype)){
			sql = "   select * from "+ tableview +
					"	start with  structurename like '%"+node+"%'"+
					"	connect by prior org_id = pid "+
					"	order by structuretype";
				
		}else if("1".equals(methodtype)){
			if(tableview != null && !"".equals(tableview)){
				sql = "select a.* from "+ tableview +" a  "+
					 "where a.pid='F24C74E3ACB645679444FE61D9DD1552'  order by nlssort(a.structurename, 'NLS_SORT=SCHINESE_STROKE_M')";
				
				
			}
		}else if("3".equals(methodtype)){
			
			sql = "select a.* from "+ tableview +" a  "+
			"where a.pid='"+node+"'  order by nlssort(a.structurename, 'NLS_SORT=SCHINESE_STROKE_M')";
			
			
		}
		
		if(sql != null && !"".equals(sql)){
			sql =sql+ "-"+leafType;
		}
		
		return sql;
		
	}
	
	/**
	 * 
	 * */
	public static BigDecimal isNullOrEmpty(String arg){
		if(arg!=null && !"".equals(arg) && !"null".equals(arg))
			return BigDecimal.valueOf(Double.parseDouble(arg));
		else
			return null;
	}
	

	
	/**
	 * 
	 * @param data1 要比较的数据 
	 * @param data2 比较值
	 * @return   0:  第一个数 大于 第二个数  / 第二个数位空
	 * 			 1： 第一个数小于等于 第二个数
	 * 			"": 第一个数位空	
	 */
	public static JSONObject U2compreData(String sign,Object data1 , String data2,JSONObject tjo){
//		JSONObject tjo = new JSONObject();
		if(data1 != null && !"".equals(data1.toString()) && !"null".equals(data1)){
			if(data2 != null && !"".equals(data2)){
				if(Double.parseDouble(data1.toString()) > Double.parseDouble(data2)){
					tjo.put(sign,"<span style='color: red;'>"+data1+"</span>");
				}else{
					tjo.put(sign,data1.toString());
				}
				
			}else{
				tjo.put(sign,data1.toString());
			}
			
		}else{
			tjo.put(sign,"");
		}
		
		return tjo;
	}
	
	
	/**
	 * 
	 * @param data1 要比较的数据 
	 * @param data2 比较值
	 * @return  flag   true  :表示 第一个数值比  比较范围大，  返回页面字体应设置为红色
	 * 				   false :表示 第一个数值比  小于等于比较范围，  返回页面字体应设置为黑色
	 */
	public static  boolean U2compreData(Double data1 , String data2){
		boolean flag = false;
		if(data1 != null && !"".equals(data1.toString()) && data1 != 0){
			if(data2 != null && !"".equals(data2)){
				if(data1 > Double.parseDouble(data2)){
					flag = true;
				}
			}
			
		}
		
		return flag;
	}
	
	
	public static Object getLLSumData(List<String> data, int i, int j) {
		// TODO Auto-generated method stub
		double sumdata = 0.001;
		if(data != null && data.size()>0){

			if(data.size()==6 && data.get(i)!="" && data.get(j)!=""){
				sumdata = Double.parseDouble(data.get(j))-Double.parseDouble(data.get(i));
			}
		}
		if(sumdata!=0.001){
			return String.format("%.0f", sumdata);
		}else{
			return "";
		}
	}
	
	/**
	 * 计算联合站合计值
	 * @param data 重要生产参数   
	 * @return
	 */
	public static String getPivotalSumData(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){

			if(data.size()==7 && data.get(0)!="" && data.get(data.size()-1)!=""){
				sumdata = Double.parseDouble(data.get(data.size()-1))-Double.parseDouble(data.get(0));
			}
		}
		if(sumdata!=0.001){
			return String.format("%.0f", sumdata);
		}else{
			return "";
		}
	}
	
	/**
	 * 计算联合站   浓度信息
	 * @param data
	 * @return
	 */
	public static String getNDSumData(String s1,String s2){
		double sumdata = 0.001;
		
		if(s1!="" && s2!=""){
			sumdata = Double.parseDouble(s1)/Double.parseDouble(s2)*100;
		}
		if(sumdata!=0.001){
			return String.format("%.2f", sumdata);
		}else{
			return "";
		}
	}
	//注汽日累计量
	public static String getSumGasData(String s1,String s2){
		double sumdata = 0.001;
		
		if(s1!="" && s2!=""){
			sumdata = Double.parseDouble(s1)*Double.parseDouble(s2);
		}
		if(sumdata!=0.001){
			return String.format("%.2f", sumdata);
		}else{
			return "";
		}
	}
	public static String getSum(List<String> data){
		double sumdata = 0.001;
		
		if(data != null && data.size()>0){
			for(int i=0;i<data.size();i++){
				if(data.get(i)!="")
					sumdata += Double.parseDouble(data.get(i));
			}
		}
		if(sumdata!=0.001){
			return String.format("%.0f", sumdata);
		}else{
			return "0";
		}
	}
	
	public static String format1pData(String data){
		if(data !="")
			return String.format("%.1f",Double.parseDouble(data));
		else
			return "";
	}
	public static String format2pData(String data){
		if(data !="")
			return String.format("%.2f",Double.parseDouble(data));
		return "";
	}
	
	public static java.math.BigDecimal getBigDecimalData(String data){
		java.math.BigDecimal newData = null;
		if(data != null && !"".equals(data) && !"null".equals(data)){
			
			newData = java.math.BigDecimal.valueOf(Double.parseDouble(data));
		}
		return newData;
	}
	
	public static Double getDoubleData(String data){
		Double newData = null;
		if(data != null && !"".equals(data) && !"null".equals(data)){
			
			newData = Double.parseDouble(data);
		}
		return newData;
	}
	
	public static Byte getByteData(String data){
		Byte newData = null;
		if(data != null && !"".equals(data) && !"null".equals(data)){
			
			newData = Byte.parseByte(data);
		}
		return newData;
	}
	
	public static Boolean getBooleanData(String data){
		Boolean newData = null;
		if(data != null && !"".equals(data) && !"null".equals(data)){
			
			newData = Boolean.parseBoolean(data);
		}
		return newData;
	}
	
	
	
	
	public static java.math.BigDecimal getBigDecimalData(Object data){
		java.math.BigDecimal newData = null;
		if(data != null && !"".equals(data.toString().trim()) && !"null".equals(data.toString().trim())){
			
			newData = java.math.BigDecimal.valueOf(Double.parseDouble(data.toString()));
		}
		return newData;
	}
	//如果不为数字设置为空
	public static java.math.BigDecimal getBigDecimalDataTrim(Object data){
		java.math.BigDecimal newData = null;
		if(data != null && !"".equals(data.toString().trim()) && !"null".equals(data.toString().trim())){
			for (int i = data.toString().length();--i>=0;){   
				   if (!Character.isDigit(data.toString().charAt(i))){
					   newData = null; 
				  }else{
					  newData = java.math.BigDecimal.valueOf(Double.parseDouble(data.toString()));
				  }
			}
			//newData = java.math.BigDecimal.valueOf(Double.parseDouble(data.toString()));
		}
		return newData;
	}
//	BigDecimal
	public static String get2THLL(List<String> dataList) {
		// TODO Auto-generated method stub
		double sumdata = 0.001;
		if(dataList != null && dataList.size()>0 && dataList.size()!=1 && dataList.get(dataList.size()-1)!="" && dataList.get(dataList.size()-2)!=""){
			sumdata = Double.parseDouble(dataList.get(dataList.size()-1))-Double.parseDouble(dataList.get(dataList.size()-2));
		}
		
		if(sumdata!=0.001){
			return String.format("%.0f", sumdata);
		}else{
			return "";
		}
	}
	
	
	public static double getSUMData(double sumdata,Object data1){
		if(data1 != null && !"".equals(data1.toString()) && !"null".equals(data1.toString())){
			sumdata += Double.parseDouble(data1.toString());
		}
		return sumdata;
	}
	
	/**
	 * 判断list 是否全部为空
	 * @param List data  要判断的list参数
	 * @return boolean flg
	 * true 全部为空
	 * false 不全部为空
	 */
	@SuppressWarnings("rawtypes")
	public static boolean getListNull(List data){
		boolean flg = false;
		for(int i =0; i<data.size(); i++){
			if(data.get(i) != null && !"".equals(String.valueOf(data.get(i)))&& !"null".equals(String.valueOf(data.get(i)))){
				
				flg =true;
			}
		}
		return flg;
	}

	/**
	 * 判断井名不为空及时间是为当前系统时间
	 * @param String wellname  井名
	 * @param String date  井名
	 * @return boolean 前系统时间
	 * true 全部为空
	 * false 不全部为空
	 */
	public static String delTrim(String param){
		
		if(param !=null && !"".equals(param)){
			return  param;
		}else{
			return null;
		}
	}
	/**
	 * 根据信息长度不全数据信息
	 * @param List row 传入Excel行数据
	 * @param int coulmnLength Excel行数据长度  稠油数据长度为23
	 * @param int local  补入信息位置
	 * @param String info 补入信息内容
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getAllDataInfo(List row,int coulmnLength,int local,String info){
		int cha = coulmnLength - row.size();
		//数据长度小于 应有长度 不全
		if(row.size() < coulmnLength){
			for(int i = 0; i<cha ; i++){
				row.add("");
			}
			row.set(local, info);
		}else{
			row.set(local, info);
		}
		
		
		return row;
	}
	/**
	 * 根据信息长度不全数据信息
	 * @param List row 传入Excel行数据
	 * @param int coulmnLength Excel行数据长度  
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getAllDataLength(List row,int coulmnLength){
		int cha = coulmnLength - row.size();
		//数据长度小于 应有长度 不全
		if(row.size() < coulmnLength){
			for(int i = 0; i<cha ; i++){
				row.add("");
			}
		}
		
		return row;
	}
	
//	public static List<Object[]> getAllDataBQ(List<Object[]> jzsDatas,String reportDate){
//		List<Object[]> newDatas = new ArrayList<Object[]>(); 
//		int dataflg = 0;
//		Object[] tableData = null;
//		
//		String[][] dates = DateBean.getReportTime("time", reportDate);
//		
//		if(jzsDatas !=null && jzsDatas.size()>0){
//			
//			for(String[] dateList:dates){
//			obj = new JSONObject();
//
//					 
//					 for(String data:dateList){
//							dataflg = 0;
//							for(Object[] U1S1SS:jzsDatas){
//								obj = new JSONObject();
//								
//								if(data.toString().equals(U1S1SS[1].toString())){
//									dataflg = 1;
//									tableData = U1S1SS;
//
//								
//							}
//						}
//					 if(dataflg == 1){
//							obj.put("RPD_U1_WATER_AUTO_ID", CommonsUtil.getClearNullData(tableData[0]));
//							obj.put("rq", data.substring(11, 16));
//							
//							obj.put("ZKD",CommonsUtil.getNotThreeData(tableData[2]));
//							obj.put("CYQ_CKYL",CommonsUtil.getNotTwoData1(tableData[3]));
//							obj.put("CYQ_DCLL1",CommonsUtil.getSumTwoData(tableData[4],tableData[5]));
//							obj.put("CYQ_DCLY",CommonsUtil.getNotOneData(tableData[6]));
//						
//						
//					 	}else{
//					 		obj.put("RPD_U1_WATER_AUTO_ID","");
//							obj.put("rq",data.substring(11, 16));
//							obj.put("ZKD","");
//							obj.put("CYQ_CKYL","");
//							obj.put("CYQ_DCLL1","");
//							obj.put("CYQ_DCLY","");
//					 }
//					 arr.add(obj);
//				 }
//			}
//		}else{
//			for(String[] dateList:dates){
//				obj = new JSONObject();
//				for(String data:dateList){
//					obj = new JSONObject();
//					obj.put("RPD_U1_WATER_AUTO_ID","");
//					obj.put("ROWRQ",data.substring(11, 16));
//					obj.put("ZKD","");
//					obj.put("CYQ_CKYL","");
//					obj.put("CYQ_DCLL1","");
//					obj.put("CYQ_DCLY","");
//					arr.add(obj);
//				}
//			}
//		
//		}
//		return list;
//	}
	
	/**
	 *  @param prgid 组织结构ID
	 *  @param objid 对象类型代码
	 *  @author Administrator
	 */
	public static String getInstruCode(String orgid, String objid) {
		
		String recode = "ZX100002SG3";
		
		return recode;
	}
	
@SuppressWarnings({ "unchecked", "rawtypes" })
//去除重复
public static List<String> setData(List<String> list) {
	 HashSet h = new HashSet(list);
	 List<String> listData = new ArrayList<String>();
	 listData.clear();
	 listData.addAll(h);
	   return listData;
	}
	
}
