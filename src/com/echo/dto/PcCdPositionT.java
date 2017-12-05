package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PcCdPositionT entity. @author MyEclipse Persistence Tools
 */

public class PcCdPositionT implements java.io.Serializable {
	private static final long serialVersionUID = 1437148895450439889L;
	private String positionid;
	private String position;
	private String positionCode;
	private String hysCoefficient;
	private String qhsCoefficient;
	private Boolean rgblnlbz;
	private String areaCode;
	private String sunitName;
	private String sunitCode;
	private String unitCode;
	private String dqmc;
	private String rlastOper;
	private Date rlastOdate;
	private Set pcCdOrgTs = new HashSet(0);

	// Constructors

	/** default constructor */
	public PcCdPositionT() {
	}


	// Property accessors

	public String getPositionid() {
		return this.positionid;
	}

	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionCode() {
		return this.positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}


	public String getHysCoefficient() {
		return this.hysCoefficient;
	}

	public void setHysCoefficient(String hysCoefficient) {
		this.hysCoefficient = hysCoefficient;
	}

	public String getQhsCoefficient() {
		return this.qhsCoefficient;
	}

	public void setQhsCoefficient(String qhsCoefficient) {
		this.qhsCoefficient = qhsCoefficient;
	}

	public Boolean getRgblnlbz() {
		return this.rgblnlbz;
	}

	public void setRgblnlbz(Boolean rgblnlbz) {
		this.rgblnlbz = rgblnlbz;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSunitName() {
		return this.sunitName;
	}

	public void setSunitName(String sunitName) {
		this.sunitName = sunitName;
	}

	public String getSunitCode() {
		return this.sunitCode;
	}

	public void setSunitCode(String sunitCode) {
		this.sunitCode = sunitCode;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getDqmc() {
		return this.dqmc;
	}

	public void setDqmc(String dqmc) {
		this.dqmc = dqmc;
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

	public Set getPcCdOrgTs() {
		return this.pcCdOrgTs;
	}

	public void setPcCdOrgTs(Set pcCdOrgTs) {
		this.pcCdOrgTs = pcCdOrgTs;
	}

}