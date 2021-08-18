/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

import java.util.List;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public interface Database extends Schema {
	
	public boolean createTable(String sql);
	
	public boolean updateTable(String sql);
	
	public boolean deleteTable(String name);
	
	public List<Table<?>> tables();
	
	public Table<?> get(String name);
}
