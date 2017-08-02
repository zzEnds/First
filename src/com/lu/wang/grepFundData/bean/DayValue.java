package com.lu.wang.grepFundData.bean;

import java.math.BigDecimal;

public class DayValue {
	
	private String date;
	private BigDecimal accuWorth;//累计净值
	private BigDecimal unitWorth;//单位净值
	private float dailyGrowthRate;//日增长率
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
