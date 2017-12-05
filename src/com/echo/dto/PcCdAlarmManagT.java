package com.echo.dto;

public class PcCdAlarmManagT {

	private String alarmuserid;
	private String username;
	private String phone;
	private String dept;
	private String alarmkey;
	
	private String dep_manag;
	private String data_unit;
	private String remark;
	private String yuliu3;
	private String yuliu4;
	private String yuliu5;
	
	public PcCdAlarmManagT(String username, String phone, String dept,
			String alarmkey, String dep_manag, String data_unit, String remark,
			String yuliu3, String yuliu4, String yuliu5) {
		super();
		this.username = username;
		this.phone = phone;
		this.dept = dept;
		this.alarmkey = alarmkey;
		this.dep_manag = dep_manag;
		this.data_unit = data_unit;
		this.remark = remark;
		this.yuliu3 = yuliu3;
		this.yuliu4 = yuliu4;
		this.yuliu5 = yuliu5;
	}

	public PcCdAlarmManagT() {
		
	}

	public String getAlarmuserid() {
		return alarmuserid;
	}

	public void setAlarmuserid(String alarmuserid) {
		this.alarmuserid = alarmuserid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getAlarmkey() {
		return alarmkey;
	}

	public void setAlarmkey(String alarmkey) {
		this.alarmkey = alarmkey;
	}

	public String getDep_manag() {
		return dep_manag;
	}

	public void setDep_manag(String dep_manag) {
		this.dep_manag = dep_manag;
	}

	public String getData_unit() {
		return data_unit;
	}

	public void setData_unit(String data_unit) {
		this.data_unit = data_unit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getYuliu3() {
		return yuliu3;
	}

	public void setYuliu3(String yuliu3) {
		this.yuliu3 = yuliu3;
	}

	public String getYuliu4() {
		return yuliu4;
	}

	public void setYuliu4(String yuliu4) {
		this.yuliu4 = yuliu4;
	}

	public String getYuliu5() {
		return yuliu5;
	}

	public void setYuliu5(String yuliu5) {
		this.yuliu5 = yuliu5;
	}


}
