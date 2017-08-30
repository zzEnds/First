package com.lu.wang.solved;

import java.util.Scanner;

/**
 * @author lu.wang 
 * A. Game With Sticks
 * http://codeforces.com/problemset/problem/451/A
 */
public class A451 {

	public static void main(String[] args) {

		codeforces();

	}

	public static void codeforces() {
		
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		String even = "Malvika";
		String odd = "Akshat";
		
		System.out.println((((n<m?n:m)%2)==0)?even:odd);
		
//		System.out.println((" ".equals(n.charAt(0)+""))?n.substring(1):n);
		
	}

}
