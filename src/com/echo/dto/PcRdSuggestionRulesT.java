package com.echo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PcRdSuggestionRulesT {

	private String ruleId;
	private String sagdId;
	private Date ruleTime;
	private BigDecimal currentSubcool;
	private BigDecimal offsetSubcool;
	private BigDecimal offsetFlow;
	private BigDecimal offsetJigFrequency;
	private BigDecimal offsetFlowNipple;
	private BigDecimal reserve1;
	private BigDecimal reserve2;
	private BigDecimal reserve3;
	
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getSagdId() {
		return sagdId;
	}
	public void setSagdId(String sagdId) {
		this.sagdId = sagdId;
	}
	public Date getRuleTime() {
		return ruleTime;
	}
	public void setRuleTime(Date ruleTime) {
		this.ruleTime = ruleTime;
	}
	public BigDecimal getCurrentSubcool() {
		return currentSubcool;
	}
	public void setCurrentSubcool(BigDecimal currentSubcool) {
		this.currentSubcool = currentSubcool;
	}
	public BigDecimal getOffsetSubcool() {
		return offsetSubcool;
	}
	public void setOffsetSubcool(BigDecimal offsetSubcool) {
		this.offsetSubcool = offsetSubcool;
	}
	public BigDecimal getOffsetFlow() {
		return offsetFlow;
	}
	public void setOffsetFlow(BigDecimal offsetFlow) {
		this.offsetFlow = offsetFlow;
	}
	public BigDecimal getOffsetJigFrequency() {
		return offsetJigFrequency;
	}
	public void setOffsetJigFrequency(BigDecimal offsetJigFrequency) {
		this.offsetJigFrequency = offsetJigFrequency;
	}
	public BigDecimal getOffsetFlowNipple() {
		return offsetFlowNipple;
	}
	public void setOffsetFlowNipple(BigDecimal offsetFlowNipple) {
		this.offsetFlowNipple = offsetFlowNipple;
	}
	public BigDecimal getReserve1() {
		return reserve1;
	}
	public void setReserve1(BigDecimal reserve1) {
		this.reserve1 = reserve1;
	}
	public BigDecimal getReserve2() {
		return reserve2;
	}
	public void setReserve2(BigDecimal reserve2) {
		this.reserve2 = reserve2;
	}
	public BigDecimal getReserve3() {
		return reserve3;
	}
	public void setReserve3(BigDecimal reserve3) {
		this.reserve3 = reserve3;
	}
	
}
