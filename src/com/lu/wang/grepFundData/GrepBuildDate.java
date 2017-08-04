package com.lu.wang.grepFundData;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sound.midi.SysexMessage;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.lu.wang.grepFundData.bean.FundInfo;
import com.lu.wang.grepFundData.utils.Constants;
import com.lu.wang.grepFundData.utils.PropertiesUtil;

public class GrepBuildDate {

	//30<= x <40
	static Integer[] middle2 = {219, 661, 779, 656, 326, 416, 251, 644, 274, 637
		, 549, 262, 321, 265, 562, 509, 264, 239, 240, 491, 457, 582, 441, 587
		, 322, 667, 552, 502, 547, 266, 560, 289, 407, 310, 257, 243, 755, 330
		, 361, 260, 418, 295, 474, 419, 508, 402, 680, 234, 294, 302, 414, 404
		, 405, 170, 313, 258, 303, 270, 365, 499, 546, 403, 384, 370, 311, 298
		, 293, 358, 314, 267, 283, 682, 352, 312};
	
	//20<= x <30
	static Integer[] middle = {697, 442, 772, 592, 567, 456, 583, 458, 494, 589
		, 745, 590, 426, 689, 556, 447, 492, 417, 580, 671, 574, 434, 605, 430
		, 529, 629, 483, 489, 490, 713, 443, 497, 464, 537, 381, 748, 641, 470
		, 465, 735, 488, 576, 389, 341, 639, 300, 287, 342, 453, 563, 383, 452
		, 356, 284, 709, 299, 633, 451, 498, 428, 292, 291, 233, 327, 378, 297
		, 795, 666, 276, 360};
	
	//20> x
	static Integer[] top = {705, 722, 746, 768, 781, 782, 786, 792, 794, 763, 757, 
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
	
	public static Set<Integer> topSet = new HashSet<Integer>();
	public static void readTopSet() {
		
		for(Integer i : top) {
			topSet.add(i);
		}
		
	}
	
	/**
	 * 循环抓取主列表信息
	 * @throws UnsupportedEncodingException 
	 */
	public static void getFundList() throws UnsupportedEncodingException {
		
		String baseUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
				Constants.TTFund.OPEN_STOCK_URL);
		
		//获取页码
		int pageCnt = getPageCnt(baseUrl + 1);
		
		for(int pageNo = 1; pageNo<=pageCnt; pageNo++) {
			//每一页的FUND
			
			String baseHtml = getTable(baseUrl + pageNo);
			
			List<FundInfo> fundInfos = analyzeHTMLByString(baseHtml, pageNo);
			
			for(FundInfo fundInfo : fundInfos) {
				
				if(topSet.contains(fundInfo.getId())) {
					
					String moreInfoUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
							Constants.TTFund.OPEN_STOCK_MORE_URL) + fundInfo.getFundId() 
							+ ".html";
					if(checkBuildDate(moreInfoUrl)) {
						
						System.err.println(fundInfo.getId() + " \t " + fundInfo.getFundId());
						
					} else {
						System.out.println(fundInfo.getId());
					}
				}
				//get到当前页FundInfo
//				System.out.println("Fund Name: " + fundInfo.getId() + " . " + fundInfo.getFundName());
				//270010&page=1&per=20
				/*String dailyRate1stPageUrl = PropertiesUtil.getProperty(Constants.PROPER_FILE, 
						Constants.TTFund.OPEN_STOCK_EVERY_URL) + fundInfo.getFundId() 
						+ "&page=" + 1 + "&per=50"; */
				
				
//				CalculateDrawdown.drawdown(dailys);
			}
			
//			outputFundInfos(fundInfos);
			
		}
		
	}
	
	private static boolean checkBuildDate(String url) throws UnsupportedEncodingException {
		
		boolean oldEnough = false;
		
		String moreInfoHtml = GrepIds.pickData(url);
		String newCoding = new String(moreInfoHtml.getBytes("ISO-8859-1"), "UTF-8");
		//infoOfFund
		Document doc = Jsoup.parse(newCoding);
		Elements divs =  doc.getElementsByClass("infoOfFund");
		
		try {
			
			String dateStr = divs.get(0).getElementsByTag(Constants.htmlTag.TABLE).get(0)
					.getElementsByTag(Constants.htmlTag.TBODY).get(0)
					.getElementsByTag(Constants.htmlTag.TR).get(1)
					.getElementsByTag(Constants.htmlTag.TD).get(0).text().replace("：", "").replace("成 立 日", "");//成 立 日
			
			Integer compareTo = Integer.valueOf(dateStr.replace("-", ""));
			if(compareTo.intValue() < 20151201) {
				oldEnough = true;
				System.out.println(dateStr);
			}
			
		} catch (Exception e) {
			System.err.println(url);
		}
		
		return oldEnough;
		
	}
	
	private static int getPageCnt(String url) {
		
		String htmlStr = GrepIds.pickData(url);
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
	
	private static String getTable(String url) {
		
		String htmlStr = GrepIds.pickData(url);
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
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		readTopSet();
		getFundList();
	}

}
