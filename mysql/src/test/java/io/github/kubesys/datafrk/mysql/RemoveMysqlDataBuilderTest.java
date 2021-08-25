/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

import io.github.kubesys.datafrk.mysql.operators.RemoveMysqlDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class RemoveMysqlDataBuilderTest {
		
	public static void main(String[] args) throws Exception {
		System.out.println(new RemoveMysqlDataBuilder()
				.delete("test")
				.where("namespace").eq("default")
				.build().toSQL());
	}
}
