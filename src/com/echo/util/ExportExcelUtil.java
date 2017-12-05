package com.echo.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExportExcelUtil {
	private static ByteArrayOutputStream abos = null;
	/**
	 * 
	 * @param dataSet
	 * @param productionTeamName
	 * @param targetFilePath
	 * @param dateStrs
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static ByteArrayOutputStream productionTeamReport(Collection<Object[]> dataSet,String productionTeamName,String targetFilePath,String[] dateStrs) throws UnsupportedEncodingException {
		if (dataSet != null && 0 < dataSet.size()) {
			String title = getTitle(dateStrs,productionTeamName);
			HSSFWorkbook wb = ExcelUtil.getExcelTemplet(targetFilePath);
			//获取第一个sheet
			HSSFSheet sheet = ExcelUtil.getExcelTemplet(wb,0);
			sheet = ExcelUtil.writeTitle(wb,sheet,title,0,1);
			wb.removeSheetAt(wb.getSheetIndex("singleWell"));
			wb = subSheet(wb,sheet,dataSet,productionTeamName,500);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();

	}
	/**
	 * 
	 * @param dataSet
	 * @param targetFilePath
	 * @param dateStrs
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static ByteArrayOutputStream singleReport(List<Object[]> dataSet,List<Object[]> singleWellInfo,String targetFilePath,String[] dateStrs) throws UnsupportedEncodingException{
		if (dataSet != null && 0 < dataSet.size() && singleWellInfo != null && singleWellInfo.size() > 0) {
			Object[] headerData = null;
			String singWellName = null;
			//singleWellInfo
			for (Iterator<Object[]> iterator = singleWellInfo.iterator(); iterator.hasNext();) {
				headerData = (Object[]) iterator.next();
			}
			singWellName = String.valueOf(headerData[0]);
			for (int i = 0; i < headerData.length; i++) {
				if (headerData[i] == null ) {
					headerData[i] = "";
				}
			}
			String header = "层位：" + headerData[1] + " 煤层号：" + headerData[2] + " 生产井段：" + headerData[3] + "m  泵径:" + headerData[4] + "mm  下泵深度：" + headerData[5] + "m   音标深度：103.87m";
			String title = getTitle(dateStrs,singWellName);
			HSSFWorkbook wb = ExcelUtil.getExcelTemplet(targetFilePath);
			//获取第一个sheet
			HSSFSheet sheet = ExcelUtil.getExcelTemplet(wb,1);
			sheet = ExcelUtil.writeTitle(wb,sheet,title,0,0);
			sheet = ExcelUtil.writeTitle(wb,sheet,header,1,0);
			wb.removeSheetAt(wb.getSheetIndex("productionTeam"));
			wb = subSheet(wb,sheet,dataSet,String.valueOf(singWellName),500);
			abos = new ByteArrayOutputStream();
			try {
				return ExcelUtil.getFileByteArray(wb,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayOutputStream();
	}
	/**
	 * 
	 * @param dateStrs
	 * @param titleName
	 * @return
	 */
	public static String getTitle(String dateStrs[] ,String titleName){
		java.util.Calendar c = java.util.Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH + 1);
		int nowDay = c.get(Calendar.DAY_OF_MONTH);
		String yearStr = dateStrs[0];
		String monthStr = dateStrs[1];
		String nowStr = dateStrs[2];
		if (!titleName.startsWith("排采")) {
			return titleName + "排采日报表";
		}
		if (!"".equals(yearStr)){
			return "保德分公司 " + yearStr + "年" + monthStr + "月" + nowStr + "日 " + titleName + "日报表";
		}
		
		return "保德分公司 " + year + "年" + month + "月" + nowDay + "日 " + titleName + "日报表";
	}

	public static ByteArrayOutputStream QjExport(Collection<Object[]> dataSet,
			 String sheetName, String time, String time1) {
		// TODO Auto-generated method stub
		if (dataSet != null && 0 < dataSet.size()) {
			Date date = new Date();		 
			Workbook book = null;
			Sheet sheet = null;
			BufferedOutputStream out = null;
			
			book = new SXSSFWorkbook(128); //缓存128在内存。
			sheet = book.createSheet(sheetName);
			
//			Row firstRow = sheet.createRow(0);
//			Cell titleCell = firstRow.createCell(1);
//			titleCell.setCellValue(sheetName);
			 //工作表列标题 
	        String[] titleQj = {"气井号", "采集时间", "通信状态","运行状态","运行时","昨运行时","套压","井底流压","动液面","产气瞬时流量","产气当日累计","产气昨日累计","气表表头累计","气表采集温度","气表采集压力","产水瞬时流量","产水当日累计","产水昨日累计","水表表头累计","电网频率","输出频率","输出电压","输出电流","冲程","冲次","下行呈最大电流","上行呈最大电流","平衡率","扭矩","转速","套压阀开度"}; 
	        String[] titleJc = {"井场", "机组编号", "采集时间","相电压","线电压","相电流","发电频率","功率","转速","油压","水温","电池电压","发电机类型"}; 
	        String[] titleJl = {"气井", "计量时间", "持续时长","有效时长","累计气量","日产气量  ","备注"}; 
	        String[] titleQjInfo = {"井号","层位","煤层号","生产井段","投产日期 ","泵挂深度","电机型号 "};
	        
			Row firstRow = sheet.createRow(0);
	        if(sheetName.equals("气井数据")){
	        	for (int i = 0; i < titleQj.length; i++) {                 
		        	Cell titleCell = firstRow.createCell(i);
		        	titleCell.setCellValue(titleQj[i]);            
		        }
	        }else if(sheetName.equals("井场数据")){
	        	for (int i = 0; i < titleJc.length; i++) {                 
		        	Cell titleCell = firstRow.createCell(i);
		        	titleCell.setCellValue(titleJc[i]);            
		        } 
	        }else if (sheetName.equals("计量撬数据")) {
	        	for (int i = 0; i < titleJl.length; i++) {                 
		        	Cell titleCell = firstRow.createCell(i);
		        	titleCell.setCellValue(titleJl[i]);            
		        } 
			}else if(sheetName.equals("井口基本信息")){
				for (int i = 0; i < titleJl.length; i++) {                 
		        	Cell titleCell = firstRow.createCell(i);
		        	titleCell.setCellValue(titleQjInfo[i]);            
		        }
			}
			
			final int startRowNum = 2;
			
			book = ExcelUtil.insertExcel(book,sheet,startRowNum, dataSet);
			
			sheet.autoSizeColumn(26,true);
			abos = new ByteArrayOutputStream();
			try {
				abos = ExcelUtil.getFileByteArray(book,abos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return abos;
		}
		
		return new ByteArrayOutputStream();
	}
	public static HSSFWorkbook writeProductionTeamSheet(HSSFWorkbook wb, HSSFSheet sheet, Collection<Object[]> dataSet){
		String amount = "合计";
		final short startRowNum = 3;
		wb = ExcelUtil.insertExcelData(wb,sheet,startRowNum, dataSet);
		int rowNum = dataSet.size() + startRowNum;
		sheet = ExcelUtil.writeOtherInfo(wb,sheet,amount,rowNum,0);
		HSSFRow row = sheet.getRow(rowNum);
		HSSFCell cell = null;
		CellStyle cellStyle = ExcelUtil.getStyle(wb);
		cellStyle = ExcelUtil.getDataStyle(cellStyle);
		for (int i = 1; i < 29; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(cellStyle); 
		}
		row.getCell(15).setCellFormula("SUM(P4:P" + rowNum + ")");
		row.getCell(15).setCellStyle(cellStyle);
		row.getCell(16).setCellFormula("SUM(Q4:Q" + rowNum + ")");
		row.getCell(16).setCellStyle(cellStyle);
		sheet.autoSizeColumn(27,true);
		return wb;
	}
	
	public static HSSFWorkbook writeSingleWellSheet(HSSFWorkbook wb, HSSFSheet sheet, Collection<Object[]> dataSet){
		final short startRowNum = 4;
		String explain = "说明：自2011年10月21日起，均报频率值；正数表示上升，负数表示下降；管网回压指水管网的回压";
		wb = ExcelUtil.insertExcelData(wb,sheet,startRowNum, dataSet);
		int rowNum = dataSet.size() + startRowNum;
		int colNum = 13;
		sheet.addMergedRegion(new CellRangeAddress(rowNum,(short)rowNum,0,(short)colNum));
		sheet = ExcelUtil.writeOtherInfo(wb,sheet,explain,rowNum,0);
		CellStyle cellStyle = ExcelUtil.getStyle(wb);
		cellStyle = ExcelUtil.getDataStyle(cellStyle);
		HSSFRow row = sheet.getRow(rowNum);
		HSSFCell cell = null;
		for (int i = 1; i < 15; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(cellStyle); 
		}
		sheet.autoSizeColumn(13,true);
		sheet.autoSizeColumn(14,true);
		
		return wb;
	}
	
	public static HSSFWorkbook subSheet(HSSFWorkbook wb, HSSFSheet sheet, Collection<Object[]> dataSet,String sheetName ,int insertRowNum){
		int index = 0;
		Object[] dataGrouping = null;
		wb.setSheetName(0,sheetName);
		boolean singleReport = sheet.getRow(1).getCell(0).getStringCellValue().indexOf("层位") > -1;
		/*
		 * 数据分组
		 */
		if (dataSet.size() > insertRowNum) {
			index = dataSet.size() / insertRowNum;
			dataGrouping = new Object[index + 1];
			HSSFSheet indexSheet = null;
			
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
			
			//写入数据
			
			for (int i = 1; i < dataGrouping.length; i++) {
				indexSheet = wb.cloneSheet(0);
				wb.setSheetName(i,sheetName + "(" + i + ")");
				if (singleReport) {
					wb = writeSingleWellSheet(wb,indexSheet,(Collection<Object[]>) dataGrouping[i]);
				}else {
					wb = writeProductionTeamSheet(wb,indexSheet,(Collection<Object[]>) dataGrouping[i]);
				}
			}
			if (singleReport) {
				wb = writeSingleWellSheet(wb,sheet,(Collection<Object[]>) dataGrouping[0]);
			}else {
				wb = writeProductionTeamSheet(wb,sheet,(Collection<Object[]>) dataGrouping[0]);
			}
			return wb;
		}
		/*
		 * 不分组写入数据
		 */
		if (singleReport) {
			wb = writeSingleWellSheet(wb,sheet,dataSet);
		}else {
			wb = writeProductionTeamSheet(wb,sheet,dataSet);
		}
		return wb;
	}
}
