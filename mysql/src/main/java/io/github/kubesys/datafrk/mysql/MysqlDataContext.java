/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

import io.github.kubesys.datafrk.core.Database;
import io.github.kubesys.datafrk.core.operators.CheckDatabase;
import io.github.kubesys.datafrk.druid.DruidDataContext;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.1
 *
 */
public class MysqlDataContext extends DruidDataContext {

	protected final MysqlDatabase database;
	
	public MysqlDataContext(Properties props) throws Exception {
		super(props);
		this.database = new MysqlDatabase(executor);
	}

	@Override
	public Database currentDatabase() {
		return this.database;
	}
	
	@Override
	public String defaultDriver() {
		return Driver.class.getName();
	}

	@Override
	public boolean checkDababase(CheckDatabase checkDatabase) {
		return this.executor.execWithValue(checkDatabase.toSQL(), "SCHEMA_NAME").size() != 0;
	}
}
