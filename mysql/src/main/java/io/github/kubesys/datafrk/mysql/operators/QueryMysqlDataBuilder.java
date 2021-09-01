/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.mysql.operators;

import io.github.kubesys.datafrk.core.operators.QueryData;
import io.github.kubesys.datafrk.core.operators.QueryDataBuilder;
import io.github.kubesys.datafrk.mysql.items.MysqlJsonType;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class QueryMysqlDataBuilder extends QueryDataBuilder<QueryMysqlDataBuilder, QueryData> {

	protected MysqlJsonType jsonType = new MysqlJsonType();
	
	@Override
	public QueryMysqlDataBuilder limit(int limit, int page) {
		int l = (limit <= 0) ? 10 : limit;
		int p = (page <= 1) ? 1 : page;
		stringBuilder.append(" LIMIT ").append((p - 1) * l).append(", ").append(p * l);
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

	// input: data.metadata.name
	// output: JSON_EXTRACT(data, '$.metadata.name')
	// https://www.yuque.com/kubesys/kube-frontend/lnbgaa
	@Override
	public String toJson(String value) {
		int idx = value.indexOf(".");
		return " JSON_EXTRACT(" + value.substring(0, idx)
		           + ", '$." + value.substring(idx + 1).replaceAll("#", ".") + "')";
	}
	
}
