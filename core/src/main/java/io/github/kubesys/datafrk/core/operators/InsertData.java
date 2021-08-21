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
public class InsertData implements SQL {

	protected final String insertDataCommand;
	
	InsertData(String insertDataCommand) {
		super();
		this.insertDataCommand = insertDataCommand;
	}
	
	@Override
	public String toSQL() {
		return this.insertDataCommand;
	}
}
