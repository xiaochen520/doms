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
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
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
public class Excel1Action extends ActionSupport { 
	
	private static final long serialVersionUID = 3974467382890171317L;
	private static final String RB_START_INSERT = "A3" ;  //日报错误信息EXCEL 数据插入开始位置
	private static final String RB_ERROR_INSERT = "A4" ;  //日报错误信息EXCEL 数据插入开始位置   注入
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
		String downloadFileName = "错误信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	
	public String getFileName(String fileName) {
		
		//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = "错误信息"+fileName+".xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override    
	public String execute() throws Exception { 
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
	
		
		//int importFlg = 0; //导入表示所在位置
		/**
		 * 来源页面
		 * cgcyrb :常规稠油日报
		 * xyrb   :稀油日报
		 * zsrb	  :注水日报
		 * zqrb   :注汽日报
		 */
		String page = getTitle();
		//当前系统时间
		 String nowdata = DateBean.getSystemTime1();
			//权限锁定判断 判断当前用户 导入操作是否在审核之后  
		boolean lockflg = AuthorityUtil.checkeUserStauts(nowdata, "", user);
		
		if(lockflg == false){
			this.addFieldError("errermsg", "当日数据已审核，不能进行EXCEL导入操作! ");
			return "fail";
			
		}
		int columsun = 0;
		String tableName ="";
		if("3".equals(deptype) ||"4".equals(deptype) || "15".equals(deptype)){
			List<Object[]> todayDatas = null;
			//根据不同页面进行对不同表查询
			if("cgcyrb".equals(page)){
				//importFlg = 21; 
				if("15".equals(deptype)){
					tableName ="PC_RPD_RULE_WELL_PROD_T";
				}else{
					tableName ="PC_RPD_RULE_WELL_PRODB_T";
				}
			}else if("xyrb".equals(page)){
				if("15".equals(deptype)){
					tableName ="pc_rpd_thin_wellw_t";
				}else{
					tableName ="pc_rpd_thin_wellwB_t";
				}					
			}else if("zsrb".equals(page)){
				if("15".equals(deptype)){
					tableName ="pc_rpd_waterflooding_well_t";
				}else{
					tableName ="pc_rpd_waterflooding_wellb_t";
				}
			}else if("zqrb".equals(page)){
				if("15".equals(deptype)){
					tableName ="pc_rpd_gas_daily_t";
				}else{
					tableName ="pc_rpd_gas_dailyb_t";
				}
			}
			String sql = " select * from "+tableName+" t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
			//获取当天数据
			todayDatas = AuthorityUtil.commonssql(sql);
			
			if("15".equals(deptype)){
				
				if(todayDatas == null || todayDatas.size() <= 0){
					this.addFieldError("errermsg", "请先进行数据准备! ");
					return "fail";
				}
			}else{
//				if(todayDatas == null || todayDatas.size() <= 0){
//					this.addFieldError("errermsg", "请联系班组人员尽快进行数据审核! ");
//					return "fail";
//				}
			}
			 List<Object[]> authorityDatas =null;
//			 if("cgcyrb".equals(page) || "xyrb".equals(page) || "zqrb".equals(page)){
//				 //获取数据过滤范围
//				 authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
//			 }else{
//				 authorityDatas = AuthorityUtil.updateDatasAuthorityCheck(user,session);
//			 }
				 //来源页面
			
			if(getUploadFileName() != null && !"".equals(getUploadFileName())){
				if(getUploadFileName().indexOf(".xls") == -1){
					this.addFieldError("errermsg", "系统只允许导入扩展名为.xls的Excel文件! ");
					return "fail";
				}
			}else{
				this.addFieldError("errermsg", "文件路径不能为空，请选择文件进行导入操作!");
				return "fail";
				
			}
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
			 
			   //表头及列明校验
			   if(datas != null && datas.size() >2){
				   List row1 = datas.get(0);
				   if(row1 != null && row1.size()>0 && row1.get(0) !=null && !"".equals(row1.get(0))){
					   List<Integer> excleTile = excelService.excelTile(page, String.valueOf(row1.get(0)));
					   //第一位 0：正确，1失败
					   if(excleTile.get(0) != 0){
						   this.addFieldError("errermsg", "上传Excel文件格式错误，请下载模板重新上传! ");
						   return "fail"; 
					   }else{
						   if(excleTile.get(1) != null){
							   columsun = excleTile.get(1);
						   }
					   }
					   
				   }else{
					   this.addFieldError("errermsg", "上传Excel文件格式错误，请下载模板重新上传! ");
					   return "fail";
				   }
			   }else{
				   this.addFieldError("errermsg", "上传Excel文件格式错误，请下载模板重新上传! ");
				   return "fail";
			   }
			   
			   if("cgcyrb".equals(page) || "xyrb".equals(page)){
				   
				   for(int i=2;i<datas.size();i++){//显示数据
						insertflg = true;
					    row = datas.get(i);
					    nullflg = CommonsUtil.getListNull(row);
					    // 不全部为空：导入数据行内有一值不为空执行 // 过滤数据不需要导入数据库
					    if(nullflg){
					    //判断井名是否为空
					    if(row.get(0) == null || "".equals(row.get(0).toString())|| "null".equals(row.get(0).toString())){
					    
					    	//存在错误信息，
					    	exportFlg = 1;
					    	insertflg = false;  //不执行插入操作
					    	newRow = row;
					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名为空");
					    //判断导入数据是否重复根据井名
					    }else{
					    	for(int z = 2; z<i;z++){
					    		//如何井名存在
					    		if(row.get(0).toString().equals(datas.get(z).get(0).toString())){
					    			exportFlg = 1;
							    	insertflg = false;  //不执行插入操作
							    	newRow = row;
							    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
							    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名重复");
					    		}
					    		
					    	}
					    	
					    }
					    //判断时间是否为当前时间
					    if(row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString())){
						    if(!nowdata.equals(DateBean.clearDateN(row.get(1).toString()))){
						    	//存在错误信息，
						    	exportFlg = 1;
						    	insertflg = false;  //不执行插入操作
						    	newRow = row;
						    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
						    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "报表时间不为当日");
						    }
					    	
					    }else{
					    	//存在错误信息，
					    	exportFlg = 1;
					    	insertflg = false;  //不执行插入操作
					    	newRow = row;
					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "报表时间错误");
					    }	
					    
//					  //数据过滤
//			    		if(insertflg && AuthorityUtil.authorityDos(row.get(0).toString(), authorityDatas) == false){
//			    			//存在错误信息，
//					    	exportFlg = 1;
//					    	insertflg = false;  //不执行插入操作
//					    	newRow = row;
//					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
//					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "您无该井口修改权限");
//			    		}
					    //执行插入操作
					    if(insertflg){
					    	//根据页面传入表示判断 那个页面的操作
					    	dataMap = new HashMap<String, Object>();
					    	if("cgcyrb".equals(page)){
					    		if(row.size() >= columsun-1 && row.get(columsun-2) != null && !"".equals(row.get(columsun-2).toString())){
					    			if("成功".equals(row.get(columsun-2).toString())){
					    				newRow = row;
					    			}else{
					    				//入库操作
							    		dataMap = excelService.insertRuleExcel(deptype, row, user.getOperName(),columsun);
							    		newRow = (List) dataMap.get("list");
					    			}
					    		}else{
					    			//入库操作
						    		dataMap = excelService.insertRuleExcel(deptype, row, user.getOperName(),columsun);
						    		newRow = (List) dataMap.get("list");
					    		}
					    		
					    		
					    		if(dataMap != null && dataMap.get("exportResult") != null && "1".equals(dataMap.get("exportResult").toString())){
					    			//存在错误信息，
							    	exportFlg = 1;
					    		}
					    		
							}else if("xyrb".equals(page)){

					    		if(row.size() >= columsun-1 && row.get(columsun-2) != null && !"".equals(row.get(columsun-2).toString())){
					    			if("成功".equals(row.get(columsun-2).toString())){
					    				newRow = row;
					    			}else{
					    				//入库操作
							    		dataMap = excelService.insertThinExcel(deptype, row, user.getOperName(),columsun);
							    		newRow = (List) dataMap.get("list");
					    			}
					    		}else{
					    			//入库操作
						    		dataMap = excelService.insertThinExcel(deptype, row, user.getOperName(),columsun);
						    		newRow = (List) dataMap.get("list");
					    		}
					    		
					    		
					    		if(dataMap != null && dataMap.get("exportResult") != null && "1".equals(dataMap.get("exportResult").toString())){
					    			//存在错误信息，
							    	exportFlg = 1;
					    		}
								
							}else if("zsrb".equals(page)){

					    		if(row.size() >= columsun-1 && row.get(columsun-2) != null && !"".equals(row.get(columsun-2).toString())){
					    			if("成功".equals(row.get(columsun-2).toString())){
					    				newRow = row;
					    			}else{
					    				//入库操作
							    		dataMap = excelService.insertWaterFoodilExcel(deptype, row, user.getOperName(),columsun);
							    		newRow = (List) dataMap.get("list");
					    			}
					    		}else{
					    			//入库操作
						    		dataMap = excelService.insertWaterFoodilExcel(deptype, row, user.getOperName(),columsun);
						    		newRow = (List) dataMap.get("list");
					    		}
					    		
					    		if(dataMap != null && dataMap.get("exportResult") != null && "1".equals(dataMap.get("exportResult").toString())){
					    			//存在错误信息，
							    	exportFlg = 1;
					    		}
								
							}else if("zqrb".equals(page)){
								//入库操作
								try {
									dataMap = excelService.insertGasDailyExcel(deptype, row, user.getOperName(),columsun);
								} catch (Exception e) {
									e.printStackTrace();
								}
					    		
					    		newRow = (List) dataMap.get("list");
							}else{
								//入库操作
								try {
									dataMap = excelService.insertGasDailyExcel(deptype, row, user.getOperName(),columsun);
								} catch (Exception e) {
									e.printStackTrace();
								}
					    		
					    		newRow = (List) dataMap.get("list");
							}
					    	
					    }
					    outDatas.add(newRow);
				    
				    }
				    
					
				    
//				    for(short n=0;n<row.size() ;n++){
//				     Object value = row.get(n);
//				     String data = String.valueOf(value);
//				     System.out.print(data +"\t");
//				    }
//				    System.out.println();
				   }
			   }else{
				   //"zsrb".equals(page) || "zqrb".equals(page)
				   for(int i=3;i<datas.size();i++){//显示数据
						insertflg = true;
					    row = datas.get(i);
					    nullflg = CommonsUtil.getListNull(row);
					    // 不全部为空：导入数据行内有一值不为空执行 // 过滤数据不需要导入数据库
					    if(nullflg){
					    //判断井名是否为空
					    if(row.get(0) == null || "".equals(row.get(0).toString())|| "null".equals(row.get(0).toString())){
					    
					    	//存在错误信息，
					    	exportFlg = 1;
					    	insertflg = false;  //不执行插入操作
					    	newRow = row;
					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名为空");
					    //判断导入数据是否重复根据井名
					    }else{
					    	for(int z = 3; z<i;z++){
					    		//如何井名存在
					    		if(row.get(0).toString().equals(datas.get(z).get(0).toString())){
					    			exportFlg = 1;
							    	insertflg = false;  //不执行插入操作
							    	newRow = row;
							    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
							    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名重复");
					    		}
					    		
					    	}
					    	
					    }
					    //判断时间是否为当前时间
					    if(row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString())){
						    if(!nowdata.equals(DateBean.clearDateN(row.get(1).toString()))){
						    	//存在错误信息，
						    	exportFlg = 1;
						    	insertflg = false;  //不执行插入操作
						    	newRow = row;
						    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
						    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "报表时间不为当日");
						    }
					    	
					    }else{
					    	//存在错误信息，
					    	exportFlg = 1;
					    	insertflg = false;  //不执行插入操作
					    	newRow = row;
					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "报表时间错误");
					    }	
					    
//					  //数据过滤
//			    		if(insertflg && AuthorityUtil.authorityDos(row.get(0).toString(), authorityDatas) == false){
//			    			//存在错误信息，
//					    	exportFlg = 1;
//					    	insertflg = false;  //不执行插入操作
//					    	newRow = row;
//					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
//					    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "您无该井口修改权限");
//			    		}
					    //执行插入操作
					    if(insertflg){
					    	//根据页面传入表示判断 那个页面的操作
					    	dataMap = new HashMap<String, Object>();
					    	if("cgcyrb".equals(page)){
					    		if(row.size() >= columsun-1 && row.get(columsun-2) != null && !"".equals(row.get(columsun-2).toString())){
					    			if("成功".equals(row.get(columsun-2).toString())){
					    				newRow = row;
					    			}else{
					    				//入库操作
							    		dataMap = excelService.insertRuleExcel(deptype, row, user.getOperName(),columsun);
							    		newRow = (List) dataMap.get("list");
					    			}
					    		}else{
					    			//入库操作
						    		dataMap = excelService.insertRuleExcel(deptype, row, user.getOperName(),columsun);
						    		newRow = (List) dataMap.get("list");
					    		}
					    		
					    		
					    		if(dataMap != null && dataMap.get("exportResult") != null && "1".equals(dataMap.get("exportResult").toString())){
					    			//存在错误信息，
							    	exportFlg = 1;
					    		}
					    		
							}else if("xyrb".equals(page)){

					    		if(row.size() >= columsun-1 && row.get(columsun-2) != null && !"".equals(row.get(columsun-2).toString())){
					    			if("成功".equals(row.get(columsun-2).toString())){
					    				newRow = row;
					    			}else{
					    				//入库操作
							    		dataMap = excelService.insertThinExcel(deptype, row, user.getOperName(),columsun);
							    		newRow = (List) dataMap.get("list");
					    			}
					    		}else{
					    			//入库操作
						    		dataMap = excelService.insertThinExcel(deptype, row, user.getOperName(),columsun);
						    		newRow = (List) dataMap.get("list");
					    		}
					    		
					    		
					    		if(dataMap != null && dataMap.get("exportResult") != null && "1".equals(dataMap.get("exportResult").toString())){
					    			//存在错误信息，
							    	exportFlg = 1;
					    		}
								
							}else if("zsrb".equals(page)){

					    		if(row.size() >= columsun-1 && row.get(columsun-2) != null && !"".equals(row.get(columsun-2).toString())){
					    			if("成功".equals(row.get(columsun-2).toString())){
					    				newRow = row;
					    			}else{
					    				//入库操作
							    		dataMap = excelService.insertWaterFoodilExcel(deptype, row, user.getOperName(),columsun);
							    		newRow = (List) dataMap.get("list");
					    			}
					    		}else{
					    			//入库操作
						    		dataMap = excelService.insertWaterFoodilExcel(deptype, row, user.getOperName(),columsun);
						    		newRow = (List) dataMap.get("list");
					    		}
					    		
					    		if(dataMap != null && dataMap.get("exportResult") != null && "1".equals(dataMap.get("exportResult").toString())){
					    			//存在错误信息，
							    	exportFlg = 1;
					    		}
								
							}else if("zqrb".equals(page)){
								//入库操作
								try {
									dataMap = excelService.insertGasDailyExcel(deptype, row, user.getOperName(),columsun);
								} catch (Exception e) {
									e.printStackTrace();
								}
					    		
					    		newRow = (List) dataMap.get("list");
							}else{
								//入库操作
					    		dataMap = excelService.insertGasDailyExcel(deptype, row, user.getOperName(),columsun);
					    		newRow = (List) dataMap.get("list");
							}
					    	
					    }
					    outDatas.add(newRow);
				    
				    }
				    
					
				    
