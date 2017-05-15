package com.lu.wang.solved;

import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * A. Soldier and Bananas
 * http://codeforces.com/problemset/problem/546/A
 */
public class A546 {
	
	public static void main(String[] args) {
		
		//n - k(1+...+w)   
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		int n = in.nextInt();
		int w = in.nextInt();
		int cnt = 0;
		for(int i=1; i<=w; i++) {
			cnt += i;
		}
		
		int res = (k * cnt) - n;
		if(res > 0) {
			System.out.println(res);
		} else {
			System.out.println(0);
		}
//		calculate();
		
	}
	
}
