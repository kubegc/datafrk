/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql;

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
		return "SELECT relname FROM pg_class c WHERE relkind = 'r' "
				+ "AND relname not like 'pg_%' AND relname not like 'sql_%' "
				+ "ORDER BY relname";
	}

	@Override
	public String listTableLabel() {
		return "relname";
	}

}
