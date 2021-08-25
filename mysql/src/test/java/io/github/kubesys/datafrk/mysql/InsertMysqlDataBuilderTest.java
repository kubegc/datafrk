/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

import io.github.kubesys.datafrk.mysql.operators.InsertMysqlDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class InsertMysqlDataBuilderTest {

	static String JSON = "{\r\n"
			+ "	\"apiVersion\": \"v1\",\r\n"
			+ "	\"kind\": \"Self\",\r\n"
			+ "	\"metadata\": {\r\n"
			+ "		\"name\": \"henry\",\r\n"
			+ "		\"namespace\": \"default\",\r\n"
			+ "		\"labels\": {\r\n"
			+ "			\"a\": \"a\",\r\n"
			+ "			\"b\": \"b\"\r\n"
			+ "		}\r\n"
			+ "	}\r\n"
			+ "}";
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(new InsertMysqlDataBuilder()
				.insertTo("test")
				.beginValue("test")
				.andValue("default")
				.andValue("doslab.io")
				.andValue("2021-08-22 01:21:39.33095")
				.andValue("2021-08-22 01:21:39.33095")
				.endValue(JSON, true)
				.build().toSQL());
	}
}
