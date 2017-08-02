package com.lu.wang.grepFundData;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.lu.wang.grepFundData.bean.DayValue;
import com.lu.wang.grepFundData.bean.FundInfo;
import com.lu.wang.grepFundData.utils.Constants;
import com.lu.wang.grepFundData.utils.PropertiesUtil;

public class GrepIds {
	
	static final String EXTRA_CONTENT = "=";
	
	private GrepIds(){
	}
	
	public static void getFundInfo() {
		
		String baseUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
				Constants.TTFund.OPEN_STOCK_URL);
		
		//获取页码
		int pageCnt = getPageCnt(baseUrl + 1);
		
		for(int pageNo = 1; pageNo<=pageCnt; pageNo++) {
			//每一页的FUND
			
			String baseHtml = getTable(baseUrl + pageNo);
			
			List<FundInfo> fundInfos = analyzeHTMLByString(baseHtml, pageNo);
			
			for(FundInfo fundInfo : fundInfos) {
				//get到当前页FundInfo
				System.out.println("Fund Name: " + fundInfo.getFundName());
				//270010&page=1&per=20
				String dailyRate1stPageUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
						Constants.TTFund.OPEN_STOCK_EVERY_URL) + fundInfo.getFundId() 
						+ "&page=" + 1 + "&per=50"; 
				
				int cnt = getPageCnt(dailyRate1stPageUrl);
				
				List<DayValue> dailys = new ArrayList<DayValue>();
				
				for(int subPageNo = 1; subPageNo<=cnt; subPageNo++) {
					
					String dailyRateUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
							Constants.TTFund.OPEN_STOCK_EVERY_URL) + fundInfo.getFundId() 
							+ "&page=" + subPageNo + "&per=50"; 
					
					String dailyRateHtml = getTable(dailyRateUrl);
					
					dailys.addAll(analyzeDailyHTMLByString(dailyRateHtml, subPageNo, cnt, fundInfo.getFundId()));
					
				}
				
				CalculateDrawdown.drawdown(dailys);
			}
			
