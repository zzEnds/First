package com.lu.wang;

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
public class Bak3D522 {
	
	public static void main(String[] args) {
		
		Long timea = System.currentTimeMillis();//time for

		
		Scanner in=new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		List<Integer> aList = new ArrayList<Integer>();
		
		for(int i=0; i<n; i++) {
			aList.add(in.nextInt());
		}
		
		for(int i=0; i<m; i++) {
			in.nextLine();
			int x = in.nextInt();
			int y = in.nextInt();

			Map<Integer, Integer> repeatMap = new HashMap<Integer, Integer>();
			int shortest = -1;
			for(int j=x-1; j<y; j++) {
				int aListJ = aList.get(j);
				if(!repeatMap.containsKey(aListJ)) {
					repeatMap.put(aListJ, j);
				} else {
					int tmp = repeatMap.get(aListJ);
					repeatMap.put(aListJ, j);
					if(shortest > (j - tmp) || shortest < 0) {
						shortest = j - tmp;
					}
				}
				if(shortest == 1) { break; }
			}
			System.out.println(shortest);
		}
		
		System.out.println(System.currentTimeMillis()-timea);//time for
	}
	
}
