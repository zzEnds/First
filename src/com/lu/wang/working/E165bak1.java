package com.lu.wang.working;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author lu.wang
 * E. Compatible Numbers
 * http://codeforces.com/problemset/problem/165/E
 */
public class E165bak1 {
	
	public static List<Integer> nums = new ArrayList<>();
	public static Map<Integer, Set<Integer>> numsMap = new HashMap<>();
//	public static Map<Integer, Integer> _pos_0 = new HashMap<>();
	public static Map<Integer, Set<Integer>> _pos_i_0 = new HashMap<>();
	
	public static void main(String[] args) {
//		System.out.println(Integer.toBinaryString(4000000));
		calculate();
		
	}
	
//	public static int quickCal(Integer x, Integer y) {
//		x = 0xFFFFFFFF & x;
//		y = 0xFFFFFFFF & y;
//		System.out.println(Integer.toBinaryString(x|y));
//		System.out.println(Integer.toBinaryString(x&y));
//		System.out.println(Integer.toBinaryString((x|y)^((~x)|y)));
//		return ((x|y) & (x & y));
//	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		int cnt = in.nextInt();
//		List<String> binaryStr = new ArrayList<>();
//		List<Integer> couples = new ArrayList<>();
		
		for (int i = 0; i < cnt; i++) {
			nums.add(in.nextInt());
		}
		
		for (int i = 0; i < cnt; i++) {
			
			Set<Integer> _1_pos = new HashSet<>();
			String str = reverse(Integer.toBinaryString(nums.get(i)));
			int currentIndex = str.indexOf("1");
			
			for (int j = 0; j < 22; j++) {
				
			}
			
			while(-1 != currentIndex) {
				_1_pos.add(currentIndex);
				if(currentIndex+1 >= str.length()) {
					break;
				}
				currentIndex = str.indexOf("1", currentIndex+1);
			}
			numsMap.put(i, _1_pos);
		}
		
		for (int i = 0; i < cnt; i++) {
			boolean notHas = true;
//			if(numsCoupleMap.containsKey(numsMap.get(i))) {
//				for (Integer num : numsCoupleMap.get(numsMap.get(i))) {
//					if(0 == (numsMap.get(num) & numsMap.get(i))) {
//						System.out.print(numsMap.get(num));
//						notHas = false;
//					}
//				}
//			}
			if(notHas) {
				System.out.println(-1);
			}
			System.out.print(" ");
		}
		
//		for (int i = 0; i < cnt; i++) {
//			if(nums.contains(couples.get(i))) {
//				System.out.print(couples.get(i));
//			} else {
//				System.out.print(-1);
//			}
//			System.out.println(" ");
//		}
		
	}
	
	public static String reverse(String s){
		return new StringBuffer(s).reverse().toString();
	}
	
	public static boolean hasOne(int i) {
		
		
		return false;
	}
	
}
