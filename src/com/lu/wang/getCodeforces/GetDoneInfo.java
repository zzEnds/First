package com.lu.wang.getCodeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class GetDoneInfo {
	
	final static String CODEFORCE = "http://codeforces.com/";
	final static int MAX_PAGE = 1;
	final static String DIV_CLASS = "datatable";
	final static String TAG_TR = "tr";
	final static String TAG_TD = "td";
	
	public static void main(String[] args) {
		
		getCntOfPeople();
		
	}
	
	public static void getCntOfPeople() {
		
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
//					while() {
//						
//					}
//					doc.getClass();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void getWebInfo(String urlStr, String elementName) {
		
	}
}
