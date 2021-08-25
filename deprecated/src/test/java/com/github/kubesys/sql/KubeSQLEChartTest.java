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
public class KubeSQLEChartTest {
	
	
	public static void main(String[] args) throws Exception {
		
		PostgreClient client = new PostgreClient(
				KubeSQLClient.createDataSourceFromResource(
						"jdbc:postgresql://119.8.188.235:30306/kube", 
						"org.postgresql.Driver", 
						"postgres", "onceas").getConnection(), "kube");
//		System.out.println(client.num("pods", "status.phase", "Succeeded"));
//		System.out.println(client.num("pods", "", ""));
		System.out.println(client.sum("nodes", "status.allocatable.memory"));
	}
}
