/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.operators;

import java.lang.reflect.Constructor;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
@SuppressWarnings("unchecked")
public abstract class AbstractBuilder<S, T> {
	
	protected StringBuilder stringBuilder = new StringBuilder();
	
	public S sql(String sql) {
		stringBuilder.append(sql);
		return (S) this;
	}
	
	public T build() throws Exception {
		String typename = getClass().getGenericSuperclass().getTypeName();
		int idx = typename.indexOf(",");
		String classname = typename.substring(idx + 1, typename.length() - 1).trim();
		Constructor<?> c = Class.forName(classname).getConstructor(String.class);
		return (T) c.newInstance(stringBuilder.toString());
	}
}


