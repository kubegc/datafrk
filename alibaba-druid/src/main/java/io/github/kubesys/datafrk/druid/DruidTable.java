/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid;

import java.sql.ResultSet;

import io.github.kubesys.datafrk.core.Table;
import io.github.kubesys.datafrk.core.operators.RemoveData;
import io.github.kubesys.datafrk.core.operators.InsertData;
import io.github.kubesys.datafrk.core.operators.QueryData;
import io.github.kubesys.datafrk.core.operators.UpdateData;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public abstract class DruidTable implements Table<ResultSet> {

	protected final DruidExecutor executor;
	
	protected final String name;
	
	public DruidTable(DruidExecutor executor, String name) {
		super();
		this.executor = executor;
		this.name = name;
	}

	@Override
	public ResultSet query(QueryData query) {
		if (check(query.toSQL().toLowerCase(), "from")) {
			return this.executor.execWithResult(query.toSQL());
		} else {
			throw new RuntimeException("the table name in " + query.toSQL() + " is not " + this.name);
		}
	}

	@Override
	public boolean insert(InsertData insert) {
		if (check(insert.toSQL().toLowerCase(), "into")) {
			return this.executor.execWithStatus(insert.toSQL());
		} else {
			throw new RuntimeException("the table name in " + insert.toSQL() + " is not " + this.name);
		}
	}

	@Override
	public boolean update(UpdateData update) {
		if (check(update.toSQL().toLowerCase(), "update")) {
			return this.executor.execWithStatus(update.toSQL());
		} else {
			throw new RuntimeException("the table name in " + update.toSQL() + " is not " + this.name);
		}
	}

	@Override
	public boolean delete(RemoveData delete) {
		if (check(delete.toSQL().toLowerCase(), "from")) {
			return this.executor.execWithStatus(delete.toSQL());
		} else {
			throw new RuntimeException("the table name in " + delete.toSQL() + " is not " + this.name);
		}
	}

	@Override
	public String name() {
		return this.name;
	}
	
	private boolean check(String sql, String key) {
		int idx = sql.indexOf(key);
		String substring = sql.substring(idx + 1 + key.length());
		return (substring.length() > name.length()) 
				? substring.startsWith(this.name.toLowerCase() + " ")
						: substring.startsWith(this.name.toLowerCase());
	}
	
}
