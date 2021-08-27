/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.operators.InsertData;
import io.github.kubesys.datafrk.core.operators.InsertDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class InsertMysqlDataBuilder extends InsertDataBuilder<InsertMysqlDataBuilder, InsertData> {

	@Override
	public InsertMysqlDataBuilder beginValue(String value, boolean json) {
		if (!json) {
			return beginValue(value);
		}
		stringBuilder.append(" VALUES('" + value + "'");
		return this;
	}

	@Override
	public InsertMysqlDataBuilder andValue(String value, boolean json) {
		if (!json) {
			return andValue(value);
		}
		stringBuilder.append(", '" + value + "'");
		return this;
	}

	@Override
	public InsertMysqlDataBuilder endValue(String value, boolean json) {
		if (!json) {
			return endValue(value);
		}
		stringBuilder.append(", '" + value + "')");
		return this;
	}
	
}
