package com.lu.wang.working;

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
 * G. Max and Min
 * http://codeforces.com/contest/566/problem/G
 */
public class G566 {
	
	public static void main(String[] args) {
		
		calculate();
		
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		List<Integer> pairsMaxX = new ArrayList<>();
		List<Integer> pairsMaxY = new ArrayList<>();
		List<Integer> pairsMinX = new ArrayList<>();
		List<Integer> pairsMinY = new ArrayList<>();
		int kittenMax = in.nextInt();
		int kittenMin = in.nextInt();
		int baseX = in.nextInt();
		int baseY = in.nextInt();
		int baseXminuxY = baseX - baseY;
		int tmpX;
		int tmpY;
		int maximPlus = 0;
		int minimPlus = 0;
		for(int i=0; i<kittenMax; i++) {
			tmpX = in.nextInt() + baseXminuxY;//实际能力下降
			tmpY = in.nextInt();
			pairsMaxX.add(tmpX);
			pairsMaxY.add(tmpY);
			//最大上涨幅度和
			if(maximPlus < (tmpX + tmpY)) {
				maximPlus = tmpX + tmpY;
			}
		}
		for(int i=0; i<kittenMin; i++) {
			tmpX = in.nextInt();
			tmpY = in.nextInt() + baseXminuxY;//实际能力下降
			pairsMinX.add(tmpX);
			pairsMinY.add(tmpY);
			//最大下降幅度和
			if(minimPlus < (tmpX + tmpY)) {
				minimPlus = tmpX + tmpY;
			}
		}
		
		//查找Min最大减幅下的couple
		
		if(maximPlus >= (minimPlus-1)) {
			System.out.println("max");
		} else {
			System.out.println("min");
		}
		System.out.println("minimPlus: "+ minimPlus + "  maximPlus: "+ maximPlus);
		System.out.println();
	}

}
