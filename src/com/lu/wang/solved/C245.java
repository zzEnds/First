package com.lu.wang.solved;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * C. Game with Coins
 * http://codeforces.com/problemset/problem/245/C
 */
public class C245 {
	
	public static int cntChest;
	public static List<Integer> coinL = new ArrayList<>();
	public static List<Integer> posList = new ArrayList<>();
	public static int times = 0;
	
	public static void main(String[] args) {
		
		calculate();
		
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		cntChest = in.nextInt();//1 ≤ n ≤ 100
		int Xmax = 0;
		for(int i=0; i<cntChest; i++) {
			coinL.add(in.nextInt());
		}
		if(cntChest <= 2 || 0 == (cntChest % 2)) {
			System.out.println(-1);
		} else {
			if(cntChest % 2 ==0) {
				Xmax = (cntChest - 2)/2;
			} else {
				Xmax = (cntChest - 1)/2;
			}
			if(Xmax > 1) {
				theCalculater(Xmax);
			} else {
				times += biggest(coinL.get(0), coinL.get(1), coinL.get(2));
			}
			System.out.println(times);
		}
	}
	
	public static void theCalculater(int Xmax) {
		for(int i=Xmax; i>0; i--){
			
			if(i>(Xmax/2)) {
				int takeCoin = Math.max(coinL.get(2*i-1), coinL.get(2*i));
				if(takeCoin > 0) {
					coinL.set(i-1, coinL.get(i-1) - takeCoin);
					times += takeCoin;
					if(0 != coinL.get(i-1)) {
						posList.add(i);
					}
				}
			}
		}
		if(Xmax/2 > 1) theCalculater(Xmax/2);
		else times += biggest(coinL.get(0), coinL.get(1), coinL.get(2));
	}
	
	public static int biggest(int a, int b, int c) {
		return Math.max(Math.max(a, b), c);
	}

}
