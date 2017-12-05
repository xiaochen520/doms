package com.echo.dto;

import java.util.Date;

/**
 * PcCdWaterInjectiontopryT entity. @author MyEclipse Persistence Tools
 */

public class PcCdWaterInjectiontopryT implements java.io.Serializable {

	
	private static final long serialVersionUID = -1565490694068545L;
	private String waterInjectiontoprid;
	private PcCdOrgT pcCdOrgT;
	private String waterInjectiontoprName;
	private String rlastOper;
	private Date rlastOdate;
	private String remark;
	private String qkid;
	private  String dyear;
	// Constructors

	/** default constructor */
	public PcCdWaterInjectiontopryT() {
	}

	

	// Property accessors

	
	public String getWaterInjectiontoprid() {
		return this.waterInjectiontoprid;
	}

	public String getDyear() {
		return dyear;
	}



	public void setDyear(String dyear) {
		this.dyear = dyear;
	}



	public void setWaterInjectiontoprid(String waterInjectiontoprid) {
		this.waterInjectiontoprid = waterInjectiontoprid;
	}

	public PcCdOrgT getPcCdOrgT() {
		return this.pcCdOrgT;
	}

	public void setPcCdOrgT(PcCdOrgT pcCdOrgT) {
		this.pcCdOrgT = pcCdOrgT;
	}

	public String getWaterInjectiontoprName() {
		return this.waterInjectiontoprName;
	}

	public void setWaterInjectiontoprName(String waterInjectiontoprName) {
		this.waterInjectiontoprName = waterInjectiontoprName;
	}


	public String getRlastOper() {
		return this.rlastOper;
	}

	public void setRlastOper(String rlastOper) {
		this.rlastOper = rlastOper;
	}

	public Date getRlastOdate() {
		return this.rlastOdate;
	}

	public void setRlastOdate(Date rlastOdate) {
		this.rlastOdate = rlastOdate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQkid() {
		return qkid;
	}

	public void setQkid(String qkid) {
		this.qkid = qkid;
	}

}