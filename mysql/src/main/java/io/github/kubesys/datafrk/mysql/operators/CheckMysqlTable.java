/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.druid.operators.CheckDruidTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CheckMysqlTable extends CheckDruidTable {

	public CheckMysqlTable(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "SELECT count(*) as num FROM information_schema.TABLES t where "
				+ "TABLE_NAME='" + name + "' and TABLE_SCHEMA = '" + database + "'";
	}

}
