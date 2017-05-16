package com.lu.wang.solved;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * A. Puzzles
 * http://codeforces.com/problemset/problem/337/A
 */
public class A337 {
	
	/**
	 * The end of the school year is near and Ms. Manana, the teacher, 
	 * will soon have to say goodbye to a yet another class. 
	 * She decided to prepare a goodbye present for her n students 
	 * and give each of them a jigsaw puzzle 
	 * (which, as wikipedia states, is a tiling puzzle that requires the assembly of numerous small, 
	 * often oddly shaped, interlocking and tessellating pieces). 
	 * The shop assistant told the teacher that there are m puzzles in the shop, 
	 * but they might differ in difficulty and size. Specifically, 
	 * the first jigsaw puzzle consists of f1 pieces, the second one consists of f2 pieces and so on. 
	 * Ms. Manana doesn't want to upset the children, 
	 * so she decided that the difference between the numbers of pieces in her presents 
	 * must be as small as possible. Let A be the number of pieces in the largest puzzle 
	 * that the teacher buys and B be the number of pieces in the smallest such puzzle. 
	 * She wants to choose such n puzzles that A - B is minimum possible. 
	 * Help the teacher and find the least possible value of A - B.
	 */
	
	public static List<Integer> sortLine = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		a337();
		
	}
	
	public static void a337() {

		
		Scanner in = new Scanner(System.in);
		int cntPeople = in.nextInt();
		int cntPuzzles = in.nextInt();
		
		for(int i=0; i<cntPuzzles; i++) {
			//from bigger ones to ...
			int input = in.nextInt();
			if(0 == i) {
				sortLine.add(input);
			} else {

				for(int j=0; j<i; j++) {
					if(input > sortLine.get(j)) {
						sortLine.add(j, input);
						break;
					} else if((j+1) == i) {
						sortLine.add(input);
					}
				}
//				asString(sortLine);
			}
		}
		
		int res = -1;
		//situation: cntPuzzles - cntPeople + 1
		int situation = cntPuzzles - cntPeople + 1;
		for(int i=0; i<situation; i++) {
			if((i + cntPeople) <= sortLine.size()) {
				int tmp = sortLine.get(i) - sortLine.get(i + cntPeople - 1);
				res = (res >= 0 && res < tmp)? res : tmp;
			} else {
				break;
			}
		}
		
		System.out.println(res);
		
	}
	
//	public static void asString(List<Integer> list) {
//		
//		System.out.println("==============Start================");
//		for(int i=0; i<list.size(); i++) {
//			System.out.println("No." + i + " -- " + list.get(i));
//		}
//		System.out.println("===============End===============");
//		
//	}
	
}
