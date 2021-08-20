/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import java.util.Properties;

import io.github.kubesys.datafrk.core.Database;
import io.github.kubesys.datafrk.druid.DruidDataContext;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class PostgresDataContext extends DruidDataContext {

	protected final PostgresDatabase database;
	
	public PostgresDataContext(Properties props) throws Exception {
		super(props);
		this.database = new PostgresDatabase(executor);
	}

	@Override
	public Database currentDatabase() {
		return this.database;
	}
}
