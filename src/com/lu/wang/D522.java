package com.lu.wang;

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
public class D522 {
	
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
		int i, j, x, y, shortest, tmp;
		
		int n = in.nextInt();
		int m = in.nextInt();
		
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
			} else {
				parts.put(tmpoo, i);
			}
		}
		
//		for(i=0; i<m*2; i++) {
//			queryList.add(in.nextInt());
//		}
		
		for(i=0; i<m; i++) {
//			x = queryList.get(i)-1;
//			y = queryList.get(i+1)-1;
			x = in.nextInt()-1;
			y = in.nextInt()-1;
			//big and small
			shortest = -1;
			for(j=0; j<valuePos.size(); j++) {
				int posX = valuePos.get(j)[0];
				int posY = valuePos.get(j)[1];
				if(posX > y) {
					break;
				}
				if(posX>=x && posY<=y && (shortest == -1 || shortest>(posY-posX))) {
					shortest = posY-posX;
					if(shortest == 1) {
						break;
					}
				}
			}
			re.append(shortest + "\n");
		}
		System.out.println(re.toString());
	}
	
}
