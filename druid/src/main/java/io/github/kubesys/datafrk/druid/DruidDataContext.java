/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid;

import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

import io.github.kubesys.datafrk.core.DataContext;
import io.github.kubesys.datafrk.core.Database;
import io.github.kubesys.datafrk.core.crud.CheckDatabase;
import io.github.kubesys.datafrk.core.crud.CreateDatabase;
import io.github.kubesys.datafrk.core.crud.DropDatabase;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DruidDataContext implements DataContext {

	protected final DruidExecutor executor;
	
	/**
	 * Properties props = new Properties(); 
	 * 
	 * props.put("druid.driverClassName",defaultDriver()); 
	 * props.put("druid.url", url); 
	 * props.put("druid.username", getUser()); 
	 * props.put("druid.password", getPwd());
	 * props.put("druid.initialSize", 10); 
	 * props.put("druid.maxActive", 100);
	 * props.put("druid.maxWait", 0);
	 * 
	 * @param props props
	 * @throws Exception exception
	 */
	public DruidDataContext(Properties props) throws Exception {
		try (DruidDataSource dataSource = new DruidDataSource()) {
			dataSource.configFromPropety(props);
			this.executor = new DruidExecutor(dataSource.getConnection());
		} 
	}

	@Override
	public Database currentDatabase() {
		return null;
	}

	@Override
	public boolean createDatabase(CreateDatabase createDatabase) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkDababase(CheckDatabase dropDatabase) {
		return false;
	}

	@Override
	public boolean dropDababase(DropDatabase dropDatabase) {
		return false;
	}
}
