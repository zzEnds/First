package com.lu.wang.solved;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * C. Fruits
 * http://codeforces.com/problemset/problem/12/C
 */
public class C12 {
	
	public static void main(String[] args) {
		
		calculate();
		
	}
	
	public static void calculate() {
		
		int maxTotal = 0;
		int minTotal = 0;
		Map<String, Integer> fruitMap = new HashMap<String, Integer>(); 
		Scanner in = new Scanner(System.in);
		int cntTag = in.nextInt();
		int cntFruit = in.nextInt();
		List<Integer> tagList = new LinkedList<Integer>();
		List<Integer> fruitCntList = new LinkedList<Integer>();
		for(int i=0; i<cntTag; i++) {
			int tag = in.nextInt();
			if(i == 0) {
				tagList.add(tag);
			} else {
				int lastSize = tagList.size();
				for(int j=lastSize-1; j>=0; j--) {
					if(tag <= tagList.get(j)) {
						tagList.add(j+1, tag);break;
					}
				}
				if(tagList.size() == lastSize) {
					tagList.add(0, tag);
				}
			}
		}
		for(int i=0; i<cntFruit; i++) {
			String fruit = in.next();
			if(fruitMap.containsKey(fruit)) {
				fruitMap.put(fruit, fruitMap.get(fruit) + 1);
			} else {
				fruitMap.put(fruit, 1);
			}
		}
		
		Iterator<Map.Entry<String, Integer>> it = fruitMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			if(null == fruitCntList || 0 >= fruitCntList.size()) {
				fruitCntList.add(entry.getValue());
			} else {
				int lastSize = fruitCntList.size();
				for(int i=fruitCntList.size()-1; i>=0; i--) {
					if(entry.getValue() <= fruitCntList.get(i)) {
						fruitCntList.add(i+1, entry.getValue());break;
					}
				}
				if(lastSize == fruitCntList.size()) {
					fruitCntList.add(0, entry.getValue());
				}
			}
		}
		int tmpSize = tagList.size();
		for(int i=0; i<fruitCntList.size(); i++) {
			maxTotal += tagList.get(i) * fruitCntList.get(i);
			minTotal += tagList.get(tmpSize - 1 - i) * fruitCntList.get(i);
		}
		System.out.println(minTotal + " " + maxTotal);
	}

}
