/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

import java.sql.SQLException;
import java.util.Collection;

import io.github.kubesys.datafrk.core.Table;
import io.github.kubesys.datafrk.druid.DruidDatabase;
import io.github.kubesys.datafrk.druid.DruidExecutor;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.1
 *
 */
public class MysqlDatabase extends DruidDatabase {

	public MysqlDatabase(DruidExecutor executor) {
		super(executor);
		this.tables();
	}

	@Override
	public Collection<Table<?>> tables() {
		this.map.clear();
		for (String name : this.executor.execWithValue(
					listTableSQL(), listTableLabel())) {
			map.put(name, new MysqlTable(executor, name));
		}
		return map.values();
	}
	
	@Override
	public String listTableSQL() {
		try {
			return "SELECT DISTINCT * FROM information_schema.TABLES t "
					+ "where TABLE_SCHEMA = '" + executor.getConnection().getCatalog() + "'";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String listTableLabel() {
		return "TABLE_NAME";
	}

}
