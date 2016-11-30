package com.lu.wang.working;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author lu.wang
 * A. Lucky Division
 * http://codeforces.com/problemset/problem/122/A
 */
public class A122 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		//
		Set<Integer> dividedBy = new HashSet<>();
		String _0 = "0";
		String _1 = "1";
		String _4 = "4";
		String _7 = "7";
		for(int i=0; i<=7; i++) {
			String currentStr = Integer.toBinaryString(i);
			String currentStr1 = currentStr.replace(_0, _4);
			currentStr1 = currentStr1.replace(_1, _7);
			String currentStr2 = currentStr.replace(_0, _7);
			currentStr2 = currentStr2.replace(_1, _4);
			dividedBy.add(Integer.valueOf(currentStr1));
			dividedBy.add(Integer.valueOf(currentStr2));
		}
		
		Scanner in = new Scanner(System.in);
		String tmp = in.next();
		String result = "NO";
		if(canDivided(dividedBy, Integer.valueOf(tmp))) {
			result = "YES";
		} else if("".equals(tmp.replace("4", "").replace("7", ""))) {
			result = "YES";
		}
		System.out.println(result);
		
	}
	
	public static boolean canDivided(Set<Integer> dividedBy, int number) {
		Iterator it = dividedBy.iterator();
		
		while(it.hasNext()) {
			int tmp = (int)it.next();
			if( (number >= tmp) && (0 == number % tmp)) {
				return true;
			}
		}
		return false;
	}


}
