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
public class RemoveData implements SQL {
	
	protected final String removeDataCommand;
	
	RemoveData(String removeDataCommand) {
		super();
		this.removeDataCommand = removeDataCommand;
	}

	@Override
	public String toSQL() {
		return this.removeDataCommand;
	}
}
