package com.lu.wang;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;

public class Test0817 {

	public static void main(String[] args) {
		String fileName = "Monthly Billing.rar";
		String rootPath = "D:/workspace0808/ForTest/src";
//		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
//		int year = Calendar.getInstance().get(Calendar.YEAR);
//		String yearMonth = "";
//		if(month>9) {
//			yearMonth = year +""+ month;
//		} else {
//			yearMonth = year +"0"+ month;
//		}
//		fileName = fileName.split("\\.")[0] + yearMonth + "." + fileName.split("\\.")[1];
//		System.out.println(fileName);
		File filelist = new File(rootPath);
		String str1 = fileName.split("\\.")[0];
		String str2 = fileName.split("\\.")[1];
		String tmp = "";
		if(filelist.exists()) {
			String[] files = filelist.list();
			for(String oneFile : files) {
				if(oneFile.startsWith(str1) && oneFile.endsWith(str2)) {
					tmp = oneFile;
					break;
				}
			}
		}
	}
}
