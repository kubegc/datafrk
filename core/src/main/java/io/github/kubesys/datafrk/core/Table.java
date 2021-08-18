/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

import io.github.kubesys.datafrk.core.crud.Delete;
import io.github.kubesys.datafrk.core.crud.Insert;
import io.github.kubesys.datafrk.core.crud.Query;
import io.github.kubesys.datafrk.core.crud.Update;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public interface Table<T> extends Schema {
	
	public T query(Query query);
	
	public boolean insert(Insert insert);
	
	public boolean update(Update update);
	
	public boolean delete(Delete delete);
}
