package com.lu.wang.getCodeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class GetDoneInfo {
	
	final static String ACCEPTS = "acceptIds";
	final static String CODEFORCE = "http://codeforces.com/";
	final static int MAX_PAGE = 32;//
	final static String DIV_CLASS = "datatable";
	final static String ENTER = "\n";
	final static String TABLE = "\t";
	final static String SPACE = " ";
	final static String TAG_TR = "tr";
	final static String TAG_TD = "td";
	final static String TAG_A = "a";
	
	public static void main(String[] args) {
		
		Set<String> ids = getAcceptIds();
		getCntOfPeople(ids);
		
	}
	
	public static Set<String> getAcceptIds() {
		String fileName = GetDoneInfo.class.getResource("").toString() + ACCEPTS;
		Set<String> idList = new HashSet<>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName.replace("file:/", ""))));
			String line;
			while((line = br.readLine()) != null) {
				idList.add(line.split(TABLE)[3].split(SPACE)[0].trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			return idList;
		}
	}
	
	public static void getCntOfPeople(Set<String> ids) {
		
		for (int pageNo = 1; pageNo <= MAX_PAGE; pageNo++) {
//			System.out.println("pageNo: "+pageNo);
			String urlStr = CODEFORCE + "problemset/page/"+ pageNo +"?order=BY_SOLVED_DESC";
			try {
				URL url = new URL(urlStr);
				HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
				StringBuffer wholePage = new StringBuffer();
				String line;
				while((line = br.readLine()) != null) {
					wholePage.append(line);
//					System.out.println(line);//全文打印
				}
				
				if(null != wholePage) {
					Document doc = Jsoup.parse(wholePage.toString());
					Element div = doc.getElementsByClass(DIV_CLASS).get(0);
					List<Element> trs = div.getElementsByTag(TAG_TR);
					for (int i = 0; i < trs.size(); i++) {
						try {
							if(null != trs.get(i) && !isEmpty(trs.get(i).getElementsByTag(TAG_TD)) && !isEmpty(trs.get(i).getElementsByTag(TAG_TD).get(0).getElementsByTag(TAG_A))) {
								String title = trs.get(i).getElementsByTag(TAG_TD).get(0).getElementsByTag(TAG_A).get(0).ownText();
								if(ids.contains(title.trim())) {
									String submits = trs.get(i).getElementsByTag(TAG_TD).last().getElementsByTag(TAG_A).get(0).ownText().replace("x", "");//&nbsp;x
									System.out.println(title + " : " + submits);
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
//					doc.getClass();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static boolean isEmpty(List ob) {
		if(ob.getClass().isInstance(List.class)) {
			if(null == ob || 0 >= ob.size()) {
				return true;
			}
		}
		return false;
	}
	
	public static void getWebInfo(String urlStr, String elementName) {
		
	}
}
