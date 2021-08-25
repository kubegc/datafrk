/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

import java.sql.SQLException;

import io.github.kubesys.datafrk.druid.DruidExecutor;
import io.github.kubesys.datafrk.druid.DruidTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.4
 *
 */
public class MysqlTable extends DruidTable {

	public MysqlTable(DruidExecutor executor, String name) {
		super(executor, name);
	}

	@Override
	public String schema() {
		try {
			return this.executor.execWithValue(
					"show create table " + executor.getConnection().getCatalog() + "." + name, 
					"Create Table").get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
