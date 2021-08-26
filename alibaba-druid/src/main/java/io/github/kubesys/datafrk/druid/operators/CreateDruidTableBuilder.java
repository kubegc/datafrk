/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid.operators;

import java.util.ArrayList;
import java.util.List;

import io.github.kubesys.datafrk.core.items.ItemType;
import io.github.kubesys.datafrk.core.operators.CreateTableBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
@SuppressWarnings("unchecked")
public abstract class CreateDruidTableBuilder<S, T> extends CreateTableBuilder<S, T> {

	protected List<String> primaryKeys = new ArrayList<>();
	
	@Override
	public S createTable(String name) {
		stringBuilder.append("CREATE TABLE ").append(name).append(" (");
		return (S) this;
	}

	@Override
	public S addItem(String name, ItemType type, boolean primaryKey) {
		if (primaryKey) {
			primaryKeys.add(name);
		}
		stringBuilder.append(name).append(" ").append(type.type()).append(", ");
		return (S) this;
	}

	@Override
	public S endCreate() {
		if (primaryKeys.size() == 0) {
			stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
		} else {
			stringBuilder.append("primary key(");
			for (String key : primaryKeys) {
				stringBuilder.append(key).append(", ");
			}
			stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
			stringBuilder.append(")");
		}
		stringBuilder.append(") ");
		return (S) this;
	}

}
