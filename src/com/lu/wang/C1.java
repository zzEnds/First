package com.lu.wang;

import java.text.DecimalFormat;
import java.util.Scanner;

public class C1 {
	
	public static void main(String[] args) {
		
		Scanner in=new Scanner(System.in);
		float[][] num = new float[3][2]; 
		
		num[0][0] = in.nextFloat();
		num[0][1] = in.nextFloat();
		num[1][0] = in.nextFloat();
		num[1][1] = in.nextFloat();
		num[2][0] = in.nextFloat();
		num[2][1] = in.nextFloat();
		
//		num[0][0] = 0;
//		num[0][1] = 1;
//		num[1][0] = 0;
//		num[1][1] = 0;
//		num[2][0] = 1;
//		num[2][1] = 0;
		
//		getArea(num);
		DecimalFormat df = new DecimalFormat("0.000000");
		String re = df.format(getArea(num));
		System.out.println(re);
	}
	
	public static double getArea(float[][] num) {
		
		double[] tmp = getDistance(num);
		double X = tmp[0]; 
//		double X = getShortest(tmp);
		double angel = getAngel(tmp);
		double part1 = Math.PI / angel;
		double part2 = X * X / 4;
		double part3 = 1 / Math.tan(angel);
		return part1 * part2 * part3;
	}
	
	public static double[] getDistance(float[][] num) {
		
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
	
	public static double getAngel(double[] num) {
		//(b^2 + c^2 - X^2)/2bc
		double arccos = (Math.pow(num[1],2) + Math.pow(num[2],2) - Math.pow(num[0],2))/(2*num[1]*num[2]);

//		double angel = Math.acos(arccos) * 180 / Math.PI;
		double angel = Math.acos(arccos);
		return angel;
	}
	

}
