/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import io.github.kubesys.datafrk.postgres.operators.RemovePostgresDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class RemovePostgresDataBuilderTest {
		
	public static void main(String[] args) throws Exception {
		System.out.println(new RemovePostgresDataBuilder()
				.delete("test")
				.where("namespace").eq("default")
				.build().toSQL());
	}
}
