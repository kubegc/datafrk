/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres.operators;

import io.github.kubesys.datafrk.core.operators.CheckDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CheckPostgresDatabase extends CheckDatabase {

	
	public CheckPostgresDatabase(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "SELECT u.datname FROM pg_catalog.pg_database u where u.datname='" + name + "'";
	}

}
