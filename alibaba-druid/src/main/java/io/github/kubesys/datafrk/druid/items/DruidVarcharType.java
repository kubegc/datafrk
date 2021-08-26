/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid.items;

import io.github.kubesys.datafrk.core.items.VarcharType;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.2
 *
 */
public abstract class DruidVarcharType extends VarcharType  {

	public DruidVarcharType(int len) {
		super(len);
	}

	@Override
	public String type() {
		return "varchar(" + len + ")";
	}
}
