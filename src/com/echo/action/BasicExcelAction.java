package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import com.echo.dto.User;
import com.echo.service.ExcelService;
import com.echo.util.CommonsUtil;
import com.echo.util.ExcelSheetParser;
import com.echo.util.ExcelUtil;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 对EXCEL 进行处理Action 包括EXCEL 上传及下载等功能
 * @author yanloong
 *
 */
public class BasicExcelAction extends ActionSupport { 
	
	private static final long serialVersionUID = 3974467382890171317L;
	private static final String RB_START_INSERT = "A3" ;  //日报错误信息EXCEL 数据插入开始位置
	private ExcelService excelService;
	//封装文件标题请求参数属性 
	private String title; 
	//封装传文件域属性    
	private File upload; 
	//封装传文件类型属性    
	private String uploadContentType; 
	//封装传文件名属性    
	private String uploadFileName; 
	//接受依赖注入属性     
	private String savePath; 
	private InputStream excelFile = null;
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public void setExcelService(ExcelService excelService) {
		this.excelService = excelService;
	}
	//接受依赖注入     
	public void setSavePath(String value) 
	{         
		this.savePath = value;     
	} 
	//返传文件保存位置     
	@SuppressWarnings("deprecation")
	private String getSavePath() throws Exception  
	{        
		return ServletActionContext.getRequest().getRealPath(savePath);     
	} 
	//文件标题settergetter 
	public void setTitle(String title){  
		this.title = title;  
		} 
	public String getTitle()
	{  return (this.title);  
	} 
	//传文件应文件内容settergetter 
	public void setUpload(File upload) 
	{  
		this.upload = upload; 
		} 
	public File getUpload() {  
		return (this.upload);  
		} 
	//传文件文件类型settergetter 
	public void setUploadContentType(String uploadContentType) { 
		this.uploadContentType = uploadContentType; 
		} 
	public String getUploadContentType(){ 
		return (this.uploadContentType);  
		} //传文件文件名settergetter
	public void setUploadFileName(String uploadFileName) 
	{ 
		this.uploadFileName = uploadFileName; 
	}
	public String getUploadFileName()
	{  return (this.uploadFileName); 
	} 
	
	public String getFileName() {
		String downloadFileName = "错误信息-基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	
	public String getFileName(String fileName) {
		
		//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = "错误信息-"+fileName+".xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override    
	public String execute() throws Exception { 
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		
		String page = getTitle();
		//当前系统时间
		int columsun = 5;
			
		
			getFileName(getUploadFileName());
			//保存文件路径
			String excelpath = getSavePath() + "\\" + ExcelUtil.getNerverExcelName(getUploadFileName());
			//服务器文件保存址原文件名建立传文件输流  
			FileOutputStream fos = new FileOutputStream(excelpath); 
			//传文件建立文件传流 
			FileInputStream fis = new FileInputStream(getUpload());  
			//传文件内容写入服务器 
			byte[] buffer = new byte[1024];  
			int len = 0;  
			while ((len = fis.read(buffer)) > 0)  
			{   
				fos.write(buffer , 0 , len); 
			}  
			   File file = new File(excelpath);
//			   String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\基础信息报表.xls";
			   ExcelSheetParser parser = new ExcelSheetParser(file);
			   List<List> datas = parser.getDatasInSheet(0);  //上传数据
			   List<List> outDatas = new ArrayList<List>();		//导出数据
			   List row = null;
			   List newRow = null;
			   int exportFlg = 0;  //存在错误信息，导出表示， 0不导出，1导出
			   HashMap<String,Object> dataMap = null; //每条数据插入信息 及要导出数量
			   boolean nullflg = true;  //导入数据行是否全部为空
			   boolean insertflg = true; //是否执行插入操作
			  
			   
			   for(int i=3;i<datas.size();i++){//显示数据
					insertflg = true;
				    row = datas.get(i);
				    nullflg = CommonsUtil.getListNull(row);
				    // 不全部为空：导入数据行内有一值不为空执行 // 过滤数据不需要导入数据库
				    if(nullflg){
				    
				 
				    //执行插入操作
				    if(insertflg){
				    	
			    		if(row.size() >= columsun-1 && row.get(columsun-2) != null && !"".equals(row.get(columsun-2).toString())){
			    			if("成功".equals(row.get(columsun-2).toString())){
			    				newRow = row;
			    			}else{
			    				//入库操作
					    		dataMap = excelService.insertBasicExcel( row,columsun);
					    		newRow = (List) dataMap.get("list");
			    			}
			    		}else{
			    			//入库操作
				    		dataMap = excelService.insertBasicExcel( row,columsun);
				    		newRow = (List) dataMap.get("list");
			    		}
			    		
			    		
			    		if(dataMap != null && dataMap.get("exportResult") != null &&  "1".equals(dataMap.get("exportResult").toString())){
			    			//存在错误信息，
					    	exportFlg = 1;
			    		}
				    
				    	
				    }
				    outDatas.add(newRow);
			    
			    }
			    
				
			    
//			    for(short n=0;n<row.size() ;n++){
//			     Object value = row.get(n);
//			     String data = String.valueOf(value);
//			     System.out.print(data +"\t");
//			    }
//			    System.out.println();
			   }
			   
			   
			    //插入数据并获取下载数据
			    
				if(outDatas != null && outDatas.size()>0){
					ReportFormsBaseUitl reportU = new ReportFormsBaseUitl();
					if(exportFlg == 1){
						String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\importModel\\基础信息.xls";
						HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
						HSSFSheet sheet = wb.getSheetAt(0);
						CellStyle cs1 = wb.createCellStyle();
						cs1 = ExcelUtil.getDataStyle(cs1);
						U2DataExportUtil.insertDataByPosition1(outDatas, wb, sheet, RB_START_INSERT);
						CellReference cr = new CellReference(RB_START_INSERT);
						int startRowNum = cr.getRow();
						Row dataRow;
						sheet.getLastRowNum();
						Cell dataCell;
						
						int k = outDatas.get(0).size();
						
						for (int x = startRowNum; x < outDatas.size()+startRowNum; x++) {
							dataRow = sheet.getRow(x);
							dataCell = dataRow.getCell(columsun-2);
							if ("失败".equals(dataCell.getStringCellValue())) {
								CellStyle style = wb.createCellStyle();
//								style = ExcelUtil.getDataStyle(style);
								// 设置水平对齐的样式为居中对齐;
//								style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
								// 设置垂直对齐的样式为居中对齐;
								ExcelUtil.setStyle(wb, style);
//								style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
								style.setFillForegroundColor(IndexedColors.RED.getIndex());
							    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//								style.setFillBackgroundColor(HSSFColor.LIGHT_TURQUOISE.index);
//								style.setFillPattern(CellStyle.SOLID_FOREGROUND);
							    
							    dataRow.getCell(0).setCellStyle(style);
							}

						}
						java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
						if(baos != null){
							byte[] ba = baos.toByteArray();
							excelFile = new ByteArrayInputStream(ba);
							baos.flush();
							baos.close();
						}
						return "excel";
						
					}else{
						request.setAttribute("EXPORTCOUNT",outDatas.size()); 
						return "SUCCESS";
					}
					
				}else{
					this.addFieldError("errermsg", "无要导入数据，请检查导入EXCEL! ");
					return "fail";
				}
			
	
			
		}
	
	
	
	
	}
