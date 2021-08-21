/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.operators;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public abstract class AbstractBuilder<T extends CommandSQL> {
	
protected StringBuilder stringBuilder;
	
	protected boolean isEnd = false;
	
	public AbstractBuilder() {
		super();
	}
	
	@SuppressWarnings("rawtypes")
	public AbstractBuilder begin() {
		this.stringBuilder = new StringBuilder();
		return this;
	}

	@SuppressWarnings("rawtypes")
	public AbstractBuilder sql(String sql) {
		this.stringBuilder.append(sql);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public AbstractBuilder end() {
		this.isEnd = true;
		return this;
	}
	
	public abstract T build();
}
