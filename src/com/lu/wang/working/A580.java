package com.lu.wang.working;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * A. Kefa and First Steps
 * http://codeforces.com/problemset/problem/580/A
 */
public class A580 {
	
	/**
	 * Kefa decided to make some money doing business on the Internet for exactly n days. 
	 * He knows that on the i-th day (1 ≤ i ≤ n) he makes ai money. Kefa loves progress, 
	 * that's why he wants to know the length of the maximum non-decreasing subsegment in sequence ai. 
	 * Let us remind you that the subsegment of the sequence is its continuous fragment. 
	 * A subsegment of numbers is called non-decreasing if all numbers in it follow in the non-decreasing order.
	 */
	
	public static List<Integer> line = new ArrayList<Integer>();
	public static Map<Integer, Integer> lineMap = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		
		codeforces();
		
	}
	
	public static void codeforces() {

		
		Scanner in = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		int cnt = in.nextInt();
		
		for(int i=0; i<cnt; i++) {
			int tmp = in.nextInt();
			lineMap.put(tmp, i);
		}
		
		for(int i=0; i<cnt; i++) {
			sb.append(lineMap.get(i+1) + 1 + " ");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
		
	}
	
	
}
