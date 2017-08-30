package com.lu.wang.solved;

import java.util.Scanner;

/**
 * @author lu.wang 
 * A. Calculating Function
 * http://codeforces.com/problemset/problem/486/A
 */
public class A486 {

	public static void main(String[] args) {

		codeforces();

	}

	public static void codeforces() {
		
		Scanner in = new Scanner(System.in);
		
		long n = in.nextLong();
		
		System.out.println((n%2 == 0)?(n/2):(-1 * ((n + 1) / 2)));
		
	}

}
