/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.druid.operators.DropDruidDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DropMysqlDatabase extends DropDruidDatabase {

	
	public DropMysqlDatabase(String name) {
		super(name);
	}
}
