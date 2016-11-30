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
 * A. HQ9+
 * http://codeforces.com/problemset/problem/133/A
 */
public class A133 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		String a = in.next();
		
		System.out.println(deal(a));
		
	}
	
	public static String deal(String a) {
		if(a.contains("H") || a.contains("Q") || a.contains("9"))
			return "YES";
		else
			return "NO";
	}

}
