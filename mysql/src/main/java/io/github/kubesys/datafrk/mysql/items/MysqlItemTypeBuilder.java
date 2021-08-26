/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.items;

import io.github.kubesys.datafrk.core.items.DatatimeType;
import io.github.kubesys.datafrk.core.items.ItemTypeBuilder;
import io.github.kubesys.datafrk.core.items.JsonType;
import io.github.kubesys.datafrk.core.items.VarcharType;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.2
 *
 */
public class MysqlItemTypeBuilder implements ItemTypeBuilder  {

	@Override
	public DatatimeType datatime() {
		return new MysqlDatatimeType();
	}

	@Override
	public VarcharType varchar(int len) {
		return new MysqlVarcharType(len);
	}

	@Override
	public JsonType json() {
		return new MysqlJsonType();
	}

}
