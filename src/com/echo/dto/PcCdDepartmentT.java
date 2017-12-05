package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PcCdDepartmentT entity. @author MyEclipse Persistence Tools
 */

public class PcCdDepartmentT implements java.io.Serializable {

	private static final long serialVersionUID = -3139747003144115369L;
	private String departmentid;
	//private PcCdOrgT pcCdOrgT;
	private String departmentName;
	private String departmentHeader;
	private String departmentAddress;
	private Long departmentPhone;
	private Date rlastOdate;
	private String rlastOper;
	private String remark;
	private Set pcCdUserTs = new HashSet(0);

	// Constructors

	/** default constructor */
	public PcCdDepartmentT() {
	}

	/** minimal constructor */
	public PcCdDepartmentT(String departmentName, Date rlastOdate,
			String rlastOper) {
		this.departmentName = departmentName;
		this.rlastOdate = rlastOdate;
		this.rlastOper = rlastOper;
	}

	/** full constructor */
	public PcCdDepartmentT( String departmentName,
			String departmentHeader, String departmentAddress,
			Long departmentPhone, Date rlastOdate, String rlastOper,
			String remark, Set pcCdUserTs) {
		//this.pcCdOrgT = pcCdOrgT;
		this.departmentName = departmentName;
		this.departmentHeader = departmentHeader;
		this.departmentAddress = departmentAddress;
		this.departmentPhone = departmentPhone;
		this.rlastOdate = rlastOdate;
		this.rlastOper = rlastOper;
		this.remark = remark;
		this.pcCdUserTs = pcCdUserTs;
	}

	// Property accessors

	public String getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

//	public PcCdOrgT getPcCdOrgT() {
//		return this.pcCdOrgT;
//	}
//
//	public void setPcCdOrgT(PcCdOrgT pcCdOrgT) {
//		this.pcCdOrgT = pcCdOrgT;
//	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentHeader() {
		return this.departmentHeader;
	}

	public void setDepartmentHeader(String departmentHeader) {
		this.departmentHeader = departmentHeader;
	}

	public String getDepartmentAddress() {
		return this.departmentAddress;
	}

	public void setDepartmentAddress(String departmentAddress) {
		this.departmentAddress = departmentAddress;
	}

	public Long getDepartmentPhone() {
		return this.departmentPhone;
	}

	public void setDepartmentPhone(Long departmentPhone) {
		this.departmentPhone = departmentPhone;
	}

	public Date getRlastOdate() {
		return this.rlastOdate;
	}

	public void setRlastOdate(Date rlastOdate) {
		this.rlastOdate = rlastOdate;
	}

	public String getRlastOper() {
		return this.rlastOper;
	}

	public void setRlastOper(String rlastOper) {
		this.rlastOper = rlastOper;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getPcCdUserTs() {
		return this.pcCdUserTs;
	}

	public void setPcCdUserTs(Set pcCdUserTs) {
		this.pcCdUserTs = pcCdUserTs;
	}

}