/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import io.github.kubesys.datafrk.core.DataContext;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DatabaseTest {

	public static void main(String[] args) throws Exception {
		DataContext context = DataContextBuilder.createDataContext();
		System.out.println(context.checkDababase(new CheckPostgresDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.createDatabase(new CreatePostgresDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.checkDababase(new CheckPostgresDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.dropDababase(new DropPostgresDatabase("abc")));
	}
}
