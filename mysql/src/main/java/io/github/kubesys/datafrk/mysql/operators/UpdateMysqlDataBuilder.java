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

}
