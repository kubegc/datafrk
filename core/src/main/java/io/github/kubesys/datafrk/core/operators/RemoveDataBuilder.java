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
public abstract class RemoveDataBuilder<S, T> extends AbstractBuilder<S, T> {
		
	public S delete(String table) {
		stringBuilder.append("DELETE FROM " + table);
		return (S) this;
	}
	
	public S where(String label) {
		stringBuilder.append(" WHERE " + label);
		return (S) this;
	}
	
	public S and(String label) {
		stringBuilder.append(" " + label);
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
}
