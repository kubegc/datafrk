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
public class CommandSQL implements SQL {
	
	protected final String command;

	public CommandSQL(String command) {
		super();
		this.command = command;
	}
	
	@Override
	public String toSQL() {
		return this.command;
	}
}
