/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.operators.RemoveData;
import io.github.kubesys.datafrk.core.operators.RemoveDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class RemoveMysqlDataBuilder extends RemoveDataBuilder<RemoveMysqlDataBuilder, RemoveData> {

	@Override
	public RemoveMysqlDataBuilder eq(String value, boolean json) {
		if (!json) {
			return eq(value);
		}
		stringBuilder.append(" = '" + value + "'::json");
		return this;
	}

	// input: data.metadata.name
	// output: JSON_EXTRACT(data, '$.metadata.name')
	@Override
	public String toJson(String value) {
		int idx = value.indexOf(".");
		return " JSON_EXTRACT(" + value.substring(0, idx) + ", '$." + value.substring(idx + 1) + "')";
	}

}
