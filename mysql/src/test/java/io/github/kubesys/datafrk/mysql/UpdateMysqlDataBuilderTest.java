/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

import io.github.kubesys.datafrk.mysql.operators.UpdateMysqlDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class UpdateMysqlDataBuilderTest {

	public static void main(String[] args) throws Exception {
		System.out.println(new UpdateMysqlDataBuilder()
				.update("test")
				.set("name").eq("henry")
				.andSet("apigroup").eq("doslab.io/v1")
				.where("name").eq("test")
				.and("apigroup").eq("doslab.io")
				.build().toSQL());
	}
}
