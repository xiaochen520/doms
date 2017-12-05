package com.echo.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * 获取配置文件中系统配置
 * @author yanlong
 *
 */
public class SystemBean {
	private String userName = "";
	private String userPwd = "";//
	private String revenuecode = "";
	private String taxFlag = "";//
	private String Mcount = "";
	private String LocalCarNo = "";//
	private String NewCarYear = "";//
	private Map newTax = null;

	private static SystemBean username;
	PropertiesConfig propertiesConfig = new PropertiesConfig();

	public String getRevenuecode() {
		return revenuecode;
	}

	public void setRevenuecode(String revenuecode) {
		this.revenuecode = revenuecode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getTaxFlag() {
		return taxFlag;
	}

	public void setTaxFlag(String taxFlag) {
		this.taxFlag = taxFlag;
	}

	public String getMcount() {
		return Mcount;
	}

	public void setMcount(String mcount) {
		Mcount = mcount;
	}

	public String getLocalCarNo() {
		return LocalCarNo;
	}

	public void setLocalCarNo(String localCarNo) {
		LocalCarNo = localCarNo;
	}
	
	public static SystemBean getusername() {
		if (username == null)
			username = new SystemBean();
		return username;
	}

	public String getNewCarYear() {
		return NewCarYear;
	}

	public void setNewCarYear(String newCarYear) {
		NewCarYear = newCarYear;
	}

	public Map getNewTax() {
		return newTax;
	}

	protected SystemBean() {
		this.setUserName(propertiesConfig.getSystemConfiguration("username"));
		this.setRevenuecode(propertiesConfig
				.getSystemConfiguration("revenuecode"));
		this.setUserPwd(propertiesConfig.getSystemConfiguration("password"));
		this.setTaxFlag(propertiesConfig.getSystemConfiguration("taxFlag"));
		this.setMcount(propertiesConfig.getSystemConfiguration("MCount"));
		this.setLocalCarNo(propertiesConfig
				.getSystemConfiguration("LocalCarNo"));
		this.setNewCarYear(propertiesConfig
				.getSystemConfiguration("NewCarYear"));
		newTax = this.readProperties("properFile/system.properties");
	}

	public Map readProperties(String filePath) {
		Properties props = new Properties();
		Map hm = new HashMap();
		try {
			String path = "";
			try {
				path = this.getClass().getClassLoader().getResource("/")
						.getPath();
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@1=" + path
						+ filePath);
			} catch (Exception e) {
				path = this.getClass().getResource("/").getPath();
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@2=" + path
						+ filePath);
			}
			path = path.replaceAll("%20", " ");
			InputStream in = new BufferedInputStream(new FileInputStream(path
					+ filePath));
			props.load(in);
			in.close();
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				hm.put(key, Property);
				System.out.println(key + " : " + Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}
	
}
