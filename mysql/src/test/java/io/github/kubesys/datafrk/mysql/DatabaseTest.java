/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

import java.util.Collection;

import io.github.kubesys.datafrk.core.DataContext;
import io.github.kubesys.datafrk.core.Database;
import io.github.kubesys.datafrk.core.Table;
import io.github.kubesys.datafrk.mysql.operators.CheckMysqlDatabase;
import io.github.kubesys.datafrk.mysql.operators.CheckMysqlTable;
import io.github.kubesys.datafrk.mysql.operators.CreateMysqlDatabase;
import io.github.kubesys.datafrk.mysql.operators.CreateMysqlTableBuilder;
import io.github.kubesys.datafrk.mysql.operators.DropMysqlDatabase;
import io.github.kubesys.datafrk.mysql.operators.DropMysqlTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DatabaseTest {

	public static void main(String[] args) throws Exception {
		DataContext context = DataContextBuilder.createDataContext();
//		database(context);
		table(context.currentDatabase());
	}

	static String sql = "CREATE TABLE henry2019 (name varchar(512), namespace varchar(128), "
			+ "apigroup varchar(128), created datetime, updated datetime, "
			+ "data json, primary key(name, namespace, apigroup)) DEFAULT CHARSET=utf8";
	
	protected static void table(Database database) throws Exception {
		System.out.println(database.tables().size());
		System.out.println(database.checkTable(new CheckMysqlTable("henry2019")));
		System.out.println(database.createTable(new CreateMysqlTableBuilder().sql(sql).build()));
		System.out.println(database.checkTable(new CheckMysqlTable("henry2019")));
		Collection<Table<?>> tables = database.tables();
		for (Table<?> t : tables) {
			System.out.println(t.name());
			System.out.println(t.schema());
		}
		Thread.sleep(10000);
		System.out.println(database.dropTable(new DropMysqlTable("henry2019")));
	}

	protected static void database(DataContext context) throws InterruptedException {
		System.out.println(context.currentDatabase().schema());
		System.out.println(context.checkDababase(new CheckMysqlDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.createDatabase(new CreateMysqlDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.checkDababase(new CheckMysqlDatabase("abc")));
		Thread.sleep(10000);
		System.out.println(context.dropDababase(new DropMysqlDatabase("abc")));
	}
}
