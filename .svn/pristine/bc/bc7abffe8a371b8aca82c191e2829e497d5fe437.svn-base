package com.echo.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReportFormsBaseUitl extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream excelFile = null;
	protected HttpServletRequest request = ServletActionContext.getRequest();
	
	public ReportFormsBaseUitl() {
		super();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setHeader("ContentType","text/xml");
	}
	public InputStream getExcelFile() {
		return excelFile;
	}

	public String getFileName(String fileName) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ fileName;
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	
	public void OutputStreamAndCloseBaos(java.io.ByteArrayOutputStream baos) {
		if(baos != null){
			byte[] ba = baos.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
		}
		IOUtils.closeQuietly( baos );
	}
	
	public String getParameters(String p) {
		Object o = ActionContext.getContext().getParameters().get(p);
		if (o == null) {
			return "";
		}
		p = Array.get(o, 0).toString();
		return p;
	}
	
	public HSSFWorkbook getWorkBook(String path) throws IOException {
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + path;
		return ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
	}
	
}
