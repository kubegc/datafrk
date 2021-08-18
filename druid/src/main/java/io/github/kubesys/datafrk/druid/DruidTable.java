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
	
	public DruidTable(DruidExecutor executor) {
		super();
		this.executor = executor;
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
