/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

import io.github.kubesys.datafrk.core.crud.DeleteItem;
import io.github.kubesys.datafrk.core.crud.InsertItem;
import io.github.kubesys.datafrk.core.crud.QueryItem;
import io.github.kubesys.datafrk.core.crud.UpdateItem;

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
}
