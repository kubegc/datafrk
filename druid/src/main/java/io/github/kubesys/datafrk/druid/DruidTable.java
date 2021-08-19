/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid;

import java.sql.ResultSet;

import io.github.kubesys.datafrk.core.Table;
import io.github.kubesys.datafrk.core.crud.DeleteItem;
import io.github.kubesys.datafrk.core.crud.InsertItem;
import io.github.kubesys.datafrk.core.crud.QueryItem;
import io.github.kubesys.datafrk.core.crud.UpdateItem;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DruidTable implements Table<ResultSet> {

	protected final DruidExecutor executor;
	
	protected final String name;
	
	public DruidTable(DruidExecutor executor, String name) {
		super();
		this.executor = executor;
		this.name = name;
	}

	@Override
	// TODO
	public String getSchema() {
		return null;
	}

	@Override
	public ResultSet query(QueryItem query) {
		return this.executor.execWithResult(query.toSQL());
	}

	@Override
	public boolean insert(InsertItem insert) {
		return this.executor.execWithStatus(insert.toSQL());
	}

	@Override
	public boolean update(UpdateItem update) {
		return this.executor.execWithStatus(update.toSQL());
	}

	@Override
	public boolean delete(DeleteItem delete) {
		return this.executor.execWithStatus(delete.toSQL());
	}
	
}
