package com.echo.dto;

import java.util.Date;

/**
 * PcCdControllerT entity. @author MyEclipse Persistence Tools
 */

public class PcCdControllerT implements java.io.Serializable {

	// Fields

	private String controllerid;
	private String instrumentationid;
	//private PcCdInstruMentationT pcCdInstruMentationT;
	private String facilityName;
	private String communicationType;
	private String ipAddress;
	private String stationNo;
	private String communicationPort;
	private String equipmentStatus;
	private String rlastOper;
	private Date rlastOdate;
	private String remark;

	// Constructors

	/** default constructor */
	public PcCdControllerT() {
	}

	/** full constructor */
	
	// Property accessors

	public String getControllerid() {
		return this.controllerid;
	}

	public void setControllerid(String controllerid) {
		this.controllerid = controllerid;
	}

	public String getInstrumentationid() {
		return instrumentationid;
	}

	public void setInstrumentationid(String instrumentationid) {
		this.instrumentationid = instrumentationid;
	}

	public String getFacilityName() {
		return this.facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getCommunicationType() {
		return this.communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getStationNo() {
		return this.stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getCommunicationPort() {
		return this.communicationPort;
	}

	public void setCommunicationPort(String communicationPort) {
		this.communicationPort = communicationPort;
	}

	public String getEquipmentStatus() {
		return this.equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
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

}