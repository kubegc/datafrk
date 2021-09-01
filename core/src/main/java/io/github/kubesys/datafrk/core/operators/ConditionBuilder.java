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
public abstract class ConditionBuilder<S, T> extends AbstractBuilder<S, T> {
	
	public S where(String label) {
		stringBuilder.append(" WHERE " + label);
		return (S) this;
	}
	
	public S where(String label, boolean json) {
		if (!json) {
			return where(label);
		}
		stringBuilder.append(" WHERE " + toJson(label));
		return (S) this;
	}
	
	public S where(String prefix, String label, boolean json) {
		if (!json) {
			return where(label);
		}
		stringBuilder.append(" WHERE " + toJson(prefix + "." + label));
		return (S) this;
	}
	
	public S and(String label) {
		stringBuilder.append(" and " + label);
		return (S) this;
	}
	
	public S and(String label, boolean json) {
		if (!json) {
			return and(label);
		}
		stringBuilder.append(" and " + toJson(label));
		return (S) this;
	}
	
	public S and(String prefix, String label, boolean json) {
		if (!json) {
			return and(label);
		}
		stringBuilder.append(" and " + toJson(prefix + "." + label));
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
	
	public S like(String value) {
		stringBuilder.append(" like '%" + value + "%'");
		return (S) this;
	}
	
	public abstract S eq(String value, boolean json);
	
	public abstract String toJson(String value);
}
