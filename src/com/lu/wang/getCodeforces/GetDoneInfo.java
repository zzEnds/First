package com.lu.wang.getCodeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class GetDoneInfo {
	
	final static String ACCEPTS = "acceptIds";
	final static String CODEFORCE = "http://codeforces.com/";
	final static int MAX_PAGE = 1;
	final static String DIV_CLASS = "datatable";
	final static String TAG_TR = "tr";
	final static String TAG_TD = "td";
	final static String TAG_A = "a";
	
	public static void main(String[] args) {
		
		List<String> ids = getAcceptIds();
		getCntOfPeople(ids);
		
	}
	
	public static List<String> getAcceptIds() {
		String fileName = ACCEPTS;
		List<String> idList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String line;
			while((line = br.readLine()) != null) {
				idList.add(line.trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			return idList;
		}
	}
	
	public static void getCntOfPeople(List<String> ids) {
		
		for (int pageNo = 1; pageNo <= MAX_PAGE; pageNo++) {
			
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
						if(!isEmpty(trs.get(i)) && 
								!isEmpty(trs.get(i).getElementsByTag(TAG_TD)) && 
								!isEmpty(trs.get(i).getElementsByTag(TAG_TD).get(0).getElementsByTag(TAG_A))) {
							String title = trs.get(i).getElementsByTag(TAG_TD).get(0).getElementsByTag(TAG_A).get(0).data();
							System.out.println(title);
						}
					}
//					doc.getClass();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static boolean isEmpty(Object ob) {
		if(ob.getClass().isInstance(List.class)) {
			List x = (List)ob;
			if(null == x || 0 >= x.size()) {
				return true;
			}
		}
		return false;
	}
	
	public static void getWebInfo(String urlStr, String elementName) {
		
	}
}
