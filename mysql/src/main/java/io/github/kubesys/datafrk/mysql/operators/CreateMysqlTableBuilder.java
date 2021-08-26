/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.items.ItemTypeBuilder;
import io.github.kubesys.datafrk.core.operators.CreateTable;
import io.github.kubesys.datafrk.druid.operators.CreateDruidTableBuilder;
import io.github.kubesys.datafrk.mysql.items.MysqlItemTypeBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreateMysqlTableBuilder extends CreateDruidTableBuilder<CreateMysqlTableBuilder, CreateTable> {

	@Override
	public CreateMysqlTableBuilder endCreate() {
		super.endCreate();
		stringBuilder.append("DEFAULT CHARSET=utf8");
		return this;
	}

	@Override
	public ItemTypeBuilder getTypeBuilder() {
		return new MysqlItemTypeBuilder();
	}

}
