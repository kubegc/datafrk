/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgre;

import io.github.kubesys.datafrk.core.operators.CreateDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreatePostgreDatabase extends CreateDatabase {

	
	public CreatePostgreDatabase(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "CREATE database " + name;
	}

}
