/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres.items;

import io.github.kubesys.datafrk.druid.items.DruidDatatimeType;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.2
 *
 */
public class PostgresDatatimeType extends DruidDatatimeType  {

	@Override
	public String type() {
		return "timestamp";
	}

}
