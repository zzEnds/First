package com.lu.wang.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author lu.wang
 * D. Closest Equals
 *
 */
public class ReadWrite {
	
	public static void main(String[] args) {

		Long timea = System.currentTimeMillis();//time for
//		if(n > 10000) {
//			System.out.println(System.currentTimeMillis()-timea);//time for
//		}
		
		File testFile = new File("D:/workspace0808/ForTest/testFile", "552Dtest.txt");
		try {
			testFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		//write
		try {
			FileOutputStream in = new FileOutputStream(testFile);
			OutputStreamWriter osWriter = new OutputStreamWriter(in);
			try {
				osWriter.write("500000 500000\n");
				StringBuffer no2 = new StringBuffer("1");
				for(int i=0; i<500000-1; i++) {
					no2.append(" " + (i+1));
				}
				no2.append("\n");
				osWriter.write(no2.toString());
				for(int i=0; i<500000-1; i++) {
					osWriter.write((i+1) + " " + (i+2) + "\n");
//					osWriter.write(i + " " + i+1 + "\n");
				}
				osWriter.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		*/
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();//3037--21584
		List<String> listOne = new ArrayList<String>();//5698--
//		/**
		//read
		try {
			FileInputStream out = new FileInputStream(testFile);
			InputStreamReader isreader = new InputStreamReader(out);
			try {
				int ch = 0;
				while((ch = isreader.read()) != -1) {
					listOne.add(String.valueOf(ch));
				}
				for(int i=0; i<listOne.size()-1; i++) {
					System.out.println(listOne.get(i));
				}
				
//				int ch = 0;
//				while((ch = isreader.read()) != -1) {
//					list1.add(String.valueOf(ch));
//					if((ch = isreader.read()) != -1) {
//						list2.add(String.valueOf(ch));
//					}
//				}
//				for(int i=0; i<list1.size()-1; i++) {
//					System.out.println(list1.get(i) + list2.get(i));
//				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		 */
		
		System.out.println(System.currentTimeMillis()-timea);//time for
	}
	
}
