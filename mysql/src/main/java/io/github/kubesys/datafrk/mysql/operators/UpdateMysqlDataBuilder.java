/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.operators.UpdateData;
import io.github.kubesys.datafrk.core.operators.UpdateDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class UpdateMysqlDataBuilder extends UpdateDataBuilder<UpdateMysqlDataBuilder, UpdateData> {

	@Override
	public UpdateMysqlDataBuilder eq(String value, boolean json) {
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
