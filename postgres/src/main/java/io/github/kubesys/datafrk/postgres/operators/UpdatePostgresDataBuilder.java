/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres.operators;

import io.github.kubesys.datafrk.core.operators.UpdateData;
import io.github.kubesys.datafrk.core.operators.UpdateDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class UpdatePostgresDataBuilder extends UpdateDataBuilder<UpdatePostgresDataBuilder, UpdateData> {

	@Override
	public UpdatePostgresDataBuilder eq(String value, boolean json) {
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
					sb.append("->>'").append(splits[i]).append("'");
				} else {
					sb.append("->'").append(splits[i]).append("'");
				}
				
			}
			return sb.toString();
		}

}