//			outputFundInfos(fundInfos);
			
		}
		
	}
	
	private static String getTable(String url) {
		
		String htmlStr = pickData(url);
		if(!StringUtil.isBlank(htmlStr)) {

			//去除末尾多余分号
			if((htmlStr.length()-1) == htmlStr.lastIndexOf(";")) {
				htmlStr = htmlStr.substring(0, htmlStr.length()-1);
			}
			
			JSONObject json = JSONObject.fromObject(htmlStr);
			if(json.containsKey("table") && 
				json.containsKey("count")) {
				
				return (String) json.get("table");
				
			} else if(json.containsKey("content") && 
					json.containsKey("pages")) {
				
				return (String) json.get("content");
				
			}
			
		}
		
		System.err.println("Empty page of No. 【" + url.substring(url.indexOf("page=")) + "】");
		return null;
		
	}
	
	private static int getPageCnt(String url) {
		
		String htmlStr = pickData(url);
		if(!StringUtil.isBlank(htmlStr)) {
			
			//去除末尾多余分号
			if((htmlStr.length()-1) == htmlStr.lastIndexOf(";")) {
				htmlStr = htmlStr.substring(0, htmlStr.length()-1);
			}
			
			JSONObject json = JSONObject.fromObject(htmlStr);
			if(json.containsKey("table") && 
				json.containsKey("count")) {
				
				return (int) json.get("count");
				
			} else if(json.containsKey("content") && 
					json.containsKey("pages")) {
				
				return (int) json.get("pages");
			}
			
		}
		
		return 0;
		
	}
	
	private static String pickData(String url) {
		String rawStr = pickRawData(url);
		if(null != rawStr && -1 != rawStr.indexOf(EXTRA_CONTENT)) {
			return rawStr.substring(rawStr.indexOf(EXTRA_CONTENT) + 1);
		} else return null;
	}
	
	private static String pickRawData(String url) {
		
		CloseableHttpClient hc = HttpClients.createDefault();
		
		try {
			
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse resp = hc.execute(httpget);
			
			try {
				
				HttpEntity entity = resp.getEntity();
				if(null != entity) {
					return EntityUtils.toString(entity);
				}
				
			} finally {
				resp.close();
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				hc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	private static List<DayValue> analyzeDailyHTMLByString(String tableHtml, int pageNo, int maxPage, String fundId) {
		
		List<DayValue> dailys = new ArrayList<DayValue>();
		Document doc = Jsoup.parse(tableHtml);

		Elements eles = doc.getElementsByTag(Constants.htmlTag.TBODY);
		
		if(null != eles && eles.size() == 1) {
			Elements trs = eles.get(0).getElementsByTag(Constants.htmlTag.TR);
			
			if(null != trs && trs.size() > 0) {
				
				for(int i=0; i<trs.size(); i++) {
					//just need first three TDs.
					if(null != trs.get(i)) {
						Elements tds = trs.get(i).getElementsByTag(Constants.htmlTag.TD);
						if(null != tds && 4 < tds.size()) {
							
							DayValue daily = new DayValue();
							
							daily.setDate(tds.get(0).text());
							daily.setUnitWorth(new BigDecimal(tds.get(1).text()));
							
							//累积净值若为空，则提示
							if(tds.get(2).text().trim().isEmpty()) {
								System.err.println("No acc worth for " + "Fund id : " + fundId + " --【pageNo:" + pageNo + ", maxPage: " + maxPage + ", (i+1): " + (i+1) + ", trs.size: "+ trs.size() + "  --】");
							}
							daily.setAccuWorth(new BigDecimal(tds.get(2).text()));
							if(tds.get(3).text().trim().isEmpty()) {
								if((pageNo != maxPage) || ((i+1) != trs.size())) {
									System.err.println("Fund id : " + fundId + " --【pageNo:" + pageNo + ", maxPage: " + maxPage + ", (i+1): " + (i+1) + ", trs.size: "+ trs.size() + "  --】");
								}
								daily.setDailyGrowthRate(0.0F);
							} else {
								
								daily.setDailyGrowthRate(Float.parseFloat(tds.get(3).text().replace("%", "")));
							}
							dailys.add(daily);
							
						} else {
							System.err.println("No enough info of td in page : 《" + pageNo + "》  in tBody no. of : 《" + i + "》");
						}
						
					} else {
						System.err.println("Empty tr in page : 《" + pageNo + "》   and No. : 《" + i + "》");
					}
				}
			} else {
				System.err.println("Empty in this tBody of in page : 《" + pageNo + "》");
			}
			
		} else {
			
			System.err.println("Error, there's no tr in tbody or too many in page : 《" + pageNo + "》");
			
		}
		
		return dailys;
	}
	
	private static List<FundInfo> analyzeHTMLByString(String tableHtml, int pageNo) {
		
		List<FundInfo> fundInfos = new ArrayList<FundInfo>();
		Document doc = Jsoup.parse(tableHtml);

		Elements trsInTable = doc.getElementsByTag(Constants.htmlTag.TR);
		
		if(null != trsInTable && trsInTable.size() > 2) {
			
			for(int i=2; i<trsInTable.size(); i++) {
				//just need first three TDs.
				if(null != trsInTable.get(i)) {
					Elements tdsInTr = trsInTable.get(i).getElementsByTag(Constants.htmlTag.TD);
					if(null != tdsInTr && 3 < tdsInTr.size()) {
						
						FundInfo fundInfo = new FundInfo();
						
						fundInfo.setId(Integer.parseInt(tdsInTr.get(0).text()));
						fundInfo.setFundId(tdsInTr.get(1).text());
						fundInfo.setFundName(tdsInTr.get(2).text());
						
						fundInfos.add(fundInfo);
						
					} else {
						System.err.println("No enough info in page : 【" + pageNo + "】   of td in tr no. of : 【" + i + "】");
					}
					
				} else {
					System.err.println("Empty in page : 【" + pageNo + "】  in this tr of no. : 【" + i + "】");
				}
			}
			
		} else {
			
			System.err.println("Error trs in page : 【" + pageNo + "】");
			
		}
		
		return fundInfos;
	}
	
	public static void outputFundInfos(List<FundInfo> infos) {
		
		for(FundInfo info : infos) {
			
			System.out.println(info.getId() + "    " + info.getFundId() + "     " + info.getFundName());
			
		}
		
	}
	
	public static void main(String[] args) {
		getFundInfo();
	}

}
