/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.items;

import io.github.kubesys.datafrk.druid.items.DruidDatatimeType;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.2
 *
 */
public class MysqlDatatimeType extends DruidDatatimeType  {

	@Override
	public String type() {
		return "datetime";
	}

}
