package com.lu.wang.grepFundData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.lu.wang.grepFundData.bean.DayValue;

/**
 * 
 * @author wl
 * D为某一天的净值，i为某一天，j为i后的某一天，Di为第i天的产品净值，Dj则是Di后面某一天的净值
 * drawdown就是最大回撤率，drawdown=max（Di-Dj）/Di，
 * 其实就是对每一个净值进行回撤率求值，然后找出最大的。
 * 
 */
public class CalculateDrawdown {
	
	public static void drawdown(List<DayValue> values) {
		
		BigDecimal maxValue = new BigDecimal(0);
		//日期由近及远，故需倒着计算
		for(int i=values.size()-1; i>=1; i--) {
			//从最早一天到昨天为止，分别计算
			BigDecimal maxOfThisDay = new BigDecimal(0);
			BigDecimal baseBd = values.get(i).getUnitWorth();//以当前值为基准，找到最大回撤
			
			for(int j=values.size()-2; j>=0; j--) {
				
				//第j天晚于第i天
				BigDecimal bd = values.get(j).getUnitWorth();
				BigDecimal drawRate = (baseBd.subtract(bd)).divide(baseBd, 7, RoundingMode.HALF_DOWN);
				
				if(maxOfThisDay.compareTo(drawRate) < 0) {
					maxOfThisDay = drawRate;
				}
			}
			
			if(maxValue.compareTo(maxOfThisDay) < 0) {
				maxValue = maxOfThisDay;
			}
		}
		
		if(maxValue.multiply(new BigDecimal(100)).compareTo(new BigDecimal(20)) > 0) {
			
			System.out.println("T^T  " + (maxValue.multiply(new BigDecimal(100))) + "%  T^T");
		} else {
			System.out.println("(⊙_⊙)  " + (maxValue.multiply(new BigDecimal(100))) + "%  (⊙_⊙)");
		}
	}

}
