/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.operators.CheckDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CheckMysqlDatabase extends CheckDatabase {

	
	public CheckMysqlDatabase(String name) {
		super(name);
	}

	@Override
	public String toSQL() {
		return "SELECT * FROM information_schema.TABLES t where t.table_name='" + name + "'";
	}

}
