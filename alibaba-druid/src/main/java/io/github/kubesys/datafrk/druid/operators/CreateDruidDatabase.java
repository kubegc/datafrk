/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid.operators;

import io.github.kubesys.datafrk.core.operators.CreateDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreateDruidDatabase extends CreateDatabase {

	
	public CreateDruidDatabase(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "CREATE database " + name;
	}

}
