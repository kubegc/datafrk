/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid;

import java.util.List;

import io.github.kubesys.datafrk.core.Database;
import io.github.kubesys.datafrk.core.Table;
import io.github.kubesys.datafrk.core.crud.CheckTable;
import io.github.kubesys.datafrk.core.crud.CreateTable;
import io.github.kubesys.datafrk.core.crud.DropTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DruidDatabase implements Database {

	protected final DruidExecutor executor;
	
	public DruidDatabase(DruidExecutor executor) {
		super();
		this.executor = executor;
	}

	@Override
	public String getSchema() {
		return null;
	}

	@Override
	public boolean createTable(CreateTable createTable) {
		return false;
	}

	@Override
	public boolean checkTable(CheckTable checkTable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dropTable(DropTable dropTable) {
		return false;
	}

	@Override
	public List<Table<?>> tables() {
		return null;
	}

	@Override
	public Table<?> get(String name) {
		return null;
	}
	
}
