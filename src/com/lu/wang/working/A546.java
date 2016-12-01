package com.lu.wang.working;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author lu.wang
 * A. Soldier and Bananas
 * http://codeforces.com/problemset/problem/546/A
 */
public class A546 {
	
	public static void main(String[] args) {
		calculate();
	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		int con_1 = in.nextInt();
		int con_2 = in.nextInt();
		int con_3 = in.nextInt();
		int con_4 = in.nextInt();
		int cnt = in.nextInt();
		
		Set<Integer> set = new HashSet<>();
		
		for (int i = 1; cnt >= (con_1*i); i++) {
			set.add(i*con_1);
		}
		for (int i = 1; cnt >= con_2*i; i++) {
			set.add(i*con_2);
		}
		for (int i = 1; cnt >= con_3*i; i++) {
			set.add(i*con_3);
		}
		for (int i = 1; cnt >= con_4*i; i++) {
			set.add(i*con_4);
		}
		
		System.out.println(set.size());
		
	}
	
}
