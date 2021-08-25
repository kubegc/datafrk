/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.operators.QueryData;
import io.github.kubesys.datafrk.core.operators.QueryDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class QueryMysqlDataBuilder extends QueryDataBuilder<QueryMysqlDataBuilder, QueryData> {

	@Override
	public QueryMysqlDataBuilder limit(int limit, int page) {
		int l = (limit <= 0) ? 10 : limit;
		int p = (page <= 1) ? 1 : page;
		stringBuilder.append(" LIMIT ").append(l).append(" OFFSET ").append((p - 1) * l);
		return this;
	}

	@Override
	public QueryMysqlDataBuilder eq(String value, boolean json) {
		if (!json) {
			return eq(value);
		}
		stringBuilder.append(" = '" + value + "'::json");
		return this;
	}
	
}
