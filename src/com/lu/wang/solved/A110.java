package com.lu.wang.solved;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * A. Nearly Lucky Number
 * http://codeforces.com/problemset/problem/110/A
 */
public class A110 {
	
	/**
	 * Petya loves lucky numbers. 
	 * We all know that lucky numbers are the positive integers 
	 * whose decimal representations contain only the lucky digits 4 and 7. 
	 * For example, numbers 47, 744, 4 are lucky and 5, 17, 467 are not.
	 * 
	 * Unfortunately, not all numbers are lucky. 
	 * Petya calls a number nearly lucky if the number of lucky digits in it is a lucky number. 
	 * He wonders whether number n is a nearly lucky number.
	 
	 */
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String str = in.next();
		
		int luckyCnt = 0;
		for(int i=0; i<str.length(); i++) {
			if("4".equals(str.charAt(i)+"") || "7".equals(str.charAt(i)+"")) {
				luckyCnt++;
			}
		}
		if(isLuckNo(luckyCnt)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
	}
	
	public static boolean isLuckNo(int x) {
		
		String xStr = x + "";
		xStr = xStr.replace("4", "").replace("7", "");
		if("".equals(xStr)) {
			return true;
		}
		
		return false;
		
	}
	

}
