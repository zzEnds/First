package com.lu.wang.solved;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author lu.wang 
 * A. I Wanna Be the Guy
 * http://codeforces.com/problemset/problem/469/A
 */
public class A469 {

	public static void main(String[] args) {

		codeforces();

	}

	public static void codeforces() {
		
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		Set<Integer> set = new HashSet<Integer>();
		while(n > 0) {
			set.add(n--);
		}
		
		
		int xCnt = in.nextInt();
		while(xCnt-- > 0) {
			int tmp = in.nextInt();
			set.remove(tmp);
		}
		
		int yCnt = in.nextInt();
		while(yCnt-- > 0) {
			int tmp = in.nextInt();
			set.remove(tmp);
		}
		
		String success = "I become the guy.";
		String failed = "Oh, my keyboard!";
		
		System.out.println((set.size() > 0)?failed:success);
	}

}
