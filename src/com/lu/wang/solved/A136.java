package com.lu.wang.solved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * A. Presents
 * http://codeforces.com/problemset/problem/136/A
 */
public class A136 {
	
	/**
	 * Little Petya very much likes gifts. Recently he has received a new laptop as a New Year gift from his mother. 
	 * He immediately decided to give it to somebody else as what can be more pleasant than giving somebody gifts. 
	 * And on this occasion he organized a New Year party at his place and invited n his friends there. 
	 * If there's one thing Petya likes more that receiving gifts, that's watching others giving gifts to somebody else. 
	 * Thus, he safely hid the laptop until the next New Year and made up his mind to watch his friends exchanging gifts 
	 * while he does not participate in the process. He numbered all his friends with integers from 1 to n. 
	 * Petya remembered that a friend number i gave a gift to a friend number pi. 
	 * He also remembered that each of his friends received exactly one gift. 
	 * Now Petya wants to know for each friend i the number of a friend who has given him a gift.
	 */
	
	public static List<Integer> line = new ArrayList<Integer>();
	public static Map<Integer, Integer> lineMap = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		
		codeforces();
		
	}
	
	public static void codeforces() {

		
		Scanner in = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		int cnt = in.nextInt();
		
		for(int i=0; i<cnt; i++) {
			int tmp = in.nextInt();
			lineMap.put(tmp, i);
		}
		
		for(int i=0; i<cnt; i++) {
			sb.append(lineMap.get(i+1) + 1 + " ");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
		
	}
	
	
}
