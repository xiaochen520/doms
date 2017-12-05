package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PcCdGasstation entity. @author MyEclipse Persistence Tools
 */

public class PcCdGasstation implements java.io.Serializable {

	// Fields

	private String stationid;
	private PcCdOrgT pcCdOrgT;
	private String stationName;
	private Byte ghs;
	private Byte cygs;
	private String cyggg;
	private Long zybs;
	private String rlastOper;
	private Date rlastOdate;
	private String bsType;
	private String remark;
	private String dyear;

	private Set pcRpdBstationWaterjetcTs = new HashSet(0);
	private Set pcRpdBstationWaterjetdTs = new HashSet(0);
	private Set pcRpdBstationBoilerdTs = new HashSet(0);
	private Set pcRpdBgasstationurnoilcTs = new HashSet(0);
	private Set pcRdBstationWaterjetTs = new HashSet(0);
	private Set pcRdBstationBoilerTs = new HashSet(0);
	private Set pcRdBgasstationurnoilTs = new HashSet(0);
	private Set pcRpdBgasstationurnoildTs = new HashSet(0);
	private Set pcRpdBstationBoilercTs = new HashSet(0);

	private PcCdAreaInfoT pcCdAreaInfoT;
	


	// Constructors
    
	/** default constructor */
	
	public PcCdGasstation() {
	}

	public PcCdAreaInfoT getPcCdAreaInfoT() {
		return pcCdAreaInfoT;
	}



	public void setPcCdAreaInfoT(PcCdAreaInfoT pcCdAreaInfoT) {
		this.pcCdAreaInfoT = pcCdAreaInfoT;
	}




	// Property accessors
	

	public String getStationid() {
		return this.stationid;
	}

	public String getDyear() {
		return dyear;
	}

	public void setDyear(String dyear) {
		this.dyear = dyear;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public PcCdOrgT getPcCdOrgT() {
		return this.pcCdOrgT;
	}

	public void setPcCdOrgT(PcCdOrgT pcCdOrgT) {
		this.pcCdOrgT = pcCdOrgT;
	}

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
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
//	public Set getPcRpdBgasstationurnoilcTs() {
//		return this.pcRpdBgasstationurnoilcTs;
//	}
//
//	public void setPcRpdBgasstationurnoilcTs(Set pcRpdBgasstationurnoilcTs) {
//		this.pcRpdBgasstationurnoilcTs = pcRpdBgasstationurnoilcTs;
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
//	public Set getPcRdBgasstationurnoilTs() {
//		return this.pcRdBgasstationurnoilTs;
//	}
//
//	public void setPcRdBgasstationurnoilTs(Set pcRdBgasstationurnoilTs) {
//		this.pcRdBgasstationurnoilTs = pcRdBgasstationurnoilTs;
//	}
//
//	public Set getPcRpdBgasstationurnoildTs() {
//		return this.pcRpdBgasstationurnoildTs;
//	}
//
//	public void setPcRpdBgasstationurnoildTs(Set pcRpdBgasstationurnoildTs) {
//		this.pcRpdBgasstationurnoildTs = pcRpdBgasstationurnoildTs;
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