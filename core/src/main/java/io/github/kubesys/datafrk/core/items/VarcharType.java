/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.items;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.2
 *
 */
public abstract class VarcharType implements ItemType  {

	protected int len;

	public VarcharType(int len) {
		super();
		this.len = len;
	}
	
}
