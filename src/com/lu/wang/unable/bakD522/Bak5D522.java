package com.lu.wang.unable.bakD522;


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
public class Bak5D522 {
	
	public static void main(String[] args) {
		
//		Long timea = System.currentTimeMillis();//time for
		theMain();
//		System.out.println(System.currentTimeMillis()-timea);//time for
	}
	
	public static void theMain() {
		StringBuffer re = new StringBuffer("");
		Scanner in=new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
//		Map<Integer, Integer> aMap = new HashMap<Integer, Integer>();
//		for(int i=0; i<n; i++) {
//			aMap.put(i, in.nextInt());
//		}
		
		List<Integer> aList = new ArrayList<Integer>();
		for(int i=0; i<n; i++) {
			aList.add(in.nextInt());
		}
		
		
//		int[][] queryL = new int[m][2];
//		for(int i=0; i<m; i++) {
//			queryL[i][0] = in.nextInt();
//			queryL[i][1] = in.nextInt();
//		}
		
		List<Integer> queryList = new ArrayList<Integer>();
		for(int i=0; i<m*2; i++) {
			queryList.add(in.nextInt());
		}
		
//		Map<Integer, Integer> queryM = new HashMap<Integer, Integer>();
//		for(int i=0; i<m*2; i++) {
//			queryM.put(i, in.nextInt());
//		}
		
		int x, y;
		for(int i=0; i<m*2; i=i+2) {
			x = queryList.get(i);
			y = queryList.get(i+1);

			Map<Integer, Integer> repeatMap = new HashMap<Integer, Integer>();
			int shortest = -1;
			for(int j=x-1; j<y; j++) {
				
				if(shortest == 1) { break; }
				
				int repeatNo = aList.get(j);
				if(!repeatMap.containsKey(repeatNo)) {
					repeatMap.put(repeatNo, j);
				} else {
					int tmp = j - repeatMap.get(repeatNo);
					repeatMap.put(repeatNo, j);
					if(shortest > tmp || shortest < 0) {
						shortest = tmp;
					}
				}
			}
			re.append(shortest + "\n");
		
		}
		System.out.println(re.toString());
	}
	
}
