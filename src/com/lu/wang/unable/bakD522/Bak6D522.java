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
public class Bak6D522 {
	
	public static void main(String[] args) {
		
//		Long timea = System.currentTimeMillis();//time for
		theMain();
//		System.out.println(System.currentTimeMillis()-timea);//time for
	}
	
	public static void theMain() {
		
		StringBuffer re = new StringBuffer("");
		Scanner in=new Scanner(System.in);
		List<Integer> aList = new ArrayList<Integer>();
		List<Integer> queryList = new ArrayList<Integer>();
		int i, j, x, y, repeatNo, shortest, tmp;
		
		int n = in.nextInt();
		int m = in.nextInt();
		for(i=0; i<n; i++) {
			aList.add(in.nextInt());
		}
		for(i=0; i<m*2; i++) {
			queryList.add(in.nextInt());
		}
		
		
		for(i=0; i<m*2; i=i+2) {
			x = queryList.get(i);
			y = queryList.get(i+1);

			Map<Integer, Integer> repeatMap = new HashMap<Integer, Integer>();
			shortest = -1;
			for(j=x-1; j<y; j++) {
				
				repeatNo = aList.get(j);
				if(!repeatMap.containsKey(repeatNo)) {
					repeatMap.put(repeatNo, j);
				} else {
					tmp = j - repeatMap.get(repeatNo);
					if(tmp == 1) { shortest = 1;break; }
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
