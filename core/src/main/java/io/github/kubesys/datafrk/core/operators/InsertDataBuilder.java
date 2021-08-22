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
public abstract class InsertDataBuilder<S, T> extends AbstractBuilder<S, T> {
	
	public S insertTo(String table) {
		stringBuilder.append("INSERT INTO " + table);
		return (S) this;
	}
	
	public S beginValue(String value) {
		stringBuilder.append(" VALUES('" + value + "'");
		return (S) this;
	}
	
	public S beginValue(int value) {
		stringBuilder.append(" VALUES(" + value);
		return (S) this;
	}
	
	public abstract S beginValue(String value, boolean json);
	
	public S andValue(String value) {
		stringBuilder.append(", '" + value + "'");
		return (S) this;
	}
	
	public S andValue(int value) {
		stringBuilder.append(", " + value);
		return (S) this;
	}
	
	public abstract S andValue(String value, boolean json);
	
	public S endValue(String value) {
		stringBuilder.append(", '" + value + "')");
		return (S) this;
	}
	
	public S endValue(int value) {
		stringBuilder.append(" ," + value + ")");
		return (S) this;
	}
	
	public abstract S endValue(String value, boolean json);
}
