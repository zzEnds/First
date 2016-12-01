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
 * A. George and Accommodation
 * http://codeforces.com/problemset/problem/467/A
 */
public class A467 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		int cntRoom = in.nextInt();
		int cnt = 0;
		for (int i = 0; i < cntRoom; i++) {
			int a = in.nextInt(), b = in.nextInt();
			if(Math.abs(a-b) >= 2) {
				cnt++;
			}
		}
		System.out.println(cnt);
		
	}
	
}
