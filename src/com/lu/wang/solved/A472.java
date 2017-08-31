package com.lu.wang.solved;

import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * A. Design Tutorial: Learn from Math
 * http://codeforces.com/problemset/problem/472/A
 */
public class A472 {
	
	
	public static void main(String[] args) {
		
		codeforces();
		
	}
	
	public static void codeforces() {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		if(0 == (n % 2)) {
			
			System.out.println("4 " + (n - 4));
			
		} else {
			
			System.out.println("9 " + (n - 9));
		}
		
	}
	

}
