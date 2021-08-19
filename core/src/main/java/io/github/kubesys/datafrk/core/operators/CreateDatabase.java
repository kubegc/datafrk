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
public abstract class CreateDatabase implements SQL {
	
	protected final String name;

	public CreateDatabase(String name) {
		super();
		this.name = name;
	}
	
}