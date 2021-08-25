/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

import java.util.Collection;

import io.github.kubesys.datafrk.core.operators.CheckTable;
import io.github.kubesys.datafrk.core.operators.CreateTable;
import io.github.kubesys.datafrk.core.operators.DropTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public interface Database extends Schema {

	public boolean createTable(CreateTable createTable);
	
	public boolean checkTable(CheckTable checkTable);
	
	public boolean dropTable(DropTable dropTable);
	
	public Collection<Table<?>> tables();
	
	public Table<?> get(String name);
	
	public void close() throws Exception;
}
