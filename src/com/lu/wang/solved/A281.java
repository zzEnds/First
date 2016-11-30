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
 * A. Word Capitalization
 * http://codeforces.com/problemset/problem/281/A
 */
public class A281 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		String tmp = in.next();
		String tail = tmp.substring(1);
		String first = tmp.charAt(0) + "";
		System.out.println(first.toUpperCase() + tail);
		
	}


}
