/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

import io.github.kubesys.datafrk.core.operators.DeleteItem;
import io.github.kubesys.datafrk.core.operators.InsertItem;
import io.github.kubesys.datafrk.core.operators.QueryItem;
import io.github.kubesys.datafrk.core.operators.UpdateItem;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public interface Table<T> extends Schema {
	
	public T query(QueryItem query);
	
	public boolean insert(InsertItem insert);
	
	public boolean update(UpdateItem update);
	
	public boolean delete(DeleteItem delete);
	
	public String name();
}
