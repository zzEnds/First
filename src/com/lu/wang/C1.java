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
		
//		Long timea = System.currentTimeMillis();//time for
			
		Scanner in=new Scanner(System.in);
		double[][] num = new double[3][2]; 
		
		//submit
		num[0][0] = in.nextDouble();
		num[0][1] = in.nextDouble();
		num[1][0] = in.nextDouble();
		num[1][1] = in.nextDouble();
		num[2][0] = in.nextDouble();
		num[2][1] = in.nextDouble();	
		
		//test1
//		num[0][0] = 0;
//		num[0][1] = 0;
//		num[1][0] = 1;
//		num[1][1] = 1;
//		num[2][0] = 0;
//		num[2][1] = 1;
		
		//test3
//		num[0][0] = 18.716839;
//		num[0][1] = 40.852752;
//		num[1][0] = 66.147248;
//		num[1][1] = -4.083161;
//		num[2][0] = 111.083161;
//		num[2][1] = 43.347248;
		
		//test6
//		num[0][0] = 17.288379;
//		num[0][1] = 68.223317;
//		num[1][0] = 48.776683;
//		num[1][1] = 71.688379;
//		num[2][0] = 23.170559;
//		num[2][1] = 106.572762;
		
		//test9
//		num[0][0] = 34.236058;
//		num[0][1] = 108.163949;
//		num[1][0] = 28.639345;
//		num[1][1] = 104.566515;
//		num[2][0] = 25.610069;
//		num[2][1] = 86.002927;
		
		String re = df.format(getArea(num));
		System.out.println(re);
		
//		System.out.println(System.currentTimeMillis()-timea);//time for
	}
	
	public static double getArea(double[][] num) {
		
		double A = Math.pow((num[0][0]-num[1][0]),2) + Math.pow((num[0][1]-num[1][1]),2);
		double B = Math.pow((num[1][0]-num[2][0]),2) + Math.pow((num[1][1]-num[2][1]),2);
		double C = Math.pow((num[0][0]-num[2][0]),2) + Math.pow((num[0][1]-num[2][1]),2);
		double a = Math.sqrt(A);
		double b = Math.sqrt(B);
		double c = Math.sqrt(C);
		
		double angelC = Math.acos((A + B - C)/(2*a*b));
		double angelA = Math.acos((C + B - A)/(2*c*b));
		double angelB = Math.PI - angelA - angelC;
		
		double XX;
		double angelX;
		
		if(a>b) {
			if(b>c) {
				angelX = angelC;
				XX = C;
			} else {
				angelX = angelB;
				XX = B;
				angelB = angelC;
			}
		} else {
			if(a>c) {
				angelX = angelC;
				XX = C;
			} else {
				angelX = angelA;
				XX = A;
				angelA = angelC;
			}
		}
		
		//n=?
		double judge1, judge2 = 0;
		int param = 0;
		do{
			param++;
			judge1 = angelA * param / angelX;
			if(!ofint(judge1)) continue;
			judge2 = angelB * param / angelX;
		}while (!ofint(judge1) || !ofint(judge2));
		
//		int n = (int) (param * Math.PI / angelX);
//		double alphaa = angelX / param;
		double part1 = param * Math.PI * XX * 0.125 / angelX;
		double part2 = Math.sin(2 * angelX / param) / Math.pow(Math.sin(angelX), 2);
		double areaS = part1 * part2;
		
		
		return areaS;
	}
	
	public static boolean ofint(double x) {
		int xx = (int)Math.round(x);
		double judge = Math.abs(x-xx);
		if(judge < 0.0001) {
			return true;
		} else return false;
	}

}
