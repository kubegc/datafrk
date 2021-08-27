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
		String sql = new QueryMysqlDataBuilder()
				.selectAll("abc")
				.where("name").like("abc")
				.and("data.metadata.name", true).like("abc")
				.orderBy("updated", true)
				.limit(2, 1)
				.build().toSQL();
		
		System.out.println(sql);
	}
	
}
