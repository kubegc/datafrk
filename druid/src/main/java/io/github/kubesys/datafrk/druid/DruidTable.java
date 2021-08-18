/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid;

import io.github.kubesys.datafrk.core.Table;
import io.github.kubesys.datafrk.core.crud.DeleteItem;
import io.github.kubesys.datafrk.core.crud.InsertItem;
import io.github.kubesys.datafrk.core.crud.QueryItems;
import io.github.kubesys.datafrk.core.crud.UpdateItem;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DruidTable<ResultSet> implements Table<ResultSet> {

	protected final DruidExecutor executor;
	
	protected final String name;
	
	public DruidTable(DruidExecutor executor, String name) {
		super();
		this.executor = executor;
		this.name = name;
	}

	@Override
	public String getSchema() {
		return null;
	}

	@Override
	public ResultSet query(QueryItems query) {
		return null;
	}

	@Override
	public boolean insert(InsertItem insert) {
		return false;
	}

	@Override
	public boolean update(UpdateItem update) {
		return false;
	}

	@Override
	public boolean delete(DeleteItem delete) {
		return false;
	}
	
}
