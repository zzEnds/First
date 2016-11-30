package com.lu.wang.solved;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * C. Average Score
 * http://codeforces.com/problemset/problem/81/C
 */
public class bak2TiimeC81 {
	
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		calculate();
		System.out.println(System.currentTimeMillis()-start);
	}
	
	public static void calculate() {
		
		Scanner in = new Scanner(System.in);
		List<Integer> sortedmarkList = new LinkedList<>();
		
		int courseCnt = 10000;//in.nextInt();//总课程数
		int a = 5781;//in.nextInt();//a科目数
		int b = 4219;//in.nextInt();//b科目数
		String first = "1";
		String second = "2";
//		in.nextLine();
//		String x = in.nextLine();
		if(a == b) {
			for(int i=0; i<a; i++) {
				System.out.print("1 ");
			}
			for(int i=0; i<b; i++) {
				System.out.print("2 ");
			}
		} else {
//			String[] xx = x.split(" ");
			List<Integer> pos = new LinkedList<>();
			for(int i=0; i<courseCnt; i++) {
				if(i%100 == 0){System.out.println(i);}
//				int mark = in.nextInt();
//				int mark = Integer.valueOf(xx[i]);
				Random r = new Random(); int mark = r.nextInt(5)+1;
				if(0 == i) {
					sortedmarkList.add(mark);pos.add(i);
				} else {
					int lastSize = sortedmarkList.size();
					for(int j=0; j<lastSize; j++) {
						if(a > b && (i < a || mark < sortedmarkList.get(a-1))) {
							//ooOO
							if(mark < sortedmarkList.get(j)) {
								sortedmarkList.add(j, mark);pos.add(j, i);
								break;
							} else if((j+1)<=a && (j+1) == sortedmarkList.size()) {
								sortedmarkList.add(j+1, mark);pos.add(j+1, i);
							}
						} else if(a < b && (i < a || mark > sortedmarkList.get(a-1))) {
							//OOoo
							if(mark > sortedmarkList.get(j)) {
								sortedmarkList.add(j, mark);pos.add(j, i);
								break;
							} else if((j+1)<=a && (j+1) == sortedmarkList.size()) {
								sortedmarkList.add(j+1, mark);pos.add(j+1, i);
							}
						} else {
							break;
						}
					}
				}
				while(pos.size()>a) {
					pos.remove(a);
					sortedmarkList.remove(a);
				}
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
