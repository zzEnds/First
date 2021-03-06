package com.lu.wang.working;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author lu.wang
 * E. Compatible Numbers
 * http://codeforces.com/problemset/problem/165/E
 */
public class E165 {
	
	public static List<String> nums = new ArrayList<>();
	public static Map<Integer, String> coupleMap = new HashMap<>();
	public static Set<Integer>[] _pos_i_1 = new Set[22];
	
	public static void main(String[] args) {
		
		calculate();
		
	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		int cnt = 1000000;//in.nextInt();
//		in.nextLine();
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < 1000000; i++) {
			sb.append(r.nextInt(4000000) + " ");
		}
		String tmp = sb.toString();//in.nextLine();
		String[] tmpArray = tmp.split(" ");
		
		for (int i = 0; i < cnt; i++) {
			System.out.println("deal one : "+ i);
			nums.add(reverse(Integer.toBinaryString(Integer.valueOf(tmpArray[i]))));
			String str = nums.get(i);
			int currentPos = str.indexOf("1");
			while(-1 != currentPos) {
				if(currentPos+1 > str.length()) {
					break;
				}
				Set<Integer> setPos = new TreeSet<>();
				if(_pos_i_1[currentPos] != null) {
					setPos = _pos_i_1[currentPos];
				}
				setPos.add(i);
				_pos_i_1[currentPos] = setPos;
				currentPos = str.indexOf("1", currentPos+1);
			}
		}
		
		for (int i = 0; i < cnt; i++) {
			System.out.println("deal two : "+ i);
			if(coupleMap.containsKey(i)) {
				System.out.print(coupleMap.get(i));
			} else {
				
				boolean notHas = true;
				String str = nums.get(i);
//				int currentPos = str.indexOf("1");
//				Set<Integer> setTogether = new TreeSet<>();
//				while(-1 != currentPos) {
//					setTogether.addAll(_pos_i_1[currentPos]);
//					currentPos = str.indexOf("1", currentPos+1);
//				}
				
				for (int j = 0; j < cnt; j++) {
					
					int currentPos = str.indexOf("1");
					Set<Integer> setTogether = new TreeSet<>();
					while(-1 != currentPos) {
						setTogether.addAll(_pos_i_1[currentPos]);
						currentPos = str.indexOf("1", currentPos+1);
					}
					
					String getta = tmpArray[j];
					coupleMap.put(j, tmpArray[i]);
					System.out.print(getta);
//					notHas = false;
				}
				if(notHas) {
					System.out.print(-1);
				}
			}
			System.out.print(" ");
		}
		
	}
	
	public static String reverse(String s){
		return new StringBuffer(s).reverse().toString();
	}
	
}
