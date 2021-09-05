/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres.items;

import io.github.kubesys.datafrk.druid.items.DruidVarcharType;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.2
 *
 */
public class PostgresVarcharType extends DruidVarcharType {

	public PostgresVarcharType(int len) {
		super(len);
	}

}
