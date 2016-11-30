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
 * A. Helpful Maths
 * http://codeforces.com/problemset/problem/339/A
 */
public class A339 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		String X = in.next();
		
		if(!X.contains("+")) {
			System.out.println(X);
		} else {
			String[] digits = X.split("[+]");
			int x1 = 0, x2 = 0, x3 = 0;
			
			for(String xx : digits) {
				switch (Integer.valueOf(xx).intValue()) {
				case 1:
					x1++;
					break;
				case 2:
					x2++;
					break;
				case 3:
					x3++;
					break;
				}
			}
			
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<x1; i++) sb.append(1).append("+");
			for(int i=0; i<x2; i++) sb.append(2).append("+");
			for(int i=0; i<x3; i++) sb.append(3).append("+");
			System.out.println(sb.toString().substring(0, sb.toString().length()-1));
		}
	}
	
}
