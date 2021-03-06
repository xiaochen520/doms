package com.echo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.Region;

import com.echo.dto.User;

public class DynamicDataExportUtil {
	private static ByteArrayOutputStream abos = null;
	public static ByteArrayOutputStream dynamicDataExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		String[] sheetNames = {"密闭转接动态数据","SAGD井动态数据2","SAGD井动态数据3","SAGD井动态数据4","SAGD井动态数据5","SAGD井动态数据","常规油井动态数据","稀油油井动态数据","采出液动态数据","观察井动态数据","气井动态数据","水源井动态数据","注水井动态数据","联合站动态数据","注转站(转油)动态数据","湿蒸汽锅炉动态数据","高干度锅炉动态数据","过热锅炉","管汇点动态数据","异常井查询","SubCool历史数据","密闭处理动态数据","SAGD管汇点动态数据","重18SAGD-3#站动态数据","SAGD实时数据","重1SAGD换热站动态数据","160万方暂存池动态数据"};
		if (dataSet != null && 0 < dataSet.size()) {
			HSSFWorkbook wb = getWorkBook(dataSet,targetFilePath,sheetName,sheetNames);
			//此处根据sheetName调整各sheet数据列样式宽度
			if ("稀油油井动态数据".equals(sheetName)) {
				//wb.removeSheetAt(wb.getSheetIndex(sheetN));
			}
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	
	public static ByteArrayOutputStream dynamicDataExporReport(Collection<Object[]> dataSet, User user) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			Map<String, List<Object[]>> map = sortByStation(dataSet);
			HSSFWorkbook wb = new HSSFWorkbook();
			
			for(String station : map.keySet()) {
				HSSFSheet sheet = wb.createSheet(station + "调控措施单");
		        sheet.setColumnWidth(0, 3500);
		        sheet.setColumnWidth(1, 2000);
		        sheet.setColumnWidth(2, 2000);
		        sheet.setColumnWidth(3, 2000);
		        sheet.setColumnWidth(4, 2000);
		        sheet.setColumnWidth(5, 2000);
		        sheet.setColumnWidth(6, 2000);
		        sheet.setColumnWidth(7, 3500);
		        
		        HSSFCellStyle style = wb.createCellStyle();
		        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        
		        HSSFCellStyle style0 = wb.createCellStyle();
		        style0.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        style0.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		        
		        HSSFCellStyle style_center = wb.createCellStyle();
		        style_center.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        style_center.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		        style_center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        style_center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		        style_center.setBorderRight(HSSFCellStyle.BORDER_THIN);
		        style_center.setBorderTop(HSSFCellStyle.BORDER_THIN);
		        
		        HSSFCellStyle style_left = wb.createCellStyle();
		        style_left.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		        
		        HSSFCellStyle style_right = wb.createCellStyle();
		        style_right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		        
		        HSSFFont font_18 = wb.createFont();
		        font_18.setFontHeightInPoints((short) 18);
		        font_18.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        
		        HSSFFont font_12 = wb.createFont();
		        font_12.setFontHeightInPoints((short) 12);
		        font_12.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        
		        HSSFRow row = sheet.createRow(0);
		        HSSFCell cell = row.createCell(0);
		        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		        cell.setCellValue("风城SAGD调控措施单");
		        style.setFont(font_18);
		        cell.setCellStyle(style);
		        
		        row = sheet.createRow(1);
		        cell = row.createCell(0);
		        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
		        cell.setCellValue("接收单位：" + station);
		        style_left.setFont(font_12);
		        cell.setCellStyle(style_left);
		        
		        row = sheet.createRow(2);
		        cell = row.createCell(0);
		        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
		        cell.setCellValue("请" + station + "按以下参数进行调整，如有问题请及时上报重大开发实验项目管理部");
		        cell.setCellStyle(style0);
		        
		        row = sheet.createRow(3);
		        cell = row.createCell(0);
		        cell.setCellValue("井号/锅炉号");
		        cell.setCellStyle(style_center);
		        
		        cell = row.createCell(1);
		        sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 2));
		        cell.setCellValue("油嘴");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(2);
		        cell.setCellValue("");
		        cell.setCellStyle(style_center);
		        
