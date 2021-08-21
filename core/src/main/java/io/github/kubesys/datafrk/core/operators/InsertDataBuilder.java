/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.operators;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class InsertDataBuilder extends AbstractBuilder<InsertData> {
	
	public InsertData build() {
		return new InsertData(stringBuilder.toString());
	}
}
