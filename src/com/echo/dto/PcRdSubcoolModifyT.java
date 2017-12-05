package com.echo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PcRdSubcoolModifyT {
	private String calRdId;
	private Date modifyTime;
	private String modifyUser;
	private BigDecimal modifyFlow;
	private BigDecimal modifyJigFrequency;
	private BigDecimal modifyFlowNipple;
	private Integer modifyMode;
	
	public String getCalRdId() {
		return calRdId;
	}
	public void setCalRdId(String calRdId) {
		this.calRdId = calRdId;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public BigDecimal getModifyFlow() {
		return modifyFlow;
	}
	public void setModifyFlow(BigDecimal modifyFlow) {
		this.modifyFlow = modifyFlow;
	}
	public BigDecimal getModifyJigFrequency() {
		return modifyJigFrequency;
	}
	public void setModifyJigFrequency(BigDecimal modifyJigFrequency) {
		this.modifyJigFrequency = modifyJigFrequency;
	}
	public BigDecimal getModifyFlowNipple() {
		return modifyFlowNipple;
	}
	public void setModifyFlowNipple(BigDecimal modifyFlowNipple) {
		this.modifyFlowNipple = modifyFlowNipple;
	}
	public Integer getModifyMode() {
		return modifyMode;
	}
	public void setModifyMode(Integer modifyMode) {
		this.modifyMode = modifyMode;
	}
}
