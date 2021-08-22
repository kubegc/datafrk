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
public abstract class QueryDataBuilder<S, T> extends AbstractBuilder<S, T> {
	
	public S selectAll(String table) {
		stringBuilder.append("SELECT * FROM " + table);
		return (S) this;
	}
	
	public S selectCount(String table) {
		stringBuilder.append("SELECT count(*) as count FROM " + table);
		return (S) this;
	}
	
	public S where(String label) {
		stringBuilder.append(" WHERE " + label);
		return (S) this;
	}
	
	public S and(String label) {
		stringBuilder.append(" and " + label);
		return (S) this;
	}
	
	public S eq(String value) {
		stringBuilder.append(" = '" + value + "'");
		return (S) this;
	}
	
	public S eq(int value) {
		stringBuilder.append(" = " + value);
		return (S) this;
	}
	
	public abstract S limit(int limit, int page);
}
