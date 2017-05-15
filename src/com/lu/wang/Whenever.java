package com.lu.wang;

import java.math.BigDecimal;

public class Whenever {

	public static void main(String[] args) {
//		int a = Calendar.getInstance().get(Calendar.YEAR);
//		System.out.println(a);//得到年
		
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
//		 System.out.println(sdf.format(new Date()));
//		 System.out.println(sdf2.format(new Date()));
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		 System.out.println(sdf.format(Long.parseLong("1480505600000")));
		
//		String x = "[{\\\"accountId\\\":300000610,\\\"accountName\\\":\\\"CMS测试账户1\\\",\\\"createDate\\\":1478620800000,\\\"limitAmount\\\":0,\\\"mainBalance\\\":427505.4,\\\"rewardBalance\\\":0},{\\\"accountId\\\":300000611,\\\"accountName\\\":\\\"CMS测试账户2\\\",\\\"createDate\\\":1478620800000,\\\"limitAmount\\\":0,\\\"mainBalance\\\":48747.1,\\\"rewardBalance\\\":0},{\\\"accountId\\\":300000612,\\\"accountName\\\":\\\"CMS测试账户3\\\",\\\"createDate\\\":1478620800000,\\\"limitAmount\\\":0,\\\"mainBalance\\\":549434.45,\\\"rewardBalance\\\":0},{\\\"accountId\\\":300000673,\\\"accountName\\\":\\\"新建账户支付\\\",\\\"createDate\\\":1481212800000,\\\"limitAmount\\\":0,\\\"mainBalance\\\":0,\\\"rewardBalance\\\":0}]";
//		System.out.println(x);
//		x = x.replace("\\\"", "\"");
//		System.out.println();
//		System.out.println(x);
//		BigDecimal a0 = new BigDecimal(0.3);//
//		BigDecimal a1 = new BigDecimal(-0.3);
//		BigDecimal a2 = new BigDecimal(0.00);
		
//		System.out.println(a0.abs().toString());
//		System.out.println(a0.compareTo(BigDecimal.ONE));;
//		System.out.println(new java.text.DecimalFormat("#.00").format(a0));
//		System.out.println(new java.text.DecimalFormat("#.00").format(a1));
//		System.out.println(new java.text.DecimalFormat("#.00").format(a2));
//		System.out.println(a2.equals(BigDecimal.ZERO));
		
		theFormat(new BigDecimal(0));
		theFormat(new BigDecimal(0.3));
		theFormat(new BigDecimal(-0.3));
		theFormat(new BigDecimal(0.13));
		theFormat(new BigDecimal(-0.31));
		theFormat(new BigDecimal(1.3));
		theFormat(new BigDecimal(-1.3));
		theFormat(new BigDecimal(21.3));
		theFormat(new BigDecimal(-21.3));
		theFormat(new BigDecimal(21.0));
		theFormat(new BigDecimal(-21.30));
		theFormat(new BigDecimal(1.0));
		theFormat(new BigDecimal(-100));
		
		
		
	}
	
	public static String theFormat(BigDecimal a0) {
		
		String mark = "";
		if(-1 == a0.compareTo(BigDecimal.ZERO)) {
			a0 = a0.negate();//*-1
			mark = "-";
		}
		if(-1 == a0.compareTo(BigDecimal.ONE)) {
			mark = mark + "0";
		}
		System.out.println(mark + (new java.text.DecimalFormat("#.00").format(a0)));
		return mark + (new java.text.DecimalFormat("#.00").format(a0));
		
	}
	
}
