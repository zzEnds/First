package com.lu.wang.solved;

import java.util.Scanner;

/**
 * @author lu.wang 
 * A. Dubstep
 * http://codeforces.com/problemset/problem/208/A
 */
public class A208 {

	public static void main(String[] args) {

		codeforces();

	}

	public static void codeforces() {
		
		Scanner in = new Scanner(System.in);
		
		String n = in.next();
		n = n.replace("WUB", " ");
		
		System.out.println(n.trim());
		
//		System.out.println((" ".equals(n.charAt(0)+""))?n.substring(1):n);
		
	}

}
