package com.yan.zhu.codeforces;

import java.util.Scanner;

public class C_158A {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();	// 数组长度
		
		int k = in.nextInt();	// 比对数值
		
		int[] a = new int[n];
		
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			
			a[i] = in.nextInt();
		}
		
		for (int i = 0; i < n; i++) {
			
			if (a[i] >= a[k] && a[i] != 0) {
				
				count++;
			} else {
				
				break;
			}
		}
		
		System.out.println(count);
	}

}
