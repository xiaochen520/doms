package com.echo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PcRdSubcoolCalResultsT implements java.io.Serializable {

	private String calrdId;
	private String sagdId;
	private Date calculateTime;
	private Integer calculateMethod;
	private BigDecimal currentSubcool;
	private BigDecimal offsetSubcool;
	private BigDecimal maxSubcool;
	private BigDecimal minSubcool;
	private BigDecimal currentFlow;
	private BigDecimal currentJigFrequency;
	private BigDecimal currentFlowNipple;
	private BigDecimal suggestFlow;
	private BigDecimal suggestJigFrequency;
	private BigDecimal suggestFlowNipple;
	private String sagdrdId;
	private BigDecimal operatePress;
	private Integer isModify;

	public String getCalrdId() {
		return calrdId;
	}
	public void setCalrdId(String calrdId) {
		this.calrdId = calrdId;
	}
	public String getSagdId() {
		return sagdId;
	}
	public void setSagdId(String sagdId) {
		this.sagdId = sagdId;
	}
	public Date getCalculateTime() {
		return calculateTime;
	}
	public void setCalculateTime(Date calculateTime) {
		this.calculateTime = calculateTime;
	}
	public Integer getCalculateMethod() {
		return calculateMethod;
	}
	public void setCalculateMethod(Integer calculateMethod) {
		this.calculateMethod = calculateMethod;
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
	public BigDecimal getMaxSubcool() {
		return maxSubcool;
	}
	public void setMaxSubcool(BigDecimal maxSubcool) {
		this.maxSubcool = maxSubcool;
	}
	public BigDecimal getMinSubcool() {
		return minSubcool;
	}
	public void setMinSubcool(BigDecimal minSubcool) {
		this.minSubcool = minSubcool;
	}
	public BigDecimal getCurrentFlow() {
		return currentFlow;
	}
	public void setCurrentFlow(BigDecimal currentFlow) {
		this.currentFlow = currentFlow;
	}
	public BigDecimal getCurrentJigFrequency() {
		return currentJigFrequency;
	}
	public void setCurrentJigFrequency(BigDecimal currentJigFrequency) {
		this.currentJigFrequency = currentJigFrequency;
	}
	public BigDecimal getCurrentFlowNipple() {
		return currentFlowNipple;
	}
	public void setCurrentFlowNipple(BigDecimal currentFlowNipple) {
		this.currentFlowNipple = currentFlowNipple;
	}
	public BigDecimal getSuggestFlow() {
		return suggestFlow;
	}
	public void setSuggestFlow(BigDecimal suggestFlow) {
		this.suggestFlow = suggestFlow;
	}
	public BigDecimal getSuggestJigFrequency() {
		return suggestJigFrequency;
	}
	public void setSuggestJigFrequency(BigDecimal suggestJigFrequency) {
		this.suggestJigFrequency = suggestJigFrequency;
	}
	public BigDecimal getSuggestFlowNipple() {
		return suggestFlowNipple;
	}
	public void setSuggestFlowNipple(BigDecimal suggestFlowNipple) {
		this.suggestFlowNipple = suggestFlowNipple;
	}
	public String getSagdrdId() {
		return sagdrdId;
	}
	public void setSagdrdId(String sagdrdId) {
		this.sagdrdId = sagdrdId;
	}
	public BigDecimal getOperatePress() {
		return operatePress;
	}
	public void setOperatePress(BigDecimal operatePress) {
		this.operatePress = operatePress;
	}
	public Integer getIsModify() {
		return isModify;
	}
	public void setIsModify(Integer isModify) {
		this.isModify = isModify;
	}
}
