package com.lu.wang.bakD522;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * C. Ancient Berland Circus
 *
 */
public class Bak1D522 {
	
	public static void main(String[] args) {
		
		Long timea = System.currentTimeMillis();//time for
		theMain();
		System.out.println(System.currentTimeMillis()-timea);//time for
	}
	
	public static void theMain() {
		
		List<Integer> re = new ArrayList<Integer>();
		Scanner in=new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		List<Integer> aList = new ArrayList<Integer>();
		Map<Integer, List<Integer>> aMap = new HashMap<Integer, List<Integer>>();
		
		for(int i=0; i<n; i++) {
			int tmp = in.nextInt();
			if(!aMap.containsKey(tmp)) {
				List<Integer> tmpList = new ArrayList<Integer>();
				tmpList.add(i);//position is i, be careful
				aMap.put(tmp, tmpList);
			} else if(aMap.containsKey(tmp)) {
				aMap.get(tmp).add(i);//position is i, be careful
			}
			aList.add(tmp);
		}
		
		int[][] queryL = new int[m][2];
		for(int i=0; i<m; i++) {
			in.nextLine();
			queryL[i][0] = in.nextInt();
			queryL[i][1] = in.nextInt();
		}
		
		
		for(int i=0; i<m; i++) {
			Map<Integer, Integer> repeatMap = new HashMap<Integer, Integer>();
			int x = queryL[i][0];
			int y = queryL[i][1];
			int shortest = -1;
			for(int j=x-1; j<y; j++) {
				int aListJ = aList.get(j);
				if(repeatMap.containsKey(aListJ)) {
					continue;
				}
				repeatMap.put(aListJ, j);
				int tmp = shortestOne(aMap.get(aListJ), x-1, y-1);
				if((shortest < 0) || (shortest > 0 && tmp > 0 && tmp < shortest)) {
					shortest = tmp;
				}
				if(shortest == 1) {
					break;
				}
			}
			re.add(shortest);
		}
		
		//test1
//		n = 5;
//		m = 3;
//		int[] aList = {1, 1, 2, 3, 2};
//		int[][] queryL = {{1, 5}, {2, 4}, {3, 5}};
		
		for(int i=0; i<re.size(); i++) {
			System.out.println(re.get(i));
		}
	}
	
	public static int shortestOne(List<Integer> repeatPos, int start, int end) {
		int shortest = -1;
		
		if(repeatPos.size()<1) {
			return shortest;
		}
		
		for(int i=0; i<repeatPos.size()-1; i++) {
			if(repeatPos.get(i) < start || repeatPos.get(i+1) > end) {
				continue;
			}
			int tmp = repeatPos.get(i+1) - repeatPos.get(i);
			if((shortest < 0) || (shortest > 0 && tmp < shortest)) {
				shortest = tmp;
			}
		}
		return shortest;
	}

}
