package com.yan.zhu.codeforces;

import java.util.Scanner;

public class A71 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

//		while (true) {

			String a = sc.next();

			System.out.print(bReturn(a));
//		}
	}

	static String bReturn(String a) {

		String b = null;

		if (a.length() > 10) {

			b = a.substring(0, 1) + a.substring(1, a.length() - 1).length()
					+ a.substring(a.length() - 1, a.length());

		} else {

			b = a;
		}
		return b;
	}
}
