/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres.operators;

import io.github.kubesys.datafrk.core.items.ItemTypeBuilder;
import io.github.kubesys.datafrk.core.operators.CreateTable;
import io.github.kubesys.datafrk.druid.operators.CreateDruidTableBuilder;
import io.github.kubesys.datafrk.postgres.items.PostgresItemTypeBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreatePostgresTableBuilder extends CreateDruidTableBuilder<CreatePostgresTableBuilder, CreateTable> {

	@Override
	public ItemTypeBuilder getTypeBuilder() {
		return new PostgresItemTypeBuilder();
	}


}
