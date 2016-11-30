package com.lu.wang.unable.bakD522;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * D. Closest Equals
 *
 */
public class Bak2D522 {
	
	public static void main(String[] args) {
		
//		Long timea = System.currentTimeMillis();//time for
		theMain();
//		System.out.println(System.currentTimeMillis()-timea);//time for
	}
	
	public static void theMain() {
		
		Scanner in=new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		List<Integer> aList = new ArrayList<Integer>();
		
		for(int i=0; i<n; i++) {
			int tmp = in.nextInt();
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
				if(shortest == 1) {
					break;
				}
				int aListJ = aList.get(j);
				if(!repeatMap.containsKey(aListJ)) {
					repeatMap.put(aListJ, j);
				} else {
					int tmp = repeatMap.get(aListJ);
					repeatMap.put(aListJ, j);
					if(shortest > (repeatMap.get(aListJ) - tmp) || shortest < 0) {
						shortest = repeatMap.get(aListJ) - tmp;
					}
				}
			}
			System.out.println(shortest);
		}
		
	}
	
}
