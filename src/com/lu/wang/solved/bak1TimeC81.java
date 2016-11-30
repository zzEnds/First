package com.lu.wang.solved;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author lu.wang
 * C. Average Score
 * http://codeforces.com/problemset/problem/81/C
 */
public class bak1TimeC81 {
	
	public static void main(String[] args) {
		
		calculate();
		
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		List<int[]> sortedmarkList = new LinkedList<int[]>();
		
		int courseCnt = in.nextInt();//总课程数
		int a = in.nextInt();//a科目数
		int b = in.nextInt();//b科目数
		String first = "1";
		String second = "2";
		if(a == b) {
			in.next();
			for(int i=0; i<a; i++) {
				System.out.print("1 ");
			}
			for(int i=0; i<b; i++) {
				System.out.print("2 ");
			}
		} else {
			
			for(int i=0; i<courseCnt; i++) {
				int mark = in.nextInt();
				int[] tmp = new int[2];
				tmp[0] = mark;
				tmp[1] = i;
				if(0 == i) {
					sortedmarkList.add(tmp);
				} else {
					int lastSize = sortedmarkList.size();
					for(int j=lastSize-1; j>=0; j--) {
						if(a > b) {
							//ooOO
							if(mark >= sortedmarkList.get(j)[0]) {
								sortedmarkList.add(j+1, tmp);break;
							}
						} else {
							//OOoo
							if(mark <= sortedmarkList.get(j)[0]) {
								sortedmarkList.add(j+1, tmp);break;
							}
						}
						
					}
					if(lastSize == sortedmarkList.size()) {
						sortedmarkList.add(0, tmp);
					}
				}
				
			}
			
			int mino = a;//>b?a:b;
			Set<Integer> pos = new HashSet<>();
			for(int i=0; i<mino; i++) {
				pos.add(sortedmarkList.get(i)[1]);
			}
			for(int i=0; i<courseCnt; i++) {
				if(pos.contains(i)) {
					System.out.print(first + " ");
				} else {
					System.out.print(second + " ");
				}
			}
		}
		
	}

}
