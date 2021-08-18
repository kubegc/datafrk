/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

import java.util.Collection;

import io.github.kubesys.datafrk.core.crud.CheckTable;
import io.github.kubesys.datafrk.core.crud.CreateTable;
import io.github.kubesys.datafrk.core.crud.DropTable;
import io.github.kubesys.datafrk.core.crud.QueryTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public interface Database extends Schema {
	
	public boolean createTable(CreateTable createTable);
	
	public boolean checkTable(CheckTable checkTable);
	
	public boolean dropTable(DropTable dropTable);
	
	public Collection<Table<?>> tables(QueryTable queryTable, String label);
	
	public Table<?> get(String name);
}
