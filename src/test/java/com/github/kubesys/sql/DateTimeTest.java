/*

 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.sql;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.2.0
 * @since   2020/4/23
 *
 */
public class DateTimeTest {
	
	
	public static void main(String[] args) throws Exception {
		String time = "2021-03-02T14:29:17Z";
		System.out.println(time.replaceAll("T", " "));
	}
}
