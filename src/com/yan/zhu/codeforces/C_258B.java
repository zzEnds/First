package com.yan.zhu.codeforces;

import java.util.Scanner;

public class C_258B {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int m = scanner.nextInt();
		
		int[] countsum = findCountAll(m);
		
		long count = 0;
		
		for (int i = 0; i < countsum.length; i++) {
			
			int[] result = findResult(countsum, i);
			
			for (int j = 0; j < result.length; j++) {
				
				if (countsum[i] > result[j]) {
					
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
	
	/**
	 * 数组六个数之和的所有情况
	 * （不能重复）
	 */
	static int[] findResult(int[] countsum, int stmp) {
		
		int[] count = {};
		
		int sum = 0;
		
		// 1
		for (int i = stmp + 1; i < countsum.length; i++) {
			
			sum = countsum[i];
			
			// 2
			for (int i2 = i + 1; i2 < countsum.length; i2++) {
				
				sum = sum + countsum[i2];
				
				// 3
				for (int i3 = i2 + 1; i3 < countsum.length; i3++) {
					
					sum = sum + countsum[i3];
					
					// 4
					for (int i4 = i3 + 1; i4 < countsum.length; i4++) {
						
						sum = sum + countsum[i4];
						
						// 5
						for (int i5 = i4 + 1; i5 < countsum.length; i5++) {
							
							sum = sum + countsum[i5];
							
							// 6
							for (int i6 = i5 + 1; i6 < countsum.length; i6++) {
								
								sum = sum + countsum[i6];
								
								count[i-(stmp + 1)] = sum;
							}
						}
					}
				}
			}
		}
		
		return count;
	}
	
	/**
	 * 所有4与7合计
	 */
	static int[] findCountAll(int m) {
		
		int[] countsum = {};
		
		StringBuffer s;
		
		for (int i = 7; i <= m; i++) {
			
			s = new StringBuffer(String.valueOf(i));
			
			countsum[i-7] = findCount47(s);
		}
		
		return countsum;
	}

	/**
	 * 寻找4与7个数
	 * s为输入数字的String类型
	 */
	static int findCount47(StringBuffer s) {
		
		int count = 0;
		
		for (int i = 0; i < s.length(); i++) {
			
			if ("4".equals(s.charAt(i)) || "7".equals(s.charAt(i))) {
				
				count++;
			}
		}
		
		return count;
	}
}
