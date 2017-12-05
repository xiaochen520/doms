package com.echo.dto;

import java.math.BigDecimal;
import java.sql.Date;
/**
 * 
 * @author  王博
 * @date 2017-5-3
 * @time 下午02:58:01
 *
 */
public class BoilerRoomGasMonitoringData implements java.io.Serializable{
	private Integer brgmdId;// 主键
	private Date acquisitionTime;// 采集时间
	private String blockStationName;// 转接名称
	private BigDecimal h2sConcentration_1;// 1#锅炉H2S浓度（PPM）
	private BigDecimal cC_Gas_1;// 1#锅炉可燃气体浓度（%）
	private BigDecimal h2sConcentration_2;// 2#锅炉H2S浓度（PPM）
	private BigDecimal cC_Gas_2;// 2#锅炉可燃气体浓度（%）
	private BigDecimal h2sConcentration_3;// 3#锅炉H2S浓度（PPM）
	private BigDecimal cC_Gas_3;// 3#锅炉可燃气体浓度（%）
	private BigDecimal h2sConcentration_4;// 4#锅炉H2S浓度（PPM）
	private BigDecimal cC_Gas_4;// 4#锅炉可燃气体浓度（%）
	private BigDecimal h2sConcentration_5;// 5#锅炉H2S浓度（PPM）
	private BigDecimal cC_Gas_5;// 5#锅炉可燃气体浓度（%）
	private BigDecimal reserved1;
	private BigDecimal reserved2;
	private int reserved3;
	private int reserved4;
	private String reserved5;
	private String reserved6;

	public BoilerRoomGasMonitoringData() {
	}

	public BoilerRoomGasMonitoringData(Integer brgmdId) {
		super();
		this.brgmdId = brgmdId;
	}

	public BoilerRoomGasMonitoringData(Integer brgmdId, Date acquisitionTime,
			String blockStationName) {
		super();
		this.brgmdId = brgmdId;
		this.acquisitionTime = acquisitionTime;
		this.blockStationName = blockStationName;
	}

	public BoilerRoomGasMonitoringData(Integer brgmdId, Date acquisitionTime,
			String blockStationName, BigDecimal h2sConcentration_1,
			BigDecimal cCGas_1, BigDecimal h2sConcentration_2,
			BigDecimal cCGas_2, BigDecimal h2sConcentration_3,
			BigDecimal cCGas_3, BigDecimal h2sConcentration_4,
			BigDecimal cCGas_4, BigDecimal h2sConcentration_5,
			BigDecimal cCGas_5, BigDecimal reserved1, BigDecimal reserved2,
			int reserved3, int reserved4, String reserved5, String reserved6) {
		super();
		this.brgmdId = brgmdId;
		this.acquisitionTime = acquisitionTime;
		this.blockStationName = blockStationName;
		this.h2sConcentration_1 = h2sConcentration_1;
		cC_Gas_1 = cCGas_1;
		this.h2sConcentration_2 = h2sConcentration_2;
		cC_Gas_2 = cCGas_2;
		this.h2sConcentration_3 = h2sConcentration_3;
		cC_Gas_3 = cCGas_3;
		this.h2sConcentration_4 = h2sConcentration_4;
		cC_Gas_4 = cCGas_4;
		this.h2sConcentration_5 = h2sConcentration_5;
		cC_Gas_5 = cCGas_5;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.reserved4 = reserved4;
		this.reserved5 = reserved5;
		this.reserved6 = reserved6;
	}

	public Integer getBrgmdId() {
		return brgmdId;
	}

	public void setBrgmdId(Integer brgmdId) {
		this.brgmdId = brgmdId;
	}

	public Date getAcquisitionTime() {
		return acquisitionTime;
	}

	public void setAcquisitionTime(Date acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}

	public String getBlockStationName() {
		return blockStationName;
	}

	public void setBlockStationName(String blockStationName) {
		this.blockStationName = blockStationName;
	}

	public BigDecimal getH2sConcentration_1() {
		return h2sConcentration_1;
	}

	public void setH2sConcentration_1(BigDecimal h2sConcentration_1) {
		this.h2sConcentration_1 = h2sConcentration_1;
	}

	public BigDecimal getcC_Gas_1() {
		return cC_Gas_1;
	}

	public void setcC_Gas_1(BigDecimal cCGas_1) {
		cC_Gas_1 = cCGas_1;
	}

	public BigDecimal getH2sConcentration_2() {
		return h2sConcentration_2;
	}

	public void setH2sConcentration_2(BigDecimal h2sConcentration_2) {
		this.h2sConcentration_2 = h2sConcentration_2;
	}

	public BigDecimal getcC_Gas_2() {
		return cC_Gas_2;
	}

	public void setcC_Gas_2(BigDecimal cCGas_2) {
		cC_Gas_2 = cCGas_2;
	}

	public BigDecimal getH2sConcentration_3() {
		return h2sConcentration_3;
	}

	public void setH2sConcentration_3(BigDecimal h2sConcentration_3) {
		this.h2sConcentration_3 = h2sConcentration_3;
	}

	public BigDecimal getcC_Gas_3() {
		return cC_Gas_3;
	}

	public void setcC_Gas_3(BigDecimal cCGas_3) {
		cC_Gas_3 = cCGas_3;
	}

	public BigDecimal getH2sConcentration_4() {
		return h2sConcentration_4;
	}

	public void setH2sConcentration_4(BigDecimal h2sConcentration_4) {
		this.h2sConcentration_4 = h2sConcentration_4;
	}

	public BigDecimal getcC_Gas_4() {
		return cC_Gas_4;
	}

	public void setcC_Gas_4(BigDecimal cCGas_4) {
		cC_Gas_4 = cCGas_4;
	}

	public BigDecimal getH2sConcentration_5() {
		return h2sConcentration_5;
	}

	public void setH2sConcentration_5(BigDecimal h2sConcentration_5) {
		this.h2sConcentration_5 = h2sConcentration_5;
	}

	public BigDecimal getcC_Gas_5() {
		return cC_Gas_5;
	}

	public void setcC_Gas_5(BigDecimal cCGas_5) {
		cC_Gas_5 = cCGas_5;
	}

	public BigDecimal getReserved1() {
		return reserved1;
	}

	public void setReserved1(BigDecimal reserved1) {
		this.reserved1 = reserved1;
	}

	public BigDecimal getReserved2() {
		return reserved2;
	}

	public void setReserved2(BigDecimal reserved2) {
		this.reserved2 = reserved2;
	}

	public int getReserved3() {
		return reserved3;
	}

	public void setReserved3(int reserved3) {
		this.reserved3 = reserved3;
	}

	public int getReserved4() {
		return reserved4;
	}

	public void setReserved4(int reserved4) {
		this.reserved4 = reserved4;
	}

	public String getReserved5() {
		return reserved5;
	}

	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
	}

	public String getReserved6() {
		return reserved6;
	}

	public void setReserved6(String reserved6) {
		this.reserved6 = reserved6;
	}

}
