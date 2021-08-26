/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.operators;

import io.github.kubesys.datafrk.core.items.ItemType;
import io.github.kubesys.datafrk.core.items.ItemTypeBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public abstract class CreateTableBuilder<S, T> extends AbstractBuilder<S, T> {

	public abstract S createTable(String name);
	
	public S addItem(String name, ItemType type) {
		return addItem(name, type, false);
	}
	
	public abstract S addItem(String name, ItemType type, boolean primaryKey);
	
	public abstract S endCreate();
	
	public abstract ItemTypeBuilder getTypeBuilder();
}
