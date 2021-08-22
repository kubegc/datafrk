/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres.operators;

import io.github.kubesys.datafrk.core.operators.QueryData;
import io.github.kubesys.datafrk.core.operators.QueryDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class QueryPostgresDataBuilder extends QueryDataBuilder<QueryPostgresDataBuilder, QueryData> {

	@Override
	public QueryPostgresDataBuilder limit(int limit, int page) {
		int l = (limit <= 0) ? 10 : limit;
		int p = (page <= 1) ? 1 : page;
		stringBuilder.append(" LIMIT ").append(l).append(" OFFSET ").append((p - 1) * l);
		return this;
	}
	
}
