/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.operators;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
@SuppressWarnings("unchecked")
public abstract class QueryDataBuilder<S, T> extends ConditionBuilder<S, T> {
	
	public S selectAll(String table) {
		stringBuilder.append("SELECT * FROM " + table);
		return (S) this;
	}
	
	public S selectCount(String table) {
		stringBuilder.append("SELECT count(*) as count FROM " + table);
		return (S) this;
	}
	
	public S orderBy(String item) {
		return orderBy(item, true);
	}
	
	public S orderBy(String item, boolean desc) {
		if (desc) {
			stringBuilder.append(" ORDER BY " + item + " desc");
		} else {
			stringBuilder.append(" ORDER BY " + item + " asc");
		}
		return (S) this;
	}
	
	public abstract S limit(int limit, int page);
}
