/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import io.github.kubesys.datafrk.core.operators.DropDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DropPostgresDatabase extends DropDatabase {

	
	public DropPostgresDatabase(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "DROP database " + name;
	}

}
