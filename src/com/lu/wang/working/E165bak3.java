package com.lu.wang.working;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * @author lu.wang
 * E. Compatible Numbers
 * http://codeforces.com/problemset/problem/165/E
 */
public class E165bak3 {
	
//	public static List<Integer> nums = new ArrayList<>();
	public static List<String> nums = new ArrayList<>();
	public static Map<Integer, Integer> numsMap1 = new HashMap<>();
//	public static List<Set<Integer>> _pos_i_1 = new ArrayList<>();
	public static Set<Integer>[] _pos_i_1 = new Set[22];
	
	public static void main(String[] args) {
		
		calculate();
		
	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		int cnt = in.nextInt();
//		Map<Integer, Set<Integer>> numsMap0 = new TreeMap<>(new Comparator<Integer>() {
//
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				// TODO Auto-generated method stub
//				return o1.compareTo(o2);
//			}
//		});
//		List<String> binaryStr = new ArrayList<>();
//		List<Integer> couples = new ArrayList<>();
		
		for (int i = 0; i < cnt; i++) {
			nums.add(reverse(Integer.toBinaryString(in.nextInt())));
		}
		
		for (int i = 0; i < cnt; i++) {
//			String str = reverse(Integer.toBinaryString(nums.get(i)));
//			int cnt1 = str.replace("0", "").length();
//			numsMap1.put(i, cnt1);
//			int cnt0 = str.replace("1", "").length()+(22-str.length());
//			Set<Integer> set = new HashSet<>();
//			if(numsMap0.containsKey(cnt0)) {
//				set = numsMap0.get(cnt0);
//			}
//			set.add(i);
//			numsMap0.put(cnt0, set);
			
			String str = nums.get(i);
			int currentPos = str.indexOf("1");
//			Set<Integer> setPos = new HashSet<>();
			while(-1 != currentPos) {
//				setPos.add(currentPos);
				if(currentPos+1 > str.length()) {
					break;
				}
				Set<Integer> setPos = new HashSet<>();
				if(_pos_i_1[currentPos] != null) {
					setPos = _pos_i_1[currentPos];
				}
				setPos.add(i);
				_pos_i_1[currentPos] = setPos;
				currentPos = str.indexOf("1", currentPos+1);
			}
		}
		
		for (int i = 0; i < cnt; i++) {
			boolean notHas = true;
			//需要排除相同的数字
//			int currentNum = nums.get(i);
			
			for (int j = 0; j < cnt; j++) {
				
			}
			if(notHas) {
				System.out.println(-1);
			}
			System.out.print(" ");
		}
		
	}
	
	public static String reverse(String s){
//		if(s.length() < 22) {
//			String x0 = 22 - s.length();
//			s = s + x0;
//		}
		return new StringBuffer(s).reverse().toString();
	}
	
	
}
