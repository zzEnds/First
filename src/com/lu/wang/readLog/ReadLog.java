package com.lu.wang.readLog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class ReadLog {
	
	public static final String KEY_PHONE = "16620867297";
	public static final String KEY_PHONE_STR1 = "\"phoneNo\":\"016620867297\"";
	public static final String KEY_PHONE_STR2 = "\"phone\":\"016620867297\"";
	
	public static final String HOST_PASSCODE = "426497508";
	public static final String MARK_A = "{";
	public static final String MARK_B = "}";
	public static final String MARK_C = "[";
	public static final String MARK_D = "]";
	public static final String MARK_E = "(";
	public static final String MARK_F = ")";
	public static final String FOLDER_FOR_WRITE = "E:/Temp/";
	public static String fileName = FOLDER_FOR_WRITE + System.currentTimeMillis();
	public static String charSet = "UTF-8";
	public static String NEXT_LINE = "\r\n";
	
	public static Set<String> functionSet = new HashSet<>();
	public static String keyStr;
	
	public static void alarm(String line) {
//		System.out.println("==============================="+line);
	}
	
	public static void writeByLine(String line) {
		
//		FileWriter fw;
		FileOutputStream fos;
		OutputStreamWriter osw;
		BufferedWriter bw;
		
		try {
			
//			fw = new FileWriter(new File(fileName));
			fos = new FileOutputStream(fileName, true);
			osw = new OutputStreamWriter(fos, charSet);
			bw = new BufferedWriter(osw);
			
			try {
//				osw.append(line);
//				osw.append("\r\n");
//				bw.newLine();
				bw.write(line + NEXT_LINE);
//				bw.write(NEXT_LINE);
//				bw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
//				fos.close();
//				osw.close();
				bw.close();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void grepLine(String line) {
		
		String head = "";
		int point_D = line.indexOf(MARK_D);
		if(point_D != -1) {
			head = line.substring(0, point_D+1);
		}
		
		int point1 = line.indexOf(keyStr);
		if(point1 == -1) {
			alarm(point1+line);return;
		}
		else if(line.lastIndexOf(keyStr) != point1) {
			writeByLine(line);
			System.out.println("more");
		}
		
		String functionHead = "";
		int point_F = line.indexOf(MARK_F);
		if(point_D != -1 && point_F != -1) {
			functionHead = line.substring(point_D+2, point_F+1);
		}
		functionSet.add(functionHead);
		
		String subA = line.substring(0, point1);
		String subB = line.substring(point1);
//		System.out.println("subA="+subA);
//		System.out.println("subB="+subB);
		
		int markPoint_A = subA.lastIndexOf(MARK_A);
		if(markPoint_A == -1) {alarm(markPoint_A+line);return;}
		int markPoint_B = subB.indexOf(MARK_B);
		if(markPoint_B == -1) {alarm(markPoint_B+line);return;}
		
		StringBuffer sb = new StringBuffer(line);
		String curStr = sb.subSequence(markPoint_A, subA.length()+markPoint_B+1).toString();
		
//		System.out.println("point1="+point1 + "; pointA="+markPoint_A + "; pointB="+markPoint_B);
//		writeByLine(head + curStr);
//		System.out.println(curStr);
		
	}
	
	public static void readByLine(String fileName) {
		
		try {
			FileReader fr = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(fr);
			
			/*int cnt = 20;
			while(cnt-- > 0) {*/
			int cnt = 1;
			while(cnt++>0) {
				String nextLine = br.readLine();
				if(nextLine == null) {
					break;
				}
				grepLine(nextLine);
//				System.out.println(nextLine);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void printSet() {
		for(String one : functionSet) {
			System.out.println(one);
		}
	}
	
	public static void main(String[] args) {

		keyStr = KEY_PHONE_STR2;//处理关键字
		String file = "E:/2018-meet/MS-web---online--config/log/27/log1.2018";
		readByLine(file);
//		printSet();//打印所属方法
	}

}
