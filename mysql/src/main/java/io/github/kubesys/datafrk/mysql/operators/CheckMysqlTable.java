/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.operators.CheckTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CheckMysqlTable extends CheckTable {

	public CheckMysqlTable(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "SELECT * FROM information_schema.SCHEMATA where SCHEMA_NAME='" + name + "'";
	}

}