		        cell = row.createCell(3);
		        sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 4));
		        cell.setCellValue("冲次");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(4);
		        cell.setCellValue("");
		        cell.setCellStyle(style_center);
		        
		        cell = row.createCell(5);
		        sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 6));
		        cell.setCellValue("汽量/水量");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(6);
		        cell.setCellValue("");
		        cell.setCellStyle(style_center);
		        
		        cell = row.createCell(7);
		        cell.setCellValue("备注");
		        cell.setCellStyle(style_center);
		        
		        row = sheet.createRow(4);
		        cell = row.createCell(0);
		        sheet.addMergedRegion(new CellRangeAddress(3, 4, 0, 0));
		        cell.setCellValue("");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(1);
		        cell.setCellValue("目前计划");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(2);
		        cell.setCellValue("调整");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(3);
		        cell.setCellValue("目前计划");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(4);
		        cell.setCellValue("调整");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(5);
		        cell.setCellValue("目前计划");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(6);
		        cell.setCellValue("调整");
		        cell.setCellStyle(style_center);
		        cell = row.createCell(7);
		        sheet.addMergedRegion(new CellRangeAddress(3, 4, 7, 7));
		        cell.setCellValue("");
		        cell.setCellStyle(style_center);
		        
		        int count = 5;
		        List<Object[]> list = map.get(station);
		        for (Object[] objects : list) {
		        	row = sheet.createRow(count++);
		        	for (int i = 1; i < objects.length; i++) {
						Object obj = objects[i];
						cell = row.createCell(i-1);
						if(obj == null || "".equals(obj)) {
							cell.setCellValue("");
						} else {
							cell.setCellValue(obj.toString());
						}
				        cell.setCellStyle(style_center);
					}
		        	cell = row.createCell(7);
		        	cell.setCellValue("");
		        	cell.setCellStyle(style_center);
				}
		        
		        row = sheet.createRow(count);
		        cell = row.createCell(0);
		        sheet.addMergedRegion(new CellRangeAddress(count, count, 0, 7));
		        cell.setCellValue("下达人：" + user.getOperName());
		        HSSFFont bold = wb.createFont();
		        bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        style_right.setFont(bold);
		        cell.setCellStyle(style_right);
		        count++;
		        
		        row = sheet.createRow(count);
		        cell = row.createCell(0);
		        sheet.addMergedRegion(new CellRangeAddress(count, count, 0, 7));
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		        cell.setCellValue("下达日期：" + format.format(new Date()));
		        cell.setCellStyle(style_right);
		        count++;
		        
		        row = sheet.createRow(count);
		        cell = row.createCell(0);
		        sheet.addMergedRegion(new CellRangeAddress(count, count, 0, 7));
		        cell.setCellValue("重大开发实验项目管理部");
		        cell.setCellStyle(style_right);
			}
	        
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	
	public static ByteArrayOutputStream dailyWHDataExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"稀油日报"};
			
			HSSFWorkbook wb = getWorkBook2(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();
	}
	public static ByteArrayOutputStream dailyRuleWHDataExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"稠油日报"};
			
			HSSFWorkbook wb = getWorkBook2(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	public static ByteArrayOutputStream dailyZhuWHDataExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"注水日报"};
			
			HSSFWorkbook wb = getWorkBook(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	public static ByteArrayOutputStream dailyGasDataExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"注汽日报"};
			
			HSSFWorkbook wb = getWorkBook(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	public static ByteArrayOutputStream dailycsWHExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"措施日报"};
			
			HSSFWorkbook wb = getWorkBook2(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	public static ByteArrayOutputStream dailyrxqlWHExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"热洗清蜡"};
			
			HSSFWorkbook wb = getWorkBook2(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	public static ByteArrayOutputStream dailyDataExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"SAGD井日报","注转站(转油)日报", "湿蒸汽锅炉日报", "高干度锅炉日报", "过热锅炉日报" ,"气井日报"};
			HSSFWorkbook wb = getWorkBook(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	public static ByteArrayOutputStream basicDataExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			//,"稀油井基础信息","常规油井基础信息","水源井基础数据","SAGD井基础信息","注转站基础信息","锅炉基础信息"
			//String[] sheetNames = {"管汇点基础信息", "注水井基础信息", "注水撬基础信息", "气井基础信息","稀油油井基础信息","水源井基础信息"};
			String[] sheetNames = {"管汇点基础信息", "注水井基础信息", "注水撬基础信息", "气井基础信息","稀油油井基础信息","常规油井基础信息","水源井基础信息","锅炉基础信息","注转站基础信息","SAGD井基础信息","阀池基础信息","管网管汇信息"};
			HSSFWorkbook wb = getWorkBook(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	
	public static ByteArrayOutputStream ipExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"IP规划大表", "IP使用表"};
			HSSFWorkbook wb = getWorkBook(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	public static ByteArrayOutputStream systemExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"井站采集点信息","站库采集点","站库系统信息","仪表设备"};
			HSSFWorkbook wb = getWorkBook(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	//点表生成
	public static ByteArrayOutputStream pTableExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"站库采集点点表生成","站库系统信息点表生成"};
			HSSFWorkbook wb = getWorkBook(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	// 检查表
	public static ByteArrayOutputStream checkDataExporReport(Collection<Object[]> dataSet,String targetFilePath,String sheetName) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String[] sheetNames = {"动态表", "统计表"};
			HSSFWorkbook wb = getWorkBook1(dataSet,targetFilePath,sheetName,sheetNames);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	@SuppressWarnings("unchecked")
	public static HSSFWorkbook getWorkBook(Collection<Object[]> dataSet,String targetFilePath,String sheetName,String[] sheetNames){
		HSSFWorkbook wb = ExcelUtil.getExcelTemplet(targetFilePath);
		//获取指定sheet
		HSSFSheet sheet = wb.getSheet(sheetName);
		int insertRowNum = 1000;//分割数据条数
		if (dataSet.size() > insertRowNum) {
			Object[] dataGrouping = splitData(dataSet,insertRowNum);
			for (int i = 0; i < dataGrouping.length; i++) {
				sheet = wb.cloneSheet(wb.getSheetIndex(sheetName));
				wb.setSheetName(wb.getSheetIndex(sheet.getSheetName()),sheetName + "(" + (i + 1) + ")");
				if (i == dataGrouping.length - 1) {
					wb.removeSheetAt(wb.getSheetIndex(sheetName));
				}
				wb = ExcelUtil.insertExcelData(wb, sheet, (short) 3, (Collection<Object[]>) dataGrouping[i]);
			}
		}
		else {
			wb = ExcelUtil.insertExcelData(wb, sheet, (short) 3, dataSet);
		}
		//移除不需要的sheet
		for (String sheetN : sheetNames) {
			if (!sheetN.equals(sheetName)) {
				wb.removeSheetAt(wb.getSheetIndex(sheetN));
			}
		}
		return wb;
	}
	//0,1,2 第三行开始导出
	@SuppressWarnings("unchecked")
	public static HSSFWorkbook getWorkBook2(Collection<Object[]> dataSet,String targetFilePath,String sheetName,String[] sheetNames){
		HSSFWorkbook wb = ExcelUtil.getExcelTemplet(targetFilePath);
		//获取指定sheet
		HSSFSheet sheet = wb.getSheet(sheetName);
		int insertRowNum = 1000;//分割数据条数
		if (dataSet.size() > insertRowNum) {
			Object[] dataGrouping = splitData(dataSet,insertRowNum);
			for (int i = 0; i < dataGrouping.length; i++) {
				sheet = wb.cloneSheet(wb.getSheetIndex(sheetName));
				wb.setSheetName(wb.getSheetIndex(sheet.getSheetName()),sheetName + "(" + (i + 1) + ")");
				if (i == dataGrouping.length - 1) {
					wb.removeSheetAt(wb.getSheetIndex(sheetName));
				}
				wb = ExcelUtil.insertExcelData(wb, sheet, (short) 2, (Collection<Object[]>) dataGrouping[i]);
			}
		}
		else {
			wb = ExcelUtil.insertExcelData(wb, sheet, (short) 2, dataSet);
		}
		//移除不需要的sheet
		for (String sheetN : sheetNames) {
			if (!sheetN.equals(sheetName)) {
				wb.removeSheetAt(wb.getSheetIndex(sheetN));
			}
		}
		return wb;
	}
	public static HSSFWorkbook getWorkBook1(Collection<Object[]> dataSet,String targetFilePath,String sheetName,String[] sheetNames){
		HSSFWorkbook wb = ExcelUtil.getExcelTemplet(targetFilePath);
		//获取指定sheet
		HSSFSheet sheet = wb.getSheet(sheetName);
		int insertRowNum = 1000;//分割数据条数
		if (dataSet.size() > insertRowNum) {
			Object[] dataGrouping = splitData(dataSet,insertRowNum);
			for (int i = 0; i < dataGrouping.length; i++) {
				sheet = wb.cloneSheet(wb.getSheetIndex(sheetName));
				wb.setSheetName(wb.getSheetIndex(sheet.getSheetName()),sheetName + "(" + (i + 1) + ")");
				if (i == dataGrouping.length - 1) {
					wb.removeSheetAt(wb.getSheetIndex(sheetName));
				}
				wb = ExcelUtil.insertExcelData(wb, sheet, (short) 1, (Collection<Object[]>) dataGrouping[i]);
			}
		}
		else {
			wb = ExcelUtil.insertExcelData(wb, sheet, (short) 1, dataSet);
		}
		//移除不需要的sheet
		for (String sheetN : sheetNames) {
			if (!sheetN.equals(sheetName)) {
				wb.removeSheetAt(wb.getSheetIndex(sheetN));
			}
		}
		return wb;
	}
	public static Object[] splitData(Collection<Object[]> dataSet ,int insertRowNum){
		int index = 0;
		Object[] dataGrouping = null;
		/*
		 * 数据分组
		 */
		if (dataSet.size() > insertRowNum) {
			index = dataSet.size() / insertRowNum;
			dataGrouping = new Object[index + 1];
			
			List<Object[]> subSet = new ArrayList<Object[]>();
			int dataIndex = 0;
			for (Iterator<Object[]> iterator = dataSet.iterator(); iterator.hasNext();) {
				subSet.add(iterator.next());
				dataIndex++;
				if (dataIndex % insertRowNum == 0) {
					dataGrouping[(dataIndex / insertRowNum) - 1] = subSet;
					subSet = new ArrayList<Object[]>();
				}
			}
			dataGrouping[index] = subSet;
		}
		
		return dataGrouping;
	}
	
	private static Map<String, List<Object[]>> sortByStation(Collection<Object[]> dataSet) {
		HashMap<String, List<Object[]>> map = new HashMap<String, List<Object[]>>();
		String station = "";
		for (Iterator iterator = dataSet.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			station = objects[0].toString();
			List<Object[]> list = map.get(station);
			if(list != null) {
				list.add(objects);
			} else {
				list = new ArrayList<Object[]>();
				list.add(objects);
				map.put(station, list);
			}
		}
		
		return map;
	}
	
}