/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

import io.github.kubesys.datafrk.mysql.operators.QueryMysqlDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class QueryMysqlDataBuilderTest  {

	public static void main(String[] args) throws Exception {
		System.out.println(new QueryMysqlDataBuilder()
				.selectAll("test")
				.where("namespace").eq("default")
				.limit(1, 1)
				.build().toSQL());
	}
	
}
