/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import java.util.Collection;

import io.github.kubesys.datafrk.core.DataContext;
import io.github.kubesys.datafrk.core.Database;
import io.github.kubesys.datafrk.core.Table;
import io.github.kubesys.datafrk.postgres.operators.CheckPostgresDatabase;
import io.github.kubesys.datafrk.postgres.operators.CheckPostgresTable;
import io.github.kubesys.datafrk.postgres.operators.CreatePostgresDatabase;
import io.github.kubesys.datafrk.postgres.operators.CreatePostgresTableBuilder;
import io.github.kubesys.datafrk.postgres.operators.DropPostgresDatabase;
import io.github.kubesys.datafrk.postgres.operators.DropPostgresTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DatabaseTest {

	public static void main(String[] args) throws Exception {
		DataContext context = DataContextBuilder.createDataContext();
		database(context);
		table(context.currentDatabase());
	}

	static String sql = "CREATE TABLE public.henry2019 (\r\n"
			+ "    name character varying(256)  NOT NULL,\r\n"
			+ "    artifactid character varying(128)  NOT NULL,\r\n"
			+ "    created timestamp without time zone  NOT NULL)";
	
	protected static void table(Database database) throws Exception {
		
		System.out.println(database.checkTable(new CheckPostgresTable("henry2019")));
		System.out.println(database.createTable(new CreatePostgresTableBuilder().sql(sql).build()));
		System.out.println(database.checkTable(new CheckPostgresTable("henry2019")));
		Collection<Table<?>> tables = database.tables();
		for (Table<?> t : tables) {
			System.out.println(t.name());
			System.out.println(t.schema());
		}
		Thread.sleep(10000);
		System.out.println(database.dropTable(new DropPostgresTable("henry2019")));
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
