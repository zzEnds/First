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
public class E165bak4 {
	
	public static List<String> nums = new ArrayList<>();
	public static List<Integer> numbers = new ArrayList<>();
	public static Map<Integer, Integer> coupleMap = new HashMap<>();
	public static Set<Integer>[] _pos_i_1 = new Set[22];
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		int cnt = in.nextInt();
		
		int tmpNum;
//		String tmp = in.nextLine();
//		String[] tmpArray = tmp.split(" ");
		for (int i = 0; i < cnt; i++) {
			tmpNum = in.nextInt();
			numbers.add(tmpNum);
			nums.add(reverse(Integer.toBinaryString(tmpNum)));
		}
		
		for (int i = 0; i < cnt; i++) {
			
			String str = nums.get(i);
			int currentPos = str.indexOf("1");
			while(-1 != currentPos) {
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
			if(coupleMap.containsKey(i)) {
				System.out.print(coupleMap.get(i));
			} else {
				Set<Integer> baseSet = new HashSet<>();
				for (int j = 0; j < cnt; j++) {
					baseSet.add(j);
				}
				
				boolean notHas = true;
				String str = nums.get(i);
				int currentPos = str.indexOf("1");
				Set<Integer> setTogether = new HashSet<>();
				while(-1 != currentPos) {
					setTogether.addAll(_pos_i_1[currentPos]);
					currentPos = str.indexOf("1", currentPos+1);
				}
				baseSet.removeAll(setTogether);
				if(null != baseSet && 0 < baseSet.size()) {
					int getta = numbers.get(baseSet.iterator().next());
					coupleMap.put(baseSet.iterator().next(), numbers.get(i));
					System.out.print(getta);
					notHas = false;
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
