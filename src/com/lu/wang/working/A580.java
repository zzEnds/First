package com.lu.wang.working;

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
	
	
	public static void main(String[] args) {
		
		codeforces();
		
	}
	
	public static void codeforces() {


		
		Scanner in = new Scanner(System.in);
		int cnt = in.nextInt();
		
		int res = 1;
		int lastOne = 0;
		int len = 0;
		
		for(int i=0; i<cnt; i++) {
			int tmp = in.nextInt();
			
			if(lastOne <= tmp) {
				len++;
				
			} else {
				if(res < len) {
					res = len;
					len = 1;
				}
			}
			
			lastOne = tmp;
		}
		
		if(res < len) {
			res = len;
		}
		
		System.out.println(res);
		
	
	}
	
//	public static void codeforces() {
//
//		
//		Scanner in = new Scanner(System.in);
//		int cnt = in.nextInt();
//		
//		int res = 1;
//		int lastOne = 0;
//		int len = 0;
//		
//		for(int i=0; i<cnt; i++) {
//			int tmp = in.nextInt();
//			
//			if(lastOne <= tmp) {
//				len++;
//				
//			} else {
//				if(res < len) {
//					res = len;
//					len = 1;
//				}
//			}
//			
//			lastOne = tmp;
//		}
//		
//		if(res < len) {
//			res = len;
//		}
//		
//		System.out.println(res);
//		
//	}
	
	
}
