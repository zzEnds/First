package com.lu.wang.grepFundData.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	/**
	 * @Title: getProperty 
	 * @Description: 单个属性读取
	 * @param @param propertyFile
	 * @param @param key
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getProperty(String propertyFile, String key) {

		InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertyFile);
		
		Properties p = new Properties();
		try {
			if (is != null) {
				p.load(is);
				return p.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	/**
	 * @Title: getProperties 
	 * @Description: 全属性获取
	 * @param @param propertyFile
	 * @return Properties    返回类型 
	 * @throws
	 */
	public static Properties getProperties(String propertyFile) {

		InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertyFile);
		Properties p = new Properties();
		try {
			if (is != null) {
				p.load(is);
				return p;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	
	}

}
