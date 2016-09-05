package com.lu.wang;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * C. Ancient Berland Circus
 *
 */
public class C1 {
	
	public static DecimalFormat df = new DecimalFormat("0.000000");
	public static void main(String[] args) {
		
		Scanner in=new Scanner(System.in);
		double[][] num = new double[3][2]; 
		
//		num[0][0] = in.nextFloat();
//		num[0][1] = in.nextFloat();
//		num[1][0] = in.nextFloat();
//		num[1][1] = in.nextFloat();
//		num[2][0] = in.nextFloat();
//		num[2][1] = in.nextFloat();
		
		num[0][0] = 17.288379;
		num[0][1] = 68.223317;
		num[1][0] = 48.776683;
		num[1][1] = 71.688379;
		num[2][0] = 23.170559;
		num[2][1] = 106.572762;
		
//		getArea(num);
		String re = df.format(getArea(num));
		System.out.println(re);
	}
	
	public static double getArea(double[][] num) {
		
		double[] tmp = getDistance(num);
		double X = tmp[0]; 
//		double X = getShortest(tmp);
		double angelX = getAngel(tmp, 0);
		double angel1 = getAngel(tmp, 1);
		double angel2 = getAngel(tmp, 2);
		
		//n=?
		double propotion1 = angelX / angel1;
		double propotion2 = angelX / angel2;
		
		double part1 = Math.PI / angelX;
		double part2 = X * X / 4;
		double part3 = 1 / Math.tan(angelX);
		
		return part1 * part2 * part3;
	}
	
	public static double[] getDistance(double[][] num) {
		
		double[] tmp = new double[3];
		
		double a = Math.pow((num[0][0]-num[1][0]),2) + Math.pow((num[0][1]-num[1][1]),2);
		double b = Math.pow((num[1][0]-num[2][0]),2) + Math.pow((num[1][1]-num[2][1]),2);
		double c = Math.pow((num[0][0]-num[2][0]),2) + Math.pow((num[0][1]-num[2][1]),2);
		a = Math.sqrt(a);
		b = Math.sqrt(b);
		c = Math.sqrt(c);
		
		if(a>b) {
			if(b>c) {
				tmp[0] = c;
				tmp[1] = a;
				tmp[2] = b;
			} else {
				tmp[0] = b;
				tmp[1] = a;
				tmp[2] = c;
			}
		} else {
			if(a>c) {
				tmp[0] = c;
				tmp[1] = a;
				tmp[2] = b;
			} else {
				tmp[0] = a;
				tmp[1] = b;
				tmp[2] = c;
			}
		}
		
		return tmp;
	}
	
//	public static double getShortest(double[] num) {
//		if(num[0]>num[1]) {
//			if(num[1]>num[2]) {
//				return num[2];
//			} else {
//				return num[1];
//			}
//		} else {
//			if(num[0]>num[2]) {
//				return num[2];
//			} else {
//				return num[0];
//			}
//		}
//	}
	
	public static double getAngel(double[] num, int i) {
		//(b^2 + c^2 - X^2)/2bc
		double arccos = 0;
		if(i == 0) {
			arccos = (Math.pow(num[1],2) + Math.pow(num[2],2) - Math.pow(num[0],2))/(2*num[1]*num[2]);
		} else if(i == 1) {
			arccos = (Math.pow(num[0],2) + Math.pow(num[2],2) - Math.pow(num[1],2))/(2*num[0]*num[2]);
		} else {
			arccos = (Math.pow(num[1],2) + Math.pow(num[0],2) - Math.pow(num[2],2))/(2*num[1]*num[0]);
		}
		
//		double angel = Math.acos(arccos) * 180 / Math.PI;
		double angel = Math.acos(arccos);
		return angel;
	}
	

}
