package com.lu.wang.solved;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author lu.wang
 * C. Average Score
 * http://codeforces.com/problemset/problem/81/C
 */
public class C81 {
	
	public static void main(String[] args) {
//		Long start = System.currentTimeMillis();
		calculate();
//		System.out.println(System.currentTimeMillis()-start);
	}
	
	public static void calculate() {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		TreeSet<Integer> list_1 = new TreeSet<>(); 
		TreeSet<Integer> list_2 = new TreeSet<>();
		TreeSet<Integer> list_3 = new TreeSet<>();
		TreeSet<Integer> list_4 = new TreeSet<>();
		TreeSet<Integer> list_5 = new TreeSet<>();
		Set<Integer> listAll = new HashSet<>();
		
		int courseCnt = in.nextInt();//总课程数
		int a = in.nextInt();//a科目数
		int b = in.nextInt();//b科目数
		in.nextLine();
		String x = in.nextLine();
		if(a == b) {
			for(int i=0; i<a; i++) {
				System.out.print("1 ");
			}
			for(int i=0; i<b; i++) {
				System.out.print("2 ");
			}
		} else {
			String[] xx = x.split(" ");
			for(int i=0; i<courseCnt; i++) {
				int mark = Integer.valueOf(xx[i]);
				switch (mark) {
				case 1:
					list_1.add(i);
					break;
				case 2:
					list_2.add(i);
					break;
				case 3:
					list_3.add(i);
					break;
				case 4:
					list_4.add(i);
					break;
				case 5:
					list_5.add(i);
					break;
				}
				if((a<b && list_5.size()>=a) || (a>b && list_1.size()>=a)) {
					break;
				}
			}
			if(a>b) {
				int tmpA = a;
				if(tmpA < list_1.size()) {
					listAll.addAll(minusSet(list_1, tmpA));
				} else {
					
					listAll.addAll(list_1);
					tmpA -= list_1.size();
					
					if(tmpA > 0) {
						
						if(tmpA < list_2.size()) {
							listAll.addAll(minusSet(list_2, tmpA));
						} else {
							
							listAll.addAll(list_2);
							tmpA -= list_2.size();
							
							if(tmpA > 0) {
								
								if(tmpA < list_3.size()) {
									listAll.addAll(minusSet(list_3, tmpA));
								} else {
									
									listAll.addAll(list_3);
									tmpA -= list_3.size();
									
									if(tmpA > 0) {
										
										if(tmpA < list_4.size()) {
											listAll.addAll(minusSet(list_4, tmpA));
										} else {
											
											listAll.addAll(list_4);
											tmpA -= list_4.size();
											
											if(tmpA > 0) {
												
												if(tmpA < list_5.size()) {
													listAll.addAll(minusSet(list_5, tmpA));
												} else {
													listAll.addAll(list_5);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
			} else {

				int tmpA = a;
				if(tmpA < list_5.size()) {
					listAll.addAll(minusSet(list_5, tmpA));
				} else {
					
					listAll.addAll(list_5);
					tmpA -= list_5.size();
					
					if(tmpA > 0) {
						
						if(tmpA < list_4.size()) {
							listAll.addAll(minusSet(list_4, tmpA));
						} else {
							
							listAll.addAll(list_4);
							tmpA -= list_4.size();
							
							if(tmpA > 0) {
								
								if(tmpA < list_3.size()) {
									listAll.addAll(minusSet(list_3, tmpA));
								} else {
									
									listAll.addAll(list_3);
									tmpA -= list_3.size();
									
									if(tmpA > 0) {
										
										if(tmpA < list_2.size()) {
											listAll.addAll(minusSet(list_2, tmpA));
										} else {
											
											listAll.addAll(list_2);
											tmpA -= list_2.size();
											
											if(tmpA > 0) {
												
												if(tmpA < list_1.size()) {
													listAll.addAll(minusSet(list_1, tmpA));
												} else {
													listAll.addAll(list_1);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			
			}
			
			for(int i=0; i<courseCnt; i++) {
				
				if(listAll.contains(i)) {
					System.out.print(1 + " ");
				} else {
					System.out.print(2 + " ");
				}
			}
		}
		
	}
	
	public static TreeSet<Integer> minusSet(TreeSet<Integer> set, int x) {
		while(set.size() > x) {
			set.remove(set.last());
		}
		return set;
	}
	
}
