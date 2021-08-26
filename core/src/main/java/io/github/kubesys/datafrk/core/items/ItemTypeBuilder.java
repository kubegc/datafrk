/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core.items;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.2
 *
 */
public interface ItemTypeBuilder  {

	public DatatimeType datatime();
	
	public VarcharType varchar(int len);
	
	public JsonType json();
}
