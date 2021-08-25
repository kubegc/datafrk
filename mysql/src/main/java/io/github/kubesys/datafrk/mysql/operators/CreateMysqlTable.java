/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.operators.CreateTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreateMysqlTable extends CreateTable {

	protected CreateMysqlTable(String createTableCommand) {
		super(createTableCommand);
	}
}
