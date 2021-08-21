/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.operators;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class CreateTableBuilder extends AbstractBuilder<CreateTable> {
	
	public CreateTable build() {
		return new CreateTable(stringBuilder.toString());
	}
}
