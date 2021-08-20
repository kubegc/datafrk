/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.operators;

import io.github.kubesys.datafrk.core.SQL;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreateTable implements SQL {
	
	protected final String sql;
	
	public CreateTable(String table) {
		super();
		this.sql = table;
	}

	@Override
	public String toSQL() {
		return this.sql;
	}
	
}
