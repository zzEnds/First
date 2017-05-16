package com.lu.wang.solved;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * A. Translation
 * http://codeforces.com/problemset/problem/41/A
 */
public class A41 {
	
	/**
	 * The translation from the Berland language into the Birland language is not an easy task. 
	 * Those languages are very similar: 
	 * a berlandish word differs from a birlandish word with the same meaning a little: 
	 * it is spelled (and pronounced) reversely. 
	 * 
	 * For example, a Berlandish word code corresponds to a Birlandish word edoc. 
	 * However, it's easy to make a mistake during the «translation». 
	 * Vasya translated word s from Berlandish into Birlandish as t. 
	 * Help him: find out if he translated the word correctly.
	 */
	
	public static List<Integer> sortLine = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		a41();
		
	}
	
	public static void a41() {

		
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b = in.next();
		
		if(ifReverse(a, b)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
	}
	
	public static boolean ifReverse(String a, String b) {
		
		if(a.length() == b.length()) {
			
			for(int i=0; i<a.length(); i++) {
//				if(a.substring(i, i+1).equals(b.substring(b.length() - i))) {
				if(!(a.charAt(i) + "").equals(b.charAt(b.length() - i - 1) + "")) {
					return false;
				}
			}
			return true;
		}
		
		return false;
		
	}

	
}
