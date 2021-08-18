/*

 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.sql;

import io.github.kubesys.datafrk.KubeSQLClient;
import io.github.kubesys.datafrk.clients.PostgreClient;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.2.0
 * @since   2020/4/23
 *
 */
public class KubeSQLClientTest {
	
	public static final String json = "{\r\n"
			+ "	\"kind\": \"Test\",\r\n"
			+ "	\"code\": 20000,\r\n"
			+ "	\"message\": null\r\n"
			+ "}";
	
	public static void main(String[] args) throws Exception {
		
		PostgreClient client = new PostgreClient(
				KubeSQLClient.createDataSourceFromResource(
						"jdbc:postgresql://127.0.0.1:5432/postgres", 
						"org.postgresql.Driver", 
						"postgres", "onceas").getConnection(), "test");
		client.createDatabase();
//		client.createTable("test");
//		client.insertObject("test", "test1", "default", "henry", "2021-01-02", "2021-01-02", json);
//		Thread.sleep(1000);
//		client.insertObject("test", "test2", "default", "henry", "2021-01-02", "2021-01-02", json);
//		System.out.println(client.query("test", "Test", 10, 1, null).toPrettyString());
//		Thread.sleep(1000);
//		client.updateObject("test", "test1", "default", "henry", "2021-02-02", json);
//		System.out.println(client.query("test", "Test", 10, 1, null).toPrettyString());
	}
}