//				    for(short n=0;n<row.size() ;n++){
//				     Object value = row.get(n);
//				     String data = String.valueOf(value);
//				     System.out.print(data +"\t");
//				    }
//				    System.out.println();
				   }
			   }
			    //插入数据并获取下载数据
			    
				if(outDatas != null && outDatas.size()>0){
					ReportFormsBaseUitl reportU = new ReportFormsBaseUitl();
					if(exportFlg == 1){
						String templetFilePath = "";
						if("cgcyrb".equals(page)){
							templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\importModel\\采出班报模板-稠油.xls";
						}else if("xyrb".equals(page)){
							templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\importModel\\采出班报模板-稀油.xls";
						}else if("zsrb".equals(page)){
							templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\importModel\\注入班报模板-注水.xls";
						}else if("zqrb".equals(page)){
							templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\importModel\\注入班报模板-注汽.xls";

						}else{
						}
						
						HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
						HSSFSheet sheet = wb.getSheetAt(0);
						CellStyle cs1 = wb.createCellStyle();
						cs1 = ExcelUtil.getDataStyle(cs1);
						
						if( "cgcyrb".equals(page) || "xyrb".equals(page)){
							U2DataExportUtil.insertDataByPosition1(outDatas, wb, sheet, RB_START_INSERT);
						}else{
							//"zsrb".equals(page) || "zqrb".equals(page)
							U2DataExportUtil.insertDataByPosition1(outDatas, wb, sheet, RB_ERROR_INSERT);
						}
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
			
		}else{
			
			this.addFieldError("errermsg", "您无导入权限，请联系管理员添加权限! ");
			return "fail";
		}
		
			
		}
	
	
	
	
	}
