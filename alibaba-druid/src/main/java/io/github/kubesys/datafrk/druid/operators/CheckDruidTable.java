/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid.operators;

import io.github.kubesys.datafrk.core.operators.CheckTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.4
 *
 */
public abstract class CheckDruidTable extends CheckTable {

	protected String database;
	
	public CheckDruidTable(String name) {
		super(name);
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

}
