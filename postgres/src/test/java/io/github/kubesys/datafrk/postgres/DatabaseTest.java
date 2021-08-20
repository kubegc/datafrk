/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import java.util.Collection;

import io.github.kubesys.datafrk.core.DataContext;
import io.github.kubesys.datafrk.core.Database;
import io.github.kubesys.datafrk.core.Table;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DatabaseTest {

	public static void main(String[] args) throws Exception {
		DataContext context = DataContextBuilder.createDataContext();
		database(context);
//		table(context.currentDatabase());
	}

	protected static void table(Database database) {
		Collection<Table<?>> tables = database.tables();
		for (Table<?> t : tables) {
			System.out.println(t.name());
			System.out.print(t.schema());
		}
	}

	protected static void database(DataContext context) throws InterruptedException {
		System.out.println(context.currentDatabase().schema());
		System.out.println(context.checkDababase(new CheckPostgresDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.createDatabase(new CreatePostgresDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.checkDababase(new CheckPostgresDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.dropDababase(new DropPostgresDatabase("abc")));
	}
}
