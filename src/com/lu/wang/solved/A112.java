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
 * A. Petya and Strings
 * http://codeforces.com/problemset/problem/112/A
 */
public class A112 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b = in.next();
		
		System.out.println(compare(a.toLowerCase(), b.toLowerCase()));
		
	}
	
	public static int compare(String a, String b) {
		int i=0;
		while(i<a.length()) {
			char A = a.charAt(i);
			char B = b.charAt(i);
			if(A != B) {
				if(A > B) {
					return 1;
				} else {
					return -1;
				}
			}
			i++;
		}
		return 0;
	}

}
