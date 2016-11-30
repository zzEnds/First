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
public class Bak7D522 {
	
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
		Map<Integer, Integer> noRepeat = new HashMap<Integer, Integer>();
		int i, j, x, y, repeatNo, shortest, tmp;
		
		int n = in.nextInt();
		int m = in.nextInt();
		
//		for(i=0; i<n; i++) {
//			int tmpoo = in.nextInt();
//			aList.add(tmpoo);
//			if(noRepeat.containsKey(tmpoo)) {
//				noRepeat.put(tmpoo, (noRepeat.get(tmpoo)+1));
//			} else {
//				noRepeat.put(tmpoo, 1);
//			}
//		}
		
//		Map<Integer, List<Integer>> parts = new HashMap<Integer, List<Integer>>();
//		List<Integer> chkPos = new ArrayList<Integer>();
		Map<Integer, Integer> parts = new HashMap<Integer, Integer>();
		int chkPos = 0;
		List<int[]> valuePos = new ArrayList<int[]>();
		for(i=0; i<n; i++) {
			int tmpoo = in.nextInt();
			aList.add(tmpoo);
			if(parts.containsKey(tmpoo)) {
				//if need this pos
				tmp = parts.get(tmpoo);
				if(chkPos <= tmp) {
					int[] arrayX = {tmp, i};
					valuePos.add(arrayX);
					chkPos = tmp;
					parts.put(tmpoo, i);
				}
//				parts.get(tmpoo).add(i);
//				parts.put(tmpoo, parts.get(tmpoo));
//				if(parts.get(tmpoo).get(index)chkPos) {
//					
//				}
			} else {
				parts.put(tmpoo, i);
			}
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
				if(noRepeat.get(repeatNo) == 1) {
					continue;
				}
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
