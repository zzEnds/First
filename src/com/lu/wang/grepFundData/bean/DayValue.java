package com.lu.wang.grepFundData.bean;

import java.math.BigDecimal;

public class DayValue {
	
	private Integer id;
	private BigDecimal accuWorth;
	private BigDecimal unitWorth;
	private float dailyGrowthRate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getAccuWorth() {
		return accuWorth;
	}
	public void setAccuWorth(BigDecimal accuWorth) {
		this.accuWorth = accuWorth;
	}
	public BigDecimal getUnitWorth() {
		return unitWorth;
	}
	public void setUnitWorth(BigDecimal unitWorth) {
		this.unitWorth = unitWorth;
	}
	public float getDailyGrowthRate() {
		return dailyGrowthRate;
	}
	public void setDailyGrowthRate(float dailyGrowthRate) {
		this.dailyGrowthRate = dailyGrowthRate;
	}
	
}
