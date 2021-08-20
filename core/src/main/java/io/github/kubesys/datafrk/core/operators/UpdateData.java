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
public class UpdateData implements SQL {
	
	protected final String updateDataCommand;
	
	public UpdateData(String updateDataCommand) {
		super();
		this.updateDataCommand = updateDataCommand;
	}

	@Override
	public String toSQL() {
		return this.updateDataCommand;
	}
}
