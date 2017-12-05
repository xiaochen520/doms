package com.echo.dto;

import java.util.Date;

/**
 * PcCdStationT entity. @author MyEclipse Persistence Tools
 */

public class PcCdStationT implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1546065477195515225L;
	private String blockstationid;
	private PcCdOrgT pcCdOrgT;
	private String blockstationName;
	private Byte ghs;
	private Byte cygs;
	private String cyggg;
	private Long zybs;
	private String rlastOper;
	private Date rlastOdate;
	private String bsType;
	private String remark;
	private Integer boilerQty;
	private String steamInjectionOwner;
	private String steamRemark;
	private PcCdAreaInfoT pcCdAreaInfoT;
	private String dyear;

	
	


	// Constructors
    
	public Integer getBoilerQty() {
		return boilerQty;
	}

	public void setBoilerQty(Integer boilerQty) {
		this.boilerQty = boilerQty;
	}

	public String getSteamInjectionOwner() {
		return steamInjectionOwner;
	}

	public void setSteamInjectionOwner(String steamInjectionOwner) {
		this.steamInjectionOwner = steamInjectionOwner;
	}

	public String getSteamRemark() {
		return steamRemark;
	}

	public void setSteamRemark(String steamRemark) {
		this.steamRemark = steamRemark;
	}

	/** default constructor */
	
	public PcCdStationT() {
	}

	public PcCdAreaInfoT getPcCdAreaInfoT() {
		return pcCdAreaInfoT;
	}



	public void setPcCdAreaInfoT(PcCdAreaInfoT pcCdAreaInfoT) {
		this.pcCdAreaInfoT = pcCdAreaInfoT;
	}


	public String getBlockstationid() {
		return this.blockstationid;
	}

	public void setBlockstationid(String blockstationid) {
		this.blockstationid = blockstationid;
	}

	public PcCdOrgT getPcCdOrgT() {
		return this.pcCdOrgT;
	}

	public void setPcCdOrgT(PcCdOrgT pcCdOrgT) {
		this.pcCdOrgT = pcCdOrgT;
	}

	public String getBlockstationName() {
		return this.blockstationName;
	}

	public void setBlockstationName(String blockstationName) {
		this.blockstationName = blockstationName;
	}

	public Byte getGhs() {
		return this.ghs;
	}

	public void setGhs(Byte ghs) {
		this.ghs = ghs;
	}

	public Byte getCygs() {
		return this.cygs;
	}

	public void setCygs(Byte cygs) {
		this.cygs = cygs;
	}

	public String getCyggg() {
		return this.cyggg;
	}

	public void setCyggg(String cyggg) {
		this.cyggg = cyggg;
	}

	public Long getZybs() {
		return this.zybs;
	}

	public void setZybs(Long zybs) {
		this.zybs = zybs;
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

	public String getBsType() {
		return this.bsType;
	}

	public void setBsType(String bsType) {
		this.bsType = bsType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDyear() {
		return dyear;
	}

	public void setDyear(String dyear) {
		this.dyear = dyear;
	}

//	public Set getPcRpdBstationWaterjetcTs() {
//		return this.pcRpdBstationWaterjetcTs;
//	}
//
//	public void setPcRpdBstationWaterjetcTs(Set pcRpdBstationWaterjetcTs) {
//		this.pcRpdBstationWaterjetcTs = pcRpdBstationWaterjetcTs;
//	}
//
//	public Set getPcRpdBstationWaterjetdTs() {
//		return this.pcRpdBstationWaterjetdTs;
//	}
//
//	public void setPcRpdBstationWaterjetdTs(Set pcRpdBstationWaterjetdTs) {
//		this.pcRpdBstationWaterjetdTs = pcRpdBstationWaterjetdTs;
//	}
//
//	public Set getPcRpdBstationBoilerdTs() {
//		return this.pcRpdBstationBoilerdTs;
//	}
//
//	public void setPcRpdBstationBoilerdTs(Set pcRpdBstationBoilerdTs) {
//		this.pcRpdBstationBoilerdTs = pcRpdBstationBoilerdTs;
//	}
//
//	public Set getPcRpdBstationTurnoilcTs() {
//		return this.pcRpdBstationTurnoilcTs;
//	}
//
//	public void setPcRpdBstationTurnoilcTs(Set pcRpdBstationTurnoilcTs) {
//		this.pcRpdBstationTurnoilcTs = pcRpdBstationTurnoilcTs;
//	}
//
//	public Set getPcRdBstationWaterjetTs() {
//		return this.pcRdBstationWaterjetTs;
//	}
//
//	public void setPcRdBstationWaterjetTs(Set pcRdBstationWaterjetTs) {
//		this.pcRdBstationWaterjetTs = pcRdBstationWaterjetTs;
//	}
//
//	public Set getPcRdBstationBoilerTs() {
//		return this.pcRdBstationBoilerTs;
//	}
//
//	public void setPcRdBstationBoilerTs(Set pcRdBstationBoilerTs) {
//		this.pcRdBstationBoilerTs = pcRdBstationBoilerTs;
//	}
//
//	public Set getPcRdBstationTurnoilTs() {
//		return this.pcRdBstationTurnoilTs;
//	}
//
//	public void setPcRdBstationTurnoilTs(Set pcRdBstationTurnoilTs) {
//		this.pcRdBstationTurnoilTs = pcRdBstationTurnoilTs;
//	}
//
//	public Set getPcRpdBstationTurnoildTs() {
//		return this.pcRpdBstationTurnoildTs;
//	}
//
//	public void setPcRpdBstationTurnoildTs(Set pcRpdBstationTurnoildTs) {
//		this.pcRpdBstationTurnoildTs = pcRpdBstationTurnoildTs;
//	}
//
//	public Set getPcRpdBstationBoilercTs() {
//		return this.pcRpdBstationBoilercTs;
//	}
//
//	public void setPcRpdBstationBoilercTs(Set pcRpdBstationBoilercTs) {
//		this.pcRpdBstationBoilercTs = pcRpdBstationBoilercTs;
//	}

	
}