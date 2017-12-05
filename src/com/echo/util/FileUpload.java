package com.echo.util;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.SmartUpload;

public class FileUpload {
	
	public int upload(PageContext pageContext) {
		int result = 1;
		try {
			// 新建一个SmartUpload对象
			SmartUpload su = new SmartUpload();
			su.initialize(pageContext);
			// 限制每个上传文件的最大长度
			su.setMaxFileSize(30 * 1024 * 1024);
			// 限制总上传文件的长度
			su.setTotalMaxFileSize(100 * 1024 * 1024);
			// 设置允许上传的文件格式
			 su.setAllowedFilesList("xls,xlsx");
			// 设置禁止上传的文件格式
//			su.setDeniedFilesList("exe,dll,bat");
			// 上传文件加载到内存
			su.upload();
			// 将上传文件保存到指定路径
			// 逐一提取上传文件信息，同时可保存文件
			for (int i = 0; i < su.getFiles().getCount(); i++) {
				com.jspsmart.upload.File file = su.getFiles().getFile(i);
				// 若文件不存在则继续
				if (file.isMissing())
					continue;
				// 对图片处理
				String str = file.getFilePathName();
				String postfix = file.getFileExt();
//				if (postfix.toLowerCase().equals(".xls")
//						|| postfix.toLowerCase().equals(".xlsx")) {
//					// 图片加水印
//					Watermars.pressImage("D:\\logo.jpg", str
//							.replace("\\", "//"), 0, 0, 0.6f);
//				}
			}
			// 上传文件到服务器
			String path = "/uploadExcel";
			int count = su.save(path);
		} catch (SecurityException ex) {
			result = 0;
			System.out.println("文件格式不正确");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
