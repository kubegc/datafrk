/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import io.github.kubesys.datafrk.core.operators.CheckTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CheckPostgresTable extends CheckTable {

	public CheckPostgresTable(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "select count(*) as num from pg_class where relname = '" + name + "'";
	}

}
