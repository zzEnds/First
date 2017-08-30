package com.lu.wang.solved;

import java.util.Scanner;

/**
 * @author lu.wang 
 * A. Arrival of the General
 * http://codeforces.com/problemset/problem/144/A
 */
public class A144 {

	public static void main(String[] args) {

		codeforces();

	}

	public static void codeforces() {
		
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		int min = 101;
		int max = 0;
		int minPos = 0;
		int maxPos = 0;
		
		for(int i=1; i<=n; i++) {
			int tmp = in.nextInt();
			
			if(tmp <= min) {
				min = tmp;
				minPos = i;
			}
			
			if(tmp > max) {
				max = tmp;
				maxPos = i;
			}
			
		}
		
		if(minPos < maxPos) {
			System.out.println((maxPos - 1) + (n - minPos) - 1);
		} else {
			System.out.println((maxPos - 1) + (n - minPos));
		}
	}

}
