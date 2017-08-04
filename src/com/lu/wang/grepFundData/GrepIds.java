package com.lu.wang.grepFundData;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
	static int START_PAGE = 17;
	static int STOP_PAGE = 21;
	static int[] top = {705, 722, 746, 768, 781, 782, 786, 792, 794, 763, 757, 
		752, 774, 765, 743, 770, 727, 728, 744, 769, 717, 670, 766, 777, 701, 
		669, 706, 791, 742, 787, 718, 694, 771, 693, 740, 761, 784, 785, 664, 
		602, 651, 677, 737, 754, 684, 775, 687, 600, 695, 557, 536, 662, 719, 
		759, 525, 691, 663, 749, 751, 665, 619, 683, 631, 636, 698, 653, 650, 
		734, 721, 609, 760, 747, 635, 558, 764, 613, 707, 753, 776, 716, 783, 
		715, 601, 730, 482, 731, 163, 520, 700, 593, 594, 615, 627, 623, 686, 
		642, 599, 688, 628, 646, 659, 767, 480, 485, 533, 625, 568, 435, 569, 
		477, 561, 668, 634, 733, 528, 710, 793, 612, 604, 598, 421, 676, 420, 
		329, 585, 532, 758, 617, 575, 541, 616, 704, 624, 473, 611, 596, 345, 
		597, 436, 555, 645, 554, 608, 630, 724, 519, 467, 675, 621, 540, 481, 
		449, 553, 515, 551, 571, 660, 614, 603, 690, 622, 478, 655, 495, 524, 
		450, 439, 626, 535, 534, 538, 486, 610, 468, 476, 584, 487, 539, 484, 305};

	private GrepIds(){
	}
	
	public static void getFundInfo() {
		
		String baseUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
				Constants.TTFund.OPEN_STOCK_URL);
		
		//获取页码
//		int pageCnt = getPageCnt(baseUrl + 1);
		
		for(int pageNo = START_PAGE; pageNo<=STOP_PAGE; pageNo++) {
			//每一页的FUND
			
			String baseHtml = getTable(baseUrl + pageNo);
			
			List<FundInfo> fundInfos = analyzeHTMLByString(baseHtml, pageNo);
			
			for(FundInfo fundInfo : fundInfos) {
				//get到当前页FundInfo
				System.out.println("Fund Name: " + fundInfo.getId() + " . " + fundInfo.getFundName());
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
	
	protected static String getTable(String url) {
		
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
	
	protected static int getPageCnt(String url) {
		
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
	
	public static String pickData(String url) {
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
	
	protected static List<DayValue> analyzeDailyHTMLByString(String tableHtml, int pageNo, int maxPage, String fundId) {
		
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
							} else {
								daily.setAccuWorth(new BigDecimal(tds.get(2).text()));
							}
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
		System.out.println(new Date());
		getFundInfo();
		System.out.println(new Date());
	}

}
