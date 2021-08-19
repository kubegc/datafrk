/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgre;

import io.github.kubesys.datafrk.core.operators.CheckDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CheckPostgreDatabase extends CheckDatabase {

	
	public CheckPostgreDatabase(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "SELECT u.datname FROM pg_catalog.pg_database u where u.datname='" + name + "'";
	}

}
