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

	@Override
	public QueryPostgresDataBuilder eq(String value, boolean json) {
		if (!json) {
			return eq(value);
		}
		stringBuilder.append(" = '" + value + "'::json");
		return this;
	}

	// input: data.metadata.name
	// output: data->'metadata'->>'activeName'
	@Override
	public String toJson(String value) {
		StringBuilder sb = new StringBuilder();
		String[] splits = value.split("\\.");
		for (int i = 0; i < splits.length; i++) {
			if (i == 0) {
				sb.append(" ").append(splits[i]);
			} else if (i == splits.length - 1) {
				// https://www.yuque.com/kubesys/kube-frontend/lnbgaa#VOZA2
				sb.append("->>'").append(splits[i].replace("#", ".")).append("'");
			} else {
				sb.append("->'").append(splits[i]).append("'");
			}
			
		}
		return sb.toString();
	}
	
}
