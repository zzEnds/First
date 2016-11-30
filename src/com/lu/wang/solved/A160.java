package com.lu.wang.solved;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * A. Twins
 * http://codeforces.com/problemset/problem/160/A
 */
public class A160 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		String tmp = in.next();
		int a = Integer.valueOf(tmp);
		tmp = in.nextLine();
		tmp = in.nextLine();
		String[] b = tmp.split(" ");
		int total = 0;
		List<Integer> sorted = new LinkedList<>();
		
		//desc
		for(int i=0; i<a; i++) {
			int currentI = Integer.valueOf(b[i]);
			if(i==0) {
				sorted.add(currentI);
				total += currentI;
			}
			else {
				for(int j=i-1; j>=-1; j--) {
					if(j==-1) {
						sorted.add(0, currentI);
						total += currentI;
						break;
					} else if(currentI <= sorted.get(j)) {
						sorted.add(j+1, currentI);
						total += currentI;
						break;
					}
				}
			}
		}
		
		float half = (float)total / 2;
		int myCoin = 0;
		int cnt = 0;
		for(int i=0; i<a; i++) {
			cnt++;
			myCoin += sorted.get(i);
			if(myCoin > half) {
				break;
			}
		}
		System.out.println(cnt);
		
	}


}
