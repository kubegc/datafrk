/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import io.github.kubesys.datafrk.postgres.operators.QueryPostgresDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class QueryPostgresDataBuilderTest  {

	public static void main(String[] args) throws Exception {
		System.out.println(new QueryPostgresDataBuilder()
				.selectAll("test")
				.where("namespace").eq("default")
				.limit(1, 1)
				.build().toSQL());
	}
	
}
