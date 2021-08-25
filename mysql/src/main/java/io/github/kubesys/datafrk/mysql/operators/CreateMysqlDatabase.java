/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.druid.operators.CreateDruidDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreateMysqlDatabase extends CreateDruidDatabase {

	
	public CreateMysqlDatabase(String name) {
		super(name);
	}

}
