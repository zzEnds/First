package com.lu.wang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * 
 * @author lu.wang
 * D. Closest Equals
 *
 */
public class ReadWrite {
	
	public static void main(String[] args) {
		
		File testFile = new File("D:/workspace0808/ForTest/testFile", "552Dtest.txt");
		try {
			testFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		
		//read
		/**
		try {
			FileInputStream out = new FileInputStream(testFile);
			InputStreamReader isreader = new InputStreamReader(out);
			try {
				int ch = 0;
				while((ch = isreader.read()) != -1) {
					System.out.println((char)ch);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 */
	}
	
}
