package com.lu.wang.grepFundData;

import java.io.IOException;
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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lu.wang.grepFundData.bean.FundInfo;
import com.lu.wang.grepFundData.utils.Constants;
import com.lu.wang.grepFundData.utils.PropertiesUtil;

public class GrepIds {
	
	static final String EXTRA_CONTENT = "var fundListObj = ";
	
	private GrepIds(){
	}
	
	public static void getFundInfo() {
		
		String baseUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
				Constants.TTFund.OPEN_STOCK_URL);
		
		//获取页码
		int pageCnt = getPageCnt(baseUrl);
		
		for(int pageNo = 1; pageNo<=pageCnt; pageNo++) {
			
			String baseHtml = getTable(baseUrl, pageNo);
			
			List<FundInfo> fundInfos = analyzeHTMLByString(baseHtml, pageNo);
			
			outputFundInfos(fundInfos);
			
		}
		
	}
	
	private static String getTable(String url, int pageNo) {
		
		String htmlStr = pickData(url, pageNo);
		if(!StringUtil.isBlank(htmlStr)) {
			
			JSONObject json = JSONObject.fromObject(htmlStr);
			if(json.containsKey("table") && 
				json.containsKey("count")) {
				
				return (String) json.get("table");
				
			}
			
		}
		
		System.err.println("Empty page of No. 【" + pageNo + "】");
		return null;
		
	}
	
	private static int getPageCnt(String url) {
		
		String htmlStr = pickData(url, 1);
		if(!StringUtil.isBlank(htmlStr)) {
			
			JSONObject json = JSONObject.fromObject(htmlStr);
			if(json.containsKey("table") && 
				json.containsKey("count")) {
				
				return (int) json.get("count");
				
			}
			
		}
		
		return 0;
		
	}
	
	private static String pickData(String url, int page) {
		String rawStr = pickRawData(url, page);
		if(null != rawStr && -1 != rawStr.indexOf(EXTRA_CONTENT)) {
			return rawStr.replace(EXTRA_CONTENT, "");
		} else return null;
	}
	
	private static String pickRawData(String url, int page) {
		
		CloseableHttpClient hc = HttpClients.createDefault();
		
		try {
			
			HttpGet httpget = new HttpGet(url + page);
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
						System.err.println("No enough info of td in tr no. of : 【" + i + "】");
					}
					
				} else {
					System.err.println("Empty in this tr of no. : 【" + i + "】");
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
