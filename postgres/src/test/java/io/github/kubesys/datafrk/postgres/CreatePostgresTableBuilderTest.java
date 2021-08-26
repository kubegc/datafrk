/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import io.github.kubesys.datafrk.core.items.ItemTypeBuilder;
import io.github.kubesys.datafrk.core.operators.CreateTable;
import io.github.kubesys.datafrk.core.operators.CreateTableBuilder;
import io.github.kubesys.datafrk.postgres.operators.CreatePostgresTableBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreatePostgresTableBuilderTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		CreateTableBuilder<?, CreateTable> tableBuilder = (CreateTableBuilder<?, CreateTable>) Class.forName(CreatePostgresTableBuilder.class.getName()).newInstance();
		ItemTypeBuilder typeBuilder =  tableBuilder.getTypeBuilder();
		tableBuilder = (CreateTableBuilder<?, CreateTable>) tableBuilder.createTable("test");
		tableBuilder = (CreateTableBuilder<?, CreateTable>)  tableBuilder.addItem("name",      typeBuilder.varchar(512), true);
		tableBuilder = (CreateTableBuilder<?, CreateTable>)  tableBuilder.addItem("namespace", typeBuilder.varchar(128), true);
		tableBuilder = (CreateTableBuilder<?, CreateTable>)  tableBuilder.addItem("apigroup",  typeBuilder.varchar(128), true);
		tableBuilder = (CreateTableBuilder<?, CreateTable>)  tableBuilder.addItem("created",   typeBuilder.datatime());
		tableBuilder = (CreateTableBuilder<?, CreateTable>)  tableBuilder.addItem("updated",   typeBuilder.datatime());
		tableBuilder = (CreateTableBuilder<?, CreateTable>)  tableBuilder.addItem("data",      typeBuilder.json());							
		tableBuilder = (CreateTableBuilder<?, CreateTable>)  tableBuilder.endCreate();	
		System.out.println(tableBuilder.build().toSQL());
	}

}
