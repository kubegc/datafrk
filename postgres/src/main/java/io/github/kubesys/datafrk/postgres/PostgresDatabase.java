/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import io.github.kubesys.datafrk.druid.DruidDatabase;
import io.github.kubesys.datafrk.druid.DruidExecutor;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class PostgresDatabase extends DruidDatabase {

	public PostgresDatabase(DruidExecutor executor) {
		super(executor);
	}

	@Override
	public String listTableSQL() {
		return null;
	}

	@Override
	public String listTableLabel() {
		return null;
	}

}
