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
public abstract class UpdateDataBuilder<S, T> extends ConditionBuilder<S, T> {
	
	public S update(String table) {
		stringBuilder.append("UPDATE " + table);
		return (S) this;
	}
	
	public S set(String label) {
		stringBuilder.append(" SET " + label);
		return (S) this;
	}
	
	public S andSet(String label) {
		stringBuilder.append(", " + label);
		return (S) this;
	}
	
}
