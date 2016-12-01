package com.lu.wang.solved;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author lu.wang
 * A. Beautiful Matrix
 * http://codeforces.com/problemset/problem/263/A
 */
public class A263 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int tmp = in.nextInt();
				if(1 == tmp) {
					cnt += Math.abs(i-2);
					cnt += Math.abs(j-2);
				}
			}
		}
		System.out.println(cnt);
		
	}
	
}
