/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres.operators;

import io.github.kubesys.datafrk.druid.operators.DropDruidTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DropPostgresTable extends DropDruidTable {

	public DropPostgresTable(String name) {
		super(name);
	}


}
