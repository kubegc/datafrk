/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres.operators;

import io.github.kubesys.datafrk.core.operators.InsertData;
import io.github.kubesys.datafrk.core.operators.InsertDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class InsertPostgresDataBuilder extends InsertDataBuilder<InsertPostgresDataBuilder, InsertData> {

	@Override
	public InsertPostgresDataBuilder beginValue(String value, boolean json) {
		if (!json) {
			return beginValue(value);
		}
		stringBuilder.append(" VALUES('" + value + "'::json");
		return this;
	}

	@Override
	public InsertPostgresDataBuilder andValue(String value, boolean json) {
		if (!json) {
			return andValue(value);
		}
		stringBuilder.append(", '" + value + "'::json");
		return this;
	}

	@Override
	public InsertPostgresDataBuilder endValue(String value, boolean json) {
		if (!json) {
			return endValue(value);
		}
		stringBuilder.append(", '" + value + "'::json)");
		return this;
	}
	
}
