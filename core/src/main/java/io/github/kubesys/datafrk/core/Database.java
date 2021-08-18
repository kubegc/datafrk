/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

import java.util.List;

import io.github.kubesys.datafrk.core.crud.CheckTable;
import io.github.kubesys.datafrk.core.crud.CreateTable;
import io.github.kubesys.datafrk.core.crud.DropTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public interface Database extends Schema {
	
	public boolean createTable(CreateTable createTable);
	
	public boolean checkTable(CheckTable checkTable);
	
	public boolean dropTable(DropTable dropTable);
	
	public List<Table<?>> tables();
	
	public Table<?> get(String name);
}
