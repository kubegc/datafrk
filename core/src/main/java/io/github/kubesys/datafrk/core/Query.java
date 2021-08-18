/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public interface Query {
	
	public Query from(String table);
	
	public Query select(String item);
	
	public Query where(String item);
	
	public Query eq(String value);
	
	public Query sql(String sql);
	
	public DataSet execute();
}
