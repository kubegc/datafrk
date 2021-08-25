/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.core;

import io.github.kubesys.datafrk.core.operators.CheckDatabase;
import io.github.kubesys.datafrk.core.operators.CreateDatabase;
import io.github.kubesys.datafrk.core.operators.DropDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public interface DataContext {
	
	public Database currentDatabase();

	public boolean createDatabase(CreateDatabase createDatabase);
	
	public boolean checkDababase(CheckDatabase checkDatabase);
	
	public boolean dropDababase(DropDatabase dropDatabase);
	
	public String defaultDriver();
	
}
