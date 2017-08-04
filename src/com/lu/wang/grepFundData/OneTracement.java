package com.lu.wang.grepFundData;

import java.util.ArrayList;
import java.util.List;

import com.lu.wang.grepFundData.bean.DayValue;
import com.lu.wang.grepFundData.utils.Constants;
import com.lu.wang.grepFundData.utils.PropertiesUtil;

public class OneTracement {

	public static String fundId = "310198";//310198 161227 110005
	
	public static void calculateOne() {
		
		//270010&page=1&per=20
		String dailyRate1stPageUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
				Constants.TTFund.OPEN_STOCK_EVERY_URL) + fundId 
				+ "&page=" + 1 + "&per=50"; 
		String url1 = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=310398&page=";
		String url2 = "&per=20&sdate=&edate=&rt=0.8154297076684172";
		int cnt = GrepIds.getPageCnt(url1+"1"+url2);
		
		List<DayValue> dailys = new ArrayList<DayValue>();
		
		for(int subPageNo = 1; subPageNo<=cnt; subPageNo++) {
			
			String dailyRateUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
					Constants.TTFund.OPEN_STOCK_EVERY_URL) + fundId 
					+ "&page=" + subPageNo + "&per=50"; 
			
			String dailyRateHtml = GrepIds.getTable(url1 + subPageNo + url2);
			
			dailys.addAll(GrepIds.analyzeDailyHTMLByString(dailyRateHtml, subPageNo, cnt, fundId));
			
		}
		
		CalculateDrawdown.drawdown(dailys);
	
		
	}
	
	public static void main(String[] args) {
		calculateOne();
	}
	
}
